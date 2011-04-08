package ssmith.lib2d;

import java.awt.Point;

public class Camera extends MyRectF {
	
	public int zoom = 1;
	private Point tmp_point = new Point();
	private ICanvas thread;

	public Camera(ICanvas t) {
		thread = t;
	}
	
	
	public void moveCam(float offx, float offy) {
		this.top += offy; 
		this.bottom += offy;
		this.left += offx;
		this.right += offx;
	}
	
	
	public void lookAt(int x, int y) {
		tmp_point.x = x;
		tmp_point.y = y;
		lookAt(tmp_point);
	}
	
	
	public void lookAt(Point p) {
		this.top = p.y - ((thread.getCanvasHeight()/2) / this.zoom);
		this.left = p.x - ((thread.getCanvasWidth()/2) / this.zoom);
		this.bottom = p.y + ((thread.getCanvasHeight()/2) / this.zoom);
		this.right = p.x + ((thread.getCanvasWidth()/2) / this.zoom);

	}
}
