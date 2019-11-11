package ui.utils.bpp;

//import com.apolloglobal.testing.api.util.webdriver.WebDriverThread;
import com.google.common.base.Function;
import com.isomorphic.webdriver.ByScLocator;
import org.hamcrest.Matcher;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.*;
import ui.pages.BasePage;
import ui.utils.ReporterManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.hamcrest.Matchers.*;

/**
 * A classic singleton helper to perform the plethora of Selenium-specific
 * operations.
 *
 * @author tchin
 */
public class SeleniumHelper {

    //New reporter
    public static ReporterManager reporter = ReporterManager.Instance;

    private int intLongTimeOut = 40;

    public int getIntLongTimeOut() {
        return intLongTimeOut;
    }

    private WebEventListener eventListener;
    private EventFiringWebDriver driver;
    //private final Logger logger = Logger.getLogger(SeleniumHelper.class);
    private WebDriver e_driver;
    private int pollingTimeOut = 250;
    private String fileUploadPath = PreProcessFiles.TEST_FILES_FOLDER_PATH;

    // variable to work with element in frame(s)
    private String activeFrame = "";

    public void setActiveFrame(String activeFrame) {
        this.activeFrame = activeFrame;
    }

    public String getActiveFrame() {
        return activeFrame;
    }

    // variables to deal with conditional events
    private List<ConditionalEvent.Event> conditionalEvent = null;

    public void setConditionalEvent(ArrayList<ConditionalEvent.Event> conditionalEvent) {
        this.conditionalEvent = conditionalEvent;
    }

    public List<ConditionalEvent.Event> getConditionalEvent() {
        return conditionalEvent;
    }

    private final int numberOfAttemptToClick = 2;
    private final int secondsToWaitAtConditionalEvent = 10;

    //
    private By elementBy = null;

    public By getElementBy() {
        return elementBy;
    }

    private static final ThreadLocal<SeleniumHelper> instance = new ThreadLocal<SeleniumHelper>() {
        @Override
        protected SeleniumHelper initialValue() {
            return new SeleniumHelper();
        }
    };

    // constructor
    private SeleniumHelper() {

        e_driver = instantiateDriver();

        driver = new EventFiringWebDriver(e_driver);

        eventListener = new WebEventListener();
        driver.register(eventListener);

        driver.manage().timeouts().implicitlyWait(10, SECONDS);
        driver.manage().window().maximize();
    }

    public static SeleniumHelper getInstance() {
        return instance.get();
    }

    private WebDriver instantiateDriver() {
        try {
            //return new WebDriverThread().getDriver();
            return BasePage.driver();
        } catch (Exception e) {
            throw new WebDriverException("Could not start the Driver", e);
        }
    }

    /**
     * Close the Selenium Helper instance
     */
    public static void closeInstance() {
        instance.remove();
    }

    /**
     * @return the web diver object that has been initially created by DriveFactory.
     * This is not the EventFiring driver
     * Use it only to get remote driver session.
     */
    public WebDriver getInitialDriver() {
        return e_driver;
    }

    /**
     * Returns the driver instance
     *
     * @return driver
     */
    public WebDriver getDriver() {
        return driver;
    }

    /**
     * Load a URL in browser
     *
     * @param targetURL: the URL to be used in the browser
     */
    public void goToURL(String targetURL) {
        driver.get(targetURL);
    }

