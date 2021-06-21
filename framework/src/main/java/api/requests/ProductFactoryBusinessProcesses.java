package api.requests;

import api.*;
import com.sun.deploy.uitoolkit.impl.fx.ui.FXAppContext;
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
import static org.junit.Assert.assertTrue;
import static org.junit.internal.matchers.StringContains.containsString;
import static org.testng.Assert.assertEquals;

/**
 *<p> Contains all methods required for main Product Factory API calls to create End to End</p>
 */

public class ProductFactoryBusinessProcesses {

    private final PropertiesHelper propertiesHelper = new PropertiesHelper();
    private final RestApiController restController;
    String Reference = null;

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

    /**
     * **/
    public JSONObject requestProcess(String fileName,String objName,String Parameter1, String Parameter2) {
        Response Response = restController.postRequest(propertiesHelper.getProperties().getProperty("pf_request_link"),
                restController.processPropertiesPF("ProductFactory/" + fileName, Parameter1, Parameter2),
                ProductFactoryAuthentication.getInstance().requestHeaderSpecification()
        );
        String ResponseString = Response.getBody().asString();

        JSONObject recordsObject = new Utilities().getResponseProperty(Response);
        JSONObject recordsData = (JSONObject) recordsObject.get("data");
        JSONObject recordsList = (JSONObject) recordsData.get(objName);

        /*Get Json object values*/
        try {
            Reference = (String) recordsList.get("reference");
        } catch (Exception e) {
            BPPLogManager.getLogger().error(Tools.getStackTrace(e));
            Reporter.fail("<br>" + Tools.getStackTrace(e) + "</br>");
            throw new RuntimeException("Can't proceed with response: " + Reference + " Please check -corespondent.json- file. Possible duplication or empty stings");
        }

        assertThat(Reference, matchesPattern("([a-z0-9-]){36}"));
        assertThat(ResponseString, containsString(objName));

        return recordsList;
    }

