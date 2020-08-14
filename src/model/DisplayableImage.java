package model;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/** ����ʾͼ������� */
public class DisplayableImage {
	public int x;// ͼ�������
	public int y;// ͼ��������
	int width;// ͼ��Ŀ�
	int height;//ͼ��ĸ�
	BufferedImage image;// ͼ�����

	/**
	 * BufferedImage��Image��һ�����࣬ Image��BufferedImage����Ҫ���þ��ǽ�һ��ͼƬ���ص��ڴ��С�
	 * BufferedImage���ɵ�ͼƬ���ڴ�����һ��ͼ�񻺳����� ����������������ǿ��Ժܷ���Ĳ������ͼƬ��
	 * ͨ��������ͼƬ�޸Ĳ������С�任��ͼƬ��ҡ�����ͼƬ͸����͸���ȡ�
	 */
	public DisplayableImage(int x, int y, int width, int height) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
	}// TYPE_INT_BGR ��ʾһ������ 8 λ RGB ��ɫ������ͼ��
		// ��Ӧ�� Windows �� Solaris ���� BGR ��ɫģ�ͣ�
		// ���д��Ϊ�������ص� Blue��Green �� Red ������ɫ��
	public DisplayableImage(int x, int y) {
		this.x = x;// ������
		this.y = y;// ������
	}
	
	public DisplayableImage(int x, int y, String url) {
		this.x = x;// ������
		this.y = y;// ������
		try {
			image = ImageIO.read(new File(url));
			this.width = image.getWidth();// ��ΪͼƬ��
			this.height = image.getHeight();// ��ΪͼƬ��

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
	 * ����ͼƬ
	 * @param image   ����ʾ��ͼƬ
	 */
	public void setImage(String url) {
		try {
			this.image = ImageIO.read(new File(url));// ��ȡָ��λ�õ�ͼƬ
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Rectangle getRect() {

		// ����һ��������(x,y)λ�ã����Ϊ(width, height) �ľ��α߽���󲢷���

		return new Rectangle(x, y, width, height);
	}
	


	// �ж��Ƿ�����ײ
	// rΪĿ��߽�
	// ��������ཻ���򷵻�true�����򷵻�false
	public boolean hit(DisplayableImage v) {
		return hit(v.getRect());// ִ�����ط���
	}
	
	

	public boolean hit(Rectangle r) {
		if (r == null) {//���Ŀ��Ϊ��
			return false;//���ز�������ײ
		}
		return this.getRect().intersects(r);// �������ߵı߽�����Ƿ�����
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
