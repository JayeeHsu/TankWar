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
	* 用来serialization的key，
	* A和B相互之间传输信息，用seralize，但是相互之间把解包之后的文件进行了更改，如果程序中不加这个，相互之间再传输，
	* 会因为这个key不一样，而失败。所以，在程序中定义，会使软件版本兼容，无论怎么改，都可以相互序列化和反序列化。
	* Java中，如果class实现了序列化接口，你没有加这一行，eclipse会自动给warning，建议加上，否则，
	* JVM会自动编译生成一个序列号，
	* 这样传输会造成反序列化失败。因为不同的JVM之间的序列化算法是不一样的，不利于程序的移植。一般情况下，
	* 建议序列化的class都给一个序列化的ID，这样可以保证序列化的成功，版本的兼容性。
	*/

	// 构造方法：
	public MainFrame() {
		setTitle("TankWar");// 设置标题
		setSize(800, 600);// 设置窗口宽高
		setResizable(false);// 设置为不可调整大小
		Toolkit tool = Toolkit.getDefaultToolkit(); // 创建系统该默认组件工具包
		Dimension d = tool.getScreenSize(); // 获取屏幕尺寸，赋给一个二维坐标对象
		setLocation(300,100);// 让主窗体在屏幕中间显示
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		/*
		 * 设置用户在此窗体上发起 "close" 时默认执行的操作,DO_NOTHING_ON_CLOSE表示不执行任何操作； 要求程序在已注册的
		 * WindowListener 对象的 windowClosing 方法中处理该操作。
		 * 更多详见百度https://zhidao.baidu.com/question/440799321.html
		 */
		addListener();// 添加事件监听,定义在下方
		setPanel(new LoginPanel(this));// 添加登陆面板
		//this.add(new LoginPanel(this));
		setVisible(true);
		//System.gc();
	}

	private void addListener() {
		addWindowListener(
				new WindowAdapter() //添加窗体事件监听//匿名内部类
				{
					public void windowClosing(WindowEvent e) // 窗体关闭时
						{
							int closeCode = JOptionPane.showConfirmDialog(
							MainFrame.this, "确定退出游戏？", "提示",JOptionPane.YES_NO_OPTION);
							// 弹出选择对话框，并记录用户选择
							if (closeCode == JOptionPane.YES_OPTION)// 如果用户选择确定
							{
								System.exit(0);// 关闭程序
							}	
						}
				});
	}
	/*WindowListener的使用:https://blog.csdn.net/u010142437/article/details/8914348*/
	/*public void windowClosing(WindowEvent e){}  窗口正在被关闭时调用的方法*/
	/*关于JOptionPane.showConfirmDialog()确认框的操作:https://blog.csdn.net/d578332749/article/details/34169265*/

	/*
	  更换主容器中的面板
	  @param panel
	               更换的面板*/
	public void setPanel(JPanel panel) {
		Container c=getContentPane();// 获取主容器对象
		 
		/*instanceof 运算符是用来在运行时指出对象是否是特定类的一个实例。用法：实例对象instanceof类
		instanceof通过返回一个布尔值来指出，这个对象是否是这个特定类或者是它的子类的一个实例。*/
		if(panel instanceof LoginPanel) {
			panel.addKeyListener((KeyListener) panel);}

		c.removeAll();// 删除容器中所有组件
		c.add(panel);// 容器添加面板
		c.validate();// 容器重新验证所有组件
		
	}

}
