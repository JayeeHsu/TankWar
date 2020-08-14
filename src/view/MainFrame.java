package view;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	/*
	* ����serialization��key��
	* A��B�໥֮�䴫����Ϣ����seralize�������໥֮��ѽ��֮����ļ������˸��ģ���������в���������໥֮���ٴ��䣬
	* ����Ϊ���key��һ������ʧ�ܡ����ԣ��ڳ����ж��壬��ʹ����汾���ݣ�������ô�ģ��������໥���л��ͷ����л���
	* Java�У����classʵ�������л��ӿڣ���û�м���һ�У�eclipse���Զ���warning��������ϣ�����
	* JVM���Զ���������һ�����кţ�
	* �����������ɷ����л�ʧ�ܡ���Ϊ��ͬ��JVM֮������л��㷨�ǲ�һ���ģ������ڳ������ֲ��һ������£�
	* �������л���class����һ�����л���ID���������Ա�֤���л��ĳɹ����汾�ļ����ԡ�
	*/

	// ���췽����
	public MainFrame() {
		setTitle("TankWar");// ���ñ���
		setSize(800, 600);// ���ô��ڿ��
		setResizable(false);// ����Ϊ���ɵ�����С
		Toolkit tool = Toolkit.getDefaultToolkit(); // ����ϵͳ��Ĭ��������߰�
		Dimension d = tool.getScreenSize(); // ��ȡ��Ļ�ߴ磬����һ����ά�������
		setLocation(300,100);// ������������Ļ�м���ʾ
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		/*
		 * �����û��ڴ˴����Ϸ��� "close" ʱĬ��ִ�еĲ���,DO_NOTHING_ON_CLOSE��ʾ��ִ���κβ����� Ҫ���������ע���
		 * WindowListener ����� windowClosing �����д���ò�����
		 * ��������ٶ�https://zhidao.baidu.com/question/440799321.html
		 */
		addListener();// ����¼�����,�������·�
		setPanel(new LoginPanel(this));// ��ӵ�½���
		//this.add(new LoginPanel(this));
		setVisible(true);
		//System.gc();
	}

	private void addListener() {
		addWindowListener(
				new WindowAdapter() //��Ӵ����¼�����//�����ڲ���
				{
					public void windowClosing(WindowEvent e) // ����ر�ʱ
						{
							int closeCode = JOptionPane.showConfirmDialog(
							MainFrame.this, "ȷ���˳���Ϸ��", "��ʾ",JOptionPane.YES_NO_OPTION);
							// ����ѡ��Ի��򣬲���¼�û�ѡ��
							if (closeCode == JOptionPane.YES_OPTION)// ����û�ѡ��ȷ��
							{
								System.exit(0);// �رճ���
							}	
						}
				});
	}
	/*WindowListener��ʹ��:https://blog.csdn.net/u010142437/article/details/8914348*/
	/*public void windowClosing(WindowEvent e){}  �������ڱ��ر�ʱ���õķ���*/
	/*����JOptionPane.showConfirmDialog()ȷ�Ͽ�Ĳ���:https://blog.csdn.net/d578332749/article/details/34169265*/

	/*
	  �����������е����
	  @param panel
	               ���������*/
	public void setPanel(JPanel panel) {
		Container c=getContentPane();// ��ȡ����������
		 
		/*instanceof �����������������ʱָ�������Ƿ����ض����һ��ʵ�����÷���ʵ������instanceof��
		instanceofͨ������һ������ֵ��ָ������������Ƿ�������ض�����������������һ��ʵ����*/
		if(panel instanceof LoginPanel) {
			panel.addKeyListener((KeyListener) panel);}

		c.removeAll();// ɾ���������������
		c.add(panel);// ����������
		c.validate();// ����������֤�������
		
	}

}
