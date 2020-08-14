package util;

import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;

public class ImageUtil {
	
	/**https://zhidao.baidu.com/question/173305759.html
	  java���ò���ͼƬ��С�ķ��������˱��� */
	//�������ڰ�ͼƬ�ĵ�ַ���ַ�������ʽ��ţ�������������
	//�����������ͼ��
	//private Image backgroud;����һ��image����
	//backgroud = ImageIO.read(new File(ImageUtil.LOGIN_BACKGROUD_IMAGE_URL));��ͼƬ��������
	//g.drawImage(backgroud, 0, 0, getWidth(), getHeight(), this);����ͼƬ
	//��ը�ز�
	public static final String BOOM_IMAGE_URL1="image/��ը/111.png";
	public static final String BOOM_IMAGE_URL2="image/��ը/222.png";
	public static final String BOOM_IMAGE_URL3="image/��ը/333.png";
	public static final String BOOM_IMAGE_URL4="image/��ը/444.png";
	public static final String BOOM_IMAGE_URL5="image/��ը/555.png";
	
	
	//UI����ͼƬ�ز�
	public static final String LOGIN_BACKGROUD_IMAGE_URL="image/UI/��Ϸ���˵�/bgblack.png";
	public static final String LOGIN_HSBACKGROUD_IMAGE_URL="image/UI/��Ϸ����/bghuise.png";
	public static final String LOGIN_STAGE1_IMAGE_URL="image/UI/��Ϸ����/�ؿ�.png";
	public static final String GAME_NAME_IMAGE_URL = "image/UI/��Ϸ���˵�/battlecity1.png";
	public static final String LOGIN_CHOICETANK_IMAGE_URL="image/UI/��Ϸ���˵�/ѡ��̹��ͼ��1-2.png";
	public static final String ONE_PLAYER_IMAGE_URL="image/UI/��Ϸ���˵�/������Ϸ1.png";
	public static final String MAP_CONSTRUCTION_IMAGE_URL="image/UI/��Ϸ���˵�/��ͼ�༭��1.png";
	public static final String TWO_PLAYERS_IMAGE_URL="image/UI/��Ϸ���˵�/˫����Ϸ1.png";
	public static final String GAME_OVER_IMAGE_URL="image/UI/��Ϸ����/gameover-big2.png";
	public static final String VICTORY_IMAGE_URL = "C:/Users/HASEE/eclipse-workspace/TankWar/image/UI/��Ϸ����/vic.png";

	
	//�ӵ���
	public static final String BULLET_UP_IMAGE_URL="image/�ڵ�/�ڵ���2.png";
	public static final String BULLET_DOWN_IMAGE_URL="image/�ڵ�/�ڵ���2.png";
	public static final String BULLET_RIGHT_IMAGE_URL="image/�ڵ�/�ڵ���2.png";
	public static final String BULLET_LEFT_IMAGE_URL="image/�ڵ�/�ڵ���2.png";
	
	
	//����ש�飺
	public static final String BASE_IMAGE_URL = "image/��ͼ/base3.png";
	public static final String BREAK_BASE_IMAGE_URL = "image/��ͼ/base4.png";

	//�ݵ�ש�飺
	public static final String GRASS_IMAGE_URL="image/��ͼ/forest2.png";
	
	//שͷש��
	public static final String BRICK_IMAGE_URL="image/��ͼ/brick.png";
	
	//����שͷ
	public static final String IRON_IMAGE_URL="image/��ͼ/steelwall2.png";
	
	//����שͷ
	public static final String RIVER0_IMAGE_URL="image/��ͼ/river3-1.png";
	public static final String RIVER1_IMAGE_URL="image/��ͼ/river3-2.png";
	public static final String RIVER2_IMAGE_URL="image/��ͼ/river3-3.png";
	
