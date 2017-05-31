//MandelbrotPanel.java  -- Draws the Mandelbrot set.

//Copyright (c) 2013 easai

//Author: easai
//Website: easai.web.fc2.com/homepage/javadev/Mandelbrot
//Created: Thu Dec 19 05:17:55 2013
//Keywords: 

//Commentary:
//This application draws either the Mandelbrot set and/or the sets that are not.
//
//Adjust the amount of step for slower machines.

//Code:

package com.github.easai.math.mandelbrot;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.github.easai.math.Complex;

/**
 * The <tt>Mandelbrot</tt> class draws the Mandelbrot set.
 * 
 * Adjust the amount of step for slower machines.
 * 
 * @author easai
 */
public class MandelbrotPanel extends JPanel {
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
	double step = .005;

	int x0 = -1;
	int y0 = -1;
	double scale = -1.0;

	/**
	 * Draws the Mandelbrot set.
	 */
	public void paint(Graphics g) {
		int w = getWidth();
		int h = getHeight();

		g.setColor(Color.white);
		g.fillRect(0, 0, w, h);
		g.setColor(Color.black);

		if (x0 == -1)
			x0 = w / 2;
		if (y0 == -1)
			y0 = h / 2;
		if (scale == -1.0)
			scale = h / 4.0;

		Complex z;
		for (double x = rx0; x <= rx1; x += step)
			for (double y = ry0; y <= ry1; y += step) {
				Complex c = new Complex(x, y);
				int maxiter = 100;
				Complex zz;
				boolean bounded = true;
				Color color = Color.black;
				int index = 0;
				z = new Complex(0, 0);
				int ele = 0xff;
				do {
					zz = f(z, c);
					if (2 < zz.abs()) {
						bounded = false;
					}
					z = zz;
				} while (bounded && ++index < maxiter);
				if (!bounded) {
					ele = 0xff - index * 10;
					if (ele < 0)
						ele = 0;
					int xx = (int) (x * scale + x0);
					int yy = (int) (y * scale + y0);
					color = new Color(0x40, 0x10, ele);
					g.setColor(color);
					g.drawLine(xx, yy, xx, yy);
				}
			}
	}

	/**
	 * Defines the function f=z*z+c.
	 */
	public static Complex f(Complex z, Complex c) {
		return z.multiply(z).add(c);
	}

	/**
	 * For debugging purposes only.
	 */
	public static void main(String args[]) {
		MandelbrotPanel mandelbrot = new MandelbrotPanel();

		JFrame f = new JFrame();
		f.getContentPane().add(mandelbrot);
		f.setSize(500, 500);
		f.setTitle("Mandelbrot");
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}

// MandelbrotPanel.java ends here
