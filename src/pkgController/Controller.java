package pkgController;

import java.util.ListIterator;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pkgModel.G1Model;
import pkgModel.G2Model;
import pkgModel.G3Model;
import pkgModel.GameStatsModel;
import pkgModel.Model;
import pkgTutorial.G1Tutorial;
import pkgTutorial.G2Tutorial;
import pkgTutorial.G3Tutorial;
import pkgView.DecisionView;
import pkgView.G1View;
import pkgView.G2View;
import pkgView.G3View;
import pkgView.CutScreenView;
import pkgView.View;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.Toggle;
import javafx.scene.layout.VBox;

/**
 *  Used to handle the communication between Models and Views.
 * 
 * Created on 11/1/2019
 * @author Theo, Chandler, Stephen and Matthew
 */
public class Controller extends Application {

	// ------------View and Model
	// Fields------------------------------------------------------------------------------------------------------------------------------------------------------

	private GameStatsModel overModel;
	private CutScreenView cutScreenView;

	private G1View g1View;
	private G1Model g1Model;
	private G1Tutorial g1Tutorial;

	private G2View g2View;
	private G2Model g2Model;
	private G2Tutorial g2Tutorial;

	private G3View g3View;
	private G3Model g3Model;
	private G3Tutorial g3Tutorial;
	private G3View g3ViewTutorial;

	private DecisionView decisionView;

//----------------Integer Fields to store model data for transfer--------------------------------------------------------------------------------------------------------------------------------------------

	private double clickOnPosX; // Initial robot position
	private double clickOnPosY; // Initial robot position, may need to be changed
	private double econScore = 0;
	private double envirmScore = 0;

	private int yIncr = 0;
	private int xIncr = 0;
	private int storyTextCounter = 0;

	private int tool = 0;
	final static int NUM_GAMES = 3;
//-----------------Long fields to store timer data for transfer-------------------------------------------------------------------------------------------------------------------------------------------------

	private long timeForScore = 1000;
	long currentTimer = 0;
//------------------String fields for storing string data for views and Saving files------------------------------------------------------------------------------------------------------------------------------------------------

	private String name1 = "SaveOfG1Model";
	private String name2 = "SaveOfG2Model";
	private String name3 = "SaveOfG3Model";
	private String name0 = "SavedGameStats";
	String[] decisionStrings = {
			"Expanding the port will increase the number of ships in our harbor. Do you want to expand the port?",
			"Building a gas power plant decreases the number of fish, but it will increase our economic health. Should we build the power plant? ",
			"Increasing funding for estuary research provide more probes for our scientists. Should we invest?" };
//------------------boolean fields to store data for models and view changes------------------------------------------------------------------------------------------------------------------------------------------------

	private boolean takeAction;
	private boolean firstDecision;
	private boolean showCutscene;
	private boolean startTutorial1 = false;
	private boolean startTutorial2 = false;
	private boolean startTutorial3 = false;
	private boolean tutorialEnd = false;
	private boolean probeInteract;
	private boolean forScore;
	private boolean endScore;
//------------------------------------------------------------------------------------------------------------------------------------------------------------------

//----------------Statics
	private static final int NET = 0;
	private static final int CAMERA = 1;
	private static final int TIME_INCREMENT = 10;
	private static final int TUTORIAL_SCROLL_SPEED = 5;
	private static final int GAME_SCROLL_SPEED = 8;

	private static final int MAZE_SIZE_TUTORIAL = 5;
	private static final int SAVE_TIME = 1000;
	private static final int DENOMINATOR_FOR_SCORE_G3 = 2000;
	private static final double ECON_INCREASE_DECSION1 = 50;

	private static final double ECON_DECREASE_DECSION1 = -25;

	private static final double ENVIRM_INCREASE_DECSION1 = 50;
	private static final double ENVIRM_DECREASE_DECSION1 = -25;

	private static final double ECON_INCREASE_DECSION2 = 75;

	private static final double ECON_DECREASE_DECSION2 = -50;

	private static final double ENVIRM_INCREASE_DECSION2 = 75;
	private static final double ENVIRM_DECREASE_DECSION2 = -50;

	private static final double ECON_INCREASE_DECSION3 = 45;

	private static final double ECON_DECREASE_DECSION3 = -20;

	private static final double ENVIRM_INCREASE_DECSION3 = 45;
	private static final double ENVIRM_DECREASE_DECSION3 = -20;

	private static final long TIME_FOR_G1 = 30000;
	private static final long TIME_FOR_G2 = 35000;

	private static final long TIME_FOR_G3 = 120000;

	private static final int MAZE_DIMENSIONS = 17;

//----------------------------------------------------
	/**
	 *main method that launches the game
	 * 
	 * Created on 11/1/2019
	 * @author Chandler Amato
	 * @param args, String[]
	 * 
	 */

	public static void main(String[] args) {
		// Launch the application
		launch(args);

	}

