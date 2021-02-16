@BuildEmpire @Hub @SubTopics @Totara
Feature: BPP University Hub Sub Topics and Labels
  As a Hub user, viewing the Topic Overview page,
  I expect to be able to see descriptions regarding my Sub-topics
  As this will help me to understand the course

  Background:
    Given I execute "Register New Student Account" reusable step
    Then I execute "Harakirimail Verify Email" reusable step
    Then I execute "Log In to Hub as Student" reusable step
    When I execute "Log Out from Hub Student" reusable step
    And I wait for "7" seconds

  @Positive @BET #TC-2233 TC-2239 TC-2242
  Scenario: Hub Sub Topics Description Validation
    #When I execute "Log Out from Hub Student" reusable step
    When I am on "MD_COMMON_LINKS_TOTARALOGINURL" URL
    And I execute "Logout as Admin Totara" reusable step if "Totara Account Expandable Menu" "element is present"
    And Browser deletes cookies
    And I wait for "5" seconds
    Then I execute "Login as Admin Totara" reusable step
    #Enrol to a course
    When I click on the "Totara Automation Only Course" link by JS
    And I click on the "Totara Left Menu Users link" link by JS
    And I click on the "Totara Enrolled Users button" button by JS
    And I wait for "3" seconds
    And I click on the "Totara Enrol User button" button by JS
    And I set "EC_AUTO_EMAIL" text to the element with ID "enrolusersearch" using JS
    And I click on the "Totara Enrol User Search button" button by JS
    And I click on the "Totara Enrol Button" button by JS
    And I click on the "Totara Finish Enroling Users button" button by JS
    #Totara Clear Topics
    When I click on the "Dashboard" "text contained in SPAN element"
    When I click on the "Totara Automation Only Course" link by JS
    And I click on the "Turn editing on" "BPP Digital Admin Value attribute button"
    And I execute "Course Topic Clean Up" reusable step if "Totara Automation First Topic Edit button" "element is present"
    And I execute "Course Topic Clean Up" reusable step if "Totara Automation First Topic Edit button" "element is present"
    And I execute "Course Topic Clean Up" reusable step if "Totara Automation First Topic Edit button" "element is present"
    And I execute "Course Topic Clean Up" reusable step if "Totara Automation First Topic Edit button" "element is present"
    And Browser performs "Refresh" command
    And I wait for "10" seconds
    #Totara Add Topic
    And I click on the "Totara Add Topic Plus icon" element
    And I wait for "2" seconds
    And I click on the "Totara Edit Topic Name icon" element
    And I fill the "Totara Set Topic Name field" field with "AutomationTopic[###]"
    And I press "MD_COMMON_KEYBOARD_ENTER" for "Totara Set Topic Name field"
    And I wait for "3" seconds
    #Totara Add Sub-Topic Label
    And I click on the "Totara Automation Course Created Topic Add and Activity button" link
    And I click on the "Totara Add an Activity Label Radio button" element
    And I click on the "Totara Add an Activity Add Button" button
    And I fill the "Totara Adding a New Label Text Field" field with "AutomationSubTopic[###]"
    And I click on the "Save and return to course" "BPP Digital Admin Value attribute button"
    #Totara Add Assignment
    And I click on the "Totara Automation Course Created Topic Add and Activity button" link
    And I click on the "Totara Add an Activity Assignment Radio button" element
    And I click on the "Totara Add an Activity Add Button" button
    And I fill the "Totara Assignment name Text Field" field with "AutomationAssignmentName[###]"
    And I fill the "Totara Adding a New Label Text Field" field with "AutomationAssignmentDescription[###]"
    And I "check" "Totara Display Description on a Course checkbox" checkbox
    And I click on the "Save and return to course" "BPP Digital Admin Value attribute button"