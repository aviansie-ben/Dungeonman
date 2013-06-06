package com.bendude56.dungeonman.world.tile;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;

import com.bendude56.dungeonman.entity.Entity;
import com.bendude56.dungeonman.entity.EntityPlayer;

/**
 * Benjamin C. Thomas
 * Computer Science 30
 * 2012/13 Semester 2
 * Centennial High School
 *
 * @author Benjamin C. Thomas
 */

public abstract class Tile {
	private static HashMap<Integer, Tile> tiles = new HashMap<Integer, Tile>();
	
	public static TileWall wall = new TileWall();
	public static TileStoneFloor stoneFloor = new TileStoneFloor();
	public static TileStairs stairs = new TileStairs();
	public static TileDoor door = new TileDoor();
	public static TileSecretDoor secretDoor = new TileSecretDoor();
	public static TileGravestone gravestone = new TileGravestone();
	
	private int id;
	
	public Tile(int tileId) {
		if (tiles.containsKey(tileId))
			throw new IllegalStateException("Duplicate tile ID!");
		
		tiles.put(tileId, this);
		this.id = tileId;
	}
	
	/**
	 * Gets the unique identifier of this tile.
	 */
	public final int getTileId() {
		return id;
	}
	
	/**
	 * Returns a boolean representing whether or not the key placement
	 * algorithm should consider this tile to be a passable tile.
	 */
	public abstract boolean canItemAlgorithmPass();
	
	/**
	 * Gets a boolean representing whether or not the visibility algorithm
	 * should consider this tile to be transparent.
	 */
	public abstract boolean isTransparent();
	
	/**
	 * Render this tile at the specified location.
	 * 
	 * @param g The graphics layer onto which this tile should be drawn
	 * @param x The x-coordinate at which to render the tile
	 * @param y The y-coordinate at which to render the tile
	 * @param state The tile state that should be rendered
	 */
	public abstract void render(Graphics g, int x, int y, TileState state);
	
	/**
	 * Gets the color that this tile should appear as on a map.
	 */
	public abstract Color getColor(TileState state);
	
	/**
	 * Called whenever a player moves into/onto this tile
	 * 
	 * @param state The state of the tile onto which the player will move
	 * @param player The player entity trying to move onto the tile
	 * 
	 * @return True if the move should be allowed, false otherwise
	 */
	public abstract boolean onPlayerMove(TileState state, EntityPlayer player);
	
	/**
	 * Called whenever a player uses the climb command while they are standing
	 * on a tile of this type.
	 * 
	 * @param state The state of the tile onto which the player is attempting
	 *        to climb.
	 * @param player The player entity that is climbing.
	 * 
	 * @return True if the climb was successful, false otherwise. If false is
	 *         returned, entities at this location will be checked.
	 */
	public abstract boolean onPlayerClimb(TileState state, EntityPlayer player);
	
	/**
	 * Called whenever a NON-PLAYER entity attempts to move into/onto this tile
	 * 
	 * @param state The state of the tile onto which the entity is moving
	 * @param e The entity that is being moved
	 * 
	 * @return True to allow the move, false to deny the move.
	 */
	public abstract boolean onEntityMove(TileState state, Entity e);
}
