package ui.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import ui.utils.DriverProvider;
import ui.utils.FileIO;
import ui.utils.ReporterManager;
import ui.utils.Tools;
import ui.utils.bpp.PropertiesHandler;
import ui.utils.bpp.TestParametersController;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by odiachuk on 07.07.17.
 */

public class BasePage {

    public static ReporterManager reporter = ReporterManager.Instance;
    private Robot robot;
    public static Map<String,String> specialLocatorsMap;

    //needed because of mac and windows have different Ctrl keys
    int systemControllKey = DriverProvider.OS_EXTENTION.equals("_mac") ? KeyEvent.VK_META : KeyEvent.VK_CONTROL;

    public static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

    //____________________________________________Timeouts section__________________________________________________

    public static final int DEFAULT_TIMEOUT = getTimeout();
    public static final int SHORT_TIMEOUT = getShortTimeout();
    public static final int STATIC_TIMEOUT = getStaticTimeout();

    private static int getTimeout() {
        String timeout = FileIO.getConfigProperty("DefaultTimeoutInSeconds");
        if (timeout == null) {
            reporter.fail("DefaultTimeoutInSeconds parameter was not found");
            timeout = "15";
        }
        return Integer.parseInt(timeout);
    }

    private static int getShortTimeout() {
        String timeout = FileIO.getConfigProperty("ShortTimeoutInSeconds");
        if (timeout == null) {
            reporter.fail("ShortTimeoutInSeconds parameter was not found");
            timeout = "3";
        }
        return Integer.parseInt(timeout);
    }

    private static int getStaticTimeout() {
        String timeout = FileIO.getConfigProperty("StaticTimeoutMilliseconds");
        if (timeout == null) {
            reporter.fail("StaticTimeoutMilliseconds parameter was not found");
            timeout = "1000";
        }
        return Integer.parseInt(timeout);
    }

    //____________________________________________________________________________________________________________

    //constructor
    public BasePage() {
    }

    //Getter, that ensures only one driver instance exists in project
    public static WebDriver driver() {
        return driver.get();
    }

    //_______________________________________________Basic Assertions_______________________________________________

    /**
     * Assertion to check if proper page is loaded
     *
     * @param title title that page should have
     * @param url url that page should have
     *
     * @return bool
     */
    public boolean isPageLoaded(String title, String url) {
        boolean result = false;
        reporter.info("Page title is: " + driver().getTitle());
        reporter.info("Page URL is: " + driver().getCurrentUrl());
        if (driver().getTitle().contains(title)) {
            result = true;
        } else {
            reporter.info("Expected title: " + title + " but was: " + driver().getTitle());
            result = false;
        }

        if (driver().getCurrentUrl().contains(url))
            result = true;
        else {
            reporter.info("Expected url: " + url + " but was: " + driver().getCurrentUrl());
            result = false;
        }

        return result;
    }

    /**
     * Assertion to check if specified text is present on the page
     *
     * @param text text to find
     *
     * @return bool
     */
    public boolean isTextPresent(String text) {
        return driver().getPageSource().contains(text);
    }

