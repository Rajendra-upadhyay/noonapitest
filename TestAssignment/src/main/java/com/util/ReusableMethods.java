package com.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

public class ReusableMethods {

	public static String getFilePath() {
		return "./resources/config.properties";
	}

	public static Properties getProperties() {
		Util util = new Util();
		Properties prop = null;
		try {
			prop = util.readPropertiesFile(ReusableMethods.getFilePath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop;
	}

	public static HashMap<String, Object> gettokenPayload1() {
		HashMap<String, Object> hm = new HashMap<String, Object>();
		hm.put("email", "test@gmail.com");
		hm.put("password", "Qwerty123");
		return hm;
	}

	
}