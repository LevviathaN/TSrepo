package ui.pages;

import com.google.common.base.Function;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.*;
import ui.utils.*;
import ui.utils.bpp.PreProcessFiles;
import ui.utils.bpp.TestParametersController;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.IntStream;

/**
 * <p> Base class for all page objects.
 * A class to store Selenium-specific operations </p>.
 * @author rlevytskyi
 */

public class BasePage {

    public static Map<String,String> specialLocatorsMap;
    public static Map<String,String> locatorsMap;

    public static final ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
    private String fileUploadPath = PreProcessFiles.TEST_FILES_FOLDER_PATH;
    //map, used by UiHandlers to determine, if exception was handled by any handler. If not, then DEFAULT_HANDLER is
    //executed.
    public static Map<String, Boolean> isHandled = new HashMap<>();

    //____________________________________________Timeouts section__________________________________________________

    public static final int DEFAULT_TIMEOUT = getTimeout();
    public static final int SHORT_TIMEOUT = getShortTimeout();

    private static int getTimeout() {
        String timeout = FileIO.getConfigProperty("DefaultTimeoutInSeconds");
        if (timeout == null) {
            Reporter.failTryTakingScreenshot("DefaultTimeoutInSeconds parameter was not found");
            timeout = "15";
        }
        return Integer.parseInt(timeout);
    }

