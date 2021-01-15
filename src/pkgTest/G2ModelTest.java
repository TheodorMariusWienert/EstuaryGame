package pkgTest;

import static org.junit.Assert.*;



import java.util.ArrayList;

import org.junit.Test;

import javafx.embed.swing.JFXPanel;
import pkgEnum.Direction;
import pkgEnum.Species;
import pkgGamePiece.Animal;
import pkgGamePiece.Crustacean;
import pkgGamePiece.Fish;
import pkgModel.G2Model;
import pkgModel.GameStatsModel;
import pkgView.G2View;

public class G2ModelTest {

	@Test
	public void test_G2Model() { //Checks if the model constructor is returning an instance of the g2Model. 
		G2Model g2Model = new G2Model(true, new GameStatsModel(), 1000, 500, 1000,30.0, 30.0, 30.0, 30.0, 30.0,30.0,0);
		assertEquals(g2Model instanceof G2Model, true); 
		
		g2Model = new G2Model(false, new GameStatsModel(), 1000, 500, 1000,30.0, 30.0, 30.0, 30.0, 30.0,30.0,0);
		assertEquals(g2Model instanceof G2Model, true); 
	}
	
	@Test
	public void test_Update() { //Tests the update method  by checking the location of the player robot.
		G2Model g2Model = new G2Model(true, new GameStatsModel(), 10, 500, 1000,30.0, 30.0, 30.0, 30.0, 30.0,30.0,0);
		
		double oldX = g2Model.getRobot().getXLoc(); 
		double oldY = g2Model.getRobot().getYLoc(); 
		
		g2Model.update(10,  10,  false, 1, 1);
		
		double newX = g2Model.getRobot().getXLoc(); 
		double newY = g2Model.getRobot().getYLoc();
		
		assertEquals(oldX - newX > 0.1, true); 
		
		g2Model.update(10, 10, true, 1,-1);
		//assertEquals(g2Model.getIsPlaying(), false); getIsPlaying deleted
		
	}
	
	@Test //Tests the first couple branches 
	public void test_updateAnimalList() { //Tests the fish branches of the updateAnimalLIst method where they go off the side of the screen.
		G2Model g2Model = new G2Model(true, new GameStatsModel(), 10, 500, 1000,30.0, 30.0, 30.0, 30.0, 30.0,30.0,0);
		g2Model.getAnimalList().removeAll(g2Model.getAnimalList());
		g2Model.getAnimalList().add(new Fish(10.0,10.0, Species.LARGEMOUTHBASS));
		
		//This should reset the fish to the left side of the screen because it went off the right.
		g2Model.getAnimalList().get(0).setX(10000);
		double oldY = g2Model.getAnimalList().get(0).getYLoc(); 
		g2Model.updateAnimalList();
		//--------------------------------------------------
		double newX = g2Model.getAnimalList().get(0).getXLoc();
		double newY = g2Model.getAnimalList().get(0).getYLoc(); 
		
		assertEquals(newX < 0 && (Math.abs(newY - oldY) > 0.01), true); //
		//This should reset the fish to the right side of the screen because the fish went off the left
		oldY = newY;
		g2Model.getAnimalList().get(0).setX(-10000);
		g2Model.updateAnimalList();
		//---------------------------------------------------
		
		newX = g2Model.getAnimalList().get(0).getXLoc(); 
		newY = g2Model.getAnimalList().get(0).getYLoc();
		
		assertEquals(newX > 500 && (Math.abs(newY - oldY) > 0.01), true);
		
	}
	@Test //This tests the second set of branches where the fish goes off the top or bottom of the screen.
	public void test2_updateAnimalList() { //Tests the fish branches of the updateAnimalList method where they go off the top of the screen
		G2Model g2Model = new G2Model(true, new GameStatsModel(), 10, 500, 1000,30.0, 30.0, 30.0, 30.0, 30.0,30.0,0);
		g2Model.getAnimalList().removeAll(g2Model.getAnimalList());
		g2Model.getAnimalList().add(new Fish(10.0,10.0, Species.LARGEMOUTHBASS));
		
		//This should reset the fish to the bottom side of the screen because it went off the top.
		g2Model.getAnimalList().get(0).setY(10000); 
		g2Model.updateAnimalList();
		//--------------------------------------------------
		double newY = g2Model.getAnimalList().get(0).getYLoc(); 
		
		assertEquals(newY < 0, true); //
		//This should reset the fish to the top side of the screen because the fish went off the bottom
		g2Model.getAnimalList().get(0).setY(-1000);
		g2Model.updateAnimalList();
		//---------------------------------------------------
		newY = g2Model.getAnimalList().get(0).getYLoc();
		assertEquals(newY > 500, true);	
	}
	
