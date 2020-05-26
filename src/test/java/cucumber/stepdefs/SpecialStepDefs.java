package cucumber.stepdefs;

import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.testng.Assert;
import ui.pages.BasePage;
import ui.utils.Conditions;
import ui.utils.BPPLogManager;
import ui.utils.Reporter;
import ui.utils.UiHandlers;
import ui.utils.bpp.ExecutionContextHandler;
import ui.utils.bpp.TestParametersController;

public class SpecialStepDefs extends BasePage {

    /**
     * Definition to click an element on the page
     *
     * @author Ruslan Levytskyi
     * @param elementLocator name or value of needed element which replaces PARAMETER definiton in SpecialLocators.json
     * @param elementType xpath template of needed element
     */
    @When("^I click on the \"([^\"]*)\" \"([^\"]*)\"$")
    public void i_click_on_element_with_parameter_special(String elementLocator, String elementType) {
        Reporter.log("Executing step: I click on the '" + elementLocator + "' " + elementType);
        if(specialLocatorsMap.containsKey(elementType)) {
            String processedLocator = TestParametersController.checkIfSpecialParameter(elementLocator);
            String xpathTemplate = specialLocatorsMap.get(elementType);
            String resultingXpath = xpathTemplate.replaceAll("PARAMETER", processedLocator);
            BPPLogManager.getLogger().info("Clicking on: " + elementLocator + " element");
            clickOnElement(initElementLocator(resultingXpath),
                    UiHandlers.PF_SPINNER_HANDLER,
                    UiHandlers.ACCEPT_ALERT,
                    UiHandlers.PF_SCROLL_TO_ELEMENT_HANDLER,
                    UiHandlers.PF_SCROLL_HANDLER,
                    UiHandlers.PAGE_NOT_LOAD_HANDLER,
                    UiHandlers.SF_CLICK_HANDLER,
                    UiHandlers.WAIT_HANDLER,
                    UiHandlers.DEFAULT_HANDLER);
            if(!elementLocator.equals(processedLocator)){
                Reporter.log("<pre>[input test parameter] " + elementLocator + "' -> '" + processedLocator + "' [output value]</pre>");
            }
        } else {
            Reporter.fail("No such locator template key");
        }
    }

    /**
     * Definition to click an element on the page if given condition is true
     *
     * @author Ruslan Levytskyi
     * @param elementLocator name or value of needed element which replaces PARAMETER definiton in SpecialLocators.json
     * @param elementType xpath template of needed element
     */
    @When("^I click on the \"([^\"]*)\" \"([^\"]*)\" if \"([^\"]*)\" \"([^\"]*)\"$")
    public void i_click_on_element_with_parameter_special_if(String elementLocator, String elementType, String conditionParameter, String condition) {
        Conditions conditions = new Conditions();
        if(conditions.checkCondition(condition,conditionParameter)){
            Reporter.log("Executing step: I click on the '" + elementLocator + "' " + elementType);
            if(specialLocatorsMap.containsKey(elementType)) {
                String processedLocator = TestParametersController.checkIfSpecialParameter(elementLocator);
                String xpathTemplate = specialLocatorsMap.get(elementType);
                String resultingXpath = xpathTemplate.replaceAll("PARAMETER", processedLocator);
                clickOnElement(initElementLocator(resultingXpath),
                        UiHandlers.PF_SCROLL_HANDLER,
                        UiHandlers.ACCEPT_ALERT,
                        UiHandlers.PF_SCROLL_TO_ELEMENT_HANDLER,
                        UiHandlers.PAGE_NOT_LOAD_HANDLER,
                        UiHandlers.PF_SPINNER_HANDLER,
                        UiHandlers.DEFAULT_HANDLER);
                if(!elementLocator.equals(processedLocator)){
                    Reporter.log("<pre>[input test parameter] " + elementLocator + "' -> '" + processedLocator + "' [output value]</pre>");
                }
            } else {
                Reporter.fail("No such locator template key");
            }
        } else{
            Reporter.log("Condition " + conditionParameter + condition + " is not true, so '" + elementLocator + elementType + "' element step will not be clicked");
        }
    }

    /**
     * Definition to set text into the input element
     *
     * @author Ruslan Levytskyi
     * @param text text you want to send to the input element
     * @param elementLocator name or value of needed element
     * @param elementType xpath template of needed element
     */
    @When("^I set \"([^\"]*)\" text to the \"([^\"]*)\" \"([^\"]*)\"$")
    public void i_set_text_special(String text, String elementLocator, String elementType) {
        Reporter.log("Executing step: I set '" + text + "' text to '" + elementLocator + "' " + elementType);
        //Todo: simplify random dates generation and automatically create EC
        if(specialLocatorsMap.containsKey(elementType)) {
            String processedText = TestParametersController.checkIfSpecialParameter(text);
            String xpathTemplate = specialLocatorsMap.get(elementType);
            String resultingXpath = xpathTemplate.replaceAll("PARAMETER",
                    TestParametersController.checkIfSpecialParameter(elementLocator));
            setText(initElementLocator(resultingXpath), processedText);
            if(!text.equals(processedText)){
                BPPLogManager.getLogger().info("Setting " + processedText + " to " + elementLocator + " element");
                Reporter.log("<pre>[input test parameter] " + text + "' -> '" + processedText + "' [output value]</pre>");
            }
        } else {
            Reporter.fail("No such locator template key");
        }
    }

