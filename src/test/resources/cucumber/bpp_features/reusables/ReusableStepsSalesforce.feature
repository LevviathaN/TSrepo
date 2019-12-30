@Reusable
Feature: Salesforce

  Scenario: Log In To Salesforce
    Given I am on "MD_COMMON_LINKS_SALESFORCEURL" URL
    Then I fill the "Salesforce Username" field with "MD_COMMON_CREDENTIALS_BPPADMINUSER"
    And I fill the "Salesforce Password" field with "MD_COMMON_CREDENTIALS_BPPADMINPASSWORD"
    And I click on the "Salesforce Login" button
    Then I should see the "Salesforce Context Bar Menu" element

  Scenario: Log Out Salesforce
    And I click on the "Salesforce User Menu Icon" button
    And I click on the "Salesforce Log Out" button