	@Test
	public void test3_updateAnimalList() { //Test the crustacean branches of the updateAnimalList method.
		G2Model g2Model = new G2Model(true, new GameStatsModel(), 10, 500, 1000,30.0, 30.0, 30.0, 30.0, 30.0,30.0,0);
		g2Model.getAnimalList().removeAll(g2Model.getAnimalList());
		g2Model.getAnimalList().add(new Crustacean(10.0,10.0, Species.BLUECRAB));
		
		//This should reset the crustacean to the left side of the screen because it went off the right.
		g2Model.getAnimalList().get(0).setX(10000);
		double oldY = g2Model.getAnimalList().get(0).getYLoc(); 
		g2Model.updateAnimalList();
		//--------------------------------------------------
		double newY = g2Model.getAnimalList().get(0).getYLoc(); 
		double newX = g2Model.getAnimalList().get(0).getXLoc();
		assertEquals(newX < 0 && (newY - oldY < 0.001), true); 
		oldY = newY;
		//This should reset the crustacean to the right side of the screen because the crustacean went off the right
		g2Model.getAnimalList().get(0).setX(-10000);
		g2Model.updateAnimalList();
		newY = g2Model.getAnimalList().get(0).getYLoc(); 
		newX = g2Model.getAnimalList().get(0).getXLoc();
		//---------------------------------------------------
		assertEquals(newX > 500 && (newY - oldY < 0.001), true);	
	}
	
	@Test 
	public void test_checkCollisionsT1() {		
		JFXPanel fxPanel = new JFXPanel(); // Need this to run javafx with JUnit (thanks StackOverflow!)
		G2View g2View = new G2View(1, 1000, 1000); //want for image dimensions
		//Tests fish and crustacean collisions that should work, pointing east
		G2Model g2Model = new G2Model(true, new GameStatsModel(), 1, g2View.getWidth(), g2View.getHeight(), 
				g2View.getRobotWidth(), g2View.getRobotHeight(), g2View.getFishWidth(), g2View.getFishHeight(), 
				g2View.getCrustWidth(), g2View.getCrustHeight(),0);
		
		//Fish
		g2Model.getAnimalList().removeAll(g2Model.getAnimalList());
		g2Model.getAnimalList().add(new Fish(40.0,10.0, Species.LARGEMOUTHBASS));
		g2Model.getRobot().setX(40-g2View.getRobotWidth());
		g2Model.getRobot().setY(10);
		
		int collision = g2Model.checkCollisions(160, 15);
		assertEquals(0, collision);
		
		//Crustacean
		g2Model.getAnimalList().removeAll(g2Model.getAnimalList());
		g2Model.getAnimalList().add(new Crustacean(100.0,100.0, Species.MITTENCRAB));
		g2Model.getRobot().setCenter(90, 100);
		
		collision = g2Model.checkCollisions(110,101);
		assertEquals(0, collision);
		
	}
	
	@Test 
	public void test_checkCollisionsT2() {
		JFXPanel fxPanel = new JFXPanel(); // Need this to run javafx with JUnit (thanks StackOverflow!)
		G2View g2View = new G2View(1, 1000, 1000); //want for image dimensions
		
		//Tests fish and crustacean collisions that should work, pointing west
		G2Model g2Model = new G2Model(true, new GameStatsModel(), 1, g2View.getWidth(), g2View.getHeight(), 
				g2View.getRobotWidth(), g2View.getRobotHeight(), g2View.getFishWidth(), g2View.getFishHeight(), 
				g2View.getCrustWidth(), g2View.getCrustHeight(),0);
		
		g2Model.getAnimalList().removeAll(g2Model.getAnimalList());
		g2Model.getAnimalList().add(new Fish(40.0,50.0, Species.LARGEMOUTHBASS));
		g2Model.getRobot().setX(100);
		g2Model.getRobot().setY(55);
		g2Model.getRobot().setDirection(Direction.WEST);
		int collision = g2Model.checkCollisions(41,57);
		assertEquals(0, collision);		
		
		g2Model.getAnimalList().removeAll(g2Model.getAnimalList());
		g2Model.getAnimalList().add(new Crustacean(100.0,100.0, Species.MITTENCRAB));
		//g2Model.getRobot().setCenter(140, 105);
		
		collision = g2Model.checkCollisions(110,101);
		assertEquals(0, collision);
		
	}
	
	
	@Test
	public void test_getObjectiveList() { //Tests the getObjectiveList method by see if it returns an instance of an arrayList.
		G2Model g2Model = new G2Model(true, new GameStatsModel(), 10, 500, 1000,30.0, 30.0, 30.0, 30.0, 30.0,30.0,0);
		assertEquals(g2Model.getObjectiveList() instanceof ArrayList, true);
	}
	
	@Test //Tests the setObjectiveList() method by creating a new list and seeing if the list in the game object is equal to the new list after using the set method.
	public void test_setObjectiveList() {
		G2Model g2Model = new G2Model(true, new GameStatsModel(), 10, 500, 1000,30.0, 30.0, 30.0, 30.0, 30.0,30.0,0);
		ArrayList<String> newList = new ArrayList<String>();
		g2Model.setObjectiveList(newList); 
		
		assertEquals(g2Model.getObjectiveList().equals(newList), true); 
	}
	
