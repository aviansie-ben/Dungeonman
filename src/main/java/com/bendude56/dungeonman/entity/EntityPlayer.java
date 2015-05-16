package com.bendude56.dungeonman.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;

import com.bendude56.dungeonman.DebugCheats;
import com.bendude56.dungeonman.GameInstance;
import com.bendude56.dungeonman.gfx.ImageUtil;
import com.bendude56.dungeonman.item.inventory.Inventory;
import com.bendude56.dungeonman.ui.GameFrame;
import com.bendude56.dungeonman.world.WorldLocation;
import com.bendude56.dungeonman.world.tile.Tile;
import com.bendude56.dungeonman.world.tile.TileSecretDoor;

/**
 * @author Benjamin C. Thomas
 */

public class EntityPlayer extends EntityAlive {
    public static final Image humanSprite = ImageUtil.loadImage("/entity/player/human.png");
    
    private Inventory inventory;
    
    public EntityPlayer(WorldLocation l, EntityStats stats) {
        super(l, stats);
        this.viewDistance = 7;
        this.inventory = new Inventory();
    }
    
    /**
     * Gets the player's inventory.
     */
    public Inventory getInventory() {
        return inventory;
    }
    
    @Override
    public void doTurn() {
        // Update world visibility
        getLocation().world.clearTileVisibility();
        
        for (int y = getLocation().y + getViewDistance(); y >= getLocation().y - getViewDistance(); y--) {
            int deltaX = getViewDistance() - Math.abs(y - getLocation().y);
            for (int x = getLocation().x + deltaX; x >= getLocation().x - deltaX; x--) {
                if (AIController.checkVisibility(getLocation(), new WorldLocation(null, x, y))) {
                    getLocation().world.setTileVisible(x, y, true);
                }
                
                if (new WorldLocation(getLocation().world, x, y).getTile() instanceof TileSecretDoor) {
                    int chance = 30 - getStats().getIntelligence();
                    
                    if (chance <= 0 || new Random().nextInt(chance) == 0) {
                        logMessage("Something about one of the walls nearby seems off to you");
                    }
                }
            }
        }
    }
    
    /**
     * Gets the distance that this player can detect hidden doors from when
     * searching.
     */
    public int getSearchDistance() {
        int d = 3;
        
        return d;
    }
    
    /**
     * Causes the player to perform a search, and note any results in the text
     * log.
     */
    public void doSearch() {
        boolean done = false;
        
        for (int y = getLocation().y + getSearchDistance(); y >= getLocation().y - getSearchDistance(); y--) {
            int deltaX = getSearchDistance() - Math.abs(y - getLocation().y);
            for (int x = getLocation().x + deltaX; x >= getLocation().x - deltaX; x--) {
                if (new WorldLocation(getLocation().world, x, y).getTile() instanceof TileSecretDoor) {
                    logMessage("A wall about " + AIController.getDistance(getLocation(), new WorldLocation(getWorld(), x, y))
                            + " tiles away seems off");
                    done = true;
                }
            }
        }
        
        if (!done) {
            logMessage("You don't find anything of interest");
        }
    }
    
    @Override
    public boolean doAction(ActionType type, Entity e) {
        if (type == ActionType.MOVE && e instanceof EntityEnemy) {
            EntityEnemy enemy = (EntityEnemy) e;
            int damage = AIController.calculateAttackPower(enemy, this);
            
            logMessage("The " + enemy.getName() + " hits you for " + damage + " damage");
            this.doDamage(damage);
            
            if (isDead()) {
                logMessage("You are DEAD!!");
            }
        }
        
        return false;
    }
    
    @Override
    public int calculateOutgoingDamage() {
        if (GameInstance.getActiveInstance().getDifficulty() >= 5) {
            return 1;
        } else {
            return super.calculateOutgoingDamage();
        }
    }
    
    @Override
    public int calculateOutgoingDeviation(int damage) {
        if (GameInstance.getActiveInstance().getDifficulty() >= 5) {
            return 0;
        } else {
            return super.calculateOutgoingDeviation(damage);
        }
    }
    
    public void logMessage(String message) {
        if (GameFrame.activeFrame != null)
            GameFrame.activeFrame.logMessage(message);
    }
    
    @Override
    public void render(Graphics g, int x, int y) {
        g.drawImage(humanSprite, x, y, null);
    }
    
    @Override
    public void doDamage(int damage) {
        if (!DebugCheats.noDamage || damage < 0)
            super.doDamage(damage);
        
        if (isDead()) {
            getLocation().setTile(Tile.gravestone);
        }
    }
    
    /**
     * Causes the player to pick up the specified dropped item and add it to
     * their inventory.
     * 
     * @param e The dropped item to be picked up.
     * 
     * @return True if the item was picked up successfully, otherwise false.
     */
    public boolean doPickup(EntityDroppedItem e) {
        logMessage("You pick up a " + e.getItemStack());
        
        inventory.addItem(e.getItemStack());
        e.die();
        
        return true;
    }
    
    @Override
    public int getDrawOrder() {
        return 1;
    }
    
    public enum PlayerRace {
        HUMAN("Human", new EntityStats(5, 5, 5, 5, 5, 5, 200), EntityPlayer.humanSprite);
        
        public final String name;
        public final EntityStats bonusStats;
        public final Image sprite;
        
        private PlayerRace(String name, EntityStats bonusStats, Image sprite) {
            this.name = name;
            this.bonusStats = bonusStats;
            this.sprite = sprite;
        }
    }
    
}
