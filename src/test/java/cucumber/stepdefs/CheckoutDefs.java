package cucumber.stepdefs;

import cucumber.api.java.en.*;
import entities.ItemEntity;
import entities.UserEntity;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.*;
import utils.*;

import java.util.ArrayList;

/**
 * Created by Ruslan Levytskyi on 15/3/2019.
 */

public class CheckoutDefs extends BasePage {
    CheckoutPage checkout = CheckoutPage.Instance;
    ViewCartPage cart = ViewCartPage.Instance;
    BaseProductPage product = new BaseProductPage();

    @Given("^There is \"([^\"]*)\" of size \"([^\"]*)\" added to Shopping Cart$")
    public void specific_items_added_to_cart(String item, String option) {
        open();
        PageHeader.Instance.openMenuByItemName(item);
        product.selectOption(option).clickAddToCart();
    }

    @Then("^I fill all required fields$")
    public void fill_shipping_fields() throws Exception {
        UserEntity user = EntitiesFactory.getUser( FileIO.getDataFile("Default_User.json"));
        checkout.populateAllCheckoutFields(user);
    }

    @Then("^I should see \"([^\"]*)\" in the Cart$")
    public void i_should_see_product_in_cart(String productTitle) {
        Assert.assertTrue(findElement(
                By.xpath("//tbody[@class='cart item']/*/*/*/*/a[text()='" + productTitle + "']")).isDisplayed(),
                productTitle + " is found in the Shopping Cart");
    }

    @Then("^I should see \"([^\"]*)\" in Mini Cart$")
    public void i_should_see_product_in_mini_cart(String productTitle) {
        Assert.assertTrue(findElement(
                By.xpath("//li[@class='item product product-item']/*/*/*/a[text()='" + productTitle + "']")).isDisplayed(),
                productTitle + " is found in the Shopping Cart");
    }

    @Then("^(Quantity|Price|Size|) of \"([^\"]*)\" in the Cart should be equal to \"([^\"]*)\"$")
    public void i_should_see_property_of_product(String prop, String product, String value) {
        ArrayList<ItemEntity> items = cart.getAllViewCartPageItems();
        boolean isPresent = false;
        switch(prop){
            case "Quantity":
                isPresent = items.stream()
                        .filter(cur -> product.equals(cur.getTitle()))
                        .filter(cur -> Integer.parseInt(value) == cur.getQty()).count() > 0;
                break;
            case "Price":
                isPresent = items.stream()
                        .filter(cur -> product.equals(cur.getTitle()))
                        .filter(cur -> Tools.convertStringPriceToFloat(value) == cur.getPrice()).count() > 0;
                break;
            case "Size":
                isPresent = items.stream()
                        .filter(cur -> product.equals(cur.getTitle()))
                        .filter(cur -> value.equals(cur.getSize())).count() > 0;
                break;
        }
        Assert.assertTrue(isPresent,"no products with such attributes");
    }

    @When("^I (increase|decrease) quantity of \"([^\"]*)\"$")
    public void change_qty_in_cart(String action, String product) {
        if(action.equals("increase")){ cart.addQuantity(product); }
        else{ cart.subQuantity(product); }
    }

    @When("^I empty Shopping Cart$")
    public void empty_shopping_cart(){
        while(isElementPresent(By.xpath("//td/a[@title='Remove item']"))){
            findElement(By.xpath("//td/a[@title='Remove item']")).click();
        }
    }
}
