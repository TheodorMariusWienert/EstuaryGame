package pkgModel;

import java.util.ArrayList;
import java.util.Collections;
import pkgGamePiece.*;

/**
 * used to handle the logic of the 3rd minigame. Created on Created 11/1/2019
 * 
 * @author Stephen Moss
 */

public class G3Model extends Model {

	private int matrixDimX;
	private int matrixDimY;
	private G3Player playerChar;
	private int[][] mazeMatrix;

	private double canvasWidth;
	private double canvasHeight;
	private double gridWidth;
	private double gridHeight;
	private int projectedX;
	private int projectedY;
	private int completedProbes = 0;
	protected int numProbes = 3;
	pkgEnum.Direction direction;

	private boolean finishedFlag = false;
	private ArrayList<Probe> probeList;

	private static final int X_INCREASE = 5;
	private static final int Y_INCREASE = 5;
	private static final int NUMBER_OF_PROBES_YES = 3;// If they decide to increase funding
	private static final int NUMBER_OF_PROBES_NO = 2;

	/**
	 * 
	 * constructor for G3Model objects
	 * 
	 * @param width       double - width of canvas
	 * @param height      double - height of canvas
	 * @param matrixDimX  int - matrix dimension in X direction
	 * @param matrixDimY  int - matrix dimension in Y direction
	 * @param yesTo3Probs boolean - shows whether there are 3 or 2 probes in the
	 *                    game.
	 */
	public G3Model(double width, double height, int matrixDimX, int matrixDimY, boolean yesTo3Probs) {
		System.out.println("New Game3");

		// Set up probes
		this.probeList = new ArrayList<Probe>();
		if (yesTo3Probs)
			numProbes = NUMBER_OF_PROBES_YES;
		else
			numProbes = NUMBER_OF_PROBES_NO;

		// Store width of canvas
		canvasWidth = width;
		canvasHeight = height;

		// Store the X and Y dimensions of a grid square (for movement calculation)
		this.matrixDimX = matrixDimX;
		this.matrixDimY = matrixDimY;

		gridWidth = (int) (canvasWidth / matrixDimX);
		gridHeight = (int) (canvasHeight / matrixDimY);

		// Initialize the matrix for the maze
		mazeMatrix = new int[matrixDimY][matrixDimX];

		// Create the maze

		// initialize the first starting point, give multiple options of direction
		mazeMatrix[1][1] = 1;
		mazeMatrix[2][1] = 1;
		mazeMatrix[1][2] = 1;
		int startLocX = 1, startLocY = 1;

		// begin maze generation
		drawNext(startLocX, startLocY);

		// Setup probe locations in lower right, upper right, and lower left corners
		// respectively
		// Also give multiple path options by clearing 2 directions from each probe

		// Bottom right
		mazeMatrix[matrixDimY - 2][matrixDimX - 2] = 2;
		mazeMatrix[matrixDimY - 3][matrixDimX - 2] = 1;
		mazeMatrix[matrixDimY - 2][matrixDimX - 3] = 1;

		// Top right
		mazeMatrix[1][matrixDimX - 2] = 2;
		mazeMatrix[1][matrixDimX - 3] = 1;
		mazeMatrix[2][matrixDimX - 2] = 1;

		// Bottom left
		mazeMatrix[matrixDimY - 2][1] = 2;
		mazeMatrix[matrixDimY - 3][1] = 1;
		mazeMatrix[matrixDimY - 2][2] = 1;

		// Initialize the character position to the center of the 1,1 starting grid
		// location

		playerChar = new G3Player((gridWidth * 1.5), (gridHeight * 1.5), X_INCREASE, Y_INCREASE);

	}

	/**
	 * returns the 2D integer array maze matrix Created on 11/4/2019
	 * 
	 * @return int[][] mazeMatrix - the 2D maze matrix.
	 *
	 * 
	 */

	public int[][] getMazeMatrix() {

		return mazeMatrix;
	}

	/**
	 * Returns the number of probes currently available for use
	 * 
	 * @return the value in the numProbes field.
	 *
	 * 
	 */

	public int getNumProbes() {
		return numProbes;
	}

