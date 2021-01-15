package pkgTest;

import static org.junit.Assert.*;

import org.junit.Test;

import pkgEnum.ObstacleState;
import pkgModel.G1Model;
import pkgModel.GameStatsModel;
import pkgTutorial.G1Tutorial;

public class G1TutorialTest {

	GameStatsModel gModel = new GameStatsModel();
	
	@Test
	public void gettersT() {
		//Tests getters to ensure that all return false initially.
		
		G1Tutorial gTutorial = new G1Tutorial(gModel, 400, 300, 100, 100, 200, 200, 200, 2000);
		
		assertFalse(gTutorial.getMovedVertically());
		assertFalse(gTutorial.getAvoidedRock());
		assertFalse(gTutorial.getGotCan());
		assertFalse(gTutorial.getAvoidedShip());
		
		assertFalse(gTutorial.getTutorialCheckList().get(0));
		assertFalse(gTutorial.getTutorialCheckList().get(1));
		assertFalse(gTutorial.getTutorialCheckList().get(2));
		assertFalse(gTutorial.getTutorialCheckList().get(3));
	}
	
	@Test
	public void moveVeriticallyTutorialT() {
		//Tests to make sure moveVerticallyTutorial places ship in middle of screen
		
		double canvasHeight = 300;
		G1Tutorial gTutorial = new G1Tutorial(gModel, 400, canvasHeight, 100, 100, 200, 200, 200, 2000);
		gTutorial.moveVerticallyTutorial();
		assertEquals(canvasHeight/2, gTutorial.getPlayer().getYLoc(), 0.05); 
		
	}
	
	@Test 
	public void avoidRockTutorialTest() {
		//Tests to make sure moveVerticallyTutorial places rock at end of screen in middle
		
		double canvasHeight = 300;
		double canvasWidth = 400;
		G1Tutorial gTutorial = new G1Tutorial(gModel, canvasWidth, canvasHeight, 100, 100, 200, 200, 200, 2000);
		
		gTutorial.avoidRockTutorial();
		assertEquals(canvasWidth, gTutorial.getObstacleList().get(0).getXLoc(), 0.05); //Ensures x location of rock correct when reset
		assertEquals(canvasHeight/2, gTutorial.getObstacleList().get(0).getYLoc(), 0.05); //Ensures x location of rock correct when reset
		assertEquals(ObstacleState.ROCK, gTutorial.getObstacleList().get(0).getObsType()); //Ensures x location of rock correct	when rest
	}
	
	@Test 
	public void avoidShipTutorialTest() {
		//Tests to make sure moveVerticallyTutorial places ship at end of screen in middle
		
		double canvasHeight = 300;
		double canvasWidth = 400;
		G1Tutorial gTutorial = new G1Tutorial(gModel, canvasWidth, canvasHeight, 100, 100, 200, 200, 200, 2000);
		
		gTutorial.avoidShipTutorial();
		assertEquals(canvasWidth, gTutorial.getObstacleList().get(0).getXLoc(), 0.05); //Ensures x location of ship correct when reset
		assertEquals(canvasHeight/2, gTutorial.getObstacleList().get(0).getYLoc(), 0.05); //Ensures x location of ship correct when reset
		assertEquals(ObstacleState.SHIP, gTutorial.getObstacleList().get(0).getObsType()); //Ensures x location of ship correct	when reset
	}
	
	@Test 
	public void getCanTutorialTest() {
		//Tests to make sure moveVerticallyTutorial places can at end of screen in middle
		
		double canvasHeight = 300;
		double canvasWidth = 400;
		G1Tutorial gTutorial = new G1Tutorial(gModel, canvasWidth, canvasHeight, 100, 100, 200, 200, 200, 2000);
		
		gTutorial.getCanTutorial();
		assertEquals(canvasWidth, gTutorial.getObstacleList().get(0).getXLoc(), 0.05); //Ensures x location of ship correct when reset
		assertEquals(canvasHeight/2, gTutorial.getObstacleList().get(0).getYLoc(), 0.05); //Ensures x location of ship correct when reset
		assertEquals(ObstacleState.CAN, gTutorial.getObstacleList().get(0).getObsType()); //Ensures x location of ship correct	when reset
	}
	
