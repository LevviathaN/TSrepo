package ui.pages;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import com.google.common.base.Function;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import ui.utils.*;

/**
 * Created by odiachuk on 07.07.17.
 */

public class BasePage {

    public static ReporterManager reporter = ReporterManager.Instance;
    public static Logger logger = Logger.getLogger(BasePage.class);
    public static HashMap<String, String> EXECUTION_CONTEXT = new HashMap<>();
    public final static String BASE_URL = (FileIO.getConfigProperty("Environment"));
    public String pageURL = "";
    public String pageTitle = "";
    Robot robot;

    //needed because of mac and windows have different Ctrl keys
    int systemControllKey = DriverProvider.OS_EXTENTION.equals("_mac") ? KeyEvent.VK_META : KeyEvent.VK_CONTROL;

    public static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

    /**
     * ____________________________________________Timeouts section__________________________________________________
     */

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
            timeout = "3";
        }

        return Integer.parseInt(timeout);
    }

    private static int getStaticTimeout() {
        String timeout = FileIO.getConfigProperty("StaticTimeoutMilliseconds");
        if (timeout == null) {
            timeout = "1000";
        }
        return Integer.parseInt(timeout);
    }

    /**
     * ____________________________________________________________________________________________________________
     */

    //constructor
    public BasePage() {
        try {
            robot = new Robot();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Getter, that ensures only one driver instance exists in project
    public static WebDriver driver() {
        return driver.get();
    }

    /**
     * _______________________________________________Basic Assertions_______________________________________________
     */

    public boolean isPageLoaded() {
        boolean result = false;
        logger.info("Page title is: " + driver().getTitle());
        logger.info("Page URL is: " + driver().getCurrentUrl());
        if (driver().getTitle().contains(pageTitle))
            result = true;
        else {
            logger.info("Expected title: " + pageTitle);
            result = false;
        }

        if (driver().getCurrentUrl().contains(pageURL))
            result = true;
        else {
            logger.info("Expected URL: " + pageURL);
            result = false;
        }

        return result;
    }

    public boolean isTextPresent(String text) {
        return driver().getPageSource().contains(text);
    }

    public boolean isElementPresent(By by) {
        try {
            WebElement element = findElementIgnoreException(by);
            return element.isDisplayed();
        } catch (RuntimeException e) {
            return false;
        }
    }

    public boolean isElementPresent(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (RuntimeException e) {
            return false;
        }
    }

    public boolean isElementPresent(String _cssSelector) {
        try {
            findElementIgnoreException(By.cssSelector(_cssSelector));
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }

    public boolean isElementPresentAndDisplay(By by) {
        try {
            return findElementIgnoreException(by).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isElementDisplayedRightNow(By by) {
        try {
            return findElementIgnoreException(by, SHORT_TIMEOUT).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * _______________________________________________Basic Actions__________________________________________________
     */

    public void reloadPage() {
        logger.info("Refreshing the page: " + "\"" + BASE_URL + pageURL + "\"");
        driver().navigate().refresh();
    }

    public void open() {
        logger.info("Opening the page: " + "\"" + BASE_URL + pageURL + "\"");
        driver().get(BASE_URL + pageURL);
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
        logger.info("Closing the browser");
        driver().close();
    }

    public String getTitle() {
        logger.info("The page title is: " + "\"" + pageTitle + "\"");
        return pageTitle;
    }

    public String getURL() {
        logger.info("The requested URL is: " + BASE_URL + pageURL);
        return BASE_URL + pageURL;
    }

    protected void sendText(String cssSelector, String text) {
        findElement(By.cssSelector(cssSelector)).sendKeys(text);
    }

    public void setText(By element, String value) {
        if (value != null) {
            findElement(element).clear();
            findElement(element).sendKeys(value);
        }
    }

    public WebElement getWebElement(By by) {
        return findElement(by);
    }


    public void selectFromDropdown(By element, String value) {
        reporter.info("Selecting '" + value + "' from dropdown");
        Select dropdown = new Select(findElement(element));
        dropdown.selectByVisibleText(value);
    }


    public WebElement findByText(String element) {
        reporter.info("Аinding '" + element + "' element");
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

    //todo: rename to clickOnFirstVisibleElement and create another method called clickOnFirstInteractibleElement
    //actually, if you have second, you don't need first
    public void clickOnAnyElement(By element, int... elementNumber) {
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

    public static void clickWithJS(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver();
        executor.executeScript("arguments[0].click();", element);
    }

    public WebElement findElement(By element, int... timeout) {
        int timeoutForFindElement = timeout.length < 1 ? DEFAULT_TIMEOUT : timeout[0];
        waitForPageToLoad();
        try {
            //synchronize();
            (new WebDriverWait(driver(), timeoutForFindElement))
                    .until(ExpectedConditions.visibilityOfElementLocated(element));
            return driver().findElement(element);
        } catch (Exception e) {
            reporter.fail(Tools.getStackTrace(e));
            throw new RuntimeException("Failure finding element");
        }
    }

    public List<WebElement> findElements(By element, int... timeout) {
        int timeoutForFindElement = timeout.length < 1 ? DEFAULT_TIMEOUT : timeout[0];
        waitForPageToLoad();
        try {
            //synchronize();
            (new WebDriverWait(driver(), timeoutForFindElement))
                    .until(ExpectedConditions.presenceOfElementLocated(element));
            return driver().findElements(element);
        } catch (Exception e) {
            reporter.fail(Tools.getStackTrace(e));
            throw new RuntimeException("Failure finding elements");
        }
    }

    public String getAttributeIDIgnoreExecption(By element, int... timeout) {
        waitForPageToLoad();
        try {
            return getAttributeID(element, timeout[0]);
        } catch (RuntimeException e) {
            reporter.info("Got exception. Exception is expected and ignored.");
        }
        return null;
    }

    public String getAttributeID(By element, int... timeout) {
        int timeoutForFindElement = timeout.length < 1 ? DEFAULT_TIMEOUT : timeout[0];
        waitForPageToLoad();
        try {
            //synchronize();
            (new WebDriverWait(driver(), timeoutForFindElement))
                    .until(ExpectedConditions.visibilityOfElementLocated(element));
            String id = findElement(element).getAttribute("id");
            return id;
        } catch (Exception e) {
            throw new RuntimeException("Failure getting attribute id of an element");
        }
    }

    public static void setDriverContextToPage(WebDriver driver) {
        reporter.info("Setting the context mode to Page");
        driver.switchTo().defaultContent();
    }

    public static void scrollToElement(WebElement element) {
        waitForPageToLoad();
        ((JavascriptExecutor) driver()).executeScript("arguments[0].scrollIntoView();", element);
    }

    public static void scrollToBottomOfPage() {
        waitForPageToLoad();
        ((JavascriptExecutor) driver()).executeScript("window.scrollTo(0, document.body.scrollHeight)");
        waitForPageToLoad();
    }

    public static void waitForPageToLoad() {

        ExpectedCondition<Boolean> expectation = driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");

        Wait<WebDriver> wait = new WebDriverWait(driver(), DEFAULT_TIMEOUT);

        try {
            wait.until(expectation);
        } catch (Exception error) {
            reporter.fail("JavaScript readyState query timeout - The page has not finished loading");
        }
        //        Wait<WebDriver> wait = new WebDriverWait((WebDriver) driver, STATIC_TIMEOUT, 500).ignoring(WebDriverException.class);
//        wait.until((Function<WebDriver, Boolean>) driver -> String.valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState"))
//                .equals("complete"));
    }


    static void waitForElement(By by) {
        WebDriverWait wait = new WebDriverWait(driver(), DEFAULT_TIMEOUT);
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public static void sleepFor(int msTimeout) {
        try {
            Thread.sleep(msTimeout);
        } catch (InterruptedException e) {
        }
    }

    static void waitForAlert(WebDriver driver, int timeout) {
        int i = 0;
        while (i++ < timeout) {
            try {
                Alert alert = driver.switchTo().alert();
                break;
            } catch (NoAlertPresentException e)  // wait for second
            {
                sleepFor(1);
                continue;
            }
        }
    }

    // Does not work because of geckodriver bug - https://stackoverflow.com/questions/40360223/webdriverexception-moveto-did-not-match-a-known-command
    public void hoverItem(By element) {
        Actions action = new Actions(driver());
        action.moveToElement(findElement(element)).build().perform();
    }

    public void switchToFrame(By xpath) {
        reporter.info("Switch to frame: " + xpath.toString());
        driver().switchTo().frame(findElement(xpath));
    }

    public void switchToDefaultContent() {
        reporter.info("Switch to default content");
        driver().switchTo().defaultContent();
    }

    public void handleMultipleWindows(String windowTitle) {
        Set<String> windows = driver().getWindowHandles();

        for (String window : windows) {
            driver().switchTo().window(window);
            if (driver().getTitle().contains(windowTitle)) {
                return;
            }
        }
    }

    public void uploadFile(String path) {

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

    public void bringToFocus() {

        robot.keyPress(systemControllKey);
        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyRelease(systemControllKey);
        robot.keyRelease(KeyEvent.VK_TAB);
        robot.delay(2000);
    }
}
