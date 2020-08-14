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
	//��ͼ�����ļ�·��
	public final static String DATA_PATH="map\\data\\";
	//��ͼԤ��ͼ·��
	public final static String IMAGE_PATH="map\\image\\";
	//��ͼ�����ļ���׺
	public final static String DATA_SUFFIX=".map";
	//��ͼԤ��ͼ��׺
	public final static String IMAGE_SUFFIX=".png";

	/** ��ȡָ�����Ƶ�ͼ������ǽ�鼯��*/
	public static List<Wall>readMap(String mapName){
		//������Ӧ���Ƶĵ�ͼ�ļ�
		File file = new File (DATA_PATH + mapName+DATA_SUFFIX);
		return readMap(file);//�������ط���
	
	}
	
	public static List<Wall>readMap(File file )
	{
		Properties pro = new Properties();//�������Լ�����
		/*Properties��̳���Hashtable�ಢ��ʵ����Map�ӿڣ�
		Ҳ��ʹ��һ�ּ�ֵ�Ե���ʽ���������Լ���
		����Properties������ĵط����������ļ���ֵ�����ַ������͡�*/
		List<Wall>walls=new ArrayList<>();//������ǽ�鼯��
	
			try {
				pro.load(new FileInputStream(file));//���Լ������ȡ��ͼ�ļ�
				String brickStr=(String) pro.get(WallType.BRICK.name());// ��ȡ��ͼ�ļ���שǽ�������Ե��ַ�������
				String grassStr = (String) pro.get(WallType.GRASS.name());// ��ȡ��ͼ�ļ��вݵ��������Ե��ַ�������
				String river0Str = (String) pro.get(WallType.RIVER0.name());// ��ȡ��ͼ�ļ��к����������Ե��ַ�������
				String river1Str = (String) pro.get(WallType.RIVER1.name());// ��ȡ��ͼ�ļ��к����������Ե��ַ�������
				String river2Str = (String) pro.get(WallType.RIVER2.name());// ��ȡ��ͼ�ļ��к����������Ե��ַ�������
				String ironStr = (String) pro.get(WallType.IRON.name());// ��ȡ��ͼ�ļ�����ǽ�������Ե��ַ�������
				if(brickStr!=null) {//�����ȡ��שǽ���ݲ���null
					walls.addAll(readWall(brickStr,WallType.BRICK));//�������ݣ����������н�������ǽ�鼯����ӵ���ǽ�鼯����
				}
				if(grassStr!=null) {//�����ȡ�Ĳݵ����ݲ���null
					walls.addAll(readWall(grassStr,WallType.GRASS));//�������ݣ����������н�������ǽ�鼯����ӵ���ǽ�鼯����
				}
				if (river0Str != null) {// �����ȡ�ĺ������ݲ���null
					walls.addAll(readWall(river0Str, WallType.RIVER0));// �������ݣ����������н�������ǽ�鼯����ӵ���ǽ�鼯����
				}
				if (river1Str != null) {// �����ȡ�ĺ������ݲ���null
					walls.addAll(readWall(river1Str, WallType.RIVER1));// �������ݣ����������н�������ǽ�鼯����ӵ���ǽ�鼯����
				}
				if (river2Str != null) {// �����ȡ�ĺ������ݲ���null
					walls.addAll(readWall(river2Str, WallType.RIVER2));// �������ݣ����������н�������ǽ�鼯����ӵ���ǽ�鼯����
				}
				if (ironStr != null) {// �����ȡ����ǽ���ݲ���null
					walls.addAll(readWall(ironStr, WallType.IRON));// �������ݣ����������н�������ǽ�鼯����ӵ���ǽ�鼯����
				}
				return walls;//������ǽ�鼯��
				
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
		String walls[] = data.split(";");// ʹ�á�;���ָ��ַ���
		Wall wall;// ����ǽ�����
		List<Wall> w = new ArrayList<>();// ����ǽ�鼯��
		switch (type) {// �ж�ǽ������
		case BRICK:// �����שǽ
			for (String wStr : walls) {// �����ָ���
				 String axes[] = wStr.split(",");// ʹ�á�,���ָ��ַ���
				// ����ǽ����󣬷ָ�ĵ�һ��ֵΪ�����꣬�ָ�ĵڶ���ֵΪ������
				wall = new BrickWall(Integer.parseInt(axes[0]), Integer.parseInt(axes[1]));// �ڴ������ϴ���שǽ����
				w.add(wall);// ��������Ӵ�ǽ��
			}
			break;
		case RIVER0:// ����Ǻ���
			for (String wStr : walls) {// �����ָ���
				String axes[] = wStr.split(",");// ʹ�á�,���ָ��ַ���
				// ����ǽ����󣬷ָ�ĵ�һ��ֵΪ�����꣬�ָ�ĵڶ���ֵΪ������
				wall = new River0Wall(Integer.parseInt(axes[0]), Integer.parseInt(axes[1]));// �ڴ������ϴ�����������
				w.add(wall);// ��������Ӵ�ǽ��
			}
		case RIVER1:// ����Ǻ���
			for (String wStr : walls) {// �����ָ���
				String axes[] = wStr.split(",");// ʹ�á�,���ָ��ַ���
				// ����ǽ����󣬷ָ�ĵ�һ��ֵΪ�����꣬�ָ�ĵڶ���ֵΪ������
				wall = new River1Wall(Integer.parseInt(axes[0]), Integer.parseInt(axes[1]));// �ڴ������ϴ�����������
				w.add(wall);// ��������Ӵ�ǽ��
			}
		case RIVER2:// ����Ǻ���
			for (String wStr : walls) {// �����ָ���
				String axes[] = wStr.split(",");// ʹ�á�,���ָ��ַ���
				// ����ǽ����󣬷ָ�ĵ�һ��ֵΪ�����꣬�ָ�ĵڶ���ֵΪ������
				wall = new River2Wall(Integer.parseInt(axes[0]), Integer.parseInt(axes[1]));// �ڴ������ϴ�����������
				w.add(wall);// ��������Ӵ�ǽ��
			}
			break;
		case GRASS:// ����ǲݵ�
			for (String wStr : walls) {// �����ָ���
				String axes[] = wStr.split(",");// ʹ�á�,���ָ��ַ���
				// ����ǽ����󣬷ָ�ĵ�һ��ֵΪ�����꣬�ָ�ĵڶ���ֵΪ������
				wall = new GrassWall(Integer.parseInt(axes[0]), Integer.parseInt(axes[1]));// �ڴ������ϴ����ݵض���
				w.add(wall);// ��������Ӵ�ǽ��
			}
			break;
		case IRON:// �������ǽ
			for (String wStr : walls) {// �����ָ���
				String axes[] = wStr.split(",");// ʹ�á�,���ָ��ַ���
				// ����ǽ����󣬷ָ�ĵ�һ��ֵΪ�����꣬�ָ�ĵڶ���ֵΪ������
				wall = new IronWall(Integer.parseInt(axes[0]), Integer.parseInt(axes[1]));// �ڴ������ϴ�����ǽ����
				w.add(wall);// ��������Ӵ�ǽ��
			}
			break;
		default:
			break;
		}
		return w;// ����ǽ�鼯��
	}


}
