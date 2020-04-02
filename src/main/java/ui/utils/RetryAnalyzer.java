package ui.utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.concurrent.ConcurrentHashMap;

public class RetryAnalyzer implements IRetryAnalyzer {

    int counter = 0;
    public static ConcurrentHashMap<String, Integer> counterMap = new ConcurrentHashMap<>();
    public static int limit = 4;

    public synchronized boolean retry(ITestResult result) {
        String scenarioName;
        String actualScenarioName = Reporter.getCurrentTestName();
        if (actualScenarioName.contains("attempt")) {
            scenarioName = actualScenarioName.substring(0,actualScenarioName.length()-10);
        } else {
            scenarioName = actualScenarioName;
        }
        if (!counterMap.containsKey(scenarioName)) {
            counterMap.put(scenarioName,1);
        } else {
            counterMap.replace(scenarioName,counterMap.get(scenarioName)+1);
        }
        if (counterMap.get(scenarioName) < limit){
            counter++;
            return true;
        } else {
            return false;
        }
    }

    public static void deleteFailedTestsFromHtmlReport(String testName) {

        String filePath = Reporter.getScreenshotFolder().toString().replace("screenshots","report.html");

        try {
            File inputFile = new File(filePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            NodeList spanNodesList = doc.getElementsByTagName("span");
            for (int i = 0; i < spanNodesList.getLength(); i++) {
                Node spanNode = spanNodesList.item(i);
                NamedNodeMap attributes = spanNode.getAttributes();
                if (attributes.getNamedItem("class").getNodeValue().equals("test-name test-name-list")) {
                    if (spanNode.getTextContent().contains(testName)) {
                        doc.removeChild(spanNode.getParentNode().getParentNode());
                    }
                }
            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();

            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(doc);

            StreamResult streamResult = new StreamResult(new File(filePath));
            transformer.transform(domSource, streamResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
