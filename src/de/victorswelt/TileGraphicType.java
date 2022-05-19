package de.victorswelt;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public enum TileGraphicType {
	GRASS_FULL(0,0),
	
	VOID(0,1),
	
	HOUSE_SMALL_00(3,0),
	HOUSE_SMALL_10(4,0),
	HOUSE_SMALL_01(3,1),
	HOUSE_SMALL_11(4,1),
	
	FARM_00(5,0),
	FARM_01(6,0),
	FARM_10(5,1),
	FARM_11(6,1),
	
	;
	
	// static global stuff
	public static int TILE_WIDTH = 64;
	public static int TILE_HEIGHT = 64;
	private static BufferedImage tilemap;
	
	public static void init() throws IOException {
		tilemap = ImageIO.read(TileGraphicType.class.getResourceAsStream("/tilemap.png"));
	}
	
	
	// the per tile stuff
	int tilemapX, tilemapY;
	private TileGraphicType(int x, int y) {
		tilemapX = x;
		tilemapY = y;
	}
	
	public void draw(Graphics2D g, int x, int y) {
		int sx = tilemapX*TILE_WIDTH, sy = tilemapY*TILE_HEIGHT;
		g.drawImage(tilemap, x, y, x+TILE_WIDTH, y+TILE_HEIGHT, sx, sy, sx+TILE_WIDTH, sy+TILE_HEIGHT, null);
	}
	
	public void draw(Graphics2D g, int x, int y, Color xorColor) {
		g.setXORMode(xorColor);
		draw(g, x, y);
		g.setPaintMode();
	}
}
