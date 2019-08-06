package bps;

import annotations.TestName;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.aem.AssetsPage;
import pages.aem.SkywordUploadPage;
import utils.BaseTest;

public class SkywordUploadTest extends BaseTest {

//    @Test
//    @TestName(name = "Skyword Upload Test")
//    public void a_skywordUploadTest(){
//        AssetsPage ast = AssetsPage.Instance;
//        SkywordUploadPage sky = SkywordUploadPage.Instance;
//
//        sky.logIn();
//        sky.navigate("");
//        sky.uploadSkywordXml("TEST FOLDER","src/main/resources/data/bps/Test_5_21_v1.xml");
//    }

    @Test
    @TestName(name = "Skyword Create Test")
    public void b_skywordCreateTest(){
        AssetsPage ast = AssetsPage.Instance;
        SkywordUploadPage sky = SkywordUploadPage.Instance;

        sky.logIn();
        sky.navigate("");
        ast.closeRatingPopup();
        sky.uploadSkywordXml("TEST FOLDER","src/main/resources/data/bps/Skyword_Create.xml");
        Assert.assertFalse(sky.isSkywordErrors(), "Asset already exists");
        ast.navigate("/test-folder");
        Assert.assertTrue(ast.isAssetPresent("Create CF Test"));
    }

    @Test
    @TestName(name = "Skyword Update Test")
    public void c_skywordUpdateTest(){
        AssetsPage ast = AssetsPage.Instance;
        SkywordUploadPage sky = SkywordUploadPage.Instance;

        sky.logIn();
        sky.navigate("");
        ast.closeRatingPopup();
        sky.uploadSkywordXml("TEST FOLDER","src/main/resources/data/bps/Skyword_Update.xml");
        ast.navigate("/test-folder");
        Assert.assertTrue(ast.isAssetPresent("Update CF Test"));
    }
}
