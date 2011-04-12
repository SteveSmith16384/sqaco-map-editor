package ssmith.lib2d;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import ssmith.lib2d.shapes.Geometry;

public abstract class Spatial {

	protected MyRectF world_bounds = new MyRectF(); // To decide if it should be drawn
	public Point world_coords_offset = new Point(); // The adjustment to local co-ords.  Basically, the parents coords
	public Node parent = null;
	protected String name;	
	public boolean visible = true;
	protected ArrayList<IRenderListener> render_listeners;
	public boolean collides = true;

	public Spatial(String _name) {
		super();

		name = _name;
		render_listeners = new ArrayList<IRenderListener>();
	}


	public void addRenderListener(IRenderListener l) {
		this.render_listeners.add(l);
	}


	public void informRenderListeners(long interpol) {
		for (IRenderListener l : this.render_listeners) {
			l.onRender(interpol);
		}
	}


	public abstract boolean intersects(Spatial s);


	public String toString() {
		return name;
	}
	
	
	public String getName() {
		return name;
	}


	public void setName(String s) {
		name = s;
	}
	
	
	public MyRectF getWorldBounds() {
		return world_bounds;
	}


	public void setParent(Node n) {
		this.parent = n;
	}


	public ArrayList<Geometry> getColliders(Node node) {
		return getColliders(node, new ArrayList<Geometry>());
	}


	/**
	 * Recurse through the node's children.
	 * 
	 */
	private ArrayList<Geometry> getColliders(Node node, ArrayList<Geometry> list_of_colliders) {
		if (node.collides) {
			if (this.intersects(node)) {
				for (Spatial child : node.children) {
					if (child != node) {
						if (child.collides) {
							if (this.intersects(child)) {
								if (child instanceof Node) {
									getColliders((Node)child, list_of_colliders);
								} else {
									list_of_colliders.add((Geometry)child);
								}
							}
						}
					}
				}
			}
		}
		return list_of_colliders;
	}


	public Node getParent() {
		return this.parent;
	}
	
	
	public void removeFromParent() {
		this.parent.children.remove(this);
	}


	public abstract void doDraw(Graphics g, Camera cam, long interpol);

	public abstract void updateGeometricState();

}
