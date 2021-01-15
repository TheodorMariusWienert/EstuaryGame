package pkgTest;

import static org.junit.Assert.*;

import org.junit.Test;

import pkgGamePiece.G1Player;
import pkgGamePiece.G3Player;

public class G3PlayerTest {
	//Constructor G3Player(initX, initY, Xincr, Yincr); 
	@Test 
	public void moveUpT1() { //Testing that it works with a negative value of increment even though this won't happen.
		G3Player G3P = new G3Player(31.1, 30.0, 0.0, -.10); 
		G3P.moveUp(); 
		double newY = G3P.getYLoc();
		assertEquals(30.1, newY, 0.01); //(0,0) is the top left, so going up will decrement
	}
    //------------------------------------------------------------------------------------------
	@Test 
	public void moveUpT2() {//Testing with a zero value of increment
		G3Player G3P = new G3Player(31.1, -5.0, 0.0, 0); 
		G3P.moveUp(); 
		double newY = G3P.getYLoc();
		assertEquals(-5.0, newY, 0.01); //(0,0) is the top left, so going up will decrement
	}
    //------------------------------------------------------------------------------------------
	@Test 
	public void moveUpT3() { //Testing with a positive value of increment
		G3Player G3P = new G3Player(31.1, -15, 0.0, 10.3); 
		G3P.moveUp(); 
		double newY = G3P.getYLoc();
		assertEquals(-25.3, newY, 0.01); //(0,0) is the top left, so going up will decrement
	}
    //------------------------------------------------------------------------------------------
	@Test 
	public void moveDownT1() { //Testing with a positive value of increment
		G3Player G3P = new G3Player(0.0, 10.0, 0.0, 3.0); 
		G3P.moveDown(); 
		double newY = G3P.getYLoc();
		assertEquals(13.0, newY, 0.01); //(0,0) is the top left, so going up will decrement
	}
    //------------------------------------------------------------------------------------------
	@Test 
	public void moveDownT2() { //Testing with a negative value of increment
		G3Player G3P = new G3Player(0.0, 12.0, 0.0, -3.0); 
		G3P.moveDown(); 
		double newY = G3P.getYLoc();
		assertEquals(9.0, newY, 0.01); //(0,0) is the top left, so going up will decrement
	}
    //------------------------------------------------------------------------------------------
	@Test 
	public void moveDownT3() { //Testing with a zero value of increment
		G3Player G3P = new G3Player(0.0, 10.0, 0.0, 0); 
		G3P.moveDown(); 
		double newY = G3P.getYLoc();
		assertEquals(10.0, newY, 0.01); //(0,0) is the top left, so going up will decrement
	}
    //------------------------------------------------------------------------------------------
	@Test 
	public void moveLeftT1() { //Testing with a zero value of increment
		G3Player G3P = new G3Player(11.0, 10.0, 0.0, 3.0); 
		G3P.moveLeft(); 
		double newX = G3P.getXLoc();
		assertEquals(11.0, newX, 0.01);} 
    //------------------------------------------------------------------------------------------
	@Test 
	public void moveLeftT2() { //Testing with a positive value of increment
		G3Player G3P = new G3Player(-8.0, 10.0, 1.0, 3.0); 
		G3P.moveLeft(); 
		double newX = G3P.getXLoc();
		assertEquals(-9.0, newX, 0.01); 
	}
    //------------------------------------------------------------------------------------------
	@Test 
	public void moveLeftT3() { //Testing with a negative value of increment
		G3Player G3P = new G3Player(1.0, 10.0, -3.0, -3.0); 
		G3P.moveLeft(); 
		double newX = G3P.getXLoc();
		assertEquals(4.0, newX, 0.01); 
	}
    //------------------------------------------------------------------------------------------
	@Test 
	public void moveRightT1() { //Testing with a negative value of increment
		G3Player G3P = new G3Player(1.0, 10.0, -4, -3.0); 
		G3P.moveRight(); 
		double newX = G3P.getXLoc();
		assertEquals(-3.0, newX, 0.01); 
	}
    //------------------------------------------------------------------------------------------
	@Test
	public void moveRightT2() { //Testing with a positive value of increment
		G3Player G3P = new G3Player(13.0, 10.0, 3.0, 3.0); 
		G3P.moveRight(); 
		double newX = G3P.getXLoc();
		assertEquals(16.0, newX, 0.01); 
	}
    //------------------------------------------------------------------------------------------
	@Test
	public void moveRightT3() { //Testing with a zero value of increment
		G3Player G3P = new G3Player(13.0, 10.0, 0.0, 3.0); 
		G3P.moveRight(); 
		double newX = G3P.getXLoc();
		assertEquals(13.0, newX, 0.01); 
	}
    //------------------------------------------------------------------------------------------

	@Test
	public void placeProbeT1() {
		G3Player G3P = new G3Player(13.0, 10.0, 0.0, 3.0); 
		
		assertEquals(G3P.placeProbe(), true);
	}
}

