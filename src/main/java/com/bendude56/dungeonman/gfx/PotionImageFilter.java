package com.bendude56.dungeonman.gfx;

import java.awt.Color;
import java.awt.image.RGBImageFilter;

/**
 * @author Benjamin C. Thomas
 */

public class PotionImageFilter extends RGBImageFilter {
	private Color potionColor;
	
	public PotionImageFilter(Color potionColor) {
		this.potionColor = potionColor;
	}

	@Override
	public int filterRGB(int x, int y, int rgb) {
		if (rgb == Color.white.getRGB())
			return potionColor.getRGB();
		else
			return rgb;
	}

}
