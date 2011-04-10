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
	public static final int CMD_ERASE = 1;
	public static final int CMD_WALL = 2;
	public static final int CMD_DOOR = 3;
	
	public static final int ICON_SIZE = 25;

	private static final long serialVersionUID = 1L;

	
	private int cmd;
	private Image img;
	private MapEditorMain main;
	
	public Icon(MapEditorMain m, int _cmd, String image_filename) {
		main = m;
		cmd = _cmd;
		img = MapEditorMain.GetImage(image_filename);
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
