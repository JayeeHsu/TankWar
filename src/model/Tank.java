package model;

import java.util.List;
import java.awt.Point;
import java.awt.Rectangle;

import Enumtype.Direction;
import Enumtype.TankType;
import util.ImageUtil;
import view.GamePanel;

public class Tank extends DisplayableImage {
	GamePanel gamePanel;// 游戏面板
	Direction direction = Direction.Up;// 移动方向
	protected boolean alive = true;// 是否存活
	protected int speed = 2;// 移动速度
	private boolean attackCoolDown = true;// 攻击冷却状态
	private int attackCoolDownTime = 500;// 攻击冷却时间，毫秒
	TankType type;// 坦克类型
	private String upImage;// 向上移动时的图片1
	private String upImage2;// 向上移动时的图片2
	private String downImage;// 向下移动时的图片1
	private String downImage2;// 向下移动时的图片2
	private String rightImage;// 向右移动时的图片1
	private String rightImage2;// 向右移动时的图片2
	private String leftImage;// 向左移动时的图片1
	private String leftImage2;// 向左移动时的图片2
	private int nowImageNum = 1;// 用于记录坦克现在是图片1还是2

	private int life = 1;// 生命值
	private int starNum = 0;// 吃到的星星道具数量，加快攻速

	private boolean Hit = false;// 用于判断=坦克是否被击中

