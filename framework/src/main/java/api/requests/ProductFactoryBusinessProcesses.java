package api.requests;

import api.*;
import io.restassured.response.Response;
import ui.utils.Reporter;
import ui.utils.bpp.ExecutionContextHandler;
import ui.utils.bpp.PropertiesHelper;

/**
 *<p> Contains all methods required for main Salesforce API calls: e.g. Create Faculty, Student Account, Add Address.</p>
 */

public class ProductFactoryBusinessProcesses {

    private PropertiesHelper propertiesHelper = new PropertiesHelper();
    private RestApiController restController;

    public ProductFactoryBusinessProcesses() {
        this.restController = new RestApiController();
    }

    public ProductFactoryBusinessProcesses createNewFinancialDimension() {

        Response fdReferenceResponse = restController.postRequest(propertiesHelper.getProperties().getProperty("pf_request_link"),
                restController.processProperties("ProductFactory/addNewFinancialDimension"),
                ProductFactoryAuthentication.getInstance().requestHeaderSpecification()
        );

        //assertThat(fdReferenceResponse.getBody().asString(), containsString("reference"));

        ExecutionContextHandler.setExecutionContextValueByKey("EC_FINANCIAL_DIMENSION_ONE", new Utilities().getResponseProperty(fdReferenceResponse, "reference"));
        Reporter.log("<pre>" + "Financial Dimension reference: " + fdReferenceResponse.getBody().asString() + "</pre>");

        return this;
    }
}
