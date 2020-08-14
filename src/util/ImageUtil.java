package util;

import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;

public class ImageUtil {
	
	/**https://zhidao.baidu.com/question/173305759.html
	  java设置插入图片大小的方法，留此备用 */
	//此类用于把图片的地址以字符串的形式存放，在其他函数中
	//用下例步骤绘图：
	//private Image backgroud;创建一个image变量
	//backgroud = ImageIO.read(new File(ImageUtil.LOGIN_BACKGROUD_IMAGE_URL));将图片赋给变量
	//g.drawImage(backgroud, 0, 0, getWidth(), getHeight(), this);绘制图片
	//爆炸素材
	public static final String BOOM_IMAGE_URL1="image/爆炸/111.png";
	public static final String BOOM_IMAGE_URL2="image/爆炸/222.png";
	public static final String BOOM_IMAGE_URL3="image/爆炸/333.png";
	public static final String BOOM_IMAGE_URL4="image/爆炸/444.png";
	public static final String BOOM_IMAGE_URL5="image/爆炸/555.png";
	
	
	//UI界面图片素材
	public static final String LOGIN_BACKGROUD_IMAGE_URL="image/UI/游戏主菜单/bgblack.png";
	public static final String LOGIN_HSBACKGROUD_IMAGE_URL="image/UI/游戏界面/bghuise.png";
	public static final String LOGIN_STAGE1_IMAGE_URL="image/UI/游戏界面/关卡.png";
	public static final String GAME_NAME_IMAGE_URL = "image/UI/游戏主菜单/battlecity1.png";
	public static final String LOGIN_CHOICETANK_IMAGE_URL="image/UI/游戏主菜单/选择坦克图标1-2.png";
	public static final String ONE_PLAYER_IMAGE_URL="image/UI/游戏主菜单/单人游戏1.png";
	public static final String MAP_CONSTRUCTION_IMAGE_URL="image/UI/游戏主菜单/地图编辑器1.png";
	public static final String TWO_PLAYERS_IMAGE_URL="image/UI/游戏主菜单/双人游戏1.png";
	public static final String GAME_OVER_IMAGE_URL="image/UI/游戏界面/gameover-big2.png";
	public static final String VICTORY_IMAGE_URL = "C:/Users/HASEE/eclipse-workspace/TankWar/image/UI/游戏界面/vic.png";

	
	//子弹：
	public static final String BULLET_UP_IMAGE_URL="image/炮弹/炮弹上2.png";
	public static final String BULLET_DOWN_IMAGE_URL="image/炮弹/炮弹下2.png";
	public static final String BULLET_RIGHT_IMAGE_URL="image/炮弹/炮弹右2.png";
	public static final String BULLET_LEFT_IMAGE_URL="image/炮弹/炮弹左2.png";
	
	
	//基地砖块：
	public static final String BASE_IMAGE_URL = "image/地图/base3.png";
	public static final String BREAK_BASE_IMAGE_URL = "image/地图/base4.png";

	//草地砖块：
	public static final String GRASS_IMAGE_URL="image/地图/forest2.png";
	
	//砖头砖块
	public static final String BRICK_IMAGE_URL="image/地图/brick.png";
	
	//铁块砖头
	public static final String IRON_IMAGE_URL="image/地图/steelwall2.png";
	
	//河流砖头
	public static final String RIVER0_IMAGE_URL="image/地图/river3-1.png";
	public static final String RIVER1_IMAGE_URL="image/地图/river3-2.png";
	public static final String RIVER2_IMAGE_URL="image/地图/river3-3.png";
	
