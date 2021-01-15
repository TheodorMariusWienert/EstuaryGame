package pkgGamePiece;

import pkgEnum.AnimationState;

/**
 * 
 * Holds location as well as state of net for animation purposes
 * 
 * Created on 11/14/2019
 * 
 * @author Chandler
 */
public class Net extends GamePiece {
	private double theta;
	private int specialTime = 100;
	private int specialTimeMax = 100;
	private AnimationState state = AnimationState.Draw;

	/**
	 * 
	 * @param x double, initial x position of the net
	 * @param y double, initial y position of the net
	 */
	public Net(double x, double y) {
		super(x, y);
		theta = 0;
	}

	/**
	 * Updates the timer held within the net to help it switch its animation state.
	 * 
	 */
	public void updateSpecialTimer() {
		this.specialTime = specialTime - 1;
		if (specialTime == 0) {
			specialTime = specialTimeMax;
			this.state = AnimationState.Draw;
		}
	}

	/**
	 * returns the specialTime field
	 * 
	 * @return i specialTime - the value of the specialTime field.
	 */
	public int getSpecialTime() {
		return specialTime;
	}

	/**
	 * returns int, special time
	 * 
	 * 
	 * @param specialTime int the value to set the initial time field to.
	 * 
	 */
	public void setSpecialTime(int specialTime) {
		this.specialTime = specialTime;
	}

}
