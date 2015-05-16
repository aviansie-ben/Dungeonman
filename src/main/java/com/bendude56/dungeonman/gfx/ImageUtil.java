package com.bendude56.dungeonman.gfx;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * @author Benjamin C. Thomas
 */

public class ImageUtil {
    /**
     * Loads an image from within the jar file's resources.
     * 
     * @param resourceName The absolute path of the resource to be loaded.
     */
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
    
    /**
     * Colors a potion's sprite to match its color description.
     * 
     * @param original The original potion sprite to be colored.
     * @param color The color that the potion should be made to look like.
     * 
     * @return The modified potion sprite.
     */
    public static Image colorPotion(Image original, Color color) {
        return Toolkit.getDefaultToolkit().createImage(
                new FilteredImageSource(original.getSource(), new PotionImageFilter(color)));
    }
}
