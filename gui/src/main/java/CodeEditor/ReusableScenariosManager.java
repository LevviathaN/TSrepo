package CodeEditor;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.stage.Stage;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.net.URL;
import java.util.*;

public class ReusableScenariosManager {

    public static Stage reusableScenariosWindow;
    CodeEditor editor;
    CodeEditorFunctionality editorFunctionality = new CodeEditorFunctionality();

    public ComboBox<String> tagsFilter;

    public ListView<String> reusablesListView;
    public ListView scenarioStepsListView;
    public ListView<String> scenarioUsageListView;
    public Button initiateButton;

    public ComboBox<String> parametersSuggestionCombobox;
    public ComboBox<String> stepdefsSuggestionCombobox;

    public ArrayList<String> reusableScenariosList = getReusableScenariosList();
    public Map<String, HashMap<String,Integer>> reusablesUsageMap = getUsagesOfAllReusableScenarios();

    public String editingCode;
    public String editingTemplate;
    public String updatedCode;
    public Document updatedDoc;
    public int editingCursorPosition;
    public String editableWord;
    public String editableLine;

    /** Displays "Reusable Scenarios Manager" */
    public void display() throws Exception {
        URL url = new URL("file:" + CodeEditorExample.guiFolder + "/src/main/resources/ReusableScenariosManagerLayout.fxml");
        Parent root = FXMLLoader.load(url);
        reusableScenariosWindow = new Stage();
        reusableScenariosWindow.setTitle("Reusable Scenarios Manager");
        reusableScenariosWindow.setMinWidth(250);

        Scene scene = new Scene(root, 620, 500);
        reusableScenariosWindow.setScene(scene);
        reusableScenariosWindow.showAndWait();

    }

    /** Method to fill the reusables list*/
    public void initiateList() {
        editor = new CodeEditor();
        tagsFilter.getItems().add("ProductFactory");
        tagsFilter.getItems().add("BuildEmpire");
        reusablesListView.getItems().addAll(getReusableScenariosList());
    }

    public void updateStepsList() {

//        scenarioStepsListView.getItems().clear();
        scenarioStepsListView.setItems(getStepsOfReusableScenario(reusablesListView.getSelectionModel().getSelectedItem()));
        scenarioStepsListView.setCellFactory(TextFieldListCell.forListView());
        scenarioUsageListView.getItems().clear();
        scenarioUsageListView.getItems().addAll(reusablesUsageMap.get(reusablesListView.getSelectionModel().getSelectedItem()).keySet());
    }

//    public void updateUsageList() {
//        scenarioUsageListView.getItems().clear();
//        scenarioUsageListView.getItems().addAll(getStepsOfReusableScenario(reusablesListView.getSelectionModel().getSelectedItem()));
//    }

    /** Method to close Save File modal */
    public void cancelCreation() {
        reusableScenariosWindow.close();
    }

