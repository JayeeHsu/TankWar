package util;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;

import Enumtype.WallType;
import model.BrickWall;
import model.GrassWall;
import model.IronWall;
import model.River0Wall;
import model.River1Wall;
import model.River2Wall;
import model.Wall;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MapIO {
	//地图数据文件路径
	public final static String DATA_PATH="map\\data\\";
	//地图预览图路径
	public final static String IMAGE_PATH="map\\image\\";
	//地图数据文件后缀
	public final static String DATA_SUFFIX=".map";
	//地图预览图后缀
	public final static String IMAGE_SUFFIX=".png";

	/** 获取指定名称地图的所有墙块集合*/
	public static List<Wall>readMap(String mapName){
		//创建对应名称的地图文件
		File file = new File (DATA_PATH + mapName+DATA_SUFFIX);
		return readMap(file);//调用重载方法
	
	}
	
	public static List<Wall>readMap(File file )
	{
		Properties pro = new Properties();//创建属性集对象
		/*Properties类继承自Hashtable类并且实现了Map接口，
		也是使用一种键值对的形式来保存属性集。
		不过Properties有特殊的地方，就是它的键和值都是字符串类型。*/
		List<Wall>walls=new ArrayList<>();//创建总墙块集合
	
			try {
				pro.load(new FileInputStream(file));//属性集对象读取地图文件
				String brickStr=(String) pro.get(WallType.BRICK.name());// 读取地图文件中砖墙名称属性的字符串数据
				String grassStr = (String) pro.get(WallType.GRASS.name());// 读取地图文件中草地名称属性的字符串数据
				String river0Str = (String) pro.get(WallType.RIVER0.name());// 读取地图文件中河流名称属性的字符串数据
				String river1Str = (String) pro.get(WallType.RIVER1.name());// 读取地图文件中河流名称属性的字符串数据
				String river2Str = (String) pro.get(WallType.RIVER2.name());// 读取地图文件中河流名称属性的字符串数据
				String ironStr = (String) pro.get(WallType.IRON.name());// 读取地图文件中铁墙名称属性的字符串数据
				if(brickStr!=null) {//如果读取的砖墙数据不是null
					walls.addAll(readWall(brickStr,WallType.BRICK));//解析数据，并将数据中解析出的墙块集合添加到总墙块集合中
				}
				if(grassStr!=null) {//如果读取的草地数据不是null
					walls.addAll(readWall(grassStr,WallType.GRASS));//解析数据，并将数据中解析出的墙块集合添加到总墙块集合中
				}
				if (river0Str != null) {// 如果读取的河流数据不是null
					walls.addAll(readWall(river0Str, WallType.RIVER0));// 解析数据，并将数据中解析出的墙块集合添加到总墙块集合中
				}
				if (river1Str != null) {// 如果读取的河流数据不是null
					walls.addAll(readWall(river1Str, WallType.RIVER1));// 解析数据，并将数据中解析出的墙块集合添加到总墙块集合中
				}
				if (river2Str != null) {// 如果读取的河流数据不是null
					walls.addAll(readWall(river2Str, WallType.RIVER2));// 解析数据，并将数据中解析出的墙块集合添加到总墙块集合中
				}
				if (ironStr != null) {// 如果读取的铁墙数据不是null
					walls.addAll(readWall(ironStr, WallType.IRON));// 解析数据，并将数据中解析出的墙块集合添加到总墙块集合中
				}
				return walls;//返回总墙块集合
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return null;
	}

	private static List<Wall> readWall(String data, WallType type) {
		String walls[] = data.split(";");// 使用“;”分割字符串
		Wall wall;// 创建墙块对象
		List<Wall> w = new ArrayList<>();// 创建墙块集合
		switch (type) {// 判断墙块类型
		case BRICK:// 如果是砖墙
			for (String wStr : walls) {// 遍历分割结果
				 String axes[] = wStr.split(",");// 使用“,”分割字符串
				// 创建墙块对象，分割的第一个值为横坐标，分割的第二个值为纵坐标
				wall = new BrickWall(Integer.parseInt(axes[0]), Integer.parseInt(axes[1]));// 在此坐标上创建砖墙对象
				w.add(wall);// 集合中添加此墙块
			}
			break;
		case RIVER0:// 如果是河流
			for (String wStr : walls) {// 遍历分割结果
				String axes[] = wStr.split(",");// 使用“,”分割字符串
				// 创建墙块对象，分割的第一个值为横坐标，分割的第二个值为纵坐标
				wall = new River0Wall(Integer.parseInt(axes[0]), Integer.parseInt(axes[1]));// 在此坐标上创建河流对象
				w.add(wall);// 集合中添加此墙块
			}
		case RIVER1:// 如果是河流
			for (String wStr : walls) {// 遍历分割结果
				String axes[] = wStr.split(",");// 使用“,”分割字符串
				// 创建墙块对象，分割的第一个值为横坐标，分割的第二个值为纵坐标
				wall = new River1Wall(Integer.parseInt(axes[0]), Integer.parseInt(axes[1]));// 在此坐标上创建河流对象
				w.add(wall);// 集合中添加此墙块
			}
		case RIVER2:// 如果是河流
			for (String wStr : walls) {// 遍历分割结果
				String axes[] = wStr.split(",");// 使用“,”分割字符串
				// 创建墙块对象，分割的第一个值为横坐标，分割的第二个值为纵坐标
				wall = new River2Wall(Integer.parseInt(axes[0]), Integer.parseInt(axes[1]));// 在此坐标上创建河流对象
				w.add(wall);// 集合中添加此墙块
			}
			break;
		case GRASS:// 如果是草地
			for (String wStr : walls) {// 遍历分割结果
				String axes[] = wStr.split(",");// 使用“,”分割字符串
				// 创建墙块对象，分割的第一个值为横坐标，分割的第二个值为纵坐标
				wall = new GrassWall(Integer.parseInt(axes[0]), Integer.parseInt(axes[1]));// 在此坐标上创建草地对象
				w.add(wall);// 集合中添加此墙块
			}
			break;
		case IRON:// 如果是铁墙
			for (String wStr : walls) {// 遍历分割结果
				String axes[] = wStr.split(",");// 使用“,”分割字符串
				// 创建墙块对象，分割的第一个值为横坐标，分割的第二个值为纵坐标
				wall = new IronWall(Integer.parseInt(axes[0]), Integer.parseInt(axes[1]));// 在此坐标上创建铁墙对象
				w.add(wall);// 集合中添加此墙块
			}
			break;
		default:
			break;
		}
		return w;// 返回墙块集合
	}


}
