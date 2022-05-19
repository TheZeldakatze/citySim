package de.victorswelt;

import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;

import javax.swing.JDesktopPane;

public class CitySimGame extends JDesktopPane {
	private static final long serialVersionUID = 1L;
	
	CitySim citySim;
	TileWorld tileWorld;
	
	ArrayList<Building> buildingList;
	
	public CitySimGame(CitySim cs, int width, int height) {
		citySim = cs;
		tileWorld = new TileWorld(width, height);
		
		add(tileWorld);
		
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				tileWorld.setSize(getWidth(), getHeight());
			}
		});
	}
	
	public ArrayList<Building> getBuildings() {
		return buildingList;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
	
	public void destroy() {
		
	}
}
