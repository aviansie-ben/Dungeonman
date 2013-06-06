package com.bendude56.dungeonman.entity;

import com.bendude56.dungeonman.GameInstance;
import com.bendude56.dungeonman.world.WorldLocation;

/**
 * Benjamin C. Thomas
 * Computer Science 30
 * 2012/13 Semester 2
 * Centennial High School
 *
 * @author Benjamin C. Thomas
 */

public abstract class EntityAlive extends Entity {
	protected int viewDistance = 0;
	private int maxHp = 1;
	private int hp = 1;
	private EntityStats stats;
	
	public EntityAlive(WorldLocation l, EntityStats stats) {
		super(l);
		this.maxHp = stats.calculateMaxHp();
		this.hp = maxHp;
		this.stats = stats;
	}
	
	public final EntityStats getStats() {
		return stats;
	}
	
	public final boolean canSee(Entity e) {
		return AIController.getDistance(getLocation(), e.getLocation()) <= viewDistance && AIController.checkVisibility(getLocation(), e.getLocation());
	}
	
	public int getViewDistance() {
		return viewDistance;
	}
	
	public int getHp() {
		return hp;
	}
	
	public int getMaxHp() {
		return maxHp;
	}
	
	@Override
	public void die() {
		hp = 0;
		super.die();
	}
	
	public void doDamage(int damage) {
		hp -= damage;
		
		if (hp > maxHp) {
			hp = maxHp;
		} else if (hp <= 0) {
			die();
		}
	}
	
	public int calculateOutgoingDamage() {
		return stats.calculateOutgoingDamage(10);
	}
	
	public int calculateOutgoingDeviation(int damage) {
		return stats.calculateOutgoingDeviation(damage);
	}
	
	public int calculateIncomingDamage(int damage) {
		if (!(this instanceof EntityPlayer) && GameInstance.getActiveInstance().getDifficulty() >= 5)
			return damage;
		else
			return stats.calculateIncomingDamage(damage);
	}
	
	public int calculateIncomingDeviation(int damage, int deviation) {
		return stats.calculateIncomingDeviation(damage, deviation);
	}
}
