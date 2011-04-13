package scs.squacomaped.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JComponent;

import scs.squacomaped.MapData;
import scs.squacomaped.MapEditorMain;
import scs.squacomaped.Static;
import scs.squacomaped.shapes.IHighlight;
import scs.squacomaped.shapes.MyLine;
import scs.squacomaped.shapes.MyRectShape;
import ssmith.lib2d.ICanvas;
import ssmith.lib2d.Spatial;
import ssmith.lib2d.shapes.Geometry;
import ssmith.lib2d.shapes.Line;
import ssmith.lib2d.shapes.PointShape;
import ssmith.lib2d.shapes.RectShape;

public class MapWindow extends JComponent implements ICanvas, MouseListener, MouseMotionListener {

	private static final int DEF_SIZE = 1000;
	public static final int POINT_SIZE = 5;

	private static final long serialVersionUID = 1L;

	private MapEditorMain main;
	public int sq_size = 15; // Fit to grid
	public MyLine current_line;
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
		if (main.getSelectedIcon() instanceof SceneryIcon) {
			Point p = this.getPoint(evt);
			if (this.current_line == null) {
				if (main.getSelectedIcon().single_click_scenery) {
					main.map_data.root_node.add(new MyRectShape(main.getSelectedIcon().cmd, p.x, p.y, p.x + MapWindow.POINT_SIZE, p.y + MapWindow.POINT_SIZE, Color.red));
					this.repaint();
				} else { // New line
					this.current_line = new MyLine("CurrentLine", Color.red);
					this.current_line.start.x = p.x;
					this.current_line.start.y = p.y;
				}
			} else { // Finished line
				this.current_line.end.x = p.x;
				this.current_line.end.y = p.y;
				current_line.setName(main.getSelectedIcon().cmd);
				current_line.col = Static.GetColorForType(main.getSelectedIcon().cmd);
				main.map_data.root_node.add(current_line);
				this.repaint();
				current_line = null;
			}
		} else if (main.getSelectedIcon().cmd == Icon.CMD_ERASE) {		
			PointShape ps = new PointShape("ErasePoint", evt.getX(), evt.getY(), Color.black);
			ArrayList<Geometry> colls = ps.getColliders(main.map_data.root_node);
			for (Geometry c : colls) {
				c.removeFromParent();
			}
		}

	}


	@Override
	public void mouseDragged(MouseEvent evt) {
		//p("Dragging");
		try {
		} catch (Exception ex) {
			MapEditorMain.HandleError(ex);
		}

	}


	@Override
	public void mouseMoved(MouseEvent evt) {
		try {
			if (main.getSelectedIcon() != null) {
				if (main.getSelectedIcon().cmd == Icon.CMD_ERASE) {
					// todo - this better
					PointShape ps = new PointShape("ErasePoint", evt.getX(), evt.getY(), Color.black);
					ArrayList<Geometry> colls = ps.getColliders(main.map_data.root_node);
					for (Geometry c : colls) {
						//if (c instanceof IHighlight) {
						IHighlight ih = (IHighlight)c; // Should not error, all must implement IHighlight
						ih.highlight(true);
						//}
					}
					if (colls.size() > 0) {
						this.invalidate();
						this.repaint();
					}
				} else {
					if (this.current_line != null) {
						Point p = this.getPoint(evt);
						this.current_line.end.x = p.x;
						this.current_line.end.y = p.y;
						this.repaint();
					}
				}
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
			for(Spatial l : map.root_node.children) {
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
