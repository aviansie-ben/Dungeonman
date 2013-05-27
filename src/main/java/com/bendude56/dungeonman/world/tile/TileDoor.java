package com.bendude56.dungeonman.world.tile;

import java.awt.Color;
import java.awt.Graphics;

import com.bendude56.dungeonman.entity.Entity;
import com.bendude56.dungeonman.entity.EntityPlayer;

public class TileDoor extends Tile {

	public TileDoor() {
		super(3);
	}

	@Override
	public Color getColor(TileState state) {
		return Color.green;
	}

	@Override
	public boolean onPlayerMove(TileState state, EntityPlayer player) {
		TileMetadataDoor m = (TileMetadataDoor) state.getMetadata();
		
		if (m.isLocked()) {
			// TODO: Implement door locking
			player.logMessage("It's locked.");
		} else {
			state.setTileType(Tile.stoneFloor);
			player.logMessage("You open the door!");
		}
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

}
