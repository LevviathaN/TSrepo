package api;

import org.testng.ITestResult;
import org.testng.annotations.*;
import ui.utils.Reporter;
import ui.utils.bpp.KeywordsHandler;
import ui.utils.bpp.MetaDataHandler;
import ui.utils.bpp.PreProcessFiles;

import java.lang.reflect.Method;

/**
 * <p>Base test class for all api tests.</p>
 *
 * @author yzosin
 */
public class BaseApiTest {

    Reporter reporter = new Reporter();
    @BeforeMethod
    public void preProcess(Method method, Object[] data) {

        System.out.println("START - BPP AUTOMATION API MODULE");

        new PreProcessFiles().initPaths(false);
        KeywordsHandler.instantiate();
        MetaDataHandler.instantiate();

        //init reporter
        Reporter.instantiate();
        Reporter.startReportingAPI(method,data);
        reporter.setLogName(method.getAnnotation(Test.class).testName());
        Reporter.logForEveryTest(method.getAnnotation(Test.class).testName());

    }

    @AfterMethod
    public void flushProcesses(ITestResult testResult) {
        Reporter.stopReportingAPI(testResult);
    }

    @AfterClass(alwaysRun = true)
    public void flushReporter() {
        ExcelResultsWriter.createApiExcel();
        Reporter.flush();
        System.out.println("EXECUTIONS HAVE FINISHED");
    }
}
