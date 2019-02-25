package smoke;

import annotations.TestName;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.HomePage;
import utils.BaseTest;


public class Smoke_WellcomePopUpTest extends BaseTest {
    @Test
    @TestName(name = "Wellcome PopUp Presence Test")
    public void smoke_WellcomePopUpPresenceTest() throws Exception {


        // Init pages
        HomePage home = HomePage.Instance;

        // Open home page
        home.open(true);
        BasePage.waitForPageToLoad();
        Thread.sleep(95000);
        Assert.assertTrue(home.isElementPresentAndDisplay(By.xpath("//SPAN[@class='close-button']")),"Wellcome Popup is not displayed");

    }
}
