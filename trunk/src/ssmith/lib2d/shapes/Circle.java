package ssmith.lib2d.shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import ssmith.lib2d.Camera;
import ssmith.lib2d.Spatial;

public class Circle extends Geometry {

	protected Point centre = new Point();
	protected int radius;

	public Circle(String name, int x, int y, int rad, Color c) {
		super(name, c);

		centre.x = x;
		centre.y = y;
		radius = rad;

		this.updateGeometricState();
	}


	@Override
	public void doDraw(Graphics g, Camera cam, long interpol) {
		super.informRenderListeners(interpol);
		if (this.visible) {
			//todo g.drawOval((int)(this.centre.x - radius - cam.left + this.world_coords_offset.x), this.centre.y - radius - cam.top + this.world_coords_offset.y, radius*2, radius*2);
		}
	}


	@Override
	public void updateGeometricState() {
		if (this.parent != null) {
			this.world_coords_offset.x = this.getParent().world_coords_offset.x;
			this.world_coords_offset.y = this.getParent().world_coords_offset.y;
		} else {
			this.world_coords_offset.x = 0;
			this.world_coords_offset.y = 0;
		}

		world_bounds.top = centre.y + radius + this.getParent().world_coords_offset.y;
		world_bounds.bottom = centre.y - radius + this.getParent().world_coords_offset.y;
		world_bounds.left = centre.x - radius + this.getParent().world_coords_offset.x;
		world_bounds.right = centre.x + radius + this.getParent().world_coords_offset.x;
	}


	@Override
	public boolean intersects(Spatial s) {
		throw new RuntimeException("Todo");
	}

}
