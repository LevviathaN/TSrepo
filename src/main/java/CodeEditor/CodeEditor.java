package CodeEditor;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebView;
import org.w3c.dom.Document;
import ui.pages.BasePage;
import ui.utils.bpp.PreProcessFiles;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CodeEditor extends StackPane implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        fileTreeView = new TreeView<File>(
//                new SimpleFileTreeItem(new File("/Users/ruslanlevytskyi/IdeaProjects/qa-automation-framework-poc/src/test/resources/cucumber/bpp_features")));
        try {
            editingTemplate = readFile(CodeEditorExample.rootFolder + "/src/main/java/CodeEditor/htmlFileContent.txt", StandardCharsets.UTF_8);
            editingCode = readFile(CodeEditorExample.rootFolder + "/src/main/java/CodeEditor/sampleText.txt", StandardCharsets.UTF_8);
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
    public TreeView<File> fileTreeView;
    public ListView<String> predictedLocatorsList;

    public static String editingCode;
    public static String editingTemplate;

    /** Set Feature Template button listener */
    public void setCode() {
        webview.getEngine().loadContent(applyEditingTemplate());
    }

    /** WebEditor listener */
    public void updateInvalidLocators() {
        List<String> validLocatorsList = new ArrayList<>(BasePage.locatorsMap.keySet());
        predictedLocatorsList.getItems().clear();
        predictedLocatorsList.getItems().addAll(suggestionList(getEditableWord(),validLocatorsList,8));
        locatorComboBox.getItems().clear();
        locatorComboBox.getItems().addAll(validator.getInvalidParameters(getCode()));
        snapshot();
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
        for (int i=0; i<s2.length(); i++) {
            char ch1 = s1.charAt(i);
            char ch2 = s2.charAt(i);
            if (ch1!=ch2) {
                distinctPosition = i;
                break;
            }
        }
        return distinctPosition;
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
        String updatedCode = getCode();
        int distinctPosition = compareStrings(editingCode, updatedCode);
        int rightRim = 0;
        int leftRim = 0;
        int centerRim = 0;
        for (int i=0; i<updatedCode.length(); i++) {
            if (updatedCode.charAt(distinctPosition + i)=='"') {
                rightRim = distinctPosition + i;
                if (i==0) {
                    centerRim = distinctPosition;
                } else {
                    break;
                }
            }
        }
        for (int i=0; updatedCode.length()-i>1; i++) {
            if (updatedCode.charAt(distinctPosition - i)=='"') {
                leftRim = distinctPosition - i + 1;
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

        if (validator.getInvalidParameters(updatedCode).contains(editableWord)) {
            System.out.println(editableWord);
            return editableWord;
        } else if (validator.getInvalidParameters(updatedCode).contains(possibleR)) {
            System.out.println(possibleR);
            return possibleR;
        } else if (validator.getInvalidParameters(updatedCode).contains(possibleL)) {
            System.out.println(possibleL);
            return possibleL;
        } else {
            System.out.println("<edited word is not a parameter>");
            return "";
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
}
