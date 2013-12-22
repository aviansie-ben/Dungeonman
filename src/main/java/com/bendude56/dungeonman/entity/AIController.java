package com.bendude56.dungeonman.entity;

import java.util.Random;

import com.bendude56.dungeonman.world.WorldLocation;
import com.bendude56.dungeonman.world.tile.TileDoor;

/**
 * @author Benjamin C. Thomas
 */

public class AIController {
	
	/**
	 * Calculate the damage that should be done when an entity attacks another
	 * entity.
	 * 
	 * @param attacker The entity which is performing the attack
	 * @param atackee The entity that is being attacked
	 * 
	 * @return The amount of damage that should be done to the attacked entity
	 */
	public static int calculateAttackPower(EntityAlive attacker, EntityAlive atackee) {
		int baseDamage = atackee.calculateIncomingDamage(attacker.calculateOutgoingDamage());
		int deviation = atackee.calculateIncomingDeviation(baseDamage, attacker.calculateOutgoingDeviation(baseDamage));
		
		if (deviation > 0)
			baseDamage += new Random().nextInt(deviation);
		
		return Math.max(0, baseDamage);
	}
	
	/**
	 * Checks whether one location is visible from another.
	 * 
	 * @param l1 The first location to check
	 * @param l2 The second location to check
	 * 
	 * @return True if visibility is unobscured, false if an opaque tile is
	 *         blocking the line of sight
	 */
	public static boolean checkVisibility(WorldLocation l1, WorldLocation l2) {
		int x1 = l1.x * 32 + 16;
		int y1 = l1.y * 32 + 16;
		int x2 = l2.x * 32 + 16;
		int y2 = l2.y * 32 + 16;
		
		// Special case for vertical lines
		if (x1 == x2) {
			for (int y = Math.min(y1, y2); y <= Math.max(y1, y2); y += 32) {
				WorldLocation l = new WorldLocation(l1.world, (int)(x1 / 32), (int)(y / 32));
				if (!l1.world.getTile(l).isTransparent()) {
					if (!(l1.world.getTile(l) instanceof TileDoor && (l1.equals(l) || l2.equals(l))))
						return false;
				}
			}
		} else {
			// Ensure that x1 < x2
			if (x1 > x2) {
				int temp = x1;
				x1 = x2;
				x2 = temp;
				
				temp = y1;
				y1 = y2;
				y2 = temp;
			}
			
			double slope = (double)(y2 - y1) / (double)(x2 - x1);
			
			// Check when x = x1 + 16 + 32n
			for (int x = x1 + 16; x <= x2; x += 32) {
				WorldLocation l;
				int y = (int)(y1 + (x - x1) * slope);
				
				l = new WorldLocation(l1.world, (int)(x / 32), (int)(y / 32));
				if (!l1.world.getTile(l).isTransparent()) {
					if (!(l1.world.getTile(l) instanceof TileDoor && (l1.equals(l) || l2.equals(l))))
						return false;
				}
				
				l = new WorldLocation(l1.world, (int)(x / 32) - 1, (int)(y / 32));
				if (!l1.world.getTile(l).isTransparent()) {
					if (!(l1.world.getTile(l) instanceof TileDoor && (l1.equals(l) || l2.equals(l))))
						return false;
				}
				
				if (y % 32 == 0) {
					l = new WorldLocation(l1.world, (int)(x / 32), (int)(y / 32) - 1);
					if (!l1.world.getTile(l).isTransparent()) {
						if (!(l1.world.getTile(l) instanceof TileDoor && (l1.equals(l) || l2.equals(l))))
							return false;
					}
					
					l = new WorldLocation(l1.world, (int)(x / 32) - 1, (int)(y / 32) - 1);
					if (!l1.world.getTile(l).isTransparent()) {
						if (!(l1.world.getTile(l) instanceof TileDoor && (l1.equals(l) || l2.equals(l))))
							return false;
					}
				}
			}
			
			// Do not check for straight horizontal lines
			if (slope != 0) {
				double deltaX = Math.abs(16 / slope);
				
				// Check when y = y1 + 16 + 32n
				for (int x = x1; x <= x2; x += deltaX) {
					WorldLocation l;
					int y = (int)(y1 + (x - x1) * slope);
					
					l = new WorldLocation(l1.world, (int)(x / 32), (int)(y / 32));
					if (!l1.world.getTile(l).isTransparent()) {
						if (!(l1.world.getTile(l) instanceof TileDoor && (l1.equals(l) || l2.equals(l))))
							return false;
					}
					
					if (x % 32 == 0) {
						l = new WorldLocation(l1.world, (int)(x / 32) - 1, (int)(y / 32));
						if (!l1.world.getTile(l).isTransparent()) {
							if (!(l1.world.getTile(l) instanceof TileDoor && (l1.equals(l) || l2.equals(l))))
								return false;
						}
					}
					
					if (y % 32 == 0) {
						l = new WorldLocation(l1.world, (int)(x / 32), (int)(y / 32) - 1);
						if (!l1.world.getTile(l).isTransparent()) {
							if (!(l1.world.getTile(l) instanceof TileDoor && (l1.equals(l) || l2.equals(l))))
								return false;
						}
						
						if (x % 32 == 0) {
							l = new WorldLocation(l1.world, (int)(x / 32) - 1, (int)(y / 32) - 1);
							if (!l1.world.getTile(l).isTransparent()) {
								if (!(l1.world.getTile(l) instanceof TileDoor && (l1.equals(l) || l2.equals(l))))
									return false;
							}
						}
					}
				}
			}
		}
		
		return true;
	}
	
	/**
	 * Gets the distance between two locations.
	 * 
	 * @param l1 The first location to check
	 * @param l2 The second location to check
	 */
	public static int getDistance(WorldLocation l1, WorldLocation l2) {
		return (int)Math.ceil(Math.sqrt(Math.pow(l1.x - l2.x, 2) + Math.pow(l1.y - l2.y, 2)));
	}
	
	/**
	 * Uses the AI to make an entity move towards a specific target
	 * 
	 * @param moving The entity which should be moved
	 * @param target The target towards which the entity should move
	 */
	public static void moveTowards(EntityEnemy moving, WorldLocation target) {
		// TODO: Implement pathfinding
		
		if (moving.getLocation().x > target.x) {
			moving.doMove(moving.getLocation().adjustLocation(-1, 0));
		} else if (moving.getLocation().x < target.x) {
			moving.doMove(target = moving.getLocation().adjustLocation(1, 0));
		} else if (moving.getLocation().y > target.y) {
			moving.doMove(target = moving.getLocation().adjustLocation(0, 1));
		} else if (moving.getLocation().y < target.y) {
			moving.doMove(target = moving.getLocation().adjustLocation(0, -1));
		}
	}
}
