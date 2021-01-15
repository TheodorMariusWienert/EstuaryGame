package pkgModel;

import java.util.ArrayList;

import java.util.Random;

import pkgEnum.Species;
import pkgEnum.AnimationState;
import pkgEnum.Direction;
import pkgGamePiece.Animal;
import pkgGamePiece.Crustacean;
import pkgGamePiece.Fish;
import pkgGamePiece.G2Player;

/**
 * used to handle the logic of game 2 Created on 11/1/2019
 * 
 * @author Chandler and Matthew
 * 
 */

public class G2Model extends Model {
	// --------------Collision dimensions--------------------------------------
	protected double canvasHeight;
	protected double canvasWidth;
	protected double robotHeight;
	protected double robotWidth;
	private double fishHeight;
	private double fishWidth;
	private double crustHeight;
	private double crustWidth;
	// -----------------------Game Lists + Player Character--------------------
	private int actionTimer;

	private boolean isPlaying = true;

	protected ArrayList<String> objectiveList = new ArrayList<String>();
	protected ArrayList<Animal> animalList = new ArrayList<Animal>();

	protected G2Player robot;
	// ------------------------------------------------------------------------------------------------------
	private GameStatsModel gModel;
	private double SCORE_ADD_ENV = 30; // Score to increment when removed from objective list
	private double SCORE_ADD_ECON = 20;
	private double ENV_BONUS = 90;
	private double ECON_BONUS = 70;
	// ----------------------------------------------------------------------------------------------------
	private Random rand = new Random(System.currentTimeMillis());
	private long timeRemaining; // Keeps track of time for timer in corner

	private final static int ACTION_TIME = 50;

	private final static double RESET_OFFSET = 0.1;

	private static final int CAMERA = 1;
	private static final int NET = 0;
	// ------------------------------------------------------------------------------------------------------

