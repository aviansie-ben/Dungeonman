package com.bendude56.dungeonman.gfx;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Benjamin C. Thomas
 * Computer Science 30
 * 2012/13 Semester 2
 * Centennial High School
 *
 * @author Benjamin C. Thomas
 */

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
		return Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(original.getSource(), new PotionImageFilter(color)));
	}
}
