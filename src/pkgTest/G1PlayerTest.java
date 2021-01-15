package pkgTest;

import static org.junit.Assert.*;

import org.junit.Test;

import pkgEnum.ShipState;
import pkgGamePiece.G1Player;

public class G1PlayerTest {
    //--------------------------------------------------------------------------------------
	@Test 
	public void getXIncrT1() {
		G1Player G1P = new G1Player(0.0, 0.0, 1.0, 1.0); 
		double xIncr = G1P.getXIncr(); 
		assertEquals(1.0, xIncr, 0.01); 
	}
	
	@Test 
	public void getXIncrT2() {
		G1Player G1P = new G1Player(0.0, 0.0, 3.0, 1.0); 
		double xIncr = G1P.getXIncr(); 
		assertEquals(3.0, xIncr, 0.01); 
	}
	//--------------------------------------------------------------------------------------	
	@Test 
	public void getYIncrT1() {
		G1Player G1P = new G1Player(0.0, 0.0, 1.0, 54.0); 
		double yIncr = G1P.getYIncr(); 
		assertEquals(54.0, yIncr, 0.01); 
	}
	
	@Test 
	public void getYIncrT2() {
		G1Player G1P = new G1Player(0.0, 0.0, 1.0, 12.0); 
		double yIncr = G1P.getYIncr(); 
		assertEquals(12.0, yIncr, 0.01); 
	}
	//--------------------------------------------------------------------------------------	
	@Test 
	public void moveUpT1() {
		G1Player G1P = new G1Player(0.0, 0.0, 1.0, 1.0); 
		G1P.moveUp(); 
		double newY = G1P.getYLoc();
		assertEquals(-1.0, newY, 0.01); //(0,0) is the top left, so going up will decrement
	}
	
	@Test 
	public void moveUpT2() {
		G1Player G1P = new G1Player(30.0, 30.0, 21.0, -11.0); 
		G1P.moveUp(); 
		double newY = G1P.getYLoc();
		assertEquals(41.0, newY, 0.01); //(0,0) is the top left, so going up will decrement
	}
	
	@Test 
	public void moveUpT3() {
		G1Player G1P = new G1Player(30.0, 30.0, 21.0, 0); 
		G1P.moveUp(); 
		double newY = G1P.getYLoc();
		assertEquals(30.0, newY, 0.01); //(0,0) is the top left, so going up will decrement
	}
	//--------------------------------------------------------------------------------------	
	//--------------------------------------------------------------------------------------	
	@Test 
	public void moveDownT1() {
		G1Player G1P = new G1Player(0.0, 0.0, 1.0, 1.0); 
		G1P.moveDown(); 
		double newY = G1P.getYLoc();
		assertEquals(1.0, newY, 0.01); //(0,0) is the top left, so going up will decrement
	}
	
	@Test 
	public void moveDownT2() {
		G1Player G1P = new G1Player(30.0, 30.0, 21.0, -11.0); 
		G1P.moveDown(); 
		double newY = G1P.getYLoc();
		assertEquals(19.0, newY, 0.01); //(0,0) is the top left, so going up will decrement
	}
	
	@Test 
	public void moveDownT3() {
		G1Player G1P = new G1Player(30.0, 30.0, 21.0, 0); 
		G1P.moveDown(); 
		double newY = G1P.getYLoc();
		assertEquals(30.0, newY, 0.01); //(0,0) is the top left, so going up will decrement
	}
	//--------------------------------------------------------------------------------------	
		@Test 
		public void moveLeftT1() {
			G1Player G1P = new G1Player(0.0, 0.0, 1.0, 1.0); 
			G1P.moveLeft(); 
			double newX = G1P.getXLoc();
			assertEquals(0, newX, 0.01); //(0,0) is the top left, so going left will decrement
		}
		
		@Test 
		public void moveLeftT2() {
			G1Player G1P = new G1Player(30.0, 30.0, -21.0, -11.0); 
			G1P.moveLeft(); 
			double newX = G1P.getXLoc();
			assertEquals(30, newX, 0.01); //(0,0) is the top left, so going left will decrement
		}
		
		@Test 
		public void moveLeftT3() {
			G1Player G1P = new G1Player(30.0, 30.0, 0.0, 0); 
			G1P.moveLeft(); 
			double newX = G1P.getXLoc();
			assertEquals(30.0, newX, 0.01); //(0,0) is the top left, so going up will decrement
		}
		//--------------------------------------------------------------------------------------	
		@Test 
		public void moveRightT1() {
			G1Player G1P = new G1Player(0.0, 0.0, 1.0, 1.0); 
			G1P.moveRight(); 
			double newX = G1P.getXLoc();
			assertEquals(0, newX, 0.01); //(0,0) is the top left, so going right will increment
		}
		
		@Test 
		public void moveRightT2() {
			G1Player G1P = new G1Player(30.0, 30.0, -21.0, -11.0); 
			G1P.moveRight(); 
			double newX = G1P.getXLoc();
			assertEquals(30.0, newX, 0.01); //(0,0) is the top left, so going right will increment
		}
		
		@Test 
		public void moveRightT3() {
			G1Player G1P = new G1Player(30.0, 30.0, 0.0, 0); 
			G1P.moveRight(); 
			double newX = G1P.getXLoc();
			assertEquals(30.0, newX, 0.01); //(0,0) is the top right, so going up will increment
		}

		@Test 
		public void getStateT1() {
			G1Player G1P = new G1Player(30.0, 30.0, 0.0, 0); 
			int stateOrdinal1 = G1P.getState().ordinal(); //should equal 0 
			assertEquals(stateOrdinal1 == 0, true); 
			
			G1P.setState(ShipState.HURT);
			stateOrdinal1 = G1P.getState().ordinal(); //should equal 1
			assertEquals(stateOrdinal1 == 1, true); 
			
		}
}
