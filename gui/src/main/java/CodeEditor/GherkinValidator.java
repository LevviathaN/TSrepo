package CodeEditor;

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
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static java.nio.file.StandardOpenOption.CREATE;

public class GherkinValidator {

    public static Map<String,String> specialLocatorsMap;
    public static Map<String,String> locatorsMap;
    public static Map<String,String> stepPatternsMap;
    public static Map<String,String> stepSignaturesMap;
    public static List<String> reusablesList;
    public static Map<String, HashMap<String,Integer>> reusablesUsageMap;
    public static List<String> validLocatorsList;
    public static List<String> validSpecialLocatorsList;
    public static List<String> validStepdefsList;
    public static Map<String,String> featureFilesMap = new HashMap<>();


    public GherkinValidator() {
        locatorsMap = GuiHelper.getLocatorsMap(CodeEditorExample.frameworkFolder + "/src/main/resources/Locators.json");
        specialLocatorsMap = GuiHelper.getLocatorsMap(CodeEditorExample.frameworkFolder + "/src/main/resources/SpecialLocators.json");
        stepPatternsMap = GuiHelper.getLocatorsMap(CodeEditorExample.frameworkFolder + "/src/main/resources/NewStepPatterns.json");
        stepSignaturesMap = GuiHelper.getLocatorsMap(CodeEditorExample.frameworkFolder + "/src/main/resources/NewStepSignatures.json");
        reusablesList = getReusableScenariosList();
        reusablesUsageMap = getUsagesOfAllReusableScenarios();
        validLocatorsList = new ArrayList<>(GherkinValidator.locatorsMap.keySet());
        validSpecialLocatorsList = new ArrayList<>(GherkinValidator.specialLocatorsMap.keySet());
        validStepdefsList = new ArrayList<>(GherkinValidator.stepPatternsMap.values());
    }


    /** Method to get parameters that are not matching any available Locator from Locators.json from Document
     *
     * @author Ruslan Levytskyi
     * */
    public List<String> getInvalidParameters(Document doc) {
        List<Node> parametersNodeList = GuiHelper.getNodeList(doc, "span","class","cm-string");
        List<String> invalidParameters = new ArrayList<>();

        for (Node parameterNode : parametersNodeList) {
            String parameterText = parameterNode.getTextContent();
            if (parameterText.startsWith("\"") && parameterText.endsWith("\"")) {
                String parameterWithoutParentheses = parameterText.substring(1,parameterText.length()-1);
                if (!locatorsMap.containsKey(parameterWithoutParentheses)) {
                    invalidParameters.add(parameterWithoutParentheses);
                }
            }
        }
        return invalidParameters;
    }

    /** Method to get parameters that are not matching any available Locator from Locators.json from Document
     *
     * @author Ruslan Levytskyi
     * */
    public List<String> getInvalidStepdefs(Document doc) {
        List<Node> linessNodeList = GuiHelper.getNodeList(doc, "span","role","presentation");
        List<String> invalidStepdefs = new ArrayList<>();

        for (Node lineNode : linessNodeList) {
            String lineText = lineNode.getTextContent().trim();
            if (lineText.startsWith("Given") || lineText.startsWith("When")
                    || lineText.startsWith("Then") || lineText.startsWith("And") || lineText.startsWith("But")) {
                String lineWithoutkeyword = lineText.replaceAll("^(Given |When |Then |And |But )", "");
                if (!isValidStepdef(lineWithoutkeyword)) {
                    invalidStepdefs.add(lineWithoutkeyword);
                }
            }
        }
        return invalidStepdefs;
    }

    /** Method to get parameters that are not matching any available Locator from Locators.json from String
     *
     * @author Ruslan Levytskyi
     * */
    public List<String> getInvalidStepdefs(String doc) {
        List<String> invalidStepdefs = new ArrayList<>();
        List<String> allDocumentLines = Arrays.asList(doc.split("\n"));

        for (String line : allDocumentLines) {
            String lineText = line.trim();
            if (lineText.startsWith("Given") || lineText.startsWith("When")
                    || lineText.startsWith("Then") || lineText.startsWith("And") || lineText.startsWith("But")) {
                String lineWithoutkeyword = lineText.replaceAll("^(Given |When |Then |And |But )", "");
                if (!isValidStepdef(lineWithoutkeyword)) {
                    invalidStepdefs.add(lineWithoutkeyword);
                }
            }
        }
        return invalidStepdefs;
    }

