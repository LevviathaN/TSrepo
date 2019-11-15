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

import java.util.List;
import java.util.Map;

/**
 * Created by Ruslan Levytskyi on 15/3/2019.
 */
public class StepDefinitions extends BasePage {

    ReporterManager reporter = ReporterManager.Instance;

    @Given("^I am on \"([^\"]*)\" URL$")
    public void i_am_on_url(String url) {
        driver().get(TestParametersController.checkIfSpecialParameter(url));
    }

    @When("^I click on the \"([^\"]*)\" (?:button|link|option|element)(?: in [^\"]*)?$")
    public void i_click_on_the_button(String element) {
        String specialElement = PropertiesHandler.getPropertyByKey(element);
        if(element.startsWith("xpath")|element.startsWith("css"))
            clickOnElement(TestParametersController.initElementByLocator(element));
        else if(!element.equals(specialElement))
            clickOnElement(TestParametersController.initElementByLocator(specialElement));
        else
            clickOnAnyElement(byText(TestParametersController.checkIfSpecialParameter(element)));
    }

    @When("^I click on the \"([^\"]*)\" (?:button|link|option) which is \"([^\"]*)\"$")
    public void i_click_on_the_n_button(String element, String nmb) {
        clickOnElement(By.xpath("//body/descendant::*[text()='" +
                TestParametersController.checkIfSpecialParameter(element) + "'][" + Integer.parseInt(nmb) + "]"));
    }

    @When("^I click on the element by locator \"([^\"]*)\"$")
    public void i_click_on_the_element_by_locator(String element) {
       if(element.startsWith("xpath")|element.startsWith("css"))
        clickOnElement(TestParametersController.initElementByLocator(element));
       else clickOnElement(TestParametersController.initElementByLocator(PropertiesHandler.getPropertyByKey(element)));
    }

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

    @When("^I wait for \"([^\"]*)\" seconds$")
    public void wait_for(String seconds) {
        sleepFor(Integer.parseInt(TestParametersController.checkIfSpecialParameter(seconds))*1000);
    }

    @When("^I hover over the \"([^\"]*)\" (?:button|link|option|element)$")
    public void hover_over(String element) {
        hoverItem(byText(TestParametersController.checkIfSpecialParameter(element)));
    }

    @Then("^I should see the \"([^\"]*)\" (?:button|message|element)$")
    public void i_should_see_the_text(String element) {
        System.out.println("I should see: " + TestParametersController.checkIfSpecialParameter(element));
        boolean isDisplayed = false;
        for(int i = 0; i<findElements(byText(TestParametersController.checkIfSpecialParameter(element))).size(); i++){
            if(findElements(byText(TestParametersController.checkIfSpecialParameter(element))).get(i).isDisplayed()){
                isDisplayed = true;
            }
        }
        Assert.assertTrue(isDisplayed, "Element with " + element + " text is not displayed");
    }

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

    @Then("^I test \"([^\"]*)\" parametric reusable$")
    public void i_test_parametric_reusable(String text, List<List<String>> data) {
        ReporterManager.info("Executing " + text + " reusable");
        for(List arg : data){
            ReporterManager.info("Step");
            for(Object step : arg){
                ReporterManager.info("Substep: " + step);
            }
        }
    }
}