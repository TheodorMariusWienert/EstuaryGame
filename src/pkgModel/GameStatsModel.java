package pkgModel;

import java.lang.Math;

/**
 * 
 * 
 * model for keeping track of scores between and during minigames.
 * 
 * @author Theodor Created 11/15/2019
 *
 */
public class GameStatsModel extends Model implements java.io.Serializable {
	private final int BEGINN_ECON = 150;
	private final int BEGINN_ENVIRM = 150;
	double econScore; // TODO Set start values if you change it change change in Test
	double envirmScore;// TODO Set start valuesif you change it change change in Test
	double overallScore;
	int decisionNumber = 0;
	int gameNumber = 0;

	public GameStatsModel() {
		econScore = BEGINN_ECON;
		envirmScore = BEGINN_ENVIRM;

		calculateOverallScore();
	}

	/**
	 * 
	 * calculates the Score with the econ and envirm Score in the gameStatsModel
	 * 
	 */

	public void calculateOverallScore() {
		double total = (econScore + envirmScore);
		double differenceAbs = Math.abs(econScore - envirmScore);

		overallScore = total - differenceAbs * 1 / 2;
	}

	/**
	 * 
	 * Returns the calculated Score but doesn't set it
	 * 
	 * @param econScore   double, the value of the current economic score
	 * @param envirmScore double, the value of the current environmental score
	 * @return overallScore double, returns the calculated overall score
	 */
	public double calculateOverallScore(double econScore, double envirmScore) {
		double total = (econScore + envirmScore);
		double differenceAbs = Math.abs(econScore - envirmScore);

		return total - differenceAbs * 1 / 2;

	}

	/**
	 *
	 * return the econScore field
	 * 
	 * @return econScore double, value of the economic score field.
	 */
	public double getEconScore() {
		return econScore;
	}

	/**
	 * 
	 * updateEconScore(double econScore)
	 * 
	 * @param econScore double, value to add to the econScore
	 * 
	 */
	public void updateEconScore(double econScore) {
		this.econScore = this.econScore + econScore;
		calculateOverallScore();
	}

	/**
	 * 
	 * returns the envirmScore field
	 * 
	 * 
	 * @return envirmScore double, the value of the environmental score field
	 * 
	 */
	public double getEnvirmScore() {
		return envirmScore;
	}

	/**
	 * 
	 * update the envirmScore field
	 *
	 * @param envirmScore double, value to add to the envirmScore field
	 * 
	 */
	public void updateEnvirmScore(double envirmScore) {
		this.envirmScore = this.envirmScore + envirmScore;
		calculateOverallScore();
	}

	/**
	 *
	 * returns the overallScore field
	 * 
	 * @return overallScore double, returns the value of the overall score field
	 */
	public double getOverallScore() {
		calculateOverallScore();
		return overallScore;
	}

	/**
	 *
	 * returns the decisionNumber field
	 * 
	 * @return decisionNumber int, value of the decision number field
	 */
	public int getDecisionNumber() {
		return decisionNumber;
	}

	/**
	 * 
	 * sets the decisionNumber to the value of the argument
	 * 
	 * @param decisionNumber int, number to change the decision number field to
	 * 
	 */
	public void setDecisionNumber(int decisionNumber) {
		this.decisionNumber = decisionNumber;
	}

	/**
	 * returns the value of the gameNumber field
	 * 
	 * @return gameNumber - int, the current game number.
	 */
	public int getGameNumber() {
		return gameNumber;
	}

	/**
	 * set the gameNumber to the value of the argument
	 * 
	 * @param gameNumber - int, the value to set the current game number to
	 */
	public void setGameNumber(int gameNumber) {
		this.gameNumber = gameNumber;
	}

	/**
	 * 
	 * sets the econScore field to the argument
	 * 
	 * @param econScore - double, value to set the current economic score field to
	 */
	public void setEconScore(double econScore) {
		this.econScore = econScore;
		calculateOverallScore();
	}

	/**
	 * sets the envirmScore to the value of the argument
	 * 
	 * @param envirmScore - double, value to set the environmental score to
	 */
	public void setEnvirmScore(double envirmScore) {
		this.envirmScore = envirmScore;

		calculateOverallScore();
	}

	/**
	 *
	 * sets the inital score to the constants BEGINN_ECON and BEGINN_ENVIRM
	 * 
	 */
	public void setToInitalScores() {
		econScore = BEGINN_ECON;
		envirmScore = BEGINN_ENVIRM;
		calculateOverallScore();

	}

}
