package model;

import java.awt.Graphics;
import java.util.HashMap;

import util.ImageUtil;
import view.GamePanel;

public class Boom extends DisplayableImage {
	private int timer = 0;// 计时器
	private int fresh = GamePanel.FRESHTIME;// 刷新时间
	private boolean alive = true;// 是否存活
	private String boomImage1;
	private String boomImage2;
	private String boomImage3;
	private String boomImage4;
	private String boomImage5;
	HashMap hm = new HashMap();

	/**
	 * 爆炸效果工作方法： x：爆炸图片横坐标 y：爆炸图片纵坐标
	 */
	public Boom(int x, int y) {
		super(x, y);
		boomImage1 = ImageUtil.BOOM_IMAGE_URL1;
		boomImage2 = ImageUtil.BOOM_IMAGE_URL2;
		boomImage3 = ImageUtil.BOOM_IMAGE_URL3;
		boomImage4 = ImageUtil.BOOM_IMAGE_URL4;
		boomImage5 = ImageUtil.BOOM_IMAGE_URL5;

	}

	public void show(Graphics g) {
		if (timer >= 300) {// 当计时器记录已到0.3秒
			alive = false;// 爆炸效果失效
		} else {
			int i = timer / 60;
			switch (i) {
			case 0:
				setImage(boomImage1);
				break;
			case 1:
				setImage(boomImage2);
				break;
			case 2:
				setImage(boomImage3);
				break;
			case 3:
				setImage(boomImage4);
				break;
			case 4:
				setImage(boomImage5);
				break;
			}
			g.drawImage(getImage(), x, y, null);// 绘制爆炸效果
			timer += fresh;// 计时器按照刷新时间递增
		}
	}

	public boolean isAlive() {
		return alive;
	}
}
