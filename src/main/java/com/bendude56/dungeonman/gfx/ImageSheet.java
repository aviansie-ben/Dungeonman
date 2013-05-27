package com.bendude56.dungeonman.gfx;

import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 * Benjamin C. Thomas
 * Computer Science 30
 * 2012/13 Semester 2
 * Centennial High School
 *
 * @author Benjamin C. Thomas
 */

public class ImageSheet {
	private Image img;
	private int tileWidth, tileHeight;
	
	public ImageSheet(Image img, int tileWidth, int tileHeight) {
		this.img = img;
		this.tileWidth = tileWidth;
		this.tileHeight = tileHeight;
	}
	
	public Image getImage() {
		return img;
	}
	
	public Image getImage(int x, int y) {
		return ((BufferedImage)img).getSubimage(x * tileWidth, y * tileHeight, tileWidth, tileHeight);
	}
	
	public int getTileWidth() {
		return tileWidth;
	}
	
	public int getTileHeight() {
		return tileHeight;
	}
}
