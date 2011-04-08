package scs.squacomaped.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JPanel;

public abstract class AbstractIconPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;

	public AbstractIconPanel() {
		this.setLayout(new GridLayout(2, 0));
	}


	public void paint(Graphics g) {
		super.paint(g);
		
		g.setColor(Color.black);
		g.drawRect(0, 0, this.getWidth()-1, this.getHeight()-1);
	}
	
}
