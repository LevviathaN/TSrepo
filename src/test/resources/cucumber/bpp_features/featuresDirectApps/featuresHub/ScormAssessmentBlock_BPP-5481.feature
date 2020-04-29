@Hub @ScormAssessment
Feature: As a user in the Hub, when a SCORM package has been set as the Key Activity on my Topic Overview page,
  I expect it to appear the same workflow as the current SCORM Assessment Block

  #TC-2715
  Scenario: Complete SCORM Assessment
    When I remember "Auto[EMAIL-HARAKIRI]" text as "EC_VARIABLE" variable
    When I execute "Register New Student Account" reusable step replacing some steps
      |5|I set "EC_AUTO_EMAIL" text to the "Email" "Build Empire text field"|
    Then I should see the "Home" "Build Empire Navigation Menu elements"
    When I execute "Log Out from Hub Student" reusable step
    Then I execute "Login as Admin Totara" reusable step
    Then I click on the "Totara Automation Course" button by JS
    And I click on the "Totara Left Menu Users link" link by JS
    And I click on the "Totara Enrolled Users button" button by JS
    And I click on the "Totara Enrol User button" button by JS
    And I execute "document.getElementById('enrolusersearch').setAttribute('value', 'EC_VARIABLE')" JS code
    And I click on the "Totara Enrol User Search button" button by JS
    And I click on the "Totara Enrol Button" button by JS
    And I click on the "Totara Finish Enroling Users button" button by JS
    Then I execute "Logout as Admin Totara" reusable step
    When I execute "Log In to Hub as Student" reusable step
    Then I should see the "My Learning" "Build Empire Navigation Menu elements"
    And I click on the "Direct App My Learning Automation Course Name" element
    And I click on the "Direct App Scorm Topic" element
    And I validate text "You haven't attempted this assessment yet" to be displayed for "Direct App Topic Assessment Status Results" element
    And I click on the "Direct App Topic Take Test Button" option
    And I wait for "5" seconds
    Then I click on the "Totara Scorm Frame Next" button by JS
    Then I click on the "Totara Scorm Frame Next" button by JS
    Then I click on the "Totara Scorm Frame Next" button by JS
    Then I click on the "Totara Scorm Frame Next" button by JS
    Then I click on the "Totara Scorm Frame Next" button by JS
    Then I click on the "Totara Scorm Frame Next" button by JS
    Then I click on the "Totara Scorm Frame Next" button by JS
    Then I click on the "Totara Scorm Frame Next" button by JS
    Then I click on the "Totara Scorm Frame Next" button by JS
    Then I click on the "Totara Scorm Frame Next" button by JS
    Then I click on the "Totara Scorm Frame Next" button by JS
    Then I click on the "Totara Scorm Frame Next" button by JS
    Then I click on the "Totara Scorm Frame Next" button by JS
    Then I click on the "Totara Scorm Frame Next" button by JS
    Then I execute "window.stop()" JS code
    Then I click on the "Totara Scorm Frame Submit" button

    And I validate text "Failed" to be displayed for "Direct App Topic Assessment Status Results" element
    And I validate text "CONTAINS=0" to be displayed for "Direct App Topic Assessment Percentage Results" element


