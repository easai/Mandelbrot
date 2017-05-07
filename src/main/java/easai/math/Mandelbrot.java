//Mandelbrot.java  -- Draws the Mandelbrot set.

//Copyright (c) 2013 - 2017 easai

//Author: easai
//Website: easai.web.fc2.com/homepage/javadev/Mandelbrot
//Createid: Thu Dec 19 05:17:55 2013
//Keywords: 

//Commentary:
//This application draws either the Mandelbrot set and/or the sets that are not.
//
//Adjust the amount of step for slower machines.
//
//Code:

package easai.math;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

/**
 * The <tt>Mandelbrot</tt> class draws the Mandelbrot set.
 * 
 * Adjust the amount of step for slower machines.
 * 
 * @author easai
 */
public class Mandelbrot extends JFrame implements MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Point p0 = new Point();
	Point p1 = new Point();

	double rx0 = -2.0;
	double rx1 = .75;
	double ry0 = -1.5;
	double ry1 = 1.5;
	double r;
	double step = .005;

	MandelbrotPanel panel = new MandelbrotPanel();

	Mandelbrot() {
		r = rx1 - rx0;
		getContentPane().add(panel);
		this.addMouseListener(this);
		p1.x = -1;

		setSize(500, 500);
		setTitle("Mandelbrot");
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
		p0.x = e.getX();
		p0.y = e.getY();
	}

	public void mouseReleased(MouseEvent e) {
		p1.x = e.getX();
		p1.y = e.getY();

		int w = getWidth();
		int h = getHeight();

		int x0 = w / 2;
		int y0 = h / 2;
		double scale = h / 4.0;

		if (0 <= p1.x) {
			double mx0 = p0.x;
			double mx1 = p1.x;
			if (mx1 < mx0) {
				mx0 = p1.x;
				mx1 = p0.x;
			}
			double my0 = p0.y;
			double my1 = p1.y;
			if (my0 < my1) {
				my0 = p1.y;
				my1 = p0.y;
			}
			// g.drawRect(p0.x,p0.y,p1.x-p0.x,p1.y-p0.y);
			rx0 = (double) (mx0 - x0) / scale;
			rx1 = (double) (mx1 - x0) / scale;
			ry0 = (double) (my1 - y0) / scale;
			ry1 = (double) (my0 - y0) / scale;

			scale = (double) w / (ry1 - ry0);
			step = .005 * (ry1 - ry0) / 2.75;
			if ((rx1 - rx0) < (ry0 - ry1)) {
				scale = (double) w / (rx1 - rx0);
				step /= r / (rx1 - rx0);
			}
			x0 = -(int) (rx0 * scale);
			y0 = -(int) (ry0 * scale);

			// System.out.println(String.format("[x: %f,%f] [y: %f,%f]",rx0,rx1,ry0,ry1));
		}

		panel.p0 = p0;
		panel.p1 = p1;

		MandelbrotPanel p = new MandelbrotPanel();
		p.rx0 = rx0;
		p.rx1 = rx1;
		p.ry0 = ry0;
		p.ry1 = ry1;
		p.step = step;
		p.x0 = x0;
		p.y0 = y0;
		p.scale = scale;

		JFrame f = new JFrame();
		f.getContentPane().add(p);
		f.setSize(500, 500);
		f.setTitle("Mandelbrot");
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	/**
	 * Creates an instance of <tt>Mandelbrot</tt> and add it to a
	 * <tt>JFrame</tt> class.
	 */
	public static void main(String args[]) {
		Mandelbrot mandelbrot = new Mandelbrot();
	}
}


