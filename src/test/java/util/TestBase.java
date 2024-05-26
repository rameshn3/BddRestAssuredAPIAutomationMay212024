package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class TestBase {
	public static String getPropertyValue(String key) throws IOException {
	    Properties prop = new Properties();
	    try {
	        // Construct the path using Paths.get to ensure platform independence
	        Path path = Paths.get(System.getProperty("user.dir"), "src", "test", "resources", "config.properties");
	        // Ensure the file exists
	        if (Files.exists(path)) {
	            try (FileInputStream fis = new FileInputStream(path.toFile())) {
	                // Load all the properties
	                prop.load(fis);
	            }
	        } else {
	            throw new FileNotFoundException("Properties file not found at " + path.toString());
	        }
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    }
	    return prop.getProperty(key);
	}

}

