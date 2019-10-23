package utils.bpp;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.HashMap;

public class DomParserDemo {



    public static void main(String[] args) {

        UltimateBPParser featureCreator = new UltimateBPParser();
        featureCreator.initiateFeatureFile();

        try {
            File inputFile = new File("TestDoablesBeans.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();


            Node beanListNode = doc.getElementsByTagName("list").item(0);
            Element beanListElement = (Element) beanListNode;
//            System.out.println("Root element : " + doc.getDocumentElement().getNodeName());
            NodeList beanList = beanListElement.getElementsByTagName("bean");
//            System.out.println("----------------------------");

            for (int i = 0; i < beanList.getLength(); i++) {
                HashMap<String,String> parametersMap = new HashMap<>();

                Node beanNode = beanList.item(i);
                Element beanElement = (Element) beanNode;
//                System.out.println(beanNode.getNodeName() + " : " + beanElement.getAttribute("class"));
                parametersMap.put("doableName",beanElement.getAttribute("class"));
                NodeList propertiesList = beanElement.getElementsByTagName("property");


                for (int j = 0; j < propertiesList.getLength(); j++) {
                    Node propertyNode = propertiesList.item(j);
//                    System.out.println("\n" + propertyNode.getNodeName());
                    Element propertyElement = (Element) propertyNode;

//                    System.out.println(propertyElement.getAttribute("name") + " : " + propertyElement.getAttribute("value"));
                    parametersMap.put(propertyElement.getAttribute("name"),propertyElement.getAttribute("value"));
                }

                featureCreator.stepBuilder(parametersMap);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        featureCreator.createDocument();
    }
}