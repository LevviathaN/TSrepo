package smoke;

import annotations.TestName;
import entities.CategoryEntity;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.MagazinePage;
import utils.BaseTest;
import utils.DriverProvider;
import utils.EntitiesFactory;
import utils.FileIO;

public class Smoke_MagazineArticleSocialMediaSharing_Test extends BaseTest {
    @DataProvider(name = "socials", parallel = true)
    public Object[][] provider(){
        return new Object[][]{
                {"facebook", "//html[@id='facebook']"},
                {"twitter", "//*[@class='action-information']"},
                {"gplus", "//div[@id='initialView']"},
        };
    }

    @Test(dataProvider = "socials")
    @TestName(name="Magazine Articles Sharing with social media")
    public void magazineSocialMediaSharing(String social, String socialLocator){
        MagazinePage magazinePage = MagazinePage.Instance;

        magazinePage.open();
        Assert.assertTrue(magazinePage.isPageLoaded(), "Magazine page is not loaded");
        magazinePage.clickOnRandomArticle();
        magazinePage.shareArticle(social);
        Assert.assertTrue(magazinePage.isElementPresent(By.xpath(socialLocator)),social + " sharing page is not loaded");
    }
}
