package com.bendude56.dungeonman;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bendude56.dungeonman.entity.EntityPlayer;
import com.bendude56.dungeonman.entity.EntityStats;
import com.bendude56.dungeonman.world.World;
import com.bendude56.dungeonman.world.WorldLocation;
import com.bendude56.dungeonman.world.tile.Tile;
import com.bendude56.dungeonman.world.tile.TileMetadata;
import com.bendude56.dungeonman.world.tile.TileMetadataDoor;
import com.bendude56.dungeonman.world.tile.TileMetadataStairs;

public class WorldTest {
    
    private static World world;
    
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        GameInstance.createNewGame(0, new EntityStats(0, 0, 0, 0, 0, 0, 0));
    }
    
    @Before
    public void setUp() throws Exception {
        world = GameInstance.getActiveInstance().getFloor(1);
        world.setRect(0, 0, world.getWidth() - 1, world.getHeight() - 1, Tile.wall);
        world.clearEntities();
    }
    
    @Test
    public void testTileSet() {
        WorldLocation l = new WorldLocation(world, 0, 0);
        world.setTile(l, Tile.stoneFloor);
        
        Assert.assertEquals(Tile.stoneFloor, world.getTile(0, 0));
        Assert.assertEquals(Tile.stoneFloor, l.getTile());
        
        l.setTile(Tile.door);
        
        Assert.assertEquals(Tile.door, world.getTile(0, 0));
        Assert.assertEquals(Tile.door, l.getTile());
    }
    
    @Test
    public void testMassTileSet() {
        world.setRect(0, 0, 3, 3, Tile.stoneFloor);
        
        for (int y = 0; y <= 3; y++) {
            for (int x = 0; x <= 3; x++) {
                Assert.assertEquals(Tile.stoneFloor, world.getTile(x, y));
            }
        }
    }
    
    @Test
    public void testMetadataEquality() {
        Assert.assertFalse(new TileMetadata().equals(new TileMetadataStairs(true)));
        Assert.assertFalse(new TileMetadataStairs(true).equals(new TileMetadataStairs(false)));
        Assert.assertFalse(new TileMetadataDoor(0).equals(new TileMetadataDoor(1)));
        
        Assert.assertTrue(new TileMetadata().equals(new TileMetadata()));
        Assert.assertTrue(new TileMetadataStairs(true).equals(new TileMetadataStairs(true)));
        Assert.assertTrue(new TileMetadataDoor(0).equals(new TileMetadataDoor(0)));
    }
    
    @Test
    public void testMetadataSet() {
        world.setMetadata(0, 0, new TileMetadataStairs(true));
        
        Assert.assertEquals(new TileMetadataStairs(true), world.getMetadata(0, 0));
        
        world.setTile(0, 0, Tile.wall);
        
        Assert.assertEquals(new TileMetadata(), world.getMetadata(0, 0));
    }
    
    @Test
    public void testEntity() {
        EntityPlayer p;
        
        world.addEntity(p = new EntityPlayer(new WorldLocation(world, 0, 0), new EntityStats(0, 0, 0, 0, 0, 0, 0)));
        
        Assert.assertNotNull(world.getEntity(p.getEntityId()));
        Assert.assertEquals(p, world.getEntities(0, 0).get(0));
        
        p.setLocation(new WorldLocation(world, 0, 1));
        
        Assert.assertEquals(0, world.getEntities(0, 0).size());
        Assert.assertEquals(p, world.getEntities(0, 1).get(0));
        
        world.removeEntity(p);
        
        Assert.assertEquals(0, world.getEntities(0, 1).size());
        Assert.assertNull(world.getEntity(p.getEntityId()));
        
        world.addEntity(p);
        
        Assert.assertNotNull(world.getEntity(p.getEntityId()));
        Assert.assertEquals(p, world.getEntities(0, 1).get(0));
        
        p.die();
        world.doTurn();
        
        Assert.assertEquals(0, world.getEntities(0, 1).size());
        Assert.assertNull(world.getEntity(p.getEntityId()));
    }
}
