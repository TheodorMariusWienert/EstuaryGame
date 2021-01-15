package pkgGamePiece;

/**
 * Inherited by the non-PlayerChar GamePieces. Holds the x/y increments as well 
 * as dictates the existence of a no arg update method
 * 
 * Created on 11/1/2019
 *  @author  Matthew
 * 
 */
public abstract class Item extends GamePiece {

	/**
	 *  Constructor for abstract class Item
	 *
	 * 
	 * @param X double, initial x location of item
	 * @param Y  double, initial y location of item
	 */
	protected Item(double X, double Y) {
		super(X, Y);
	}
	
	/**
	 * Constructor for abstract class Item
	 * 
	 * @param xIncr  double, the value that you wish to set the xIncr field to 
	 */
	public void setXIncr(double xIncr) {
		this.xIncr = xIncr;
	}
	
	/**
	 *  Constructor for abstract class Item
	 * 
	 * 
	 * @param yIncr  double, the value that you wish to set the yIncr field to 
	 */
	public void setYIncr(double yIncr) {
		this.yIncr = yIncr;
	}
	
	/**
	 * abstract method to be implemented in subclasses. 
	 * 
	 */
	public abstract void update();
}
