package com.bendude56.dungeonman.gfx;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageUtil {
	public static Image loadImage(String resourceName) {
		try {
			return ImageIO.read(ImageUtil.class.getResource(resourceName));
		} catch (IOException e) {
			return new BufferedImage(0, 0, BufferedImage.TYPE_INT_ARGB);
		}
	}
	
	public static ImageSheet loadImageSheet(String resourceName, int tileWidth, int tileHeight) {
		return new ImageSheet(loadImage(resourceName), tileWidth, tileHeight);
	}
	
	public static Image colorPotion(Image original, Color color) {
		// TODO: Color potions
		return original;
	}
}
