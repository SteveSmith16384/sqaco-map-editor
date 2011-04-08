package ssmith.lib2d;

import ssmith.lang.GeometryFuncs;

public class MyRectF {
	
	public int left, top, right, bottom;
	
	public MyRectF() {
		this(0, 0, 0, 0);
	}
	
	public MyRectF(int sx, int sy, int ex, int ey) {
		left = sx;
		top = sy;
		right = ex;
		bottom = ey;
	}


	public void set(int sx, int sy, int ex, int ey) {
		left = sx;
		top = sy;
		right = ex;
		bottom = ey;
	}

	
	public void set(MyRectF r) {
		set(r.left, r.top, r.right, r.bottom);
	}

	
	public void offset(int x, int y) {
		left += x;
		top += y;
		right += x;
		bottom += y;
	}
	
	
	public boolean intersects(MyRectF r2) {
		return GeometryFuncs.RectangleIntersects(left, top, right, bottom, 
				r2.left, r2.top, r2.right, r2.bottom);
		
	}
}
