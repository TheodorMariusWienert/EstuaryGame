package pkgTest;

import static org.junit.Assert.*;

import org.junit.Test;

import pkgEnum.ObstacleState;
import pkgGamePiece.Obstacle;
import pkgGamePiece.Probe;

public class ItemTest {

	@Test
	public void testGetXIncr() {
		Obstacle tObs = new Obstacle(10.0,10.0,10, ObstacleState.ROCK) ;
		assertEquals(tObs.getXIncr(), 10, 0.05); 
		
		tObs = new Obstacle(10.0, 10.0, -3, ObstacleState.ROCK);
		assertEquals(tObs.getXIncr(), -3, 0.05); 
		
		tObs = new Obstacle(10.0, 10.0, 0, ObstacleState.ROCK);
		assertEquals(tObs.getXIncr(), 0, 0.05); 
		
	}

	
	@Test
	public void testGetYIncr() {
		Obstacle tObs = new Obstacle(10.0,10.0,10, ObstacleState.ROCK) ;
		tObs.setYIncr(10);
		assertEquals(tObs.getYIncr(), 10, 0.05); 
			
		tObs = new Obstacle(10.0, 10.0, -3, ObstacleState.ROCK);
		tObs.setYIncr(-3);
		assertEquals(tObs.getYIncr(), -3, 0.05); 
			
		tObs = new Obstacle(10.0, 10.0, 0, ObstacleState.ROCK);
		tObs.setYIncr(0);
		assertEquals(tObs.getYIncr(), 0, 0.05); 
	}

	@Test
	public void testSetXIncr() {
		
		Obstacle tObs = new Obstacle(10.0,10.0,0, ObstacleState.ROCK);
		tObs.setXIncr(10);
		assertEquals(tObs.getXIncr(), 10, 0.05); 
			
		tObs = new Obstacle(10.0, 10.0, 0, ObstacleState.ROCK);
		tObs.setXIncr(-3);
		assertEquals(tObs.getXIncr(), -3, 0.05); 
			
		tObs = new Obstacle(10.0, 10.0, 0, ObstacleState.ROCK);
		tObs.setXIncr(0);
		assertEquals(tObs.getXIncr(), 0, 0.05); 
	}

}
