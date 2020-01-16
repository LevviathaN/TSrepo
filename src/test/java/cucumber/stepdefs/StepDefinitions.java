package cucumber.stepdefs;

import cucumber.api.java.AfterStep;
import cucumber.reusablesteps.ReusableRunner;
import cucumber.api.java.en.*;
//import io.cucumber.java.en.*;
import org.hamcrest.Matchers;
import org.openqa.selenium.By;
import org.testng.Assert;
import ui.pages.BasePage;
import ui.utils.*;
import ui.utils.bpp.ExecutionContextHandler;
import ui.utils.bpp.TestParametersController;

import java.util.Map;

import static com.jcabi.matchers.RegexMatchers.matchesPattern;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;

/**
 * Created by Ruslan Levytskyi on 15/3/2019.
 */
public class StepDefinitions extends BasePage {

    String actualValue = "";

    @AfterStep
    public void postActions() {
        driver().switchTo().defaultContent();
    }

    /**
     * Definition to go to specified url.
     * Also checks if the parameter is Meta Data or Execution Context value
     *
     * @author Ruslan Levytskyi
     * @param url url where you want navigate to
     */
    @Given("^I am on \"([^\"]*)\" URL$")
    public void i_am_on_url(String url) {
        Reporter.log("Executing step: I am on '" + url + "' url");
        String processedUrl = TestParametersController.checkIfSpecialParameter(url);
        driver().get(processedUrl);
        if(!url.equals(processedUrl)){
            Reporter.log("<pre>[input test parameter] " + url + "' -> '" + processedUrl + "' [output value]</pre>");
        }
        waitForPageToLoad();
    }

    /**
     * Definition to click an element on the page
     *
     * @author Ruslan Levytskyi
     * @param element locator for element you want to click on
     *      initElementLocator builds locator, depending on input parameter:
     *      1. Starts with "xpath" or "css" - locator is passed directly into a method
     *      2. Parameter exists in locators document - locator value is returned from document
     *      3. None of above - parameter is treated as text value of element: //*[contains(text(), 'parameter')]
     */
    @When("^I click on the \"([^\"]*)\" (?:button|link|option|element)(?: in [^\"]*)?$")
    public void i_click_on_the_button(String element) {
        Reporter.log("Executing step: I click on the '" + element + "' element");
        clickOnElement(initElementLocator(element), UiHandlers.PF_SPINNER_HANDLER);
        waitForPageToLoad();
    }

    /**
     * Definition to send some text into some text input field
     *
     * @author Ruslan Levytskyi
     * @param element locator for element you want to send text to
     *       Parameter exists in locators document - locator value is returned from document
     * @param text text you want to send to element
     *             Here we also check if text is EC_ or MD_ of KW_
     */
    @When("^I fill the \"([^\"]*)\" field with \"([^\"]*)\"$")
    public void fill_field(String element, String text) {
        Reporter.log("Executing step: I fill the  '" + element + "' field with '" + text + "'");
        String processedText = TestParametersController.checkIfSpecialParameter(text);
        findElement(initElementLocator(element)).sendKeys(processedText);
        if(!text.equals(processedText)){
            Reporter.log("<pre>[input test parameter] " + text + "' -> '" + processedText + " [output value]</pre>");
        }
    }

    /**
     * Definition to send some text into some text input field
     *
     * @author Ruslan Levytskyi
     * @param seconds amount of seconds you want to wait
     *             Here we also check if text is EC_ or MD_ of KW_
     */
    @When("^I wait for \"([^\"]*)\" seconds$")
    public void wait_for(String seconds) {
        Reporter.log("Executing step: I wait for " + seconds + " seconds");
        sleepFor(Integer.parseInt(TestParametersController.checkIfSpecialParameter(seconds))*1000);
    }

    /**
     * Definition to hover over element
     *
     * @author Ruslan Levytskyi
     * @param element locator of element you want to hover over
     *             Here we also check if text is EC_ or MD_ of KW_
     */
    @When("^I hover over the \"([^\"]*)\" (?:button|link|option|element)$")
    public void hover_over(String element) {
        Reporter.log("Executing step: I hover over the '" + element + "' element");
        hoverItem(initElementLocator(element));
    }

