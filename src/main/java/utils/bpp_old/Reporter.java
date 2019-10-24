package utils.bpp_old;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.FileAppender;
import org.apache.log4j.PatternLayout;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author hemantojha
 * <p>
 * Provides the reporting interface
 * </p>
 */
public class Reporter {

    //protected final Logger logger = Logger.getLogger(Reporter.class);

    private static ExtentReports extent;
    private static String root = System.getProperty("user.dir");
    private static String filePath = "extentreport.html";
    private static Path reportPath;
    private static Path screenshotFolder;
    private static Path ecFolder;
    private static Path logFolder;
    private static StringBuilder htmlStringBuilder = new StringBuilder();
    private static boolean buildStatus = true;
    private static ArrayList failuresBucket = new ArrayList<String>();

    private static ConcurrentHashMap<Long, ExtentTest> testStorage = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<Long, ExtentTest> testNodesStorage = new ConcurrentHashMap<>();

    /**
     * <p>
     * Instantiate the Extend report with report configurations
     * </p>
     */
    public static void instantiate() {

        if (extent != null) {
            return;
        }

        // create the report at path root/report/report_<nano time>
        try {
            // generate report folder name
            Path rootPath = getNewReportPath();

            // create directory if not exists
            if (Files.notExists(rootPath))
                reportPath = Files.createDirectories(rootPath);
            else
                reportPath = rootPath;

        } catch (IOException e) {
            e.printStackTrace();
        }

        // lazy initialization of the extent report object
        if (null == extent) {
            extent = new ExtentReports();

            ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(Paths.get(reportPath.toString(), filePath).toAbsolutePath().toFile());

            // report configuration
            htmlReporter.config().setChartVisibilityOnOpen(false);
            htmlReporter.config().setDocumentTitle("Noah Automation");
            htmlReporter.config().setReportName("Regression cycle");
            htmlReporter.config().setTheme(Theme.DARK);

            String js = ReadFileInText.readFile("ReportJQuery.js");
            if (!js.equals("")) {
                htmlReporter.config().setJS(js);
            } else {
                NoahLogManager.getLogger().warn("ReportJQuery.js file is empty");
            }

            //adding a custom style sheet to the report
            String css = ReadFileInText.readFile("ReportStyle.css");
            if (!css.equals("")) {
                htmlReporter.config().setCSS(css);
            } else {
                NoahLogManager.getLogger().warn("ReportStyle.css file is empty");
            }

            extent.attachReporter(htmlReporter);
        }
    }

    public static void setSystemInfo(Map<String, String> info) {

        for (Map.Entry<String, String> entry : info.entrySet()) {
            extent.setSystemInfo(entry.getKey(), entry.getValue());
        }
        extent.flush();
    }

    /**
     * <p>
     * Returns the path of the report folder created
     * </p>
     *
     * @return reportPath path of the report to be written to
     */
    public static Path getReportPath() {
        return reportPath;
    }

    /**
     * <p>
     * Generate the unique report name
     * </p>
     *
     * @return
     */
    private static Path getNewReportPath() {
        LocalDateTime dateTime = LocalDateTime.now();
        String reportName = "report" + "_" + dateTime.toLocalDate() + "_" + dateTime.toLocalTime().getHour() + "_"
                + dateTime.toLocalTime().getMinute() + "_" + dateTime.toLocalTime().getSecond();

        return Paths.get(root, "report", reportName);
    }

    /**
     * <p>
     * Add test to the report with the give test name
     * </p>
     *
     * @param testName name of the test executing
     */
    private static String browserLink;

