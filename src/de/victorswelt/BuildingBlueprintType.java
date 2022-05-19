package de.victorswelt;

public enum BuildingBlueprintType {
	SMALL_HOUSE(new TileGraphicType[][] {{TileGraphicType.HOUSE_SMALL_00, TileGraphicType.HOUSE_SMALL_01},
										{TileGraphicType.HOUSE_SMALL_10, TileGraphicType.HOUSE_SMALL_11}}),
	
	FARM(new TileGraphicType[][] {{TileGraphicType.FARM_00, TileGraphicType.FARM_01},
		{TileGraphicType.FARM_10, TileGraphicType.FARM_11}});
	
	TileGraphicType[][] tiles;
	
	private BuildingBlueprintType(TileGraphicType tiles[][]) {
		this.tiles = tiles;
	}
	
	public boolean buildAt(TileWorld tw, int xoff, int yoff) {
		if(xoff<0 || yoff<0 || xoff + tiles[0].length >= tw.width || yoff + tiles.length >= tw.height)
			return false;
		
		for(int x = 0; x < tiles[0].length; x++) {
			for(int y = 0; y < tiles.length; y++) {
				tw.world[x+xoff][y+yoff] = tiles[x][y];
			}
		}
		
		return true;
	}
}
