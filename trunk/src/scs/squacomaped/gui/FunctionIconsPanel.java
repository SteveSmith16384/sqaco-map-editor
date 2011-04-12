package scs.squacomaped.gui;

import scs.squacomaped.MapEditorMain;

public class FunctionIconsPanel extends AbstractIconPanel {

	private static final long serialVersionUID = 1L;

	public FunctionIconsPanel(MapEditorMain main) {
		super();

		SceneryIcon ti = new SceneryIcon(main, Icon.CMD_ERASE, MapEditorMain.MAP_ED_ICONS + "erase.png", true);
		this.add(ti);
	}
	

}
