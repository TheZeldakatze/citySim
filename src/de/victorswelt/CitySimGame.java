package de.victorswelt;

import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JDesktopPane;

public class CitySimGame extends JDesktopPane {
	private static final long serialVersionUID = 1L;
	private static final int GAME_TICK_DELAY = 20;
	
	CitySim citySim;
	TileWorld tileWorld;
	
	GameToolbar toolbar;
	
	ArrayList<Building> buildingList;
	
	Tool currentTool;
	
	Timer tickTimer;
	
	public CitySimGame(CitySim cs, int width, int height) {
		citySim = cs;
		
		tickTimer = new Timer();
		
		tileWorld = new TileWorld(this, width, height, tickTimer);
		toolbar = new GameToolbar(this);
		
		
		add(toolbar);
		add(tileWorld);
		
		toolbar.repaint();
		
		currentTool = new SelectorTool(this, tileWorld);
		
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				toolbar.setSize(toolbar.getPreferredSize());
				toolbar.setLocation(getWidth()/2 - toolbar.getWidth()/2, 0);
				tileWorld.setBounds(0, 0, getWidth(), getHeight());
			}
		});
		
		setCursor(GameIcon.inspectCursor);
		
		// start the game tick system
		tickTimer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				gameTick();
			}
		}, 0, GAME_TICK_DELAY);
	}
	
	private void gameTick() {
		
	}
	
	
	public ArrayList<Building> getBuildings() {
		return buildingList;
	}
	
	public void addBuilding(Building building) {
		buildingList.add(building);
	}
	
	public void setTool(Tool t) {
		currentTool = t;
	}
	
	public void mouseClickCallback(int tileX, int tileY) {
		currentTool.clickCallback();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
	
	public void destroy() {
		tickTimer.cancel();
	}
}