    /**
     * Close browser
     */
    public void closeBrowser() {
        try {
            if (driver != null) {
                driver.quit();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Waits for a page to load completely
     *
     * @param timeoutSeconds: the integer value that specifies the timeout
     */
    public void waitForPageLoad(int timeoutSeconds) {
        Wait<WebDriver> wait = new WebDriverWait(driver, timeoutSeconds, 500).ignoring(WebDriverException.class);
        wait.until((Function<WebDriver, Boolean>) driver -> String.valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState"))
                .equals("complete"));
            }

    /**
     * Action to switch to specified window index. Index = 1 is supposed to be
     * the index of main window
     *
     * @param index - index of a window that it is needed to switch to.
     */
    public void switchToWindowByIndex(int index) {
        ArrayList<String> windows;
        if (index > 1) {
            waitWhileExpectedWindowsAppear(5, index);
            windows = new ArrayList<String>(driver.getWindowHandles());
            driver.switchTo().window(windows.get(index - 1));
        } else {
            try {
                waitWhileExpectedWindowsLeft(5, 1);
            } catch (Exception e) {
                reporter.info("Seems like several windows are still opened." +
                        " This may mean that some windows don't close themselves automatically." +
                        " Trying to close them...");
                //NoahLogManager.getLogger().warn("Seems like several windows are still opened." +
                //                        " This may mean that some windows don't close themselves automatically." +
                //                        " Trying to close them...");
                windows = new ArrayList<String>(driver.getWindowHandles());
                for (int i = windows.size(); i > 1; i--) {
                    driver.switchTo().window(windows.get(i - 1)).close();
                    waitWhileExpectedWindowsLeft(5, i - 1);
                }
            }
            windows = new ArrayList<String>(driver.getWindowHandles());
            driver.switchTo().window(windows.get(0));
        }
    }

    /**
     * Action to wait while browser opens all expected windows. Used when
     * expected number of windows is greater than 1
     *
     * @param timeoutSeconds  - number of SECONDS to wait for expected windows to appears
     * @param numberOfWindows - indicates the minimal number of windows to wait for
     */
    private void waitWhileExpectedWindowsAppear(int timeoutSeconds, int numberOfWindows) {
        Matcher<?> matcher = is(greaterThanOrEqualTo(numberOfWindows));
        new FluentWait<WebDriver>(driver).withTimeout(timeoutSeconds, SECONDS).pollingEvery(1000, MILLISECONDS)
                .until((Function<WebDriver, Boolean>) d -> (matcher.matches(driver.getWindowHandles().size())));
                    }

    /**
     * Action to wait while browser close extra windows and leaves the main one.
     * Used when expected number of windows is 1
     *
     * @param timeoutSeconds  - number of SECONDS to wait for expected windows to disappears
     * @param numberOfWindows - indicates the number of windows be left
     */
    private void waitWhileExpectedWindowsLeft(int timeoutSeconds, int numberOfWindows) {
        Matcher<?> matcher = is(lessThanOrEqualTo(numberOfWindows));
        new FluentWait<WebDriver>(driver).withTimeout(timeoutSeconds, SECONDS).pollingEvery(1000, MILLISECONDS)
                .until((Function<WebDriver, Boolean>) d -> (matcher.matches(driver.getWindowHandles().size())));
                    }

    /**
     * Method to define type of locators to be used in Doable classes
     *
     * @param locator
     * @return By-type value
     */
    private By initElementByLocator(String locator) {
        if (PageLocatorMatcher.isXpath(locator)) {
            return By.xpath(locator.substring(6));
        } else if (PageLocatorMatcher.isId(locator)) {
            return By.id(locator.substring(3));
        } else if (PageLocatorMatcher.isCss(locator)) {
            return By.cssSelector(locator.substring(4));
        } else if (PageLocatorMatcher.isName(locator)) {
            return By.name(locator.substring(5));
        } else if (PageLocatorMatcher.isTagname(locator)) {
            return By.tagName(locator.substring(8));
        } else if (PageLocatorMatcher.isClass(locator)) {
            return By.className(locator.substring(10));
        } else if (PageLocatorMatcher.isLink(locator)) {
            return By.linkText(locator.substring(5));
        } else if (locator.startsWith("scLocator")) {
            return ByScLocator.scLocator(locator);
        } else {
            reporter.fail("Cannot initialize " + locator + " as an accepted type of value. Property item cannot be found!");
            //Reporter.fail("Cannot initialize " + locator + " as an accepted type of value. Property item cannot be found!");
            return By.linkText(locator);
        }
    }

    /**
     * Action to get text data from the input text field / text area
     *
     * @param locator locator type to be used to locate the input text field / text area
     * @return text value from the
     */
    public String getData(String locator) {
        this.elementBy = initElementByLocator(locator);
        WebElement webelement = driver.findElement(this.elementBy);
        String data = webelement.getText().trim();
        if (data.isEmpty()) {
            try {
                data = webelement.getAttribute("value").trim();
            } catch (Exception e) {
                data = "";
            }
        }
        return data;
    }

    /**
     * Action to get object's property value
     *
     * @param locator  locator type to be used to locate the object
     * @param property property name to extract the value
     * @return text value of the specified property or NOT_AVAILABLE
     */
    public String getPropertyValue(String locator, String property) {

        this.elementBy = initElementByLocator(locator);
        WebElement webelement = driver.findElement(this.elementBy);
        String value = webelement.getAttribute(property);

        if (value == null) {
            return "NOT_AVAILABLE";
        } else {
            return value;
        }
    }

    /**
     * Action to select radio button identified by locator based on
     * boolean expression is true or false
     *
     * @param locator:   locator type to be used to locate the radio button element
     * @param mustCheck: true or false
     */
    public void selectRadioButton(String locator, boolean mustCheck) {
        this.elementBy = initElementByLocator(locator);
        WebElement webelement = driver.findElement(this.elementBy);
        boolean clickButton = !webelement.isSelected() & mustCheck;
        if (clickButton) {
            webelement.click();
        }
    }

    /**
     * Action to validate text data from an element
     *
     * @param locator: locator type to be used to locate the radio button element
     * @return String webelement text
     */
    public String getTextValueFromField(String locator) {
        this.elementBy = initElementByLocator(locator);
        WebElement webelement = driver.findElement(this.elementBy);
        String data = webelement.getText().trim();
        if (data.isEmpty()) {
            try {
                reporter.info("Getting text from value attribute");
                //NoahLogManager.getLogger().info("Getting text from value attribute");
                data = webelement.getAttribute("value").trim();
            } catch (Exception e) {
                data = "";
            }
        }
        return data;
    }

    /**
     * Action to check / uncheck the checkbox on a web page
     *
     * @param locator:         locator type to be used to locate the radio button element
     * @param shouldBeChecked: true / false
     */
    public void checkUncheckCheckbox(String locator, boolean shouldBeChecked) {
        this.elementBy = initElementByLocator(locator);
        WebElement checkbox = driver.findElement(this.elementBy);
        boolean doClick = (!checkbox.isSelected() & shouldBeChecked) || (checkbox.isSelected() & !shouldBeChecked);
        if (doClick) {
            checkbox.click();
        }
    }

    /**
     * Action to verify if checkbox is checked or unchecked
     *
     * @param locator: locator type to be used to locate the radio button element
     */
    public boolean verifyCheckBox(String locator) {
        this.elementBy = initElementByLocator(locator);
        WebElement checkbox = driver.findElement(this.elementBy);
        return checkbox.isSelected();
    }

    /**
     * Action to select a value from a dropdown
     *
     * @param locator: locator type to be used to locate the dropdown element
     * @param value:   value to be selected from dropdown
     */
    public void selectValueFromDropDown(String locator, String value) {
        this.elementBy = initElementByLocator(locator);
        if (!value.equals("")) {
            elementToBeEnable(this.elementBy);
            WebElement webelement = driver.findElement(this.elementBy);
            Select dropdown = new Select(webelement);
            waitForOptions(locator);
            try {
                selectDropDownOption(dropdown, value);
            } catch (StaleElementReferenceException e1) {
                webelement = driver.findElement(this.elementBy);
                dropdown = new Select(webelement);
                waitForOptions(locator);
                selectDropDownOption(dropdown, value);
            }
        }
    }

    /**
     * This method select the value from the dropdown
     *
     * @param dropdown : to pass the WebElement into dropdown
     * @param value    : pass the value to select from dropdown
     */
    private void selectDropDownOption(Select dropdown, String value) {
        try {
            dropdown.selectByVisibleText(value);
        } catch (NoSuchElementException n) {
            value = value.replaceAll(String.valueOf((char) 160), String.valueOf((char) 32));
            dropdown.selectByVisibleText(value);
        }
        WebDriverWait wait = new WebDriverWait(driver, intLongTimeOut);
        wait.until(ExpectedConditions.attributeContains(dropdown.getFirstSelectedOption(), "text", value));
    }

    /**
     * This method gets auto selected value from a dropdown
     *
     * @param locator: locator type to be used to locate the dropdown  element
     * @return a string indicating a value that has been selected
     */
    public String autoSelectFromDropdown(String locator) {
        this.elementBy = initElementByLocator(locator);
        elementToBeEnable(this.elementBy);
        WebElement selectElement = driver.findElement(this.elementBy);
        Select select = new Select(selectElement);
        waitForOptions(locator);

        int value;
        Random random = new Random();
        List<WebElement> allOptions = select.getOptions();

        if (allOptions.get(0).getText().toLowerCase().contains("none")||
                allOptions.get(0).getText().toLowerCase().contains("Select a country...")) {
            value = 1 + random.nextInt(allOptions.size() - 1);
        } else {
            value = random.nextInt(allOptions.size() - 1);
        }

        select.selectByIndex(value);
        return allOptions.get(value).getText();
    }

    /**
     * This method wait for the value to populate in the dropdown
     *
     * @param locator :- selector to find the element
     */
    private void waitForOptions(String locator) {
        this.elementBy = initElementByLocator(locator);
        try {
            reporter.info("Waiting for options to be available...");
            //NoahLogManager.getLogger().info("Waiting for options to be available...");
            WebElement webelement = driver.findElement(this.elementBy);
            Select dropdown = new Select(webelement);
            new FluentWait<WebDriver>(driver).withTimeout(60, SECONDS).pollingEvery(10, MILLISECONDS)
                    .until((Function<WebDriver, Boolean>) d -> (dropdown.getOptions().size() >= 2));
        } catch (StaleElementReferenceException s) {
            reporter.info("Seems like the web page is being updated. Waiting...");
            //NoahLogManager.getLogger().info("Seems like the web page is being updated. Waiting...");
            WebElement webelement = driver.findElement(this.elementBy);
            Select dropdown = new Select(webelement);
            new FluentWait<WebDriver>(driver).withTimeout(60, SECONDS).pollingEvery(10, MILLISECONDS)
                    .until((Function<WebDriver, Boolean>) d -> (dropdown.getOptions().size() >= 2));
                        }
        }

    /**
     * Wait for the element to be enable on web page
     *
     * @param locator: locator to wait to make it enable
     */
    public void elementToBeEnable(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, intLongTimeOut);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * Wait for the element to be displayed on the page
     *
     * @param locator: locator to check it is displayed on the webpage
     */
    public void waitForElementToBeDisplayed(String locator) {
        this.elementBy = initElementByLocator(locator);
        new FluentWait<WebDriver>(driver).withTimeout(intLongTimeOut, SECONDS)
                .pollingEvery(pollingTimeOut, MILLISECONDS)
                .ignoring(NoSuchElementException.class)
                .until(ExpectedConditions.visibilityOfElementLocated(this.elementBy));
    }

    public void waitTillElementAttributeChange (String locator, String desiredAttributeState) {

        this.elementBy = initElementByLocator(locator);

        WebDriverWait wait = new WebDriverWait(this.driver,20);

        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                String attribute = driver.findElement(elementBy).getAttribute(desiredAttributeState.split("=")[0]);

                return attribute.contains(desiredAttributeState.split("=")[1]);
            }
        });
    }

