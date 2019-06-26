package bps;

import pages.aem.AssetsPage;
import utils.BaseTest;

public class LagoTest extends BaseTest {

    public void lagoTest(){
        AssetsPage ast = AssetsPage.Instance;

        ast.logIn();
        ast.openUrl("");
    }

}
