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
     *            this world.
     * @param floor The floor that is being generated. The first floor of the
     *            dungeon is floor 1, the floor below that is floor 2, etc.
     */
    public abstract void generateLevel(int difficulty, int floor);
}
