package model;

import java.util.ArrayList;
import java.util.List;

import util.MapIO;



public class Map {
	public static List<Wall>walls =new ArrayList<>();//地图中所有墙块的集合
	
	//私有构造方法
	private Map() {
		
	}
	
	/**
	 * 获取地图对象
	 *  level 关卡数
	 *  @return 指定关卡的地图对象	
	 */
	
	public
	static Map getMap(String level) {
		walls.clear();//墙块集合清空
		walls.addAll(MapIO.readMap(level));//读取指定关卡的墙快集合
		//基地围墙构造
		for(int i=220;i<=280;i+=20) {
			for(int j=500;j<=540;j+=20) {
				if(i>=240&&i<=260&&j>=520) {//如果墙快与基地重合
					continue;//执行下一次循环
				}else {
					walls.add(new BrickWall(i,j));
				}
			}
		}
		return new Map();//返回新的地图对象
	}
	/**
	 * 获取地图对象
	 * 
	 * @param level
	 *              关卡数
	 * @return 指定关卡的地图对象
	 */
	public static Map getMap(int level) {
		return getMap(String.valueOf(level));// 调用重载方法
	}

	/**
	 * 获取地图对象中的所有墙块
	 * 
	 * @return 墙块集合
	 */
	public static List<Wall> getWalls() {
		return walls;
	}
	
	
}
