package com.github.easai.math.mandelbrot;

import java.awt.event.ActionListener;
import java.util.Hashtable;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.Box;
import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MandelbrotMenu {
	Locale locale = Locale.US;
	JMenuItem mi;
	JMenuBar mb = new JMenuBar();
	JMenu m[];
	Hashtable<JMenuItem, MENUITEM> comp = new Hashtable<>();
	static String menus[] = { "File", "Edit", "Help" };
	String menuitems[][] = { { "Quit" }, { "Copy" }, { "About" } };

	enum MENUITEM {
		nFileQuit, nEditCopy, nHelpAbout
	};

	MENUITEM mi_num[][] = { { MENUITEM.nFileQuit }, { MENUITEM.nEditCopy }, { MENUITEM.nHelpAbout } };

	public void setMenu(JFrame frame, ActionListener l, Locale locale) {
		this.locale = locale;
		setMenu(l);
		frame.setJMenuBar(mb);
	}

	public void setMenu(JApplet ap, ActionListener l, Locale locale) {
		this.locale = locale;
		setMenu(l);
		ap.setJMenuBar(mb);
	}

	public void setMenu(ActionListener l) {
		// setMnemonic(new MenuShortcut(KeyEvent.VK_A))
		m = new JMenu[menus.length];
		ResourceBundle menuStrings = null;
		ResourceBundle menuItemStrings = null;
		String menuTitle, shortcuts = "";
		if (locale != Locale.US) {
			menuStrings = ResourceBundle.getBundle("JavaMenuMenuMenu", locale);
			menuItemStrings = ResourceBundle.getBundle("JavaMenuMenuMenuItem", locale);
		}

		for (int i = 0; i < menus.length; i++) {
			if (locale == Locale.US) {
				menuTitle = menus[i];
			} else {
				menuTitle = menuStrings.getString(menus[i]);
			}
			m[i] = new JMenu(menuTitle);
			if (menuTitle != null && 0 < menuTitle.length()) {
				m[i].setMnemonic(menuTitle.charAt(0));
			}
			if (i != menus.length - 1) {
				mb.add(m[i]);
			}
			for (int j = 0; j < menuitems[i].length; j++) {
				String str = menuitems[i][j];
				if (locale != Locale.US) {
					str = menuItemStrings.getString(menuitems[i][j]);
				}

				if (str != null && 0 < str.length()) {

					int index = 0;
					char ch = str.charAt(index);
					int len = str.length();
					while (shortcuts.indexOf(ch) != -1 && ++index < len) {
						ch = str.charAt(index);
					}
					if (index < len) {
						mi = new JMenuItem(str, ch);
						shortcuts += ch;
					} else {
						mi = new JMenuItem(str, str.charAt(0));
					}

				} else {
					mi = new JMenuItem(str);
				}

				m[i].add(mi);

				comp.put(mi, mi_num[i][j]);
				mi.addActionListener(l);
				// if ( // disabled menuitems
				// mi_num[i][j] == nRun ||
				// ((ap != null)
				// && (mi_num[i][j] == nOpenURL
				// || mi_num[i][j] == nSave)))

				// mi.setEnabled (false);
				// else if (mi_num[i][j] == nQuit)
				// mi.setShortcut(new
				// MenuShortcut(KeyEvent.VK_Q|KeyEvent.CTRL_MASK));
			}
		}
		mb.add(Box.createHorizontalGlue());
		mb.add(m[menus.length - 1]);

	}
}
