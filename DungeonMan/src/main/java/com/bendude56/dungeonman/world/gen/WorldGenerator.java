package com.bendude56.dungeonman.world.gen;

import java.util.Random;

import com.bendude56.dungeonman.world.World;

public abstract class WorldGenerator {
	public World world;
	public Random random;
	
	public WorldGenerator(World world) {
		this.world = world;
		this.random = new Random();
	}
	
	public abstract void generateLevel(int difficulty);
}
