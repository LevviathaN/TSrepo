@Salesforce @CSA @Cases
Feature: CSA Workflow Alerts
  As a Salesforce CSA member:

  @EndToEnd #TC-804 TC-805 TC-1418 TC-1419
  Scenario: CSA Workflow Alerts End To End
    Given I execute "Log In To Salesforce" reusable step
    And I execute "Create CSA Case My Course" reusable step
    And I execute "Log Out Salesforce" reusable step
    And I wait for "3" seconds
    And I execute "Login To Yahoo" reusable step
    And I fill the "Yahoo Search Email" field with "EC_CASENUMBER"
    And I press "MD_COMMON_KEYBOARD_ENTER" for "Yahoo Search Email"
    Then I click on the "Yahoo First Email Link" element
    And I validate text "CONTAINS=New case created." to be displayed for "Yahoo Case Email Header" element
    And I wait for "3" seconds
    Given I am on "MD_COMMON_LINKS_SALESFORCEURL" URL
    Then I fill the "Salesforce Username" field with "MD_COMMON_CREDENTIALS_BPPADMINUSER"
    And I fill the "Salesforce Password" field with "MD_COMMON_CREDENTIALS_BPPADMINPASSWORD"
    And I click on the "Salesforce Login" button
    And I execute "Search Case by Number" reusable step
    And I should scroll to the "top" of the page
    Then I click on the "Salesforce Edit Case Status" button by JS
    And I click on the "Salesforce Case Status Dropdown" element
    And I click on the "Ready to Close" "Salesforce cases status option"
    And I click on the "Salesforce Save Account Button" button
    And I wait for "2" seconds
    And I execute "Log Out Salesforce" reusable step
    And I wait for "1" seconds
    Given I am on "MD_COMMON_LINKS_YAHOOMAILURL" URL
    And I wait for "1" seconds
    And I fill the "Yahoo Search Email" field with "EC_CASENUMBER"
    And I press "MD_COMMON_KEYBOARD_ENTER" for "Yahoo Search Email"
    Then I click on the "Yahoo First Email Link" element
    And I validate text "CONTAINS=Case ready to be closed." to be displayed for "Yahoo Case Email Header" element
    Then I should scroll to the "bottom" of the page
    Then I click on the "Yahoo Reply" button by JS
    Then I fill the "Yahoo Body" field with "Automation regression testing"
    Then I click on the "Yahoo Send" button
    And I wait for "2" seconds
    Given I execute "Log In To Salesforce" reusable step
    And I execute "Search Case by Number" reusable step
    And I validate text "Re-opened" to be displayed for "Salesforce Case Status Data Field" element