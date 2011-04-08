package scs.squacomaped.gui;

import java.awt.event.MouseListener;

import scs.squacomaped.MapEditorMain;

public class CommandIcon extends Icon {

	private static final long serialVersionUID = 1L;
	
	public static final byte CMD_DRAW = 1;
	public static final byte CMD_ERASE = 2;
	public static final byte CMD_PICK = 3;
	
	public static final String ICONS_DIR = "data/map_ed_icons/";

	public CommandIcon(MapEditorMain m, byte cmd, String filename, MouseListener ml) {
		super(m, cmd, filename, ml);
	}

}
