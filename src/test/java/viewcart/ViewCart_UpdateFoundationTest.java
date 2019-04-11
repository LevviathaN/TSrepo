package viewcart;

import annotations.TestName;
import entities.ItemEntity;
import enums.ProductTypes;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.FoundationPage;
import pages.HomePage;
import pages.ViewCartPage;
import utils.BaseTest;
import utils.EntitiesFactory;
import utils.FileIO;
import utils.ProductSync;

public class ViewCart_UpdateFoundationTest extends BaseTest {

    @Test
    @TestName(name="Foundation Update test")
    public void viewCart_UpdateFoundationTest() throws Exception {

        //init test entities
        ItemEntity item = EntitiesFactory.getItem( FileIO.getDataFile("Default_Foundation.json") );
        ItemEntity updateditem = EntitiesFactory.getItem( FileIO.getDataFile("Updated_Foundation.json") );

        //init pages
        HomePage home = HomePage.Instance;
        FoundationPage prodPage = FoundationPage.Instance;
        ViewCartPage cartPage = ViewCartPage.Instance;

        //open home page and add Sheets to cart
        home.open();
        ProductSync.check(ProductTypes.FOUNDATION);
        home.header.openMenuByItemName("Tomorrow Platform Bed");
        prodPage
                .selectOption(item.getSize())
                .clickAddToCart();
        ProductSync.uncheck(ProductTypes.FOUNDATION);
        cartPage.clickOnEditProduct(item.getTitle());

        prodPage
                .selectOption(updateditem.getSize())
                .clickUpdateCart();

        Assert.assertTrue(cartPage.itemDisplayedOnViewCartPage(updateditem), "Updated item was not displayed");

    }
}
