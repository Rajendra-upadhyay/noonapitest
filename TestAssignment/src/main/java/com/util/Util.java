package com.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Util {
	public static Logger logger = LogManager.getLogger(Util.class.getName());

	public Properties readPropertiesFile(String fileName) throws IOException {
		FileInputStream fis = null;
		Properties prop = null;
		try {
			fis = new FileInputStream(fileName);
			prop = new Properties();
			prop.load(fis);
		} catch (IOException exception) {
			exception.printStackTrace();
		} finally {
			fis.close();
		}
		return prop;
	}
}
