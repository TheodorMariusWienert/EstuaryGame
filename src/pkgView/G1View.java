package pkgView;

import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import pkgEnum.ObstacleState;
import pkgEnum.ShipState;
import pkgGamePiece.G1Player;
import pkgGamePiece.Obstacle;

/**
 * used to create the view for the first game. Created on 11/1/2019
 * 
 * @author Theodor
 * 
 */

public class G1View extends View {

	// --------------------------------Images
	private Image shipImg = new Image("images/G1_ship.png");
	private Image obstacleShip = new Image("images/obstacleShip.png");
	private Image rockImg = new Image("images/Rock.png");
	private Image canImg = new Image("images/smallCan.png");
	private Image bg = new Image("images/blurredRiver_flip.jpg");
	private Image bg2 = new Image("images/blurredRiver.jpg");

	// ---------------------------------------------

	// ---------------------Width and Heights for Images

	private double shipImgWidth = shipImg.getWidth();
	private double shipImgHeight = shipImg.getHeight();
	private int rockImgWidth = (int) rockImg.getWidth();
	private int rockImgHeight = (int) rockImg.getHeight();
	private int canImgWidth = (int) canImg.getWidth();
	private int canImgHeight = (int) canImg.getHeight();

	// ---------------------------------------------

	private double posX1forScroll;
	private double posX2forScroll;
	private int scrollSpeed = 9;
	private int flash;

	/**
	 * Constructor for the first Game
	 * 
	 * @param width       double, width of the Screen
	 * @param height      double, height of the Screen
	 * @param currentTime long, the time we have got for this Game
	 */
	public G1View(double width, double height, long currentTime) {

		super(width, height);

		posX1forScroll = 0;
		posX2forScroll = canvasWidth;

		flash = 0;

		root = new Group();
		theScene = new Scene(root);

		Canvas canvas = new Canvas(canvasWidth, canvasHeight);
		root.getChildren().add(canvas);
		gc = canvas.getGraphicsContext2D();

		tutorialLabel.setAlignment(Pos.TOP_LEFT);
		tutorialLabel.setFont(FONT_FOR_TUTORIAL);

		bigScoreBox = new VBox();
		bigScoreBox.setPrefSize(canvasWidth, canvasHeight);
		root.getChildren().add(bigScoreBox);

		root.getChildren().add(tutorialLabel);
		createTimer(currentTime);

		root.getChildren().add(getSkipButton()); // has to be the last thing added to root for button to work

	}

	/**
	 * draws the game score and the player position, and obstacles on the screen, as
	 * well as the scrolling background.
	 * 
	 * @param ship         G1Player, the Player for the G1
	 * @param obstacleList ArrayList&lt;Obstacle&gt;, the list with the Obstacles
	 * @param econScore    double, Econ score which we want to display
	 * @param envirmScore  double, Envirm score which we want to display
	 * @param overallScore double, Overall score which we want to display
	 * @param currentTime  long, the current Time which we have
	 * 
	 * @author MEU CJA
	 *
	 * 
	 */
	public void update(G1Player ship, ArrayList<Obstacle> obstacleList, double econScore, double envirmScore,
			double overallScore, long currentTime) {

		updateTimer(currentTime);
		drawGameScore(econScore, envirmScore, overallScore, bigScoreBox);
		posX1forScroll = posX1forScroll - scrollSpeed;
		posX2forScroll = posX2forScroll - scrollSpeed;
		gc.clearRect(0, 0, canvasWidth, canvasHeight);
		gc.drawImage(bg, posX1forScroll, 0, canvasWidth * 1.04, canvasHeight);
		gc.drawImage(bg2, posX2forScroll, 0, canvasWidth * 1.04, canvasHeight);// draws the background every update. I
																				// don't think that is necessary but it
																				// is fine.

		// Creates two backgrounds that leap from one another to keep it scrolling
		if (posX1forScroll < -canvasWidth - 40) {
			posX1forScroll = canvasWidth;

		}
		if (posX2forScroll < -canvasWidth - 40) {
			posX2forScroll = canvasWidth;
		}

		if (ship.getState() == ShipState.HURT && flash == 2) {
			gc.drawImage(shipImg, 0, 0, shipImgWidth, shipImgHeight, ship.getXLoc(), ship.getYLoc(), shipImgWidth,
					shipImgHeight);
			flash -= 2;
		} else if (ship.getState() == ShipState.HURT && flash < 2) {
			flash += 1;
		} else {
			gc.drawImage(shipImg, 0, 0, shipImgWidth, shipImgHeight, ship.getXLoc(), ship.getYLoc(), shipImgWidth,
					shipImgHeight);
		}

		for (Obstacle obstacle : obstacleList) {
			if (obstacle.getObsType() == ObstacleState.ROCK) {
				gc.drawImage(rockImg, 0, 0, rockImgWidth, rockImgHeight, obstacle.getXLoc(), obstacle.getYLoc(),
						rockImgWidth, rockImgHeight);
			} else if (obstacle.getObsType() == ObstacleState.CAN) {
				gc.drawImage(canImg, 0, 0, canImgWidth, canImgHeight, obstacle.getXLoc(), obstacle.getYLoc(),
						canImgWidth, canImgHeight);
			} else {
				gc.drawImage(obstacleShip, 0, 0, shipImgWidth, shipImgHeight, obstacle.getXLoc(), obstacle.getYLoc(),
						shipImgWidth, shipImgHeight);
			}
		}
	}

	/**
	 * Set the Speed how fast the Background is moving
	 * 
	 * @param scrollSpeed void
	 */
	public void setScrollSpeed(int scrollSpeed) {
		this.scrollSpeed = scrollSpeed;
	}

	/**
	 * returns the height of ship
	 * 
	 * @return double
	 * 
	 */
	public double getShipHeight() {
		return shipImgHeight;
	}

	/**
	 * returns the width of ship
	 * 
	 * @return double
	 */
	public double getShipWidth() {
		return shipImgWidth;
	}

	/**
	 * returns height of Rock
	 * 
	 * @return int
	 */

	public int getRockHeight() {
		return rockImgHeight;
	}

	/**
	 * returns the width of rock
	 * 
	 * @return int
	 */
	public int getRockWidth() {
		return rockImgWidth;
	}

	/**
	 * returns the height of can
	 * 
	 * @return int
	 */
	public int getCanHeight() {
		return canImgHeight;
	}

	/**
	 * returns the width of can
	 * 
	 * @return int
	 */
	public int getCanWidth() {
		return canImgWidth;
	}

}