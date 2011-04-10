package scs.squacomaped.gui;

import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.JToggleButton;

public class SelectLayersPanel extends JPanel implements MouseListener {
	
	private static final long serialVersionUID = 1L;
	
	public JToggleButton walls, scenery, other;
	
	public SelectLayersPanel() {
		this.setLayout(new GridLayout(5, 1));
		
		walls = new JToggleButton("Walls");
		walls.setSelected(true);
		walls.addMouseListener(this);
		this.add(walls);
		
		scenery = new JToggleButton("Scenery");
		scenery.setSelected(true);
		scenery.addMouseListener(this);
		this.add(scenery);
		
		other = new JToggleButton("Other");
		other.setSelected(true);
		other.addMouseListener(this);
		this.add(other);
	}

	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
