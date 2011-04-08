package ssmith.lib2d.shapes;

import java.awt.Color;
import java.awt.Graphics;

import ssmith.lang.GeometryFuncs;
import ssmith.lib2d.Camera;
import ssmith.lib2d.MyRectF;
import ssmith.lib2d.Node;
import ssmith.lib2d.Spatial;

public class RectShape extends Geometry {

	public MyRectF rect = new MyRectF();
	private MyRectF temp_rect = new MyRectF();

	public RectShape(String name, int sx, int sy, int ex, int ey, Color c) {
		super(name, c);

		rect.left = sx;
		rect.top = sy;
		rect.right = ex;
		rect.bottom = ey;

		this.updateGeometricState();

	}

	public void updateGeometricState() {
		if (this.parent != null) {
			this.world_coords_offset.x = this.getParent().world_coords_offset.x;
			this.world_coords_offset.y = this.getParent().world_coords_offset.y;
		} else {
			this.world_coords_offset.x = 0;
			this.world_coords_offset.y = 0;
		}

		world_bounds.top = Math.min(rect.top, rect.bottom) + this.world_coords_offset.y;
		world_bounds.bottom = Math.max(rect.top, rect.bottom) + this.world_coords_offset.y;

		world_bounds.left = Math.min(rect.left, rect.right) + this.world_coords_offset.x;
		world_bounds.right = Math.max(rect.left, rect.right) + this.world_coords_offset.x;
	}


	public void updateCoordsXYWH(int x, int y, int w, int h) {
		rect.left = x;
		rect.top = y;
		rect.right = x+w;
		rect.bottom = y+h;

	}


	@Override
	public void doDraw(Graphics g, Camera cam, long interpol) {
		super.informRenderListeners(interpol);
		if (this.visible) {
			//Log.d("tag", "Drawing rect");
			temp_rect.set(rect);
			temp_rect.offset((int)-cam.left + this.world_coords_offset.x, (int)-cam.top + this.world_coords_offset.y);
			g.drawRect(temp_rect.left, temp_rect.top, temp_rect.right - temp_rect.left, temp_rect.bottom - temp_rect.top);
		}
	}


	@Override
	public boolean intersects(Spatial s) {
		if (s instanceof Node || s instanceof RectShape) {
			return this.rect.intersects(s.getWorldBounds());
		} else if (s instanceof Line) {
			Line l2 = (Line)s;
			return GeometryFuncs.DoLineAndRectCross(this.getWorldBounds().left, this.getWorldBounds().top, this.getWorldBounds().right, this.getWorldBounds().bottom, l2.start.x, l2.start.y, l2.end.x, l2.end.y);
		} else {
			throw new RuntimeException("todo");
		}
	}

}

