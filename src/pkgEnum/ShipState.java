package pkgEnum;

/**
 * 
 * sets the state of the player controlled ship to hurt or normal depending on
 * whether it collided or not.
 * 
 * @author Matthew
 */
public enum ShipState {
	NORMAL("normal"), HURT("hurt");

	private String name = null;

	/**
	 * sets the name of the name field
	 * 
	 * @author Matthew
	 * @param name - value that the name field should be set to.
	 * 
	 */
	private ShipState(String name) {
		this.name = name;
	}

	/**
	 * 
	 * gets the value in the name field
	 * 
	 * @author Matthew
	 * @return the value contained within the name field.
	 */
	public String getName() {
		return name;
	}
};
