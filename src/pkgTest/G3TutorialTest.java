package pkgTest;

import static org.junit.Assert.*;

import org.junit.Test;

import pkgModel.G3Model;
import pkgTutorial.G3Tutorial;

public class G3TutorialTest {
	
	
	@Test
	public void construction() {
		
		G3Tutorial currentModel = new G3Tutorial(100,100, 15, 15, true);
		
		assert(currentModel.getMazeMatrix() != null);
		
	}
	
	
	//See if the tutorial progresses from moving down
	@Test
	public void step1() {
		
		G3Tutorial currentModel = new G3Tutorial(100,100, 15, 15, true);
		
		currentModel.update(0, -1, false);
		
		assert(currentModel.getTutorialCheckList().get(0) == true);
		
		
	}
	
	//See if the tutorial progresses from moving right
	@Test
	public void step1b() {
		
		G3Tutorial currentModel = new G3Tutorial(100,100, 15, 15, true);
		
		currentModel.update(1, 0, false);
		
		assert(currentModel.getTutorialCheckList().get(0) == true);
		
		
	}
	
	//See if the tutorial progresses when a probe is placed
	@Test
	public void step2() {
		
		G3Tutorial currentModel = new G3Tutorial(100,100, 3, 3, true);
		
		currentModel.update(1, 0, false);
		
		currentModel.update(0, 0, true);
		
		assert(currentModel.getTutorialCheckList().get(1) == true);
		
		
	}
	
	@Test
	public void step3() {
		
		G3Tutorial currentModel = new G3Tutorial(100,100, 5, 5, true);
		
		//move down, right, up left, all while placing probes if possible
		for (int i = 0; i < 100; i++) {
			currentModel.update(0, -1, true);
		}
		
		for (int i = 0; i < 100; i++) {
			currentModel.update(1, 0, true);
		}
		
		for (int i = 0; i < 100; i++) {
			currentModel.update(0, 1, true);
		}
		
		for (int i = 0; i < 100; i++) {
			currentModel.update(-1, 0, true);
		}
		
		//wait a milion turns for probes to be able to be picked up
		for (int i = 0; i < 1000000; i++){
			currentModel.update(0, 0, true);
		}
		
		
		//move down, right, up left, all while picking up probes if possible
		for (int i = 0; i < 100; i++) {
			currentModel.update(0, -1, true);
		}
		
		for (int i = 0; i < 100; i++) {
			currentModel.update(1, 0, true);
		}
		
		for (int i = 0; i < 100; i++) {
			currentModel.update(0, 1, true);
		}
		
		for (int i = 0; i < 100; i++) {
			currentModel.update(-1, 0, true);
		}
		
		assert(currentModel.getTutorialCheckList().get(2) == true);
		
	}
	
}