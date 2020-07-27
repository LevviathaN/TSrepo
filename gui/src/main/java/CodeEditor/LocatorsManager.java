package CodeEditor;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import ui.pages.BasePage;
import ui.utils.JSONReader;
import ui.utils.Locator;
import ui.utils.Tools;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

import static java.util.Map.Entry.comparingByKey;
import static java.util.stream.Collectors.toMap;

public class LocatorsManager {

    public static Stage locatorsManagerWindow;
    public TableView<Locator> locatorsTable;
    public TableColumn<Locator, String> nameColumn;
    public TableColumn<Locator, String> valueColumn;
    public TextField nameField;
    public TextField valueField;
    public Label message;

    public TableView<Locator> specialLocatorsTable;
    public TableColumn<Locator, String> specialNameColumn;
    public TableColumn<Locator, String> specialValueColumn;
    public TextField specialNameField;
    public TextField specialValueField;
    public Label specialMessage;

    /**
     * Displays "Save Feature File modal window"
     */
    public void display() throws Exception {
        URL url = new URL("file:" + CodeEditorExample.guiFolder + "/src/main/java/CodeEditor/LocatorsManagerLayout.fxml");
        Parent root = FXMLLoader.load(url);
        locatorsManagerWindow = new Stage();
//        createFeatureWindow.initModality(Modality.APPLICATION_MODAL);
        locatorsManagerWindow.setTitle("Locators Manager");
        locatorsManagerWindow.setMinWidth(250);


        Scene scene = new Scene(root, 600, 500);
        locatorsManagerWindow.setScene(scene);
        locatorsManagerWindow.showAndWait();

    }

    public void initLocatorsTable() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        valueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
        locatorsTable.setItems(getLocators());
//        getLocatorsMap("src/test/resources/cucumber/bpp_features/featuresFromGUI/newLocatorsFile.json");
        Tools.createFile("src/main/resources/NewLocators.json",
                JSONReader.toJson(getLocators()));
    }

    public void addLocator() {
        Locator newLocator = new Locator(nameField.getText(),valueField.getText());
        ObservableList<Locator> locatorsList = getLocators();
        locatorsList.add(newLocator);
        String newJson = JSONReader.toJson(locatorsList);
        Tools.createFile("src/main/resources/NewLocators.json", newJson);
        nameField.clear();
        valueField.clear();
        message.visibleProperty().setValue(true);
    }

    public ObservableList<Locator> getLocators() {
        ObservableList<Locator> locatorsList = FXCollections.observableArrayList();
        HashMap<String, String> sorted = BasePage.locatorsMap.entrySet().stream().sorted(comparingByKey())
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));

        for (String key : sorted.keySet()) {
            locatorsList.add(new Locator(key, BasePage.locatorsMap.get(key)));
        }
        return locatorsList;
    }

    public void initSpecialLocatorsTable() {
        specialNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        specialValueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
        specialLocatorsTable.setItems(getSpecialLocators());
//        getLocatorsMap("src/test/resources/cucumber/bpp_features/featuresFromGUI/newLocatorsFile.json");
        Tools.createFile("src/main/resources/NewSpecialLocators.json",
                JSONReader.toJson(getSpecialLocators()));
    }

    public void addSpecialLocator() {
        Locator newLocator = new Locator(specialNameField.getText(),specialValueField.getText());
        ObservableList<Locator> locatorsList = getSpecialLocators();
        locatorsList.add(newLocator);
        String newJson = JSONReader.toJson(locatorsList);
        Tools.createFile("src/main/resources/NewSpecialLocators.json", newJson);
        specialNameField.clear();
        specialValueField.clear();
        specialMessage.visibleProperty().setValue(true);
    }

    public ObservableList<Locator> getSpecialLocators() {
        ObservableList<Locator> locatorsList = FXCollections.observableArrayList();
        HashMap<String, String> sorted = BasePage.specialLocatorsMap.entrySet().stream().sorted(comparingByKey())
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));

        for (String key : sorted.keySet()) {
            locatorsList.add(new Locator(key, BasePage.specialLocatorsMap.get(key)));
        }
        return locatorsList;
    }

    /**
     * Method to close Save File modal
     */
    public void cancelCreation() {
        locatorsManagerWindow.close();
    }
}
