package cucumber.api;

import api.requests.SalesforceBusinessProcesses;
import api.requests.SalesforceBusinessProcessesUAT;
import io.cucumber.java.en.*;

public class SalesforceAPI {
    @When("I create new faculty record")
    public void i_create_new_faculty_record() {
        if (System.getProperties().containsKey("environment") && System.getProperty("environment").equals("UAT"))
            new SalesforceBusinessProcessesUAT().createNewFacultyUAT();
        else
            new SalesforceBusinessProcesses().createNewFaculty();
    }

    @Then("I should submit faculty")
    public void i_should_submit_faculty() {
        if (System.getProperties().containsKey("environment") && System.getProperty("environment").equals("UAT"))
            new SalesforceBusinessProcessesUAT().submitNewFacultyUAT();
        else
            new SalesforceBusinessProcesses().submitNewFaculty();
    }

    @And("I create new student account")
    public void i_should_create_student_account() {
        if (System.getProperties().containsKey("environment") && System.getProperty("environment").equals("UAT"))
            new SalesforceBusinessProcessesUAT().
                    createNewStudentUAT().
                    addAddressUAT().
                    getAccountProfileIdUAT().
                    addOpportunityUAT().
                    addOfferingUAT().
                    submitNewAccountUAT();
        else {
            new SalesforceBusinessProcesses().
                    createNewStudent().
                    addAddress().
                    getAccountProfileId().
                    addOpportunity().
                    addOffering().
                    submitNewAccount();
        }
    }
}
