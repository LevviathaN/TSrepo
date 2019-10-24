package utils.bpp;

import utils.ReporterManager;
import utils.bpp.PreProcessFiles;
import utils.bpp.ExcelGetDataHelper;
//import utils.bpp.NoahLogManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Functionality requested in AGQA-851 Created by Nick B. on 4/27/2017.
 * Functionality requested in AGQA-937. Updated by Serge S. on 7/25/2017.
 * Functionality requested in AGQA-1022. Updated by Nick B. on 8/7/2017.
 * <p>
 * The class is used for handling the properties passed as test parameters.
 * </p>
 */
public class PropertiesHandler {

    //New reporter
    public static ReporterManager reporter = ReporterManager.Instance;

    //private static final Logger log = Logger.getLogger(PropertiesHandler.class);

    private static final String PROPERTIES_FOLDER = /** todo: hardcoded path */ "src/main/resources/data/bpp/test/properties";

    //Next map is needed to store the web-elements' locator ('xpath', 'css-selector' etc.)
    private static Map<String, String> properties = new TreeMap<String, String>();

    //Next map is needed to store the frames that may contain any particular web-element the framework needs to deal with.
    private static Map<String, String> ancestors = new TreeMap<String, String>();

    //Next map is needed to store the conditional event that needs to be handled when interacting with any particular web-element.
    private static Map<String, String> conditionalEvents = new TreeMap<String, String>();

    /**
     * <p>
     * method needed to read the excel file and:
     * - get web-elements' locator and store them in static variable called 'properties'.
     * - get frames that may contain web-elements and store them in static variable called 'ancestors'.
     * - get conditional event that need to be executed after interacting with any particular web-element and store them in
     * static variable called 'conditionalEvents'.
     * This should be called before test
     * execution.
     *
     * @throws IOException          when properties file is not found.
     * @throws NullPointerException when failed to reade excel sheet
     *                              </p>
     */
    public static void gatherProperties() throws IOException, NullPointerException {

        ExcelGetDataHelper excelGetDataHelper = new ExcelGetDataHelper("LocatorPropertiesUI.xlsx");

        try {
            List<ArrayList<String>> propertiesList = excelGetDataHelper.getDataFromPropertiesSheet(PROPERTIES_FOLDER);
            int logicalNameIndex = 1;
            int parentIndex = 2;
            int descriptorIndex = 4;
            int conditionalEvent = 5;

            for (ArrayList<String> list : propertiesList) {
                properties.put(list.get(logicalNameIndex), list.get(descriptorIndex));
                ancestors.put(list.get(logicalNameIndex), list.get(parentIndex));
                conditionalEvents.put(list.get(logicalNameIndex), list.get(conditionalEvent));
            }
        } catch (IOException e1) {
            reporter.info("Failed to read properties file located in the " + PROPERTIES_FOLDER.toString());
            //NoahLogManager.getLogger().error("Failed to read properties file located in the " + PROPERTIES_FOLDER.toString(), e1);
            throw new IOException();
        } catch (NullPointerException e2) {
            reporter.info("Failed to read sheet from properties file located in the " + PROPERTIES_FOLDER.toString());
            //NoahLogManager.getLogger().error("Failed to read sheet from properties file located in the " + PROPERTIES_FOLDER.toString(), e2);
            throw new NullPointerException();
        } catch (Exception e3) {
            reporter.info("Failed to gather properties");
            //NoahLogManager.getLogger().error("Failed to gather properties", e3);
            throw new NullPointerException();
        }
    }

    /**
     * <p>
     *
     * @param key seeking property's name
     * @return returns the descriptor (locator) by Key
     * </p>
     */
    public static String getPropertyByKey(String key) {
        if (properties.containsKey(key)) {
            return properties.get(key);
        } else {
            reporter.info("Requested " + key + " property was not found. "
                    + "\n\t\tPossible reasons are:\n"
                    + "\t\t- the property is missed in properties file provided into the "
                    + PreProcessFiles.TEST_PROPERTIES_FILES_FOLDER_PATH + "/ folder path\n"
                    + "\t\t- the requested property is misspelled in the excel spreadsheet provided into the "
                    + PreProcessFiles.TEST_INPUT_FILES_FOLDER_PATH + "/ folder path.");
            //NoahLogManager.getLogger().warn("Requested " + key + " property was not found. "
            //                    + "\n\t\tPossible reasons are:\n"
            //                    + "\t\t- the property is missed in properties file provided into the "
            //                    + PreProcessFiles.TEST_PROPERTIES_FILES_FOLDER_PATH + "/ folder path\n"
            //                    + "\t\t- the requested property is misspelled in the excel spreadsheet provided into the "
            //                    + PreProcessFiles.TEST_INPUT_FILES_FOLDER_PATH + "/ folder path.");
            return key;
        }
    }

    /**
     * <p>
     *
     * @param key - seeking for element ancestor
     * @return returns the ancestor by provided Key.
     * </p>
     */
    public static String getParentByKey(String key) {
        if (ancestors.containsKey(key)) {
            return ancestors.get(key);
        } else {
            reporter.info("Requested " + key + " property was not found. "
                    + "\n\t\tPossible reasons are:\n"
                    + "\t\t- the property is missed in properties file provided into the "
                    + PreProcessFiles.TEST_PROPERTIES_FILES_FOLDER_PATH + "/ folder path\n"
                    + "\t\t- the requested property is misspelled in the excel spreadsheet provided into the "
                    + PreProcessFiles.TEST_INPUT_FILES_FOLDER_PATH + "/ folder path.");
            //NoahLogManager.getLogger().warn("Requested " + key + " property was not found. "
            //                    + "\n\t\tPossible reasons are:\n"
            //                    + "\t\t- the property is missed in properties file provided into the "
            //                    + PreProcessFiles.TEST_PROPERTIES_FILES_FOLDER_PATH + "/ folder path\n"
            //                    + "\t\t- the requested property is misspelled in the excel spreadsheet provided into the "
            //                    + PreProcessFiles.TEST_INPUT_FILES_FOLDER_PATH + "/ folder path.");
            return "";
        }
    }

    /**
     * <p>
     *
     * @param key - seeking for the conditional event that needs to be executed when interacting with a web-element
     * @return returns the conditional event by Key
     * </p>
     */
    public static String getConditionalEventByKey(String key) {
        if (conditionalEvents.containsKey(key)) {
            return conditionalEvents.get(key);
        } else {
            reporter.info("Requested " + key + " property was not found."
                    + "\n\t\tPossible reasons are:\n"
                    + "\t\t- the property is missed in properties file provided into the "
                    + PreProcessFiles.TEST_PROPERTIES_FILES_FOLDER_PATH + "/ folder path\n"
                    + "\t\t- the requested property is misspelled in the excel spreadsheet provided into the "
                    + PreProcessFiles.TEST_INPUT_FILES_FOLDER_PATH + "/ folder path.");
            //NoahLogManager.getLogger().warn("Requested " + key + " property was not found."
            //                    + "\n\t\tPossible reasons are:\n"
            //                    + "\t\t- the property is missed in properties file provided into the "
            //                    + PreProcessFiles.TEST_PROPERTIES_FILES_FOLDER_PATH + "/ folder path\n"
            //                    + "\t\t- the requested property is misspelled in the excel spreadsheet provided into the "
            //                    + PreProcessFiles.TEST_INPUT_FILES_FOLDER_PATH + "/ folder path.");
            return "";
        }
    }
}
