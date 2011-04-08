package scs.squacomaped.gui;

import java.awt.GridLayout;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.JToggleButton;

public class SelectLayersPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	public JToggleButton walls, floor, scenery, other, raised_floor;
	
	public SelectLayersPanel(MouseListener ml) {
		this.setLayout(new GridLayout(5, 1));
		
		walls = new JToggleButton("Walls");
		walls.setSelected(true);
		walls.addMouseListener(ml);
		this.add(walls);
		
		floor = new JToggleButton("Floor");
		floor.setSelected(true);
		floor.addMouseListener(ml);
		this.add(floor);
		
		raised_floor = new JToggleButton("RaisedFloor");
		raised_floor.setSelected(true);
		raised_floor.addMouseListener(ml);
		this.add(raised_floor);
		
		scenery = new JToggleButton("Scenery");
		scenery.setSelected(true);
		scenery.addMouseListener(ml);
		this.add(scenery);
		
		other = new JToggleButton("Other");
		other.setSelected(true);
		other.addMouseListener(ml);
		this.add(other);
	}

}
