package pkgView;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

//TODO timer for Decision

/**
 * Used to handle the view for the decision Created on 11/3/2019
 * 
 * @author Theodor
 *
 * 
 */

public class DecisionView extends View {

	private ToggleGroup decisionToggleGroup;
	private Button doButton;
	private int decisionNumber;
	private Image dec1FirstChoice = new Image("images/bg1.png");;
	private Image dec1SecondChoice = new Image("images/bg2.png");;
	private Image dec2FirstChoice = new Image("images/bg3.png");;
	private Image dec2SecondChoice = new Image("images/bg4.png");;
	private Image dec3FirstChoice = new Image("images/bg5.png");;
	private Image dec3SecondChoice = new Image("images/bg6.png");;
	private Image[] bgArray = { dec1FirstChoice, dec1SecondChoice, dec2FirstChoice, dec2SecondChoice, dec3FirstChoice,
			dec3SecondChoice };

	private final static int IMG_VIEW_HEIGHT = 60;
	private final static int IMG_VIEW_WIDTH = 75;

	private Image plusImg = new Image("images/plusSized.png");
	private Image minusImg = new Image("images/minusSized.png");
	private Image leafImg = new Image("images/Leaf.png");
	private Image econImg = new Image("images/Economic.png");

	private Image shipImg = new Image("images/G1_ship.png");
	private Image fishImg = new Image("images/fish_bass_right.png");
	private Image probeImg = new Image("images/Probe_Pending_1.png");

	private ImageView plusV = new ImageView(plusImg);
	private ImageView minusV = new ImageView(minusImg);
	private ImageView leafV = new ImageView(leafImg);
	private ImageView econV = new ImageView(econImg);

	private ImageView shipV = new ImageView(shipImg);
	private ImageView fishV = new ImageView(fishImg);
	private ImageView probeV = new ImageView(probeImg);

	private Label decPlusLabel = new Label();
	private Label decMinusLabel = new Label();
	private Label plusLabel = new Label("", plusV);
	private Label minusLabel = new Label("", minusV);

	/**
	 * Constructor for DecisionView, this view creates the decision before each of
	 * the mini games
	 * 
	 * @param decisionNumber int, which decision We are at
	 * @param econScore      double, Econ score which we want to display
	 * @param envirmScore    double, Envirm score which we want to display
	 * @param overallScore   double, Overall score which we want to display
	 * @param width          double, width of the screen
	 * @param height         double, height of the screen
	 * @param decisionString String, this is the Question for the Decision
	 */
	public DecisionView(int decisionNumber, double econScore, double envirmScore, double overallScore, double width,
			double height, String decisionString) {

		super(width, height);

		this.decisionNumber = decisionNumber + 1;
		root = new Group();

		// ----------------------------------------------

		dec1FirstChoice = new Image("images/bg1.png");
		dec1SecondChoice = new Image("images/bg2.png");
		dec2SecondChoice = new Image("images/bg4.png");
		dec2FirstChoice = new Image("images/bg3.png");
		dec3FirstChoice = new Image("images/bg6.png");
		dec3SecondChoice = new Image("images/bg5.png");

		plusV.setFitHeight(IMG_VIEW_HEIGHT);
		plusV.setFitWidth(IMG_VIEW_WIDTH);
		minusV.setFitHeight(IMG_VIEW_HEIGHT);
		minusV.setFitWidth(IMG_VIEW_WIDTH);
		leafV.setFitHeight(IMG_VIEW_HEIGHT);
		leafV.setFitWidth(IMG_VIEW_WIDTH);
		econV.setFitHeight(IMG_VIEW_HEIGHT);
		econV.setFitWidth(IMG_VIEW_WIDTH);
		shipV.setFitHeight(IMG_VIEW_HEIGHT);
		shipV.setFitWidth(IMG_VIEW_WIDTH);
		fishV.setFitHeight(IMG_VIEW_HEIGHT);
		fishV.setFitWidth(IMG_VIEW_WIDTH);
		probeV.setFitHeight(IMG_VIEW_HEIGHT);
		probeV.setFitWidth(IMG_VIEW_WIDTH);

		Canvas canvas = new Canvas(canvasWidth, canvasHeight);
		gc = canvas.getGraphicsContext2D();
		gc.clearRect(0, 0, canvasWidth, canvasHeight);
		gc.drawImage(bgArray[2 * (this.decisionNumber - 1)], 0, 0, canvasWidth, canvasHeight); // makes sure the correct
																								// image is drawn on
																								// creation
		// -----------------------------------------------------------------------------
		VBox everythingBox = new VBox();

		everythingBox.setPrefHeight(canvasHeight);
		everythingBox.setPrefWidth(canvasWidth);

		bigScoreBox = new VBox();
		bigScoreBox.setPrefHeight(canvasHeight * 2 / 3);
		bigScoreBox.setPrefWidth(canvasWidth * 2 / 3);
		bigScoreBox.setAlignment(Pos.TOP_CENTER);
		bigScoreBox.setPadding(INSETS);
		;

		drawScore(econScore, envirmScore, overallScore, decisionString, bigScoreBox);

		decisionToggleGroup = new ToggleGroup();

		// ---------------------creating plusBox and
		// minusBox--------------------------------------
		HBox plusBox = new HBox();
		plusBox.getChildren().add(plusLabel);
		plusBox.getChildren().add(decPlusLabel);
		plusBox.setAlignment(Pos.CENTER);

		HBox minusBox = new HBox();
		minusBox.getChildren().add(minusLabel);
		minusBox.getChildren().add(decMinusLabel);
		minusBox.setAlignment(Pos.CENTER);

		// -----------------------------------------------------------------------------

		VBox decisionBox = new VBox();
		decisionBox.setMinSize(350, 250);
		// Creating new Radio buttons.

		// ---------------------Create a HBox-------------------------------------------
		HBox buttonBox = new HBox();
		buttonBox.setSpacing(10);

		RadioButton yesButton = new RadioButton("Yes");
		yesButton.setUserData("y");
		RadioButton noButton = new RadioButton("No");
		noButton.setUserData("n");
		doButton = new Button("Do It ");

		yesButton.setToggleGroup(decisionToggleGroup);
		noButton.setToggleGroup(decisionToggleGroup);

		buttonBox.getChildren().addAll(yesButton, noButton, doButton);
		buttonBox.setAlignment(Pos.CENTER);
		buttonBox.setPadding(INSETS);
		buttonBox.setStyle("-fx-background-color: rgba(255, 255, 255, 0.5); -fx-background-radius: 10;");
		decisionBox.getChildren().addAll(new Group(buttonBox));
		decisionBox.getChildren().addAll(plusBox, minusBox);
		decisionBox.setAlignment(Pos.CENTER);

		everythingBox.getChildren().add(bigScoreBox);
		everythingBox.getChildren().add(decisionBox);
		root.getChildren().add(canvas); // First try
		root.getChildren().add(everythingBox);

		theScene = new Scene(root);

	}

