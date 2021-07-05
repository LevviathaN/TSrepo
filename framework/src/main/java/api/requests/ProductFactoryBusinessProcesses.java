package api.requests;

import api.*;
import io.restassured.response.Response;
import org.json.simple.JSONArray;
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
import static org.testng.Assert.assertEquals;

/**
 * Contains all methods required for main Product Factory API calls to create End to End
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
     * Method for processing JSON file of request.
     * @param fileName - json file that is coresspondent for current request
     * @param objName - json object (operationName) of current json file
     * @param parameter1 - Parameter for JSON object String. If required. Look at FinancialDimension method
     * @param parameter2 - Parameter for JSON object String. If required. Look at FinancialDimension method
     **/

    public JSONObject requestProcess(String fileName,String objName,String parameter1, String parameter2) {
        Response Response = restController.postRequest(propertiesHelper.getProperties().getProperty("pf_request_link"),
                restController.processPropertiesPF("ProductFactory/" + fileName, parameter1, parameter2),
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
            throw new RuntimeException("Can't proceed with response: " + Reference + " Please check -corespondent.json- file. Possible duplication or empty strings");
        }

        assertThat(Reference, matchesPattern("([a-z0-9-]){36}"));
        assertThat(ResponseString, containsString(objName));

        return recordsList;
    }

    public ProductFactoryBusinessProcesses createNewFinancialDimension(String parameter1, String parameter2) {

        JSONObject recordsList = requestProcess("addFinancialDimension","createFinancialDimension", parameter1, parameter2);

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

        BPPLogManager.getLogger().info("Financial Dimension of " + parameter1 + "and " + parameter2 + " was successfully created.");

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

    public ProductFactoryBusinessProcesses createNewPaper(Boolean parameter1) {

        JSONObject recordsList = requestProcess("addPaper","createPaper", String.valueOf(parameter1), null);

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

    public ProductFactoryBusinessProcesses createNewRegion() {

        JSONObject recordsList = requestProcess("addRegion","createRegion", null, null);

        /*Get JSON object values*/
        String Name = TestParametersController.checkIfSpecialParameter(String.valueOf(recordsList.get("name")));

        /*Set EC values for JSON object values*/
        ExecutionContextHandler.setExecutionContextValueByKey("EC_REGION_REFERENCE", Reference);
        ExecutionContextHandler.setExecutionContextValueByKey("EC_REGION_NAME", Name);

        /*Report log with Json object values*/
        Reporter.log("<pre>" +
                "<br>Region: " +
                "<br>" + "Region Reference: " + "<font color='red'><b>" + Reference + "</font></b>" +
                "<br>" + "Region Name: " + "<font color='red'><b>" + Name + "</font></b>" +
                "</pre>");

        BPPLogManager.getLogger().info("Region was successfully created.");

        return this;
    }

    public ProductFactoryBusinessProcesses createNewLocation() {

        JSONObject recordsList = requestProcess("addLocation","createLocation", null, null);

        /*Get JSON object values*/
        String Name = TestParametersController.checkIfSpecialParameter(String.valueOf(recordsList.get("name")));
        String AddressLine1 = TestParametersController.checkIfSpecialParameter(String.valueOf(recordsList.get("addressLine1")));
        String AddressLine2 = TestParametersController.checkIfSpecialParameter(String.valueOf(recordsList.get("addressLine2")));
        String AddressLine3 = TestParametersController.checkIfSpecialParameter(String.valueOf(recordsList.get("addressLine3")));
        String PostCode = TestParametersController.checkIfSpecialParameter(String.valueOf(recordsList.get("postcode")));
        String City = TestParametersController.checkIfSpecialParameter(String.valueOf(recordsList.get("city")));
        String CountryCode = TestParametersController.checkIfSpecialParameter(String.valueOf(recordsList.get("countryCode")));

        /*Set EC values for JSON object values*/
        ExecutionContextHandler.setExecutionContextValueByKey("EC_LOCATION_REFERENCE", Reference);
        ExecutionContextHandler.setExecutionContextValueByKey("EC_LOCATION_NAME", Name);
        ExecutionContextHandler.setExecutionContextValueByKey("EC_LOCATION_ADDRESS_LINE_ONE", AddressLine1);
        ExecutionContextHandler.setExecutionContextValueByKey("EC_LOCATION_ADDRESS_LINE_TWO", AddressLine2);
        ExecutionContextHandler.setExecutionContextValueByKey("EC_LOCATION_ADDRESS_LINE_THREE", AddressLine3);
        ExecutionContextHandler.setExecutionContextValueByKey("EC_LOCATION_POST_CODE", PostCode);
        ExecutionContextHandler.setExecutionContextValueByKey("EC_LOCATION_CITY", City);
        ExecutionContextHandler.setExecutionContextValueByKey("EC_LOCATION_COUNTRY_CODE", CountryCode);

        /*Report log with Json object values*/
        Reporter.log("<pre>" +
                "<br>Location: " +
                "<br>" + "Location Reference: " + "<font color='red'><b>" + Reference + "</font></b>" +
                "<br>" + "Location Name: " + "<font color='red'><b>" + Name + "</font></b>" +
                "<br>" + "Location Address Line 1: " + "<font color='red'><b>" + AddressLine1 + "</font></b>" +
                "<br>" + "Location Address Line 2: " + "<font color='red'><b>" + AddressLine2 + "</font></b>" +
                "<br>" + "Location Address Line 3: " + "<font color='red'><b>" + AddressLine3 + "</font></b>" +
                "<br>" + "Location Post Code: " + "<font color='red'><b>" + PostCode + "</font></b>" +
                "<br>" + "Location Coty: " + "<font color='red'><b>" + City + "</font></b>" +
                "<br>" + "Location Country Code: " + "<font color='red'><b>" + CountryCode + "</font></b>" +
                "</pre>");

        BPPLogManager.getLogger().info("Location was successfully created.");

        return this;
    }

    public ProductFactoryBusinessProcesses createNewSessionDuration(Boolean parameter1) {

        JSONObject recordsList = requestProcess("addSessionDuration","createSessionDuration", String.valueOf(parameter1), null);

        /*Get JSON object values*/
        String Description = TestParametersController.checkIfSpecialParameter(String.valueOf(recordsList.get("description")));
        String DateCount = TestParametersController.checkIfSpecialParameter(String.valueOf(recordsList.get("dateCount")));
        String AllowedForCBA = TestParametersController.checkIfSpecialParameter(String.valueOf(recordsList.get("allowedForCba")));

        /*Set EC values for JSON object values*/
        ExecutionContextHandler.setExecutionContextValueByKey("EC_SESSION_DURATION_REFERENCE", Reference);
        ExecutionContextHandler.setExecutionContextValueByKey("EC_SESSION_DURATION_DESCRIPTION", Description);
        ExecutionContextHandler.setExecutionContextValueByKey("EC_SESSION_DURATION_DATE_COUNT", DateCount);
        ExecutionContextHandler.setExecutionContextValueByKey("EC_SESSION_DURATION_ALLOWED_FOR_CBA", AllowedForCBA);


        /*Report log with Json object values*/
        Reporter.log("<pre>" +
                "<br>Session Duration: " +
                "<br>" + "Session Duration Reference: " + "<font color='red'><b>" + Reference + "</font></b>" +
                "<br>" + "Session Duration Description: " + "<font color='red'><b>" + Description + "</font></b>" +
                "<br>" + "Session Duration Date Count: " + "<font color='red'><b>" + DateCount + "</font></b>" +
                "<br>" + "Session Duration Allowed For CBA: " + "<font color='red'><b>" + AllowedForCBA + "</font></b>" +
                "</pre>");

        BPPLogManager.getLogger().info("Session Duration was successfully created.");

        return this;
    }

    public ProductFactoryBusinessProcesses createNewPricingMatrix() {

        JSONObject recordsList = requestProcess("addPricingMatrix","createPricingMatrix", null, null);

        /*Set EC values for JSON object values*/
        ExecutionContextHandler.setExecutionContextValueByKey("EC_PRICING_MATRIX_REFERENCE", Reference);

        /*Report log with Json object values*/
        Reporter.log("<pre>" +
                "<br>Pricing Matrix: " +
                "<br>" + "Pricing Matrix Reference: " + "<font color='red'><b>" + Reference + "</font></b>" +
                "</pre>");

        BPPLogManager.getLogger().info("Pricing Matrix was successfully created.");

        return this;
    }

    public ProductFactoryBusinessProcesses createNewPrices() {

        JSONObject recordsList = requestProcess("addPrices","createPrices", null, null);

        /*Set EC values for JSON object values*/
        ExecutionContextHandler.setExecutionContextValueByKey("EC_PRICES_REFERENCE", Reference);

        /*Report log with Json object values*/
        Reporter.log("<pre>" +
                "<br>Prices: " +
                "<br>" + "Prices Reference: " + "<font color='red'><b>" + Reference + "</font></b>" +
                "</pre>");

        BPPLogManager.getLogger().info("Prices was successfully created for Pricing Matrix.");

        return this;
    }

    public ProductFactoryBusinessProcesses createNewDigitalContent(String parameter1, String parameter2) {

        JSONObject recordsList = requestProcess("addDigitalContent","createDigitalContent", parameter1, parameter2);

        /*Get JSON object values*/
        String Name = TestParametersController.checkIfSpecialParameter(String.valueOf(recordsList.get("name")));

        /*Set EC values for JSON object values*/
        ExecutionContextHandler.setExecutionContextValueByKey("EC_DIGITAL_CONTENT_REFERENCE", Reference);

        /*Report log with Json object values*/
        Reporter.log("<pre>" +
                "<br>Digital Content: " +
                "<br>" + "Digital Content Reference: " + "<font color='red'><b>" + Reference + "</font></b>" +
                "</pre>");

        BPPLogManager.getLogger().info("Digital Content was successfully created.");

        return this;
    }

    public ProductFactoryBusinessProcesses createNewUniversityProgrammes() {

        JSONObject recordsList = requestProcess("addUniversityProgrammes","createProgramme", null, null);

        /*Get JSON object values*/
        String Code = (String) recordsList.get("code");
        String Name = (String) recordsList.get("name");

        /*Set EC values for JSON object values*/
        ExecutionContextHandler.setExecutionContextValueByKey("EC_UNIVERSITY_PROGRAMME_REFERENCE", Reference);
        ExecutionContextHandler.setExecutionContextValueByKey("EC_UNIVERSITY_PROGRAMME_CODE", Code);
        ExecutionContextHandler.setExecutionContextValueByKey("EC_UNIVERSITY_PROGRAMME_NAME", Name);

        /*Report log with Json object values*/
        Reporter.log("<pre>" +
                "<br>University Programmes: " +
                "<br>" + "University Programmes Reference: " + "<font color='red'><b>" + Reference + "</font></b>" +
                "<br>" + "University Programmes Code: " + "<font color='red'><b>" + Code + "</font></b>" +
                "<br>" + "University Programmes Name: " + "<font color='red'><b>" + Name + "</font></b>" +
                "</pre>");

        BPPLogManager.getLogger().info("University Programme was successfully created.");

        return this;
    }

    public ProductFactoryBusinessProcesses createNewProgrammeCohorts() {

        JSONObject recordsList = requestProcess("addProgrammeCohort","createCohort", null, null);

        /*Get JSON object values*/
        String Name = (String) recordsList.get("name");
        String StartTeachingDate = (String) recordsList.get("startTeachingDate");

        /*Set EC values for JSON object values*/
        ExecutionContextHandler.setExecutionContextValueByKey("EC_PROGRAMME_COHORT_REFERENCE", Reference);
        ExecutionContextHandler.setExecutionContextValueByKey("EC_PROGRAMME_COHORT_NAME", Name);
        ExecutionContextHandler.setExecutionContextValueByKey("EC_PROGRAMME_COHORT_CODE", StartTeachingDate);

        /*Report log with Json object values*/
        Reporter.log("<pre>" +
                "<br>Programme Cohort: " +
                "<br>" + "Programme Cohort Reference: " + "<font color='red'><b>" + Reference + "</font></b>" +
                "<br>" + "Programme Cohort Name: " + "<font color='red'><b>" + Name + "</font></b>" +
                "<br>" + "Programme Cohort Code: " + "<font color='red'><b>" + StartTeachingDate + "</font></b>" +
                "</pre>");

        BPPLogManager.getLogger().info("Programme Cohort was successfully created.");

        return this;
    }

    public ProductFactoryBusinessProcesses createNewModuleSections(Boolean parameter1) {

        JSONObject recordsList = requestProcess("addModuleSections","createModule", String.valueOf(parameter1), null);

        /*Get JSON object values*/
        String Name = (String) recordsList.get("name");
        String TermCode = (String) recordsList.get("termCode");
        Boolean GroupRequirementCohort = (Boolean) recordsList.get("groupRequirementCohort");
        Boolean GroupRequirementMode = (Boolean) recordsList.get("groupRequirementMode");
        Boolean GroupRequirementLocation = (Boolean) recordsList.get("groupRequirementLocation");

        /*Set EC values for JSON object values*/
        ExecutionContextHandler.setExecutionContextValueByKey("EC_MODULE_SECTIONS_REFERENCE", Reference);
        ExecutionContextHandler.setExecutionContextValueByKey("EC_MODULE_SECTIONS_NAME", Name);
        ExecutionContextHandler.setExecutionContextValueByKey("EC_MODULE_SECTIONS_TERM_CODE", TermCode);
        ExecutionContextHandler.setExecutionContextValueByKey("EC_MODULE_SECTIONS_GROUP_REQUIREMENT_COHORT", String.valueOf(GroupRequirementCohort));
        ExecutionContextHandler.setExecutionContextValueByKey("EC_MODULE_SECTIONS_GROUP_REQUIREMENT_MODE", String.valueOf(GroupRequirementMode));
        ExecutionContextHandler.setExecutionContextValueByKey("EC_MODULE_SECTIONS_GROUP_REQUIREMENT_LOCATION", String.valueOf(GroupRequirementLocation));

        /*Report log with Json object values*/
        Reporter.log("<pre>" +
                "<br>Module Sections: " +
                "<br>" + "Module Sections Reference: " + "<font color='red'><b>" + Reference + "</font></b>" +
                "<br>" + "Module Sections Name: " + "<font color='red'><b>" + Name + "</font></b>" +
                "<br>" + "Module Sections Term Code: " + "<font color='red'><b>" + TermCode + "</font></b>" +
                "<br>" + "Module Sections Group Requirement Location: " + "<font color='red'><b>" + GroupRequirementCohort + "</font></b>" +
                "<br>" + "Module Sections Group Requirement Mode: " + "<font color='red'><b>" + GroupRequirementMode + "</font></b>" +
                "<br>" + "Module Sections Group Requirement Location: " + "<font color='red'><b>" + GroupRequirementLocation + "</font></b>" +
                "</pre>");

        BPPLogManager.getLogger().info("Module Section was successfully created.");

        return this;
    }

    public ProductFactoryBusinessProcesses createNewStockSite() {

        JSONObject recordsList = requestProcess("addStockSites","createStockSite", null, null);

        /*Get JSON object values*/
        String Name = (String ) recordsList.get("name");
        String AddressLine1 = (String ) recordsList.get("addressLine1");
        String AddressLine2 = (String ) recordsList.get("addressLine2");
        String AddressLine3 = (String ) recordsList.get("addressLine3");
        String PostCode = (String ) recordsList.get("postcode");
        String City = (String ) recordsList.get("city");
        String CountryCode = (String ) recordsList.get("countryCode");

        /*Set EC values for JSON object values*/
        ExecutionContextHandler.setExecutionContextValueByKey("EC_STOCK_SITE_REFERENCE", Reference);
        ExecutionContextHandler.setExecutionContextValueByKey("EC_STOCK_SITE_NAME", Name);
        ExecutionContextHandler.setExecutionContextValueByKey("EC_STOCK_SITE_ADDRESS_LINE_ONE", AddressLine1);
        ExecutionContextHandler.setExecutionContextValueByKey("EC_STOCK_SITE_ADDRESS_LINE_TWO", AddressLine2);
        ExecutionContextHandler.setExecutionContextValueByKey("EC_STOCK_SITE_ADDRESS_LINE_THREE", AddressLine3);
        ExecutionContextHandler.setExecutionContextValueByKey("EC_STOCK_SITE_POST_CODE", PostCode);
        ExecutionContextHandler.setExecutionContextValueByKey("EC_STOCK_SITE_CITY", City);
        ExecutionContextHandler.setExecutionContextValueByKey("EC_STOCK_SITE_COUNTRY_CODE", CountryCode);

        /*Report log with Json object values*/
        Reporter.log("<pre>" +
                "<br>Stock Site: " +
                "<br>" + "Stock Site Reference: " + "<font color='red'><b>" + Reference + "</font></b>" +
                "<br>" + "Stock Site Name: " + "<font color='red'><b>" + Name + "</font></b>" +
                "<br>" + "Stock Site Address Line 1: " + "<font color='red'><b>" + AddressLine1 + "</font></b>" +
                "<br>" + "Stock Site Address Line 2: " + "<font color='red'><b>" + AddressLine2 + "</font></b>" +
                "<br>" + "Stock Site Address Line 3: " + "<font color='red'><b>" + AddressLine3 + "</font></b>" +
                "<br>" + "Stock Site Post Code: " + "<font color='red'><b>" + PostCode + "</font></b>" +
                "<br>" + "Stock Site Coty: " + "<font color='red'><b>" + City + "</font></b>" +
                "<br>" + "Stock Site Country Code: " + "<font color='red'><b>" + CountryCode + "</font></b>" +
                "</pre>");

        BPPLogManager.getLogger().info("Location was successfully created.");

        return this;
    }

    public ProductFactoryBusinessProcesses createNewMaterialType(Boolean parameter1) {

        JSONObject recordsList = requestProcess("addMaterialType","createMaterialType", String.valueOf(parameter1), null);

        /*Get JSON object values*/
        String Name = (String) recordsList.get("name");
        String Description = (String) recordsList.get("description");
        Boolean IsExpiryDateRequired = (Boolean) recordsList.get("isExpiryDateRequired");
        Boolean IsIsbnRequired = (Boolean) recordsList.get("isIsbnRequired");
        Boolean IsPriceRequired = (Boolean) recordsList.get("isPriceRequired");
        Boolean IsWeightRequired = (Boolean) recordsList.get("isWeightRequired");

        /*Set EC values for JSON object values*/
        ExecutionContextHandler.setExecutionContextValueByKey("EC_MATERIAL_TYPE_REFERENCE", Reference);
        ExecutionContextHandler.setExecutionContextValueByKey("EC_MATERIAL_TYPE_NAME", Name);
        ExecutionContextHandler.setExecutionContextValueByKey("EC_MATERIAL_TYPE_DESCRIPTION", Description);
        ExecutionContextHandler.setExecutionContextValueByKey("EC_MATERIAL_TYPE_", String.valueOf(IsExpiryDateRequired));
        ExecutionContextHandler.setExecutionContextValueByKey("EC_MATERIAL_TYPE_", String.valueOf(IsIsbnRequired));
        ExecutionContextHandler.setExecutionContextValueByKey("EC_MATERIAL_TYPE_", String.valueOf(IsPriceRequired));
        ExecutionContextHandler.setExecutionContextValueByKey("EC_MATERIAL_TYPE_", String.valueOf(IsWeightRequired));

        /*Report log with Json object values*/
        Reporter.log("<pre>" +
                "<br>Material Type: " +
                "<br>" + "Material Type Reference: " + "<font color='red'><b>" + Reference + "</font></b>" +
                "<br>" + "Material Type Name: " + "<font color='red'><b>" + Name + "</font></b>" +
                "<br>" + "Material Type Description: " + "<font color='red'><b>" + Description + "</font></b>" +
                "<br>" + "Material Type Is Expiry Date Required: " + "<font color='red'><b>" + IsExpiryDateRequired + "</font></b>" +
                "<br>" + "Material Type Is Isbn Required: " + "<font color='red'><b>" + IsIsbnRequired + "</font></b>" +
                "<br>" + "Material Type Is Price Required: " + "<font color='red'><b>" + IsPriceRequired + "</font></b>" +
                "<br>" + "Material Type Is Weight Required: " + "<font color='red'><b>" + IsWeightRequired + "</font></b>" +
                "</pre>");

        BPPLogManager.getLogger().info("Material Type was successfully created.");

        return this;
    }

    public ProductFactoryBusinessProcesses createNewClients() {

        JSONObject recordsList = requestProcess("addClients","createClient", null, null);

        /*Get JSON object values*/
        String Name = TestParametersController.checkIfSpecialParameter(String.valueOf(recordsList.get("name")));

        /*Set EC values for JSON object values*/
        ExecutionContextHandler.setExecutionContextValueByKey("EC_CLIENTS_REFERENCE", Reference);
        ExecutionContextHandler.setExecutionContextValueByKey("EC_CLIENTS_NAME", Name);

        /*Report log with Json object values*/
        Reporter.log("<pre>" +
                "<br>Clients: " +
                "<br>" + "Clients Reference: " + "<font color='red'><b>" + Reference + "</font></b>" +
                "<br>" + "Clients Name: " + "<font color='red'><b>" + Name + "</font></b>" +
                "</pre>");

        BPPLogManager.getLogger().info("Client was successfully created.");

        return this;
    }

    public ProductFactoryBusinessProcesses createNewStreams() {

        JSONObject recordsList = requestProcess("addStreams","createStream", null, null);

        /*Get JSON object values*/
        String Name = TestParametersController.checkIfSpecialParameter(String.valueOf(recordsList.get("name")));

        /*Set EC values for JSON object values*/
        ExecutionContextHandler.setExecutionContextValueByKey("EC_STREAM_REFERENCE", Reference);
        ExecutionContextHandler.setExecutionContextValueByKey("EC_STREAM_NAME", Name);

        /*Report log with Json object values*/
        Reporter.log("<pre>" +
                "<br>Streams: " +
                "<br>" + "Streams Reference: " + "<font color='red'><b>" + Reference + "</font></b>" +
                "<br>" + "Streams Name: " + "<font color='red'><b>" + Name + "</font></b>" +
                "</pre>");

        BPPLogManager.getLogger().info("Stream was successfully created.");

        return this;
    }

    public ProductFactoryBusinessProcesses createNewDeactivationReason(Boolean parameter1) {

        JSONObject recordsList = requestProcess("addDeactivationReason","createDeactivationReason", String.valueOf(parameter1), null);

        /*Get JSON object values*/
        String Description = (String) recordsList.get("description");
        Boolean PreventReactivation = (Boolean) recordsList.get("preventReactivation");

        /*Set EC values for JSON object values*/
        ExecutionContextHandler.setExecutionContextValueByKey("EC_DEACTIVATION_REASON_REFERENCE", Reference);
        ExecutionContextHandler.setExecutionContextValueByKey("EC_DEACTIVATION_REASON_DESCRIPTION", Description);
        ExecutionContextHandler.setExecutionContextValueByKey("EC_DEACTIVATION_REASON_PREVENT_REACTIVATION", String.valueOf(PreventReactivation));

        /*Report log with Json object values*/
        Reporter.log("<pre>" +
                "<br>Deactivation Reason: " +
                "<br>" + "Deactivation Reason Reference: " + "<font color='red'><b>" + Reference + "</font></b>" +
                "<br>" + "Deactivation Reason Description: " + "<font color='red'><b>" + Description + "</font></b>" +
                "<br>" + "Deactivation Reason Prevent Reactivation: " + "<font color='red'><b>" + PreventReactivation + "</font></b>" +
                "</pre>");

        BPPLogManager.getLogger().info("Deactivation Reason was successfully created.");

        return this;
    }

    public ProductFactoryBusinessProcesses createNewCourse() {

        JSONObject recordsList = requestProcess("addCourse","createCourse", null, null);

        /*Set EC values for JSON object values*/
        ExecutionContextHandler.setExecutionContextValueByKey("EC_COURSE_REFERENCE", Reference);

        /*Report log with Json object values*/
        Reporter.log("<pre>" +
                "<br>Course: " +
                "<br>" + "Course Reference: " + "<font color='red'><b>" + Reference + "</font></b>" +
                "</pre>");

        BPPLogManager.getLogger().info("Course was successfully created.");

        return this;
    }

    public ProductFactoryBusinessProcesses createNewInstance() {

        JSONObject recordsList = requestProcess("addInstance","createInstance", null, null);

        /*Get JSON object values*/
        JSONArray sessionsArray = (JSONArray) recordsList.get("sessions");
        JSONObject sessionsObj = (JSONObject) sessionsArray.get(0);

        String SessionReference = (String) sessionsObj.get("reference");
        String Status = (String) recordsList.get("status");
        Integer Capacity = (Integer) recordsList.get("capacity");

        /*Set EC values for JSON object values*/
        ExecutionContextHandler.setExecutionContextValueByKey("EC_INSTANCE_REFERENCE", Reference);
        ExecutionContextHandler.setExecutionContextValueByKey("EC_SESSION_REFERENCE", SessionReference);

        /*Report log with Json object values*/
        Reporter.log("<pre>" +
                "<br>Instance: " +
                "<br>" + "Instance Reference: " + "<font color='red'><b>" + Reference + "</font></b>" +
                "<br>" + "Instance Status: " + "<font color='red'><b>" + Status + "</font></b>" +
                "<br>" + "Instance Capacity: " + "<font color='red'><b>" + Capacity + "</font></b>" +
                "<br>" + "Session Reference: " + "<font color='red'><b>" + SessionReference + "</font></b>" +
                "</pre>");

        BPPLogManager.getLogger().info("Instance was successfully created.");

        return this;
    }

    public ProductFactoryBusinessProcesses changeInstanceCapacity(Integer parameter1) {

        Response Response = restController.postRequest(propertiesHelper.getProperties().getProperty("pf_request_link"),
                restController.processPropertiesPF("ProductFactory/changeInstanceCapacity", String.valueOf(parameter1), null),
                ProductFactoryAuthentication.getInstance().requestHeaderSpecification()
        );

        String ResponseString = Response.getBody().asString();

        JSONObject recordsObject = new Utilities().getResponseProperty(Response);
        JSONObject recordsData = (JSONObject) recordsObject.get("data");
        JSONObject recordsList = (JSONObject) recordsData.get("updateInstance");

        /*Get Json object values*/
        try {
            Reference = (String) recordsList.get("reference");
        } catch (Exception e) {
            BPPLogManager.getLogger().error(Tools.getStackTrace(e));
            Reporter.fail("<br>" + Tools.getStackTrace(e) + "</br>");
            throw new RuntimeException("Can't proceed with response: " + Reference + " Please check -corespondent.json- file. Possible duplication or empty strings");
        }

        assertThat(Reference, matchesPattern("([a-z0-9-]){36}"));
        assertThat(ResponseString, containsString("updateInstance"));

        /*Get JSON object values*/
        Long Capacity = (Long) recordsList.get("capacity");

        /*Set EC values for JSON object values*/
        ExecutionContextHandler.setExecutionContextValueByKey("EC_INSTANCE_CAPACITY", String.valueOf(Capacity));

        /*Report log with Json object values*/
        Reporter.log("<pre>" +
                "<br>Instance: " +
                "<br>" + "Instance Reference: " + "<font color='red'><b>" + Reference + "</font></b>" +
                "<br>" + "Instance Capacity: " + "<font color='red'><b>" + Capacity + "</font></b>" +
                "</pre>");

        BPPLogManager.getLogger().info("Instance Capacity was successfully changed.");

        return this;
    }

    public ProductFactoryBusinessProcesses getInstanceSessions() {

        Response Response = restController.postRequest(propertiesHelper.getProperties().getProperty("pf_request_link"),
                restController.processPropertiesPF("ProductFactory/getInstanceSessions", null, null),
                ProductFactoryAuthentication.getInstance().requestHeaderSpecification()
        );

        String ResponseString = Response.getBody().asString();

        /*Get JSON object values*/
        JSONObject recordsObject = new Utilities().getResponseProperty(Response);
        JSONObject recordsData = (JSONObject) recordsObject.get("data");
        JSONArray recordsArray = (JSONArray) recordsData.get("sessions");
        JSONObject recordsList = (JSONObject) recordsArray.get(0);
        JSONArray recordsArrayList = (JSONArray) recordsList.get("timings");
        JSONObject item = (JSONObject) recordsArrayList.get(0);
        String timingReference = (String) item.get("reference");

        /*Get Json object values*/
        try {
            Reference = timingReference;
        } catch (Exception e) {
            BPPLogManager.getLogger().error(Tools.getStackTrace(e));
            Reporter.fail("<br>" + Tools.getStackTrace(e) + "</br>");
            throw new RuntimeException("Can't proceed with response: " + Reference + " Please check -corespondent.json- file. Possible duplication or empty strings");
        }

        assertThat(Reference, matchesPattern("([a-z0-9-]){36}"));
        assertThat(ResponseString, containsString("session"));

        /*Set EC values for JSON object values*/
        ExecutionContextHandler.setExecutionContextValueByKey("EC_SESSION_TIMING_REFERENCE", Reference);

        /*Report log with Json object values*/
        Reporter.log("<pre>" +
                "<br>Session Timing: " +
                "<br>" + "Session Timing Reference: " + "<font color='red'><b>" + Reference + "</font></b>" +
                "</pre>");

        BPPLogManager.getLogger().info("Session Timing Reference timing was successfully received.");

        return this;
    }

    public ProductFactoryBusinessProcesses changeSessionTimings() {

        JSONObject recordsList = requestProcess("changeSessionTimings","changeSessionTimings", null, null);

        /*Get JSON object values*/
        JSONArray recordsArrayList = (JSONArray) recordsList.get("timings");
        JSONObject item = (JSONObject) recordsArrayList.get(0);
        String sessionDate = (String) item.get("sessionDate");
        String startTime = (String) item.get("startTime");
        String endTime = (String) item.get("endTime");

        /*Set EC values for JSON object values*/
        ExecutionContextHandler.setExecutionContextValueByKey("EC_SESSION_REFERENCE", Reference);
        ExecutionContextHandler.setExecutionContextValueByKey("EC_SESSION_DATE", sessionDate);
        ExecutionContextHandler.setExecutionContextValueByKey("EC_SESSION_START_TIME", String.valueOf(startTime));
        ExecutionContextHandler.setExecutionContextValueByKey("EC_SESSION_END_TIME", String.valueOf(endTime));

        /*Report log with Json object values*/
        Reporter.log("<pre>" +
                "<br>Session: " +
                "<br>" + "Session : " + "<font color='red'><b> Changed successfully</font></b>" +
                "<br>" + "Session Reference: " + "<font color='red'><b>" + Reference + "</font></b>" +
                "<br>" + "Session Date: " + "<font color='red'><b>" + sessionDate + "</font></b>" +
                "<br>" + "Session Start Time: " + "<font color='red'><b>" + startTime + "</font></b>" +
                "<br>" + "Session End Time: " + "<font color='red'><b>" + endTime + "</font></b>" +
                "</pre>");

        BPPLogManager.getLogger().info("Session Timing was successfully changed.");

        return this;
    }

    public ProductFactoryBusinessProcesses getInstanceSteps() {

        Response Response = restController.postRequest(propertiesHelper.getProperties().getProperty("pf_request_link"),
                restController.processPropertiesPF("ProductFactory/getInstanceSteps", null, null),
                ProductFactoryAuthentication.getInstance().requestHeaderSpecification()
        );

        String ResponseString = Response.getBody().asString();

        /*Get JSON object values*/
        JSONObject recordsObject = new Utilities().getResponseProperty(Response);
        JSONObject recordsData = (JSONObject) recordsObject.get("data");
        JSONArray recordsArray = (JSONArray) recordsData.get("steps");
        JSONObject recordsList = (JSONObject) recordsArray.get(0);

        String dueDate = (String) recordsList.get("dueDate");
        Long stepNumber = (Long) recordsList.get("stepNumber");

        /*Get Json object values*/
        try {
            Reference = String.valueOf(recordsList.get("reference"));;
        } catch (Exception e) {
            BPPLogManager.getLogger().error(Tools.getStackTrace(e));
            Reporter.fail("<br>" + Tools.getStackTrace(e) + "</br>");
            throw new RuntimeException("Can't proceed with response: " + Reference + " Please check -corespondent.json- file. Possible duplication or empty strings");
        }

        assertThat(Reference, matchesPattern("([a-z0-9-]){36}"));
        assertThat(ResponseString, containsString("steps"));

        /*Set EC values for JSON object values*/
        ExecutionContextHandler.setExecutionContextValueByKey("EC_STEP_REFERENCE", Reference);
        ExecutionContextHandler.setExecutionContextValueByKey("EC_STEP_DUE_DATE", dueDate);
        ExecutionContextHandler.setExecutionContextValueByKey("EC_STEP_STEPNUMBER", String.valueOf(stepNumber));

        /*Report log with Json object values*/
        Reporter.log("<pre>" +
                "<br>Steps: " +
                "<br>" + "Step Reference: " + "<font color='red'><b>" + Reference + "</font></b>" +
                "<br>" + "Step Due Date: " + "<font color='red'><b>" + dueDate + "</font></b>" +
                "<br>" + "Step Number: " + "<font color='red'><b>" + stepNumber + "</font></b>" +
                "</pre>");

        BPPLogManager.getLogger().info("Step Reference was successfully received.");

        return this;
    }

    public ProductFactoryBusinessProcesses changeInstanceSteps() {

        JSONObject recordsList = requestProcess("changeStepDueDate","changeStepDueDate", null, null);

        /*Get JSON object values*/
        String dueDate = (String) recordsList.get("dueDate");
        Long stepNumber = (Long) recordsList.get("stepNumber");

        /*Set EC values for JSON object values*/
        ExecutionContextHandler.setExecutionContextValueByKey("EC_STEP_DUE_DATE", dueDate);
        ExecutionContextHandler.setExecutionContextValueByKey("EC_STEP_STEPNUMBER", String.valueOf(stepNumber));

        /*Report log with Json object values*/
        Reporter.log("<pre>" +
                "<br>Changed Steps: " +
                "<br>" + "Step Reference: " + "<font color='red'><b>" + Reference + "</font></b>" +
                "<br>" + "Step Due Date: " + "<font color='red'><b>" + dueDate + "</font></b>" +
                "<br>" + "Step Number: " + "<font color='red'><b>" + stepNumber + "</font></b>" +
                "</pre>");

        BPPLogManager.getLogger().info("Steps was successfully changed.");

        return this;
    }

    public ProductFactoryBusinessProcesses calculateCoursePrice() {

        JSONObject recordsList = requestProcess("calculateCoursePrice","calculateCoursePrice", null, null);

        /*Get JSON object values*/
        Long price = (Long) recordsList.get("price");


        /*Set EC values for JSON object values*/
        ExecutionContextHandler.setExecutionContextValueByKey("EC_STEP_DUE_DATE", String.valueOf(price));

        /*Report log with Json object values*/
        Reporter.log("<pre>" +
                "<br>Price: " +
                "<br>" + "Course Price: " + "<font color='red'><b>" + price + "</font></b>" +
                "</pre>");

        BPPLogManager.getLogger().info("Course Price was successfully calculated.");

        return this;
    }

    public ProductFactoryBusinessProcesses activateCourse() {

        JSONObject recordsList = requestProcess("activateCourse","activateCourse", null, null);

        /*Get JSON object values*/
        String status = (String) recordsList.get("status");

        /*Set EC values for JSON object values*/
        ExecutionContextHandler.setExecutionContextValueByKey("EC_COURSE_ACTIVATION_STATUS", status);

        /*Report log with Json object values*/
        Reporter.log("<pre>" +
                "<br>Course Activation: " +
                "<br>" + "Course Reference: " + "<font color='red'><b>" + Reference + "</font></b>" +
                "<br>" + "Course Activation Status: " + "<font color='red'><b>" + status + "</font></b>" +
                "</pre>");

        BPPLogManager.getLogger().info("Course was successfully activated.");

        return this;
    }

    public ProductFactoryBusinessProcesses activateInstance() {

        JSONObject recordsList = requestProcess("activateInstance","activateInstance", null, null);

        /*Get JSON object values*/
        String status = (String) recordsList.get("status");

        /*Set EC values for JSON object values*/
        ExecutionContextHandler.setExecutionContextValueByKey("EC_COURSE_ACTIVATION_STATUS", status);

        /*Report log with Json object values*/
        Reporter.log("<pre>" +
                "<br>Instance Activation: " +
                "<br>" + "Instance Reference: " + "<font color='red'><b>" + Reference + "</font></b>" +
                "<br>" + "Instance Activation Status: " + "<font color='red'><b>" + status + "</font></b>" +
                "</pre>");

        BPPLogManager.getLogger().info("Instance was successfully activated.");

        return this;
    }

    public ProductFactoryBusinessProcesses createCourseBulkOperation() {

        JSONObject recordsList = requestProcess("createCourseBulkOperation","createCourseBulkOperation", null, null);

        /*Set EC values for JSON object values*/
        ExecutionContextHandler.setExecutionContextValueByKey("EC_COURSE_BULK_OPERATION_REFERENCE", Reference);

        /*Report log with Json object values*/
        Reporter.log("<pre>" +
                "<br>Course Bulk Operation: " +
                "<br>" + "Course Bulk Operation Reference: " + "<font color='red'><b>" + Reference + "</font></b>" +
                "</pre>");

        BPPLogManager.getLogger().info("Course Bulk Operation was successfully created.");

        return this;
    }

    public ProductFactoryBusinessProcesses bulkWebPublish(Boolean parameter1) {

        Response Response = restController.postRequest(propertiesHelper.getProperties().getProperty("pf_request_link"),
                restController.processPropertiesPF("ProductFactory/bulkWebPublish", String.valueOf(parameter1), null),
                ProductFactoryAuthentication.getInstance().requestHeaderSpecification()
        );
        String ResponseString = Response.getBody().asString();

        JSONObject recordsObject = new Utilities().getResponseProperty(Response);
        JSONObject recordsData = (JSONObject) recordsObject.get("data");
        JSONObject recordsList = (JSONObject) recordsData.get("courseBulkOperationWebPublish");

        /*Get Json object values*/
        JSONArray coursesArray = (JSONArray) recordsList.get("courses");
        JSONObject coursesFirstArray = (JSONObject) coursesArray.get(0);
        Boolean courseIncluded = (Boolean) coursesFirstArray.get("included");
        JSONObject coursesCourseObject = (JSONObject) coursesFirstArray.get("course");
        Boolean courseAvailableOnWEB = (Boolean) coursesCourseObject.get("availableOnWebsite");
        String courseStatus = (String) coursesCourseObject.get("status");
        JSONArray coursesInstancesArray = (JSONArray) coursesFirstArray.get("instances");
        JSONObject instancesFirstArray = (JSONObject) coursesInstancesArray.get(0);
        Boolean instanceIncluded = (Boolean) coursesFirstArray.get("included");
        JSONObject instancesObject = (JSONObject) instancesFirstArray.get("instance");
        Boolean instanceAvailableOnWEB = (Boolean) instancesObject.get("availableOnWebsite");
        String instanceStatus = (String) instancesObject.get("status");

        try {
            Reference = (String) recordsList.get("reference");
        } catch (Exception e) {
            BPPLogManager.getLogger().error(Tools.getStackTrace(e));
            Reporter.fail("<br>" + Tools.getStackTrace(e) + "</br>");
            throw new RuntimeException("Can't proceed with response: " + Reference + " Please check -corespondent.json- file. Possible duplication or empty strings");
        }

        assertThat(Reference, matchesPattern("([a-z0-9-]){36}"));
        assertThat(ResponseString, containsString("courseBulkOperationWebPublish"));

        /*Set EC values for JSON object values*/
        ExecutionContextHandler.setExecutionContextValueByKey("EC_COURSE_INCLUDED", String.valueOf(courseIncluded));
        ExecutionContextHandler.setExecutionContextValueByKey("EC_COURSE_AWAILABLE_ON_WEB", String.valueOf(courseAvailableOnWEB));
        ExecutionContextHandler.setExecutionContextValueByKey("EC_COURSE_STATUS",courseStatus );
        ExecutionContextHandler.setExecutionContextValueByKey("EC_INSTANCE_INCLUDED", String.valueOf(instanceIncluded));
        ExecutionContextHandler.setExecutionContextValueByKey("EC_INSTANCE_AWAILABLE_ON_WEB", String.valueOf(instanceAvailableOnWEB));
        ExecutionContextHandler.setExecutionContextValueByKey("EC_INSTANCE_STATUS",instanceStatus);

        /*Report log with Json object values*/
        Reporter.log("<pre>" +
                "<br>Course Web Publish: " +
                "<br>" + "Course Bulk Operation Reference: " + "<font color='red'><b>" + Reference + "</font></b>" +
                "<br>" + "Course Included: " + "<font color='red'><b>" + courseIncluded + "</font></b>" +
                "<br>" + "Course Available on Web: " + "<font color='red'><b>" + courseAvailableOnWEB + "</font></b>" +
                "<br>" + "Course Status: " + "<font color='red'><b>" + courseStatus + "</font></b>" +
                "<br>" + "Instance Included: " + "<font color='red'><b>" + instanceIncluded + "</font></b>" +
                "<br>" + "Instance Available on Web: " + "<font color='red'><b>" + instanceAvailableOnWEB + "</font></b>" +
                "<br>" + "Instance Status: " + "<font color='red'><b>" + instanceStatus + "</font></b>" +
                "</pre>");

        BPPLogManager.getLogger().info("Bulk Web Publish was successfully executed.");

        return this;
    }

}