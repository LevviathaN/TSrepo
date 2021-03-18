package CodeEditor;


import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebView;
import org.openqa.selenium.WebElement;
import org.w3c.dom.Document;

import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathException;
import javax.xml.xpath.XPathFactory;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static java.nio.file.StandardOpenOption.CREATE;

public class CodeEditor extends StackPane implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fileTreeView.setRoot(trimFileTreeView(new SimpleFileTreeItem(new File(CodeEditorExample.frameworkFolder + "/src/test/resources/cucumber/bpp_features"))));
        CodeEditorFunctionality.editingTemplate = GuiHelper.readFile(CodeEditorExample.guiFolder + "/src/main/resources/htmlFileContent.txt", StandardCharsets.UTF_8);
        editor.editingCode = GuiHelper.readFile(CodeEditorExample.guiFolder + "/src/main/resources/sampleText.txt", StandardCharsets.UTF_8).replaceAll("\r","");
    }

    private final GherkinValidator validator = new GherkinValidator();
    private final FeatureCRUD crud = new FeatureCRUD();
    private final LocatorsManager locatorsManager = new LocatorsManager();
    private final ReusableScenariosManager reusablesManager = new ReusableScenariosManager();
    private final CodeEditorFunctionality editor = new CodeEditorFunctionality();

    public WebView webview;
    public Label labeledCode;
    public Button copyCode;
    public Button saveChangesButton;
    public Button createFeature;
    public ComboBox<String> locatorComboBox;
    public ComboBox<String> stepdefComboBox;
    public TreeView<String> fileTreeView;
    public ListView<String> predictedLocatorsList;
    public ListView<String> predictedStepdefsList;
    public Button loadFeatureButton;

    /** Set Feature Template button listener */
    public void setCode() {
        webview.getEngine().loadContent(editor.applyEditingTemplate());
        createFeature.setDisable(false);
    }

    /** WebEditor listener */
    public void webEditorKeyOffListener() {
        editor.updatedDoc = webview.getEngine().getDocument();
        editor.updateEditingData(getCode());
        updateValidationItems();
    }

    /** Method to refresh prediction lists and invalid elements dropdowns */
    public void updateValidationItems() {
        editor.updateAutocompleteItems(predictedStepdefsList,predictedLocatorsList);

        locatorComboBox.getItems().clear();
        if (validator.isValidStepdef(editor.editableWord)) {
            System.out.println("Parameter is valid");
            locatorComboBox.setPromptText("VALID");
        } else {
            locatorComboBox.setPromptText(editor.editableWord);
        }
        locatorComboBox.getItems().addAll(validator.getInvalidParameters(editor.updatedCode));

        stepdefComboBox.getItems().clear();
        if (validator.isValidStepdef(editor.editableLine.trim().replaceAll("^(Given |When |Then |And |But )", ""))) {
            System.out.println("Stepdef is valid");
            stepdefComboBox.setPromptText("VALID");
        } else {
            stepdefComboBox.setPromptText(editor.editableLine);
        }
        stepdefComboBox.getItems().addAll(validator.getInvalidStepdefs(editor.updatedDoc));
    }

    /** Save Feature File button listener */
    public void createNewFeature() {
        editor.editingCode = getCode();
//        snapshot();
        try {
            crud.display(editor);
            fileTreeView.setRoot(trimFileTreeView(new SimpleFileTreeItem(new File(
                    CodeEditorExample.frameworkFolder + "/src/test/resources/cucumber/bpp_features"))));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void debugButtonListener() {
        try {
            if (GherkinValidator.reusablesList.contains(editor.getReusableNameFromStep(editor.editableLine))) {
                reusablesManager.display(editor.getReusableNameFromStep(editor.editableLine));
            } else {
                System.out.println("Invalid reusable");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        getEditingCursorPosition();
        System.out.println("Debug");
   }

    /** Valid Parameters list listener */
   public void clickOnLocatorsList() {
       fixParameter();
   }

    /** Valid Stepdefs list listener */
   public void clickOnStepdefsList() {
       fixStepdef();
   }

    /** onClick() for fileTreeView.
     * If selected item is feature file - enables "Load" button*/
   public void checkLoadButtonAvailability() {
        if (!fileTreeView.getSelectionModel().isEmpty()) {
            if (fileTreeView.getSelectionModel().getSelectedItem().getValue().endsWith(".feature")) {
                loadFeatureButton.setDisable(false);
                saveChangesButton.setDisable(false);
            } else {
                loadFeatureButton.setDisable(true);
                saveChangesButton.setDisable(true);
            }
        }
   }

    /** Load button listener */
   public void loadFeature() {
       String filePath = GherkinValidator.featureFilesMap.get(fileTreeView.getSelectionModel().getSelectedItem().getValue());
       editor.editingCode = GuiHelper.readFile(filePath, StandardCharsets.UTF_8).replaceAll("\r","");
       setCode();
   }

   public void saveChanges() {
       byte[] data = editor.editingCode.getBytes();
       Path p = Paths.get(GherkinValidator.featureFilesMap.get(fileTreeView.getSelectionModel().getSelectedItem().getValue()));
       
       try {
           OutputStream out = new BufferedOutputStream(Files.newOutputStream(p, CREATE));
           out.write(data, 0, data.length);
           out.close();
//           messageLabel.setText("File saved!");
       } catch (IOException x) {
           System.err.println(x);
       }
   }

   //___________________________menu items listeners____________________________

    public void openLocatorsManager() {
        try {
            locatorsManager.display();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void openReusablesManager() {
        try {
            reusablesManager.display();
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
        return CodeEditorFunctionality.editingTemplate.replace("${code}", editor.editingCode);
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
        editor.editingCode = (String ) webview.getEngine().executeScript("editor.getValue();");
    }

    /** Method to replace invalid parameter, being edited, to the valid one, selected from the list
     *
     * @author Ruslan Levytskyi
     * */
    private void fixParameter() {
        if (!predictedLocatorsList.getSelectionModel().isEmpty()) {
            editor.editingCode = editor.editingCode.replaceAll('"'+editor.editableWord+'"','"'+predictedLocatorsList.getSelectionModel().getSelectedItem()+'"');
            setCode();
        }
    }

    /** Method to replace invalid step, being edited, to the valid one, selected from the list
     *
     * @author Ruslan Levytskyi
     * */
    private void fixStepdef() {
        if (!predictedStepdefsList.getSelectionModel().isEmpty()) {
            String fixedLine = predictedStepdefsList.getSelectionModel().getSelectedItem();
            List<String> params = GuiHelper.getQuoted(editor.editableLine,'"');
            List<String> signature = GuiHelper.getQuoted(predictedStepdefsList.getSelectionModel().getSelectedItem(),'"');
            for (int i = 0; i < Math.min(params.size(), signature.size()); i++) {
                fixedLine = fixedLine.replaceFirst(signature.get(i),params.get(i));
            }
            editor.editingCode = editor.editingCode.replace(editor.editableLine, fixedLine.substring(1,fixedLine.length()-1));
            setCode();
        }
    }

    private WebElement getElementFromWebViewByXpath(String xpath) {
        WebElement element;
        try {
            element = (WebElement)
                    XPathFactory.newInstance().newXPath().evaluate(
                            "xpath",
                            editor.updatedDoc, XPathConstants.NODE);
            return element;
        } catch (XPathException e) {
            e.printStackTrace();
        }
        return null;
    }

    /** Method to trim all the unnecessary absolute path from fileTree elements
     *
     * @author Ruslan Levytskyi
     * */
    private TreeItem<String> trimFileTreeView(TreeItem<File> fileTree) {
        TreeItem<String> nameTree = new TreeItem<>();
        String separator = "/";
        String rootName = fileTree.getValue().getPath()
                .split(separator)[fileTree.getValue().getPath().split(separator).length-1];
        nameTree.setValue(rootName);
        for (TreeItem<File> child : fileTree.getChildren()) {
            if(!child.isLeaf()) {
                nameTree.getChildren().add(trimFileTreeView(child));
            } else {
                String childName = child.getValue().getPath()
                        .split(separator)[child.getValue().getPath().split(separator).length-1];
                nameTree.getChildren().add(new TreeItem<>(childName));
                GherkinValidator.featureFilesMap.put(childName,child.getValue().getPath());
            }
        }
        return nameTree;
    }


    //______________________New editor Functions___________________

    private int[] getEditingCursorPosition() {
        int[] pos = new int[2];
        pos[0] = (Integer ) webview.getEngine().executeScript("editor.getCursor().line;");
        pos[1] = (Integer ) webview.getEngine().executeScript("editor.getCursor().ch;");
        return pos;
    }
}
