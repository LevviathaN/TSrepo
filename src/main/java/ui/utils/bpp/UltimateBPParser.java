package ui.utils.bpp;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

import static java.nio.file.StandardOpenOption.CREATE;

public class UltimateBPParser {

    String fileContent;
    String fileName;
    String businessProcess;
    String tesT;

    public void initiateFeatureFile(){
        businessProcess = "Initial PB";
        fileContent = "Feature: Product Factory\n" +
                      "  As a product setter\n" +
                      "  I want to be able to log into Product Factory\n" +
                      "  In order to (just cause)\n";
    }

    public void createDocument() {

        // Convert the string to a byte array.
        byte data[] = fileContent.getBytes();
        Path p = Paths.get("src/test/resources/cucumber/bpp_features/TestFeature.feature");


        try (OutputStream out = new BufferedOutputStream(
                Files.newOutputStream(p, CREATE))) {
            out.write(data, 0, data.length);
        } catch (IOException x) {
            System.err.println(x);
        }
    }

    public void stepBuilder(HashMap<String,String> parameters){
        String step;

        if(!parameters.get("test").equals(tesT)){
            fileContent = fileContent + "\n\n\n  Scenario: " +
                    parameters.get("test") + "\n" +
                    "  #//////////////////////////////////////////////////////////////////////////////////////////\n" +
                    "  #//////////////////////////////////////////////////////////////////////////////////////////\n";
            tesT = parameters.get("test");
        }

        if(!parameters.get("businessProcess").equals(businessProcess)){
            fileContent = fileContent + "\n    #____________________________Business Process - " +
                    parameters.get("businessProcess") + "____________________________\n";
            businessProcess = parameters.get("businessProcess");
        }

        switch(parameters.get("doableName")){
            case "com.apolloglobal.testing.api.impl.GoToUrlDoable":
                step = "    Given I am on \"" + parameters.get("value") + "\" BPP URL";
                System.out.println(step);
                break;
            case "com.apolloglobal.testing.api.impl.ClickDoable":
                step = "    When I click on the BPP element located by \"" + parameters.get("elementLocation") + "\"";
                System.out.println(step);
                break;
            case "com.apolloglobal.testing.api.impl.SetTextDoable":
                step = "    When I fill the \"" + parameters.get("elementLocation") + "\" BPP field with \"" +
                        parameters.get("value") + "\"";
                System.out.println(step);
                break;
            case "com.apolloglobal.testing.api.impl.CaptureDataDoable":
                step = "    When I capture data from \"" + parameters.get("elementLocation") + "\" BPP element into \"" +
                        parameters.get("value") + "\" variable";
                System.out.println(step);
                break;
            case "com.apolloglobal.testing.api.impl.WaitDoable":
                step = "    When I wait for \"" + parameters.get("value") + "\" seconds at BPP page";
                System.out.println(step);
                break;
            case "com.apolloglobal.testing.api.impl.ElementNotVisibleDoable":
                step = "    When I verify that element located by \"" + parameters.get("elementLocation") +
                        "\" is not visible on BPP page";
                System.out.println(step);
                break;
            case "com.apolloglobal.testing.api.impl.ValidateTextDoable":
                step = "    When I verify that BPP element \"" + parameters.get("elementLocation") + "\" contains text \""
                        + parameters.get("value") + "\"";
                System.out.println(step);
                break;
            case "com.apolloglobal.testing.api.impl.SmartClientClickDoable":
                step = "    When I SmartClient click on BPP element \"" + parameters.get("elementLocation") + "\"";
                System.out.println(step);
                break;
            case "com.apolloglobal.testing.api.impl.JSAlertDoable":
                step = "    When I confirm BPP JS Alert \"" + parameters.get("value") + "\"";
                System.out.println(step);
                break;
            case "com.apolloglobal.testing.api.impl.PressKeyDoable":
                step = "    When I press the \"" + parameters.get("value") + "\" key on the keyboard for BPP";
                System.out.println(step);
                break;
            case "com.apolloglobal.testing.api.impl.JSExecutorDoable":
                step = "    When I execute \"" + parameters.get("value") + "\" JS script for BPP";
                System.out.println(step);
                break;
            case "com.apolloglobal.testing.api.impl.ValidateObjectPropertyDoable":
                step = "    When I validate that BPP element \"" + parameters.get("elementLocation") +
                        "\" has \"" + parameters.get("value") + "\"";
                System.out.println(step);
                break;
            case "com.apolloglobal.testing.api.impl.CheckboxDoable":
                step = "    When I switch \"" + parameters.get("elementLocation") + "\" BPP checkbox to \"" +
                        parameters.get("value") + "\"";
                System.out.println(step);
                break;
            case "com.apolloglobal.testing.api.impl.SelectFromDropdownDoable":
                step = "    When I select \"" + parameters.get("value") + "\" from BPP dropdown \"" +
                        parameters.get("elementLocation") +"\"";
                System.out.println(step);
                break;
            case "com.apolloglobal.testing.api.impl.SwitchToWindowDoable":
                step = "    When I switch to \"" + parameters.get("value") + "\" BPP window";
                System.out.println(step);
                break;
            default:
                step = "Can not recognise doable: " + parameters.get("doableName");
                break;
        }

        fileContent = fileContent + step + "\n";
    }

}
