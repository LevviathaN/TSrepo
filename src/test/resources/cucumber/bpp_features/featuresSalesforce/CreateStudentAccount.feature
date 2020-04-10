@Salesforce @Student
Feature: Create Student Account
  Description

  @Positive #TC-253 TC-254 TC-294 TC-76
  Scenario: Create Student Account and Submit Application
    Given I execute "Log In To Salesforce" reusable step
    Given I execute "Create Student Account" reusable step
    And I execute "Create Address" reusable step
    And I execute "Get Profile ID" reusable step
    And I execute "Create Opportunity" reusable step
    And I click on the "EC_OPPORTUNITY_NAME" "Salesforce link"
    And I click on the "Product Catalog Items" "Salesforce related new button"
    And I click on the "New" "Salesforce dropdown option"
    And I fill the "Salesforce Offering Text Field" field with "2381626"
    And I click on the "Salesforce Search" button
    And I "check" "Salesforce Offering Checkbox" checkbox
    And I "check" "Salesforce Product Checkbox" checkbox
    And I click on the "Salesforce Add Products" button
    Given I execute "Submit Application" reusable step

    And I click on the "Salesforce More Actions Dropdown" button
    And I click on the "Submit Application LEX" "Salesforce dropdown option"
    And I click on the "Salesforce Account Name Link" element
    And I click on the "Salesforce Account Details Tab" element
    And I validate text "STARTS-WITH=BP" to be displayed for "Salesforce Banner ID Field" element
    And I capture text data "Salesforce Banner ID Field" as "EC_BANNER_ID" variable
    And I capture text data "Salesforce Profile Services ID Field" as "EC_PROFILE_ID" variable