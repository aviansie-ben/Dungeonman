package com.bendude56.dungeonman.world.tile;

import java.awt.Color;

import com.bendude56.dungeonman.entity.Entity;
import com.bendude56.dungeonman.entity.EntityPlayer;

public class TileDoor extends Tile {

	public TileDoor() {
		super(3);
	}

	@Override
	public Color getColor(TileState state) {
		return Color.green;
	}

	@Override
	public boolean onPlayerMove(TileState state, EntityPlayer player) {
		// TODO: Implement door locking here
		state.setTileType(Tile.stoneFloor);
		player.logMessage("You open the door!");
		return false;
	}

	@Override
	public boolean onEntityMove(TileState state, Entity e) {
		return false;
	}

}