	/**
	 * @author Theodor 
	 *         This starts the game.
	 * 
	 * @param theStage  The stage for starting the game.
	 *
	 *
	 * 
	 */
	@Override
	public void start(Stage theStage) {

//---------Sets the game to full screen---------------------------------------------------------------------------------------------------------------------------------------------------------

		Screen screen = Screen.getPrimary();
		Rectangle2D bounds = screen.getVisualBounds();
		theStage.setX(bounds.getMinX());
		theStage.setY(bounds.getMinY());
		double width = bounds.getWidth();
		double height = bounds.getHeight();
		theStage.setWidth(width);
		theStage.setHeight(height);
		theStage.initStyle(StageStyle.UNDECORATED);
//------------End of full screen stuff------------------------------------------------------------------------------------------------------------------------------------------------------

//------------Serializable for loading from a file------------------------------------------------------------------------------------------------------------------------------------------------------

		// FullScreen Stuff
		// try for serialization for gameStats
		try {
			// Reading the object from a file

			FileInputStream inputFile = new FileInputStream(name0);
			ObjectInputStream in = new ObjectInputStream(inputFile);

			// Method for deserialization of object
			overModel = (GameStatsModel) in.readObject();

			in.close();
			inputFile.close();

			System.out.println("GameStats has been deserialized ");

			endScore = true;
			showCutscene = true;

		}

		catch (IOException ex) {
			System.out.println("IOException is caught");
			System.out.println("New GameStarts");
			overModel = new GameStatsModel();

		} catch (ClassNotFoundException ex) {
			System.out.println("ClassNotFoundException is caught");
			System.out.println("New GameStarts");
			overModel = new GameStatsModel();
		}

//---------------End of initial serializable---------------------------------------------------------------------------------------------------------------------------------------------------

		cutScreenView = new CutScreenView(width, height);

		AnimationTimer currTimer = new AnimationTimer() {

			boolean endCheck;

			public void handle(long currentNanoTime) {

				try {
					if (currentTimer <= 0) {

						// Starts title screen, waits for button press to start cutscene 1
//----------------------------------Cutscene 1-------------------------------------------------------------------------------------------------------------------------------

						if (!startTutorial1 && overModel.getGameNumber() == 0 && !showCutscene) {
							cutScreenView.setScene(theStage);
							cutScreenView.displayStartScreen();
							cutScreenView.getStartButton().setOnAction(new EventHandler<ActionEvent>() {
								@Override
								public void handle(ActionEvent event) {
									showCutscene = true;
									// Array containing the text for the cutscene
									String[] storyText = { "Hi! You must be the new Mayor!",
											"We need you to make three decisions that will impact the health of our town's economy and estuary.",
											"Try to boost the health of our town's estuary and economy as high as possible, while keeping them at the same level!",
											"We need to know if we should expand our port, but first we want you to experience piloting one of ships!" };
									// If the cutscene has progressed through all of the text it needs to move on to
									// the tutorial and initialize all the views necessary.
									if (storyTextCounter == 4) {
										startTutorial1 = true;
										g1View = new G1View(width, height, currentTimer); // height was uheight, but

										g1View.setScrollSpeed(TUTORIAL_SCROLL_SPEED);
										g1Tutorial = new G1Tutorial(new GameStatsModel(), g1View.getWidth(),
												g1View.getHeight(), g1View.getShipWidth(), g1View.getShipHeight(),
												g1View.getRockWidth(), g1View.getRockHeight(), g1View.getCanWidth(),
												g1View.getCanHeight());
										g1View.setScene(theStage);
										gameListener(g1View);
										storyTextCounter = 0;
									} else { // Branch executes if there is still text left to show for the cutscene
										cutScreenView.displayCutScreen(storyText[storyTextCounter++]);

									}
								}
							});
						}
//-------------------------------------------End of cutscene 1-----------------------------------------------------------------------------------------------------------------------
//-------------------------------------------Begin Tutorial 1-----------------------------------------------------------------------------------------------------------------------

						// Game tutorial 1, plays after button pressed and startTutorial1Fliped
						endCheck = true;
						if (startTutorial1 && !tutorialEnd) {

							for (boolean bool : g1Tutorial.getTutorialCheckList()) { // loops through the tutorials
																						// Boolean array and checks if
																						// all of them are true. If they
																						// are it is the end of the
																						// tutorial
								endCheck = endCheck && bool;
							}

							if (endCheck) { // If all of the flags in the tutorial checkList are true it means its the
											// end, so it ends the tutorial one.
								VBox temBox = g1View.getTextBox();
								temBox.getChildren().clear();
								tutorialEnd = true;
								startTutorial1 = false;
							}

							g1Tutorial.update(yIncr); // If it is not the end it calls the update on the G1Tutorial View
							g1View.update(g1Tutorial.getPlayer(), g1Tutorial.getObstacleList(), 0, 0, 0, -1);

							if (g1Tutorial.getMovedVertically() == false && !endCheck) { // Used to display the
																							// objectives the player
																							// needs to do to get
																							// through the tutorial.
								g1View.displayText("Press the arrow Keys to move up and down");
							} else if (!g1Tutorial.getAvoidedRock() && !endCheck) {
								g1View.displayText(
										"Use the arrow keys to avoid the rock. If you hit rocks your ship will leak oil and hurt the estuary.");
							} else if (!g1Tutorial.getAvoidedShip() && !endCheck) {
								g1View.displayText(
										"Use the arrow keys to avoid the ship. If you hit other ships oil will leak and hurt the estuary ");
							} else if (!g1Tutorial.getGotCan() && !endCheck) {
								g1View.displayText(
										"Use the arrow keys to collect the can to help clean up the estuary.");
							}

						}
//--------------------------------------------End Tutorial 1 Logic in Controller----------------------------------------------------------------------------------------------------------------------

//--------------------------------------------Begin Cutscene 2 Logic in Controller----------------------------------------------------------------------------------------------------------------------
						if (!startTutorial2 && overModel.getGameNumber() == 1 && !tutorialEnd) {
							if (showCutscene) { // Branch used if cutscene has not been called yet.
								// storyText contains all of the strings that will be displayed when the
								// cutscene is being clicked through
								String[] storyText = {
										"Next, decide whether to build offshore wind turbines or a gas powerplant to power our city.",
										"Please investigate how this decision impacts the wildlife in our estuary.",
										"Investigate using our research robot to photograph indeginous species and capture the invasive species. Try to get them all!" };
								cutScreenView.setScene(theStage);
								cutScreenView.displayCutScreen(storyText[storyTextCounter]);

								cutScreenView.getStartButton().setOnAction(new EventHandler<ActionEvent>() {
									@Override
									public void handle(ActionEvent event) {
										if (storyTextCounter == 2) { // This branch executes if the cutscene is over,
																		// and displayed all of its text.
											startTutorial2 = true;
											showCutscene = false;

											g2View = new G2View(currentTimer, width, height); // height was also once
																								// end
											tool = NET; // Sets the tool to NET
											g2View.setScene(theStage);
											// Initialize the tutorial
											g2Tutorial = new G2Tutorial(firstDecision, overModel, -1, g2View.getWidth(),
													g2View.getHeight(), g2View.getRobotWidth(), g2View.getRobotHeight(),
													g2View.getFishWidth(), g2View.getFishHeight(),
													g2View.getCrustWidth(), g2View.getCrustHeight(), tool);

											clickOnPosX = g2Tutorial.getRobot().getXLoc()
													+ g2View.getRobotWidth() * g2Tutorial.getRobot().getCenterXMult();
											clickOnPosY = g2Tutorial.getRobot().getYLoc()
													+ g2View.getRobotHeight() * g2Tutorial.getRobot().getCenterYMult();

											game2Listener(g2View); // sets up the different listeners for game 2
											storyTextCounter = 0;
										} else { // This branch executes if the tutorial needs to display more text.
											storyTextCounter = storyTextCounter + 1;
											cutScreenView.displayCutScreen(storyText[storyTextCounter]);

										}

									}

								});
							}

						}
//---------------------------------------------------End Cutscene 2 Logic in the controller---------------------------------------------------------------------------------------------------------------
//---------------------------------------------------Begin Tutorial 2 Logic in the Controller---------------------------------------------------------------------------------------------------------------

						if (startTutorial2 && !tutorialEnd) {

							for (Boolean bool : g2Tutorial.getTutorialChecklist()) {
								endCheck = endCheck && bool; // endCheck true if all in TutorialChecklist true
							}

							if (endCheck) { // If the boolean array in Tutorial2 is all true then this ends the tutorial
								tutorialEnd = true;
								startTutorial2 = false;
								VBox temBox = g2View.getTextBox();
								g2View.getRoot().getChildren().remove(temBox); // Removes the text that appears when the
																				// tutorial is being used

							}

//							the 2 lines here are needed to update the model and the view during the tutorial when it is running
							g2Tutorial.update(clickOnPosX, clickOnPosY, takeAction, tool);
							g2View.update(g2Tutorial.getRobot(), g2Tutorial.getAnimalList(),
									g2Tutorial.getObjectiveList(), g2Tutorial.getTimeRemaining(),
									overModel.getEconScore(), overModel.getEnvirmScore(), overModel.getOverallScore());

							if (!g2Tutorial.getTutorialChecklist().get(0)) { // Used along with the tutorialChecklist to
																				// display text giving instruction to
																				// the player. Shows them what to do to
																				// complete the tutorial
								g2View.displayText("Click with mouse to move.");
							} else if (!g2Tutorial.getTutorialChecklist().get(1)) {
								g2View.displayText(
										"Move to invasive fish and click on it with net to remove. The invasive species can be found in the top left.");
							} else if (!g2Tutorial.getTutorialChecklist().get(2)) {
								g2View.displayText(
										"Use buttons in top left to switch tools. Click on the buttons to switch.");
							} else if (!g2Tutorial.getTutorialChecklist().get(3)) {
								g2View.displayText(
										"Select the camera and click on the indeginous species to take a picture of it");
							}

						}
//-------------------------------------------------End Tutorial 2 Logic in the controller-----------------------------------------------------------------------------------------------------------------
//-------------------------------------------------Begin Cutscene 3 logic in the Controller----------------------------------------------------------------------------------------------------------------

						if (!startTutorial3 && overModel.getGameNumber() == 2) {

							if (showCutscene) { // This branch executes if the cutscene is not finished.
								// storyText contains the strings that will be displayed during the cutscene
								String[] storyText = { "Finally, choose if we should invest in more estuary research.",
										"To make an informed choice we first want you to work in the field as a researcher." };

								cutScreenView.setScene(theStage);
								cutScreenView.displayCutScreen(storyText[storyTextCounter]);
								cutScreenView.getStartButton().setOnAction(new EventHandler<ActionEvent>() {
									@Override
									public void handle(ActionEvent event) {
										if (storyTextCounter == 1) { // If the cutscene has displayed all of its text
																		// need to end the cutscene
											showCutscene = false;
											// Initializes all the models and views necessary for game three
											// HUUU

											g3Tutorial = new G3Tutorial(width, height, MAZE_SIZE_TUTORIAL,
													MAZE_SIZE_TUTORIAL, firstDecision);
											g3ViewTutorial = new G3View(g3Tutorial.getMazeMatrix(),
													g3Tutorial.getGridY(), g3Tutorial.getGridX(),
													g3Tutorial.getPlayerX(), g3Tutorial.getPlayerY(), width, height,
													-1);

											g3ViewTutorial.setScene(theStage);
											// end of model and view initialization for game three
											gameListener(g3ViewTutorial); // Sets the listeners for game3

											startTutorial3 = true; // makes the tutorial start for game three
										}

										else { // Branch Executes if the cutscene 3 is not over.
											cutScreenView.displayCutScreen(storyText[storyTextCounter]);
											storyTextCounter = storyTextCounter + 1;
										}

									}

								});
							}
						}
//----------------------------------------------End of Cutscene 3 logic in Controller--------------------------------------------------------------------------------------------------------------------
//----------------------------------------------Beginning of Tutorial 3 logic in the Controller--------------------------------------------------------------------------------------------------------------------

						if (startTutorial3 && !tutorialEnd) {
							g3ViewTutorial.update(g3Tutorial.getPlayerX(), g3Tutorial.getPlayerY(),
									g3Tutorial.getDirection(), g3Tutorial.getProbeList(), g3Tutorial.getNumProbes(),
									currentTimer - timeForScore);
							g3Tutorial.update(xIncr, yIncr, probeInteract);

							if (!g3Tutorial.getTutorialCheckList().get(0)) { // Used to display the tutorial objectives
																				// to show what the player is supposed
																				// to do to end the tutorial.
								g3ViewTutorial.displayText("Use the arrow keys to move the scientist");
							}
							if (g3Tutorial.getTutorialCheckList().get(0) && !g3Tutorial.getTutorialCheckList().get(1)) {
								g3ViewTutorial.displayText(
										"Use the spacebar to place the probes at the locations marked with an X");
							}
							if (g3Tutorial.getTutorialCheckList().get(1) && !g3Tutorial.getTutorialCheckList().get(2)) {
								g3ViewTutorial.displayText(
										"Return to the X after the probe turns green and use the spacebar to collect the data and probe");
							}

							boolean endCheck = true;
							for (Boolean bool : g3Tutorial.getTutorialCheckList()) { // If all of the booleans in the
																						// tutorials boolean array are
																						// true, need to end the
																						// tutorial
								endCheck = endCheck && bool;
							}

							if (endCheck) { // Ends the tutorial
								g3ViewTutorial.updateProbes(g3Tutorial.getProbeList());
								startTutorial3 = false;
								tutorialEnd = true;

							}
						}
//-----------------------------------End of Tutorial 3 Logic in Controller-------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------Beginning of reset game Logic in the controller------------------------------------------------------------------------------------------------------------------------------

						if (overModel.getGameNumber() == 3) { // Game number is 3 after the final game ends, because we
																// start at 0.
							if (endScore) {
								cutScreenView.setScene(theStage); // Displays end screen.
								cutScreenView.displayEndScreen(overModel.getEconScore(), overModel.getEnvirmScore(),
										overModel.getOverallScore());
								cutScreenView.getStartButton().setOnAction(new EventHandler<ActionEvent>() {
									@Override
									public void handle(ActionEvent event) {
										deleteFile(name0); // deletes the save file.
										// Reset game
										overModel.setGameNumber(0);
										overModel.setToInitalScores();
										cutScreenView = new CutScreenView(width, height); // Recreates a new title
																							// screen.
										startTutorial1 = false; // Sets all of the tutorials to false so they dont start
																// until they are supposed to.
										startTutorial2 = false;
										startTutorial3 = false;
										tutorialEnd = false;
										showCutscene = false;
										storyTextCounter = 0; // Resets the storyTextCounter to 0 so it is ready for the
																// first cutscene
									}
								});

								endScore = false;
							}
						}

						if (tutorialEnd) { // When a tutorial is over create the next decision view so the player can
											// make a decision
							// initalize Decision View
							decisionView = new DecisionView(overModel.getGameNumber(), overModel.getEconScore(),
									overModel.getEnvirmScore(), overModel.getOverallScore(), width, height,
									decisionStrings[overModel.getGameNumber()]);// TODO CHECK
							// initalize DecisionListener
							decisionListeners(decisionView, theStage, width, height);
							decisionView.setScene(theStage);

							tutorialEnd = false;
						}

					} else {
//------------------------------------End Reset Game Logic in Controller------------------------------------------------------------------------------------------------------------------------------

//-----------------------------------Begin Game 1 Logic in Controller-------------------------------------------------------------------------------------------------------------------------------

						if (overModel.getGameNumber() == 1) { // If the game is game 1

							g1View.setScrollSpeed(GAME_SCROLL_SPEED);

							currentTimer -= TIME_INCREMENT; // Decrements the timer for the minigame

							if (currentTimer > timeForScore) { // IF the timer is still greater than the time when the
																// score displays update the game
								g1Model.update(yIncr);
								g1View.update(g1Model.getPlayer(), g1Model.getObstacleList(), overModel.getEconScore(),
										overModel.getEnvirmScore(), overModel.getOverallScore(),
										currentTimer - timeForScore);
								if (currentTimer % SAVE_TIME == 0) // Every 1000 mS save the game to file.
									save(g1Model, name1);
							} else {
								if (forScore) {
									controllEndAnimation(g1View);
									deleteFile(name1);
								}
							}
						}
//------------------------------------End of Game 1 Logic in Controller------------------------------------------------------------------------------------------------------------------------------
//------------------------------------Beginning of Game 2 Logic in Controller------------------------------------------------------------------------------------------------------------------------------

						if (overModel.getGameNumber() == 2) {

							currentTimer -= TIME_INCREMENT;
							if (g2Model.update(clickOnPosX, clickOnPosY, takeAction, tool, currentTimer - timeForScore)
									&& currentTimer > timeForScore) { // Updates game if the timer is still larger than
																		// the time left to display the score
								currentTimer -= TIME_INCREMENT;
								g2View.update(g2Model.getRobot(), g2Model.getAnimalList(), g2Model.getObjectiveList(),
										g2Model.getTimeRemaining(), overModel.getEconScore(),
										overModel.getEnvirmScore(), overModel.getOverallScore());
								if (currentTimer % SAVE_TIME == 0) // Saves game every 1000ms
									save(g2Model, name2);
							} else {
								if (forScore) { // Ends the view of game two and gets ready to show the cutscene.
									controllEndAnimation(g2View);
									deleteFile(name2);
									showCutscene = true;
									currentTimer = timeForScore;
								}

							}
						}
//-------------------------------------End of Game 2 Logic in Controller----------------------------------------------------------------------------------------------------------------------------
//-------------------------------------Beginning of Game 3 Logic in the Controller-----------------------------------------------------------------------------------------------------------------------------

						if (overModel.getGameNumber() == 3) {

							g3View.updateProbes(g3Model.getProbeList()); // Updates the probe list to make sure that the
																			// correct amount displays
							currentTimer -= TIME_INCREMENT; // Timer decremeents
							if (g3Model.update(xIncr, yIncr, probeInteract) && currentTimer > timeForScore) { // IF the
																												// game
																												// is
																												// not
																												// over
																												// go
																												// through
																												// this
																												// branch
								probeInteract = false;
								// Shortcuts to returning false when all games are complete
								g3View.update(g3Model.getPlayerX(), g3Model.getPlayerY(), g3Model.getDirection(),
										g3Model.getProbeList(), g3Model.getNumProbes(), currentTimer - timeForScore);
								if (currentTimer % SAVE_TIME == 0) {
									g3Tutorial = null;// Saves the game every 1000ms
									save(g3Model, name3);

								}
							}

							else {

								if (forScore) { // If the game is over this branch executes
									// changes the score based off of the time remaining-----------------------
									long remainingTime = currentTimer - timeForScore;
									double gainedScore = (double) remainingTime / DENOMINATOR_FOR_SCORE_G3;// changes
																											// the score
																											// based off
																											// of the
																											// time
																											// remaining
									gainedScore = Math.ceil(gainedScore);
									overModel.updateEconScore(gainedScore);
									overModel.updateEnvirmScore(gainedScore);
									// changes the score based off of the time remaining------------------------
									currentTimer = timeForScore;
									controllEndAnimation(g3View);
									endScore = true;
									deleteFile(name3);

								}

							}
						}

					}

					Thread.sleep(TIME_INCREMENT);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}

		};
		currTimer.start();
		theStage.show();
	}
//------------------------------End of Game 3 Logic in Controller------------------------------------------------------------------------------------------------------------------------------------
//------------------------------End of Tick in controller-----------------------------------------------------------------------------------------------------------------------------------

//------------------------------Begin Game 1 and Game 3 Listener Logic in Controller------------------------------------------------------------------------------------------------------------------------------------

	/**
	 * setting the view for game 1 and game 3
	 * @author Theodor
	 * @param view  Sets up the listeners for the game1 and game3 views
	 */
	public void gameListener(View view) {
		Scene scene = view.getScene();
		yIncr = 0;
		xIncr = 0;
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				switch (event.getCode()) { // Uses the arrow keys to change fields in the controller that control game 1
											// and 3.
				case UP: // Up arrow key to go up
					yIncr = 1;
					break;
				case DOWN: // Down arrow key to go down
					yIncr = -1;
					break;
				case LEFT: // Left arrow key to go left
					xIncr = -1;
					break;
				case RIGHT: // Right arrow key to go right
					xIncr = 1;
					break;
				case SPACE: // Space bar to use for the third game to place probes
					probeInteract = true;
					break;
				}

			}
		});

		scene.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				switch (event.getCode()) { // Used so the character will stop moving when the key is released in games 1
											// and 3
				case UP: // Release up arrow stop going up
					yIncr = 0;
					break;
				case DOWN: // Release down arrow stop going down
					yIncr = 0;
					break;
				case LEFT: // Release left arrow stop going left
					xIncr = 0;
					break;
				case RIGHT: // Release right arrow to stop going right
					xIncr = 0;
					break;
				case SPACE: // Release space bar to stop interacting with probe.
					probeInteract = false;
					break;
				}

			}
		});
