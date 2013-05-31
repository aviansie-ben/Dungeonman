package com.bendude56.dungeonman.world.tile;

import java.awt.Color;
import java.awt.Graphics;

import com.bendude56.dungeonman.entity.Entity;
import com.bendude56.dungeonman.entity.EntityPlayer;

public class TileSecretDoor extends Tile {

	public TileSecretDoor() {
		super(4);
	}

	@Override
	public Color getColor(TileState state) {
		return Color.darkGray;
	}

	@Override
	public boolean onPlayerMove(TileState state, EntityPlayer player) {
		player.logMessage("You discover a hidden door!");
		state.setTileType(Tile.door);
		return false;
	}

	@Override
	public boolean onEntityMove(TileState state, Entity e) {
		return false;
	}
	
	@Override
	public void render(Graphics g, int x, int y, TileState state) {
		g.setColor(getColor(state));
		g.fillRect(x, y, 32, 32);
	}

	@Override
	public boolean canItemAlgorithmPass() {
		return false;
	}

	@Override
	public boolean isTransparent() {
		return false;
	}

}
