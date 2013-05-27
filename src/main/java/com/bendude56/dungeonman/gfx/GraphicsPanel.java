package com.bendude56.dungeonman.gfx;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import com.bendude56.dungeonman.GameInstance;
import com.bendude56.dungeonman.entity.Entity;
import com.bendude56.dungeonman.world.WorldLocation;
import com.bendude56.dungeonman.world.tile.TileState;

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
	
	public void drawGameWorld() {
		int viewpointX = (centerX * 33) + 16 - getWidth() / 2;
		int viewpointY = (centerY * 33) + 16 - getHeight() / 2;
		
		if (img == null) img = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
		
		Graphics g = img.getGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, img.getWidth(null), img.getHeight(null));
		
		if (GameInstance.getActiveInstance() != null) {
			for (int y = viewpointY / 33; y <= (viewpointY + img.getHeight(null)) / 33; y++) {
				if (y >= 0 && y < GameInstance.getActiveWorld().getHeight()) {
					for (int x = viewpointX / 33; x <= (viewpointX + img.getWidth(null)) / 33; x++) {
						if (x >= 0 && x < GameInstance.getActiveWorld().getWidth()) {
							g.setColor(GameInstance.getActiveWorld().getTile(x, y).getColor(new TileState(new WorldLocation(GameInstance.getActiveWorld(), x, y))));
							g.fillRect(x * 33 - viewpointX, y * 33 - viewpointY, 32, 32);
						}
					}
				}
			}
			
			for (Entity e : GameInstance.getActiveWorld().getEntities(viewpointX / 33, viewpointY / 33, (viewpointX + img.getWidth(null)) / 33, (viewpointY + img.getHeight(null)) / 33)) {
				Image i = e.getDrawImage();
				
				if (i != null) {
					g.drawImage(i, e.getLocation().x * 33 - viewpointX, e.getLocation().y * 33 - viewpointY, null);
				}
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
