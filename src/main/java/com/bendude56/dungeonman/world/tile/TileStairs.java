package com.bendude56.dungeonman.world.tile;

import java.awt.Color;

import com.bendude56.dungeonman.entity.Entity;
import com.bendude56.dungeonman.entity.EntityPlayer;

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

	@Override
	public boolean onPlayerMove(TileState state, EntityPlayer player) {
		player.logMessage("You see a staircase going " + (isGoingUp(state) ? "up" : "down"));
		return true;
	}

	@Override
	public boolean onEntityMove(TileState state, Entity e) {
		return true;
	}

}
