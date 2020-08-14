package model;

public abstract class Wall extends DisplayableImage {
	private boolean alive = true;// 墙块是否存活

	// 墙构造方法：x（初始化横坐标），y（初始化纵坐标），URL（初始化图片路径）
	public Wall(int x, int y, String url) {
		super(x, y, url);// 调用父类构造方法
	}

	public boolean isAlive() {
		return alive;
	}

	// 设置墙块存活情况
	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	// 重写判断方法，如果两个墙块坐标相同，则认为两个墙块为同一墙块
	public boolean equals(Object obj) {
		if (obj instanceof Wall) {
			Wall w = (Wall) obj;// 强制转换为墙块对象
			if (w.x == x && w.y == y) {
				return true;
			}
		}return super.equals(obj);//返回父类方法；
	}
}
