package utils;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

/**
 * Created by Ruslan Levytskyi on 05.07.19.
 */

public class XmlReader {

    private static File fXmlFile;
    private static DocumentBuilderFactory dbFactory;
    private static DocumentBuilder dBuilder;
    private static Document doc;
    private static Node rootNode;

    public XmlReader(String file){
        try{
            fXmlFile = new File(file);
            dbFactory = DocumentBuilderFactory.newInstance();
            dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();
//            rootNode = doc.getDocumentElement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //BPS specific method
    public int getDescriptionsQty(){
        NodeList nList = doc.getElementsByTagName("productDescriptions2018");
        return nList.getLength();
    }

    public String getActionType(){
        return "";
    }

    public void go() {

        try {
            NodeList nList = doc.getElementsByTagName("productDescriptions2018");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    System.out.println("Title : " + eElement.getElementsByTagName("title").item(0).getTextContent());
                    System.out.println("Author : " + eElement.getElementsByTagName("author").item(0).getTextContent());
                    System.out.println("Action : " + eElement.getElementsByTagName("action").item(0).getTextContent());
                    System.out.println("ID : " + eElement.getElementsByTagName("id").item(0).getTextContent());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
