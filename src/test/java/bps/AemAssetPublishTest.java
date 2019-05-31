package bps;

import annotations.TestName;
import org.testng.annotations.Test;
import pages.aem.AssetsPage;

public class AemAssetPublishTest {

    @Test
    @TestName(name="Asset Publication Test")
    public void publicationTest(){
        AssetsPage ast = AssetsPage.Instance;

        ast.logIn();
        ast.navigate("/test-folder");
        ast.selectAsset("2016nstPMS0547.jpg");

    }
}
