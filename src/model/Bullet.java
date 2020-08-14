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
	static  int dx ;// 子弹图片定位要减去的宽高，用于确定子弹发射的位置
	static  int dy ;//详见Tank类的attack()函数
	private  GamePanel gamePanel;// 游戏面板
	private int speed = 4;// 子弹飞行速度
	private boolean alive = true;// 子弹是否存活
	private String upImage = ImageUtil.BULLET_UP_IMAGE_URL;// 子弹图片
	private String downImage = ImageUtil.BULLET_DOWN_IMAGE_URL;
	private String rightImage = ImageUtil.BULLET_RIGHT_IMAGE_URL;
	private String leftImage = ImageUtil.BULLET_LEFT_IMAGE_URL;
	TankType owner;// 发出子弹的坦克类型

	private boolean isHitIronWall = false;

	public Bullet(int x, int y, Direction direction, GamePanel gamePanel, TankType owner) {
		super(x, y);// 调用父类构造方法
		this.direction = direction;
		if(owner.equals(TankType.PLAYER1)||owner.equals(TankType.PLAYER2)||owner.equals(TankType.BOT2)||owner.equals(TankType.DJBOT2)) {
			this.speed=16;//将玩家坦克和迅捷型坦克的子弹速度设置为16
		}
		switch (direction) {
		case Up:
			try {
				dx=8;
				dy=10;
				image = ImageIO.read(new File(upImage));
				this.width = image.getWidth();// 宽为图片宽
				this.height = image.getHeight();// 高为图片高
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case Down:
			try {
				dx=8;
				dy=10;
				image = ImageIO.read(new File(downImage));
				this.width = image.getWidth();// 宽为图片宽
				this.height = image.getHeight();// 高为图片高
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case Right:			
			try {
				dx=10;
				dy=8;
			image = ImageIO.read(new File(rightImage));
			this.width = image.getWidth();// 宽为图片宽
			this.height = image.getHeight();// 高为图片高
		} catch (IOException e) {
			e.printStackTrace();
		}
		break;
		case Left:			
			try {
				dx=10;
				dy=8;
			image = ImageIO.read(new File(leftImage));
			this.width = image.getWidth();// 宽为图片宽
			this.height = image.getHeight();// 高为图片高
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
		case Up:// 如果向上
			upward();// 向上移动
			break;
		case Down:// 如果向下
			downward();// 向下移动
			break;
		case Left:// 如果向左
			leftward();// 向左移动
			break;
		case Right:// 如果向右
			rightward();// 向右移动
			break;
	}
}
	/**
	 * 向左移动
	 */
	private void leftward() {
		x -= speed;// 横坐标减少
		moveToBorder();// 移动出面板边界时销毁子弹
	}

	/**
	 * 向右移动
	 */
	private void rightward() {
		x += speed;// 横坐标增加
		moveToBorder();// 移动出面板边界时销毁子弹
	}

	/**
	 * 向上移动
	 */
	private void upward() {
		y -= speed;// 总坐标减少
		moveToBorder();// 移动出面板边界时销毁子弹
	}

	/**
	 * 向下移动
	 */
	private void downward() {
		y += speed;// 纵坐标增加
		moveToBorder();// 移动出面板边界时销毁子弹
	}

	private void moveToBorder() {
		if(x<0||x>520-getWidth()||y<0||y>560-getHeight()) {
			//如果子弹坐标离开游戏面板
			this.dispose();//销毁子弹
		}
		
	}
	//synchronized 关键字，代表这个方法加锁,相当于不管哪一个线程（例如线程A），运行到这个方法时,都要检查有没有其它线程B（或者C、 D等）正在用这个方法(或者该类的其他同步方法)，有的话要等正在使用synchronized方法的线程B（或者C 、D）运行完这个方法后再运行此线程A,没有的话,锁定调用者,然后直接运行。
	private synchronized void dispose() {
		this.alive=false;
	}

public boolean isAlive() {
		
		return alive;
	}
	

	/**
	 * 击中基地
	 */
	public void hitBase() {
		BaseWall b = gamePanel.getBase();// 获取基地
		if (this.hit(b)) {// 如果子弹击中基地
			this.dispose();// 子弹销毁
			b.setAlive(false);// 基地阵亡
		}
	}
	/**
	 * 击中墙块
	 */
	public void hitWall() {
		List<Wall> walls = gamePanel.getWalls();// 获取所有墙块
		for (int i = 0, lengh = walls.size(); i < lengh; i++) {// 遍历所有墙块
			Wall w = walls.get(i);// 获取墙块对象
			if (this.hit(w)) {// 如果子弹击中墙块
				if (w instanceof BrickWall) {// 如果是砖墙
//qx					new AudioPlayer(AudioUtil.HIT).new AudioThread().start();
					this.dispose();
					//alive = false;// 子弹销毁
					w.setAlive(false);// 砖墙销毁
				}
				if (w instanceof IronWall) {// 如果是钢砖
					//alive = false;// 子弹销毁
					this.dispose();
					if(this.isHitIronWall) {
						w.setAlive(false);
					}
//qx					new AudioPlayer(AudioUtil.HIT).new AudioThread().start();
				}
				if(w instanceof River0Wall||w instanceof River2Wall||w instanceof River1Wall) {
					//如果是河流则什么都不做
					}
				if(w instanceof GrassWall) {
					//如果是草地则什么都不做
					
				}
			}
		}
	}

	/**
	 * 击中坦克
	 */
	public void hitTank() {
		List<Tank> tanks = gamePanel.getTanks();// 获取所有坦克的集合
		for (int i = 0, lengh = tanks.size(); i < lengh; i++) {// 遍历坦克集合
			Tank t = tanks.get(i);// 获取坦克对象
			if (t.isAlive() && this.hit(t)) {// 如果坦克是存活的并且子弹击中了坦克
				switch (owner) {// 判断子弹属于什么坦克
				case PLAYER1:// 如果是玩家1的子弹，效果同下
				case PLAYER2:// 如果是玩家2的子弹
					if (t instanceof BotTank) {// 如果击中的坦克是电脑
						//alive = false;// 子弹销毁
						this.dispose();
							t.setHit(true);
//							System.out.println("Hit!");
					} else if (t instanceof Tank) {// 如果击中的是玩家
						//alive = false;// 子弹销毁
						this.dispose();
					}
					break;
				case BOT1:// 如果是电脑的子弹
				case BOT2:
				case BOT3:
				case BOT4:
				case DJBOT1:
				case DJBOT2:	
				case DJBOT3:
				case DJBOT4:			
					if (t instanceof BotTank) {// 如果击中的坦克是电脑
						//alive = false;// 子弹销毁
						this.dispose();
					} else if (t instanceof Tank) {// 如果击中的是玩家
						//alive = false;// 子弹销毁
						this.dispose();
						t.setHit(true);
						// 玩家坦克中弹
					}
					break;
				    default:// 默认
					//alive = false;// 子弹销毁
					this.dispose();
					t.setAlive(false);// 坦克阵亡
				}
			}
		}
	}

	/**
	 * 子弹抵消
	 */
	public void hitBullet() {
		List<Bullet> bullets=gamePanel.getBullets();
		for(int i=0;i<bullets.size();i++) {
			Bullet b=bullets.get(i);
			if(this.alive) {
				if(this.hit(b)&&this.owner!=b.owner) {
					//this.alive=false;
					b.dispose();//销毁子弹
					this.dispose();//销毁子弹
				}
			}
		}
	}
	public void setIsHitIronWall(boolean b) {//设置子弹可以打穿钢砖
		this.isHitIronWall=b;
	}
 
	
}
