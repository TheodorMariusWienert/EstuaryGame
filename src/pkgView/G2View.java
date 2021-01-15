package pkgView;

import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import pkgEnum.Direction;
import pkgGamePiece.Animal;
import pkgGamePiece.Crustacean;
import pkgGamePiece.Fish;
import pkgGamePiece.G2Player;
import pkgGamePiece.GamePiece;

/**
 * Used to create the View objects for the second game Created on 11/1/2019
 * 
 * @author Chandler
 * 
 */
public class G2View extends View {

//-----------------------Image Loading---------------------------------------------------------------------------------
	private Image robotImgE = new Image("images/robotE_sized.png");
	private Image robotImgW = new Image("images/robotW_sized.png");
	private Image blueCatFishE = new Image("images/BlueCatFish_E.png");
	private Image troutE = new Image("images/fish_trout_right.png");
	private Image snakeHeadE = new Image("images/Snakehead_E.png", fishImgWidth, fishImgHeight, false, false);
	private Image bassE = new Image("images/fish_bass_right.png");
	private Image blueCatFishW = new Image("images/BlueCatFish_W.png");
	private Image troutW = new Image("images/fish_trout_left.png");
	private Image snakeHeadW = new Image("images/SnakeHead_W.png", fishImgWidth, fishImgHeight, false, false);
	private Image bassW = new Image("images/fish_bass_left.png");
	private Image flashImg = new Image("images/Flash.png");
	private Image netImgE = new Image("images/NetE.png");
	private Image netImgW = new Image("images/NetW.png");
	private Image bg = new Image("images/underwater2.png");
	private Image bCrabImg = new Image("images/bluecrab_0.png");
	private Image mCrabImg = new Image("images/mittencrab_1.png");
	private Image cameraImg = new Image("images/cameraImg.png");
//-----------------------Image View-----------------------------------------------------------------------------------
	private ImageView bassImgView = new ImageView(bassE); // USED TO ADD THE IMAGE TO THE LABELS IN THE VBOX
	private ImageView blueCatFishImgView2 = new ImageView(blueCatFishE);
	private ImageView snakeHeadImgView3 = new ImageView(snakeHeadE);
	private ImageView troutImgView4 = new ImageView(troutE);
	private ImageView blueCrabImgView = new ImageView(bCrabImg);
	private ImageView mCrabImgView = new ImageView(mCrabImg);
	private ImageView netImgView = new ImageView(netImgE);
	private ImageView cameraImgView = new ImageView(cameraImg);

//----------------------Image List for Animals------------------------------------------------------------------------
	private ArrayList<Image> fishImgList = new ArrayList<Image>();
	private ArrayList<Image> crustImgList = new ArrayList<Image>();
//----------------------Image Dimensions------------------------------------------------------------------------------
	private double robotImgWidth = robotImgE.getWidth();
	private double robotImgHeight = robotImgE.getHeight();

	private double flashImgWidth = flashImg.getWidth();
	private double flashImgHeight = flashImg.getHeight();
	private double crustImgHeight = bCrabImg.getHeight();
	private double crustImgWidth = bCrabImg.getWidth();
//----------------------Objectives organization box--------------------------------------------------------------------
	private VBox objectiveBox;
	private VBox row1; // TODO delete maybe
	private VBox row2;
	private HBox rowBox;

//------------------- Button and ToggleGroup ----------------------------
	ToggleGroup toolToggleGroup;
	RadioButton cameraButton;
	RadioButton netButton;

//------------- helps with tutorial-----------------------------------
	Label invasive1Label = new Label();
	Label unInvasive1Label = new Label();
	Label speciesInvLabel;
	Label speciesNatLabel;
	// ---------------------------------Statics-------------------------

	private final static int IMG_VIEW_HEIGHT = 50;
	private final static int IMG_VIEW_WIDTH = 100;
	private final static double fishImgWidth = 257.0 * 1.2;
	private final static double fishImgHeight = 91.0 * 1.2;

