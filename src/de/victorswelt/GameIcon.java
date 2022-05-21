package de.victorswelt;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.ImageIcon;

public class GameIcon {
	public static ImageIcon build, bulldoze, water, inspect;
	
	public static Cursor buildCursor, bulldozeCursor, inspectCursor;
	
	static {
		build    = new ImageIcon(GameIcon.class.getResource("/build.png"));
		bulldoze = new ImageIcon(GameIcon.class.getResource("/bulldoze.png"));
		water    = new ImageIcon(GameIcon.class.getResource("/water.png"));
		inspect  = new ImageIcon(GameIcon.class.getResource("/inspect.png"));
		
		buildCursor    = Toolkit.getDefaultToolkit().createCustomCursor(build.getImage(), new Point(0, 0), "build cursor");
		bulldozeCursor = Toolkit.getDefaultToolkit().createCustomCursor(bulldoze.getImage(), new Point(0, 0), "bulldoze cursor");
		inspectCursor  = Toolkit.getDefaultToolkit().createCustomCursor(inspect.getImage(), new Point(0, 0), "inspect cursor");
		
	}
}
