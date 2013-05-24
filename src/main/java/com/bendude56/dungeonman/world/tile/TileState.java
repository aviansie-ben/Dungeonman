package com.bendude56.dungeonman.world.tile;

import com.bendude56.dungeonman.world.World;
import com.bendude56.dungeonman.world.WorldLocation;

/**
 * Benjamin C. Thomas
 * Computer Science 30
 * 2012/13 Semester 2
 * Centennial High School
 *
 * @author Benjamin C. Thomas
 */

public class TileState {
	private TileMetadata metadata;
	private final int x, y;
	private final World w;
	private Tile tileType;
	
	public TileState(WorldLocation l) {
		metadata = l.getMetadata().clone();
		x = l.x;
		y = l.y;
		w = l.world;
		tileType = l.getTile();
	}
	
	public TileMetadata getMetadata() {
		return metadata;
	}

	public void setMetadata(TileMetadata metadata) {
		this.metadata = metadata;
	}

	public Tile getTileType() {
		return tileType;
	}

	public void setTileType(Tile tileType) {
		this.tileType = tileType;
	}

	public void update() {
		w.setTile(x, y, tileType);
		w.setMetadata(x, y, metadata);
	}
}
