package api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
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

    public Response postRequest(String baseURI, String requestBody) {
        Response response = given()
                .spec(SalesforceAuthentication.getInstance().requestHeaderSpecification())
                .when()
                .contentType(ContentType.JSON)
                .baseUri(baseURI)
                .body(requestBody)
                .post();

        if (Integer.toString(response.getStatusCode()).matches("2.+")) {
            //LogManager.getLogger().info("Request sent successfully! Response code: " + response.getStatusCode());
        } else {
            //NoahLogManager.getLogger().error("Response code: " + response.getStatusCode());
        }
        return response;
    }

    /**
     * @param baseURI - will be changed based of record type to be created
     */
    public Response getRequest(String baseURI) {
        Response response = given()
                .spec(SalesforceAuthentication.getInstance().requestHeaderSpecification())
                .when()
                .contentType(ContentType.JSON)
                .get(baseURI)
                .andReturn();

        if (Integer.toString(response.getStatusCode()).matches("2.+")) {
            //NoahLogManager.getLogger().info("Request sent successfully! Response code: " + response.getStatusCode());
        } else {
            //NoahLogManager.getLogger().error("Response code: " + response.getStatusCode());
        }
        return response;
    }

    /**
     * @param requestTemplate - the name of Json file which will be used to retrieve Json file as an Object
     */
    public static String processProperties(String requestTemplate) {

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

    public static Map processLocatorProperties(String locatorsFile) {

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

    public static Response postQTestRequest(String baseURI, String requestBody, String bearerToken) {
        Response response = given()
                .header("Authorization", bearerToken)
                .contentType(ContentType.JSON)
                .baseUri(baseURI)
                .when()
                .body(requestBody)
                .post();
        if (Integer.toString(response.getStatusCode()).matches("2.+")) {
            //LogManager.getLogger().info("Request sent successfully! Response code: " + response.getStatusCode());
        } else {
            //NoahLogManager.getLogger().error("Response code: " + response.getStatusCode());
        }
        return response;
    }
}