	//�����ز�
	public static final String ADD_LIFE_URL="image/̹�˴�ս�زĳ��棨ģ������ͼ��ȡ��/̹�˴�ս�����زĿ�/����/̹��1.png";
	public static final String STAR_URL="image/̹�˴�ս�زĳ��棨ģ������ͼ��ȡ��/̹�˴�ս�����زĿ�/����/�����2.png";
	public static final String SPADE_URL="image/̹�˴�ս�زĳ��棨ģ������ͼ��ȡ��/̹�˴�ս�����زĿ�/����/����1.png";
	public static final String TIMER_URL="image/̹�˴�ս�زĳ��棨ģ������ͼ��ȡ��/̹�˴�ս�����زĿ�/����/�ӱ�1.png";
	public static final String BOMB_URL="image/̹�˴�ս�زĳ��棨ģ������ͼ��ȡ��/̹�˴�ս�����زĿ�/����/ը��1.png";
	public static final String GUN_URL="image/̹�˴�ս�زĳ��棨ģ������ͼ��ȡ��/̹�˴�ս�����زĿ�/����/�ֿ�1.png";
	//̹��ͼƬ�ز�
		/**���1̹�ˣ�*/
		public static final String PLAYER1_LEVEL1_UP_IMAGE_URL ="image/̹��/�ҷ�̹��/1Player/1/m1-2-1.png" ;
		public static final String PLAYER1_LEVEL1_DOWN_IMAGE_URL ="image/̹��/�ҷ�̹��/1Player/1/m1-4-1.png" ;
		public static final String PLAYER1_LEVEL1_RIGHT_IMAGE_URL ="image/̹��/�ҷ�̹��/1Player/1/m1-3-1.png" ;
		public static final String PLAYER1_LEVEL1_LEFT_IMAGE_URL ="image/̹��/�ҷ�̹��/1Player/1/m1-1-1.png" ;
		
		public static final String PLAYER1_LEVEL2_UP_IMAGE_URL ="image/̹��/�ҷ�̹��/1Player/2/m2-2-1.png" ;
		public static final String PLAYER1_LEVEL2_DOWN_IMAGE_URL ="image/̹��/�ҷ�̹��/1Player/2/m2-4-1.png" ;
		public static final String PLAYER1_LEVEL2_RIGHT_IMAGE_URL ="image/̹��/�ҷ�̹��/1Player/2/m2-3-1.png" ;
		public static final String PLAYER1_LEVEL2_LEFT_IMAGE_URL ="image/̹��/�ҷ�̹��/1Player/2/m2-1-1.png" ;
		
		public static final String PLAYER1_LEVEL3_UP_IMAGE_URL ="image/̹��/�ҷ�̹��/1Player/3/m3-2-1.png" ;
		public static final String PLAYER1_LEVEL3_DOWN_IMAGE_URL ="image/̹��/�ҷ�̹��/1Player/3/m3-4-1.png" ;
		public static final String PLAYER1_LEVEL3_RIGHT_IMAGE_URL ="image/̹��/�ҷ�̹��/1Player/3/m3-3-1.png" ;
		public static final String PLAYER1_LEVEL3_LEFT_IMAGE_URL ="image/̹��/�ҷ�̹��/1Player/3/m3-1-1.png" ;
		
		public static final String PLAYER1_LEVEL4_UP_IMAGE_URL ="image/̹��/�ҷ�̹��/1Player/4/m4-2-1.png" ;
		public static final String PLAYER1_LEVEL4_DOWN_IMAGE_URL ="image/̹��/�ҷ�̹��/1Player/4/m4-4-1.png" ;
		public static final String PLAYER1_LEVEL4_RIGHT_IMAGE_URL ="image/̹��/�ҷ�̹��/1Player/4/m4-3-1.png" ;
		public static final String PLAYER1_LEVEL4_LEFT_IMAGE_URL ="image/̹��/�ҷ�̹��/1Player/4/m4-1-1.png" ;
		
		public static final String PLAYER1_LEVEL1_UP_IMAGE2_URL ="image/̹��/�ҷ�̹��/1Player/1/m1-2-2.png" ;
		public static final String PLAYER1_LEVEL1_DOWN_IMAGE2_URL ="image/̹��/�ҷ�̹��/1Player/1/m1-4-2.png" ;
		public static final String PLAYER1_LEVEL1_RIGHT_IMAGE2_URL ="image/̹��/�ҷ�̹��/1Player/1/m1-3-2.png" ;
		public static final String PLAYER1_LEVEL1_LEFT_IMAGE2_URL ="image/̹��/�ҷ�̹��/1Player/1/m1-1-2.png" ;
		
		public static final String PLAYER1_LEVEL2_UP_IMAGE2_URL ="image/̹��/�ҷ�̹��/1Player/2/m2-2-2.png" ;
		public static final String PLAYER1_LEVEL2_DOWN_IMAGE2_URL ="image/̹��/�ҷ�̹��/1Player/2/m2-4-2.png" ;
		public static final String PLAYER1_LEVEL2_RIGHT_IMAGE2_URL ="image/̹��/�ҷ�̹��/1Player/2/m2-3-2.png" ;
		public static final String PLAYER1_LEVEL2_LEFT_IMAGE2_URL ="image/̹��/�ҷ�̹��/1Player/2/m2-1-2.png" ;
		
