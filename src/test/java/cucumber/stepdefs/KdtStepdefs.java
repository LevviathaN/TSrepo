package cucumber.stepdefs;

//import cucumber.api.java.en.*;
import io.cucumber.java.en.*;
import org.hamcrest.Matchers;
import org.openqa.selenium.By;
import pages.BasePage;
import utils.ReporterManager;
import utils.bpp.ExecutionContextHandler;
import utils.bpp.SeleniumHelper;
import utils.bpp.TestParametersController;

import static org.hamcrest.MatcherAssert.assertThat;

import static com.jcabi.matchers.RegexMatchers.matchesPattern;
import static org.hamcrest.Matchers.*;

public class KdtStepdefs extends BasePage {

    SeleniumHelper helper = SeleniumHelper.getInstance();
    ReporterManager reporter = ReporterManager.Instance;

    @Given("^I am on \"([^\"]*)\" BPP URL$")
    public void i_am_on_url(String url) {
        helper.goToURL(TestParametersController.checkIfSpecialParameter(url));
        helper.waitForPageLoad(helper.getIntLongTimeOut());
    }

    @When("^I click on the BPP element located by \"([^\"]*)\"$")
    public void i_click_on_the_button(String elementLocatorByPropertyName) {
        elementLocatorByPropertyName = TestParametersController.checkIfSpecialParameter(elementLocatorByPropertyName);
        helper.clickElement(elementLocatorByPropertyName, "");
    }

    @When("^I fill the \"([^\"]*)\" BPP field with \"([^\"]*)\"$")
    public void fill_field(String elementLocatorByPropertyName, String value){
        elementLocatorByPropertyName = TestParametersController.checkIfSpecialParameter(elementLocatorByPropertyName);
        value = TestParametersController.checkIfSpecialParameter(value);
        helper.enterField(elementLocatorByPropertyName, value);
    }

    @Then("^I capture data from \"([^\"]*)\" BPP element into \"([^\"]*)\" variable$")
    public void capture_data(String elementLocatorByPropertyName, String value){
        elementLocatorByPropertyName = TestParametersController.checkIfSpecialParameter(elementLocatorByPropertyName);
        value = TestParametersController.checkIfSpecialParameter(value);
        reporter.info("Capturing data from: " + elementLocatorByPropertyName);
        String textData = helper.getData(elementLocatorByPropertyName);

        /**
         * Add the textData value to the ExecutionContext list to be used further.
         * Get textData from ExecutionContext into html report
         */
        if (!value.equals("")) {
            if (textData.equals("")) {
                reporter.info("Saving EC key " + value + " with an empty string. No application data found.");
            } else {
                reporter.info("Saving EC key " + value + " = " + textData);
            }
            ExecutionContextHandler.setExecutionContextValueByKey(value, textData);
        } else
            reporter.info("Cannot save EC value with an empty key. Check your parameters.");
    }

    @When("^I wait for \"([^\"]*)\" seconds at BPP page$")
    public void wait_for(String value) {
        value = TestParametersController.checkIfSpecialParameter(value);
        reporter.info("Waiting for : " + value + " seconds...");
        helper.unconditionalWait(value);
        reporter.info("Wait time elapsed!");
    }

    @Then("^I verify that element located by \"([^\"]*)\" is not visible on BPP page$")
    public void i_verify_elements_absense(String elementLocatorByPropertyName) {
        elementLocatorByPropertyName = TestParametersController.checkIfSpecialParameter(elementLocatorByPropertyName);
        assertThat(SeleniumHelper.getInstance().isElementVisible(elementLocatorByPropertyName), is(false) );
        reporter.info("Element: " + elementLocatorByPropertyName + " does not exist on the following page.");

    }

