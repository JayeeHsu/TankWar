package model;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/** 可显示图像抽象类 */
public class DisplayableImage {
	public int x;// 图像横坐标
	public int y;// 图像纵坐标
	int width;// 图像的宽
	int height;//图像的高
	BufferedImage image;// 图像对象

	/**
	 * BufferedImage是Image的一个子类， Image和BufferedImage的主要作用就是将一副图片加载到内存中。
	 * BufferedImage生成的图片在内存里有一个图像缓冲区， 利用这个缓冲区我们可以很方便的操作这个图片，
	 * 通常用来做图片修改操作如大小变换、图片变灰、设置图片透明或不透明等。
	 */
	public DisplayableImage(int x, int y, int width, int height) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
	}// TYPE_INT_BGR 表示一个具有 8 位 RGB 颜色分量的图像，
		// 对应于 Windows 或 Solaris 风格的 BGR 颜色模型，
		// 具有打包为整数像素的 Blue、Green 和 Red 三种颜色。
	public DisplayableImage(int x, int y) {
		this.x = x;// 横坐标
		this.y = y;// 纵坐标
	}
	
	public DisplayableImage(int x, int y, String url) {
		this.x = x;// 横坐标
		this.y = y;// 纵坐标
		try {
			image = ImageIO.read(new File(url));
			this.width = image.getWidth();// 宽为图片宽
			this.height = image.getHeight();// 高为图片高

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	/**
	 * 设置图片
	 * @param image   所显示的图片
	 */
	public void setImage(String url) {
		try {
			this.image = ImageIO.read(new File(url));// 读取指定位置的图片
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Rectangle getRect() {

		// 创建一个坐标在(x,y)位置，宽高为(width, height) 的矩形边界对象并返回

		return new Rectangle(x, y, width, height);
	}
	


	// 判断是否发生碰撞
	// r为目标边界
	// 如果两者相交，则返回true，否则返回false
	public boolean hit(DisplayableImage v) {
		return hit(v.getRect());// 执行重载方法
	}
	
	

	public boolean hit(Rectangle r) {
		if (r == null) {//如果目标为空
			return false;//返回不发生碰撞
		}
		return this.getRect().intersects(r);// 返回两者的边界对象是否相切
	}
	


	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

}
