package pkgEnum;

import java.util.Random;

/**
 *  Used to set the species of the animals in the second game.
 *  @author Matthew and Chandler
 */
public enum Species {

	BLUECATFISH("Blue Catfish", true), SNAKEHEAD("Snakehead", true), BROOKTROUT("Brook Trout", false),
	LARGEMOUTHBASS("Large Mouth Bass", false), BLUECRAB("Blue Crab", false), MITTENCRAB("Mitten Crab", true);

	private String name = null;
	private boolean isInvasive;
	private static Species[] ANIMAL_ARRAY = Species.values();
	private static int FISH_SIZE = 4; // Number of fish, must be first species
	private static int CRUST_SIZE = 2;
	private static final Random rand = new Random(System.currentTimeMillis());

	/**
	 * 
	 * 
	 * set the name and isInvasive field of the enum
	 * 
	 * @author Matthew and Chandler
	 * @param name       String, name of the species
	 * @param isInvasive boolean, determines whether the species is invasive or not
	 * 
	 */
	private Species(String name, boolean isInvasive) {
		this.name = name;
		this.isInvasive = isInvasive;
	}

	/**
	 *  return the value of the name field of the enum
	 *         @author Matthew and Chandler Method
	 * @return String the value in the name field of the enum
	 */
	public String getName() {
		return name;
	}

	/**
	 * returns a Species that is used to change what the fish species is.
	 * 
	 * @author Matthew and Chandler
	 * @return Species returns a new enum so that the fish species can change
	 * 
	 */
	public static Species randomFish() {
		return ANIMAL_ARRAY[rand.nextInt(FISH_SIZE)];
	}

	/**
	 * returns a Species that is used to change what the crustacean species is.
	 *  @author Matthew and Chandler
	 * @return returns a new enum so that the fish species can change.
	 */
	public static Species randomCrust() {
		return ANIMAL_ARRAY[rand.nextInt(CRUST_SIZE) + FISH_SIZE];
	}

	/**
	 * 
	 * returns the value of teh isInvasive field of the enum
	 * @author Matthew and Chandler
	 * @return isInvasive, says whether the species is invasive or not
	 * 
	 */
	public boolean getIsInvasive() {
		return this.isInvasive;
	}

};