    /**
     * Definition to verify presence of element on the page
     *
     * @author Ruslan Levytskyi
     * @param elementLocator name or value of needed element
     * @param elementType xpath template of needed element
     */
    @When("^I should see the \"([^\"]*)\" \"([^\"]*)\"$")
    public void i_should_see_special(String elementLocator, String elementType) {
        Reporter.log("Executing step: I should see the '" + elementLocator + "' " + elementType);
        if(specialLocatorsMap.containsKey(elementType)) {
            String xpathTemplate = specialLocatorsMap.get(elementType);
            String resultingXpath = xpathTemplate.replaceAll("PARAMETER",
                    TestParametersController.checkIfSpecialParameter(elementLocator));
            BPPLogManager.getLogger().info("Validating presence of " + elementLocator);
            Assert.assertTrue(isElementPresentAndDisplay(initElementLocator(resultingXpath)));
        } else {
            Reporter.fail("No such locator template key");
        }
    }

    /**
     * Definition to verify that a certain number of elements are present on the page
     *
     * @author Ruslan Levytskyi
     * @param elementLocator name or value of needed element
     * @param elementType xpath template of needed element
     * @param expectedQuantity expected quantity of elements to be present on the page
     */
    @When("^I should see \"([^\"]*)\" \"([^\"]*)\" in quantity of \"([^\"]*)\"$")
    public void i_should_see_number_of_elements_special(String elementLocator, String elementType, String expectedQuantity) {
        Reporter.log("Executing step: I should see the '" + elementLocator + "' " + elementType);
        if(specialLocatorsMap.containsKey(elementType)) {
            String xpathTemplate = specialLocatorsMap.get(elementType);
            String resultingXpath = xpathTemplate.replaceAll("PARAMETER",
                    TestParametersController.checkIfSpecialParameter(elementLocator));
            BPPLogManager.getLogger().info("Validating the number of elements is " + expectedQuantity);
            Assert.assertTrue(expectedQuantity.equals(numberOfElements(initElementLocator(resultingXpath))));
            int actualNumberOfElements = numberOfElements(initElementLocator(resultingXpath));
            if (expectedQuantity.contains("more than")) {
                Assert.assertTrue(actualNumberOfElements > Integer.parseInt(expectedQuantity.substring(10)));
            } else if (expectedQuantity.contains("less than")) {
                Assert.assertTrue(actualNumberOfElements < Integer.parseInt(expectedQuantity.substring(10)));
            } else {
                Assert.assertTrue(expectedQuantity.equals(String.valueOf(actualNumberOfElements)));
            }
        } else {
            Reporter.fail("No such locator template key");
        }
    }

    /**
     * Definition to check invisibility of the element
     *
     * @param elementLocator name or value of needed element
     * @param elementType xpath template of needed element
     * @author Andrii Yakymchuk
     */
    @Then("I shouldn't see the \"([^\"]*)\" \"([^\"]*)\"$")
    public void i_should_not_see_the_element_special(String elementLocator, String elementType) {
        Reporter.log("Executing step: I should not see the '" + elementLocator + "' " + elementType);

        if(specialLocatorsMap.containsKey(elementType)) {
            String xpathTemplate = specialLocatorsMap.get(elementType);
            String resultingXpath = xpathTemplate.replaceAll("PARAMETER",
                    TestParametersController.checkIfSpecialParameter(elementLocator));
            if (checkIfElementNotExist(initElementLocator(resultingXpath))) {
                Assert.assertTrue(false, "Element with " + elementLocator + "' " + elementType + " text is not displayed");
            } else {
                Assert.assertTrue(true, "Element with " + elementLocator + "' " + elementType + " Shouldn't be displayed");
            }
        } else {
            Reporter.fail("No such locator template key");
        }
    }

    /**
     * Definition to verify presence of element on the page
     *
     * @author Ruslan Levytskyi
     * @param attributeName name of attribute of element you want to check
     * @param elementLocator name or value of needed element
     * @param elementType xpath template of needed element
     * @param attributeValue value that tested attribute should have
     */
    @When("^Attribute \"([^\"]*)\" of \"([^\"]*)\" \"([^\"]*)\" should have value \"([^\"]*)\"$")
    public void elements_attribute_should_have_value_special(String attributeName, String elementLocator, String elementType, String attributeValue) {
        Reporter.log("Executing step: Attribute '" + attributeName + "' of '" + elementLocator + "' " + elementType + " should have value '" + attributeValue + "'");
        if(specialLocatorsMap.containsKey(elementType)) {
            String xpathTemplate = specialLocatorsMap.get(elementType);
            String resultingXpath = xpathTemplate.replaceAll("PARAMETER",
                    TestParametersController.checkIfSpecialParameter(elementLocator));
            String actualAttributeValue = findElement(initElementLocator(resultingXpath)).getAttribute(attributeName);
            Assert.assertTrue(actualAttributeValue.equalsIgnoreCase(TestParametersController.checkIfSpecialParameter(attributeValue)));
        } else {
            Reporter.fail("No such locator template key");
        }
    }

