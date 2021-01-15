package pkgGamePiece;

import pkgEnum.Direction;

/**
 * 
 * The player class for game 2. Created on 11/1/2019
 * 
 * @author Matthew and Chandler
 */

public class G2Player extends PlayerChar {

	double toX;
	double toY;

	double robotCenterX;
	double robotCenterY;

	double robotHeight;
	double robotWidth;

	//
	double centerXMult = (double) 1 / 2;
	double centerYMult = (double) 2 / 3;

	boolean isEast = true;

	Net captureNet;
	private int selectedTool; // 1 is camera, 0 is net

	/**
	 * Constructor for G2Player
	 * 
	 * 
	 * @param initX       double, the value of the G2Player's initial x
	 * @param initY       double, the value of the G2Player's initial y
	 * @param xIncr       double, the value of the G2Player's xIncr
	 * @param yIncr       double, the value of the G2Player's yIncr
	 * @param robotWidth  double, the value of the robot images width
	 * @param robotHeight double, the value of the robot images height
	 * 
	 *
	 * 
	 */

	public G2Player(double initX, double initY, double xIncr, double yIncr, double robotWidth, double robotHeight) {
		super(initX, initY, xIncr, yIncr);
		this.captureNet = new Net(initX, initY);
		this.setSelectedTool(1);
		this.robotHeight = robotHeight;
		this.robotWidth = robotWidth;

		robotCenterX = xloc + centerXMult * robotWidth;
		robotCenterY = yloc + centerYMult * robotHeight;
	}

	/**
	 * 
	 * 
	 * updates the player character to the X and Y position that are fed to the
	 * method. Also sets its direction to be facing the correct way.
	 * 
	 * @param toX double, X position to move the robot to
	 * @param toY double, Y position to move the robot to
	 */
	public void update(double toX, double toY) {
		if (robotCenterX > toX) {
			if (robotCenterX - toX < xIncr) {
				robotCenterX = toX;
			} else {
				robotCenterX -= xIncr;
			}
			isEast = false;
		} else if (robotCenterX < toX) {
			if (toX - robotCenterX < xIncr) {
				robotCenterX = toX;
			} else {
				robotCenterX += xIncr;
			}
			isEast = true;
		}
		if (robotCenterY > toY) {
			if (robotCenterY - toY < yIncr) {
				robotCenterY = toY;
			} else {
				robotCenterY -= yIncr;
			}
		} else if (robotCenterY < toY) {
			if (toY - robotCenterY < yIncr) {
				robotCenterY = toY;
			} else {
				robotCenterY += yIncr;
			}
		}

		xloc = robotCenterX - centerXMult * robotWidth;
		yloc = robotCenterY - ((double) centerYMult) * robotHeight;

		if (isEast) {
			direction = Direction.EAST;
		} else {
			direction = Direction.WEST;
		}
	}

	/**
	 * return the net
	 * 
	 * Created on 11/2/2019
	 * 
	 * @return Net, captureNet, the value of the captureNet field.
	 * 
	 */

	public Net getCaptureNet() {
		return captureNet;
	}

	/**
	 * sets the netObject to the correct location of the robot and updates the
	 * captureNets animationTimer field.
	 * 
	 */
	public void update() {
		captureNet.setX(this.getXLoc());
		captureNet.setY(this.getYLoc());
		captureNet.updateSpecialTimer();
	}

	/**
	 * gets the value of the selectedTool field
	 * 
	 * @return selectedTool - the value of the selected tool field.
	 */

	public int getSelectedTool() {
		return selectedTool;
	}

	/**
	 * gets the value of the selectedTool field
	 * 
	 * @param selectedTool the value the selected tool should be set to
	 * 
	 */

	public void setSelectedTool(int selectedTool) {
		this.selectedTool = selectedTool;
	}

	/**
	 * returns the value of the centerXMult field
	 * 
	 * @return the value of the centerXMult field.
	 * 
	 */

	public double getCenterXMult() {
		return centerXMult;
	}

	/**
	 * gets the value of the centerYMult
	 * 
	 * @return the value of the centerYMult field.
	 * 
	 */
	public double getCenterYMult() {
		return centerYMult;
	}

	/**
	 * sets the values of the G2Players robotCenterX and robotCenterY field.
	 * 
	 * @param xCenter double, value of the robotCenterX field
	 * @param yCenter double, value of the robotCenterY field
	 * 
	 */
	public void setCenter(double xCenter, double yCenter) {
		robotCenterX = xCenter;
		robotCenterY = yCenter;

		xloc = robotCenterX - centerXMult * robotWidth;
		yloc = robotCenterY - ((double) centerYMult) * robotHeight;
	}

}
