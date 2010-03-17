package de.topicmapslab.tmcledit.model.compare;

public class MyCompare {

	public static boolean intCompare(int one, int two) {

		if (one != two)
			return false;

		return true;
	}

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
