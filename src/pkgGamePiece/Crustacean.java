package pkgGamePiece;

import java.util.Random;

import pkgEnum.Species;

/**
 * Crustacean - Used to create the crustaceans in game 2 Created on 11/12/2019
 * 
 * @author Matthew
 * 
 */
public class Crustacean extends Animal {

	/**
	 * 
	 * 
	 * Intractable crustacean in G2, swim around and can be captured with net or
	 * picture taken with camera depending on species. Respawns as different
	 * crustacean when goes off screen
	 * 
	 * @param X       crustaceans initial X value
	 * @param Y       crustaceans initial Y value
	 * @param species crustaceans species
	 * 
	 * 
	 */

	public Crustacean(double X, double Y, Species species) {
		super(X, Y, species);
		yIncr = 0;
		Random rand = new Random();
		while (xIncr == 0)
			xIncr = rand.nextInt(upperBound4) - 3;
	}

	/**
	 * randomizes slope after crustacean goes offscreen
	 * 
	 * 
	 * 
	 */
	@Override
	public void randomizeSlope() {
		Random rand = new Random();
		if (rand.nextInt(2) == 1) {
			xIncr = (rand.nextInt(upperBound3) + 1); // Half up half down
		} else {
			xIncr = -(rand.nextInt(upperBound3) + 1); // Half up half down
		}
		this.species = Species.randomCrust();

	}

	/**
	 * resets crustacean to far off screen if it is captured. Will then be respawned
	 * closer to screen by G2Model
	 * 
	 * @param canvasWidth  double, width of canvas, used to set new X position
	 * @param canvasHeight double, height of canvas, used to set new Y position
	 */
	@Override
	public void resetOffScreen(double canvasWidth, double canvasHeight) {
		int xDir;
		Random rand = new Random();
		xDir = rand.nextInt(upperBound1);
		if (xDir == 1) {
			setX(canvasWidth * 3); // sets off screen in positive direction
		} else {
			setX(canvasWidth * -3); // sets off screen in negative direction
		}

		randomizeSlope();
	}

}
