package com.bendude56.dungeonman.world.tile;

import java.awt.Color;
import java.awt.Graphics;

import com.bendude56.dungeonman.entity.Entity;
import com.bendude56.dungeonman.entity.EntityPlayer;

public class TileStoneFloor extends TileFloor {

	public TileStoneFloor() {
		super(1);
	}

	@Override
	public Color getColor(TileState state) {
		return Color.lightGray;
	}

}
