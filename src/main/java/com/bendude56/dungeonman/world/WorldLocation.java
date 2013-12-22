package com.bendude56.dungeonman.world;

import com.bendude56.dungeonman.world.tile.Tile;
import com.bendude56.dungeonman.world.tile.TileMetadata;

/**
 * @author Benjamin C. Thomas
 */

public class WorldLocation {
	public World world;
	public int x, y;
	
	public WorldLocation(World world, int x, int y) {
		this.world = world;
		this.x = x;
		this.y = y;
	}
	
	public Tile getTile() {
		return world.getTile(this);
	}
	
	public void setTile(Tile t) {
		world.setTile(this, t);
	}
	
	public TileMetadata getMetadata() {
		return world.getMetadata(this);
	}
	
	public void setMetadata(TileMetadata m) {
		world.setMetadata(this, m);
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
			return new WorldLocation(world, x + deltaX, y - deltaY);
		} else if (orientation == 1) {
			return new WorldLocation(world, x + deltaY, y + deltaX);
		} else if (orientation == 2) {
			return new WorldLocation(world, x - deltaX, y + deltaY);
		} else if (orientation == 3) {
			return new WorldLocation(world, x - deltaY, y - deltaX);
		} else {
			throw new IllegalArgumentException("orientation must be between 0 and 3");
		}
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WorldLocation other = (WorldLocation)obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
}
