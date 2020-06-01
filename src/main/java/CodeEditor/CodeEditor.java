package CodeEditor;


import api.RestApiController;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebView;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import ui.utils.bpp.PreProcessFiles;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * A syntax highlighting code editor for JavaFX created by wrapping a
 * CodeMirror code editor in a WebView.
 *
 * See http://codemirror.net for more information on using the codemirror editor.
 */
public class CodeEditor extends StackPane implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PreProcessFiles preProcessFiles = new PreProcessFiles();
        preProcessFiles.initPaths(false);
        rootFolder = PreProcessFiles.ROOT_FOLDER_PATH;
        try {
            editingTemplate = readFile(rootFolder + "/src/main/java/CodeEditor/htmlFileContent.txt", StandardCharsets.UTF_8);
            editingCode = readFile(rootFolder + "/src/main/java/CodeEditor/sampleText.txt", StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private GherkinValidator validator = new GherkinValidator();
    public WebView webview;
    public Label labeledCode;
    public Button revertEdits;
    public Button copyCode;
    public TextArea textArea;
    public ChoiceBox<String> choiceBox;

    String rootFolder;
    private String editingCode;
    private String editingTemplate;

    /** applies the editing template to the editing code to create the html+javascript source for a code editor. */
    private String applyEditingTemplate() {
        return editingTemplate.replace("${code}", editingCode);
    }

    /** sets the current code in the editor and creates an editing snapshot of the code which can be reverted to. */
    public void setCode() {
        webview.getEngine().loadContent(applyEditingTemplate());
    }

    public void setHtmlText() {
        Document doc = webview.getEngine().getDocument();
        validator.getNodeList(doc, "span","class","cm-string");
//        System.out.println(validator.getHtmlFromDocument(doc));

        StringBuilder invalidParameters = new StringBuilder();
        for(String parameter : validator.validateParameters(doc)) {
            invalidParameters.append(parameter);
            invalidParameters.append(", ");
        }
        labeledCode.setText(invalidParameters.toString());
        getCodeAndSnapshot();
    }



    /** returns the current code in the editor and updates an editing snapshot of the code which can be reverted to. */
    public String getCodeAndSnapshot() {
        this.editingCode = (String ) webview.getEngine().executeScript("editor.getValue();");
        return editingCode;
    }

    static String readFile(String path, Charset encoding)
            throws IOException
    {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

    public void writeFile(String fileName, String content)
            throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        writer.write(content);

        writer.close();
    }
}
