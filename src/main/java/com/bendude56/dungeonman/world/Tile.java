package com.bendude56.dungeonman.world;

import java.awt.Color;

public abstract class Tile {
	public static TileWall wall = new TileWall();
	public static TileStoneFloor stoneFloor = new TileStoneFloor();
	public static TileRedFloor redFloor = new TileRedFloor();
	
	public abstract Color getColor();
}
