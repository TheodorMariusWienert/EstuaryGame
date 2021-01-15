package pkgGamePiece;

import pkgEnum.ObstacleState;

/**
 * 
 * Used for the obstacles in minigame 1, the rocks and the ships. Created on
 * 11/1/2019
 * 
 * @author Matthew and Chandler
 *
 */
public class Obstacle extends Item {

	private ObstacleState obsType;

	/**
	 * Constructor for Animal class
	 * 
	 * 
	 * @param X     double, initial x location
	 * @param Y     double, initial y location
	 * @param xIncr int, x increment
	 * @param obs   ObstacleState, the obstacleState enum that defines what type of
	 *              obstacle the object is
	 *
	 * 
	 */
	public Obstacle(double X, double Y, int xIncr, ObstacleState obs) {
		super(X, Y);
		this.xIncr = xIncr;
		this.obsType = obs;
	}

	/**
	 * updates the x location of the obstacles by decrementing the xIncr.
	 * 
	 * 
	 */

	@Override
	public void update() {
		this.xloc = this.xloc - xIncr;
	}

	/**
	 * sets the obstacle type of the Obstacle object.
	 * 
	 * @param obs ObstacleState - the value of the ObstacleState enum for the
	 *            Obstacle enum.
	 * 
	 */
	public void setObsType(ObstacleState obs) {
		obsType = obs;
	}

	/**
	 * returns the value of the obsType enum.
	 * 
	 * @return ObstacleState obsType, the value of the obsType enum field.
	 */

	public ObstacleState getObsType() {
		return obsType;
	}

}