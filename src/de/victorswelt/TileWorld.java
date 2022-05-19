package de.victorswelt;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

public class TileWorld extends JPanel implements MouseListener, MouseMotionListener, MouseWheelListener {
	int width, height, camX, camY, selectedTileX, selectedTileY, oldMouseX, oldMouseY;
	float scale = 1f;
	TileGraphicType[][] world;
	
	public boolean needRedraw;
	
	private Color redXORmask = new Color(255, 0, 0),
			selectorBoxColor = new Color(100, 100, 10);
	
	public TileWorld(int width, int height) {
		super();
		this.width = width;
		this.height = height;
		
		world = new TileGraphicType[width][height];
		for(int x = 0; x<width; x++) {
			for(int y = 0; y<height; y++) {
				world[x][y] = TileGraphicType.GRASS_FULL;
			}
		}
		
		world[3][3] = TileGraphicType.HOUSE_SMALL_00;
		world[4][3] = TileGraphicType.HOUSE_SMALL_10;
		world[3][4] = TileGraphicType.HOUSE_SMALL_01;
		world[4][4] = TileGraphicType.HOUSE_SMALL_11;
		
		
		// add the listeners
		addMouseListener(this);
		addMouseMotionListener(this);
		addMouseWheelListener(this);
		
		// the redraw timer
		new Timer().scheduleAtFixedRate(new TimerTask() {
			
			@Override
			public void run() {
				repaint();
			}
		}, 0, 40);
	}
	
	@Override
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		
		Graphics2D g = (Graphics2D) graphics;
		
		// paint the scene
		int tileXoff = camX / TileGraphicType.TILE_WIDTH,
				tileYoff = camY / TileGraphicType.TILE_HEIGHT,
				subTileX = camX % TileGraphicType.TILE_WIDTH,
				subTileY = camY % TileGraphicType.TILE_HEIGHT;
		
		
		for(int x = -1; x<=getWidth()/TileGraphicType.TILE_WIDTH; x++) {
			for(int y = -1; y<=getHeight()/TileGraphicType.TILE_HEIGHT+1; y++) {
				getTileGraphicAt(x + tileXoff, y + tileYoff)
					.draw(g, x*TileGraphicType.TILE_WIDTH - subTileX, y*TileGraphicType.TILE_HEIGHT - subTileY);
			}
		}
		
		// draw the selection box
		g.setColor(selectorBoxColor);
		g.drawRect(selectedTileX*TileGraphicType.TILE_WIDTH - camX, 
				selectedTileY*TileGraphicType.TILE_HEIGHT - camY, 
				TileGraphicType.TILE_WIDTH, TileGraphicType.TILE_HEIGHT);
	}
	
	public TileGraphicType getTileGraphicAt(int x, int y) {
		if(x<0 || y<0 || x>=width || y>=height)
			return TileGraphicType.VOID;
		return world[x][y];
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if((e.getModifiersEx() & InputEvent.BUTTON3_DOWN_MASK) != 0) {
			camX += oldMouseX-e.getX();
			camY += oldMouseY-e.getY();
			oldMouseX = e.getX();
			oldMouseY = e.getY();
		}
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
		// move the selection box
		selectedTileX = (e.getX() + camX) / TileGraphicType.TILE_WIDTH;
		selectedTileY = (e.getY() + camY) / TileGraphicType.TILE_HEIGHT;
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		oldMouseX = e.getX();
		oldMouseY = e.getY();
		System.out.println("pressed");
		if(e.getButton() == MouseEvent.BUTTON1)
			BuildingBlueprintType.SMALL_HOUSE.buildAt(this, selectedTileX, selectedTileY);
		if(e.getButton() == MouseEvent.BUTTON2)
			BuildingBlueprintType.FARM.buildAt(this, selectedTileX, selectedTileY);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		scale += e.getScrollAmount()*0.5;
		
	}
}
