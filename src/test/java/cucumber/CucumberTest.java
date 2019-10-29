package cucumber;

import gherkin.events.PickleEvent;
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
        tags = {"@Bodies"},
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
        PickleEvent currentPickle = pickleWrapper.getPickleEvent();
        PickleStep step;
        for(int iter = 0; iter < currentPickle.pickle.getSteps().size(); iter++){
            step = currentPickle.pickle.getSteps().get(iter);
            step.getText();
            if(step.getText().contains("reusable")){
                System.out.println("reusable: " + step.getText());

                PickleLocation tempLocation = new PickleLocation(7,21);
                List<Argument> tempList = new ArrayList<Argument>();
                List<PickleLocation> tempLoc = new ArrayList<PickleLocation>();
                tempLoc.add(tempLocation);
                ArrayList<String> stepTextList = reusableHelper.getReusablePickleSteps(step.getText());
                String stepText = stepTextList.get(0);
                PickleStep tempStep = new PickleStep(stepText, tempList, tempLoc);
                currentPickle.pickle.getSteps().add(1, tempStep);
            }
        }
        this.testNGCucumberRunner.runScenario(currentPickle);
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