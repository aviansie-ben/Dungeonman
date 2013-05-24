package com.bendude56.dungeonman;

public class GameInstance {
	private int nextEntityId = 1;
	
	public int generateEntityId() {
		return nextEntityId++;
	}
}
