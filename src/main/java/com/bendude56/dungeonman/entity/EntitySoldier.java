package com.bendude56.dungeonman.entity;

import java.awt.Graphics;

import com.bendude56.dungeonman.world.WorldLocation;

/**
 * Benjamin C. Thomas
 * Computer Science 30
 * 2012/13 Semester 2
 * Centennial High School
 *
 * @author Benjamin C. Thomas
 */

public class EntitySoldier extends EntityEnemy {

	public EntitySoldier(WorldLocation l) {
		super(l, new EntityStats(0, 10, 8, 5, 9, 3, 50), "Soldier", 3, 10);
	}

	@Override
	public void render(Graphics g, int x, int y) {
		g.drawImage(EntityPlayer.humanSprite, x, y, null);
	}

}
