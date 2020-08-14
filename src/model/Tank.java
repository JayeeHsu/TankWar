package model;

import java.util.List;
import java.awt.Point;
import java.awt.Rectangle;

import Enumtype.Direction;
import Enumtype.TankType;
import util.ImageUtil;
import view.GamePanel;

public class Tank extends DisplayableImage {
	GamePanel gamePanel;// ��Ϸ���
	Direction direction = Direction.Up;// �ƶ�����
	protected boolean alive = true;// �Ƿ���
	protected int speed = 2;// �ƶ��ٶ�
	private boolean attackCoolDown = true;// ������ȴ״̬
	private int attackCoolDownTime = 500;// ������ȴʱ�䣬����
	TankType type;// ̹������
	private String upImage;// �����ƶ�ʱ��ͼƬ1
	private String upImage2;// �����ƶ�ʱ��ͼƬ2
	private String downImage;// �����ƶ�ʱ��ͼƬ1
	private String downImage2;// �����ƶ�ʱ��ͼƬ2
	private String rightImage;// �����ƶ�ʱ��ͼƬ1
	private String rightImage2;// �����ƶ�ʱ��ͼƬ2
	private String leftImage;// �����ƶ�ʱ��ͼƬ1
	private String leftImage2;// �����ƶ�ʱ��ͼƬ2
	private int nowImageNum = 1;// ���ڼ�¼̹��������ͼƬ1����2

	private int life = 1;// ����ֵ
	private int starNum = 0;// �Ե������ǵ����������ӿ칥��

	private boolean Hit = false;// �����ж�=̹���Ƿ񱻻���

