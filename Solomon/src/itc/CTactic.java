package itc;

import java.util.*;
import robocode.*;


public class CTactic {

	
	//This is the success threshold that a tactic needs to be labelled as effective 
	protected final double GAUGING_THRESHOLD = 0.7; 
	//protected List gaugingList = new ArrayList();
	protected List<Byte> gaugingList  = new ArrayList<Byte>();

	protected Random r = new Random();
	
	/**
	 * This should be overwritten by every tactic.
	 * @param s
	 */
	public void run_(solomon s)
	{

	}
	
	/**
	 * This should be overwritten by every tactic.
	 * @param s
	 * @param e
	 */
	public void onScannedRobot_(solomon s, ScannedRobotEvent e)
	{
		
	}
	
	/**
	 * This should be overwritten by every tactic.
	 * @param s
	 * @param e
	 */
	public void onHitByBullet_(solomon s, HitByBulletEvent e)
	{
		
	}
	
	/**
	 * This should be overwritten by every tactic.
	 * @param s
	 * @param e
	 */
	public void onHitRobot_(solomon s, HitRobotEvent e)
	{
		
	}
	
	/**
	 * Uses the distance of the enemy robot to figure out how
	 * much energy to expend when firing. The bias varies, depending on the
	 * robot's status. If it's very aggressive, it'll be more likely
	 * to fire with full strength. If very defensive, it'll almost never
	 * do that, and so on, so forth.
	 * 
	 * @param s
	 * @param enemyDist
	 */
	protected void fire(solomon s, double enemyDist) {
		// TODO: This can be simplified (probably very easily so it doesn't require a case statement);
	
		// Case statement to pick bias.
		int bias = 0;
		switch (s.getStatus()) {
			case 0 :
				bias = 400;
				break;
			case 1 :
				bias = 300;
				break;
			case 2 :
				bias = 200;
				break;
			case 3 :
				bias = 100;
				break;
			default :
				break;
		}
		
		double firePower = 0;
		
		// This if statement is so that if the enemy is more than 500 units away, it won't even bother with the bias
		// or if it's closer than 100 units, it'll go straight to full power.
		if (enemyDist > 500)
		{
			firePower = 0.1;
		}
		else if (enemyDist < 100)
		{

			firePower = 5.0;
			
			s.fire(firePower);
			s.fire(firePower);
			s.fire(firePower);
		}
		else
		{
			firePower = bias/enemyDist;
		}
		
		
		s.fire(firePower);
	}
	
	
	/**
	 * Calculates the efficiency of the tactic using the gaugingList
	 * and returns true or false
	 * @return
	 */
	public boolean isGoodTactic(int status)
	{
		boolean result = false;
		double sumOfGauging = 0;	
		double sumOfArray = 0;
		double sumOfElements = (double)gaugingList.size();
		
		for(int i = 0; i < sumOfElements; i++)
		{
		  sumOfArray += gaugingList.get(i);
		}
		
		if(sumOfElements != 0)
		{
		   sumOfGauging = (sumOfArray/sumOfElements);
		   //System.out.println("sumOfGauging = " + sumOfGauging + " ( " + sumOfArray + " / " + sumOfElements);
		}
		else
		{
			sumOfGauging = 1;
		}
		
		if(sumOfGauging > GAUGING_THRESHOLD)
		{
			result = true;
		}
		
		return result;
	}
	
	/**
	 *  Returns a random number.
	 *  @return
	 */
	protected double getRandom()
	{
		return r.nextDouble();
	}
	/**
	 *  Returns a number, between zero and input.
	 * @param highest
	 * @return
	 */
	protected double getRandom(int highest)
	{
		return (double)(r.nextInt(highest));
	}
	
	// What follows are radian translations of calculations that return degrees. Done for compatability with Math.*;
	
	/**
	 * Turns the gun right by the specified number of radians.
	 * 
	 * @param s
	 * @param amountToRotateRadians
	 */
	protected void turnGunRightRadians(solomon s, double amountToRotateRadians) {
		s.turnGunRight((amountToRotateRadians/180)*Math.PI);
	}
	
	/**
	 * Turns the gun right by the specified number of radians.
	 *
	 * @param s
	 * @param amountToRotateRadians
	 */
	protected void turnRadarRightRadians(solomon s, double amountToRotateRadians) {
		s.turnRadarRight((amountToRotateRadians/180)*Math.PI);
	}
	
	/**
	 * Turns the whole robot right by the specified number of radians.
	 *
	 * @param s
	 * @param amountToRotateRadians
	 */
	protected void turnRightRadians(solomon s, double amountToRotateRadians) {
		s.turnRight((amountToRotateRadians/180)*Math.PI);
	}

	/**
	 * Gets the heading in radians.
	 * @param s
	 * @return
	 */
	protected double getHeadingRadians(solomon s) 
	{
		return (s.getHeading() * (Math.PI/180));
	}
	
	/**
	 * Gets the gun heading in radians.
	 * @param s
	 * @return
	 */
	protected double getGunHeadingRadians(solomon s) 
	{
		return (s.getGunHeading() * (Math.PI/180));
	}
	
	/**
	 * Gets the radar heading in radians.
	 * @param s
	 * @return
	 */
	protected double getRadarHeadingRadians(solomon s)
	{
		return (s.getRadarHeading() * (Math.PI/180));
	}
	
	/**
	 * Takes in amount in degrees and returns it in radians
	 * @param degrees
	 * @return
	 */
	protected double convertToRadians(double degrees)
	{
		return (degrees * (Math.PI/180));
	}
	
	/**
	 * Takes in radians and converts to degrees.
	 * @param radians
	 * @return
	 */
	protected double convertToDegrees(double radians)
	{
		return (radians * (180/Math.PI));
	}
}