    public void elementToBePresent(String locator) {
        this.elementBy = initElementByLocator(locator);
        WebDriverWait wait = new WebDriverWait(driver, intLongTimeOut);
        wait.until(ExpectedConditions.presenceOfElementLocated(this.elementBy));
    }

    /**
     * Action to click an element on a web page
     *
     * @param locator: locator type to be used to locate the button element
     */
    public void clickElement(String locator, String expectedValueToAppearAfterClick) {
        this.elementBy = initElementByLocator(locator);
        WebElement webelement = driver.findElement(this.elementBy);
        webelement.click();
        postClickCheck(expectedValueToAppearAfterClick);
    }

    public void executeScript(String script) {
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript(script);

    }

    /**
     * Action to click on the element if it is visible on the page, or to skip it if it is not visible
     *
     * @param locator: locator type to be used to locate the element
     */
    public void skip(String locator) {
        this.elementBy = initElementByLocator(locator);
        try {
            WebElement webelement = driver.findElement(this.elementBy);
            reporter.info("Element: " + this.elementBy + " is Clicked");
            //Reporter.passMessage("Element: " + this.elementBy + " is Clicked");
            webelement.click();
        } catch (Exception e) {
            reporter.info("Element: " + this.elementBy + "  is Skipped");
            //Reporter.passMessage("Element: " + this.elementBy + "  is Skipped");
        }
    }