	/**
	 * ̹�˹��췽���� x��ʼ�������� y��ʼ�������� url ͼƬ·�� gamePanel��Ϸ��� type̹������
	 */
	public Tank(int x, int y, String url, GamePanel gamePanel, TankType type) {
		super(x, y, url);
		this.gamePanel = gamePanel;
		this.type = type;
		direction = Direction.Up;// ��ʼ����������
		switch (type) {// �ж�̹������
		case PLAYER1:// ��������1
			upImage = ImageUtil.PLAYER1_LEVEL1_UP_IMAGE_URL;// ��¼���1�ĸ������ͼƬ
			downImage = ImageUtil.PLAYER1_LEVEL1_DOWN_IMAGE_URL;
			rightImage = ImageUtil.PLAYER1_LEVEL1_RIGHT_IMAGE_URL;
			leftImage = ImageUtil.PLAYER1_LEVEL1_LEFT_IMAGE_URL;
			upImage2 = ImageUtil.PLAYER1_LEVEL1_UP_IMAGE2_URL;// ��¼���1�ĸ������ͼƬ2
			downImage2 = ImageUtil.PLAYER1_LEVEL1_DOWN_IMAGE2_URL;
			rightImage2 = ImageUtil.PLAYER1_LEVEL1_RIGHT_IMAGE2_URL;
			leftImage2 = ImageUtil.PLAYER1_LEVEL1_LEFT_IMAGE2_URL;
			this.speed = 4;
			break;
		case PLAYER2:// ��������2
			upImage = ImageUtil.PLAYER2_LEVEL1_UP_IMAGE_URL;// ��¼���2�ĸ������ͼƬ
			downImage = ImageUtil.PLAYER2_LEVEL1_DOWN_IMAGE_URL;
			rightImage = ImageUtil.PLAYER2_LEVEL1_RIGHT_IMAGE_URL;
			leftImage = ImageUtil.PLAYER2_LEVEL1_LEFT_IMAGE_URL;
			upImage2 = ImageUtil.PLAYER2_LEVEL1_UP_IMAGE2_URL;// ��¼���2�ĸ������ͼƬ
			downImage2 = ImageUtil.PLAYER2_LEVEL1_DOWN_IMAGE2_URL;
			rightImage2 = ImageUtil.PLAYER2_LEVEL1_RIGHT_IMAGE2_URL;
			leftImage2 = ImageUtil.PLAYER2_LEVEL1_LEFT_IMAGE2_URL;
			this.speed = 4;
			break;
		case BOT1:// �����һ����ͨ����
			upImage = ImageUtil.BOT_LEVEL1_UP_IMAGE_URL;// ��¼�����ĸ������ͼƬ
			downImage = ImageUtil.BOT_LEVEL1_DOWN_IMAGE_URL;
			rightImage = ImageUtil.BOT_LEVEL1_RIGHT_IMAGE_URL;
			leftImage = ImageUtil.BOT_LEVEL1_LEFT_IMAGE_URL;
			upImage2 = ImageUtil.BOT_LEVEL1_UP_IMAGE2_URL;// ��¼�����ĸ������ͼƬ2
			downImage2 = ImageUtil.BOT_LEVEL1_DOWN_IMAGE2_URL;
			rightImage2 = ImageUtil.BOT_LEVEL1_RIGHT_IMAGE2_URL;
			leftImage2 = ImageUtil.BOT_LEVEL1_LEFT_IMAGE2_URL;
			break;
		case BOT2:// ����Ƕ�����ͨ����
			upImage = ImageUtil.BOT_LEVEL2_UP_IMAGE_URL;
			downImage = ImageUtil.BOT_LEVEL2_DOWN_IMAGE_URL;
			rightImage = ImageUtil.BOT_LEVEL2_RIGHT_IMAGE_URL;
			leftImage = ImageUtil.BOT_LEVEL2_LEFT_IMAGE_URL;
			upImage2 = ImageUtil.BOT_LEVEL2_UP_IMAGE2_URL;
			downImage2 = ImageUtil.BOT_LEVEL2_DOWN_IMAGE2_URL;
			rightImage2 = ImageUtil.BOT_LEVEL2_RIGHT_IMAGE2_URL;
			leftImage2 = ImageUtil.BOT_LEVEL2_LEFT_IMAGE2_URL;
			this.speed = 4;
			break;
		case BOT3:// �����������ͨ����
			upImage = ImageUtil.BOT_LEVEL3_UP_IMAGE_URL;// ��¼�����ĸ������ͼƬ
			downImage = ImageUtil.BOT_LEVEL3_DOWN_IMAGE_URL;
			rightImage = ImageUtil.BOT_LEVEL3_RIGHT_IMAGE_URL;
			leftImage = ImageUtil.BOT_LEVEL3_LEFT_IMAGE_URL;
			upImage2 = ImageUtil.BOT_LEVEL3_UP_IMAGE2_URL;// ��¼�����ĸ������ͼƬ2
			downImage2 = ImageUtil.BOT_LEVEL3_DOWN_IMAGE2_URL;
			rightImage2 = ImageUtil.BOT_LEVEL3_RIGHT_IMAGE2_URL;
			leftImage2 = ImageUtil.BOT_LEVEL3_LEFT_IMAGE2_URL;
			this.life = 2;
			break;
		case BOT4:// ������ļ���ͨ����
			upImage = ImageUtil.BOT_LEVEL4_UP_IMAGE_URL;// ��¼�����ĸ������ͼƬ
			downImage = ImageUtil.BOT_LEVEL4_DOWN_IMAGE_URL;
			rightImage = ImageUtil.BOT_LEVEL4_RIGHT_IMAGE_URL;
			leftImage = ImageUtil.BOT_LEVEL4_LEFT_IMAGE_URL;
			upImage2 = ImageUtil.BOT_LEVEL4_UP_IMAGE2_URL;// ��¼�����ĸ������ͼƬ2
			downImage2 = ImageUtil.BOT_LEVEL4_DOWN_IMAGE2_URL;
			rightImage2 = ImageUtil.BOT_LEVEL4_RIGHT_IMAGE2_URL;
			leftImage2 = ImageUtil.BOT_LEVEL4_LEFT_IMAGE2_URL;
			this.life = 4;
			this.speed = 1;
			break;
		case DJBOT1:// �����һ�����ߵ���
			upImage = ImageUtil.BOT_LEVEL1_UP_IMAGE_URL;// ��¼�����ĸ������ͼƬ
			downImage = ImageUtil.BOT_LEVEL1_DOWN_IMAGE_URL;
			rightImage = ImageUtil.BOT_LEVEL1_RIGHT_IMAGE_URL;
			leftImage = ImageUtil.BOT_LEVEL1_LEFT_IMAGE_URL;
			upImage2 = ImageUtil.BOT_LEVEL1_UP_IMAGE2_URL;// ��¼�����ĸ������ͼƬ2
			downImage2 = ImageUtil.BOT_LEVEL1_DOWN_IMAGE2_URL;
			rightImage2 = ImageUtil.BOT_LEVEL1_RIGHT_IMAGE2_URL;
			leftImage2 = ImageUtil.BOT_LEVEL1_LEFT_IMAGE2_URL;
			break;
		case DJBOT2:// ����Ƕ������ߵ���
			upImage = ImageUtil.DJBOT_LEVEL2_UP_IMAGE_URL;// ��¼�����ĸ������ͼƬ
			downImage = ImageUtil.DJBOT_LEVEL2_DOWN_IMAGE_URL;
			rightImage = ImageUtil.DJBOT_LEVEL2_RIGHT_IMAGE_URL;
			leftImage = ImageUtil.DJBOT_LEVEL2_LEFT_IMAGE_URL;
			upImage2 = ImageUtil.DJBOT_LEVEL2_UP_IMAGE2_URL;// ��¼�����ĸ������ͼƬ2
			downImage2 = ImageUtil.DJBOT_LEVEL2_DOWN_IMAGE2_URL;
			rightImage2 = ImageUtil.DJBOT_LEVEL2_RIGHT_IMAGE2_URL;
			leftImage2 = ImageUtil.DJBOT_LEVEL2_LEFT_IMAGE2_URL;
			this.speed = 4;// �����ٶ�Ϊ4
			break;

		case DJBOT3:// ������������ߵ���
			upImage = ImageUtil.DJBOT_LEVEL3_UP_IMAGE_URL;// ��¼�����ĸ������ͼƬ
			downImage = ImageUtil.DJBOT_LEVEL3_DOWN_IMAGE_URL;
			rightImage = ImageUtil.DJBOT_LEVEL3_RIGHT_IMAGE_URL;
			leftImage = ImageUtil.DJBOT_LEVEL3_LEFT_IMAGE_URL;
			upImage2 = ImageUtil.DJBOT_LEVEL3_UP_IMAGE2_URL;// ��¼�����ĸ������ͼƬ2
			downImage2 = ImageUtil.DJBOT_LEVEL3_DOWN_IMAGE2_URL;
			rightImage2 = ImageUtil.DJBOT_LEVEL3_RIGHT_IMAGE2_URL;
			leftImage2 = ImageUtil.DJBOT_LEVEL3_LEFT_IMAGE2_URL;
			this.life = 2;
			break;
		case DJBOT4:// ������ļ����ߵ���
			upImage = ImageUtil.DJBOT_LEVEL4_UP_IMAGE_URL;// ��¼�����ĸ������ͼƬ
			downImage = ImageUtil.DJBOT_LEVEL4_DOWN_IMAGE_URL;
			rightImage = ImageUtil.DJBOT_LEVEL4_RIGHT_IMAGE_URL;
			leftImage = ImageUtil.DJBOT_LEVEL4_LEFT_IMAGE_URL;
			upImage2 = ImageUtil.DJBOT_LEVEL4_UP_IMAGE2_URL;// ��¼�����ĸ������ͼƬ2
			downImage2 = ImageUtil.DJBOT_LEVEL4_DOWN_IMAGE2_URL;
			rightImage2 = ImageUtil.DJBOT_LEVEL4_RIGHT_IMAGE2_URL;
			leftImage2 = ImageUtil.DJBOT_LEVEL4_LEFT_IMAGE2_URL;
			this.life = 4;
			this.speed = 1;
			break;

		}
	}

