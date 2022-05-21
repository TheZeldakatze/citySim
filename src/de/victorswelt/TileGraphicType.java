package de.victorswelt;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.management.timer.TimerNotification;

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
	private static BufferedImage tilemap, tilemapRed, tilemapBlue;
	
	public static void init() throws IOException {
		tilemap = ImageIO.read(TileGraphicType.class.getResourceAsStream("/tilemap.png"));
		tilemapRed = tintImage(tilemap, new Color(255,0,0,0));
		tilemapBlue = tintImage(tilemap, new Color(0,255,0,0));
		
	}
	
	private static BufferedImage tintImage(BufferedImage src, Color tint) {
		BufferedImage dst = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TRANSLUCENT);
		Graphics2D g = dst.createGraphics();
		g.setXORMode(tint);
		g.drawImage(src, 0, 0, null);
		g.dispose();
		
		return dst;
	}
	
	
	// the per tile stuff
	int tilemapX, tilemapY;
	private TileGraphicType(int x, int y) {
		tilemapX = x;
		tilemapY = y;
	}
	
	public void draw(Graphics2D g, int x, int y, int scaledWidth, int scaledHeight) {
		drawFromTilemap(tilemap, g, x, y, scaledWidth, scaledHeight);
	}
	
	public void drawRed(Graphics2D g, int x, int y, int scaledWidth, int scaledHeight) {
		drawFromTilemap(tilemapRed, g, x, y, scaledWidth, scaledHeight);
	}
	
	public void drawBlue(Graphics2D g, int x, int y, int scaledWidth, int scaledHeight) {
		drawFromTilemap(tilemapBlue, g, x, y, scaledWidth, scaledHeight);
	}
	
	private void drawFromTilemap(BufferedImage tilemap, Graphics2D g, int x, int y, int scaledWidth, int scaledHeight) {
		int sx = tilemapX*TILE_WIDTH, sy = tilemapY*TILE_HEIGHT;
		g.drawImage(tilemap, x, y, x+scaledWidth, y+scaledHeight, sx, sy, sx+TILE_WIDTH, sy+TILE_HEIGHT, null);
	}
}
