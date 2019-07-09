package bps;

import annotations.TestName;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.aem.AS400UploadPage;
import pages.aem.AssetsPage;
import utils.BaseTest;

public class AS400UploadTest extends BaseTest {

    @Test
    @TestName(name = "Content Fragment Create Test")
    public void a_contentFragmentCreateTest(){
        AS400UploadPage as4 = AS400UploadPage.Instance;

        as4.logIn();
        as4.navigate("");
        as4.as400Upload("DMAT", "src/main/resources/data/bps/DMAT_short.csv");
        Assert.assertTrue(as4.isAS400Errors());
    }
}
