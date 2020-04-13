package cucumber.stepdefs;

import cucumber.reusablesteps.ReusableRunner;
import io.cucumber.java.AfterStep;
import io.cucumber.java.en.*;
import org.hamcrest.Matchers;
import org.openqa.selenium.NoSuchWindowException;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import ui.pages.BasePage;
import ui.utils.*;
import ui.utils.bpp.ExecutionContextHandler;
import ui.utils.bpp.JavaScriptHelper;
import ui.utils.bpp.TestParametersController;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import static com.jcabi.matchers.RegexMatchers.matchesPattern;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static ui.utils.bpp.ExecutionContextHandler.getExecutionContextValueByKey;

/**
 * Created by Ruslan Levytskyi on 15/3/2019.
 */
public class StepDefinitions extends BasePage {

    @AfterStep
    public void postActions() {
        try {
            driver().switchTo().defaultContent();
        }
        catch (NoSuchWindowException e) {
            BPPLogManager.getLogger().info("Catching exception: " + e.getMessage().substring(0,44));
        }
    }

    /**
     * Definition to go to specified url.
     * Also checks if the parameter is Meta Data or Execution Context value
     *
     * @param url url where you want navigate to
     * @author Ruslan Levytskyi
     */
    @Given("^I am on \"([^\"]*)\" URL$")
    public void i_am_on_url(String url) {
        Reporter.log("Executing step: I am on '" + url + "' url");
        String processedUrl = TestParametersController.checkIfSpecialParameter(url);
        driver().get(processedUrl);
        if (!url.equals(processedUrl)) {
            Reporter.log("<pre>[input test parameter] " + url + "' -> '" + processedUrl + "' [output value]</pre>");
        }
        waitForPageToLoad();
    }

    /**
     * Definition to click an element on the page
     *
     * @param element locator for element you want to click on
     *                initElementLocator builds locator, depending on input parameter:
     *                1. Starts with "xpath" or "css" - locator is passed directly into a method
     *                2. Parameter exists in locators document - locator value is returned from document
     *                3. None of above - parameter is treated as text value of element: //*[contains(text(), 'parameter')]
     * @author Ruslan Levytskyi
     */
    @When("^I click on the \"([^\"]*)\" (?:button|link|option|element)$")
    public void i_click_on_the_button(String element) {
        Reporter.log("Executing step: I click on the '" + element + "' element");
        clickOnElement(initElementLocator(element),
                UiHandlers.PF_SPINNER_HANDLER,
                UiHandlers.ACCEPT_ALERT,
                UiHandlers.PF_SCROLL_TO_ELEMENT_HANDLER,
                UiHandlers.PF_SCROLL_HANDLER,
                UiHandlers.SF_CLICK_HANDLER,
                UiHandlers.WAIT_HANDLER,
                UiHandlers.DEFAULT_HANDLER);
    }

    /**
     * Definition to click an element on the page if given condition is true
     *
     * @param element locator for element you want to click on
     *                initElementLocator builds locator, depending on input parameter:
     *                1. Starts with "xpath" or "css" - locator is passed directly into a method
     *                2. Parameter exists in locators document - locator value is returned from document
     *                3. None of above - parameter is treated as text value of element: //*[contains(text(), 'parameter')]
     * @author Ruslan Levytskyi
     */
    @When("^I click on the \"([^\"]*)\" (?:button|link|option|element) if \"([^\"]*)\" \"([^\"]*)\"$")
    public void i_click_on_the_button_if(String element, String conditionParameter, String condition) {
        Conditions conditions = new Conditions();
        if (conditions.checkCondition(condition, conditionParameter)) {
            Reporter.log("Executing step: I click on the '" + element + "' element");
            clickOnElement(initElementLocator(element), UiHandlers.ACCEPT_ALERT, UiHandlers.PF_SPINNER_HANDLER);
        } else {
            Reporter.log("Condition " + conditionParameter + condition + " is not true, so '" + element + "' element step will not be clicked");
        }
    }

