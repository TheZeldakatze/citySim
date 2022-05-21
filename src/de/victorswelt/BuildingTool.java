package de.victorswelt;

import java.lang.reflect.InvocationTargetException;

public class BuildingTool extends Tool {
	private BuildingBlueprintType blueprint;
	Class<? extends Building> buildingClass;
	

	public BuildingTool(CitySimGame game, TileWorld world, BuildingBlueprintType blueprint, Class<? extends Building> buildingClass2) {
		super(game, world);
		this.blueprint = blueprint;
		this.buildingClass = buildingClass2;
		
		world.setCurrentBlueprint(blueprint);
		
		game.setCursor(GameIcon.buildCursor);
	}
	
	public void clickCallback() {
		int tileX = world.getSelectedTileX(), tileY = world.getSelectedTileY();
		if(blueprint.canBuildAt(world, tileX, tileY)) {
			try {
				buildingClass.getConstructors()[0].newInstance(game, tileX, tileY);
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			}
			
			game.setTool(new SelectorTool(game, world));
		}
	}
}
