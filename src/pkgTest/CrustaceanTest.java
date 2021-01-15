package pkgTest;

import static org.junit.Assert.*;

import org.junit.Test;

import pkgEnum.Species;
import pkgGamePiece.Crustacean;

public class CrustaceanTest {
	
	@Test
	public void constructorTest() {
		Crustacean t1Crustacean = new Crustacean(10.0, 10.0, Species.MITTENCRAB) ;
		assertEquals(t1Crustacean instanceof Crustacean, true); 
	}

	@Test
	public void testRandomizeSlope() {
		Crustacean t1Crustacean = new Crustacean(10.0, 10.0, Species.MITTENCRAB) ;
		double oldSlope = t1Crustacean.getXIncr(); 
		while(t1Crustacean.getXIncr() == oldSlope) {
			t1Crustacean.randomizeSlope(); 
		}
		assertEquals(oldSlope == t1Crustacean.getXIncr(), false); 
	}

	@Test
	public void testResetOffScreen() {
		Crustacean t1Crustacean = new Crustacean(10.0, 10.0, Species.MITTENCRAB);
		
		double canvasWidth = 500;
		double canvasHeight = 500;
		
		for(int i = 0; i < 200; i++) {
			t1Crustacean.resetOffScreen(canvasWidth, canvasHeight);
			assertEquals(Math.abs(t1Crustacean.getXLoc()), canvasWidth*3,0.01); 
		}
		//TODO think about testing this. I don't think that it is possible. 
	}

}
