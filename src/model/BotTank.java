package model;

import java.awt.Rectangle;
import java.util.List;
import java.util.Random;

import Enumtype.Direction;
import Enumtype.TankType;
import util.ImageUtil;
import view.GamePanel;

/**
 * ����̹���� Ϊ�˿�����Ϸ�Ѷȣ�����̹����������ƶ��ĸ���Ӧ�ÿ���Ϊ�����������С(�������·���
 * ���ҵ���̹�����ÿ���ƶ��ķ��򶼲�ͬ������ĳһ�����������ƶ���ʱ��ҲӦ�ò���ͬ
 *
 */

public class BotTank extends Tank {

	private Random random = new Random();
	private Direction dir;// �ƶ�����
	private int freshTime = GamePanel.FRESHTIME;// ˢ��ʱ�䣬������Ϸ����ˢ��ʱ��
	private int moveTimer = 0;// �ƶ���ʱ��
		
	private boolean pause= false;//����̹����ͣ״̬
	
	//����̹�˹��췽��
	public BotTank(int x, int y, String url, GamePanel gamePanel, TankType type) {
		super(x, y, url, gamePanel, type);
		dir = Direction.Down;// �ƶ�����Ĭ������
		setAttackCoolDownTime(1000);// ���ù�����ȴʱ��
	}


//��ȡ̹����ͣ״̬
	public boolean isPause() {
		return pause;
	}
//����̹����ͣ״̬
	public void setPause(boolean pause) {
		this.pause = pause;
	}

	public void go() {
		if (isAttackCoolDown()) {// �����ȴ�������Ϳ��Թ���
			attack();// ����
		}
		if (moveTimer > random.nextInt(1500) + 500) {// ����ƶ���ʱ�����������0.5~2.0�룬�����һ������
			dir = randomDirection();
			moveTimer = 0;// ���ü�ʱ��
		} else {
			moveTimer += freshTime;// ��ʱ������ˢ��ʱ�����
		}
		switch (dir) {// ���ݷ���ѡ�����ĸ������ƶ�
		case Up:
			upWard();
			break;
		case Down:
			downWard();
			break;
		case Left:
			leftWard();
			break;
		case Right:
			rightWard();
			break;
		}
	}

private boolean hitWall(int x,int y) {
	Rectangle next = new Rectangle(x,y,width-3,height-3);// ����̹���ƶ����Ŀ������
	List<Wall> walls=gamePanel.getWalls();//��ȡ����ǽ��
	for(int i=0,length=walls.size();i<length;i++) {//��������ǽ��
		Wall w=walls.get(i);//��ȡǽ�����
		if(w instanceof GrassWall) {//����ǲݵ�
			continue;//ִ����һ��ѭ��
		}else if (w.hit(next)) {//����ǲݵ�
			return true;//����ײ��ǽ��
		}
	}
	return false;
}

	@Override
	public Rectangle getRect() {
		return new Rectangle(x, y, width - 3, height - 3);
	}



	@Override
	// ��д����������ÿ��ֻ��4%���ʻᴥ�����๥������
	public void attack() {
		int rnum = random.nextInt(100);// �������������Χ��0-99
		if (rnum < 4) {// ��������С��4
			super.attack();// ִ�и��๥������
		}
	}

	// ��д�ƶ������ı߽��¼�

	protected void moveToBorder() {
		if (x < 0) {// ���̹�˺�����С��0
			x = 0;// ��̹�˺��������0
			dir = randomDirection();// ��������ƶ�����
		} else if (x > gamePanel.getWidth() - width) {// ���̹�˺����곬�������ֵ
			x = gamePanel.getWidth() - width;// ��̹�˺����걣�����ֵ
			dir = randomDirection();// ��������ƶ�����
		}
		if (y < 0) {// ���̹��������С��0
			y = 0;// ��̹�����������0
			dir = randomDirection();// ��������ƶ�����
		} else if (y > gamePanel.getHeight() - height) {// ���̹�������곬�������Χ
			y = gamePanel.getHeight() - height;// ��̹�������걣�����ֵ
			dir = randomDirection();// ��������ƶ�����
		}

	}
	
	private Direction randomDirection() {
		Direction[]dirs=Direction.values();// ��ȡ�������ö��ֵ
		Direction oldDir=dir;//����ԭ���ķ���
		Direction newDir=dirs[random.nextInt(4)];
		if(oldDir==newDir||newDir==Direction.Up) {
			// �������̹��ԭ���ķ���ͱ�������ķ�����ͬ��
			//���ߵ���̹���µķ��������ϣ�
			//��ô�������һ�η���
			//��ô����Ŀ����ʹ����̹������һ��ط����£��ĸ�������
			return dirs[random.nextInt(4)];
		}
		return newDir;
	}



}

