package com.bendude56.dungeonman.world.tile;

import java.awt.Color;

import com.bendude56.dungeonman.entity.Entity;
import com.bendude56.dungeonman.entity.EntityPlayer;

public class TileWall extends Tile {

	public TileWall() {
		super(0);
	}

	@Override
	public Color getColor(TileState state) {
		return Color.gray;
	}

	@Override
	public boolean onPlayerMove(TileState state, EntityPlayer player) {
		return false;
	}

	@Override
	public boolean onEntityMove(TileState state, Entity e) {
		return false;
	}

}