    /**
     * Definition to check or uncheck the checkbox
     * If svalue is check, but the checkbox is unchecked, than this method checks the checkbox
     * Vice versa.
     *
     * @param value = value to set a statement to the checkbox. Can be "check" or "uncheck"
     * @param elementLocator locator of checkbox
     */
    @When("^I \"(check|uncheck)\" \"([^\"]*)\" \"([^\"]*)\"$")
    public void i_check_uncheck_special(String value, String elementLocator, String elementType){
        Reporter.log("Executing step: I " + value + " '" + elementLocator + "' " + elementType);
        boolean state = true;
        if(value.equals("check")){state = true;}
        else if(value.equals("uncheck")){state = false;}
        if(specialLocatorsMap.containsKey(elementType)) {
            String processedLocator = TestParametersController.checkIfSpecialParameter(elementLocator);
            String xpathTemplate = specialLocatorsMap.get(elementType);
            String resultingXpath = xpathTemplate.replaceAll("PARAMETER", processedLocator);
            checkCheckbox(initElementLocator(resultingXpath),state,
                    UiHandlers.PF_SPINNER_HANDLER,
                    UiHandlers.ACCEPT_ALERT,
                    UiHandlers.PF_SCROLL_TO_ELEMENT_HANDLER,
                    UiHandlers.PF_SCROLL_HANDLER,
                    UiHandlers.PAGE_NOT_LOAD_HANDLER,
                    UiHandlers.SF_CLICK_HANDLER,
                    UiHandlers.WAIT_HANDLER,
                    UiHandlers.DEFAULT_HANDLER);
            if(!elementLocator.equals(processedLocator)){
                Reporter.log("<pre>[input test parameter] " + elementLocator + "' -> '" + processedLocator + "' [output value]</pre>");
            }
        } else {
            Reporter.fail("No such locator template key");
        }
    }

    @And("^I select \"([^\"]*)\" from \"([^\"]*)\" \"([^\"]*)\"$")
    public void i_select_from_element_special(String value, String elementLocator, String elementType) {
        Reporter.log("Executing step: I select: " + value + " from " + elementLocator + "' " + elementType);
        if(specialLocatorsMap.containsKey(elementType)) {
            String processedLocator = TestParametersController.checkIfSpecialParameter(elementLocator);
            String xpathTemplate = specialLocatorsMap.get(elementType);
            String resultingXpath = xpathTemplate.replaceAll("PARAMETER", processedLocator);

            if (value.equals("KW_AUTO_SELECT")) {
                Reporter.log("Starting random selection from dropdown.");
                String autoSelectedValue = autoSelectFromDropdown(initElementLocator(resultingXpath));
                Reporter.log("Selected \"" + autoSelectedValue + "\" value from " + elementLocator + "' " + elementType);
            } else if (value.contains("EC_")) {
                Reporter.log("Selecting value using Execution Context");
                String executionContextValue = ExecutionContextHandler.getExecutionContextValueByKey(value);
                selectValueFromDropDown(initElementLocator(resultingXpath), executionContextValue);
                Reporter.log("Selected \"" + executionContextValue + "\" value from " + elementLocator + "' " + elementType);
            } else {
                Reporter.log("Selecting \"" + value + "\" value from " + elementLocator + "' " + elementType);
                selectValueFromDropDown(initElementLocator(resultingXpath), value);
            }
        }else {
            Reporter.fail("No such locator template key");
        }
    }

    /**
     * Definition to execute JS code for some particular element
     *
     * @param jsCode JS code to execute
     * @author Ruslan Levytskyi
     */
    @And("^I execute \"([^\"]*)\" JS code for \"([^\"]*)\" \"([^\"]*)\"$")
    public void i_execute_js_code_for_element_special(String jsCode, String elementLocator, String elementType) {
        Reporter.log("Executing JS code: " + jsCode);
        if(specialLocatorsMap.containsKey(elementType)) {
            String processedLocator = TestParametersController.checkIfSpecialParameter(elementLocator);
            String xpathTemplate = specialLocatorsMap.get(elementType);
            String resultingXpath = xpathTemplate.replaceAll("PARAMETER", processedLocator);
            isElementPresentAndDisplay(initElementLocator(resultingXpath));
            BPPLogManager.getLogger().info("Executing JS code for: " + elementLocator + " element");
            executeJSCodeForElement(initElementLocator(resultingXpath),jsCode);
            if(!elementLocator.equals(processedLocator)){
                Reporter.log("<pre>[input test parameter] " + elementLocator + "' -> '" + processedLocator + "' [output value]</pre>");
            }
        } else {
            Reporter.fail("No such locator template key");
        }
    }
}
