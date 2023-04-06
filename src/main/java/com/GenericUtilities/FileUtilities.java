package com.GenericUtilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
/**
 * This class has all the reusable methods related to properties file.
 * @author sunil
 *
 */
public class FileUtilities {
	/**
	 * This method is used to read the data from the property file.
	 * @param key
	 * @return
	 */
	public String readData(String key) {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(IPathConstants.FILEPATH);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Properties property = new Properties();
		try {
			property.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String value = property.getProperty(key);
		return value;
	}
}
