package ui.utils;

import api.RestApiController;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import netscape.javascript.JSObject;
import org.openqa.selenium.remote.RemoteWebDriver;
import ui.utils.bpp.PropertiesHelper;

import java.net.MalformedURLException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class qTestAPI {
    /**@TestRunStatusUpdate method used to update the Test Run status in qTest to Passed or Failed. It has a constructor that contains:
     * @param test - declares the current test name that is finished and should be updated in qTest.
     * @param Result - defines the Passed or Failed status to send through JSON POST request for specific test
     * @param Code - according to qTest API should send 601 - for Passed, 602 for Failed etc.
     * @param TestRunID - ID of current test that has been finished. This ID is defined by getTestRunIDfromSuite method**/

    public static synchronized void TestRunStatusUpdate(String test, String Result, int Code, String TestRunID, String ErrorInformation) throws MalformedURLException {

        String bearerToken;
        String ProjectID;
        String BrowserInformation;

        /**This block is created to update Execution History with Browser Name and Version
         * @param browserName - used to get Name of the Current Browser
         * @param browserVersion - used to get Version of the Current Browser**/

        String browserName = DriverProvider.getCurrentBrowserName();
        String browserVersion = ((RemoteWebDriver) DriverProvider.getDriver()).getCapabilities().getVersion();
        BrowserInformation = browserName.toUpperCase() + " " + browserVersion;

        /**This block is created to generate specific data in Date/Time/Time Zone format
         * @param formattedDate - generate current Date in (YYYY-MM-ddThh:mm:ssXXX) format
         * @param currentDateTime - generate current Date, Time and TimeZone format.
         * Example is currentDateTime = (2018-04-25T11:22:33+03:00)**/

        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("YYYY-MM-dd'T'hh:mm:ssXXX");
        String formattedDate = ZonedDateTime.now().format(dateFormat);
        String currentDateTime = (formattedDate);

        bearerToken = FileIO.getConfigProperty("QTest_Bearer_token");
        ProjectID = FileIO.getConfigProperty("QTest_Test_ProjectID");

        /**@param bearerToken - is Token of qTest user that should be used to create a GET/POST requests
         * Example - (Bearer #######-####-####-####-########) This Token is located inside Resources -> API & SDK in qTest
         * @param ProjectID - Declares inside configuration.properties file. This variable is located in the URL of the qTest Web Application.
         * @param BrowserInformation - Variable to get Browser Type and Version. Currently this information is sent to qTest execution logs to Operating System column**/

        String baseURI = "https://globalqatest.qtestnet.com/api/v3/projects/" + ProjectID + "/test-runs/" + TestRunID + "/test-logs";

        String requestBody = RestApiController.processProperties("qTestGetTestRunIDfromSuite");

        String replacedBody = requestBody
                .replace("currentDateTime", currentDateTime)
                .replace("BrowserInformation",BrowserInformation)
                .replace("Result", "Passed")
                .replace("Code", "601");

        Response response = given()
                .header("Authorization", bearerToken)
                .contentType(ContentType.JSON)
                .baseUri(baseURI)
                .when()
                .body(replacedBody)
                .post();

        if (response.getStatusCode() == 201) {
            BPPLogManager.getLogger().info("Response code: " + response.getStatusCode() + " - Test Run Status was successfully updated for " + test + " test");
        } else if (response.getStatusCode() == 400) {
            BPPLogManager.getLogger().error("Response code: " + response.getStatusCode() + " - Bad Request. Test Run Status wasn't updated for " + test + " test");
            response.prettyPrint();
        } else if (response.getStatusCode() == 500) {
            BPPLogManager.getLogger().error("Response code: " + response.getStatusCode() + " - Internal Server Error. Test Run Status wasn't updated for " + test + " test");
            response.prettyPrint();
        } else {
            BPPLogManager.getLogger().error("Response code: " + response.getStatusCode() + " Test Run Status wasn't updated for " + test + " test");
            response.prettyPrint();
        }
    }

    /**@getTestRunIDfromSuite is a GET method. It is used to get Test Run ID's for each test run inside the specific suite.
     * ProjectID and TestSuiteID are variables that are declared inside configuration.properties file and can be changed
     * according to project and TestSuite you are working with.
     * **/

    public static synchronized Map<String, String> getTestRunIDfromSuite() {

        String bearerToken;
        String ProjectID;
        String TestSuiteID;

        bearerToken = FileIO.getConfigProperty("QTest_Bearer_token");
        ProjectID = FileIO.getConfigProperty("QTest_Test_ProjectID");
        TestSuiteID = FileIO.getConfigProperty("QTest_Test_PF_SuiteID");

        String baseURI = "https://globalqatest.qtestnet.com/api/v3/projects/" + ProjectID + "/test-suites/" + TestSuiteID + "/test-runs";
        Response response = given()
                .header("Authorization", bearerToken)
                .baseUri(baseURI)
                .when()
                .get();

        JsonPath jsonPathEvaluator = response.jsonPath();
        List<String> id = jsonPathEvaluator.get("id");
        List<String> name = jsonPathEvaluator.get("name");
        Map<String,String> map = new LinkedHashMap<String, String>();

        for (int i=0; i<id.size(); i++) {
            map.put(name.get(i),String.valueOf(id.get(i)));
        }
        return map;
    }

}
