package model;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Random;

import Enumtype.ToolType;
import util.ImageUtil;
import view.GamePanel;

public class Tool extends DisplayableImage{
	private static String[] imgURL= {
			ImageUtil.ADD_LIFE_URL,
			ImageUtil.BOMB_URL,
			ImageUtil.SPADE_URL,
			ImageUtil.TIMER_URL,
			ImageUtil.STAR_URL,
			ImageUtil.GUN_URL,
	};
	
	private static Image[] toolImgs= {
			Toolkit.getDefaultToolkit().createImage(imgURL[0]),
			Toolkit.getDefaultToolkit().createImage(imgURL[1]),
			Toolkit.getDefaultToolkit().createImage(imgURL[2]),
			Toolkit.getDefaultToolkit().createImage(imgURL[3]),
			Toolkit.getDefaultToolkit().createImage(imgURL[4]),
			Toolkit.getDefaultToolkit().createImage(imgURL[5]),
	};
	
	private int timer=0;//计时器
	private int aliveTime=9000;//道具存活的时间
	private Random r= new Random();//随机数对象
	private static int height=20,width=20;
	ToolType type;//道具类型
	private boolean alive =true;//存活状态
	private GamePanel gamePanel;
	public static Tool getToolInstance(int x,int y,GamePanel gamePanel) {
		return new Tool(x,y,gamePanel);
	}
	private Tool(int x,int y,GamePanel gamePanel) {
		super(x,y,width,height);
		this.gamePanel=gamePanel;
		type=ToolType.values()[r.nextInt(6)];
	}
	public void changeToolType() {
		type=ToolType.values()[r.nextInt(6)];
		x=r.nextInt(460);
		y=r.nextInt(500);
		this.alive=true;
	}
	public void draw(Graphics g) {
		if(timer>aliveTime) {
			this.gamePanel.setSCTool(false);
			timer=0;
			setAlive(false);
		}else {
			//System.out.println("画道具...(shouldCreateTool："+gamePanel.getSCTool()+")");
			g.drawImage(toolImgs[type.ordinal()],x,y,null);
			timer+=GamePanel.FRESHTIME;
		}
	}
	public void setAlive(boolean alive) {
		this.alive=alive;
		timer=0;
	}

	public boolean getAlive() {
		// TODO Auto-generated method stub
		return this.alive;
	}
	
}
