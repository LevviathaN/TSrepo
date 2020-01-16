package cucumber;

import java.util.HashMap;
import java.util.Map;

public enum StepPatterns {

    I_AM_ON_URL("^I am on \"([^\"]*)\" URL$"),
    I_CLICK_ON_THE_BUTTON("^I click on the \"([^\"]*)\" (?:button|link|option|element)(?: in [^\"]*)?$"),
    FILL_THE_FIELD("^I fill the \"([^\"]*)\" field with \"([^\"]*)\"$"),
    WAIT_FOR("^I wait for \"([^\"]*)\" seconds$"),
    HOVER_OVER("^I hover over the \"([^\"]*)\" (?:button|link|option|element)$"),
    I_SHOULD_SEE_THE_TEXT("^I should see the \"([^\"]*)\" (?:button|message|element)$"),
    I_SHOULD_SEE_THE_MESSAGE_LOCATED_IN_FRAME("^I should see the \"([^\"]*)\" message in \"([^\"]*)\" frame$"),
    I_SHOULD_BE_REDIRECTED_TO_THE_PAGE("^I should be redirected to the \"([^\"]*)\" page$"),
    I_EXECUTE_REUSABLE_STEP("^I execute \"([^\"]*)\" reusable step$"),
    I_REMEMBER_TEXT("^I remember \"([^\"]*)\" text as \"([^\"]*)\" variable$"),
    I_CLICK_ON_ELEMENT_WITH_PARAMETER_SPECIAL("^I click on the \"([^\"]*)\" \"([^\"]*)\"$"),
    I_CLICK_ON_ELEMENT_SPECIAL("^I click on the \"([^\"]*)\"$"),
    I_SET_TEXT_SPECIAL("^I set \"([^\"]*)\" text to the \"([^\"]*)\" \"([^\"]*)\"$"),
    I_SHOULD_SEE_SPECIAL("^I should see \"([^\"]*)\" \"([^\"]*)\"$"),
    I_AM_LOGGED_IN_PF_AS("^I am logged into Product Factory as \"([^\"]*)\"$"),
    I_FILL_PF_FIELD("^I fill the \"([^\"]*)\" PF field with \"([^\"]*)\"$"),
    I_SELECT_FROM_DIALOG("^I select \"([^\"]*)\" from PF dialog$"),
    I_CHECK_UNCHECK("^I \"(check|uncheck)\" \"([^\"]*)\" checkbox$"),
    I_CHECK_UNCHECK_SPECIAL("^I \"(check|uncheck)\" \"([^\"]*)\" \"([^\"]*)\"$"),
    ELEMENTS_ATTRIBUTE_SHOULD_HAVE_VALUE("^Attribute \"([^\"]*)\" of \"([^\"]*)\" should have value \"([^\"]*)\"$"),
    ELEMENTS_ATTRIBUTE_SHOULD_HAVE_VALUE_SPECIAL("^Attribute \"([^\"]*)\" of \"([^\"]*)\" \"([^\"]*)\" should have value \"([^\"]*)\"$"),
    I_PRESS_KEY("^I press \"([^\"]*)\" for \"([^\"]*)\"$"),
    I_VALIDATE_TEXT("^I validate text \"([^\"]*)\" to be displayed for \"([^\"]*)\"element$");

    private final String pattern;

    private static final Map<String, StepPatterns> lookup = new HashMap<String, StepPatterns>();

    static {
        for (StepPatterns steps : StepPatterns.values()) {
            lookup.put(steps.getPattern(), steps);
        }
    }

    StepPatterns(String pattern) {
        this.pattern = pattern;
    }

    public String getPattern() {
        return pattern;
    }

    public static StepPatterns get(String pattern) {
        return lookup.get(pattern);
    }

    public static StepPatterns getEnum(String pattern) {
        for (StepPatterns p: values()) {
            if (p.getPattern().equalsIgnoreCase(pattern))
                return p;
        }throw new IllegalArgumentException("Can't find pattern type " + pattern);
    }

}
