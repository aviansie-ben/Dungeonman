package com.bendude56.dungeonman.world.gen;

import java.util.Random;

import com.bendude56.dungeonman.world.World;

/**
 * @author Benjamin C. Thomas
 */

public abstract class WorldGenerator {
	public World world;
	public Random random;
	
	public WorldGenerator(World world) {
		this.world = world;
		this.random = new Random();
	}
	
	/**
	 * Generates the specified world with the proper difficulty level.
	 * 
	 * @param difficulty The difficulty level of the game which is generating
	 *        this world.
	 */
	public abstract void generateLevel(int difficulty);
}