    public static void addTest(String testName, String tagName, String sheetName, String logName) {

        String browserName;
        if (System.getProperties().containsKey("driverType")) {
            browserName = GlobalDataBridge.getInstance().getBufferValueByKey("current_browser").toString();
        } else {
            browserName = PropertiesHelper.determineEffectivePropertyValue("driver_id");
        }


        if (browserName.equalsIgnoreCase("chrome")|| browserName.equalsIgnoreCase("bstack_chrome")) {
            browserLink = "<img src='https://cdnjs.cloudflare.com/ajax/libs/browser-logos/45.10.0/archive/chrome_12-48/chrome_12-48_64x64.png' class='BrowserLogo'>";
        } else if (browserName.equalsIgnoreCase("firefox")|| browserName.equalsIgnoreCase("bstack_firefox")) {
            browserLink = "<img src='https://cdnjs.cloudflare.com/ajax/libs/browser-logos/45.10.0/archive/firefox_1/firefox_1_64x64.png' class='BrowserLogo'>";
        } else if (browserName.equalsIgnoreCase("ie")|| browserName.equalsIgnoreCase("bstack_ie")) {
            browserLink = "<img src='https://cdnjs.cloudflare.com/ajax/libs/browser-logos/45.10.0/archive/internet-explorer_9-11/internet-explorer_9-11_64x64.png' class='BrowserLogo'>";
        } else if (browserName.equalsIgnoreCase("safari")|| browserName.equalsIgnoreCase("bstack_safari")) {
            browserLink = "<img src='https://cdnjs.cloudflare.com/ajax/libs/browser-logos/45.10.0/archive/safari_1-7/safari_1-7_64x64.png' class='BrowserLogo'>";
        }

        ExtentTest test = extent.createTest(testName);
        testStorage.put(Thread.currentThread().getId(), test);


        for (String tagsListItem : tagName.split(";")) {

            //pass tag name assigned to the test to the final report
            if (!tagsListItem.equals("")) {
                test.assignCategory(tagsListItem + "<span>&nbsp;-&nbsp;Tag</span>");
            }
        }

        //pass the sheet name where the test is run from to the final report
        test.assignCategory(sheetName + "<span>&nbsp;-&nbsp;Sheet</span>");

        test.assignCategory(browserName.concat("<span>&nbsp;-&nbsp;Browser</span>"));

        //pass the log file name to the final report
        test.assignCategory("<a>" + logName + "</a>");

        //info message for grabbing logo.
        test.info(browserLink);
    }


    /**
     * Used in DriverType to launch a web driver via SauceLabs
     *
     * @return the test name that is run in current thread
     */
    public static String getCurrentTestName() {
        return testStorage.get(Thread.currentThread().getId()).getModel().getName();
    }

    /**
     * <p>
     * Set simple log to the test
     * </p>
     *
     * @param log identify the log for test execution
     */
    public static void log(String log) {
        testNodesStorage.get(Thread.currentThread().getId()).info(log);
    }

    /**
     * <p>
     * Set warning log to the test
     * </p>
     *
     * @param log identify the log for test execution
     */
    public static void warn(String log) {
        testNodesStorage.get(Thread.currentThread().getId()).warning(log);
    }

    /**
     * Set simple log to the test
     *
     * @param log message that will be shown in report
     */
    public static void testLog(String log) {
        testStorage.get(Thread.currentThread().getId()).info(log);
    }

    /**
     * <p>
     * Input the log as passing step of Business Process.
     * Requested at AGQA-931
     * </p>
     *
     * @param log identify the log for test execution
     */
    public static void passMessage(String log) {
        testNodesStorage.get(Thread.currentThread().getId()).pass(log);
    }

