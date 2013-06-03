package com.bendude56.dungeonman.entity;

import java.awt.Graphics;
import java.awt.Image;

import com.bendude56.dungeonman.gfx.ImageUtil;
import com.bendude56.dungeonman.item.inventory.Inventory;
import com.bendude56.dungeonman.ui.GameFrame;
import com.bendude56.dungeonman.world.WorldLocation;

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
		this.viewDistance = 3;
		this.inventory = new Inventory();
	}
	
	public Inventory getInventory() {
		return inventory;
	}

	@Override
	public void doTurn() {
		// Do nothing (Player actions are handled elsewhere)
	}

	@Override
	public boolean doAction(ActionType type, Entity e) {
		// TODO: Damage when attacked by monsters
		return false;
	}
	
	public void logMessage(String message) {
		GameFrame.activeFrame.logMessage(message);
	}

	@Override
	public void render(Graphics g, int x, int y) {
		g.drawImage(humanSprite, x, y, null);
	}

	public boolean doPickup(EntityDroppedItem e) {
		inventory.addItem(e.getItemStack());
		e.die();
		
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
