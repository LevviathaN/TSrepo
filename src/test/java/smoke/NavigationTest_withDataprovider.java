package smoke;

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

public class NavigationTest_withDataprovider extends BaseTest {
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
    @TestName (name="Navigation validation")
    public void topMenuValidation(ProductTypes type, Class page, String itemName, String itemEntity) throws Exception {

        //init test entities
        ItemEntity item = EntitiesFactory.getItem( FileIO.getDataFile("Default_" + itemEntity +".json") );

        //init pages
        HomePage home = HomePage.Instance;
        ViewCartPage cart = ViewCartPage.Instance;
        BaseProductPage product = (BaseProductPage) page.getConstructor().newInstance();

        //test steps
        home.open();
        ProductSync.check(type);
        home.header.openMenuByItemName(itemName);
        Assert.assertTrue(product.isPageLoaded(), "Page was not opened: " + product.getURL());
        product.isProductInStock()
                .selectOption(item.getSize())
                .selectOption(item.getType())
                .clickAddToCart();
        ProductSync.uncheck(type);
        //check, if user can navigate to pdp by clicking on it in cart page
        cart.clickOnProduct(type.toString());
        Assert.assertTrue(product.isPageLoaded(), "Page was not opened: " + product.getURL());

    }
}
