package scs.squacomaped.shapes;

import java.awt.Color;

import ssmith.lib2d.shapes.RectShape;

public class MyRectShape extends RectShape implements IHighlight {
	
	private Color n_col, h_col;
	
	public MyRectShape(String name, int sx, int sy, int ex, int ey, Color c) {
		super(name, sx, sy, ex, ey, c);
		
		n_col = c;
		h_col = Color.white;
	}

	@Override
	public void highlight(boolean b) {
		if (b) {
			super.col = h_col;
		} else {
			super.col = n_col;
		}
	}

}
