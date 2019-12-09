package ui.utils.bpp;

import api.RestApiController;
import ui.utils.BPPLogManager;
import ui.utils.FileIO;
import ui.utils.ReporterManager;

import java.io.*;
import java.util.*;

/**
 * <p>This function uses a Java util, ResourceBundle, to identify file name located in src.
 * This returns a String value identified by the key in the property file </p>
 *
 * @author tchin
 */
public class PropertiesHelper {

    Properties properties;

    //Map is needed to store the web-elements' locator ('xpath', 'css-selector' etc.)
    private static Map<String, String> locatorProperties = new TreeMap<String, String>();

    private static final String PROPERTIES_FOLDER = PreProcessFiles.TEST_PROPERTIES_FILES_FOLDER_PATH;
    public static ReporterManager reporter = ReporterManager.Instance;

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
            inputStream = new FileInputStream(PreProcessFiles.ROOT_FOLDER_PATH + "/src/main/resources/api/configuration.properties");
            properties.load(inputStream);
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    /**
     * <p>It returns the property value specified in either environment variable or configuration.properties
     * It gives priority to the property specified in Java environment variable For e.g. -Ddriver_id=FIREFOX
     *
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

    /**
     * <p>
     * method needed to read the JSON file and:
     * - get web-elements' locator and store them in static variable called 'properties'.
     * This should be called before test
     * execution.
     *
     * @throws NullPointerException when failed to reade excel sheet
     *                              </p>
     */
    public static void gatherPropertiesFromJSON() throws NullPointerException {

        RestApiController controller = new RestApiController();

        try {
            Map<String, String> props = controller.processLocatorProperties("/src/main/resources/data/bpp/test.properties/Locators.json");
            locatorProperties.putAll(props);
        } catch (NullPointerException e2) {
            reporter.info("Failed to read sheet from properties file located in the " + PROPERTIES_FOLDER.toString());
            BPPLogManager.getLogger().error("Failed to read sheet from properties file located in the " + PROPERTIES_FOLDER.toString(), e2);
            throw new NullPointerException();
        } catch (Exception e3) {
            reporter.info("Failed to gather properties");
            BPPLogManager.getLogger().error("Failed to gather properties", e3);
            throw new NullPointerException();
        }
    }
}
