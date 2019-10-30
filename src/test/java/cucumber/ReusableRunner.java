package cucumber;

import cucumber.stepdefs.ProductFactoryDefs;
import cucumber.stepdefs.StepDefinitions;

import java.util.ArrayList;
import java.util.List;


public class ReusableRunner {
    public static void executeReusable(String reusableName){
        StepDefinitions stepDefs = new StepDefinitions();
        ProductFactoryDefs pfStepDefs = new ProductFactoryDefs();
        ReusableHelper helper = new ReusableHelper();
        System.out.println("Start executing \"" + reusableName + "\" reusable step");
        System.out.println("it contains " + helper.getReusablePickleSteps(reusableName).size() + " reusable step");

        for(String step : helper.getReusablePickleSteps(reusableName)){
            System.out.println("Executing: " + step);

            String arg1, arg2 = "";
            arg1 = getQuotet(step, '"').get(0);
            if(getQuotet(step, '"').toArray().length==2){
                arg2 = getQuotet(step, '"').get(1);
            }

            //General stepdefs
            if(step.matches("^I am on \"([^\"]*)\" URL$")){
                stepDefs.i_am_on_url(arg1);
            }else if(step.matches("^I click on the \"([^\"]*)\" (?:button|link|option)(?: in [^\"]*)?$")){
                stepDefs.i_click_on_the_button(arg1);
            }else if(step.matches("^I click on the \"([^\"]*)\" (?:button|link|option) which is \"([^\"]*)\"$")){
                stepDefs.i_click_on_the_n_button(arg1, arg2);
            }else if(step.matches("^I fill the \"([^\"]*)\" field with \"([^\"]*)\"$")){
                stepDefs.fill_field(arg1, arg2);
            }else if(step.matches("^I wait for \"([^\"]*)\" seconds$")){
                stepDefs.wait_for(arg1);
            }else if(step.matches("^I should be redirected to the \"([^\"]*)\" page$")){
                stepDefs.i_should_be_redirected_to_page(arg1);
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

    public static List<String> getQuotet(final String input, final char quote) {
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

//    public static void main(String[] args){
//        executeReusable("Log In");
//    }
}
