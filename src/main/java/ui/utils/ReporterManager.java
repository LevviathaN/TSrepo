package ui.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.relevantcodes.extentreports.*;
import org.apache.log4j.FileAppender;
import org.apache.log4j.PatternLayout;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.Test;
import org.testng.reporters.Files;

public class ReporterManager {

    private static ReporterManager instance;
    public static ReporterManager Instance = (instance != null) ? instance : new ReporterManager();
    public static String filename;
    static String Report_folder = "report";
    private static Path logFolder;

    ReporterManager() {

    }

    private static Map<Long, ExtentTest> testThread = new ConcurrentHashMap<Long, ExtentTest>();
    private static ExtentReports extent;

    public static Logger logger = LogManager.getLogger(ReporterManager.class);

    private synchronized static ExtentReports getInstance() {

        if (extent == null) {
            extent = new ExtentReports(Report_folder + File.separator +
                    "report" + "_" + Tools.getReportName(), true, NetworkMode.ONLINE);
        }
        extent.loadConfig(new File(FileIO.EXTENT_CONFIG));
        return extent;
    }

    public synchronized Map<Long, ExtentTest> startTest(Method m, String testName, String testDescription) {
        Long threadID = Thread.currentThread().getId();

        ExtentTest test = getInstance().startTest(testName, testDescription);
        //test.assignCategory(DriverProvider.getCurrentBrowserName());
        for (String gr : getTestGroups(m)) {
            test.assignCategory(gr);
        }
        testThread.put(threadID, test);
        return testThread;
    }

    public synchronized static ExtentTest report() {
        ExtentTest report = null;
        Long threadID = Thread.currentThread().getId();
        if (testThread.containsKey(threadID)) {
            report = testThread.get(threadID);
        }
        return report;
    }

    public synchronized static void closeTest() {
        getInstance().endTest(report());
    }

    public synchronized static void closeReporter() {
        getInstance().flush();
    }

    public String getTestName(Method m, Object[] data) {
        String testName = null;
        String address = null;

        String[] testGroups = m.getAnnotation(Test.class).groups();
        for (int i = 0; i < testGroups.length; i++) {
            if (testGroups[i].startsWith("http")) {
                address = testGroups[i];
            }
        }
        if (address != null) {
            testName = "<a href=" + "\"" + address + "\""
                    + "target=_blank alt=This test is linked to test case. Click to open it>"
                    + m.getAnnotation(Test.class).testName() + "</a>";
        } else {
            try {
                testName = m.getAnnotation(Test.class).testName(); // get name from annotation
                testName = testName + data[0].toString(); // add value from first DataProvider parameter ot test name
            } catch (Exception e) {
                //no TestName specified
            }
            ;
        }

        if (testName == null || testName.equals("")) {
            testName = m.getName();
        }
        return testName;
    }

    private String getTestDescription(Method m) {
        String testDescription = null;
        testDescription = m.getAnnotation(Test.class).description();
        if (testDescription == null || testDescription.equals("")) {
            testDescription = "";
        }
        return testDescription;
    }


    private String[] getTestGroups(Method m) {
        String[] testGroups = m.getAnnotation(Test.class).groups();
        if (testGroups == null || testGroups.equals("")) {
            testGroups[0] = "";
        }
        return testGroups;
    }


    public void startReporting(Method m, Object[] data) {
        startTest(m, getTestName(m, data), getTestDescription(m));
        String testGroups = "";
        for (String gr : getTestGroups(m)) {
            testGroups = testGroups + gr + "; ";
        }
        logger.info(
                "--------------------------------------------------------------------------------------------------------");
        logger.info("Started test '" + getTestName(m, data) + "' Groups: '" + testGroups.trim() + "'");
    }

    public void stopReporting() {
        closeTest();
    }

    public void stopReporting(ITestResult result) {
        closeTest();

        if (result.getStatus() == ITestResult.FAILURE)
            fail("Test failed", result.getThrowable());
        else if (result.getStatus() == ITestResult.SKIP)
            info("Test skiped");
        else
            pass("Test passed");
    }

    public void info(String details) {
        logger.info(details);
        report().log(LogStatus.INFO, details);
    }

    public void pass(String details) {
        logger.info(details);
        report().log(LogStatus.PASS, details);
    }

    public void fail(String details) {
        String screenshotFile;
        String message = "<pre>" + details + "</pre>";
        logger.error(details);
        try {
            screenshotFile = takeScreenshot(DriverProvider.getDriver());
            message = message + "<br><a href=\"" + screenshotFile + "\" target=_blank alt>"
                    + "SCREENSHOT" + "</a><br>";
        } catch (Exception e) {
            // processing of problem with taking screenshot
        }
        report().log(LogStatus.FAIL, message);
    }

    public void fail(String details, Throwable e) {
        String exceptionString = Tools.getStackTrace(e);
        fail(details + "\n\n" + exceptionString);
    }

    public void fatalFail(String message) {
        logger.error(message);
        report().log(LogStatus.FAIL, message);
    }

    public void skip(String message) {
        logger.info(message);
        report().log(LogStatus.SKIP, message);
        throw new SkipException(message);
    }

    public static String takeScreenshot(WebDriver driver) {
        return takeScreenshot(driver, "");
    }

    public static String takeScreenshot(WebDriver driver, String name) {

        String screenshotName;
        File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        screenshotName = testThread.get(Thread.currentThread().getId()).getTest().getName();
        filename = screenshotName.replace("\"", "_") + System.nanoTime() + ".png";
        try {
            Files.copyFile(new FileInputStream(file), new File(Report_folder + File.separator + filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filename;
    }

    public synchronized void logForEveryTest(String fileName) {

        logFolder = Paths.get(Report_folder, "Logs");
        try {
            logFolder = Paths.get(Report_folder, "Logs");
            if (java.nio.file.Files.notExists(logFolder))
                java.nio.file.Files.createDirectory(logFolder);
        } catch (IOException e) {
            BPPLogManager.getLogger().info("Unable to create logs folder");
        }

        FileAppender fileApp = new FileAppender();
        fileApp.setFile(logFolder + "/" + fileName);
        fileApp.setLayout(new PatternLayout("%d{yyyy-MM-dd HH:mm:ss} [%t] %-5p %C{1}:%L - %m%n"));
        fileApp.setAppend(true);
        fileApp.activateOptions();
        BPPLogManager.addFileAppender(fileApp);
    }
}