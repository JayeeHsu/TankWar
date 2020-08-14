package model;

import java.util.Random;

import util.ImageUtil;

public class River2Wall extends Wall {
	



	/**
	 * 
	 * 河流构造方法
	 * 
	 * @param x 初始化横坐标
	 * @param y 初始化纵坐标
	 */
	public River2Wall(int x, int y) {
		super(x,y,ImageUtil.RIVER2_IMAGE_URL);}
		
}