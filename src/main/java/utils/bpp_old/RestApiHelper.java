//package utils.bpp_old;
//
//import io.restassured.response.Response;
//
//import javax.ws.rs.InternalServerErrorException;
//import javax.ws.rs.NotFoundException;
//import java.util.HashMap;
//
//import static io.restassured.RestAssured.given;
//
///**
// * This class contains controlling logic to handle HTTP methods for REST API interaction,
// * as well as mechanisms to compare value of an expected result to an actual one
// * in the response's attributes (Status Line, Headers, Body).
// */
//
//public class RestApiHelper {
//
//    private HashMap<String, String> configsMap = new HashMap<>();
//    private String contentType;
//    private String baseURI;
//
//    /**
//     * This method is used to get the response via REST API by means of HTTP GET method.
//     * @param requestConfigs accept the string with necessary request configs, from LocatorPropertiesUI.xlsx, which is decomposed and processed in this method.
//     *        Content type and the locator (tag, node, etc. It depends on the content type) should be passed to this parameter.
//     *        Other configs are optional and mainly depend on the request itself. Parameters are separated by commas in the LocatorPropertiesUI.
//     * @param requestProperties accept the string with the base URI and the expected result from Test.xlsx, which is decomposed and processed in this method.
//     *        Those two parameters should be separated by pipe sign (|).
//     */
//    public void validateGETRequest(String requestConfigs, String requestProperties) {
//
//        getMapOfParameters(requestConfigs);
//
//        // Set contentType field with a value passed to the method from the LocatorPropertiesUI.xlsx
//        contentType = configsMap.get("contentType");
//        //Removing this value from the map, since the map will be used to configure the HTTP request.
//        configsMap.remove("contentType");
//
//        String[] properties = requestProperties.split("\\|");
//        // Set baseURI field with a value passed to the method from the Test.xlsx
//        baseURI = properties[0];
//        // Set the value of the variable with a value passed to the method from the Test.xlsx
//        String expectedValue = properties[1];
//
//        Boolean searchStatus = false;
//
//        if (contentType.equals("html")) {
//
//            // Set the value of the variable with a value passed to the method from the LocatorPropertiesUI.xlsx
//            String tagName = configsMap.get("tag");
//            // Removing this value from the map, since the map will be used to configure the HTTP request.
//            configsMap.remove("tag");
//
//            Response response = getResponse();
//            searchStatus = validateElementByProperty(response, expectedValue, tagName);
//
//        } else if(contentType.equals("xml")) {
//
//            // Set the value of the variable with a value passed to the method from the LocatorPropertiesUI.xlsx
//            String nodeName = configsMap.get("node");
//            // Removing this value from the map, since the map will be used to configure the HTTP request.
//            configsMap.remove("node");
//
//            Response response = getResponse();
//            searchStatus = validateElementByProperty(response, expectedValue, nodeName);
//        }
//
//        if (!searchStatus) {
//            throw new AssertionError();
//        }
//    }
//
//    /**
//     * This method is used to validate an expected result to an actual one in the body of the response by given locator.
//     * @param response
//     * @param expectedValue
//     * @param property is the type of the locator.
//     * @return Boolean value if an expected result was found in a response body or not.
//     */
//    private Boolean validateElementByProperty(Response response, String expectedValue, String property) {
//
//        Boolean searchStatus = false;
//
//        // Perform actions if response status code belongs to "Success" type.
//        if (Integer.toString(response.getStatusCode()).matches("2.+")) {
//
//            NoahLogManager.getLogger().info("The response code: " + Integer.toString(response.getStatusCode()));
//
//            //Perform search in the response body by given locator if content type is HTML.
//            if (contentType.equals("html")) {
//                if (expectedValue.contains(response.htmlPath().getNode(property).toString()) ||
//                        response.htmlPath().getNode(property).toString().contains(expectedValue)) {
//
//                    searchStatus = true;
//                }
//
//                //Perform search in the response body by given locator if content type is XML.
//            } else if(contentType.equals("xml")) {
//                if (expectedValue.contains(response.xmlPath().getNode(property).toString()) ||
//                        response.xmlPath().getNode(property).toString().contains(expectedValue)) {
//
//                    searchStatus = true;
//                }
//            }
//
//            //Throw exception if response status code belongs to "Client errors" type.
//        } else if (Integer.toString(response.getStatusCode()).matches("4.+")) {
//            throw new NotFoundException(Integer.toString(response.getStatusCode()));
//            //Throw exception if response status code belongs to "Server errors" type.
//        } else if (Integer.toString(response.getStatusCode()).matches("5.+")) {
//            throw new InternalServerErrorException(Integer.toString(response.getStatusCode()));
//        }
//
//        return searchStatus;
//    }
//
//    /**
//     * This method is used to create a request configuration map.
//     * @param requestConfigs is a string containing parameters.
//     */
//    private synchronized void getMapOfParameters(String requestConfigs) {
//
//        for (String config : requestConfigs.split(",") ) {
//
//            String[] keyValuePair = config.split("=");
//            configsMap.put(keyValuePair[0], keyValuePair[1]);
//        }
//    }
//
//    /**
//     * This method is used to get the response only.
//     * @return response.
//     */
//
//    private Response getResponse() {
//
//        return given()
//                .baseUri(baseURI)
//                .params(configsMap)
//                .get();
//    }
//
//}
