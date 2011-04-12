package scs.squacomaped;

import java.awt.Color;

import scs.squacomaped.gui.Icon;

public class Static {

	
	public static Color GetColorForType(String type) {
		if (type.equalsIgnoreCase(Icon.CMD_WALL)) {
			return Color.blue;
		} else {
			return Color.black;
		}
	}
}
