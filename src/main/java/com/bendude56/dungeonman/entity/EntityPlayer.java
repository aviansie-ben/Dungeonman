package com.bendude56.dungeonman.entity;

import java.awt.Image;

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

	@Override
	public void doAction(ActionType type, Entity e) {
		// TODO: Damage when attacked by monsters
	}
	
	public void logMessage(String message) {
		// TODO: Log message
	}

	@Override
	public Image getDrawImage() {
		// TODO Auto-generated method stub
		return null;
	}

}
