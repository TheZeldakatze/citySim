package de.victorswelt;

public abstract class Tool {
	CitySimGame game;
	TileWorld world;
	
	public Tool(CitySimGame game, TileWorld world) {
		super();
		this.game = game;
		this.world = world;
	}
	
	public abstract void clickCallback();
}
