package com.bendude56.dungeonman.world.tile;

public class TileMetadataStairs extends TileMetadata {
	private final boolean isGoingUp;
	
	public TileMetadataStairs(boolean isGoingUp) {
		this.isGoingUp = isGoingUp;
	}
	
	public boolean isGoingUp() {
		return isGoingUp;
	}
	
	@Override
	public TileMetadata clone() {
		return new TileMetadataStairs(isGoingUp);
	}
}
