package scs.squacomaped;

import java.awt.Color;

import scs.squacomaped.gui.Icon;

public class Static {
	
	public static Color GetColorForType(String type) {
		if (type.equalsIgnoreCase(Icon.CMD_WALL)) {
			return Color.blue;
		} else if (type.equalsIgnoreCase(Icon.CMD_DOOR)) {
			return Color.magenta;
		} else if (type.equalsIgnoreCase(Icon.CMD_HEDGE)) {
			return Color.green.darker();
		} else if (type.equalsIgnoreCase(Icon.CMD_DEPLOY1)) {
			return Color.green;
		} else if (type.equalsIgnoreCase(Icon.CMD_DEPLOY2)) {
			return Color.red;
		} else {
			throw new RuntimeException("Unknown type: " + type);
		}
	}
}
