package pkgEnum;

/**
 * Used for setting the direction of the animals in game 2
 * 
 * @author Matthew Enum
 *
 *
 */
public enum Direction {
	// 8 directions and a default "none" direction
	NORTH("north"), SOUTH("south"), EAST("east"), WEST("west"), NORTHEAST("northEast"), NORTHWEST("northWest"),
	SOUTHEAST("southEast"), SOUTHWEST("southWest"), NONE("none");

	private String name = null;

	/**
	 * sets the name field for the various values of the enum
	 * 
	 * @author Matthew
	 * @param name the name that the name field should be set to
	 * 
	 */
	private Direction(String name) {
		this.name = name;
	}

	/**
	 * gets the value of the name field
	 * 
	 * @author Matthew Method
	 * @return  the value of the name field.
	 * 
	 */
	public String getName() {
		return name;
	}
};