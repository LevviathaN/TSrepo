package ui.utils;

import api.RestApiController;
import org.jooq.tools.json.ParseException;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import ui.utils.bpp.*;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


/**
 * <p>Base test class for all ui tests.</p>
 * @author yzosin
 */
public class BaseUITest {

    private PreProcessFiles preProcessFiles;
    private String sessionId;
    Reporter reporter = new Reporter();
    private static final Map<String, String> executionEnvironment = new HashMap<>();
    private static final Object lock = new Object();

    @BeforeMethod
    public void beforeWithData(Object[] data, Method method) {
        RestApiController apiController = new RestApiController();

        //init reporter
        Reporter.instantiate();
        Reporter.startReporting(method, data);
        reporter.setLogName(method.getAnnotation(Test.class).testName());
        Reporter.logForEveryTest(method.getAnnotation(Test.class).testName());

        preProcessFiles = new PreProcessFiles();
        SeleniumHelper.specialLocatorsMap = apiController.processLocatorProperties("//src/main/resources/SpecialLocators.json");
        SeleniumHelper.locatorsMap = apiController.processLocatorProperties("//src/main/resources/Locators.json");

        try {
            BPPLogManager.getLogger().info("Driver creation");
            //temporary hardcoded solution to run PF Cleanup on Firefox
            if (method.getName().equals("runPFDatabaseCleanup")) {
                DriverProvider.instance.set(DriverProvider.getFirefoxBrowserStack());
                SeleniumHelper.driver.set(DriverProvider.instance.get());
            } else {
                SeleniumHelper.driver.set(DriverProvider.getDriver());
            }
            SeleniumHelper.driver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        } catch (Exception e) {
            Reporter.failTryTakingScreenshot("Before test failure during Driver creation. Please check options for test executions ");
            Reporter.flush();
            Assert.fail();
        }

        try {
           preProcessFiles.preProcessTestConfiguration();
        } catch (Exception e) {
            e.printStackTrace();
        }

        KeywordsHandler.instantiate();
        MetaDataHandler.instantiate();
        setExecutionEnvironmentInfo();
    }

    @AfterMethod
    public synchronized void endTest(ITestResult testResult) throws IOException, ParseException {

        // close reporter
        Reporter.stopReporting(testResult);
        try {
            Reporter.writeToFile();
            Reporter.saveAllECToFile();
            if (System.getProperties().containsKey("qtest") && System.getProperty("qtest").equalsIgnoreCase("TRUE")) {
                String scenarioName;
                String actualScenarioName = Reporter.getCurrentTestName();
                if (actualScenarioName.contains("attempt")) {
                    scenarioName = actualScenarioName.substring(0,actualScenarioName.length()-10);
                } else {
                    scenarioName = actualScenarioName;
                }
                if (qTestAPI.getTestRunIDfromSuite().containsKey(scenarioName)) {
                    String qtestID = qTestAPI.getTestRunIDfromSuite().get(scenarioName);
                    if (testResult.toString().contains("SUCCESS")){
                        BPPLogManager.getLogger().info("Test " + Reporter.getCurrentTestName() + " PASSED");
                        qTestAPI.TestRunStatusUpdate(Reporter.getCurrentTestName(), "Passed", 601, qtestID, "");
                    } else {
                        BPPLogManager.getLogger().info("Test " + Reporter.getCurrentTestName() + " FAILED");
                        qTestAPI.TestRunStatusUpdate(Reporter.getCurrentTestName(), "Failed", 602, qtestID, testResult.getThrowable().toString());
                    }
                    Reporter.addQtestLink(qtestID);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            if (DriverProvider.getCurrentBrowserName().toUpperCase().contains("BSTACK")) {
                sessionId = ((RemoteWebDriver) DriverProvider.getDriver()).getSessionId().toString();
                Reporter.addLinkToReport(Reporter.getScreencastLinkFromBrowserStack(sessionId));

                Reporter.updateBrowserStackJob(testResult.toString().contains("FAILURE") ? "fail" : "pass", sessionId);
                SeleniumHelper.driver().quit();
                DriverProvider.closeDriver();
            } else {
                SeleniumHelper.driver().quit();
                DriverProvider.closeDriver();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @AfterClass(alwaysRun = true)
    public void flushReporter() {
        Reporter.setSystemInfo(executionEnvironment);
        Reporter.flush();
        ExecutionContextHandler.resetExecutionContextValues();
        RetryAnalyzer.deletePreviousAttemptsFromHtmlReport();
    }

    public static void setExecutionEnvironmentInfo() {
        synchronized (lock) {
            if (!executionEnvironment.isEmpty()) {
                return;
            }
            executionEnvironment.put("Operating System", System.getProperty("os.name"));
        }
    }
}