    /**
     * @author Ruslan Levytskyi
     */
    private ArrayList<String> getReusableScenariosList() {

        ArrayList<String> availableReusableStepsList = new ArrayList<>();

        try {
            File inputFile = new File("framework/src/main/resources/data/bpp/ReusableTestSteps.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();


            Node reusablesNode = doc.getElementsByTagName("reusables").item(0);
            Element reusablesElement = (Element) reusablesNode;

            NodeList reusablesList = reusablesElement.getElementsByTagName("reusable");
            for (int i = 0; i < reusablesList.getLength(); i++) {
                Node reusableNode = reusablesList.item(i);
                Element reusableElement = (Element) reusableNode;
                availableReusableStepsList.add(reusableElement.getAttribute("name"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return availableReusableStepsList;
    }

    /**
     * @author Ruslan Levytskyi
     */
    private ObservableList<String> getStepsOfReusableScenario(String reusableName) {

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

    /**
     * @author Ruslan Levytskyi
     */
    private ArrayList<String> getUsagesOfReusableScenario(String reusableName) {

        ArrayList<String> scenariosList = new ArrayList<>();

        try {
            File inputFile = new File("framework/src/main/resources/data/bpp/PFScenarios.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            ArrayList<String> availableScenariosList = new ArrayList<>();

            Node reusablesNode = doc.getElementsByTagName("scenarios").item(0);
            Element scenariosElement = (Element) reusablesNode;

            NodeList allStepsList = scenariosElement.getElementsByTagName("step");
            for (int i = 0; i < allStepsList.getLength(); i++) {
                Node stepNode = allStepsList.item(i);
                Element stepElement = (Element) stepNode;

                if (stepElement.getTextContent().contains("I execute") && stepElement.getTextContent().contains(reusableName)) {
                    Node scenarioNode = stepNode.getParentNode();
                    Element scenarioElement = (Element) scenarioNode;
                    String scenarioName = scenarioElement.getAttribute("name");
                    if (scenariosList.contains(scenarioName)) {
                        int qtt = 0;
                        for (int j = 0; j < scenariosList.size(); i++) {
                            String name = scenariosList.get(j);
                            if (name.equals(scenarioName)) {
                                scenariosList.remove(j);
                                qtt++;
                            }
                        }
                        scenariosList.add(scenarioName + " (" + qtt + ")");
                    } else {
                        scenariosList.add(scenarioName);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return scenariosList;
    }

    /**
     * @author Ruslan Levytskyi
     */
    private Map<String, HashMap<String,Integer>> getUsagesOfAllReusableScenarios() {

        Map<String, HashMap<String,Integer>> usageMap = new HashMap<>();

        try {
            File inputFile = new File("framework/src/main/resources/data/bpp/PFScenarios.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            ArrayList<String> availableScenariosList = new ArrayList<>();

            Node reusablesNode = doc.getElementsByTagName("scenarios").item(0);
            Element scenariosElement = (Element) reusablesNode;

            NodeList allStepsList = scenariosElement.getElementsByTagName("step");

            for (String reusableName : reusableScenariosList) {
                HashMap<String,Integer> scenariosMap = new HashMap<>();
                for (int i = 0; i < allStepsList.getLength(); i++) {
                    Node stepNode = allStepsList.item(i);
                    Element stepElement = (Element) stepNode;

                    if (stepElement.getTextContent().contains("I execute") && stepElement.getTextContent().contains(reusableName)) {
                        Node scenarioNode = stepNode.getParentNode();
                        Element scenarioElement = (Element) scenarioNode;
                        String scenarioName = scenarioElement.getAttribute("name");
                        if (scenariosMap.containsKey(scenarioName)) {
                            scenariosMap.put(scenarioName,scenariosMap.get(scenarioName)+1);
                        } else {
                            scenariosMap.put(scenarioName, 1);
                        }
                    }
                }
                usageMap.put(reusableName,scenariosMap);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return usageMap;
    }

    /** Method to refresh prediction lists and invalid elements dropdowns */
    public void updateValidationItems() {
        parametersSuggestionCombobox.getItems().clear();

        String originalStepdef = "";
        String lineText = editorFunctionality.editableLine.trim();

        if (lineText.startsWith("Given") || lineText.startsWith("When")
                || lineText.startsWith("Then") || lineText.startsWith("And") || lineText.startsWith("But")) {
            lineText = lineText.replaceAll("^(Given |When |Then |And |But )", "");
        }

        for (String stepPattern : GherkinValidator.stepPatternsMap.values()) {
            if (lineText.matches(stepPattern)) {
                originalStepdef = editorFunctionality.beutifyStepdef(stepPattern).substring(1,editorFunctionality.beutifyStepdef(stepPattern).length()-1);
                break;
            }
        }

        if (!originalStepdef.equals("")) {
            Map<String, String> signatureMap = editorFunctionality.getStepSignatureMap(lineText, originalStepdef);
            String parameterType = signatureMap.get(editableWord);
            switch (parameterType) {
                case "LOCATOR":
                    System.out.println("LOCATOR");
                    parametersSuggestionCombobox.getItems().addAll(editorFunctionality.suggestionList(editableWord,editor.validLocatorsList,8));
                    break;
                case "LOC_TEMPLATE":
                    System.out.println("LOC_TEMPLATE");
                    parametersSuggestionCombobox.getItems().addAll(editorFunctionality.suggestionList(editableWord,editor.validSpecialLocatorsList,8));
                    break;
                case "REUSABLE":
                    System.out.println("REUSABLE");
                    parametersSuggestionCombobox.getItems().addAll(editorFunctionality.suggestionList(editableWord,GherkinValidator.reusablesList,8));
                    break;
                case "PARAM":
                    System.out.println("PARAM");
                    break;
                default:
                    System.out.println("Default");
                    parametersSuggestionCombobox.getItems().addAll(editorFunctionality.suggestionList(editableWord,editor.validLocatorsList,8));
                    break;
            }
        } else {
            parametersSuggestionCombobox.getItems().addAll(editorFunctionality.suggestionList(editableWord,editor.validLocatorsList,8));
        }


        stepdefsSuggestionCombobox.getItems().clear();
        stepdefsSuggestionCombobox.getItems().addAll(editorFunctionality.beutifyStepdefs(editorFunctionality.suggestionList(editableLine,editor.validStepdefsList,8)));

//        locatorComboBox.getItems().clear();
//        if (validator.isValidStepdef(editableWord)) {
//            System.out.println("Parameter is valid");
//            locatorComboBox.setPromptText("VALID");
//        } else {
//            locatorComboBox.setPromptText(editableWord);
//        }
//        locatorComboBox.getItems().addAll(validator.getInvalidParameters(updatedCode));
//
//        stepdefComboBox.getItems().clear();
//        if (validator.isValidStepdef(editableLine.trim().replaceAll("^(Given |When |Then |And |But )", ""))) {
//            System.out.println("Stepdef is valid");
//            stepdefComboBox.setPromptText("VALID");
//        } else {
//            stepdefComboBox.setPromptText(editableLine);
//        }
//        stepdefComboBox.getItems().addAll(validator.getInvalidStepdefs(updatedDoc));
    }

    /** WebEditor listener */
    public void updateEditingData() {
        if (editingCode == null) {editingCode = getCode();}
        updatedCode = getCode();
        if (updatedCode.length()>3) {
            editingCursorPosition = editorFunctionality.compareStrings(editingCode, updatedCode);
            editableLine = editor.getEditableLine();
            editableWord = editor.getEditableWord();
        }

        updateValidationItems();
        snapshot();
    }

    private String getCode() {
        return (String ) scenarioStepsListView.getSelectionModel().getSelectedItem();
    }

    private void snapshot() {
        editingCode = (String ) scenarioStepsListView.getSelectionModel().getSelectedItem();
    }

    private void editReusable() {

    }
}
