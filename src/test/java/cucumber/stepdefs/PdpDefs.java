package cucumber.stepdefs;

import cucumber.api.java.en.*;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.*;

/**
 * Created by Ruslan Levytskyi on 15/3/2019.
 */

public class PdpDefs extends BasePage {

    @When("^I click on \"View Cart\" button in Cross Sell modal$|^If Cross Sell popup appears I click on the \"View Cart\" button$")
    public void click_on_cross_sell_view_cart(){
        if (isElementPresentAndDisplay(By.cssSelector(".xs-modal"))){
            clickOnElement(By.xpath("//a[@class='__to-checkout button']"));
        }
    }

    @Then("^I add \"([^\"]*)\" to cart from Cross Sell popup$")
    public void add_cross_sell_product_to_cart(String productName){
        switch (productName){
            case "Tomorrow Cooling Memory Foam Pillow":
                findElements(By.xpath("//button[@class='action tocart cross-sell button']")).get(0).click();
                break;
            case "Tomorrow Waterproof Mattress Protector":
                findElements(By.xpath("//button[@class='action tocart cross-sell button']")).get(1).click();
                break;
            case "Tomorrow Platform Bed":
                findElements(By.xpath("//button[@class='action tocart cross-sell button']")).get(2).click();
                break;
        }
    }


    @Then("^price should be equal to \"([^\"]*)\"$")
    public void price_should_be_equal(String price){
        Assert.assertEquals(findElement(By.xpath("//span[@data-price-type='finalPrice']")).getText(),price,"Price dismatch");
    }

}
