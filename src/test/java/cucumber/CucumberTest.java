package cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.*;
import org.testng.annotations.*;
import utils.BaseTest;

@CucumberOptions(
        features = "src/test/resources/cucumber",
        glue = {"cucumber.stepdefs"},
        tags = {"not @Ignore"},
        plugin = {"pretty"})
public class CucumberTest extends BaseTest {
    private TestNGCucumberRunner testNGCucumberRunner;

    @BeforeClass(alwaysRun = true)
    public void setUpClass() throws Exception {
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }

    @Test(groups = "cucumber", description = "Runs Cucumber Feature", dataProvider = "features")
    public void feature(CucumberFeatureWrapper cucumberFeature) {
        testNGCucumberRunner.runCucumber(cucumberFeature.getCucumberFeature());
    }

    @DataProvider
    public Object[][] features() {
        return testNGCucumberRunner.provideFeatures();
    }

    @AfterClass(alwaysRun = true)
    public void tearDownClass() throws Exception {
        testNGCucumberRunner.finish();
    }
}