	/**
	 * Changes the background for the different Decisions
	 * 
	 * @param firstOrSecondChoice int,background number to change to
	 */
	public void changeBackgroundWithDecision(int firstOrSecondChoice) {
		gc.clearRect(0, 0, canvasWidth, canvasHeight);

		if (firstOrSecondChoice == 0) {

			gc.drawImage(bgArray[2 * (decisionNumber - 1)], 0, 0, canvasWidth, canvasHeight); // logic with picking the
																								// right image.

		} else {

			gc.drawImage(bgArray[1 + (decisionNumber - 1) * 2], 0, 0, canvasWidth, canvasHeight);
		}
	}

	/**
	 * This display what gets increased or decreased because of the decision for
	 * each of The Decision.
	 * 
	 * @param decision boolean,true if one decision is chosen, false if the other.
	 *                 Used to switch labels.
	 */
	public void changeLabelWithDecision(boolean decision) {
		if (decisionNumber == 1) {
			if (decision) {
				decPlusLabel.setGraphic(shipV);
				decMinusLabel.setGraphic(leafV);
			} else {
				decPlusLabel.setGraphic(leafV);
				decMinusLabel.setGraphic(shipV);
			}
		} else if (decisionNumber == 2) {
			if (decision) {
				decPlusLabel.setGraphic(econV);
				decMinusLabel.setGraphic(fishV);
			} else {
				decPlusLabel.setGraphic(fishV);
				decMinusLabel.setGraphic(econV);
			}
		} else if (decisionNumber == 3) {
			if (decision) {
				decPlusLabel.setGraphic(probeV);
				decMinusLabel.setGraphic(econV);
			} else {
				decPlusLabel.setGraphic(econV);
				decMinusLabel.setGraphic(probeV);
			}
		}
	}

	/**
	 * returns the Button for the Decision
	 * 
	 * @return Button
	 * 
	 */
	public Button getButton() {
		return doButton;
	}

	/**
	 * returns the Toggle Group from the decision
	 * 
	 * @return ToggleGroup
	 */

	public ToggleGroup getToggleGroup() {
		return decisionToggleGroup;
	}

	/**
	 * returns the decision number
	 * 
	 * @return int
	 */
	public int getDecisionNumber() {
		return this.decisionNumber;
	}

}