    /**
     * Method is used to handle specific events (JavaScript running, appearance of a text etc.) that may occur after click action.
     *
     * @param textToAppear text value that it may be required to wait for it's appearance
     */
    private void postClickCheck(String textToAppear) {

        if (this.conditionalEvent == null) {
            return;
        }

        for (ConditionalEvent.Event conditionalEvent : this.conditionalEvent) {
            if (conditionalEvent.key.equalsIgnoreCase("WaitForElementToAppear")) {
                waitForElement(conditionalEvent, numberOfAttemptToClick);
            } else if (conditionalEvent.key.equalsIgnoreCase("WaitForJavaScriptToUpdate")) {
                checkForJavaScriptToRun(conditionalEvent);
            } else if (conditionalEvent.key.equalsIgnoreCase("WaitForElementToContainText")) {
                waitForText(conditionalEvent, textToAppear);
            } else if (conditionalEvent.key.equalsIgnoreCase("AcceptAlert")) {
                acceptAlert(conditionalEvent);
            }
        }
    }


    /**
     * The method is used to accept alert if this alert is expected
     * and to fail test if alert is unexpected;
     */
    private void acceptAlert(ConditionalEvent.Event conditionalEvent) {
        if (conditionalEvent.key.equals("AcceptAlert".toLowerCase())) {
            waitForPopupToAppear();
            final Pattern ACCEPTABLE_ALERT =
                    Pattern.compile("^(Selecting a new template will delete all unsaved content. Do you want to proceed?)" +
                            "|(Account Alert: SLA Information: .*)" +
                            "|(Are you sure you want to delete the selected page?.*)" +
                            "|(Potential duplicate company record. Check existing account:.*)" +
                            "|(Are you sure you want to remove this component?.*)" +
                            "|(Set marking allocation for all selected submissions?.*)" +
                            "|(Potential duplicate student account record found.*)$");
            try {
                Alert jsalert = driver.switchTo().alert();
                String alertMsg = jsalert.getText();
                java.util.regex.Matcher matcher = ACCEPTABLE_ALERT.matcher(alertMsg);
                if (matcher.find()) {
                    reporter.info("\t\t-" + alertMsg + "\n" + " popup appeared");
                    //Reporter.log("\t\t-" + alertMsg + "\n" + " popup appeared");
                    jsalert.accept();
                    reporter.info("Alert message found and accepted");
                    //Reporter.log("Alert message found and accepted");
                } else {
                    //Reporter.failTryTakingScreenshot("Unexpected error occurred:" +
                    //                            "<pre><textarea>" + alertMsg + "</textarea></pre>");
                }
            } catch (NoAlertPresentException Ex) {
                reporter.info("No alert message found");
                //Reporter.log("No alert message found");
            }
        }
    }

