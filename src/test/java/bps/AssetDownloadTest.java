package bps;

import annotations.TestName;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.aem.AssetsPage;
import utils.BaseTest;

public class AssetDownloadTest extends BaseTest {

    @Test
    @TestName(name = "Asset Download Test")
    public void a_assetDownloadTest(){
        AssetsPage ast = AssetsPage.Instance;
        
        ast.logIn();
        ast.navigate("/test-folder");
        ast.closeRatingPopup();
        ast.selectAsset("2016nstPMS0547.jpg");
        ast.downloadSelectedAssets();
        Assert.assertTrue(ast.isAssetDownloaded("2016nstPMS0547.jpg"),"Asset is downloaded");

    }
}
