@Reusable
Feature: Hub, Totara

  Scenario: Login as Admin Totara
    Given I am on "MD_COMMON_LINKS_TOTARALOGINURL" URL
    And I fill the "Salesforce Username" field with "MD_COMMON_CREDENTIALS_TOTARAADMINUSER"
    And I fill the "Salesforce Password" field with "MD_COMMON_CREDENTIALS_TOTARAADMINPASSWORD"
    Then I click on the "Totara Login" button by JS

  Scenario: Logout as Admin Totara
    Then I click on the "Totara Account Expandable Menu" element by JS
    Then I click on the "Totara Logout Link" button by JS
    And I wait for "2" seconds
    And I execute "document.getElementsByClassName('logininfo')[0].innerText;" JS code

  Scenario: Add Key Activities As Admin Totara
    Then I click on the "Totara Automation Course" button by JS
    And I click on the "Totara Key Activities Administration Link" link by JS
    And I click on the "Topic to test adding activities" link by JS
    Then I click on the "Totara Add Activity Top Key" button by JS
    Then I wait for "3" seconds
    And I select "KW_AUTO_SELECT" from "Totara Activities Dropdown" element
    Then I execute "document.getElementById('id_text_mainboxtexteditable').innerText='MainTopAutomation';" JS code
    Then I execute "document.getElementById('id_text_submitbuttontexteditable').innerText='SubmitTopAutomation';" JS code
    Then I click on the "Totara Save" button by JS
    And I click on the "Totara Continue" button by JS
    And I click on the "Topic to test adding activities" link by JS
    Then I click on the "Totara Add Activity Bottom Key" button by JS
    Then I execute "document.getElementById('id_text_mainboxtexteditable').innerText='MainBottomAutomation';" JS code
    Then I execute "document.getElementById('id_text_submitbuttontexteditable').innerText='SubmitBottomAutomation';" JS code
    And I select "KW_AUTO_SELECT" from "Totara Activities Dropdown" element
    Then I click on the "Totara Save" button by JS
    And I click on the "Totara Continue" button by JS
    And I click on the "Topic to test adding activities" link by JS
    And I validate text "Testing Activities" to be displayed for "Totara Top Key Activity Name" element
    And I validate text "Testing Activities" to be displayed for "Totara Bottom Key Activity Name" element