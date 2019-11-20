package cucumber.stepdefs;

import cucumber.ReusableRunner;
import cucumber.api.java.en.*;
//import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.testng.Assert;
import ui.pages.BasePage;
import ui.utils.*;
import ui.utils.bpp.ExecutionContextHandler;
import ui.utils.bpp.PropertiesHandler;
import ui.utils.bpp.TestParametersController;

import java.io.IOException;
import java.util.List;
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
        clickOnAnyElement(initElementLocator(element));
        waitForPageToLoad();
    }

    @When("^I click on the \"([^\"]*)\" (?:button|link|option) which is \"([^\"]*)\"$")
    public void i_click_on_the_n_button(String element, String nmb) {
        clickOnElement(By.xpath("//body/descendant::*[text()='" +
                TestParametersController.checkIfSpecialParameter(element) + "'][" + Integer.parseInt(nmb) + "]"));
    }

    /**
     * Definition to send some text into some text input field
     *
     * initElementLocator builds locator, depending on input parameter:
     * 1. Starts with "xpath" or "css" - locator is passed directly into a method
     * 2. Parameter exists in locators document - locator value is returned from document
     * 3. None of above - parameter is treated as text value of element: //*[contains(text(), 'parameter')]
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
        String specialElement = PropertiesHandler.getPropertyByKey(element);
        if(element.startsWith("xpath")|element.startsWith("css"))
            findElement(TestParametersController.initElementByLocator(element))
                    .sendKeys(TestParametersController.checkIfSpecialParameter(text));
        else if(!element.equals(specialElement))
            findElement(TestParametersController.initElementByLocator(specialElement))
                    .sendKeys(TestParametersController.checkIfSpecialParameter(text));
        else
            findElement(By.xpath("//input[@name='" + TestParametersController.checkIfSpecialParameter(element) +
                "']")).sendKeys(TestParametersController.checkIfSpecialParameter(text));
    }

    /**
     * Definition to send some text into some text input field
     *
     * initElementLocator builds locator, depending on input parameter:
     * 1. Starts with "xpath" or "css" - locator is passed directly into a method
     * 2. Parameter exists in locators document - locator value is returned from document
     * 3. None of above - parameter is treated as text value of element: //*[contains(text(), 'parameter')]
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
     * initElementLocator builds locator, depending on input parameter:
     * 1. Starts with "xpath" or "css" - locator is passed directly into a method
     * 2. Parameter exists in locators document - locator value is returned from document
     * 3. None of above - parameter is treated as text value of element: //*[contains(text(), 'parameter')]
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
     * initElementLocator builds locator, depending on input parameter:
     * 1. Starts with "xpath" or "css" - locator is passed directly into a method
     * 2. Parameter exists in locators document - locator value is returned from document
     * 3. None of above - parameter is treated as text value of element: //*[contains(text(), 'parameter')]
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
        System.out.println("Current page is " + driver().getTitle());
        System.out.println("Expected page is " + pageTitle);
        Assert.assertEquals(driver().getTitle(), TestParametersController.checkIfSpecialParameter(pageTitle),
                "Current page is " + TestParametersController.checkIfSpecialParameter(pageTitle));
    }


    @Then("^I execute \"([^\"]*)\" reusable step$")
    public void i_execute_reusable_step(String reusableName) {
        ReusableRunner.executeReusable(TestParametersController.checkIfSpecialParameter(reusableName));
    }

    //todo next three definitions of Reusables soon
    @Then("^I execute \"([^\"]*)\" reusable step without some steps$")
    public void i_execute_reusable_step_without(String reusableName) {
        ReusableRunner.executeReusable(TestParametersController.checkIfSpecialParameter(reusableName));
    }

    @Then("^I execute \"([^\"]*)\" reusable step with some additional steps$")
    public void i_execute_reusable_step_with(String reusableName, Map<Integer, String> steps) {
        ReusableRunner.executeReusableAddSteps(TestParametersController.checkIfSpecialParameter(reusableName), steps);
    }

    @Then("^I execute \"([^\"]*)\" reusable step replacing some steps$")
    public void i_execute_reusable_step_replace(String reusableName) {
        ReusableRunner.executeReusable(TestParametersController.checkIfSpecialParameter(reusableName));
    }

    //todo: create EC_ variable each time any random value is generated
    @Then("^I remember \"([^\"]*)\" text as \"([^\"]*)\" variable$")
    public void i_remember_text(String text, String varName) {
        ExecutionContextHandler.setExecutionContextValueByKey(varName, TestParametersController.checkIfSpecialParameter(text));
    }
}