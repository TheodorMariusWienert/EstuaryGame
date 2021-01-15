
package pkgView;

import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import pkgGamePiece.Probe;

/**
 * Used to create the View for the 3rd game. Created on 11/1/2019
 * 
 * @author Stephen
 *
 * 
 */

public class G3View extends View {

	double playerXpos;
	double playerYpos;
	double heightOfRectangle;
	double widthRectangle;

	// Some animaiton variables

	// Counter for number of frames so far
	private int imgAnimationFrameCount = 0;
	// Number of frames that the sprite and probe animation lasts (adjustable)
	private int spriteFrameCycle = 15;
	private int probeFrameCycle = 30;
	// the number of images that the sprite and probes are animated with
	private int totalSpriteFrames = 3;
	private int totalProbeFrames = 2;
	// starting index to be used in array of images
	private int spriteAnimationIndex = 0;
	private int probeAnimationIndex = 0;
	// to be calculated in the constructor by frameCycle/totalFrames
	private int spriteAnimationDivisor;
	private int probeAnimationDivsior;

	private int sciImgDivisor = 3;
	private double sciImgMultiplier = 1.7;

	// Variables for storing relevant image assets

	private Image probeCompleteImg;
	private Image dirtTileImage;
	private Image dirtPathImage;
	private Image waterTileImage;
	private Image reedsImage;
	private Image probeTileImage;
	private Image grassImage;
	private Image[] probePending;
	private Image[] scientistRight;
	private Image[] scientistLeft;
	private Image[] scientistStop;

	// Labels
	private Label probeLabel;
	private ArrayList<VBox> probeLabelList;

	/**
	 * 
	 * Construcotr for the last Game
	 * 
	 * @param mazeMatrix        int[][], the matrix for the maze
	 * @param heightOfRectangle double, height of the rectangle for the blogs of the
	 *                          maze
	 * @param widthRectangle    double, width of the rectangle for the blogs of the
	 *                          maze
	 * @param playerXPos        double, X position of the player
	 * @param playerYPos        double, Y position of the player
	 * @param width             double, width of the Screen
	 * @param height            double, height of the Screen
	 * @param currentTimer      long, timer for this game
	 * 
	 */

