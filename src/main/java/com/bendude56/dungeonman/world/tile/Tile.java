package com.bendude56.dungeonman.world.tile;

import java.awt.Color;

public abstract class Tile {
	public static TileWall wall = new TileWall();
	public static TileStoneFloor stoneFloor = new TileStoneFloor();
	
	public abstract Color getColor();
}