	//道具素材
	public static final String ADD_LIFE_URL="image/坦克大战素材初版（模拟器截图提取）/坦克大战工版素材库/道具/坦克1.png";
	public static final String STAR_URL="image/坦克大战素材初版（模拟器截图提取）/坦克大战工版素材库/道具/五角星2.png";
	public static final String SPADE_URL="image/坦克大战素材初版（模拟器截图提取）/坦克大战工版素材库/道具/钢锹1.png";
	public static final String TIMER_URL="image/坦克大战素材初版（模拟器截图提取）/坦克大战工版素材库/道具/钟表1.png";
	public static final String BOMB_URL="image/坦克大战素材初版（模拟器截图提取）/坦克大战工版素材库/道具/炸弹1.png";
	public static final String GUN_URL="image/坦克大战素材初版（模拟器截图提取）/坦克大战工版素材库/道具/钢盔1.png";
	//坦克图片素材
		/**玩家1坦克：*/
		public static final String PLAYER1_LEVEL1_UP_IMAGE_URL ="image/坦克/我方坦克/1Player/1/m1-2-1.png" ;
		public static final String PLAYER1_LEVEL1_DOWN_IMAGE_URL ="image/坦克/我方坦克/1Player/1/m1-4-1.png" ;
		public static final String PLAYER1_LEVEL1_RIGHT_IMAGE_URL ="image/坦克/我方坦克/1Player/1/m1-3-1.png" ;
		public static final String PLAYER1_LEVEL1_LEFT_IMAGE_URL ="image/坦克/我方坦克/1Player/1/m1-1-1.png" ;
		
		public static final String PLAYER1_LEVEL2_UP_IMAGE_URL ="image/坦克/我方坦克/1Player/2/m2-2-1.png" ;
		public static final String PLAYER1_LEVEL2_DOWN_IMAGE_URL ="image/坦克/我方坦克/1Player/2/m2-4-1.png" ;
		public static final String PLAYER1_LEVEL2_RIGHT_IMAGE_URL ="image/坦克/我方坦克/1Player/2/m2-3-1.png" ;
		public static final String PLAYER1_LEVEL2_LEFT_IMAGE_URL ="image/坦克/我方坦克/1Player/2/m2-1-1.png" ;
		
		public static final String PLAYER1_LEVEL3_UP_IMAGE_URL ="image/坦克/我方坦克/1Player/3/m3-2-1.png" ;
		public static final String PLAYER1_LEVEL3_DOWN_IMAGE_URL ="image/坦克/我方坦克/1Player/3/m3-4-1.png" ;
		public static final String PLAYER1_LEVEL3_RIGHT_IMAGE_URL ="image/坦克/我方坦克/1Player/3/m3-3-1.png" ;
		public static final String PLAYER1_LEVEL3_LEFT_IMAGE_URL ="image/坦克/我方坦克/1Player/3/m3-1-1.png" ;
		
		public static final String PLAYER1_LEVEL4_UP_IMAGE_URL ="image/坦克/我方坦克/1Player/4/m4-2-1.png" ;
		public static final String PLAYER1_LEVEL4_DOWN_IMAGE_URL ="image/坦克/我方坦克/1Player/4/m4-4-1.png" ;
		public static final String PLAYER1_LEVEL4_RIGHT_IMAGE_URL ="image/坦克/我方坦克/1Player/4/m4-3-1.png" ;
		public static final String PLAYER1_LEVEL4_LEFT_IMAGE_URL ="image/坦克/我方坦克/1Player/4/m4-1-1.png" ;
		
		public static final String PLAYER1_LEVEL1_UP_IMAGE2_URL ="image/坦克/我方坦克/1Player/1/m1-2-2.png" ;
		public static final String PLAYER1_LEVEL1_DOWN_IMAGE2_URL ="image/坦克/我方坦克/1Player/1/m1-4-2.png" ;
		public static final String PLAYER1_LEVEL1_RIGHT_IMAGE2_URL ="image/坦克/我方坦克/1Player/1/m1-3-2.png" ;
		public static final String PLAYER1_LEVEL1_LEFT_IMAGE2_URL ="image/坦克/我方坦克/1Player/1/m1-1-2.png" ;
		
		public static final String PLAYER1_LEVEL2_UP_IMAGE2_URL ="image/坦克/我方坦克/1Player/2/m2-2-2.png" ;
		public static final String PLAYER1_LEVEL2_DOWN_IMAGE2_URL ="image/坦克/我方坦克/1Player/2/m2-4-2.png" ;
		public static final String PLAYER1_LEVEL2_RIGHT_IMAGE2_URL ="image/坦克/我方坦克/1Player/2/m2-3-2.png" ;
		public static final String PLAYER1_LEVEL2_LEFT_IMAGE2_URL ="image/坦克/我方坦克/1Player/2/m2-1-2.png" ;
		
