package pkgTutorial;

import java.util.ArrayList;

import pkgEnum.ObstacleState;
import pkgGamePiece.Obstacle;
import pkgModel.G1Model;
import pkgModel.GameStatsModel;

/**
 * 
 * 
 * used to handle the logic for the game 1 tutorial.
 * 
 * @author Stephen Created 11/12/2019
 */
public class G1Tutorial extends G1Model {

	private Boolean movedVertically = false;
	private Boolean avoidedRock = false;
	private Boolean gotCan = false;
	private Boolean avoidedShip = false;

	private ArrayList<Boolean> tutorialChecklist = new ArrayList<Boolean>();

	private static final int TUTORIAL_SCROLL_SPEED = 5;

	/**
	 * Constructor for the G1Tutorial objects
	 * 
	 * @param gModel       gameStats Model to be passed to super class
	 * @param canvasWidth  width of canvas
	 * @param canvasHeight height of canvas
	 * @param shipWidth    width of ship image
	 * @param shipHeight   height of ship image
	 * @param rockWidth    width of rock image
	 * @param rockHeight   height of rock image
	 * @param canWidth     width of can image
	 * @param canHeight    height of can image
	 * 
	 */
	public G1Tutorial(GameStatsModel gModel, double canvasWidth, double canvasHeight, double shipWidth,
			double shipHeight, int rockWidth, int rockHeight, int canWidth, int canHeight) {
		super(true, gModel, canvasWidth, canvasHeight, shipWidth, shipHeight, rockWidth, rockHeight, canWidth,
				canHeight); // Uses the superClass constructor
		obstacleList = new ArrayList<Obstacle>(); // Changes the obstacle list to contain the custom class
													// TutorialObstacle
		objectScrollSpeed = TUTORIAL_SCROLL_SPEED;
		obstacleList.add(new TutorialObstacle(-canvasWidth, -canvasHeight, objectScrollSpeed, ObstacleState.ROCK));
		this.getPlayer().setY(canvasHeight / 2); // Sets the player to the middle of the screen

		tutorialChecklist.add(movedVertically); // Adds the tutorial objectives to the checklist that must be completed
												// before the game starts
		tutorialChecklist.add(avoidedRock);
		tutorialChecklist.add(gotCan);
		tutorialChecklist.add(avoidedShip);
	}

	/**
	 * returns the value of the tutorialCheckList field
	 * 
	 * @return ArrayList of booleans, the tutorial checklist
	 * 
	 */
	public ArrayList<Boolean> getTutorialCheckList() {
		return this.tutorialChecklist;
	}

	/**
	 * Method - getMovedVertically() Purpose - returns the value of the
	 * movedVertically boolean field
	 * 
	 * @return movedVeritcally boolean representing if the boolean has moved
	 *         vertically
	 */

	public boolean getMovedVertically() {
		return this.movedVertically;
	} // Getters for the tutorial objective list variables

	/**
	 * returns the value of the avoidedRock boolean field
	 * 
	 * @return if movedVertically boolean
	 */
	public boolean getAvoidedRock() {
		return this.avoidedRock;
	}

	/**
	 * returns the value of the gotCan boolean value
	 * 
	 * @return if avoidedRock boolean
	 */
	public boolean getGotCan() {
		return this.gotCan;
	}

	/**
	 * return the value of the avoidedShip boolean value
	 * 
	 * @return if gotCan boolean
	 */
	public boolean getAvoidedShip() {
		return this.avoidedShip;
	}

	/**
	 * sets up the moveVertically tutorial by setting the player to the middle of
	 * the screen.
	 */
	public void moveVerticallyTutorial() { // Resets the player to the middle of the screen
		this.getPlayer().setY(canvasHeight / 2);

	}

	/**
	 * sets up the avoidRockTutorial by spawning a rock and setting it to the middle
	 * of the screen.
	 */
	public void avoidRockTutorial() { // Called when the avoid rock tutorial is started
		avoidedRock = false;
		TutorialObstacle tmpObstacle = (TutorialObstacle) this.getObstacleList().get(0);
		tmpObstacle.setX(this.canvasWidth);
		tmpObstacle.setY(this.canvasHeight / 2);
		tmpObstacle.setCollision(false);
	}

	/**
	 * sets up the avoid ship tutorial by spawning a rock and setting it to the
	 * middle of the screen.
	 */

