package old;

import annotations.TestName;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
import sun.jvm.hotspot.debugger.Page;
import sun.jvm.hotspot.oops.Instance;
import utils.BaseTest;
import utils.ScreenshotComparison;

import java.io.IOException;

import static pages.BasePage.waitForPageToLoad;
import static utils.ScreenshotComparison.getExpectedScreenshot;


public class images extends BaseTest{


    @Test
    @TestName(name = "compare")

    public void doComparison() throws IOException {

        HomePage home = HomePage.Instance;
        ShopPage shop = ShopPage.Instance;
        MattressesPage mattress = MattressesPage.Instance;
        FoamPillowPage foam = FoamPillowPage.Instance;
        PlushPillowPage plush = PlushPillowPage.Instance;
        ComforterPage comf = ComforterPage.Instance;
        SheetsetPage sheets = SheetsetPage.Instance;
        MattressProtectorPage protector = MattressProtectorPage.Instance;
        MonitorPage monitor = MonitorPage.Instance;
        DrapesPage drapes = DrapesPage.Instance;
        AdjustablePage base = AdjustablePage.Instance;
        FoundationPage foundation = FoundationPage.Instance;


        home.open();
        waitForPageToLoad();
        getExpectedScreenshot("Homepage");

        shop.open();
        waitForPageToLoad();
        getExpectedScreenshot("Shop page");

        mattress.open();
        waitForPageToLoad();
        getExpectedScreenshot("Mattress page");

        foam.open();
        waitForPageToLoad();
        getExpectedScreenshot("Foam Pillow Page");

        plush.open();
        waitForPageToLoad();
        getExpectedScreenshot("Plush Pillow page");

        comf.open();
        waitForPageToLoad();
        getExpectedScreenshot("Comforter page");

        sheets.open();
        waitForPageToLoad();
        getExpectedScreenshot("Sheet Set page");

        protector.open();
        waitForPageToLoad();
        getExpectedScreenshot("Mattress Protector page");

        monitor.open();
        waitForPageToLoad();
        getExpectedScreenshot("Monitor page");

        drapes.open();
        waitForPageToLoad();
        getExpectedScreenshot("Drapes page");

        base.open();
        waitForPageToLoad();
        getExpectedScreenshot("Adjustible Base page");

        foundation.open();
        waitForPageToLoad();
        getExpectedScreenshot("Foundation page");
    }

}
