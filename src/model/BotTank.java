package model;

import java.awt.Rectangle;
import java.util.List;
import java.util.Random;

import Enumtype.Direction;
import Enumtype.TankType;
import util.ImageUtil;
import view.GamePanel;

/**
 * 电脑坦克类 为了控制游戏难度，电脑坦克随机向上移动的概率应该控制为比其他方向更小(基地在下方）
 * 并且电脑坦克最好每次移动的方向都不同，向着某一个方向连续移动的时间也应该不相同
 *
 */

public class BotTank extends Tank {

	private Random random = new Random();
	private Direction dir;// 移动方向
	private int freshTime = GamePanel.FRESHTIME;// 刷新时间，采用游戏面板的刷新时间
	private int moveTimer = 0;// 移动计时器
		
	private boolean pause= false;//电脑坦克暂停状态
	
	//电脑坦克构造方法
	public BotTank(int x, int y, String url, GamePanel gamePanel, TankType type) {
		super(x, y, url, gamePanel, type);
		dir = Direction.Down;// 移动方向默认向下
		setAttackCoolDownTime(1000);// 设置攻击冷却时间
	}


//获取坦克暂停状态
	public boolean isPause() {
		return pause;
	}
//设置坦克暂停状态
	public void setPause(boolean pause) {
		this.pause = pause;
	}

	public void go() {
		if (isAttackCoolDown()) {// 如果冷却结束，就可以攻击
			attack();// 攻击
		}
		if (moveTimer > random.nextInt(1500) + 500) {// 如果移动计时器超过随机的0.5~2.0秒，则随机一个方向
			dir = randomDirection();
			moveTimer = 0;// 重置计时器
		} else {
			moveTimer += freshTime;// 计时器按照刷新时间递增
		}
		switch (dir) {// 根据方向选择朝着哪个方向移动
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
	Rectangle next = new Rectangle(x,y,width-3,height-3);// 创建坦克移动后的目标区域
	List<Wall> walls=gamePanel.getWalls();//获取所有墙块
	for(int i=0,length=walls.size();i<length;i++) {//遍历所有墙块
		Wall w=walls.get(i);//获取墙块对象
		if(w instanceof GrassWall) {//如果是草地
			continue;//执行下一次循环
		}else if (w.hit(next)) {//如果是草地
			return true;//返回撞到墙块
		}
	}
	return false;
}

	@Override
	public Rectangle getRect() {
		return new Rectangle(x, y, width - 3, height - 3);
	}



	@Override
	// 重写攻击方法，每次只有4%概率会触发父类攻击方法
	public void attack() {
		int rnum = random.nextInt(100);// 创建随机数，范围在0-99
		if (rnum < 4) {// 如果随机数小于4
			super.attack();// 执行父类攻击方法
		}
	}

	// 重写移动到面板的边界事件

	protected void moveToBorder() {
		if (x < 0) {// 如果坦克横坐标小于0
			x = 0;// 让坦克横坐标等于0
			dir = randomDirection();// 随机调整移动方向
		} else if (x > gamePanel.getWidth() - width) {// 如果坦克横坐标超出了最大值
			x = gamePanel.getWidth() - width;// 让坦克横坐标保持最大值
			dir = randomDirection();// 随机调整移动方向
		}
		if (y < 0) {// 如果坦克纵坐标小于0
			y = 0;// 让坦克纵坐标等于0
			dir = randomDirection();// 随机调整移动方向
		} else if (y > gamePanel.getHeight() - height) {// 如果坦克纵坐标超出了最大范围
			y = gamePanel.getHeight() - height;// 让坦克纵坐标保持最大值
			dir = randomDirection();// 随机调整移动方向
		}

	}
	
	private Direction randomDirection() {
		Direction[]dirs=Direction.values();// 获取出方向的枚举值
		Direction oldDir=dir;//保存原来的方向
		Direction newDir=dirs[random.nextInt(4)];
		if(oldDir==newDir||newDir==Direction.Up) {
			// 如果电脑坦克原来的方向和本次随机的方向相同，
			//或者电脑坦克新的方向是向上，
			//那么重新随机一次方向
			//这么做的目的是使电脑坦克向玩家基地方向（下）的概率增大
			return dirs[random.nextInt(4)];
		}
		return newDir;
	}



}