    @Then("^I verify that BPP element \"([^\"]*)\" contains text \"([^\"]*)\"$")
    public void i_verify_text(String elementLocatorByPropertyName, String value) {
        elementLocatorByPropertyName = TestParametersController.checkIfSpecialParameter(elementLocatorByPropertyName);
        value = TestParametersController.checkIfSpecialParameter(value);
        String actualValue = "";
        actualValue = helper.getTextValueFromField(elementLocatorByPropertyName);
        reporter.info("Validating data from: " + elementLocatorByPropertyName
                + " to match the expected value '" + value + "'");

        String newValue = value;

        // Capability to accept Regular Expression, Substring and exact string
        // Usages:
        // RE=^Starting.*Ending$
        // CONTAINS=substring
        // exact string
        // CI=some string
        if (value.toUpperCase().trim().startsWith("RE=")) {
            newValue = newValue.substring("RE=".length());
            assertThat(actualValue.trim(), matchesPattern(newValue ));
            reporter.info("Actual value '" + actualValue + "' matches the pattern " + "'" +newValue +"'");
        } else if (value.toUpperCase().startsWith("CONTAINS=")) {
            newValue = newValue.substring("CONTAINS=".length());
            if (value.contains("EC")) {
                String executionContextValue = ExecutionContextHandler.getExecutionContextValueByKey(newValue);
                assertThat(actualValue.trim().toLowerCase(), Matchers.containsString(executionContextValue.toLowerCase()));
            } else {
                assertThat(actualValue.trim(), Matchers.containsString(newValue));
                reporter.info("Actual value '" + actualValue + "' contains the string " + "'" + newValue + "'");
            }
        } else if(value.toUpperCase().startsWith("NOT_CONTAINS=")) {
            newValue = newValue.substring("NOT_CONTAINS=".length());
            if (value.contains("EC")) {
                String executionContextValue = ExecutionContextHandler.getExecutionContextValueByKey(newValue);
                assertThat(actualValue.trim(), not(Matchers.containsString(executionContextValue)));
            } else {
                assertThat(actualValue.trim(), not(Matchers.containsString(newValue)));
                reporter.info("Actual value '" + actualValue + "' not contains the string " + "'" + newValue + "'");
            }
        } else if (value.toUpperCase().startsWith("CASE=")) {
            newValue = newValue.substring("CASE=".length());
            assertThat(actualValue.trim(), Matchers.equalTo(newValue));
            reporter.info("Actual value '" + actualValue + "' equals to the case sensitive string " + "'" +newValue +"'");
        } else if (value.toUpperCase().contains("STARTS-WITH=")) {
            newValue = newValue.substring("STARTS-WITH=".length());
            assertThat(actualValue.trim(), Matchers.startsWith(newValue));
            reporter.info("Actual value '" + actualValue + "' starts with case sensitive string " + "'" +newValue +"'");
        }else {
            assertThat(actualValue.trim(), Matchers.equalToIgnoringWhiteSpace(value));
            reporter.info("Actual value '" + actualValue + "' equals to the case insensitive string " + "'" +newValue +"'");
        }
    }

    @When("^I SmartClient click on BPP element \"([^\"]*)\"$")
    public void i_smart_click(String element) {
        element = TestParametersController.checkIfSpecialParameter(element);
        clickOnAnyElement(byText(element));
    }

    @When("^I confirm BPP JS Alert \"([^\"]*)\"$")
    public void i_confirm_js_alert(String value) {
        value = TestParametersController.checkIfSpecialParameter(value);
        String expectedAlertMsg = value.trim();

        String alertMessage = helper.acceptAlertMessage().trim();

        if (expectedAlertMsg != "") {
            assertThat(alertMessage, containsString(expectedAlertMsg));
            reporter.info("Actual alert message '" + alertMessage + "' matches the expected one.");
        } else {
            reporter.info("Alert accepted");
        }
    }

