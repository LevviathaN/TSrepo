package api.requests;

import api.*;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import ui.utils.BPPLogManager;
import ui.utils.Reporter;
import ui.utils.Tools;
import ui.utils.bpp.ExecutionContextHandler;
import ui.utils.bpp.PropertiesHelper;
import ui.utils.bpp.TestParametersController;
import java.io.IOException;
import static com.jcabi.matchers.RegexMatchers.matchesPattern;
import static org.junit.Assert.assertThat;
import static org.junit.internal.matchers.StringContains.containsString;

/**
 *<p> Contains all methods required for main Product Factory API calls to create End to End</p>
 */

public class ProductFactoryBusinessProcesses {

    private PropertiesHelper propertiesHelper = new PropertiesHelper();
    private RestApiController restController;

    public ProductFactoryBusinessProcesses() {
        this.restController = new RestApiController();
    }

    public void createNewISBNcode() throws IOException {
        Document doc = Jsoup.connect("https://generate.plus/en/number/isbn").get();
        Element result = doc.select("div[class='content single text-center text-bold h1 char-break']").first();
        ExecutionContextHandler.setExecutionContextValueByKey("EC_ISBN", result.text());

        assertThat(result.text(), matchesPattern("^[0-9]{1}-[0-9]{4}-[0-9]{4}-[0-9]{1}"));

        Reporter.log("<pre>" + "ISBN nubmer: " + result.text() + "</pre>");
        BPPLogManager.getLogger().info("ISBN Code was successfully created. ISBN: " + result.text());
    }

    public ProductFactoryBusinessProcesses createNewFinancialDimension(String Parameter1, String Parameter2) {

        Response financialDimensionResponse = restController.postRequest(propertiesHelper.getProperties().getProperty("pf_request_link"),
                restController.processPropertiesPF("ProductFactory/addFinancialDimension",
                        TestParametersController.checkIfSpecialParameter(Parameter1),
                        TestParametersController.checkIfSpecialParameter(Parameter2)
                ),
                ProductFactoryAuthentication.getInstance().requestHeaderSpecification()
        );
        String ResponseString = financialDimensionResponse.getBody().asString();

        JSONObject recordsObject = new Utilities().getResponseProperty(financialDimensionResponse);
        JSONObject recordsData = (JSONObject) recordsObject.get("data");
        JSONObject recordsList = (JSONObject) recordsData.get("createFinancialDimension");

        /*Get Json object values*/
        String Reference = null;
        try {
            Reference = (String) recordsList.get("reference");
        } catch (Exception e) {
            BPPLogManager.getLogger().error(Tools.getStackTrace(e));
            Reporter.fail("<br>" + Tools.getStackTrace(e) + "</br>");
            throw new RuntimeException("Can't proceed with response: " + Reference + " Please check -corespondent.json- file. Possible duplication or empty stings");
        }

        String DimensionType = (String) recordsList.get("dimensionType");
        String Target = (String) recordsList.get("target");
        String Code = (String) recordsList.get("code");
        String Description = (String) recordsList.get("description");

        assertThat(Reference, matchesPattern("([a-z0-9-]){36}"));
        assertThat(ResponseString, containsString("createFinancialDimension"));

        /*Set EC values for JSON object values*/
        ExecutionContextHandler.setExecutionContextValueByKey("EC_FD_" + Target.toUpperCase().replace(" ", "_") + "_" + DimensionType.toUpperCase().replace(" ", "_") + "_REFERENCE", Reference);
        ExecutionContextHandler.setExecutionContextValueByKey("EC_FD_" + Target.toUpperCase().replace(" ", "_") + "_" + DimensionType.toUpperCase().replace(" ", "_") + "_DIMENSION_TYPE", DimensionType);
        ExecutionContextHandler.setExecutionContextValueByKey("EC_FD_" + Target.toUpperCase().replace(" ", "_") + "_" + DimensionType.toUpperCase().replace(" ", "_") + "_TARGET", Target);
        ExecutionContextHandler.setExecutionContextValueByKey("EC_FD_" + Target.toUpperCase().replace(" ", "_") + "_" + DimensionType.toUpperCase().replace(" ", "_") + "_CODE", Code);
        ExecutionContextHandler.setExecutionContextValueByKey("EC_FD_" + Target.toUpperCase().replace(" ", "_") + "_" + DimensionType.toUpperCase().replace(" ", "_") + "_DESCRIPTION", Description);

        /*Report log with Json object values*/
        Reporter.log("<pre>" +
                "<br>Financial Dimension - " + Target + " - " + DimensionType + ": " +
                "<br>" + "Financial Dimension " + Target + " " + DimensionType + " Reference: " + "<font color='red'><b>" + Reference + "</font></b>" +
                "<br>" + "Financial Dimension " + Target + " " + DimensionType + " Code: " + "<font color='red'><b>" + Code + "</font></b>" +
                "<br>" + "Financial Dimension " + Target + " " + DimensionType + " Dimension Type: " + "<font color='red'><b>" + DimensionType + "</font></b>" +
                "<br>" + "Financial Dimension " + Target + " " + DimensionType + " Target: " + "<font color='red'><b>" + Target + "</font></b>" +
                "<br>" + "Financial Dimension " + Target + " " + DimensionType + " Description: " + "<font color='red'><b>" + Description + "</font></b>" +
                "</pre>");

        BPPLogManager.getLogger().info("Financial Dimension of " + Parameter1 + "and " + Parameter2 + " was successfully created.");

        return this;
    }

