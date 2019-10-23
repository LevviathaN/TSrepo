package cucumber.stepdefs;

import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import pages.BasePage;
import utils.ReporterManager;

public class KdtStepdefs extends BasePage {

    ReporterManager reporter = ReporterManager.Instance;

    @Given("^I am on \"([^\"]*)\" BPP URL$")
    public void i_am_on_url(String url) {
        driver().get(url);
    }

    @When("^I click on the BPP element located by \"([^\"]*)\"$")
    public void i_click_on_the_button(String element) {

        clickOnAnyElement(byText(element));
    }

    @When("^I fill the \"([^\"]*)\" BPP field with \"([^\"]*)\"$")
    public void fill_field(String field, String text){
        findElement(By.xpath(field)).sendKeys(text);
    }

    @Then("^I capture data from \"([^\"]*)\" BPP element into \"([^\"]*)\" variable$")
    public void capture_data(String field, String text){
        findElement(By.xpath(field)).sendKeys(text);
    }

    @When("^I wait for \"([^\"]*)\" seconds at BPP page$")
    public void wait_for(String seconds) {
        sleepFor(Integer.parseInt(seconds)*1000);
    }

    @Then("^I verify that element located by \"([^\"]*)\" is not visible on BPP page$")
    public void i_verify_elements_absense(String element) {
        isElementPresentAndDisplay(byText(element));
    }

    @Then("^I verify that BPP element \"([^\"]*)\" contains text \"([^\"]*)\"$")
    public void i_verify_text(String element) {
        isElementPresentAndDisplay(byText(element));
    }

    @When("^I SmartClient click on BPP element \"([^\"]*)\"$")
    public void i_smart_click(String element) {
        clickOnAnyElement(byText(element));
    }

    @When("^I confirm BPP JS Alert \"([^\"]*)\"$")
    public void i_confirm_js_alert(String element) {
        clickOnAnyElement(byText(element));
    }

    @When("^I press the \"([^\"]*)\" key on the keyboard for BPP$")
    public void i_press_key(String element) {
        clickOnAnyElement(byText(element));
    }

    @When("^I execute \"([^\"]*)\" JS script for BPP$")
    public void i_execute_js(String element) {
        clickOnAnyElement(byText(element));
    }

    @When("^I validate that BPP element \"([^\"]*)\" has \"([^\"]*)\"$")
    public void i_validate_element_attribute(String element,String attributeNameValue) { //class=submit etc.
        clickOnAnyElement(byText(element));
    }

    @When("^I switch \"([^\"]*)\" BPP checkbox to \"([^\"]*)\"$")
    public void i_check_checkbox(String element,String value) {
        clickOnAnyElement(byText(element));
    }

    @When("^I select \"([^\"]*)\" from BPP dropdown \"([^\"]*)\"$")
    public void i_select_from_dropdown(String element,String value) {
        clickOnAnyElement(byText(element));
    }

    @When("^I switch to \"([^\"]*)\" BPP window$")
    public void i_switch_to_window(String value) {
        clickOnAnyElement(byText(value));
    }
}
