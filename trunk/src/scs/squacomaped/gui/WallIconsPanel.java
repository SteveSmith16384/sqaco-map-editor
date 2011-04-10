package scs.squacomaped.gui;

import scs.squacomaped.MapEditorMain;


public class WallIconsPanel extends AbstractIconPanel {

	private static final long serialVersionUID = 1L;

	public WallIconsPanel(MapEditorMain main) {
		super();
		
		// Texture Icons
		/*for (short i=1 ; i<=TextureStateCache.MAX_TEX_NUM ; i++) {
			WallIcon ti = new WallIcon(main, i, TextureStateCache.GetFilename(i), main);
			this.add(ti);
		}*/
		WallIcon ti = new WallIcon(main, Icon.CMD_ERASE, MapEditorMain.MAP_ED_ICONS + "erase.png");
		this.add(ti);
		ti = new WallIcon(main, Icon.CMD_WALL, MapEditorMain.MAP_ED_ICONS + "bricks.png");
		this.add(ti);
		ti = new WallIcon(main, Icon.CMD_DOOR, MapEditorMain.MAP_ED_ICONS + "door_ns.png");
		this.add(ti);
		// Todo- add the rest
	}
}
