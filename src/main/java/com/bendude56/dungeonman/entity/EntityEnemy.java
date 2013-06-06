package com.bendude56.dungeonman.entity;

import com.bendude56.dungeonman.world.WorldLocation;

/**
 * Benjamin C. Thomas
 * Computer Science 30
 * 2012/13 Semester 2
 * Centennial High School
 *
 * @author Benjamin C. Thomas
 */

public abstract class EntityEnemy extends EntityAlive {
	private Entity target;
	private WorldLocation targetLastSeen;
	private String name;
	private int damagePower;

	public EntityEnemy(WorldLocation l, EntityStats stats, String name, int viewDistance, int damagePower) {
		super(l, stats);
		this.damagePower = damagePower;
		this.viewDistance = viewDistance;
		this.name = name;
	}
	
	/**
	 * Gets the entity that this entity is currently targetting. This value
	 * shouldn't be used for pathfinding. Use {@link #getTargetLastSeen()}
	 * instead when working with pathfinding.
	 */
	public Entity getTarget() {
		return target;
	}
	
	/**
	 * Gets the location at which the target of this entity was last seen by
	 * this entity. This is the location towards which this entity will head.
	 */
	public WorldLocation getTargetLastSeen() {
		return targetLastSeen;
	}
	
	/**
	 * Gets an integer representing the base amount of damage done by this
	 * entity.
	 */
	public int getDamagePower() {
		return damagePower;
	}
	
	/**
	 * Sets the target towards which this entity should head.
	 * 
	 * @param e The new entity to target. MUST be in sight of this entity.
	 */
	public void setTarget(Entity e) {
		if (e != null && !canSee(e)) {
			throw new IllegalArgumentException("Cannot target an entity that is out of sight!");
		}
		
		target = e;
		targetLastSeen = e.getLocation();
	}

	@Override
	public void doTurn() {
		if (target != null && canSee(target)) {
			targetLastSeen = target.getLocation();
		}
		
		targetCheck();
		
		if (target != null)
			AIController.moveTowards(this, targetLastSeen);
	}
	
	@Override
	public boolean doAction(ActionType type, Entity e) {
		if (type == ActionType.MOVE && e instanceof EntityPlayer) {
			EntityPlayer p = (EntityPlayer)e;
			int damage = AIController.calculateAttackPower(p, this);
			
			p.logMessage("You hit the " + name + " for " + damage + " damage");
			this.doDamage(damage);
			
			if (isDead()) {
				p.logMessage("The " + name + " has died!");
			}
		}
		return false;
	}
	
	private void targetCheck() {
		for (Entity e : getWorld().getEntities(getLocation().x - viewDistance, getLocation().y - viewDistance,
				getLocation().x + viewDistance, getLocation().y + viewDistance)) {
			if (e instanceof EntityPlayer && canSee(e) && target == null) {
				setTarget(e);
			}
		}
	}
	
	/**
	 * Attempts to move this entity to the designated location, firing any
	 * events along the way.
	 * 
	 * @param l The location to which this entity should move.
	 */
	public void doMove(WorldLocation l) {
		if (!l.getTile().onEntityMove(l.world.getTileState(l), this)) {
			return;
		}
		
		for (Entity e : l.world.getEntities(l)) {
			if (!e.doAction(ActionType.MOVE, this)) {
				return;
			}
		}
		
		setLocation(l);
	}

	/**
	 * Gets the name that should be displayed for this entity in the text log.
	 */
	public String getName() {
		return name;
	}

}
