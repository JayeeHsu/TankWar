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
	 * ��Ϸ����FreshTime��5����
	 */
	private BufferedImage image;
	private Graphics g;// ͼƬ�Ļ�ͼ����
	private MainFrame frame;// ������
	private GameType gameType;// ��Ϸģʽ
	private Tank player1, player2;// ���1,���2
	private boolean SPACE_key, s_key, w_key, a_key, d_key, up_key, down_key, left_key, right_key, CTRL_key;// �����Ƿ��±�־����൥���ǰ�����
	private boolean Num1_key, Num2_key, Num3_key, Num4_key, Num5_key, Num6_key, F1_key, F2_key, F3_key, F4_key, F5_key,
			F6_key;;// �Ƿ񰴰�����־����൥���ǰ�����
	private Image GameoverImage;// ��Ϸ����ͼƬ
	private Image backgroud;// ��ɫ����ͼƬ
	private Image VictoryImage;
	private int level = Level.previsousLevel();// �ؿ�ֵ
	private List<Bullet> bullets;// �����ӵ�����
	private volatile List<Tank> allTanks;// ����̹�˺ϼ�
	private List<Tank> botTanks;// ����̹�˺ϼ�
	private static int botCount = 12;// ����̹������
	private int botReadyCount = botCount;// ׼�������ĵ���̹������
	private int botSurplusCount = botCount;// ����̹��ʣ����
	private int botMaxInMap = 6;// ����������̹����
	private int botX[] = { 0, 240, 480 };// ����̹�˳�����3��������λ��
	private List<Tank> playerTanks;// ���̹�˼���
	private volatile boolean finish = false;// ��Ϸ�Ƿ����
	/*
	 * volatile��һ���������η���type
	 * specifier��.volatile����������Ϊָ��ؼ��֣�ȷ������ָ�������������Ż���ʡ�ԣ���Ҫ��ÿ��ֱ�Ӷ�ֵ��
	 */
	private BaseWall base;// ����
	private List<Wall> walls;
	private List<Boom> boomImage;// ̹��������ı�ըЧ���ϼ�
	private Random r = new Random();// ���������
	private int createBotTimer = 0;// ��������̹�˼�ʱ��
	private Tank survivor;// ����ң������ߣ����ڻ������һ����ըЧ��
//qx//private List<AudioClip> audios = AudioUtil.getAudios();// ���б�����Ч�ļ���
	private Tool tool = Tool.getToolInstance(r.nextInt(460), r.nextInt(500), this);
	private boolean shouldCreateTool = false;
	private int pauseTimer = 0;// ����̹����ͣ��ʱ��

	// ��Ϸ��幹�췽����
	// frame ������
	// level �ؿ�
	// gameType ��Ϸģʽ

	/**
	 * ��Ϸ֡ˢ���߳��ڲ���
	 */
	private class FreshThread extends Thread {
		public void run() {// �߳�������
			while (!finish) {// �����Ϸδֹͣ
				repaint();// ִ�б����ػ淽��
				System.gc();// ����һ�λ���������������󣬻����ڴ�
				try {
					Thread.sleep(FRESHTIME);// ָ��ʱ������»��ƽ���
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * ������Ϸ֡ˢ��
	 */

	public GamePanel(MainFrame frame, int level, GameType gameType) {
		/**
		 * ��Ϸ֡ˢ���߳��ڲ���
		 */

		this.frame = frame;
		frame.setSize(660, 600);
		this.level = level;
		this.gameType = gameType;
		setBackground(Color.BLACK);
		init();// ��ʼ�����
		Thread t = new FreshThread();// ������֡ˢ���̣߳���һ������Ҫ���������Ļ���Ϸ�Ῠס
		t.start();// �����߳�
//qx	new AudioPlayer(AudioUtil.START).new AudioThread().start();// ���ű�����Ч
		addListener();// ��������
	}

	/**
	 * �����ʼ��
	 */
	private void init() {
		/**
		 * ��Ϸ֡ˢ���߳��ڲ���
		 */

		bullets = new ArrayList();// ʵ�����ӵ�����
		allTanks = new ArrayList();// ʵ��������̹�˼���
		walls = new ArrayList<>();// ʵ��������ǽ�鼯��
		boomImage = new ArrayList<>();// ʵ������ըЧ��
		try {
			backgroud = ImageIO.read(new File(ImageUtil.LOGIN_BACKGROUD_IMAGE_URL));// ��ȡ��ɫ����ͼƬ
			GameoverImage = ImageIO.read(new File(ImageUtil.GAME_OVER_IMAGE_URL));// ��ȡ��Ϸ����ͼƬ
			VictoryImage = ImageIO.read(new File(ImageUtil.VICTORY_IMAGE_URL));// ��ȡʤ��ͼƬ
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		image = new BufferedImage(1000, 800, BufferedImage.TYPE_INT_BGR);// ʵ������ͼƬ���������ʵ�ʴ�С
		g = image.getGraphics();// ��ȡ��ͼƬ��ͼ����

		playerTanks = new Vector<>();// ʵ�������̹�˼���
		player1 = new Tank(160, 520, ImageUtil.PLAYER1_LEVEL1_UP_IMAGE_URL, this, TankType.PLAYER1);// ʵ�������1
		playerTanks.add(player1);// ���̹�˼���������1
		if (gameType == GameType.TWO_PLAYERS) {// �����˫��ģʽ
			player2 = new Tank(320, 520, ImageUtil.PLAYER2_LEVEL1_UP_IMAGE_URL, this, TankType.PLAYER2);
			playerTanks.add(player2);// ���̹�˼���������2
		}

		botTanks = new ArrayList<>();// ʵ��������̹�˼���
		botTanks.add(new BotTank(botX[0], 1, ImageUtil.BOT_LEVEL1_DOWN_IMAGE_URL, this, TankType.BOT1));// �ڵ�һ��λ����ӵ���̹��
		botTanks.add(new BotTank(botX[1], 1, ImageUtil.BOT_LEVEL1_DOWN_IMAGE_URL, this, TankType.BOT1));// �ڵڶ���λ����ӵ���̹��
		botTanks.add(new BotTank(botX[2], 1, ImageUtil.BOT_LEVEL1_DOWN_IMAGE_URL, this, TankType.BOT1));// �ڵ�����λ����ӵ���̹��
		botReadyCount -= 3;// ׼��������̹��������ȥ��ʼ������
		allTanks.addAll(playerTanks);// ����̹�˼���������̹�˼���
		allTanks.addAll(botTanks);// ����̹�˼�����ӵ���̹�˼���
		base = new BaseWall(240, 520);// ʵ��������
		initWalls();// ��ʼ����ͼ�е�ǽ��
	}

	/**
	 * �齨����
	 */
	private void addListener() {
		frame.addKeyListener(this);// ������������̼�����������ʵ��KeyListener�ӿ�
	}

	/**
	 * ��ʼ����ͼ�е�ǽ��
	 */
	public void initWalls() {
		Map map = Map.getMap(level);// ��ȡ��ǰ�ؿ��ĵ�ͼ����
		walls.addAll(map.getWalls());// ǽ�鼯����ӵ�ǰ��ͼ�е�����ǽ��
		walls.add(base);// ǽ�鼯����ӻ���
	}

	/**
	 * ��д�����齨����
	 */

	public void paint(Graphics g) {
		paintTankAction();// ִ��̹�˶���
		createBotTank(level);// ѭ����������̹��
		paintImage();// ������Ҫ��ͼƬ
		g.drawImage(image, 0, 0, this);// ����ͼƬ���Ƶ������
		System.gc();// ���ڵ��������ռ������ڵ���ʱ�������ռ����������Ի���δʹ�õ��ڴ�ռ䡣
	}

	private void paintImage() {
		g.setColor(Color.BLACK);// ʹ�ú�ɫ����
		g.fillRect(0, 0, image.getWidth(), image.getHeight());// ���һ����������ͼƬ�ĺ�ɫ����
		paintBoom();// ���Ʊ�ըЧ��
		paintLife();// ����Ļ�Ҳ�������̹������ֵ
		paintBotTanks();// ���Ƶ���̹��
		paintPlayerTanks();// �������̹��
		allTanks.addAll(playerTanks);// ̹�˼���������̹�˼���
		allTanks.addAll(botTanks);// ̹�˼�����ӵ���̹�˼���
		paintWalls();// ����ǽ��
		paintBullets();// �����ӵ�
		g.drawImage(base.getImage(), base.x, base.y, this);// ���ƻ���
		paintTool();// ���Ƶ���
		paintBotCount();// ����Ļ�Ҳ����ʣ��̹������
		if (botSurplusCount == 0) {// ������е��Զ�������

			paintBotCount();// ����Ļ��������ʣ��̹������
			stopThread();// ������Ϸ֡ˢ���߳�
			paintVictory();
//Ϊ����ʾ���㣬��һ�ش����ֱ����ʾ�������棬������ʱע�͵�����ʵ�ǵڶ��صĵ�ͼ��ûд��������//gotoNextLevel();// ������һ�ؿ�
		}
		if (gameType == GameType.ONE_PLAYER) {// ����ǵ���ģʽ
			if (!player1.isAlive() && player1.getLife() == 0) {// ������1����,�������1������������0
				boomImage.add(new Boom(player1.x, player1.y));// ������1��ըЧ��
				paintBoom();// ���Ʊ�ըЧ��
				stopThread();// ������Ϸ֡ˢ���߳�
				paintGameOver();// ����Ļ�������game over
				gotoPrevisousLevel();// ���½��뱾�ؿ�
			}
		} else if (gameType == GameType.TWO_PLAYERS) {// �����˫��ģʽ
			if (player1.isAlive() && !player2.isAlive() && player2.getLife() == 0) {// ������1�� �Ҵ���
				survivor = player1;// �Ҵ��������1
			} else if (!player1.isAlive() && player1.getLife() == 0 && player2.isAlive()) {
				survivor = player2;// �Ҵ��������2
			} else if (!(player1.isAlive() || player2.isAlive())) {// ����������ȫ������

				boomImage.add(new Boom(survivor.x, survivor.y));// ����Ҵ��߱�ըЧ��
				paintBoom();// ���Ʊ�ըЧ��
				stopThread();// ������Ϸ֡ˢ���߳�
				paintGameOver();// ����Ļ�������game over
				gotoPrevisousLevel();// ���½��뱾�ؿ�
			}
		}

		if (!base.isAlive()) {// ������ر�����
			base.setImage(ImageUtil.BREAK_BASE_IMAGE_URL);// ����ʹ������ͼƬ
			stopThread();// ������Ϸ֡ˢ���߳�
			paintGameOver();// ����Ļ�������game over
			gotoPrevisousLevel();// ���½��뱾�ؿ�
		}
	}

	private void paintVictory() {
		g.drawImage(backgroud, 0, 0, getWidth(), getHeight(), this);// ���Ʊ���
		g.drawImage(VictoryImage, (getWidth() - VictoryImage.getWidth(null)) / 2,
				(getHeight() - VictoryImage.getHeight(null)) / 2, this);

//qx	new AudioPlayer(AudioUtil.GAMEOVER).new AudioThread().start();//�½�һ����Ч�̣߳����ڲ�����Ч

	}

	private void gotoPrevisousLevel() {
		Thread jump = new JumpPageThead(Level.previsousLevel());// �������½��뱾�ؿ����߳�
		jump.start();// �����߳�
	}

	private void gotoNextLevel() {
		Thread jump = new JumpPageThead(Level.nextLevel());// ������ת����һ�ؿ����߳�
		jump.start();// �����߳�
	}

	private synchronized void stopThread() {
		/**
		 * ynchronized �ؼ��֣����������������,�൱�ڲ�����һ���̣߳������߳�A���� ���е��������ʱ,��Ҫ�����û�������߳�B������C��
		 * D�ȣ�������������� (���߸��������ͬ������)���еĻ�Ҫ������ʹ��synchronized�������߳�B ������C
		 * ��D����������������������д��߳�A,û�еĻ�,����������, Ȼ��ֱ�����С�
		 */
		frame.removeKeyListener(this);// ������ɾ����������¼���������
		finish = true;// ��Ϸֹͣ��־Ϊtrue
	}

	private void paintWalls() {
		for (int i = 0; i < walls.size(); i++) {// ѭ������ǽ�鼯��
			Wall w = walls.get(i);// ��ȡǽ�����
			if (w.isAlive()) {// ���ǽ����Ч
				g.drawImage(w.getImage(), w.x, w.y, this);// ����ǽ��
			} else {// ���ǽ����Ч
				walls.remove(i);// �ڼ����Єh����ǽ��
				i--;// ѭ������-1����֤�´�ѭ��i��ֵ������i+1���Ա���Ч�������ϣ��ҷ�ֹ�±�Խ��
			}
		}
	}

	private void paintBullets() {
		for (int i = 0; i < bullets.size(); i++) {// ѭ�������ӵ�����
			Bullet b = bullets.get(i);// ��ȡ�ӵ�����
			if (b.isAlive()) {// ����ӵ���Ч
				b.move();// �ӵ�ִ���ƶ�����
				b.hitBase();// �ӵ�ִ�л��л����ж�
				b.hitWall();// �ӵ�ִ�л���ǽ���ж�
				b.hitTank();// �ӵ�ִ�л���̹���ж�
				b.hitBullet();// �ӵ�ִ�е����ж�
				g.drawImage(b.getImage(), b.x, b.y, this);// �����ӵ�
			} else {// ����ӵ���Ч
				bullets.remove(i);// �ڼ�����ɾ�����ӵ�
				i--;// ѭ������-1����֤�´�ѭ��i��ֵ������i+1���Ա���Ч�������ϣ��ҷ�ֹ�±�Խ��
			}
		}

	}

	private void paintBotTanks() {
		for (int i = 0; i < botTanks.size(); i++) {// ѭ����������̹�˼���
			BotTank t = (BotTank) botTanks.get(i);// ��ȡ����̹�˶���
			if (t.getLife() == 0) {
				t.setAlive(false);
			}
			if (t.isAlive()) {// ���̹�˴��
				if (!t.isPause()) {// �������̹�˲�������ͣ״̬
					t.go();// ����̹���ж�
				}
				if (t.isPause()) {// ����̹�˴�����ͣ״̬
					if (pauseTimer > 2500) {// �����ͣʱ�����2.5��
						t.setPause(false);// �����ͣ״̬
						pauseTimer = 0;// ��һ����ͣ״̬���¼�ʱ
					}
					pauseTimer += FRESHTIME;// ��ͣʱ�俪ʼ�ۻ�
				}
				if (t.getHit()) {
					t.decreaseLife();
					t.setHit(false);
				}

				g.drawImage(t.getImage(), t.x, t.y, this);// ����̹��
			} else {// ���̹������

				if (t.isDJBotTank()) {// ����ǵ���̹�ˣ�����shouldCreateTool����Ҫ����һ�����ߣ�Ϊ��
					tool.changeToolType();
					shouldCreateTool = true;
					// System.out.println("����̹������(shouldCreateTool��"+shouldCreateTool+")");
				}
				botTanks.remove(i);// ������ɾ����̹��
				i--;// ѭ������-1����֤�´�ѭ��i��ֵ������i+1���Ա���Ч�������ϣ��ҷ�ֹ�±�Խ��
				boomImage.add(new Boom(t.x, t.y));// ��̹��λ�ô�����ըЧ��
				decreaseBot();// ʣ��̹������-1
			}
		}

	}

	private void decreaseBot() {
		botSurplusCount--;// ����ʣ������-1
	}

	/**
	 * ���Ƶ���
	 */

	public void paintTool() {
		if (tool.getAlive() && shouldCreateTool) {// �����Ҫ����һ�����ߣ�ɱ����һ������̹�ˣ�paintbotTanks���������ж�����
			// System.out.println("�Ѿ�������һ������̹��...(shouldCreateTool��"+shouldCreateTool+")");
			tool.draw(g);
		}
	}

	private void paintPlayerTanks() {
		for (int i = 0; i < playerTanks.size(); i++) {// ѭ���������̹��
			Tank t = playerTanks.get(i);// ��ȡ���̹�˶���
			if (t.getLife() == 0) {
				t.setAlive(false);
			}
			if (t.isAlive()) {// ���̹�˴��
				t.hitTool();// �ж��Ƿ���ײ������
				t.addStar();
				g.drawImage(t.getImage(), t.x, t.y, this);// ����̹��
				if (t.getHit()) {
					t.decreaseLife();
					t.setHit(false);
				}
			} else {// ���̹������
				// TankType type=t.getTankType();
				// int life=t.getLife();
				playerTanks.remove(i);// ������ɾ����̹��
				boomImage.add(new Boom(t.x, t.y));// ��̹��λ�ô�����ըЧ��
//qx				AudioClip blast=audios.get(2);
//qx				blast.play();					
				 i--;// ѭ������-1����֤�´�ѭ��i��ֵ������i+1���Ա���Ч�������ϣ��ҷ�ֹ�±�Խ��

			}
		}

	}

	/**
	 * ����Ļ�Ҳ����ʣ��̹������
	 * 
	 */
	private void paintBotCount() {
		g.setColor(Color.green);// ʹ����ɫ
		g.drawString("�з�̹��ʣ�ࣺ" + botSurplusCount, 540, 36);// ��ָ����������ַ���

	}

	/**
	 * ����Ļ�Ҳ�������̹������ֵ
	 * 
	 */
	private void paintLife() {
		g.setColor(Color.ORANGE);// ʹ�ó�ɫ
		g.drawString("P1����ֵ��" + player1.getLife(), 540, 60);// ��ָ����������ַ���
		if (gameType.equals(GameType.TWO_PLAYERS)) {
			g.drawString("P2����ֵ��" + player2.getLife(), 540, 84);// ��ָ����������ַ���
		}
	}

	/**
	 * ����Ļ�������game over
	 */
	private void paintGameOver() {
		g.drawImage(backgroud, 0, 0, getWidth(), getHeight(), this);// ���Ʊ���
		g.drawImage(GameoverImage, (getWidth() - GameoverImage.getWidth(null)) / 2,
				(getHeight() - GameoverImage.getHeight(null)) / 2, this);

//qx	new AudioPlayer(AudioUtil.GAMEOVER).new AudioThread().start();//�½�һ����Ч�̣߳����ڲ�����Ч
	}

	/**
	 * ���Ʊ�ըЧ��
	 */
	private void paintBoom() {
		for (int i = 0; i < boomImage.size(); i++) {// ѭ��������ըЧ������
			Boom boom = boomImage.get(i);// ��ȡ��ը����
			if (boom.isAlive()) {// �����ըЧ����Ч
				/*
				 * qx AudioClip blast=audios.get(2);// ��ȡ��ը��Ч���� blast.play();// ���ű�ը��Ч
				 */
				boom.show(g);// չʾ��ըЧ��
			} else {// �����ըЧ����Ч
				boomImage.remove(i);// �ڼ����Єh���˱�ը����
				i--;// ѭ������-1����֤�´�ѭ��i��ֵ������i+1���Ա���Ч�������ϣ��ҷ�ֹ�±�Խ��
			}
		}
	}

	private void createBotTank(int level) {
		int index = r.nextInt(3);
		createBotTimer += FRESHTIME;// ��ʱ������ˢ��ʱ�����
		if (botTanks.size() < botMaxInMap && botReadyCount > 0 && createBotTimer >= 1500) {
			// �������ϵ���̹��С�ڳ�����������ҡ�׼���ϳ���̹����������0���ҡ���ʱ����¼�ѹ�1.5s��ʱ
			Rectangle bornRect = new Rectangle(botX[index], 1, 35, 35);// ����̹�������������
			for (int i = 0, lengh = allTanks.size(); i < lengh; i++) {// ѭ����������̹�˼���
				Tank t = allTanks.get(i);// ��ȡ̹�˶���
				if (t.isAlive() && t.hit(bornRect)) {// ������ϴ��������λ���غϲ�����̹��
					return;// ��������
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
//			��ȷ�ĵ�һ��̹�����ɣ�Ϊ���ڵ�һ����ʾ����̹�ˣ���ע�͵������·��Ĵ���
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

//qx	new AudioPlayer(AudioUtil.ADD).new AudioThread().start();//��Ч�̣߳���ʱע�͵�
			botReadyCount--;// ׼���ϳ��ĵ���̹������-1
			createBotTimer = 0;// �������Լ�ʱ�����¼�ʱ
		}
	}

	private void paintTankAction() {
		if (SPACE_key) {// �����SPACE�����ǰ���״̬
			player1.attack();// ���1̹�˹���
		}
		if (w_key) {// �����W�����ǰ���״̬
			player1.upWard();// ���1̹�������ƶ�
		}
		if (d_key) {// �����D�����ǰ���״̬
			player1.rightWard();// ���1̹�������ƶ�
		}
		if (a_key) {// �����A�����ǰ���״̬
			player1.leftWard();
			;// ���1̹�����ƶ�
		}
		if (s_key) {// �����S�����ǰ���״̬
			player1.downWard();// ���1̹�������ƶ�
		}
		if (gameType == GameType.TWO_PLAYERS) {
			if (CTRL_key) {// �����С����1�����ǰ���״̬
				player2.attack();// ���2̹�˹���
			}
			if (up_key) {// ������������ǰ���״̬
				player2.upWard();// ���2̹�������ƶ�
			}
			if (right_key) {// ������������ǰ���״̬
				player2.rightWard();// ���2̹�������ƶ�
			}
			if (left_key) {// ������������ǰ���״̬
				player2.leftWard();// ���2̹�����ƶ�
			}
			if (down_key) {// ������������ǰ���״̬
				player2.downWard();// ���2̹�˺��ƶ�
			}
		}
		// �����������벿��:
		if (Num1_key) {// �����1�����ǰ���״̬
		
		}
		if (F1_key) {// �����F1�����ǰ���״̬
			shouldCreateTool=true;//����һ������
			player1.addLife();// ���1����ֵ+1
			if(this.gameType==GameType.TWO_PLAYERS) {
			player2.addLife();// ���2����ֵ+1
			}
		}

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		switch (e.getKeyCode()) {// �жϰ��µİ���ֵ

		case KeyEvent.VK_SPACE:// ������µ��ǡ��ո�
			SPACE_key = true;// ���ո񡱰��±�־Ϊtrue
			break;
		case KeyEvent.VK_W:// ������µ��ǡ�W��
			w_key = true;// ��W�����±�־Ϊtrue
			a_key = false;// ��A�����±�־Ϊfalse
			s_key = false;// ��S�����±�־Ϊfalse
			d_key = false;// ��D�����±�־Ϊfalse
			break;
		case KeyEvent.VK_A:// ������µ��ǡ�A��
			w_key = false;// ��W�����±�־Ϊfalse
			a_key = true;// ��A�����±�־Ϊtrue
			s_key = false;// ��S�����±�־Ϊfalse
			d_key = false;// ��D�����±�־Ϊfalse
			break;
		case KeyEvent.VK_S:// ������µ��ǡ�S��
			w_key = false;// ��W�����±�־Ϊfalse
			a_key = false;// ��A�����±�־Ϊfalse
			s_key = true;// ��S�����±�־Ϊtrue
			d_key = false;// ��D�����±�־Ϊfalse
			break;
		case KeyEvent.VK_D:// ������µ��ǡ�D��
			w_key = false;// ��W�����±�־Ϊfalse
			a_key = false;// ��A�����±�־Ϊfalse
			s_key = false;// ��S�����±�־Ϊfalse
			d_key = true;// ��D�����±�־Ϊtrue
			break;
		case KeyEvent.VK_CONTROL:// ������µ���С��������1
			CTRL_key = true;// "CTRL"���±�־Ϊtrue
			break;
		case KeyEvent.VK_UP:// ������µ��ǡ�����
			up_key = true;// ���������±�־Ϊtrue
			down_key = false;// ���������±�־Ϊfalse
			right_key = false;// ���������±�־Ϊfalse
			left_key = false;// ���������±�־Ϊfalse
			break;
		case KeyEvent.VK_DOWN:// ������µ��ǡ�����
			up_key = false;// ���������±�־Ϊfalse
			down_key = true;// ���������±�־Ϊtrue
			right_key = false;// ���������±�־Ϊfalse
			left_key = false;// ���������±�־Ϊfalse
			break;
		case KeyEvent.VK_LEFT:// ������µ��ǡ�����
			up_key = false;// ���������±�־Ϊfalse
			down_key = false;// ���������±�־Ϊfalse
			right_key = false;// ���������±�־Ϊfalse
			left_key = true;// ���������±�־Ϊtrue
			break;
		case KeyEvent.VK_RIGHT:// ������µ��ǡ�����
			up_key = false;// ���������±�־Ϊfalse
			down_key = false;// ���������±�־Ϊfalse
			right_key = true;// ���������±�־Ϊtrue
			left_key = false;// ���������±�־Ϊfalse
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
		case KeyEvent.VK_SPACE:// ���̧����ǡ��ո�
			SPACE_key = false;// ���ո񡱰��±�־Ϊfalse
			break;
		case KeyEvent.VK_W:// ���̧����ǡ�W��
			w_key = false;// ��W�����±�־Ϊfalse
			break;
		case KeyEvent.VK_A:// ���̧����ǡ�A��
			a_key = false;// ��A�����±�־Ϊfalse
			break;
		case KeyEvent.VK_S:// ���̧����ǡ�S��
			s_key = false;// ��S�����±�־Ϊfalse
			break;
		case KeyEvent.VK_D:// ���̧����ǡ�D��
			d_key = false;// ��D�����±�־Ϊfalse
			break;
		case KeyEvent.VK_CONTROL:// ���̧�����С����1
			CTRL_key = false;// "CTRL"���±�־Ϊfalse
			break;
		case KeyEvent.VK_UP:// ���̧����ǡ�����
			up_key = false;// ���������±�־Ϊfalse
			break;
		case KeyEvent.VK_DOWN:// ���̧����ǡ�����
			down_key = false;// ���������±�־Ϊfalse
			break;
		case KeyEvent.VK_LEFT:// ���̧����ǡ�����
			left_key = false;// ���������±�־Ϊfalse
			break;
		case KeyEvent.VK_RIGHT:// ���̧����ǡ�����
			right_key = false;// ���������±�־Ϊfalse
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
	 * ��ȡ���ض���
	 * 
	 * @return ����
	 */
	public BaseWall getBase() {
		return base;
	}

	public List<Bullet> getBullets() {
		return bullets;
	}

	private class JumpPageThead extends Thread {
		int level;// ��ת�Ĺؿ�

		/**
		 * ��ת�̹߳��췽��
		 * 
		 * @param level - ��ת�Ĺؿ�
		 */
		public JumpPageThead(int level) {
			this.level = level;
		}

		/**
		 * �߳�������
		 */
		public void run() {
			try {
				Thread.sleep(1000);// 1���Ӻ�
				frame.setPanel(new LevelPanel(level, gameType, frame));// ��������ת��ָ���ؿ�
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
