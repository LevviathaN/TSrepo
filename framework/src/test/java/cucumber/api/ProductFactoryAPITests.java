package cucumber.api;

import api.requests.ProductFactoryBusinessProcesses;
import io.cucumber.java.en.When;

import java.io.IOException;

public class ProductFactoryAPITests {

    @When("I create Financial Dimensions record for Course Type item")
    public void i_create_financial_dimension_course_type() {
            new ProductFactoryBusinessProcesses()
                    .createNewFinancialDimension("COSTCENTRE","COURSETYPE")
                    .createNewFinancialDimension("PROJECT","COURSETYPE");
    }

    @When("I create Financial Dimensions record for Material Type item")
    public void i_create_financial_dimension_material_type() {
        new ProductFactoryBusinessProcesses()
                .createNewFinancialDimension("PRODUCT","BODY")
                .createNewFinancialDimension("PRODUCT","REGION")
                .createNewFinancialDimension("PRODUCT","LOCATION")
                .createNewFinancialDimension("PRODUCT","MATERIALTYPE");
    }

    @When("I create Financial Dimensions record for Location item")
    public void i_create_financial_dimension_location() {
        new ProductFactoryBusinessProcesses()
                .createNewFinancialDimension("PRODUCT","LOCATION");
    }

    @When("I create Financial Dimensions record for Body item")
    public void i_create_financial_dimension_body() {
        new ProductFactoryBusinessProcesses()
                .createNewFinancialDimension("PRODUCT","BODY");
    }

    @When("I create Financial Dimensions record for Region item")
    public void i_create_financial_dimension_region() {
        new ProductFactoryBusinessProcesses()
                .createNewFinancialDimension("PRODUCT","REGION");
    }



}