	public void avoidShipTutorial() { // Called when the avoid ship tutorial is started
		avoidedShip = false;
		TutorialObstacle tmpObstacle = (TutorialObstacle) this.getObstacleList().get(0);
		tmpObstacle.setX(this.canvasWidth);
		tmpObstacle.setY(this.canvasHeight / 2);
		tmpObstacle.setObsType(ObstacleState.SHIP);

		tmpObstacle.setCollision(false);
	}

	/**
	 * sets up the get can tutorial by spawning a can and setting it to the middle
	 * of the screen.
	 */

	public void getCanTutorial() { // called when the get can tutorial is started
		gotCan = false;
		TutorialObstacle tmpObstacle = (TutorialObstacle) this.getObstacleList().get(0);
		tmpObstacle.setX(this.canvasWidth);
		tmpObstacle.setY(this.canvasHeight / 2);
		tmpObstacle.setObsType(ObstacleState.CAN);

		tmpObstacle.setCollision(false);
	}

	/**
	 * updates the player position, checks to make sure if the player is done with
	 * each section of the tutorial and updates the objects so the game progresses
	 * 
	 * @param yIncr value determines whether the ship moves up or down.
	 */
	public void update(int yIncr) {

		this.updateObstacleList();

		if (yIncr == 1) {
			ship.moveUp();
		} else if (yIncr == -1) {
			ship.moveDown();
		}
		keepPlayerInBounds();

		if (this.getPlayer().getYLoc() != canvasHeight / 2 && !movedVertically) { // IF the player hasn't pressed the
																					// arrow keys to move nothing else
																					// will occur
			this.movedVertically = true;
			tutorialChecklist.set(0, true);
			avoidRockTutorial(); // Starts the avoid the rock tutorial
		}

		if (movedVertically) {
			TutorialObstacle tmp = (TutorialObstacle) this.getObstacleList().get(0);
			int obstacleHeight;
			int obstacleWidth;

			if (tmp.getObsType().getName().equals("rock")) { // Assigns the obstacle dimensions based off of what type
																// of obstacle it is ROCK
				obstacleHeight = rockHeight;
				obstacleWidth = rockWidth;
			}
			if (tmp.getObsType().getName().equals("ship")) { // SHIP OBSTACLE DIMENSIONS
				obstacleHeight = (int) shipHeight;
				obstacleWidth = (int) shipWidth;
			} else { // CAN OBSTACLE DIMENSIONS
				obstacleHeight = canHeight;
				obstacleWidth = canWidth;
			}

			tmp.update(this.getPlayer(), obstacleHeight, obstacleWidth, shipHeight, shipWidth);

			// If there is no collision, the rock is behind the player, and the obstacle
			// type is a rock, the player has successfully avoided a rock
			if (tmp.getXLoc() < this.getPlayer().getXLoc() && !tmp.getCollision()
					&& tmp.getObsType().getName().equals("rock")) {
				tutorialChecklist.set(1, true);
				avoidedRock = true;
			}
			// If there is no collision, the ship is behind the player, and the obstacle
			// type is a ship, the player has successfully avoided a ship
			if (tmp.getXLoc() < this.getPlayer().getXLoc() && !tmp.getCollision()
					&& tmp.getObsType().getName().equals("ship")) {
				tutorialChecklist.set(3, true);
				avoidedShip = true;
			}
			// If there is a collision, and the obstacle type is a can, the player has
			// successfully collected a can.
			if (tmp.getCollision() && tmp.getObsType().getName().equals("can")) {
				tutorialChecklist.set(2, true);
				gotCan = true;
			}

		}

	}

	/**
	 * will respawn the ship, can, and rock if the player crashes into them so they
	 * can practice dodging it
	 * 
	 * @param obst obstacle to be respawned
	 */
	@Override
	public void recycleObstacle(Obstacle obst) {
		if (movedVertically) { // Executes if the ship has moved.
			super.recycleObstacle(obst);

			if (!avoidedRock) { // Will respawn a rock if they haven't completed the rock tutorial
				avoidRockTutorial();
			}

			else if (!avoidedShip) { // Will respawn a ship if they haven't completed the avoidShip tutorial
				avoidShipTutorial();
			}

			else if (!gotCan) { // Will respawn a can if they haven't completed the collect can Tutorial.
				getCanTutorial();
			}

		}
	}

}