	@Test //Tests the getTimeRemaining method
	public void test_getTimeRemaining() {
		G2Model g2Model = new G2Model(true, new GameStatsModel(), 10, 500, 1000,30.0, 30.0, 30.0, 30.0, 30.0,30.0,0);
		assertEquals(g2Model.getTimeRemaining(), 10); 
	}
	
	@Test
	public void takeActionT1() {
		JFXPanel fxPanel = new JFXPanel(); // Need this to run javafx with JUnit (thanks StackOverflow!)
		G2View g2View = new G2View(1, 1000, 1000); //want for image dimensions
		//Tests takeAction for non invasive species with camera
		GameStatsModel gModel = new GameStatsModel();
		G2Model g2Model = new G2Model(true, gModel, 1, g2View.getWidth(), g2View.getHeight(), 
				g2View.getRobotWidth(), g2View.getRobotHeight(), g2View.getFishWidth(), g2View.getFishHeight(), 
				g2View.getCrustWidth(), g2View.getCrustHeight(),1);
		
		//Fish
		g2Model.getAnimalList().removeAll(g2Model.getAnimalList());
		g2Model.getAnimalList().add(new Fish(40.0,10.0, Species.LARGEMOUTHBASS));
		g2Model.getRobot().setX(40-g2View.getRobotWidth());
		g2Model.getRobot().setY(10);
		
		double toX = 160;
		double toY = 15;
		
		double initEconScore = gModel.getEconScore();
		g2Model.takeAction(true, toX, toY);
		System.out.println(gModel.getEnvirmScore());
		double finalEconScore = gModel.getEconScore();
		
		assertEquals(20, finalEconScore-initEconScore, 0.05); //Depend on global variable
		
		//Tests case where actionTimer did not reset
		g2Model.getAnimalList().removeAll(g2Model.getAnimalList());
		g2Model.getAnimalList().add(new Fish(40.0,10.0, Species.LARGEMOUTHBASS));
		initEconScore = gModel.getEconScore();
		g2Model.takeAction(true, toX, toY);
		System.out.println(gModel.getEnvirmScore());
		finalEconScore = gModel.getEconScore();
		
		assertEquals(0, finalEconScore-initEconScore, 0.05); //Depend on global variable
	}
	
	@Test	
	public void takeActionT2() {
		JFXPanel fxPanel = new JFXPanel(); // Need this to run javafx with JUnit (thanks StackOverflow!)
		G2View g2View = new G2View(1, 1000, 1000); //want for image dimensions
		//Tests takeAction for invasive species with tool
		GameStatsModel gModel = new GameStatsModel();
		G2Model g2Model = new G2Model(true, gModel, 1, g2View.getWidth(), g2View.getHeight(), 
				g2View.getRobotWidth(), g2View.getRobotHeight(), g2View.getFishWidth(), g2View.getFishHeight(), 
				g2View.getCrustWidth(), g2View.getCrustHeight(),0);
	
		g2Model.getAnimalList().clear();
		g2Model.getAnimalList().add(new Fish(40.0,10.0, Species.BLUECATFISH));
		g2Model.getRobot().setX(40-g2View.getRobotWidth());
		g2Model.getRobot().setY(10);
		
		double toX = 160;
		double toY = 15;
		
		double initEvirmScore = gModel.getEnvirmScore();
		g2Model.takeAction(true, toX, toY);
		System.out.println(gModel.getEnvirmScore());
		double finalEvirmScore = gModel.getEnvirmScore();
		
		assertEquals(30, finalEvirmScore-initEvirmScore, 0.05); //Depend on global variable
		
	}
	
	@Test
	public void takeActionT3() {
		JFXPanel fxPanel = new JFXPanel(); // Need this to run javafx with JUnit (thanks StackOverflow!)
		G2View g2View = new G2View(1, 1000, 1000); //want for image dimensions
		//Tests takeAction for when objectivelist is cleared
		GameStatsModel gModel = new GameStatsModel();
		G2Model g2Model = new G2Model(true, gModel, 1, g2View.getWidth(), g2View.getHeight(), 
				g2View.getRobotWidth(), g2View.getRobotHeight(), g2View.getFishWidth(), g2View.getFishHeight(), 
				g2View.getCrustWidth(), g2View.getCrustHeight(),0);
	
		g2Model.getAnimalList().clear();
		g2Model.getAnimalList().add(new Fish(40.0,10.0, Species.BLUECATFISH));
		g2Model.getObjectiveList().clear();
		g2Model.getObjectiveList().add("Blue Catfish");
		g2Model.getRobot().setX(40-g2View.getRobotWidth());
		g2Model.getRobot().setY(10);
		
		double toX = 160;
		double toY = 15;
		
		double initEvirmScore = gModel.getEnvirmScore();
		g2Model.takeAction(true, toX, toY);
		System.out.println(gModel.getEnvirmScore());
		double finalEvirmScore = gModel.getEnvirmScore();
		
		assertEquals(120, finalEvirmScore-initEvirmScore, 0.05); //Depend on global variables
		
	}

}