	@Override
	public Rectangle getRect() {
		return new Rectangle(x, y, width, height);
	}

	/**
	 * �ж��Ƿ�ײ��ǽ�� x ̹�˺����� y̹�������� ײ������ǽ�飬�򷵻�true
	 */
	private boolean hitWall(int x, int y) {
		Rectangle next = new Rectangle(x, y, width, height);// ����̹���ƶ����Ŀ������
		List<Wall> walls = gamePanel.getWalls();// ��ȡ����ǽ��
		for (int i = 0, length = walls.size(); i < length; i++) {// ��������ǽ��
			Wall w = walls.get(i);
			if (w instanceof GrassWall) {// ����ǲݵ�
				continue;// ִ����һ��ѭ��
			} else if (w.hit(next)) {// ���ײ��ǽ��
				return true;// ����ײ��ǽ��
			}

		}
		return false;
	}

	/**
	 * �ж��Ƿ�ײ��̹�� x ̹�˺����� y̹�������� ײ������ǽ�飬�򷵻�true
	 */

	private boolean hitTank(int x, int y) {
		Rectangle next = new Rectangle(x, y, width, height);
		List<Tank> tanks = gamePanel.getTanks();// ��ȡ����̹��
		for (int i = 0, length = tanks.size(); i < length; i++) {
			Tank t = tanks.get(i);// ��ȡtank����
			if (!this.equals(t)) {// �����̹����������ͬһ������
				if (t.isAlive() && t.hit(next)) {// �����̹�˴�����������ײ
					return true;// ������ײ
				}

			}
		}
		return false;
	}

