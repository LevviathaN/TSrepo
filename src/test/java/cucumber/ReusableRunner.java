package cucumber;

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
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ReusableRunner {

//    private static StepDefinitions stepDefs;
//    private static ProductFactoryDefs pfStepDefs;
//
//    public ReusableRunner(){
//        stepDefs = new StepDefinitions();
//        pfStepDefs = new ProductFactoryDefs();
//    }

    public static void executeReusableAddSteps(String reusableName, Map<Integer, String> subSteps){
        StepDefinitions stepDefs = new StepDefinitions();
        ProductFactoryDefs pfStepDefs = new ProductFactoryDefs();
        System.out.println("Start executing \"" + reusableName + "\" reusable step");
        System.out.println("it contains " + getReusablePickleSteps(reusableName).size() + " reusable step");

        ArrayList<String> reusable = getReusablePickleSteps(reusableName);

        String step = "";
        for(int i = 0; i<reusable.size(); i++){
            ReporterManager.info("Executing: " + step);
            if(subSteps.containsKey(i)){
                ReporterManager.info("Adding \"" + subSteps.get(i) + "\" on the " + i + " position");
                reusable.add(i, subSteps.get(i));
            }

            step = reusable.get(i);
            String arg1, arg2 = "";
            arg1 = getQuotet(step, '"').get(0);
            if(getQuotet(step, '"').toArray().length==2){
                arg2 = getQuotet(step, '"').get(1);
            }

            //General stepdefs
            if(step.matches("^I am on \"([^\"]*)\" URL$")){
                stepDefs.i_am_on_url(arg1);
            }else if(step.matches("^I click on the \"([^\"]*)\" (?:button|link|option|element)(?: in [^\"]*)?$")){
                stepDefs.i_click_on_the_button(arg1);
            }else if(step.matches("^I click on the \"([^\"]*)\" (?:button|link|option) which is \"([^\"]*)\"$")){
                stepDefs.i_click_on_the_n_button(arg1, arg2);
            }else if(step.matches("^I click on the element by locator \"([^\"]*)\"$")){
                stepDefs.i_click_on_the_element_by_locator(arg1);
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

            //Product Factory stepdefs
            else if(step.matches("^I am logged into Product Factory as \"([^\"]*)\"$")){
                pfStepDefs.log_in_as(arg1);
            }else if(step.matches("^I fill the \"([^\"]*)\" PF field with \"([^\"]*)\"$")){
                pfStepDefs.fill_pf_field(arg1, arg2);
            }else if(step.matches("^I select \"([^\"]*)\" from PF dialog$")){
                pfStepDefs.select_from_dialog(arg1);
            }
        }
    }

    public static void executeReusable(String reusableName){
        StepDefinitions stepDefs = new StepDefinitions();
        ProductFactoryDefs pfStepDefs = new ProductFactoryDefs();
        System.out.println("Start executing \"" + reusableName + "\" reusable step");
        System.out.println("it contains " + getReusablePickleSteps(reusableName).size() + " reusable step");

        for(String step : getReusablePickleSteps(reusableName)){
            ReporterManager.info("Executing: " + step);

            String arg1, arg2 = "";
            arg1 = getQuotet(step, '"').get(0);
            if(getQuotet(step, '"').toArray().length==2){
                arg2 = getQuotet(step, '"').get(1);
            }

            //General stepdefs
            if(step.matches("^I am on \"([^\"]*)\" URL$")){
                stepDefs.i_am_on_url(arg1);
            }else if(step.matches("^I click on the \"([^\"]*)\" (?:button|link|option|element)(?: in [^\"]*)?$")){
                stepDefs.i_click_on_the_button(arg1);
            }else if(step.matches("^I click on the \"([^\"]*)\" (?:button|link|option) which is \"([^\"]*)\"$")){
                stepDefs.i_click_on_the_n_button(arg1, arg2);
            }else if(step.matches("^I click on the element by locator \"([^\"]*)\"$")){
                stepDefs.i_click_on_the_element_by_locator(arg1);
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

            //Product Factory stepdefs
            else if(step.matches("^I am logged into Product Factory as \"([^\"]*)\"$")){
                pfStepDefs.log_in_as(arg1);
            }else if(step.matches("^I fill the \"([^\"]*)\" PF field with \"([^\"]*)\"$")){
                pfStepDefs.fill_pf_field(arg1, arg2);
            }else if(step.matches("^I select \"([^\"]*)\" from PF dialog$")){
                pfStepDefs.select_from_dialog(arg1);
            }
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

    private static ArrayList<String> getReusablePickleSteps(String pickleFullName){

        String pickleName = pickleFullName;
        ArrayList<String> stepsList = new ArrayList<>();

        try{
            File inputFile = new File("src/main/resources/data/bpp/ReusableTestSteps.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            Node reusablesNode = doc.getElementsByTagName("reusables").item(0);
            Element reusablesElement = (Element) reusablesNode;

            NodeList reusablesList = reusablesElement.getElementsByTagName("reusable");
            for (int i = 0; i < reusablesList.getLength(); i++) {
                Node reusableNode = reusablesList.item(i);
                Element reusableElement = (Element) reusableNode;
                if(reusableElement.getAttribute("name").equals(pickleName)){
                    NodeList steps = reusableElement.getElementsByTagName("step");
                    for (int j = 0; j < steps.getLength(); j++){
                        stepsList.add(steps.item(j).getTextContent());
                    }
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return stepsList;
    }

//    public static void main(String[] args){
//        executeReusable("Log In");
//    }
}