	/**
	 * 
	 * @param xInterval     int, value player character wants to move in the x
	 *                      direction
	 * @param yInterval     int, value player character wants to move in the x
	 *                      direction
	 * @param probeInteract boolean, whether the player interacts with a probe or
	 *                      not.
	 * @return boolean returns true if the game is still running.
	 */
	public boolean update(int xInterval, int yInterval, boolean probeInteract) {

		yInterval = -yInterval;

		// Determine direction

		switch (xInterval) {
		case -1:
			this.direction = pkgEnum.Direction.WEST;
			break;
		case 1:
			this.direction = pkgEnum.Direction.EAST;
			break;
		case 0:
			if (yInterval != 0) {
				this.direction = pkgEnum.Direction.NORTH;
			} else {
				this.direction = pkgEnum.Direction.NONE;
			}
			break;
		}
		// If all 3 probes are picked up and done, short circuit to false return
		if (finishedFlag == true) {

			return false;
		}

		// Check based on projected X coordinate
		if (xInterval != 0) {
			projectedX = (int) (playerChar.getXLoc() + (xInterval * playerChar.getXIncr()));
			projectedY = (int) playerChar.getYLoc();
			if (isValid(projectedX, projectedY)) {
				if (xInterval > 0)
					playerChar.moveRight();
				else
					playerChar.moveLeft();
			}
		}

		// Check based on projected Y coordinate
		if (yInterval != 0) {
			projectedX = (int) playerChar.getXLoc();
			projectedY = (int) (playerChar.getYLoc() + (yInterval * playerChar.getYIncr()));
			if (isValid(projectedX, projectedY)) {
				if (yInterval > 0)
					playerChar.moveDown();
				if (yInterval < 0)
					playerChar.moveUp();
			}
		}

		if (probeInteract && (completedProbes < 3)) {

			probeHandle();
		}

		// update all the probes in the list
		for (Probe probe : probeList) {
			probe.update();
		}

		return true;
	}

	/**
	 * Gets the X coordinate of the current player position
	 * 
	 * @return the XLoc of the player.
	 *
	 */

	public double getPlayerX() {
		return playerChar.getXLoc();
	}

	/**
	 * Get the Y coordinate of the current player position
	 * 
	 * @return the value of the playerChar Y field.
	 *
	 * 
	 */

	public double getPlayerY() {
		return playerChar.getYLoc();
	}

	/**
	 * Get the direction the player character should be facing
	 * 
	 * @return value of the direction field
	 *
	 * 
	 */

	public pkgEnum.Direction getDirection() {
		return this.direction;
	}

	/**
	 * Returns the width dimension of the maze grid
	 * 
	 * @return the width dimension of the maze grid
	 *
	 * 
	 */

	public double getGridX() {

		return gridWidth;

	}

	/**
	 * Returns the height dimension of the maze grid
	 * 
	 * @return value of the gridHeight field.
	 *
	 * 
	 */

	public double getGridY() {
		return gridHeight;
	}

	/**
	 * Returns a list of the currently active probes
	 * 
	 * @return value of the probeList field.
	 *
	 * 
	 */

	public ArrayList<Probe> getProbeList() {

		return this.probeList;
	}

	/**
	 * 
	 * Recursive function that performs a depth first search starting in random
	 * directions to create new maze paths
	 * 
	 * @param locX current X location in the maze
	 * @param locY current Y location in the maze
	 *
	 * 
	 */

