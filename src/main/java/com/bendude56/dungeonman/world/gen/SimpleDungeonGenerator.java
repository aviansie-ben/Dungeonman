package com.bendude56.dungeonman.world.gen;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.bendude56.dungeonman.GameInstance;
import com.bendude56.dungeonman.entity.EntitySoldier;
import com.bendude56.dungeonman.world.World;
import com.bendude56.dungeonman.world.WorldLocation;
import com.bendude56.dungeonman.world.gen.WorldFeature.DoorType;
import com.bendude56.dungeonman.world.gen.WorldFeature.WorldFeatureInfo;
import com.bendude56.dungeonman.world.tile.Tile;
import com.bendude56.dungeonman.world.tile.TileMetadataStairs;

/**
 * Benjamin C. Thomas
 * Computer Science 30
 * 2012/13 Semester 2
 * Centennial High School
 *
 * @author Benjamin C. Thomas
 */

public class SimpleDungeonGenerator extends WorldGenerator {
	
	private ArrayList<WorldLocation> possibleStairs = new ArrayList<WorldLocation>();
	private ArrayList<WorldLocation> possibleItems = new ArrayList<WorldLocation>();
	private ArrayList<WorldLocation> possibleMonsters = new ArrayList<WorldLocation>();

	public SimpleDungeonGenerator(World world) {
		super(world);
	}

	@Override
	public void generateLevel(int difficulty) {	
		// Start the generation algorithm with a 4x4 room in the center of the map
		generate(new WorldFeatureRoom(4, 4), new WorldLocation(world, world.getWidth() / 2, world.getHeight() / 2), DoorType.NONE, -1, 0);
		
		// Select locations for entry and exit stairs
		if (possibleStairs.size() < 2) {
			generateLevel(difficulty);
			return;
		}
		
		WorldLocation entry = possibleStairs.get(random.nextInt(possibleStairs.size()));
		possibleStairs.remove(entry);
		WorldLocation exit = possibleStairs.get(random.nextInt(possibleStairs.size()));
		
		// Set up the entry and exit stairs
		world.setEntryLocation(entry);
		world.setTileAndMetadata(entry, Tile.stairs, new TileMetadataStairs(true));
		world.setExitLocation(exit);
		world.setTileAndMetadata(exit, Tile.stairs, new TileMetadataStairs(false));
		
		// Generate ALL the items
		new KeyGenerator(world, possibleItems, random).generateAllKeys();
		new ItemPopulator(possibleItems, random).generateAllItems();
		
		// Add enemies
		for (WorldLocation l : possibleMonsters) {
			if (random.nextInt(2) == 0)
				world.addEntity(new EntitySoldier(l));
		}
	}
	
	public boolean generate(WorldFeature f, WorldLocation l, DoorType door, int orientation, int iteration) {
		WorldFeatureInfo info;
		
		if (f.checkLocation(l, orientation)) {
			info = f.generateAt(door, l, orientation, random);
			
			for (WorldLocation possibleLocation : info.possibleStairs)
				possibleStairs.add(possibleLocation);
			
			for (WorldLocation possibleLocation : info.possibleItems)
				possibleItems.add(possibleLocation);
			
			for (WorldLocation possibleLocation : info.possibleMonsters)
				possibleMonsters.add(possibleLocation);
			
			if (iteration < 7) {
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
			
			if (iteration < 3 && numCorridors == 0 && numRooms == 0) {
				numRooms = 1;
			}
		} else if (f instanceof WorldFeatureRoom) {
			numRooms = 0;
			numCorridors = random.nextInt(3) + ((iteration > 0) ? 0 : 1);
			
			if (iteration < 3 && numCorridors == 0) {
				numCorridors = 1;
			}
		} else {
			numRooms = 0;
			numCorridors = 0;
		}
		
		for (int i = 0; i < numRooms; i++) {
			DoorType door = DoorType.getRandomType(random);
			
			tryGenerate(new WorldFeatureRoom(random.nextInt(5) + 3, random.nextInt(5) + 3), (List<WorldLocation>) info.walls.clone(), (List<Integer>) info.wallOrientations.clone(), door, iteration);
		}
		
		for (int i = 0; i < numCorridors; i++) {
			DoorType door = (f instanceof WorldFeatureRoom) ? DoorType.getRandomType(random) : DoorType.NONE;
			
			tryGenerate(new WorldFeatureCorridor(random.nextInt(10) + 5), (List<WorldLocation>) info.walls.clone(), (List<Integer>) info.wallOrientations.clone(), door, iteration);
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
		while (true) {
			GameInstance.createNewGame(0, null);
			World w = GameInstance.getActiveInstance().getFloor(1);
			
			showDialog(w);
		}
	}
	
	public static void showDialog(World w) {
		BufferedImage img = new BufferedImage(w.getWidth(), w.getHeight(), BufferedImage.TYPE_INT_RGB);
		int[] p = new int[w.getWidth() * w.getHeight()];
		for (int y = 0; y < w.getHeight(); y++) {
			for (int x = 0; x < w.getWidth(); x++) {
				p[(y * w.getWidth()) + x] = w.getTile(x, y).getColor(w.getTileState(x, y)).getRGB();
			}
		}
		img.setRGB(0, 0, w.getWidth(), w.getHeight(), p, 0, w.getWidth());
		
		JOptionPane.showMessageDialog(null, null, "Generation Test", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(img.getScaledInstance(w.getWidth() * 2, w.getHeight() * 2, Image.SCALE_AREA_AVERAGING)));
	}

}
