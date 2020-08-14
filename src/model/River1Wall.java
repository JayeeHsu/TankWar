package model;

import java.util.Random;

import util.ImageUtil;

public class River1Wall extends Wall {



	/**
	 * 
	 * 河流构造方法
	 * 
	 * @param x 初始化横坐标
	 * @param y 初始化纵坐标
	 */
	public River1Wall(int x, int y) {
		super(x,y,ImageUtil.RIVER1_IMAGE_URL);}
		
}