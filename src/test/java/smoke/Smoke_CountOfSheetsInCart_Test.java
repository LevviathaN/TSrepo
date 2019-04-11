package smoke;

import annotations.TestName;
import entities.ItemEntity;
import enums.ProductTypes;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SheetsetPage;
import pages.ShopPage;
import pages.ViewCartPage;
import utils.BaseTest;
import utils.EntitiesFactory;
import utils.FileIO;
import utils.ProductSync;

/**
 * @author Taras
 * @since 7/27/2017.
 */
public class Smoke_CountOfSheetsInCart_Test extends BaseTest {

    @Test
    @TestName(name = "Check count of sheets in cart and cart flag in header")
    public void countOfSheetsInCart_Test() throws Exception {

        int countOfGoodsFromMiniCartIcon;
        int countOfGoodsInMiniCart;

        //creating system entities
        ItemEntity defaultSheet = EntitiesFactory.getItem(FileIO.getDataFile("Default_Sheets.json"));
        ItemEntity updatedSheet = EntitiesFactory.getItem(FileIO.getDataFile("Updated_Sheets.json"));

        //Pages initializing
        HomePage homePage = HomePage.Instance;
        ViewCartPage cart = ViewCartPage.Instance;
        homePage.open();
        ProductSync.check(ProductTypes.SHEETSET);
        ShopPage shopPage = homePage.header.clickShopMenuItem();
        SheetsetPage sheetsetPage = shopPage.clickOnShopSheetsButton();

        //filling the cart with different types and count of sheets
        sheetsetPage.selectOption(defaultSheet.getSize()).clickAddToCart();
        sheetsetPage.open();

        sheetsetPage.selectOption(defaultSheet.getSize()).clickAddToCart();

        //checking if items were added and counted normally
        countOfGoodsFromMiniCartIcon = sheetsetPage.header.getCountOfGoodsFromMiniCartIcon();
        countOfGoodsInMiniCart = sheetsetPage.header.getCountOfGoodsInMiniCart();
        Assert.assertTrue(countOfGoodsInMiniCart == countOfGoodsFromMiniCartIcon, "Count of added to cart items equal to count from cart icon");

        sheetsetPage.open();
        sheetsetPage.selectOption(updatedSheet.getSize()).clickAddToCart();
        sheetsetPage.open();

        sheetsetPage.selectOption(updatedSheet.getSize()).clickAddToCart();
        sheetsetPage.open();

        sheetsetPage.selectOption(updatedSheet.getSize()).clickAddToCart();
        sheetsetPage.open();

        ProductSync.uncheck(ProductTypes.SHEETSET);
        //getting count of selected goods from cart icon
        countOfGoodsFromMiniCartIcon = sheetsetPage.header.getCountOfGoodsFromMiniCartIcon();
        //getting count of selected goods from cart
        countOfGoodsInMiniCart = sheetsetPage.header.getCountOfGoodsInMiniCart();

        //checking if sheets were added and counted
        Assert.assertTrue(countOfGoodsInMiniCart == countOfGoodsFromMiniCartIcon, "Count of added to cart items equal to count from cart icon");
    }

}