	@Test
	public void updateTest() {
		//Walks through tutorial ensuring that update method accounts correctly accounts for obstacles passing the ship that do not collide 
		
		double canvasHeight = 300;
		double canvasWidth = 400;
		G1Tutorial gTutorial = new G1Tutorial(gModel, canvasWidth, canvasHeight, 100, 100, 200, 200, 200, 2000);
		
		double initY = gTutorial.getPlayer().getYLoc();
		gTutorial.update(1);
		double finalY = gTutorial.getPlayer().getYLoc();
		
		assertEquals(-gTutorial.getPlayer().getYIncr(),finalY-initY,0.05); //Tests to ensure Y incrimented correctly
		assertTrue(gTutorial.getMovedVertically()); //Ensures move veritically flag flipped
		assertTrue(gTutorial.getTutorialCheckList().get(0)); //Ensures move veritically flag flipped
		
		gTutorial.getPlayer().setY(0); //ensures no collision
		gTutorial.getObstacleList().get(0).setX(0); // rock passes player completing rock tutorial
		gTutorial.update(0);
		
		assertTrue(gTutorial.getAvoidedRock()); //Ensures flag completing avoid rock tutorial flipped
		assertTrue(gTutorial.getTutorialCheckList().get(1)); //Ensures flag completing avoid rock tutorial flipped
		
		gTutorial.getObstacleList().get(0).setX(-1000); // resets obstacle to start avoid ship tutorial
		gTutorial.update(1);
		assertEquals(ObstacleState.SHIP, gTutorial.getObstacleList().get(0).getObsType()); //checks to ensure obstacle type recycled into ship correctly
		
		gTutorial.getObstacleList().get(0).setX(0); //ship passes player, completing ship tutorial
		gTutorial.update(0);
		
		assertTrue(gTutorial.getAvoidedShip()); //Ensures flag completing avoid ship tutorial flipped
		assertTrue(gTutorial.getTutorialCheckList().get(3)); //Ensures flag completing avoid ship tutorial flipped
		
		gTutorial.getObstacleList().get(0).setX(-1000);
		gTutorial.update(-1);
		assertEquals(ObstacleState.CAN, gTutorial.getObstacleList().get(0).getObsType());
		
		gTutorial.getPlayer().setY(canvasHeight/2);
		gTutorial.getObstacleList().get(0).setX(200); // collision between can will occur, ending last tutorial
		gTutorial.update(0);
		assertTrue(gTutorial.getGotCan()); //Ensures flag completing avoid rock tutorial flipped
		assertTrue(gTutorial.getTutorialCheckList().get(2)); //Ensures flag completing avoid rock tutorial flipped
		
	}
	
	@Test
	public void recycleObjectT() {
		//Tests recycle object for case where ship collides with rock initially
		
		double canvasHeight = 300;
		double canvasWidth = 400;
		G1Tutorial gTutorial = new G1Tutorial(gModel, canvasWidth, canvasHeight, 100, 100, 200, 200, 200, 2000);
		
		gTutorial.update(1); // to flip movedVertically flag, start avoid rock tutorial
		gTutorial.getObstacleList().get(0).setX(0); // Collides with ship, will flip flag
		gTutorial.update(0); // update to register collision
		gTutorial.getObstacleList().get(0).setX(-1000); //force recycle to occur
		gTutorial.update(1);
		
		assertEquals(canvasWidth, gTutorial.getObstacleList().get(0).getXLoc(), 0.05); // Ensures obstacle recycled to correct location
		assertEquals(ObstacleState.ROCK,gTutorial.getObstacleList().get(0).getObsType()); //Ensures obstacle type does not change
		
	}
	
	
}
