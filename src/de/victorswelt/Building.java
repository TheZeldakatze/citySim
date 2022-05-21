package de.victorswelt;

public abstract class Building {
	CitySimGame game;
	int x, y;
	BuildingBlueprintType blueprint;
	
	public Building(CitySimGame game, int x, int y, BuildingBlueprintType blueprint) {
		this.game = game;
		this.x = x;
		this.y = y;
		this.blueprint = blueprint;
		
		blueprint.buildAt(game.tileWorld, x, y);
	}
	
	public BuildingBlueprintType getBlueprint() {
		return blueprint;
	}
	
	public int getWidth() {
		return blueprint.getWidth();
	}
	
	public int getHeight() {
		return blueprint.getHeight();
	}
	
	public abstract void tick();
	
	public void clickAction() {
		
	}
}
