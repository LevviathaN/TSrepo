package cucumber.stepdefs;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import pages.BasePage;

/**
 * Created by Ruslan Levytskyi on 15/3/2019.
 */
public class StepDefinitions extends BasePage {

    @Given("^I am on the Home Page$")
    public void i_am_on_the_home_page() { open(); }

    @Given("^I am on the \"([^\"]*)\" page on URL \"([^\"]*)\"$")
    public void i_am_on_the_page_on_URL(String title, String url) {
        openUrl(url);
    }

    @When("^I click on the \"([^\"]*)\" [^\"]*$")
    public void i_click_on_the_button(String button) { clickOnElement(By.xpath("//*[text()='" + button + "']")); }

    @Then("^I should see the \"([^\"]*)\" [^\"]*$")
    public void i_should_see_the_button(String button) { findElement(By.xpath("//*[text()='" + button + "']")); }
}