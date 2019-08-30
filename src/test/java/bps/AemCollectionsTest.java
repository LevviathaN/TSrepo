package bps;

import annotations.TestName;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.bps.AssetsPage;
import pages.bps.CollectionsPage;
import utils.BaseTest;

public class AemCollectionsTest extends BaseTest {

    @Test
    @TestName(name = "Create Collection Test")
    public void a_createCollectionTest(){
        CollectionsPage col = CollectionsPage.Instance;

        col.logIn();
        col.navigate("");
        col.closeRatingPopup();
        col.createCollection("SampleCol");
        col.navigate("");
        Assert.assertTrue(col.isCollectionPresent("SampleCol"));
    }

//    @Test(priority = 1)
//    @TestName(name = "Associate collection with Assets")
//    public void b_associateCollectionTest(){
//        AssetsPage ast = AssetsPage.Instance;
//        CollectionsPage col = CollectionsPage.Instance;
//
//        ast.logIn();
//        ast.navigate("/test-folder");
//        ast.closeRatingPopup();
//        ast.selectAsset("2016nstPMS0547.jpg");
//        ast.addSelectedAssetsToCol("SampleCol");
//    }

    @Test
    @TestName(name = "Delete Collection Test")
    public void d_deleteCollectionTest(){
        CollectionsPage col = CollectionsPage.Instance;
        AssetsPage ast = AssetsPage.Instance;

        col.logIn();
        col.navigate("");
        col.closeRatingPopup();
        ast.selectAsset("SampleCol");
        ast.deleteSelectedAssets();
        Assert.assertFalse(col.isCollectionPresent("SampleCol"),"Collection is present");
    }
}
