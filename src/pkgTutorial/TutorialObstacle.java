package pkgTutorial;

import pkgEnum.ObstacleState;
import pkgGamePiece.G1Player;
import pkgGamePiece.Obstacle;

/**
 * used to modify the obstacles so they work for the tutorial
 * 
 * @author Theo
 *  Created 11/8/2019
 *
 */
public class TutorialObstacle extends Obstacle {

	private boolean collision;

	/**
	 * Constructor for the tutorialObstacle objects.
	 * 
	 * @param X     X position
	 * @param Y     Y position
	 * @param xIncr X slope
	 * @param obs   Obstacle Type
	 */
	public TutorialObstacle(double X, double Y, int xIncr, ObstacleState obs) {
		super(X, Y, xIncr, obs);
		boolean collision = false;
	}

	/**
	 *  update the position of the Tutorial
	 * Obstacle and determine if there is a collision. Sets the collision field to
	 * true
	 * 
	 * @param ship            player controleld ship
	 * @param obstacleHeight  obstacle types height
	 * @param obstacleWidth   obstacle types width
	 * @param shipHeight      height of the player ship
	 * @param shipWidth       width of the player ship
	 */
	public void update(G1Player ship, int obstacleHeight, int obstacleWidth, double shipHeight, double shipWidth) {
		if (ship.getState().getName().equals("hurt")) {
			this.collision = true;
		}

		if (((this.getXLoc() < ship.getXLoc() + shipWidth) && (this.getXLoc() + obstacleWidth > ship.getXLoc()))
				&& ((this.getYLoc() < ship.getYLoc() + shipHeight)
						&& (this.getYLoc() + obstacleHeight > ship.getYLoc() + 0.5 * shipHeight))) {
			collision = true;
		}
	}

	/**
	 * returns the value of the collision field
	 * 
	 * @return true if the object collided false if it didn't
	 */
	public boolean getCollision() {
		return this.collision;
	}

	/**
	 * sets the collision field to the argument value
	 * 
	 * @param collision value to set the collision field to.
	 */
	public void setCollision(boolean collision) {
		this.collision = collision;
	}

}
