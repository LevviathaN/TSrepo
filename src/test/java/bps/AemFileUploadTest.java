package bps;

import annotations.TestName;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.aem.AssetsPage;
import utils.BaseTest;

public class AemFileUploadTest extends BaseTest {

    @Test
    @TestName(name = "File upload test")
    public void fileUploadTest() {
        AssetsPage ast = AssetsPage.Instance;

        ast.logIn();
        ast.navigate("/test-folder");
        ast.uploadAsset("/src/main/resources/data/bps/Assets/2016nstPMS0547.jpg");
        Assert.assertTrue(ast.isAssetPresent("2016nstPMS0547.jpg"), "Asset is not present");
    }

//    @Test
//    @TestName(name="Delete Asset Test")
//    public void deleteTest(){
//        AssetsPage ast = AssetsPage.Instance;
//
//        ast.logIn();
//        ast.navigate("/test-folder");
//        if(!ast.isAssetPresent("2016nstPMS0547.jpg")){
//            ast.uploadAsset("/Users/ruslanlevytskyi/Downloads/2016nstPMS0547.jpg");
//        }
//        ast.selectAsset("2016nstPMS0547.jpg");
//        ast.deleteSelectedAssets();
//        Assert.assertFalse(ast.isAssetPresent("2016nstPMS0547.jpg"), "Asset is present");
//    }
}
