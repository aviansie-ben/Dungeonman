package com.bendude56.dungeonman.world;

import com.bendude56.dungeonman.world.tile.Tile;
import com.bendude56.dungeonman.world.tile.TileMetadata;

/**
 * @author Benjamin C. Thomas
 */

public class WorldLocation {
    public final World world;
    public final int x, y;
    
    public WorldLocation(World world, int x, int y) {
        this.world = world;
        this.x = x;
        this.y = y;
    }
    
    public Tile getTile() {
        return world.getTile(this);
    }
    
    public void setTile(Tile t) {
        world.setTile(this, t);
    }
    
    public TileMetadata getMetadata() {
        return world.getMetadata(this);
    }
    
    public void setMetadata(TileMetadata m) {
        world.setMetadata(this, m);
    }
    
    public WorldLocation adjustLocation(int deltaX, int deltaY) {
        return adjustLocation(deltaX, deltaY, Direction.NORTH);
    }
    
    public WorldLocation adjustLocation(int deltaX, int deltaY, Direction dir) {
        switch (dir) {
        case NORTH:
            return new WorldLocation(world, x + deltaX, y - deltaY);
        case EAST:
            return new WorldLocation(world, x + deltaY, y + deltaX);
        case SOUTH:
            return new WorldLocation(world, x - deltaX, y + deltaY);
        case WEST:
            return new WorldLocation(world, x - deltaY, y - deltaX);
        default:
            throw new IllegalArgumentException("Unknown direction!");
        }
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + x;
        result = prime * result + y;
        return result;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        WorldLocation other = (WorldLocation) obj;
        if (x != other.x)
            return false;
        if (y != other.y)
            return false;
        return true;
    }
    
    public enum Direction {
        NORTH(0), EAST(1), SOUTH(2), WEST(3);
        
        private static final Direction[] byValue = new Direction[] { NORTH, EAST, SOUTH, WEST };
        
        private final int value;
        
        private Direction(int value) {
            this.value = value;
        }
        
        public Direction flip() {
            return byValue[(value + 2) % 4];
        }
        
        public Direction rotateClockwise() {
            return byValue[(value + 1) % 4];
        }
        
        public Direction rotateCounterClockwise() {
            return byValue[(value + 3) % 4];
        }
    }
}
