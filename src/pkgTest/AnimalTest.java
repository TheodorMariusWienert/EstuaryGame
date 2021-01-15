package pkgTest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import pkgEnum.AnimationState;
import pkgEnum.Species;
import pkgGamePiece.Animal;
import pkgGamePiece.Fish;

public class AnimalTest {

	@Test
	public void updateT1() { // currently update always sends the fish to the left and go up
		Animal t1Fish = new Fish(0.0, 0.0, Species.LARGEMOUTHBASS);
		double xSlope = t1Fish.getXIncr(); // gets the xSlope
		double ySlope = t1Fish.getYIncr(); // gets the ySlope
		double newLocX = -xSlope + t1Fish.getXLoc();
		double newLocY = -ySlope + t1Fish.getYLoc();
		t1Fish.update();
		;

		System.out.println(newLocX + " " + t1Fish.getXLoc());
		System.out.println(newLocY + " " + t1Fish.getYLoc());
		assertEquals(newLocX, t1Fish.getXLoc(), 0.01);
		assertEquals(newLocY, t1Fish.getYLoc(), 0.01);
	}
	
	@Test
	public void getAnimationStateT1() { // currently update always sends the fish to the left and go up
		
		//TESTS ALL OF THE ORDINAL STATES OF THE ENUM
		Animal t1Fish = new Fish(0.0, 0.0, Species.BLUECATFISH);
		int returnOrdinal = t1Fish.getAnimationState(); 
		assertEquals(returnOrdinal, 0); 
		
		t1Fish.setAnimationState(AnimationState.Hide);
		returnOrdinal = t1Fish.getAnimationState(); 
		assertEquals(returnOrdinal,  1);
		
		t1Fish.setAnimationState(AnimationState.Special);
		returnOrdinal = t1Fish.getAnimationState(); 
		assertEquals(returnOrdinal,  2);
		
		
		
	}
	

	@Test
	public void updateSpecialTimerT1() {
		//Tests if the branch to see if the special time is reset after 100 calls. This is used for the animation of the flash
		
		Animal t1Fish = new Fish(0.0, 0.0, Species.BLUECATFISH);
		int testInt = 99; 
		for(int i = 0; i < 100; i++) {
			t1Fish.updateSpecialTimer(); 
			System.out.println(testInt); 
			testInt = t1Fish.getSpecialTime(); 
			if(i !=99)
				assertEquals(testInt, 99 - i); //tests all of the cases before the timer resets.
			else
				assertEquals(testInt, 100);  //tests the final case where the timer resets. 
			
		}
		
	}
	
	@Test
	public void setxSlopeT1() {
		//Tests if the branch to see if the setxSlope works
		
		Animal t1Fish = new Fish(0.0, 0.0, Species.BLUECATFISH);
		t1Fish.setXIncr(10); 
		double testInt = t1Fish.getXIncr(); 
		
		assertEquals(testInt, 10, 0.05); 
		
		
		
	}
	
	@Test
	public void setySlopeT1() {
		//Tests if the branch to see if the setySlope works
		
		Animal t1Fish = new Fish(0.0, 0.0, Species.BLUECATFISH);
		t1Fish.setYIncr(10); 
		double testInt = t1Fish.getYIncr(); 
		
		assertEquals(testInt, 10, 0.05); 
		
		
		
	}
	
	@Test
	public void getSpeciesT1() {
		//Tests if the branch to see if the getSpecies works
		
		Animal t1Fish = new Fish(0.0, 0.0, Species.BLUECATFISH);
		int testInt = t1Fish.getSpecies().ordinal(); 
		//if the get species works the ordinal values should be 0 here
		assertEquals(testInt, 0); 
		
		//if the get species works the ordinal values should be 3 here
		t1Fish = new Fish(0.0,0.0, Species.LARGEMOUTHBASS);
		testInt = t1Fish.getSpecies().ordinal(); 
		assertEquals(testInt, 3); 
		
		
		
	}
	
	@Test
	public void setSpeciesT1() {
		Animal t1Fish = new Fish(0.0, 0.0, Species.BLUECATFISH);
		t1Fish.setSpecies(Species.BROOKTROUT);
		
		//if the set species works, species should now be brooktrout
		assertEquals(t1Fish.getSpecies(), Species.BROOKTROUT); 
		
		//if the get species works the ordinal values should be Large mout bass still
		t1Fish = new Fish(0.0,0.0, Species.LARGEMOUTHBASS);
		t1Fish.setSpecies(Species.LARGEMOUTHBASS);
		assertEquals(t1Fish.getSpecies(), Species.LARGEMOUTHBASS); 
	}
	
	

}
