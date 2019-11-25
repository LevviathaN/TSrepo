package cucumber.stepdefs.productFactoryStepDefs;

import cucumber.api.java.en.*;
//import io.cucumber.java.en.*;
import ui.pages.bpp.product_factory.ProductFactoryBasePage;
import ui.utils.bpp.ExecutionContextHandler;
import ui.utils.bpp.TestParametersController;

/**
 * Created by Ruslan Levytskyi on 21/10/2019.
 */

public class ProductFactoryDefs extends ProductFactoryBasePage {

    @Given("^I am logged into Product Factory as \"([^\"]*)\"$")
    public void log_in_as(String role){
        logInAs(role);
    }

    @When("^I fill the \"([^\"]*)\" PF field with \"([^\"]*)\"$")
    public void fill_pf_field(String field, String text){
        findElement(pfField(field)).sendKeys(TestParametersController.checkIfSpecialParameter(text));
    }

    @When("^I select \"([^\"]*)\" from PF dialog$")
    public void select_from_dialog(String value){
        pfSelectFromModal(TestParametersController.checkIfSpecialParameter(value));
    }

    @When("^I edit \"([^\"]*)\"$")
    public void click_on_edit(String value){
        clickOnElement(pfEdit(TestParametersController.checkIfSpecialParameter(value)));
    }

    @When("^I \"(check|uncheck)\" \"([^\"]*)\" PF checkbox$")
    public void check_uncheck(String value, String element){
        boolean state = true;
        if(value.equals("check")){state = true;}
        else if(value.equals("uncheck")){state = false;}
        pfCheckbox(TestParametersController.checkIfSpecialParameter(element),state);
    }

    @When("^I turn \"([^\"]*)\" PF toggle \"([^\"]*)\"$")
    public void turn_toggle(String element, String value){
        boolean state = true;
        if(value.equals("on")){state = true;}
        else if(value.equals("off")){state = false;}
        pfToggle(element,state);
    }

    @Then("^I remember text of \"([^\"]*)\" PF field as \"([^\"]*)\" variable$")
    public void i_remember_text(String element, String varName) {
//        EXECUTION_CONTEXT.put(varName, findElement(pfField(element)).getText());

        ExecutionContextHandler.setExecutionContextValueByKey(varName,
                findElement(pfField(element)).getText());
    }

    @When("^I change \"([^\"]*)\" PF value$")
    public void i_change(String value){
        clickOnElement(pfChangeButton(TestParametersController.checkIfSpecialParameter(value)));
    }
}
