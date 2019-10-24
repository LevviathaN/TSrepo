package utils.bpp_old;

import utils.bpp_old.PreProcessFiles;

import java.io.*;
import java.util.Formatter;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * <p>This function uses a Java util, ResourceBundle, to identify file name located in src.
 * This returns a String value identified by the key in the property file </p>
 * @author tchin
 * 
 */
public class PropertiesHelper {

	Properties properties;

	public static String getConfiguration(String configuration, String key) {
		if (configuration.equalsIgnoreCase("TestBeans")) {
			return ResourceBundle.getBundle("configuration", Locale.getDefault()).getString(key);
		}

		return "Configuration not found";
	}

	public Properties getProperties() {

		properties = new Properties();
		InputStream inputStream;
		try {
			inputStream = new FileInputStream(PreProcessFiles.TEST_RESOURCES_FOLDER_PATH + "/configuration.properties");
			properties.load(inputStream);
			inputStream.close();
			String version = properties.getProperty("build.version").toString();
			Formatter  f = new Formatter(PreProcessFiles.TEST_RESOURCES_FOLDER_PATH + "/ProjectVersion.txt");
			f.format("%s", version, " \r\n");
			f.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}

	public static String getVersionFromFile() {
		String currentProjectVersion = null;
		try {
			try(BufferedReader br = new BufferedReader(new FileReader(PreProcessFiles.TEST_RESOURCES_FOLDER_PATH + "/ProjectVersion.txt"))) {
				StringBuilder sb = new StringBuilder();
				String line = br.readLine();

				while (line != null) {
					sb.append(line);
					sb.append(System.lineSeparator());
					line = br.readLine();
				}
				currentProjectVersion = sb.toString();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "Current Project Version: " + currentProjectVersion;
	}


	/**
	 * <p>It returns the property value specified in either environment variable or configuration.properties
	 * It gives priority to the property specified in Java environment variable For e.g. -Ddriver_id=FIREFOX
	 * @param key used to search for property
	 * @return </p>
	 */
	public static String determineEffectivePropertyValue(String key) {

		PropertiesHelper propertiesHelper = new PropertiesHelper();

		if (null != System.getProperty(key)) {
			return System.getProperty(key);
		} else {
			return propertiesHelper.getProperties().getProperty(key);
		}
	}
}