    /**
     * Definition to check visibility of the element
     *
     * @author Ruslan Levytskyi
     * @param element locator of element you want to check if it's visible
     *             Here we also check if text is EC_ or MD_ of KW_
     */
    @Then("^I should see the \"([^\"]*)\" (?:button|message|element)$")
    public void i_should_see_the_text(String element) {
        Reporter.log("Executing step: I should see the '" + element + "' element");
        waitForPageToLoad();
        boolean isDisplayed = false;
        for(int i = 0; i<findElements(initElementLocator(element)).size(); i++){
            if(findElements(initElementLocator(element)).get(i).isDisplayed()){
                isDisplayed = true;
            }
        }
        Assert.assertTrue(isDisplayed, "Element with " + element + " text is not displayed");
    }

    /**
     * Definition to verify that you are on the page with correct title
     *
     * @author Ruslan Levytskyi
     * @param pageTitle locator of element you want to check if it's visible
     *             Here we also check if text is EC_ or MD_ of KW_
     */
    @Then("^I should be redirected to the \"([^\"]*)\" page$")
    public void i_should_be_redirected_to_page(String pageTitle) {
        Reporter.log("Executing step: I should be redirected to the '" + pageTitle + "' page");
        waitForPageToLoad();
        String processedPageTitle = TestParametersController.checkIfSpecialParameter(pageTitle);
        Reporter.log("Current page is " + driver().getTitle());
        System.out.println("Expected page is " + pageTitle);
        Assert.assertEquals(driver().getTitle(), processedPageTitle, "Current page is " + processedPageTitle);
        if(!pageTitle.equals(processedPageTitle)){
            Reporter.log("<pre>[input test parameter] " + pageTitle + "' -> '" + processedPageTitle + " [output value]</pre>");
        }
    }

    /**
     * Definition to execute reusable steps
     *
     * @author Ruslan Levytskyi
     * @param reusableName name of reusable step (Scenario in ReusableSteps.feature) you want to execute
     *             Here we also check if text is EC_ or MD_ of KW_
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
     * Definition to execute reusable steps
     *
     * @author Ruslan Levytskyi
     * @param text text you want to save into a variable
     * @param varName name of variable in which you want to save text
     */
    //todo: create EC_ variable each time any random value is generated
    @Then("I remember \"([^\"]*)\" text as \"([^\"]*)\" variable$")
    public void i_remember_text(String text, String varName) {
        Reporter.log("Executing step: I remember '" + text + "' text as '" + varName + "' variable");
        ExecutionContextHandler.setExecutionContextValueByKey(varName, TestParametersController.checkIfSpecialParameter(text));
    }

    /**
     * Definition to verify presence of element on the page
     *
     * @author Ruslan Levytskyi
     * @param attributeName name of attribute of element you want to check
     * @param elementLocator name or value of needed element
     * @param attributeValue value that tested attribute should have
     */
    @When("^Attribute \"([^\"]*)\" of \"([^\"]*)\" should have value \"([^\"]*)\"$")
    public void elements_attribute_should_have_value(String attributeName, String elementLocator, String attributeValue) {
        Reporter.log("Executing step: Attribute '" + attributeName + "' of '" + elementLocator + "' should have value '" + attributeValue + "'");
        Assert.assertTrue(findElement(initElementLocator(elementLocator)).getAttribute(attributeName).equalsIgnoreCase(attributeValue));
    }

    /**
     * Definition to check or uncheck the checkbox
     * If svalue is check, but the checkbox is unchecked, than this method checks the checkbox
     * Vice versa.
     *
     * @param value = value to set a statement to the checkbox. Can be "check" or "uncheck"
     * @param element By locator of checkbox
     */
    @When("^I \"(check|uncheck)\" \"([^\"]*)\" checkbox$")
    public void i_check_uncheck(String value, String element){
        Reporter.log("Executing step: I " + value + " '" + element + "' checkbox");
        String processedElement = TestParametersController.checkIfSpecialParameter(element);
        boolean state = true;
        if(value.equals("check")){state = true;}
        else if(value.equals("uncheck")){state = false;}
        checkCheckbox(initElementLocator(element),state);
        if(!element.equals(processedElement)){
            Reporter.log("<pre>[input test parameter] " + element + "' -> '" + processedElement + " [output value]</pre>");
        }
    }

