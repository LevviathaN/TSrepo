package bps;

import annotations.TestName;
import org.testng.annotations.Test;
import pages.aem.AssetsPage;

public class AemDeleteAssetTest {

    @Test
    @TestName(name="Delete Asset Test")
    public void deleteTest(){
        AssetsPage ast = AssetsPage.Instance;

        ast.logIn();
        ast.navigate("/test-folder");
    }
}