	public boolean isAlive() {
		return alive;
	}

	private void moveToBorder() {
		if (x < 0) {// ���̹�˺�����С��0
			x = 0;// ��̹�˺��������0
		} else if (x > 480) {// ���̹�˺����곬�����Χ
			x = 520 - width;// ��̹�˺����걣�����ֵ
		}
		if (y < 0) {// ���̹�˺�����С��0
			y = 0;// ��̹�˺��������0
		} else if (y > 520) {// ���̹�������곬�������Χ
			y = 560 - height;// ��̹�������걣�����ֵ
		}

	}

	/**
	 * �����ƶ�
	 */
	public void leftWard() {

		if (direction != Direction.Left)// ����ƶ�֮ǰ�ķ���������
		{
			setImage(leftImage);// ����Ϊ����ͼƬ1
			nowImageNum = 1;// ��¼��ǰ̹��ͼƬ
		}
		if (direction == Direction.Left) {
			switch (nowImageNum) {
			case 1:
				setImage(leftImage2);
				nowImageNum = 2;
				break;
			case 2:
				setImage(leftImage);
				nowImageNum = 1;
				break;// ����̹��������ͼƬ1��2��仯����������Ч��

			}
		}
		direction = Direction.Left;
		{
			if (!hitWall(x - speed, y) && !hitTank(x - speed, y))// �������֮���λ�ò���ײ��ǽ���̹��}
				x -= speed;// ������ݼ�
			moveToBorder();// �ж��Ƿ��ƶ������ı߽�
		}
	}

	/**
	 * �����ƶ�
	 */
	public void rightWard() {
		if (direction != Direction.Right)// ����ƶ�֮ǰ�ķ���������
		{
			setImage(rightImage);
			nowImageNum = 1;
		}
		if (direction == Direction.Right) {
			switch (nowImageNum) {
			case 1:
				setImage(rightImage2);
				nowImageNum = 2;
				break;
			case 2:
				setImage(rightImage);
				nowImageNum = 1;
				break;

			}
		}
		direction = Direction.Right;// �ƶ�������Ϊ��
		if (!hitWall(x + speed, y) && !hitTank(x + speed, y)) {// �������֮���λ�ò���ײ��ǽ���̹��
			x += speed;// ���������
			moveToBorder();// �ж��Ƿ��ƶ������ı߽�
		}
	}

