package utils;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import utils.bpp.UltimateBPParser;

import java.io.File;
import java.util.HashMap;

/**
 * Created by Ruslan Levytskyi on 05.07.19.
 */

public class XmlReader {

    String filePath;
    String fileName;
    String fileContent;
    Document doc;

    public XmlReader(String _filePath){
        filePath = _filePath;
        fileContent = "";
        doc = getDocument();
    }


    public void initiateRead(){

    }

    public Document getDocument(){
        try {
            File inputFile = new File(filePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return doc;
    }

    public NodeList getNodeListByTagName(String tagName){
        return doc.getElementsByTagName(tagName);
    }

    public Node getNodeByTagName(String tagName, int index){
        return getNodeListByTagName(tagName).item(index);
    }

    public Element getElementByTagName(String tagName, int index){
        return (Element) getNodeByTagName(tagName, index);
    }



    public NodeList getNodeListOfElementByTagName(Element element, String tagName){
        return element.getElementsByTagName(tagName);
    }

    public Node getNodeOfElementByTagName(Element element, String tagName, int index){
        return getNodeListOfElementByTagName(element, tagName).item(index);
    }



    public void mai(String[] args) {

        UltimateBPParser featureCreator = new UltimateBPParser();
        featureCreator.initiateFeatureFile();


        try {



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
