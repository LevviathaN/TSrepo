package ui.utils;

import api.RestApiController;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import ui.pages.BasePage;
import ui.utils.bpp.KeywordsHandler;
import ui.utils.bpp.MetaDataHandler;
import ui.utils.bpp.PreProcessFiles;

import java.lang.reflect.Method;
import java.net.MalformedURLException;

/**
 * Base test class for all ui tests.
 * @author yzosin
 */
public class BaseTest {

    private ReporterManager reporter;
    private PreProcessFiles preProcessFiles;
    private String sessionId;

    @BeforeMethod
    public void beforeWithData(Object[] data, Method method) {
        RestApiController apiController = new RestApiController();

        //init reporter
        reporter = ReporterManager.Instance;
        reporter.startReporting(method, data);
        preProcessFiles = new PreProcessFiles();
        BasePage.specialLocatorsMap = apiController.processLocatorProperties("//src/main/resources/data/bpp/test.properties/SpecialLocators.json");

        try {
            BPPLogManager.getLogger().info("Driver creation");
            BasePage.driver.set(DriverProvider.getDriver());
        } catch (Exception e) {
            reporter.fail("Before test failure during Driver creation", e);
            reporter.stopReporting();
            reporter.closeReporter();
            Assert.fail();
        }

        try {
            boolean startTestExecution = preProcessFiles.preProcessTestConfiguration();
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

        try {
            if (DriverProvider.getCurrentBrowserName().toUpperCase().contains("BSTACK")) {
                sessionId = ((RemoteWebDriver) DriverProvider.getDriver()).getSessionId().toString();
                reporter.addLinkToReport(reporter.getScreencastLinkFromBrowserStack(sessionId));

                reporter.updateBrowserStackJob(testResult.toString().contains("FAILURE") ? "fail" : "pass", sessionId);
                BasePage.driver().quit();
                DriverProvider.closeDriver();
            } else {
                BasePage.driver().quit();
                DriverProvider.closeDriver();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @AfterSuite(alwaysRun = true)
    public void flushReporter() {
        reporter.closeReporter();
    }
}
