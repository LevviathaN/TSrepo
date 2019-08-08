package bps;

import annotations.TestName;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.aem.AS400UploadPage;
import pages.aem.AssetsPage;
import pages.aem.MetadataFormPage;
import utils.BaseTest;

public class AS400UploadTest extends BaseTest {

    @Test
    @TestName(name = "ITAP Upload Test")
    public void a_ItapUploadTest(){

        AS400UploadPage as4 = AS400UploadPage.Instance;
        AssetsPage ast = AssetsPage.Instance;
        MetadataFormPage mdf = MetadataFormPage.Instance;

        ast.logIn();
        ast.navigate("/test-folder");
        ast.closeRatingPopup();
        ast.openPropertiesOfAsset("2016nstPMS0547.jpg");
        mdf.addValueToMultifield("SKU Numbers","184337");
        ast.clickByText("Save & Close");

        as4.navigate("");
        as4.as400Upload("ITAP", "src/main/resources/data/bps/ITAP_short.csv");
        Assert.assertFalse(as4.isAS400Errors(), "Some errors");
        ast.navigate("/test-folder");
        ast.openPropertiesOfAsset("2016nstPMS0547.jpg");
        Assert.assertTrue(mdf.verifyMetadata("CAB SKU/104897","Master SKU/1091175","SKU Status/A"));
        //todo: add more name/value pairs
    }

    @Test
    @TestName(name = "DMAT Upload Test")
    public void b_DmatUploadTest(){

        AS400UploadPage as4 = AS400UploadPage.Instance;
        AssetsPage ast = AssetsPage.Instance;
        MetadataFormPage mdf = MetadataFormPage.Instance;

        ast.logIn();
        as4.navigate("");
        as4.as400Upload("DMAT", "src/main/resources/data/bps/DMAT_short.csv");
        Assert.assertFalse(as4.isAS400Errors(), "Some errors");
        ast.navigate("/test-folder");
        ast.openPropertiesOfAsset("2016nstPMS0547.jpg");
        Assert.assertTrue(as4.verifyDmat("type / bait-hooks"), "Tags are correct");
        //todo: add multiple tags verification
    }
}
