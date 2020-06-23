package CodeEditor;

import api.RestApiController;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import ui.pages.BasePage;
import ui.utils.Tools;

import java.util.ArrayList;
import java.util.List;

public class GherkinValidator {

    RestApiController apiController = new RestApiController();

    public GherkinValidator() {
        BasePage.locatorsMap = apiController.processLocatorProperties("//src/main/resources/Locators.json");
        BasePage.specialLocatorsMap = apiController.processLocatorProperties("//src/main/resources/SpecialLocators.json");
        BasePage.stepPatternsMap = apiController.processLocatorProperties("//src/main/resources/StepPatterns.json");
        BasePage.stepSignaturesMap = apiController.processLocatorProperties("//src/main/resources/StepSignatures.json");
    }


    /** Method to get parameters that are not matching any available Locator from Locators.json from Document
     *
     * @author Ruslan Levytskyi
     * */
    public List<String> getInvalidParameters(Document doc) {
        List<Node> parametersNodeList = Tools.getNodeList(doc, "span","class","cm-string");
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

    /** Method to get parameters that are not matching any available Locator from Locators.json from Document
     *
     * @author Ruslan Levytskyi
     * */
    public List<String> getInvalidStepdefs(Document doc) {
        List<Node> linessNodeList = Tools.getNodeList(doc, "span","role","presentation");
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
    public List<String> getInvalidParameters(String doc) {
        List<String> quotedList = Tools.getQuoted(doc,'"');
        List<String> invalidParameters = new ArrayList<>();

        for (String quoted : quotedList) {
            if (!BasePage.locatorsMap.containsKey(quoted)) {
                invalidParameters.add(quoted);
            }
        }
        return invalidParameters;
    }

    public boolean isValidStepdef(String stepdef) {
        boolean validNode = false;
        for (String stepPattern : BasePage.stepPatternsMap.values()) {
            if (stepdef.matches(stepPattern)) {
                validNode = true;
                break;
            }
        }
        return validNode;
    }
}
