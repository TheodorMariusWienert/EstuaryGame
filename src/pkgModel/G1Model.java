package pkgModel;

import java.util.ArrayList;
import java.util.Random;

import pkgEnum.ObstacleState;
import pkgEnum.ShipState;
import pkgGamePiece.G1Player;
import pkgGamePiece.Item;
import pkgGamePiece.Obstacle;

/**
 *  Model used for the game 1  logic
 *   Created on 11/1/2019
 * @author  Matthew and Chandler
 */

public class G1Model extends Model {

	protected int objectScrollSpeed = 9;

	protected double canvasHeight;
	protected double canvasWidth;
	protected double shipHeight;
	protected double shipWidth;
	protected int rockHeight;
	protected int rockWidth;
	protected int canHeight;
	protected int canWidth;
	private GameStatsModel gModel;

	protected ArrayList<Obstacle> obstacleList = new ArrayList<Obstacle>();
	protected G1Player ship;
	private Obstacle lastSpawnedObstacle;

	// TODO Balance
	private double SCORE_DEC_ENV = -2; // Score to decrement when bad collision occurs
	private double SCORE_DEC_ECON = -1;
	private double SCORE_ADD_ENV = 30; // Score to increment when good collision ocurs
	private double SCORE_ADD_ECON = 10;

	private static final double SHIP_OFFSET = 35;
	private static final double X_INCREASE_FOR_PLAYER = 8;
	private static final double Y_INCREASE_FOR_PLAYER = 8;
	private static final double PLAYER_START_POS = 100;

	/**
	 *  Constructor for G1Model which takes
	 *         dimensions of the canvas and image and generates the ship based off
	 *         of these conditions.
	 *
	 * 
	 * @param decision      boolean, result of decision to build port
	 * @param gModel        GameStatsModel, the gameStatsModel that stores the
	 *                     scoring data between games
	 * @param canvasWidth   double, width of canvas
	 * @param canvasHeight  double, height of canvas
	 * @param shipWidth     double, the width of the ship objects
	 * @param shipHeight    double, the height of the ship objects
	 * @param rockWidth     int, the width of the rock objects
	 * @param rockHeight    int, the height of the rock objects
	 * @param canWidth      double, height of can objects
	 * @param canHeight     int, width of can objects
	 *
	 * 
	 */

	public G1Model(boolean decision, GameStatsModel gModel, double canvasWidth, double canvasHeight, double shipWidth,
			double shipHeight, int rockWidth, int rockHeight, int canWidth, int canHeight) {
		this.gModel = gModel;

		this.canvasWidth = canvasWidth;
		this.canvasHeight = canvasHeight;
		this.shipHeight = shipHeight;
		this.shipWidth = shipWidth;
		this.rockHeight = rockHeight;
		this.rockWidth = rockWidth;
		this.canHeight = canHeight;
		this.canWidth = canWidth;

		Random rand = new Random();
		lastSpawnedObstacle = new Obstacle(-500, -canvasHeight * 2, objectScrollSpeed, ObstacleState.ROCK);

		if (decision) { // IF Decides Yes for Port expansion
			this.ship = new G1Player(PLAYER_START_POS, canvasHeight / 2, X_INCREASE_FOR_PLAYER, Y_INCREASE_FOR_PLAYER);
			// Added by Chandler on 11/4/19
			// Want rockHeight to be int of canvasHeight
			obstacleList.add(new Obstacle(-canvasWidth, -canvasHeight, objectScrollSpeed, ObstacleState.ROCK));
			obstacleList.add(new Obstacle(-canvasWidth, -canvasHeight, objectScrollSpeed, ObstacleState.ROCK));
			obstacleList.add(new Obstacle(-canvasWidth, -canvasHeight, objectScrollSpeed, ObstacleState.CAN));
			obstacleList.add(new Obstacle(-canvasWidth, -canvasHeight, objectScrollSpeed, ObstacleState.SHIP));
			obstacleList.add(new Obstacle(-canvasWidth, -canvasHeight, objectScrollSpeed, ObstacleState.ROCK));
			obstacleList.add(new Obstacle(-canvasWidth, -canvasHeight, objectScrollSpeed, ObstacleState.SHIP));
			obstacleList.add(new Obstacle(-canvasWidth, -canvasHeight, objectScrollSpeed, ObstacleState.ROCK));
			obstacleList.add(new Obstacle(-canvasWidth, -canvasHeight, objectScrollSpeed, ObstacleState.SHIP));
			obstacleList.add(lastSpawnedObstacle);
			// -------------------------
		} else {
			this.ship = new G1Player(PLAYER_START_POS, canvasHeight / 2, X_INCREASE_FOR_PLAYER, Y_INCREASE_FOR_PLAYER);
			obstacleList.add(new Obstacle(-canvasWidth, -canvasHeight, objectScrollSpeed, ObstacleState.ROCK));
			obstacleList.add(new Obstacle(-canvasWidth, -canvasHeight, objectScrollSpeed, ObstacleState.ROCK));
			obstacleList.add(new Obstacle(-canvasWidth, -canvasHeight, objectScrollSpeed, ObstacleState.CAN));
			obstacleList.add(new Obstacle(-canvasWidth, -canvasHeight, objectScrollSpeed, ObstacleState.SHIP));
			obstacleList.add(new Obstacle(-canvasWidth, -canvasHeight, objectScrollSpeed, ObstacleState.ROCK));
			obstacleList.add(new Obstacle(-canvasWidth, -canvasHeight, objectScrollSpeed, ObstacleState.SHIP));
		}
	}

