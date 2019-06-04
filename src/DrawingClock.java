import java.awt.*;

import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class DrawingClock {
	JFrame frame;
	clock panell, panel2, panel3, panel4;

	public static void main(String args[]) {
		DrawingClock dc = new DrawingClock();
		dc.go();
	}

	public void go() {
		frame = new JFrame("时钟");
		frame.getContentPane().setLayout(null);
		panell = new clock(160, 150, "北京时间", 0);// 线程1画北京时间时钟
		frame.getContentPane().add(panell);
		panel2 = new clock(400, 150, "格林威治时间", -7);// //线程2画北京时间时钟
		frame.getContentPane().add(panel2);
		panel3 = new clock(160, 450, "东京时间", 1);// 线程3画北京时间时钟
		frame.getContentPane().add(panel3);
		panel4 = new clock(400, 450, "夏威夷时间", -10);// 线程4画北京时间时钟
		frame.getContentPane().add(panel4);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 650);
		frame.setVisible(true);
	}
}

class clock extends JPanel implements Runnable {
	Thread thr1 = new Thread(this);
	String text;
	int z;
	Graphics graphics;
	Date currentDate = new Date(); // 获取当前日期信息
	String lastdate = currentDate.toString(); // 获取当前日期信息的字符串形式
	int xcenter, ycenter; // 时钟的中心坐标值
	int radius = 100;// 时钟的半径

	void drawCircle() {
		graphics.drawArc(xcenter - radius, ycenter - radius, radius * 2,
				radius * 2, 0, 360);
		for (int i = 1; i <= 12; i++)// 画表盘数字
		{
			int x = xcenter + (int) ((radius - 15) * Math.sin(i * Math.PI / 6));
			int y = ycenter - (int) ((radius - 15) * Math.cos(i * Math.PI / 6));
			String str = "" + i;
			graphics.drawString(str, x, y);
		}
	}

	public void paint(Graphics g) {
		graphics = g;
		g.clearRect(xcenter - radius, ycenter - radius, radius * 2, radius * 2);
		drawCircle();// 画表盘
		int xh, yh, xm, ym, xs, ys;
		// 定义变量用于存放当前的秒针、分针和时针的末端坐标值
		int s, m, h;
		currentDate = new Date(); // 获取当前日期信息
		String today = text + "："; // 获取当前日期信息的字符串形式
		Calendar nowcal = Calendar.getInstance();
		s = nowcal.get(Calendar.SECOND);// 获取当前的秒值
		m = nowcal.get(Calendar.MINUTE);// 获取当前的分钟值
		h = nowcal.get(Calendar.HOUR);// 获取当前的小时值
		h += z;
		nowcal.set(Calendar.HOUR, h);
		today += nowcal.getTime().toString();
		// 获取当前秒针、分针和时针的末端坐标值
		xs = (int) (Math.cos(s * Math.PI / 30 - Math.PI / 2) * (radius - 10) + xcenter);
		ys = (int) (Math.sin(s * Math.PI / 30 - Math.PI / 2) * (radius - 10) + ycenter);
		xm = (int) (Math.cos(m * Math.PI / 30 - Math.PI / 2) * (radius - 40) + xcenter);
		ym = (int) (Math.sin(m * Math.PI / 30 - Math.PI / 2) * (radius - 40) + ycenter);
		xh = (int) (Math.cos((h * 30 + m / 2) * Math.PI / 180 - Math.PI / 2)
				* (radius - 60) + xcenter);
		yh = (int) (Math.sin((h * 30 + m / 2) * Math.PI / 180 - Math.PI / 2)
				* (radius - 60) + ycenter);
		// 清除界面,显示日期信息,并画秒针、分针和时针
		g.clearRect(xcenter - 100, ycenter + radius + 2, 200, 30);
		g.drawString(today, xcenter - 100, ycenter + radius + 20);
		g.drawLine(xcenter, ycenter, xs, ys);
		g.drawLine(xcenter, ycenter, xm, ym);
		g.drawLine(xcenter, ycenter, xh, yh);
	}

	public void run() {
		Thread me = Thread.currentThread();
		while ((thr1 == me)) {// 若是本线程则休眠1000个单位后刷新
			try {
				Thread.currentThread().sleep(1000);// 当前线程休眠1秒
			} catch (InterruptedException e) {
			}
			repaint();// 重画时钟
		}
	}

	public clock(int x, int y, String s, int zone) {
		z = zone;
		text = s;
		xcenter = x;
		ycenter = y;
		setSize(600, 800);
		thr1.start();// 启动线程
	}
}