    /**
     * Definition to send some text into some text input field
     *
     * @param element locator for element you want to send text to
     *                Parameter exists in locators document - locator value is returned from document
     * @param text    text you want to send to element
     *                Here we also check if text is EC_ or MD_ of KW_
     * @author Ruslan Levytskyi
     */
    @When("^I fill the \"([^\"]*)\" field with \"([^\"]*)\"$")
    public void fill_field(String element, String text) {
        Reporter.log("Executing step: I fill the  '" + element + "' field with '" + text + "'");
        String processedText = TestParametersController.checkIfSpecialParameter(text);
        BPPLogManager.getLogger().info("Setting: " + element + " with value: " + text);
        setText(initElementLocator(element), processedText);
        if (!text.equals(processedText)) {
            Reporter.log("<pre>[input test parameter] " + text + "' -> '" + processedText + " [output value]</pre>");
        }
    }

    /**
     * Definition to send some text into text input field using Javasript.
     * Was implemented to cover Totara functionality
     *
     * @param element locator for element you want to send text to
     *                Parameter exists in locators document - locator value is returned from document
     * @param text    text you want to send to element
     *                Here we also check if text is EC_ or MD_ of KW_
     * @author Yurii Zosin
     */
    @When("^I fill the \"([^\"]*)\" field with \"([^\"]*)\" byJS$")
    public void fill_field_with_JS (String element, String text) {
        Reporter.log("Executing step: I fill the  '" + element + "' field with '" + text + "'");
        String processedText = TestParametersController.checkIfSpecialParameter(text);

        BPPLogManager.getLogger().info("Setting: " + element + " with value: " + text);
        setText(initElementLocator(element), processedText);
        if (!text.equals(processedText)) {
            Reporter.log("<pre>[input test parameter] " + text + "' -> '" + processedText + " [output value]</pre>");
        }
    }

    /**
     * Definition to send some text into some text input field
     *
     * @param seconds amount of seconds you want to wait
     *                Here we also check if text is EC_ or MD_ of KW_
     * @author Ruslan Levytskyi
     */
    @When("^I wait for \"([^\"]*)\" seconds$")
    public void wait_for(String seconds) {
        Reporter.log("Executing step: I wait for " + seconds + " seconds");
        sleepFor(Integer.parseInt(TestParametersController.checkIfSpecialParameter(seconds)) * 1000);
    }

    /**
     * Definition to hover over element
     *
     * @param element locator of element you want to hover over
     *                Here we also check if text is EC_ or MD_ of KW_
     * @author Ruslan Levytskyi
     */
    @When("^I hover over the \"([^\"]*)\" (?:button|link|option|element)$")
    public void hover_over(String element) {
        Reporter.log("Executing step: I hover over the '" + element + "' element");
        hoverItem(initElementLocator(element));
    }

    /**
     * Definition to check visibility of the element
     *
     * @param element locator of element you want to check if it's visible
     *                Here we also check if text is EC_ or MD_ of KW_
     * @author Ruslan Levytskyi
     */
    @Then("^I should see the \"([^\"]*)\" (?:button|message|element)$")
    public void i_should_see_the_text(String element) {
        Reporter.log("Executing step: I should see the '" + element + "' element");
        boolean isDisplayed = false;
        for (int i = 0; i < findElements(initElementLocator(element)).size(); i++) {
            if (findElements(initElementLocator(element)).get(i).isDisplayed()) {
                isDisplayed = true;
            }
        }
        Assert.assertTrue(isDisplayed, "Element with " + element + " text is not displayed");
    }