    public ProductFactoryBusinessProcesses createNewFinancialDimension(String Parameter1, String Parameter2) {

        JSONObject recordsList = requestProcess("addFinancialDimension","createFinancialDimension", Parameter1, Parameter2);

        /*Get JSON object values*/
        String DimensionType = (String) recordsList.get("dimensionType");
        String Target = (String) recordsList.get("target");
        String Code = (String) recordsList.get("code");
        String Description = (String) recordsList.get("description");

        /*Set EC values for JSON object values*/
        ExecutionContextHandler.setExecutionContextValueByKey("EC_FD_" + Target.toUpperCase().replace(" ", "_") + "_" + DimensionType.toUpperCase().replace(" ", "_") + "_REFERENCE", Reference);
        ExecutionContextHandler.setExecutionContextValueByKey("EC_FD_" + Target.toUpperCase().replace(" ", "_") + "_" + DimensionType.toUpperCase().replace(" ", "_") + "_DIMENSION_TYPE", DimensionType);
        ExecutionContextHandler.setExecutionContextValueByKey("EC_FD_" + Target.toUpperCase().replace(" ", "_") + "_" + DimensionType.toUpperCase().replace(" ", "_") + "_TARGET", Target);
        ExecutionContextHandler.setExecutionContextValueByKey("EC_FD_" + Target.toUpperCase().replace(" ", "_") + "_" + DimensionType.toUpperCase().replace(" ", "_") + "_CODE", Code);
        ExecutionContextHandler.setExecutionContextValueByKey("EC_FD_" + Target.toUpperCase().replace(" ", "_") + "_" + DimensionType.toUpperCase().replace(" ", "_") + "_DESCRIPTION", Description);

        /*Report log with JSON object values*/
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

        JSONObject recordsList = requestProcess("addVatRule","createVatRule", null, null);

        /*Get JSON object values*/
        String Code = (String) recordsList.get("code");
        String Description = (String) recordsList.get("description");
        Long Rate = (Long) recordsList.get("rate");

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

        JSONObject recordsList = requestProcess("addExamPreparation","createExamPreparation", null, null);

        /*Get JSON object values*/
        String Name = (String) recordsList.get("name");
        String Description = (String) recordsList.get("description");

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

        JSONObject recordsList = requestProcess("addStudyMode","createStudyMode", null, null);

        /*Get JSON object values*/
        String Name = TestParametersController.checkIfSpecialParameter(String.valueOf(recordsList.get("name")));
        String Description = TestParametersController.checkIfSpecialParameter(String.valueOf(recordsList.get("description")));

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

        JSONObject recordsList = requestProcess("addCourseType","createCourseType", null, null);

        /*Get JSON object values*/
        String Description = TestParametersController.checkIfSpecialParameter(String.valueOf(recordsList.get("description")));

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

    public ProductFactoryBusinessProcesses createNewVertical() {

        JSONObject recordsList = requestProcess("addVertical","createVertical", null, null);

        /*Get JSON object values*/
        String Name = TestParametersController.checkIfSpecialParameter(String.valueOf(recordsList.get("name")));

        /*Set EC values for JSON object values*/
        ExecutionContextHandler.setExecutionContextValueByKey("EC_VERTICAL_REFERENCE", Reference);
        ExecutionContextHandler.setExecutionContextValueByKey("EC_VERTICAL_NAME", Name);

        /*Report log with Json object values*/
        Reporter.log("<pre>" +
                "<br>Vertical: " +
                "<br>" + "Vertical Reference: " + "<font color='red'><b>" + Reference + "</font></b>" +
                "<br>" + "Vertical Description: " + "<font color='red'><b>" + Name + "</font></b>" +
                "</pre>");

        BPPLogManager.getLogger().info("Vertical was successfully created.");

        return this;
    }

    public ProductFactoryBusinessProcesses createNewBody() {

        JSONObject recordsList = requestProcess("addBody","createBody", null, null);

        /*Get JSON object values*/
        String ShortName = TestParametersController.checkIfSpecialParameter(String.valueOf(recordsList.get("shortName")));
        String Name = TestParametersController.checkIfSpecialParameter(String.valueOf(recordsList.get("name")));
        String Description = TestParametersController.checkIfSpecialParameter(String.valueOf(recordsList.get("description")));

        /*Set EC values for JSON object values*/
        ExecutionContextHandler.setExecutionContextValueByKey("EC_BODY_REFERENCE", Reference);
        ExecutionContextHandler.setExecutionContextValueByKey("EC_BODY_SHORTNAME", ShortName);
        ExecutionContextHandler.setExecutionContextValueByKey("EC_BODY_NAME", Name);
        ExecutionContextHandler.setExecutionContextValueByKey("EC_BODY_DESCRIPTION", Description);

        /*Report log with Json object values*/
        Reporter.log("<pre>" +
                "<br>Body: " +
                "<br>" + "Body Reference: " + "<font color='red'><b>" + Reference + "</font></b>" +
                "<br>" + "Body Short Name: " + "<font color='red'><b>" + ShortName + "</font></b>" +
                "<br>" + "Body Name: " + "<font color='red'><b>" + Name + "</font></b>" +
                "<br>" + "Body Description: " + "<font color='red'><b>" + Description + "</font></b>" +
                "</pre>");

        BPPLogManager.getLogger().info("Body was successfully created.");

        return this;
    }

    public ProductFactoryBusinessProcesses createNewSitting() {

        JSONObject recordsList = requestProcess("addSitting","createSitting", null, null);

        /*Get JSON object values*/
        String Name = TestParametersController.checkIfSpecialParameter(String.valueOf(recordsList.get("name")));
        String StartDate = TestParametersController.checkIfSpecialParameter(String.valueOf(recordsList.get("startDate")));
        String EndDate = TestParametersController.checkIfSpecialParameter(String.valueOf(recordsList.get("endDate")));

        /*Set EC values for JSON object values*/
        ExecutionContextHandler.setExecutionContextValueByKey("EC_SITTING_REFERENCE", Reference);
        ExecutionContextHandler.setExecutionContextValueByKey("EC_SITTING_NAME", Name);
        ExecutionContextHandler.setExecutionContextValueByKey("EC_SITTING_STARTDATE", StartDate);
        ExecutionContextHandler.setExecutionContextValueByKey("EC_SITTING_ENDDATE", EndDate);

        /*Report log with Json object values*/
        Reporter.log("<pre>" +
                "<br>Sitting: " +
                "<br>" + "Sitting Reference: " + "<font color='red'><b>" + Reference + "</font></b>" +
                "<br>" + "Sitting Name: " + "<font color='red'><b>" + Name + "</font></b>" +
                "<br>" + "Sitting Start Date: " + "<font color='red'><b>" + StartDate + "</font></b>" +
                "<br>" + "Sitting End Date: " + "<font color='red'><b>" + EndDate + "</font></b>" +
                "</pre>");

        BPPLogManager.getLogger().info("Sitting was successfully created.");

        return this;
    }

    public ProductFactoryBusinessProcesses createNewPaper() {

        JSONObject recordsList = requestProcess("addPaper","createPaper", null, null);

        /*Get JSON object values*/
        String ShortName = TestParametersController.checkIfSpecialParameter(String.valueOf(recordsList.get("shortName")));
        String Name = TestParametersController.checkIfSpecialParameter(String.valueOf(recordsList.get("name")));
        String Description = TestParametersController.checkIfSpecialParameter(String.valueOf(recordsList.get("description")));
        String IsCBA = TestParametersController.checkIfSpecialParameter(String.valueOf(recordsList.get("isCba")));
        String TimeInHours = TestParametersController.checkIfSpecialParameter(String.valueOf(recordsList.get("timeInHours")));
        String ExamSchedule = TestParametersController.checkIfSpecialParameter(String.valueOf(recordsList.get("examSchedule")));

        /*Set EC values for JSON object values*/
        ExecutionContextHandler.setExecutionContextValueByKey("EC_PAPER_REFERENCE", Reference);
        ExecutionContextHandler.setExecutionContextValueByKey("EC_PAPER_SHORTNAME", ShortName);
        ExecutionContextHandler.setExecutionContextValueByKey("EC_PAPER_NAME", Name);
        ExecutionContextHandler.setExecutionContextValueByKey("EC_PAPER_DESCRIPTION", Description);
        ExecutionContextHandler.setExecutionContextValueByKey("EC_PAPER_ISCBA", IsCBA);
        ExecutionContextHandler.setExecutionContextValueByKey("EC_PAPER_TIMEINHOURS", TimeInHours);
        ExecutionContextHandler.setExecutionContextValueByKey("EC_PAPER_EXAMSCHEDULE", ExamSchedule);

        /*Report log with Json object values*/
        Reporter.log("<pre>" +
                "<br>Paper: " +
                "<br>" + "Paper Reference: " + "<font color='red'><b>" + Reference + "</font></b>" +
                "<br>" + "Paper Short Name: " + "<font color='red'><b>" + ShortName + "</font></b>" +
                "<br>" + "Paper Name: " + "<font color='red'><b>" + Name + "</font></b>" +
                "<br>" + "Paper Description: " + "<font color='red'><b>" + Description + "</font></b>" +
                "<br>" + "Paper Is CBA: " + "<font color='red'><b>" + IsCBA + "</font></b>" +
                "<br>" + "Paper Time in Hours: " + "<font color='red'><b>" + TimeInHours + "</font></b>" +
                "<br>" + "Paper Exam Schedule: " + "<font color='red'><b>" + ExamSchedule + "</font></b>" +
                "</pre>");

        BPPLogManager.getLogger().info("Paper was successfully created.");

        return this;
    }

    public ProductFactoryBusinessProcesses createNewLevel() {

        JSONObject recordsList = requestProcess("addLevel","createLevel", null, null);

        /*Get JSON object values*/
        String ShortName = TestParametersController.checkIfSpecialParameter(String.valueOf(recordsList.get("shortName")));
        String Name = TestParametersController.checkIfSpecialParameter(String.valueOf(recordsList.get("name")));
        String Description = TestParametersController.checkIfSpecialParameter(String.valueOf(recordsList.get("description")));

        /*Set EC values for JSON object values*/
        ExecutionContextHandler.setExecutionContextValueByKey("EC_LEVEL_REFERENCE", Reference);
        ExecutionContextHandler.setExecutionContextValueByKey("EC_LEVEL_SHORTNAME", ShortName);
        ExecutionContextHandler.setExecutionContextValueByKey("EC_LEVEL_NAME", Name);
        ExecutionContextHandler.setExecutionContextValueByKey("EC_LEVEL_DESCRIPTION", Description);

        /*Report log with Json object values*/
        Reporter.log("<pre>" +
                "<br>Level: " +
                "<br>" + "Level Reference: " + "<font color='red'><b>" + Reference + "</font></b>" +
                "<br>" + "Level Short Name: " + "<font color='red'><b>" + ShortName + "</font></b>" +
                "<br>" + "Level Name: " + "<font color='red'><b>" + Name + "</font></b>" +
                "<br>" + "Level Description: " + "<font color='red'><b>" + Description + "</font></b>" +
                "</pre>");

        BPPLogManager.getLogger().info("Level was successfully created.");

        return this;
    }

    public ProductFactoryBusinessProcesses linkBodyToLevels() {

        requestProcess("linkBodyToLevels","linkBodyToLevels", null, null);

        assertEquals(Reference,TestParametersController.checkIfSpecialParameter("EC_BODY_REFERENCE"));

        /*Report log with Json object values*/
        Reporter.log("<pre>" + "Response Reference equal to Body Reference" + "</pre>");
        BPPLogManager.getLogger().info("Body was successfully linked to Level.");

        return this;
    }

    public ProductFactoryBusinessProcesses changePaperBody() {

        requestProcess("changePaperBody","changePaperBody", null, null);

        assertEquals(Reference,TestParametersController.checkIfSpecialParameter("EC_PAPER_REFERENCE"));

        /*Report log with Json object values*/
        Reporter.log("<pre>" + "Response Reference equal to Body Reference" + "</pre>");
        BPPLogManager.getLogger().info("Paper's Body was successfully changed.");

        return this;
    }

    public ProductFactoryBusinessProcesses linkPaperToLevels() {

        requestProcess("linkPaperToLevels","linkPaperToLevels", null, null);

        assertEquals(Reference,TestParametersController.checkIfSpecialParameter("EC_PAPER_REFERENCE"));

        /*Report log with Json object values*/
        Reporter.log("<pre>" + "Response Reference equal to Paper Reference" + "</pre>");
        BPPLogManager.getLogger().info("Paper was successfully linked to Level.");

        return this;
    }
}