package bps;

import annotations.TestName;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.bps.AssetsPage;
import utils.BaseTest;

public class AemAssetPublishTest extends BaseTest {

    @Test
    @TestName(name="Asset Publication Test")
    public void publicationTest(){
        AssetsPage ast = AssetsPage.Instance;

        ast.logIn();
        ast.navigate("/test-folder");
        ast.closeRatingPopup();
        ast.selectAsset("2016nstPMS0547.jpg");
        ast.publishSelectedAssets();
        Assert.assertTrue(ast.isElementPresent(ast.byText("Queued up for publish to Brand Portal.")),
                "Success message appeared");
        ast.logIntoBP();
        ast.bpNavigate("/test-folder");
        Assert.assertTrue(ast.isAssetPresent("2016nstPMS0547.jpg"),"Asset is present in BP UI");
        //todo: verify on BP environment
    }
}
