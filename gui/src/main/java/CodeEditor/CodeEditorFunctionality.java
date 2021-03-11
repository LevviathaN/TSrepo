package CodeEditor;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static java.nio.file.StandardOpenOption.CREATE;

public class CodeEditorFunctionality {

    private GherkinValidator validator = new GherkinValidator();

    public String editingCode;
    public static String editingTemplate;
    public String updatedCode;
    public Document updatedDoc;
    public int editingCursorPosition;
    public String editableWord;
    public String editableLine;

    /** applies the editing template to the editing code to create the html+javascript source for a code editor.
     *
     * @author Ruslan Levytskyi
     * */
    public String applyEditingTemplate() {
        return editingTemplate.replace("${code}", editingCode);
    }

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

    //todo: make this method more universal. Pass strings before and after as parameters
    /** Custom method to get the line that was updated recently in web view.
     *
     * @author Ruslan Levytskyi
     * */
    public String getEditableLine() {
        String editableLine = "";
        String possibleR = "";
        String possibleL = "";
        int rightRim = updatedCode.length();
        int leftRim = 0;
        int centerRim = 0;
        for (int i=0; i+editingCursorPosition<updatedCode.length(); i++) {
            if (updatedCode.charAt(editingCursorPosition + i)=='\n') {
                rightRim = editingCursorPosition + i;
                if (i==0) {
                    centerRim = editingCursorPosition;
                } else {
                    break;
                }
            }
        }
        for (int i=0; editingCursorPosition-i>1; i++) {
            if (updatedCode.charAt(editingCursorPosition - 1 - i)=='\n') {
                leftRim = editingCursorPosition - i;
                if (i!=0) {
                    break;
                }
            }
        }
        editableLine = updatedCode.substring(leftRim, rightRim).trim().replaceAll("^(Given |When |Then |And |But )", "");
        if (centerRim != 0) {
            possibleR = updatedCode.substring(centerRim, rightRim).trim().replaceAll("^(Given |When |Then |And |But )", "");
            possibleL = updatedCode.substring(leftRim, centerRim).trim().replaceAll("^(Given |When |Then |And |But )", "");
        }

        List<String> invalidLinesList = validator.getInvalidStepdefs(updatedCode);
        if (invalidLinesList.contains(editableLine)) {
            System.out.println(editableLine);
            return editableLine;
        } else if (invalidLinesList.contains(possibleR)) {
            System.out.println(possibleR);
            return possibleR;
        } else if (invalidLinesList.contains(possibleL)) {
            System.out.println(possibleL);
            return possibleL;
        } else {
            System.out.println("Invalid Stepdef");
            return editableLine;
        }
    }

    //todo: make this method more universal. Pass strings before and after as parameters
    /** Custom method to get the word between parentheses that was updated recently in web view.
     *
     * @author Ruslan Levytskyi
     * */
    public String getEditableWord() {
        String word = "";
        String possibleR = "";
        String possibleL = "";
        int rightRim = updatedCode.length();
        int leftRim = 0;
        int centerRim = 0;
        for (int i=0; i+editingCursorPosition<updatedCode.length(); i++) {
            if (updatedCode.charAt(editingCursorPosition + i)=='"') {
                rightRim = editingCursorPosition + i;
                if (i==0) {
                    centerRim = editingCursorPosition;
                } else {
                    break;
                }
            }
        }
        for (int i=0; editingCursorPosition-i>1; i++) {
            if (updatedCode.charAt(editingCursorPosition - 1 - i)=='"') {
                leftRim = editingCursorPosition - i;
                if (i==0) {
                    centerRim = editingCursorPosition;
                } else {
                    break;
                }
            }
        }
        word = updatedCode.substring(leftRim, rightRim);
        if (centerRim != 0) {
            possibleR = updatedCode.substring(centerRim, rightRim);
            possibleL = updatedCode.substring(leftRim, centerRim);
        }

        List<String> invalidParametersList = validator.getInvalidParameters(updatedCode);
        if (invalidParametersList.contains(word)) {
            System.out.println(word);
            return word;
        } else if (invalidParametersList.contains(possibleR)) {
            System.out.println(possibleR);
            return possibleR;
        } else if (invalidParametersList.contains(possibleL)) {
            System.out.println(possibleL);
            return possibleL;
        } else {
            System.out.println("Invalid Locator");
            return word;
        }
    }


    public void updateEditingData(String code) {
        updatedCode =  code;
        if (updatedCode.length()>3) {
            editingCursorPosition = compareStrings(editingCode, updatedCode);
            editableLine = getEditableLine();
            editableWord = getEditableWord();
        }
        editingCode = code;
    }

