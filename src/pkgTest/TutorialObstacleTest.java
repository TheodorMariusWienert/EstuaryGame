package pkgTest;

import static org.junit.Assert.*;

import org.junit.Test;

import pkgEnum.ObstacleState;
import pkgEnum.ShipState;
import pkgGamePiece.G1Player;
import pkgTutorial.TutorialObstacle;

public class TutorialObstacleTest {

	@Test
	public void getCollisionT() {
		//Ensures that collision is false initially
		TutorialObstacle obs = new TutorialObstacle(400, 400, 5, ObstacleState.ROCK);
		assertFalse(obs.getCollision()); //Collision should be false initially
	}
	
	@Test
	public void setCollisionT() {
		//Sets collision as true and checks it with getter
		TutorialObstacle obs = new TutorialObstacle(400, 400, 5, ObstacleState.ROCK);
		assertFalse(obs.getCollision()); //Collision should be false initially
		obs.setCollision(true);
		assertTrue(obs.getCollision()); //Collision should now be false
	}
	
	@Test
	public void updateT1() {
		//Tests first case of collision in update if playerShip has state hurt:
		
		TutorialObstacle obs = new TutorialObstacle(400, 400, 5, ObstacleState.ROCK);
		G1Player ship = new G1Player(100, 400, 0, 5);
		ship.setState(ShipState.HURT);
		obs.update(ship, 100, 100, 200, 200);
		assertTrue(obs.getCollision());
		
	}
	
	@Test
	public void updateT2() {
		//Tests second case of collision in update if obstacle and ship in hit box
		
		TutorialObstacle obs = new TutorialObstacle(400, 400, 5, ObstacleState.ROCK);
		G1Player ship = new G1Player(350, 350, 0, 5);
		obs.update(ship, 100, 100, 200, 200);
		assertTrue(obs.getCollision());
	}

}
