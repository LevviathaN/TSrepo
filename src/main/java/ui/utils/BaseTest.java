package ui.utils;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import ui.pages.BasePage;
import ui.utils.bpp.KeywordsHandler;
import ui.utils.bpp.MetaDataHandler;
import ui.utils.bpp.PreProcessFiles;

import java.lang.reflect.Method;
import java.net.MalformedURLException;


public class BaseTest {

    ReporterManager reporter;
    PreProcessFiles preProcessFiles;
    protected String sessionId;

    @BeforeMethod
    public void beforeWithData(Object[] data, Method method) {

        //init reporter
        reporter = ReporterManager.Instance;
        reporter.startReporting(method, data);
        preProcessFiles = new PreProcessFiles();

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

                if (testResult.toString().contains("FAILURE")) {
                    reporter.updateBrowserStackJob("fail", sessionId);
                }else {
                    reporter.updateBrowserStackJob("pass", sessionId);
                }
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
