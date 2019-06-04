package bps;

import annotations.TestName;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.aem.AssetsPage;
import utils.BaseTest;

public class AemDeleteAssetTest extends BaseTest {

    @Test
    @TestName(name="Delete Asset Test")
    public void deleteTest(){
        AssetsPage ast = AssetsPage.Instance;

        ast.logIn();
        ast.navigate("/test-folder");
        if(!ast.isAssetPresent("2016nstPMS0547.jpg")){
            ast.uploadAsset("/Users/ruslanlevytskyi/Downloads/2016nstPMS0547.jpg");
        }
        ast.selectAsset("2016nstPMS0547.jpg");
        ast.deleteSelectedAssets();
        Assert.assertFalse(ast.isAssetPresent("2016nstPMS0547.jpg"), "Asset is present");
    }
}
