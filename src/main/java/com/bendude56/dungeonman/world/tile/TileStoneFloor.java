package com.bendude56.dungeonman.world.tile;

import java.awt.Color;

/**
 * @author Benjamin C. Thomas
 */

public class TileStoneFloor extends TileFloor {

	public TileStoneFloor() {
		super(1);
	}

	@Override
	public Color getColor(TileState state) {
		return Color.lightGray;
	}

}
