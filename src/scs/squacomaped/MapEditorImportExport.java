package scs.squacomaped;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.IOException;

import scs.squacomaped.gui.Icon;
import scs.squacomaped.gui.MapWindow;
import scs.squacomaped.shapes.MyLine;
import scs.squacomaped.shapes.MyRectShape;
import ssmith.io.IOFunctions;
import ssmith.io.TextFile;
import ssmith.lib2d.Spatial;
import ssmith.lib2d.shapes.Line;
import ssmith.lib2d.shapes.RectShape;

public class MapEditorImportExport {

	public static final String VERSION = "version";
	public static final String MISSION_NAME = "mission_name";
	public static final String NUM_SIDES = "num_sides";
	public static final String COMMENT = "comment";
	//public static final String WALL = "wall";
	//public static final String DOOR = "door";

	public static MapData Import(String filename) throws IOException {
		MapData map_data = new MapData();

		TextFile tf = new TextFile();
		tf.openFile(filename, TextFile.READ);
		String line[] = null;

		while (tf.isEOF() == false) { // Loop through each line of the file
			String tmp = tf.readLine(); 
			if (tmp.length() > 0) {
			line = tmp.replaceAll("\"", "").split(",");
				if (line[0].equalsIgnoreCase("version")) {
					map_data.version = Float.parseFloat(line[1]);
				} else if (line[0].equalsIgnoreCase(MISSION_NAME)) {
					map_data.mission_name = line[1];
				} else if (line[0].equalsIgnoreCase(NUM_SIDES)) {
					map_data.num_sides = Integer.parseInt(line[1]);
				} else if (line[0].equalsIgnoreCase(COMMENT)) {
					map_data.comments.add(line[1]);
				} else if (line[0].equalsIgnoreCase(Icon.CMD_WALL)) {
					int sx = Integer.parseInt(line[1]);
					int sy = Integer.parseInt(line[2]);
					int ex = Integer.parseInt(line[3]);
					int ey = Integer.parseInt(line[4]);
					map_data.root_node.add(new MyLine(line[0], sx, sy, ex, ey, Static.GetColorForType(line[0])));
				} else if (line[0].equalsIgnoreCase(Icon.CMD_DEPLOY1) || line[0].equalsIgnoreCase(Icon.CMD_DEPLOY2) || line[0].equalsIgnoreCase(Icon.CMD_DEPLOY3) || line[0].equalsIgnoreCase(Icon.CMD_DEPLOY4)) {
					int sx = Integer.parseInt(line[1]);
					int sy = Integer.parseInt(line[2]);
					map_data.root_node.add(new MyRectShape(line[0], sx, sy, sx + MapWindow.POINT_SIZE, sy + MapWindow.POINT_SIZE, Static.GetColorForType(line[0])));
				} else {
					throw new RuntimeException("Unknown type: '" + line[0] + "'");
				}
			}
		}
		tf.close();

		map_data.root_node.updateGeometricState();
		
		return map_data;
	}


	public static void Export(MapData map, String filename) throws FileNotFoundException, IOException {
		StringBuffer data = new StringBuffer();
		data.append(VERSION + "," + MapEditorMain.VERSION + "\n");
		data.append(MISSION_NAME + "," + map.mission_name + "\n");
		data.append(NUM_SIDES +"," + map.num_sides + "\n");

		/*for (Line l : map.lines) {
			data.append(l.getName() + "," + l.start.x + "," + l.start.y + "," + l.end.x + "," + l.end.y + "\n");
		}

		for (RectShape l : map.points) {
			data.append(l.getName() + "," + l.rect.left + "," + l.rect.top + "\n");
		}*/

		for (Spatial s : map.root_node.children) {
			if (s instanceof Line) {
				Line l = (Line)s;
				data.append(l.getName() + "," + l.start.x + "," + l.start.y + "," + l.end.x + "," + l.end.y + "\n");
			} else if (s instanceof RectShape) {
				RectShape l = (RectShape)s;
				data.append(l.getName() + "," + l.rect.left + "," + l.rect.top + "\n");
			} else {
				throw new RuntimeException("Unknown type: " + s);
			}
		}
		// Make backup first
		IOFunctions.CopyFile(filename, filename + "~", false);
		TextFile.QuickWrite(filename, data.toString(), true);

	}

}
