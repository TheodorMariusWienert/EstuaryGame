package pkgTest;

import static org.junit.Assert.*;

import org.junit.Test;

import pkgEnum.Species;
import pkgGamePiece.Crustacean;
import pkgGamePiece.Fish;

public class FishTest {

	@Test
	public void testRandomizeSlope() { //Tests both the x and y randomization. 
		//
	    Fish t1Fish = new Fish(10.0, 10.0, Species.BROOKTROUT);
	    Fish t2Fish = new Fish(10.0, 10.0, Species.BROOKTROUT);
	    double oldSlope = t1Fish.getXIncr(); 
		
		t1Fish.setXIncr(1);
		
		while(t1Fish.getXIncr() == oldSlope) {
			t1Fish.randomizeSlope();
		}
		assertEquals(oldSlope == t1Fish.getXIncr(), false); 
		
		t2Fish.setXIncr(-1);
		
		oldSlope = t2Fish.getXIncr(); 
		
		while(t2Fish.getXIncr() == oldSlope) {
			t2Fish.randomizeSlope(); 
		}
		
		assertEquals(oldSlope == t2Fish.getXIncr(), false); 
		
		t1Fish.setYIncr(1);
		oldSlope = t1Fish.getYIncr(); 
		
		while(t1Fish.getYIncr() == oldSlope) {
			t1Fish.randomizeSlope();
		}
		assertEquals(oldSlope == t1Fish.getYIncr(), false);
		
		t2Fish.setYIncr(-1);
		oldSlope = t2Fish.getYIncr(); 
		
		while(t2Fish.getYIncr() == oldSlope) {
			t2Fish.randomizeSlope();
		}
		
		assertEquals(oldSlope == t2Fish.getYIncr(), false);
		
		
	}


	@Test
	public void testFish() {
		//Tests if an instance of the fish class was created. 
		Fish T1Fish = new Fish(10.0, 10.0, Species.BROOKTROUT);
		assertEquals(T1Fish instanceof Fish, true); 
	}

	@Test
	public void testResetOffScreen() {
		Fish t1Fish = new Fish(10.0, 10.0, Species.LARGEMOUTHBASS);
		
		double canvasWidth = 500;
		double canvasHeight = 500;
		
		for(int i = 0; i < 500; i++) {
			t1Fish.resetOffScreen(canvasWidth, canvasHeight);
			assertEquals(Math.abs(t1Fish.getXLoc()), canvasWidth*3,0.01); 
		}
		//TODO think about testing this. I don't think that it is possible. 
	}
	
}
