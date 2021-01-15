package pkgTest;

import static org.junit.Assert.*;

import org.junit.Test;

import pkgGamePiece.Net;

public class NetTest {

	@Test
	public void testNet() {
		Net testNet = new Net(10, 10); 
		assertEquals(testNet instanceof Net, true); 
	}

	@Test
	public void testUpdateSpecialTimer() { //Tests all stats of the special time parameter
		Net testNet = new Net(10, 10);
		int counter = 99; 
		for(int i = 99; i >= 0; i--) {
			counter = i; 
			testNet.updateSpecialTimer();
			if(counter != 0) {
				System.out.println(counter + " " + testNet.getSpecialTime());
				assertEquals(testNet.getSpecialTime() == counter, true);
			}
			else
				assertEquals(testNet.getSpecialTime() == 100, true);
			
		}
		
	}
	
	@Test
	public void testSetSPecialTime() {
		Net testNet = new Net(10, 10);
		testNet.setSpecialTime(20);
		assertEquals(testNet.getSpecialTime(), 20);
		testNet.setSpecialTime(40);
		assertEquals(testNet.getSpecialTime(), 40); 
		testNet.setSpecialTime(56);
		assertEquals(testNet.getSpecialTime(), 56);
	}

}
