package pkgView;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * used for the title and cutscene screens. Created on 10.06.2019
 * 
 * @author Matthew
 *
 *
 *
 */
public class CutScreenView extends View {

	private HBox bigScoreBoxAligner = new HBox();
	private Button startButton; // functionality as start/stop/continue button.

	// -------------------Title Screen
	// Background/Images---------------------------------------------------
	private Image bg = new Image("images/underwater2.png");
	VBox startButtonBox = new VBox(8);
	protected String gameTitle = "Everlasting Estuary Enterprise";
	private Image fishImg1 = new Image("images/fish_bass_right.png");
	private Image fishImg2 = new Image("images/fish_trout_left.png");
	private Image robotImg = new Image("images/robotW_sized.png");
	private double fish1X = (canvasWidth / 2.0);
	private Label titleLabel;
	// --------------------Label for end
	// screen-----------------------------------------------
	private String textLabel;
	private Label dispLabel = new Label();

	// ------------------Offsets and Images for
	// CutScreens----------------------------------------------
	private Image cutImage = new Image("images/Cutscene_first.png");
	private Label cutLabel;

	// ------------------Setting Constants -------------------------------------
	private static final double Y_OFFSET = (double) 120 / 1080;
	private static final double X_OFFSET = (double) 720 / 1920;
	private static final int CUT_WIDTH = 800;
	private static final int BUTTON_OFFSET = 300;
	private static final int SCORE_DIFF = 100;
	private static final double FISH_INCR = 1.5;
	private static final double FISH_1_Y_OFF = 0.75;
	private static final double FISH_2_Y_OFF = 0.25;
	private static final double ROBOT_X_OFF = 2;
	private static final double ROBOT_Y_OFF = 0.2;
	private static final Font FONT_FOR_TITLE = new Font(100);
	private static final Font FONT_FOR_TEXT = new Font(35);

	/**
	 * Constructor for CutScreenView, these are the screen in between Games and the
	 * Star screen and the end screen for the 3 games
	 * 
	 * @param width:  double the Width of the Screen
	 * @param height: double the Height of the Screen
	 */
	public CutScreenView(double width, double height) {
		super(width, height);

		// ---------- Generating javafx canvas and obtaining graphics
		// context---------------
		root = new Group();
		theScene = new Scene(root);
		Canvas canvas = new Canvas(canvasWidth, canvasHeight);
		root.getChildren().add(canvas);
		gc = canvas.getGraphicsContext2D();
		// ---------- Setting start button for title
		// screen------------------------------------
		startButton = new Button("Start Game");
		startButton.setAlignment(Pos.CENTER);
		startButton.setFocusTraversable(false);
		startButtonBox.setAlignment(Pos.CENTER);
		startButtonBox.setPrefWidth(canvasWidth);
		startButtonBox.setPrefHeight(canvasHeight);
		// ---------- Setting titleLabel for title
		// screen------------------------------------
		titleLabel = new Label();
		titleLabel.setText(gameTitle);
		titleLabel.setFont(FONT_FOR_TITLE);
		titleLabel.setAlignment(Pos.TOP_CENTER);
		titleLabel.setPrefWidth(canvasWidth);
		// --------- Creating label for cutScreens
		// ----------------------------------------
		cutLabel = new Label();
		cutLabel.setFont(FONT_FOR_TEXT);
		cutLabel.setPrefWidth(CUT_WIDTH);
		cutLabel.setWrapText(true);
		// -------- Formatting dispLabel ----------------------------------------------
		dispLabel.setFont(new Font(30.0));
		dispLabel.setPadding(INSETS);
		// --------- Adding to root ---------------------------------------------------
		startButtonBox.getChildren().add(titleLabel);
		startButtonBox.getChildren().add(startButton);
		root.getChildren().add(titleLabel);
		root.getChildren().add(cutLabel);
		root.getChildren().add(startButtonBox);
	}