//--------------------------------End game 1 and game 3 listener logic in the controller----------------------------------------------------------------------------------------------------------------------------------
//--------------------------------Begin Skip Button for game 1 and 3 Logic in Controller----------------------------------------------------------------------------------------------------------------------------

		view.getSkipButton().setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (view instanceof G1View) { // If the view is G1View go into this branch
					if (currentTimer > 0) { // If the time is greater than 0 and someone calls the skip button. This
											// branch executes if its during the minigame
						saveGameStats(overModel, name0);
						currentTimer = 0;
						showCutscene = true;
						System.out.println("skip g1");
						deleteFile(name1);
					}

					else { // This branch executes if the branch is during the tutorial.
						ListIterator<Boolean> checkIter = g1Tutorial.getTutorialCheckList().listIterator();
						while (checkIter.hasNext()) { // Sets all the things in the tutorial checkList to be true.
							checkIter.next();
							checkIter.set(true);
						}
					}
				} else { // This branch executes if it is not game 1.
					if (currentTimer > 0) { // If the timer is greater than 0 this branch executes.
						currentTimer = 0;
						showCutscene = true;
						tutorialEnd = false;
						saveGameStats(overModel, name0);
						deleteFile(name3);
						endScore = true;
					}

					else { // If the game 3 tutorial is occuring this branch executes.
						ListIterator<Boolean> checkIter = g3Tutorial.getTutorialCheckList().listIterator();
						while (checkIter.hasNext()) { // Sets all the things in the checklist to be true.
							checkIter.next();
							checkIter.set(true);
						}

					}
				}
			}

		});

	}
