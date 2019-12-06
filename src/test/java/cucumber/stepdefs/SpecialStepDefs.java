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
     * @param elementLocator name or value of needed element
     * @param elementType xpath template of needed element
     */
    @When("^I click on the \"([^\"]*)\" \"([^\"]*)\"$")
    public void i_click_on_element_with_parameter_special(String elementLocator, String elementType) {
        if(specialLocatorsMap.containsKey(elementType)) {
            String xpathTemplate = specialLocatorsMap.get(elementType);
            String resultingXpath = xpathTemplate.replace("PARAMETER",
                    TestParametersController.checkIfSpecialParameter(elementLocator));
            clickOnElement(By.xpath(resultingXpath),10000);
        } else {
            reporter.fail("No such locator template key");
        }
    }

    /**
     * Definition to click an element on the page
     *
     * @author Ruslan Levytskyi
     * @param elementType xpath template of needed element
     */
    @When("^I click on the \"([^\"]*)\"$")
    public void i_click_on_element_special(String elementType) {
        if(specialLocatorsMap.containsKey(elementType)) {
            String xpath = specialLocatorsMap.get(elementType);
            sleepFor(2000);
            clickOnElement(By.xpath(xpath),10000);
        } else {
            reporter.fail("No such locator key");
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

}
