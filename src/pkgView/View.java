package pkgView;

import javafx.geometry.Insets;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 * Class - View - used as an abstract class that all the other views inherit
 * Created on 11/1/2019
 * 
 * 
 * @author Theodor
 *
 * 
 */

public abstract class View {

	double canvasWidth;
	double canvasHeight;

	Group root;
	Scene theScene;

	// -----------------------------------Boxes
	private HBox singleScoreBoxEcon;
	private HBox singleScoreBoxEnvir;
	VBox bigScoreBox;
	private HBox timerBox;
	private HBox singleScoreBoxOver;
	private VBox scoreBox;
	private VBox textBox;
	// --------------------------------------------------------------------------------

	// ------------------------------------------Graphic Contexts
	GraphicsContext gcEnvirScore;
	GraphicsContext gcEconScore;
	GraphicsContext gcOverScore;
	GraphicsContext gc;
	GraphicsContext pgc;

	// --------------------------------------------------------------------------------

	protected Label tutorialLabel = new Label();

	Button skipGameButton;

	// ------------------Images
	ImageView leaf = new ImageView(new Image("images/Leaf.png"));
	ImageView econ = new ImageView(new Image("images/Economic.png"));

	// --------------------------Statics
	private final static int IMG_VIEW_HEIGHT = 25;
	protected final static int IMG_VIEW_WIDTH = 50;

	protected final static Font FONT_FOR_LABELS = new Font(30);
	private final static Font FONT_FOR_TIMER = new Font(20);
	protected final static Font FONT_FOR_TUTORIAL = new Font(35);
	private final static int BUTTON_HEIGHT = 40;
	private final static int BUTTON_WIDTH = 100;
	private final static int SPACING_BIG_SCORE_BOX = 20;
	private final static int SPACING_SCORE_BOX = 4;

	protected static final Insets INSETS = new Insets(10, 10, 10, 10);

	// -------------------------------------

	/**
	 * Method View(double width, double height) Purpose - Constructor for the view ,
	 * sets some values and height and width for the scenes
	 * 
	 * @param width:  double width of the Screen
	 * @param height: double height of the Screen
	 */
	public View(double width, double height) {
		econ.setFitHeight(IMG_VIEW_HEIGHT * 2);
		econ.setFitWidth(2 * IMG_VIEW_WIDTH / 2);

		leaf.setFitHeight(IMG_VIEW_HEIGHT * 2);
		leaf.setFitWidth(2 * IMG_VIEW_WIDTH / 2);

		canvasWidth = width;
		canvasHeight = height;

		double buttonPlacementX = canvasWidth - BUTTON_WIDTH;
		double buttonPlacementY = canvasHeight - BUTTON_HEIGHT;

		skipGameButton = new Button("Skip Game");
		skipGameButton.setLayoutX(buttonPlacementX);
		skipGameButton.setLayoutY(buttonPlacementY);
		skipGameButton.setFocusTraversable(false);
		skipGameButton.setMaxSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		skipGameButton.setStyle("-fx-font-size:15");

	}

	/**
	 * Method setScene(Stage theStage) Purpose - Set the scene to the given stage,
	 * this actually draws the view from which i call it to the stage.
	 * 
	 * @param theStage: Stage, the stage where this this should be added to.
	 */
	public void setScene(Stage theStage) {
		theStage.setScene(theScene);
		String css = this.getClass().getResource("style.css").toExternalForm();
		theScene.getStylesheets().add(css);

	}

	/**
	 * Method - drawScore( double econScore, double envirmScore,double
	 * overallScore,String text,VBox bigScoreBox) Purpose - draws the score on the
	 * screen. Makes a Score Box and Draws the score on the screen
	 * 
	 * @param econScore:    double the Econ Score to draw
	 * @param envirmScore:  double the Envirm Score to draw
	 * @param overallScore: double the OVerall Score to draw
	 * @param text:         : String the Text i want to Display
	 * @param bigScoreBox:  VBox, holds the different Score Boxes
	 */
	public void drawScore(double econScore, double envirmScore, double overallScore, String text, VBox bigScoreBox) {
		bigScoreBox.getChildren().clear();

		Label endLabel = new Label();
		endLabel.setText(text);
		endLabel.setWrapText(true);
		endLabel.setTextAlignment(TextAlignment.CENTER);
		endLabel.setFont(FONT_FOR_LABELS);
		endLabel.setStyle("-fx-background-color: rgba(255, 255, 255, 0.5); -fx-background-radius: 10;");

		bigScoreBox.getChildren().add(endLabel);
		bigScoreBox.setSpacing(SPACING_BIG_SCORE_BOX);
		drawBars(econScore, envirmScore, overallScore, bigScoreBox);
	}