	public G3View(int[][] mazeMatrix, double heightOfRectangle, double widthRectangle, double playerXPos,
			double playerYPos, double width, double height, long currentTimer) {

		super(width, height);

		// Better fit
		tutorialLabel.setTranslateX(0);
		tutorialLabel.setTranslateY(canvasHeight - 50);
		this.widthRectangle = widthRectangle;
		this.heightOfRectangle = heightOfRectangle;

		// Calculate the number of frames each image should be displayed for
		this.spriteAnimationDivisor = this.spriteFrameCycle / this.totalSpriteFrames;
		this.probeAnimationDivsior = this.probeFrameCycle / this.totalProbeFrames;

		// Load up the relevant images
		this.probeCompleteImg = new Image("images/Probe_Complete.png", this.widthRectangle, this.heightOfRectangle,
				false, false);
		this.dirtTileImage = new Image("images/tile_dirt_center.png", this.widthRectangle, this.heightOfRectangle,
				false, false);
		this.waterTileImage = new Image("images/tile_water_C.png", this.widthRectangle, this.heightOfRectangle, false,
				false);
		this.reedsImage = new Image("images/Reeds.png", this.widthRectangle, this.heightOfRectangle, false, false);
		this.probeTileImage = new Image("images/tile_water_probe.png", this.widthRectangle, this.heightOfRectangle,
				false, false);
		this.dirtPathImage = new Image("images/tile_dirt_lighter.png", this.widthRectangle, this.heightOfRectangle,
				false, false);
		this.grassImage = new Image("images/cordgrass.png", this.widthRectangle, this.heightOfRectangle, false, false);

		// initialize the animation images in arrays
		this.probePending = new Image[this.totalProbeFrames];
		this.scientistRight = new Image[this.totalSpriteFrames];
		this.scientistLeft = new Image[this.totalSpriteFrames];
		this.scientistStop = new Image[this.totalSpriteFrames];

		// Load up animation images into arrays
		for (int i = 0; i < this.totalProbeFrames; i++) {
			this.probePending[i] = new Image("images/Probe_Pending_" + i + ".png", this.widthRectangle,
					this.heightOfRectangle, false, false);
		}

		for (int i = 0; i < this.totalSpriteFrames; i++) {
			this.scientistRight[i] = new Image("images/Scientist/sciR" + (i + 1) + ".png", this.widthRectangle,
					this.heightOfRectangle * 2, false, false);
			this.scientistLeft[i] = new Image("images/Scientist/sciL" + (i + 1) + ".png", this.widthRectangle,
					this.heightOfRectangle * 2, false, false);
			this.scientistStop[i] = new Image("images/Scientist_Placeholder.png", this.widthRectangle,
					this.heightOfRectangle * 2, false, false);
		}

		// Creating the probe counter
		// --------------------------------------------------------------
		HBox probeCounter = new HBox(); // Added by Chandler Amato
		ImageView probeImageView = new ImageView(probePending[1]);
		this.probeLabel = new Label("X 2", probeImageView); // TODO probably put this in the constructor so that it
															// changes when the decision changes.
		probeLabel.setFont(FONT_FOR_LABELS);
		probeLabel.setStyle("-fx-font-weight: bold");
		probeLabel.setTextFill(Color.CRIMSON);
		probeLabel.setStyle("-fx-background-color: rgba(255, 255, 255, 0.5); -fx-background-radius: 10;");
		// --------------------------------------------------------

		this.probeLabelList = new ArrayList<VBox>();
		for (int i = 0; i < 3; i++) {
			this.probeLabelList.add(new VBox());

		}

		tutorialLabel.setAlignment(Pos.BOTTOM_LEFT);
		tutorialLabel.setFont(FONT_FOR_TUTORIAL);

		root = new Group();
		theScene = new Scene(root);

		// Set up the two canvases, one for background, other for player
		Canvas canvas = new Canvas(canvasWidth, canvasHeight);
		Canvas playerCanvas = new Canvas(canvasWidth, canvasHeight);
		root.getChildren().add(canvas);
		root.getChildren().add(playerCanvas);
		root.getChildren().add(tutorialLabel);

		gc = canvas.getGraphicsContext2D();
		pgc = playerCanvas.getGraphicsContext2D();

		// Begin rendering the maze from the 2D int array generated and passed by the
		// model
		int k = 0;
		int p = 0;
		for (int[] i : mazeMatrix) {

			for (int j : i) {
				// If the data indicates a wall
				if (j == 0) {

					gc.fillRect(k * widthRectangle, p * heightOfRectangle, widthRectangle, heightOfRectangle);

					// Draw water and reeds around right and bottom edge barriers
					if ((k >= mazeMatrix[0].length - 1) || (p >= mazeMatrix.length - 1)) {
						gc.drawImage(this.waterTileImage, k * widthRectangle, p * heightOfRectangle);
						gc.drawImage(this.reedsImage, k * widthRectangle, p * heightOfRectangle);
					}
					// Draw dirt and grass elsewhere
					else {
						gc.drawImage(this.dirtTileImage, k * widthRectangle, p * heightOfRectangle);
						gc.drawImage(this.grassImage, k * widthRectangle, p * heightOfRectangle);
					}

					// Draw lighter dirt if it is traversible
				} else if (j == 1) {
					gc.drawImage(this.dirtPathImage, k * widthRectangle, p * heightOfRectangle);
				}

				// If the data indicates a probe, draw a probe marker in this grid
				else if (j == 2) {
					gc.drawImage(this.probeTileImage, k * widthRectangle, p * heightOfRectangle);
					// draw probe tile at this location
				}

				k++;

			}

			// Again, draw more water to right as insurance
			gc.drawImage(this.waterTileImage, k * widthRectangle, p * heightOfRectangle);
			k = 0;
			p++;

		}

		// Draw water at the bottom
		int gridwidth = mazeMatrix[1].length;
		for (int i = 0; i < gridwidth + 1; i++) {
			gc.drawImage(this.waterTileImage, i * widthRectangle, p * heightOfRectangle);
		}

		// Initialize the starting location of the player
		this.playerXpos = playerXPos;
		this.playerYpos = playerYPos;

		// Score box
		bigScoreBox = new VBox();
		bigScoreBox.setPrefSize(canvasWidth, canvasHeight);
		root.getChildren().add(bigScoreBox);

		// Populate the probe counter
		// --------------------------------------- Added by Chandler Amato
		probeCounter.getChildren().add(probeLabel);
		probeImageView.setFitHeight(IMG_VIEW_WIDTH);
		probeImageView.setFitWidth(IMG_VIEW_WIDTH);
		probeCounter.setAlignment(Pos.TOP_LEFT);
		bigScoreBox.getChildren().add(probeCounter);
		// --------------------------------------

		createTimer(currentTimer);
		root.getChildren().add(getSkipButton()); // has to be the last thing added to root for button to work
	}

