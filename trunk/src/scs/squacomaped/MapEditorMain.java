package scs.squacomaped;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import scs.squacomaped.gui.Icon;
import scs.squacomaped.gui.IconPanelsPanel;
import scs.squacomaped.gui.MapWindow;
import scs.squacomaped.gui.MyMenuBar;
import scs.squacomaped.gui.SelectLayersPanel;
import ssmith.lib2d.Camera;

public class MapEditorMain extends JFrame implements ActionListener, WindowListener {

	public static final boolean DEBUG = false;
	public static final float VERSION = 0.1f;
	public static final String MAP_ED_ICONS = "data/icons/";
	public static final String TITLE = "SquCo Map Editor";

	private static final long serialVersionUID = 1L;

	public MapData map_data = new MapData();
	private MapWindow map_window;
	private static Hashtable<String, Image> img_cache = new Hashtable<String, Image>();
	private Icon selected_icon = null;
	private IconPanelsPanel icons_panel;
	public SelectLayersPanel layers_panel;
	private String current_filename = "";
	private JScrollPane scroll;
	private boolean data_changed = false;
	public Camera cam;

	public MapEditorMain() {
		try {
			icons_panel = new IconPanelsPanel(this);
			layers_panel = new SelectLayersPanel();

			this.updateTitle();

			this.getContentPane().setLayout(new BorderLayout());

			this.getContentPane().add(layers_panel, BorderLayout.EAST);
			// this.getContentPane().add(new CommandIconsPanel(this),
			// BorderLayout.WEST);
			this.getContentPane().add(new MyMenuBar(this), BorderLayout.NORTH);
			this.getContentPane().add(icons_panel, BorderLayout.SOUTH);

			map_window = new MapWindow(this);
			scroll = new JScrollPane(map_window);
			scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			this.getContentPane().add(scroll, BorderLayout.CENTER);

			this.setSize(400, 400);
			cam = new Camera(map_window);
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
		this.map_data = MapEditorImportExport.Import(filename); // todo - put map data into sub-nodes?
		updateTitle();
		this.map_window.repaint();
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
		JOptionPane.showMessageDialog(null, t.toString(), "Error", JOptionPane.ERROR_MESSAGE);
	}

	/*
	 * @Override public void mouseClicked(MouseEvent evt) { try { if
	 * (evt.getComponent() instanceof Icon) { //p("Wall icon selected."); Icon
	 * ti = (Icon)evt.getComponent(); this.selected_icon = ti;
	 * this.icons_panel.repaint(); } else if (evt.getComponent() instanceof
	 * JToggleButton) { map_window.repaint(); } else if (evt.getComponent() ==
	 * map_window) { mapWindowClicked(evt); } } catch (Exception ex) {
	 * ex.printStackTrace(); } }
	 */

	/*
	 * private void mapWindowClicked(MouseEvent evt) { //data_changed = true;
	 * //p("Square " + x + "," + y + " selected.");
	 * this.map_window.clicked(evt); }
	 */

	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			String cmd = arg0.getActionCommand();
			if (cmd.equalsIgnoreCase(MyMenuBar.CMD_NEW)) {
				if (checkForChanges() == false) {
					return;
				}
				this.map_data = new MapData(); // todo - ask for mission name, num sides etc...
				this.map_window.repaint();
			} else if (cmd.equalsIgnoreCase(MyMenuBar.CMD_LOAD)) {
				if (checkForChanges() == false) {
					return;
				}
				JFileChooser fc = new JFileChooser(new File(current_filename).getAbsolutePath());
				int ret = fc.showOpenDialog(this);
				if (ret == JFileChooser.APPROVE_OPTION) {
					this.loadMap(fc.getSelectedFile().toString());
				}
			} else if (cmd.equalsIgnoreCase(MyMenuBar.CMD_SAVE)) {
				if (current_filename.length() > 0) {
				if (this.map_data != null) {
					MapEditorImportExport.Export(this.map_data, current_filename);
					data_changed = false;
					JOptionPane.showMessageDialog(this, "Map file saved", "Saved", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(this, "No map to save!", "NOT Saved!", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(this, "Please select Save As...", "NOT Saved!", JOptionPane.ERROR_MESSAGE);
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
				// Nothing yet
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
			if (JOptionPane.showConfirmDialog(null,
					"Map not saved.  Continue?", "Closing",
					JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION) {
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

	public void setSelectedIcon(Icon ic) {
		this.selected_icon = ic;
		this.icons_panel.repaint();
	}

	public Icon getSelectedIcon() {
		return selected_icon;

	}
	

}