	private void drawNext(int locX, int locY) {

		// Will contain a random direction to be looked at in a random order
		int currentDirection;

		// FIXED: populate a new ArrayList with shuffled integers from 0-3
		ArrayList<Integer> directionList = new ArrayList<Integer>();
		for (int i = 0; i < 4; i++)
			directionList.add(i);
		Collections.shuffle(directionList);

		int localLocX = locX;
		int localLocY = locY;
		int nextLocX = -1;
		int nextLocY = -1;

		// for each direction
		for (int i = 0; i < 4; i++) {

			// FIXED: iterates through a RANDOMLY SHUFFLED order of directions (Fixes
			// previous tendency to go counter-clockwise)
			currentDirection = directionList.get(i);

			// Depending on direction check tile 2 away in that direction
			// if it is on the grid and it is not marked as a 1, draw to it
			switch (currentDirection) {
			case 0:
				nextLocX = localLocX;
				nextLocY = localLocY - 2;
				if ((1 <= nextLocY && nextLocY <= (matrixDimY - 2)) && (1 <= nextLocX && nextLocX <= (matrixDimX - 2))
						&& mazeMatrix[nextLocY][nextLocX] == 0) {
					// Draw to tested location and block in between
					mazeMatrix[nextLocY][nextLocX] = 1;
					mazeMatrix[nextLocY + 1][nextLocX] = 1;
					// recursive call on new location
					drawNext(nextLocX, nextLocY);
				}
				break;
			case 1:
				nextLocX = localLocX + 2;
				nextLocY = localLocY;
				if ((1 <= nextLocY && nextLocY <= (matrixDimY - 2)) && (1 <= nextLocX && nextLocX <= (matrixDimX - 2))
						&& mazeMatrix[nextLocY][nextLocX] == 0) {
					mazeMatrix[nextLocY][nextLocX] = 1;
					mazeMatrix[nextLocY][nextLocX - 1] = 1;
					drawNext(nextLocX, nextLocY);
				}
				break;
			case 2:
				nextLocX = localLocX;
				nextLocY = localLocY + 2;
				if ((1 <= nextLocY && nextLocY <= (matrixDimY - 2)) && (1 <= nextLocX && nextLocX <= (matrixDimX - 2))
						&& mazeMatrix[nextLocY][nextLocX] == 0) {
					mazeMatrix[nextLocY][nextLocX] = 1;
					mazeMatrix[nextLocY - 1][nextLocX] = 1;
					drawNext(nextLocX, nextLocY);
				}
				break;
			case 3:
				nextLocX = localLocX - 2;
				nextLocY = localLocY;
				if ((1 <= nextLocY && nextLocY <= (matrixDimY - 2)) && (1 <= nextLocX && nextLocX <= (matrixDimX - 2))
						&& mazeMatrix[nextLocY][nextLocX] == 0) {
					mazeMatrix[nextLocY][nextLocX] = 1;
					mazeMatrix[nextLocY][nextLocX + 1] = 1;
					drawNext(nextLocX, nextLocY);
				}
				break;
			}

		}

		return;

	}

	/**
	 *  determines  if a player attempted move is valid, and returns true if it is and false if it isnt;
	 * @param futureX  X location player is attempting to move to
	 * @param futureY  Y location player is attempting to move to
	 *
	 * 
	 */

	private boolean isValid(int futureX, int futureY) {

		if ((futureX < 0) || (futureY < 0) || (futureX > canvasWidth) || (futureY > canvasHeight)) {
			return false;
		}

		if (mazeMatrix[projectedY / (int) gridHeight][projectedX / (int) gridWidth] == 0) {

			return false;
		}
		// if it equals anything but wall, return true
		else {

			return true;
		}
	}

	/**
	 *handles the logic of    placing and picking up probes, and determining if they are done.
	 * @return boolean  Allows the player to interact with probes in the specific probe location. True if they interacted false if they didn't.
	 */
	private boolean probeHandle() {
		// Get current player grid
		int currentPlayerGridX = (int) (playerChar.getXLoc() / (int) gridWidth);
		int currentPlayerGridY = (int) (playerChar.getYLoc() / (int) gridHeight);

		// If the current player location is not in a probe grid, just return false;
		if (mazeMatrix[currentPlayerGridY][currentPlayerGridX] != 2) {

			return false;
		}

		for (Probe probe : probeList) {

			// If the probe is already in the list
			if ((probe.getXLoc() == currentPlayerGridX) && (probe.getYLoc() == currentPlayerGridY)) {

				// if probe is done, change state return true
				if (probe.pickUp()) {
					numProbes++;
					completedProbes++;
					if (completedProbes == 3) {
						finishedFlag = true;
					}
				}
				return false;
			}

		}

		// If there is an available probe, place it
		if (numProbes > 0) {
			this.probeList.add(new Probe(currentPlayerGridX, currentPlayerGridY));
			numProbes--;
		}

		return true;

	}


}