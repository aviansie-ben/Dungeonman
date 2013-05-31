package com.bendude56.dungeonman.entity;

import com.bendude56.dungeonman.world.WorldLocation;

public class AIController {
	public static int calculateAttackPower(EntityAlive attacker, EntityAlive atackee) {
		return atackee.getStats().calculateIncomingDamage(attacker.getStats().calculateOutgoingDamage(10));
	}
	
	public static boolean checkVisibility(WorldLocation l1, WorldLocation l2) {
		int x1 = l1.x * 32 + 16;
		int y1 = l1.y * 32 + 16;
		int x2 = l2.x * 32 + 16;
		int y2 = l2.y * 32 + 16;
		
		// Avoid dividing by zero when possible
		if (x1 == x2) {
			if (y2 < y1) {
				int temp = x1;
				x1 = x2;
				x2 = temp;
				
				temp = y1;
				y1 = y2;
				y2 = temp;
			}
			
			for (int y = y1 + 16; y < y2; y += 32) {
				if (!l1.world.getTile(x1 / 32, y / 32).isTransparent()) {
					return false;
				}
			}
		} else {
			if (x2 < x1) {
				int temp = x1;
				x1 = x2;
				x2 = temp;
				
				temp = y1;
				y1 = y2;
				y2 = temp;
			}
			
			double slope = (y2 - y1) / (x2 - x1);
			
			for (int x = x1 + 16; x < x2; x += 32) {
				if (!l1.world.getTile((int)(x / 32), (int)((slope * (x - x1) + y1) / 32)).isTransparent()) {
					return false;
				}
			}
			
			// Avoid dividing by zero when possible
			if (slope != 0) {
				double deltaX = Math.abs(16 / slope);
				
				for (double x = x1 + deltaX; x < x2; x += 2 * deltaX) {
					if (slope > 0) {
						if (!l1.world.getTile((int)(x / 32), (int)((slope * (x - x1) + y1 - 16) / 32)).isTransparent()) {
							return false;
						}
					} else {
						if (!l1.world.getTile((int)(x / 32), (int)((slope * (x - x1) + y1 + 16) / 32)).isTransparent()) {
							return false;
						}
					}
				}
			}
		}
		
		return true;
	}
}
