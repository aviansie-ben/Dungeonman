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

public class EntityPlayer extends EntityAlive {

	public EntityPlayer(WorldLocation l, int maxHp) {
		super(l, maxHp);
		this.viewDistance = 10;
	}

	@Override
	public void doTurn() {
		// Do nothing (UI will control player actions)
	}

}
