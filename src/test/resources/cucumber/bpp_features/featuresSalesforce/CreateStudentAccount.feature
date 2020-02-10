@Salesforce @Student
Feature: Create Student Account
  Description

  @Positive #TC-253 TC-254 TC-294 TC-76
  Scenario: Create Student Account and Submit Application
    Given I execute "Log In To Salesforce" reusable step
#    And I execute "Switch to Sales App" reusable step
    And I execute "Create Student Account" reusable step
    And I execute "Create Address" reusable step
    And I execute "Get Profile ID" reusable step
    And I execute "Create Opportunity" reusable step
    And I execute "Add Product Catalog Item" reusable step
    And I execute "Submit Application" reusable step