package com.bendude56.dungeonman.world.tile;

import java.awt.Color;
import java.util.HashMap;

import com.bendude56.dungeonman.entity.Entity;
import com.bendude56.dungeonman.entity.EntityPlayer;

public abstract class Tile {
	private static HashMap<Integer, Tile> tiles = new HashMap<Integer, Tile>();
	
	public static TileWall wall = new TileWall();
	public static TileStoneFloor stoneFloor = new TileStoneFloor();
	public static TileStairs stairs = new TileStairs();
	public static TileDoor door = new TileDoor();
	public static TileSecretDoor secretDoor = new TileSecretDoor();
	
	private int id;
	
	public Tile(int tileId) {
		if (tiles.containsKey(tileId))
			throw new IllegalStateException("Duplicate tile ID!");
		
		tiles.put(tileId, this);
		this.id = tileId;
	}
	
	public final int getTileId() {
		return id;
	}
	
	public abstract Color getColor(TileState state);
	public abstract boolean onPlayerMove(TileState state, EntityPlayer player);
	public abstract boolean onEntityMove(TileState state, Entity e);
}