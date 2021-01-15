package pkgGamePiece;

/**
 * 
 * 
 * Abstract class that all player character classes will inherit from. 
 * Created on 11/1/2019
 * 
 * @author Matthew
 */

public abstract class PlayerChar extends GamePiece {

	/**
	 * Constructor for abstract class PlayerChar
	 * 
	 * 
	 * 
	 * @param X     double, initial x location
	 * @param Y     double, initial y location
	 * @param xIncr double, value of PlayerChar's xIncr
	 * @param yIncr double, value of PlayerChar's yIncr
	 * 
	 */

	protected PlayerChar(double X, double Y, double xIncr, double yIncr) {
		super(X, Y);
		this.xIncr = xIncr;
		this.yIncr = yIncr;
	}

	/**
	 * causes the PlayerChar Chars yLoc to decrease, making it move up
	 */
	public void moveUp() {
		yloc = yloc - yIncr;
	}

	/**
	 * causes the PlayerChar Chars yLoc to increase, making it move down
	 * 
	 *
	 * 
	 */
	public void moveDown() {

		yloc = yloc + yIncr;

	}

	/**
	 * causes the PlayerChar xLoc to decrease , moving it left
	 * 
	 * 
	 */
	public void moveLeft() {
		xloc = xloc - xIncr;
	}

	/**
	 * causes the PlayerChar xLoc to increase, moving it right
	 * 
	 */
	public void moveRight() {
		xloc = xloc + xIncr;
	}

}
