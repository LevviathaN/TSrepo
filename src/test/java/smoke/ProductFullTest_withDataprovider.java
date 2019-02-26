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
    @DataProvider(name = "default_item_provider", parallel = true)
    public Object[][] provider (){
        return new Object[][]{
                {ProductTypes.PLUSH_PILLOW, PlushPillowPage.class, "PlushPillow"},
                {ProductTypes.FOAM_PILLOW,  FoamPillowPage.class, "FoamPillow"},
                {ProductTypes.MONITOR, MonitorPage.class, "Monitor"},
                {ProductTypes.MATTRESS, MattressesPage.class, "Mattress"},
                {ProductTypes.MATTRESS_PROTECTOR, MattressProtectorPage.class, "Protector"},
                {ProductTypes.COMFORTER,  ComforterPage.class, "Comforter"},
                {ProductTypes.DRAPES, DrapesPage.class, "Drapes"},
                {ProductTypes.SHEETSET, SheetsetPage.class, "Sheets"},
                {ProductTypes.ADJUSTABLE_BASE, AdjustablePage.class, "Adjustable"},
                {ProductTypes.FOUNDATION, FoundationPage.class, "Foundation"}
        };
    }

    @Test (dataProvider = "default_item_provider")
    @TestName (name="Product Full Flow Test")
    public void productFullTest(ProductTypes type, Class page, String itemEntity) throws Exception {

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
        header.openMenuByItemName(type.getValue());
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
