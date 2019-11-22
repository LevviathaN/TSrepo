package api;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ui.utils.ReporterManager;
import ui.utils.bpp.KeywordsHandler;
import ui.utils.bpp.PreProcessFiles;

import java.lang.reflect.Method;

public class BaseApiTest {

    public ReporterManager reporter;

    @BeforeClass
    public void initializeInstances() {
        System.out.println("START - NOAH AUTOMATION API MODULE");

        new PreProcessFiles().initPaths();

        //reporter.instantiate();
        KeywordsHandler.instantiate();
    }

    @BeforeMethod
    public void preProcess(Method method) {

        String testName = method.getAnnotation(Test.class).testName();
        //Reporter.addApiTest(testName);
        //Reporter.logForEveryTest(testName);
    }

    @AfterMethod
    public void flushProcesses(ITestResult testResult) {

        //Reporter.stopReportingAPI(testResult);
    }

    @AfterClass
    public void stop() {
        ExcelResultsWriter.createApiExcel();
        System.out.println("EXECUTIONS HAVE FINISHED");
        //Reporter.flush();
    }
}
