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
	
	/**
	 * Gets the metadata that was present at the specified location when this
	 * state was taken.
	 */
	public TileMetadata getMetadata() {
		return metadata;
	}

	/**
	 * Sets the metadata on the specified tile. This change will not be applied
	 * until {@link #update()} is called.
	 */
	public void setMetadata(TileMetadata metadata) {
		this.metadata = metadata;
	}

	/**
	 * Gets the tile type that was present at the specified location when this
	 * state was taken.
	 */
	public Tile getTileType() {
		return tileType;
	}

	/**
	 * Sets the type of tile at the specified location. This change will not be
	 * applied until {@link #update()} is called.
	 */
	public void setTileType(Tile tileType) {
		this.tileType = tileType;
	}

	/**
	 * Applies any changes that have been made to this tile state snapshot to
	 * the live world.
	 */
	public void update() {
		w.setTile(x, y, tileType);
		w.setMetadata(x, y, metadata);
	}
}
