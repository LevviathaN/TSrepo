package cucumber.api;

import api.requests.ProductFactoryBusinessProcesses;
import io.cucumber.java.en.When;

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
    public void i_generate_new_isbn_code() throws IOException {
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

    @When("I create new Vertical")
    public void i_create_vertical() {
        new ProductFactoryBusinessProcesses()
                .createNewVertical();
    }

    @When("I create new Body")
    public void i_create_body() {
        new ProductFactoryBusinessProcesses()
                .createNewVertical()
                .createNewFinancialDimension("PRODUCT","BODY")
                .createNewBody();
    }

    @When("I create new Sitting")
    public void i_create_sitting() {
        new ProductFactoryBusinessProcesses()
                .createNewVertical()
                .createNewFinancialDimension("PRODUCT","BODY")
                .createNewBody()
                .createNewSitting();
    }

    @When("I create new Paper")
    public void i_create_paper() {
        new ProductFactoryBusinessProcesses()
                .createNewPaper();
    }

    @When("I create new Level")
    public void i_create_level() {
        new ProductFactoryBusinessProcesses()
                .createNewLevel();
    }

    @When("I link Body to Levels")
    public void i_link_body_to_levels() {
        new ProductFactoryBusinessProcesses()
                .createNewVertical()
                .createNewFinancialDimension("PRODUCT","BODY")
                .createNewBody()
                .createNewLevel()
                .linkBodyToLevels();
    }

    @When("I change Paper Body")
    public void i_change_paper_body() {
        new ProductFactoryBusinessProcesses()
                .createNewVertical()
                .createNewFinancialDimension("PRODUCT","BODY")
                .createNewBody()
                .createNewPaper()
                .changePaperBody();
    }

    @When("I link Paper to Levels")
    public void i_link_paper_to_levels() {
        new ProductFactoryBusinessProcesses()
                .createNewLevel()
                .createNewPaper()
                .linkPaperToLevels();
    }

}
