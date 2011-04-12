package scs.squacomaped.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;

import scs.squacomaped.MapEditorMain;

public abstract class Icon extends JComponent implements MouseListener {
	
	// Commands
	public static final String CMD_ERASE = "Erase";
	
	// These are also used in the file format SO DON'T CHANGE!!
	public static final String CMD_WALL = "Wall";
	public static final String CMD_DOOR = "Door";
	public static final String CMD_HEDGE = "Hedge";
	public static final String CMD_DEPLOY1 = "Deploy1";
	public static final String CMD_DEPLOY2 = "Deploy2";
	public static final String CMD_DEPLOY3 = "Deploy3";
	public static final String CMD_DEPLOY4 = "Deploy4";
	public static final String CMD_ESCAPE1 = "Escape1";
	public static final String CMD_ESCAPE2 = "Escape2";
	public static final String CMD_ESCAPE3 = "Escape3";
	public static final String CMD_ESCAPE4 = "Escape4";
	
	public static final int ICON_SIZE = 25;

	private static final long serialVersionUID = 1L;
	
	public String cmd;
	private Image img;
	private MapEditorMain main;
	public boolean single_click_scenery;
	
	public Icon(MapEditorMain m, String _cmd, String image_filename, boolean _single_click_scenery) {
		main = m;
		cmd = _cmd;
		img = MapEditorMain.GetImage(image_filename);
		single_click_scenery = _single_click_scenery;
		
		this.setSize(ICON_SIZE, ICON_SIZE);
		this.setPreferredSize(new Dimension(ICON_SIZE, ICON_SIZE));
		this.addMouseListener(this);
		
	}

	
	public void paint(Graphics g) {
		g.drawImage(img, 0, 0, ICON_SIZE, ICON_SIZE, this);
		if (main.getSelectedIcon() == this) {
			g.setColor(Color.yellow);
			g.drawRect(0, 0, ICON_SIZE-1, ICON_SIZE-1);
		}
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getComponent() instanceof Icon) {
			Icon ic = (Icon)e.getComponent();
			main.setSelectedIcon(ic);
		}
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
