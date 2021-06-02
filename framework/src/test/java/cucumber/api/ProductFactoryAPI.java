package cucumber.api;

import api.requests.ProductFactoryBusinessProcesses;
import api.requests.SalesforceBusinessProcesses;
import api.requests.SalesforceBusinessProcessesUAT;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import ui.utils.bpp.PropertiesHelper;

public class ProductFactoryAPI {

    @And("I create new Financial Dimension for Course")
    public void i_create_financial_dimension() {
            new ProductFactoryBusinessProcesses().
                    createNewFinancialDimension();
    }
}
