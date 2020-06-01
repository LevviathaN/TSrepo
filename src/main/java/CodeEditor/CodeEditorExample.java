package CodeEditor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class CodeEditorExample extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        URL url = new URL("file:/Users/ruslanlevytskyi/IdeaProjects/qa-automation-framework-poc/src/main/java/CodeEditor/CodeEditorLayout.fxml");
        Parent root = FXMLLoader.load(url);
        primaryStage.setTitle("BDD Test Manager");
        primaryStage.setScene(new Scene(root, 800, 700));
        primaryStage.show();
    }


    public void startCodeEditor(String[] args) {
        launch(args);
    }


}
