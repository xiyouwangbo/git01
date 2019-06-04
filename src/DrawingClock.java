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
		frame = new JFrame("ʱ��");
		frame.getContentPane().setLayout(null);
		panell = new clock(160, 150, "����ʱ��", 0);// �߳�1������ʱ��ʱ��
		frame.getContentPane().add(panell);
		panel2 = new clock(400, 150, "��������ʱ��", -7);// //�߳�2������ʱ��ʱ��
		frame.getContentPane().add(panel2);
		panel3 = new clock(160, 450, "����ʱ��", 1);// �߳�3������ʱ��ʱ��
		frame.getContentPane().add(panel3);
		panel4 = new clock(400, 450, "������ʱ��", -10);// �߳�4������ʱ��ʱ��
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
	Date currentDate = new Date(); // ��ȡ��ǰ������Ϣ
	String lastdate = currentDate.toString(); // ��ȡ��ǰ������Ϣ���ַ�����ʽ
	int xcenter, ycenter; // ʱ�ӵ���������ֵ
	int radius = 100;// ʱ�ӵİ뾶

	void drawCircle() {
		graphics.drawArc(xcenter - radius, ycenter - radius, radius * 2,
				radius * 2, 0, 360);
		for (int i = 1; i <= 12; i++)// ����������
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
		drawCircle();// ������
		int xh, yh, xm, ym, xs, ys;
		// ����������ڴ�ŵ�ǰ�����롢�����ʱ���ĩ������ֵ
		int s, m, h;
		currentDate = new Date(); // ��ȡ��ǰ������Ϣ
		String today = text + "��"; // ��ȡ��ǰ������Ϣ���ַ�����ʽ
		Calendar nowcal = Calendar.getInstance();
		s = nowcal.get(Calendar.SECOND);// ��ȡ��ǰ����ֵ
		m = nowcal.get(Calendar.MINUTE);// ��ȡ��ǰ�ķ���ֵ
		h = nowcal.get(Calendar.HOUR);// ��ȡ��ǰ��Сʱֵ
		h += z;
		nowcal.set(Calendar.HOUR, h);
		today += nowcal.getTime().toString();
		// ��ȡ��ǰ���롢�����ʱ���ĩ������ֵ
		xs = (int) (Math.cos(s * Math.PI / 30 - Math.PI / 2) * (radius - 10) + xcenter);
		ys = (int) (Math.sin(s * Math.PI / 30 - Math.PI / 2) * (radius - 10) + ycenter);
		xm = (int) (Math.cos(m * Math.PI / 30 - Math.PI / 2) * (radius - 40) + xcenter);
		ym = (int) (Math.sin(m * Math.PI / 30 - Math.PI / 2) * (radius - 40) + ycenter);
		xh = (int) (Math.cos((h * 30 + m / 2) * Math.PI / 180 - Math.PI / 2)
				* (radius - 60) + xcenter);
		yh = (int) (Math.sin((h * 30 + m / 2) * Math.PI / 180 - Math.PI / 2)
				* (radius - 60) + ycenter);
		// �������,��ʾ������Ϣ,�������롢�����ʱ��
		g.clearRect(xcenter - 100, ycenter + radius + 2, 200, 30);
		g.drawString(today, xcenter - 100, ycenter + radius + 20);
		g.drawLine(xcenter, ycenter, xs, ys);
		g.drawLine(xcenter, ycenter, xm, ym);
		g.drawLine(xcenter, ycenter, xh, yh);
	}

	public void run() {
		Thread me = Thread.currentThread();
		while ((thr1 == me)) {// ���Ǳ��߳�������1000����λ��ˢ��
			try {
				Thread.currentThread().sleep(1000);// ��ǰ�߳�����1��
			} catch (InterruptedException e) {
			}
			repaint();// �ػ�ʱ��
		}
	}

	public clock(int x, int y, String s, int zone) {
		z = zone;
		text = s;
		xcenter = x;
		ycenter = y;
		setSize(600, 800);
		thr1.start();// �����߳�
	}
}