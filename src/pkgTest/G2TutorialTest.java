package pkgTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import javafx.embed.swing.JFXPanel;
import pkgEnum.AnimationState;
import pkgEnum.Species;
import pkgGamePiece.Fish;
import pkgModel.G2Model;
import pkgModel.GameStatsModel;
import pkgTutorial.G2Tutorial;
import pkgView.G2View;

public class G2TutorialTest {

	@Test
	public void setSlopeT() {
		JFXPanel fxPanel = new JFXPanel(); // Need this to run javafx with JUnit (thanks StackOverflow!)
		G2View g2View = new G2View(1, 1000, 1000); //want for image dimensions
		//Tests takeAction for when objectivelist is cleared
		GameStatsModel gModel = new GameStatsModel();	
		G2Tutorial g2Tutorial = new G2Tutorial(true, gModel, 1, g2View.getWidth(), g2View.getHeight(), 
				g2View.getRobotWidth(), g2View.getRobotHeight(), g2View.getFishWidth(), g2View.getFishHeight(), 
				g2View.getCrustWidth(), g2View.getCrustHeight(),0); 
		
		g2Tutorial.getAnimalList().clear();
		g2Tutorial.getAnimalList().add(new Fish(40.0,10.0, Species.BLUECATFISH));
		g2Tutorial.setSlopeZero(g2Tutorial.getAnimalList().get(0));
		
		assertEquals(0, g2Tutorial.getAnimalList().get(0).getXIncr(), 0.05);
		assertEquals(0, g2Tutorial.getAnimalList().get(0).getYIncr(), 0.05);
		
	}
	
	@Test
	public void spawnNonInvasiveT() {
		JFXPanel fxPanel = new JFXPanel(); // Need this to run javafx with JUnit (thanks StackOverflow!)
		G2View g2View = new G2View(1, 1000, 1000); //want for image dimensions
		//Tests takeAction for when objectivelist is cleared
		GameStatsModel gModel = new GameStatsModel();	
		G2Tutorial g2Tutorial = new G2Tutorial(true, gModel, 1, g2View.getWidth(), g2View.getHeight(), 
				g2View.getRobotWidth(), g2View.getRobotHeight(), g2View.getFishWidth(), g2View.getFishHeight(), 
				g2View.getCrustWidth(), g2View.getCrustHeight(),0); 
		
		g2Tutorial.getAnimalList().clear();
		g2Tutorial.getAnimalList().add(new Fish(40.0,10.0, Species.BLUECATFISH));
		g2Tutorial.spawnNonInvasive();
		
		assertEquals(Species.LARGEMOUTHBASS, g2Tutorial.getAnimalList().get(0).getSpecies());
	}
	
	@Test
	public void getTutorialChecklistT() {
		JFXPanel fxPanel = new JFXPanel(); // Need this to run javafx with JUnit (thanks StackOverflow!)
		G2View g2View = new G2View(1, 1000, 1000); //want for image dimensions
		//Tests takeAction for when objectivelist is cleared
		GameStatsModel gModel = new GameStatsModel();	
		G2Tutorial g2Tutorial = new G2Tutorial(true, gModel, 1, g2View.getWidth(), g2View.getHeight(), 
				g2View.getRobotWidth(), g2View.getRobotHeight(), g2View.getFishWidth(), g2View.getFishHeight(), 
				g2View.getCrustWidth(), g2View.getCrustHeight(),0); 
		
		//Objective list should all be false when starting
		assertEquals(false, g2Tutorial.getTutorialChecklist().get(0));
		assertEquals(false, g2Tutorial.getTutorialChecklist().get(1));
		assertEquals(false, g2Tutorial.getTutorialChecklist().get(2));
		assertEquals(false, g2Tutorial.getTutorialChecklist().get(3));
		
	}

	@Test
	public void takeActionT1() {
		JFXPanel fxPanel = new JFXPanel(); // Need this to run javafx with JUnit (thanks StackOverflow!)
		G2View g2View = new G2View(1, 1000, 1000); //want for image dimensions
		//Tests overloaded take action for invasive species with net
		GameStatsModel gModel = new GameStatsModel();
		G2Tutorial g2Tutorial = new G2Tutorial(true, gModel, 1, g2View.getWidth(), g2View.getHeight(), 
				g2View.getRobotWidth(), g2View.getRobotHeight(), g2View.getFishWidth(), g2View.getFishHeight(), 
				g2View.getCrustWidth(), g2View.getCrustHeight(),1);
		
		g2Tutorial.getAnimalList().removeAll(g2Tutorial.getAnimalList());
		
		double initX = 40;
		double initY = 10;
		
		g2Tutorial.getAnimalList().add(new Fish(initX,initY, Species.BLUECATFISH));
		g2Tutorial.getRobot().setX(40-g2View.getRobotWidth());
		g2Tutorial.getRobot().setY(10);
		g2Tutorial.getRobot().setSelectedTool(0);
		
		double toX = 160;
		double toY = 15;
		boolean action = true;
		g2Tutorial.takeAction(action, toX, toY);
		
		double newX = g2Tutorial.getAnimalList().get(0).getXLoc();
		double newY = g2Tutorial.getAnimalList().get(0).getYLoc();
		
		assertFalse(initX==newX);
		assertFalse(initY==newY);
	}
	
