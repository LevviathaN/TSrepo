package smoke;

import annotations.TestName;
import enums.ProductTypes;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.*;
import utils.BaseTest;
import utils.ProductSync;

public class Smoke_WhiteGloveDeliveryTest extends BaseTest {
    @DataProvider(name = "default_item_provider")
    public Object[][] provider() {
        return new Object[][]{
                {ProductTypes.MATTRESS, MattressesPage.class, "Tomorrow Hybrid Mattress"},
                {ProductTypes.ADJUSTABLE_BASE, AdjustablePage.class, "Tomorrow Adjustable Bed"}
        };
    }

    @Test(dataProvider = "default_item_provider")
    @TestName(name = "White Glove validation")
    public void whiteGloveValidation (ProductTypes type, Class page, String itemName) throws Exception {

        HomePage home = HomePage.Instance;
        CheckoutPage checkout = CheckoutPage.Instance;
        ViewCartPage cart = ViewCartPage.Instance;
        BaseProductPage bp = (BaseProductPage) page.getConstructor().newInstance();

        home.open();
        ProductSync.check(type);
        home.header.openMenuByItemName(itemName);

        bp.clickAddToCart();
        ProductSync.uncheck(type);
        cart.clickOnProceedToChechout();

        //Set valid address
        checkout.populateSpippingInfo("New Jersey", "07064");

        //Verify White Glove availability
        checkout.selectWhiteGloveSpipping();
        //Assert.assertTrue(checkout.getShippingPrice().equals("$75.00"), "White Glove price is not added");

        //Verify Old Mattress Removal availability
        checkout.selectOldMattressRemoval();
        //Assert.assertTrue(checkout.getShippingPrice().equals("$124.00"), "Old Mattress Removal price is not added");

        //Verify correct delivery date selected
        checkout.selectDeliveryDate();
        //Assert.assertTrue(checkout.firstAvailableDay().equals(checkout.selectedDate()), "Failed to select delivery date");

        //Verify WG price subtracted and free shipping prise equals 0$
        checkout.selectFreeShipping();
        //Assert.assertTrue(checkout.getShippingPrice().equals("$0.00"), "White Glove price is not subtracted"); //todo - remove assertion comments

    }
}
