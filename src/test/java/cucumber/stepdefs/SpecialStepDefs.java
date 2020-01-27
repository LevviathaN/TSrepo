package cucumber.stepdefs;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.testng.Assert;
import ui.pages.BasePage;
import ui.utils.Conditions;
import ui.utils.BPPLogManager;
import ui.utils.Reporter;
import ui.utils.UiHandlers;
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
            isElementPresentAndDisplay(By.xpath(resultingXpath));
            BPPLogManager.getLogger().info("Clicking on: " + elementLocator + " element");
            scrollToElement(findElement(By.xpath(resultingXpath)));
            clickOnElement(By.xpath(resultingXpath), UiHandlers.PF_SPINNER_HANDLER, UiHandlers.ACCEPT_ALERT/*, UiHandlers.PF_SCROLL_HANDLER*/);
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
                isElementPresentAndDisplay(By.xpath(resultingXpath));
                clickOnElement(By.xpath(resultingXpath), UiHandlers.PF_SCROLL_HANDLER, UiHandlers.ACCEPT_ALERT, UiHandlers.PF_SPINNER_HANDLER);
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
            setText(By.xpath(resultingXpath), processedText);
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
            Assert.assertTrue(isElementPresentAndDisplay(By.xpath(resultingXpath)));
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
        Reporter.log("Executing step: I should see the '" + elementLocator + "' " + elementType);
        waitForPageToLoad();

        if(specialLocatorsMap.containsKey(elementType)) {
            String xpathTemplate = specialLocatorsMap.get(elementType);
            String resultingXpath = xpathTemplate.replaceAll("PARAMETER",
                    TestParametersController.checkIfSpecialParameter(elementLocator));
            if (checkIfElementNotExist(By.xpath(resultingXpath))) {
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
            Assert.assertTrue(findElement(By.xpath(resultingXpath)).getAttribute(attributeName).equalsIgnoreCase(attributeValue));
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
            scrollToElement(findElement(By.xpath(resultingXpath)));
            checkCheckbox(By.xpath(resultingXpath),state);
            if(!elementLocator.equals(processedLocator)){
                Reporter.log("<pre>[input test parameter] " + elementLocator + "' -> '" + processedLocator + "' [output value]</pre>");
            }
        } else {
            Reporter.fail("No such locator template key");
        }
    }

}