	/**
	 * returns the Obstacle list *
	 *         
	 * @return  ArrayList, list of Obstacles that are recycled for the
	 *         game.
	 *
	 * 
	 */

	public ArrayList<Obstacle> getObstacleList() {
		return (this.obstacleList);
	}

	/**
	 * return the Ship object which  is the player controlled object
	 * 
	 * @return  G1Player, ship, the object that is representing the player
	 *         character.
	 */

	public G1Player getPlayer() {
		return ship;
	}

	/**
	 *  Prevents the player  from exiting the screen on the top or the bottom.
	 */
	public void keepPlayerInBounds() {
		if (ship.getYLoc() < 0) {
			ship.setY(0);
		}
		if (ship.getYLoc() > canvasHeight - shipHeight - SHIP_OFFSET) {
			ship.setY(canvasHeight - shipHeight - SHIP_OFFSET);
		}
	}

	/**
	 *  checks for collision  and updates the colliding objects if one occurs
	 * 
	 * 
	 */

	public void checkCollisions() {
		ship.setState(ShipState.NORMAL);

		for (Item obs : obstacleList) { // loop through each obstacle
			// Checks see if any obstacle is touching the ship
			Obstacle tmp = (Obstacle) obs;
			boolean collision = false;

			double obstacleHeight = 0;
			double obstacleWidth = 0;

			if (tmp.getObsType().getName().equals("rock")) {
				obstacleHeight = rockHeight;
				obstacleWidth = rockWidth;
			}
			if (tmp.getObsType().getName().equals("ship")) {
				obstacleHeight = shipHeight;
				obstacleWidth = shipWidth;
			}
			if (tmp.getObsType().getName().equals("can")) {
				obstacleHeight = canHeight;
				obstacleWidth = canWidth;
			}

			if (((tmp.getXLoc() < ship.getXLoc() + shipWidth) && (tmp.getXLoc() + obstacleWidth > ship.getXLoc())) // Checks
																													// if
																													// the
																													// fish
																													// is
																													// within
																													// the
																													// bounds
																													// of
																													// the
																													// robot
																													// image.
																													// Coordinates
																													// of
																													// the
																													// image
																													// is
																													// always
																													// from
																													// the
																													// topLeft
																													// corner.
					&& ((tmp.getYLoc() < ship.getYLoc() + shipHeight)
							&& (tmp.getYLoc() + obstacleHeight > ship.getYLoc() + 0.5 * shipHeight))) {
				collision = true;
			}
			if (collision && tmp.getObsType().getName().equals("can")) {
				tmp.setX(-canvasWidth);
				gModel.updateEconScore(SCORE_ADD_ECON);
				gModel.updateEnvirmScore(SCORE_ADD_ENV);
				// TODO increment score
			} else if (collision) {
				gModel.updateEconScore(SCORE_DEC_ECON);
				gModel.updateEnvirmScore(SCORE_DEC_ENV);
				ship.setState(ShipState.HURT);
			}

		}
	}

	/**
	 * iterates     through obstacleList and calls update on each item.
	 * 
	 *
	 * 
	 */

	public void updateObstacleList() {

		for (Obstacle obst : this.obstacleList) {
			obst.update();
			if (obst.getXLoc() < -rockWidth) {
				recycleObstacle(obst);
			}
		}
	}

	/**
	 *  randomly  respawns the obstacles using a gaussian distribution with a mean of
	 *         1.2 and a standard deviation of 3 so they appear randomly parallel to
	 *         the x - axis with random timer
	 * @param obst  obstacle that is being recycled
	 */
	public void recycleObstacle(Obstacle obst) {
		Random rand = new Random();
		Obstacle tmp = lastSpawnedObstacle;
		double std = 3;
		double mean = 1.2;

		double multiplierDelay = Math.abs(rand.nextGaussian()) * std + mean; // Gaussian with mean 1.2 and std 3

		if (tmp.getXLoc() < canvasWidth - rockWidth * multiplierDelay) {

			obst.setX(canvasWidth + rockWidth);
			obst.setY(rockHeight * rand.nextInt((int) canvasHeight / rockHeight));
			lastSpawnedObstacle = obst;
		}

	}

	/**
	 * used to move the player up and down, update obstacle positions, call the
	 * checkCollisions method and keep the player in bounds using keepPlayerInBounds
	 * method. Created on 11/1/2019
	 * 
	 * @param yIncr int, corresponding to key input for movement up/down
	 */

	public void update(int yIncr) {
		this.updateObstacleList();
		if (yIncr == 1) {
			ship.moveUp();
		} else if (yIncr == -1) {
			ship.moveDown();
		}
		checkCollisions();
		keepPlayerInBounds();
	}

}