	/**
	 * Constructor for the G2View Objects.
	 * 
	 * @param currentTime long, the time we have for this game
	 * @param width       double, the width of the Screen
	 * @param height      double, the height of the screen
	 */
	public G2View(long currentTime, double width, double height) {
		super(width, height);

		root = new Group();
		theScene = new Scene(root);
		// Here the VBox is created which is used to organize the sea life that the user
		// is supposed to collect.
		objectiveBox = new VBox();
		// ------------------------------------------------------------------------------------------------------------
		// Setting the height and width of the imageViews that are used to give the
		// labels an image to the left of the text.
		bassImgView.setFitHeight(IMG_VIEW_HEIGHT);
		bassImgView.setFitWidth(IMG_VIEW_WIDTH);
		blueCatFishImgView2.setFitHeight(IMG_VIEW_HEIGHT);
		blueCatFishImgView2.setFitWidth(IMG_VIEW_WIDTH);
		snakeHeadImgView3.setFitHeight(IMG_VIEW_HEIGHT);
		snakeHeadImgView3.setFitWidth(IMG_VIEW_WIDTH);
		troutImgView4.setFitHeight(IMG_VIEW_HEIGHT);
		troutImgView4.setFitWidth(IMG_VIEW_WIDTH);
		blueCrabImgView.setFitHeight(IMG_VIEW_HEIGHT);
		blueCrabImgView.setFitWidth(IMG_VIEW_WIDTH);
		mCrabImgView.setFitHeight(IMG_VIEW_HEIGHT);
		mCrabImgView.setFitWidth(IMG_VIEW_WIDTH);
		netImgView.setFitHeight(IMG_VIEW_HEIGHT * 2);
		netImgView.setFitWidth(IMG_VIEW_WIDTH * 1.3);
		cameraImgView.setFitHeight(IMG_VIEW_HEIGHT * 2);
		cameraImgView.setFitWidth(IMG_VIEW_WIDTH * 1.3);

		// -------------------------------------------------------------------------------------------------------------
		Label invasive1Label = new Label("Blue Catfish", blueCatFishImgView2);
		Label invasive2Label = new Label("Snakehead", snakeHeadImgView3);
		Label invasive3Label = new Label("Mitten Crab", mCrabImgView);

		Label unInvasive1Label = new Label("Large Mouth Bass", bassImgView); // Creating labels to go in the vBox
		Label unInvasive2Label = new Label("Brook Trout", troutImgView4);
		Label unInvasive3Label = new Label("Blue Crab", blueCrabImgView);

		// -------------------------------------------------------------------------------------------------------------
		unInvasive1Label.setFont(FONT_FOR_LABELS); // Changing the font
		invasive1Label.setFont(FONT_FOR_LABELS);
		invasive2Label.setFont(FONT_FOR_LABELS);
		unInvasive2Label.setFont(FONT_FOR_LABELS);
		invasive3Label.setFont(FONT_FOR_LABELS);
		unInvasive3Label.setFont(FONT_FOR_LABELS);
		// ----------------------------------------------------------------------------------------
		toolToggleGroup = new ToggleGroup();
		cameraButton = new RadioButton();
		cameraButton.setGraphic(cameraImgView);
		cameraButton.setUserData("camera");
		cameraButton.setFocusTraversable(false);

		netButton = new RadioButton();
		netButton.setGraphic(netImgView);
		netButton.setUserData("net");
		netButton.setFocusTraversable(false);

		netButton.setToggleGroup(toolToggleGroup);
		netButton.setSelected(true);
		cameraButton.setToggleGroup(toolToggleGroup);
		// -------------------------------------------------------------------------------------------------------------
		objectiveBox.setPrefSize(canvasWidth, canvasHeight); // setting preferred size of the vbox
		speciesNatLabel = new Label("Indigenous Species:");
		speciesInvLabel = new Label("Invasive Species:");
		speciesNatLabel.setFont(FONT_FOR_LABELS);
		speciesInvLabel.setFont(FONT_FOR_LABELS);

		row1 = new VBox(speciesNatLabel, unInvasive1Label, unInvasive2Label, unInvasive3Label, cameraButton);
		row1.setAlignment(Pos.TOP_LEFT);
		row1.setStyle("-fx-background-color: rgba(0, 100, 0, 0.5); -fx-background-radius: 10;-fx-padding: 0px;");

		row2 = new VBox(speciesInvLabel, invasive1Label, invasive2Label, invasive3Label, netButton);
		row2.setAlignment(Pos.TOP_LEFT);

		row2.setStyle("-fx-background-color: rgba(100,0, 0, 0.5); -fx-background-radius: 10;-fx-padding: 0px;");
		rowBox = new HBox(row1, row2);

		objectiveBox.getChildren().add(rowBox);

		// -------------------------------------------------------------------------------------------------------------
		fishImgList.add(blueCatFishE); // Adding all of the animals images to the image list. The order is important
										// because it is related to their ID in the enum.
		fishImgList.add(snakeHeadE);
		fishImgList.add(troutE);
		fishImgList.add(bassE);
		fishImgList.add(blueCatFishW); // Adding all of the animals images to the image list. The order is important
										// because it is related to their ID in the enum.
		fishImgList.add(snakeHeadW);
		fishImgList.add(troutW);
		fishImgList.add(bassW);
		// ----------------------------------------
		crustImgList.add(bCrabImg);
		crustImgList.add(mCrabImg);
		// ------------------------------------------------------------------------------------------------------------
		Canvas canvas = new Canvas(canvasWidth, canvasHeight);
		root.getChildren().add(canvas);
		gc = canvas.getGraphicsContext2D();

		bigScoreBox = new VBox();

		rowBox.getChildren().add(new Group(bigScoreBox));

		// Create Timer
		createTimer(currentTime);

		tutorialLabel.setFont(FONT_FOR_LABELS);
		tutorialLabel.setWrapText(true);
		VBox bigObjectiveBox = new VBox();
		bigObjectiveBox.setMinSize(canvasHeight, canvasWidth);

		objectiveBox.setAlignment(Pos.TOP_LEFT);

		bigObjectiveBox.getChildren().add(objectiveBox);
		root.getChildren().add(bigObjectiveBox); // Adds the object box to the root.
		root.getChildren().add(tutorialLabel);
		root.getChildren().add(getSkipButton()); // has to be the last thing added to root for button to work

	}

