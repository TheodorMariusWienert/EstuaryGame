package pkgGamePiece;

import java.util.Random;

import pkgEnum.Species;

/**
 * 
 * 
 * Intractable fish in G2, swim around and can be captured with net or picture
 * taken with camera depending on species. Respawns as different fish when goes
 * off screen
 * 
 * Created on 11/12/2019
 * 
 * @author- Matthew
 */
public class Fish extends Animal {

	/**
	 * acts as the constructor for the fish class
	 * 
	 * @param X       double, the fish's initial x
	 * @param Y       double, the fish's initial y
	 * @param species Species, the fish's initial species
	 * 
	 */
	public Fish(double X, double Y, Species species) {
		super(X, Y, species);

		Random rand = new Random();
		while (xIncr == 0) {// I hate when xSlope is zero it looks funky so I changed it
			xIncr = rand.nextInt(upperBound4) - 2; // Half up half down
			yIncr = rand.nextInt(upperBound4) - 2; // Half right, half left
		}
		updateDirection();
	}

	/**
	 * randomizes slope after fish goes offscreen
	 * 
	 * Created on 11/12/2019
	 * 
	 */
	@Override
	public void randomizeSlope() {
		Random rand = new Random();
		if (this.xIncr > 0) {
			xIncr = rand.nextInt(upperBound3) + 1; // Half up half down
		} else {
			xIncr = -1 * (rand.nextInt(upperBound3) + 1);
		}
		if (this.yIncr > 0) {
			yIncr = rand.nextInt(upperBound2) + 1; // Half up half down
		} else {
			yIncr = -1 * (rand.nextInt(upperBound3) + 1);
		}

		this.species = Species.randomFish();

	}

	/**
	 * resets Fish to far off screen if it is captured. Will then be respawned
	 * closer to screen by G2Model
	 * 
	 * 
	 * @param canvasWidth  double, width of canvas, used to set new X position
	 * @param canvasHeight double, height of canvas, used to set new Y position
	 * 
	 */
	@Override
	public void resetOffScreen(double canvasWidth, double canvasHeight) {
		int xDir;
		int yDir;
		Random rand = new Random();
		xDir = rand.nextInt(upperBound1);
		yDir = rand.nextInt(upperBound1);
		if (xDir == 1) {
			setX(canvasWidth * 3); // sets off screen in positive direction
		} else {
			setX(canvasWidth * -3); // sets off screen in negative direction
		}
		if (yDir == 1) {
			setY(canvasHeight * 3); // sets off screen in positive direction
		} else {
			setY(canvasHeight * -3); // sets off screen in negative direction
		}
		randomizeSlope();
	}
}
