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
		WallIcon ti = new WallIcon(main, (short)-1, MapEditorMain.MAP_ED_ICONS + "erase.png", main);
		this.add(ti);
	}
}
