package ui.utils;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import ui.pages.BasePage;
import ui.utils.bpp.KeywordsHandler;
import ui.utils.bpp.MetaDataHandler;
import ui.utils.bpp.PreProcessFiles;

import java.lang.reflect.Method;


public class BaseTest {

    ReporterManager reporter;
    PreProcessFiles preProcessFiles;
    public final boolean Parallel = false;
    boolean startTestExecution;

    @BeforeMethod
    public void beforeWithData(Object[] data, Method method) {

        //init reporter
        reporter = ReporterManager.Instance;
        reporter.startReporting(method, data);
        preProcessFiles = new PreProcessFiles();

        try {
            reporter.info("Driver creation");
            BasePage.driver.set(DriverProvider.getDriver());
        } catch (Exception e) {
            reporter.fail("Before test failure during Driver creation", e);
            reporter.stopReporting();
            reporter.closeReporter();
            Assert.fail();
        }

        try {
            startTestExecution = preProcessFiles.preProcessTestConfiguration();
        } catch (Exception e) {
            e.printStackTrace();
        }

        KeywordsHandler.instantiate();
        MetaDataHandler.instantiate();

    }

    @AfterMethod
    public void endTest(ITestResult testResult) {

        // close reporter
        reporter.stopReporting(testResult);

        //close driver
        BasePage.driver().quit();
        DriverProvider.closeDriver();

    }

    @AfterSuite(alwaysRun = true)
    public void flushReporter() {
        reporter.closeReporter();
    }
}
