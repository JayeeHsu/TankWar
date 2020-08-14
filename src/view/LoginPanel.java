package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import Enumtype.GameType;
import util.ImageUtil;

public class LoginPanel extends JPanel implements KeyListener {

	/* LoginPanel��̳�Jpanel�ಢʵ��KeyListener�ӿ� */

	private static final long seriaVersionUID = 1L;
	private GameType type;// ��Ϸģʽ��GameType���Ѷ����enum��ö�٣�����
	private Image backgroud;// ����ͼƬ
	private Image gamename;// ��Ϸ��ͼ�꣺BattleCity
	private Image tank;// ̹��ͼ��
	private Image onePlayer; // ������Ϸģʽ
	private Image twoPlayers;// ˫����Ϸģʽ
	private Image mapConstruction;//�Զ����ͼ�༭����������δʵ��
	private int y1 = 285, y2 = 345, y3=405, y4=465;// ̹��ͼ���ѡ����ĸ�Y����//Ŀǰֻ�õ�������������Ϊ�Զ����ͼ������ģʽ����δ���
	private int tankY = 285;// ̹��ͼ��Y����
	private MainFrame frame;
	

	// ��¼��幹�췽����
	public LoginPanel(MainFrame frame) {
		this.frame = frame;
		addListener();// ����������
		try {
			gamename = ImageIO.read(new File(ImageUtil.GAME_NAME_IMAGE_URL));
			backgroud = ImageIO.read(new File(ImageUtil.LOGIN_BACKGROUD_IMAGE_URL));// ��ȡ����ͼƬ
			tank = ImageIO.read(new File(ImageUtil.LOGIN_CHOICETANK_IMAGE_URL));// ��ȡѡ��̹��ͼ��
			onePlayer = ImageIO.read(new File(ImageUtil.ONE_PLAYER_IMAGE_URL));// ��ȡ������Ϸģʽͼ��
			twoPlayers = ImageIO.read(new File(ImageUtil.TWO_PLAYERS_IMAGE_URL));// ��ȡ˫����Ϸģʽͼ��
			mapConstruction = ImageIO.read(new File(ImageUtil.MAP_CONSTRUCTION_IMAGE_URL));// ��ͼ�༭��
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// ��д��ͼ������
	public void paint(Graphics g) {
		// ���Ʊ���ͼƬ������������壺
		g.drawImage(backgroud, 0, 0, getWidth(), getHeight(), this);
		// ������Ϸ��BATTLECITY
		g.drawImage(gamename, 100, 25, this);
		// ����ģʽѡ�
		g.drawImage(onePlayer, 300, 300, this);
		g.drawImage(twoPlayers, 300, 360, this);
		g.drawImage(mapConstruction, 300, 420, this);
		// ����ѡ��̹��ͼ�꣺
		g.drawImage(tank, 230, tankY, 35, 35, this);
		Font font = new Font("����", Font.BOLD, 10);// ��������


		
	}


	private void addListener() {
		frame.addKeyListener(this);// ������������̼�����������ʵ��KeyListener�ӿ�
	}
	private void gotoLevelPanel() {
		frame.removeKeyListener(this);// ������ɾ�����̼���
		frame.setPanel(new LevelPanel(1, type, frame));
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		// ��ʵ�ִ˷�����������ɾ��(ʵ�ֽӿ�)
	}

	@Override
	public void keyPressed(KeyEvent e) {  //�����¼�ʱִ�еĺ���
		int code = e.getKeyCode();//��ȡ���µİ���ֵ
		switch(code){
		case KeyEvent.VK_UP://������µ��ǡ�����
			if(tankY == y1) {
				tankY = y4;
			}else if(tankY == y4) {
				tankY = y3;
			}else if(tankY == y3){
				tankY = y2;
			}else if(tankY == y2){
				tankY=y1;
			}
			repaint();// ��������֮����Ҫ���»�ͼ
			break;
		case KeyEvent.VK_DOWN:

			if (tankY == y4) {
				tankY = y1;
			}else if(tankY ==y1){
				tankY = y2;
			}else if(tankY == y2){
				tankY = y3;
			}else if(tankY == y3){
				tankY=y4;}
			repaint();// ��������֮����Ҫ���»�ͼ
				break;
			case KeyEvent.VK_ENTER:// ������µ��ǡ�Enter��
				if (tankY == y1) {// ���̹��ͼ���ڵ�һ��λ��
					type=GameType.ONE_PLAYER;
					gotoLevelPanel();// ��ת�ؿ����
					frame.removeKeyListener(this);
				}
				if(tankY == y2){
					type = GameType.TWO_PLAYERS;// ��ϷģʽΪ˫��ģʽ
					gotoLevelPanel();// ��ת�ؿ����
					frame.removeKeyListener(this);
				}

				
			
			}
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		// ��ʵ�ִ˷�����������ɾ��(ʵ�ֽӿ�)
	}



}