	/**
	 *
	 * Constructor of the G2Model class
	 * 
	 * 
	 * @param decision     boolean, used to tell the minigame which decision the
	 *                     player went with so it can impact the minigame.
	 * @param gModel       GameStatsModel, gameStatsModel to store scoring
	 *                     information between games
	 * @param currentTime  long, time in miliseconds that the game lasts
	 * @param canvasWidth  double, the canvas width of the javaFx window.
	 * @param canvasHeight double, the canvas height of the javaFx window.
	 * @param robotWidth   double, width of the robot image to be drawn, used in the
	 *                     collision detection.
	 * @param robotHeight  double, height of the robot image to be drawn, used in
	 *                     the collision detection.
	 * @param fishWidth    double, width of the fish image, used in collision
	 *                     detection.
	 * @param fishHeight   double, height of the fish image, used in collision
	 *                     detection.
	 * @param crustWidth   double, width of the crustacean image, used in collision
	 *                     detection.
	 * @param crustHeight  double, height of the crustacan image, used in collision
	 *                     detection.
	 * @param tool         int, represents the tool that the player should start
	 *                     with
	 * 
	 * 
	 */
	public G2Model(boolean decision, GameStatsModel gModel, long currentTime, double canvasWidth, double canvasHeight,
			double robotWidth, double robotHeight, double fishWidth, double fishHeight, double crustWidth,
			double crustHeight, int tool) {
		this.gModel = gModel;
		this.canvasWidth = canvasWidth;
		this.canvasHeight = canvasHeight;
		this.robotHeight = robotHeight;
		this.robotWidth = robotWidth;
		this.fishHeight = fishHeight;
		this.fishWidth = fishWidth;
		this.crustHeight = crustHeight;
		this.crustWidth = crustWidth;

		// getObjectiveList().add("Fish"); //Testing the objective list to see if it
		// will be checked off.
		// ----------------------------------------------------------
		objectiveList.add("Snakehead"); // Add the names of all of the fish to the objective list.
		objectiveList.add("Blue Catfish");
		objectiveList.add("Large Mouth Bass");
		objectiveList.add("Brook Trout");
		objectiveList.add("Blue Crab");
		objectiveList.add("Mitten Crab");
		this.robot = new G2Player(canvasWidth / 4, canvasHeight / 4, 5.0, 5.0, robotWidth, robotHeight); // Balance
		// Tutorial assumes /4 locations for robot initially
		robot.setSelectedTool(tool);
		if (decision) { // TODO figure out impact of decision, also figure out the spawn position of the
						// fish so it looks better.

			// Create a certain number of fish and none fish
			animalList.add(
					new Fish(rand.nextInt((int) canvasWidth), rand.nextInt((int) canvasHeight), Species.randomFish()));

			animalList.add(
					new Fish(rand.nextInt((int) canvasWidth), rand.nextInt((int) canvasHeight), Species.randomFish()));

			animalList.add(
					new Fish(rand.nextInt((int) canvasWidth), rand.nextInt((int) canvasHeight), Species.randomFish()));

			animalList.add(
					new Fish(rand.nextInt((int) canvasWidth), rand.nextInt((int) canvasHeight), Species.randomFish()));
			animalList.add(
					new Fish(rand.nextInt((int) canvasWidth), rand.nextInt((int) canvasHeight), Species.randomFish()));

			// ----------------------------------------------------------
			animalList.add(
					new Crustacean(rand.nextInt((int) canvasWidth), canvasHeight - crustHeight, Species.randomCrust()));
			animalList.add(
					new Crustacean(rand.nextInt((int) canvasWidth), canvasHeight - crustHeight, Species.randomCrust()));
			// ----------------------------------------------------------
			timeRemaining = currentTime; // In miliseconds

			this.actionTimer = ACTION_TIME; // Wait half a second.
			// -------------------------
		} else {
			animalList.add(
					new Fish(rand.nextInt((int) canvasWidth), rand.nextInt((int) canvasHeight), Species.randomFish()));

			animalList.add(
					new Fish(rand.nextInt((int) canvasWidth), rand.nextInt((int) canvasHeight), Species.randomFish()));

			animalList.add(
					new Fish(rand.nextInt((int) canvasWidth), rand.nextInt((int) canvasHeight), Species.randomFish()));

			animalList.add(
					new Fish(rand.nextInt((int) canvasWidth), rand.nextInt((int) canvasHeight), Species.randomFish()));
			animalList.add(
					new Fish(rand.nextInt((int) canvasWidth), rand.nextInt((int) canvasHeight), Species.randomFish()));

			// ----------------------------------------------------------
			animalList.add(
					new Crustacean(rand.nextInt((int) canvasWidth), canvasHeight - crustHeight, Species.randomCrust()));
			animalList.add(
					new Crustacean(rand.nextInt((int) canvasWidth), canvasHeight - crustHeight, Species.randomCrust()));
		}

	}

	/**
	 * Removes fish from the fish list if they have been caught/a picture has been
	 * taken of them, also calls the updateFishList method. Allows the player robot
	 * to move
	 * 
	 * 
	 * 
	 * @param clickOnPosX double where the Player clicked
	 * @param clickOnPosY double where the Player clicked
	 * @param doAction    boolean, a boolean value that is true if a picture was
	 *                    taken and false if one was not.
	 * @param tool        int, the tool that the player has selected
	 * @param currentTime long, the time left in the game
	 * 
	 * @return boolean, if true the game is still occuring, if false the game is
	 *         over.
	 * 
	 */
//TODO work on the organization of this method. 
	public boolean update(double clickOnPosX, double clickOnPosY, boolean doAction, int tool, long currentTime) {
		if (actionTimer < ACTION_TIME) {
			actionTimer += 1;
		}

		timeRemaining = currentTime;

		if (timeRemaining < 0) {
			isPlaying = false;
		}

		this.updateAnimalList();

		robot.update(clickOnPosX, clickOnPosY);

		takeAction(doAction, clickOnPosX, clickOnPosY);
		robot.setSelectedTool(tool);

		return isPlaying;

		// TODO update scoring
	}

