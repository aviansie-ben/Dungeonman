package com.bendude56.dungeonman.world.tile;

import java.awt.Color;

import com.bendude56.dungeonman.entity.Entity;
import com.bendude56.dungeonman.entity.EntityPlayer;

public class TileSecretDoor extends Tile {

	public TileSecretDoor() {
		super(4);
	}

	@Override
	public Color getColor(TileState state) {
		return Color.darkGray;
	}

	@Override
	public boolean onPlayerMove(TileState state, EntityPlayer player) {
		player.logMessage("You discover a hidden door!");
		state.setTileType(Tile.door);
		return false;
	}

	@Override
	public boolean onEntityMove(TileState state, Entity e) {
		return false;
	}

}
