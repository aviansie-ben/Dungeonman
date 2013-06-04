package com.bendude56.dungeonman.world.tile;

import java.awt.Color;
import java.awt.Graphics;

import com.bendude56.dungeonman.entity.Entity;
import com.bendude56.dungeonman.entity.EntityPlayer;

public class TileStoneFloor extends TileFloor {

	public TileStoneFloor() {
		super(1);
	}

	@Override
	public Color getColor(TileState state) {
		return Color.lightGray;
	}

	@Override
	public boolean onPlayerMove(TileState state, EntityPlayer player) {
		return true;
	}

	@Override
	public boolean onEntityMove(TileState state, Entity e) {
		return true;
	}
	
	@Override
	public void render(Graphics g, int x, int y, TileState state) {
		g.setColor(getColor(state));
		g.fillRect(x, y, 32, 32);
	}

	@Override
	public boolean canItemAlgorithmPass() {
		return true;
	}

	@Override
	public boolean isTransparent() {
		return true;
	}

	@Override
	public boolean onPlayerClimb(TileState state, EntityPlayer player) {
		return false;
	}

}