	/**
	 * iterates through animalList and calls update on each Animal. Also calls
	 * randomizeSlope() if the animal goes off screen.
	 * 
	 * 
	 * 
	 */
	public void updateAnimalList() {
		Random rand = new Random();
		for (Animal animal : this.animalList) {
			if (animal instanceof Fish) {
				if (animal.getXLoc() > canvasWidth + fishWidth) {
					animal.setX(0 - fishWidth + RESET_OFFSET);
					animal.setY(rand.nextInt((int) canvasHeight));
					animal.randomizeSlope();

				}
				if (animal.getXLoc() < 0 - fishWidth) {
					animal.setX(canvasWidth + fishWidth - RESET_OFFSET);
					animal.setY(rand.nextInt((int) canvasHeight));
					animal.randomizeSlope();
				}
				if (animal.getYLoc() > canvasHeight + fishHeight) {
					animal.setY(0 - fishHeight + RESET_OFFSET);
					animal.randomizeSlope();

				}
				if (animal.getYLoc() < 0 - fishHeight) {
					animal.setY(canvasHeight + fishHeight - RESET_OFFSET);
					animal.randomizeSlope();

				}
			} else {
				if (animal.getXLoc() > canvasWidth + crustWidth) {
					animal.setX(0 - crustWidth + RESET_OFFSET);
					animal.randomizeSlope();

				}
				if (animal.getXLoc() < 0 - crustWidth) {
					animal.setX(canvasWidth + crustWidth - RESET_OFFSET);
					animal.randomizeSlope();
				}
			}
			animal.update();
		}
	}

	/**
	 * returns robot to obtain state and location in view
	 * 
	 * 
	 * @return G2Player, robot contains location and state of player
	 *
	 * 
	 */

	public G2Player getRobot() {
		return robot;
	}

	/**
	 * 
	 * checks to see if there is an animal where the mouse clicked and if it is
	 * within the needed hitbox. If collision occurs returns index of ArrayList
	 * animal corresponding to animal which was acted on. If not, returns -1.
	 * 
	 * 
	 * @param clickOnPosX xPosition of mouse click
	 * @param clickOnPosY yPosition of mouse click
	 * @return index int corresponding to index in ArrayList of Animals where
	 *         collision occured
	 * 
	 */

	// Checks if the fish is within the bounds of the robot image. Coordinates of
	// the image is always from the topLeft corner.

	public int checkCollisions(double clickOnPosX, double clickOnPosY) {

		double animalWidth;
		double animalHeight;
		double animalXPos;
		double animalYPos;

		boolean clickAnimal;
		boolean inHitBox;
		boolean vertBox;

		if (animalList.get(0) instanceof Fish) {
			animalWidth = fishWidth;
			animalHeight = fishHeight;
		} else {
			animalWidth = crustWidth;
			animalHeight = crustHeight;
		}

		for (Animal animal : animalList) { // loop through each animal
			animalXPos = animal.getXLoc();
			animalYPos = animal.getYLoc();
			clickAnimal = false;
			inHitBox = false;
			vertBox = false;

			// Ensures the animal was actually clicked
			if (((animalXPos < clickOnPosX) && (animalXPos + animalWidth > clickOnPosX))
					&& ((animalYPos < clickOnPosY) && (animalYPos + animalHeight > clickOnPosY))) {
				clickAnimal = true;
			}

			if ((animalYPos < robot.getYLoc() + robotHeight) && (animalYPos + animalHeight > robot.getYLoc())) {
				vertBox = true;

			}

			if ((robot.getDirection() == Direction.EAST) && (((animalXPos < robot.getXLoc() + robotWidth * 3 / 2)
					&& (animalXPos + animalWidth > robot.getXLoc() + robotWidth * 1 / 4)) && vertBox)) {
				inHitBox = true;
			}

			else if ((robot.getDirection() == Direction.WEST) && (((animalXPos < robot.getXLoc() + robotWidth * 1 / 2)
					&& (animalXPos + animalWidth > robot.getXLoc() - robotWidth * 1 / 4)) && vertBox)) {
				inHitBox = true;
			}

			if (inHitBox && clickAnimal) {
				return animalList.indexOf(animal); // Returns index that collides with
			}
		}
		return -1; // Returns -1 if no collision occurs
	}

