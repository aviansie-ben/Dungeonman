package com.bendude56.dungeonman.item;

/**
 * Benjamin C. Thomas
 * Computer Science 30
 * 2012/13 Semester 2
 * Centennial High School
 *
 * @author Benjamin C. Thomas
 */

public class ItemMetadata {
	
	/**
	 * Makes an exact copy of this metadata. Modifying this copy MUST NOT
	 * affect the original.
	 */
	@Override
	public ItemMetadata clone() {
		return new ItemMetadata();
	}
	
	@Override
	public boolean equals(Object o) {
		return o.getClass() == ItemMetadata.class;
	}
	
}
