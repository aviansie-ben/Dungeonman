package com.bendude56.dungeonman.world.tile;

import java.awt.Color;
import java.awt.Graphics;

import com.bendude56.dungeonman.entity.Entity;
import com.bendude56.dungeonman.entity.EntityPlayer;
import com.bendude56.dungeonman.item.ItemStack;

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
			ItemStack i = player.getInventory().getKey(m.getKeyId());
			
			if (i != null) {
				player.getInventory().removeItem(i);
				state.setTileType(Tile.stoneFloor);
				player.logMessage("You unlock the door!");
			} else {
				player.logMessage("It's locked.");
			}
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

	@Override
	public boolean canItemAlgorithmPass() {
		return false;
	}

}
