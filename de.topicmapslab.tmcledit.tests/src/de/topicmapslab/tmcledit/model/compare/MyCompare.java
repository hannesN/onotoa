package de.topicmapslab.tmcledit.model.compare;

public class MyCompare {

	/**
	 * Boolean compare of two integer values 
	 * 
	 * @param one int #1
	 * @param two int #1
	 * @return boolean result of the compare
	 */
	
	public static boolean intCompare(int one, int two) {

		if (one != two)
			return false;

		return true;
	}

	/**
	 * Check if two Strings are the same with the special condition,
	 * that two Null objects are equal.
	 * 
	 * @param one String #1
	 * @param two String #2
	 * @return boolean result of the compare
	 */
	
	public static boolean stringCompare(String one, String two) {

		if ((one == null) ^ (two == null))
			return false;

		if ((one == null) && (two == null))
			return true;

		if (one.equals(two))
			return true;

		return false;
	}

}
