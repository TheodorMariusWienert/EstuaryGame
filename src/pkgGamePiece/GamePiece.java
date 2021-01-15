package pkgGamePiece;

import pkgEnum.Direction;

/**
 *
 * Super class for all GamePieces, which are objects to be used in models and
 * passed to view to hold positions.
 * 
 * Created on 11/1/2019
 * 
 * @author Chandler and Matthew
 */
public abstract class GamePiece implements java.io.Serializable {
	protected double xloc;
	protected double yloc;
	protected double xIncr;
	protected double yIncr;

	Direction direction = Direction.EAST;

	/**
	 * Constructor for abstract class GamePiece
	 * 
	 * 
	 * @param X double, initial x location
	 * @param Y double, initial y location
	 */
	protected GamePiece(double X, double Y) {
		xloc = X;
		yloc = Y;
	}

	/**
	 * returns the xLocation of the PlayerChar object on the x-yplane
	 * 
	 * 
	 * @return the x location of the PlayerChar object on the x-y plane
	 */
	public double getXLoc() {
		return xloc;
	}

	/**
	 * returns the yLocation of the PlayerChar object on the x-y plane
	 * 
	 * 
	 * 
	 * @return the y location of the PlayerChar object on the x-y plane
	 * 
	 */
	public double getYLoc() {
		return yloc;
	}

	/**
	 * sets the xLoc value to the parameter
	 * 
	 * 
	 * @param X double, the x value that the xLoc should be set to.
	 *
	 */
	public void setX(double X) {
		xloc = X;
	}

	/**
	 * sets the yLoc value to the parameter
	 * 
	 * 
	 * 
	 * @param Y double, the y value that the yLoc should be set to.
	 */
	public void setY(double Y) {
		yloc = Y;
	}

	/**
	 * returns the GamePiece's direction field
	 * 
	 * 
	 * 
	 * @return returns the value direction field.
	 */
	public Direction getDirection() {
		return (this.direction);
	}

	/**
	 * sets the GamePiece's direction field
	 * 
	 * 
	 * 
	 * @param d Direction, a Direction enum that will define the gamePieces
	 *          Direction.
	 */
	public void setDirection(Direction d) {
		this.direction = d;
	}

	/**
	 * returns the xIncr of the PlayerChar object which defines movement on the x-y
	 * plane
	 * 
	 * *
	 * 
	 * @return the value the xLoc of the PlayerChar object is incremented using the
	 *         moveLeft and moveRight methods
	 */
	public double getXIncr() {
		return xIncr;
	}

	/**
	 * returns the yIncr of the PlayerChar object which defines movement on the x-y
	 * plane
	 * 
	 * 
	 * 
	 * @return the value the yLoc of the PlayerChar object is incremented using the
	 *         moveUp and moveDown methods
	 */
	public double getYIncr() {
		return yIncr;
	}
}
