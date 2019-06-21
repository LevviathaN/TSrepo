package debug;

import annotations.TestName;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.aem.AssetsPage;
import utils.BaseTest;

public class DebugTest extends BaseTest {

    @Test
    @TestName(name = "Debug Test")
    public void debugTest(){
        AssetsPage ast = AssetsPage.Instance;

        ast.logIn();
        ast.navigate("/basspro/test-folder");
        

        if (ast.isAssetPresent("2016nstPMS0547.jpg")){
            ast.reporter.info("Element is present");
        }
        else{
            ast.reporter.info("Element is absent");
        }
        //Assert.assertTrue(ast.isElementPresentAndDisplay(ast.byText("2016nstPMS0547.jpg")), "Asset is not present");
    }

}