		public static final String PLAYER1_LEVEL3_UP_IMAGE2_URL ="image/̹��/�ҷ�̹��/1Player/3/m3-2-2.png" ;
		public static final String PLAYER1_LEVEL3_DOWN_IMAGE2_URL ="image/̹��/�ҷ�̹��/1Player/3/m3-4-2.png" ;
		public static final String PLAYER1_LEVEL3_RIGHT_IMAGE2_URL ="image/̹��/�ҷ�̹��/1Player/3/m3-3-2.png" ;
		public static final String PLAYER1_LEVEL3_LEFT_IMAGE2_URL ="image/̹��/�ҷ�̹��/1Player/3/m3-1-2.png" ;
		
		public static final String PLAYER1_LEVEL4_UP_IMAGE2_URL ="image/̹��/�ҷ�̹��/1Player/4/m4-2-2.png" ;
		public static final String PLAYER1_LEVEL4_DOWN_IMAGE2_URL ="image/̹��/�ҷ�̹��/1Player/4/m4-4-2.png" ;
		public static final String PLAYER1_LEVEL4_RIGHT_IMAGE2_URL ="image/̹��/�ҷ�̹��/1Player/4/m4-3-2.png" ;
		public static final String PLAYER1_LEVEL4_LEFT_IMAGE2_URL ="image/̹��/�ҷ�̹��/1Player/4/m4-1-2.png" ;
		
		
		
		/**���2̹�ˣ�*/
		public static final String PLAYER2_LEVEL1_UP_IMAGE_URL ="image/̹��/�ҷ�̹��/2Player/1/m21-2-1.png" ;
		public static final String PLAYER2_LEVEL1_DOWN_IMAGE_URL ="image/̹��/�ҷ�̹��/2Player/1/m21-4-1.png" ;
		public static final String PLAYER2_LEVEL1_RIGHT_IMAGE_URL ="image/̹��/�ҷ�̹��/2Player/1/m21-3-1.png" ;
		public static final String PLAYER2_LEVEL1_LEFT_IMAGE_URL ="image/̹��/�ҷ�̹��/2Player/1/m21-1-1.png" ;
		
		public static final String PLAYER2_LEVEL2_UP_IMAGE_URL ="image/̹��/�ҷ�̹��/2Player/2/m22-2-1.png" ;
		public static final String PLAYER2_LEVEL2_DOWN_IMAGE_URL ="image/̹��/�ҷ�̹��/2Player/2/m22-4-1.png" ;
		public static final String PLAYER2_LEVEL2_RIGHT_IMAGE_URL ="image/̹��/�ҷ�̹��/2Player/2/m22-3-1.png" ;
		public static final String PLAYER2_LEVEL2_LEFT_IMAGE_URL ="image/̹��/�ҷ�̹��/2Player/2/m22-1-1.png" ;
		
		public static final String PLAYER2_LEVEL3_UP_IMAGE_URL ="image/̹��/�ҷ�̹��/2Player/3/m23-2-1.png" ;
		public static final String PLAYER2_LEVEL3_DOWN_IMAGE_URL ="image/̹��/�ҷ�̹��/2Player/3/m23-4-1.png" ;
		public static final String PLAYER2_LEVEL3_RIGHT_IMAGE_URL ="image/̹��/�ҷ�̹��/2Player/3/m23-3-1.png" ;
		public static final String PLAYER2_LEVEL3_LEFT_IMAGE_URL ="image/̹��/�ҷ�̹��/2Player/3/m23-1-1.png" ;
		
		public static final String PLAYER2_LEVEL4_UP_IMAGE_URL ="image/̹��/�ҷ�̹��/2Player/4/m24-2-1.png" ;
		public static final String PLAYER2_LEVEL4_DOWN_IMAGE_URL ="image/̹��/�ҷ�̹��/2Player/4/m24-4-1.png" ;
		public static final String PLAYER2_LEVEL4_RIGHT_IMAGE_URL ="image/̹��/�ҷ�̹��/2Player/4/m24-3-1.png" ;
		public static final String PLAYER2_LEVEL4_LEFT_IMAGE_URL ="image/̹��/�ҷ�̹��/2Player/4/m24-1-1.png" ;
		