    /**
     * The method is used to wait until popup appears;
     */

    private void waitForPopupToAppear() {

        new FluentWait<WebDriver>(driver)
                .withTimeout(10, SECONDS)
                .ignoring(NoAlertPresentException.class)
                .pollingEvery(1, SECONDS)
                .until(ExpectedConditions.alertIsPresent());
    }

    /**
     * The method is used to wait while JS is running after interacting with a web-element;
     */
    private void checkForJavaScriptToRun(ConditionalEvent.Event conditionalEvent) {
        if (conditionalEvent.key.equals("WaitForJavaScriptToUpdate".toLowerCase())) {
            if (conditionalEvent.value.isEmpty()) {
                JavaScriptHelper.waitForJavaScriptToRun(driver, secondsToWaitAtConditionalEvent);
            } else {
                try {
                    By LocatorOfElementToBeUpdated = initElementByLocator(conditionalEvent.value);
                    WebElement elementToBeUpdate = driver.findElement(LocatorOfElementToBeUpdated);
                    JavaScriptHelper.waitForJavaScriptToRun(driver, elementToBeUpdate, secondsToWaitAtConditionalEvent);
                } catch (Exception e) {
                    reporter.info("Some minor problems occurred when waiting for JS to update the web element.\n" +
                            "Continue the test execution.");
                    //NoahLogManager.getLogger().warn("Some minor problems occurred when waiting for JS to update the web element.\n" +
                    //                            "Continue the test execution.");
                }
            }
        }
    }