    /** Method to refresh prediction lists and invalid elements dropdowns */
    public void updateAutocompleteItems(ListView<String> predictedStepdefsListView, ListView<String> predictedLocatorsListView) {
        predictedLocatorsListView.getItems().clear();

        String originalStepdef = "";
        String lineText = editableLine.trim();

        if (lineText.startsWith("Given") || lineText.startsWith("When")
                || lineText.startsWith("Then") || lineText.startsWith("And") || lineText.startsWith("But")) {
            lineText = lineText.replaceAll("^(Given |When |Then |And |But )", "");
        }

        for (String stepPattern : GherkinValidator.stepPatternsMap.values()) {
            if (lineText.matches(stepPattern)) {
                originalStepdef = beutifyStepdef(stepPattern).substring(1,beutifyStepdef(stepPattern).length()-1);
                break;
            }
        }

        if (!originalStepdef.equals("")) {
            Map<String, String> signatureMap = getStepSignatureMap(lineText, originalStepdef);
            String parameterType = signatureMap.get(editableWord);
            switch (parameterType) {
                case "LOCATOR":
                    System.out.println("LOCATOR");
                    predictedLocatorsListView.getItems().addAll(suggestionList(editableWord,GherkinValidator.validLocatorsList,8));
                    break;
                case "LOC_TEMPLATE":
                    System.out.println("LOC_TEMPLATE");
                    predictedLocatorsListView.getItems().addAll(suggestionList(editableWord,GherkinValidator.validSpecialLocatorsList,8));
                    break;
                case "REUSABLE":
                    System.out.println("REUSABLE");
                    predictedLocatorsListView.getItems().addAll(suggestionList(editableWord,GherkinValidator.reusablesList,8));
                    break;
                case "PARAM":
                    System.out.println("PARAM");
                    break;
                default:
                    System.out.println("Default");
                    predictedLocatorsListView.getItems().addAll(suggestionList(editableWord,GherkinValidator.validLocatorsList,8));
                    break;
            }
        } else {
            predictedLocatorsListView.getItems().addAll(suggestionList(editableWord,GherkinValidator.validLocatorsList,8));
        }

        predictedStepdefsListView.getItems().clear();
        predictedStepdefsListView.getItems().addAll(beutifyStepdefs(suggestionList(editableLine,GherkinValidator.validStepdefsList,8)));
    }

    /** Method to refresh prediction lists and invalid elements dropdowns */
    public void updateAutocompleteItems(ComboBox<String> predictedStepdefsListView, ComboBox<String> predictedLocatorsListView) {
        predictedLocatorsListView.getItems().clear();

        String originalStepdef = "";
        String lineText = editableLine.trim();

        if (lineText.startsWith("Given") || lineText.startsWith("When")
                || lineText.startsWith("Then") || lineText.startsWith("And") || lineText.startsWith("But")) {
            lineText = lineText.replaceAll("^(Given |When |Then |And |But )", "");
        }

        for (String stepPattern : GherkinValidator.stepPatternsMap.values()) {
            if (lineText.matches(stepPattern)) {
                originalStepdef = beutifyStepdef(stepPattern).substring(1,beutifyStepdef(stepPattern).length()-1);
                break;
            }
        }

        if (!originalStepdef.equals("")) {
            Map<String, String> signatureMap = getStepSignatureMap(lineText, originalStepdef);
            String parameterType = signatureMap.get(editableWord);
            switch (parameterType) {
                case "LOCATOR":
                    System.out.println("LOCATOR");
                    predictedLocatorsListView.getItems().addAll(suggestionList(editableWord,GherkinValidator.validLocatorsList,8));
                    break;
                case "LOC_TEMPLATE":
                    System.out.println("LOC_TEMPLATE");
                    predictedLocatorsListView.getItems().addAll(suggestionList(editableWord,GherkinValidator.validSpecialLocatorsList,8));
                    break;
                case "REUSABLE":
                    System.out.println("REUSABLE");
                    predictedLocatorsListView.getItems().addAll(suggestionList(editableWord,GherkinValidator.reusablesList,8));
                    break;
                case "PARAM":
                    System.out.println("PARAM");
                    break;
                default:
                    System.out.println("Default");
                    predictedLocatorsListView.getItems().addAll(suggestionList(editableWord,GherkinValidator.validLocatorsList,8));
                    break;
            }
        } else {
            predictedLocatorsListView.getItems().addAll(suggestionList(editableWord,GherkinValidator.validLocatorsList,8));
        }

        predictedStepdefsListView.getItems().clear();
        predictedStepdefsListView.getItems().addAll(beutifyStepdefs(suggestionList(editableLine,GherkinValidator.validStepdefsList,8)));
    }

    public String getReusableNameFromStep(String step) {
        String reusableName = "";
        if (step.contains("I execute")) {
            reusableName = GuiHelper.getQuoted(step,'"').get(0);
        } else {
            System.out.println("Invalid step");
        }
        return reusableName;
    }

    /**
     * @author Ruslan Levytskyi
     */
    public ObservableList<String> getStepsOfReusableScenario(String reusableName) {

        ObservableList<String> stepsList = FXCollections.observableArrayList();

        try {
            File inputFile = new File("framework/src/main/resources/data/bpp/ReusableTestSteps.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            ArrayList<String> availableReusableStepsList = new ArrayList<>();

            Node reusablesNode = doc.getElementsByTagName("reusables").item(0);
            Element reusablesElement = (Element) reusablesNode;

            NodeList reusablesList = reusablesElement.getElementsByTagName("reusable");
            for (int i = 0; i < reusablesList.getLength(); i++) {
                Node reusableNode = reusablesList.item(i);
                Element reusableElement = (Element) reusableNode;
                availableReusableStepsList.add(reusableElement.getAttribute("name"));
                if (reusableElement.getAttribute("name").equals(reusableName)) {
                    NodeList steps = reusableElement.getElementsByTagName("step");
                    for (int j = 0; j < steps.getLength(); j++) {
                        stepsList.add(steps.item(j).getTextContent());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stepsList;
    }
}
