package smoke;

import annotations.TestName;
import entities.ItemEntity;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.CheckoutPage;
import pages.MattressesPage;
import pages.ViewCartPage;
import utils.BaseTest;
import utils.EntitiesFactory;
import utils.FileIO;

public class DirectMatress_Test extends BaseTest{
    @DataProvider(name = "matress")
    public Object[][] provider(){
        return new Object[][]{
                {"Medium Soft", "Queen", 990},
                {"Medium Soft", "Full", 850},
                {"Medium Soft", "King", 1200},
                {"Medium Soft", "Twin", 595},
                {"Medium Soft", "Twin XL", 645},
                {"Medium Soft", "Cal. King", 1250},
                {"Medium Firm", "Queen", 990},
                {"Medium Firm", "Full", 850},
                {"Medium Firm", "King", 1200},
                {"Medium Firm", "Twin", 595},
                {"Medium Firm", "Twin XL", 645},
                {"Medium Firm", "Cal. King", 1250}
        };
    }

    @Test (dataProvider = "matress")
    @TestName (name = "Direct Mattress Order Test")
    public void DirectMattressOrder_test(String feel, String size, int price) throws Exception {

        MattressesPage mattress = MattressesPage.Instance;
        ViewCartPage cart = ViewCartPage.Instance;
        CheckoutPage checkout = CheckoutPage.Instance;

        ItemEntity item = EntitiesFactory.getItem( FileIO.getDataFile("Default_Mattress.json") );
        item.setSize(size);
        item.setType(feel);
        item.setPrice(price);

        mattress.open();
        mattress.selectMattressSize(size);
        mattress.selectMattressFeel(feel);
        mattress.clickAddToCart();

        Assert.assertTrue(cart.itemDisplayedOnViewCartPage(item), "Selected mattress was not found on the cart page");

        cart.clickOnProceedToChechout();

        Assert.assertTrue(checkout.itemDisplayedOnCheckoutPage(item), "Selected mattress was not found on the checkout page");
    }

}