    /**
     * Definition to verify that you are on the page with correct title
     *
     * @param pageTitle locator of element you want to check if it's visible
     *                  Here we also check if text is EC_ or MD_ of KW_
     * @author Ruslan Levytskyi
     */
    @Then("^I should be redirected to the \"([^\"]*)\" page$")
    public void i_should_be_redirected_to_page(String pageTitle) {
        Reporter.log("Executing step: I should be redirected to the '" + pageTitle + "' page");
        String processedPageTitle = TestParametersController.checkIfSpecialParameter(pageTitle);
        Reporter.log("Current page is " + driver().getTitle());
        System.out.println("Expected page is " + pageTitle);
        Assert.assertEquals(driver().getTitle(), processedPageTitle, "Current page is " + processedPageTitle);
        if (!pageTitle.equals(processedPageTitle)) {
            Reporter.log("<pre>[input test parameter] " + pageTitle + "' -> '" + processedPageTitle + " [output value]</pre>");
        }
    }

    /**
     * Definition to execute reusable steps
     *
     * @param reusableName name of reusable step (Scenario in ReusableSteps.feature) you want to execute
     *                     Here we also check if text is EC_ or MD_ of KW_
     * @author Ruslan Levytskyi
     */
    @Then("^I execute \"([^\"]*)\" reusable step$")
    public void i_execute_reusable_step(String reusableName) {
        Reporter.log("Executing step: I execute '" + reusableName + "' reusable step");
        ReusableRunner.getInstance().executeReusable(TestParametersController.checkIfSpecialParameter(reusableName));
    }

    @Then("^I execute \"([^\"]*)\" reusable step with some additional steps$")
    public void i_execute_reusable_step_with(String reusableName, Map<Integer, String> steps) {
        Reporter.log("Executing step: I execute '" + reusableName + "' reusable step with some additional steps");
        ReusableRunner.getInstance().executeReusableAddSteps(TestParametersController.checkIfSpecialParameter(reusableName), steps);
    }

    @Then("^I execute \"([^\"]*)\" reusable step replacing some steps$")
    public void i_execute_reusable_step_replace(String reusableName, Map<Integer, String> steps) {
        Reporter.log("Executing step: I execute '" + reusableName + "' reusable step with replacing some steps");
        ReusableRunner.getInstance().executeReusableReplaceStep(TestParametersController.checkIfSpecialParameter(reusableName), steps);
    }

    /**
     * Definition to execute reusable step if given condition is true
     *
     * @param reusableName name of reusable step (Scenario in ReusableSteps.feature) you want to execute
     *                     Here we also check if text is EC_ or MD_ of KW_
     * @author Ruslan Levytskyi
     */
    @Then("^I execute \"([^\"]*)\" reusable step if \"([^\"]*)\" \"([^\"]*)\"$")
    public void i_execute_reusable_step_if(String reusableName, String conditionParameter, String condition) {
        Conditions conditions = new Conditions();
        if (conditions.checkCondition(condition, conditionParameter)) {
            Reporter.log("Executing step: I execute '" + reusableName + "' reusable step with replacing some steps");
            ReusableRunner.getInstance().executeReusable(TestParametersController.checkIfSpecialParameter(reusableName));
        } else {
            Reporter.log("Condition " + conditionParameter + " " + condition + " is not true, so '" + reusableName + "' reusable step will not be executed");
        }
    }

    /**
     * Definition to save some text value in EC variable
     *
     * @param text    text you want to save into a variable
     * @param varName name of variable in which you want to save text
     * @author Ruslan Levytskyi
     */
    @Then("^I remember \"([^\"]*)\" text as \"([^\"]*)\" variable$")
    public void i_remember_text(String text, String varName) {
        Reporter.log("Executing step: I remember '" + text + "' text as '" + varName + "' variable");
        ExecutionContextHandler.setExecutionContextValueByKey(varName, TestParametersController.checkIfSpecialParameter(text));
    }

