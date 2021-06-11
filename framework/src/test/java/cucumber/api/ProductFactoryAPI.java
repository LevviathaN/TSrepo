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

    @When("I create new Financial Dimension")
    public void i_create_financial_dimension() {
            new ProductFactoryBusinessProcesses()
                    .createNewFinancialDimension("COSTCENTRE","COURSETYPE")
                    .createNewFinancialDimension("PROJECT","COURSETYPE")
                    .createNewFinancialDimension("PRODUCT","BODY")
                    .createNewFinancialDimension("PRODUCT","REGION")
                    .createNewFinancialDimension("PRODUCT","LOCATION");
    }

    @When("I generate new ISBN code")
    public void iGenerateNewISBNCode() throws IOException {
        new ProductFactoryBusinessProcesses()
                .createNewISBNcode();
    }

    @When("I create new Vat Rule")
    public void i_create_vat_rule() {
        new ProductFactoryBusinessProcesses()
                .createNewVatRule();
    }

    @When("I create new Exam Preparation")
    public void i_create_exam_preparation() {
        new ProductFactoryBusinessProcesses()
                .createNewExamPreparation();
    }

    @When("I create new Study Mode")
    public void i_create_study_mode() {
        new ProductFactoryBusinessProcesses()
                .createNewStudyMode();
    }

    @When("I create new Course Type")
    public void i_create_course_type() {
        new ProductFactoryBusinessProcesses()
                .createNewFinancialDimension("COSTCENTRE","COURSETYPE")
                .createNewFinancialDimension("PROJECT","COURSETYPE")
                .createNewVatRule()
                .createNewExamPreparation()
                .createNewStudyMode()
                .createNewCourseType();
    }

}
