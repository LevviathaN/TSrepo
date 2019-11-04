package cucumber;

import gherkin.pickles.Pickle;
import gherkin.pickles.PickleStep;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.*;
import org.testng.annotations.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import ui.utils.BaseTest;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import static java.nio.file.StandardOpenOption.CREATE;

@CucumberOptions(
        features = "src/test/resources/cucumber/bpp_features",
        glue = {"cucumber.stepdefs"},
        tags = {"@Reusable"},
        plugin = {"pretty"})

public class ReusableSteps {

    private TestNGCucumberRunner testNGCucumberRunner;
    private String fileContent;
    private String step;

    public ReusableSteps() {}

    @BeforeClass(alwaysRun = true)
    public void setUpClass() {
        this.testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
        step = "Initial step";
        fileContent = "<reusables>";
    }


    @Test(
            groups = {"cucumber"},
            description = "Runs Cucumber Scenarios",
            dataProvider = "scenarios"
    )
    public void findReusable(PickleEventWrapper pickleWrapper, CucumberFeatureWrapper featureWrapper) throws Throwable {
        System.out.println("Debug " + pickleWrapper.getPickleEvent().pickle.getName());

        Pickle pickle = pickleWrapper.getPickleEvent().pickle;
        step = "<reusable name=\"" + pickle.getName() + "\">";
        for(PickleStep pickleStep : pickle.getSteps()){
            String currentStep = pickleStep.getText();
            currentStep = currentStep.replaceAll("<", "&lt;");
            currentStep = currentStep.replaceAll(">", "&gt;");
            step = step + "<step>" + currentStep + "</step>";
        }
        step = step + "</reusable>";
        fileContent = fileContent + step;
    }

    @DataProvider()
    public Object[][] scenarios() {
        return this.testNGCucumberRunner == null ? new Object[0][0] : this.testNGCucumberRunner.provideScenarios();
    }

    @AfterClass(alwaysRun = true)
    public void tearDownClass() {
        if (this.testNGCucumberRunner != null) {
            this.testNGCucumberRunner.finish();

            fileContent = fileContent + "</reusables>";
            byte data[] = fileContent.getBytes();
            Path p = Paths.get("src/main/resources/ReusableTestSteps.xml");


            try (OutputStream out = new BufferedOutputStream(
                    Files.newOutputStream(p, CREATE))) {
                out.write(data, 0, data.length);
            } catch (IOException x) {
                System.err.println(x);
            }
        }
    }
}