    public ProductFactoryBusinessProcesses createNewVatRule() {
        Response vatRuleResponse = restController.postRequest(propertiesHelper.getProperties().getProperty("pf_request_link"),
                restController.processPropertiesPF("ProductFactory/addVatRule",null,null),
                ProductFactoryAuthentication.getInstance().requestHeaderSpecification()
        );
        String ResponseString = vatRuleResponse.getBody().asString();

        JSONObject recordsObject = new Utilities().getResponseProperty(vatRuleResponse);
        JSONObject recordsData = (JSONObject) recordsObject.get("data");
        JSONObject recordsList = (JSONObject) recordsData.get("createVatRule");

        /*Get Json object values*/
        String Reference = null;
        try {
            Reference = TestParametersController.checkIfSpecialParameter(String.valueOf(recordsList.get("reference")));
        } catch (Exception e) {
            BPPLogManager.getLogger().error(Tools.getStackTrace(e));
            Reporter.fail("<br>" + Tools.getStackTrace(e) + "</br>");
            throw new RuntimeException("Can't proceed with response: " + Reference + " Please check -corespondent.json- file. Possible duplication or empty stings");
        }

        String Code = (String) recordsList.get("code");
        String Description = (String) recordsList.get("description");
        Long Rate = (Long) recordsList.get("rate");

        assertThat(Reference, matchesPattern("([a-z0-9-]){36}"));
        assertThat(ResponseString, containsString("createVatRule"));

        /*Set EC values for JSON object values*/
        ExecutionContextHandler.setExecutionContextValueByKey("EC_VAT_RULE_REFERENCE", Reference);
        ExecutionContextHandler.setExecutionContextValueByKey("EC_VAT_RULE_CODE", Code);
        ExecutionContextHandler.setExecutionContextValueByKey("EC_VAT_RULE_DESCRIPTION", Description);
        ExecutionContextHandler.setExecutionContextValueByKey("EC_VAT_RULE_RATE", String.valueOf(Rate));

        /*Report log with Json object values*/
        Reporter.log("<pre>" +
                "<br>Vat Rule: " +
                "<br>" + "Vat Rule Reference: " + "<font color='red'><b>" + Reference + "</font></b>" +
                "<br>" + "Vat Rule Code: " + "<font color='red'><b>" + Code + "</font></b>" +
                "<br>" + "Vat Rule Description: " + "<font color='red'><b>" + Description + "</font></b>" +
                "<br>" + "Vat Rule Rate: " + "<font color='red'><b>" + Rate + "</font></b>" +
                "</pre>");

        BPPLogManager.getLogger().info("Vat Rule was successfully created.");

        return this;
    }

    public ProductFactoryBusinessProcesses createNewExamPreparation() {
        Response examPreparationResponse = restController.postRequest(propertiesHelper.getProperties().getProperty("pf_request_link"),
                restController.processPropertiesPF("ProductFactory/addExamPreparation",null,null),
                ProductFactoryAuthentication.getInstance().requestHeaderSpecification()
        );
        String ResponseString = examPreparationResponse.getBody().asString();

        JSONObject recordsObject = new Utilities().getResponseProperty(examPreparationResponse);
        JSONObject recordsData = (JSONObject) recordsObject.get("data");
        JSONObject recordsList = (JSONObject) recordsData.get("createExamPreparation");

        /*Get Json object values*/
        String Reference = null;
        try {
            Reference = TestParametersController.checkIfSpecialParameter(String.valueOf(recordsList.get("reference")));
        } catch (Exception e) {
            BPPLogManager.getLogger().error(Tools.getStackTrace(e));
            Reporter.fail("<br>" + Tools.getStackTrace(e) + "</br>");
            throw new RuntimeException("Can't proceed with response: " + Reference + " Please check -corespondent.json- file. Possible duplication or empty stings");
        }

        String Name = TestParametersController.checkIfSpecialParameter(String.valueOf(recordsList.get("name")));
        String Description = TestParametersController.checkIfSpecialParameter(String.valueOf(recordsList.get("description")));

        assertThat(Reference, matchesPattern("([a-z0-9-]){36}"));
        assertThat(ResponseString, containsString("createExamPreparation"));

        /*Set EC values for JSON object values*/
        ExecutionContextHandler.setExecutionContextValueByKey("EC_EXAM_PREPARATION_REFERENCE", Reference);
        ExecutionContextHandler.setExecutionContextValueByKey("EC_EXAM_PREPARATION_NAME", Name);
        ExecutionContextHandler.setExecutionContextValueByKey("EC_EXAM_PREPARATION_DESCRIPTION", Description);

        /*Report log with Json object values*/
        Reporter.log("<pre>" +
                "<br>Exam Preparation: " +
                "<br>" + "Exam Preparation Reference: " + "<font color='red'><b>" + Reference + "</font></b>" +
                "<br>" + "Exam Preparation Name: " + "<font color='red'><b>" + Name + "</font></b>" +
                "<br>" + "Exam Preparation Description: " + "<font color='red'><b>" + Description + "</font></b>" +
                "</pre>");

        BPPLogManager.getLogger().info("Exam Preparation was successfully created.");

        return this;
    }

