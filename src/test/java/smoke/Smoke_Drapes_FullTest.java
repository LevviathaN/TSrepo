package smoke;

import annotations.TestName;
import entities.ItemEntity;
import entities.UserEntity;
import enums.ProductTypes;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
import utils.BaseTest;
import utils.EntitiesFactory;
import utils.FileIO;
import utils.ProductSync;

public class Smoke_Drapes_FullTest extends BaseTest {

    @Test(retryAnalyzer = utils.RetryAnalyzer.class)
    @TestName(name="Drapes Workflow test")
    public void smoke_Drapes_FullTest() throws Exception {

        //init test entities
        ItemEntity item = EntitiesFactory.getItem( FileIO.getDataFile("Default_Drapes.json") );
        UserEntity user = EntitiesFactory.getUser( FileIO.getDataFile("Default_User.json")); // get user data from file

        //init pages
        HomePage home = HomePage.Instance;
        ViewCartPage cart = ViewCartPage.Instance;
        CheckoutPage checkout = CheckoutPage.Instance;
        CheckoutReviewPage review = CheckoutReviewPage.Instance;

        //open home page and add Drapes to cart
        home.open();
        ProductSync.check(ProductTypes.DRAPES);
        home.header.clickShopMenuItem()
                .clickOnShopOurDrapesButton()
                .selectOption(item.getSize())
                .selectOption(item.getType())
                .clickAddToCart();
        home.header.clickShopMenuItem();

        ProductSync.uncheck(ProductTypes.DRAPES);

        // check item in cart
        Assert.assertTrue(home.header.itemWasFoundInMiniCart(item),  "Item was not displayed in mini-cart");

        //check item displayed in order
        home.header.clickOnViewCartButton();
        Assert.assertTrue(cart.itemDisplayedOnViewCartPage(item), "Item was not displayed in cart");
        cart.clickOnProceedToChechout();


        //set all user related fields
        checkout.populateAllCheckoutFields(user);

        //check Order Review page was opened
        Assert.assertTrue(review.isPaymentMethodTitleDisplayed(),"Payment page was not displayed");

        //check item in final order
         Assert.assertTrue(review.itemWasFoundInOrder(item), "Item was not displayed on final page");

    }
}
