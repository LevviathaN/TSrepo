package CodeEditor;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReusableScenariosManager {

    public static Stage reusableScenariosWindow;
    GherkinValidator validator = new GherkinValidator();
    CodeEditorFunctionality editor = new CodeEditorFunctionality();

    public ComboBox<String> tagsFilter;

    public WebView reusableWebView;
    public ListView<String> reusablesListView;
    public ListView scenarioStepsListView;
    public ListView<String> scenarioUsageListView;
    public Button initiateButton;
    public Button saveReusableButton;

    public ComboBox<String> parametersSuggestionCombobox;
    public ComboBox<String> stepdefsSuggestionCombobox;


    /** Displays "Reusable Scenarios Manager" */
    public void display() throws Exception {
        URL url = new URL("file:" + CodeEditorExample.guiFolder + "/src/main/resources/ReusableScenariosManagerLayout.fxml");
        Parent root = FXMLLoader.load(url);
        HBox hbox = (HBox) root.getChildrenUnmodifiable().get(1);
        VBox vbox = (VBox) hbox.getChildren().get(0);
        ListView<String> listView = (ListView<String>) vbox.getChildren().get(1);
        listView.getItems().addAll(validator.getReusableScenariosList());
        reusableScenariosWindow = new Stage();
        reusableScenariosWindow.setTitle("Reusable Scenarios Manager");
        reusableScenariosWindow.setMinWidth(250);

        Scene scene = new Scene(root, 620, 500);
        reusableScenariosWindow.setScene(scene);
        reusableScenariosWindow.showAndWait();

    }

    /** Displays "Reusable Scenarios Manager" with preselected reusable scenario*/
    public void display(String preselectedItem) throws Exception {
        URL url = new URL("file:" + CodeEditorExample.guiFolder + "/src/main/resources/ReusableScenariosManagerLayout.fxml");
        Parent root = FXMLLoader.load(url);

        HBox hbox = (HBox) root.getChildrenUnmodifiable().get(1);
        VBox reusablesVbox = (VBox) hbox.getChildren().get(0);
        ListView<String> reusablesListView = (ListView<String>) reusablesVbox.getChildren().get(1);
        reusablesListView.getItems().addAll(validator.getReusableScenariosList());
        reusablesListView.getSelectionModel().select(preselectedItem);

        VBox stepsVbox = (VBox) hbox.getChildren().get(1);
        ListView scenarioStepsListView = (ListView) stepsVbox.getChildren().get(1);

        VBox usagesVbox = (VBox) hbox.getChildren().get(2);
        ListView<String> scenarioUsageListView = (ListView<String>) usagesVbox.getChildren().get(1);


        scenarioStepsListView.setItems(editor.getStepsOfReusableScenario(reusablesListView.getSelectionModel().getSelectedItem()));
        scenarioStepsListView.setCellFactory(TextFieldListCell.forListView());
        scenarioUsageListView.getItems().clear();
        scenarioUsageListView.getItems().addAll(GherkinValidator.reusablesUsageMap.get(reusablesListView.getSelectionModel().getSelectedItem()).keySet());

        reusableScenariosWindow = new Stage();
        reusableScenariosWindow.setTitle("Reusable Scenarios Manager");
        reusableScenariosWindow.setMinWidth(250);

        Scene scene = new Scene(root, 620, 500);
        reusableScenariosWindow.setScene(scene);
        reusableScenariosWindow.showAndWait();

    }

    /** Method to fill the reusables list*/
    public void initiateList() {
        tagsFilter.getItems().add("ProductFactory");
        tagsFilter.getItems().add("BuildEmpire");
    }

    public void updateStepsList() {
        editor.editingCode = validator.getReusableScenarioFromFeature(reusablesListView.getSelectionModel().getSelectedItem());
        reusableWebView.getEngine().loadContent(editor.applyEditingTemplate());
        scenarioStepsListView.setItems(editor.getStepsOfReusableScenario(reusablesListView.getSelectionModel().getSelectedItem()));
        scenarioStepsListView.setCellFactory(TextFieldListCell.forListView());
        scenarioUsageListView.getItems().clear();
        scenarioUsageListView.getItems().addAll(GherkinValidator.reusablesUsageMap.get(reusablesListView.getSelectionModel().getSelectedItem()).keySet());
    }

    /** Method to close Save File modal */
    public void cancelCreation() {
        reusableScenariosWindow.close();
    }

    /** WebEditor listener */
    public void webEditorKeyReleaseListener() {
        editor.updatedDoc = reusableWebView.getEngine().getDocument();
        editor.updateEditingData(getCodeFromWebView());
    }

    /** WebEditor listener */
    public void updateEditingData() {
        if (editor.editingCode == null) {
            editor.editingCode = getCode();
        }
        editor.updateEditingData(getCode());
        editor.updateAutocompleteItems(stepdefsSuggestionCombobox, parametersSuggestionCombobox);
    }

    private String getCode() {
        return (String ) scenarioStepsListView.getSelectionModel().getSelectedItem();
    }

    /** Method to get current text from WebEditor
     *
     * text from WebEditor
     * */
    private String getCodeFromWebView() {
        return (String ) reusableWebView.getEngine().executeScript("editor.getValue();");
    }

    public void saveReusable() {
        validator.reusablesStepsMapToFile(reusableFromEditorToMap(),"");
    }

    public Map<String, List<String>> reusableFromEditorToMap () {
        Map<String, List<String>> reusableList = new HashMap<>();
        String name = reusablesListView.getSelectionModel().getSelectedItem();
        List<String> steps = scenarioStepsListView.getItems();
        reusableList.put(name,steps);
        return reusableList;
    }
}
