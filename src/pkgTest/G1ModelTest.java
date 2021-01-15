package pkgTest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import pkgEnum.ObstacleState;
import pkgGamePiece.G1Player;
import pkgModel.G1Model;
import pkgModel.GameStatsModel;

public class G1ModelTest {

	GameStatsModel gmodel = new GameStatsModel();

	@Test // Will have to update if increments change
	public void update_Test1() { // Testing moving down
		G1Model gModel = new G1Model(true, gmodel, 400, 300, 100, 100, 200, 200, 200, 2000);
		
		gModel.getPlayer().setY(100); //yIncr is 8 and it will subtract when going down
		gModel.update(-1);
		assertEquals(108, gModel.getPlayer().getYLoc(),0.01); // Make general with getPlayer method
		
		gModel.getPlayer().setY(100);
		gModel.update(1);
		assertEquals(92, gModel.getPlayer().getYLoc(),0.01);
	}


	@Test
	public void updateObstacleList_test() {
		G1Model gModel = new G1Model(false, gmodel, 400, 300, 100, 100, 200, 200, 200, 2000);
		gModel.getObstacleList().get(0).setX(50);
		gModel.getObstacleList().get(0).setY(50); //Added this to initialize the obstacles to the right place. 
		double init_X = gModel.getObstacleList().get(0).getXLoc();
		gModel.updateObstacleList();
		double final_X = gModel.getObstacleList().get(0).getXLoc();
		
		assertEquals(gModel.getObstacleList().get(0).getXIncr(), (init_X - final_X), 0.01);
	}
	
	@Test 
	public void getPlayer_test() { //Tests to see if the G1Model getPlayer() method works. 
		G1Model gModel = new G1Model(true, gmodel, 400, 300, 100, 100, 200, 200, 200, 2000);
		G1Player tempPlayer = gModel.getPlayer(); 
		assertEquals(tempPlayer instanceof G1Player, true); 
	
	}
	
	@Test
	public void test_keepPlayerInBounds() {
		G1Model gModel = new G1Model(true, gmodel, 400, 300, 100, 100, 200, 200, 200, 2000);
		gModel.getPlayer().setY(-1);
		gModel.keepPlayerInBounds(); 
		assertEquals(gModel.getPlayer().getYLoc(), 0, 0.01); //Checks if it keeps the ship inbounds on the lower edge of the canvas
		
		gModel.getPlayer().setY(300);
		gModel.keepPlayerInBounds(); 
		assertEquals(gModel.getPlayer().getYLoc(), 300-100-35, 0.01); // checks to see if the ship is inbounds on the upper edge of the screen.
		
	}
	
	@Test 
	public void test_checkCollisions() {
		
		G1Model gModel = new G1Model(true, gmodel, 400, 300, 100, 100, 200, 200, 200, 2000);
		gModel.getPlayer().setX(0);
		gModel.getPlayer().setY(0);
		
		gModel.getObstacleList().get(0).setX(0);
		gModel.getObstacleList().get(0).setY(0);
		gModel.checkCollisions();

		assertEquals(gModel.getPlayer().getState().getName(), "hurt"); //Tests the hurting branch of the g1Model
		
		gModel.getObstacleList().get(0).setX(0);
		gModel.getObstacleList().get(0).setY(0);
		gModel.getObstacleList().get(0).setObsType(ObstacleState.CAN); 
		double oldScore = gmodel.getOverallScore();
		gModel.checkCollisions();

		assertEquals(gmodel.getOverallScore() !=oldScore, true); //tests the scoring branch of the g1Model.
	}

}
