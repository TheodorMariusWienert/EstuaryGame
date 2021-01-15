package pkgGamePiece;

import pkgEnum.ShipState;

/**
 * 
 * used to create the controllable ship for the first game
 * 
 * @author Chandler and Matthew reated on 11/2/2019
 */

public class G1Player extends PlayerChar {

	public ShipState state = ShipState.NORMAL;

	/**
	 * Constructor for G1Player
	 * 
	 * 
	 * @param initX double, the value of the G1Player's initial x
	 * @param initY double, the value of the G1Player's initial y
	 * @param xIncr double, the value of the G1Player's xIncr
	 * @param yIncr double, the value of the G1Player's yIncr
	 *
	 * 
	 */

	public G1Player(double initX, double initY, double xIncr, double yIncr) {
		super(initX, initY, xIncr, yIncr);
	}

	/**
	 * overriden here so that the player cannot move left in game 1.
	 *
	 */
	@Override
	public void moveLeft() {
	}

	/**
	 * overriden here so that the player cannot move right in game 1.
	 *
	 */
	@Override
	public void moveRight() {
	}

	/**
	 * return the value of the state enum field of the G1Player object
	 * 
	 * @return ShipState, the value of the state field of the G1Player object
	 */
	public ShipState getState() {
		return state;
	}

	/**
	 * set the State of the Ship
	 * 
	 * @param state the value the state field should be set to.
	 */
	public void setState(ShipState state) {
		this.state = state;
	}

}