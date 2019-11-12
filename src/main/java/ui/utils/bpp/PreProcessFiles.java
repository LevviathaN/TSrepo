package ui.utils.bpp;

//import com.apolloglobal.testing.api.util.*;


import org.apache.log4j.Logger;
import ui.utils.ReporterManager;

import java.io.IOException;

public class PreProcessFiles {

	//New reporter
	public static ReporterManager reporter = ReporterManager.Instance;

	public static org.apache.log4j.Logger logger = Logger.getLogger(PreProcessFiles.class);

	public static String TEST_INPUT_FILES_FOLDER_PATH = null;
	public static String METADATA_AND_KEYWORDS_FILES_FOLDER_PATH = null;
	public static String TEST_PROPERTIES_FILES_FOLDER_PATH = null;
	public static String TEST_RESOURCES_FOLDER_PATH = null;
	public static String ROOT_FOLDER_PATH = null;
	public static String TEST_EXECUTION_FILE_NAME = "Test.xlsx";
	public static String IMAGES_FOLDER_PATH = null;
	public static String TEST_FILES_FOLDER_PATH = null;

	PropertiesHelper propertiesHelper;
	public PreProcessFiles(){
        this.propertiesHelper = new PropertiesHelper();
    }

	String className = this.getClass().getName().replace('.', '/');
	String classJar =
			this.getClass().getResource("/" + className + ".class").toString();


	/**
	 * The method is used for providing the right paths to the NoahClient's and the Noah's resources folders
	 * - argument that come from command line
	 */
	public void initPaths(){
		String rootFolder = System.getProperty("user.dir").replace("\\", "/");
		if (!classJar.startsWith("jar:")) {
			TEST_INPUT_FILES_FOLDER_PATH = rootFolder + "/src/main/resources/data/bpp/input";
			METADATA_AND_KEYWORDS_FILES_FOLDER_PATH = rootFolder + "/src/main/resources/data/bpp/keywords.metadata";
			TEST_PROPERTIES_FILES_FOLDER_PATH = rootFolder + "/src/main/resources/data/bpp/test.properties";
			TEST_RESOURCES_FOLDER_PATH = rootFolder + "/src/main/resources/data/bpp/";
            ROOT_FOLDER_PATH = rootFolder;
			IMAGES_FOLDER_PATH = rootFolder + "/images";
			TEST_FILES_FOLDER_PATH = rootFolder + "/testFilesToUpload";
		}
		else {
			TEST_INPUT_FILES_FOLDER_PATH = rootFolder + "/input";
			METADATA_AND_KEYWORDS_FILES_FOLDER_PATH = rootFolder + "/resources/data/bpp/keywords.metadata";
			TEST_PROPERTIES_FILES_FOLDER_PATH = rootFolder + "/resources/test.properties";
			TEST_RESOURCES_FOLDER_PATH = rootFolder + "/resources";
			ROOT_FOLDER_PATH = rootFolder;
			IMAGES_FOLDER_PATH = rootFolder + "/images";
			TEST_FILES_FOLDER_PATH = rootFolder + "/testFilesToUpload";
		}

	}


	/**
	 * 
	 * Determines if arguments have been provided to run tests locally from the
	 * project level rather than expectedly the NoahClient. The files are
	 * expected to be located locally from the current directory under a folder
	 * named, "input." Default values to run the test execution are Test.xlsx
	 * and sheet name "Test". The end user is expected to configure their test
	 * execution (change files and sheets) through the batch file in the NoahClient.
	 * 
	 * @author tchin
	 * @param args criteria specified by the end user in the NoahClient project
	 * @throws IOException will throw exception ending the test execution if invalid information is available
	 * @return returns true if all test configurations are correct and normal test execution can proceed
	 */
	public boolean preProcessTestConfiguration(String... args) throws IOException {

		try {
			initPaths();
			PropertiesHandler.gatherProperties();

			boolean generateBeansXmlFile = true;
			if (args.length > 0) {
				if (args.length > 1) {
					TEST_EXECUTION_FILE_NAME = args[0];
				} else {
					logger.info("You must specify a string for the test xlsx file name such as Test.xlsx and the name of the test sheet");
					generateBeansXmlFile = false;
				}
			}else {
				TEST_EXECUTION_FILE_NAME = System.getProperty("test.file");
			}

			if (generateBeansXmlFile) {
				logger.info("Current Project Version: " + propertiesHelper.getProperties().getProperty("build.version"));
			}
			else {
				return false;
			}

			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return  false;
		}
	}

}