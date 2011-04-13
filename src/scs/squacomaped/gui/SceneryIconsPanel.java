package scs.squacomaped.gui;

import scs.squacomaped.MapEditorMain;

public class SceneryIconsPanel extends AbstractIconPanel {

	private static final long serialVersionUID = 1L;

	public SceneryIconsPanel(MapEditorMain main) {
		super();
		
		SceneryIcon ti = new SceneryIcon(main, Icon.CMD_WALL, MapEditorMain.MAP_ED_ICONS + "bricks.png", false);
		this.add(ti);
		ti = new SceneryIcon(main, Icon.CMD_DOOR, MapEditorMain.MAP_ED_ICONS + "door_ns.png", false);
		this.add(ti);
		ti = new SceneryIcon(main, Icon.CMD_HEDGE, MapEditorMain.MAP_ED_ICONS + "hedge.png", false);
		this.add(ti);
		ti = new SceneryIcon(main, Icon.CMD_DEPLOY1, MapEditorMain.MAP_ED_ICONS + "deploy1.png", true);
		this.add(ti);
		ti = new SceneryIcon(main, Icon.CMD_DEPLOY2, MapEditorMain.MAP_ED_ICONS + "deploy2.png", true);
		this.add(ti);
		/*ti = new SceneryIcon(main, Icon.CMD_DEPLOY3, MapEditorMain.MAP_ED_ICONS + "deploy3.png", true);
		this.add(ti);
		ti = new SceneryIcon(main, Icon.CMD_DEPLOY4, MapEditorMain.MAP_ED_ICONS + "deploy4.png", true);
		this.add(ti);*/
		ti = new SceneryIcon(main, Icon.CMD_ESCAPE1, MapEditorMain.MAP_ED_ICONS + "escape1.png", true);
		this.add(ti);
		ti = new SceneryIcon(main, Icon.CMD_ESCAPE2, MapEditorMain.MAP_ED_ICONS + "escape2.png", true);
		this.add(ti);
		/*ti = new SceneryIcon(main, Icon.CMD_ESCAPE3, MapEditorMain.MAP_ED_ICONS + "escape3.png", true);
		this.add(ti);
		ti = new SceneryIcon(main, Icon.CMD_ESCAPE4, MapEditorMain.MAP_ED_ICONS + "escape4.png", true);
		this.add(ti);*/
	}
}
