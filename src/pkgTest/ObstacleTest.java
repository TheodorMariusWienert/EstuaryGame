package pkgTest;

import static org.junit.Assert.*;

import org.junit.Test;

import pkgEnum.ObstacleState;
import pkgGamePiece.Obstacle;

public class ObstacleTest {

	@Test
	public void testUpdate() {
		Obstacle testObs = new Obstacle(10.0, 10.0, 10, ObstacleState.CAN); //Tests a few iterations of the update method
		testObs.setXIncr(2);
		testObs.update(); 
		assertEquals(testObs.getXLoc(), 8.0, 0.01); 
		
		testObs.update();
		assertEquals(testObs.getXLoc(), 6.0, 0.01);
	}

	@Test
	public void testObstacle() {
		Obstacle testObs = new Obstacle(10.0, 10.0, 10, ObstacleState.CAN); //test if the constructor is working
		assertEquals(testObs instanceof Obstacle, true); 
	}

	@Test
	public void testSetObsType() {
		Obstacle testObs = new Obstacle(10.0, 10.0, 10, ObstacleState.CAN); //Tests if the setObsType(ObstacleState) field works
		testObs.setObsType(ObstacleState.ROCK);
		assertEquals(testObs.getObsType().getName(), "rock"); 
	}

	@Test
	public void testGetObsType() {
		Obstacle testObs = new Obstacle(10.0, 10.0, 10, ObstacleState.CAN); //Test if the getObstacleState method works. 
		testObs.setObsType(ObstacleState.ROCK);
		assertEquals(testObs.getObsType().getName(), "rock"); 
	}

}
