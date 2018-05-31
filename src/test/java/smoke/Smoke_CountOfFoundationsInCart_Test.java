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

public class Smoke_CountOfFoundationsInCart_Test extends BaseTest {

    @Test
    @TestName(name = "Check count of Foundations in cart and cart flag in header")
    public void countOfPlatformBedsInCart_Test() throws Exception {

        int countOfGoodsFromCartIcon;
        int countOfGoodsInCart;

        //creating system entities
        ItemEntity defaultBase = EntitiesFactory.getItem(FileIO.getDataFile("Default_Foundation.json"));
        ItemEntity updatedBase = EntitiesFactory.getItem(FileIO.getDataFile("Updated_Foundation.json"));

        //Pages initializing
        HomePage homePage = HomePage.Instance;
        ViewCartPage cart = ViewCartPage.Instance;
        FoundationPage foundationPage = FoundationPage.Instance;

        homePage.open();
        homePage.header.openMenuByItemName("Tomorrow Platform Bed");

        ProductSync.check(ProductTypes.FOUNDATION);

        //filling the cart with different types and count of sheets
        foundationPage.selectBaseSize(defaultBase.getSize()).clickAddToCart();
        foundationPage.open();

        foundationPage.selectBaseSize(defaultBase.getSize()).clickAddToCart();
        foundationPage.open();

        //checking if items were added and counted normally
        countOfGoodsFromCartIcon = foundationPage.header.getCountOfGoodsFromCartIcon();
        countOfGoodsInCart = foundationPage.header.getCountOfGoodsInCart();
        Assert.assertTrue(countOfGoodsInCart == countOfGoodsFromCartIcon, "Count of added to cart items equal to count from cart icon");

        foundationPage.selectBaseSize(updatedBase.getSize()).clickAddToCart();
        foundationPage.open();

        foundationPage.selectBaseSize(updatedBase.getSize()).clickAddToCart();
        foundationPage.open();

        foundationPage.selectBaseSize(updatedBase.getSize()).clickAddToCart();
        foundationPage.open();

        ProductSync.uncheck(ProductTypes.FOUNDATION);
        //getting count of selected goods from cart icon
        countOfGoodsFromCartIcon = foundationPage.header.getCountOfGoodsFromCartIcon();
        //getting count of selected goods from cart
        countOfGoodsInCart = foundationPage.header.getCountOfGoodsInCart();

        //checking if sheets were added and counted
        Assert.assertTrue(countOfGoodsInCart == countOfGoodsFromCartIcon, "Count of added to cart items equal to count from cart icon");
    }

}
