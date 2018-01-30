package viewcart;

import annotations.TestName;
import entities.ItemEntity;
import enums.ProductTypes;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AdjustablePage;
import pages.HomePage;
import pages.ViewCartPage;
import utils.BaseTest;
import utils.EntitiesFactory;
import utils.FileIO;
import utils.ProductSync;

public class ViewCart_UpdateAdjustableBaseTest extends BaseTest {

    @Test
    @TestName(name="Adjustable Base Update test")
    public void viewCart_UpdateAdjustableBaseTest() throws Exception {

        //init test entities
        ItemEntity item = EntitiesFactory.getItem( FileIO.getDataFile("Default_Adjustable.json") );
        ItemEntity updateditem = EntitiesFactory.getItem( FileIO.getDataFile("Updated_Adjustable.json") );

        //init pages
        HomePage home = HomePage.Instance;
        AdjustablePage prodPage = AdjustablePage.Instance;
        ViewCartPage cartPage = ViewCartPage.Instance;

        //open home page and add Sheets to cart
        home.open();
        ProductSync.check(ProductTypes.ADJUSTABLE_BASE);
        home.header.clickShopMenuItem()
                .clickOnShopOurBaseButton()
                .selectBaseSize(item.getSize())
                .clickAddToCart();
        ProductSync.uncheck(ProductTypes.ADJUSTABLE_BASE);
        cartPage.clickOnEditProduct(item.getTitle());

        prodPage
                .selectBaseSize(updateditem.getSize())
                .clickUpdateCart();

        Assert.assertTrue(cartPage.itemDisplayedOnViewCartPage(updateditem), "Updated item was not displayed");

    }
}
