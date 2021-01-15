package pkgTest;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

import javafx.scene.image.Image;
import pkgEnum.Direction;
import pkgGamePiece.G2Player;

public class G2PlayerTest {
	
	private double robotImgWidth = 134;
	private double robotImgHeight = 240;
	
	//--------------------------------------------------------------------------------------	
		@Test 
		public void moveUpT1() {
			G2Player G2P = new G2Player(0.0, 0.0, 1.0, 1.0, robotImgWidth, robotImgHeight); 
			G2P.moveUp(); 
			double newY = G2P.getYLoc();
			assertEquals(-1.0, newY, 0.01); //(0,0) is the top left, so going up will decrement
		}
		
		@Test 
		public void moveUpT2() {
			G2Player G2P = new G2Player(30.0, 30.0, 21.0, -11.0, robotImgWidth, robotImgHeight); 
			G2P.moveUp(); 
			double newY = G2P.getYLoc();
			assertEquals(41.0, newY, 0.01); //(0,0) is the top left, so going up will decrement
		}
		
		@Test 
		public void moveUpT3() {
			G2Player G2P = new G2Player(30.0, 30.0, 21.0, 0, robotImgWidth, robotImgHeight); 
			G2P.moveUp(); 
			double newY = G2P.getYLoc();
			assertEquals(30.0, newY, 0.01); //(0,0) is the top left, so going up will decrement
		}
		//--------------------------------------------------------------------------------------	
		@Test 
		public void moveDownT1() {
			G2Player G2P = new G2Player(0.0, 0.0, 1.0, 1.0, robotImgWidth, robotImgHeight); 
			G2P.moveDown(); 
			double newY = G2P.getYLoc();
			assertEquals(1.0, newY, 0.01); //(0,0) is the top left, so going up will decrement
		}
		
		@Test 
		public void moveDownT2() {
			G2Player G2P = new G2Player(30.0, 30.0, 21.0, -11.0, robotImgWidth, robotImgHeight); 
			G2P.moveDown(); 
			double newY = G2P.getYLoc();
			assertEquals(19.0, newY, 0.01); //(0,0) is the top left, so going up will decrement
		}
		
		@Test 
		public void moveDownT3() {
			G2Player G2P = new G2Player(30.0, 30.0, 21.0, 0, robotImgWidth, robotImgHeight); 
			G2P.moveDown(); 
			double newY = G2P.getYLoc();
			assertEquals(30.0, newY, 0.01); //(0,0) is the top left, so going up will decrement
		}
		//--------------------------------------------------------------------------------------	
		@Test 
		public void moveRightT1() {
			G2Player G2P = new G2Player(0.0, 0.0, 1.0, 1.0, robotImgWidth, robotImgHeight); 
			G2P.moveRight(); 
			double newX = G2P.getXLoc();
			assertEquals(1.0, newX, 0.01); //(0,0) is the top left, so going Right will increment
		}
		
		@Test 
		public void moveRightT2() {
			G2Player G2P = new G2Player(30.0, 30.0, -21.0, -11.0, robotImgWidth, robotImgHeight); 
			G2P.moveRight(); 
			double newX = G2P.getXLoc();
			assertEquals(9.0, newX, 0.01); //(0,0) is the top left, so going right will increment
		}
		
		@Test 
		public void moveRightT3() {
			G2Player G2P = new G2Player(30.0, 30.0, 0, 0, robotImgWidth, robotImgHeight); 
			G2P.moveRight(); 
			double newX = G2P.getXLoc();
			assertEquals(30.0, newX, 0.01); //(0,0) is the top left, so going right will increment
		}
		//--------------------------------------------------------------------------------------	
		@Test 
		public void moveLeftT1() {
			G2Player G2P = new G2Player(0.0, 0.0, 1.0, 1.0, robotImgWidth, robotImgHeight); 
			G2P.moveLeft(); 
			double newX = G2P.getXLoc();
			assertEquals(-1.0, newX, 0.01); //(0,0) is the top left, so going left will decrement
		}
		
		@Test 
		public void moveLeftT2() {
			G2Player G2P = new G2Player(30.0, 30.0, -21.0, -11.0, robotImgWidth, robotImgHeight); 
			G2P.moveLeft(); 
			double newX = G2P.getXLoc();
			assertEquals(51.0, newX, 0.01); //(0,0) is the top left, so going left will decrement
		}
		
		@Test 
		public void moveLeftT3() {
			G2Player G2P = new G2Player(30.0, 30.0, 0, 0, robotImgWidth, robotImgHeight); 
			G2P.moveLeft(); 
			double newX = G2P.getXLoc();
			assertEquals(30.0, newX, 0.01); //(0,0) is the top left, so going left will decrement
		}
		