	/**
	 * Method - drawBars(double econScore,double envirmScore, double
	 * overallScore,VBox bigScoreBox)
	 * 
	 * Purpose - This Draws the bars for the given Scores inside a Box
	 * 
	 * @param econScore:    double the Econ Score to draw
	 * @param envirmScore:  double the Envirm Score to draw
	 * @param overallScore: double the OVerall Score to draw
	 * @param bigScoreBox:  VBox, holds the different Score Boxes
	 */
	public void drawBars(double econScore, double envirmScore, double overallScore, VBox bigScoreBox) {
		scoreBox = new VBox();
		scoreBox.setAlignment(Pos.CENTER);

		Label overLabel = new Label();
		overLabel.setText("Overall Score" + " " + Double.toString(overallScore));
		overLabel.setFont(FONT_FOR_LABELS);

		Label envirLabel = new Label();
		envirLabel.setText(Double.toString(envirmScore));
		envirLabel.setGraphic(leaf);
		envirLabel.setFont(FONT_FOR_LABELS);

		Label econLabel = new Label();
		econLabel.setText(Double.toString(econScore));
		econLabel.setGraphic(econ);
		econLabel.setFont(FONT_FOR_LABELS);

		singleScoreBoxEcon = new HBox(econLabel);
		singleScoreBoxEcon.setStyle("-fx-background-color: rgba(255, 255, 255, 0.5); -fx-background-radius: 10;");
		singleScoreBoxEcon.setSpacing(SPACING_SCORE_BOX);
		singleScoreBoxEcon.setAlignment(Pos.CENTER_LEFT);
		singleScoreBoxEcon.setPadding(INSETS);

		singleScoreBoxEnvir = new HBox(envirLabel);
		singleScoreBoxEnvir.setSpacing(SPACING_SCORE_BOX);
		singleScoreBoxEnvir.setStyle("-fx-background-color: rgba(255, 255, 255, 0.5); -fx-background-radius: 10;");
		singleScoreBoxEnvir.setAlignment(Pos.CENTER_LEFT);
		singleScoreBoxEnvir.setPadding(INSETS);

		singleScoreBoxOver = new HBox(overLabel);
		singleScoreBoxOver.setStyle("-fx-background-color: rgba(255, 255, 255, 0.5); -fx-background-radius: 10;");
		singleScoreBoxOver.setSpacing(SPACING_SCORE_BOX);
		singleScoreBoxOver.setAlignment(Pos.CENTER_LEFT);
		singleScoreBoxOver.setPadding(INSETS);

		// ---------------------This draws the Bar for EconScore
		Canvas canvasEcon = new Canvas(econScore, IMG_VIEW_HEIGHT * 1.5);
		HBox canvasEconBox = new HBox();
		canvasEconBox.setAlignment(Pos.CENTER_LEFT);
		canvasEconBox.getChildren().add(new Group(canvasEcon));
		singleScoreBoxEcon.getChildren().add(new Group(canvasEconBox));
		gcEconScore = canvasEcon.getGraphicsContext2D();
		// -------------------------------------------------------------

		// ---------------------This draws the Bar for EnvirmScore
		Canvas canvasEnvirm = new Canvas(envirmScore, IMG_VIEW_HEIGHT * 1.5);
		HBox canvasEnvirmBox = new HBox();
		canvasEnvirmBox.setAlignment(Pos.CENTER);
		canvasEnvirmBox.getChildren().add(new Group(canvasEnvirm));
		singleScoreBoxEnvir.getChildren().add(new Group(canvasEnvirmBox));
		gcEnvirScore = canvasEnvirm.getGraphicsContext2D();
		// -------------------------------------------------------------

		// ---------------------This draws the Bar for Overall Score
		Canvas canvasOverall = new Canvas(overallScore, IMG_VIEW_HEIGHT * 1.5);
		HBox canvasOverallBox = new HBox();
		canvasOverallBox.setAlignment(Pos.CENTER_LEFT);
		canvasOverallBox.getChildren().add(new Group(canvasOverall));
		singleScoreBoxOver.getChildren().add(new Group(canvasOverallBox));
		gcOverScore = canvasOverall.getGraphicsContext2D();
		// -------------------------------------------------------------
		// If EconScore and EnvirmScore are to far apart it draws them red
		if (Math.abs(econScore - envirmScore) > 100) {
			canvasEcon.getGraphicsContext2D().setFill(Paint.valueOf("Red"));
			canvasEnvirm.getGraphicsContext2D().setFill(Paint.valueOf("Red"));
			canvasOverall.getGraphicsContext2D().setFill(Paint.valueOf("Red"));
		}
		// Else green
		else {
			canvasOverall.getGraphicsContext2D().setFill(Paint.valueOf("Green"));
			canvasEcon.getGraphicsContext2D().setFill(Paint.valueOf("Green"));
			canvasEnvirm.getGraphicsContext2D().setFill(Paint.valueOf("Green"));
		}

		gcOverScore.fillRect(0, 0, overallScore, IMG_VIEW_HEIGHT * 1.5);
		gcEnvirScore.fillRect(0, 0, envirmScore, IMG_VIEW_HEIGHT * 1.5);
		gcEconScore.fillRect(0, 0, econScore, IMG_VIEW_HEIGHT * 1.5);

		scoreBox.setSpacing(SPACING_BIG_SCORE_BOX);
		scoreBox.getChildren().add(new Group(singleScoreBoxEcon));
		scoreBox.getChildren().add(new Group(singleScoreBoxEnvir));
		scoreBox.getChildren().add(new Group(singleScoreBoxOver));

		bigScoreBox.getChildren().add(scoreBox);

	}

