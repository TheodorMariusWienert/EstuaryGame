package pkgGamePiece;

import java.util.Random;

import pkgEnum.Species;
import pkgEnum.AnimationState;
import pkgEnum.Direction;

/**
 * 
 * Inherited by Fish and Crustacean sub classes to be used in G2Model Defines
 * update method, abstract methods, and other setters/getters Created on
 * 11/1/2019
 * 
 * @author Matthew and Chandler
 * 
 * 
 * 
 *
 * 
 */

public abstract class Animal extends Item {

	// Used to ensure that the fish can have some animations.
	private int specialTime = 100; // Used in conjunction with the animation state enum to allow the animations
									// like flash to occur for a short amount of ticks
	private AnimationState state = AnimationState.Draw;

	protected Species species;

	protected int upperBound4 = 5;
	protected int upperBound3 = 4;
	protected int upperBound2 = 3;
	protected int upperBound1 = 2;

	/**
	 * return the value of the specialTime field Created on 11/3/2019
	 * 
	 * @author Chandler Method
	 * @return the value of the special animation timer
	 */

	public int getSpecialTime() {
		return this.specialTime;
	}

	/**
	 * returns the ordinal value of the AnimationState enum
	 * 
	 * @return the ordinal() value of the AnimationState enum.
	 *
	 * 
	 */

	public int getAnimationState() {
		return state.ordinal();
	}

	/**
	 * sets the state field to the new AnimationState enum value. Created on
	 * 11/3/2019
	 * 
	 * @author Chandler Method
	 * @param newState the new AnimationState for the animal object
	 *
	 * 
	 */

	public void setAnimationState(AnimationState newState) {
		this.state = newState;
	}

	/**
	 * Adds randomness to the movement speed of the fish. Created on
	 * 
	 * 
	 * 
	 * @param X       double, initial x location
	 * @param Y       double, initial y location
	 * @param species Species of the fish - crustacean
	 *
	 * 
	 */

	public Animal(double X, double Y, Species species) {
		super(X, Y);
		this.species = species;
	}

	/**
	 * Abstract method that must be implemented to randomize slopes of animals when
	 * the go off screen.
	 * 
	 * 
	 * 
	 * 
	 */

	public abstract void randomizeSlope();

	/**
	 * reset any animal objects off screen so they can be different species.
	 * 
	 * @param canvasWidth  width of the canvas
	 * @param canvasHeight height of the canvas
	 */

	public abstract void resetOffScreen(double canvasWidth, double canvasHeight);

	/**
	 * updates the xloc and yloc attributes of the animal class
	 * 
	 * 
	 * 
	 */

	@Override
	public void update() {
		this.xloc -= xIncr;
		this.yloc -= yIncr;
		updateDirection();
		updateSpecialTimer();
	}

	/**
	 * Updates the timer held within the fish to help it switch its animation state.
	 * 
	 * 
	 * 
	 */

	public void updateSpecialTimer() {
		this.specialTime = specialTime - 1;
		if (specialTime == 0) {
			specialTime = 100;
			this.state = AnimationState.Draw;
		}
	}

	/**
	 * Updates the direction of the animal after it updates.
	 * 
	 * 
	 *
	 */
	public void updateDirection() {

		if (xIncr < 0) {
			direction = Direction.EAST;
		} else {
			direction = Direction.WEST;
		}

	}

	/**
	 * Returns the Species of the animal
	 * 
	 * @return Species, the species of the animal
	 *
	 */
	public Species getSpecies() {
		return species;
	}

	/**
	 * 
	 * Sets the Species of the animal
	 * 
	 * 
	 * 
	 * @param spec Species, the specie to set the species field to.
	 *
	 */
	public void setSpecies(Species spec) {
		species = spec;
	}

}
