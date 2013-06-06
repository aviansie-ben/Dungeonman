package com.bendude56.dungeonman.entity;

/**
 * Benjamin C. Thomas
 * Computer Science 30
 * 2012/13 Semester 2
 * Centennial High School
 *
 * @author Benjamin C. Thomas
 */

public class EntityStats {
	private int magic;
	private int strength;
	private int defense;
	private int agility;
	private int endurance;
	private int intelligence;
	
	private int baseMaxHp;
	
	public EntityStats(int magic, int strength, int defense, int agility,
			int endurance, int intelligence, int baseMaxHp) {
		this.magic = magic;
		this.strength = strength;
		this.defense = defense;
		this.agility = agility;
		this.endurance = endurance;
		this.intelligence = intelligence;
		this.baseMaxHp = baseMaxHp;
	}

	public int getMagic() {
		return magic;
	}

	public void setMagic(int magic) {
		this.magic = magic;
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public int getDefense() {
		return defense;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

	public int getAgility() {
		return agility;
	}

	public void setAgility(int agility) {
		this.agility = agility;
	}

	public int getEndurance() {
		return endurance;
	}

	public void setEndurance(int endurance) {
		this.endurance = endurance;
	}

	public int getIntelligence() {
		return intelligence;
	}

	public void setIntelligence(int intelligence) {
		this.intelligence = intelligence;
	}
	
	public int getBaseMaxHp() {
		return baseMaxHp;
	}

	public void setBaseMaxHp(int baseMaxHp) {
		this.baseMaxHp = baseMaxHp;
	}
	
	public int calculateMaxHp() {
		return this.baseMaxHp + this.endurance * 5 + this.strength * 3;
	}
	
	public int calculateDodgeChance() {
		if (this.agility == 0)
			return 500;
		else
			return (int)(500 / Math.pow(this.agility, 2));
	}
	
	public int calculateOutgoingDamage(int damage) {
		return damage + (int)(((double)damage / 30) * this.strength);
	}
	
	public int calculateIncomingDamage(int damage) {
		return Math.max((int)(damage - ((double)damage / 40) * this.defense), 0);
	}
	
	public int calculateOutgoingDeviation(int damage) {
		return (int)(((double)damage / 10) * this.strength);
	}
	
	public int calculateIncomingDeviation(int damage, int deviation) {
		return deviation - (int)(((double)damage / 15) * this.defense);
	}
	
}
