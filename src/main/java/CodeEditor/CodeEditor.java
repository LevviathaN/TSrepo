package CodeEditor;


import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebView;
import org.openqa.selenium.WebElement;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import ui.pages.BasePage;
import ui.utils.Tools;

import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathException;
import javax.xml.xpath.XPathFactory;
import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class CodeEditor extends StackPane implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fileTreeView.setRoot(new SimpleFileTreeItem(new File(CodeEditorExample.rootFolder + "/src/test/resources/cucumber/bpp_features")));
        try {
            editingTemplate = readFile(CodeEditorExample.rootFolder + "/src/main/java/CodeEditor/htmlFileContent.txt", StandardCharsets.UTF_8);
            editingCode = readFile(CodeEditorExample.rootFolder + "/src/main/java/CodeEditor/sampleText.txt", StandardCharsets.UTF_8).replaceAll("\r","");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private GherkinValidator validator = new GherkinValidator();
    private FeatureCRUD crud = new FeatureCRUD();

    public WebView webview;
    public Label labeledCode;
    public Button copyCode;
    public Button createFeature;
    public ComboBox<String> locatorComboBox;
    public ComboBox<String> stepdefComboBox;
    public TreeView<File> fileTreeView;
    public ListView<String> predictedLocatorsList;
    public ListView<String> predictedStepdefsList;

    public static String editingCode;
    public static String editingTemplate;
    public static String updatedCode;
    public static Document updatedDoc;
    public static int editingCursorPosition;
    public static String editableWord;
    public static String editableLine;


    private List<String> validLocatorsList = new ArrayList<>(BasePage.locatorsMap.keySet());
    private List<String> validStepdefsList = new ArrayList<>(BasePage.stepPatternsMap.values());

    /** Set Feature Template button listener */
    public void setCode() {
        webview.getEngine().loadContent(applyEditingTemplate());
    }

    /** WebEditor listener */
    public void updateInvalidLocators() {
        updatedCode = getCode();
        updatedDoc = webview.getEngine().getDocument();
        if (updatedCode.length()>3) {
            editingCursorPosition = compareStrings(editingCode, updatedCode);
            editableLine = getEditableLine();
            editableWord = getEditableWord();
        }

        predictedLocatorsList.getItems().clear();
        predictedLocatorsList.getItems().addAll(suggestionList(editableWord,validLocatorsList,8));

        predictedStepdefsList.getItems().clear();
        predictedStepdefsList.getItems().addAll(beutifyStepdefs(suggestionList(editableLine,validStepdefsList,8)));

        locatorComboBox.getItems().clear();
        locatorComboBox.setPromptText(editableWord);
        locatorComboBox.getItems().addAll(validator.getInvalidParameters(updatedDoc));

        stepdefComboBox.getItems().clear();
        stepdefComboBox.setPromptText(editableLine);
        stepdefComboBox.getItems().addAll(validator.getInvalidStepdefs(updatedDoc));
        snapshot();
//        webview.getEngine().executeScript(
//                "var aTags = document.getElementsByTagName(\"span\");\n" +
//                        "var searchText = \"SearchingText\";\n" +
//                        "var found;\n" +
//                        "\n" +
//                        "for (var i = 0; i < aTags.length; i++) {\n" +
//                        "  if (aTags[i].textContent == searchText) {\n" +
//                        "    found = aTags[i];\n" +
//                        "    break;\n" +
//                        "  }\n" +
//                        "}\n" +
//                        "found.scrollIntoView();"
//        );
    }

    /** Save Feature File button listener */
    public void createNewFeature() {
        snapshot();
        try {
            crud.display();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void debugButtonListener() {
        fixStepdef();
        fixParameter();
   }


//    _____________________________additional functions________________________________

    /** applies the editing template to the editing code to create the html+javascript source for a code editor.
     *
     * @author Ruslan Levytskyi
     * */
    private String applyEditingTemplate() {
        return editingTemplate.replace("${code}", editingCode);
    }

    /** Method to get current text from WebEditor
     *
     * text from WebEditor
     * */
    private String getCode() {
        return (String ) webview.getEngine().executeScript("editor.getValue();");
    }

    /** Saves the current text from WebEditor into a variable
     *
     * @author Ruslan Levytskyi
     * */
    private void snapshot() {
        editingCode = (String ) webview.getEngine().executeScript("editor.getValue();");
    }

    private String readFile(String path, Charset encoding) throws IOException
    {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

    private void writeFile(String fileName, String content)
            throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        writer.write(content);

        writer.close();
    }

    /** Method to compare 2 strings and get first distinct character
     *
     * @author Ruslan Levytskyi
     * */
    private int compareStrings(String s1, String s2) {
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
    /** Custom method to get the word between parentheses that was updated recently in web view.
     *
     * @author Ruslan Levytskyi
     * */
    private String getEditableWord() {
        String editableWord = "";
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
                if (i!=0) {
                    break;
                }
            }
        }
        editableWord = updatedCode.substring(leftRim, rightRim);
        if (centerRim != 0) {
            possibleR = updatedCode.substring(centerRim, rightRim);
            possibleL = updatedCode.substring(leftRim, centerRim);
        }

        List<String> invalidParametersList = validator.getInvalidParameters(updatedDoc);
        if (invalidParametersList.contains(editableWord)) {
            System.out.println(editableWord);
            return editableWord;
        } else if (invalidParametersList.contains(possibleR)) {
            System.out.println(possibleR);
            return possibleR;
        } else if (invalidParametersList.contains(possibleL)) {
            System.out.println(possibleL);
            return possibleL;
        } else {
            if (validLocatorsList.contains(editableWord)) {
                System.out.println("Locator is valid");
                return "VALID";
            } else {
                System.out.println("<edited word is not a parameter>");
                return "";
            }
        }
    }

    //todo: make this method more universal. Pass strings before and after as parameters
    /** Custom method to get the line that was updated recently in web view.
     *
     * @author Ruslan Levytskyi
     * */
    private String getEditableLine() {
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
        editableLine = updatedCode.substring(leftRim, rightRim).trim().replaceAll("^(Given |When |Then |And |But )", "");;
        if (centerRim != 0) {
            possibleR = updatedCode.substring(centerRim, rightRim).trim().replaceAll("^(Given |When |Then |And |But )", "");;
            possibleL = updatedCode.substring(leftRim, centerRim).trim().replaceAll("^(Given |When |Then |And |But )", "");;
        }

        List<String> invalidLinesList = validator.getInvalidStepdefs(updatedDoc);
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
            boolean isValid = false;
            for (String regx : validStepdefsList) {
                if (editableLine.matches(regx)) {
                    isValid = true;
                    break;
                }
            }
            if (isValid) {
                System.out.println("Stepdef is valid");
                return "VALID";
            } else {
                System.out.println("<edited line is not a step>");
                return "";
            }
        }
    }

    /** Method to get a list of suggestions from chooseList based on the given word
     *
     * @param word - suggestion key
     * @param chooseList - list of valid parameters to choose from
     * @param restriction - max number of suggestions in the list
     *
     * @author Ruslan Levytskyi
     * */
    private List<String> suggestionList(String word, List<String> chooseList, int restriction) {
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

    private void fixParameter() {
        if (!predictedLocatorsList.getSelectionModel().isEmpty()) {
            editingCode = editingCode.replaceAll('"'+editableWord+'"','"'+predictedLocatorsList.getSelectionModel().getSelectedItem()+'"');
            setCode();
        }
    }

    private void fixStepdef() {
        if (!predictedStepdefsList.getSelectionModel().isEmpty()) {
            String fixedLine = predictedStepdefsList.getSelectionModel().getSelectedItem();
            List<String> params = Tools.getQuoted(editableLine,'"');
            List<String> signature = Tools.getQuoted(predictedStepdefsList.getSelectionModel().getSelectedItem(),'"');
            for (int i = 0; i < Math.min(params.size(), signature.size()); i++) {
                fixedLine = fixedLine.replaceFirst(signature.get(i),params.get(i));
            }
            editingCode = editingCode.replace(editableLine, fixedLine.substring(1,fixedLine.length()-1));
            setCode();
        }
    }

    public static Map<String,String> getStepSignatureMap(String step, String template) {
        if (step.matches(template)) {
            Map<String,String> stepSignatureMap = new HashMap<>();
            List<String> params = Tools.getQuoted(step,'"');
            List<String> signature = Tools.getQuoted(template,'"');
            for (int i = 0; i < Math.min(params.size(), signature.size()); i++) {
                stepSignatureMap.put(params.get(i),signature.get(i));
            }
            return stepSignatureMap;
        } else {
            System.out.println("getStepSignatureMap error: step does not match provided template");
            return null;
        }
    }

    private List<String> beutifyStepdefs(List<String> stepDefs) {

        List<String> beutifiedStepdefs = new ArrayList<>();
        for (String stepdef : stepDefs) {
            List<String> signature = new ArrayList<>(Arrays.asList(BasePage.stepSignaturesMap.get(stepdef).split(",")));
            for (String element : signature) {
                stepdef = stepdef.replaceFirst("(\\(\\[\\^\\\"\\]\\*\\))", element);
            }
            beutifiedStepdefs.add(stepdef);
        }
        return beutifiedStepdefs;
    }

    public static String beutifyStepdef(String stepdef) {
        List<String> signature = new ArrayList<>(Arrays.asList(BasePage.stepSignaturesMap.get(stepdef).split(",")));
        for (String element : signature) {
            stepdef = stepdef.replaceFirst("(\\(\\[\\^\\\"\\]\\*\\))", element);
        }
        return stepdef;
    }

    private WebElement getElementFromWebViewByXpath(String xpath) {
        WebElement element;
        try {
            element = (WebElement)
                    XPathFactory.newInstance().newXPath().evaluate(
                            "xpath",
                            updatedDoc, XPathConstants.NODE);
            return element;
        } catch (XPathException e) {
            e.printStackTrace();
        }
        return null;
    }
}
