package api;

import com.google.api.client.json.JsonString;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import ui.utils.BPPLogManager;
import ui.utils.Tools;
import ui.utils.bpp.TestParametersController;

import java.util.Map;
import java.util.Random;

import static io.restassured.RestAssured.given;

public class RestApiController {

    /**
     * @param baseURI     - will be changed based of record type to be created
     * @param requestBody - JSON format; depends on record type
     */

    public Response postRequest(String baseURI, String requestBody, RequestSpecification auth) {
        Response response = given()
                .spec(auth)
                .when()
                .contentType(ContentType.JSON)
                .baseUri(baseURI)
                .body(requestBody)
                .post();

        if (Integer.toString(response.getStatusCode()).matches("2.+")) {
            BPPLogManager.getLogger().info("Request sent successfully! Response code: " + response.getStatusCode());
        } else {
            BPPLogManager.getLogger().error("Response code: " + response.getStatusCode());
        }
        return response;
    }

    /**
     * @param baseURI - will be changed based of record type to be created
     */
    public Response getRequest(String baseURI, RequestSpecification auth) {
        Response response = given()
                .spec(auth)
                .when()
                .contentType(ContentType.JSON)
                .get(baseURI)
                .andReturn();

        if (Integer.toString(response.getStatusCode()).matches("2.+")) {
            BPPLogManager.getLogger().info("Request sent successfully! Response code: " + response.getStatusCode());
        } else {
           BPPLogManager.getLogger().error("Response code: " + response.getStatusCode());
        }
        return response;
    }

    /**
     * @param requestTemplate - the name of Json file which will be used to retrieve Json file as an Object
     */
    public String processProperties(String requestTemplate) {

        JSONObject map = new Utilities().getJsonObject(requestTemplate);

        map.keySet().forEach(keyStr -> {
            Object keyValue = map.get(keyStr);

            if (keyValue instanceof JSONArray) {
                JSONArray valueArray = (JSONArray) keyValue;
                keyValue = valueArray.get(new Random().nextInt(valueArray.size())).toString();
            }

            map.put(keyStr, TestParametersController.checkIfSpecialParameter(keyValue.toString()));

        });

        return map.toString();
    }

    public String processPropertiesPF(String requestTemplate, String Parameter1, String Parameter2) {

        JSONObject jo = new Utilities().getJsonObject(requestTemplate);

        /*Get command list*/
        Map variables = ((Map) jo.get("variables"));
        Map command = ((Map) variables.get("command"));


        /*Get and Put Special Parameter*/

        if (!(command.get("dimensionType") == null)) {
            command.put("dimensionType", Parameter1);
        }
        if (!(command.get("target") == null)) {
            command.put("target", Parameter2);
        }
        if (!(command.get("code") == null)) {
            command.put("code", TestParametersController.checkIfSpecialParameter(String.valueOf(command.get("code"))));
        }
        if (!(command.get("name") == null)) {
            command.put("name", TestParametersController.checkIfSpecialParameter(String.valueOf(command.get("name"))));
        }
        if (!(command.get("description") == null)) {
            command.put("description", TestParametersController.checkIfSpecialParameter(String.valueOf(command.get("description"))));
        }
        if (!(command.get("vatRuleReference") == null)) {
            command.put("vatRuleReference", TestParametersController.checkIfSpecialParameter(String.valueOf(command.get("vatRuleReference"))));
        }
        if (!(command.get("costCentreFinancialDimensionReference") == null)) {
            command.put("costCentreFinancialDimensionReference", TestParametersController.checkIfSpecialParameter(String.valueOf(command.get("costCentreFinancialDimensionReference"))));
        }
        if (!(command.get("projectFinancialDimensionReference") == null)) {
            command.put("projectFinancialDimensionReference", TestParametersController.checkIfSpecialParameter(String.valueOf(command.get("projectFinancialDimensionReference"))));
        }
        if (!(command.get("examPreparationReference") == null)) {
            command.put("examPreparationReference", TestParametersController.checkIfSpecialParameter(String.valueOf(command.get("examPreparationReference"))));
        }
        if (!(command.get("studyModeReference") == null)) {
            command.put("studyModeReference", TestParametersController.checkIfSpecialParameter(String.valueOf(command.get("studyModeReference"))));
        }

        return jo.toJSONString();
    }

    public Map processLocatorProperties(String locatorsFile) {

        JSONObject map = new Tools().getJsonObjectForLocators(locatorsFile);

        map.keySet().forEach(keyStr -> {
            Object keyValue = map.get(keyStr);

            if (keyValue instanceof JSONArray) {
                JSONArray valueArray = (JSONArray) keyValue;
                keyValue = valueArray.get(new Random().nextInt(valueArray.size())).toString();
            }

            map.put(keyStr, keyValue.toString());

        });

        return map;
    }
}
