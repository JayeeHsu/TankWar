package model;

import util.ImageUtil;

public class BrickWall extends Wall{
	public BrickWall(int x,int y) {
		super(x, y, ImageUtil.BRICK_IMAGE_URL);//调用父类构造方法
	}
}
