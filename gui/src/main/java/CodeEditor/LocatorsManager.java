package CodeEditor;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import ui.pages.BasePage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class LocatorsManager {

    public static Stage locatorsManagerWindow;
    public TableView<Locator> locatorsTable;
    public TableColumn<Locator,String> nameColumn;
    public TableColumn<Locator,String> valueColumn;

    /** Displays "Save Feature File modal window" */
    public void display() throws Exception {
        URL url = new URL("file:" + CodeEditorExample.guiFolder + "/src/main/java/CodeEditor/LocatorsManagerLayout.fxml");
        Parent root = FXMLLoader.load(url);
        locatorsManagerWindow = new Stage();
//        createFeatureWindow.initModality(Modality.APPLICATION_MODAL);
        locatorsManagerWindow.setTitle("Locators Manager");
        locatorsManagerWindow.setMinWidth(250);


        Scene scene = new Scene(root, 300, 200);
        locatorsManagerWindow.setScene(scene);
        locatorsManagerWindow.showAndWait();

    }

    public void initTable() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        valueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
        locatorsTable.setItems(getLocators());
    }

    public ObservableList<Locator> getLocators() {
        ObservableList<Locator> locatorsList = FXCollections.observableArrayList();
        for (String key : BasePage.locatorsMap.keySet()) {
            locatorsList.add(new Locator(key,BasePage.locatorsMap.get(key)));

        }
        return locatorsList;
    }

    /** Method to close Save File modal */
    public void cancelCreation() {
        locatorsManagerWindow.close();
    }
}