	/**
	 * �����ƶ�
	 */
	public void upWard() {
		if (direction != Direction.Up)// ����ƶ�֮ǰ�ķ���������
		{
			setImage(upImage);
			nowImageNum = 1;
		}
		if (direction == Direction.Up) {
			switch (nowImageNum) {
			case 1:
				setImage(upImage2);
				nowImageNum = 2;
				break;
			case 2:
				setImage(upImage);
				nowImageNum = 1;
				break;

			}
		}
		direction = Direction.Up;// �ƶ�������Ϊ��
		if (!hitWall(x, y - speed) && !hitTank(x, y - speed)) {// �������֮���λ�ò���ײ��ǽ���̹��
			y -= speed;// ������ݼ�
			moveToBorder();// �ж��Ƿ��ƶ������ı߽�
		}
	}

	/**
	 * �����ƶ�
	 */
	public void downWard() {
		if (direction != Direction.Down)// ����ƶ�֮ǰ�ķ���������
		{
			setImage(downImage);
			nowImageNum = 1;
		}
		if (direction == Direction.Down) {
			switch (nowImageNum) {
			case 1:
				setImage(downImage2);
				nowImageNum = 2;
				break;
			case 2:
				setImage(downImage);
				nowImageNum = 1;
				break;
			}
		}
		direction = Direction.Down;// �ƶ�������Ϊ��
		if (!hitWall(x, y + speed) && !hitTank(x, y + speed)) {// �������֮���λ�ò���ײ��ǽ���̹��
			y += speed;// ���������
			moveToBorder();// �ж��Ƿ��ƶ������ı߽�
		}
	}

	/**
	 * ��ȡ�ڿ�
	 */
	private Point getHeadPoint() {
		Point p = new Point();// �����������Ϊ�ڿ�
		switch (direction) {// �ж��ƶ�����
		case Up:// �������
			p.x = x + width / 2;// �ڿں�����ȡx�ӿ�ȵ�һ��
			p.y = y;// �ڿ�������ȡy
			break;
		case Down:// �������
			p.x = x + width / 2;// �ڿں�����ȡx�ӿ�ȵ�һ��
			p.y = y + height;// �ڿ�������ȡy�Ӹ߶�
			break;
		case Right:// �������
			p.x = x + width;// �ڿں�����ȡx�ӿ��
			p.y = y + height / 2;// �ڿ�������ȡy�Ӹ߶ȵ�һ��
			break;
		case Left:// �������
			p.x = x;// �ڿں�����ȡx
			p.y = y + height / 2;// �ڿ�������ȡy�Ӹ߶ȵ�һ��
			break;
		default:// Ĭ��
			p = null;// �ڿ�Ϊnull
		}
		return p;// �����ڿ�
	}

	/**
	 * ����
	 */
	public void attack() {

		if (attackCoolDown && alive) {// ����������������ȴ��̹�˴��������alive�жϣ���̹�������󰴹�������Ȼ����ƾ�շ����ӵ���
			Point p = getHeadPoint();// ��ȡ̹���ڿ�(�ڿ�)����
			Bullet b = new Bullet(p.x - Bullet.dx / 2, p.y - Bullet.dy / 2, direction, gamePanel, type);// ��̹���ڿ�λ�÷�����̹�˽Ƕ���ͬ���ӵ�
			gamePanel.addBullet(b);// ��Ϸ�������ӵ�
//qx		AudioPlayer fire=new AudioPlayer(AudioUtil.FIRE);//������Ч
//qx		fire.new AudioThread().start();
			new AttackCD().start();// �������ܿ�ʼ��ȴ
		}
	}

	/**
	 * ������ȴʱ���߳�
	 */
	private class AttackCD extends Thread {
		public void run() {// �߳�������
			attackCoolDown = false;// ������������Ϊ��ȴ״̬
			try {
				Thread.sleep(attackCoolDownTime);// ����0.5��
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			attackCoolDown = true;// ���������ܽ����ȴ״̬
		}
	}

	public boolean isAttackCoolDown() {
		return attackCoolDown;
	}

	public void setAttackCoolDownTime(int attackCoolDownTime) {
		this.attackCoolDownTime = attackCoolDownTime;
	}

	/**
	 * �ж��Ƿ�Ե��˵���
	 * 
	 * @param x ������
	 * @param y ������
	 * @return ��ײ����true������false
	 */