	/**
	 * update the player animation and probe animations.
	 * 
	 * @author Stephen Moss
	 * @param playerXpos      double, X position of Player
	 * @param playerYpos      double, Y position of Player
	 * @param playerDirection Enum.Direction, holds the different directions
	 * @param probeList       ArrayList&lt;Probe&gt;, holds the different probes
	 * @param numProbes       int, the number of probes, we have
	 * @param currentTime     long, the remaining time
	 * 
	 *
	 * 
	 */

	public void update(double playerXpos, double playerYpos, pkgEnum.Direction playerDirection,
			ArrayList<Probe> probeList, int numProbes, long currentTime) {

		// Display the number of probes
		probeLabel.setText("X" + " " + numProbes);
		// Update the player location
		updatePlayer(playerXpos, playerYpos, playerDirection);
		updateProbes(probeList);
		// updating the animations
		this.imgAnimationFrameCount++;
		this.spriteAnimationIndex = (this.imgAnimationFrameCount % this.spriteFrameCycle) / this.spriteAnimationDivisor;
		this.probeAnimationIndex = (this.imgAnimationFrameCount % this.probeFrameCycle) / this.probeAnimationDivsior;
		// Maintain timer with info passed from controller
		updateTimer(currentTime);
	}

	/**
	 * called by the update method. Handles the player animation updates
	 * 
	 * @author Stephen Moss
	 * @param playerXpos:      double, X position of Player
	 * @param playerYpos:      double, Y position of Player
	 * @param playerDirection: Enum.Direction, holds the different directions
	 * 
	 *
	 * 
	 */

	public void updatePlayer(double playerXpos, double playerYpos, pkgEnum.Direction playerDirection) {

		// Default array to use is idle
		Image[] scientistDirection = this.scientistStop;

		// Take the given direction passed from the model
		if (playerDirection != null) {
			switch (playerDirection) {
			case WEST:
				scientistDirection = this.scientistLeft;
				break;
			case EAST:
			case NORTH:
				scientistDirection = this.scientistRight;
				break;
			case NONE:
				scientistDirection = this.scientistStop;
				break;
			}
		}

		// Clear last frame
		pgc.clearRect(0, 0, canvasWidth, canvasHeight);
		this.playerXpos = playerXpos - widthRectangle / 10;
		this.playerYpos = playerYpos - heightOfRectangle / 10;
		pgc.drawImage(scientistDirection[this.spriteAnimationIndex],
				(this.playerXpos - (this.widthRectangle) / sciImgDivisor),
				(this.playerYpos - (this.heightOfRectangle * sciImgMultiplier)));
	}

	/**
	 * udates the probes that are in the problist when they are done collecting
	 * data/are placed.
	 * 
	 * @author Stephen Moss
	 * @param probeList: ArrayList&lt;Probe&gt;, hold the probes
	 *
	 *
	 * 
	 */

	public void updateProbes(ArrayList<Probe> probeList) {

		// For each probe that has been placed
		for (Probe probe : probeList) {

			// Actions depend on probe current status
			switch (probe.getStatus()) {

			// If it is in the process of reading sensor
			case 1:
				// Draw pending animation
				gc.drawImage(this.waterTileImage, probe.getXLoc() * widthRectangle,
						probe.getYLoc() * heightOfRectangle);
				gc.drawImage(this.probePending[this.probeAnimationIndex], probe.getXLoc() * widthRectangle,
						probe.getYLoc() * heightOfRectangle);
				gc.fillText("" + (probe.getTime() / 100), (probe.getXLoc()) * widthRectangle,
						(probe.getYLoc() + .5) * heightOfRectangle);
				break;
			// If it is completed, draw it so
			case 2:
				gc.drawImage(this.waterTileImage, probe.getXLoc() * widthRectangle,
						probe.getYLoc() * heightOfRectangle);
				gc.drawImage(this.probeCompleteImg, probe.getXLoc() * widthRectangle,
						probe.getYLoc() * heightOfRectangle);
				gc.fillText("" + (probe.getTime() / 100), (probe.getXLoc()) * widthRectangle,
						(probe.getYLoc() + .5) * heightOfRectangle);
				break;
			// If it is finished, draw plain water image
			case 3:
				gc.drawImage(this.waterTileImage, probe.getXLoc() * widthRectangle,
						probe.getYLoc() * heightOfRectangle);
				break;
			}

			gc.setFill(Color.CRIMSON);

		}

	}

}