    /**
     * Definition to verify presence of element on the page
     *
     * @param attributeName  name of attribute of element you want to check
     * @param elementLocator name or value of needed element
     * @param attributeValue value that tested attribute should have
     * @author Ruslan Levytskyi
     */
    @When("^Attribute \"([^\"]*)\" of \"([^\"]*)\" should have value \"([^\"]*)\"$")
    public void elements_attribute_should_have_value(String attributeName, String elementLocator, String attributeValue) {
        Reporter.log("Executing step: Attribute '" + attributeName + "' of '" + elementLocator + "' should have value '" + attributeValue + "'");
       if (attributeValue.toUpperCase().contains("CONTAINS=")) {
           String attributeValueCropped = attributeValue.substring("CONTAINS=".length());
            if (attributeValue.contains("EC_")) {
                String executionContextValue = TestParametersController.checkIfSpecialParameter(ExecutionContextHandler.getExecutionContextValueByKey(attributeValueCropped));
                Assert.assertTrue(findElement(initElementLocator(elementLocator)).getAttribute(attributeName).contains(executionContextValue));
            }
        } else if (attributeValue.contains("EC_")) {
            String executionContextValue = TestParametersController.checkIfSpecialParameter(ExecutionContextHandler.getExecutionContextValueByKey(attributeValue));
            Assert.assertTrue(findElement(initElementLocator(elementLocator)).getAttribute(attributeName).equalsIgnoreCase(executionContextValue));
        } else {
        Assert.assertTrue(findElement(initElementLocator(elementLocator)).getAttribute(attributeName).equalsIgnoreCase(attributeValue));
        }
    }

    /**
     * Definition to check or uncheck the checkbox
     * If svalue is check, but the checkbox is unchecked, than this method checks the checkbox
     * Vice versa.
     *
     * @param value   = value to set a statement to the checkbox. Can be "check" or "uncheck"
     * @param element By locator of checkbox
     */
    @When("^I \"(check|uncheck)\" \"([^\"]*)\" checkbox$")
    public void i_check_uncheck(String value, String element) {
        Reporter.log("Executing step: I " + value + " '" + element + "' checkbox");
        String processedElement = TestParametersController.checkIfSpecialParameter(element);
        boolean state = true;
        if (value.equals("check")) {
            state = true;
        } else if (value.equals("uncheck")) {
            state = false;
        }
        checkCheckbox(initElementLocator(element), state);
        if (!element.equals(processedElement)) {
            Reporter.log("<pre>[input test parameter] " + element + "' -> '" + processedElement + " [output value]</pre>");
        }
    }

    /**
     * Definition to imitate key press from keyboard
     *
     * @param fieldValue : value to be entered in appropriate field using metadata values
     * @param element:   By locator of element to press key
     */
    @Then("^I press \"([^\"]*)\" for \"([^\"]*)\"$")
    public void i_press_from_keyboard(String fieldValue, String element) {
        Reporter.log("Executing step: I press the " + fieldValue + " from keyboard");
        pressKeyFromKeyboard(initElementLocator(element), TestParametersController.checkIfSpecialParameter(fieldValue));
        waitForPageToLoad();
    }

    /**
     * Definition to check visibility of the element located in frame
     *
     * @param element locator of element you want to check if it's visible
     *                Here we also check if text is EC_ or MD_ of KW_
     * @param frame   frame name to switch
     */
    @Then("^I should see the \"([^\"]*)\" message in \"([^\"]*)\" frame$")
    public void i_should_see_the_message_in_frame(String element, String frame) {
        Reporter.log("Executing step: I should see the " + element + " element located in frame");
        waitForPageToLoad();
        switchToFrame(initElementLocator(frame));
        findElements(initElementLocator(element));
    }

    /**
     * Definition to check invisibility of the element
     *
     * @param element: By locator of a element
     * @author Andrii Yakymchuk
     */
    @Then("I shouldn't see the \"([^\"]*)\"(?: button| message| element| text)?$")
    public void i_should_not_see_the_element(String element) {
        Reporter.log("Executing step: I shouldn't see the '" + element + "' element");
        if (checkIfElementNotExist(initElementLocator(element))) {
            Assert.assertTrue(false, "Element with " + element + " text is not displayed");
        } else {
            Assert.assertTrue(true, "Element with " + element + " is displayed, but it shouldn't");
        }
    }

