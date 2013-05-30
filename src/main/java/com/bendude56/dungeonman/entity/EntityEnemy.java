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

	public EntityEnemy(WorldLocation l, EntityStats stats, int viewDistance) {
		super(l, stats);
		this.viewDistance = viewDistance;
	}
	
	public Entity getTarget() {
		return target;
	}
	
	public WorldLocation getTargetLastSeen() {
		return targetLastSeen;
	}
	
	public void setTarget(Entity e) {
		if (e != null && !canSee(e)) {
			throw new IllegalArgumentException("Cannot target an entity that is out of sight!");
		}
		
		target = e;
		targetLastSeen = e.getLocation();
	}

	@Override
	public void doTurn() {
		if (target != null && canSee(target))
			targetLastSeen = target.getLocation();
		
		// TODO: Add enemy AI here
	}

}