    /**
     * The method is used to wait for specific text to occur in an element on a page
     * OR specific field to contain any text
     *
     * @param text specific text value that must occur on the page.
     *             Pass empty value if it's needed to wait for specific field to contain any text
     */
    private void waitForText(ConditionalEvent.Event conditionalEvent, String text) {
        if (conditionalEvent.key.equals("WaitForElementToContainText".toLowerCase())) {
            By locator = initElementByLocator(conditionalEvent.value);
            if (text.isEmpty()) {
                new FluentWait<WebDriver>(driver)
                        .withTimeout(intLongTimeOut, SECONDS)
                        .ignoring(StaleElementReferenceException.class, ElementNotVisibleException.class)
                        .pollingEvery(2, SECONDS)
                        .until((Function<WebDriver, Boolean>) d -> !(driver.findElement(locator).getText().trim().isEmpty()));
            } else {
                reporter.info("Waiting for '" + text + "' text value to appear in the field located " + locator.toString());
                //NoahLogManager.getLogger().info("Waiting for '" + text + "' text value to appear in the field located " + locator.toString());
                reporter.info("Waiting for '" + text + "' text value to appear in the field located " + locator.toString());
                //Reporter.log("Waiting for '" + text + "' text value to appear in the field located " + locator.toString());
                new FluentWait<WebDriver>(driver)
                        .withTimeout(intLongTimeOut, SECONDS)
                        .ignoring(StaleElementReferenceException.class, ElementNotVisibleException.class)
                        .pollingEvery(2, SECONDS)
                        .until((Function<WebDriver, Boolean>) d -> driver.findElement(locator).getText().trim().equals(text));
                            }
            }
        }

    /**
     * The method is used to wait until AJAX will be fully completed;
     */