    /**
     * Definition to verify that a certain number of elements are present on the page
     *
     * @author Ruslan Levytskyi
     * @param element xpath of needed element
     * @param expectedQuantity expected quantity of elements to be present on the page
     */
    @When("^I should see the \"([^\"]*)\"(?: button| message| element| text)? in quantity of \"([^\"]*)\"$")
    public void i_should_see_number_of_elements(String element, String expectedQuantity) {
        Reporter.log("Executing step: I should see " + expectedQuantity + " '" + element + "' elements");
        int actualNumberOfElements = numberOfElements(initElementLocator(element));
        if (expectedQuantity.contains("more than")) {
            Assert.assertTrue(actualNumberOfElements > Integer.parseInt(expectedQuantity.substring(10)));
        } else if (expectedQuantity.contains("less than")) {
            Assert.assertTrue(actualNumberOfElements < Integer.parseInt(expectedQuantity.substring(10)));
        } else {
            Assert.assertTrue(expectedQuantity.equals(String.valueOf(actualNumberOfElements)));
        }
    }

    /**
     * Action to upload a file
     *
     * @param element:  locator type to be used to locate the element for uploading a file
     * @param fileName: file that should be uploaded to element on the page
     */
    @Then("I upload \"([^\"]*)\" file to \"([^\"]*)\" element")
    public void i_upload_file_to_element(String fileName, String element) {
        Reporter.log("Executing step: I upload the " + fileName + " to: " + element);
        fileUpload(initElementLocator(element), fileName);
        waitForPageToLoad();
    }

    /**
     * Definition to validate text from web element
     *
     * @param text     : value to be checked
     * @param element: By locator of element to press key
     */
    @Then("^I validate text \"([^\"]*)\" to be displayed for \"([^\"]*)\" element$")
    public void i_validate_text_to_be_displayed_for_element(String text, String element) {
        String actualValue = "";
        Reporter.log("Executing step: I validate " + text + " to be displayed for: " + element);
        if (element.equalsIgnoreCase("CHECK_URL")) {
            actualValue = BasePage.driver().getCurrentUrl();
            Reporter.log("Validating URL to match :" + text);
            assertThat(actualValue, containsString(text));
        } else {
            actualValue = getTextValueFromField(initElementLocator(element));
            String newValue = text.replaceAll("''","\"");
            if (text.toUpperCase().trim().startsWith("RE=")) {
                newValue = newValue.substring("RE=".length());
                assertThat(actualValue.trim(), matchesPattern(newValue));
                Reporter.log("<pre>Actual value '" + actualValue + "' matches the pattern " + "'" + newValue + "'</pre>");
                BPPLogManager.getLogger().info("Actual value '" + actualValue + "' matches the pattern " + "'" + newValue + "'");
            } else if (text.toUpperCase().startsWith("CONTAINS=")) {
                newValue = newValue.substring("CONTAINS=".length());
                if (text.contains("EC")) {
                    String executionContextValue = ExecutionContextHandler.getExecutionContextValueByKey(newValue);
                    assertThat(actualValue.trim().toLowerCase(), Matchers.containsString(executionContextValue.toLowerCase()));
                } else {
                    assertThat(actualValue.trim(), Matchers.containsString(newValue));
                    Reporter.log("<pre>Actual value '" + actualValue + "' contains the string " + "'" + newValue + "'</pre>");
                    BPPLogManager.getLogger().info("Actual value '" + actualValue + "' contains the string " + "'" + newValue + "'");
                }
            } else if (text.toUpperCase().startsWith("NOT_CONTAINS=")) {
                newValue = newValue.substring("NOT_CONTAINS=".length());
                if (text.contains("EC")) {
                    String executionContextValue = ExecutionContextHandler.getExecutionContextValueByKey(newValue);
                    assertThat(actualValue.trim(), not(Matchers.containsString(executionContextValue)));
                } else {
                    assertThat(actualValue.trim(), not(Matchers.containsString(newValue)));
                    Reporter.log("<pre>Actual value '" + actualValue + "' not contains the string " + "'" + newValue + "'</pre>");
                    BPPLogManager.getLogger().info("Actual value '" + actualValue + "' not contains the string " + "'" + newValue + "'");
                }
            } else if (text.toUpperCase().startsWith("CASE=")) {
                newValue = newValue.substring("CASE=".length());
                assertThat(actualValue.trim(), Matchers.equalTo(newValue));
                Reporter.log("<pre>Actual value '" + actualValue + "' equals to the case sensitive string " + "'" + newValue + "'</pre>");
                BPPLogManager.getLogger().info("Actual value '" + actualValue + "' equals to the case sensitive string " + "'" + newValue + "'");
            } else if (text.toUpperCase().contains("STARTS-WITH=")) {
                newValue = newValue.substring("STARTS-WITH=".length());
                assertThat(actualValue.trim(), Matchers.startsWith(newValue));
                Reporter.log("<pre>Actual value '" + actualValue + "' starts with case sensitive string " + "'" + newValue + "'</pre>");
                BPPLogManager.getLogger().info("Actual value '" + actualValue + "' starts with case sensitive string " + "'" + newValue + "'");
            } else if (text.contains("EC_")) {
                String executionContextValue = ExecutionContextHandler.getExecutionContextValueByKey(newValue);
                assertThat(actualValue.trim(), Matchers.equalTo(executionContextValue));
                Reporter.log("<pre>Actual value '" + actualValue + "' equals to " + "'" + newValue + ": " + executionContextValue + "'</pre>");
                BPPLogManager.getLogger().info("Actual value '" + actualValue + "' equals to " + "'" + newValue + ": " + executionContextValue + "'");
            } else {
                assertThat(actualValue.trim(), Matchers.equalToIgnoringWhiteSpace(text));
                BPPLogManager.getLogger().info("Actual value '" + actualValue + "' equals to the case insensitive string " + "'" + newValue + "'");
                Reporter.log("<pre>Actual value '" + actualValue + "' equals to the case insensitive string " + "'" + newValue + "'</pre>");
            }
        }
    }

