package pkgTest;

import static org.junit.Assert.*;

import org.junit.Test;

import pkgModel.G3Model;

public class G3ModelTest {
	
	@Test
	public void returnMaze() {
		
		G3Model currentModel = new G3Model(100,100, 15, 15, true);
		
		assert(currentModel.getMazeMatrix() != null);
		
	}

	@Test
	public void basicConstruction() {
		G3Model currentModel = new G3Model(100,100, 15, 15, false);
		currentModel = new G3Model(100,100, 15, 15, true);
		
		int[][] currentMatrix = currentModel.getMazeMatrix();
		
		//Test the starting place is open and all the corners are probes
		assertEquals(currentMatrix[1][1], 1);
		assertEquals(currentMatrix[1][13], 2);
		assertEquals(currentMatrix[13][1], 2);
		assertEquals(currentMatrix[13][13], 2);
		
		//
		
	}

	@Test
	public void matrixNavigable() {
		G3Model currentModel = new G3Model(100,100, 15, 15, true);
		
		boolean xFlag = false;
		boolean yFlag = false; 
		
		int[][] currentMatrix = currentModel.getMazeMatrix();
		
		for (int i = 1; i < 14; i++) {
			for (int j = 1; j < 14; j++) {
				if (currentMatrix[i][j] == 1) {
					xFlag = true;
				}
			}	
			
			assert(xFlag);
			xFlag = false;
		}
		
		for (int i = 1; i < 14; i++) {
			for (int j = 1; j < 14; j++) {
				if (currentMatrix[j][i] == 1) {
					yFlag = true;
				}
			}	
			
			assert(yFlag);
			yFlag = false;
		}
	}
	
	@Test
	public void testGetPlayerX() {
		G3Model currentModel = new G3Model(100,100, 15, 15, true);
		
		assert(currentModel.getPlayerY() >= 0);
	}
	
	@Test
	public void testGetPlayerY() {
		G3Model currentModel = new G3Model(100,100, 15, 15, true);
		
		assert(currentModel.getPlayerY() >= 0);
	}
	
	@Test
	public void testGetGridX() {
		
		G3Model currentModel = new G3Model(100,100, 15, 15, true);
		
		assert(currentModel.getGridX() > 0);
	}
	
	@Test
	public void testGetGridY() {
		G3Model currentModel = new G3Model(100,100, 15, 15, true);
		
		assert(currentModel.getGridY() > 0);
	}
	
	
	@Test
	public void shouldMoveUp() {
		
		G3Model currentModel = new G3Model(100,100, 5, 5, true);
		
		double originalY = currentModel.getPlayerY();
		
		currentModel.update(0, 1, false);
		
		assert(originalY != currentModel.getPlayerY());
		
	}
	
	@Test
	public void shouldMoveDown() {
		
		G3Model currentModel = new G3Model(100,100, 5, 5, true);
		
		double originalY = currentModel.getPlayerY();
		
		currentModel.update(0, -1, false);
		
		assert(originalY != currentModel.getPlayerY());
		
	}
	
	@Test
	public void shouldMoveLeft() {
		
		G3Model currentModel = new G3Model(100,100, 5, 5, true);
		
		double originalX = currentModel.getPlayerX();
		
		currentModel.update(-1, 0, false);
		
		assert(originalX != currentModel.getPlayerX());
		
	}
	
	@Test
	public void shouldMoveRight() {
		
		G3Model currentModel = new G3Model(100,100, 5, 5, true);
		
		double originalX = currentModel.getPlayerX();
		
		currentModel.update(1, 0, false);
		
		assert(originalX != currentModel.getPlayerX());
		
	}
	
	@Test
	public void testBorderUp() {
		
		G3Model currentModel = new G3Model(5, 5, 5, 5, true);
		
		double originalY = currentModel.getPlayerY();
		double originalX = currentModel.getPlayerX();
		
		currentModel.update(0, 1, false);
		
		assert(originalY == currentModel.getPlayerY());
		assert(originalX == currentModel.getPlayerX());
	
	}
	
	@Test
	public void testBorderDown() {
		
		G3Model currentModel = new G3Model(5, 5, 5, 5, true);
		
		double originalY = currentModel.getPlayerY();
		double originalX = currentModel.getPlayerX();
		
		currentModel.update(0, -1, false);
		
		assert(originalY == currentModel.getPlayerY());
		assert(originalX == currentModel.getPlayerX());
	
	}
	
	@Test
	public void testBorderLeft() {
		
		G3Model currentModel = new G3Model(5, 5, 5, 5, true);
		
		double originalY = currentModel.getPlayerY();
		double originalX = currentModel.getPlayerX();
		
		currentModel.update(0, -1, false);
		
		assert(originalY == currentModel.getPlayerY());
		assert(originalX == currentModel.getPlayerX());
	
	}
	
	@Test
	public void testBorderRight() {
		
		G3Model currentModel = new G3Model(5, 5, 5, 5, true);
		
		double originalY = currentModel.getPlayerY();
		double originalX = currentModel.getPlayerX();
		
		currentModel.update(0, -1, false);
		
		assert(originalY == currentModel.getPlayerY());
		assert(originalX == currentModel.getPlayerX());
	
	}
	
	@Test
	public void testBarrierUp() {
		G3Model currentModel = new G3Model(100,100, 5, 5, true);
		
		for (int i = 0; i < 1000; i++) {
			currentModel.update(0,1,true);
		}
		
		double originalX = currentModel.getPlayerX();
		
		currentModel.update(0, 1, false);
		
		assert(originalX == currentModel.getPlayerX());
		
	}
	
	@Test
	public void testGetNumProbes() {
		G3Model currentModel = new G3Model(100,100, 5, 5, true);
		assert(currentModel.getNumProbes() == 3);
	
		currentModel = new G3Model(100,100, 5, 5, false);
		assert(currentModel.getNumProbes() == 2);
	}
	
	@Test
	public void testGetProbeList() {
		G3Model currentModel = new G3Model(100,100, 5, 5, true);
		assert(currentModel.getProbeList().isEmpty());

	}
	
	@Test
	public void testNoProbeHandle() {
		G3Model currentModel = new G3Model(100,100, 15, 15, true);
		
		currentModel.update(0,0,true);
		
		assert(currentModel.getProbeList().size() == 0 );
	}
	
	@Test
	public void testProbeHandle() {
		G3Model currentModel = new G3Model(100,100, 3, 3, true);
		
		currentModel.update(0,0,true);
		
		assert(currentModel.getProbeList().size() > 0 );
	}
	
	@Test
	public void testProbeHandle2() {
		G3Model currentModel = new G3Model(100,100, 5, 5, true);
		
		for (int i = 0; i < 1000; i++) {
			currentModel.update(1,0,true);
		}
		currentModel.update(0,0,true);
		
		assert(currentModel.getProbeList().size() > 0 );
	}
	
	@Test
	public void getDirectionT1() {
		G3Model currentModel = new G3Model(100,100, 5, 5, true);
		currentModel.update(1, 0, false);
		assertEquals(pkgEnum.Direction.EAST,currentModel.getDirection());
		
		currentModel.update(-1, 0, false);
		assertEquals(pkgEnum.Direction.WEST,currentModel.getDirection());
	}

}
