package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import Enumtype.GameType;
import util.ImageUtil;

public class LevelPanel extends JPanel {
	/**
	 * 
	 */
	private static final long seriaVersionUID=1L;
	private int level;//�ؿ�ֵ
	private GameType type;//��Ϸģʽ
	private MainFrame frame;//������
	private Image stage;//�ؿ���ͼƬ
	private Image backgroud;// ��ɫ����ͼƬ

	public LevelPanel(int level, GameType type, MainFrame frame) {
		try {
			stage =ImageIO.read(new File(ImageUtil.LOGIN_STAGE1_IMAGE_URL));//��ȡ�ؿ���ͼƬ
			backgroud = ImageIO.read(new File(ImageUtil.LOGIN_HSBACKGROUD_IMAGE_URL));// ��ȡ����ͼƬ
		} catch (IOException e) {
			e.printStackTrace();
		}
	this.frame=frame;
	this.level=level;
	this.type=type;
	

	Thread t=new LevelPanelThread();//�����ؿ���嶯���߳�
	t.start();
	}
	
	/**
	 * ��д��ͼ����
	 */
	
	public void paint(Graphics g) {
	
		g.drawImage(backgroud,0,0,getWidth(),getHeight(),this);//���Ʊ���
		g.drawImage(stage,(getWidth()-stage.getWidth(null))/2,(getHeight()-stage.getHeight(null))/2,this);

			
	}
	
//	private void gotoGamePanel() {
//		System.gc();//���ն����ڴ�
//		frame.setPanel(new GamePanel(frame, level, type));// ��������ת���˹ؿ���Ϸ���
//	}
	private class LevelPanelThread extends Thread {
		
		public void run() {
			
			repaint();// �ػ����
			try {
				Thread.sleep(2000);// ����0.5��
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			gotoGamePanel();// ��ת����Ϸ���
		}
		}
		
	private void gotoGamePanel() {
		System.gc();
		frame.setPanel(new GamePanel(frame, level, type));// ��������ת���˹ؿ���Ϸ���
	}
	

	}
