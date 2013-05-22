package com.bendude56.dungeonman.world.gen;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.List;

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
		generate(new WorldFeatureRoom(4, 4), new WorldLocation(world, 50, 50), DoorType.NONE, -1, 0);
	}
	
	public boolean generate(WorldFeature f, WorldLocation l, DoorType door, int orientation, int iteration) {
		WorldFeatureInfo info;
		
		if (f.checkLocation(l, orientation)) {
			info = f.generateAt(door, l, orientation);
			
			if (iteration < 10) {
				generateChildren(f, info, iteration);
			}
			
			return true;
		} else {
			return false;
		}
	}
	
	@SuppressWarnings("unchecked")
	public void generateChildren(WorldFeature f, WorldFeatureInfo info, int iteration) {
		int numRooms, numCorridors;
		
		if (f instanceof WorldFeatureCorridor) {
			numRooms = (random.nextInt(3) > 0) ? 1 : 0;
			numCorridors = random.nextInt((((WorldFeatureCorridor)f).corridorLength / 3) + 1);
		} else if (f instanceof WorldFeatureRoom) {
			numRooms = 0;
			numCorridors = random.nextInt(3) + ((iteration > 0) ? 0 : 1);
		} else {
			numRooms = 0;
			numCorridors = 0;
		}
		
		for (int i = 0; i < numRooms; i++) {
			tryGenerate(new WorldFeatureRoom(4, 4), (List<WorldLocation>) info.walls.clone(), (List<Integer>) info.wallOrientations.clone(), DoorType.NONE, iteration);
		}
		
		for (int i = 0; i < numCorridors; i++) {
			tryGenerate(new WorldFeatureCorridor(10), (List<WorldLocation>) info.walls.clone(), (List<Integer>) info.wallOrientations.clone(), DoorType.NONE, iteration);
		}
	}
	
	public void tryGenerate(WorldFeature f, List<WorldLocation> locations, List<Integer> orientations, DoorType door, int iteration) {
		while (locations.size() > 0) {
			int location = random.nextInt(locations.size());
			
			if (generate(f, locations.get(location), door, orientations.get(location), iteration + 1)) {
				return;
			} else {
				locations.remove(location);
				orientations.remove(location);
			}
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
