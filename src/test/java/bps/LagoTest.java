package bps;

import pages.bps.AssetsPage;
import utils.BaseTest;

public class LagoTest extends BaseTest {

    public void lagoTest(){
        AssetsPage ast = AssetsPage.Instance;

        ast.logIn();
        ast.checkLagoStatus(4668126,"testValue");
    }

}
