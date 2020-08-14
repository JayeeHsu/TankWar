package model;

import java.awt.Graphics;
import java.util.HashMap;

import util.ImageUtil;
import view.GamePanel;

public class Boom extends DisplayableImage {
	private int timer = 0;// ��ʱ��
	private int fresh = GamePanel.FRESHTIME;// ˢ��ʱ��
	private boolean alive = true;// �Ƿ���
	private String boomImage1;
	private String boomImage2;
	private String boomImage3;
	private String boomImage4;
	private String boomImage5;
	HashMap hm = new HashMap();

	/**
	 * ��ըЧ������������ x����ըͼƬ������ y����ըͼƬ������
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
		if (timer >= 300) {// ����ʱ����¼�ѵ�0.3��
			alive = false;// ��ըЧ��ʧЧ
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
			g.drawImage(getImage(), x, y, null);// ���Ʊ�ըЧ��
			timer += fresh;// ��ʱ������ˢ��ʱ�����
		}
	}

	public boolean isAlive() {
		return alive;
	}
}
