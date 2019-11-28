package cucumber;

import cucumber.stepdefs.SpecialStepDefs;
import cucumber.stepdefs.productFactoryStepDefs.ProductFactoryDefs;
import cucumber.stepdefs.StepDefinitions;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import ui.utils.ReporterManager;
import ui.utils.Tools;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import static cucumber.StepPatterns.*;


public class ReusableRunner {

    private static StepDefinitions stepDefs = new StepDefinitions();
    private static SpecialStepDefs specialStepDefs = new SpecialStepDefs();
    private static ProductFactoryDefs pfStepDefs = new ProductFactoryDefs();

    private static ArrayList<String> reusable;
    private static ReporterManager reporter = ReporterManager.Instance;

    public static HashMap<String, RunReusable> stepDefsMap = new HashMap<>();

    private static String step = "";
    private static String arg1 = "";
    private static String arg2 = "";
    private static String arg3 = "";

    public static void executeReusableAddSteps(String reusableName, Map<Integer, String> subSteps){
        reporter.info("Start executing \"" + reusableName + "\" reusable step. " +
                "It contains" + getReusableReusableSteps(reusableName).size() + " reusable steps");

        reusable = getReusableReusableSteps(reusableName);

        for(int i = 0; i<reusable.size(); i++){
            reporter.info("Executing: " + step);
            if(subSteps.containsKey(i)){
                reporter.info("Adding \"" + subSteps.get(i) + "\" on the " + i + " position");
                reusable.add(i, subSteps.get(i));
            }

            executeStep(i);
        }
    }

    public static void executeReusable(String reusableName){
        reporter.info("Start executing \"" + reusableName + "\" reusable step. " +
                "It contains" + getReusableReusableSteps(reusableName).size() + " reusable steps");

        reusable = getReusableReusableSteps(reusableName);

        for(int i = 0; i<reusable.size(); i++){
            reporter.info("Executing: " + step);

            executeStep(i);
        }
    }

    private static void executeStep(int i){
        step = reusable.get(i);
        arg1 = Tools.getQuotet(step, '"').get(0);
        if(Tools.getQuotet(step, '"').toArray().length==2){
            arg2 = Tools.getQuotet(step, '"').get(1);
        }
        if(Tools.getQuotet(step, '"').toArray().length==3){
            arg3 = Tools.getQuotet(step, '"').get(2);
        }

        //General stepdefs
        stepDefsMap.put(I_CLICK_ON_THE_BUTTON.getPattern(),() -> stepDefs.i_click_on_the_button(arg1));
        stepDefsMap.put(I_AM_ON_URL.getPattern(),() -> stepDefs.i_am_on_url(arg1));
        stepDefsMap.put(I_CLICK_ON_THE_N_BUTTON.getPattern(),() -> stepDefs.i_click_on_the_n_button(arg1, arg2));
        stepDefsMap.put(FILL_THE_FIELD.getPattern(),() -> stepDefs.fill_field(arg1, arg2));
        stepDefsMap.put(WAIT_FOR.getPattern(),() -> stepDefs.wait_for(arg1));
        stepDefsMap.put(HOVER_OVER.getPattern(),() -> stepDefs.hover_over(arg1));
        stepDefsMap.put(I_SHOULD_SEE_THE_TEXT.getPattern(),() -> stepDefs.i_should_see_the_text(arg1));
        stepDefsMap.put(I_SHOULD_BE_REDIRECTED_TO_THE_PAGE.getPattern(),() -> stepDefs.i_should_be_redirected_to_page(arg1));
        stepDefsMap.put(I_EXECUTE_REUSABLE_STEP.getPattern(),() -> stepDefs.i_execute_reusable_step(arg1));
        stepDefsMap.put(I_REMEMBER_TEXT.getPattern(),() -> stepDefs.i_remember_text(arg1, arg2));

        //Special stepdefs
        stepDefsMap.put(I_CLICK_ON_ELEMENT_WITH_PARAMETER_SPECIAL.getPattern(),() -> specialStepDefs.i_click_on_element_with_parameter_special(arg1, arg2));
        stepDefsMap.put(I_CLICK_ON_ELEMENT_SPECIAL.getPattern(),() -> specialStepDefs.i_click_on_element_special(arg1));
        stepDefsMap.put(I_SET_TEXT_SPECIAL.getPattern(),() -> specialStepDefs.i_set_text_special(arg1, arg2, arg3));
        stepDefsMap.put(I_SHOULD_SEE_SPECIAL.getPattern(),() -> specialStepDefs.i_should_see_special(arg1, arg2));

        //Product Factory stepdefs
        stepDefsMap.put(I_AM_LOGGED_IN_PF_AS.getPattern(),() -> pfStepDefs.log_in_as(arg1));
        stepDefsMap.put(I_FILL_PF_FIELD.getPattern(),() -> pfStepDefs.fill_pf_field(arg1, arg2));
        stepDefsMap.put(I_SELECT_FROM_DIALOG.getPattern(),() -> pfStepDefs.select_from_dialog(arg1));
        stepDefsMap.put(CHECK_UNCHECK.getPattern(),() -> pfStepDefs.check_uncheck(arg1, arg2));

        for(String regx : stepDefsMap.keySet()){
            if (reusable.get(i).matches(regx)){
                stepDefsMap.get(regx).runReusable();
            }
        }
    }

    private static ArrayList<String> getReusableReusableSteps(String reusableName){

        ArrayList<String> stepsList = new ArrayList<>();

        try{
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
                if(reusableElement.getAttribute("name").equals(reusableName)){
                    NodeList steps = reusableElement.getElementsByTagName("step");
                    for (int j = 0; j < steps.getLength(); j++){
                        stepsList.add(steps.item(j).getTextContent());
                    }
                    isReusableExist = true;
                }
            }
            if(!isReusableExist){
                reporter.info(reusableName + " reusable step does not exist");
                reporter.info("Here is a list of available reusable steps:");
                for(String availStep : availableReusableStepsList){
                    reporter.info("  " + availStep);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return stepsList;
    }

}