    /**
     * Definition scroll the page to the bottom after page is loaded
     *
     * @author Andrii Yakymchuk
     */
    @And("^I should scroll to the \"(top|bottom)\" of the page$")
    public void i_should_scroll_to_top_bottom_of_the_page(String value) {
        Reporter.log("Executing step: I should scroll to the " + value + " of the page");
        if (value.equals("top")) {
            scrollToTopOfPage();
        } else if (value.equals("bottom")) {
            scrollToBottomOfPage();
        }

    }

    @And("I select \"([^\"]*)\" from \"([^\"]*)\" element")
    public void i_select_from_element(String value, String element) {
        Reporter.log("Executing step: I select: " + value + " from " + element + " dropdown");
        if (value.equals("KW_AUTO_SELECT")) {
            Reporter.log("Starting random selection from dropdown.");
            String autoSelectedValue = autoSelectFromDropdown(initElementLocator(element));
            Reporter.log("Selected \"" + autoSelectedValue + "\" value from " + element);
        } else {
            Reporter.log("Selecting \"" + value + "\" value from " + element);
            selectValueFromDropDown(initElementLocator(element), value);
        }
    }

    /**
     * Switching to the window with appropriate index. Used when few windows are open in browser.
     * If needed it may be used in order to switch to any extra window and then back to the main window.
     * Index = 1 is supposed to be the index of the main window
     * MAXIMUM acceptable window index is 9
     */
    @Then("^I switch to window with index \"([^\"]*)\"$")
    public void i_switch_to_window_with_index(String value) {
        if (value.length() > 0) {
            int index = Integer.parseInt(value.substring(0, 1));
            Reporter.log("Switching to the window with index = " + index);
            switchToWindowByIndex(index);
        } else {
            Reporter.log("REQUIRED 'WINDOW INDEX' PARAMETER IS MISSED");
        }
    }

