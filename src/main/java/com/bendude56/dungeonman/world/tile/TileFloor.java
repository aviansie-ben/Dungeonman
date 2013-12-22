package com.bendude56.dungeonman.world.tile;

import java.awt.Graphics;

import com.bendude56.dungeonman.entity.Entity;
import com.bendude56.dungeonman.entity.EntityPlayer;

/**
 * @author Benjamin C. Thomas
 */

public abstract class TileFloor extends Tile {

	public TileFloor(int tileId) {
		super(tileId);
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
