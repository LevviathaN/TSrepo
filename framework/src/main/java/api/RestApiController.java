package api;

import com.sun.tools.xjc.api.Property;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import ui.utils.BPPLogManager;
import ui.utils.Tools;
import ui.utils.bpp.TestParametersController;

import java.lang.reflect.Array;
import java.util.ArrayList;
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

    public String processPropertiesPF(String requestTemplate, String parameter1, String parameter2) {

        JSONObject jo = new Utilities().getJsonObject(requestTemplate);

        /*Get command list*/
        Map variables = ((Map) jo.get("variables"));
//        Map command = ((Map) variables.get("command"));
        Map.Entry<String,String> entry = (Map.Entry<String, String>) variables.entrySet().iterator().next();
        String key = entry.getKey();

        if(key.equals("command")) {

            Map command = (Map) variables.get(key);

            /*Get and Put Special Parameter*/
            if (!(command.get("dimensionType") == null)) {
                command.put("dimensionType", parameter1);
            }
            if (!(command.get("target") == null)) {
                command.put("target", parameter2);
            }
            if (!(command.get("contentType") == null)) {
                command.put("contentType", parameter1);
            }
            if (!(command.get("targetPlatform") == null)) {
                command.put("targetPlatform", parameter2);
            }
            if (!(command.get("capacity") == null)) {
                command.put("capacity", Integer.valueOf(parameter1));
            }
            if (!(command.get("isCba") == null)) {
                boolean boolStr = Boolean.parseBoolean(parameter1);
                command.put("isCba", boolStr);
            }
            if (!(command.get("allowedForCba") == null)) {
                boolean boolStr = Boolean.parseBoolean(parameter1);
                command.put("allowedForCba", boolStr);
            }
            if (!(command.get("isExpiryDateRequired") == null)) {
                boolean boolStr = Boolean.parseBoolean(parameter1);
                command.put("isExpiryDateRequired", boolStr);
            }
            if (!(command.get("preventReactivation") == null)) {
                boolean boolStr = Boolean.parseBoolean(parameter1);
                command.put("preventReactivation", boolStr);
            }
            if (!(command.get("isIsbnRequired") == null)) {
                boolean boolStr = Boolean.parseBoolean(parameter1);
                command.put("isIsbnRequired", boolStr);
            }
            if (!(command.get("isPriceRequired") == null)) {
                boolean boolStr = Boolean.parseBoolean(parameter1);
                command.put("isPriceRequired", boolStr);
            }
            if (!(command.get("isWeightRequired") == null)) {
                boolean boolStr = Boolean.parseBoolean(parameter1);
                command.put("isWeightRequired", boolStr);
            }
            if (!(command.get("groupRequirementCohort") == null)) {
                boolean boolStr = Boolean.parseBoolean(parameter1);
                command.put("groupRequirementCohort", boolStr);
            }
            if (!(command.get("groupRequirementMode") == null)) {
                boolean boolStr = Boolean.parseBoolean(parameter1);
                command.put("groupRequirementMode", boolStr);
            }
            if (!(command.get("groupRequirementLocation") == null)) {
                boolean boolStr = Boolean.parseBoolean(parameter1);
                command.put("groupRequirementLocation", boolStr);
            }
            if (!(command.get("code") == null)) {
                command.put("code", TestParametersController.checkIfSpecialParameter(String.valueOf(command.get("code"))));
            }
            if (!(command.get("shortName") == null)) {
                command.put("shortName", TestParametersController.checkIfSpecialParameter(String.valueOf(command.get("shortName"))));
            }
            if (!(command.get("name") == null)) {
                command.put("name", TestParametersController.checkIfSpecialParameter(String.valueOf(command.get("name"))));
            }
            if (!(command.get("description") == null)) {
                command.put("description", TestParametersController.checkIfSpecialParameter(String.valueOf(command.get("description"))));
            }
            if (!(command.get("startDate") == null)) {
                command.put("startDate", TestParametersController.checkIfSpecialParameter(String.valueOf(command.get("startDate"))));
            }
            if (!(command.get("endDate") == null)) {
                command.put("endDate", TestParametersController.checkIfSpecialParameter(String.valueOf(command.get("endDate"))));
            }
            if (!(command.get("startTeachingDate") == null)) {
                command.put("startTeachingDate", TestParametersController.checkIfSpecialParameter(String.valueOf(command.get("startTeachingDate"))));
            }
            if (!(command.get("termCode") == null)) {
                command.put("termCode", TestParametersController.checkIfSpecialParameter(String.valueOf(command.get("termCode"))));
            }
            if (!(command.get("timings") == null)) {
                ArrayList<String> bodyList = new ArrayList<String>();
                JSONArray bodyArray = (JSONArray) command.get("timings");
                JSONObject timingObj = (JSONObject) bodyArray.get(0);
                String sessionTimingReference = (String) timingObj.get("sessionTimingReference");
                String sessionDate = (String) timingObj.get("sessionDate");
                String startTime = (String) timingObj.get("startTime");
                String endTime = (String) timingObj.get("endTime");
                ((JSONObject) bodyArray.get(0)).put("sessionTimingReference", TestParametersController.checkIfSpecialParameter(sessionTimingReference));
                ((JSONObject) bodyArray.get(0)).put("sessionDate", TestParametersController.checkIfSpecialParameter(sessionDate));
                ((JSONObject) bodyArray.get(0)).put("startTime", TestParametersController.checkIfSpecialParameter(startTime));
                ((JSONObject) bodyArray.get(0)).put("endTime", TestParametersController.checkIfSpecialParameter(endTime));
                bodyList.add(String.valueOf(bodyArray));
            }
            if (!(command.get("bodyReferences") == null)) {
                JSONArray bodyArray = (JSONArray) command.get("bodyReferences");
                ArrayList<String> bodyList = new ArrayList<String>();
                bodyList.add(TestParametersController.checkIfSpecialParameter(String.valueOf(bodyArray.get(0))));
                command.put("bodyReferences", bodyList);
            }
            if (!(command.get("courseReferences") == null)) {
                JSONArray bodyArray = (JSONArray) command.get("courseReferences");
                ArrayList<String> bodyList = new ArrayList<String>();
                bodyList.add(TestParametersController.checkIfSpecialParameter(String.valueOf(bodyArray.get(0))));
                command.put("courseReferences", bodyList);
            }
            if (!(command.get("levelReferences") == null)) {
                JSONArray levelArray = (JSONArray) command.get("levelReferences");
                ArrayList<String> levelList = new ArrayList<String>();
                levelList.add(TestParametersController.checkIfSpecialParameter(String.valueOf(levelArray.get(0))));
                command.put("levelReferences", levelList);
            }
            if (!(command.get("paperReferences") == null)) {
                JSONArray paperArray = (JSONArray) command.get("paperReferences");
                ArrayList<String> levelList = new ArrayList<String>();
                levelList.add(TestParametersController.checkIfSpecialParameter(String.valueOf(paperArray.get(0))));
                command.put("paperReferences", levelList);
            }
            if (!(command.get("regionReferences") == null)) {
                JSONArray regionArray = (JSONArray) command.get("regionReferences");
                ArrayList<String> levelList = new ArrayList<String>();
                levelList.add(TestParametersController.checkIfSpecialParameter(String.valueOf(regionArray.get(0))));
                command.put("regionReferences", levelList);
            }
            if (!(command.get("sittingReferences") == null)) {
                JSONArray regionArray = (JSONArray) command.get("sittingReferences");
                ArrayList<String> levelList = new ArrayList<String>();
                levelList.add(TestParametersController.checkIfSpecialParameter(String.valueOf(regionArray.get(0))));
                command.put("sittingReferences", levelList);
            }
            if (!(command.get("courseTypeReferences") == null)) {
                JSONArray regionArray = (JSONArray) command.get("courseTypeReferences");
                ArrayList<String> levelList = new ArrayList<String>();
                levelList.add(TestParametersController.checkIfSpecialParameter(String.valueOf(regionArray.get(0))));
                command.put("courseTypeReferences", levelList);
            }
            if (!(command.get("bodyReference") == null)) {
                command.put("bodyReference", TestParametersController.checkIfSpecialParameter(String.valueOf(command.get("bodyReference"))));
            }
            if (!(command.get("operationReference") == null)) {
                command.put("operationReference", TestParametersController.checkIfSpecialParameter(String.valueOf(command.get("operationReference"))));
            }
            if (!(command.get("reference") == null)) {
                command.put("reference", TestParametersController.checkIfSpecialParameter(String.valueOf(command.get("reference"))));
            }
            if (!(command.get("streamReference") == null)) {
                command.put("streamReference", TestParametersController.checkIfSpecialParameter(String.valueOf(command.get("streamReference"))));
            }
            if (!(command.get("courseReference") == null)) {
                command.put("courseReference", TestParametersController.checkIfSpecialParameter(String.valueOf(command.get("courseReference"))));
            }
            if (!(command.get("defaultLocationReference") == null)) {
                command.put("defaultLocationReference", TestParametersController.checkIfSpecialParameter(String.valueOf(command.get("defaultLocationReference"))));
            }
            if (!(command.get("defaultSessionDurationReference") == null)) {
                command.put("defaultSessionDurationReference", TestParametersController.checkIfSpecialParameter(String.valueOf(command.get("defaultSessionDurationReference"))));
            }
            if (!(command.get("paperReference") == null)) {
                command.put("paperReference", TestParametersController.checkIfSpecialParameter(String.valueOf(command.get("paperReference"))));
            }
            if (!(command.get("sittingReference") == null)) {
                command.put("sittingReference", TestParametersController.checkIfSpecialParameter(String.valueOf(command.get("sittingReference"))));
            }
            if (!(command.get("vatRuleReference") == null)) {
                command.put("vatRuleReference", TestParametersController.checkIfSpecialParameter(String.valueOf(command.get("vatRuleReference"))));
            }
            if (!(command.get("verticalReference") == null)) {
                command.put("verticalReference", TestParametersController.checkIfSpecialParameter(String.valueOf(command.get("verticalReference"))));
            }
            if (!(command.get("regionReference") == null)) {
                command.put("regionReference", TestParametersController.checkIfSpecialParameter(String.valueOf(command.get("regionReference"))));
            }
            if (!(command.get("courseTypeReference") == null)) {
                command.put("courseTypeReference", TestParametersController.checkIfSpecialParameter(String.valueOf(command.get("courseTypeReference"))));
            }
            if (!(command.get("pricingMatrixReference") == null)) {
                command.put("pricingMatrixReference", TestParametersController.checkIfSpecialParameter(String.valueOf(command.get("pricingMatrixReference"))));
            }
            if (!(command.get("levelReference") == null)) {
                command.put("levelReference", TestParametersController.checkIfSpecialParameter(String.valueOf(command.get("levelReference"))));
            }
            if (!(command.get("programmeReference") == null)) {
                command.put("programmeReference", TestParametersController.checkIfSpecialParameter(String.valueOf(command.get("programmeReference"))));
            }
            if (!(command.get("cohortReference") == null)) {
                command.put("cohortReference", TestParametersController.checkIfSpecialParameter(String.valueOf(command.get("cohortReference"))));
            }
            if (!(command.get("sessionReference") == null)) {
                command.put("sessionReference", TestParametersController.checkIfSpecialParameter(String.valueOf(command.get("sessionReference"))));
            }
            if (!(command.get("stepReference") == null)) {
                command.put("stepReference", TestParametersController.checkIfSpecialParameter(String.valueOf(command.get("stepReference"))));
            }
            if (!(command.get("dueDate") == null)) {
                command.put("dueDate", TestParametersController.checkIfSpecialParameter(String.valueOf(command.get("dueDate"))));
            }
            if (!(command.get("costCentreFinancialDimensionReference") == null)) {
                command.put("costCentreFinancialDimensionReference", TestParametersController.checkIfSpecialParameter(String.valueOf(command.get("costCentreFinancialDimensionReference"))));
            }
            if (!(command.get("projectFinancialDimensionReference") == null)) {
                command.put("projectFinancialDimensionReference", TestParametersController.checkIfSpecialParameter(String.valueOf(command.get("projectFinancialDimensionReference"))));
            }
            if (!(command.get("financialDimensionReference") == null)) {
                command.put("financialDimensionReference", TestParametersController.checkIfSpecialParameter(String.valueOf(command.get("financialDimensionReference"))));
            }
            if (!(command.get("examPreparationReference") == null)) {
                command.put("examPreparationReference", TestParametersController.checkIfSpecialParameter(String.valueOf(command.get("examPreparationReference"))));
            }
            if (!(command.get("studyModeReference") == null)) {
                command.put("studyModeReference", TestParametersController.checkIfSpecialParameter(String.valueOf(command.get("studyModeReference"))));
            }
        } else {
            if (!(variables.get("instanceReference") == null)) {
                variables.put("instanceReference", TestParametersController.checkIfSpecialParameter(String.valueOf(variables.get("instanceReference"))));
            }
        }

        return jo.toString();
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
