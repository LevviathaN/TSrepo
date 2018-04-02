package old;

import annotations.TestName;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.BaseTest;
import utils.ScreenshotComparison;
import pages.BasePage;

import java.io.IOException;

import static pages.BasePage.clickOnElementIgnoreException;
import static pages.BasePage.driver;
import static pages.BasePage.waitForPageToLoad;


public class images extends BaseTest{

    String url1 = "https://www.tomorrowsleep.com"; //"http://www.google.ru/ru";
    String url2 = "https://www.tomorrowsleep.com";//"https://bettersleep:stg-tsleep-@45@staging.tomorrowsleep.com"; //"http://www.google.com.ua";

    @Test
    @TestName(name = "compare")

    public void doComparison() throws IOException {
        BasePage.openUrl(url1);
        waitForPageToLoad();
        clickOnElementIgnoreException(By.xpath("//SPAN[@class='close-button']"));
        ScreenshotComparison.getExpectedScreenshot();

        BasePage.openUrl(url2);
        waitForPageToLoad();
        //clickOnElementIgnoreException(By.xpath("//SPAN[@class='close-button']"));
        ScreenshotComparison.getActualScreenshot();

        Assert.assertFalse(ScreenshotComparison.compare(), "lol");

    }

}
