package scs.squacomaped.gui;

import javax.swing.JTabbedPane;

import scs.squacomaped.MapEditorMain;

public class IconPanelsPanel extends JTabbedPane {
	
	private static final long serialVersionUID = 1L;

	public IconPanelsPanel(MapEditorMain m) {
		super();
		
		this.addTab("Walls", null, new WallIconsPanel(m), "Walls");
		
	}

}
