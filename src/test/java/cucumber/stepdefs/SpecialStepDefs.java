package cucumber.stepdefs;

import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.testng.Assert;
import ui.pages.BasePage;
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
        if(specialLocatorsMap.containsKey(elementType)) {
            String xpathTemplate = specialLocatorsMap.get(elementType);
            String resultingXpath = xpathTemplate.replace("PARAMETER",
                    TestParametersController.checkIfSpecialParameter(elementLocator));
            isElementPresentAndDisplay(By.xpath(resultingXpath));
            clickOnElement(By.xpath(resultingXpath),10000);
        } else {
            reporter.fail("No such locator template key");
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
        if(specialLocatorsMap.containsKey(elementType)) {
            String xpathTemplate = specialLocatorsMap.get(elementType);
            String resultingXpath = xpathTemplate.replace("PARAMETER",
                    TestParametersController.checkIfSpecialParameter(elementLocator));
            setText(By.xpath(resultingXpath), TestParametersController.checkIfSpecialParameter(text));
        } else {
            reporter.fail("No such locator template key");
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
        if(specialLocatorsMap.containsKey(elementType)) {
            String xpathTemplate = specialLocatorsMap.get(elementType);
            String resultingXpath = xpathTemplate.replace("PARAMETER",
                    TestParametersController.checkIfSpecialParameter(elementLocator));
            Assert.assertTrue(isElementPresentAndDisplay(By.xpath(resultingXpath)));
        } else {
            reporter.fail("No such locator template key");
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
        if(specialLocatorsMap.containsKey(elementType)) {
            String xpathTemplate = specialLocatorsMap.get(elementType);
            String resultingXpath = xpathTemplate.replace("PARAMETER",
                    TestParametersController.checkIfSpecialParameter(elementLocator));
            Assert.assertTrue(findElement(By.xpath(resultingXpath)).getAttribute(attributeName).equalsIgnoreCase(attributeValue));
        } else {
            reporter.fail("No such locator template key");
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
        boolean state = true;
        if(value.equals("check")){state = true;}
        else if(value.equals("uncheck")){state = false;}
        if(specialLocatorsMap.containsKey(elementType)) {
            String xpathTemplate = specialLocatorsMap.get(elementType);
            String resultingXpath = xpathTemplate.replace("PARAMETER",
                    TestParametersController.checkIfSpecialParameter(elementLocator));
            checkCheckbox(By.xpath(resultingXpath),state);
        } else {
            reporter.fail("No such locator template key");
        }
    }

}
