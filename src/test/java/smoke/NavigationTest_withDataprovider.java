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

public class NavigationTest_withDataprovider extends BaseTest {
    @DataProvider(name = "default_item_provider")
    public Object[][] provider (){
        return new Object[][]{
                {ProductTypes.PLUSH_PILLOW, PlushPillowPage.class, "Tomorrow Hypoallergenic Plush Pillow"},
                {ProductTypes.FOAM_PILLOW,  FoamPillowPage.class, "Tomorrow Cooling Memory Foam Pillow"},
                {ProductTypes.MONITOR, MonitorPage.class, "Tomorrow Sleeptracker® Monitor"},
                {ProductTypes.MATTRESS, MattressesPage.class, "Tomorrow Hybrid Mattress" },
                {ProductTypes.MATTRESS_PROTECTOR, MattressProtectorPage.class, "Tomorrow Waterproof Mattress Protector" },
                {ProductTypes.COMFORTER,  ComforterPage.class, "Tomorrow White Comforter"},
                {ProductTypes.DRAPES, DrapesPage.class, "Tomorrow Blackout Curtains"},
                {ProductTypes.SHEETSET, SheetsetPage.class, "Tomorrow White Sheet Set"},
                {ProductTypes.ADJUSTABLE_BASE, AdjustablePage.class, "Tomorrow Adjustable Bed"},
                {ProductTypes.FOUNDATION, FoundationPage.class, "Tomorrow Platform Bed"}
        };
    }

    @Test (dataProvider = "default_item_provider")
    @TestName (name="Navigation validation")
    public void topMenuValidation(ProductTypes type, Class page, String itemName) throws Exception {

        HomePage home = HomePage.Instance;
        ViewCartPage cart = ViewCartPage.Instance;
        BaseProductPage bp = (BaseProductPage) page.getConstructor().newInstance();

        home.open();
        ProductSync.check(type);
        home.header.openMenuByItemName(itemName);

        Assert.assertTrue(bp.isPageLoaded(), "Page was not opened: " + bp.getURL());

        if (type == ProductTypes.MONITOR) // no default value for monitor - user have to select type before Adding to cart
            MonitorPage.Instance.selectMonitorType("One Person");

        bp.clickAddToCart();
        ProductSync.uncheck(type);
        //ViewCartPage cart = home.header.clickOnViewCartButton();
        cart.clickOnProduct(type.toString());

        Assert.assertTrue(bp.isPageLoaded(), "Page was not opened: " + bp.getURL());

    }
}
