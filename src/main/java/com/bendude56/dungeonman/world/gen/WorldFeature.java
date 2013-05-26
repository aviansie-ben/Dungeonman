package com.bendude56.dungeonman.world.gen;

import java.util.ArrayList;
import java.util.Random;

import com.bendude56.dungeonman.world.WorldLocation;

public abstract class WorldFeature {
	public abstract boolean checkLocation(WorldLocation l, int orientation);
	public abstract WorldFeatureInfo generateAt(DoorType door, WorldLocation l, int orientation, Random random);
	
	public static class WorldFeatureInfo {
		public ArrayList<WorldLocation> walls = new ArrayList<WorldLocation>();
		public ArrayList<Integer> wallOrientations = new ArrayList<Integer>();
		public ArrayList<WorldLocation> possibleItems = new ArrayList<WorldLocation>();
		public ArrayList<WorldLocation> possibleStairs = new ArrayList<WorldLocation>();
		public ArrayList<WorldLocation> possibleMonsters = new ArrayList<WorldLocation>();
	}
	
	public enum DoorType {
		NONE, NORMAL, SECRET, LOCKED, SECRET_LOCKED
	}
}
