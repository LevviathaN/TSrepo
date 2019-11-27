package cucumber;

import java.util.HashMap;
import java.util.Map;

public enum StepPatterns {

    I_AM_ON_URL("^I am on \"([^\"]*)\" URL$"),
    I_CLICK_ON_THE_BUTTON("^I click on the \"([^\"]*)\" (?:button|link|option|element)(?: in [^\"]*)?$");

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
