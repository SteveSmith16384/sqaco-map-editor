package scs.squacomaped.gui;

import java.awt.Dimension;

import scs.squacomaped.MapEditorMain;

public class CommandIconsPanel extends AbstractIconPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Icon draw, erase, pick;
	
	public CommandIconsPanel(MapEditorMain m) {
		super();
		
		this.setPreferredSize(new Dimension(Icon.ICON_SIZE, Icon.ICON_SIZE));
		
		draw = new CommandIcon(m, CommandIcon.CMD_DRAW, CommandIcon.ICONS_DIR + "draw.png", m);
		this.add(draw);
		erase = new CommandIcon(m, CommandIcon.CMD_ERASE, CommandIcon.ICONS_DIR + "erase.png", m);
		this.add(erase);
		pick = new CommandIcon(m, CommandIcon.CMD_PICK, CommandIcon.ICONS_DIR + "pick.png", m);
		this.add(pick);
	}

}
