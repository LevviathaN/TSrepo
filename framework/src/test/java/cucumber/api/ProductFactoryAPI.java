package cucumber.api;

import api.requests.ProductFactoryBusinessProcesses;
import api.requests.SalesforceBusinessProcesses;
import api.requests.SalesforceBusinessProcessesUAT;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import ui.utils.Reporter;
import ui.utils.bpp.ExecutionContextHandler;
import ui.utils.bpp.PropertiesHelper;
import ui.utils.bpp.TestParametersController;

import java.io.IOException;

public class ProductFactoryAPI {

    @And("I create new Financial Dimension")
    public void i_create_financial_dimension_for_course() {
            new ProductFactoryBusinessProcesses()
                    .createNewFinancialDimension("COSTCENTRE","COURSETYPE")
                    .createNewFinancialDimension("PROJECT","COURSETYPE")
                    .createNewFinancialDimension("PRODUCT","BODY")
                    .createNewFinancialDimension("PRODUCT","REGION")
                    .createNewFinancialDimension("PRODUCT","LOCATION")
            ;
    }

    @When("I generate new ISBN code")
    public void iGenerateNewISBNCode() throws IOException {
        new ProductFactoryBusinessProcesses().
                createNewISBNcode();
    }
}
