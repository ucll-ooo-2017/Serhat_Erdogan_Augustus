package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Evaluation {

	String result = "";
	InputStream inputStream;
	
	public String getPropValue() throws IOException {
		try {
			Properties prop = new Properties();
			String propFileName = "evaluation.properties";

			InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file " + propFileName + " not found in classpath");
			}
			String feedback = prop.getProperty("feedback");
			
			result = feedback;
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			inputStream.close();
		}
		return result;
	}

}
