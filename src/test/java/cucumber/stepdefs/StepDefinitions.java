package cucumber.stepdefs;

import cucumber.api.java.en.*;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.BasePage;
import utils.*;

/**
 * Created by Ruslan Levytskyi on 15/3/2019.
 */
public class StepDefinitions extends BasePage {

    ReporterManager reporter = ReporterManager.Instance;

    @Given("^I am on the (Home|Cart|Shop|Magazine|Reviews|FAQ) Page$")
    public void i_am_on_the_home_page(String page) {
        switch(page){
            case "Home":
                driver().get("https://www.tomorrowsleep.com/");
                break;
            case "Cart":
                driver().get("https://www.tomorrowsleep.com/checkout/cart/");
                break;
            case "Shop":
                driver().get("https://www.tomorrowsleep.com/shop");
                break;
            case "Magazine":
                driver().get("https://www.tomorrowsleep.com/magazine/");
                break;
            case "Reviews":
                driver().get("https://www.tomorrowsleep.com/review/");
                break;
            case "FAQ":
                driver().get("https://www.tomorrowsleep.com/faq/");
                break;
            default:
                reporter.fail("No such page");
        }
    }

//    @Given("^I am on the \"([^\"]*)\" PDP$")
//    public void i_am_on_the_pdp(String title) {
//        open();
//        PageHeader.Instance.openMenuByItemName(title);
//    }

    @When("^I click on the \"([^\"]*)\" (?:button|link|option)(?: in [^\"]*)?$")
    public void i_click_on_the_button(String button) {
        clickOnAnyElement(By.xpath("//*[text()='" + button + "']"));
    }

    @When("^I wait for ([^\"]*) seconds$")
    public void wait_for(String seconds) {
        sleepFor(Integer.parseInt(seconds)*1000);
    }

    @Then("^I should see the \"([^\"]*)\" (?:button|message|element)$")
    public void i_should_see_the_text(String element) {
        boolean isDisplayed = false;
        for(int i=0; i<findElements(By.xpath("//*[text()='" + element + "']")).size(); i++){
            if(findElements(By.xpath("//*[text()='" + element + "']")).get(i).isDisplayed()){
                isDisplayed = true;
            }
        }
        Assert.assertTrue(isDisplayed, "Element with " + element + " text is not displayed");
    }

    @Then("^I should be redirected to the \"([^\"]*)\" page$")
    public void i_should_be_redirected_to_page(String page) {
        System.out.println("Current page is " + driver().getTitle());
        System.out.println("Expected page is " + page);
        Assert.assertEquals(driver().getTitle(), page, "Current page is " + page);
    }
}