	/**
	 *
	 * Displays the Start screen for the Gam with a background and the start BUtton
	 */
	public void displayStartScreen() {
		dispLabel.setText("");
		fish1X = fish1X + FISH_INCR;
		if (fish1X > canvasWidth + fishImg1.getWidth()) {
			fish1X = (0 - fishImg1.getWidth());
		}
		gc.clearRect(0, 0, canvasWidth, canvasHeight);
		gc.drawImage(bg, 0, 0, canvasWidth, canvasHeight);
		gc.drawImage(fishImg1, fish1X, canvasHeight * FISH_1_Y_OFF, fishImg1.getWidth(), fishImg1.getHeight());
		gc.drawImage(fishImg2, canvasWidth - fish1X, canvasHeight * FISH_2_Y_OFF);
		gc.drawImage(robotImg, canvasWidth - fish1X + ROBOT_X_OFF * fishImg2.getWidth(), canvasHeight * ROBOT_Y_OFF);
		startButton.setText("Start Game");
	}

	/**
	 * 
	 * Displays the End Screen( text and reset Buttons) after all the 3 Games,
	 * 
	 * @param econScore    double , Econ Score for the End of the game Screen
	 * @param envirmScore  double, Envimr Score for the End of the game Screen
	 * @param overallScore double,Overall Score for the End of the game Screen
	 */
	public void displayEndScreen(double econScore, double envirmScore, double overallScore) {
		gc.clearRect(0, 0, canvasWidth, canvasHeight);
		cutLabel.setText("");
		this.titleLabel.setText(gameTitle);

		if ((econScore - envirmScore) > SCORE_DIFF) {
			textLabel = "Great job! But your economic health is a little larger than your estuary health" + "\n"
					+ "Next time try to increase the health levels together!";
		} else if ((envirmScore - econScore) > SCORE_DIFF) {
			textLabel = "Great job! But your evironmental health is a little larger than your estuary health" + "\n"
					+ "Next time try to increase the health levels together!";
		} else {
			textLabel = "Great job increasing the economic and estuary health together!";
		}

		dispLabel.setText(textLabel);
		dispLabel.setStyle("-fx-background-color: rgba(255, 255, 255, 0.5); -fx-background-radius: 10;");
		dispLabel.setWrapText(true);

		root.getChildren().remove(startButton);

		bigScoreBoxAligner.setPrefWidth(canvasWidth);
		bigScoreBoxAligner.setAlignment(Pos.BASELINE_CENTER);

		bigScoreBox = new VBox();
		bigScoreBox.setMinSize(canvasWidth, canvasHeight);
		bigScoreBox.setSpacing(20);
		bigScoreBox.getChildren().add(startButton);
		bigScoreBoxAligner.getChildren().add(bigScoreBox);
		bigScoreBox.setAlignment(Pos.CENTER);
		root.getChildren().add(bigScoreBoxAligner);

		drawBars(econScore, envirmScore, overallScore, bigScoreBox);
		bigScoreBox.getChildren().add(new Group(dispLabel));

		gc.drawImage(bg, 0, 0, canvasWidth, canvasHeight);

		startButton.setText("Reset Game");
	}

	/**
	 * 
	 * Display the Text for the Screen in between game and the continue button
	 * 
	 * @param text: String, Text to to Display
	 */
	public void displayCutScreen(String text) {
		this.titleLabel.setText("");
		startButton.setText("Continue");
		startButtonBox.setPrefSize(BUTTON_OFFSET, BUTTON_OFFSET);
		startButtonBox.setTranslateX(canvasWidth - BUTTON_OFFSET);
		startButtonBox.setTranslateY(canvasHeight - BUTTON_OFFSET);

		gc.clearRect(0, 0, canvasWidth, canvasHeight);
		gc.drawImage(cutImage, 0, 0, canvasWidth, canvasHeight);
		cutLabel.setTranslateY(Y_OFFSET * canvasHeight);
		cutLabel.setTranslateX(X_OFFSET * canvasWidth);
		cutLabel.setText(text);
		cutLabel.setFont(FONT_FOR_TUTORIAL);
		cutLabel.setWrapText(true);
	}

	/**
	 * returns the Button to click on.
	 * 
	 * @return Button
	 */
	public Button getStartButton() {
		return startButton;
	}

}