		@Test
		public void getSelectedTooldT1() {
			G2Player G2P = new G2Player(30.0, 30.0, 0, 0, robotImgWidth, robotImgHeight); 
			G2P.setSelectedTool(0);
			int selectedTool = G2P.getSelectedTool(); 
			assertEquals(selectedTool, 0); 
			
			G2P.setSelectedTool(1);
			selectedTool = G2P.getSelectedTool(); 
			assertEquals(selectedTool, 1); 
			
		}
		
		@Test
		public void getCenterMultsT1() {
			//If this test fails than global variables in G2 Player must have changed
			
			G2Player G2P = new G2Player(30.0, 30.0, 0, 0, robotImgWidth, robotImgHeight); 
			
			double cXMult = G2P.getCenterXMult();
			double currentXMult = (double) 1/2;
			
			assertEquals(cXMult, currentXMult, 0.001);
			
			double cYMult = G2P.getCenterYMult();
			double currentYMult = (double) 2/3;
			assertEquals(cYMult, currentYMult, 0.001);
			
		}
		
		//I dont get this test...
		@Test
		public void updateT1() {
			G2Player G2P = new G2Player(30.0, 30.0, 0, 0, robotImgWidth, robotImgHeight); 
			
			Random rand = new Random(); 
			G2P.setX(rand.nextDouble()); 
			G2P.setY(rand.nextDouble());
			G2P.update();
			double NetX = G2P.getCaptureNet().getXLoc();
			double NetY = G2P.getCaptureNet().getYLoc(); 
			
			assertEquals(NetX, G2P.getXLoc(), 0.001); 
			assertEquals(NetY, G2P.getYLoc(), 0.001); 
			
			
		}
		
		
		@Test
		public void updateT2() {
			G2Player G2P = new G2Player(30.0, 30.0, 1, 1, robotImgWidth, robotImgHeight); 
			
			//Tests update when moving right and down. Confirms east direction
			G2P.update(robotImgWidth+50,robotImgHeight-60);
			assertEquals(31, G2P.getXLoc(), 0.05);
			assertEquals(29, G2P.getYLoc(), 0.05);
			assertEquals(Direction.EAST, G2P.getDirection());
			
			//Tests update when moving left and up. Confirms west direction
			G2P.update(-robotImgWidth-50, robotImgHeight+60);
			assertEquals(30, G2P.getXLoc(), 0.05);
			assertEquals(30, G2P.getYLoc(), 0.05);
			assertEquals(Direction.WEST, G2P.getDirection());
			
		}
		
		@Test
		public void updateT3() {
			G2Player G2P = new G2Player(500.0, 500.0, 1000, 1000, robotImgWidth, robotImgHeight);
			
			//Test case where distance between the robot center and the clicked point are smaller
			//than the increments, so move striaght to where mouse clicks
			
			
			//First test case where robot must move to the right and down
			double newRobotCenterX = 800;
			double newRobotCenterY = 200;
			
			double newXloc = newRobotCenterX - G2P.getCenterXMult()*robotImgWidth;
			double newYloc = newRobotCenterY - G2P.getCenterYMult()*robotImgHeight;
		
			G2P.update(newRobotCenterX, newRobotCenterY);
			
			assertEquals(newXloc, G2P.getXLoc(), 0.05);
			assertEquals(newYloc, G2P.getYLoc(), 0.05);
			assertEquals(Direction.EAST, G2P.getDirection());
			
			//Then test case where robot must move left and up
			newRobotCenterX = 400;
			newRobotCenterY = 900;
			newXloc = newRobotCenterX - G2P.getCenterXMult()*robotImgWidth;
			newYloc = newRobotCenterY - G2P.getCenterYMult()*robotImgHeight;
			
			G2P.update(newRobotCenterX, newRobotCenterY);
			assertEquals(newXloc, G2P.getXLoc(), 0.05);
			assertEquals(newYloc, G2P.getYLoc(), 0.05);
			assertEquals(Direction.WEST, G2P.getDirection());
		}
		
		@Test
		public void setCenterT1() {
			G2Player G2P = new G2Player(500.0, 500.0, 1000, 1000, robotImgWidth, robotImgHeight);
			
			G2P.setCenter(400, 450);
			
			double newXloc = 400 - G2P.getCenterXMult()*robotImgWidth;
			double newYloc = 450 - G2P.getCenterYMult()*robotImgHeight;
			
			assertEquals(newXloc, G2P.getXLoc(), 0.005);
			assertEquals(newYloc, G2P.getYLoc(), 0.005);
		}
		
		
}
