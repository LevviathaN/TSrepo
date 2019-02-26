package viewcart;

import annotations.TestName;
import entities.ItemEntity;
import enums.ProductTypes;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.*;
import utils.EntitiesFactory;
import utils.FileIO;
import utils.ProductSync;
import utils.BaseTest;

/**
 * @author Ruslan Levytskyi
 * @since 2/26/2019.
 */

public class ViewCart_UpdateProductTest_WithDataProvider extends BaseTest {
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
    @TestName(name = "Update Item in Cart")
    public void viewCart_Update_Test(ProductTypes type, Class page, String itemEntity) throws Exception {

        //init test entities
        ItemEntity item = EntitiesFactory.getItem( FileIO.getDataFile("Default_" + itemEntity +".json") );
        ItemEntity updatedItem = itemEntity == "FoamPillow"|| itemEntity == "PlushPillow" ? item
                : EntitiesFactory.getItem( FileIO.getDataFile("Updated_" + itemEntity +".json") );

        //init pages
        HomePage home = HomePage.Instance;
        ViewCartPage cart = ViewCartPage.Instance;
        BaseProductPage product = (BaseProductPage) page.getConstructor().newInstance();

        //test steps
        home.open();
        ProductSync.check(type);
        home.header.openMenuByItemName(type.getValue());
        product.isProductInStock()
                .selectOption(item.getSize())
                .selectOption(item.getType())
                .clickAddToCart();
        ProductSync.uncheck(type);

        Assert.assertTrue(cart.itemDisplayedOnViewCartPage(type.toString()),  "Item was not displayed in cart");

        cart.clickOnEditProduct(item.getTitle());
        product
                .selectOption(updatedItem.getSize())
                .selectOption(updatedItem.getType())
                .clickUpdateCart();

        Assert.assertTrue(cart.itemDisplayedOnViewCartPage(updatedItem), "Updated item was not displayed");

    }
}
