package viewcart;

import annotations.TestName;
import entities.ItemEntity;
import entities.UserEntity;
import enums.ProductTypes;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.*;
import utils.BaseTest;
import utils.EntitiesFactory;
import utils.FileIO;
import utils.ProductSync;

public class ViewCart_DeleteTest extends BaseTest {
    @DataProvider(name = "default_item_provider", parallel = true)
    public Object[][] provider () {
        return new Object[][]{
                {ProductTypes.PLUSH_PILLOW, PlushPillowPage.class, "Tomorrow Hypoallergenic Plush Pillow", "Default_PlushPillow.json"},
                {ProductTypes.FOAM_PILLOW,  FoamPillowPage.class, "Tomorrow Cooling Memory Foam Pillow", "Default_FoamPillow.json"},
                {ProductTypes.MONITOR, MonitorPage.class, "Tomorrow Sleeptracker® Monitor", "Default_Monitor.json"},
                {ProductTypes.MATTRESS, MattressesPage.class, "Tomorrow Hybrid Mattress", "Default_Mattress.json"},
                {ProductTypes.MATTRESS_PROTECTOR, MattressProtectorPage.class, "Tomorrow Waterproof Mattress Protector", "Default_Protector.json"},
                {ProductTypes.COMFORTER,  ComforterPage.class, "Tomorrow White Comforter", "Default_Comforter.json"},
                {ProductTypes.DRAPES, DrapesPage.class, "Tomorrow Blackout Curtains", "Default_Drapes.json"},
                {ProductTypes.SHEETSET, SheetsetPage.class, "Tomorrow White Sheet Set", "Default_Sheets.json"},
                {ProductTypes.ADJUSTABLE_BASE, AdjustablePage.class, "Tomorrow Adjustable Bed", "Default_Adjustable.json"},
                {ProductTypes.FOUNDATION, FoundationPage.class, "Tomorrow Platform Bed", "Default_Foundation.json"}

        };
    }


    @Test(dataProvider="default_item_provider")
    @TestName(name = "Delete Item from Cart")
    public void viewCart_Delete_Test(ProductTypes type, Class page, String itemMenuName, String itemEntity) throws Exception {

        //init test entities
        ItemEntity item = EntitiesFactory.getItem( FileIO.getDataFile(itemEntity) );

        //init pages
        HomePage home = HomePage.Instance;
        ViewCartPage viewcart = ViewCartPage.Instance;
        BaseProductPage product = (BaseProductPage) page.getConstructor().newInstance();

        home.open();
        ProductSync.check(type);
        home.header.openMenuByItemName(itemMenuName);

        Assert.assertTrue(product.isPageLoaded(), "Page was not opened: " + product.getURL());

        product.isProductInStock()
                .selectOption(item.getSize())
                .selectOption(item.getType())
                .clickAddToCart();

        // check item in viewcart
        Assert.assertTrue(viewcart.itemDisplayedOnViewCartPage(type.toString()),  "Item was not displayed in cart");

        viewcart.clickOnDeleteProduct(type.toString());

        ProductSync.uncheck(type);

        Assert.assertTrue(!viewcart.itemDisplayedOnViewCartPage(type.toString()),  "Item was displayed in cart");

    }
}
