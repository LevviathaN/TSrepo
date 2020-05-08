package CodeEditor;

import api.RestApiController;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import ui.pages.BasePage;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class GherkinValidator {

    RestApiController apiController = new RestApiController();

    public GherkinValidator() {
        BasePage.locatorsMap = apiController.processLocatorProperties("//src/main/resources/Locators.json");
    }

    public List<Node> getNodeList(Document doc, String nodeName, String attributeName, String attributeValue) {
        List<Node> neededNodesList = new ArrayList<>();
        NodeList nodeList = doc.getElementsByTagName(nodeName);

        Node nodeFromList;
        List<String> nodeValuesList = new ArrayList<>();
        for (int i=0; i < nodeList.getLength(); i++) {
            nodeFromList = nodeList.item(i);
            NamedNodeMap nodeFromListAttributes = nodeFromList.getAttributes();

            Node attributeNode;
            for (int j=0; j < nodeFromListAttributes.getLength(); j++) {
                attributeNode = nodeFromListAttributes.item(j);
                if (attributeNode.getNodeName().contains(attributeName)){
                    if (attributeNode.getNodeValue().contains(attributeValue)) {
                        neededNodesList.add(nodeFromList);
                        String spanNodeValue = nodeFromList.getTextContent();
                        nodeValuesList.add(spanNodeValue);
//                        System.out.println(spanNodeValue);
                    }
                }
            }
        }
        return neededNodesList;
    }

    public String getHtmlFromDocument(Document doc) {
        String htmlString;
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        try {
            printDocument(doc, stream);
            htmlString = new String(stream.toByteArray());
            return htmlString;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void printDocument(Document doc, OutputStream out) throws IOException, TransformerException {
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

        transformer.transform(new DOMSource(doc),
                new StreamResult(new OutputStreamWriter(out, "UTF-8")));
    }

    public List<String> validateParameters(Document doc) {
        List<Node> parametersNodeList = getNodeList(doc, "span","class","cm-string");
        List<String> invalidParameters = new ArrayList<>();

        for (Node parameterNode : parametersNodeList) {
            String parameterText = parameterNode.getTextContent();
            if (parameterText.startsWith("\"") && parameterText.endsWith("\"")) {
                String parameterWithoutParentheses = parameterText.substring(1,parameterText.length()-1);
                if (!BasePage.locatorsMap.containsKey(parameterWithoutParentheses)) {
                    invalidParameters.add(parameterWithoutParentheses);
                }
            }
        }
        return invalidParameters;
    }
}
