@Salesforce @CSA
Feature: CSA Workflow Alerts
  As a Salesforce CSA member:

  @EndToEnd #TC-804 TC-805 TC-1418 TC-1419
  Scenario: CSA Workflow Alerts
    Given I execute "Log In To Salesforce" reusable step
    And I execute "Create CSA Case My Course" reusable step
    And I execute "Login To Yahoo" reusable step
    And I fill the "Yahoo Search Email" field with "EC_CASENUMBER"
    And I press "MD_COMMON_KEYBOARD_ENTER" for "Yahoo Search Email"
    Then I click on the "Yahoo First Email Link" element
    And I validate text "CONTAINS=New case created." to be displayed for "Yahoo Case Email Header" element
    And I wait for "3" seconds
    And I am on "MD_COMMON_LINKS_SALESFORCEURL" URL
    And I execute "Search Case by Number" reusable step
    Then I click on the "Salesforce Edit Case Status" button by JS
    And I click on the "Salesforce Case Status Dropdown" element
    And I click on the "Ready to Close" "Salesforce cases status option"
    And I click on the "Save" "Product Factory button title"
    And I wait for "2" seconds
    Given I am on "MD_COMMON_LINKS_YAHOOMAILURL" URL
    And I click on the "Yahoo Next" button by JS
    And I fill the "Yahoo Password" field with "MD_COMMON_CREDENTIALS_YAHOOEMAILCSAPASSWORD"
    And I click on the "Yahoo Sign In" button by JS
    And I fill the "Yahoo Search Email" field with "EC_CASENUMBER"
    And I press "MD_COMMON_KEYBOARD_ENTER" for "Yahoo Search Email"
    Then I click on the "Yahoo First Email Link" element
    And I validate text "CONTAINS=Case ready to be closed." to be displayed for "Yahoo Case Email Header" element
    Then I click on the "Yahoo Reply" button
    Then I fill the "Yahoo Body" field with "Automation regression testing"
    Then I click on the "Yahoo Send" button
    And I wait for "2" seconds
    And I am on "MD_COMMON_LINKS_SALESFORCEURL" URL
    And I execute "Search Case by Number" reusable step
    And I validate text "Re-opened" to be displayed for "Salesforce Case Status Data Field" element

  @Positive
  Scenario: CSA Ready To Close
    Given I execute "Log In To Salesforce" reusable step
    And I execute "Create CSA Case My Course" reusable step
    And I execute "Login To Yahoo" reusable step
    And I fill the "Yahoo Search Email" field with "EC_CASENUMBER"
    And I press "MD_COMMON_KEYBOARD_ENTER" for "Yahoo Search Email"
    Then I click on the "Yahoo First Email Link" element
    And I validate text "CONTAINS=New case created." to be displayed for "Yahoo Case Email Header" element
    And I wait for "3" seconds
    And I am on "MD_COMMON_LINKS_SALESFORCEURL" URL
    And I execute "Search Case by Number" reusable step
    Then I click on the "Salesforce Edit Case Status" button by JS
    And I click on the "Salesforce Case Status Dropdown" element
    And I click on the "Ready to Close" "Salesforce cases status option"
    And I click on the "Save" "Product Factory button title"
    And I wait for "2" seconds
    Given I am on "MD_COMMON_LINKS_YAHOOMAILURL" URL
    And I click on the "Yahoo Next" button by JS
    And I fill the "Yahoo Password" field with "MD_COMMON_CREDENTIALS_YAHOOEMAILCSAPASSWORD"
    And I click on the "Yahoo Sign In" button by JS
    And I fill the "Yahoo Search Email" field with "EC_CASENUMBER"
    And I press "MD_COMMON_KEYBOARD_ENTER" for "Yahoo Search Email"
    Then I click on the "Yahoo First Email Link" element
    And I validate text "CONTAINS=Case ready to be closed." to be displayed for "Yahoo Case Email Header" element
