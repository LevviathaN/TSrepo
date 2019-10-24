//package utils.bpp_old;
//
//import utils.bpp_old.RunDoables;
//import utils.bpp_old.*;
//import org.openqa.selenium.*;
//import org.openqa.selenium.remote.RemoteWebDriver;
//
//import javax.ws.rs.InternalServerErrorException;
//import java.io.IOException;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.ArrayList;
//import java.util.List;
//
//import static utils.bpp_old.NoahLogManager.getLogger;
//
///**
// * <p>
// * This abstract class contains all of the properties for each doable.
// * </p>
// *
// * @author tchin
// */
//public abstract class AbstractDoable implements Doable {
//
//    //protected static final Logger logger = Logger.getLogger("NoahLogger");
//
//    private String lastBusinessProcess = "";
//    private String lastSkipBusinessProcess = "";
//    private int repeatCount = 0;
//    protected String currentTestName;
//
//
//    // from the test xlsx;
//    protected String run;
//    protected String test;
//    protected String testOnFail;
//    protected String businessProcess;
//    protected String value;
//    protected String iteration;
//
//    // logical Name from Businessinstall Process xlsx = element Location
//    protected String elementLocation;
//    protected String stepId;
//    protected String doableOnFail;
//    protected String uiAction;
//    protected String parameters;
//    protected String elementLocatorByPropertyName;
//    protected String testTagValue;
//    protected String sheetName;
//    protected String logName;
//    protected String qtestID;
//    protected String errorMsg;
//
//    // Reserved to determine when a test starts and ends
//    protected String testStatus;
//    protected String sessionId;
//
//    private AbstractDoable abstractDoable;
//
//    //STATIC is needed for working with this field in the #isNextDoableInstanceOfBanner() method.
//    // TODO: Determine how to avoid the usage of STATIC
//    private int doableIndex = 0;
//
//    // determines Pass Fail status of each test
//    protected boolean executionStatus = true;
//
////  Setters and Getters
//
//    // following getters and setters are part of the java beans contract
//
//    public String getIteration() {
//        return iteration;
//    }
//
//    public void setIteration(String iteration) {
//        this.iteration = iteration;
//    }
//
//    public String getTestStatus() {
//        return testStatus;
//    }
//
//    public void setTestTagValue(String testTagValue) {
//        this.testTagValue = testTagValue;
//    }
//
//    public String getTestTagValue() { return testTagValue; }
//
//    public void setTestStatus(String testStatus) {
//        this.testStatus = testStatus;
//    }
//
//    public String getParameters() {
//        return parameters;
//    }
//
//    public void setParameters(String parameters) {
//        this.parameters = parameters;
//    }
//
//    public String getUiAction() {
//        return uiAction;
//    }
//
//    public void setUiAction(String uiAction) {
//        this.uiAction = uiAction;
//    }
//
//    public String getTestOnFail() {
//        return testOnFail;
//    }
//
//    public void setTestOnFail(String testOnFail) {
//        this.testOnFail = testOnFail;
//    }
//
//    public String getStepId() {
//        return stepId;
//    }
//
//    public void setStepId(String stepId) {
//        this.stepId = stepId;
//    }
//
//    public String getDoableOnFail() {
//        return doableOnFail;
//    }
//
//    public void setDoableOnFail(String doableOnFail) {
//        this.doableOnFail = doableOnFail;
//    }
//
//    public String getRun() {
//        return run;
//    }
//
//    public void setRun(String run) {
//        this.run = run;
//    }
//
//    public String getTest() {
//        return test;
//    }
//
//    public void setTest(String test) {
//        this.test = test;
//    }
//
//    public String getOnFail() {
//        return doableOnFail;
//    }
//
//    public void setOnFail(String onFail) {
//        this.doableOnFail = onFail;
//    }
//
//    public String getBusinessProcess() {
//        return businessProcess;
//    }
//
//    public void setBusinessProcess(String businessProcess) {
//        this.businessProcess = businessProcess;
//    }
//
//    public String getElementLocation() {
//        return elementLocation;
//    }
//
//    public void setElementLocation(String elementLocation) {
//        this.elementLocation = elementLocation;
//    }
//
//    public String getValue() {
//        return value;
//    }
//
//    public void setValue(String value) {
//        this.value = value;
//    }
//
//    public void setSheetName(String sheetName) {
//        this.sheetName = sheetName;
//    }
//
//    public String getSheetName() {
//        return sheetName;
//    }
//
//    //get the name of the log file for further usage in the report
//    private void setLogName(String testName) {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM.dd.yyyy_HH.mm");
//        LocalDateTime dateTime = LocalDateTime.now();
//        String formattedDateTime = dateTime.format(formatter);
//
//        this.logName = testName.replace(" ", "_") + "_" + formattedDateTime + ".log";
//    }
//
//    /**
//     * this list of Doables that make up this Doable.
//     * <p>
//     * STATIC is needed for working with this list in the #isNextDoableInstanceOfBanner() method.
//     * TODO: Determine how to avoid the usage of STATIC
//     */
//    protected List<Doable> doables = new ArrayList<Doable>();
//
//    /**
//     * Accessors
//     *
//     * @return doables UI actions
//     **/
//    public List<Doable> getDoables() {
//        return doables;
//    }
//
//    public void setDoables(List<Doable> doables) {
//        this.doables = doables;
//    }
//
//    /** API methods **/
//
//    /**
//     * <p> Default implementation of doIt() is to recursively call doIt() on all
//     * doables that compose this Doable. </p>
//     * <p>
//     * <p> -Exceptions are cataloged into the log, report, and extentreports </p>
//     */
//    public void doIt() {
//
//
//        // loop through all the doables
//        for (; doableIndex < doables.size(); doableIndex++) {
//
//            // create a local instance of abstract doable
//            abstractDoable = (AbstractDoable) doables.get(doableIndex);
//
//            // create new test for the report when testStatus = Start
//            if (abstractDoable.getTestStatus().equalsIgnoreCase("Start")) {
//
//                // Initialize the Driver object
//                //System.setProperty("test_name", abstractDoable.getTest());
//
//                // create the test for report and set the name with getTest()
//                this.currentTestName = this.currentTestName.equalsIgnoreCase("SERIAL") ? abstractDoable.getTest() : this.currentTestName;
//                setLogName(abstractDoable.getTest());
//                Reporter.addTest(abstractDoable.getTest(), abstractDoable.getTestTagValue(), abstractDoable.getSheetName(), this.logName);
//                GlobalDataBridge.getInstance().setBufferValueByKey("testName", abstractDoable.getTest());
//                Reporter.logForEveryTest(this.logName);
//                Reporter.testLog("Iteration = " + abstractDoable.getIteration());
//                getLogger().info("Executing Test: " + abstractDoable.getTest());
//
//                this.sessionId = (((RemoteWebDriver) SeleniumHelper.getInstance().getInitialDriver()).getSessionId()).toString();
//                RunDoables.setExecutionEnvironmentInfo();
//            }
//
//            try {
//                // -------------Execute the step--------------------//
//                String businessProcessName = abstractDoable.getBusinessProcess();
//                getLogger().info("Current Business Process Running :- " + businessProcessName);
//
//                //Create a node in report when new business process start
//                if (!(lastBusinessProcess.equals(businessProcessName))) {
//                    Reporter.node("Executing Business Process: " + businessProcessName, businessProcessName + " execution has started");
//                    lastBusinessProcess = businessProcessName;
//                    lastSkipBusinessProcess = lastBusinessProcess;
//                }
//                Reporter.log("<pre>Performing the " + abstractDoable.getUiAction() + " action</pre>");
//
//                doables.get(doableIndex).setParent(this);
//                doables.get(doableIndex).doIt();
//                repeatCount = 0;
//
//                // -------------End Execute the step--------------------//
//
//            } catch (AssertionError e) { // catch the validation exception and
//                // report the failure
//
//                if (doables.get(doableIndex).getClass().getName().contains("ValidateGet")) {
//                    reportApiFailAndLog("Expected value: " + "\"" + abstractDoable.getValue().split("\\|")[1] + "\"" + " was not found in the response body.");
//                    skipLogic();
//                } else {
//                    reportFailAndLog("FAILURE: Validation does not match application value = " + abstractDoable.getValue()
//                            + "\n" + e.getLocalizedMessage());
//                }
//
//                e.printStackTrace();
//                errorMsg = e.getLocalizedMessage();
//                executionStatus = false;
//
//                // catches the all common selenium exceptions and mark the
//                // failure on report; must list ALL exceptions here
//
//                //--------------Exception Reference-------------------//
//                // Reference https://seleniumhq.github.io/selenium/docs/api/py/_modules/selenium/common/exceptions.html
//                // Reference http://toolsqa.com/selenium-webdriver/exception-handling-selenium-webdriver/
//                //----------------------------------------------------//
//            } catch (StaleElementReferenceException e) {
//                errorMsg =  "<pre><textarea>" + e.getLocalizedMessage() + "</textarea></pre><br />";
//                try {
//                    reportAndLog("Stale element reference exception occurred." +
//                            " Trying to work with the element again.", "warn", true);
//                    doables.get(doableIndex).doIt();
//                } catch (Exception e1) {
//                    errorMsg =  "<pre><textarea>" + e.getLocalizedMessage() + "</textarea></pre><br />";
//                    handleWebElementException(e1);
//                }
//            } catch (ElementNotVisibleException | TimeoutException | NoSuchElementException | ElementNotSelectableException e) {
//                errorMsg =  "<pre><textarea>" + e.getLocalizedMessage() + "</textarea></pre><br />";
//                handleWebElementException(e);
//
//                // catch NoAlertPresent and UnhandledAlert Exceptions
//            } catch (NoAlertPresentException | UnhandledAlertException ex) {
//                errorMsg =  "<pre><textarea>" + SeleniumHelper.getInstance().getDriver().switchTo().alert().getText() + "</textarea></pre><br />";
//                handleUnexpectedError(
//                        "Unexpected alert occurred with the following message:" + errorMsg + "Attempting to close the alert.");
//                ex.printStackTrace();
//                executionStatus = false;
//                // skip all the atomic steps till the end of current test
//                skipLogic();
//
//            } catch (NotFoundException | InternalServerErrorException ex) {
//
//                String responseCode = ex.getMessage().substring(ex.getMessage().length() - 3, ex.getMessage().length() );
//
//                reportApiFailAndLog("An error has occurred while getting the response from the requested resource. The response code is: " + responseCode);
//                ex.printStackTrace();
//                executionStatus = false;
//                // skip all the atomic steps till the end of current test
//                skipLogic();
//
//            } catch (ScriptTimeoutException e) {
//
//                if (e.getLocalizedMessage().contains("script timeout") && (repeatCount < 2)) {
//                    try {
//                        SeleniumHelper.getInstance().waitForAjaxToComplete();
//                    } catch (TimeoutException ex) {
//                        ex.printStackTrace();
//
//                        doableIndex--;
//                        repeatCount++;
//                    }
//                } else {
//                    errorMsg = "<pre><textarea>" + e.getLocalizedMessage() + "</textarea></pre><br />";
//                    handleUnexpectedError(
//                            "Unexpected error occurred: " + errorMsg);
//                }
//            } catch (WebDriverException except) {
//
//                if(except.getLocalizedMessage().contains("not clickable at point") && (repeatCount < 2)) {
//
//                    try {
//                        throw new ElementNotClickableException(except);
//                    } catch (ElementNotClickableException e) {
//
//                        try {
//                            Thread.sleep(3000);
//                            getLogger().info("Element " + abstractDoable.elementLocation + " is not currently clickable. Setting timeout and will retry the following action in 3 seconds.");
//                        } catch (InterruptedException e1) {
//                            e1.printStackTrace();
//                        }
//
//                        doableIndex--;
//                        repeatCount++;
//                    }
//
//                } else {
//                    except.printStackTrace();
//                    executionStatus = false;
//                    // skip all the atomic steps till the end of current test
//                    skipLogic();
//                }
//
//            } catch (Exception e) {
//                e.printStackTrace();
//                executionStatus = false;
//                // skip all the atomic steps till the end of current test
//                skipLogic();
//            }
//
//            // Handle the activities at the end of every tests
//            if (abstractDoable.getTestStatus().equalsIgnoreCase("End")) {
//                // set the test info to the report
//
//                /**@First_If_Block - used inside VM option. Example - (-Dqtest=true/false) is used to check if we need to update status of the Test Run in qTest.
//                 * @Second_If_Block - checkes if current Test name includes in qTest. If yes, it generate TestRunID from getTestRunIDfromSuite() method
//                 * @Third_If_Block - send Passed or Failed status for the Test and update status of Test Run in qTest if previous If Block was true**/
//                if (System.getProperties().containsKey("qtest") && System.getProperty("qtest").equalsIgnoreCase("TRUE")) {
//                    if (qTestAPI.getTestRunIDfromSuite().containsKey(abstractDoable.getTest())) {
//                        qtestID = qTestAPI.getTestRunIDfromSuite().get(abstractDoable.getTest().toString());
//                        if (executionStatus) {
//                            getLogger().info("Test " + abstractDoable.getTest() + " PASSED");
//                            qTestAPI.TestRunStatusUpdate(abstractDoable.getTest(), "Passed", 601, qtestID, "");
//                        } else {
//                            getLogger().info("Test " + abstractDoable.getTest() + " FAILED");
//                            qTestAPI.TestRunStatusUpdate(abstractDoable.getTest(), "Failed", 602, qtestID, errorMsg.replace("\n", "\\n").replace("\"","'").replace("<pre><textarea>"," ").replace("</textarea></pre><br />",".").replaceAll("[!@#$%^&*():;?=/<>{}\\[\\]]", " "));
//
//
//                        }
//                    }
//                }
//
//                SeleniumHelper.getInstance().closeBrowser();
//                SeleniumHelper.closeInstance();
//
//                if ((System.getProperties().containsKey("driverType") && System.getProperty("driverType").contains("BSTACK")) ||
//                        (PropertiesHelper.determineEffectivePropertyValue("driver_id") != null && PropertiesHelper.determineEffectivePropertyValue("driver_id").contains("BSTACK"))) {
//
//                    // create a node in report for the screen cast as BrowserStack Information
//                    Reporter.addLinkToReport(Reporter.getScreencastLinkFromBrowserStack(sessionId));
//
//                    if (executionStatus) {
//                        Reporter.updateBrowserStackJob("pass", this.sessionId);
//                        getLogger().info("Writing Pass test status to BrowserStackJob");
//                    } else {
//                        Reporter.updateBrowserStackJob("fail", this.sessionId);
//                        getLogger().info("Writing Fail test status to BrowserStackJob");
//                    }
//                }
//                Reporter.flush();
//
//                // write Execution Context variables into file
//                try {
//                    Reporter.writeToFile();
//                    Reporter.saveAllECToFile();
//                } catch (IOException e) {
//                    getLogger().error("Failed to write Execution Context variables into file!");
//                }
//                // Reset the Execution Context
//                ExecutionContextHandler.resetExecutionContextValues();
//                // Reset exectuionStatus
//                executionStatus = true;
//            }
//        }
//
//        postDo();
//    }
//
//    /**
//     * Skips all the further atomic actions till the end of the test case and
//     * set the doableIndex to the next test
//     */
//    private void skipLogic() {
//
//        if (abstractDoable.getTestOnFail().equalsIgnoreCase("Exit")) {
//            for (; doableIndex < doables.size(); doableIndex++) {
//                abstractDoable = (AbstractDoable) doables.get(doableIndex);
//
//                String businessProcessName = abstractDoable.getBusinessProcess();
//                getLogger().info("lastBusinessProcess value :- " + lastBusinessProcess + " lastSkipBusinessProcess value :- " + lastSkipBusinessProcess);
//
//                //If business process execute and failed then it create a skip node for remaining business process
//                if (!lastSkipBusinessProcess.equalsIgnoreCase(businessProcessName)) {
//                    Reporter.skip("Skipping Business Process : " + businessProcessName);
//                    lastSkipBusinessProcess = businessProcessName;
//                }
//
//                if (((AbstractDoable) doables.get(doableIndex)).getTestStatus().equalsIgnoreCase("End")) {
//                    break;
//                }
//            }
//        } else if (abstractDoable.getTestOnFail().equalsIgnoreCase("Continue")) {
//            for (; doableIndex < doables.size(); doableIndex++) {
//                abstractDoable = (AbstractDoable) doables.get(doableIndex);
//
//                String businessProcessName = abstractDoable.getBusinessProcess();
//
//				/*
//                    If Business process written Continue on fail then it execute next business process and if next business process
//					fail and created skip node for the remaining business process
//				 */
//
//                if (!lastSkipBusinessProcess.equalsIgnoreCase(businessProcessName)) {
//                    Reporter.skip("Skipping Business Process : " + businessProcessName);
//                    lastSkipBusinessProcess = businessProcessName;
//                }
//
//                if (((AbstractDoable) doables.get(doableIndex)).getTestStatus().equalsIgnoreCase("NextDoables in Process") ||
//                        ((AbstractDoable) doables.get(doableIndex)).getTestStatus().equalsIgnoreCase("End")) {
//                    break;
//                }
//            }
//        }
//    }
//
//    /**
//     * <p>
//     * Executes before every doable class with the exception of
//     * CaptureDataDoable class. Provides a wait for element to be displayed
//     * defined by a long timeout in SeleniumHelper. Then evaluates user input if
//     * it is a metadata, keyword, or execution context variable.
//     * </p>
//     */
//    public void preDo() {
//        if (!this.elementLocation.equals("")) {
//            setElementLocatorByPropertyName();
//            setConditionalEvent();
//            handleFrames();
//            if (!this.toString().contains("ElementNotVisibleDoable")) {
//                if (this.toString().contains("UploadFileDoable")) {
//                    SeleniumHelper.getInstance().elementToBePresent(this.elementLocatorByPropertyName);
//                } else {
//                    SeleniumHelper.getInstance().waitForElementToBeDisplayed(this.elementLocatorByPropertyName);
//                }
//            }
//        }
//        String inputTestParameter = this.value;
//        this.value = TestParametersController.checkIfSpecialParameter(this.value);
//        reportAndLog("[input test parameter] '" + inputTestParameter + "' -> '" + this.value + "' [output value]", "info", true);
//    }
//
//    /**
//     * <p>
//     * Executes before every REST API doable class.
//     * Then evaluates user input if it is a metadata, keyword, or execution context variable.
//     * </p>
//     */
//    public void preDoRestApi() {
//        if (!this.elementLocation.equals("")) {
//            setElementLocatorByPropertyName();
//        }
//
//        String inputTestParameter = this.value;
//        this.value = TestParametersController.checkIfSpecialParameter(this.value);
//        reportAndLog("[input test parameter] '" + inputTestParameter + "' -> '" + this.value + "' [output value]", "info", true);
//    }
//
//    /**
//     * <p>
//     * Executes before every banner doable class with the exception of
//     * Then evaluates user input if it is a metadata, keyword, or execution context variable.
//     * </p>
//     */
//
//    public void preDoTestParameterOnly() {
//        String inputTestParameter = this.value;
//        this.value = TestParametersController.checkIfSpecialParameter(this.value);
//        reportAndLog("[input test parameter] '" + inputTestParameter + "' -> '" + this.value + "' [output value]", "info", true);
//    }
//
//    /**
//     * Reserved method for use after ALL test have been executed.
//     */
//    public void postDo() {
//        Reporter.flush();
//    }
//
//    /**
//     * <p>
//     * This is specific for CaptureDataDoable.class to exclude parameter type
//     * warning message set this.elementLocatorByPropertyName in accordance with
//     * provided property, this is performed before
//     * this.elementLocation is used in Doable classes (actions)
//     * </p>
//     */
//    public void setElementLocatorByPropertyName() {
//        if (!this.elementLocation.equals("")) {
//            this.elementLocatorByPropertyName = PropertiesHandler.getPropertyByKey(this.elementLocation);
//
//            if (PageLocatorMatcher.isECVariableInXpath(this.elementLocatorByPropertyName)) {
//                this.elementLocatorByPropertyName = PageLocatorMatcher.updateXpath(this.elementLocatorByPropertyName);
//            }
//        } else {
//            this.elementLocatorByPropertyName = "";
//        }
//    }
//
//    /**
//     * <p>
//     *
//     * @param element    - value defined in 'Parent' column
//     *                   </p>
//     */
//    private String getParents(String element) {
//        reportAndLog("Gathering parent value for " + element, "info", true);
//        String parents = PropertiesHandler.getParentByKey(element);
//        if (!parents.isEmpty()) {
//            reportAndLog("The found parent value for " + element + " is '" + "' " + parents, "info", true);
//            return parents;
//        } else {
//            reportAndLog("The " + element + " does not have a parent value", "info", true);
//            return "";
//        }
//    }
//
//    /**
//     * <p>
//     *
//     * @param frameName - iFrame defined as string to be switched to
//     *                  </p>
//     */
//    private void switchToFrame(String frameName) {
//        SeleniumHelper helper = SeleniumHelper.getInstance();
//        String frame = PropertiesHandler.getPropertyByKey(frameName);
//        helper.elementToBePresent(frame);
//        WebElement frameElement = helper.getDriver().findElement(helper.getElementBy());
//        reportAndLog("Switching to frame " + frameName, "info", true);
//        try {
//            helper.getDriver().switchTo().frame(frameElement);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * The method is used to determine whether web-element is housed in any frame and to switch into if needed
//     */
//    private void handleFrames() {
//        String parents = getParents(this.elementLocation);
//        if (!parents.equals(SeleniumHelper.getInstance().getActiveFrame())) {
//            SeleniumHelper.getInstance().getDriver().switchTo().defaultContent();
//            String[] frames = {};
//            if (!parents.isEmpty()) {
//                frames = parents.split(">");
//            }
//
//            for (int i = 0; i < frames.length; i++) {
//                switchToFrame(frames[i]);
//            }
//            SeleniumHelper.getInstance().setActiveFrame(parents);
//        }
//    }
//
//    /**
//     * The method is used to set the conditional event that needs to be executed when interacting with a particular web-element
//     */
//    public void setConditionalEvent() {
//        //setting the default value
//        SeleniumHelper.getInstance().setConditionalEvent(null);
//
//        String conditionalEventParameter = PropertiesHandler.getConditionalEventByKey(this.elementLocation);
//
//        String[] allEvents = conditionalEventParameter.split("\\>");
//        ArrayList<ConditionalEvent.Event> eventList = new ArrayList<>();
//
//        if (conditionalEventParameter.isEmpty()) {
//            return;
//        }
//
//        for (String event : allEvents) {
//            eventList.add(ConditionalEventHandler.getConditionalEvent(event));
//        }
//
//        //ConditionalEvent.Event conditionalEvent = ConditionalEventHandler.getConditionalEvent(conditionalEventParameter);
//
//        if (eventList.isEmpty()) {
//            return;
//        }
//
//        String conditionalEventValue;
//        for (ConditionalEvent.Event conditionalEvent : eventList) {
//            conditionalEventValue = conditionalEvent.value;
//            if (!conditionalEventValue.isEmpty()) {
//                String locatorByPropertyKey = PropertiesHandler.getPropertyByKey(conditionalEventValue);
//                if (locatorByPropertyKey.equals(conditionalEventValue)) {
//                    reportAndLog("Conditional event parameter '" + conditionalEventParameter + "' cannot be processed properly." +
//                            " Please, check the value placed after the '|' sign.", "warn", true);
//                    return;
//                }
//                conditionalEvent.value = locatorByPropertyKey;
//            }
//        }
//        SeleniumHelper.getInstance().setConditionalEvent(eventList);
//    }
//
//    /**
//     * <p>
//     * Writes to log with specified logLevel. Writes to report if writeToReport
//     * as information type. is true.
//     * </p>
//     *
//     * @param log           message that will be shown in log and/or report
//     * @param logLevel      indicates the log level: info, warn or error
//     * @param writeToReport indicates if it's needed the message to be shown in report
//     * @author tchin
//     */
//    protected void reportAndLog(String log, String logLevel, boolean writeToReport) {
//        switch (logLevel) {
//            case "info":
//                getLogger().info(log);
//                if (writeToReport) {
//                    Reporter.log(log);
//                }
//                break;
//            case "warn":
//                getLogger().warn(log);
//                if (writeToReport) {
//                    Reporter.warn(log);
//                }
//                break;
//            case "error":
//                getLogger().error(log);
//                break;
//            case "pass":
//                getLogger().info(log);
//                if (writeToReport) {
//                    Reporter.passMessage(log);
//                }
//                break;
//            default:
//                getLogger().info("Check spelling in method for logLevel");
//                break;
//        }
//    }
//
//    /**
//     * Record in report with failure as type and log with error level.
//     *
//     * @param log information to be logged in report and logger
//     * @author tchin
//     */
//    public void reportFailAndLog(String log) {
//        Reporter.fail(log);
//        getLogger().error(log);
//    }
//
//    public void reportApiFailAndLog(String log) {
//        Reporter.failTryTakingScreenshot(log);
//        getLogger().error(log);
//    }
//
//    /**
//     * used in catch blocks where any web-element related exceptions are caught
//     *
//     * @param e the exception object
//     */
//    protected void handleWebElementException(Exception e) {
//        reportFailAndLog("FAILURE:" + "\n" +
//                "Test: " + abstractDoable.getTest() + "\n" +
//                "Business Process: " + abstractDoable.getBusinessProcess() + "\n" +
//                "Action: " + abstractDoable.getUiAction() + "\n" +
//                "Element Location / Logical Name: " + abstractDoable.getElementLocation() + "\n" +
//                "Business Process Step: " + abstractDoable.getStepId() + "\n" +
//                e.getLocalizedMessage());
//
//        e.printStackTrace();
//        executionStatus = false;
//        // skip all the atomic steps till the end of current test
//        skipLogic();
//    }
//
//    // ========= PRODUCT CATALOG ========
//
//    public void openbravoPreDo() {
//        if (!this.elementLocation.equals("")) {
//            setElementLocatorByPropertyName();
//            setConditionalEvent();
//            handleFrames();
//        }
//        String inputTestParameter = this.value;
//        this.value = TestParametersController.checkIfSpecialParameter(this.value);
//        reportAndLog("[input test parameter] '" + inputTestParameter + "' -> '" + this.value + "' [output value]", "info", true);
//
//    }
//
//    private void handleUnexpectedError(String msg) {
//
//        Reporter.failTryTakingScreenshot(msg);
//    }
//}