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
	private int level;//关卡值
	private GameType type;//游戏模式
	private MainFrame frame;//主窗体
	private Image stage;//关卡数图片
	private Image backgroud;// 灰色背景图片

	public LevelPanel(int level, GameType type, MainFrame frame) {
		try {
			stage =ImageIO.read(new File(ImageUtil.LOGIN_STAGE1_IMAGE_URL));//读取关卡数图片
			backgroud = ImageIO.read(new File(ImageUtil.LOGIN_HSBACKGROUD_IMAGE_URL));// 读取背景图片
		} catch (IOException e) {
			e.printStackTrace();
		}
	this.frame=frame;
	this.level=level;
	this.type=type;
	

	Thread t=new LevelPanelThread();//创建关卡面板动画线程
	t.start();
	}
	
	/**
	 * 重写绘图方法
	 */
	
	public void paint(Graphics g) {
	
		g.drawImage(backgroud,0,0,getWidth(),getHeight(),this);//绘制背景
		g.drawImage(stage,(getWidth()-stage.getWidth(null))/2,(getHeight()-stage.getHeight(null))/2,this);

			
	}
	
//	private void gotoGamePanel() {
//		System.gc();//回收对象内存
//		frame.setPanel(new GamePanel(frame, level, type));// 主窗体跳转到此关卡游戏面板
//	}
	private class LevelPanelThread extends Thread {
		
		public void run() {
			
			repaint();// 重绘组件
			try {
				Thread.sleep(2000);// 休眠0.5秒
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			gotoGamePanel();// 跳转到游戏面板
		}
		}
		
	private void gotoGamePanel() {
		System.gc();
		frame.setPanel(new GamePanel(frame, level, type));// 主窗体跳转到此关卡游戏面板
	}
	

	}
