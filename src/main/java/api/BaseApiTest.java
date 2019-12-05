package api;

import org.testng.ITestResult;
import org.testng.annotations.*;
import ui.utils.ReporterManager;
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

    private ReporterManager reporter;
    private PreProcessFiles preProcessFiles;
    private String sessionId;

    @BeforeMethod
    public void preProcess(Method method, Object[] data) {

        System.out.println("START - BPP AUTOMATION API MODULE");

        new PreProcessFiles().initPaths(false);
        KeywordsHandler.instantiate();
        MetaDataHandler.instantiate();

        //init reporter
        reporter = ReporterManager.Instance;
        reporter.startReportingAPI(method, data);

    }

    @AfterMethod
    public void flushProcesses(ITestResult testResult) {

        reporter.stopReportingAPI(testResult);
    }

    @AfterSuite(alwaysRun = true)
    public void flushReporter() {
        //ExcelResultsWriter.createApiExcel();
        reporter.closeReporter();
        System.out.println("EXECUTIONS HAVE FINISHED");
    }
}