		public static final String PLAYER1_LEVEL3_UP_IMAGE2_URL ="image/坦克/我方坦克/1Player/3/m3-2-2.png" ;
		public static final String PLAYER1_LEVEL3_DOWN_IMAGE2_URL ="image/坦克/我方坦克/1Player/3/m3-4-2.png" ;
		public static final String PLAYER1_LEVEL3_RIGHT_IMAGE2_URL ="image/坦克/我方坦克/1Player/3/m3-3-2.png" ;
		public static final String PLAYER1_LEVEL3_LEFT_IMAGE2_URL ="image/坦克/我方坦克/1Player/3/m3-1-2.png" ;
		
		public static final String PLAYER1_LEVEL4_UP_IMAGE2_URL ="image/坦克/我方坦克/1Player/4/m4-2-2.png" ;
		public static final String PLAYER1_LEVEL4_DOWN_IMAGE2_URL ="image/坦克/我方坦克/1Player/4/m4-4-2.png" ;
		public static final String PLAYER1_LEVEL4_RIGHT_IMAGE2_URL ="image/坦克/我方坦克/1Player/4/m4-3-2.png" ;
		public static final String PLAYER1_LEVEL4_LEFT_IMAGE2_URL ="image/坦克/我方坦克/1Player/4/m4-1-2.png" ;
		
		
		
		/**玩家2坦克：*/
		public static final String PLAYER2_LEVEL1_UP_IMAGE_URL ="image/坦克/我方坦克/2Player/1/m21-2-1.png" ;
		public static final String PLAYER2_LEVEL1_DOWN_IMAGE_URL ="image/坦克/我方坦克/2Player/1/m21-4-1.png" ;
		public static final String PLAYER2_LEVEL1_RIGHT_IMAGE_URL ="image/坦克/我方坦克/2Player/1/m21-3-1.png" ;
		public static final String PLAYER2_LEVEL1_LEFT_IMAGE_URL ="image/坦克/我方坦克/2Player/1/m21-1-1.png" ;
		
		public static final String PLAYER2_LEVEL2_UP_IMAGE_URL ="image/坦克/我方坦克/2Player/2/m22-2-1.png" ;
		public static final String PLAYER2_LEVEL2_DOWN_IMAGE_URL ="image/坦克/我方坦克/2Player/2/m22-4-1.png" ;
		public static final String PLAYER2_LEVEL2_RIGHT_IMAGE_URL ="image/坦克/我方坦克/2Player/2/m22-3-1.png" ;
		public static final String PLAYER2_LEVEL2_LEFT_IMAGE_URL ="image/坦克/我方坦克/2Player/2/m22-1-1.png" ;
		
		public static final String PLAYER2_LEVEL3_UP_IMAGE_URL ="image/坦克/我方坦克/2Player/3/m23-2-1.png" ;
		public static final String PLAYER2_LEVEL3_DOWN_IMAGE_URL ="image/坦克/我方坦克/2Player/3/m23-4-1.png" ;
		public static final String PLAYER2_LEVEL3_RIGHT_IMAGE_URL ="image/坦克/我方坦克/2Player/3/m23-3-1.png" ;
		public static final String PLAYER2_LEVEL3_LEFT_IMAGE_URL ="image/坦克/我方坦克/2Player/3/m23-1-1.png" ;
		
		public static final String PLAYER2_LEVEL4_UP_IMAGE_URL ="image/坦克/我方坦克/2Player/4/m24-2-1.png" ;
		public static final String PLAYER2_LEVEL4_DOWN_IMAGE_URL ="image/坦克/我方坦克/2Player/4/m24-4-1.png" ;
		public static final String PLAYER2_LEVEL4_RIGHT_IMAGE_URL ="image/坦克/我方坦克/2Player/4/m24-3-1.png" ;
		public static final String PLAYER2_LEVEL4_LEFT_IMAGE_URL ="image/坦克/我方坦克/2Player/4/m24-1-1.png" ;
		
		public static final String PLAYER2_LEVEL1_UP_IMAGE2_URL ="image/坦克/我方坦克/2Player/1/m21-2-2.png" ;
		public static final String PLAYER2_LEVEL1_DOWN_IMAGE2_URL ="image/坦克/我方坦克/2Player/1/m21-4-2.png" ;
		public static final String PLAYER2_LEVEL1_RIGHT_IMAGE2_URL ="image/坦克/我方坦克/2Player/1/m21-3-2.png" ;
		public static final String PLAYER2_LEVEL1_LEFT_IMAGE2_URL ="image/坦克/我方坦克/2Player/1/m21-1-2.png" ;
		