	/**
	 * Method - drawGameScore(double econScore, double envirmScore, double
	 * overallScore, VBox bigScoreBox) Purpose - Draws the Score in Game
	 * 
	 * @param econScore:    double the Econ Score to draw
	 * @param envirmScore:  double the Envirm Score to draw
	 * @param overallScore: double the OVerall Score to draw
	 * @param bigScoreBox:  VBox, holds the different Score Boxes
	 */
	public void drawGameScore(double econScore, double envirmScore, double overallScore, VBox bigScoreBox) {
		bigScoreBox.getChildren().clear();

		Label overLabel = new Label();
		overLabel.setText("Overall Score" + " " + Double.toString(overallScore));
		overLabel.setFont(FONT_FOR_LABELS);
		overLabel.setAlignment(Pos.CENTER);
		overLabel.setStyle("-fx-background-color: rgba(100, 100, 100, 0.5); -fx-background-radius: 10;");

		Label envirLabel = new Label();
		envirLabel.setText(Double.toString(envirmScore));
		envirLabel.setGraphic(leaf);
		envirLabel.setFont(FONT_FOR_LABELS);
		envirLabel.setAlignment(Pos.CENTER);
		envirLabel.setStyle("-fx-background-color: rgba(100, 100, 100, 0.5); -fx-background-radius: 10;");

		Label econLabel = new Label();
		econLabel.setText(Double.toString(econScore));

		econLabel.setGraphic(econ);
		econLabel.setFont(FONT_FOR_LABELS);
		econLabel.setAlignment(Pos.CENTER);
		econLabel.setStyle("-fx-background-color: rgba(100, 100, 100, 0.5); -fx-background-radius: 10;");

		scoreBox = new VBox(overLabel, econLabel, envirLabel);
		scoreBox.setSpacing(SPACING_SCORE_BOX);
		scoreBox.setAlignment(Pos.TOP_CENTER);
		bigScoreBox.getChildren().add(scoreBox);

	}

