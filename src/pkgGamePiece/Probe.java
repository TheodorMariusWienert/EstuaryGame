
package pkgGamePiece;

/**
 * 
 * used to create probe objects in Game 3 for the maze. Created on 11/1/2019
 * 
 * @author Stephen
 * 
 *
 * 
 */
public class Probe extends Item {

	double timer = 3000;
	int status = 0;

	/**
	 *
	 * Constructor for Probe class
	 * 
	 * 
	 * @param X double, initial x location
	 * @param Y double, initial y location
	 *
	 * 
	 */
	public Probe(double X, double Y) {
		super(X, Y);
		// Initialize status to working (constructor is what is called when it is
		// placed)
		this.status = 1;
	}

	/**
	 * updates the probe timer field, and sets it to the different statuses
	 * depending on the value of the timer
	 * 
	 */

	@Override
	public void update() {

		// if probe is active, count down timer, and if it hits zero set it to done
		if (this.status == 1) {
			this.timer -= 10;

			if (this.timer == 0) {
				this.status = 2;
			}

		}

	}

	/**
	 * get the timer field of the Probe object.
	 * 
	 * @return : double, amount of time since the probe was placed
	 */

	public double getTime() {
		return timer;

	}

	/**
	 * return the value of the probes getStatus field
	 * 
	 * @return the value of the status field.
	 */
	public int getStatus() {
		return this.status;
	}

	/**
	 * called to pick up the probe if the probe is in the done collecting data
	 * status. Status == 2 is when the probe is done collecting data
	 * 
	 * @return true if the probe was successfully picked up because it was done
	 *         collecting data, or false if the probe was not done collecting data
	 */
	public boolean pickUp() {
		// If the probe is ready, change its status to retrieved
		if (this.status == 2) {
			this.status = 3;
			return true;
		}
		return false;
	}

	/**
	 * returns a String representation of the Probe object
	 * 
	 * @return the string representation of the Probe Object.
	 */
	public String toString() {
		// Print status for debug purposes
		return (this.getXLoc() + " " + this.getYLoc() + " " + this.timer);
	}

}