		public static final String PLAYER2_LEVEL1_UP_IMAGE2_URL ="image/̹��/�ҷ�̹��/2Player/1/m21-2-2.png" ;
		public static final String PLAYER2_LEVEL1_DOWN_IMAGE2_URL ="image/̹��/�ҷ�̹��/2Player/1/m21-4-2.png" ;
		public static final String PLAYER2_LEVEL1_RIGHT_IMAGE2_URL ="image/̹��/�ҷ�̹��/2Player/1/m21-3-2.png" ;
		public static final String PLAYER2_LEVEL1_LEFT_IMAGE2_URL ="image/̹��/�ҷ�̹��/2Player/1/m21-1-2.png" ;
		
		public static final String PLAYER2_LEVEL2_UP_IMAGE2_URL ="image/̹��/�ҷ�̹��/2Player/2/m22-2-2.png" ;
		public static final String PLAYER2_LEVEL2_DOWN_IMAGE2_URL ="image/̹��/�ҷ�̹��/2Player/2/m22-4-2.png" ;
		public static final String PLAYER2_LEVEL2_RIGHT_IMAGE2_URL ="image/̹��/�ҷ�̹��/2Player/2/m22-3-2.png" ;
		public static final String PLAYER2_LEVEL2_LEFT_IMAGE2_URL ="image/̹��/�ҷ�̹��/2Player/2/m22-1-2.png" ;
		
		public static final String PLAYER2_LEVEL3_UP_IMAGE2_URL ="image/̹��/�ҷ�̹��/2Player/3/m23-2-2.png" ;
		public static final String PLAYER2_LEVEL3_DOWN_IMAGE2_URL ="image/̹��/�ҷ�̹��/2Player/3/m23-4-2.png" ;
		public static final String PLAYER2_LEVEL3_RIGHT_IMAGE2_URL ="image/̹��/�ҷ�̹��/2Player/3/m23-3-2.png" ;
		public static final String PLAYER2_LEVEL3_LEFT_IMAGE2_URL ="image/̹��/�ҷ�̹��/2Player/3/m23-1-2.png" ;
		
		public static final String PLAYER2_LEVEL4_UP_IMAGE2_URL ="image/̹��/�ҷ�̹��/2Player/4/m24-2-2.png" ;
		public static final String PLAYER2_LEVEL4_DOWN_IMAGE2_URL ="image/̹��/�ҷ�̹��/2Player/4/m24-4-2.png" ;
		public static final String PLAYER2_LEVEL4_RIGHT_IMAGE2_URL ="image/̹��/�ҷ�̹��/2Player/4/m24-3-2.png" ;
		public static final String PLAYER2_LEVEL4_LEFT_IMAGE2_URL ="image/̹��/�ҷ�̹��/2Player/4/m24-1-2.png" ;
		
		/**�з���̹ͨ�ˣ�*/
		public static final String BOT_LEVEL1_UP_IMAGE_URL ="image/̹��/�з�̹��/��̹ͨ��/1/1-2-1.png";
		public static final String BOT_LEVEL1_DOWN_IMAGE_URL ="image/̹��/�з�̹��/��̹ͨ��/1/1-4-1.png";
		public static final String BOT_LEVEL1_RIGHT_IMAGE_URL ="image/̹��/�з�̹��/��̹ͨ��/1/1-3-1.png";
		public static final String BOT_LEVEL1_LEFT_IMAGE_URL ="image/̹��/�з�̹��/��̹ͨ��/1/1-1-1.png";
		
		public static final String BOT_LEVEL2_UP_IMAGE_URL ="image/̹��/�з�̹��/��̹ͨ��/2/2-2-1.png";
		public static final String BOT_LEVEL2_DOWN_IMAGE_URL ="image/̹��/�з�̹��/��̹ͨ��/2/2-4-1.png";
		public static final String BOT_LEVEL2_RIGHT_IMAGE_URL ="image/̹��/�з�̹��/��̹ͨ��/2/2-3-1.png";
		public static final String BOT_LEVEL2_LEFT_IMAGE_URL ="image/̹��/�з�̹��/��̹ͨ��/2/2-1-1.png";
		
		public static final String BOT_LEVEL3_UP_IMAGE_URL ="image/̹��/�з�̹��/��̹ͨ��/3/3-2-1.png";
		public static final String BOT_LEVEL3_DOWN_IMAGE_URL ="image/̹��/�з�̹��/��̹ͨ��/3/3-4-1.png";
		public static final String BOT_LEVEL3_RIGHT_IMAGE_URL ="image/̹��/�з�̹��/��̹ͨ��/3/3-3-1.png";
		public static final String BOT_LEVEL3_LEFT_IMAGE_URL ="image/̹��/�з�̹��/��̹ͨ��/3/3-1-1.png";
		
