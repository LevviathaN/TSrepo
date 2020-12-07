package ui.utils.specialDataHandlers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author ruslanlevytskyi
 * <p>
 * The class to get locator properties for web-elements
 * </p>
 */
public class PropertiesHelper {

    Properties properties;

    /**
     * <p>This function used to get properties for API requests </p>
     *
     * @author rlevytskyi
     */
    public Properties getProperties() {

        properties = new Properties();
        InputStream inputStream;
        try {
            inputStream = new FileInputStream(PreProcessFiles.ROOT_FOLDER_PATH + "/src/main/resources/default.properties");
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
     * It gives priority to the property specified in Java environment variable For e.g. -Ddriver=FIREFOX
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
}
