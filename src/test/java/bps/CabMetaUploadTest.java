package bps;

import annotations.TestName;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.bps.AssetsPage;
import pages.bps.CabUploadPage;
import utils.BaseTest;

public class CabMetaUploadTest extends BaseTest {
    @Test
    @TestName(name = "Cab Metadata Upload Test")
    public void CabUploadTest(){
        AssetsPage ast = AssetsPage.Instance;
        CabUploadPage cab = CabUploadPage.Instance;

        cab.logIn();
        cab.navigate("");
        ast.closeRatingPopup();
        cab.uploadCabMetaCsv("src/main/resources/data/bps/SKU XREF.csv");
        Assert.assertFalse(cab.isCabErrors(), "Something is wrong");
        ast.navigate("/test-folder/");
        ast.openPropertiesOfAsset("2016nstPMS0547.jpg");
        Assert.assertTrue(cab.verifyCabMeta("104897","202840","786096483698"),
                "Correct CAB metadata");
    }
}