    /**
     * Perform click using JavaScript
     *
     */
    @When("^I click on the \"([^\"]*)\" (?:button|link|option|element) by JS$")
    public void i_click_with_JS(String element) {
        Reporter.log("Executing step: I click on the '" + element + "' element by JS");
        String condition = driver().getTitle();
        clickWithJS(initElementLocator(element));
        if (!condition.equals("Media") & (!condition.equals("BPP Totara Staging: Log in to the site"))
                & (!condition.equals("BPPTS: My Learning"))) {
        }
    }

    /**
     * Provides the ability to use the browser's navigation capabilities.
     *
     * @param operation: browser operation performed can be FORWARD, BACK, or REFRESH case-insensitive
     */
    @Then("^Browser performs \"([^\"]*)\" command$")
    public void browser_navigates(String operation) {
        String browserOperation = operation.toUpperCase();
        Reporter.log("Executing step: Performing browser " + browserOperation + " operation");
        switch (browserOperation) {
            case "FORWARD":
                BPPLogManager.getLogger().info("Browser FORWARD operation executing.");
                driver().navigate().forward();
                break;
            case "BACK":
                BPPLogManager.getLogger().info("Browser BACK operation executing.");
                driver().navigate().back();
                break;
            case "REFRESH":
                BPPLogManager.getLogger().info("Browser REFRESH operation executing.");
                driver().navigate().refresh();
                break;
            default:
                BPPLogManager.getLogger().info("No navigation operation performed.  Check spelling for page navigation parameter.  Only 'Forward', 'Back', and 'Refresh' are supported.");
                break;
        }
    }

    /**
     * Definition scroll the page to the bottom after page is loaded
     *
     * @param element locator of element you want to check if it's visible and soon to put into Execution Context
     * @param executionContext Name that starts with 'EC_' that is used to store saved text value from element
     *
     * @author Andrii Yakymchuk
     */

    @And("^I capture text data \"([^\"]*)\" as \"([^\"]*)\" variable$")
    public void i_capture_text_data_as_variable(String element, String executionContext) {
        String value = getTextValueFromField(initElementLocator(element));
        Reporter.log("Capturing data from : " + initElementLocator(element) +": " + executionContext);
        if (!executionContext.equals("")) {
            if (value.equals("")) {
                Reporter.log("Saving EC key " + executionContext + " with an empty string. No application data found.");
            } else {
                Reporter.log("Saving EC key " + executionContext + " = " + value);
            }
            ExecutionContextHandler.setExecutionContextValueByKey(executionContext, value);
        } else
            Reporter.log("Cannot save EC value with an empty key. Check your parameters.");
    }

    /**
     * Definition to execute JS code
     *
     * @param jsCode JS code to execute
     * @author Ruslan Levytskyi
     */
    @And("^I execute \"([^\"]*)\" JS code$")
    public void i_execute_js_code_for_element(String jsCode) {
        Reporter.log("Executing JS code: " + jsCode);
        executeJSCode(jsCode);
    }

    /**
     * Definition to get specific data using REGEX. Required for BE Create New Line Manager workflow
     *
     * @param element locator of element you want to check is visible
     * @param executionContext Name that starts with 'EC_' that is used to store saved text value from element
     *
     * @author yzosin
     */
    @And("^I capture special data \"([^\"]*)\" as \"([^\"]*)\" variable$")
    public void i_capture_special_data(String element, String executionContext) {

        String value = selectSpecificData(initElementLocator(element));
        Reporter.log("Capturing data from : " + initElementLocator(element) +": " + executionContext);
        if (!executionContext.equals("")) {
            if (value.equals("")) {
                Reporter.log("Saving EC key " + executionContext + " with an empty string. No application data found.");
            } else {
                Reporter.log("Saving EC key " + executionContext + " = " + value);
            }
            ExecutionContextHandler.setExecutionContextValueByKey(executionContext, value);
        } else
            Reporter.log("Cannot save EC value with an empty key. Check your parameters.");
    }
}