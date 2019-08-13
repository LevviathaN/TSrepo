package bps;

import annotations.TestName;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.aem.AssetsPage;
import utils.BaseTest;
import utils.DriverProvider;

public class AemFileUploadTest extends BaseTest {

    @Test
    @TestName(name = "File upload test")
    public void fileUploadTest() {
        AssetsPage ast = AssetsPage.Instance;

        ast.logIn();
        ast.navigate("/test-folder");
        if(DriverProvider.OS_EXTENTION.equals("_mac")) ast.bringToFocus();
        ast.closeRatingPopup();
        ast.uploadAsset("src/main/resources/data/bps/Assets/2016nstPMS0547.jpg");
        Assert.assertTrue(ast.isAssetPresent("2016nstPMS0547.jpg"), "Asset is not present");
    }
}