		public static final String PLAYER2_LEVEL2_UP_IMAGE2_URL ="image/坦克/我方坦克/2Player/2/m22-2-2.png" ;
		public static final String PLAYER2_LEVEL2_DOWN_IMAGE2_URL ="image/坦克/我方坦克/2Player/2/m22-4-2.png" ;
		public static final String PLAYER2_LEVEL2_RIGHT_IMAGE2_URL ="image/坦克/我方坦克/2Player/2/m22-3-2.png" ;
		public static final String PLAYER2_LEVEL2_LEFT_IMAGE2_URL ="image/坦克/我方坦克/2Player/2/m22-1-2.png" ;
		
		public static final String PLAYER2_LEVEL3_UP_IMAGE2_URL ="image/坦克/我方坦克/2Player/3/m23-2-2.png" ;
		public static final String PLAYER2_LEVEL3_DOWN_IMAGE2_URL ="image/坦克/我方坦克/2Player/3/m23-4-2.png" ;
		public static final String PLAYER2_LEVEL3_RIGHT_IMAGE2_URL ="image/坦克/我方坦克/2Player/3/m23-3-2.png" ;
		public static final String PLAYER2_LEVEL3_LEFT_IMAGE2_URL ="image/坦克/我方坦克/2Player/3/m23-1-2.png" ;
		
		public static final String PLAYER2_LEVEL4_UP_IMAGE2_URL ="image/坦克/我方坦克/2Player/4/m24-2-2.png" ;
		public static final String PLAYER2_LEVEL4_DOWN_IMAGE2_URL ="image/坦克/我方坦克/2Player/4/m24-4-2.png" ;
		public static final String PLAYER2_LEVEL4_RIGHT_IMAGE2_URL ="image/坦克/我方坦克/2Player/4/m24-3-2.png" ;
		public static final String PLAYER2_LEVEL4_LEFT_IMAGE2_URL ="image/坦克/我方坦克/2Player/4/m24-1-2.png" ;
		
		/**敌方普通坦克：*/
		public static final String BOT_LEVEL1_UP_IMAGE_URL ="image/坦克/敌方坦克/普通坦克/1/1-2-1.png";
		public static final String BOT_LEVEL1_DOWN_IMAGE_URL ="image/坦克/敌方坦克/普通坦克/1/1-4-1.png";
		public static final String BOT_LEVEL1_RIGHT_IMAGE_URL ="image/坦克/敌方坦克/普通坦克/1/1-3-1.png";
		public static final String BOT_LEVEL1_LEFT_IMAGE_URL ="image/坦克/敌方坦克/普通坦克/1/1-1-1.png";
		
		public static final String BOT_LEVEL2_UP_IMAGE_URL ="image/坦克/敌方坦克/普通坦克/2/2-2-1.png";
		public static final String BOT_LEVEL2_DOWN_IMAGE_URL ="image/坦克/敌方坦克/普通坦克/2/2-4-1.png";
		public static final String BOT_LEVEL2_RIGHT_IMAGE_URL ="image/坦克/敌方坦克/普通坦克/2/2-3-1.png";
		public static final String BOT_LEVEL2_LEFT_IMAGE_URL ="image/坦克/敌方坦克/普通坦克/2/2-1-1.png";
		
		public static final String BOT_LEVEL3_UP_IMAGE_URL ="image/坦克/敌方坦克/普通坦克/3/3-2-1.png";
		public static final String BOT_LEVEL3_DOWN_IMAGE_URL ="image/坦克/敌方坦克/普通坦克/3/3-4-1.png";
		public static final String BOT_LEVEL3_RIGHT_IMAGE_URL ="image/坦克/敌方坦克/普通坦克/3/3-3-1.png";
		public static final String BOT_LEVEL3_LEFT_IMAGE_URL ="image/坦克/敌方坦克/普通坦克/3/3-1-1.png";
		
