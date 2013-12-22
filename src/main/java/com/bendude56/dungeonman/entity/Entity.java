package com.bendude56.dungeonman.entity;

import java.awt.Graphics;

import com.bendude56.dungeonman.item.ItemStack;
import com.bendude56.dungeonman.world.World;
import com.bendude56.dungeonman.world.WorldLocation;

/**
 * @author Benjamin C. Thomas
 */

public abstract class Entity {
	private int x, y;
	private World world;
	private boolean dead = false;
	private final int id;
	
	public Entity(WorldLocation l) {
		setLocation(l);
		id = l.world.getGameInstance().generateEntityId();
	}
	
	/**
	 * Drops the specified {@link com.bendude56.dungeonman.item.ItemStack} at this
	 * entity's location.
	 * 
	 * @param i The stack of items to be dropped
	 */
	public final void dropItem(ItemStack i) {
		world.addEntity(new EntityDroppedItem(getLocation(), i));
	}
	
	/**
	 * Get the world in which this entity is located
	 */
	public final World getWorld() {
		return world;
	}
	
	/**
	 * Gets the location of this entity within its world
	 */
	public final WorldLocation getLocation() {
		return new WorldLocation(world, x, y);
	}
	
	/**
	 * Teleports this entity to the specified location. Teleporting between
	 * worlds may cause unintended problems; when teleporting between worlds,
	 * use {@link com.bendude56.dungeonman.GameInstance#teleport(Entity, WorldLocation)}.
	 * 
	 * @param l
	 */
	public void setLocation(WorldLocation l) {
		x = l.x;
		y = l.y;
		world = l.world;
	}
	
	/**
	 * Renders this entity to a graphics panel at the specified location
	 * 
	 * @param g The graphics object on which this entity should be rendered
	 * @param x The x location at which to render this entity
	 * @param y The y location at which to render this entity
	 */
	public abstract void render(Graphics g, int x, int y);
	
	/**
	 * Performs logic after the player makes a move
	 */
	public abstract void doTurn();
	
	/**
	 * Called when an entity performs the specified action on this entity
	 * 
	 * @param type The type of action which has been performed (e.g. moving)
	 * @param e The entity which has performed the action
	 * 
	 * @return True if the action should follow through (e.g. Entity should
	 *         move)
	 */
	public abstract boolean doAction(ActionType type, Entity e);
	
	/**
	 * Gets a boolean value indicating whether this entity is visible to
	 * the player and should be rendered
	 */
	public boolean isVisible() {
		return world.isTileVisible(getLocation()) && !dead;
	}
	
	/**
	 * Gets a boolean value representing whether or not this entity has been
	 * marked as dead. An entity marked as dead will be removed after all
	 * entities have completed their actions for the next turn.
	 */
	public final boolean isDead() {
		return dead;
	}
	
	/**
	 * Marks this entity for removal from the world.
	 */
	public void die() {
		dead = true;
	}
	
	/**
	 * Gets the ID associated with this entity
	 */
	public final int getEntityId() {
		return id;
	}
	
	public enum ActionType {
		PICKUP, MOVE, CLIMB
	}
}
