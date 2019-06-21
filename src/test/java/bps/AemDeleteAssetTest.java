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
        ast.navigate("/basspro/test-folder");
        if(!ast.isAssetPresent("2016nstPMS0547.jpg")){
            ast.uploadAsset("/src/main/resources/data/bps/Assets/2016nstPMS0547.jpg");
        }
        ast.selectAsset("2016nstPMS0547.jpg");
        ast.deleteSelectedAssets();
        if(ast.isElementPresentAndDisplay(ast.byText("Force Delete"))){
            ast.clickOnAnyElement(ast.byText("Delete"));
            ast.reporter.info("FORCE DELETE prompted");
            ast.sleepFor(5000);
            ast.clickOnAnyElement(ast.byText("Delete"));
        }
        Assert.assertFalse(ast.isAssetPresent("2016nstPMS0547.jpg"), "Asset is present");
    }
}
