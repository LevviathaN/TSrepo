package bps;

import annotations.TestName;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.aem.AssetsPage;
import pages.aem.MetadataFormPage;
import utils.BaseTest;

public class AssetRelationTest extends BaseTest {

    @Test
    @TestName(name="Asset Relate Source Test")
    public void assetRelateSourceTest(){
        AssetsPage ast = AssetsPage.Instance;
        MetadataFormPage meta = MetadataFormPage.Instance;

        ast.logIn();
        ast.navigate("/test-folder");
        ast.closeRatingPopup();
        //ast.uploadAsset("src/main/resources/data/bps/Assets/2016nstPMS0567.jpg");
        ast.openPropertiesOfAsset("2016nstPMS0547.jpg");
        ast.relateAsset("TEST FOLDER/2016nstPMS0567.jpg", "Source"); //todo click on TEST FOLDER is received by "//div[@class='dam-asset-picker-overlay']"
        Assert.assertTrue(ast.isElementPresent(ast.byText("Relation(s) have been created.")),
                "Success message appeared");
        ast.clickByText("OK");
        ast.openPropertiesOfAsset("2016nstPMS0547.jpg");
        Assert.assertTrue(meta.verifyRelation("2016nstPMS0567.jpg","Source"),"Asset is related");
        //todo: check source asset's metadata
        //todo: NOT WORKING RIGHT NOW
    }
}
