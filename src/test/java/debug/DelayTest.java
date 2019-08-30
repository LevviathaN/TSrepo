package debug;

import annotations.TestName;
import org.testng.annotations.Test;
import pages.bps.AssetsPage;
import utils.BaseTest;

public class DelayTest extends BaseTest {
    @Test
    @TestName(name = "Artificial Delay Test")
    public void delayTest(){
        AssetsPage ast = AssetsPage.Instance;

        ast.sleepFor(3*60000);
    }
}
