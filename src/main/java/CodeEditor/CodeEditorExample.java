package CodeEditor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.utils.bpp.PreProcessFiles;

import java.net.URL;

public class CodeEditorExample extends Application {

    public static String rootFolder;

    @Override
    public void start(Stage primaryStage) throws Exception{
        PreProcessFiles preProcessFiles = new PreProcessFiles();
        preProcessFiles.initPaths(false);
        rootFolder = PreProcessFiles.ROOT_FOLDER_PATH;

        URL url = new URL("file:" + rootFolder + "/src/main/java/CodeEditor/CodeEditorLayout.fxml");
        final FXMLLoader loader = new FXMLLoader(url);
        final Parent root = (Parent) loader.load();

        primaryStage.setTitle("BDD Test Manager");
        primaryStage.setScene(new Scene(root, 800, 700));
        primaryStage.show();
    }


    public void startCodeEditor(String[] args) {
        launch(args);
    }


}