    public void waitForAjaxToComplete() {

        new FluentWait<WebDriver>(driver)
                .withTimeout(10, SECONDS)
                .ignoring(ScriptTimeoutException.class)
                .pollingEvery(2, SECONDS)
                .until((Function<WebDriver, Boolean>) driver -> (Boolean) getInstance().driver.
                            executeScript("jQuery.active == 0"));
    }
    /**
     * Action to imitate key press from keyboard
     *
     * @param fieldValue: value to be entered in appropriate field using metadata values
     */
    public void keyboardPress(String fieldValue) {
        if (!fieldValue.equals("")) {
            try {
                WebElement keyItem = driver.findElement(this.elementBy);
                keyItem.sendKeys(Keys.valueOf(fieldValue));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            reporter.info("No value defined for 'PressKey' action. Check your parameters");
            //NoahLogManager.getLogger().info("No value defined for 'PressKey' action. Check your parameters");
        }
    }

    /**
     * The method is used to click on a web-element few times to force the appearance of an expected web-element
     *
     * @param attempts value is taken from 'numberOfAttemptToClick' class field
     */
    private void waitForElement(ConditionalEvent.Event conditionalEvent, int attempts) {
        if (conditionalEvent.key.equals("WaitForElementToAppear".toLowerCase())) {

            WebDriverWait wait = new WebDriverWait(driver, secondsToWaitAtConditionalEvent);
            String elementToWaitForLocatorKey = conditionalEvent.value;
            if (PageLocatorMatcher.isECVariableInXpath(elementToWaitForLocatorKey)) {
                elementToWaitForLocatorKey = PageLocatorMatcher.updateXpath(elementToWaitForLocatorKey);
            }
            By locatorOfElementToWaitFor = initElementByLocator(elementToWaitForLocatorKey);

            boolean elementIsShown = true;
            while (attempts > 0) {
                try {
                    if (!elementIsShown) {
                        wait.until(ExpectedConditions.elementToBeClickable(this.elementBy)).click();
                    }
                    wait.until(ExpectedConditions.visibilityOfElementLocated(locatorOfElementToWaitFor));
                    break;
                } catch (Exception e) {
                    reporter.info("Attempting to click again.");
                    //NoahLogManager.getLogger().warn("Attempting to click again.");
                    elementIsShown = false;
                    attempts--;
                }
            }
        }
    }

    /**
     * Action to set text into a field on a web page
     *
     * @param locator:    locator type to be used to locate the button element
     * @param fieldValue: value to be entered in appropriate field
     */
    public void enterField(String locator, String fieldValue) {
        this.elementBy = initElementByLocator(locator);
        WebElement textField = driver.findElement(this.elementBy);

        if (!fieldValue.equals("")) {
            if (fieldValue.trim().equalsIgnoreCase("CLEAR-TEXT")) {
                textField.clear();
            } else {
                try {
                    textField.clear();
                    textField.sendKeys(fieldValue);
                } catch (InvalidElementStateException e) {
                    new Actions(driver).sendKeys(fieldValue).perform();
                }
            }
        }
    }

    /**
     * Action to clear text into a field on a web page
     *
     * @param locator: locator type to be used to locate the button element
     */
    public void clearField(String locator) {
        this.elementBy = initElementByLocator(locator);
        WebElement textField = driver.findElement(this.elementBy);
        try {
            textField.clear();
        } catch (InvalidElementStateException e) {
            new Actions(driver).sendKeys("").perform();
        }
    }

    /**
     * Action clears the entire  text field as Selenium's clear() refuses to work on new chrome versions
     *
     * @param locator: locator type to be used to locate the button element
     */
    public void clearEntireField(String locator) {
        this.elementBy = initElementByLocator(locator);
        WebElement textField = driver.findElement(this.elementBy);
        String backSpace = Keys.BACK_SPACE.toString();
        try {
            textField.click();
            int size = textField.getAttribute("value").length();

            if (size != 0)
                IntStream.range(0, size).mapToObj(i -> backSpace).forEach(textField::sendKeys);

        } catch (InvalidElementStateException e) {
            new Actions(driver).sendKeys("").perform();
        }
    }

    /**
     * Action to accept alert / error message on the web page
     *
     * @return String webelement text with information provided in the Popup Window
     */
    public String acceptAlertMessage() {
        Alert jsalert = new WebDriverWait(driver, 10).until(ExpectedConditions.alertIsPresent());
        String alertMsg = jsalert.getText();

        reporter.info("Logging " + alertMsg + " popup appeared");
        //Reporter.log("Logging " + alertMsg + " popup appeared");
        jsalert.accept();
        return alertMsg;
    }

    /**
     * Action to upload a file
     *
     * @param locator: locator type to be used to locate the element for uploading a file
     */
    public void fileUpload(String locator, String filename) {
        this.elementBy = initElementByLocator(locator);
        WebElement webelement = driver.findElement(this.elementBy);
        webelement.sendKeys(fileUploadPath + "/" + filename);
    }

    /**
     * Provides the ability to use the browser's navigation capabilities.
     *
     * @param operation: browser operation performed can be FORWARD, BACK, or REFRESH case-insensitive
     */
    public void pageNavigation(String operation) {
        String browserOperation = operation.toUpperCase();
        switch (browserOperation) {
            case "FORWARD":
                reporter.info("Browser FORWARD operation executing.");
                //NoahLogManager.getLogger().info("Browser FORWARD operation executing.");
                driver.navigate().forward();
                break;
            case "BACK":
                reporter.info("Browser BACK operation executing.");
                //NoahLogManager.getLogger().info("Browser BACK operation executing.");
                driver.navigate().back();
                break;
            case "REFRESH":
                reporter.info("Browser REFRESH operation executing.");
                //NoahLogManager.getLogger().info("Browser REFRESH operation executing.");
                driver.navigate().refresh();
                break;
            default:
                reporter.info("No navigation operation performed.  Check spelling for page navigation parameter.  Only 'Forward', 'Back', and 'Refresh' are supported.");
                //NoahLogManager.getLogger().info("No navigation operation performed.  Check spelling for page navigation parameter.  Only 'Forward', 'Back', and 'Refresh' are supported.");
                break;
        }

    }

    public void unconditionalWait(String seconds) {
        try {
            Thread.sleep(Integer.parseInt(seconds) * 1000);
        } catch (InterruptedException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public String selectSpecificData(String locator) {

        String[] decomposedLocator = locator.split("\\|");

        this.elementBy = initElementByLocator(decomposedLocator[0]);
        WebElement webelement = driver.findElement(this.elementBy);
        String data;

        try {
            data = webelement.getText().trim();
        } catch (Exception e) {
            return "";
        }

            for (String singleElement : data.split(" ")) {
                if (singleElement.matches(decomposedLocator[1])) {
                    return singleElement;
                }
            }

            throw new NotFoundException("Requested element was not found by " + decomposedLocator[1]);
    }

    public Boolean isElementVisible(String locator) {
        try {
            this.elementBy = initElementByLocator(locator);
            driver.findElement(this.elementBy);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    // ============= PRODUCT CATALOG ====================

    private final ProductCatalog productCatalog = new ProductCatalog();

    public ProductCatalog getProductCatalog() {
        return productCatalog;
    }

    public class ProductCatalog {

        private List<By> ByLocators;

        ProductCatalog() {
            ByLocators = new ArrayList<By>(2);
        }

        public void click(String locators) {
            ByLocators.clear();

            final String[] array;
            for (String locator : array = locators.split(">>>")) {
                ByLocators.add(getInstance().initElementByLocator(locator.trim()));
            }

            waitForApplicationToBeReady();
            reporter.info("APPLICATION IS DONE");
            //NoahLogManager.getLogger().info("APPLICATION IS DONE");

            if (array[0].trim().startsWith("scLocator")) {
                try {
                    waitForSmartClientElementToBeClickAble(array[0].trim());
                } catch (Exception e) {
                    Actions action = new Actions(getInstance().driver);
                    action.moveByOffset(0, 0).click().perform();
                    waitForSmartClientElementToBeClickAble(array[0].trim());
                }
            } else {
                getInstance().waitForElementToBeDisplayed(array[0].trim());
            }

            if (ByLocators.size() == 1) {
                getInstance().clickElement(array[0].trim(), "");
                return;
            }

            List<WebElement> list;
            WebElement parent = getInstance().driver.findElement(ByLocators.get(0));
            for (WebElement element : list = parent.findElements(ByLocators.get(1))) {
                if (list.isEmpty()) {
                    reporter.info("NO ELEMENT FOUND");
                    //NoahLogManager.getLogger().info("NO ELEMENT FOUND");
                    return;
                }
                try {
                    new WebDriverWait(getInstance().driver, 1)
                            .until(ExpectedConditions.elementToBeClickable(element)).click();
                    return;
                } catch (Exception e) {
                }
            }
        }

        public void setText(String locators, String fieldValue) {
            ByLocators.clear();

            final String[] array;
            for (String locator : array = locators.split(">>>")) {
                ByLocators.add(getInstance().initElementByLocator(locator.trim()));
            }

            waitForApplicationToBeReady();
            reporter.info("APPLICATION IS DONE");
            //NoahLogManager.getLogger().info("APPLICATION IS DONE");

            if (array[0].trim().startsWith("scLocator")) {
                try {
                    waitForSmartClientElementReadyForKeyPress(array[0].trim());
                } catch (Exception e) {
                    Actions action = new Actions(getInstance().driver);
                    action.moveByOffset(0, 0).click().perform();
                    waitForSmartClientElementReadyForKeyPress(array[0].trim());
                }
            } else {
                getInstance().waitForElementToBeDisplayed(array[0].trim());
            }

            if (ByLocators.size() == 1) {
                getInstance().enterField(array[0].trim(), fieldValue);
                return;
            }

            List<WebElement> list;
            WebElement parent = getInstance().driver.findElement(ByLocators.get(0));
            for (WebElement element : list = parent.findElements(ByLocators.get(1))) {
                if (list.isEmpty()) {
                    reporter.info("NO ELEMENT FOUND");
                    //NoahLogManager.getLogger().info("NO ELEMENT FOUND");
                    return;
                }
                try {
                    new WebDriverWait(getInstance().driver, 1)
                            .until(ExpectedConditions.elementToBeClickable(element)).sendKeys(fieldValue);
                    return;
                } catch (Exception e) {
                }
            }
        }

        private void waitForSmartClientElementToBeClickAble(final String scLocator) {
            new FluentWait<WebDriver>(getInstance().driver)
                    .pollingEvery(1, SECONDS)
                    .withTimeout(10, SECONDS)
                    .ignoring(WebDriverException.class)
                    .until((Function<WebDriver, Boolean>) driver -> (Boolean) getInstance().driver.
                            executeScript("return isc.AutoTest.isElementClickable(arguments[0])", scLocator));
        }

        private void waitForApplicationToBeReady() {
            try {
                getInstance().driver.executeScript("return isc)");
            } catch (WebDriverException e) {
                getInstance().waitForPageLoad(10);
                return;
            }
            new FluentWait<WebDriver>(getInstance().driver)
                    .pollingEvery(1, SECONDS)
                    .withTimeout(30, SECONDS)
                    .ignoring(WebDriverException.class)
                    .until((Function<WebDriver, Boolean>) driver -> (Boolean) getInstance().driver.
                            executeScript("return isc.AutoTest.isSystemDone()"));
        }

        private void waitForSmartClientElementReadyForKeyPress(final String scLocator) {
            new FluentWait<WebDriver>(getInstance().driver)
                    .pollingEvery(1, SECONDS)
                    .withTimeout(10, SECONDS)
                    .ignoring(WebDriverException.class)
                    .until((Function<WebDriver, Boolean>) driver -> (Boolean) getInstance().driver.
                            executeScript("return isc.AutoTest.isElementReadyForKeyPresses(arguments[0])", scLocator));
        }
    }
}