    @When("^I press the \"([^\"]*)\" key on the keyboard for BPP$")
    public void i_press_key(String value) {
        value = TestParametersController.checkIfSpecialParameter(value);
        reporter.info("Pressing key " + value + " from keyboard ");
        helper.waitForPageLoad(helper.getIntLongTimeOut());
        helper.keyboardPress(value);
    }

    @When("^I execute \"([^\"]*)\" JS script for BPP$")
    public void i_execute_js(String value) {
        value = TestParametersController.checkIfSpecialParameter(value);
        reporter.info("Executing the following script: " + value);
        helper.executeScript(value);
    }

    @When("^I validate that BPP element \"([^\"]*)\" has \"([^\"]*)\"$")
    public void i_validate_element_attribute(String elementLocatorByPropertyName,String value) {
        elementLocatorByPropertyName = TestParametersController.checkIfSpecialParameter(elementLocatorByPropertyName);
        value = TestParametersController.checkIfSpecialParameter(value);//class=submit etc.
        String property = value.substring(0,value.indexOf("="));
        String expectedValue = value.substring(value.indexOf("=")+1);

        SeleniumHelper helper = SeleniumHelper.getInstance();
        String actualValue = helper.getPropertyValue(elementLocatorByPropertyName, property);

        if (expectedValue.contains("EC")) {
            expectedValue = ExecutionContextHandler.getExecutionContextValueByKey(expectedValue);
        }

        if(actualValue.equalsIgnoreCase("NOT_AVAILABLE")){
            assertThat("The given property does not belong to the object", false);
        }

        assertThat("The actual property value:  " + actualValue
                + " is different from expected: " + expectedValue, actualValue.trim().toLowerCase(), Matchers.equalTo(expectedValue.toLowerCase()));

        reporter.info("Validating data from: " + elementLocatorByPropertyName
                + " to match the expected value '" + expectedValue + "'");
    }

    @When("^I switch \"([^\"]*)\" BPP checkbox to \"([^\"]*)\"$")
    public void i_check_checkbox(String elementLocatorByPropertyName, String value) {
        elementLocatorByPropertyName = TestParametersController.checkIfSpecialParameter(elementLocatorByPropertyName);
        value = TestParametersController.checkIfSpecialParameter(value);
        boolean isChecked = Boolean.parseBoolean(value);
        reporter.info("Setting the checkbox field with value: " + value + ": " + elementLocatorByPropertyName);
        SeleniumHelper helper = SeleniumHelper.getInstance();
        helper.checkUncheckCheckbox(elementLocatorByPropertyName, isChecked);
    }

    @When("^I select \"([^\"]*)\" from BPP dropdown \"([^\"]*)\"$")
    public void i_select_from_dropdown(String elementLocatorByPropertyName,String value) {
        elementLocatorByPropertyName = TestParametersController.checkIfSpecialParameter(elementLocatorByPropertyName);
        value = TestParametersController.checkIfSpecialParameter(value);
        if (value.equals("KW_AUTO_SELECT")) {
            reporter.info("Starting random selection from dropdown.");
            String autoSelectedValue = helper.autoSelectFromDropdown(elementLocatorByPropertyName);
            reporter.info("Selected \"" + autoSelectedValue + "\" value from: " + elementLocatorByPropertyName);
        } else {
            reporter.info("Selecting \"" + value + "\" value from: " + elementLocatorByPropertyName);
            helper.selectValueFromDropDown(elementLocatorByPropertyName, value);
        }
    }

    @When("^I switch to \"([^\"]*)\" BPP window$")
    public void i_switch_to_window(String value) {
        value = TestParametersController.checkIfSpecialParameter(value);
        if (value.length() > 0) {
            int index = Integer.parseInt(value.substring(0, 1));

            reporter.info("Switching to the window with index = " + index);
            SeleniumHelper helper = SeleniumHelper.getInstance();
            helper.switchToWindowByIndex(index);
        }
        else{
            reporter.info("REQUIRED 'WINDOW INDEX' PARAMETER IS MISSED");
        }
    }
}
