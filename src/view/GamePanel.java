package view;

import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import Enumtype.GameType;
import Enumtype.TankType;
import model.BaseWall;
import model.Boom;
import model.BotTank;
import model.Bullet;
import model.Level;
import model.Map;
import model.Tank;
import model.Tool;
import model.Wall;
import util.ImageUtil;

public class GamePanel extends JPanel implements KeyListener {

	public static final int FRESHTIME = 5;
	/*
	 * 游戏界面FreshTime：5毫秒
	 */
	private BufferedImage image;
	private Graphics g;// 图片的绘图对象
	private MainFrame frame;// 主窗体
	private GameType gameType;// 游戏模式
	private Tank player1, player2;// 玩家1,玩家2
	private boolean SPACE_key, s_key, w_key, a_key, d_key, up_key, down_key, left_key, right_key, CTRL_key;// 按键是否按下标志，左侧单词是按键名
	private boolean Num1_key, Num2_key, Num3_key, Num4_key, Num5_key, Num6_key, F1_key, F2_key, F3_key, F4_key, F5_key,
			F6_key;;// 是否按按键标志，左侧单词是按键名
	private Image GameoverImage;// 游戏结束图片
	private Image backgroud;// 黑色背景图片
	private Image VictoryImage;
	private int level = Level.previsousLevel();// 关卡值
	private List<Bullet> bullets;// 所有子弹集合
	private volatile List<Tank> allTanks;// 所有坦克合集
	private List<Tank> botTanks;// 电脑坦克合集
	private static int botCount = 12;// 电脑坦克总数
	private int botReadyCount = botCount;// 准备出场的电脑坦克总数
	private int botSurplusCount = botCount;// 电脑坦克剩余量
	private int botMaxInMap = 6;// 场上最大电脑坦克数
	private int botX[] = { 0, 240, 480 };// 电脑坦克出生的3个横坐标位置
	private List<Tank> playerTanks;// 玩家坦克集合
	private volatile boolean finish = false;// 游戏是否结束
	/*
	 * volatile是一个类型修饰符（type
	 * specifier）.volatile的作用是作为指令关键字，确保本条指令不会因编译器的优化而省略，且要求每次直接读值。
	 */
	private BaseWall base;// 基地
	private List<Wall> walls;
	private List<Boom> boomImage;// 坦克阵亡后的爆炸效果合集
	private Random r = new Random();// 随机数对象
	private int createBotTimer = 0;// 创建电脑坦克计时器
	private Tank survivor;// （玩家）辛存者，用于绘制最后一个爆炸效果
//qx//private List<AudioClip> audios = AudioUtil.getAudios();// 所有背景音效的集合
	private Tool tool = Tool.getToolInstance(r.nextInt(460), r.nextInt(500), this);
	private boolean shouldCreateTool = false;
	private int pauseTimer = 0;// 电脑坦克暂停计时器

	// 游戏面板构造方法：
	// frame 主窗体
	// level 关卡
	// gameType 游戏模式

