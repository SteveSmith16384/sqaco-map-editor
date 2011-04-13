package ssmith.lib2d;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import ssmith.lang.GeometryFuncs;
import ssmith.lib2d.shapes.Line;
import ssmith.lib2d.shapes.RectShape;

public class Node extends Spatial {

	public Point coords = new Point(); // For storing the position of the node, to be added to its children
	public ArrayList<Spatial> children = new ArrayList<Spatial>();
	
	public Node(String name) {
		this(name, 0, 0);
	}
	
	
	public Node(String name, int x, int y) {
		super(name);

		coords.x = x;
		coords.y = y;
}
	
	
	public int getNumChildren() {
		return this.children.size();
	}
	
	
	public void add(Spatial s) {
		this.attachChild(s);
	}
	
	
	public void attachChild(Spatial s) {
		if (s.getParent() != null) {
			throw new RuntimeException(s + " already has a parent!");
		}
		this.children.add(s);
		s.setParent(this);
	}


	public void updateGeometricState() {
		if (this.getParent() != null) {
			this.world_coords_offset.x = this.coords.x + this.getParent().world_coords_offset.x;
			this.world_coords_offset.y = this.coords.y + this.getParent().world_coords_offset.y;
		} else {
			this.world_coords_offset.x = this.coords.x;
			this.world_coords_offset.y = this.coords.y;
		}
		
		// Set our bounds to zero
		world_bounds.top = this.world_coords_offset.y;
		world_bounds.bottom = this.world_coords_offset.y;
		world_bounds.left = this.world_coords_offset.x;
		world_bounds.right = this.world_coords_offset.x;
		
		for (Spatial child : children) {
			child.updateGeometricState();
			
			// Extend our bounds if required
			world_bounds.top = Math.min(world_bounds.top, child.world_bounds.top);
			world_bounds.bottom = Math.max(world_bounds.bottom, child.world_bounds.bottom);
			world_bounds.left = Math.min(world_bounds.left, child.world_bounds.left);
			world_bounds.right = Math.max(world_bounds.right, child.world_bounds.right);
			
			//child.updateWorldCoords();
		}
	}


	@Override
	public void doDraw(Graphics g, Camera cam, long interpol) {
		//Log.d("tag", "Drawing node");
		if (this.visible) {
			for (Spatial child : children) {
				child.doDraw(g, cam, interpol); // todo - only draw children in the view
			}
		}
	}


	@Override
	public boolean intersects(Spatial s) {
		if (s instanceof Node || s instanceof RectShape) {
			return this.world_bounds.intersects(s.getWorldBounds());
		} else if (s instanceof Line) {
			Line l2 = (Line)s;
			return GeometryFuncs.DoLineAndRectCross(this.getWorldBounds().left, this.getWorldBounds().top, this.getWorldBounds().right, this.getWorldBounds().bottom, l2.start.x, l2.start.y, l2.end.x, l2.end.y);
		} else {
			throw new RuntimeException("todo");
		}
	}

}