    /**
     * Definition to imitate key press from keyboard
     *
     * @param fieldValue : value to be entered in appropriate field using metadata values
     * @param element: By locator of element to press key
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
     *             Here we also check if text is EC_ or MD_ of KW_
     * @param frame frame name to switch
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
     * @author Andrii Yakymchuk
     * @param element:  By locator of a element
     *
     */
    @Then("I shouldn't see the \"([^\"]*)\" (?:button|message|element|text)$")
    public void i_should_not_see_the_element(String element) {
        Reporter.log("Executing step: I shouldn't see the '" + element + "' element");
        waitForPageToLoad();
            if(checkIfElementNotExist(initElementLocator(element))) {
                Assert.assertTrue(false, "Element with " + element + " text is not displayed");
            } else {
                Assert.assertTrue(true, "Element with " + element + " is displayed, but it shouldn't");
            }
        }

    /**
     * Action to upload a file
     *
     * @param element: locator type to be used to locate the element for uploading a file
     * @param fileName: file that should be uploaded to element on the page
     */
    @Then("I upload \"([^\"]*)\" file to \"([^\"]*)\" element")
    public void i_upload_file_to_element(String fileName, String element) {
        Reporter.log("Executing step: I upload the " + fileName + " to: " + element);
        fileUpload(initElementLocator(element), fileName);
        waitForPageToLoad();
    }

    @Then("^I validate text \"([^\"]*)\" to be displayed for \"([^\"]*)\" element$")
    public void i_validate_text_to_be_displayed_for_element(String text, String element) {
        Reporter.log("Executing step: I validate " + text + " to be displayed for: " + element);
        actualValue = getTextValueFromField(initElementLocator(element));
        String newValue = text;
        if (text.toUpperCase().trim().startsWith("RE=")) {
            newValue = newValue.substring("RE=".length());
            assertThat(actualValue.trim(), matchesPattern(newValue ));
            Reporter.log("<pre>Actual value '" + actualValue + "' matches the pattern " + "'" +newValue +"'</pre>");
        } else if (text.toUpperCase().startsWith("CONTAINS=")) {
            newValue = newValue.substring("CONTAINS=".length());
            if (text.contains("EC")) {
                String executionContextValue = ExecutionContextHandler.getExecutionContextValueByKey(newValue);
                assertThat(actualValue.trim().toLowerCase(), Matchers.containsString(executionContextValue.toLowerCase()));
            } else {
                assertThat(actualValue.trim(), Matchers.containsString(newValue));
                Reporter.log("<pre>Actual value '" + actualValue + "' contains the string " + "'" + newValue + "'</pre>");
            }
        } else if(text.toUpperCase().startsWith("NOT_CONTAINS=")) {
            newValue = newValue.substring("NOT_CONTAINS=".length());
            if (text.contains("EC")) {
                String executionContextValue = ExecutionContextHandler.getExecutionContextValueByKey(newValue);
                assertThat(actualValue.trim(), not(Matchers.containsString(executionContextValue)));
            } else {
                assertThat(actualValue.trim(), not(Matchers.containsString(newValue)));
                Reporter.log("<pre>Actual value '" + actualValue + "' not contains the string " + "'" + newValue + "'</pre>");
            }
        } else if (text.toUpperCase().startsWith("CASE=")) {
            newValue = newValue.substring("CASE=".length());
            assertThat(actualValue.trim(), Matchers.equalTo(newValue));
            Reporter.log("<pre>Actual value '" + actualValue + "' equals to the case sensitive string " + "'" +newValue +"'</pre>");
        } else if (text.toUpperCase().contains("STARTS-WITH=")) {
            newValue = newValue.substring("STARTS-WITH=".length());
            assertThat(actualValue.trim(), Matchers.startsWith(newValue));
            Reporter.log("<pre>Actual value '" + actualValue + "' starts with case sensitive string " + "'" +newValue +"'</pre>");
        }else {
            assertThat(actualValue.trim(), Matchers.equalToIgnoringWhiteSpace(text));
            Reporter.log("<pre>Actual value '" + actualValue + "' equals to the case insensitive string " + "'" +newValue +"'</pre>");
        }
        waitForPageToLoad();
    }

    /**
     * Definition scroll the page to the bottom after page is loaded
     *
     * @author Andrii Yakymchuk
     */
    @And("^I should scroll to the bottom of the page$")
    public void iShouldScrollToBottomOfThePage() {
        Reporter.log("Executing step: I should scroll to bottom of the page");
        waitForPageToLoad();
        scrollToBottomOfPage();
    }
}