    /**
     * Assertion to check if specified element is displayed on the page
     *
     * @param by locator of element
     *
     * @return bool
     */
    public boolean isElementPresentAndDisplay(By by) {
        try {
            return findElementIgnoreException(by).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    //________________________________________________Basic Actions___________________________________________________

    public void reloadPage() {
        reporter.info("Refreshing the page: " + "\"" + driver().getTitle() + "\"");
        driver().navigate().refresh();
    }

    public static String getSource() {
        String s = driver().getPageSource();
        return s;
    }

    public void openUrl(String url) {
        reporter.info("Opening the: " + url);
        driver().get(url);
    }

    public void close() {
        reporter.info("Closing the browser");
        driver().close();
    }

    public String getTitle() {
        reporter.info("The page title is: " + "\"" + driver().getTitle() + "\"");
        return driver().getTitle();
    }

    public String getURL() {
        reporter.info("The requested URL is: " + driver().getCurrentUrl());
        return driver().getCurrentUrl();
    }

    public void setText(By element, String value) {
        if (value != null) {
            findElement(element).clear();
            findElement(element).sendKeys(value);
        }
    }

    public void selectFromDropdown(By element, String value) {
        reporter.info("Selecting '" + value + "' from dropdown");
        Select dropdown = new Select(findElement(element));
        dropdown.selectByVisibleText(value);
    }

    public WebElement findByText(String element) {
        return findElement(byText(element));
    }

    public void clickByText(String text) {
        reporter.info("Clicking on element with text '" + text + "'");
        findElement(byText(text)).click();
    }

    public By byText(String element) {
        return By.xpath("//*[text()='" + element + "']");
    }

    public By byAttribute(String attributeName, String attributeValue) {
        return By.xpath("//*[@" + attributeName + "='" + attributeValue + "']");
    }

    public By byTagAndAttribute(String tagName, String attributeName, String attributeValue) {
        return By.xpath("//" + tagName + "[@" + attributeName + "='" + attributeValue + "']");
    }

    //_______________________________________________Complex Actions__________________________________________________

    /**
     * Method to click on element and continue execution if element is not found
     *
     * @param element locator of element to find
     * @param timeout optional value of timeout for finding specified element
     */
    public static void clickOnElementIgnoreException(By element, int... timeout) {
        waitForPageToLoad();
        int timeoutForFindElement = timeout.length < 1 ? DEFAULT_TIMEOUT : timeout[0];
        try {
            (new WebDriverWait(driver(), timeoutForFindElement))
                    .until(ExpectedConditions.visibilityOfElementLocated(element));
            driver().findElement(element).click();
        } catch (Exception e) {
            // nothing
        }
        waitForPageToLoad();
    }

    /**
     * Method to click on first visible element by specified locator
     *
     * @param element locator of element to find
     * @param elementNumber optional order number of element, if you want to find not FIRST visible element but N of
     *                      all visible elements, found by specified locator
     */
    //todo: rename to clickOnFirstVisibleElement and create another method called clickOnFirstInteractibleElement
    //actually, if you have second, you don't need first
    public void clickOnFirstVisibleElement(By element, int... elementNumber) {
        waitForPageToLoad();
        List<WebElement> elements = findElements(element);
        if (elementNumber.length > 0) {
            elements.get(elementNumber[0]).click();
        } else {
            for (WebElement elem : elements) {
                try {
                    elem.click();
                    break;
                } catch (Exception e) {
                    //nothing
                }
            }
        }
    }

    /**
     * Method to find group element and continue execution if element is not found
     *
     * @param element locator of element to find
     * @param timeout optional value of timeout for finding specified element
     *
     * @return WebElement
     */
    public static WebElement findElementIgnoreException(By element, int... timeout) {
        waitForPageToLoad();
        int timeoutForFindElement = timeout.length < 1 ? DEFAULT_TIMEOUT : timeout[0];
        try {
            (new WebDriverWait(driver(), timeoutForFindElement))
                    .until(ExpectedConditions.visibilityOfElementLocated(element));
            return driver().findElement(element);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Method to find group elements and continue execution if element is not found
     *
     * @param element locator of element to find
     * @param timeout optional value of timeout for finding specified element
     *
     * @return list of WebElements
     */
    public static List<WebElement> findElementsIgnoreException(By element, int... timeout) {
        int timeoutForFindElement = timeout.length < 1 ? DEFAULT_TIMEOUT : timeout[0];
        waitForPageToLoad();
        try {
            (new WebDriverWait(driver(), timeoutForFindElement))
                    .until(ExpectedConditions.presenceOfElementLocated(element));
            return driver().findElements(element);
        } catch (Exception e) {
            return new ArrayList<WebElement>();
        }
    }

    /**
     * Method to click on element
     *
     * @param element locator of element to click on
     * @param timeout optional value of timeout for finding specified element
     */
    public static void clickOnElement(By element, int... timeout) {
        int timeoutForFindElement = timeout.length < 1 ? DEFAULT_TIMEOUT : timeout[0];
        waitForPageToLoad();
        try {
            (new WebDriverWait(driver(), timeoutForFindElement))
                    .until(ExpectedConditions.visibilityOfElementLocated(element));
            driver().findElement(element).click();
        } catch (Exception e) {
            reporter.fail(Tools.getStackTrace(e));
            throw new RuntimeException("Failure clicking on element");
        }
        waitForPageToLoad();
    }

    /**
     * Method to find element by locator
     *
     * @param element locator of element to find
     * @param timeout optional value of timeout for finding selected element
     *
     * @return list of WebElements found by specified locator
     */
    public WebElement findElement(By element, int... timeout) {
        int timeoutForFindElement = timeout.length < 1 ? DEFAULT_TIMEOUT : timeout[0];
        waitForPageToLoad();
        try {
            (new WebDriverWait(driver(), timeoutForFindElement))
                    .until(ExpectedConditions.visibilityOfElementLocated(element));
            return driver().findElement(element);
        } catch (Exception e) {
            reporter.fail(Tools.getStackTrace(e));
            throw new RuntimeException("Failure finding element");
        }
    }

    /**
     * Method to find elements by locator
     *
     * @param element locator of element to find
     * @param timeout optional value of timeout for finding selected element
     *
     * @return list of WebElements found by specified locator
     */
    public List<WebElement> findElements(By element, int... timeout) {
        int timeoutForFindElement = timeout.length < 1 ? DEFAULT_TIMEOUT : timeout[0];
        waitForPageToLoad();
        try {
            (new WebDriverWait(driver(), timeoutForFindElement))
                    .until(ExpectedConditions.presenceOfElementLocated(element));
            return driver().findElements(element);
        } catch (Exception e) {
            reporter.fail(Tools.getStackTrace(e));
            throw new RuntimeException("Failure finding elements");
        }
    }

    /**
     * Method to get value of element attribute and continue execution if element or attribute is not found
     *
     * @param element locator of element to find attribute value of
     * @param attributeName name of element attribute you want to get
     * @param timeout optional value of timeout for finding selected element
     *
     * @return value of attribute of element
     */
    public String getElementAttributeIgnoreExecption(By element, String attributeName, int... timeout) {
        waitForPageToLoad();
        try {
            return getElementAttribute(element, attributeName, timeout[0]);
        } catch (RuntimeException e) {
            reporter.info("Got exception. Exception is expected and ignored.");
        }
        return null;
    }

    /**
     * Method to get value of element attribute
     *
     * @param element locator of element to find attribute value of
     * @param attributeName name of element attribute you want to get
     * @param timeout optional value of timeout for finding selected element
     *
     * @return value of attribute of element
     */
    public String getElementAttribute(By element, String attributeName, int... timeout) {
        int timeoutForFindElement = timeout.length < 1 ? DEFAULT_TIMEOUT : timeout[0];
        waitForPageToLoad();
        try {
            (new WebDriverWait(driver(), timeoutForFindElement))
                    .until(ExpectedConditions.visibilityOfElementLocated(element));
            return findElement(element).getAttribute(attributeName);
        } catch (Exception e) {
            throw new RuntimeException("Failure getting attribute id of an element");
        }
    }

    /**
     * Method to form By element locator based on type of inserted locator
     * Used in StepDefinitions
     *
     *  1. Starts with "xpath" or "css" - locator is passed directly into a method
     *  2. Parameter exists in locators document - locator value is returned from document
     *  3. None of above - parameter is treated as text value of element: //*[contains(text(), 'parameter')]
     *
     * @param element string locator fo find element
     * @return element By locator
     */
    public By initElementLocator(String element) {
        String locatorFromFile = PropertiesHandler.getPropertyByKey(element);
        //if direct locator
        if (element.startsWith("xpath")| element.startsWith("css")) {
            return TestParametersController.initElementByLocator(element);
        } else if(!element.equals(locatorFromFile))
            return TestParametersController.initElementByLocator(locatorFromFile);
        else
            return byText(TestParametersController.checkIfSpecialParameter(element));
    }

    /**
     * Method to scroll to the element
     * Scrolls down until element is in a view
     *
     * @param element WebElement of element to scroll to
     */
    public static void scrollToElement(WebElement element) {
        waitForPageToLoad();
        ((JavascriptExecutor) driver()).executeScript("arguments[0].scrollIntoView();", element);
    }

    /**
     * Method to scroll to the bottom of the page
     */
    public static void scrollToBottomOfPage() {
        ((JavascriptExecutor) driver()).executeScript("window.scrollTo(0, document.body.scrollHeight)");
        waitForPageToLoad();
    }

    /**
     * Method to wait for page to load for DEFAULT_TIMEOUT
     */
    public static void waitForPageToLoad() {
        ExpectedCondition<Boolean> expectation = driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
        Wait<WebDriver> wait = new WebDriverWait(driver(), DEFAULT_TIMEOUT);
        try {
            wait.until(expectation);
        } catch (Exception error) {
            reporter.fail("JavaScript readyState query timeout - The page has not finished loading");
        }
    }

    /**
     * Method to wait for selectrd element for DEFAULT_TIMEOUT
     *
     * @param by locator of element to wait for
     */
    //unused functionality. To be removed in next commits
    static void waitForElement(By by) {
        WebDriverWait wait = new WebDriverWait(driver(), DEFAULT_TIMEOUT);
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    /**
     * Method to call Thread.sleep
     *
     * @param msTimeout time to wait in milliseconds
     */
    public static void sleepFor(int msTimeout) {
        try {
            Thread.sleep(msTimeout);
        } catch (InterruptedException e) {
        }
    }

    /**
     * Method to wait for alert
     *
     * @param timeout time to wait in milliseconds
     */
    static void waitForAlert(int timeout) {
        int i = 0;
        while (i++ < timeout) try {
            Alert alert = driver().switchTo().alert();
            break;
        } catch (NoAlertPresentException e)  // wait for second
        {
            sleepFor(1);
            continue;
        }
    }

    /**
     * Method to hover over selected element
     *
     * @param element locator of element to hover over
     */
    // Does not work in Firefox because of geckodriver bug
    // https://stackoverflow.com/questions/40360223/webdriverexception-moveto-did-not-match-a-known-command
    public void hoverItem(By element) {
        Actions action = new Actions(driver());
        action.moveToElement(findElement(element)).build().perform();
    }

    /**
     * Method to switch to iFrame on the page
     *
     * @param xpath xpath of iFrame you need to switch to
     */
    public void switchToFrame(By xpath) {
        reporter.info("Switch to frame: " + xpath.toString());
        driver().switchTo().frame(findElement(xpath));
    }

    /**
     * Method to switch back from iFrame to default page content
     */
    public void switchToDefaultContent() {
        reporter.info("Switch to default content");
        driver().switchTo().defaultContent();
    }

    /**
     * Method to switch between opened tabs in your browser
     *
     * @param tabTitle name of opened tab you want to switch to
     */
    public void handleMultipleTabs(String tabTitle) {
        Set<String> windows = driver().getWindowHandles();

        for (String window : windows) {
            driver().switchTo().window(window);
            if (driver().getTitle().contains(tabTitle)) {
                return;
            }
        }
    }

    /**
     * Method to upload the file from your machine, using upload window of your browser (Chrome recomended)
     * Method should be called after upload button on site page is clicked
     *
     * @param path path to file on your machine, that you want to upload
     */
    public void uploadFile(String path) {
        if(robot==null){
            try { robot = new Robot(); }
            catch (Exception e) { e.printStackTrace(); }
        }

        File file = new File(path);
        StringSelection stringSelection = new StringSelection(file.getAbsolutePath());

        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
        robot.delay(2000);

        if (DriverProvider.OS_EXTENTION.equals("_mac")) {
            reporter.info("Opening Goto Window");
            robot.keyPress(systemControllKey);
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_G);
            robot.keyRelease(systemControllKey);
            robot.keyRelease(KeyEvent.VK_SHIFT);
            robot.keyRelease(KeyEvent.VK_G);
        }

        //Paste the clipboard value
        reporter.info("Pasting from clipboard");
        robot.keyPress(systemControllKey);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(systemControllKey);
        robot.keyRelease(KeyEvent.VK_V);

        //Press Enter key to close the Goto window and Upload window
        reporter.info("\"Executing Cmd + Tab\"");
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        robot.delay(2000);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }

    /**
     * Method to bring your browser window back to focus
     * in case of some other application, that will need to be opened during test, take the focus off
     *
     * In particular case, java Robot opens Java application in the middle of test execution
     * That causes browser window do loose focus. To continue execution this method is called
     */
    public void bringToFocus() {
        if(robot == null) {
            try { robot = new Robot(); }
            catch (Exception e) { e.printStackTrace(); }
        }

        robot.keyPress(systemControllKey);
        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyRelease(systemControllKey);
        robot.keyRelease(KeyEvent.VK_TAB);
        robot.delay(2000);
    }
}
