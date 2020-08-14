package model;

import java.util.ArrayList;
import java.util.List;

import util.MapIO;



public class Map {
	public static List<Wall>walls =new ArrayList<>();//��ͼ������ǽ��ļ���
	
	//˽�й��췽��
	private Map() {
		
	}
	
	/**
	 * ��ȡ��ͼ����
	 *  level �ؿ���
	 *  @return ָ���ؿ��ĵ�ͼ����	
	 */
	
	public
	static Map getMap(String level) {
		walls.clear();//ǽ�鼯�����
		walls.addAll(MapIO.readMap(level));//��ȡָ���ؿ���ǽ�켯��
		//����Χǽ����
		for(int i=220;i<=280;i+=20) {
			for(int j=500;j<=540;j+=20) {
				if(i>=240&&i<=260&&j>=520) {//���ǽ��������غ�
					continue;//ִ����һ��ѭ��
				}else {
					walls.add(new BrickWall(i,j));
				}
			}
		}
		return new Map();//�����µĵ�ͼ����
	}
	/**
	 * ��ȡ��ͼ����
	 * 
	 * @param level
	 *              �ؿ���
	 * @return ָ���ؿ��ĵ�ͼ����
	 */
	public static Map getMap(int level) {
		return getMap(String.valueOf(level));// �������ط���
	}

	/**
	 * ��ȡ��ͼ�����е�����ǽ��
	 * 
	 * @return ǽ�鼯��
	 */
	public static List<Wall> getWalls() {
		return walls;
	}
	
	
}
