package ui.utils;

import ui.utils.specialDataHandlers.TestParametersController;

/**
 * @author rlevytskyi
 * <p> Allows to create and check different condisions. Currently is used only in StepDefinitions </p>
 *
 */
public class Conditions extends SeleniumHelper {

    /**
     * Method to check, if given condition is true.
     * @param condition - logical statement to check
     * @param conditionParameter - subject, to which logical statement is applied
     *
     * For example, your condition is "element is present". Then, your conditionParameter will be some element on the page
     * "Bodies element is present", "Save button element is present"
     */
    public boolean checkCondition(String condition, String conditionParameter){
        switch (condition) {
            case "element is present":
                return isElementPresentAndDisplay(initElementLocator(conditionParameter));
            case "element is absent":
                return !isElementPresentAndDisplay(initElementLocator(conditionParameter));
            case "EC variable is needed":
                return !conditionParameter.equals(TestParametersController.checkIfSpecialParameter(conditionParameter));
            default:
                return false;
        }
    }
}
