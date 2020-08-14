package model;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import Enumtype.Direction;
import Enumtype.TankType;
import util.ImageUtil;
import view.GamePanel;

public class Bullet extends DisplayableImage {
	Direction direction;
	static  int dx ;// �ӵ�ͼƬ��λҪ��ȥ�Ŀ�ߣ�����ȷ���ӵ������λ��
	static  int dy ;//���Tank���attack()����
	private  GamePanel gamePanel;// ��Ϸ���
	private int speed = 4;// �ӵ������ٶ�
	private boolean alive = true;// �ӵ��Ƿ���
	private String upImage = ImageUtil.BULLET_UP_IMAGE_URL;// �ӵ�ͼƬ
	private String downImage = ImageUtil.BULLET_DOWN_IMAGE_URL;
	private String rightImage = ImageUtil.BULLET_RIGHT_IMAGE_URL;
	private String leftImage = ImageUtil.BULLET_LEFT_IMAGE_URL;
	TankType owner;// �����ӵ���̹������

	private boolean isHitIronWall = false;

	public Bullet(int x, int y, Direction direction, GamePanel gamePanel, TankType owner) {
		super(x, y);// ���ø��๹�췽��
		this.direction = direction;
		if(owner.equals(TankType.PLAYER1)||owner.equals(TankType.PLAYER2)||owner.equals(TankType.BOT2)||owner.equals(TankType.DJBOT2)) {
			this.speed=16;//�����̹�˺�Ѹ����̹�˵��ӵ��ٶ�����Ϊ16
		}
		switch (direction) {
		case Up:
			try {
				dx=8;
				dy=10;
				image = ImageIO.read(new File(upImage));
				this.width = image.getWidth();// ��ΪͼƬ��
				this.height = image.getHeight();// ��ΪͼƬ��
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case Down:
			try {
				dx=8;
				dy=10;
				image = ImageIO.read(new File(downImage));
				this.width = image.getWidth();// ��ΪͼƬ��
				this.height = image.getHeight();// ��ΪͼƬ��
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case Right:			
			try {
				dx=10;
				dy=8;
			image = ImageIO.read(new File(rightImage));
			this.width = image.getWidth();// ��ΪͼƬ��
			this.height = image.getHeight();// ��ΪͼƬ��
		} catch (IOException e) {
			e.printStackTrace();
		}
		break;
		case Left:			
			try {
				dx=10;
				dy=8;
			image = ImageIO.read(new File(leftImage));
			this.width = image.getWidth();// ��ΪͼƬ��
			this.height = image.getHeight();// ��ΪͼƬ��
		} catch (IOException e) {
			e.printStackTrace();
		}
		break;
		}
		this.gamePanel = gamePanel;
		this.owner = owner;
	}

	public void move() {
		switch(direction) {
		case Up:// �������
			upward();// �����ƶ�
			break;
		case Down:// �������
			downward();// �����ƶ�
			break;
		case Left:// �������
			leftward();// �����ƶ�
			break;
		case Right:// �������
			rightward();// �����ƶ�
			break;
	}
}
	/**
	 * �����ƶ�
	 */
	private void leftward() {
		x -= speed;// ���������
		moveToBorder();// �ƶ������߽�ʱ�����ӵ�
	}

	/**
	 * �����ƶ�
	 */
	private void rightward() {
		x += speed;// ����������
		moveToBorder();// �ƶ������߽�ʱ�����ӵ�
	}

	/**
	 * �����ƶ�
	 */
	private void upward() {
		y -= speed;// ���������
		moveToBorder();// �ƶ������߽�ʱ�����ӵ�
	}

	/**
	 * �����ƶ�
	 */
	private void downward() {
		y += speed;// ����������
		moveToBorder();// �ƶ������߽�ʱ�����ӵ�
	}

	private void moveToBorder() {
		if(x<0||x>520-getWidth()||y<0||y>560-getHeight()) {
			//����ӵ������뿪��Ϸ���
			this.dispose();//�����ӵ�
		}
		
	}
	//synchronized �ؼ��֣����������������,�൱�ڲ�����һ���̣߳������߳�A�������е��������ʱ,��Ҫ�����û�������߳�B������C�� D�ȣ��������������(���߸��������ͬ������)���еĻ�Ҫ������ʹ��synchronized�������߳�B������C ��D����������������������д��߳�A,û�еĻ�,����������,Ȼ��ֱ�����С�
	private synchronized void dispose() {
		this.alive=false;
	}

public boolean isAlive() {
		
		return alive;
	}
	

	/**
	 * ���л���
	 */
	public void hitBase() {
		BaseWall b = gamePanel.getBase();// ��ȡ����
		if (this.hit(b)) {// ����ӵ����л���
			this.dispose();// �ӵ�����
			b.setAlive(false);// ��������
		}
	}
	/**
	 * ����ǽ��
	 */
	public void hitWall() {
		List<Wall> walls = gamePanel.getWalls();// ��ȡ����ǽ��
		for (int i = 0, lengh = walls.size(); i < lengh; i++) {// ��������ǽ��
			Wall w = walls.get(i);// ��ȡǽ�����
			if (this.hit(w)) {// ����ӵ�����ǽ��
				if (w instanceof BrickWall) {// �����שǽ
//qx					new AudioPlayer(AudioUtil.HIT).new AudioThread().start();
					this.dispose();
					//alive = false;// �ӵ�����
					w.setAlive(false);// שǽ����
				}
				if (w instanceof IronWall) {// ����Ǹ�ש
					//alive = false;// �ӵ�����
					this.dispose();
					if(this.isHitIronWall) {
						w.setAlive(false);
					}
//qx					new AudioPlayer(AudioUtil.HIT).new AudioThread().start();
				}
				if(w instanceof River0Wall||w instanceof River2Wall||w instanceof River1Wall) {
					//����Ǻ�����ʲô������
					}
				if(w instanceof GrassWall) {
					//����ǲݵ���ʲô������
					
				}
			}
		}
	}

	/**
	 * ����̹��
	 */
	public void hitTank() {
		List<Tank> tanks = gamePanel.getTanks();// ��ȡ����̹�˵ļ���
		for (int i = 0, lengh = tanks.size(); i < lengh; i++) {// ����̹�˼���
			Tank t = tanks.get(i);// ��ȡ̹�˶���
			if (t.isAlive() && this.hit(t)) {// ���̹���Ǵ��Ĳ����ӵ�������̹��
				switch (owner) {// �ж��ӵ�����ʲô̹��
				case PLAYER1:// ��������1���ӵ���Ч��ͬ��
				case PLAYER2:// ��������2���ӵ�
					if (t instanceof BotTank) {// ������е�̹���ǵ���
						//alive = false;// �ӵ�����
						this.dispose();
							t.setHit(true);
//							System.out.println("Hit!");
					} else if (t instanceof Tank) {// ������е������
						//alive = false;// �ӵ�����
						this.dispose();
					}
					break;
				case BOT1:// ����ǵ��Ե��ӵ�
				case BOT2:
				case BOT3:
				case BOT4:
				case DJBOT1:
				case DJBOT2:	
				case DJBOT3:
				case DJBOT4:			
					if (t instanceof BotTank) {// ������е�̹���ǵ���
						//alive = false;// �ӵ�����
						this.dispose();
					} else if (t instanceof Tank) {// ������е������
						//alive = false;// �ӵ�����
						this.dispose();
						t.setHit(true);
						// ���̹���е�
					}
					break;
				    default:// Ĭ��
					//alive = false;// �ӵ�����
					this.dispose();
					t.setAlive(false);// ̹������
				}
			}
		}
	}

	/**
	 * �ӵ�����
	 */
	public void hitBullet() {
		List<Bullet> bullets=gamePanel.getBullets();
		for(int i=0;i<bullets.size();i++) {
			Bullet b=bullets.get(i);
			if(this.alive) {
				if(this.hit(b)&&this.owner!=b.owner) {
					//this.alive=false;
					b.dispose();//�����ӵ�
					this.dispose();//�����ӵ�
				}
			}
		}
	}
	public void setIsHitIronWall(boolean b) {//�����ӵ����Դ򴩸�ש
		this.isHitIronWall=b;
	}
 
	
}
