package com.finger.birds.ucontroller.jstl;

public class StringUtils {

	public final static String EMPTY = "";

	/**
	 * Check the string is empty
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isEmpty(String s) {
		if (s == null || EMPTY.equals(s)) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param s
	 * @return
	 */
	public static String toLowerCase(String s) {
		int length = s.length();
		for (int i = 0; i < length; i++) {
			if (isUpperCase(s.charAt(i))) {// The upper case is appear ! To be
											// sure the first upper case char.
				char[] chars = s.toCharArray();
				for (; i < length; i++) {
					char c = chars[i];
					if (isUpperCase(c)) {
						chars[i] = (char) (c ^ 0x20);
					}
				}
				return String.valueOf(chars);
			}
		}
		return s;
	}

	/**
	 * @param string
	 * @return
	 */
	public static String toUpperCase(String string) {
		int length = string.length();
		for (int i = 0; i < length; i++) {
			if (isLowerCase(string.charAt(i))) {
				char[] chars = string.toCharArray();
				for (; i < length; i++) {
					char c = chars[i];
					if (isLowerCase(c)) {
						chars[i] = (char) (c & 0x5f);
					}
				}
				return String.valueOf(chars);
			}
		}
		return string;
	}

	/**
	 * Check the char is in 'A - Z'
	 * 
	 * @param c
	 * @return
	 */
	public static boolean isUpperCase(char c) {
		return (c >= 'A') && (c <= 'Z');
	}

	/**
	 * Check the char is in 'A - Z'
	 * 
	 * @param c
	 * @return
	 */
	public static boolean isLowerCase(char c) {
		return (c >= 'a') && (c <= 'z');
	}

	/**
	 * add the "..."
	 * 
	 * @param seq
	 * @param maxLength
	 * @param truncationIndicator
	 * @return
	 */
	public static String truncate(CharSequence seq, int maxLength, String truncationIndicator) {
		int len = seq.length();
		if (len <= maxLength) {
			return seq.toString();
		}
		return new StringBuilder(maxLength)
				.append(seq, 0, maxLength)
				.append(truncationIndicator)
				.toString();
	}
	
	
}
