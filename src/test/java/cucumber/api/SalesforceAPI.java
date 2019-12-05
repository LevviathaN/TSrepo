package cucumber.api;

import api.requests.SalesforceBusinessProcesses;
import cucumber.api.java.en.*;

public class SalesforceAPI {
    @When("I create new faculty record")
    public void i_create_new_faculty_record() {
       new SalesforceBusinessProcesses().createNewFaculty();
    }

    @Then("I should submit faculty")
    public void i_should_submit_faculty() {
        new SalesforceBusinessProcesses().submitNewFaculty();
    }
}
