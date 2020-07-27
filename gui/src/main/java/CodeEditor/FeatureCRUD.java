package CodeEditor;

import javafx.fxml.FXMLLoader;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import ui.utils.bpp.PreProcessFiles;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardOpenOption.CREATE;

public class FeatureCRUD {

    public static Stage createFeatureWindow;
    public TextField featureNameField;
    public Label messageLabel;

    /** Displays "Save Feature File modal window" */
    public void display() throws Exception {
        URL url = new URL("file:" + CodeEditorExample.guiFolder + "/src/main/java/CodeEditor/CreateFeatureModal.fxml");
        Parent root = FXMLLoader.load(url);
        createFeatureWindow = new Stage();
//        createFeatureWindow.initModality(Modality.APPLICATION_MODAL);
        createFeatureWindow.setTitle("Save feature file");
        createFeatureWindow.setMinWidth(250);

        Scene scene = new Scene(root, 300, 120);
        createFeatureWindow.setScene(scene);
        createFeatureWindow.showAndWait();

    }

    /** Method to create feature file using the name fom text field*/
    public void createFeatureFile() {
        byte[] data = CodeEditor.editingCode.getBytes();
        Path p = Paths.get("src/test/resources/cucumber/bpp_features/featuresFromGUI/" + featureNameField.getText() + ".feature");

        try {
            OutputStream out = new BufferedOutputStream(Files.newOutputStream(p, CREATE));
            out.write(data, 0, data.length);
            out.close();
            messageLabel.setText("File saved!");
        } catch (IOException x) {
            System.err.println(x);
        }

        createFeatureWindow.close();
    }

    /** Method to close Save File modal */
    public void cancelCreation() {
        createFeatureWindow.close();
    }
}
