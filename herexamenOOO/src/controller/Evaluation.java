package controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Evaluation {

	String result = "";
	InputStream inputStream;
	FileOutputStream outputStream;

	public String getPropValue(String key) throws IOException {
		try {
			Properties prop = new Properties();
			String propFileName = "evaluation.properties";

			// inputStream =
			// getClass().getClassLoader().getResourceAsStream(propFileName);
			inputStream = new FileInputStream("evaluation.properties");
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file " + propFileName + " not found in classpath");
			}
			String feedback = prop.getProperty(key);

			result = feedback;
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			inputStream.close();
		}
		return result;
	}

	public void setProperty(String propName, String propValue) {
		try {
			Properties prop = new Properties();
			Properties prop2 = new Properties();

			InputStream inputStream = new FileInputStream("evaluation.properties");
			prop2.load(inputStream);
			inputStream.close();

			outputStream = new FileOutputStream("evaluation.properties");
			prop.setProperty("test", prop2.getProperty("test"));
			prop.setProperty(propName, propValue);
			prop.store(outputStream, null);

		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			try {
				this.outputStream.close();
			} catch (IOException e) {
				System.out.println("Exception: " + e);

			}
		}

	}

}
