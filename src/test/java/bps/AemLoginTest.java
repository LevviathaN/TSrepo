package bps;

import annotations.TestName;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.aem.AemBasePage;
import utils.BaseTest;

public class AemLoginTest extends BaseTest {

    @Test()
    @TestName(name = "Login Test")
    public void loginTest() {
        AemBasePage aem = AemBasePage.Instance;

        aem.logIn();
        Assert.assertTrue(aem.isElementPresent(aem.byText("Navigation")));
    }
}
