package bpp;

import annotations.TestName;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.bpp.product_factory.ProductFactoryBasePage;
import utils.BaseTest;

public class CreateBodyTest extends BaseTest {

    @Test
    @TestName(name="Create Body Test")
    public void createBodyTest(){
        ProductFactoryBasePage pf = ProductFactoryBasePage.Instance;

        pf.logInAs("Senior Product Setter");
        pf.createNewBody("SomeShortName","SomeName");
        Assert.assertTrue(pf.isElementPresent(pf.byText("SomeShortName")),
                "Success message appeared");
    }
}
