package com.bendude56.dungeonman.entity;

import java.awt.Graphics;
import java.awt.Image;

import com.bendude56.dungeonman.world.World;
import com.bendude56.dungeonman.world.WorldLocation;

public abstract class Entity {
	private int x, y;
	private World world;
	private boolean dead = false;
	private final int id;
	
	public Entity(WorldLocation l) {
		setLocation(l);
		id = l.world.getGameInstance().generateEntityId();
	}
	
	public final World getWorld() {
		return world;
	}
	
	public final WorldLocation getLocation() {
		return new WorldLocation(world, x, y);
	}
	
	public void setLocation(WorldLocation l) {
		x = l.x;
		y = l.y;
		world = l.world;
	}
	
	public abstract void render(Graphics g, int x, int y);
	public abstract void doTurn();
	public abstract boolean doAction(ActionType type, Entity e);
	
	public boolean isVisible() {
		return world.isTileVisible(getLocation()) && !dead;
	}
	
	public final boolean isDead() {
		return dead;
	}
	
	public void die() {
		dead = true;
	}
	
	public final int getEntityId() {
		return id;
	}
	
	public enum ActionType {
		PICKUP, MOVE, CLIMB
	}
}
