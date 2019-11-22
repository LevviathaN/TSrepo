package ui.utils;


import java.io.File;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

// CSV
// Properties
// XML

/**
 * Used to read/write properties from properties files and taking screenshots
 */
public class FileIO {

    static String TARGET_FOLDER = "target";
    static String DATA_RESOURCES = "src/main/resources/data/staging/";
    static String PROD_DATA_RESOURCES = "src/main/resources/data/production/";
    static String CONFIG_FILE = System.getProperty("config");
    static String PROPERTIES = "src/main/resources/" + ((CONFIG_FILE == null) ? "default" : CONFIG_FILE) + ".properties";
    static String EXTENT_CONFIG = "src/main/resources/extent-config.xml";

    public static String filename;

    public static String getConfigProperty(String fieldName) {
        String fileLocation = PROPERTIES;
        String result = null;

        try {
            //open file
            File file = new File(fileLocation);
            //open input stream to read file
            FileInputStream fileInput = new FileInputStream(file);
            //create Properties object
            Properties properties = new Properties();
            //load properties from file
            properties.load(fileInput);
            //close file
            fileInput.close();
            //get property by name
            result = properties.getProperty(fieldName);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            ReporterManager.Instance.fatalFail("Config was not found");
        } catch (IOException e) {
            e.printStackTrace();
            ReporterManager.Instance.fatalFail("Config was not opened");
        } catch (Exception e) {
            e.printStackTrace();
            ReporterManager.Instance.fatalFail("Field was not found: " + fieldName);
        }
        return result;
    }

    public static String getDataFile(String filename) {
        if (FileIO.getConfigProperty("EnvType").equals("PROD")) {
            return PROD_DATA_RESOURCES + filename;
        } else
            return DATA_RESOURCES + filename;
    }
}

