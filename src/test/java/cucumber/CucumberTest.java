package cucumber;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.*;
import org.testng.annotations.*;
import utils.BaseTest;

@CucumberOptions(
        features = "src/test/resources/bps",
        glue = {"cucumber.stepdefs"},
        tags = {"not @Ignore"},
        plugin = {"pretty"})
public class CucumberTest extends BaseTest {
    private TestNGCucumberRunner testNGCucumberRunner;

    @BeforeClass(alwaysRun = true)
    public void setUpClass() throws Exception {
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }

    @Test(description = "Runs Cucumber Feature", dataProvider = "features")
    public void feature(CucumberFeatureWrapper cucumberFeature) {
//        testNGCucumberRunner.runCucumber(cucumberFeature.getCucumberFeature());
    }

    @Test(description = "Runs Cucumber Scenarios", dataProvider = "scenarios")
    public void runScenario(PickleEventWrapper pickleWrapper, CucumberFeatureWrapper featureWrapper) throws Throwable {
// the 'featureWrapper' parameter solely exists to display the feature file in a test report
        testNGCucumberRunner.runScenario(pickleWrapper.getPickleEvent());
    }

//    @DataProvider
//    public Object[][] features() {
//        return testNGCucumberRunner.provideFeatures();
//    }
//
//    @DataProvider
//    public Object[][] scenarios() {
//        return testNGCucumberRunner.provideFeatures();
//    }

    @AfterClass(alwaysRun = true)
    public void tearDownClass() throws Exception {
        testNGCucumberRunner.finish();
    }
}