//-------------------------------End Skip Button Logic for game 1 and 3 In the Controller-----------------------------------------------------------------------------------------------------------------------------------
//-------------------------------Begin Game 2 Listener Logic----------------------------------------------------------------------------------------------------------------------------------

	/**
	 * sets up the listeners for game 2
	 * 
	 * @param g2view  sets up the listeners for the G2View object
	 */
	public void game2Listener(G2View g2view) { // Special method for game2 listeners because it uses mouse instead of
												// arrows
		Scene scene = g2View.getScene();
		g2view.getToolToggleGroup().selectedToggleProperty().addListener(new ChangeListener<Toggle>() { // Creates a
																										// toggle button
																										// to switch the
																										// tools when
																										// clicked
			public void changed(ObservableValue<? extends Toggle> ov, Toggle toggle, Toggle new_toggle) {
				if (new_toggle.getUserData().equals("camera")) { // If tool = 1 the tool is camera
					tool = CAMERA;
				} else if (new_toggle.getUserData().equals("net")) { // If tool = 0 the tool is net.
					tool = NET;
				}

			}
		});

//--------------------------------Begin Game 2 Skip Button Logic. Continue Game 2 Listener----------------------------------------------------------------------------------------------------------------------------------

		g2view.getSkipButton().setOnAction(new EventHandler<ActionEvent>() { // Creates the skip button for the second
																				// game. Code must be replicated because
																				// we are using a different set of
																				// listeners here.

			@Override
			public void handle(ActionEvent event) {
				if (currentTimer > 0) { // If it is the game just need to set showCutscene to true to end the game.
					currentTimer = 0;
					showCutscene = true;
					saveGameStats(overModel, name0);
					deleteFile(name2);
				} else {
					ListIterator<Boolean> checkIter = g2Tutorial.getTutorialChecklist().listIterator(); // If it is the
																										// game 2
																										// tutorial,
																										// need to set
																										// all of the
																										// tutorial
																										// CheckList
																										// booleans to
																										// true to skip.
					while (checkIter.hasNext()) { // Sets all of the booleans to true in the checkList.
						checkIter.next();
						checkIter.set(true);
					}

				}
			}

		});
//------------------------------------End Game 2 Skip Button Logic. Continue Game 2 Listener------------------------------------------------------------------------------------------------------------------------------

		scene.setOnMousePressed(new EventHandler<MouseEvent>() { // Mouse handlere that will get the mouse x and y when
																	// it is clicked. This is used to set the robot to a
																	// position and have it take an action
			@Override
			public void handle(MouseEvent e) {
				clickOnPosX = e.getSceneX();
				clickOnPosY = e.getSceneY();
				takeAction = true;
			}
		});
		scene.setOnMouseReleased(new EventHandler<MouseEvent>() { // Used when the mouse click is released to set the
																	// takeAction to false, and to also get the mouse
																	// position to move the robto
			@Override
			public void handle(MouseEvent e) {
				clickOnPosX = e.getSceneX();
				clickOnPosY = e.getSceneY();
				takeAction = false;
			}
		});

	}
