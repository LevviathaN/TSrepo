package ui.utils.bpp;

import datageneration.execution.ExecutionContext;
//import ui.utils.bpp.NoahLogManager;
import ui.utils.ReporterManager;
//import ui.utils.bpp.Reporter;

import java.util.Map;

/**
 * Functionality requested in AGQA-865
 * Created by Nick B. on 4/25/2017.
 * <p>The class is used for handling the execution context variables during the tests running.
 * This class is implemented using the DataManagementUtil-1.5.1 library provided by UTOPIA SOLUTIONS </p>
 */

public class ExecutionContextHandler {

    static ReporterManager reporter = ReporterManager.Instance;

    //private static final Logger log = Logger.getLogger(ExecutionContextHandler.class);

    private static final String PREFIX = "EC_";

    private static final ThreadLocal<ExecutionContext> handler = new ThreadLocal<ExecutionContext>() {
        @Override
        protected ExecutionContext initialValue() {
            return new ExecutionContext(PREFIX);
        }
    };

    public static String getExecutionContextValueByKey(String key) {
        ExecutionContext executionContext  = handler.get();
        if (executionContext.getValues().containsKey(key)) {
            return executionContext.getValue(key);
        } else {
            reporter.info("Requested " + key + " execution context key is absent.\n\t\tPossible reasons are:\n" +
                    "\t\t- some previous CaptureData action(s) failed;\n" +
                    "\t\t- the requested key is misspelled in the excel spreadsheet located in the " + PreProcessFiles.TEST_INPUT_FILES_FOLDER_PATH + "/ folder path.");
            reporter.info("Requested " + key + " execution context key is absent.<pre>Possible reasons are:<br>" +
                    "- some previous CaptureData action(s) failed;<br>" +
                    "- the requested key is misspelled in the excel spreadsheet located in the " + PreProcessFiles.TEST_INPUT_FILES_FOLDER_PATH + "/ folder path.</pre>");
            return key;
        }
    }

    public static void setExecutionContextValueByKey(String key, String value){
        handler.get().setValue(key, value);
    }

    public static void resetExecutionContextValues(){
        handler.get().reset();
    }

    public static Map<String, String> getAllValues(){
        return handler.get().getValues();
    }
}
