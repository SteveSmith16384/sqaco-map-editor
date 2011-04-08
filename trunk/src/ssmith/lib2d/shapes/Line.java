package ssmith.lib2d.shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import ssmith.lang.GeometryFuncs;
import ssmith.lib2d.Camera;
import ssmith.lib2d.Node;
import ssmith.lib2d.Spatial;

public class Line extends Geometry {
	
	//private static final float MIN_SIZE = 0.01f;
	
	public Point start = new Point();
	public Point end = new Point();
	
	public Line(String name, Color c) {
		this(name, 0, 0, 0, 0, c);
	}
	
	
	public Line(String name, int sx, int sy, int ex, int ey, Color c) {
		super(name, c);
		this.setByXYXY(sx, sy, ex, ey);
		this.updateGeometricState();
	}
	
	
	public void setByXYXY(int sx, int sy, int ex, int ey) {
		this.start.x = sx;
		this.start.y = sy;
		this.end.x = ex;
		this.end.y = ey;
	}
	
	
	public void setByXYAngLen(int x, int y, int ang, int len) {
		this.start.x = x;
		this.start.y = y;
		
		end = GeometryFuncs.GetPointFromAngle(ang, len);
	}

	
	public void updateGeometricState() {
		if (this.parent != null) {
			this.world_coords_offset.x = this.getParent().world_coords_offset.x;
			this.world_coords_offset.y = this.getParent().world_coords_offset.y;
		} else {
			this.world_coords_offset.x = 0;
			this.world_coords_offset.y = 0;
		}

		world_bounds.top = Math.min(start.y, end.y) + world_coords_offset.y;
		world_bounds.bottom = Math.max(start.y, end.y) + world_coords_offset.y;
			
		world_bounds.left = Math.min(start.x, end.x) + world_coords_offset.x;
		world_bounds.right = Math.max(start.x, end.x) + world_coords_offset.x;

		// Ensure not empty
		if (this.world_bounds.right <= this.world_bounds.left) {
			this.world_bounds.right = this.world_bounds.left + 1;
		}
		if (this.world_bounds.bottom <= this.world_bounds.top) {
			this.world_bounds.bottom = this.world_bounds.top + 1;
		}
	}

	@Override
	public void doDraw(Graphics g, Camera cam, long interpol) {
		super.informRenderListeners(interpol);
		if (this.visible) {
			g.drawLine((int)(start.x - cam.left + world_coords_offset.x), (int)(start.y - cam.top + world_coords_offset.y), (int)(end.x - cam.left + world_coords_offset.x), (int)(end.y - cam.top + world_coords_offset.y));
		}		
	}


	@Override
	public boolean intersects(Spatial s) {
		if (s instanceof Node || s instanceof RectShape || s instanceof Circle) {
			//return GeometryFuncs.DoLineAndRectCross(start.x, start.y, end.x, end.y, s.getWorldBounds().left, s.getWorldBounds().top, s.getWorldBounds().right, s.getWorldBounds().bottom);
			return GeometryFuncs.DoLineAndRectCross(this.getWorldBounds().left, this.getWorldBounds().top, this.getWorldBounds().right, this.getWorldBounds().bottom, s.getWorldBounds().left, s.getWorldBounds().top, s.getWorldBounds().right, s.getWorldBounds().bottom);
		} else if (s instanceof Line) {
			Line l2 = (Line)s;
			return GeometryFuncs.GetLineIntersection(start.x, start.y, end.x, end.y, l2.start.x, l2.start.y, l2.end.x, l2.end.y) != null;
		} else {
			throw new RuntimeException("todo");
		}
	}

}
