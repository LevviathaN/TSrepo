package CodeEditor;

import org.w3c.dom.Document;

import java.util.*;

public class CodeEditorFunctionality {

    public String editingCode;
    public String editingTemplate;
    public String updatedCode;
    public Document updatedDoc;
    public int editingCursorPosition;
    public String editableWord;
    public String editableLine;

    /** Method to get a list of suggestions from chooseList based on the given word
     *
     * @param word - suggestion key
     * @param chooseList - list of valid parameters to choose from
     * @param restriction - max number of suggestions in the list
     *
     * @author Ruslan Levytskyi
     * */
    public List<String> suggestionList(String word, List<String> chooseList, int restriction) {
        List<String> suggestions = new ArrayList<>();
        int w = 0;
        for (int i=0; word.length()-i>2; i++) {
            for (String option : chooseList) {
                String trimmedWord = word.substring(0,word.length()-i);
                if (option.contains(trimmedWord) && !suggestions.contains(option)) {
                    if (w > restriction) {
                        break;
                    }
                    suggestions.add(option);
                    w++;
                }
            }
        }
        return suggestions;
    }

    /** Method to replace all regex parameters in stepdef template with their signatures
     *
     * @author Ruslan Levytskyi
     * */
    public List<String> beutifyStepdefs(List<String> stepDefs) {

        List<String> beutifiedStepdefs = new ArrayList<>();
        for (String stepdef : stepDefs) {
            List<String> signature = new ArrayList<>(Arrays.asList(GherkinValidator.stepSignaturesMap.get(stepdef).split(",")));
            for (String element : signature) {
                stepdef = stepdef.replaceFirst("(\\(\\[\\^\\\"\\]\\*\\))", element);
            }
            beutifiedStepdefs.add(stepdef);
        }
        return beutifiedStepdefs;
    }

    /** Method to replace all regex parameters in stepdef template with their signatures
     *
     * @author Ruslan Levytskyi
     * */
    public String beutifyStepdef(String stepdef) {
        List<String> signature = new ArrayList<>(Arrays.asList(GherkinValidator.stepSignaturesMap.get(stepdef).split(",")));
        for (String element : signature) {
            stepdef = stepdef.replaceFirst("(\\(\\[\\^\\\"\\]\\*\\))", element);
        }
        return stepdef;
    }

    /** Method to get a signature of each parameter of the step, if it is valid
     *
     * @param step - step, to get parameters from
     * @param template - valid stepdef, to get parameter signature from
     *
     * @return a map, where key is actual parameter of provided stepdef, and value - it`s signature (LOCATOR,REUSABLE,LOC_TEMPLATE e.t.c.)
     *
     * @author Ruslan Levytskyi
     * */
    public Map<String,String> getStepSignatureMap(String step, String template) {
        Map<String,String> stepSignatureMap = new HashMap<>();
        List<String> params = GuiHelper.getQuoted(step,'"');
        List<String> signature = GuiHelper.getQuoted(template,'"');
        for (int i = 0; i < Math.min(params.size(), signature.size()); i++) {
            stepSignatureMap.put(params.get(i),signature.get(i));
        }
        return stepSignatureMap;
    }

    /** Method to compare 2 strings and get first distinct character
     *
     * @author Ruslan Levytskyi
     * */
    public int compareStrings(String s1, String s2) {
        int distinctPosition = 0;
        String smallerString = s1.length()<s2.length() ? s1 : s2;
        String biggerString = s1.length()>s2.length() ? s1 : s2;
        for (int i=0; i<smallerString.length(); i++) {
            char ch1 = s1.charAt(i);
            char ch2 = s2.charAt(i);
            if (ch1!=ch2) {
                distinctPosition = i;
                break;
            }
        }
        if (!smallerString.equals(biggerString) && distinctPosition==0) {
            return s2.length();
        } else {
            return distinctPosition;
        }
    }
}
