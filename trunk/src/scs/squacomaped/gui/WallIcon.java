package scs.squacomaped.gui;

import java.awt.event.MouseListener;

import scs.squacomaped.MapEditorMain;

public class WallIcon extends Icon {

	private static final long serialVersionUID = 1L;

	public WallIcon(MapEditorMain m, short cmd, String filename, MouseListener ml) {
		super(m, cmd, filename, ml);
	}

	/*public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.black);
		g.drawRect(0, 0, ICON_SIZE-1, ICON_SIZE-1);
		g.setColor(Color.white);
		g.drawRect(1, 1, ICON_SIZE-3, ICON_SIZE-3);
	}*/
	
}
