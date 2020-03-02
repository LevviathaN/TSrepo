package api.requests;

import api.RestApiController;
import api.SoapApiController;
import api.Utilities;
import io.restassured.response.Response;
import ui.utils.BPPLogManager;
import ui.utils.GlobalDataBridge;
import ui.utils.Reporter;
import ui.utils.bpp.ExecutionContextHandler;
import ui.utils.bpp.PropertiesHelper;

import java.util.ArrayList;
import java.util.Random;

import static com.jcabi.matchers.RegexMatchers.matchesPattern;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.containsString;

/**
 *<p> Contains all methods required for main Salesforce API calls: e.g. Create Faculty, Student Account, Add Address.</p>
 */

public class SalesforceBusinessProcesses {

    private PropertiesHelper propertiesHelper = new PropertiesHelper();
    private RestApiController restController;
    private SoapApiController soapController;
    private Utilities utils;


    public SalesforceBusinessProcesses() {
        this.restController = new RestApiController();
        this.soapController = new SoapApiController();
        this.utils = new Utilities();
    }

    public SalesforceBusinessProcesses createNewFaculty() {

        Response facultyResponse = restController.postRequest(propertiesHelper.getProperties().getProperty("sf_faculty_url"),
                restController.processProperties("addNewFaculty"));

        assertThat(facultyResponse.getBody().asString(), containsString("success"));

        ExecutionContextHandler.setExecutionContextValueByKey("EC_FACULTY_ID", new Utilities().getResponseProperty(facultyResponse, "id"));
        Reporter.log("<pre>" + "Faculty Id: " + facultyResponse.getBody().asString() + "</pre>");

        return this;
    }

    public SalesforceBusinessProcesses submitNewFaculty() {

        Response thisResponse = soapController.postSoapRequest(propertiesHelper.getProperties().getProperty("sf_faculty_submit_url"),
                soapController.processSoapProperties("faculty_SubmitApplication", "FacultyId"));

        assertThat(thisResponse.getBody().asString(), containsString("Application Submitted Successfully"));

        Response dataResponse = restController.getRequest(propertiesHelper.getProperties().getProperty("sf_faculty_url") + "/" + ExecutionContextHandler.getExecutionContextValueByKey("EC_FACULTY_ID"));

        ArrayList<String> recordData = utils.getRecordData(utils.getResponseProperty(dataResponse));

        GlobalDataBridge.getInstance().setBufferValueByKey("Faculty".concat(Integer.toString(new Random().nextInt(999999999))), recordData);

        assertThat(recordData.get(10), anyOf(matchesPattern("[a-z]\\.[a-z]+"), matchesPattern("[a-z]\\.[a-z]+[0-9]+$")));
        assertThat(recordData.get(25), matchesPattern("[A-Z][a-z]\\d+"));

        return this;
    }

    public SalesforceBusinessProcesses createNewStudent() {

        Response accountResponse = restController.postRequest(propertiesHelper.getProperties().getProperty("sf_account_url"),
                restController.processProperties("addNewAccount"));

        assertThat(accountResponse.getBody().asString(), containsString("success"));
        Reporter.log("<pre>" + "Account Id: " + accountResponse.getBody().asString() + "</pre>");

        ExecutionContextHandler.setExecutionContextValueByKey("EC_ACCOUNT_ID", new Utilities().getResponseProperty(accountResponse, "id"));

        return this;
    }

    public SalesforceBusinessProcesses addAddress() {

        Response addressResponse = restController.postRequest(propertiesHelper.getProperties().getProperty("sf_address_url"),
                restController.processProperties("addNewAddress"));

        assertThat(addressResponse.getBody().asString(), containsString("success"));
        Reporter.log("<pre>" + "Address Id: " + addressResponse.getBody().asString() + "</pre>");

        return this;
    }

    public SalesforceBusinessProcesses getAccountProfileId() {

        Response thisResponse = soapController.postSoapRequest(propertiesHelper.getProperties().getProperty("sf_student_get_profile_id_url"),
                soapController.processSoapProperties("account_GetProfileID", "accountId"));

        assertThat(thisResponse.getBody().asString(), containsString("Success"));

        return this;
    }

    public SalesforceBusinessProcesses addOpportunity() {

        Response opportunityResponse = restController.postRequest(propertiesHelper.getProperties().getProperty("sf_opportunity_url"),
                restController.processProperties("addNewOpportunity"));

        assertThat(opportunityResponse.getBody().asString(), containsString("success"));
        Reporter.log("<pre>" + "Opportunity Id: " + opportunityResponse.getBody().asString() + "</pre>");

        ExecutionContextHandler.setExecutionContextValueByKey("EC_OPPORTUNITY_ID", new Utilities().getResponseProperty(opportunityResponse, "id"));

        return this;
    }

    public SalesforceBusinessProcesses addOffering() {

        Response offeringResponse = restController.postRequest(propertiesHelper.getProperties().getProperty("sf_product_url"),
                restController.processProperties("addNewProductItem"));

        assertThat(offeringResponse.getBody().asString(), containsString("success"));
        Reporter.log("<pre>" + "Offering Id: " + offeringResponse.getBody().asString() + "</pre>");

        return this;
    }

