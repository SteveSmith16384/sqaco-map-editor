package scs.squacomaped;

import java.util.ArrayList;

import ssmith.lib2d.Node;

public class MapData {
	
	//public ArrayList<Line> lines = new ArrayList<Line>();
	//public ArrayList<RectShape> points = new ArrayList<RectShape>();
	public Node root_node = new Node("root");
	public String mission_name;
	public ArrayList<String> comments = new ArrayList<String>();
	public int num_sides;
	public float version;
	
	
	public MapData() {
		super();
	}

}
