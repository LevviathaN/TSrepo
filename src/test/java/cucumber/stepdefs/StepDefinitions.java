package cucumber.stepdefs;

import cucumber.reusablesteps.ReusableRunner;
import cucumber.api.java.en.*;
//import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.testng.Assert;
import ui.pages.BasePage;
import ui.utils.*;
import ui.utils.bpp.ExecutionContextHandler;
import ui.utils.bpp.TestParametersController;

import java.util.Map;

/**
 * Created by Ruslan Levytskyi on 15/3/2019.
 */
public class StepDefinitions extends BasePage {

    ReporterManager reporter = ReporterManager.Instance;

    /**
     * Definition to go to specified url.
     * Also checks if the parameter is Meta Data or Execution Context value
     *
     * @author Ruslan Levytskyi
     * @param url url where you want navigate to
     */
    @Given("^I am on \"([^\"]*)\" URL$")
    public void i_am_on_url(String url) {
        driver().get(TestParametersController.checkIfSpecialParameter(url));
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
        clickOnFirstVisibleElement(initElementLocator(element));
        waitForPageToLoad();
    }

    /**
     * Definition to click an element which is N of elements found on the page using specified locator
     *
     * @author Ruslan Levytskyi
     * @param element locator for element you want to click on

     */
    @When("^I click on the \"([^\"]*)\" (?:button|link|option) which is \"([^\"]*)\"$")
    public void i_click_on_the_n_button(String element, String nmb) {
        clickOnElement(By.xpath("//body/descendant::*[text()='" +
                TestParametersController.checkIfSpecialParameter(element) + "'][" + Integer.parseInt(nmb) + "]"));
    }

    /**
     * Definition to send some text into some text input field
     *
     * @author Ruslan Levytskyi
     * @param element locator for element you want to send text to
     *      1. Starts with "xpath" or "css" - locator is passed directly into a method
     *      2. Parameter exists in locators document - locator value is returned from document
     *      3. None of above - parameter is treated as text value of element: //input[@name='parameter')]
     * @param text text you want to send to element
     *             Here we also check if text is EC_ or MD_ of KW_
     */
    @When("^I fill the \"([^\"]*)\" field with \"([^\"]*)\"$")
    public void fill_field(String element, String text){

        if(element.startsWith("xpath")|element.startsWith("css"))
            findElement(TestParametersController.initElementByLocator(element))
                    .sendKeys(TestParametersController.checkIfSpecialParameter(text));
        else
            findElement(By.xpath("//input[@name='" + TestParametersController.checkIfSpecialParameter(element) +
                "']")).sendKeys(TestParametersController.checkIfSpecialParameter(text));
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
        reporter.info("Current page is " + driver().getTitle());
        System.out.println("Expected page is " + pageTitle);
        Assert.assertEquals(driver().getTitle(), TestParametersController.checkIfSpecialParameter(pageTitle),
                "Current page is " + TestParametersController.checkIfSpecialParameter(pageTitle));
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
        ReusableRunner.executeReusable(TestParametersController.checkIfSpecialParameter(reusableName));
        this.value = TestParametersController.checkIfSpecialParameter(reusableName);
        reporter.info("[input test parameter] '" + reusableName + "' -> '" + this.value + "' [output value]");
    }

    @Then("^I execute \"([^\"]*)\" reusable step with some additional steps$")
    public void i_execute_reusable_step_with(String reusableName, Map<Integer, String> steps) {
        ReusableRunner.executeReusableAddSteps(TestParametersController.checkIfSpecialParameter(reusableName), steps);
        this.value = TestParametersController.checkIfSpecialParameter(reusableName);
        reporter.info("[input test parameter] '" + reusableName + "' -> '" + this.value + "' [output value]");
    }


    /**
     * Definition to execute reusable steps
     *
     * @author Ruslan Levytskyi
     * @param text text you want to save into a variable
     * @param varName name of variable in which you want to save text
     */
    //todo: create EC_ variable each time any random value is generated
    @Then("^I remember \"([^\"]*)\" text as \"([^\"]*)\" variable$")
    public void i_remember_text(String text, String varName) {
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
        boolean state = true;
        if(value.equals("check")){state = true;}
        else if(value.equals("uncheck")){state = false;}
        checkCheckbox(initElementLocator(element),state);
    }
}