    public SalesforceBusinessProcesses submitNewAccount() {

        Response thisResponse = soapController.postSoapRequest(propertiesHelper.getProperties().getProperty("sf_student_submit_application_url"),
                soapController.processSoapProperties("account_SubmitApplication", "id"));

        assertThat(thisResponse.getBody().asString(), containsString("Application Submitted Successfully"));

        Response dataResponse = restController.getRequest(propertiesHelper.getProperties().getProperty("sf_account_url") + "/" + ExecutionContextHandler.getExecutionContextValueByKey("EC_ACCOUNT_ID"));
        ArrayList<String> recordData = utils.getRecordData(utils.getResponseProperty(dataResponse));
        GlobalDataBridge.getInstance().setBufferValueByKey("Student".concat(Integer.toString(new Random().nextInt(999999999))), recordData);

        assertThat(recordData.get(68), matchesPattern("BP[0-9]+"));

        BPPLogManager.getLogger().info("Banner Id: " + recordData.get(68));
        return this;
    }

    public SalesforceBusinessProcesses createNewBusinessAccount() {

        Response businessAccountResponse = restController.postRequest(propertiesHelper.getProperties().getProperty("sf_account_url"),
                restController.processProperties("addNewBusinessAccount"));

        assertThat(businessAccountResponse.getBody().asString(), containsString("success"));
        Reporter.log("<pre>" + "Business Account Id: " + businessAccountResponse.getBody().asString() + "</pre>");

        ExecutionContextHandler.setExecutionContextValueByKey("EC_BUSINESS_ACCOUNT_ID", new Utilities().getResponseProperty(businessAccountResponse, "id"));

        return this;
    }

    public SalesforceBusinessProcesses addTrainingManager() {

        Response trainingManagerResponse = restController.postRequest(propertiesHelper.getProperties().getProperty("sf_contact_url"),
                restController.processProperties("addNewTrainingManager"));

        assertThat(trainingManagerResponse.getBody().asString(), containsString("success"));
        Reporter.log("<pre>" + "Training Manager Id: " + trainingManagerResponse.getBody().asString() + "</pre>");

        return this;
    }

    public SalesforceBusinessProcesses addBusinessAccountAddress() {

        Response businessAccountAddressResponse = restController.postRequest(propertiesHelper.getProperties().getProperty("sf_address_url"),
                restController.processProperties("addNewBusinessAccountAddress"));

        assertThat(businessAccountAddressResponse.getBody().asString(), containsString("success"));
        Reporter.log("<pre>" + "Business Account Address Id: " + businessAccountAddressResponse.getBody().asString() + "</pre>");

        return this;
    }

    public SalesforceBusinessProcesses getBusinessAccountId() {

        Response thisResponse = soapController.postSoapRequest(propertiesHelper.getProperties().getProperty("sf_sponsor_get_company_id_url"),
                soapController.processSoapProperties("sponsor_GetCompanyProfileID", "id"));

        assertThat(thisResponse.getBody().asString(), containsString("Success"));

        return this;
    }

    public SalesforceBusinessProcesses submitBusinessAccount() {

        Response thisResponse = soapController.postSoapRequest(propertiesHelper.getProperties().getProperty("sf_sponsor_get_ar_sis_services_id_url"),
                soapController.processSoapProperties("sponsor_Get_AR_SIS_ServicesID", "id"));

        assertThat(thisResponse.getBody().asString(), containsString("Application Submitted Successfully"));

        return this;
    }

    public SalesforceBusinessProcesses addSponsorship() {

        Response sponsorshipResponse = restController.postRequest(propertiesHelper.getProperties().getProperty("sf_sponsorship_url"),
                restController.processProperties("addNewSponsorship"));

        assertThat(sponsorshipResponse.getBody().asString(), containsString("success"));
        Reporter.log("<pre>" + "Sponsorship Id: " + sponsorshipResponse.getBody().asString() + "</pre>");

        return this;
    }

    /*    public SalesforceBusinessProcesses createNewCase() {

        Response caseResponse = restController.postRequest(propertiesHelper.getProperties().getProperty("sf_case_url"),
                restController.processProperties("addNewCase"));

        assertThat(caseResponse.getBody().asString(), containsString("success"));

        ExecutionContextHandler.setExecutionContextValueByKey("EC_CASE_ID", new Utilities().getResponseProperty(caseResponse, "id"));
        Response dataResponse = restController.getRequest(propertiesHelper.getProperties().getProperty("sf_case_url") + "/" + ExecutionContextHandler.getExecutionContextValueByKey("EC_CASE_ID"));

        ExecutionContextHandler.setExecutionContextValueByKey("EC_CASE_OWNER", new Utilities().getResponseProperty(dataResponse, "Owner_Name_Department__c"));

        Reporter.log("<pre>" + "Case Id: " + caseResponse.getBody().asString() + "</pre>");
        Reporter.log("<pre>" + "Case Owner: " + ExecutionContextHandler.getExecutionContextValueByKey("EC_CASE_OWNER") + "</pre>");

        return this;
    }*/


}
