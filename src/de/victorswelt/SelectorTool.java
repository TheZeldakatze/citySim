package de.victorswelt;

public class SelectorTool extends Tool {

	public SelectorTool(CitySimGame game, TileWorld world) {
		super(game, world);
		game.tileWorld.currentBlueprint = null;
		
		game.setCursor(GameIcon.inspectCursor);
	}

	@Override
	public void clickCallback() {
		
	}
}
