package com.bendude56.dungeonman.world.gen;

import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.bendude56.dungeonman.world.Tile;
import com.bendude56.dungeonman.world.World;
import com.bendude56.dungeonman.world.WorldLocation;
import com.bendude56.dungeonman.world.gen.WorldFeature.DoorType;
import com.bendude56.dungeonman.world.gen.WorldFeature.WorldFeatureInfo;

public class SimpleDungeonGenerator extends WorldGenerator {

	public SimpleDungeonGenerator(World world) {
		super(world);
	}

	@Override
	public void generateLevel(int difficulty) {
		// Clear the map
		for (int y = 0; y < world.height; y++) {
			for (int x = 0; x < world.width; x++) {
				world.tiles[x][y] = Tile.wall;
			}
		}
		
		// Select a position
		generate(new WorldFeatureCorridor(random.nextInt(5) + 10), new WorldLocation(world, 50, 50), random.nextInt(4));
	}
	
	public boolean generate(WorldFeature f, WorldLocation l, int orientation) {
		WorldFeatureInfo info;
		
		if (f.checkLocation(l, orientation)) {
			info = f.generateAt(DoorType.NONE, l, orientation);
			
			if (random.nextInt(2) > 0) {
				System.out.println("GENERATION CONTINUE!");
				int wall = random.nextInt(info.walls.size());
				generate(new WorldFeatureCorridor(random.nextInt(5) + 5), info.walls.get(wall), info.wallOrientations.get(wall));
				
				wall = random.nextInt(info.walls.size());
				generate(new WorldFeatureCorridor(random.nextInt(5) + 5), info.walls.get(wall), info.wallOrientations.get(wall));
			}
			
			return true;
		} else {
			System.out.println("CANNOT GENERATE THERE!");
			return false;
		}
	}
	
	
	
	public static void main(String[] args) {
		int width = 100, height = 100;
		
		while (true) {
			World w = new World();
			w.width = width;
			w.height = height;
			w.tiles = new Tile[width][height];
			
			new SimpleDungeonGenerator(w).generateLevel(0);
			
			BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			int[] p = new int[width * height];
			for (int y = 0; y < height; y++) {
				for (int x = 0; x < width; x++) {
					p[(y * width) + x] = w.tiles[x][y].getColor().getRGB();
				}
			}
			img.setRGB(0, 0, width, height, p, 0, width);
			
			JOptionPane.showMessageDialog(null, null, "Generation Test", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(img.getScaledInstance(width * 4, height * 4, Image.SCALE_AREA_AVERAGING)));
		}
	}

}
