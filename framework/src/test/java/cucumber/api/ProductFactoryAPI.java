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
                    .createNewFinancialDimension("PRODUCT","LOCATION")
                    .createNewFinancialDimension("PRODUCT","MATERIALTYPE");
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
                .createNewPaper(false);
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
                .createNewPaper(false)
                .changePaperBody();
    }

    @When("I link Paper to Levels")
    public void i_link_paper_to_levels() {
        new ProductFactoryBusinessProcesses()
                .createNewLevel()
                .createNewPaper(false)
                .linkPaperToLevels();
    }

    @When("I create new Region")
    public void i_create_region() {
        new ProductFactoryBusinessProcesses()
                .createNewFinancialDimension("PRODUCT","REGION")
                .createNewRegion();
    }

    @When("I create new Location")
    public void i_create_location() {
        new ProductFactoryBusinessProcesses()
                .createNewFinancialDimension("PRODUCT","REGION")
                .createNewFinancialDimension("PRODUCT","LOCATION")
                .createNewRegion()
                .createNewLocation();
    }

    @When("I create new Session Duration")
    public void i_create_session_duration() {
        new ProductFactoryBusinessProcesses()
                .createNewSessionDuration(false);
    }

    @When("I create new Pricing Matrix")
    public void i_create_pricing_matrix() {
        new ProductFactoryBusinessProcesses()
                .createNewVertical()
                .createNewFinancialDimension("PRODUCT","BODY")
                .createNewBody()
                .createNewSitting()
                .createNewPricingMatrix();
    }

    @When("I create new Prices")
    public void i_create_prices() {
        new ProductFactoryBusinessProcesses()
                .createNewFinancialDimension("COSTCENTRE","COURSETYPE")
                .createNewFinancialDimension("PROJECT","COURSETYPE")
                .createNewFinancialDimension("PRODUCT","BODY")
                .createNewFinancialDimension("PRODUCT","REGION")
                .createNewFinancialDimension("PRODUCT","LOCATION")
                .createNewVatRule()
                .createNewExamPreparation()
                .createNewStudyMode()
                .createNewCourseType()
                .createNewVertical()
                .createNewBody()
                .createNewSitting()
                .createNewPaper(false)
                .createNewLevel()
                .linkBodyToLevels()
                .changePaperBody()
                .linkPaperToLevels()
                .createNewRegion()
                .createNewLocation()
                .createNewPricingMatrix()
                .createNewPrices();
    }

    @When("I create new Digital Content")
    public void i_create_digital_content() {
        new ProductFactoryBusinessProcesses()
                .createNewFinancialDimension("COSTCENTRE","COURSETYPE")
                .createNewFinancialDimension("PROJECT","COURSETYPE")
                .createNewFinancialDimension("PRODUCT","BODY")
                .createNewVertical()
                .createNewBody()
                .createNewSitting()
                .createNewVatRule()
                .createNewExamPreparation()
                .createNewStudyMode()
                .createNewCourseType()
                .createNewPaper(false)
                .createNewLevel()
                .linkBodyToLevels()
                .changePaperBody()
                .linkPaperToLevels()
                .createNewDigitalContent("PROFESSIONALQUALIFICATIONS","TOTARA");

    }

    @When("I create new University Programmes")
    public void i_create_university_programmes() {
        new ProductFactoryBusinessProcesses()
                .createNewUniversityProgrammes();
    }

    @When("I create new Programme Cohorts")
    public void i_create_programme_cohorts() {
        new ProductFactoryBusinessProcesses()
                .createNewUniversityProgrammes()
                .createNewProgrammeCohorts();
    }

    @When("I create new Module Sections")
    public void i_create_module_sections() {
        new ProductFactoryBusinessProcesses()
                .createNewUniversityProgrammes()
                .createNewProgrammeCohorts()
                .createNewModuleSections(false);
    }

    @When("I create new Stock Site")
    public void i_create_stock_site() {
        new ProductFactoryBusinessProcesses()
                .createNewStockSite();
    }

    @When("I create new Material Type")
    public void i_create_material_type() {
        new ProductFactoryBusinessProcesses()
                .createNewFinancialDimension("PRODUCT","MATERIALTYPE")
                .createNewMaterialType(true);
    }

    @When("I create new Clients")
    public void i_create_clients() {
        new ProductFactoryBusinessProcesses()
                .createNewClients();
    }

    @When("I create new Streams")
    public void i_create_streams() {
        new ProductFactoryBusinessProcesses()
                .createNewStreams();
    }

    @When("I create new Deactivation Reason")
    public void i_create_deactivation_reason() {
        new ProductFactoryBusinessProcesses()
                .createNewDeactivationReason(true);
    }

    @When("I create new Course")
    public void i_create_course() {
        new ProductFactoryBusinessProcesses()
                .createNewFinancialDimension("COSTCENTRE","COURSETYPE")
                .createNewFinancialDimension("PROJECT","COURSETYPE")
                .createNewFinancialDimension("PRODUCT","BODY")
                .createNewFinancialDimension("PRODUCT","REGION")
                .createNewVertical()
                .createNewBody()
                .createNewSitting()
                .createNewVatRule()
                .createNewExamPreparation()
                .createNewStudyMode()
                .createNewCourseType()
                .createNewPaper(false)
                .createNewLevel()
                .linkBodyToLevels()
                .changePaperBody()
                .linkPaperToLevels()
                .createNewRegion()
                .createNewCourse();
    }

    @When("I create new Instance")
    public void i_create_instance_for_course() {
        new ProductFactoryBusinessProcesses()
                .createNewFinancialDimension("COSTCENTRE","COURSETYPE")
                .createNewFinancialDimension("PROJECT","COURSETYPE")
                .createNewFinancialDimension("PRODUCT","BODY")
                .createNewFinancialDimension("PRODUCT","REGION")
                .createNewFinancialDimension("PRODUCT","LOCATION")
                .createNewVertical()
                .createNewBody()
                .createNewSitting()
                .createNewVatRule()
                .createNewExamPreparation()
                .createNewStudyMode()
                .createNewCourseType()
                .createNewPaper(false)
                .createNewLevel()
                .linkBodyToLevels()
                .changePaperBody()
                .linkPaperToLevels()
                .createNewRegion()
                .createNewLocation()
                .createNewCourse()
                .createNewSessionDuration(false)
                .createNewInstance();
    }

    @When("I change Instance Capacity")
    public void i_change_instance_capacity() {
        new ProductFactoryBusinessProcesses()
                .createNewFinancialDimension("COSTCENTRE","COURSETYPE")
                .createNewFinancialDimension("PROJECT","COURSETYPE")
                .createNewFinancialDimension("PRODUCT","BODY")
                .createNewFinancialDimension("PRODUCT","REGION")
                .createNewFinancialDimension("PRODUCT","LOCATION")
                .createNewVertical()
                .createNewBody()
                .createNewSitting()
                .createNewVatRule()
                .createNewExamPreparation()
                .createNewStudyMode()
                .createNewCourseType()
                .createNewPaper(false)
                .createNewLevel()
                .linkBodyToLevels()
                .changePaperBody()
                .linkPaperToLevels()
                .createNewRegion()
                .createNewLocation()
                .createNewCourse()
                .createNewSessionDuration(false)
                .createNewStreams()
                .createNewInstance()
                .changeInstanceCapacity(10);
    }

    @When("I get Instance Sessions")
    public void i_get_instance_sessions() {
        new ProductFactoryBusinessProcesses()
                .createNewFinancialDimension("COSTCENTRE","COURSETYPE")
                .createNewFinancialDimension("PROJECT","COURSETYPE")
                .createNewFinancialDimension("PRODUCT","BODY")
                .createNewFinancialDimension("PRODUCT","REGION")
                .createNewFinancialDimension("PRODUCT","LOCATION")
                .createNewVertical()
                .createNewBody()
                .createNewSitting()
                .createNewVatRule()
                .createNewExamPreparation()
                .createNewStudyMode()
                .createNewCourseType()
                .createNewPaper(false)
                .createNewLevel()
                .linkBodyToLevels()
                .changePaperBody()
                .linkPaperToLevels()
                .createNewRegion()
                .createNewLocation()
                .createNewCourse()
                .createNewSessionDuration(false)
                .createNewStreams()
                .createNewInstance()
                .changeInstanceCapacity(10)
                .getInstanceSessions();
    }

    @When("I change Session Timings")
    public void i_change_session_timings() {
        new ProductFactoryBusinessProcesses()
                .createNewFinancialDimension("COSTCENTRE","COURSETYPE")
                .createNewFinancialDimension("PROJECT","COURSETYPE")
                .createNewFinancialDimension("PRODUCT","BODY")
                .createNewFinancialDimension("PRODUCT","REGION")
                .createNewFinancialDimension("PRODUCT","LOCATION")
                .createNewVertical()
                .createNewBody()
                .createNewSitting()
                .createNewVatRule()
                .createNewExamPreparation()
                .createNewStudyMode()
                .createNewCourseType()
                .createNewPaper(false)
                .createNewLevel()
                .linkBodyToLevels()
                .changePaperBody()
                .linkPaperToLevels()
                .createNewRegion()
                .createNewLocation()
                .createNewCourse()
                .createNewSessionDuration(false)
                .createNewStreams()
                .createNewInstance()
                .changeInstanceCapacity(10)
                .getInstanceSessions()
                .changeSessionTimings();
    }

    @When("I get Instance Steps")
    public void i_get_instance_steps() {
        new ProductFactoryBusinessProcesses()
                .createNewFinancialDimension("COSTCENTRE","COURSETYPE")
                .createNewFinancialDimension("PROJECT","COURSETYPE")
                .createNewFinancialDimension("PRODUCT","BODY")
                .createNewFinancialDimension("PRODUCT","REGION")
                .createNewFinancialDimension("PRODUCT","LOCATION")
                .createNewVertical()
                .createNewBody()
                .createNewSitting()
                .createNewVatRule()
                .createNewExamPreparation()
                .createNewStudyMode()
                .createNewCourseType()
                .createNewPaper(false)
                .createNewLevel()
                .linkBodyToLevels()
                .changePaperBody()
                .linkPaperToLevels()
                .createNewRegion()
                .createNewLocation()
                .createNewCourse()
                .createNewSessionDuration(false)
                .createNewStreams()
                .createNewInstance()
                .changeInstanceCapacity(10)
                .getInstanceSessions()
                .changeSessionTimings()
                .getInstanceSteps();
    }

    @When("I change Instance Steps")
    public void i_change_instance_steps() {
        new ProductFactoryBusinessProcesses()
                .createNewFinancialDimension("COSTCENTRE","COURSETYPE")
                .createNewFinancialDimension("PROJECT","COURSETYPE")
                .createNewFinancialDimension("PRODUCT","BODY")
                .createNewFinancialDimension("PRODUCT","REGION")
                .createNewFinancialDimension("PRODUCT","LOCATION")
                .createNewVertical()
                .createNewBody()
                .createNewSitting()
                .createNewVatRule()
                .createNewExamPreparation()
                .createNewStudyMode()
                .createNewCourseType()
                .createNewPaper(false)
                .createNewLevel()
                .linkBodyToLevels()
                .changePaperBody()
                .linkPaperToLevels()
                .createNewRegion()
                .createNewLocation()
                .createNewCourse()
                .createNewSessionDuration(false)
                .createNewStreams()
                .createNewInstance()
                .changeInstanceCapacity(10)
                .getInstanceSessions()
                .changeSessionTimings()
                .getInstanceSteps()
                .changeInstanceSteps();
    }

    @When("I calculate Course Price")
    public void i_calculate_course_price() {
        new ProductFactoryBusinessProcesses()
                .createNewFinancialDimension("COSTCENTRE","COURSETYPE")
                .createNewFinancialDimension("PROJECT","COURSETYPE")
                .createNewFinancialDimension("PRODUCT","BODY")
                .createNewFinancialDimension("PRODUCT","REGION")
                .createNewFinancialDimension("PRODUCT","LOCATION")
                .createNewVatRule()
                .createNewExamPreparation()
                .createNewStudyMode()
                .createNewCourseType()
                .createNewVertical()
                .createNewBody()
                .createNewSitting()
                .createNewPaper(false)
                .createNewLevel()
                .linkBodyToLevels()
                .changePaperBody()
                .linkPaperToLevels()
                .createNewRegion()
                .createNewLocation()
                .createNewSessionDuration(false)
                .createNewPricingMatrix()
                .createNewPrices()
                .createNewDigitalContent("PROFESSIONALQUALIFICATIONS","TOTARA")
                .createNewCourse()
                .calculateCoursePrice();
    }

    @When("I activate Course")
    public void i_activate_course() {
        new ProductFactoryBusinessProcesses()
                .createNewFinancialDimension("COSTCENTRE","COURSETYPE")
                .createNewFinancialDimension("PROJECT","COURSETYPE")
                .createNewFinancialDimension("PRODUCT","BODY")
                .createNewFinancialDimension("PRODUCT","REGION")
                .createNewFinancialDimension("PRODUCT","LOCATION")
                .createNewVertical()
                .createNewBody()
                .createNewSitting()
                .createNewVatRule()
                .createNewExamPreparation()
                .createNewStudyMode()
                .createNewCourseType()
                .createNewPaper(false)
                .createNewLevel()
                .linkBodyToLevels()
                .changePaperBody()
                .linkPaperToLevels()
                .createNewDigitalContent("PROFESSIONALQUALIFICATIONS","TOTARA")
                .createNewRegion()
                .createNewLocation()
                .createNewCourse()
                .createNewSessionDuration(false)
                .createNewStreams()
                .createNewInstance()
                .changeInstanceCapacity(10)
                .getInstanceSessions()
                .changeSessionTimings()
                .getInstanceSteps()
                .changeInstanceSteps()
                .createNewPricingMatrix()
                .createNewPrices()
                .calculateCoursePrice()
                .activateCourse();
    }

    @When("I activate Instance")
    public void i_activate_instance() {
        new ProductFactoryBusinessProcesses()
                .createNewFinancialDimension("COSTCENTRE","COURSETYPE")
                .createNewFinancialDimension("PROJECT","COURSETYPE")
                .createNewFinancialDimension("PRODUCT","BODY")
                .createNewFinancialDimension("PRODUCT","REGION")
                .createNewFinancialDimension("PRODUCT","LOCATION")
                .createNewVertical()
                .createNewBody()
                .createNewSitting()
                .createNewVatRule()
                .createNewExamPreparation()
                .createNewStudyMode()
                .createNewCourseType()
                .createNewPaper(false)
                .createNewLevel()
                .linkBodyToLevels()
                .changePaperBody()
                .linkPaperToLevels()
                .createNewDigitalContent("PROFESSIONALQUALIFICATIONS","TOTARA")
                .createNewRegion()
                .createNewLocation()
                .createNewCourse()
                .createNewSessionDuration(false)
                .createNewStreams()
                .createNewInstance()
                .changeInstanceCapacity(10)
                .getInstanceSessions()
                .changeSessionTimings()
                .getInstanceSteps()
                .changeInstanceSteps()
                .createNewPricingMatrix()
                .createNewPrices()
                .calculateCoursePrice()
                .activateCourse()
                .activateInstance();
    }

    @When("I create Course Bulk Operation")
    public void i_create_course_bulk_operation() {
        new ProductFactoryBusinessProcesses()
                .createNewFinancialDimension("COSTCENTRE","COURSETYPE")
                .createNewFinancialDimension("PROJECT","COURSETYPE")
                .createNewFinancialDimension("PRODUCT","BODY")
                .createNewFinancialDimension("PRODUCT","REGION")
                .createNewFinancialDimension("PRODUCT","LOCATION")
                .createNewVertical()
                .createNewBody()
                .createNewSitting()
                .createNewVatRule()
                .createNewExamPreparation()
                .createNewStudyMode()
                .createNewCourseType()
                .createNewPaper(false)
                .createNewLevel()
                .linkBodyToLevels()
                .changePaperBody()
                .linkPaperToLevels()
                .createNewDigitalContent("PROFESSIONALQUALIFICATIONS","TOTARA")
                .createNewRegion()
                .createNewLocation()
                .createNewCourse()
                .createNewSessionDuration(false)
                .createNewStreams()
                .createNewInstance()
                .changeInstanceCapacity(10)
                .getInstanceSessions()
                .changeSessionTimings()
                .getInstanceSteps()
                .changeInstanceSteps()
                .createNewPricingMatrix()
                .createNewPrices()
                .calculateCoursePrice()
                .activateCourse()
                .activateInstance()
                .createCourseBulkOperation();
    }

    @When("I execute Bulk Web Publishing")
    public void i_execute_bulk_web_publishing() {
        new ProductFactoryBusinessProcesses()
                .createNewFinancialDimension("COSTCENTRE","COURSETYPE")
                .createNewFinancialDimension("PROJECT","COURSETYPE")
                .createNewFinancialDimension("PRODUCT","BODY")
                .createNewFinancialDimension("PRODUCT","REGION")
                .createNewFinancialDimension("PRODUCT","LOCATION")
                .createNewVertical()
                .createNewBody()
                .createNewSitting()
                .createNewVatRule()
                .createNewExamPreparation()
                .createNewStudyMode()
                .createNewCourseType()
                .createNewPaper(false)
                .createNewLevel()
                .linkBodyToLevels()
                .changePaperBody()
                .linkPaperToLevels()
                .createNewDigitalContent("PROFESSIONALQUALIFICATIONS","TOTARA")
                .createNewRegion()
                .createNewLocation()
                .createNewCourse()
                .createNewSessionDuration(false)
                .createNewStreams()
                .createNewInstance()
                .changeInstanceCapacity(10)
                .getInstanceSessions()
                .changeSessionTimings()
                .getInstanceSteps()
                .changeInstanceSteps()
                .createNewPricingMatrix()
                .createNewPrices()
                .calculateCoursePrice()
                .activateCourse()
                .activateInstance()
                .createCourseBulkOperation()
                .bulkWebPublish(true);
    }

}