    /**
     * <p>
     * Input the log as passing step of the test and attach the screenshot
     * </p>
     *
     * @param log identify the log for test execution
     */
    public static void pass(String log) {
        try {
            testStorage.get(Thread.currentThread().getId()).pass(log, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        } catch (IOException e) {
            testStorage.get(Thread.currentThread().getId()).pass(log);
        }
    }

    /**
     * <p>
     * Input the log as failing step of the test and attach the screenshot
     * </p>
     *
     * @param log identify the log for test execution
     */
    public static void fail(String log) {
        try {
            //String screenshotPath = takeScreenshot().substring(takeScreenshot().indexOf("screenshots"));
            String screenshotPath = takeScreenshot();
            screenshotPath = screenshotPath.substring(screenshotPath.indexOf("screenshots"));
            testNodesStorage.get(Thread.currentThread().getId()).fail(log, MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
            buildStatus = false;
            failuresBucket.add(log);
        } catch (Exception e) {
            testStorage.get(Thread.currentThread().getId()).fail(log);
        }
    }

    public static void failTryTakingScreenshot(String log) {

        try {
            testNodesStorage.get(Thread.currentThread().getId()).fail(log);
            buildStatus = false;
            failuresBucket.add(log);
        } catch (Exception e) {
            testStorage.get(Thread.currentThread().getId()).fail(log);
        }
    }

    /**
     * <p>
     * Get the final build status, to fail the maven build.
     * </p>
     *
     * @return
     */
    public static boolean getBuildStatus() {
        return buildStatus;
    }

    /**
     * <p>
     * Get the list of failed messages
     * </p>
     *
     * @return
     */
    public static ArrayList<String> getFailureBucket() {
        return failuresBucket;
    }

    /**
     * <p>
     * skip log
     * </p>
     *
     * @param log identify the log for test execution
     */
    public static void skip(String log) {
        ExtentTest childNode = testStorage.get(Thread.currentThread().getId()).createNode(log).skip(log);
        testNodesStorage.put(Thread.currentThread().getId(), childNode);
    }

    /**
     * <p>
     * Attach a child node to the step, all the function are similar to the step
     * </p>
     *
     * @param businessProcess name of the business process for the node
     * @param log             identify the log for test execution
     */
    public static void node(String businessProcess, String log) {
        ExtentTest childNode = testStorage.get(Thread.currentThread().getId()).createNode(businessProcess).info(log);
        testNodesStorage.put(Thread.currentThread().getId(), childNode);
    }

    /**
     * <p>
     * Flush the test data in the test of the report Need to get called after
     * every test finishes
     * </p>
     */
    public synchronized static void flush() {
        extent.flush();
    }

    /**
     * <p>
     * Take the screenshot
     * </p>
     *
     * @return
     */
    private static String takeScreenshot() {

        String testCaseName = testStorage.get(Thread.currentThread().getId()).getModel().getName();

        try {
            // now copy the screenshot to desired location using copyFile method
            screenshotFolder = Paths.get(reportPath.toString(), "screenshots");

            if (Files.notExists(screenshotFolder))
                Files.createDirectory(screenshotFolder);

            String fileName = testCaseName.replace(" ", "_") + "_" + System.nanoTime();

            Path screenshotPath = Paths.get(screenshotFolder.toString(), fileName + ".png");

            Screenshot screenshot = null;

            //shooting strategy work for Chrome Only.
            String driver_id = new PropertiesHelper().getProperties().getProperty("driver_id");

           boolean hasDriverId = System.getProperties().contains("driver_id");
           boolean hasChrome = false;
            if (hasDriverId) {
                hasChrome = System.getProperty("driver_id").contains("CHROME");
            }

            //verify if Chrome is specified from maven or config.properties
//            if ((System.getProperties().containsKey("driverType") && System.getProperty("driverType").contains("CHROME")) ||
//                    (PropertiesHelper.determineEffectivePropertyValue("driver_id") != null && PropertiesHelper.determineEffectivePropertyValue("driver_id").contains("CHROME")))
                if (System.getProperties().containsKey("driverType") && GlobalDataBridge.getInstance().bufferContainsKey("current_browser") ||
                        (PropertiesHelper.determineEffectivePropertyValue("driver_id") != null && PropertiesHelper.determineEffectivePropertyValue("driver_id").contains("CHROME"))){
                NoahLogManager.getLogger().info("Taking Screen Shot For Chrome ");
                screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(100))
                        .takeScreenshot(SeleniumHelper.getInstance().getDriver());
            } else {
                screenshot = new AShot()
                        .takeScreenshot(SeleniumHelper.getInstance().getDriver());
            }

            ImageIO.write(screenshot.getImage(), "PNG", new File(screenshotPath.toString()));

            return screenshotPath.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Path getScreenshotFolder() {
        return screenshotFolder;
    }

    /**
     * <p>
     * Make relative path.
     * </p>
     *
     * @param file the file instance
     * @return the file path
     */
    public static String makeRelativePath(File file) {

        String absolute = file.getAbsolutePath();
        String outputDir = targetDir().toString();
        int beginIndex = absolute.indexOf(outputDir) + outputDir.length();
        String relative = absolute.substring(beginIndex);
        return ".." + relative.replace('\\', '/');
    }

    private static File targetDir() {

        String relPath = Reporter.class.getProtectionDomain().getCodeSource().getLocation().getFile();
        File targetDir = new File(relPath + "../../target");
        if (!targetDir.exists()) {
            targetDir.mkdir();
        }
        return targetDir;
    }

    /**
     * <p>
     * Build html report with Execution Context data
     * </p>
     *
     * @param fileContent reference information for EC html
     * @throws IOException Signals that an I/O exception of some sort has occurred. This
     *                     class is the general class of exceptions produced by failed
     *                     or interrupted I/O operations.
     */
    private static void createHtml(String fileContent) throws IOException {
        htmlStringBuilder.append("<html>");
        htmlStringBuilder.append("<head>");
        htmlStringBuilder.append("<style>" + "body {font-weight: italic; color: blue}" + "</style>");
        htmlStringBuilder.append("</head>");
        htmlStringBuilder.append("<h3></h3>");
        htmlStringBuilder.append(fileContent);
        htmlStringBuilder.append("<body>");
        htmlStringBuilder.append("</body></html>");
    }

    /**
     * <p>
     * Write html report with Execution Context data into separate file for each test
     * </p>
     *
     * @throws IOException Signals that an I/O exception of some sort has occurred. This
     *                     class is the general class of exceptions produced by failed
     *                     or interrupted I/O operations.
     */
    public synchronized static void writeToFile() throws IOException {

        if (ExecutionContextHandler.getAllValues().size() == 0) {
            return;
        }

        String testCaseName = testStorage.get(Thread.currentThread().getId()).getModel().getName();

        NoahLogManager.getLogger().info("Saving the EC values as HTML report for the test: " + testCaseName);

        try {
            for (Map.Entry entry : ExecutionContextHandler.getAllValues().entrySet()) {
                createHtml(entry.getKey() + ": " + entry.getValue());
            }
        } catch (IOException e) {
            fail("Unable to write EC variable into html report!");
            NoahLogManager.getLogger().error("Unable to write EC variable into html report!");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM.dd.yyyy_HH.mm.ss");
        LocalDateTime dateTime = LocalDateTime.now();
        String formattedDateTime = dateTime.format(formatter);

        ecFolder = Paths.get(reportPath.toString(), "ExecutionContext");
        try {
            ecFolder = Paths.get(reportPath.toString(), "ExecutionContext");
            if (Files.notExists(ecFolder))
                Files.createDirectory(ecFolder);
        } catch (IOException e) {
            NoahLogManager.getLogger().info("Unable to create folder to store EC variables");
        }

        String fileName = "EC_" + "Test_" + testCaseName.replace(" ", "_") + "_" + formattedDateTime + ".html";
        String tempFile = ecFolder + File.separator + fileName;
        File file = new File(tempFile);

        if (!htmlStringBuilder.toString().isEmpty()) {
            OutputStream outputStream = new FileOutputStream(file.getAbsoluteFile(), true);
            Writer writer = new OutputStreamWriter(outputStream);
            writer.write(htmlStringBuilder.toString());
            writer.close();
            htmlStringBuilder.setLength(0);
        } else {
            NoahLogManager.getLogger().info("During the " + testCaseName
                    + " test execution no EC variables were found to be saved into report.");
        }

    }

    /**
     * <p>
     * Write report with all Execution Context variables into one txt file
     * </p>
     *
     * @throws IOException Signals that an I/O exception of some sort has occurred. This
     *                     class 6is the general class of exceptions produced by failed
     *                     or interrupted I/O operations.
     */
    public synchronized static void saveAllECToFile() throws IOException {

        if (ExecutionContextHandler.getAllValues().size() == 0) {
            return;
        }

        String testCaseName = testStorage.get(Thread.currentThread().getId()).getModel().getName();
        NoahLogManager.getLogger().info("Saving all EC values in text file for the test: " + testCaseName);

        for (Map.Entry entry : ExecutionContextHandler.getAllValues().entrySet()) {
            String fileName = "Execution_Context_Variables" + ".txt";
            String tempFile = ecFolder + File.separator + fileName;
            File file = new File(tempFile);
            if (!file.exists()) {
                file.createNewFile();
                FileWriter writer = new FileWriter(file, true);
                BufferedWriter bufferedWriter = new BufferedWriter(writer);
                bufferedWriter.write(entry.getKey() + ": " + entry.getValue());
                bufferedWriter.close();
            } else {
                FileWriter writer = new FileWriter(file, true);
                BufferedWriter bufferedWriter = new BufferedWriter(writer);
                bufferedWriter.newLine();
                bufferedWriter.write(entry.getKey() + ": " + entry.getValue());
                bufferedWriter.close();
            }
        }
    }

    /**
     * <p>
     * Create log for every test in test suite
     * </p>
     */

    public synchronized static void logForEveryTest(String fileName) {

        logFolder = Paths.get(reportPath.toString(), "Logs");
        try {
            logFolder = Paths.get(reportPath.toString(), "Logs");
            if (Files.notExists(logFolder))
                Files.createDirectory(logFolder);
        } catch (IOException e) {
            NoahLogManager.getLogger().info("Unable to create logs folder");
        }

        FileAppender fileApp = new FileAppender();
        fileApp.setFile(logFolder + "/" + fileName);
        fileApp.setLayout(new PatternLayout("%d{yyyy-MM-dd HH:mm:ss} [%t] %-5p %C{1}:%L - %m%n"));
        fileApp.setAppend(true);
        fileApp.activateOptions();
        NoahLogManager.addFileAppender(fileApp);
    }

    public static String getScreencastLinkFromBrowserStack(String sessionId) {
        return String.format("https://api.browserstack.com/automate/builds/" + PropertiesHelper.determineEffectivePropertyValue("browserStackBuild") + "/sessions/" + sessionId);
    }

    public static void addLinkToReport(String ref) {
        String link = String.format("<a target='_blank' href='%s'>Screencast Link</a>", ref);
        node("BrowserStack Information", link);
    }


    public static void updateBrowserStackJob(String jobStatus, String sessionId) {
        try {
            String payload = new String();
            if (jobStatus.contains("pass")) {
                payload = String.format("{\"status\":\"passed\"}");
            } else {
                payload = String.format("{\"status\":\"failed\"}");
            }

            String userName = PropertiesHelper.determineEffectivePropertyValue("browserStackUsername");
            String password = PropertiesHelper.determineEffectivePropertyValue("browserStackPassword");

            URL url = new URL(String.format("https://api.browserstack.com/automate/builds/" + PropertiesHelper.determineEffectivePropertyValue("browserStackBuild") + "/sessions/" + sessionId + ".json"));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Authorization",
                    "Basic " + getBasicAuthenticationEncoding(userName, password));

            OutputStreamWriter osw = new OutputStreamWriter(connection.getOutputStream());
            osw.write(payload);
            osw.flush();
            osw.close();

            if (connection.getResponseCode() == 200) {
                Reporter.log("BrowserStack job has been updated successfully");
            } else {
                Reporter.log("BrowserStack job has NOT been updated");
            }

            System.err.println(connection.getResponseCode());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static String getBasicAuthenticationEncoding(String username, String password) {

        String userPassword = username + ":" + password;
        return new String(Base64.encodeBase64(userPassword.getBytes()));
    }
}