package cucumber;

import cucumber.stepdefs.SpecialStepDefs;
import cucumber.stepdefs.productFactoryStepDefs.ProductFactoryDefs;
import cucumber.stepdefs.StepDefinitions;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import ui.utils.ReporterManager;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class ReusableRunner {

    private static StepDefinitions stepDefs = new StepDefinitions();
    private static SpecialStepDefs specialStepDefs = new SpecialStepDefs();
    private static ProductFactoryDefs pfStepDefs = new ProductFactoryDefs();
    private static String step = "";
    private static ArrayList<String> reusable;
    private static ReporterManager reporter = ReporterManager.Instance;

    public static void executeReusableAddSteps(String reusableName, Map<Integer, String> subSteps){
        StepDefinitions stepDefs = new StepDefinitions();
        ProductFactoryDefs pfStepDefs = new ProductFactoryDefs();
        reporter.info("Start executing \"" + reusableName + "\" reusable step");
        reporter.info("it contains " + getReusableReusableSteps(reusableName).size() + " reusable step");

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
        StepDefinitions stepDefs = new StepDefinitions();
        ProductFactoryDefs pfStepDefs = new ProductFactoryDefs();
        reporter.info("Start executing \"" + reusableName + "\" reusable step");
        reporter.info("it contains " + getReusableReusableSteps(reusableName).size() + " reusable step");

        reusable = getReusableReusableSteps(reusableName);

        for(int i = 0; i<reusable.size(); i++){
            reporter.info("Executing: " + step);

            executeStep(i);
        }
    }

    private static void executeStep(int i){
        step = reusable.get(i);
        String arg1 = "";
        String arg2 = "";
        String arg3 = "";
        arg1 = getQuotet(step, '"').get(0);
        if(getQuotet(step, '"').toArray().length==2){
            arg2 = getQuotet(step, '"').get(1);
        }
        if(getQuotet(step, '"').toArray().length==3){
            arg3 = getQuotet(step, '"').get(2);
        }

        //General stepdefs
        if(step.matches("^I am on \"([^\"]*)\" URL$")){
            stepDefs.i_am_on_url(arg1);
        }else if(step.matches("^I click on the \"([^\"]*)\" (?:button|link|option|element)(?: in [^\"]*)?$")){
            stepDefs.i_click_on_the_button(arg1);
        }else if(step.matches("^I click on the \"([^\"]*)\" (?:button|link|option) which is \"([^\"]*)\"$")){
            stepDefs.i_click_on_the_n_button(arg1, arg2);
        }else if(step.matches("^I fill the \"([^\"]*)\" field with \"([^\"]*)\"$")){
            stepDefs.fill_field(arg1, arg2);
        }else if(step.matches("^I wait for \"([^\"]*)\" seconds$")){
            stepDefs.wait_for(arg1);
        }else if(step.matches("^I hover over the \"([^\"]*)\" (?:button|link|option|element)$")){
            stepDefs.hover_over(arg1);
        }else if(step.matches("^I should see the \"([^\"]*)\" (?:button|message|element)$")){
            stepDefs.i_should_see_the_text(arg1);
        }else if(step.matches("^I should be redirected to the \"([^\"]*)\" page$")){
            stepDefs.i_should_be_redirected_to_page(arg1);
        }else if(step.matches("^I execute \"([^\"]*)\" reusable step$")){
            stepDefs.i_execute_reusable_step(arg1);
        }else if(step.matches("^I remember \"([^\"]*)\" text as \"([^\"]*)\" variable$")){
            stepDefs.i_remember_text(arg1, arg2);
        }

        //Special stepdefs
        else if(step.matches("^I click on the \"([^\"]*)\" \"([^\"]*)\"$")){
            specialStepDefs.i_click_on_element_with_parameter_special(arg1, arg2);
        }else if(step.matches("^I click on the \"([^\"]*)\"$")){
            specialStepDefs.i_click_on_element_special(arg1);
        }else if(step.matches("^I set \"([^\"]*)\" text to the \"([^\"]*)\" \"([^\"]*)\"$")){
            specialStepDefs.i_set_text_special(arg1, arg2, arg3);
        }else if(step.matches("^I should see \"([^\"]*)\" \"([^\"]*)\"$")){
            specialStepDefs.i_should_see_special(arg1, arg2);
        }

        //Product Factory stepdefs
        else if(step.matches("^I am logged into Product Factory as \"([^\"]*)\"$")){
            pfStepDefs.log_in_as(arg1);
        }else if(step.matches("^I fill the \"([^\"]*)\" PF field with \"([^\"]*)\"$")){
            pfStepDefs.fill_pf_field(arg1, arg2);
        }else if(step.matches("^I select \"([^\"]*)\" from PF dialog$")){
            pfStepDefs.select_from_dialog(arg1);
        }else if(step.matches("^I \"([^\"]*)\" \"([^\"]*)\" PF checkbox$")){
            pfStepDefs.check_uncheck(arg1, arg2);
        }
    }

    private static List<String> getQuotet(final String input, final char quote) {
        final ArrayList<String> result = new ArrayList<>();
        int n = -1;
        for(int i = 0; i < input.length(); i++) {
            if(input.charAt(i) == quote) {
                if(n == -1) { //not currently inside quote -> start new quote
                    n = i + 1;
                } else { //close current quote
                    result.add(input.substring(n, i));
                    n = -1;
                }
            }
        }
        return result;
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
