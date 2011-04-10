package scs.squacomaped.gui;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import scs.squacomaped.MapEditorMain;

public class MyMenuBar extends JMenuBar {
	
	public static final String CMD_NEW = "New";
	public static final String CMD_LOAD = "Open";
	public static final String CMD_SAVE = "Save";
	public static final String CMD_SAVE_AS = "Save As";
	public static final String CMD_ABOUT = "About";
	public static final String CMD_ZOOM_SMALL = "Small";
	public static final String CMD_ZOOM_LARGE = "Large";
	public static final String CMD_CHECK_MAP = "Check Map";

	private static final long serialVersionUID = 1L;

	public MyMenuBar(MapEditorMain m) {
		// Create a menu
		JMenu menu_file = new JMenu("File");
		this.add(menu_file);

		// Create a menu item
		JMenuItem item = new JMenuItem(CMD_NEW);
		item.setActionCommand(CMD_NEW);
		item.addActionListener(m);
		menu_file.add(item);

		item = new JMenuItem(CMD_LOAD);
		item.setActionCommand(CMD_LOAD);
		item.addActionListener(m);
		menu_file.add(item);

		item = new JMenuItem(CMD_SAVE);
		item.setActionCommand(CMD_SAVE);
		item.addActionListener(m);
		menu_file.add(item);

		item = new JMenuItem(CMD_SAVE_AS);
		item.setActionCommand(CMD_SAVE_AS);
		item.addActionListener(m);
		menu_file.add(item);

		JMenu menu_zoom = new JMenu("Zoom");
		this.add(menu_zoom);

		item = new JMenuItem(CMD_ZOOM_SMALL);
		item.setActionCommand(CMD_ZOOM_SMALL);
		item.addActionListener(m);
		menu_zoom.add(item);

		item = new JMenuItem(CMD_ZOOM_LARGE);
		item.setActionCommand(CMD_ZOOM_LARGE);
		item.addActionListener(m);
		menu_zoom.add(item);

		JMenu menu_tools = new JMenu("Tools");
		this.add(menu_tools);

		item = new JMenuItem(CMD_CHECK_MAP);
		item.setActionCommand(CMD_CHECK_MAP);
		item.addActionListener(m);
		menu_tools.add(item);

		JMenu menu_help = new JMenu("Help");
		this.add(menu_help);

		item = new JMenuItem(CMD_ABOUT);
		item.setActionCommand(CMD_ABOUT);
		item.addActionListener(m);
		menu_help.add(item);
	}
	
}
