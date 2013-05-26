package com.bendude56.dungeonman.world.tile;

import java.awt.Color;

public class TileStairs extends TileFloor {

	public TileStairs() {
		super(2);
	}

	@Override
	public Color getColor(TileState state) {
		return (isGoingUp(state)) ? Color.blue : Color.red;
	}
	
	private boolean isGoingUp(TileState state) {
		return state.getMetadata() instanceof TileMetadataStairs && ((TileMetadataStairs)state.getMetadata()).isGoingUp();
	}

}
