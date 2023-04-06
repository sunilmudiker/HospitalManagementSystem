package com.GenericUtilities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
/**
 * This class has all reusable methods related to java utilities.
 * @author sunil
 *
 */
public class JavaUtilities {
	/**
	 * This method is used to get random number.
	 * @param limit
	 * @return
	 */
	public int getRandomNum(int limit) {
		Random random = new Random();
		int num = random.nextInt(limit);
		return num;
	}
	/**
	 * This method is used to get system date.
	 * @return
	 */
	public String getSystemDate() {
		Date dt = new Date();
		String date = dt.toString();
		return date;
	}
	/**
	 * This method is used to get formatted date.
	 * @return
	 */
	public String getFormattedDate() {
		SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yy hh-mm-ss");
		Date dt = new Date();
		String date = sd.format(dt);
		return date;
	}
}
