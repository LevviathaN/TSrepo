package viewcart;

import annotations.TestName;
import entities.ItemEntity;
import enums.ProductTypes;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.*;
import utils.BaseTest;
import utils.EntitiesFactory;
import utils.FileIO;
import utils.ProductSync;

public class ViewCart_ChangeQuantityTest extends BaseTest {
    @DataProvider(name = "default_item_provider", parallel = true)
    public Object[][] provider () {
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


    @Test(dataProvider="default_item_provider")
    @TestName(name = "Change item quantity")
    public void viewCart_ChangeQuantityTest(ProductTypes type, Class page, String itemEntity) throws Exception {

        int qty = 1;

        //init test entities
        ItemEntity item = EntitiesFactory.getItem( FileIO.getDataFile("Default_" + itemEntity +".json") );

        //init pages
        HomePage home = HomePage.Instance;
        ViewCartPage viewcart = ViewCartPage.Instance;
        BaseProductPage product = (BaseProductPage) page.getConstructor().newInstance();

        //test steps
        home.open();
        ProductSync.check(type);
        home.header.openMenuByItemName(type.getValue());

        Assert.assertTrue(product.isPageLoaded(), "Page was not opened: " + product.getURL());

        product.isProductInStock()
                .selectOption(item.getSize())
                .selectOption(item.getType())
                .clickAddToCart();
        ProductSync.uncheck(type);

        // check item in viewcart
        Assert.assertTrue(viewcart.itemDisplayedOnViewCartPage(type.toString(), qty),  "Item was not displayed in cart");

        viewcart.addQuantity(type.toString());
        qty++;

        Assert.assertTrue(viewcart.itemDisplayedOnViewCartPage(type.toString(), qty),  "Item was displayed in cart with correct quanity");

        viewcart.subQuantity(type.toString());
        qty--;

        Assert.assertTrue(viewcart.itemDisplayedOnViewCartPage(type.toString(), qty),  "Item was displayed in cart with correct quanity");
    }
}
