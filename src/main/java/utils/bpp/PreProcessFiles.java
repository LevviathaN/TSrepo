package utils.bpp;

//import com.apolloglobal.testing.api.util.*;

//import utils.bpp.NoahLogManager;
import utils.ReporterManager;
import utils.bpp.PropertiesHandler;
import utils.bpp.PropertiesHelper;

import java.io.IOException;

public class PreProcessFiles {

	//New reporter
	public static ReporterManager reporter = ReporterManager.Instance;

	//private static final Logger logger = Logger.getLogger(PreProcessFiles.class);

	public static String TEST_INPUT_FILES_FOLDER_PATH = null;
	public static String METADATA_AND_KEYWORDS_FILES_FOLDER_PATH = null;
	public static String TEST_PROPERTIES_FILES_FOLDER_PATH = null;
	public static String TEST_RESOURCES_FOLDER_PATH = null;
	public static String ROOT_FOLDER_PATH = null;
	public static String TEST_EXECUTION_FILE_NAME = "Test.xlsx";
	public static String TEST_EXECUTION_SHEET_NAME = "Test";
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
	 * @param args - argument that come from command line
	 */
	private void initPaths(String[] args){
		String rootFolder = System.getProperty("user.dir").replace("\\", "/");
		if (!classJar.startsWith("jar:")) {
			TEST_INPUT_FILES_FOLDER_PATH = rootFolder + "/src/main/resources/input";
			METADATA_AND_KEYWORDS_FILES_FOLDER_PATH = rootFolder + "/src/main/resources/keywords.metadata";
			TEST_PROPERTIES_FILES_FOLDER_PATH = rootFolder + "/src/main/resources/test.properties";
			TEST_RESOURCES_FOLDER_PATH = rootFolder + "/src/main/resources";
            ROOT_FOLDER_PATH = rootFolder;
			IMAGES_FOLDER_PATH = rootFolder + "/images";
			TEST_FILES_FOLDER_PATH = rootFolder + "/testFilesToUpload";
		}
		else {
			TEST_INPUT_FILES_FOLDER_PATH = rootFolder + "/input";
			METADATA_AND_KEYWORDS_FILES_FOLDER_PATH = rootFolder + "/resources/keywords.metadata";
			TEST_PROPERTIES_FILES_FOLDER_PATH = rootFolder + "/resources/test.properties";
			TEST_RESOURCES_FOLDER_PATH = rootFolder + "/resources";
			ROOT_FOLDER_PATH = rootFolder;
			IMAGES_FOLDER_PATH = rootFolder + "/images";
			TEST_FILES_FOLDER_PATH = rootFolder + "/testFilesToUpload";
		}
		reporter.info("ROOT_FOLDER_PATH: " + ROOT_FOLDER_PATH);
		reporter.info("TEST_INPUT_FILES_FOLDER_PATH: " + TEST_INPUT_FILES_FOLDER_PATH);
		reporter.info("METADATA_AND_KEYWORDS_FILES_FOLDER_PATH: " + METADATA_AND_KEYWORDS_FILES_FOLDER_PATH);
		reporter.info("TEST_PROPERTIES_FILES_FOLDER_PATH: " + TEST_PROPERTIES_FILES_FOLDER_PATH);
		reporter.info("TEST_RESOURCES_FOLDER_PATH: " + TEST_RESOURCES_FOLDER_PATH);
		reporter.info("IMAGES_FOLDER_PATH: " + IMAGES_FOLDER_PATH);
		reporter.info("TEST_FILES_TO_UPLOAD_FOLDER_PATH: " + TEST_FILES_FOLDER_PATH);

//		NoahLogManager.getLogger().info("ROOT_FOLDER_PATH: " + ROOT_FOLDER_PATH);
//		NoahLogManager.getLogger().info("TEST_INPUT_FILES_FOLDER_PATH: " + TEST_INPUT_FILES_FOLDER_PATH);
//		NoahLogManager.getLogger().info("METADATA_AND_KEYWORDS_FILES_FOLDER_PATH: " + METADATA_AND_KEYWORDS_FILES_FOLDER_PATH);
//		NoahLogManager.getLogger().info("TEST_PROPERTIES_FILES_FOLDER_PATH: " + TEST_PROPERTIES_FILES_FOLDER_PATH);
//		NoahLogManager.getLogger().info("TEST_RESOURCES_FOLDER_PATH: " + TEST_RESOURCES_FOLDER_PATH);
//		NoahLogManager.getLogger().info("IMAGES_FOLDER_PATH: " + IMAGES_FOLDER_PATH);
//		NoahLogManager.getLogger().info("TEST_FILES_TO_UPLOAD_FOLDER_PATH: " + TEST_FILES_FOLDER_PATH);
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
	public boolean preProcessTestConfiguration(String[] args) throws IOException {

		try {
			initPaths(args);
			PropertiesHandler.gatherProperties();

			boolean generateBeansXmlFile = true;
			if (args.length > 0) {
				if (args.length > 1) {
					TEST_EXECUTION_FILE_NAME = args[0];
//					TEST_EXECUTION_SHEET_NAME = args[1];
				} else {
					reporter.info("You must specify a string for the test xlsx file name such as Test.xlsx and the name of the test sheet");
//					NoahLogManager.getLogger().warn("You must specify a string for the test xlsx file name such as Test.xlsx and the name of the test sheet");
					generateBeansXmlFile = false;
				}
			}else {
				TEST_EXECUTION_FILE_NAME = System.getProperty("test.file");
			}

			if (generateBeansXmlFile) {
				reporter.info("Current Project Version: " + propertiesHelper.getProperties().getProperty("build.version"));
				//NoahLogManager.getLogger().info("Current Project Version: " + propertiesHelper.getProperties().getProperty("build.version"));
//				new BeansXmlGenerator().xmlGenerate(TEST_EXECUTION_FILE_NAME, TEST_EXECUTION_SHEET_NAME);
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


	/**
	 * <p>
	 * This function helps to iterate through the loop within the input folder
	 * and saves all the xlsx file present inside in the csv format
	 * </p>
	 */
//	public void getXlsxFileConvertToCsv() {
//		File folder = new File(TEST_INPUT_FILES_FOLDER_PATH);
//		List<File> list = new ArrayList<>(Arrays.asList(folder.listFiles())).stream()
//				.filter(file ->
//						(file.getName().equals(TEST_EXECUTION_FILE_NAME) | file.getName().startsWith("BusinessProcess"))
//				).collect(Collectors.toList());
//		for (File f : list) {
//			if (f.getName().indexOf(".xlsx") != -1) {
//				if (f.getName().indexOf("~") == -1) {
//					try {
//						ExcelToCsvHelper.convertExcelToCsv(f.getName());
//					} catch (IOException e) {
//						e.printStackTrace();
//					}
//				}
//			}
//		}
//	}
}