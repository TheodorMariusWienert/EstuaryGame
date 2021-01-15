package pkgTest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import pkgGamePiece.Probe;

public class ProbeTest {

	// Rudimentary test to ensure timeSincePlaced returned
	@Test
	public void testGetTime() {
		Probe p1 = new Probe(15, 15);
		double time = 3000;
		assertEquals(time, p1.getTime(), 0.1);
	}
	
	@Test
	public void testProbe() {
		Probe probeTest = new Probe(10.0, 10.0);
		assertEquals(probeTest instanceof Probe, true);
	}
	
	@Test
	public void testUpdate() { //tests the update method
		Probe probeTest = new Probe(10.0, 10.0);
		probeTest.update();
		double timeIncrement = 10;
		double originalTime = 3000;
		assertEquals(probeTest.getTime(), originalTime-timeIncrement, 0.01);
		
		probeTest.update();
		assertEquals(probeTest.getTime(), originalTime - 2*timeIncrement, 0.01);
		
		while(probeTest.getTime() !=0) {probeTest.update();}
		assertEquals(probeTest.getStatus(), 2);
	}
	
	@Test
	public void testToString() {
		Probe probeTest = new Probe(10.0, 10.0);
		String string = probeTest.getXLoc() + " " + probeTest.getYLoc() + " " + probeTest.getTime(); //test the toString method
		assertEquals(string, probeTest.toString());
	}
	
	@Test
	public void testPickup() {
		Probe probeTest = new Probe(10.0, 10.0);
		assertEquals(probeTest.pickUp(), false);  //tests the can't pickup branch
		
		while(probeTest.getTime() !=0) {probeTest.update();}
		assertEquals(probeTest.pickUp(), true); //tests the can pickup branch
		
	}
	

}