	/**
	 * 坦克构造方法： x初始化横坐标 y初始化纵坐标 url 图片路径 gamePanel游戏面板 type坦克类型
	 */
	public Tank(int x, int y, String url, GamePanel gamePanel, TankType type) {
		super(x, y, url);
		this.gamePanel = gamePanel;
		this.type = type;
		direction = Direction.Up;// 初始化方向向上
		switch (type) {// 判断坦克类型
		case PLAYER1:// 如果是玩家1
			upImage = ImageUtil.PLAYER1_LEVEL1_UP_IMAGE_URL;// 记录玩家1四个方向的图片
			downImage = ImageUtil.PLAYER1_LEVEL1_DOWN_IMAGE_URL;
			rightImage = ImageUtil.PLAYER1_LEVEL1_RIGHT_IMAGE_URL;
			leftImage = ImageUtil.PLAYER1_LEVEL1_LEFT_IMAGE_URL;
			upImage2 = ImageUtil.PLAYER1_LEVEL1_UP_IMAGE2_URL;// 记录玩家1四个方向的图片2
			downImage2 = ImageUtil.PLAYER1_LEVEL1_DOWN_IMAGE2_URL;
			rightImage2 = ImageUtil.PLAYER1_LEVEL1_RIGHT_IMAGE2_URL;
			leftImage2 = ImageUtil.PLAYER1_LEVEL1_LEFT_IMAGE2_URL;
			this.speed = 4;
			break;
		case PLAYER2:// 如果是玩家2
			upImage = ImageUtil.PLAYER2_LEVEL1_UP_IMAGE_URL;// 记录玩家2四个方向的图片
			downImage = ImageUtil.PLAYER2_LEVEL1_DOWN_IMAGE_URL;
			rightImage = ImageUtil.PLAYER2_LEVEL1_RIGHT_IMAGE_URL;
			leftImage = ImageUtil.PLAYER2_LEVEL1_LEFT_IMAGE_URL;
			upImage2 = ImageUtil.PLAYER2_LEVEL1_UP_IMAGE2_URL;// 记录玩家2四个方向的图片
			downImage2 = ImageUtil.PLAYER2_LEVEL1_DOWN_IMAGE2_URL;
			rightImage2 = ImageUtil.PLAYER2_LEVEL1_RIGHT_IMAGE2_URL;
			leftImage2 = ImageUtil.PLAYER2_LEVEL1_LEFT_IMAGE2_URL;
			this.speed = 4;
			break;
		case BOT1:// 如果是一级普通电脑
			upImage = ImageUtil.BOT_LEVEL1_UP_IMAGE_URL;// 记录电脑四个方向的图片
			downImage = ImageUtil.BOT_LEVEL1_DOWN_IMAGE_URL;
			rightImage = ImageUtil.BOT_LEVEL1_RIGHT_IMAGE_URL;
			leftImage = ImageUtil.BOT_LEVEL1_LEFT_IMAGE_URL;
			upImage2 = ImageUtil.BOT_LEVEL1_UP_IMAGE2_URL;// 记录电脑四个方向的图片2
			downImage2 = ImageUtil.BOT_LEVEL1_DOWN_IMAGE2_URL;
			rightImage2 = ImageUtil.BOT_LEVEL1_RIGHT_IMAGE2_URL;
			leftImage2 = ImageUtil.BOT_LEVEL1_LEFT_IMAGE2_URL;
			break;
		case BOT2:// 如果是二级普通电脑
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
		case BOT3:// 如果是三级普通电脑
			upImage = ImageUtil.BOT_LEVEL3_UP_IMAGE_URL;// 记录电脑四个方向的图片
			downImage = ImageUtil.BOT_LEVEL3_DOWN_IMAGE_URL;
			rightImage = ImageUtil.BOT_LEVEL3_RIGHT_IMAGE_URL;
			leftImage = ImageUtil.BOT_LEVEL3_LEFT_IMAGE_URL;
			upImage2 = ImageUtil.BOT_LEVEL3_UP_IMAGE2_URL;// 记录电脑四个方向的图片2
			downImage2 = ImageUtil.BOT_LEVEL3_DOWN_IMAGE2_URL;
			rightImage2 = ImageUtil.BOT_LEVEL3_RIGHT_IMAGE2_URL;
			leftImage2 = ImageUtil.BOT_LEVEL3_LEFT_IMAGE2_URL;
			this.life = 2;
			break;
		case BOT4:// 如果是四级普通电脑
			upImage = ImageUtil.BOT_LEVEL4_UP_IMAGE_URL;// 记录电脑四个方向的图片
			downImage = ImageUtil.BOT_LEVEL4_DOWN_IMAGE_URL;
			rightImage = ImageUtil.BOT_LEVEL4_RIGHT_IMAGE_URL;
			leftImage = ImageUtil.BOT_LEVEL4_LEFT_IMAGE_URL;
			upImage2 = ImageUtil.BOT_LEVEL4_UP_IMAGE2_URL;// 记录电脑四个方向的图片2
			downImage2 = ImageUtil.BOT_LEVEL4_DOWN_IMAGE2_URL;
			rightImage2 = ImageUtil.BOT_LEVEL4_RIGHT_IMAGE2_URL;
			leftImage2 = ImageUtil.BOT_LEVEL4_LEFT_IMAGE2_URL;
			this.life = 4;
			this.speed = 1;
			break;
		case DJBOT1:// 如果是一级道具电脑
			upImage = ImageUtil.BOT_LEVEL1_UP_IMAGE_URL;// 记录电脑四个方向的图片
			downImage = ImageUtil.BOT_LEVEL1_DOWN_IMAGE_URL;
			rightImage = ImageUtil.BOT_LEVEL1_RIGHT_IMAGE_URL;
			leftImage = ImageUtil.BOT_LEVEL1_LEFT_IMAGE_URL;
			upImage2 = ImageUtil.BOT_LEVEL1_UP_IMAGE2_URL;// 记录电脑四个方向的图片2
			downImage2 = ImageUtil.BOT_LEVEL1_DOWN_IMAGE2_URL;
			rightImage2 = ImageUtil.BOT_LEVEL1_RIGHT_IMAGE2_URL;
			leftImage2 = ImageUtil.BOT_LEVEL1_LEFT_IMAGE2_URL;
			break;
		case DJBOT2:// 如果是二级道具电脑
			upImage = ImageUtil.DJBOT_LEVEL2_UP_IMAGE_URL;// 记录电脑四个方向的图片
			downImage = ImageUtil.DJBOT_LEVEL2_DOWN_IMAGE_URL;
			rightImage = ImageUtil.DJBOT_LEVEL2_RIGHT_IMAGE_URL;
			leftImage = ImageUtil.DJBOT_LEVEL2_LEFT_IMAGE_URL;
			upImage2 = ImageUtil.DJBOT_LEVEL2_UP_IMAGE2_URL;// 记录电脑四个方向的图片2
			downImage2 = ImageUtil.DJBOT_LEVEL2_DOWN_IMAGE2_URL;
			rightImage2 = ImageUtil.DJBOT_LEVEL2_RIGHT_IMAGE2_URL;
			leftImage2 = ImageUtil.DJBOT_LEVEL2_LEFT_IMAGE2_URL;
			this.speed = 4;// 设置速度为4
			break;

		case DJBOT3:// 如果是三级道具电脑
			upImage = ImageUtil.DJBOT_LEVEL3_UP_IMAGE_URL;// 记录电脑四个方向的图片
			downImage = ImageUtil.DJBOT_LEVEL3_DOWN_IMAGE_URL;
			rightImage = ImageUtil.DJBOT_LEVEL3_RIGHT_IMAGE_URL;
			leftImage = ImageUtil.DJBOT_LEVEL3_LEFT_IMAGE_URL;
			upImage2 = ImageUtil.DJBOT_LEVEL3_UP_IMAGE2_URL;// 记录电脑四个方向的图片2
			downImage2 = ImageUtil.DJBOT_LEVEL3_DOWN_IMAGE2_URL;
			rightImage2 = ImageUtil.DJBOT_LEVEL3_RIGHT_IMAGE2_URL;
			leftImage2 = ImageUtil.DJBOT_LEVEL3_LEFT_IMAGE2_URL;
			this.life = 2;
			break;
		case DJBOT4:// 如果是四级道具电脑
			upImage = ImageUtil.DJBOT_LEVEL4_UP_IMAGE_URL;// 记录电脑四个方向的图片
			downImage = ImageUtil.DJBOT_LEVEL4_DOWN_IMAGE_URL;
			rightImage = ImageUtil.DJBOT_LEVEL4_RIGHT_IMAGE_URL;
			leftImage = ImageUtil.DJBOT_LEVEL4_LEFT_IMAGE_URL;
			upImage2 = ImageUtil.DJBOT_LEVEL4_UP_IMAGE2_URL;// 记录电脑四个方向的图片2
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
	 * 判断是否撞到墙块 x 坦克横坐标 y坦克纵坐标 撞到任意墙块，则返回true
	 */
	private boolean hitWall(int x, int y) {
		Rectangle next = new Rectangle(x, y, width, height);// 创建坦克移动后的目标区域
		List<Wall> walls = gamePanel.getWalls();// 获取所有墙块
		for (int i = 0, length = walls.size(); i < length; i++) {// 遍历所有墙块
			Wall w = walls.get(i);
			if (w instanceof GrassWall) {// 如果是草地
				continue;// 执行下一次循环
			} else if (w.hit(next)) {// 如果撞到墙块
				return true;// 返回撞到墙块
			}

		}
		return false;
	}

	/**
	 * 判断是否撞到坦克 x 坦克横坐标 y坦克纵坐标 撞到任意墙块，则返回true
	 */

	private boolean hitTank(int x, int y) {
		Rectangle next = new Rectangle(x, y, width, height);
		List<Tank> tanks = gamePanel.getTanks();// 获取所有坦克
		for (int i = 0, length = tanks.size(); i < length; i++) {
			Tank t = tanks.get(i);// 获取tank对象
			if (!this.equals(t)) {// 如果此坦克与自身不是同一个对象
				if (t.isAlive() && t.hit(next)) {// 如果此坦克存活并且与自身相撞
					return true;// 返回相撞
				}

			}
		}
		return false;
	}

	public boolean isAlive() {
		return alive;
	}

	private void moveToBorder() {
		if (x < 0) {// 如果坦克横坐标小于0
			x = 0;// 让坦克横坐标等于0
		} else if (x > 480) {// 如果坦克横坐标超出最大范围
			x = 520 - width;// 让坦克横坐标保持最大值
		}
		if (y < 0) {// 如果坦克横坐标小于0
			y = 0;// 让坦克横坐标等于0
		} else if (y > 520) {// 如果坦克纵坐标超出了最大范围
			y = 560 - height;// 让坦克纵坐标保持最大值
		}

	}

	/**
	 * 向左移动
	 */
	public void leftWard() {

		if (direction != Direction.Left)// 如果移动之前的方向不是左移
		{
			setImage(leftImage);// 设置为左移图片1
			nowImageNum = 1;// 记录当前坦克图片
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
				break;// 在让坦克在左移图片1和2间变化，产生动画效果

			}
		}
		direction = Direction.Left;
		{
			if (!hitWall(x - speed, y) && !hitTank(x - speed, y))// 如果左移之后的位置不会撞到墙块和坦克}
				x -= speed;// 横坐标递减
			moveToBorder();// 判断是否移动到面板的边界
		}
	}

	/**
	 * 向右移动
	 */
	public void rightWard() {
		if (direction != Direction.Right)// 如果移动之前的方向不是右移
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
		direction = Direction.Right;// 移动方向设为右
		if (!hitWall(x + speed, y) && !hitTank(x + speed, y)) {// 如果右移之后的位置不会撞到墙块和坦克
			x += speed;// 横坐标递增
			moveToBorder();// 判断是否移动到面板的边界
		}
	}

	/**
	 * 向上移动
	 */
	public void upWard() {
		if (direction != Direction.Up)// 如果移动之前的方向不是上移
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
		direction = Direction.Up;// 移动方向设为上
		if (!hitWall(x, y - speed) && !hitTank(x, y - speed)) {// 如果上移之后的位置不会撞到墙块和坦克
			y -= speed;// 纵坐标递减
			moveToBorder();// 判断是否移动到面板的边界
		}
	}

	/**
	 * 向下移动
	 */
	public void downWard() {
		if (direction != Direction.Down)// 如果移动之前的方向不是下移
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
		direction = Direction.Down;// 移动方向设为下
		if (!hitWall(x, y + speed) && !hitTank(x, y + speed)) {// 如果下移之后的位置不会撞到墙块和坦克
			y += speed;// 纵坐标递增
			moveToBorder();// 判断是否移动到面板的边界
		}
	}

	/**
	 * 获取炮口
	 */
	private Point getHeadPoint() {
		Point p = new Point();// 创建点对象，作为炮口
		switch (direction) {// 判断移动方向
		case Up:// 如果向上
			p.x = x + width / 2;// 炮口横坐标取x加宽度的一半
			p.y = y;// 炮口纵坐标取y
			break;
		case Down:// 如果向下
			p.x = x + width / 2;// 炮口横坐标取x加宽度的一半
			p.y = y + height;// 炮口纵坐标取y加高度
			break;
		case Right:// 如果向右
			p.x = x + width;// 炮口横坐标取x加宽度
			p.y = y + height / 2;// 炮口纵坐标取y加高度的一半
			break;
		case Left:// 如果向左
			p.x = x;// 炮口横坐标取x
			p.y = y + height / 2;// 炮口纵坐标取y加高度的一半
			break;
		default:// 默认
			p = null;// 炮口为null
		}
		return p;// 返回炮口
	}

	/**
	 * 攻击
	 */
	public void attack() {

		if (attackCoolDown && alive) {// 如果攻击功能完成冷却且坦克存活（如果不加alive判断，则坦克死亡后按攻击键仍然可以凭空发射子弹）
			Point p = getHeadPoint();// 获取坦克炮口(炮口)对象
			Bullet b = new Bullet(p.x - Bullet.dx / 2, p.y - Bullet.dy / 2, direction, gamePanel, type);// 在坦克炮口位置发射与坦克角度相同的子弹
			gamePanel.addBullet(b);// 游戏面板添加子弹
//qx		AudioPlayer fire=new AudioPlayer(AudioUtil.FIRE);//开火音效
//qx		fire.new AudioThread().start();
			new AttackCD().start();// 攻击功能开始冷却
		}
	}

	/**
	 * 攻击冷却时间线程
	 */
	private class AttackCD extends Thread {
		public void run() {// 线程主方法
			attackCoolDown = false;// 将攻击功能设为冷却状态
			try {
				Thread.sleep(attackCoolDownTime);// 休眠0.5秒
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			attackCoolDown = true;// 将攻击功能解除冷却状态
		}
	}

	public boolean isAttackCoolDown() {
		return attackCoolDown;
	}

	public void setAttackCoolDownTime(int attackCoolDownTime) {
		this.attackCoolDownTime = attackCoolDownTime;
	}

	/**
	 * 判断是否吃到了道具
	 * 
	 * @param x 横坐标
	 * @param y 纵坐标
	 * @return 相撞返回true，否则false
	 */

	public final boolean hitTool() {
		Tool tool = gamePanel.getTool();
		List<Tank> tanks = gamePanel.getPlayerTanks();// 获取所有玩家坦克
		for (int i = 0, lengh = tanks.size(); i < lengh; i++) {// 遍历所有玩家坦克
			Tank t = tanks.get(i);// 获取tank对象
			if (t.type == TankType.PLAYER1 || t.type == TankType.PLAYER2) {// 如果此坦克是玩家坦克
				if (t.isAlive() && t.hit(tool) && tool.getAlive()) {// 如果此坦克存活并且与道具相撞
					switch (tool.type) {// 根据道具选择对应的效果
					case ADD_LIFE:
						t.life++;
						System.out.println("生命增加道具" + life);
						break;
					case STAR:
						System.out.println("星星道具");
						t.starNum++;
						if (t.starNum > 3) {
							t.starNum = 3;
						}
						break;
					case SPADE:
						System.out.println("钢撬道具");
						addSpade();
						break;
					case TIMER:
						System.out.println("定时器道具");
						addTimer();
						break;
					case BOMB:
						System.out.println("爆炸道具");
						addBomb();
						break;
					case GUN:
						t.starNum = 3;
						System.out.println("暴走模式（效果等同于三颗星星）");
						break;
					}
					tool.setAlive(false);// 让道具消失
					return true;// 返回true
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
					b.setIsHitIronWall(true);// 可以打掉钢砖
				}
			}
		}
	}

	private void addSpade() {
		List<Wall> walls = gamePanel.getWalls();
		// 基地砖墙
		for (int a = 340; a <= 400; a += 20) {// 循环基地砖墙的横坐标
			for (int i = 220; i <= 280; i += 20) {
				for (int j = 500; j <= 540; j += 20) {
					if (i >= 240 && i <= 260 && j >= 520) {// 如果墙快与基地重合
						continue;// 执行下一次循环
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

	public void hackaddLife() {// 作弊加血
		this.life = this.life + 1;
		//System.out.println("玩家1：" + this.life);
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
				|| type.equals(TankType.DJBOT4)) {// 判断是否为道具坦克
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
