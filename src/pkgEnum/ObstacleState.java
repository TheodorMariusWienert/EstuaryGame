package pkgEnum;

/**
 * 
 * sets the states of the obstacles in game 1
 * 
 * @author Matthew
 */
public enum ObstacleState {
	ROCK("rock"), SHIP("ship"), CAN("can");

	private String name = null;

	/**
	 * sets the name field of the enum
	 * 
	 * @author Matthew
	 * 
	 * @param name the value that the name field should be set to
	 */
	private ObstacleState(String name) {
		this.name = name;
	}

	/**
	 * returns the value of the name
	 *         field
	 * @author Matthew 
	 *@return  the value of the name field.
	 * 
	 */
	public String getName() {
		return name;
	}
};
