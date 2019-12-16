package cucumber.reusablesteps;

import cucumber.stepdefs.SpecialStepDefs;
import cucumber.stepdefs.StepDefinitions;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import ui.utils.Reporter;
import ui.utils.Tools;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static cucumber.StepPatterns.*;


public class ReusableRunner {
    private static ReusableRunner instance;
    public static ReusableRunner Instance = (instance != null) ? instance : new ReusableRunner();

    public ReusableRunner() {

        //General stepdefs
        stepDefsMap.put(I_CLICK_ON_THE_BUTTON.getPattern(),() -> stepDefs.i_click_on_the_button(arg1));
        stepDefsMap.put(I_AM_ON_URL.getPattern(),() -> stepDefs.i_am_on_url(arg1));
        stepDefsMap.put(FILL_THE_FIELD.getPattern(),() -> stepDefs.fill_field(arg1, arg2));
        stepDefsMap.put(WAIT_FOR.getPattern(),() -> stepDefs.wait_for(arg1));
        stepDefsMap.put(HOVER_OVER.getPattern(),() -> stepDefs.hover_over(arg1));
        stepDefsMap.put(I_SHOULD_SEE_THE_TEXT.getPattern(),() -> stepDefs.i_should_see_the_text(arg1));
        stepDefsMap.put(I_SHOULD_BE_REDIRECTED_TO_THE_PAGE.getPattern(),() -> stepDefs.i_should_be_redirected_to_page(arg1));
        stepDefsMap.put(I_EXECUTE_REUSABLE_STEP.getPattern(),() -> stepDefs.i_execute_reusable_step(arg1));
        stepDefsMap.put(I_REMEMBER_TEXT.getPattern(),() -> stepDefs.i_remember_text(arg1, arg2));
        stepDefsMap.put(ELEMENTS_ATTRIBUTE_SHOULD_HAVE_VALUE.getPattern(),() -> stepDefs.elements_attribute_should_have_value(arg1, arg2, arg3));
        stepDefsMap.put(I_CHECK_UNCHECK.getPattern(),() -> stepDefs.i_check_uncheck(arg1, arg2));

        //Special stepdefs
        stepDefsMap.put(I_CLICK_ON_ELEMENT_WITH_PARAMETER_SPECIAL.getPattern(),() -> specialStepDefs.i_click_on_element_with_parameter_special(arg1, arg2));
        stepDefsMap.put(I_SET_TEXT_SPECIAL.getPattern(),() -> specialStepDefs.i_set_text_special(arg1, arg2, arg3));
        stepDefsMap.put(I_SHOULD_SEE_SPECIAL.getPattern(),() -> specialStepDefs.i_should_see_special(arg1, arg2));
        stepDefsMap.put(ELEMENTS_ATTRIBUTE_SHOULD_HAVE_VALUE_SPECIAL.getPattern(),() -> specialStepDefs.elements_attribute_should_have_value_special(arg1, arg2, arg3, arg4));
        stepDefsMap.put(I_CHECK_UNCHECK_SPECIAL.getPattern(),() -> specialStepDefs.i_check_uncheck_special(arg1, arg2, arg3));
    }

    private StepDefinitions stepDefs = new StepDefinitions();
    private SpecialStepDefs specialStepDefs = new SpecialStepDefs();

    private ArrayList<String> reusable;

    public HashMap<String, RunReusable> stepDefsMap = new HashMap<>();

    private String step = "";
    private String arg1 = "";
    private String arg2 = "";
    private String arg3 = "";
    private String arg4 = "";