	@Test
	public void takeActionT2() {
		JFXPanel fxPanel = new JFXPanel(); // Need this to run javafx with JUnit (thanks StackOverflow!)
		G2View g2View = new G2View(1, 1000, 1000); //want for image dimensions
		//Tests overloaded take action for indigenous species with camera
		GameStatsModel gModel = new GameStatsModel();
		G2Tutorial g2Tutorial = new G2Tutorial(true, gModel, 1, g2View.getWidth(), g2View.getHeight(), 
				g2View.getRobotWidth(), g2View.getRobotHeight(), g2View.getFishWidth(), g2View.getFishHeight(), 
				g2View.getCrustWidth(), g2View.getCrustHeight(),1);
		
		g2Tutorial.getAnimalList().removeAll(g2Tutorial.getAnimalList());
		
		double initX = 40;
		double initY = 10;
		
		g2Tutorial.getAnimalList().add(new Fish(initX,initY, Species.LARGEMOUTHBASS));
		g2Tutorial.getRobot().setX(40-g2View.getRobotWidth());
		g2Tutorial.getRobot().setY(10);
		g2Tutorial.getRobot().setSelectedTool(1);

		double toX = 160;
		double toY = 15;
		boolean action = true;
		g2Tutorial.takeAction(action, toX, toY);
		
		assertEquals(2, g2Tutorial.getAnimalList().get(0).getAnimationState()); //should have animation state of 2
	}
	
	@Test
	public void updateT1() {
		//Tests changing first checklist component: click to move
		JFXPanel fxPanel = new JFXPanel(); // Need this to run javafx with JUnit (thanks StackOverflow!)
		G2View g2View = new G2View(1, 1000, 1000); //want for image dimensions
		
		//Tests if first tutorial checklist becomes true if newX changes
		GameStatsModel gModel = new GameStatsModel();
		G2Tutorial g2Tutorial = new G2Tutorial(true, gModel, 1, g2View.getWidth(), g2View.getHeight(), 
				g2View.getRobotWidth(), g2View.getRobotHeight(), g2View.getFishWidth(), g2View.getFishHeight(), 
				g2View.getCrustWidth(), g2View.getCrustHeight(),1);
		
		double newX = g2View.getWidth()/2 +  g2Tutorial.getRobot().getCenterXMult()*g2View.getRobotWidth();
		double newY = g2View.getHeight()/4 + g2Tutorial.getRobot().getCenterYMult()*g2View.getRobotHeight();
		
		g2Tutorial.update(newX, newY, false, 0);
		assertTrue(g2Tutorial.getTutorialChecklist().get(0)); //movement should make first checklist item true
		
		//Create new tutorial, this time testing case where only newY is different
		g2Tutorial = new G2Tutorial(true, gModel, 1, g2View.getWidth(), g2View.getHeight(), 
				g2View.getRobotWidth(), g2View.getRobotHeight(), g2View.getFishWidth(), g2View.getFishHeight(), 
				g2View.getCrustWidth(), g2View.getCrustHeight(),1);
		
		newX = g2View.getWidth()/4 +  g2Tutorial.getRobot().getCenterXMult()*g2View.getRobotWidth();
		newY = g2View.getHeight()/2 + g2Tutorial.getRobot().getCenterYMult()*g2View.getRobotHeight();
		
		g2Tutorial.update(newX, newY, false, 0);
		assertTrue(g2Tutorial.getTutorialChecklist().get(0)); 
	}
	
	@Test
	public void updateT2() {
		JFXPanel fxPanel = new JFXPanel(); // Need this to run javafx with JUnit (thanks StackOverflow!)
		G2View g2View = new G2View(1, 1000, 1000); //want for image dimensions
		
		//Tests if first tutorial checklist becomes true if newX changes
		GameStatsModel gModel = new GameStatsModel();
		G2Tutorial g2Tutorial = new G2Tutorial(true, gModel, 1, g2View.getWidth(), g2View.getHeight(), 
				g2View.getRobotWidth(), g2View.getRobotHeight(), g2View.getFishWidth(), g2View.getFishHeight(), 
				g2View.getCrustWidth(), g2View.getCrustHeight(),0);

		System.out.println(g2Tutorial.getRobot().getYLoc());
		
		double newX = g2View.getWidth()/2+50;
		double newY = g2View.getHeight()/2+50;
		g2Tutorial.getRobot().setCenter(g2View.getWidth()/2, g2View.getHeight()/2);
		g2Tutorial.update(newX, newY, true, 0);
		g2Tutorial.update(newX, newY, true, 0); // need to call twice to get to else if
		assertTrue(g2Tutorial.getTutorialChecklist().get(1)); //second update should net fish, make second checklist item true
		
		//Testing third checklist item: switching to camera
		g2Tutorial.update(newX, newY, true, 1); //update to trigger call
		assertTrue(g2Tutorial.getTutorialChecklist().get(2)); //changing tool should make third checklist item true
		
		//reset position of now indeginous fish to be clicked
		g2Tutorial.getAnimalList().get(0).setX(g2View.getWidth()/2);
		g2Tutorial.getAnimalList().get(0).setY(g2View.getHeight()/2);
		g2Tutorial.update(newX, newY, true, 0);
		assertTrue(g2Tutorial.getTutorialChecklist().get(3)); //taking photo of indeginous fish make fourth checklist item true
	}
	
}
