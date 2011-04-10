package scs.squacomaped;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.IOException;

import ssmith.io.TextFile;
import ssmith.lib2d.shapes.Line;

public class MapEditorImportExport {
	
	public static final String VERSION = "version";
	public static final String MISSION_NAME = "mission_name";
	public static final String NUM_SIDES = "num_sides";
	public static final String COMMENT = "comment";
	public static final String WALL = "wall";
	//public static final String MISSION_NAME = "mission_name";

	public static MapData Import(String filename) throws IOException {
		MapData map_data = new MapData();

		TextFile tf = new TextFile();
		tf.openFile(filename, TextFile.READ);
		String line[] = null;

		while (tf.isEOF() == false) { // Loop through each line of the file
			line = tf.readLine().replaceAll("\"", "").split(",");
			if (line[0].equalsIgnoreCase("version")) {
				map_data.version = Float.parseFloat(line[1]);
			} else if (line[0].equalsIgnoreCase(MISSION_NAME)) {
				map_data.mission_name = line[1];
			} else if (line[0].equalsIgnoreCase(NUM_SIDES)) {
				map_data.num_sides = Integer.parseInt(line[1]);
			} else if (line[0].equalsIgnoreCase(COMMENT)) {
				map_data.comments.add(line[1]);
			} else if (line[0].equalsIgnoreCase(WALL)) {
				int type = Integer.parseInt(line[1]);
				int sx = Integer.parseInt(line[2]);
				int sy = Integer.parseInt(line[3]);
				int ex = Integer.parseInt(line[4]);
				int ey = Integer.parseInt(line[5]);
				map_data.lines.add(new Line(WALL, sx, sy, ex, ey, Color.blue));
			} else {
				throw new RuntimeException("Unknown type: " + line[0]);
			}
		}
		tf.close();

		return map_data;
	}


	public static void Export(MapData map, String filename) throws FileNotFoundException, IOException {
		StringBuffer data = new StringBuffer();
		data.append(VERSION + "," + MapEditorMain.VERSION + "\n");
		data.append(MISSION_NAME + "," + map.mission_name + "\n");
		data.append(NUM_SIDES +"," + map.num_sides + "\n");
		
		for (Line l : map.lines) {
			data.append(l.getName() + "," + l.start.x + "," + l.start.y + "," + l.end.x + "," + l.end.y + "\n");
		}


		TextFile.QuickWrite(filename, data.toString(), true);

	}

}
