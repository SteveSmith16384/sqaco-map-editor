package scs.squacomaped.gui;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JComponent;

import scs.squacomaped.MapData;
import scs.squacomaped.MapEditorMain;

public class MapWindow extends JComponent {
	
	private static final int DEF_SIZE = 1000;

	private static final long serialVersionUID = 1L;

	private MapEditorMain main;

	public MapWindow(MapEditorMain m) {
		main = m;

		this.setPreferredSize();
		this.addMouseListener(m);
		this.addMouseMotionListener(m);

	}
	
	
	public void setPreferredSize() {
		MapData map = main.getMapData();
		this.setSize(DEF_SIZE, DEF_SIZE);
		this.setPreferredSize(new Dimension(DEF_SIZE, DEF_SIZE));
		this.validate();

		
	}


	public void paint(Graphics g) {
		MapData map = main.getMapData();

		for(int i=0 ; i<map.lines.size() ; i++) {
		}
	}

}