		public static final String BOT_LEVEL4_UP_IMAGE_URL ="image/坦克/敌方坦克/普通坦克/4/4-2-1.png";
		public static final String BOT_LEVEL4_DOWN_IMAGE_URL ="image/坦克/敌方坦克/普通坦克/4/4-4-1.png";
		public static final String BOT_LEVEL4_RIGHT_IMAGE_URL ="image/坦克/敌方坦克/普通坦克/4/4-3-1.png";
		public static final String BOT_LEVEL4_LEFT_IMAGE_URL ="image/坦克/敌方坦克/普通坦克/4/4-1-1.png";
		
		public static final String BOT_LEVEL1_UP_IMAGE2_URL ="image/坦克/敌方坦克/普通坦克/1/1-2-2.png";
		public static final String BOT_LEVEL1_DOWN_IMAGE2_URL ="image/坦克/敌方坦克/普通坦克/1/1-4-2.png";
		public static final String BOT_LEVEL1_RIGHT_IMAGE2_URL ="image/坦克/敌方坦克/普通坦克/1/1-3-2.png";
		public static final String BOT_LEVEL1_LEFT_IMAGE2_URL ="image/坦克/敌方坦克/普通坦克/1/1-1-2.png";
		
		public static final String BOT_LEVEL2_UP_IMAGE2_URL ="image/坦克/敌方坦克/普通坦克/2/2-2-2.png";
		public static final String BOT_LEVEL2_DOWN_IMAGE2_URL ="image/坦克/敌方坦克/普通坦克/2/2-4-2.png";
		public static final String BOT_LEVEL2_RIGHT_IMAGE2_URL ="image/坦克/敌方坦克/普通坦克/2/2-3-2.png";
		public static final String BOT_LEVEL2_LEFT_IMAGE2_URL ="image/坦克/敌方坦克/普通坦克/2/2-1-2.png";
		
		public static final String BOT_LEVEL3_UP_IMAGE2_URL ="image/坦克/敌方坦克/普通坦克/3/3-2-2.png";
		public static final String BOT_LEVEL3_DOWN_IMAGE2_URL ="image/坦克/敌方坦克/普通坦克/3/3-4-2.png";
		public static final String BOT_LEVEL3_RIGHT_IMAGE2_URL ="image/坦克/敌方坦克/普通坦克/3/3-3-2.png";
		public static final String BOT_LEVEL3_LEFT_IMAGE2_URL ="image/坦克/敌方坦克/普通坦克/3/3-1-2.png";
		
		public static final String BOT_LEVEL4_UP_IMAGE2_URL ="image/坦克/敌方坦克/普通坦克/4/4-2-2.png";
		public static final String BOT_LEVEL4_DOWN_IMAGE2_URL ="image/坦克/敌方坦克/普通坦克/4/4-4-2.png";
		public static final String BOT_LEVEL4_RIGHT_IMAGE2_URL ="image/坦克/敌方坦克/普通坦克/4/4-3-2.png";
		public static final String BOT_LEVEL4_LEFT_IMAGE2_URL ="image/坦克/敌方坦克/普通坦克/4/4-1-2.png";
		
		/**敌方道具坦克：*/
		public static final String DJBOT_LEVEL1_UP_IMAGE_URL ="image/坦克/敌方坦克/道具坦克/d1/dj1-2-1.png";
		public static final String DJBOT_LEVEL1_DOWN_IMAGE_URL ="image/坦克/敌方坦克/道具坦克/d1/dj1-4-1.png";
		public static final String DJBOT_LEVEL1_RIGHT_IMAGE_URL ="image/坦克/敌方坦克/道具坦克/d1/dj1-3-1.png";
		public static final String DJBOT_LEVEL1_LEFT_IMAGE_URL ="image/坦克/敌方坦克/道具坦克/d1/dj1-1-1.png";
		
		public static final String DJBOT_LEVEL2_UP_IMAGE_URL ="image/坦克/敌方坦克/道具坦克/d2/dj2-2-1.png";
		public static final String DJBOT_LEVEL2_DOWN_IMAGE_URL ="image/坦克/敌方坦克/道具坦克/d2/dj2-4-1.png";
		public static final String DJBOT_LEVEL2_RIGHT_IMAGE_URL ="image/坦克/敌方坦克/道具坦克/d2/dj2-3-1.png";
		public static final String DJBOT_LEVEL2_LEFT_IMAGE_URL ="image/坦克/敌方坦克/道具坦克/d2/dj2-1-1.png";
		
