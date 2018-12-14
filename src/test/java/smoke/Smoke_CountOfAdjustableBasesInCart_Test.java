package smoke;

import annotations.TestName;
import entities.ItemEntity;
import enums.ProductTypes;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
import utils.BaseTest;
import utils.EntitiesFactory;
import utils.FileIO;
import utils.ProductSync;

public class Smoke_CountOfAdjustableBasesInCart_Test extends BaseTest {

    @Test
    @TestName(name = "Check count of Adjustable Bases in cart and cart flag in header")
    public void countOfAdjustableBasesInCart_Test() throws Exception {

        int countOfGoodsFromCartIcon;
        int countOfGoodsInCart;

        //creating system entities
        ItemEntity defaultBase = EntitiesFactory.getItem(FileIO.getDataFile("Default_Adjustable.json"));
        ItemEntity updatedBase = EntitiesFactory.getItem(FileIO.getDataFile("Updated_Adjustable.json"));

        //Pages initializing
        HomePage homePage = HomePage.Instance;
        ViewCartPage cart = ViewCartPage.Instance;
        homePage.open();
        ProductSync.check(ProductTypes.ADJUSTABLE_BASE);
        ShopPage shopPage = homePage.header.clickShopMenuItem();
        AdjustablePage adjustableBasePage = shopPage.clickOnShopOurBaseButton();

        //filling the cart with different types and count of sheets
        adjustableBasePage.selectBaseSize(defaultBase.getSize()).clickAddToCart();
        adjustableBasePage.open();

        adjustableBasePage.selectBaseSize(defaultBase.getSize()).clickAddToCart();
        adjustableBasePage.open();

        //checking if items were added and counted normally
        countOfGoodsFromCartIcon = adjustableBasePage.header.getCountOfGoodsFromMiniCartIcon();
        countOfGoodsInCart = adjustableBasePage.header.getCountOfGoodsInMiniCart();
        Assert.assertTrue(countOfGoodsInCart == countOfGoodsFromCartIcon, "Count of added to cart items equal to count from cart icon");

        adjustableBasePage.selectBaseSize(updatedBase.getSize()).clickAddToCart();
        adjustableBasePage.open();

        adjustableBasePage.selectBaseSize(updatedBase.getSize()).clickAddToCart();
        adjustableBasePage.open();

        adjustableBasePage.selectBaseSize(updatedBase.getSize()).clickAddToCart();
        adjustableBasePage.open();

        ProductSync.uncheck(ProductTypes.ADJUSTABLE_BASE);
        //getting count of selected goods from cart icon
        countOfGoodsFromCartIcon = adjustableBasePage.header.getCountOfGoodsFromMiniCartIcon();
        //getting count of selected goods from cart
        countOfGoodsInCart = adjustableBasePage.header.getCountOfGoodsInMiniCart();

        //checking if sheets were added and counted
        Assert.assertTrue(countOfGoodsInCart == countOfGoodsFromCartIcon, "Count of added to cart items equal to count from cart icon");
    }

}
