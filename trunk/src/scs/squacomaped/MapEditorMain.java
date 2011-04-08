package scs.squacomaped;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;

import scs.squacomaped.gui.Icon;
import scs.squacomaped.gui.IconPanelsPanel;
import scs.squacomaped.gui.MapWindow;
import scs.squacomaped.gui.MyMenuBar;
import scs.squacomaped.gui.SelectLayersPanel;

public class MapEditorMain extends JFrame implements MouseListener, ActionListener, MouseMotionListener, WindowListener {

	public static final boolean DEBUG = false;
	public static final float VERSION = 0.1f;
	public static final String MAP_ED_ICONS = "map_editor/map_ed_icons/";
	public static final String TITLE = "SquCo Map Editor";

	private static final long serialVersionUID = 1L;

	private MapData map_data;
	private MapWindow map_window;
	private static Hashtable<String, Image> img_cache = new Hashtable<String, Image>();
	public Icon selected_icon = null;
	private IconPanelsPanel icons_panel = new IconPanelsPanel(this);
	public SelectLayersPanel layers_panel = new SelectLayersPanel(this);
	private String current_filename = "";
	private JScrollPane scroll;
	private boolean data_changed = false;

	public MapEditorMain() {
		try {
			this.updateTitle();

			this.getContentPane().setLayout(new BorderLayout());

			this.getContentPane().add(layers_panel, BorderLayout.EAST);
			//this.getContentPane().add(new CommandIconsPanel(this), BorderLayout.WEST);
			this.getContentPane().add(new MyMenuBar(this), BorderLayout.NORTH);
			this.getContentPane().add(icons_panel, BorderLayout.SOUTH);

			this.setSize(400, 400);
			this.setVisible(true);
			
			this.addWindowListener(this);
			
			this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}


	public MapData getMapData() {
		return this.map_data;
	}


	private void updateTitle() {
		this.setTitle(TITLE + " Map Editor: " + this.current_filename);
	}


	public void loadMap(String filename) throws IOException {
		current_filename = filename;
		this.map_data = MapEditorImportExport.Import(filename);
		if (scroll != null) {
			this.remove(scroll);
		}
		map_window = new MapWindow(this);
		scroll = new JScrollPane(map_window);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		this.getContentPane().add(scroll, BorderLayout.CENTER);
		this.validate();
		updateTitle();
		data_changed = false;
	}


	public static Image GetImage(String filename) {
		while (img_cache.containsKey(filename) == false) {
			if (new File(filename).canRead() == false) {
				throw new RuntimeException("Image " + filename + " not found.");
			}
			Image img = Toolkit.getDefaultToolkit().getImage(filename);
			img_cache.put(filename, img);
		}
		return img_cache.get(filename);
	}


	public static void HandleError(Throwable t) {
		t.printStackTrace();
		JOptionPane.showMessageDialog(null, t.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	}


	@Override
	public void mouseClicked(MouseEvent evt) {
		try {
			if (evt.getComponent() instanceof Icon) {
				//p("Wall icon selected.");
				Icon ti = (Icon)evt.getComponent();
				this.selected_icon = ti;
				this.icons_panel.repaint();
			} else if (evt.getComponent() instanceof JToggleButton) {
				map_window.repaint();
			} else if (evt.getComponent() == map_window) {
				mapWindowClicked(evt);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}


	private void mapWindowClicked(MouseEvent evt) {
		data_changed = true;
		
		/*int x = evt.getX() / MapWindow.SQ_SIZE;
		int y = evt.getY() / MapWindow.SQ_SIZE;
		//p("Square " + x + "," + y + " selected.");

/*		if (x < this.map_data.size && y < this.map_data.size) {
			ServerMapSquare sq = this.map_data.map[x][y]; 
			//if (this.current_mode == CommandIcon.CMD_DRAW) {
			if (selected_icon == null) {
				// Do nothing
			} else if (selected_icon instanceof WallIcon) {
				if (selected_icon.cmd > 0) {
					sq.major_type = MapDataTable.MT_WALL;
					sq.texture_code = this.selected_icon.cmd;
				} else { // Clear it
					sq.major_type = MapDataTable.MT_NOTHING;
					sq.texture_code = 0;
				}
			} else if (selected_icon instanceof FloorIcon) {
				if (selected_icon.cmd > 0) {
					sq.major_type = MapDataTable.MT_FLOOR;
					sq.texture_code = this.selected_icon.cmd; 
				} else { // Clear it
					sq.major_type = MapDataTable.MT_NOTHING;
					sq.texture_code = 0;
				}
			} else if (selected_icon instanceof RaisedFloorIcon) {
				if (selected_icon.cmd > 0) {
					//sq.major_type = MapDataTable.MT_FLOOR;
					sq.raised_texture_code = this.selected_icon.cmd; 
				} else { // Clear it
					//sq.major_type = MapDataTable.MT_NOTHING;
					sq.raised_texture_code = 0;
				}
			} else if (selected_icon instanceof MiscIcon) {
				if (selected_icon.cmd > 0) {
					MiscIcon si = (MiscIcon)selected_icon;
					if (si.cmd == MiscIcon.DOOR) {
						sq.door_type = si.side;
					} else if (si.cmd == MiscIcon.DEPLOY) {
						sq.deploy_sq_side = si.side;
					} else if (si.cmd == MiscIcon.OWNER) {
						sq.owner_side = si.side;
					} else if (si.cmd == MiscIcon.ESCAPE_HATCH) {
						sq.escape_hatch_side = si.side;
					} else if (si.cmd == MiscIcon.COMPUTER) {
						sq.major_type = MapDataTable.MT_COMPUTER;
						sq.owner_side = si.side;
					} else {
						throw new RuntimeException("Unknown MiscIcon cmd:" + selected_icon.cmd);
					}
				} else { // Clear it
					sq.owner_side = 0;
					sq.deploy_sq_side = 0;
					sq.door_type = 0;
					sq.escape_hatch_side = 0;
				}
			} else if (selected_icon instanceof SceneryIcon) {
				if (selected_icon.cmd > 0) {
					SceneryIcon si = (SceneryIcon)selected_icon;
					sq.scenery_code = si.cmd;
					sq.scenery_direction = si.direction; 
				} else { // Clear it
					sq.scenery_code = 0;
					sq.scenery_direction = 0;
				}
			} else {
				throw new RuntimeException("Unknown select icon: " + this.selected_icon);
			}
			//} 
			this.map_window.repaint();
		}*/
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
		try {
			if (evt.getComponent() == map_window) {
				mapWindowClicked(evt);
			}
		} catch (Exception ex) {
			MapEditorMain.HandleError(ex);
		}
	}


	@Override
	public void mouseReleased(MouseEvent evt) {
		try {
			if (evt.getComponent() == map_window) {
				mapWindowClicked(evt);
			}
		} catch (Exception ex) {
			MapEditorMain.HandleError(ex);
		}
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			String cmd = arg0.getActionCommand();
			if (cmd.equalsIgnoreCase(MyMenuBar.CMD_LOAD)) {
				if (checkForChanges() == false) {
					return;
				}
				JFileChooser fc = new JFileChooser(new File(current_filename).getAbsolutePath());
				int ret = fc.showOpenDialog(this);
				if (ret == JFileChooser.APPROVE_OPTION) {
					this.loadMap(fc.getSelectedFile().toString());
				}
			} else if (cmd.equalsIgnoreCase(MyMenuBar.CMD_SAVE)) {
				if (this.map_data != null) {
					MapEditorImportExport.Export(this.map_data, current_filename);
					data_changed = false;
					JOptionPane.showMessageDialog(this, "Map file saved", "Saved", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(this, "No map to save!", "NOT Saved!", JOptionPane.INFORMATION_MESSAGE);
				}
			} else if (cmd.equalsIgnoreCase(MyMenuBar.CMD_SAVE_AS)) {
				if (this.map_data != null) {
					JFileChooser fc = new JFileChooser(new File(current_filename).getAbsolutePath());
					int ret = fc.showOpenDialog(this);
					if (ret == JFileChooser.APPROVE_OPTION) {
						this.current_filename = fc.getSelectedFile().toString();
						MapEditorImportExport.Export(this.map_data, current_filename);
						data_changed = false;
						updateTitle();
						JOptionPane.showMessageDialog(this, "Map file saved", "Saved", JOptionPane.INFORMATION_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(this, "No map to save!", "NOT Saved!", JOptionPane.INFORMATION_MESSAGE);
				}
			} else if (cmd.equalsIgnoreCase(MyMenuBar.CMD_ABOUT)) {
				JOptionPane.showMessageDialog(this, "Stellar Forces Map Editor.  See http://www.stellarforces.com/.", "Help | About", JOptionPane.INFORMATION_MESSAGE);
			} else if (cmd.equalsIgnoreCase(MyMenuBar.CMD_ZOOM_SMALL)) {
				//MapWindow.SQ_SIZE = 20;
				map_window.setPreferredSize();
				this.repaint();
			} else if (cmd.equalsIgnoreCase(MyMenuBar.CMD_ZOOM_LARGE)) {
				//MapWindow.SQ_SIZE = 40;
				map_window.setPreferredSize();
				this.repaint();
			} else if (cmd.equalsIgnoreCase(MyMenuBar.CMD_CHECK_MAP)) {
				StringBuffer str = new StringBuffer();
				// todo?
				if (str.length() > 0) {
					JOptionPane.showMessageDialog(this, str.toString(), "Map Issues", JOptionPane.INFORMATION_MESSAGE);
				}
			} else {
				throw new RuntimeException("Unknown command: " + cmd);
			}
		} catch (Exception ex) {
			MapEditorMain.HandleError(ex);
		}
	}


	@Override
	public void mouseDragged(MouseEvent evt) {
		//p("Dragging");
		try {
			if (evt.getComponent() == map_window) {
				mapWindowClicked(evt);
			}
		} catch (Exception ex) {
			MapEditorMain.HandleError(ex);
		}
		
	}


	@Override
	public void mouseMoved(MouseEvent evt) {
		/*p("Moving");
		try {
			if (evt.getComponent() == map_window) {
				mapWindowClicked(evt);
			}
		} catch (Exception ex) {
			MapEditorMain.HandleError(ex);
		}
			*/	
	}


	@Override
	public void windowActivated(WindowEvent arg0) {
	}


	@Override
	public void windowClosed(WindowEvent arg0) {
	}


	@Override
	public void windowClosing(WindowEvent evt) {
		if (checkForChanges() == false) {
			return;
		}
		this.setVisible(false);
		System.exit(0);
	}

	
	private boolean checkForChanges() {
		if (this.data_changed) {
			if (JOptionPane.showConfirmDialog(null, "Map not saved.  Continue?", "Closing", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION) {
				return false;
			}
		}
		return true;
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
	}


	@Override
	public void windowDeiconified(WindowEvent arg0) {
	}


	@Override
	public void windowIconified(WindowEvent arg0) {
	}


	@Override
	public void windowOpened(WindowEvent arg0) {
	}

	
	public static void main(String args[]) {
		new MapEditorMain();
	}


	public static void p(Object o) {
		System.out.println(o);
	}



}
