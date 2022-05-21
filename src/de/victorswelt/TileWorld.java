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
	private static final long serialVersionUID = 1L;
	
	int width, height, camX, camY, selectedTileX, selectedTileY, oldMouseX, oldMouseY, scaledTileWidth, scaledTileHeight;
	float scale = 1f;
	TileGraphicType[][] world;
	BuildingBlueprintType currentBlueprint;
	
	CitySimGame game;
	
	public boolean needRedraw;
	
	private Color selectorBoxColor = new Color(100, 100, 10);
	
	public TileWorld(CitySimGame csg, int width, int height, Timer timer) {
		this.width = width;
		this.height = height;
		game = csg;
		scaledTileWidth = TileGraphicType.TILE_WIDTH;
		scaledTileHeight = TileGraphicType.TILE_HEIGHT;
		
		world = new TileGraphicType[width][height];
		for(int x = 0; x<width; x++) {
			for(int y = 0; y<height; y++) {
				world[x][y] = TileGraphicType.GRASS_FULL;
			}
		}
		
		setBackground(Color.RED);
		
		world[3][3] = TileGraphicType.HOUSE_SMALL_00;
		world[4][3] = TileGraphicType.HOUSE_SMALL_10;
		world[3][4] = TileGraphicType.HOUSE_SMALL_01;
		world[4][4] = TileGraphicType.HOUSE_SMALL_11;
		
		
		// add the listeners
		addMouseListener(this);
		addMouseMotionListener(this);
		addMouseWheelListener(this);
		
		// the redraw timer
		timer.scheduleAtFixedRate(new TimerTask() {
			
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
		int tileXoff = camX / scaledTileWidth,
				tileYoff = camY / scaledTileHeight,
				subTileX = camX % scaledTileWidth,
				subTileY = camY % scaledTileHeight;
		
		
		for(int x = -1; x<=getWidth()/scaledTileWidth+1; x++) {
			for(int y = -1; y<=getHeight()/scaledTileHeight+1; y++) {
				getTileGraphicAt(x + tileXoff, y + tileYoff)
					.draw(g, x*scaledTileWidth - subTileX, y*scaledTileHeight - subTileY, scaledTileWidth, scaledTileHeight);
			}
		}
		
		int selectPixelOffX = selectedTileX*scaledTileWidth - camX,
			selectPixelOffY = selectedTileY*scaledTileHeight - camY;
		
		if(currentBlueprint != null) {
			boolean canBeBuilt = currentBlueprint.canBuildAt(this, selectedTileX, selectedTileY);
			for(int x = 0; x < currentBlueprint.tiles[0].length; x++) {
				for(int y = 0; y < currentBlueprint.tiles.length; y++) {
					if(canBeBuilt)
						currentBlueprint.tiles[x][y]
								.drawRed(g, selectPixelOffX + x*scaledTileWidth, selectPixelOffY + y*scaledTileHeight, scaledTileWidth, scaledTileHeight);
					else
						currentBlueprint.tiles[x][y]
								.drawBlue(g, selectPixelOffX + x*scaledTileWidth, selectPixelOffY + y*scaledTileHeight, scaledTileWidth, scaledTileHeight);
					
				}
			}
		}
		
		// draw the selection box
		g.setColor(selectorBoxColor);
		g.drawRect(selectedTileX*scaledTileWidth - camX, 
				selectedTileY*scaledTileHeight - camY, 
				scaledTileWidth, scaledTileHeight);
	}
	
	public TileGraphicType getTileGraphicAt(int x, int y) {
		if(x<0 || y<0 || x>=width || y>=height)
			return TileGraphicType.VOID;
		return world[x][y];
	}
	
	

	public int getTileWidth() {
		return width;
	}

	public int getTileHeight() {
		return height;
	}

	public int getSelectedTileX() {
		return selectedTileX;
	}

	public int getSelectedTileY() {
		return selectedTileY;
	}

	public void setCurrentBlueprint(BuildingBlueprintType currentBlueprint) {
		this.currentBlueprint = currentBlueprint;
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
		selectedTileX = (e.getX() + camX) / scaledTileWidth;
		selectedTileY = (e.getY() + camY) / scaledTileHeight;
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		oldMouseX = e.getX();
		oldMouseY = e.getY();
		
		if(e.getButton() != MouseEvent.BUTTON3)
			game.mouseClickCallback(selectedTileX, selectedTileY);
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
		scale += e.getWheelRotation()*(scale*0.1);
		scale = Math.max(scale, 0.2f);
		
		scaledTileWidth = (int) Math.max(TileGraphicType.TILE_WIDTH*scale, 1);
		scaledTileHeight = (int) Math.max(TileGraphicType.TILE_HEIGHT*scale, 1);
	}
}
