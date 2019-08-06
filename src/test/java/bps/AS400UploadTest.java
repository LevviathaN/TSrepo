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
        //todo verifyMetadata() method
    }
}
