package scs.squacomaped.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JComponent;

import scs.squacomaped.MapData;
import scs.squacomaped.MapEditorMain;
import ssmith.lib2d.ICanvas;
import ssmith.lib2d.shapes.Line;
import ssmith.lib2d.shapes.RectShape;

public class MapWindow extends JComponent implements ICanvas, MouseListener, MouseMotionListener {

	private static final int DEF_SIZE = 1000;
	private static final int POINT_SIZE = 5;

	private static final long serialVersionUID = 1L;

	private MapEditorMain main;
	public int sq_size = 15; // Fit to grid
	public Line current_line;
	public int mouse_x, mouse_y;
	private Point mouse_point = new Point();

	public MapWindow(MapEditorMain m) {
		main = m;

		this.setPreferredSize();
		this.addMouseListener(this);
		this.addMouseMotionListener(this);

	}


	public void setPreferredSize() {
		//MapData map = main.getMapData();
		this.setSize(DEF_SIZE, DEF_SIZE);
		this.setPreferredSize(new Dimension(DEF_SIZE, DEF_SIZE));
		this.validate();


	}


	private Point getPoint(MouseEvent evt) {
		int x = evt.getX();
		int y = evt.getY();
		if (sq_size > 0) {
			x = x / sq_size;
			x = x * sq_size;
			y = y / sq_size;
			y = y * sq_size;
		}
		mouse_point.x = x;
		mouse_point.y = y;
		return mouse_point;
	}


	@Override
	public void mouseClicked(MouseEvent evt) {
		Point p = this.getPoint(evt);
		if (this.current_line == null) { // New line
			if (main.getSelectedIcon().single_click_scenery) {
				main.map_data.points.add(new RectShape(main.getSelectedIcon().cmd, p.x, p.y, p.x + POINT_SIZE, p.y + POINT_SIZE, Color.red));
				this.repaint();
			} else {
				this.current_line = new Line("CurrentLine", Color.red); // todo - colour
				this.current_line.start.x = p.x;
				this.current_line.start.y = p.y;
			}
		} else { // Finished!
			this.current_line.end.x = p.x;
			this.current_line.end.y = p.y;
			current_line.setName(main.getSelectedIcon().cmd);
			main.map_data.lines.add(current_line);
			this.repaint();
			current_line = null;
		}


	}


	@Override
	public void mouseDragged(MouseEvent evt) {
		//p("Dragging");
		try {
			// todo
		} catch (Exception ex) {
			MapEditorMain.HandleError(ex);
		}

	}


	@Override
	public void mouseMoved(MouseEvent evt) {
		try {
			if (this.current_line != null) {
				Point p = this.getPoint(evt);
				this.current_line.end.x = p.x;
				this.current_line.end.y = p.y;
				this.repaint();
			}
		} catch (Exception ex) {
			MapEditorMain.HandleError(ex);
		}
	}


	public void paint(Graphics g) {
		if (current_line != null) {
			current_line.doDraw(g, main.cam, 0l);
		}
		MapData map = main.getMapData();
		if (map != null) {
			for(Line l : map.lines) {
				l.doDraw(g, main.cam, 0l);
			}
		}
	}


	@Override
	public void mouseEntered(MouseEvent arg0) {
		// Do nothing

	}


	@Override
	public void mouseExited(MouseEvent arg0) {
		// Do nothing

	}


	@Override
	public void mousePressed(MouseEvent evt) {
		// Do nothing

	}


	@Override
	public void mouseReleased(MouseEvent evt) {
		// Do nothing

	}


	@Override
	public int getCanvasHeight() {
		return DEF_SIZE;
	}


	@Override
	public int getCanvasWidth() {
		return DEF_SIZE;
	}



}
