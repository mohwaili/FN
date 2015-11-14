package Helpers;

import java.util.concurrent.ThreadLocalRandom;

public class Helper {

	/**
	 * Generating random number
	 * 
	 * @param none
	 */
	public static int generateRandomNumber(int min, int max) {
		return ThreadLocalRandom.current().nextInt(min, max+1);
	}
	
}