	/**
	 * 
	 * returns the Animals list
	 * 
	 * 
	 * @return the list of Animals
	 */

	public ArrayList<Animal> getAnimalList() {
		return (this.animalList);
	}

	/**
	 * returns the index of the animal that the action is successfully acted on.
	 * Successfully taken if action is taken when the robot is colliding with the
	 * animal.
	 * 
	 * 
	 * @param takeAction  true if the player is taking a picture, or capturing and
	 *                    animal
	 * @param clickOnPosX the xposition of the mouse after a click
	 * @param clickOnPosY the y position of the mouse after a click
	 *
	 * 
	 */
	// TODO fix/think about with new animal subclassses
	public void takeAction(boolean takeAction, double clickOnPosX, double clickOnPosY) {
		// TODO update Scoring

		int index = -1;
		boolean toRemove = false;
		if (takeAction && actionTimer == ACTION_TIME) {
			actionTimer = 0;
			index = checkCollisions(clickOnPosX, clickOnPosY);
		}

		if (index > -1 && robot.getSelectedTool() == CAMERA) { // If there was a collision and the selected tool is a
																// Camera TODO ADD AN ENUM

			if (!animalList.get(index).getSpecies().getIsInvasive()) { // Allows the action if the species is not
																		// invasive
				// FOR THE TOOL
				animalList.get(index).setAnimationState(AnimationState.Special);
				toRemove = true;
			}
		}

		if (index > -1 && robot.getSelectedTool() == NET) {

			if (animalList.get(index).getSpecies().getIsInvasive()) { // Allows the action if the species is invasive.
				toRemove = true;
			}
		}

		if (index != -1 && toRemove && getObjectiveList().contains(animalList.get(index).getSpecies().getName())) {
			getObjectiveList().remove(animalList.get(index).getSpecies().getName());
			if (animalList.get(index).getSpecies().getIsInvasive() == true) {
				gModel.updateEnvirmScore(SCORE_ADD_ENV);
			} else {
				gModel.updateEconScore(SCORE_ADD_ECON);
			}
			if (getObjectiveList().size() == 0) {
				isPlaying = false;
				gModel.updateEnvirmScore(ENV_BONUS);
				gModel.updateEconScore(ECON_BONUS);
			}
		}
		if (index > -1 && robot.getSelectedTool() == NET && animalList.get(index).getSpecies().getIsInvasive()) { // Reset
																													// off
																													// screen
																													// if
																													// netted,
																													// goes
																													// after
																													// remoce
			animalList.get(index).resetOffScreen(canvasWidth, canvasHeight);
		}
	}

	/**
	 * returns the objectiveList
	 * 
	 * 
	 * 
	 * @return the list that contains all the names of the animals that need to be
	 *         capture and have their picture taken.
	 * 
	 */

	public ArrayList<String> getObjectiveList() {
		return objectiveList;
	}

	/**
	 * sets the objectiveList
	 * 
	 * @param objectiveList the ArrayList of strings that the objectiveList is  set to.
	 *
	 * 
	 */
	public void setObjectiveList(ArrayList<String> objectiveList) {
		this.objectiveList = objectiveList;
	}

	/**
	 * returns the remaining time for the game
	 * 
	 * 
	 * 
	 * @return timeRemaining, time to display in the timer
	 * 
	 */
	public long getTimeRemaining() {
		return timeRemaining;
	}

}
