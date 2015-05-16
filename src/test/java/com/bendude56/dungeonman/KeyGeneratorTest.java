package com.bendude56.dungeonman;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.junit.Test;

import com.bendude56.dungeonman.entity.EntityStats;
import com.bendude56.dungeonman.world.World;
import com.bendude56.dungeonman.world.WorldLocation;
import com.bendude56.dungeonman.world.gen.KeyGenerator;
import com.bendude56.dungeonman.world.gen.SimpleDungeonGenerator;
import com.bendude56.dungeonman.world.tile.Tile;
import com.bendude56.dungeonman.world.tile.TileMetadataDoor;

/**
 * Benjamin C. Thomas Computer Science 30 2012/13 Semester 2 Centennial High
 * School
 *
 * @author Benjamin C. Thomas
 */

public class KeyGeneratorTest {
    
    @Test
    public void test() {
        GameInstance.createNewGame(0, new EntityStats(0, 0, 0, 0, 0, 0, 0));
        World w = GameInstance.getActiveWorld();
        
        w.setRect(0, 0, w.getWidth() - 1, w.getHeight() - 1, Tile.wall);
        w.setRect(2, 2, 6, 6, Tile.stoneFloor);
        w.setTile(4, 7, Tile.door);
        w.setMetadata(4, 7, new TileMetadataDoor(1));
        w.setEntryLocation(new WorldLocation(w, 2, 2));
        w.setTile(2, 2, Tile.stairs);
        
        SimpleDungeonGenerator.showDialog(w);
        
        new KeyGenerator(w, (List<WorldLocation>) Arrays.asList(new WorldLocation[] { new WorldLocation(w, 3, 3) }), new Random())
                .generateAllKeys();
    }
    
}
