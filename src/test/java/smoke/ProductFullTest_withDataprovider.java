package smoke;

import annotations.TestName;
import entities.ItemEntity;
import entities.UserEntity;
import enums.ProductTypes;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.*;
import utils.BaseTest;
import utils.EntitiesFactory;
import utils.FileIO;
import utils.ProductSync;

/**
 * @author Ruslan Levytskyi
 * @since 2/25/2019.
 */

public class ProductFullTest_withDataprovider extends BaseTest {
    @DataProvider(name = "default_item_provider", parallel = Parallel)
    public Object[][] provider (){
        return new Object[][]{
                {ProductTypes.PLUSH_PILLOW, PlushPillowPage.class, "Tomorrow Hypoallergenic Plush Pillow", "PlushPillow"},
                {ProductTypes.FOAM_PILLOW,  FoamPillowPage.class, "Tomorrow Cooling Memory Foam Pillow", "FoamPillow"},
                {ProductTypes.MONITOR, MonitorPage.class, "Tomorrow Sleeptracker® Monitor", "Monitor"},
                {ProductTypes.MATTRESS, MattressesPage.class, "Tomorrow Hybrid Mattress", "Mattress" },
                {ProductTypes.MATTRESS_PROTECTOR, MattressProtectorPage.class, "Tomorrow Waterproof Mattress Protector", "Protector" },
                {ProductTypes.COMFORTER,  ComforterPage.class, "Tomorrow White Comforter", "Comforter"},
                {ProductTypes.DRAPES, DrapesPage.class, "Tomorrow Blackout Curtains", "Drapes"},
                {ProductTypes.SHEETSET, SheetsetPage.class, "Tomorrow White Sheet Set", "Sheets"},
                {ProductTypes.ADJUSTABLE_BASE, AdjustablePage.class, "Tomorrow Adjustable Bed", "Adjustable"},
                {ProductTypes.FOUNDATION, FoundationPage.class, "Tomorrow Platform Bed", "Foundation"}
        };
    }

    @Test (dataProvider = "default_item_provider")
    @TestName (name="Product Full Flow Test")
    public void productFullTest(ProductTypes type, Class page, String itemName, String itemEntity) throws Exception {

        //init test entities
        ItemEntity item = EntitiesFactory.getItem( FileIO.getDataFile("Default_" + itemEntity +".json") );
        UserEntity user = EntitiesFactory.getUser( FileIO.getDataFile("Default_User.json")); // get user data from file

        //init pages
        PageHeader header = PageHeader.Instance;
        HomePage home = HomePage.Instance;
        ViewCartPage cart = ViewCartPage.Instance;
        CheckoutPage checkout = CheckoutPage.Instance;
        CheckoutReviewPage review = CheckoutReviewPage.Instance;
        BaseProductPage product = (BaseProductPage) page.getConstructor().newInstance();

        home.open();
        ProductSync.check(type);
        header.openMenuByItemName(itemName);
        //Assert.assertTrue(product.isPageLoaded(), "Page was not opened: " + product.getURL());
        product.isProductInStock()
                .selectOption(item.getSize())
                .selectOption(item.getType())
                .clickAddToCart();
        ProductSync.uncheck(type);

        Assert.assertTrue(header.itemWasFoundInMiniCart(item),  "Item was not displayed in mini-cart");
        Assert.assertTrue(cart.itemDisplayedOnViewCartPage(item), "Item was not displayed in cart");
        cart.clickOnProceedToChechout();
        checkout.populateAllCheckoutFields(user);
        Assert.assertTrue(review.isPaymentMethodTitleDisplayed(),"Payment page was not displayed");
        Assert.assertTrue(review.itemWasFoundInOrder(item), "Item was not displayed on final page");
    }
}