	/**
	 * draws the game score, check which animals where found, and calls the
	 * drawAndFlip for Robot, and checks all the animal states and changes them
	 * respectively Created on
	 * 
	 * @param robot         G2Player, the Robot you controll for G2
	 * @param animalList    ArrayList&lt;Animal&gt;, the List of animal we got in
	 *                      this game
	 * @param objectiveList ArrayList&lt;String&gt;, list of Strings with the
	 *                      objectives
	 * @param timeRemaining long, the current Time which we have left
	 * @param econScore     double, Econ score which we want to display
	 * @param envirmScore   double, Envirm score which we want to display
	 * @param overallScore  double, Overall score which we want to display
	 * 
	 */
	public void update(G2Player robot, ArrayList<Animal> animalList, ArrayList<String> objectiveList,
			long timeRemaining, double econScore, double envirmScore, double overallScore) {

		gc.clearRect(0, 0, canvasWidth, canvasHeight);
		gc.drawImage(bg, 0, 0, canvasWidth, canvasHeight);

		drawGameScore(econScore, envirmScore, overallScore, bigScoreBox);

		for (Animal animal : animalList) {

			// First thing defined in Animation state is Draw, second thing is hide, and 3rd
			// is Special Special is used to animate things like pictures being taken.

			if (animal.getAnimationState() == 2) {
				drawAndFlip(animal);
				gc.drawImage(flashImg, 0, 0, flashImgWidth, flashImgHeight, animal.getXLoc(), animal.getYLoc(),
						flashImgWidth, flashImgHeight);
			}
			if (animal.getAnimationState() == 0) { // This will be the default image for the fish default state of them
				drawAndFlip(animal); // swimming
			}

		}

		drawAndFlip(robot);
		for (Node n : objectiveBox.getChildren()) {// Loops through all of the children of the objectiveBox
			HBox tmpHBox = new HBox();
			if (n instanceof HBox) {
				tmpHBox = (HBox) n;
			}
			for (Node j : tmpHBox.getChildren()) { // This loops through all of the children of the HBox and will print
				VBox tmpVBox = new VBox();
				if (j instanceof VBox) {
					tmpVBox = (VBox) j;
				}

				for (Node m : tmpVBox.getChildren()) { // Loops through all of the children of the VBox

					if (m instanceof Label) {
						Label tmpLabel = (Label) m; // every child is a label.

						if (!objectiveList.contains(tmpLabel.getText())) { // Checks if the objectiveList (updated in
																			// the model) contains the labels text and
																			// if it doesn't switches the fish label to
																			// DONE.
							if (!tmpLabel.getText().equals("Use tool")
									&& !tmpLabel.getText().equals("Invasive Species:")
									&& !tmpLabel.getText().equals("Indigenous Species:")) { // This line is so the tool
																							// labels don't say done.
								tmpLabel.setText("Done");
							}
						}
					}
				}
			}
		}

		updateTimer(timeRemaining);

	}

