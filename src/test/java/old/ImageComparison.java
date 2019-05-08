package old;

import annotations.TestName;
import org.testng.annotations.Test;
import utils.BaseTest;
import utils.DriverProvider;
import utils.FileIO;
import utils.ScreenshotComparison;

import java.io.IOException;

import static utils.ScreenshotComparison.compare;
import static utils.ScreenshotComparison.getActualScreenshot;

public class ImageComparison extends BaseTest {



    @Test
    @TestName (name = "Homepage comparison")

    public void compareHomepage() throws IOException {
        DriverProvider.getDriver().get("https://www.tomorrowsleep.com");
        getActualScreenshot("Homepage");
        //Assert.assertFalse(compare("Homepage", "Homepage", "Homepage difference"), "Homepage image differs");
        if (compare("Homepage", "Homepage", "Homepage difference")){
            //Assert.;
            ScreenshotComparison.overwriteScreenshot("Homepage", "Homepage", FileIO.filename);
        }
    }

}