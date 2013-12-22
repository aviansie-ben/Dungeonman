package com.bendude56.dungeonman.entity;

import com.bendude56.dungeonman.GameInstance;
import com.bendude56.dungeonman.world.WorldLocation;

/**
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
	
	/**
	 * Gets an object representing the levelling statistics of this entity
	 */
	public final EntityStats getStats() {
		return stats;
	}
	
	/**
	 * Checks whether this entity can see the specified entity
	 * 
	 * @param e The entity that should be checked for visibility
	 * 
	 * @return True if and only if the entity is within view distance and the
	 *         line of sight between the two entities is unobstructed
	 */
	public final boolean canSee(Entity e) {
		return AIController.getDistance(getLocation(), e.getLocation()) <= viewDistance && AIController.checkVisibility(getLocation(), e.getLocation());
	}
	
	/**
	 * Gets the number of tiles away that this entity is capable of seeing
	 */
	public int getViewDistance() {
		return viewDistance;
	}
	
	/**
	 * Gets an integer representing the current amount of health that this
	 * entity has. If this value ever reaches 0, this entity will be
	 * automatically marked for removal.
	 */
	public int getHp() {
		return hp;
	}
	
	/**
	 * Gets an integer representing the maximum possible amount of health that
	 * this entity could possibly have.
	 */
	public int getMaxHp() {
		return maxHp;
	}
	
	@Override
	public void die() {
		hp = 0;
		super.die();
	}
	
	/**
	 * Does a specified amount of damage to this entity. This entity will
	 * automatically be marked for removal if its health would drop below 0.
	 * 
	 * @param damage The amount of damage that should be done to this
	 *               entity. Supplying a negative damage will cause this
	 *               entity to be healed.
	 */
	public void doDamage(int damage) {
		hp -= damage;
		
		if (hp > maxHp) {
			hp = maxHp;
		} else if (hp <= 0) {
			die();
		}
	}
	
	/**
	 * Calculates the base amount of damage that this entity will do when
	 * attacking another entity.
	 */
	public int calculateOutgoingDamage() {
		return stats.calculateOutgoingDamage(10);
	}
	
	/**
	 * Calculates the amount of deviation (only in the positive direction) that
	 * should be applied to any outgoing damage coming from this entity.
	 * 
	 * @param damage The base amount of damage being done by the attack
	 * 
	 * @return The random deviation that should be applied to the attack. Note
	 *         that this value CAN be zero.
	 */
	public int calculateOutgoingDeviation(int damage) {
		return stats.calculateOutgoingDeviation(damage);
	}
	
	/**
	 * Adjusts incoming damage to account for the defensive capabilities of
	 * this entity.
	 * 
	 * @param damage The base amount of damage that should be adjusted. This
	 *               should be retrieved from the attacker's
	 *               {@link EntityAlive#calculateOutgoingDamage()}.
	 * 
	 * @return The new base amount of damage to do to this entity
	 */
	public int calculateIncomingDamage(int damage) {
		if (!(this instanceof EntityPlayer) && GameInstance.getActiveInstance().getDifficulty() >= 5)
			return damage;
		else
			return stats.calculateIncomingDamage(damage);
	}
	
	/**
	 * Adjusts the standard deviation to account for this entity's defensive
	 * capabilities.
	 * 
	 * @param damage The base amount of damage being done.
	 * @param deviation The deviation supplied by the attacker's
	 *                  {@link EntityAlive#calculateOutgoingDeviation(int)}
	 * 
	 * @return The adjusted amount of deviation to be applied to the attack
	 */
	public int calculateIncomingDeviation(int damage, int deviation) {
		return stats.calculateIncomingDeviation(damage, deviation);
	}
}
