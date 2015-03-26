package me.guligo.tools.gentool.utils;

/**
 * Contains common helper functions.
 * 
 * @author guligo
 */
public class Utils {

	private static Utils instance;

	public static Utils getInstance() {
		if (instance == null) {
			instance = new Utils();
		}
		return instance;
	}

	private Utils() {
		// do nothing
	}

	public String removeLastLine(String str) {
		StringBuilder sb = new StringBuilder(str);
		int lastIndex = sb.lastIndexOf(System.lineSeparator());
		sb.delete(lastIndex, lastIndex + System.lineSeparator().length());
		return sb.toString();
	}

}
