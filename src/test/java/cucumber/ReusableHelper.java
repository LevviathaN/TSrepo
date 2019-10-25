package cucumber;

import gherkin.pickles.Pickle;
import gherkin.pickles.PickleStep;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.nio.file.StandardOpenOption.CREATE;

public class ReusableHelper {

    String fileContent;
    String step;

    public void initiateXmlFile(){
        step = "Initial step";
        fileContent = "<reusables>";
    }

    public void createReusablesXml(){
        fileContent = fileContent + "</reusables>";
        byte data[] = fileContent.getBytes();
        Path p = Paths.get("src/main/resources/data/bpp/ReusableTestSteps.xml");


        try (OutputStream out = new BufferedOutputStream(
                Files.newOutputStream(p, CREATE))) {
            out.write(data, 0, data.length);
        } catch (IOException x) {
            System.err.println(x);
        }
    }

    public void stepBuilder(Pickle pickle){
        step = "<reusable name=" + pickle.getName() + ">";
        for(PickleStep pickleStep : pickle.getSteps()){
            step = step + "<step>" + pickleStep.getText() + "</step>";
        }
        step = step + "</reusable>";
        fileContent = fileContent + step;
    }

    public ArrayList<String> getReusablePickleSteps(String pickleName){
        ArrayList<String> stepsList = new ArrayList<>();

        try{
            File inputFile = new File("TestDoablesBeans.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();


            NodeList reusablesList = doc.getElementsByTagName("reusable");
            for (int i = 0; i < reusablesList.getLength(); i++) {
                Node reusableNode = reusablesList.item(i);
                Element reusableElement = (Element) reusableNode;
                if(reusableElement.getAttribute("name").equals(pickleName)){
                    NodeList steps = reusableElement.getElementsByTagName("step");
                    for (int j = 0; j < steps.getLength(); j++){
                        stepsList.add(steps.item(j).getTextContent());
                    }
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return stepsList;
    }
}
