package old;

import annotations.TestName;
import org.testng.annotations.Test;
import pages.ts.ShopPage;
import utils.BaseTest;

import java.io.IOException;

import static pages.BasePage.waitForPageToLoad;
import static utils.ScreenshotComparison.getExpectedScreenshot;


public class images extends BaseTest{


    @Test
    @TestName(name = "compare")

    public void doComparison() throws IOException {

        ShopPage shop = ShopPage.Instance;

        shop.open();
        waitForPageToLoad();
        getExpectedScreenshot("Shop page");

    }

}
