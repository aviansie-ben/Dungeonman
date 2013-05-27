package com.bendude56.dungeonman.entity;

import java.awt.Graphics;
import java.awt.Image;

import com.bendude56.dungeonman.gfx.ImageUtil;
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

	public EntityPlayer(WorldLocation l, int maxHp) {
		super(l, maxHp);
		this.viewDistance = 10;
	}

	@Override
	public void doTurn() {
		// Do nothing (Player actions are handled elsewhere)
	}

	@Override
	public void doAction(ActionType type, Entity e) {
		// TODO: Damage when attacked by monsters
	}
	
	public void logMessage(String message) {
		GameFrame.activeFrame.logMessage(message);
	}

	@Override
	public void render(Graphics g, int x, int y) {
		g.drawImage(humanSprite, x, y, null);
	}

}
