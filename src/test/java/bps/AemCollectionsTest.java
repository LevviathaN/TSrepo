package bps;

import annotations.TestName;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.aem.AssetsPage;
import pages.aem.CollectionsPage;
import utils.BaseTest;

public class AemCollectionsTest extends BaseTest {

    @Test
    @TestName(name = "Create Collection Test")
    public void createCollectionTest(){
        CollectionsPage col = CollectionsPage.Instance;

        col.logIn();
        col.navigate("");
        col.createCollection("SampleCol");
        col.navigate("");
        Assert.assertTrue(col.isCollectionPresent("SampleCol"));
    }

    @Test
    @TestName(name = "Associate collection with Assets")
    public void associateCollectionTest(){
        AssetsPage ast = AssetsPage.Instance;
        CollectionsPage col = CollectionsPage.Instance;

        ast.logIn();
        ast.navigate("/basspro/test-folder");
        ast.selectAsset("2016nstPMS0547.jpg");
        ast.addSelectedAssetsToCol("SampleCol");
    }
}
