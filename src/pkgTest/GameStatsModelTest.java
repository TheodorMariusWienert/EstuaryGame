package pkgTest;

import static org.junit.Assert.assertEquals;
import java.lang.Math;

import org.junit.Test;

import pkgEnum.AnimationState;
import pkgEnum.Species;
import pkgGamePiece.Animal;
import pkgGamePiece.Fish;
import pkgModel.GameStatsModel;

/**
 * Test for Game Stats Model
 * 
 * Created on 11/27/2019
 * 
 *  
 *  
 * @author Theo
 *
 * 
 */



public class GameStatsModelTest {
	double startEcon = 150;
	double startEnvirm = 150;
	GameStatsModel gmodel = new GameStatsModel();

	/**
	 * 
	 *  
	 */
	/**
	 * 
	 */
	@Test
	public void getEconScoreT1() {
		double testEconScore = startEcon;//
		double econScore = gmodel.getEconScore();

		assertEquals(testEconScore, econScore, 0.01);
	}

	/**
	 *  
	 * 
	 */
	@Test
	public void getEnvirmScoreT1() {
		double testEnvirmScore = startEnvirm;//
		double envirmScore = gmodel.getEnvirmScore();

		assertEquals(testEnvirmScore, envirmScore, 0.01);
	}

	/**
	 *  
	 *  
	 */

	@Test
	public void getDecisionNumberT1() {
		int testDecisionNumber = 0;//
		double decisionNumber = gmodel.getDecisionNumber();

		assertEquals(testDecisionNumber, decisionNumber, 0.01);
	}

	/**
	 *  
	 *  
	 */
	@Test
	public void setDecisionNumberT1() {
		int testDecisionNumber = 1;//
		gmodel.setDecisionNumber(testDecisionNumber);

		assertEquals(testDecisionNumber, gmodel.getDecisionNumber(), 0.01);
	}

	/**
	 *  
	 *  
	 */
	@Test
	public void getGameNumberT1() {
		int testGameNumber = 0;//
		double gameNumber = gmodel.getGameNumber();

		assertEquals(testGameNumber, gameNumber, 0.01);
	}

	/**
	 *  
	 *  
	 */
	@Test
	public void setGameNumberT1() {
		int testGameNumber = 1;//
		gmodel.setGameNumber(testGameNumber);

		assertEquals(testGameNumber, gmodel.getGameNumber(), 0.01);
	}

	/**
	 *  
	 *  
	 */
	@Test
	public void setEconScoreT1() {
		int testScore = 100;//
		gmodel.setEconScore(testScore);

		assertEquals(testScore, gmodel.getEconScore(), 0.01);
	}

	/**
	 *  
	 *  
	 */
	@Test
	public void setEnvirmScoreT1() {
		int testScore = 100;//
		gmodel.setEnvirmScore(testScore);

		assertEquals(testScore, gmodel.getEnvirmScore(), 0.01);
	}

	/**
	 *  
	 *  
	 */

	@Test
	public void calculateOverallScoreT1() {
		double testEcon = 10;
		double testEnvirm = 10;// currently update always sends the fish to the left and go up
		double score = gmodel.calculateOverallScore(testEcon, testEnvirm);
		double testTotal = 10 + 10;
		double testDifferenceAbs = Math.abs(testEcon - testEnvirm);
		double testResult = testTotal - testDifferenceAbs * 1 / 2;
		assertEquals(testResult, score, 0.01);

	}

	/**
	 *  
	 *  
	 */
	@Test
	public void getOverallScoreT1() {
		gmodel.setEconScore(startEcon);
		gmodel.setEnvirmScore(startEnvirm);
		double testOverallScore = gmodel.calculateOverallScore(startEcon, startEnvirm);

		assertEquals(testOverallScore, gmodel.getOverallScore(), 0.01);
	}

	/**
	 *  
	 *  
	 */

	@Test
	public void updateEconScoreT1() {
		double valueUpdate = 10;
		double testEcon = gmodel.getEconScore();
		gmodel.updateEconScore(valueUpdate);
		double newTestScore = testEcon + valueUpdate;
		assertEquals(newTestScore, gmodel.getEconScore(), 0.01);
	}

	/**
	 *  
	 *  
	 */
	@Test
	public void updateEnvirmScoreT1() {
		double valueUpdate = 10;
		double testEnvirm = gmodel.getEnvirmScore();
		gmodel.updateEnvirmScore(valueUpdate);
		double newTestScore = testEnvirm + valueUpdate;
		assertEquals(newTestScore, gmodel.getEnvirmScore(), 0.01);
	}
	
	/**
	 *  
	 */
	@Test
	public void setToInitialT1() {
		
		gmodel.setToInitalScores();
		
		assertEquals(startEcon, gmodel.getEconScore(), 0.01);
		assertEquals(startEnvirm, gmodel.getEnvirmScore(), 0.01);
	}
	
	

}
