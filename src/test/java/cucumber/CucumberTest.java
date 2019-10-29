package cucumber;

import gherkin.pickles.Argument;
import gherkin.pickles.Pickle;
import gherkin.pickles.PickleLocation;
import gherkin.pickles.PickleStep;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.*;
import org.testng.annotations.*;
import utils.BaseTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@CucumberOptions(
        features = "src/test/resources/cucumber/bpp_features",
        glue = {"cucumber.stepdefs"},
        tags = {"@Old"},
        plugin = {"pretty"})

public class CucumberTest  extends BaseTest {
    private TestNGCucumberRunner testNGCucumberRunner;
    ReusableSteps reusableSteps;
    ReusableHelper reusableHelper;

    public CucumberTest() {
        reusableSteps = new ReusableSteps();
        reusableHelper = new ReusableHelper();
    }

    @BeforeClass(
            alwaysRun = true
    )
    public void setUpClass() {
        this.testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }

    @Test(
            groups = {"cucumber"},
            description = "Runs Cucumber Scenarios",
            dataProvider = "scenarios"
    )
    public void runScenario(PickleEventWrapper pickleWrapper, CucumberFeatureWrapper featureWrapper) throws Throwable {
        this.testNGCucumberRunner.runScenario(pickleWrapper.getPickleEvent());
    }

    @Test(
            groups = {"cucumber"},
            description = "Runs Cucumber Scenarios",
            dataProvider = "scenarios"
    )
    public void runScenarioWithReusable(PickleEventWrapper pickleWrapper, CucumberFeatureWrapper featureWrapper) throws Throwable {
        Pickle currentPickle = pickleWrapper.getPickleEvent().pickle;
        PickleStep step;
        for(int iter = 0; iter < currentPickle.getSteps().size(); iter++){
            step = currentPickle.getSteps().get(iter);
            if(step.getText().contains("reusable")){
                System.out.println("reusable: " + step.getText());

                PickleLocation tempLocation = new PickleLocation(7,3);
                List<Argument> tempList = new ArrayList<Argument>();
                List<PickleLocation> tempLoc = new ArrayList<PickleLocation>();
                PickleStep tempStep = new PickleStep(reusableHelper.getReusablePickleSteps(step.getText()).get(0), tempList, tempLoc);
            }
        }
        this.testNGCucumberRunner.runScenario(pickleWrapper.getPickleEvent());
    }

    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return this.testNGCucumberRunner == null ? new Object[0][0] : this.testNGCucumberRunner.provideScenarios();
    }

    @AfterClass(
            alwaysRun = true
    )
    public void tearDownClass() {
        if (this.testNGCucumberRunner != null) {
            this.testNGCucumberRunner.finish();
        }
    }
}