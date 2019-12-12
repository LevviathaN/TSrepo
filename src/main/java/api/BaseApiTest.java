package api;

import org.testng.ITestResult;
import org.testng.annotations.*;
import ui.utils.Reporter;
import ui.utils.bpp.KeywordsHandler;
import ui.utils.bpp.MetaDataHandler;
import ui.utils.bpp.PreProcessFiles;

import java.lang.reflect.Method;

/**
 * Base test class for all api tests.
 *
 * @author yzosin
 */
public class BaseApiTest {


    private PreProcessFiles preProcessFiles;
    private String sessionId;

    @BeforeMethod
    public void preProcess(Method method, Object[] data) {

        System.out.println("START - BPP AUTOMATION API MODULE");

        new PreProcessFiles().initPaths(false);
        KeywordsHandler.instantiate();
        MetaDataHandler.instantiate();

        //init reporter
        Reporter.instantiate();
        Reporter.addApiTest(method.getAnnotation(Test.class).testName());

    }

    @AfterMethod
    public void flushProcesses(ITestResult testResult) {

        Reporter.stopReportingAPI(testResult);
    }

    @AfterSuite(alwaysRun = true)
    public void flushReporter() {
        //ExcelResultsWriter.createApiExcel();
        Reporter.flush();
        System.out.println("EXECUTIONS HAVE FINISHED");
    }
}
