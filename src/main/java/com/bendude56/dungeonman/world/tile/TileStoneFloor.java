package com.bendude56.dungeonman.world.tile;

import java.awt.Color;

/**
 * Benjamin C. Thomas
 * Computer Science 30
 * 2012/13 Semester 2
 * Centennial High School
 *
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