	/**
	 * 游戏帧刷新线程内部类
	 */
	private class FreshThread extends Thread {
		public void run() {// 线程主方法
			while (!finish) {// 如果游戏未停止
				repaint();// 执行本类重绘方法
				System.gc();// 绘制一次会产生大量垃圾对象，回收内存
				try {
					Thread.sleep(FRESHTIME);// 指定时间后重新绘制界面
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 结束游戏帧刷新
	 */

	public GamePanel(MainFrame frame, int level, GameType gameType) {
		/**
		 * 游戏帧刷新线程内部类
		 */

		this.frame = frame;
		frame.setSize(660, 600);
		this.level = level;
		this.gameType = gameType;
		setBackground(Color.BLACK);
		init();// 初始化组件
		Thread t = new FreshThread();// 创建游帧刷新线程，这一步很重要，不创建的话游戏会卡住
		t.start();// 启动线程
//qx	new AudioPlayer(AudioUtil.START).new AudioThread().start();// 播放背景音效
		addListener();// 开启监听
	}

	/**
	 * 组件初始化
	 */
	private void init() {
		/**
		 * 游戏帧刷新线程内部类
		 */

		bullets = new ArrayList();// 实例化子弹集合
		allTanks = new ArrayList();// 实例化所有坦克集合
		walls = new ArrayList<>();// 实例化所有墙块集合
		boomImage = new ArrayList<>();// 实例化爆炸效果
		try {
			backgroud = ImageIO.read(new File(ImageUtil.LOGIN_BACKGROUD_IMAGE_URL));// 读取黑色背景图片
			GameoverImage = ImageIO.read(new File(ImageUtil.GAME_OVER_IMAGE_URL));// 读取游戏结束图片
			VictoryImage = ImageIO.read(new File(ImageUtil.VICTORY_IMAGE_URL));// 读取胜利图片
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		image = new BufferedImage(1000, 800, BufferedImage.TYPE_INT_BGR);// 实例化主图片，采用面板实际大小
		g = image.getGraphics();// 获取主图片绘图对象

		playerTanks = new Vector<>();// 实例化玩家坦克集合
		player1 = new Tank(160, 520, ImageUtil.PLAYER1_LEVEL1_UP_IMAGE_URL, this, TankType.PLAYER1);// 实例化玩家1
		playerTanks.add(player1);// 玩家坦克集合添加玩家1
		if (gameType == GameType.TWO_PLAYERS) {// 如果是双人模式
			player2 = new Tank(320, 520, ImageUtil.PLAYER2_LEVEL1_UP_IMAGE_URL, this, TankType.PLAYER2);
			playerTanks.add(player2);// 玩家坦克集合添加玩家2
		}

		botTanks = new ArrayList<>();// 实例化电脑坦克集合
		botTanks.add(new BotTank(botX[0], 1, ImageUtil.BOT_LEVEL1_DOWN_IMAGE_URL, this, TankType.BOT1));// 在第一个位置添加电脑坦克
		botTanks.add(new BotTank(botX[1], 1, ImageUtil.BOT_LEVEL1_DOWN_IMAGE_URL, this, TankType.BOT1));// 在第二个位置添加电脑坦克
		botTanks.add(new BotTank(botX[2], 1, ImageUtil.BOT_LEVEL1_DOWN_IMAGE_URL, this, TankType.BOT1));// 在第三个位置添加电脑坦克
		botReadyCount -= 3;// 准备出场的坦克总数减去初始化数量
		allTanks.addAll(playerTanks);// 所有坦克集合添加玩家坦克集合
		allTanks.addAll(botTanks);// 所有坦克集合添加电脑坦克集合
		base = new BaseWall(240, 520);// 实例化基地
		initWalls();// 初始化地图中的墙块
	}

	/**
	 * 组建监听
	 */
	private void addListener() {
		frame.addKeyListener(this);// 主窗体载入键盘监听，本类已实现KeyListener接口
	}

	/**
	 * 初始化地图中的墙快
	 */
	public void initWalls() {
		Map map = Map.getMap(level);// 获取当前关卡的地图对象
		walls.addAll(map.getWalls());// 墙块集合添加当前地图中的所有墙块
		walls.add(base);// 墙块集合添加基地
	}

	/**
	 * 重写绘制组建方法
	 */

	public void paint(Graphics g) {
		paintTankAction();// 执行坦克动作
		createBotTank(level);// 循环创建电脑坦克
		paintImage();// 绘制主要的图片
		g.drawImage(image, 0, 0, this);// 将主图片绘制到面板上
		System.gc();// 用于调用垃圾收集器，在调用时，垃圾收集器将运行以回收未使用的内存空间。
	}

	private void paintImage() {
		g.setColor(Color.BLACK);// 使用黑色背景
		g.fillRect(0, 0, image.getWidth(), image.getHeight());// 填充一个覆盖整个图片的黑色矩形
		paintBoom();// 绘制爆炸效果
		paintLife();// 在屏幕右侧绘制玩家坦克生命值
		paintBotTanks();// 绘制电脑坦克
		paintPlayerTanks();// 绘制玩家坦克
		allTanks.addAll(playerTanks);// 坦克集合添加玩家坦克集合
		allTanks.addAll(botTanks);// 坦克集合添加电脑坦克集合
		paintWalls();// 绘制墙块
		paintBullets();// 绘制子弹
		g.drawImage(base.getImage(), base.x, base.y, this);// 绘制基地
		paintTool();// 绘制道具
		paintBotCount();// 在屏幕右侧绘制剩余坦克数量
		if (botSurplusCount == 0) {// 如果所有电脑都被消灭

			paintBotCount();// 在屏幕顶部绘制剩余坦克数量
			stopThread();// 结束游戏帧刷新线程
			paintVictory();
//为了演示方便，第一关打完后直接显示结束画面，所以暂时注释掉（其实是第二关的地图还没写哈哈哈）//gotoNextLevel();// 进入下一关卡
		}
		if (gameType == GameType.ONE_PLAYER) {// 如果是单人模式
			if (!player1.isAlive() && player1.getLife() == 0) {// 如果玩家1阵亡,并且玩家1的生命数等于0
				boomImage.add(new Boom(player1.x, player1.y));// 添加玩家1爆炸效果
				paintBoom();// 绘制爆炸效果
				stopThread();// 结束游戏帧刷新线程
				paintGameOver();// 在屏幕中央绘制game over
				gotoPrevisousLevel();// 重新进入本关卡
			}
		} else if (gameType == GameType.TWO_PLAYERS) {// 如果是双人模式
			if (player1.isAlive() && !player2.isAlive() && player2.getLife() == 0) {// 如果玩家1是 幸存者
				survivor = player1;// 幸存者是玩家1
			} else if (!player1.isAlive() && player1.getLife() == 0 && player2.isAlive()) {
				survivor = player2;// 幸存者是玩家2
			} else if (!(player1.isAlive() || player2.isAlive())) {// 如果两个玩家全部阵亡

				boomImage.add(new Boom(survivor.x, survivor.y));// 添加幸存者爆炸效果
				paintBoom();// 绘制爆炸效果
				stopThread();// 结束游戏帧刷新线程
				paintGameOver();// 在屏幕中央绘制game over
				gotoPrevisousLevel();// 重新进入本关卡
			}
		}

		if (!base.isAlive()) {// 如果基地被击中
			base.setImage(ImageUtil.BREAK_BASE_IMAGE_URL);// 基地使用阵亡图片
			stopThread();// 结束游戏帧刷新线程
			paintGameOver();// 在屏幕中央绘制game over
			gotoPrevisousLevel();// 重新进入本关卡
		}
	}

	private void paintVictory() {
		g.drawImage(backgroud, 0, 0, getWidth(), getHeight(), this);// 绘制背景
		g.drawImage(VictoryImage, (getWidth() - VictoryImage.getWidth(null)) / 2,
				(getHeight() - VictoryImage.getHeight(null)) / 2, this);

//qx	new AudioPlayer(AudioUtil.GAMEOVER).new AudioThread().start();//新建一个音效线程，用于播放音效

	}

	private void gotoPrevisousLevel() {
		Thread jump = new JumpPageThead(Level.previsousLevel());// 创建重新进入本关卡的线程
		jump.start();// 启动线程
	}

	private void gotoNextLevel() {
		Thread jump = new JumpPageThead(Level.nextLevel());// 创建跳转到下一关卡的线程
		jump.start();// 启动线程
	}

	private synchronized void stopThread() {
		/**
		 * ynchronized 关键字，代表这个方法加锁,相当于不管哪一个线程（例如线程A）， 运行到这个方法时,都要检查有没有其它线程B（或者C、
		 * D等）正在用这个方法 (或者该类的其他同步方法)，有的话要等正在使用synchronized方法的线程B （或者C
		 * 、D）运行完这个方法后再运行此线程A,没有的话,锁定调用者, 然后直接运行。
		 */
		frame.removeKeyListener(this);// 主窗体删除本类键盘事件监听对象
		finish = true;// 游戏停止标志为true
	}

	private void paintWalls() {
		for (int i = 0; i < walls.size(); i++) {// 循环遍历墙块集合
			Wall w = walls.get(i);// 获取墙块对象
			if (w.isAlive()) {// 如果墙块有效
				g.drawImage(w.getImage(), w.x, w.y, this);// 绘制墙块
			} else {// 如果墙块无效
				walls.remove(i);// 在集合中刪除此墙块
				i--;// 循环变量-1，保证下次循环i的值不会变成i+1，以便有效遍历集合，且防止下标越界
			}
		}
	}

	private void paintBullets() {
		for (int i = 0; i < bullets.size(); i++) {// 循环遍历子弹集合
			Bullet b = bullets.get(i);// 获取子弹对象
			if (b.isAlive()) {// 如果子弹有效
				b.move();// 子弹执行移动操作
				b.hitBase();// 子弹执行击中基地判断
				b.hitWall();// 子弹执行击中墙壁判断
				b.hitTank();// 子弹执行击中坦克判断
				b.hitBullet();// 子弹执行抵消判断
				g.drawImage(b.getImage(), b.x, b.y, this);// 绘制子弹
			} else {// 如果子弹无效
				bullets.remove(i);// 在集合中删除此子弹
				i--;// 循环变量-1，保证下次循环i的值不会变成i+1，以便有效遍历集合，且防止下标越界
			}
		}

	}

	private void paintBotTanks() {
		for (int i = 0; i < botTanks.size(); i++) {// 循环遍历电脑坦克集合
			BotTank t = (BotTank) botTanks.get(i);// 获取电脑坦克对象
			if (t.getLife() == 0) {
				t.setAlive(false);
			}
			if (t.isAlive()) {// 如果坦克存活
				if (!t.isPause()) {// 如果电脑坦克不处于暂停状态
					t.go();// 电脑坦克行动
				}
				if (t.isPause()) {// 电脑坦克处于暂停状态
					if (pauseTimer > 2500) {// 如果暂停时间大于2.5秒
						t.setPause(false);// 解除暂停状态
						pauseTimer = 0;// 下一次暂停状态重新计时
					}
					pauseTimer += FRESHTIME;// 暂停时间开始累积
				}
				if (t.getHit()) {
					t.decreaseLife();
					t.setHit(false);
				}

				g.drawImage(t.getImage(), t.x, t.y, this);// 绘制坦克
			} else {// 如果坦克阵亡

				if (t.isDJBotTank()) {// 如果是道具坦克，设置shouldCreateTool（需要创造一个道具）为真
					tool.changeToolType();
					shouldCreateTool = true;
					// System.out.println("道具坦克阵亡(shouldCreateTool："+shouldCreateTool+")");
				}
				botTanks.remove(i);// 集合中删除此坦克
				i--;// 循环变量-1，保证下次循环i的值不会变成i+1，以便有效遍历集合，且防止下标越界
				boomImage.add(new Boom(t.x, t.y));// 在坦克位置创建爆炸效果
				decreaseBot();// 剩余坦克数量-1
			}
		}

	}

	private void decreaseBot() {
		botSurplusCount--;// 电脑剩余数量-1
	}

	/**
	 * 绘制道具
	 */

	public void paintTool() {
		if (tool.getAlive() && shouldCreateTool) {// 如果需要创造一个道具（杀死了一个道具坦克（paintbotTanks函数中有判定））
			// System.out.println("已经打死了一个道具坦克...(shouldCreateTool："+shouldCreateTool+")");
			tool.draw(g);
		}
	}

	private void paintPlayerTanks() {
		for (int i = 0; i < playerTanks.size(); i++) {// 循环遍历玩家坦克
			Tank t = playerTanks.get(i);// 获取玩家坦克对象
			if (t.getLife() == 0) {
				t.setAlive(false);
			}
			if (t.isAlive()) {// 如果坦克存活
				t.hitTool();// 判断是否碰撞到道具
				t.addStar();
				g.drawImage(t.getImage(), t.x, t.y, this);// 绘制坦克
				if (t.getHit()) {
					t.decreaseLife();
					t.setHit(false);
				}
			} else {// 如果坦克阵亡
				// TankType type=t.getTankType();
				// int life=t.getLife();
				playerTanks.remove(i);// 集合中删除此坦克
				boomImage.add(new Boom(t.x, t.y));// 在坦克位置创建爆炸效果
//qx				AudioClip blast=audios.get(2);
//qx				blast.play();					
				 i--;// 循环变量-1，保证下次循环i的值不会变成i+1，以便有效遍历集合，且防止下标越界

			}
		}

	}

	/**
	 * 在屏幕右侧绘制剩余坦克数量
	 * 
	 */
	private void paintBotCount() {
		g.setColor(Color.green);// 使用蓝色
		g.drawString("敌方坦克剩余：" + botSurplusCount, 540, 36);// 在指定坐标绘制字符串

	}

	/**
	 * 在屏幕右侧绘制玩家坦克生命值
	 * 
	 */
	private void paintLife() {
		g.setColor(Color.ORANGE);// 使用橙色
		g.drawString("P1生命值：" + player1.getLife(), 540, 60);// 在指定坐标绘制字符串
		if (gameType.equals(GameType.TWO_PLAYERS)) {
			g.drawString("P2生命值：" + player2.getLife(), 540, 84);// 在指定坐标绘制字符串
		}
	}

	/**
	 * 在屏幕中央绘制game over
	 */
	private void paintGameOver() {
		g.drawImage(backgroud, 0, 0, getWidth(), getHeight(), this);// 绘制背景
		g.drawImage(GameoverImage, (getWidth() - GameoverImage.getWidth(null)) / 2,
				(getHeight() - GameoverImage.getHeight(null)) / 2, this);

//qx	new AudioPlayer(AudioUtil.GAMEOVER).new AudioThread().start();//新建一个音效线程，用于播放音效
	}

	/**
	 * 绘制爆炸效果
	 */
	private void paintBoom() {
		for (int i = 0; i < boomImage.size(); i++) {// 循环遍历爆炸效果集合
			Boom boom = boomImage.get(i);// 获取爆炸对象
			if (boom.isAlive()) {// 如果爆炸效果有效
				/*
				 * qx AudioClip blast=audios.get(2);// 获取爆炸音效对象 blast.play();// 播放爆炸音效
				 */
				boom.show(g);// 展示爆炸效果
			} else {// 如果爆炸效果无效
				boomImage.remove(i);// 在集合中刪除此爆炸对象
				i--;// 循环变量-1，保证下次循环i的值不会变成i+1，以便有效遍历集合，且防止下标越界
			}
		}
	}

	private void createBotTank(int level) {
		int index = r.nextInt(3);
		createBotTimer += FRESHTIME;// 计时器按照刷新时间递增
		if (botTanks.size() < botMaxInMap && botReadyCount > 0 && createBotTimer >= 1500) {
			// “当场上电脑坦克小于场上最大数”且“准备上场的坦克数量大于0”且“计时器记录已过1.5s”时
			Rectangle bornRect = new Rectangle(botX[index], 1, 35, 35);// 创建坦克随机出生区域
			for (int i = 0, lengh = allTanks.size(); i < lengh; i++) {// 循环遍历所有坦克集合
				Tank t = allTanks.get(i);// 获取坦克对象
				if (t.isAlive() && t.hit(bornRect)) {// 如果场上存在与随机位置重合并存活的坦克
					return;// 结束方法
				}
			}
			switch (level) {
			case 2:
				botTanks.add(new BotTank(botX[index], 1, ImageUtil.BOT_LEVEL1_DOWN_IMAGE_URL, GamePanel.this,
						TankType.BOT1));
				break;

			case 3:
				if (botReadyCount % 3 == 0) {
					if (botReadyCount % 6 == 0) {
						botTanks.add(new BotTank(botX[index], 1, ImageUtil.BOT_LEVEL4_DOWN_IMAGE_URL, GamePanel.this,
								TankType.BOT4));
					} else {
						botTanks.add(new BotTank(botX[index], 1, ImageUtil.DJBOT_LEVEL1_DOWN_IMAGE_URL, GamePanel.this,
								TankType.DJBOT2));
					}

				} else {
					botTanks.add(new BotTank(botX[index], 1, ImageUtil.BOT_LEVEL1_DOWN_IMAGE_URL, GamePanel.this,
							TankType.BOT1));
				}
				break;
//			正确的第一关坦克生成，为了在第一关演示所有坦克，先注释掉，用下方的代码
			case 1:
				if (botReadyCount == 9) {
					botTanks.add(new BotTank(botX[index], 1, ImageUtil.DJBOT_LEVEL2_DOWN_IMAGE_URL, GamePanel.this,
							TankType.DJBOT2));
				}
				if (botReadyCount == 8) {
					botTanks.add(new BotTank(botX[index], 1, ImageUtil.BOT_LEVEL3_DOWN_IMAGE_URL, GamePanel.this,
							TankType.BOT3));
				}
				if (botReadyCount == 7) {
					botTanks.add(new BotTank(botX[index], 1, ImageUtil.DJBOT_LEVEL1_DOWN_IMAGE_URL, GamePanel.this,
							TankType.DJBOT1));
				}
				if (botReadyCount == 6) {
					botTanks.add(new BotTank(botX[index], 1, ImageUtil.DJBOT_LEVEL2_DOWN_IMAGE_URL, GamePanel.this,
							TankType.DJBOT2));
				}
				if (botReadyCount == 5) {
					botTanks.add(new BotTank(botX[index], 1, ImageUtil.BOT_LEVEL4_DOWN_IMAGE_URL, GamePanel.this,
							TankType.BOT4));
				}
				if (botReadyCount == 4) {
					botTanks.add(new BotTank(botX[index], 1, ImageUtil.DJBOT_LEVEL4_DOWN_IMAGE_URL, GamePanel.this,
							TankType.DJBOT4));
				}
				if (botReadyCount == 3) {
					botTanks.add(new BotTank(botX[index], 1, ImageUtil.DJBOT_LEVEL3_DOWN_IMAGE_URL, GamePanel.this,
							TankType.DJBOT3));
				}
				if (botReadyCount == 2) {
					botTanks.add(new BotTank(botX[index], 1, ImageUtil.BOT_LEVEL3_DOWN_IMAGE_URL, GamePanel.this,
							TankType.BOT3));
				}
				if (botReadyCount == 1) {
					botTanks.add(new BotTank(botX[index], 1, ImageUtil.BOT_LEVEL2_DOWN_IMAGE_URL, GamePanel.this,
							TankType.BOT2));
				}
				break;
			}

//qx	new AudioPlayer(AudioUtil.ADD).new AudioThread().start();//音效线程，暂时注释掉
			botReadyCount--;// 准备上场的电脑坦克数量-1
			createBotTimer = 0;// 产生电脑计时器重新计时
		}
	}

	private void paintTankAction() {
		if (SPACE_key) {// 如果“SPACE”键是按下状态
			player1.attack();// 玩家1坦克攻击
		}
		if (w_key) {// 如果“W”键是按下状态
			player1.upWard();// 玩家1坦克向上移动
		}
		if (d_key) {// 如果“D”键是按下状态
			player1.rightWard();// 玩家1坦克向右移动
		}
		if (a_key) {// 如果“A”键是按下状态
			player1.leftWard();
			;// 玩家1坦克左移动
		}
		if (s_key) {// 如果“S”键是按下状态
			player1.downWard();// 玩家1坦克向下移动
		}
		if (gameType == GameType.TWO_PLAYERS) {
			if (CTRL_key) {// 如果“小键盘1”键是按下状态
				player2.attack();// 玩家2坦克攻击
			}
			if (up_key) {// 如果“←”键是按下状态
				player2.upWard();// 玩家2坦克向上移动
			}
			if (right_key) {// 如果“→”键是按下状态
				player2.rightWard();// 玩家2坦克向右移动
			}
			if (left_key) {// 如果“↑”键是按下状态
				player2.leftWard();// 玩家2坦克左移动
			}
			if (down_key) {// 如果“↓”键是按下状态
				player2.downWard();// 玩家2坦克后移动
			}
		}
		// 以下是作弊码部分:
		if (Num1_key) {// 如果“1”键是按下状态
		
		}
		if (F1_key) {// 如果“F1”键是按下状态
			shouldCreateTool=true;//生成一个道具
			player1.addLife();// 玩家1生命值+1
			if(this.gameType==GameType.TWO_PLAYERS) {
			player2.addLife();// 玩家2生命值+1
			}
		}

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		switch (e.getKeyCode()) {// 判断按下的按键值

		case KeyEvent.VK_SPACE:// 如果按下的是“空格”
			SPACE_key = true;// “空格”按下标志为true
			break;
		case KeyEvent.VK_W:// 如果按下的是“W”
			w_key = true;// “W”按下标志为true
			a_key = false;// “A”按下标志为false
			s_key = false;// “S”按下标志为false
			d_key = false;// “D”按下标志为false
			break;
		case KeyEvent.VK_A:// 如果按下的是“A”
			w_key = false;// “W”按下标志为false
			a_key = true;// “A”按下标志为true
			s_key = false;// “S”按下标志为false
			d_key = false;// “D”按下标志为false
			break;
		case KeyEvent.VK_S:// 如果按下的是“S”
			w_key = false;// “W”按下标志为false
			a_key = false;// “A”按下标志为false
			s_key = true;// “S”按下标志为true
			d_key = false;// “D”按下标志为false
			break;
		case KeyEvent.VK_D:// 如果按下的是“D”
			w_key = false;// “W”按下标志为false
			a_key = false;// “A”按下标志为false
			s_key = false;// “S”按下标志为false
			d_key = true;// “D”按下标志为true
			break;
		case KeyEvent.VK_CONTROL:// 如果按下的是小键盘数字1
			CTRL_key = true;// "CTRL"按下标志为true
			break;
		case KeyEvent.VK_UP:// 如果按下的是“↑”
			up_key = true;// “↑”按下标志为true
			down_key = false;// “↓”按下标志为false
			right_key = false;// “→”按下标志为false
			left_key = false;// “←”按下标志为false
			break;
		case KeyEvent.VK_DOWN:// 如果按下的是“↓”
			up_key = false;// “↑”按下标志为false
			down_key = true;// “↓”按下标志为true
			right_key = false;// “→”按下标志为false
			left_key = false;// “←”按下标志为false
			break;
		case KeyEvent.VK_LEFT:// 如果按下的是“←”
			up_key = false;// “↑”按下标志为false
			down_key = false;// “↓”按下标志为false
			right_key = false;// “→”按下标志为false
			left_key = true;// “←”按下标志为true
			break;
		case KeyEvent.VK_RIGHT:// 如果按下的是“→”
			up_key = false;// “↑”按下标志为false
			down_key = false;// “↓”按下标志为false
			right_key = true;// “→”按下标志为true
			left_key = false;// “←”按下标志为false
			break;
		case KeyEvent.VK_1:
			Num1_key = true;
			break;
		case KeyEvent.VK_F1:
			F1_key = true;
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_SPACE:// 如果抬起的是“空格”
			SPACE_key = false;// “空格”按下标志为false
			break;
		case KeyEvent.VK_W:// 如果抬起的是“W”
			w_key = false;// “W”按下标志为false
			break;
		case KeyEvent.VK_A:// 如果抬起的是“A”
			a_key = false;// “A”按下标志为false
			break;
		case KeyEvent.VK_S:// 如果抬起的是“S”
			s_key = false;// “S”按下标志为false
			break;
		case KeyEvent.VK_D:// 如果抬起的是“D”
			d_key = false;// “D”按下标志为false
			break;
		case KeyEvent.VK_CONTROL:// 如果抬起的是小键盘1
			CTRL_key = false;// "CTRL"按下标志为false
			break;
		case KeyEvent.VK_UP:// 如果抬起的是“↑”
			up_key = false;// “↑”按下标志为false
			break;
		case KeyEvent.VK_DOWN:// 如果抬起的是“↓”
			down_key = false;// “↓”按下标志为false
			break;
		case KeyEvent.VK_LEFT:// 如果抬起的是“←”
			left_key = false;// “←”按下标志为false
			break;
		case KeyEvent.VK_RIGHT:// 如果抬起的是“→”
			right_key = false;// “→”按下标志为false
			break;
		case KeyEvent.VK_1:
			Num1_key = false;
			break;

		case KeyEvent.VK_F1:
			F1_key = false;
			break;
		}

	}

	public List<Tank> getTanks() {
		// TODO Auto-generated method stub
		return allTanks;
	}

	public List<Wall> getWalls() {
		// TODO Auto-generated method stub
		return walls;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	public void addBullet(Bullet b) {
		bullets.add(b);
	}

	/**
	 * 获取基地对象
	 * 
	 * @return 基地
	 */
	public BaseWall getBase() {
		return base;
	}

	public List<Bullet> getBullets() {
		return bullets;
	}

	private class JumpPageThead extends Thread {
		int level;// 跳转的关卡

		/**
		 * 跳转线程构造方法
		 * 
		 * @param level - 跳转的关卡
		 */
		public JumpPageThead(int level) {
			this.level = level;
		}

		/**
		 * 线程主方法
		 */
		public void run() {
			try {
				Thread.sleep(1000);// 1秒钟后
				frame.setPanel(new LevelPanel(level, gameType, frame));// 主窗体跳转到指定关卡
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	public List<Tank> getBotTanks() {
		return botTanks;
	}

	public List<Tank> getPlayerTanks() {
		// TODO Auto-generated method stub
		return playerTanks;
	}

	public Tool getTool() {
		return tool;
	}

	public void setSCTool(boolean a) {
		shouldCreateTool = a;
	}

	public boolean getSCTool() {
		return shouldCreateTool;
	}
}
