package pkgTest;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

import pkgEnum.Direction;
import pkgEnum.Species;
import pkgGamePiece.Fish;
import pkgGamePiece.GamePiece;

public class GamePieceTest {


	@Test
	public void testGetXLoc() {
		Random rand = new Random(); 
		double nxtDouble = rand.nextDouble(); 
		Fish f1 = new Fish(nxtDouble, nxtDouble, Species.BLUECATFISH); 
		assertEquals(f1.getXLoc(), nxtDouble, 0.001); 
	}

	@Test
	public void testGetYLoc() {
		Random rand = new Random(); 
		double nxtDouble = rand.nextDouble(); 
		Fish f1 = new Fish(nxtDouble, nxtDouble, Species.BLUECATFISH); 
		assertEquals(f1.getYLoc(), nxtDouble, 0.001); 
	}

	@Test
	public void testSetX() {
		Random rand = new Random(); 
		double nxtDouble = rand.nextDouble(); 
		Fish f1 = new Fish(0, 0, Species.BLUECATFISH); 
		f1.setX(nxtDouble);
		
		assertEquals(f1.getXLoc(), nxtDouble, 0.001);
	}

	@Test
	public void testSetY() {
		Random rand = new Random(); 
		double nxtDouble = rand.nextDouble(); 
		Fish f1 = new Fish(0, 0, Species.BLUECATFISH); 
		f1.setY(nxtDouble);
		
		assertEquals(f1.getYLoc(), nxtDouble, 0.001);
	}

	@Test
	public void testGetDirection() {
		Random rand = new Random(); 
		double nxtDouble = rand.nextDouble(); 
		Fish f1 = new Fish(0, 0, Species.BLUECATFISH); 
		Direction testD1 = f1.getDirection();
		assertEquals(testD1 instanceof Direction, true); //TODO look at how this is tested
	}

	@Test
	public void testSetDirection() {//Take a look at this
		Random rand = new Random(); 
		double nxtDouble = rand.nextDouble(); 
		Fish f1 = new Fish(0, 0, Species.BLUECATFISH); 
		
		
		f1.setDirection(Direction.NORTH);
		Direction testD1 = f1.getDirection(); 
		assertEquals(testD1.getName() == "north", true);
		
		f1.setDirection(Direction.SOUTH);
		testD1 = f1.getDirection(); 
		assertEquals(testD1.getName() == "south", true); //TODO look at how this is tested
		
		f1.setDirection(Direction.WEST);
		testD1 = f1.getDirection(); 
		assertEquals(testD1.getName() == "west", true);
		
		f1.setDirection(Direction.EAST);
		testD1 = f1.getDirection(); 
		assertEquals(testD1.getName() == "east", true);
		
		f1.setDirection(Direction.NORTHEAST);
		testD1 = f1.getDirection(); 
		assertEquals(testD1.getName() == "northEast", true);
		
		f1.setDirection(Direction.NORTHWEST);
		testD1 = f1.getDirection(); 
		assertEquals(testD1.getName() == "northWest", true);
		
		f1.setDirection(Direction.SOUTHEAST);
		testD1 = f1.getDirection(); 
		assertEquals(testD1.getName() == "southEast", true);
		
		f1.setDirection(Direction.SOUTHWEST);
		testD1 = f1.getDirection(); 
		assertEquals(testD1.getName() == "southWest", true);
		
	}

}