	/**
	 * Method - createTimer(long currentTime) Purpose - Draws the current remaining
	 * time on Screen
	 * 
	 * @param currentTime: long, the time We have for this game
	 */
	public void createTimer(long currentTime) {
		Label timerLabel = new Label(Double.toString(currentTime));
		timerLabel.setFont(FONT_FOR_TIMER);

		timerBox = new HBox(timerLabel);

		timerBox.setAlignment(Pos.TOP_RIGHT);
		timerLabel.setStyle(
				"-fx-background-color: rgba(100, 100, 100, 0.5); -fx-background-radius: 10;-fx-text-fill: yellow;");

		VBox bigTimerBox = new VBox(timerBox);
		bigTimerBox.setMinSize(canvasWidth, canvasHeight);

		root.getChildren().add(bigTimerBox);

	}

	/**
	 * Method - updateTimer(long timeRemaining) Purpose - updates the remaining time
	 * on screen
	 * 
	 * @param timeRemaining: long, the time which is remaining for the current game
	 */
	public void updateTimer(long timeRemaining) {
		for (Node n : timerBox.getChildren()) {
			Label timerLabel = (Label) n;
			timerLabel.setText("Time remaining: " + Long.toString(timeRemaining / 1000));
		}

	}

	/**
	 * Method - endAnimation(double overallScore,double econScore,double
	 * envirmScore,String text,VBox bigScoreBox)
	 * 
	 * Purpose - Prepares the Screen for the End Animation
	 * 
	 * @param overallScore: double the OVerall Score to draw
	 * @param econScore:    double the Econ Score to draw
	 * @param envirmScore:  double the Envirm Score to draw
	 * @param text:         String, the String which should be displayed in the
	 *                      EndScreen of each Game
	 * @param bigScoreBox:  VBox, holds the different Score Boxes
	 */
	public void endAnimation(double overallScore, double econScore, double envirmScore, String text, VBox bigScoreBox) {
		bigScoreBox.getChildren().clear();
		bigScoreBox = new VBox();
		bigScoreBox.setMinSize(canvasWidth, canvasHeight);
		bigScoreBox.setAlignment(Pos.CENTER);

		drawScore(econScore, envirmScore, overallScore, text, bigScoreBox);
		root.getChildren().add(bigScoreBox);

	}

	/**
	 * Method - displayText(String Text) Purpose - Displays the Text for Tutorial on
	 * Screen
	 * 
	 * @param text: String, the Text to Display
	 */
	public void displayText(String text) {

		textBox = new VBox();

		textBox.setPrefSize(canvasWidth, canvasHeight);
		textBox.setAlignment(Pos.BOTTOM_CENTER);
		textBox.getChildren().add(new Group(tutorialLabel));

		root.getChildren().add(1, textBox);
		tutorialLabel.setWrapText(true);
		tutorialLabel.setMaxWidth(0.75 * canvasWidth);
		tutorialLabel.setText(text);

		if (!text.equals("")) {
			tutorialLabel.setStyle("-fx-background-color: rgba(255, 255, 255, 0.75); -fx-background-radius: 10;");

		}

	}

	/**
	 * Method - getTextBox() Purpose - returns the textBox field
	 * 
	 * @return - textBoox
	 */
	public VBox getTextBox() {
		return textBox;

	}

	/**
	 * Method - getBigScoreBox() Purpose - returns the bigScorebox field.
	 * 
	 * @return - bigScoreBox
	 */
	public VBox getBigScoreBox() {
		return bigScoreBox;
	}

	/**
	 * Method - getWidth() Purpose - returns the canvasWidth
	 * 
	 * @return - canvasWidth
	 */
	public double getWidth() {
		return canvasWidth;
	}

	/**
	 * Method getHeight() Purpose - returns the canvasHeight field
	 * 
	 * @return canvasHeight
	 */
	public double getHeight() {
		return canvasHeight;
	}

	/**
	 * Method - getScene() Purpose - returns theScene field.
	 * 
	 * @return theScene
	 */
	public Scene getScene() {
		return theScene;
	}

	/**
	 * Method - getSkipButton() Purpose - returns the skipGameButton field
	 * 
	 * @return - skipGameButton
	 */
	public Button getSkipButton() {
		return skipGameButton;
	}

	/**
	 * method getRoot() Purpose - returns the value in the root field.
	 * 
	 * @return root.
	 */
	public Group getRoot() {
		return this.root;
	}

}