		public static final String BOT_LEVEL4_UP_IMAGE_URL ="image/̹��/�з�̹��/��̹ͨ��/4/4-2-1.png";
		public static final String BOT_LEVEL4_DOWN_IMAGE_URL ="image/̹��/�з�̹��/��̹ͨ��/4/4-4-1.png";
		public static final String BOT_LEVEL4_RIGHT_IMAGE_URL ="image/̹��/�з�̹��/��̹ͨ��/4/4-3-1.png";
		public static final String BOT_LEVEL4_LEFT_IMAGE_URL ="image/̹��/�з�̹��/��̹ͨ��/4/4-1-1.png";
		
		public static final String BOT_LEVEL1_UP_IMAGE2_URL ="image/̹��/�з�̹��/��̹ͨ��/1/1-2-2.png";
		public static final String BOT_LEVEL1_DOWN_IMAGE2_URL ="image/̹��/�з�̹��/��̹ͨ��/1/1-4-2.png";
		public static final String BOT_LEVEL1_RIGHT_IMAGE2_URL ="image/̹��/�з�̹��/��̹ͨ��/1/1-3-2.png";
		public static final String BOT_LEVEL1_LEFT_IMAGE2_URL ="image/̹��/�з�̹��/��̹ͨ��/1/1-1-2.png";
		
		public static final String BOT_LEVEL2_UP_IMAGE2_URL ="image/̹��/�з�̹��/��̹ͨ��/2/2-2-2.png";
		public static final String BOT_LEVEL2_DOWN_IMAGE2_URL ="image/̹��/�з�̹��/��̹ͨ��/2/2-4-2.png";
		public static final String BOT_LEVEL2_RIGHT_IMAGE2_URL ="image/̹��/�з�̹��/��̹ͨ��/2/2-3-2.png";
		public static final String BOT_LEVEL2_LEFT_IMAGE2_URL ="image/̹��/�з�̹��/��̹ͨ��/2/2-1-2.png";
		
		public static final String BOT_LEVEL3_UP_IMAGE2_URL ="image/̹��/�з�̹��/��̹ͨ��/3/3-2-2.png";
		public static final String BOT_LEVEL3_DOWN_IMAGE2_URL ="image/̹��/�з�̹��/��̹ͨ��/3/3-4-2.png";
		public static final String BOT_LEVEL3_RIGHT_IMAGE2_URL ="image/̹��/�з�̹��/��̹ͨ��/3/3-3-2.png";
		public static final String BOT_LEVEL3_LEFT_IMAGE2_URL ="image/̹��/�з�̹��/��̹ͨ��/3/3-1-2.png";
		
		public static final String BOT_LEVEL4_UP_IMAGE2_URL ="image/̹��/�з�̹��/��̹ͨ��/4/4-2-2.png";
		public static final String BOT_LEVEL4_DOWN_IMAGE2_URL ="image/̹��/�з�̹��/��̹ͨ��/4/4-4-2.png";
		public static final String BOT_LEVEL4_RIGHT_IMAGE2_URL ="image/̹��/�з�̹��/��̹ͨ��/4/4-3-2.png";
		public static final String BOT_LEVEL4_LEFT_IMAGE2_URL ="image/̹��/�з�̹��/��̹ͨ��/4/4-1-2.png";
		
		/**�з�����̹�ˣ�*/
		public static final String DJBOT_LEVEL1_UP_IMAGE_URL ="image/̹��/�з�̹��/����̹��/d1/dj1-2-1.png";
		public static final String DJBOT_LEVEL1_DOWN_IMAGE_URL ="image/̹��/�з�̹��/����̹��/d1/dj1-4-1.png";
		public static final String DJBOT_LEVEL1_RIGHT_IMAGE_URL ="image/̹��/�з�̹��/����̹��/d1/dj1-3-1.png";
		public static final String DJBOT_LEVEL1_LEFT_IMAGE_URL ="image/̹��/�з�̹��/����̹��/d1/dj1-1-1.png";
		
