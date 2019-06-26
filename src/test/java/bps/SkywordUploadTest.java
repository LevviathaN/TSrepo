package bps;

import annotations.TestName;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.aem.AssetsPage;
import pages.aem.SkywordUploadPage;
import utils.BaseTest;

public class SkywordUploadTest extends BaseTest {

    @Test
    @TestName(name = "Skyword Upload Teest")
    public void skywordUploadTest(){
        AssetsPage ast = AssetsPage.Instance;
        SkywordUploadPage sky = SkywordUploadPage.Instance;

        sky.logIn();
        sky.navigate("");
        sky.uploadSkywordXml("TEST FOLDER","src/main/resources/data/bps/Test_5_21_v1.xml");
        sky.getResults();
    }
}
