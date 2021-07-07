package api.requests;

import api.ProductFactoryAuthentication;
import api.RestApiController;
import api.Utilities;
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

public class ProductFactoryBusinessProcessesUpdateRequests {

    private final PropertiesHelper propertiesHelper = new PropertiesHelper();
    private final RestApiController restController;
    String Reference = null;

    public ProductFactoryBusinessProcessesUpdateRequests() {
        this.restController = new RestApiController();
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
}