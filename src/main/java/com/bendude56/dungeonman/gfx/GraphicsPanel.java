package com.bendude56.dungeonman.gfx;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import com.bendude56.dungeonman.DebugCheats;
import com.bendude56.dungeonman.GameInstance;
import com.bendude56.dungeonman.entity.Entity;
import com.bendude56.dungeonman.world.tile.Tile;
import com.bendude56.dungeonman.world.tile.TileState;

/**
 * @author Benjamin C. Thomas
 */

public class GraphicsPanel extends JPanel {
	private static final long	serialVersionUID	= 0L;
	
	public Image img;
	public int centerX, centerY;
	
	public GraphicsPanel() {
		this.addComponentListener(new ComponentListener() {
			@Override
			public void componentResized(ComponentEvent e) {
				img = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
				GraphicsPanel.this.drawGameWorld();
				GraphicsPanel.this.repaint();
			}
			
			@Override
			public void componentMoved(ComponentEvent e) {
			}
			
			@Override
			public void componentShown(ComponentEvent e) {
			}
			
			@Override
			public void componentHidden(ComponentEvent e) {
			}
		});
	}
	
	/**
	 * Draws the active game world and any tiles or entities visible within it.
	 */
	public void drawGameWorld() {
		int viewpointX = (centerX * (Tile.TILE_WIDTH + 1)) + Tile.TILE_WIDTH / 2 - getWidth() / 2;
		int viewpointY = (centerY * (Tile.TILE_HEIGHT + 1)) + Tile.TILE_HEIGHT / 2 - getHeight() / 2;
		
		if (img == null) img = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
		
		Graphics g = img.getGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, img.getWidth(null), img.getHeight(null));
		
		if (GameInstance.getActiveInstance() != null) {
			for (int y = viewpointY / (Tile.TILE_HEIGHT + 1); y <= (viewpointY + img.getHeight(null)) / (Tile.TILE_HEIGHT + 1); y++) {
				if (y >= 0 && y < GameInstance.getActiveWorld().getHeight()) {
					for (int x = viewpointX / (Tile.TILE_WIDTH + 1); x <= (viewpointX + img.getWidth(null)) / (Tile.TILE_WIDTH + 1); x++) {
						if (x >= 0 && x < GameInstance.getActiveWorld().getWidth()) {
							TileState state = GameInstance.getActiveWorld().getTileState(x, y);
							
							if (GameInstance.getActiveWorld().isTileKnown(x, y) || DebugCheats.xRay) {
								state.getTileType().render(g, x * (Tile.TILE_WIDTH + 1) - viewpointX, y * (Tile.TILE_HEIGHT + 1) - viewpointY, state);
								
								if (!GameInstance.getActiveWorld().isTileKnown(x, y)) {
									g.setColor(new Color(0, 0, 0, 230));
									g.fillRect(x * (Tile.TILE_WIDTH + 1) - viewpointX, y * (Tile.TILE_HEIGHT + 1) - viewpointY, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);
								} else if (!GameInstance.getActiveWorld().isTileVisible(x, y)) {
									g.setColor(new Color(0, 0, 0, 200));
									g.fillRect(x * (Tile.TILE_WIDTH + 1) - viewpointX, y * (Tile.TILE_HEIGHT + 1) - viewpointY, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);
								}
							}
						}
					}
				}
			}
			
			for (Entity e : GameInstance.getActiveWorld().getEntities(viewpointX / (Tile.TILE_WIDTH + 1), viewpointY / (Tile.TILE_HEIGHT + 1), (viewpointX + img.getWidth(null)) / (Tile.TILE_WIDTH + 1), (viewpointY + img.getHeight(null)) / (Tile.TILE_HEIGHT + 1))) {
				if (e.getLocation().world.isTileVisible(e.getLocation()) || DebugCheats.xRay)
						e.render(g, e.getLocation().x * (Tile.TILE_WIDTH + 1) - viewpointX, e.getLocation().y * (Tile.TILE_HEIGHT + 1) - viewpointY);
			}
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if (img != null)
			g.drawImage(img, 0, 0, null);
	}

}
