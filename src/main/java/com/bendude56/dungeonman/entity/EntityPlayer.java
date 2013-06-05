package com.bendude56.dungeonman.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;

import com.bendude56.dungeonman.DebugCheats;
import com.bendude56.dungeonman.entity.Entity.ActionType;
import com.bendude56.dungeonman.gfx.ImageUtil;
import com.bendude56.dungeonman.item.inventory.Inventory;
import com.bendude56.dungeonman.ui.GameFrame;
import com.bendude56.dungeonman.world.WorldLocation;
import com.bendude56.dungeonman.world.tile.TileSecretDoor;

/**
 * Benjamin C. Thomas
 * Computer Science 30
 * 2012/13 Semester 2
 * Centennial High School
 *
 * @author Benjamin C. Thomas
 */

public class EntityPlayer extends EntityAlive {
	public static final Image humanSprite = ImageUtil.loadImage("/entity/player/human.png");
	
	private Inventory inventory;

	public EntityPlayer(WorldLocation l, EntityStats stats) {
		super(l, stats);
		this.viewDistance = 5;
		this.inventory = new Inventory();
	}
	
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
	
	public int getSearchDistance() {
		int d = 3;
		
		return d;
	}
	
	public void doSearch() {
		boolean done = false;
		
		for (int y = getLocation().y + getSearchDistance(); y >= getLocation().y - getSearchDistance(); y--) {
			int deltaX = getSearchDistance() - Math.abs(y - getLocation().y);
			for (int x = getLocation().x + deltaX; x >= getLocation().x - deltaX; x--) {
				if (new WorldLocation(getLocation().world, x, y).getTile() instanceof TileSecretDoor) {
					logMessage("A wall about " + AIController.getDistance(getLocation(), new WorldLocation(getWorld(), x, y)) + " tiles away seems off");
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
			EntityEnemy enemy = (EntityEnemy)e;
			int damage = AIController.calculateAttackPower(enemy, this, 10);
			
			logMessage("The " + enemy.getName() + " hits you for " + damage + " damage");
			this.doDamage(damage);
			
			if (isDead()) {
				logMessage("You are DEAD!!");
			}
		}
		
		return false;
	}
	
	public void logMessage(String message) {
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
	}

	public boolean doPickup(EntityDroppedItem e) {
		inventory.addItem(e.getItemStack());
		e.die();
		
		logMessage("You pick up a " + e.getItemStack().getItem().getItemName(e.getItemStack()) + " (x" + e.getItemStack().getAmount() + ")");
		
		return true;
	}
	
	public enum PlayerRace {
		HUMAN("Human", new EntityStats(5, 5, 5, 5, 5, 5, 200));
		
		public final String name;
		public final EntityStats bonusStats;
		
		private PlayerRace(String name, EntityStats bonusStats) {
			this.name = name;
			this.bonusStats = bonusStats;
		}
	}

}