	public final boolean hitTool() {
		Tool tool = gamePanel.getTool();
		List<Tank> tanks = gamePanel.getPlayerTanks();// ��ȡ�������̹��
		for (int i = 0, lengh = tanks.size(); i < lengh; i++) {// �����������̹��
			Tank t = tanks.get(i);// ��ȡtank����
			if (t.type == TankType.PLAYER1 || t.type == TankType.PLAYER2) {// �����̹�������̹��
				if (t.isAlive() && t.hit(tool) && tool.getAlive()) {// �����̹�˴����������ײ
					switch (tool.type) {// ���ݵ���ѡ���Ӧ��Ч��
					case ADD_LIFE:
						t.life++;
						System.out.println("�������ӵ���" + life);
						break;
					case STAR:
						System.out.println("���ǵ���");
						t.starNum++;
						if (t.starNum > 3) {
							t.starNum = 3;
						}
						break;
					case SPADE:
						System.out.println("���˵���");
						addSpade();
						break;
					case TIMER:
						System.out.println("��ʱ������");
						addTimer();
						break;
					case BOMB:
						System.out.println("��ը����");
						addBomb();
						break;
					case GUN:
						t.starNum = 3;
						System.out.println("����ģʽ��Ч����ͬ���������ǣ�");
						break;
					}
					tool.setAlive(false);// �õ�����ʧ
					return true;// ����true
				}
			}
		}
		return false;
	}

	private void addBomb() {
		List<Tank> botTanks = gamePanel.getBotTanks();
		for (int i = 0; i < botTanks.size(); i++) {
			BotTank b = (BotTank) botTanks.get(i);
			b.setAlive(false);
		}
	}

	private void addTimer() {
		List<Tank> botTanks = gamePanel.getBotTanks();
		for (int i = 0; i < botTanks.size(); i++) {
			BotTank b = (BotTank) botTanks.get(i);
			b.setPause(true);
		}
	}

	public void addStar() {
		if (starNum == 1) {
			this.setAttackCoolDownTime(400);
			speed = 4;
			// System.out.println(attackCoolDownTime+","+starNum);
		}
		if (starNum == 2) {
			speed = 5;
			this.setAttackCoolDownTime(300);
			// System.out.println(attackCoolDownTime+","+starNum);
		}
		if (starNum == 3) {
			speed = 10;
			this.setAttackCoolDownTime(250);
			List<Bullet> bullets = gamePanel.getBullets();
			for (int i = 0; i < bullets.size(); i++) {
				Bullet b = bullets.get(i);
				if (b.isAlive() && b.owner == type) {
					b.setIsHitIronWall(true);// ���Դ����ש
				}
			}
		}
	}

	private void addSpade() {
		List<Wall> walls = gamePanel.getWalls();
		// ����שǽ
		for (int a = 340; a <= 400; a += 20) {// ѭ������שǽ�ĺ�����
			for (int i = 220; i <= 280; i += 20) {
				for (int j = 500; j <= 540; j += 20) {
					if (i >= 240 && i <= 260 && j >= 520) {// ���ǽ��������غ�
						continue;// ִ����һ��ѭ��
					} else {
						walls.add(new IronWall(i, j));
					}
				}
			}
		}

	}

	public int getLife() {

		return this.life;
	}

	public void hackaddLife() {// ���׼�Ѫ
		this.life = this.life + 1;
		//System.out.println("���1��" + this.life);
	}

	public void decreaseLife() {
		if (life > 0) {
			life--;
		} else {
			return;
		}

	}

	public TankType getTankType() {
		return type;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public boolean isDJBotTank() {
		if (type.equals(TankType.DJBOT1) || type.equals(TankType.DJBOT2) || type.equals(TankType.DJBOT3)
				|| type.equals(TankType.DJBOT4)) {// �ж��Ƿ�Ϊ����̹��
			return true;
		} else {
			return false;
		}
	}

	public void setHit(boolean a) {
		this.Hit = a;
	}

	public boolean getHit() {
		return this.Hit;
	}

	public void addLife() {
		this.life++;
	}
}