    /** Method to get parameters that are not matching any available Locator from Locators.json from String
     *
     * @author Ruslan Levytskyi
     * */
    public List<String> getInvalidParameters(String doc) {
        List<String> quotedList = GuiHelper.getQuoted(doc,'"');
        List<String> invalidParameters = new ArrayList<>();

        for (String quoted : quotedList) {
            if (!locatorsMap.containsKey(quoted)) {
                invalidParameters.add(quoted);
            }
        }
        return invalidParameters;
    }

    public boolean isValidStepdef(String stepdef) {
        boolean validNode = false;
        for (String stepPattern : stepPatternsMap.values()) {
            if (stepdef.matches(stepPattern)) {
                validNode = true;
                break;
            }
        }
        return validNode;
    }

    public List<String> getReusableScenariosList() {
        ArrayList<String> availableReusableStepsList = new ArrayList<>();

        try {
            File inputFile = new File(CodeEditorExample.frameworkFolder + "/src/main/resources/data/bpp/ReusableTestSteps.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            Node reusablesNode = doc.getElementsByTagName("reusables").item(0);
            Element reusablesElement = (Element) reusablesNode;

            NodeList reusablesList = reusablesElement.getElementsByTagName("reusable");
            for (int i = 0; i < reusablesList.getLength(); i++) {
                Node reusableNode = reusablesList.item(i);
                Element reusableElement = (Element) reusableNode;
                availableReusableStepsList.add(reusableElement.getAttribute("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return availableReusableStepsList;
    }

    public List<String> getReusableScenariosList(String tag) {
        ArrayList<String> availableReusableStepsList = new ArrayList<>();

        try {
            File inputFile = new File(CodeEditorExample.frameworkFolder + "/src/main/resources/data/bpp/ReusableTestSteps.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            Node reusablesNode = doc.getElementsByTagName("reusables").item(0);
            Element reusablesElement = (Element) reusablesNode;

            NodeList reusablesList = reusablesElement.getElementsByTagName("reusable");
            for (int i = 0; i < reusablesList.getLength(); i++) {
                Node reusableNode = reusablesList.item(i);
                Element reusableElement = (Element) reusableNode;
                Node tagsNode = reusableElement.getElementsByTagName("tags").item(0);
                for (int j = 0; j < ((Element) tagsNode).getElementsByTagName("tag").getLength(); j++) {
                    if (((Element) tagsNode).getElementsByTagName("tag").item(j).getTextContent().equals(tag)) {
                        availableReusableStepsList.add(reusableElement.getAttribute("name"));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return availableReusableStepsList;
    }

    /**
     * @author Ruslan Levytskyi
     */
    public Map<String, HashMap<String,Integer>> getUsagesOfAllReusableScenarios() {

        Map<String, HashMap<String,Integer>> usageMap = new HashMap<>();

        try {
            File inputFile = new File("framework/src/main/resources/data/bpp/PFScenarios.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            ArrayList<String> availableScenariosList = new ArrayList<>();

            Node reusablesNode = doc.getElementsByTagName("scenarios").item(0);
            Element scenariosElement = (Element) reusablesNode;

            NodeList allStepsList = scenariosElement.getElementsByTagName("step");

            for (String reusableName : GherkinValidator.reusablesList) {
                HashMap<String,Integer> scenariosMap = new HashMap<>();
                for (int i = 0; i < allStepsList.getLength(); i++) {
                    Node stepNode = allStepsList.item(i);
                    Element stepElement = (Element) stepNode;

                    if (stepElement.getTextContent().contains("I execute") && stepElement.getTextContent().contains(reusableName)) {
                        Node scenarioNode = stepNode.getParentNode();
                        Element scenarioElement = (Element) scenarioNode;
                        String scenarioName = scenarioElement.getAttribute("name");
                        if (scenariosMap.containsKey(scenarioName)) {
                            scenariosMap.put(scenarioName,scenariosMap.get(scenarioName)+1);
                        } else {
                            scenariosMap.put(scenarioName, 1);
                        }
                    }
                }
                usageMap.put(reusableName,scenariosMap);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return usageMap;
    }

    public void reusablesStepsMapToFile(Map<String, List<String>> reusableList, String filePath) {
        String step;
        String fileContent = "<scenarios>";

        for (String scenarioName : reusableList.keySet()) {
            scenarioName = scenarioName.replaceAll("<", "&lt;");
            scenarioName = scenarioName.replaceAll(">", "&gt;");
            scenarioName = scenarioName.replaceAll("&", "&amp;");
            step = "<scenario name=\"" + scenarioName + "\">";
            for(String currentStep : reusableList.get(scenarioName)){
                currentStep = currentStep.replaceAll("<", "&lt;");
                currentStep = currentStep.replaceAll(">", "&gt;");
                currentStep = currentStep.replaceAll("&", "&amp;");
                step = step + "<step>" + currentStep + "</step>";
            }
            step = step + "</scenario>";
            fileContent = fileContent + step;
        }

        fileContent = fileContent + "</scenarios>";
        byte[] data = fileContent.getBytes();
        Path p = Paths.get("framework/src/main/resources/data/bpp/TemporaryReusables.xml");

        try{
            Files.deleteIfExists(p);
        }catch(IOException e){e.printStackTrace();}

        try (OutputStream out = new BufferedOutputStream(Files.newOutputStream(p, CREATE))) {
            out.write(data, 0, data.length);
        } catch (IOException x) {
            System.err.println(x);
        }
    }

    public void reusablesUsagesMapToFile(Map<String, List<String>> reusableList, String filePath) {
        String usage = "Initial step";
        String fileContent = "<scenarios>";

        for (String scenarioName : reusableList.keySet()) {
            scenarioName = scenarioName.replaceAll("<", "&lt;");
            scenarioName = scenarioName.replaceAll(">", "&gt;");
            scenarioName = scenarioName.replaceAll("&", "&amp;");
            usage = "<scenario name=\"" + scenarioName + "\">";
            for(String currentUsage : reusableList.get(scenarioName)){
                currentUsage = currentUsage.replaceAll("<", "&lt;");
                currentUsage = currentUsage.replaceAll(">", "&gt;");
                currentUsage = currentUsage.replaceAll("&", "&amp;");
                usage = usage + "<usage>" + currentUsage + "</usage>";
            }
            usage = usage + "</scenario>";
            fileContent = fileContent + usage;
        }

        fileContent = fileContent + "</scenarios>";
        byte[] data = fileContent.getBytes();
        Path p = Paths.get("src/main/resources/data/bpp/PFScenarios.xml");

        try{
            Files.deleteIfExists(p);
        }catch(IOException e){e.printStackTrace();}

        try (OutputStream out = new BufferedOutputStream(Files.newOutputStream(p, CREATE))) {
            out.write(data, 0, data.length);
        } catch (IOException x) {
            System.err.println(x);
        }
    }

    /**
     * @author Ruslan Levytskyi
     */
    public ArrayList<String> getUsagesOfReusableScenario(String reusableName) {

        ArrayList<String> scenariosList = new ArrayList<>();

        try {
            File inputFile = new File("framework/src/main/resources/data/bpp/PFScenarios.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            ArrayList<String> availableScenariosList = new ArrayList<>();

            Node reusablesNode = doc.getElementsByTagName("scenarios").item(0);
            Element scenariosElement = (Element) reusablesNode;

            NodeList allStepsList = scenariosElement.getElementsByTagName("step");
            for (int i = 0; i < allStepsList.getLength(); i++) {
                Node stepNode = allStepsList.item(i);
                Element stepElement = (Element) stepNode;

                if (stepElement.getTextContent().contains("I execute") && stepElement.getTextContent().contains(reusableName)) {
                    Node scenarioNode = stepNode.getParentNode();
                    Element scenarioElement = (Element) scenarioNode;
                    String scenarioName = scenarioElement.getAttribute("name");
                    if (scenariosList.contains(scenarioName)) {
                        int qtt = 0;
                        for (int j = 0; j < scenariosList.size(); i++) {
                            String name = scenariosList.get(j);
                            if (name.equals(scenarioName)) {
                                scenariosList.remove(j);
                                qtt++;
                            }
                        }
                        scenariosList.add(scenarioName + " (" + qtt + ")");
                    } else {
                        scenariosList.add(scenarioName);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return scenariosList;
    }

    public String getReusableScenarioFromFeature(String scenarioName, String featureName) {
        String filePath = featureFilesMap.get("ReusableSteps" + featureName + ".feature"); //todo remove hardcode!
        String feature = GuiHelper.readFile(filePath, StandardCharsets.UTF_8).replaceAll("\r","");
        int i = feature.indexOf(scenarioName + "\n");
        String scenarioPlus = feature.substring(i);
        int j = scenarioPlus.indexOf("Scenario: ");
        return "Feature: Edit\nScenario: " + scenarioPlus.substring(0,j);
    }
}