		public static final String DJBOT_LEVEL2_UP_IMAGE_URL ="image/̹��/�з�̹��/����̹��/d2/dj2-2-1.png";
		public static final String DJBOT_LEVEL2_DOWN_IMAGE_URL ="image/̹��/�з�̹��/����̹��/d2/dj2-4-1.png";
		public static final String DJBOT_LEVEL2_RIGHT_IMAGE_URL ="image/̹��/�з�̹��/����̹��/d2/dj2-3-1.png";
		public static final String DJBOT_LEVEL2_LEFT_IMAGE_URL ="image/̹��/�з�̹��/����̹��/d2/dj2-1-1.png";
		
		public static final String DJBOT_LEVEL3_UP_IMAGE_URL ="image/̹��/�з�̹��/����̹��/d3/dj3-2-1.png";
		public static final String DJBOT_LEVEL3_DOWN_IMAGE_URL ="image/̹��/�з�̹��/����̹��/d3/dj3-4-1.png";
		public static final String DJBOT_LEVEL3_RIGHT_IMAGE_URL ="image/̹��/�з�̹��/����̹��/d3/dj3-3-1.png";
		public static final String DJBOT_LEVEL3_LEFT_IMAGE_URL ="image/̹��/�з�̹��/����̹��/d3/dj3-1-1.png";
		
		public static final String DJBOT_LEVEL4_UP_IMAGE_URL ="image/̹��/�з�̹��/����̹��/d4/dj4-2-1.png";
		public static final String DJBOT_LEVEL4_DOWN_IMAGE_URL ="image/̹��/�з�̹��/����̹��/d4/dj4-4-1.png";
		public static final String DJBOT_LEVEL4_RIGHT_IMAGE_URL ="image/̹��/�з�̹��/����̹��/d4/dj4-3-1.png";
		public static final String DJBOT_LEVEL4_LEFT_IMAGE_URL ="image/̹��/�з�̹��/����̹��/d4/dj4-1-1.png";
		
		public static final String DJBOT_LEVEL1_UP_IMAGE2_URL ="image/̹��/�з�̹��/����̹��/d1/dj1-2-2.png";
		public static final String DJBOT_LEVEL1_DOWN_IMAGE2_URL ="image/̹��/�з�̹��/����̹��/d1/dj1-4-2.png";
		public static final String DJBOT_LEVEL1_RIGHT_IMAGE2_URL ="image/̹��/�з�̹��/����̹��/d1/dj1-3-2.png";
		public static final String DJBOT_LEVEL1_LEFT_IMAGE2_URL ="image/̹��/�з�̹��/����̹��/d1/dj1-1-2.png";
		
		public static final String DJBOT_LEVEL2_UP_IMAGE2_URL ="image/̹��/�з�̹��/����̹��/d2/dj2-2-2.png";
		public static final String DJBOT_LEVEL2_DOWN_IMAGE2_URL ="image/̹��/�з�̹��/����̹��/d2/dj2-4-2.png";
		public static final String DJBOT_LEVEL2_RIGHT_IMAGE2_URL ="image/̹��/�з�̹��/����̹��/d2/dj2-3-2.png";
		public static final String DJBOT_LEVEL2_LEFT_IMAGE2_URL ="image/̹��/�з�̹��/����̹��/d2/dj2-1-2.png";
		
		public static final String DJBOT_LEVEL3_UP_IMAGE2_URL ="image/̹��/�з�̹��/����̹��/d3/dj3-2-2.png";
		public static final String DJBOT_LEVEL3_DOWN_IMAGE2_URL ="image/̹��/�з�̹��/����̹��/d3/dj3-4-2.png";
		public static final String DJBOT_LEVEL3_RIGHT_IMAGE2_URL ="image/̹��/�з�̹��/����̹��/d3/dj3-3-2.png";
		public static final String DJBOT_LEVEL3_LEFT_IMAGE2_URL ="image/̹��/�з�̹��/����̹��/d3/dj3-1-2.png";
		
		public static final String DJBOT_LEVEL4_UP_IMAGE2_URL ="image/̹��/�з�̹��/����̹��/d4/ddj4-2-2.png";
		public static final String DJBOT_LEVEL4_DOWN_IMAGE2_URL ="image/̹��/�з�̹��/����̹��/d4/ddj4-4-2.png";
		public static final String DJBOT_LEVEL4_RIGHT_IMAGE2_URL ="image/̹��/�з�̹��/����̹��/d4/ddj4-3-2.png";
		public static final String DJBOT_LEVEL4_LEFT_IMAGE2_URL ="image/̹��/�з�̹��/����̹��/d4/ddj4-1-2.png";
		
		
}