    public ProductFactoryBusinessProcesses createNewStudyMode() {
        Response studyModeResponse = restController.postRequest(propertiesHelper.getProperties().getProperty("pf_request_link"),
                restController.processPropertiesPF("ProductFactory/addStudyMode",null,null),
                ProductFactoryAuthentication.getInstance().requestHeaderSpecification()
        );
        String ResponseString = studyModeResponse.getBody().asString();

        JSONObject recordsObject = new Utilities().getResponseProperty(studyModeResponse);
        JSONObject recordsData = (JSONObject) recordsObject.get("data");
        JSONObject recordsList = (JSONObject) recordsData.get("createStudyMode");

        /*Get Json object values*/
        String Reference = null;
        try {
            Reference = TestParametersController.checkIfSpecialParameter(String.valueOf(recordsList.get("reference")));
        } catch (Exception e) {
            BPPLogManager.getLogger().error(Tools.getStackTrace(e));
            Reporter.fail("<br>" + Tools.getStackTrace(e) + "</br>");
            throw new RuntimeException("Can't proceed with response: " + Reference + " Please check -corespondent.json- file. Possible duplication or empty stings");
        }

        String Name = TestParametersController.checkIfSpecialParameter(String.valueOf(recordsList.get("name")));
        String Description = TestParametersController.checkIfSpecialParameter(String.valueOf(recordsList.get("description")));

        assertThat(Reference, matchesPattern("([a-z0-9-]){36}"));
        assertThat(ResponseString, containsString("createStudyMode"));

        /*Set EC values for JSON object values*/
        ExecutionContextHandler.setExecutionContextValueByKey("EC_STUDY_MODE_REFERENCE", Reference);
        ExecutionContextHandler.setExecutionContextValueByKey("EC_STUDY_MODE_NAME", Name);
        ExecutionContextHandler.setExecutionContextValueByKey("EC_STUDY_MODE_DESCRIPTION", Description);

        /*Report log with Json object values*/
        Reporter.log("<pre>" +
                "<br>Study Mode: " +
                "<br>" + "Study Mode Reference: " + "<font color='red'><b>" + Reference + "</font></b>" +
                "<br>" + "Study Mode Name: " + "<font color='red'><b>" + Name + "</font></b>" +
                "<br>" + "Study Mode Description: " + "<font color='red'><b>" + Description + "</font></b>" +
                "</pre>");

        BPPLogManager.getLogger().info("Study Mode was successfully created.");

        return this;
    }

    public ProductFactoryBusinessProcesses createNewCourseType() {
        Response studyModeResponse = restController.postRequest(propertiesHelper.getProperties().getProperty("pf_request_link"),
                restController.processPropertiesPF("ProductFactory/addCourseType",null,null),
                ProductFactoryAuthentication.getInstance().requestHeaderSpecification()
        );
        String ResponseString = studyModeResponse.getBody().asString();

        JSONObject recordsObject = new Utilities().getResponseProperty(studyModeResponse);
        JSONObject recordsData = (JSONObject) recordsObject.get("data");
        JSONObject recordsList = (JSONObject) recordsData.get("createCourseType");

        /*Get Json object values*/
        String Reference = null;
        try {
            Reference = TestParametersController.checkIfSpecialParameter(String.valueOf(recordsList.get("reference")));
        } catch (Exception e) {
            BPPLogManager.getLogger().error(Tools.getStackTrace(e));
            Reporter.fail("<br>" + Tools.getStackTrace(e) + "</br>");
            throw new RuntimeException("Can't proceed with response: " + Reference + " Please check -corespondent.json- file. Possible duplication or empty stings");
        }

        String Name = TestParametersController.checkIfSpecialParameter(String.valueOf(recordsList.get("name")));
        String Description = TestParametersController.checkIfSpecialParameter(String.valueOf(recordsList.get("description")));

        assertThat(Reference, matchesPattern("([a-z0-9-]){36}"));
        assertThat(ResponseString, containsString("createCourseType"));

        /*Set EC values for JSON object values*/
        ExecutionContextHandler.setExecutionContextValueByKey("EC_COURSE_TYPE_REFERENCE", Reference);
        ExecutionContextHandler.setExecutionContextValueByKey("EC_COURSE_TYPE_DESCRIPTION", Description);

        /*Report log with Json object values*/
        Reporter.log("<pre>" +
                "<br>Course Type: " +
                "<br>" + "Course Type Reference: " + "<font color='red'><b>" + Reference + "</font></b>" +
                "<br>" + "Course Type Description: " + "<font color='red'><b>" + Description + "</font></b>" +
                "</pre>");

        BPPLogManager.getLogger().info("Course Type was successfully created.");

        return this;
    }

}