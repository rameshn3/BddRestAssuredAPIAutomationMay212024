package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestBase {

	
public static String getPropertyValue(String key) throws IOException {
	Properties prop = new Properties();
	try {
		//read the properties file
		File f = new File(System.getProperty("user.dir")+"\\src\\test\\resources\\config.properties");
		FileInputStream fis = new FileInputStream(f);
		//load all the properties
		prop.load(fis);
	} catch (FileNotFoundException e) {
		
		e.printStackTrace();
	}
	return prop.getProperty(key);
}
	
}
