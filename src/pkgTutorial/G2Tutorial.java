package pkgTutorial;

import java.util.ArrayList;

import pkgEnum.AnimationState;
import pkgEnum.Species;
import pkgGamePiece.Animal;
import pkgGamePiece.Fish;
import pkgModel.G2Model;
import pkgModel.GameStatsModel;

/**
 * create the objects necessary for the tutorial for game 2.
 * 
 * @author Matthew Created 11/9/2019
 */
public class G2Tutorial extends G2Model {

	private Boolean clickToMove = false;
	private Boolean capturedInvasive = false;
	private Boolean switchTool = false;
	private Boolean photoTaken = false;
	private int INIT_POS_DIV = 4;
	private static final int CAMERA = 1;
	private static final int NET = 0;

	private ArrayList<Boolean> tutorialChecklist = new ArrayList<Boolean>();

	/**
	 * G2Tutorial Constructor for the game2Tutorial objects
	 * 
	 * @param decision     boolean, used to tell the minigame which decision the
	 *                     player went with so it can impact the minigame.
	 * @param gModel       GameStatsModel, gameStatsModel to store scoring
	 *                     information between games
	 * @param currentTime  long, time in milliseconds that the game lasts
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
	 * @param crustHeight  double, height of the crustacean image, used in collision
	 *                     detection.
	 * @param tool         int, represents the tool that the player should start
	 *                     with
	 */
	public G2Tutorial(boolean decision, GameStatsModel gModel, long currentTime, double canvasWidth,
			double canvasHeight, double robotWidth, double robotHeight, double fishWidth, double fishHeight,
			double crustWidth, double crustHeight, int tool) {
		super(decision, gModel, currentTime, canvasWidth, canvasHeight, robotWidth, robotHeight, fishWidth, fishHeight,
				crustWidth, crustHeight, tool);

		clickToMove = false;
		capturedInvasive = false;
		switchTool = false;
		photoTaken = false;

		tutorialChecklist.add(clickToMove);
		tutorialChecklist.add(capturedInvasive);
		tutorialChecklist.add(switchTool);
		tutorialChecklist.add(photoTaken);

		animalList.clear();

		// Creates invasive fish first for net demo
		animalList.add(new Fish(canvasWidth / 2, canvasHeight / 2, Species.BLUECATFISH));
		setSlopeZero(animalList.get(0));
	}

	/**
	 * returns the tutorialChecklist field which contains a Boolean arrayList that
	 * has to be all true for the tutorial to end
	 * 
	 * @return the arrayList that contains all of the objectives that need to be
	 *         completed to end the tutorial
	 */
	public ArrayList<Boolean> getTutorialChecklist() {
		return tutorialChecklist;
	}

	/**
	 * sets both slopes of the animal object used as an argumetn to 0.
	 * 
	 * @param animal Animal object that both slopes (x and y) should be set to 0.
	 */
	public void setSlopeZero(Animal animal) {
		animal.setXIncr(0);
		animal.setYIncr(0);
	}

	/**
	 * spawn a non invasive species when called during the tutorial
	 */
	public void spawnNonInvasive() {
		animalList.get(0).resetOffScreen(canvasWidth, canvasHeight);
		animalList.get(0).setSpecies(Species.LARGEMOUTHBASS);
		animalList.get(0).setAnimationState(AnimationState.Draw);
	}

	/**
	 * updates the fish / crustacean animation states.
	 * 
	 * @param takeAction  boolean, determines if a photo or a net was swung
	 * @param ClickOnPosX the mouse position that the robot should move to
	 * @param ClickOnPosY the mouse position that the robot should move to
	 */
	@Override // needed lighter version of this method
	public void takeAction(boolean takeAction, double ClickOnPosX, double ClickOnPosY) {
		int index = -1;
		index = checkCollisions(ClickOnPosX, ClickOnPosY);
		if (index > -1 && robot.getSelectedTool() == CAMERA) { // If there was a collision and the selected tool is a
																// Camera
			if (!animalList.get(index).getSpecies().getIsInvasive()) { // Allows the action if the species is not
																		// invasive
				animalList.get(index).setAnimationState(AnimationState.Special);
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
	 * updates the tutorial checkList if objectives were completed. updates the
	 * robot position and the animal positions.
	 * 
	 * @param ClickOnPosX x position of mouse, where the robot moves to
	 * @param ClickOnPosY y position of mouse, where the robot moves to
	 * @param doAction    true if the robot is taking a picture or capturing
	 *                    something
	 * @param tool        determines the tool that is selected by the robot.
	 */
	public void update(double ClickOnPosX, double ClickOnPosY, boolean doAction, int tool) {

		super.update(ClickOnPosX, ClickOnPosY, doAction, tool, -1);

		if (!tutorialChecklist.get(2)) {
			setSlopeZero(animalList.get(0)); // prevents fish from moving
		}

		if (!tutorialChecklist.get(0)
				&& !(ClickOnPosX == canvasWidth / INIT_POS_DIV + getRobot().getCenterXMult() * robotWidth
						&& ClickOnPosY == canvasHeight / INIT_POS_DIV + getRobot().getCenterYMult() * robotHeight)) {
			tutorialChecklist.set(0, true); // clickToMove true;
		} else if (!tutorialChecklist.get(1) && animalList.get(0).getXLoc() != canvasWidth / 2) {
			tutorialChecklist.set(1, true);
			animalList.get(0).resetOffScreen(canvasWidth, canvasHeight);
		} else if (!tutorialChecklist.get(2) && tool == CAMERA) {
			tutorialChecklist.set(2, true);
			spawnNonInvasive();
		}

		if (!tutorialChecklist.get(3) && animalList.get(0).getAnimationState() == 2) {
			tutorialChecklist.set(3, true);
		} else if (tutorialChecklist.get(2)) {
			animalList.get(0).setSpecies(Species.LARGEMOUTHBASS); // In case goes off screen want to keep same species
		}

	}
}
