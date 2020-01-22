package ui.utils;

import ui.pages.BasePage;
import ui.utils.bpp.TestParametersController;

public class Conditions extends BasePage {

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
