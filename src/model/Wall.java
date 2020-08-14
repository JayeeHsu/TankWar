package model;

public abstract class Wall extends DisplayableImage {
	private boolean alive = true;// ǽ���Ƿ���

	// ǽ���췽����x����ʼ�������꣩��y����ʼ�������꣩��URL����ʼ��ͼƬ·����
	public Wall(int x, int y, String url) {
		super(x, y, url);// ���ø��๹�췽��
	}

	public boolean isAlive() {
		return alive;
	}

	// ����ǽ�������
	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	// ��д�жϷ������������ǽ��������ͬ������Ϊ����ǽ��Ϊͬһǽ��
	public boolean equals(Object obj) {
		if (obj instanceof Wall) {
			Wall w = (Wall) obj;// ǿ��ת��Ϊǽ�����
			if (w.x == x && w.y == y) {
				return true;
			}
		}return super.equals(obj);//���ظ��෽����
	}
}
