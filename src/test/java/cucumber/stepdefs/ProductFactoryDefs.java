package cucumber.stepdefs;

import cucumber.api.java.en.*;
//import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.testng.Assert;
import utils.*;
import pages.bpp.product_factory.ProductFactoryBasePage;

/**
 * Created by Ruslan Levytskyi on 21/10/2019.
 */

public class ProductFactoryDefs extends ProductFactoryBasePage {

    @Given("^I am logged into Product Factory as \"([^\"]*)\"$")
    public void log_in_as(String role){
        logInAs(role);
    }

    @When("^I fill the \"([^\"]*)\" PF field with \"([^\"]*)\"$")
    public void fill_pf_field(String field, String text){
        findElement(pfField(field)).sendKeys(text);
    }
}
