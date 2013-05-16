package com.bendude56.dungeonman.world;

public class WorldLocation {
	public World world;
	public int x, y;
	
	public WorldLocation(World world, int x, int y) {
		this.world = world;
		this.x = x;
		this.y = y;
	}
	
	public Tile getTile() {
		return world.tiles[x][y];
	}
	
	public void setTile(Tile t) {
		world.tiles[x][y] = t;
	}
	
	public WorldLocation adjustLocation(int deltaX, int deltaY) {
		return adjustLocation(deltaX, deltaY, 0);
	}
	
	//      0
	//      |
	//  3 --+-- 1
	//      |
	//      2
	
	public WorldLocation adjustLocation(int deltaX, int deltaY, int orientation) {
		if (orientation == 0) {
			return new WorldLocation(world, x + deltaX, y + deltaY);
		} else if (orientation == 1) {
			return new WorldLocation(world, x + deltaY, y + deltaX);
		} else if (orientation == 2) {
			return new WorldLocation(world, x - deltaX, y - deltaY);
		} else if (orientation == 3) {
			return new WorldLocation(world, x - deltaY, y - deltaX);
		} else {
			throw new IllegalArgumentException("orientation must be between 0 and 3");
		}
	}
}
