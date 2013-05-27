package com.bendude56.dungeonman.world.tile;

import java.awt.Color;
import java.awt.Graphics;

import com.bendude56.dungeonman.entity.Entity;
import com.bendude56.dungeonman.entity.EntityPlayer;

public class TileStairs extends TileFloor {

	public TileStairs() {
		super(2);
	}

	@Override
	public Color getColor(TileState state) {
		return (isGoingUp(state)) ? Color.blue : Color.red;
	}
	
	private boolean isGoingUp(TileState state) {
		return state.getMetadata() instanceof TileMetadataStairs && ((TileMetadataStairs)state.getMetadata()).isGoingUp();
	}

	@Override
	public boolean onPlayerMove(TileState state, EntityPlayer player) {
		player.logMessage("You see a staircase going " + (isGoingUp(state) ? "up" : "down"));
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

}
