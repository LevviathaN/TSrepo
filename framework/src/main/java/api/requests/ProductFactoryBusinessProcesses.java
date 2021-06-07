package api.requests;

import api.*;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import ui.utils.Reporter;
import ui.utils.bpp.ExecutionContextHandler;
import ui.utils.bpp.PropertiesHelper;
import ui.utils.bpp.TestParametersController;
import java.io.IOException;

import static com.jcabi.matchers.RegexMatchers.matchesPattern;
import static org.junit.Assert.assertThat;
import static org.junit.internal.matchers.StringContains.containsString;

/**
 *<p> Contains all methods required for main Salesforce API calls: e.g. Create Faculty, Student Account, Add Address.</p>
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
    }

    public ProductFactoryBusinessProcesses createNewFinancialDimension(String Parameter1, String Parameter2) {

        Response fdReferenceResponse = restController.postRequest(propertiesHelper.getProperties().getProperty("pf_request_link"),
                restController.processPropertiesPF("ProductFactory/addFinancialDimension",
                        TestParametersController.checkIfSpecialParameter(Parameter1),
                        TestParametersController.checkIfSpecialParameter(Parameter2)
                ),
                ProductFactoryAuthentication.getInstance().requestHeaderSpecification()
        );

        assertThat(fdReferenceResponse.getBody().asString(), containsString("createFinancialDimension"));

        JSONObject recordsObject = new Utilities().getResponseProperty(fdReferenceResponse);
        JSONObject recordsData = (JSONObject) recordsObject.get("data");
        JSONObject recordsList = (JSONObject) recordsData.get("createFinancialDimension");
        /*Get Json object values*/
        String Reference = TestParametersController.checkIfSpecialParameter(String.valueOf(recordsList.get("reference")));;
        String DimensionType = TestParametersController.checkIfSpecialParameter(String.valueOf(recordsList.get("dimensionType")));
        String Target = TestParametersController.checkIfSpecialParameter(String.valueOf(recordsList.get("target")));
        String Code = TestParametersController.checkIfSpecialParameter(String.valueOf(recordsList.get("code")));
        String Description = TestParametersController.checkIfSpecialParameter(String.valueOf(recordsList.get("description")));

        assertThat(Reference, matchesPattern("([a-z0-9-]){36}"));

        /*Set EC values for JSON object values*/
        ExecutionContextHandler.setExecutionContextValueByKey("EC_FD_" + Target.toUpperCase().replace(" ","_") + "_" + DimensionType.toUpperCase().replace(" ","_")  + "_REFERENCE", Reference);
        ExecutionContextHandler.setExecutionContextValueByKey("EC_FD_" + Target.toUpperCase().replace(" ","_") + "_" +  DimensionType.toUpperCase().replace(" ","_")  + "_DIMENSION_TYPE", DimensionType);
        ExecutionContextHandler.setExecutionContextValueByKey("EC_FD_" + Target.toUpperCase().replace(" ","_") + "_" +  DimensionType.toUpperCase().replace(" ","_")  + "_TARGET", Target);
        ExecutionContextHandler.setExecutionContextValueByKey("EC_FD_" + Target.toUpperCase().replace(" ","_") + "_" +  DimensionType.toUpperCase().replace(" ","_")  + "_CODE", Code);
        ExecutionContextHandler.setExecutionContextValueByKey("EC_FD_" + Target.toUpperCase().replace(" ","_") + "_" +  DimensionType.toUpperCase().replace(" ","_")  + "_DESCRIPTION", Description);
        /*Report log with Json object values*/
        Reporter.log("<pre>" +
                     "<br>Financial Dimension - " + Target + " - " + DimensionType + ": " +
                     "<br>" +   "Financial Dimension " + Target +  " " + DimensionType + " Reference: " + "<font color='red'><b>" + Reference + "</font></b>" +
                     "<br>" +  "Financial Dimension " + Target +  " " + DimensionType + " Code: " + "<font color='red'><b>" + Code + "</font></b>" +
                     "<br>" +  "Financial Dimension " + Target +  " " + DimensionType + " Dimension Type: " + "<font color='red'><b>" + DimensionType + "</font></b>" +
                     "<br>" +  "Financial Dimension " + Target +  " " + DimensionType + " Target: " + "<font color='red'><b>" + Target + "</font></b>" +
                     "<br>" +  "Financial Dimension " + Target +  " " + DimensionType + " Description: " + "<font color='red'><b>" + Description + "</font></b>" +
                     "</pre>");

        return this;
    }
}
