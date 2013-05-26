package com.bendude56.dungeonman.world.tile;

import java.awt.Color;

public class TileWall extends Tile {

	public TileWall() {
		super(0);
	}

	@Override
	public Color getColor(TileState state) {
		return Color.gray;
	}

}
