@Hub @KeyActivities
Feature: As an admin, setting up a course in Totara, I expect to see new controls associated with each topic
  that allows me to set the 'Key Activities' for each Topic

  @Positive #TC-2633 TC-2634
  Scenario:  As an admin, I expect to see new controls associated with each topic that allows me to set the 'Key Activities' for each Topic
    And I execute "Login as Admin Totara" reusable step
    Then I click on the "Totara Automation Course" button by JS
    And I click on the "Totara Key Activities Administration Link" link by JS
    And I click on the "Topic to test adding activities" link by JS
    Then I click on the "Totara Add Activity Top Key" button by JS
    Then I wait for "3" seconds
    And I select "[scorm] Simple test" from "Totara Activities Dropdown" element
    Then I execute "document.getElementById('id_text_mainboxtexteditable').innerHTML='MainTopAutomation';" JS code
    Then I execute "document.getElementById('id_text_submitbuttontexteditable').innerHTML='SubmitTopAutomation';" JS code
    Then I click on the "Totara Save" button by JS
    And I click on the "Totara Continue" button by JS
    And I click on the "Topic to test adding activities" link by JS
    Then I click on the "Totara Add Activity Bottom Key" button by JS
    Then I execute "document.getElementById('id_text_mainboxtexteditable').innerHTML='MainBottomAutomation';" JS code
    Then I execute "document.getElementById('id_text_submitbuttontexteditable').innerHTML='SubmitBottomAutomation';" JS code
    And I select "[assign] Testing Activities" from "Totara Activities Dropdown" element
    Then I click on the "Totara Save" button by JS
    And I click on the "Totara Continue" button by JS
    And I click on the "Topic to test adding activities" link by JS
    And I validate text "Simple Test" to be displayed for "Totara Top Key Activity Name" element
    And I validate text "Testing Activities" to be displayed for "Totara Bottom Key Activity Name" element
    And I execute "Logout as Admin Totara" reusable step