package ui.utils.bpp;

import java.io.IOException;

public class PreProcessFiles {

	@edu.umd.cs.findbugs.annotations.SuppressFBWarnings("MS_PKGPROTECT")
	public static String METADATA_AND_KEYWORDS_FILES_FOLDER_PATH = null;
	public static String PROPERTIES_FILES_FOLDER_PATH = null;
	public static String ROOT_FOLDER_PATH = null;
	public static String TEST_FILES_FOLDER_PATH = null;

	public PreProcessFiles(){
    }

	String className = this.getClass().getName().replace('.', '/');
	String classJar =
			this.getClass().getResource("/" + className + ".class").toString();


	/**
	 * The method is used for providing the right paths to the NoahClient's and the Noah's resources folders
	 * - argument that come from command line
	 */
	public void initPaths(boolean moduleUI){
		String rootFolder = System.getProperty("user.dir").replace("\\", "/");
		if (!classJar.startsWith("jar:")) {

			ROOT_FOLDER_PATH = rootFolder;
			METADATA_AND_KEYWORDS_FILES_FOLDER_PATH = rootFolder + "/src/main/resources/data/bpp/keywords.metadata";

			if(moduleUI){
			PROPERTIES_FILES_FOLDER_PATH = rootFolder + "/src/main/resources/default.properties";
			TEST_FILES_FOLDER_PATH = rootFolder + "/testFilesToUpload";
			}
		}
		else {
			ROOT_FOLDER_PATH = rootFolder;
			METADATA_AND_KEYWORDS_FILES_FOLDER_PATH = rootFolder + "/resources/data/bpp/keywords.metadata";
			TEST_FILES_FOLDER_PATH = rootFolder + "/testFilesToUpload";
			if (moduleUI) {
				PROPERTIES_FILES_FOLDER_PATH = rootFolder + "/resources/default.properties";
			}
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
			initPaths(true);
			PropertiesHelper.gatherPropertiesFromJSON();

			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return  false;
		}
	}

}