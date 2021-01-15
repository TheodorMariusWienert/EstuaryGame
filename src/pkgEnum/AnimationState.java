package pkgEnum;

/**
 * 
 * Used to animate the states of the game2 animals.
 * 
 * @author Chandler
 * 
 */
public enum AnimationState {
	// Draw is just the default animal is moving around and just animating
	// Hide was used to make them disappear when caught but may be obsolete.
	// Special was used for animations like the camera flash.
	Draw("Draw"), Hide("Hide"), Special("Special");

	private String name = null;

	/**
	 * sets the name field.
	 * 
	 * @author - Matthew Method
	 * @param name name for the specific values of the enum
	 * 
	 */
	private AnimationState(String name) {
		this.name = name;
	}

	/**
	 *  gets the name field
	 * @author Matthew Method
	 * @return  the value of the name field
	 * 
	 */
	
	public String getName() {
		return name;
	}
};
