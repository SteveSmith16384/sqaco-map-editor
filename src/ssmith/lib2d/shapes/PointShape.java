package ssmith.lib2d.shapes;

import java.awt.Color;


public class PointShape extends RectShape {
	
	public PointShape(String name, int x, int y, Color c) {
		super(name, x, y, x, y, c);
	}

	
	public void updateCoordsXY(int x, int y) {
		super.updateCoordsXYWH(x, y, 1, 1);
	}
}
