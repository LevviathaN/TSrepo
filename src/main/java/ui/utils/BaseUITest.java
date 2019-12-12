package ui.utils;

import api.RestApiController;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
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
public class BaseUITest {

    private PreProcessFiles preProcessFiles;
    private String sessionId;
    Reporter reporter = new Reporter();

    @BeforeMethod
    public void beforeWithData(Object[] data, Method method) {
        RestApiController apiController = new RestApiController();

        //init reporter
        Reporter.instantiate();
        Reporter.startReporting(method, data);
        reporter.setLogName(method.getAnnotation(Test.class).testName());
        reporter.logForEveryTest(reporter.testLogName);

        preProcessFiles = new PreProcessFiles();
        BasePage.specialLocatorsMap = apiController.processLocatorProperties("//src/main/resources/data/bpp/test.properties/SpecialLocators.json");
        BasePage.locatorsMap = apiController.processLocatorProperties("//src/main/resources/data/bpp/test.properties/Locators.json");

        try {
            BPPLogManager.getLogger().info("Driver creation");
            BasePage.driver.set(DriverProvider.getDriver());
        } catch (Exception e) {
            Reporter.fail("Before test failure during Driver creation");
            Reporter.flush();
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
        Reporter.stopReporting(testResult);

        try {
            if (DriverProvider.getCurrentBrowserName().toUpperCase().contains("BSTACK")) {
                sessionId = ((RemoteWebDriver) DriverProvider.getDriver()).getSessionId().toString();
                Reporter.addLinkToReport(Reporter.getScreencastLinkFromBrowserStack(sessionId));

                Reporter.updateBrowserStackJob(testResult.toString().contains("FAILURE") ? "fail" : "pass", sessionId);
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
        Reporter.flush();
    }
}