		public static final String DJBOT_LEVEL3_UP_IMAGE_URL ="image/坦克/敌方坦克/道具坦克/d3/dj3-2-1.png";
		public static final String DJBOT_LEVEL3_DOWN_IMAGE_URL ="image/坦克/敌方坦克/道具坦克/d3/dj3-4-1.png";
		public static final String DJBOT_LEVEL3_RIGHT_IMAGE_URL ="image/坦克/敌方坦克/道具坦克/d3/dj3-3-1.png";
		public static final String DJBOT_LEVEL3_LEFT_IMAGE_URL ="image/坦克/敌方坦克/道具坦克/d3/dj3-1-1.png";
		
		public static final String DJBOT_LEVEL4_UP_IMAGE_URL ="image/坦克/敌方坦克/道具坦克/d4/dj4-2-1.png";
		public static final String DJBOT_LEVEL4_DOWN_IMAGE_URL ="image/坦克/敌方坦克/道具坦克/d4/dj4-4-1.png";
		public static final String DJBOT_LEVEL4_RIGHT_IMAGE_URL ="image/坦克/敌方坦克/道具坦克/d4/dj4-3-1.png";
		public static final String DJBOT_LEVEL4_LEFT_IMAGE_URL ="image/坦克/敌方坦克/道具坦克/d4/dj4-1-1.png";
		
		public static final String DJBOT_LEVEL1_UP_IMAGE2_URL ="image/坦克/敌方坦克/道具坦克/d1/dj1-2-2.png";
		public static final String DJBOT_LEVEL1_DOWN_IMAGE2_URL ="image/坦克/敌方坦克/道具坦克/d1/dj1-4-2.png";
		public static final String DJBOT_LEVEL1_RIGHT_IMAGE2_URL ="image/坦克/敌方坦克/道具坦克/d1/dj1-3-2.png";
		public static final String DJBOT_LEVEL1_LEFT_IMAGE2_URL ="image/坦克/敌方坦克/道具坦克/d1/dj1-1-2.png";
		
		public static final String DJBOT_LEVEL2_UP_IMAGE2_URL ="image/坦克/敌方坦克/道具坦克/d2/dj2-2-2.png";
		public static final String DJBOT_LEVEL2_DOWN_IMAGE2_URL ="image/坦克/敌方坦克/道具坦克/d2/dj2-4-2.png";
		public static final String DJBOT_LEVEL2_RIGHT_IMAGE2_URL ="image/坦克/敌方坦克/道具坦克/d2/dj2-3-2.png";
		public static final String DJBOT_LEVEL2_LEFT_IMAGE2_URL ="image/坦克/敌方坦克/道具坦克/d2/dj2-1-2.png";
		
		public static final String DJBOT_LEVEL3_UP_IMAGE2_URL ="image/坦克/敌方坦克/道具坦克/d3/dj3-2-2.png";
		public static final String DJBOT_LEVEL3_DOWN_IMAGE2_URL ="image/坦克/敌方坦克/道具坦克/d3/dj3-4-2.png";
		public static final String DJBOT_LEVEL3_RIGHT_IMAGE2_URL ="image/坦克/敌方坦克/道具坦克/d3/dj3-3-2.png";
		public static final String DJBOT_LEVEL3_LEFT_IMAGE2_URL ="image/坦克/敌方坦克/道具坦克/d3/dj3-1-2.png";
		
		public static final String DJBOT_LEVEL4_UP_IMAGE2_URL ="image/坦克/敌方坦克/道具坦克/d4/ddj4-2-2.png";
		public static final String DJBOT_LEVEL4_DOWN_IMAGE2_URL ="image/坦克/敌方坦克/道具坦克/d4/ddj4-4-2.png";
		public static final String DJBOT_LEVEL4_RIGHT_IMAGE2_URL ="image/坦克/敌方坦克/道具坦克/d4/ddj4-3-2.png";
		public static final String DJBOT_LEVEL4_LEFT_IMAGE2_URL ="image/坦克/敌方坦克/道具坦克/d4/ddj4-1-2.png";
		
		
}