    private static int getShortTimeout() {
        String timeout = FileIO.getConfigProperty("ShortTimeoutInSeconds");
        if (timeout == null) {
            Reporter.failTryTakingScreenshot("ShortTimeoutInSeconds parameter was not found");
            timeout = "3";
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
        Reporter.log("Page title is: " + driver().getTitle());
        Reporter.log("Page URL is: " + driver().getCurrentUrl());
        if (driver().getTitle().contains(title)) {
            result = true;
        } else {
            Reporter.failTryTakingScreenshot("Expected title: " + title + " but was: " + driver().getTitle());
            result = false;
        }

        if (driver().getCurrentUrl().contains(url))
            result = true;
        else {
            Reporter.failTryTakingScreenshot("Expected url: " + url + " but was: " + driver().getCurrentUrl());
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
        Reporter.log("Refreshing the page: " + "\"" + driver().getTitle() + "\"");
        driver().navigate().refresh();
    }

    public static String getSource() {
        String s = driver().getPageSource();
        return s;
    }

    public void openUrl(String url) {
        Reporter.log("Opening the: " + url);
        driver().get(url);
    }

    public void close() {
        Reporter.log("Closing the browser");
        driver().close();
    }

    public String getTitle() {
        Reporter.log("The page title is: " + "\"" + driver().getTitle() + "\"");
        return driver().getTitle();
    }

    public String getURL() {
        Reporter.log("The requested URL is: " + driver().getCurrentUrl());
        return driver().getCurrentUrl();
    }

    public void setText(By element, String value) {
        if (value != null) {
            BPPLogManager.getLogger().info("Setting: " + element +" with value: " + value);
            clearEntireField(element);
            findElement(element).sendKeys(value);
        }
    }

    /**
     * Action clears the entire  text field as Selenium's clear() refuses to work on new chrome versions
     *
     * @param element: locator type to be used to locate the button element
     */
    //Todo: Fix click in the middle issue
    //cursor is set in the middle of the field, so if text in field is bigger than the half of the field, text
    //will not be erased completely. Temporary fixed, but the method needs refactoring
    public void clearEntireField(By element) {
        WebElement textField = findElement(element);
        String backSpace = Keys.BACK_SPACE.toString();
        try {
            clickOnElement(element, UiHandlers.PF_SPINNER_HANDLER);
//            textField.click();
            int size = textField.getAttribute("value").length();

            if (size != 0) {
                IntStream.range(0, size).mapToObj(i -> backSpace).forEach(textField::sendKeys);
            }
            clickOnElement(element, UiHandlers.PF_SPINNER_HANDLER);
//            textField.click();
            size = textField.getAttribute("value").length();
            if (size != 0) {
                IntStream.range(0, size).mapToObj(i -> backSpace).forEach(textField::sendKeys);
            }

        } catch (InvalidElementStateException e) {
            textField.sendKeys("");
        }
    }

    /**
     * Action to select a value from a dropdown
     *
     * @param locator: locator type to be used to locate the dropdown element
     * @param value:   value to be selected from dropdown
     */
    public void selectValueFromDropDown(By locator, String value) {
        if (!value.equals("")) {
            elementToBeEnable(locator);
            WebElement webelement = driver().findElement(locator);
            Select dropdown = new Select(webelement);
            waitForOptions(locator);
            try {
                selectDropDownOption(dropdown, value);
            } catch (StaleElementReferenceException e1) {
                webelement = driver().findElement(locator);
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
    public void selectDropDownOption(Select dropdown, String value) {
        try {
            dropdown.selectByVisibleText(value);
        } catch (NoSuchElementException n) {
            value = value.replaceAll(String.valueOf((char) 160), String.valueOf((char) 32));
            dropdown.selectByVisibleText(value);
        }
        WebDriverWait wait = new WebDriverWait(driver(), DEFAULT_TIMEOUT);
        wait.until(ExpectedConditions.attributeContains(dropdown.getFirstSelectedOption(), "text", value));
    }
    /**
     * This method gets auto selected value from a dropdown
     *
     * @param locator: locator type to be used to locate the dropdown  element
     * @return a string indicating a value that has been selected
     */
    public String autoSelectFromDropdown(By locator) {

        elementToBeEnable(locator);
        WebElement selectElement = driver().findElement(locator);
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
    private void waitForOptions(By locator) {
        try {
            BPPLogManager.getLogger().info("Waiting for options to be available...");
            WebElement webelement = driver().findElement(locator);
            Select dropdown = new Select(webelement);
            new FluentWait<WebDriver>(driver()).withTimeout(Duration.of(30, ChronoUnit.SECONDS)).pollingEvery(Duration.ofMillis(10))
                    .until((Function<WebDriver, Boolean>) d -> (dropdown.getOptions().size() >= 2));
        } catch (StaleElementReferenceException s) {
            BPPLogManager.getLogger().info("Seems like the web page is being updated. Waiting...");
            WebElement webelement = driver().findElement(locator);
            Select dropdown = new Select(webelement);
            new FluentWait<WebDriver>(driver()).withTimeout(Duration.of(30, ChronoUnit.SECONDS)).pollingEvery(Duration.ofMillis(10))
                    .until((Function<WebDriver, Boolean>) d -> (dropdown.getOptions().size() >= 2));
        }
    }

    /**
     * Wait for the element to be enable on web page
     *
     * @param locator: locator to wait to make it enable
     */
    public void elementToBeEnable(By locator) {
        WebDriverWait wait = new WebDriverWait(driver(), SHORT_TIMEOUT);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public WebElement findByText(String element) {
        BPPLogManager.getLogger().info("Trying to find element: " + element );
        return findElement(byText(element));
    }

    public void clickByText(String text) {
        Reporter.log("Clicking on element with text '" + text + "'");
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
                }  catch(ElementClickInterceptedException clk){
                    BPPLogManager.getLogger().info("Looks like some other element received a click. Will try again");
                    driver().findElement(element).click();
                } catch (Exception e) {
                    e.printStackTrace();
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
        int timeoutForFindElement = timeout.length < 1 ? SHORT_TIMEOUT : timeout[0];
        try {
            (new WebDriverWait(driver(), timeoutForFindElement))
                    .until(ExpectedConditions.visibilityOfElementLocated(element));
            return driver().findElement(element);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Method to click on element
     *
     * @param element locator of element to click on
     * @param handlers code from UiHandlers enum, that will be executed, when exception occurs
     */
    public static void clickOnElement(By element, UiHandlers... handlers) {
        try {
            (new WebDriverWait(driver(), DEFAULT_TIMEOUT))
                    .until(ExpectedConditions.visibilityOfElementLocated(element));
            driver().findElement(element).click();
            waitForPageToLoad();
        } catch (Exception e) {
            for(UiHandlers handler : handlers){
                handler.getHandler().handle(element, e);
            }
        }
    }

    public static void clickWithJS(WebElement element){
        JavascriptExecutor executor = (JavascriptExecutor)driver();
        executor.executeScript("arguments[0].click();", element);
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
            BPPLogManager.getLogger().error(Tools.getStackTrace(e));
            Reporter.failTryTakingScreenshot(Tools.getStackTrace(e));
            throw new RuntimeException("Failure finding element");
        }
    }

    /**
     * Method to find element by locator checking presence of element (not visibility)
     *
     * @param element locator of element to find
     * @param timeout optional value of timeout for finding selected element
     *
     * @return list of WebElements found by specified locator
     */
    public WebElement findPresentElement(By element, int... timeout) {
        int timeoutForFindElement = timeout.length < 1 ? DEFAULT_TIMEOUT : timeout[0];
        waitForPageToLoad();
        try {
            (new WebDriverWait(driver(), timeoutForFindElement))
                    .until(ExpectedConditions.presenceOfElementLocated(element));
            return driver().findElement(element);
        } catch (TimeoutException e) {
            BPPLogManager.getLogger().info("Exception caught. Trying to find an element again.");
            new FluentWait<WebDriver>(driver()).withTimeout(Duration.of(10, ChronoUnit.SECONDS))
                    .pollingEvery(Duration.ofMillis(2000))
                    .ignoring(TimeoutException.class).ignoring(NoSuchElementException.class)
                    .until(ExpectedConditions.presenceOfElementLocated(element));
            return driver().findElement(element);
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
        BPPLogManager.getLogger().info("Finding elements: " + element);
        try {
            (new WebDriverWait(driver(), timeoutForFindElement))
                    .until(ExpectedConditions.presenceOfElementLocated(element));
            return driver().findElements(element);
        } catch (Exception e) {
            BPPLogManager.getLogger().error(Tools.getStackTrace(e));
            Reporter.failTryTakingScreenshot(Tools.getStackTrace(e));
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
            Reporter.log("Got exception. Exception is expected and ignored.");
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
        BPPLogManager.getLogger().info("Getting an: " + element + " by attribute: " + attributeName);
        try {
            (new WebDriverWait(driver(), timeoutForFindElement))
                    .until(ExpectedConditions.visibilityOfElementLocated(element));
            return findElement(element).getAttribute(attributeName);
        } catch (Exception e) {
            Reporter.failTryTakingScreenshot(Tools.getStackTrace(e));
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

        if (locatorsMap.containsKey(element)) {
            return TestParametersController.initElementByLocator(locatorsMap.get(element));
        } else {
            return byText(TestParametersController.checkIfSpecialParameter(element));
        }
    }

    /**
     * Method to check or uncheck the checkbox
     * If shouldBeChecked is true, but the checkbox is unchecked, than this method checks the checkbox
     * Vice versa.
     *
     * @param shouldBeChecked = boolean to set a statement to the checkbox
     * @param element By locator of checkbox
     */
    public void checkCheckbox(By element, boolean shouldBeChecked){
        WebElement checkbox = findElement(element);
        if((!checkbox.isSelected() & shouldBeChecked) || (checkbox.isSelected() & !shouldBeChecked)){
            BPPLogManager.getLogger().info("Checking the checkbox " + checkbox);
            checkbox.click();
        }
    }

    /**
     * Method to scroll to the element
     * Scrolls down until element is in a view
     *
     * @param element WebElement of element to scroll to
     */
    public static void scrollToElement(WebElement element) {
        waitForPageToLoad();
        BPPLogManager.getLogger().info("Scrolling to element: " + element);
        ((JavascriptExecutor) driver()).executeScript("arguments[0].scrollIntoView();", element);
    }

    /**
     * Method to scroll to the bottom of the page
     */
    public static void scrollToBottomOfPage() {
        BPPLogManager.getLogger().info("Scrolling to bottom of the page.");
        ((JavascriptExecutor) driver()).executeScript("window.scrollTo(0, document.body.scrollHeight)");
        waitForPageToLoad();
    }

    /**
     * Method to scroll by a certain amount of pixels
     */
    public static void scrollBy(int x, int y) {
        BPPLogManager.getLogger().info("Scrolling to bottom of the page.");
        ((JavascriptExecutor) driver()).executeScript("window.scrollBy(" + x + ", " + y +")");
        waitForPageToLoad();
    }

    /**
     * Method to wait for page to load for DEFAULT_TIMEOUT
     */
    public static void waitForPageToLoad(){
        Wait<WebDriver> wait = new WebDriverWait(driver(), DEFAULT_TIMEOUT, 1000).ignoring(WebDriverException.class);
        wait.until(new Function<WebDriver, Boolean>() {
            public Boolean apply(WebDriver driver) {
                return String.valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState"))
                        .equals("complete");
            }
        });
    }

    /**
     * Method to wait for selectrd element for DEFAULT_TIMEOUT
     *
     * @param by locator of element to wait for
     */
    //unused functionality. To be removed in next commits
    static void waitForElement(By by) {
        WebDriverWait wait = new WebDriverWait(driver(), DEFAULT_TIMEOUT);
        BPPLogManager.getLogger().info("Waiting for element: " + by);
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
        BPPLogManager.getLogger().info("Waiting for alert");
        while (i++ < timeout) try {
            driver().switchTo().alert();
            break;
        } catch (NoAlertPresentException e)
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
    // Does not work in Firefox because of geckodriver_mac bug
    // https://stackoverflow.com/questions/40360223/webdriverexception-moveto-did-not-match-a-known-command
    public void hoverItem(By element) {
        BPPLogManager.getLogger().info("Hover over an alement: " + element);
        Actions action = new Actions(driver());
        action.moveToElement(findElement(element)).build().perform();
    }

    /**
     * Method to switch to iFrame on the page
     *
     * @param frameName locator of iFrame you need to switch to
     */
    public void switchToFrame(By frameName) {
        BPPLogManager.getLogger().info("Switching to frame: " + frameName);
        WebDriverWait wait = new WebDriverWait(driver(), 10);
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameName));
        sleepFor(5);
        try {
            driver().switchTo().frame(findPresentElement(frameName, 10));
        } catch (Exception e) {
            e.getMessage();
        }
    }

    /**
     * Method to switch back from iFrame to default page content
     */
    public void switchToDefaultContent() {
        Reporter.log("Switch to default content");
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
            BPPLogManager.getLogger().info("Swtiching to window: " + window);
            driver().switchTo().window(window);
            if (driver().getTitle().contains(tabTitle)) {
                return;
            }
        }
    }

    /**
     * Action to imitate key press from keyboard
     *
     * @param value: value to be entered in appropriate field using metadata values
     * @param element By locator of element to press the key for
     */
    public void pressKeyFromKeyboard(By element, String value) {
        if (!value.equals("")) {
            try {
                WebElement keyItem = findElement(element);
                keyItem.sendKeys(Keys.valueOf(value));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            BPPLogManager.getLogger().error("No value defined for 'PressKey'. Check your parameters");
        }
    }

    /**
     * Action to verify that element is not present
     *
     * @param element By locator of element to press the key for
     */
    public boolean checkIfElementNotExist(By element) {
        waitForPageToLoad();
        if (driver().findElements(element).size() != 0) {
            BPPLogManager.getLogger().info("Element: " + element + " is not displayed");
            return true;
        } else {
            BPPLogManager.getLogger().info("Element: " + element + " is displayed");
            return false;
        }
    }

    /**
     * Action to upload a file
     *
     * @param locator: locator type to be used to locate the element for uploading a file
     */
    public void fileUpload(By locator, String filename) {
        WebElement webelement = findPresentElement(locator);
        ((RemoteWebElement) webelement ).setFileDetector(new LocalFileDetector());
        webelement.sendKeys(fileUploadPath + "/" + filename);
    }

    /**
     * Action to validate text data from an element
     *
     * @param locator: locator type to be used to locate the radio button element
     * @return String webelement text
     */
    public String getTextValueFromField(By locator) {

        WebElement webelement = driver().findElement(locator);
        String data = webelement.getText().trim();
        if (data.isEmpty()) {
            try {
                BPPLogManager.getLogger().info("Getting text from value attribute");
                data = webelement.getAttribute("value").trim();
            } catch (Exception e) {
                data = "";
            }
        }
        return data;
    }

    /**
     * Action to accept alert / error message on the web page
     *
     * @return String webelement text with information provided in the Popup Window
     */
    public void acceptAlertMessage() {
        Alert jsalert = new WebDriverWait(driver(), 5).until(ExpectedConditions.alertIsPresent());
        String alertMsg = jsalert.getText();

        Reporter.log("Expected:" + alertMsg + " popup appeared");
        jsalert.accept();
    }
}
