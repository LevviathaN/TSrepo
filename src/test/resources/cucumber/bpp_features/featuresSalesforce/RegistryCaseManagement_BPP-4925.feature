@Salesforce @RegistryCaseManagement
Feature: Registry Case Management
  As a Registry Salesforce Classic User switching to Lightning Experience,
  I want Registry Case Management to perform as expected

  @EndToEnd #TC-2535
  Scenario: Registry Case Management End To End
    Given I execute "Log In To Salesforce" reusable step
    And I execute "Create ID Card Registry Case" reusable step
    Then I click on the "Salesforce Edit Case Status" button by JS
    And I click on the "Salesforce Case Status Dropdown" element
    And I click on the "In Progress" "Salesforce cases status option"
    And I click on the "Save" "Product Factory button title"
    And I wait for "2" seconds
    And I validate text "In Progress" to be displayed for "Salesforce Case Status Data Field" element
    Then I click on the "Salesforce Edit Case Status" button by JS
    And I click on the "Salesforce Case Status Dropdown" element
    And I click on the "Ready to Close" "Salesforce cases status option"
    And I click on the "Save" "Product Factory button title"
    And I wait for "2" seconds
    And I validate text "Ready to Close" to be displayed for "Salesforce Case Status Data Field" element