    /**
     * Execute reusable scenario with some additional steps
     *
     * @param reusableName name of scenario, that you want to use as a reusable step.
     *                     This scenario must be in src/main/resources/data/bpp/ReusableTestSteps.xml
     * @param subSteps     Map of steps you want to add to scenario beside it's own,
     *                     where key - index in scenario whe you want to insert your additional step
     *                     and value - step, written in gherkin notation
     * @author Ruslan Levytskyi
     */
    public void executeReusableAddSteps(String reusableName, Map<Integer, String> subSteps) {
        Reporter.node("Executing " + reusableName + " reusable scenario. ",
                "It contains " + getStepsOfReusableScenario(reusableName).size() + " reusable steps");
        reusable = getStepsOfReusableScenario(reusableName);

        for (int i = 0; i < reusable.size(); i++) {

            Reporter.node("Executing " + reusableName + " reusable scenario. ",
                    "It contains " + getStepsOfReusableScenario(reusableName).size() + " reusable steps");
            if (subSteps.containsKey(i)) {
                //reporter.info("Adding \"" + subSteps.get(i) + "\" on the " + i + " position");
                reusable.add(i, subSteps.get(i));
            }

            executeStep(i);
        }
    }

    /**
     * Execute reusable scenario
     *
     * @param reusableName name of scenario, that you want to use as a reusable step.
     *                     This scenario must be in src/main/resources/data/bpp/ReusableTestSteps.xml
     * @author Ruslan Levytskyi
     */
    public void executeReusable(String reusableName) {

        Reporter.node("Executing " + reusableName + " reusable scenario. ",
                "It contains " + getStepsOfReusableScenario(reusableName).size() + " reusable steps");
        reusable = getStepsOfReusableScenario(reusableName);

        for (int i = 0; i < reusable.size(); i++) {
            Reporter.log("Executing: " + step);
            executeStep(i);
        }
    }

    /**
     * Execute i-th step of reusable scenario
     *
     * @param i index of step of reusable scenario to execute
     * @author Ruslan Levytskyi
     */
    private void executeStep(int i) {
        step = reusable.get(i);
        arg1 = Tools.getQuotet(step, '"').get(0);
        List<String> arguments = Tools.getQuotet(step, '"');
        if (arguments.toArray().length == 1) {
            arg1 = arguments.get(0);
        } else if (arguments.toArray().length == 2) {
            arg1 = arguments.get(0);
            arg2 = arguments.get(1);
        } else if (arguments.toArray().length == 3) {
            arg1 = arguments.get(0);
            arg2 = arguments.get(1);
            arg3 = arguments.get(2);
        } else if (arguments.toArray().length == 4) {
            arg1 = arguments.get(0);
            arg2 = arguments.get(1);
            arg3 = arguments.get(2);
            arg4 = arguments.get(3);
        }

        for (String regx : stepDefsMap.keySet()) {
            if (reusable.get(i).matches(regx)) {
                stepDefsMap.get(regx).runReusable();
            }
        }
    }

    /**
     * Get list of steps of reusable scenario with specified name
     *
     * @param reusableName name of reusable scenario, steps of which you want to get.
     * @return list of steps of reusable scenario with specified name
     * @author Ruslan Levytskyi
     */
    private synchronized ArrayList<String> getStepsOfReusableScenario(String reusableName) {

        ArrayList<String> stepsList = new ArrayList<>();

        try {
            File inputFile = new File("src/main/resources/data/bpp/ReusableTestSteps.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            ArrayList<String> availableReusableStepsList = new ArrayList<>();

            Node reusablesNode = doc.getElementsByTagName("reusables").item(0);
            Element reusablesElement = (Element) reusablesNode;

            NodeList reusablesList = reusablesElement.getElementsByTagName("reusable");
            boolean isReusableExist = false;
            for (int i = 0; i < reusablesList.getLength(); i++) {
                Node reusableNode = reusablesList.item(i);
                Element reusableElement = (Element) reusableNode;
                availableReusableStepsList.add("Hi");
                if (reusableElement.getAttribute("name").equals(reusableName)) {
                    NodeList steps = reusableElement.getElementsByTagName("step");
                    for (int j = 0; j < steps.getLength(); j++) {
                        stepsList.add(steps.item(j).getTextContent());
                    }
                    isReusableExist = true;
                }
            }
            if (!isReusableExist) {
                Reporter.warn(reusableName + " reusable step does not exist. " +
                        "Here is a list of available reusable steps: ");
                for (String availStep : availableReusableStepsList) {
                    Reporter.log("  " + availStep);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stepsList;
    }

}