//-------------------------End Game 2 Listener Logic-----------------------------------------------------------------------------------------------------------------------------------------
//-------------------------Begin decisionListener Logic----------------------------------------------------------------------------------------------------------------------------------------
//-------------------------Begin Toggle button Logic continue decisionListener Logic-----------------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Sets up the listeners for the decisionViews.
	 * @author Theodor 
	 * @param decisionView  decisionView, object that needs its listeners being set
	 *                     up
	 * @param theStage      Stage, the stage that needs listeners set up.
	 * @param canvasWidth   double, the canvas width
	 * @param canvasHeight double, the canvas height
	 */
	public void decisionListeners(DecisionView decisionView, Stage theStage, double canvasWidth, double canvasHeight) {
		firstDecision = false;
		decisionView.getToggleGroup().selectedToggleProperty().addListener( // Creates the toggle button that is used to
																			// make the buttons that can be yess or no.

				new ChangeListener<Toggle>() { // This executes when the buttons are switched
					public void changed(ObservableValue<? extends Toggle> ov, Toggle toggle, Toggle new_toggle) {
						if (decisionView.getToggleGroup().getSelectedToggle() != null) { // Null Branch
						}

						if (decisionView.getToggleGroup().getSelectedToggle().getUserData().toString().equals("y")) { // If
																														// the
																														// userData
																														// stored
																														// in
																														// the
																														// button
																														// is
																														// 'y'
																														// (yes)
																														// this
																														// branch
																														// activates
							firstDecision = true;
							decisionView.changeBackgroundWithDecision(1); // Changes the background to the other image.
							decisionView.changeLabelWithDecision(firstDecision);

							if (decisionView.getDecisionNumber() == 1) {
								econScore = overModel.getEconScore() + ECON_INCREASE_DECSION1;
								envirmScore = overModel.getEnvirmScore() - ENVIRM_DECREASE_DECSION1;

							} else if (decisionView.getDecisionNumber() == 2) {
								econScore = overModel.getEconScore() + ECON_INCREASE_DECSION2;
								envirmScore = overModel.getEnvirmScore() - ENVIRM_DECREASE_DECSION1;
							} else if (decisionView.getDecisionNumber() == 3) {
								econScore = overModel.getEconScore() - ECON_DECREASE_DECSION3;
								envirmScore = overModel.getEnvirmScore() + ENVIRM_INCREASE_DECSION3;
							}

							decisionView.drawScore(econScore, envirmScore,
									overModel.calculateOverallScore(econScore, envirmScore),
									decisionStrings[overModel.getGameNumber()], decisionView.getBigScoreBox());
						}

						if (decisionView.getToggleGroup().getSelectedToggle().getUserData().toString().equals("n")) { // If
																														// the
																														// userData
																														// stored
																														// in
																														// the
																														// button
																														// is
																														// "n"
																														// or
																														// (no)
																														// this
																														// branch
																														// activates
							firstDecision = false;

							decisionView.changeBackgroundWithDecision(0); // Changes the background to the other image
							decisionView.changeLabelWithDecision(firstDecision);

							if (decisionView.getDecisionNumber() == 1) {
								econScore = overModel.getEconScore() - ECON_DECREASE_DECSION1;
								envirmScore = overModel.getEnvirmScore() + ENVIRM_INCREASE_DECSION1;
							}
							if (decisionView.getDecisionNumber() == 2) {
								econScore = overModel.getEconScore() - ECON_DECREASE_DECSION2;
								envirmScore = overModel.getEnvirmScore() + ENVIRM_INCREASE_DECSION2;
							}

				else if (decisionView.getDecisionNumber() == 3) {
								econScore = overModel.getEconScore() + ECON_INCREASE_DECSION3;
								envirmScore = overModel.getEnvirmScore() - ENVIRM_DECREASE_DECSION3;
							}
							decisionView.drawScore(econScore, envirmScore,
									overModel.calculateOverallScore(econScore, envirmScore),
									decisionStrings[overModel.getGameNumber()], decisionView.getBigScoreBox());
						}
					}

				});
///---------------------End Toggle Logic continue Decision Listener Logic---------------------------------------------------------------------------------------------------------------------------------------------
//----------------------Begin Loading Logic--------------------------------------------------------------------------------------------------------------------------------------------

		((DecisionView) decisionView).getButton().setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (decisionView.getToggleGroup().getSelectedToggle() != null) {
					overModel.setGameNumber(overModel.getGameNumber() + 1);
					if (overModel.getGameNumber() == 1) {
						currentTimer = TIME_FOR_G1;
					}
					if (overModel.getGameNumber() == 2) {
						currentTimer = TIME_FOR_G2;
					}
					if (overModel.getGameNumber() == 3)
						currentTimer = TIME_FOR_G3;
					forScore = true;

					overModel.setEconScore(econScore);
					overModel.setEnvirmScore(envirmScore);

					// Start game
					if (overModel.getGameNumber() == 1) {// TODO �nder
						try {

							// Reading the object from a file
							FileInputStream inputFile = new FileInputStream(name1);
							ObjectInputStream in = new ObjectInputStream(inputFile);

							// Method for deserialization of object
							g1Model = (G1Model) in.readObject();

							in.close();
							inputFile.close();

							System.out.println("G1Model has been deserialized ");

						}

						catch (IOException ex) {
							System.out.println("IOException is caught");
							System.out.println("New Game1Starts");

							g1Model = new G1Model(firstDecision, overModel, g1View.getWidth(), g1View.getHeight(),
									g1View.getShipWidth(), g1View.getShipHeight(), g1View.getRockWidth(),
									g1View.getRockHeight(), g1View.getCanWidth(), g1View.getCanHeight());
						}

						catch (ClassNotFoundException ex) {
							System.out.println("ClassNotFoundException is caught");
							System.out.println("New Game1Starts");
							g1Model = new G1Model(firstDecision, overModel, g1View.getWidth(), g1View.getHeight(),
									g1View.getShipWidth(), g1View.getShipHeight(), g1View.getRockWidth(),
									g1View.getRockHeight(), g1View.getCanWidth(), g1View.getCanHeight());
						}
						// Added by Chandler Amato
						// ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

						g1View.setScene(theStage);
						gameListener(g1View);
					}
					if (overModel.getGameNumber() == 2) {
						try {

							// Reading the object from a file
							FileInputStream inputFile = new FileInputStream(name2);
							ObjectInputStream in = new ObjectInputStream(inputFile);

							// Method for deserialization of object
							g2Model = (G2Model) in.readObject();

							in.close();
							inputFile.close();

							System.out.println("G2Model has been deserialized ");

						}

						catch (IOException ex) {
							System.out.println("IOException is caught");
							System.out.println("New Game2Starts");
							g2Model = new G2Model(firstDecision, overModel, currentTimer, g2View.getWidth(),
									g2View.getHeight(), g2View.getRobotWidth(), g2View.getRobotHeight(),
									g2View.getFishWidth(), g2View.getFishHeight(), g2View.getCrustWidth(),
									g2View.getCrustHeight(), tool);

						}

						catch (ClassNotFoundException ex) {
							System.out.println("ClassNotFoundException is caught");
							System.out.println("New Game2Starts");
							g2Model = new G2Model(firstDecision, overModel, currentTimer, g2View.getWidth(),
									g2View.getHeight(), g2View.getRobotWidth(), g2View.getRobotHeight(),
									g2View.getFishWidth(), g2View.getFishHeight(), g2View.getCrustWidth(),
									g2View.getCrustHeight(), tool);
						}

						clickOnPosX = g2Model.getRobot().getXLoc()
								+ g2View.getRobotWidth() * g2Model.getRobot().getCenterXMult();
						clickOnPosY = g2Model.getRobot().getYLoc()
								+ g2View.getRobotHeight() * g2Model.getRobot().getCenterYMult();

						g2View.setScene(theStage);
						game2Listener(g2View);

					}
					if (overModel.getGameNumber() == 3) {

						try {

							// Reading the object from a file
							FileInputStream inputFile = new FileInputStream(name3);
							ObjectInputStream in = new ObjectInputStream(inputFile);

							// Method for deserialization of object
							g3Model = (G3Model) in.readObject();

							in.close();
							inputFile.close();

							System.out.println("G3Model has been deserialized ");

						}

						catch (IOException ex) {
							System.out.println("IOException is caught");
							System.out.println("New Game3Starts");

							g3Model = new G3Model(canvasWidth, canvasHeight, MAZE_DIMENSIONS, MAZE_DIMENSIONS,
									firstDecision);

						}

						catch (ClassNotFoundException ex) {
							System.out.println("ClassNotFoundException is caught");
							System.out.println("New Game3Starts");
							g3Model = new G3Model(canvasWidth, canvasHeight, MAZE_DIMENSIONS, MAZE_DIMENSIONS,
									firstDecision);
						}

						g3View = new G3View(g3Model.getMazeMatrix(), g3Model.getGridY(), g3Model.getGridX(),
								g3Model.getPlayerX(), g3Model.getPlayerY(), canvasWidth, canvasHeight, currentTimer);

						g3View.setScene(theStage);

						gameListener(g3View);

					}

				}
			}
		});
	}

	/**
	 *Ends the current game and gets read to show the next cutscene
	 * @author Theodor       
	 * @param tempView  view of the game that is ending
	 */
	public void controllEndAnimation(View tempView) {
		tempView.endAnimation(overModel.getOverallScore(), overModel.getEconScore(), overModel.getEnvirmScore(),
				"Game " + overModel.getGameNumber() + " finished", tempView.getBigScoreBox());
		forScore = false;

		showCutscene = true;
		saveGameStats(overModel, name0);
	}

	/**
	 * 
	 * Serialize the models.
	 * @author  Theodor
	 * @param saveModel  model being saved
	 * @param name       name of the file being saved to.
	 */
	public void save(Model saveModel, String name) {
		try {
			// Saving our Game Stats
			FileOutputStream file = new FileOutputStream(name);
			ObjectOutputStream out = new ObjectOutputStream(file);

			out.writeObject(saveModel);

			out.close();
			file.close();

			System.out.println(name + "is serilazied");

		}

		catch (IOException ex) {
			System.out.println("IOException is caught");
			ex.printStackTrace();
		}

	}

	/**
	 *  Serializes the GameStatsModel
	 * @author  Theodor
	 * @param saveGameStatsModel the gameStatsModel that is being saved
	 * @param name               name of the file being saved to
	 * 
	 */
	public void saveGameStats(GameStatsModel saveGameStatsModel, String name) {
		try {
			// Saving our Game Stats
			FileOutputStream file = new FileOutputStream(name);
			ObjectOutputStream out = new ObjectOutputStream(file);

			out.writeObject(saveGameStatsModel);

			out.close();
			file.close();

			System.out.println(name + "is serilazied");

		}

		catch (IOException ex) {
			System.out.println("IOException is caught in Save GameStats");
			ex.printStackTrace();
		}

	}

	/**
	 * @author   Theodor
	 * 
	 *deletes the file at the end of the game
	 * @param name  name of file to be deleted
	 * 
	 */

	public void deleteFile(String name) {
		try {
			File f = new File(name); // file to be delete
			if (f.delete()) // returns Boolean value
			{
				System.out.println(f.getName() + " was deleted"); // getting and printing the file name
			} else {
				System.out.println("failed to delete");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}