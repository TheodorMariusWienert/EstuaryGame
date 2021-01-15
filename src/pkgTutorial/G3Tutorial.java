package pkgTutorial;

import java.util.ArrayList;

import pkgModel.G3Model;

/**
 * Used to create the Game three tutorial
 * @author Chandler
 * Created 11/8/2019
 *
 */
public class G3Tutorial extends G3Model {
	
	//Create an ordered checklist for tutorial steps
	private ArrayList<Boolean> tutorialChecklist = new ArrayList<Boolean>();
	private Boolean moveCharacter = new Boolean(false); 
	private Boolean placeProbe = new Boolean(false);
	private Boolean pickupProbe = new Boolean(false);
	private double initialX; 
	private double initialY;
	
	private static final double MINIMUM_MOVEMENT=0.01;
	
	/**
	 * Used as the constructor for the G3Tutorial objects  
	 * @param width  width of the canvas
	 * @param height  height of the canvas
	 * @param matrixDimX  X dimension of the maze matrix
	 * @param matrixDimy  Y dimension of the maze matrix
	 * @param yesTo3Probs  used to set the number of probes to 2 or 3 depending on the boolean value
	 */
	public G3Tutorial(double width, double height, int matrixDimX, int matrixDimy, boolean yesTo3Probs){
		super(width, height, matrixDimX, matrixDimy, true); 
		
		//Add the milestones to the list 
		tutorialChecklist.add(moveCharacter);
		tutorialChecklist.add(placeProbe);
		tutorialChecklist.add(pickupProbe);
		
		initialX = this.getPlayerX();
		initialY = this.getPlayerY();
	}
	
	/**
	 *  used to update the player position, and update the tutorial CheckList if things are completed. 
	 * 
	 * @param xInterval  player moving in x direction
	 * @param yInterval  player moving in y direction
	 * @param probeInteract  true if the player interacts with a probe
	 */
	public boolean update(int xInterval, int yInterval, boolean probeInteract) {
		
		boolean returnBool = super.update(xInterval, yInterval, probeInteract);

		//See if player moved the character
		if(this.getPlayerX() - initialX > MINIMUM_MOVEMENT || this.getPlayerY() - initialY > MINIMUM_MOVEMENT) {
			this.moveCharacter = true;
			tutorialChecklist.set(0,true);
		}
		
		//See if the player has placed a probe
		if(this.numProbes < 3) {
			this.placeProbe = true;
			tutorialChecklist.set(1, true);
		}
		
		//see if the player placed a probe, and now has all of them back
		if(tutorialChecklist.get(1) && numProbes == 3) {
			this.pickupProbe = true;
			tutorialChecklist.set(2,true);
		}
		return returnBool;
	}
	
	/**
	 * return the tutorial checkList 
	 * @return   the ArrayList that contains all of the objectives needed to complete the tutorial
	 */
	public ArrayList<Boolean> getTutorialCheckList(){return this.tutorialChecklist;}

}
