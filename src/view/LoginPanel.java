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

	/* LoginPanel类继承Jpanel类并实现KeyListener接口 */

	private static final long seriaVersionUID = 1L;
	private GameType type;// 游戏模式，GameType是已定义的enum（枚举）类型
	private Image backgroud;// 背景图片
	private Image gamename;// 游戏名图标：BattleCity
	private Image tank;// 坦克图标
	private Image onePlayer; // 单人游戏模式
	private Image twoPlayers;// 双人游戏模式
	private Image mapConstruction;//自定义地图编辑器，功能尚未实现
	private int y1 = 285, y2 = 345, y3=405, y4=465;// 坦克图标可选择的四个Y坐标//目前只用到两个，后两个为自定义地图和在线模式，尚未完成
	private int tankY = 285;// 坦克图标Y坐标
	private MainFrame frame;
	

	// 登录面板构造方法：
	public LoginPanel(MainFrame frame) {
		this.frame = frame;
		addListener();// 添加组件监听
		try {
			gamename = ImageIO.read(new File(ImageUtil.GAME_NAME_IMAGE_URL));
			backgroud = ImageIO.read(new File(ImageUtil.LOGIN_BACKGROUD_IMAGE_URL));// 读取背景图片
			tank = ImageIO.read(new File(ImageUtil.LOGIN_CHOICETANK_IMAGE_URL));// 读取选择坦克图标
			onePlayer = ImageIO.read(new File(ImageUtil.ONE_PLAYER_IMAGE_URL));// 读取单人游戏模式图标
			twoPlayers = ImageIO.read(new File(ImageUtil.TWO_PLAYERS_IMAGE_URL));// 读取双人游戏模式图标
			mapConstruction = ImageIO.read(new File(ImageUtil.MAP_CONSTRUCTION_IMAGE_URL));// 地图编辑器
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 重写绘图方法：
	public void paint(Graphics g) {
		// 绘制背景图片，填满整个面板：
		g.drawImage(backgroud, 0, 0, getWidth(), getHeight(), this);
		// 绘制游戏名BATTLECITY
		g.drawImage(gamename, 100, 25, this);
		// 绘制模式选项：
		g.drawImage(onePlayer, 300, 300, this);
		g.drawImage(twoPlayers, 300, 360, this);
		g.drawImage(mapConstruction, 300, 420, this);
		// 绘制选择坦克图标：
		g.drawImage(tank, 230, tankY, 35, 35, this);
		Font font = new Font("宋体", Font.BOLD, 10);// 创建字体


		
	}


	private void addListener() {
		frame.addKeyListener(this);// 主窗体载入键盘监听，本类已实现KeyListener接口
	}
	private void gotoLevelPanel() {
		frame.removeKeyListener(this);// 主窗体删除键盘监听
		frame.setPanel(new LevelPanel(1, type, frame));
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		// 不实现此方法，但不可删除(实现接口)
	}

	@Override
	public void keyPressed(KeyEvent e) {  //当按下键时执行的函数
		int code = e.getKeyCode();//获取按下的按键值
		switch(code){
		case KeyEvent.VK_UP://如果按下的是“↑”
			if(tankY == y1) {
				tankY = y4;
			}else if(tankY == y4) {
				tankY = y3;
			}else if(tankY == y3){
				tankY = y2;
			}else if(tankY == y2){
				tankY=y1;
			}
			repaint();// 按键按下之后，需要重新绘图
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
			repaint();// 按键按下之后，需要重新绘图
				break;
			case KeyEvent.VK_ENTER:// 如果按下的是“Enter”
				if (tankY == y1) {// 如果坦克图标在第一个位置
					type=GameType.ONE_PLAYER;
					gotoLevelPanel();// 跳转关卡面板
					frame.removeKeyListener(this);
				}
				if(tankY == y2){
					type = GameType.TWO_PLAYERS;// 游戏模式为双人模式
					gotoLevelPanel();// 跳转关卡面板
					frame.removeKeyListener(this);
				}

				
			
			}
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		// 不实现此方法，但不可删除(实现接口)
	}



}
