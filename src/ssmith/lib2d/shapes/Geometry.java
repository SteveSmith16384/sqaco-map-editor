package ssmith.lib2d.shapes;

import java.awt.Color;

import ssmith.lib2d.Spatial;

public abstract class Geometry extends Spatial {
	
	protected Color col;
	
	public Geometry(String name, Color c) {
		super(name);
		
		col = c;
	}
	
	
}