	/**
	 * draws the fish and if they are going the other direction flips them.
	 * 
	 * 
	 * @param gP GamePiece, the fish to be drawn
	 * 
	 * @author MEU CJA
	 *
	 */

	public void drawAndFlip(GamePiece gP) {
		int indexModifier = 0;
		if (gP.getDirection().ordinal() >= 3) {
			indexModifier = 4; // makes sure that the right image is selected because first all the 4 east
								// images are in, so if you add 4 the correct west image is selected
		}
		if (gP instanceof Fish) {
			Fish tmp = (Fish) gP;
			Image tmpImg = fishImgList.get(indexModifier + tmp.getSpecies().ordinal());
			gc.drawImage(tmpImg, 0.0, 0.0, tmpImg.getWidth(), tmpImg.getHeight(), gP.getXLoc(), gP.getYLoc(),
					tmpImg.getWidth(), tmpImg.getHeight());
		} else {

			Crustacean tmp = (Crustacean) gP;
			gc.drawImage(crustImgList.get(tmp.getSpecies().ordinal() - 4), 0.0, 0.0, crustImgWidth, crustImgHeight,
					gP.getXLoc(), gP.getYLoc(), crustImgWidth, crustImgHeight);
		}
	}

	/**
	 * draw and flip the robot in the correct direction
	 * 
	 * @param robot G2Player, the Robot for G2
	 */
	public void drawAndFlip(G2Player robot) {
		if (robot.getDirection() == Direction.WEST) {
			gc.drawImage(robotImgW, robot.getXLoc(), robot.getYLoc());
			if (robot.getSelectedTool() == 0) { // If the tool is the net, draw the net img
				gc.drawImage(netImgW, robot.getXLoc() - robotImgWidth * 0.35, robot.getYLoc() + robotImgHeight * 0.85,
						netImgE.getWidth(), netImgE.getHeight());
			}
		} else {
			gc.drawImage(robotImgE, robot.getXLoc(), robot.getYLoc());
			if (robot.getSelectedTool() == 0) { // If the tool is the net, draw the net img
				gc.drawImage(netImgE, robot.getXLoc() + robotImgWidth * 0.35, robot.getYLoc() + robotImgHeight * 0.85,
						netImgE.getWidth(), netImgE.getHeight());
			}
		}
	}

	/**
	 * gets the width of the robot Image.
	 * 
	 * @author CA / MEU
	 * @return robotImgWidth double, the width of the robot image.
	 * 
	 *
	 * 
	 */

	public double getRobotWidth() {
		return robotImgWidth;
	}

	/**
	 * gets the Height of the robot Image.
	 * 
	 * @author CA / MEU
	 * @return robotImgHeight double, the Height of the robot image.
	 *
	 *
	 * 
	 */

	public double getRobotHeight() {
		return robotImgHeight;
	}

	/**
	 * gets the width of the Fish Image.
	 * 
	 * @author MEU CJA
	 * @return robotFishWidth double, the width of the Fish image.
	 *
	 *
	 * 
	 */

	public double getFishWidth() {
		return fishImgWidth;
	}

	/**
	 * gets the height of the Fish Image.
	 * 
	 * @author MEU CJA
	 * @return robotFishHeight double, the height of the Fish image.
	 *
	 *
	 * 
	 */

	public double getFishHeight() {
		return fishImgHeight;
	}

	/**
	 * returns the crustacean images width
	 *
	 * @author MEU CJA
	 * @return double returns the crustacean image width
	 * 
	 *
	 * 
	 */

	public double getCrustWidth() {
		return crustImgWidth;
	}

	/**
	 * returns the crustacean images height
	 * 
	 * @author MEU CJA
	 * @return double returns the crustacean image height
	 * 
	 *
	 * 
	 */
	public double getCrustHeight() {
		return crustImgHeight;
	}

	/**
	 * returns the toolToggleGroup field.
	 * 
	 * @return ToggleGroup
	 */
	public ToggleGroup getToolToggleGroup() {
		return toolToggleGroup;
	}

}