package data;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
 
/**
 * @author Crunchify.com
 * 
 */
 
public class CrunchifyGetPropertyValues {
	String path = "";
	InputStream inputStream;
 
	public String getPropValues(String key) throws IOException {
 
		try {
			Properties prop = new Properties();
			String propFileName = "config.properties";
 
			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
 
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
 
			
			// get the property value and print it out
			path = prop.getProperty(key);
			
			return path;
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			inputStream.close();
		}
		return path;
	}
}