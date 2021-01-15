package pkgGamePiece;

/**
 *
 * used to create G3Player objects in the game 3 Created on 11/1/2019
 * 
 * @author Stephen
 */

public class G3Player extends PlayerChar {

	/**
	 * Constructor for G3Player
	 * 
	 * 
	 * @param initX double, initial x location
	 * @param initY double, initial y location
	 * @param xIncr double, increment in x Direction
	 * @param yIncr double increment in y Direction
	 *
	 * 
	 */

	public G3Player(double initX, double initY, double xIncr, double yIncr) {
		super(initX, initY, xIncr, yIncr);
	}

	/**
	 * Places a probe within the maze. Created on 11/1/2019
	 * 
	 * @return boolean, indicates success or failure of probe placement.
	 *
	 * 
	 */

	public boolean placeProbe() {
		return true;
	}

}
