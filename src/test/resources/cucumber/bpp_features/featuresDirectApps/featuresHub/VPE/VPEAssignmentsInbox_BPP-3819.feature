@Hub @VPE
Feature: The Hub - Law School - Assignments/Emails - Inbox Overview
  As a user, if an "assignment" has been added to a topic in a VPE course,
  I expect each assignment to display as a separate "email" on the left hand side of the page

  @Positive @Assignment #TC-1916, TC-2109, TC-2112, TC-2081, TC-2076, TC-2133
  Scenario: The Hub - Law School - Assignments and Emails - Inbox Overview
    Then I execute "Login as Admin Totara" reusable step
    When I click on the "Totara Volodymyr Course" link by JS
    Then I am on "https://totara.staging.bppdigital.buildempire.app/course/management.php?categoryid=4&courseid=227" URL
    And I execute "arguments[0].click();" JS code for "View" "text contained in A element"
    And I click on the "Turn editing on" "BPP Digital Admin Value attribute button"
    And I should scroll to the "bottom" of the page
    Then I wait for "2" seconds
    And I execute "arguments[0].click();" JS code for "Increase the number of sections" "Salesforce cases status option"
    And I should scroll to the "bottom" of the page
    And I click on the "Totara Add Activity For VPE Last Topic" button by JS
    And I wait for "1" seconds
    And I execute "arguments[0].click();" JS code for "Assignment" "Product Factory button"
    Then I click on the "Totara Add an Activity Add Button" link by JS
    And I wait for "2" seconds
    And I fill the "Totara Add New Quiz Name Text Field" field with "AutoTest Created"
    And I execute "arguments[0].click();" JS code for "VPE Settings" "text contained in A element"
    And I fill the "Totara VPE Email Sender" field with "AutoTestSender"
    And I wait for "1" seconds
    And I execute "arguments[0].click();" JS code for "Save and return to course" "BPP Digital Admin Value attribute button"
    And I wait for "3" seconds
    And I should scroll to the "bottom" of the page
    And I click on the "Totara Add Activity For VPE Last Topic" button by JS
    And I wait for "1" seconds
    And I execute "arguments[0].click();" JS code for "Assignment" "Product Factory button"
    Then I click on the "Totara Add an Activity Add Button" link by JS
    And I wait for "2" seconds
    And I fill the "Totara Add New Quiz Name Text Field" field with "AutoTest Assignment2"
    And I select "2023" from "Totara Assignment Due Date Year" element
    And I wait for "1" seconds
    And I execute "arguments[0].click();" JS code for "Save and return to course" "BPP Digital Admin Value attribute button"
    And I wait for "3" seconds
    And I should scroll to the "bottom" of the page
    And I click on the "Totara Add Activity For VPE Last Topic" button by JS
    And I wait for "1" seconds
    And I execute "arguments[0].click();" JS code for "Assignment" "Product Factory button"
    Then I click on the "Totara Add an Activity Add Button" link by JS
    And I wait for "2" seconds
    And I fill the "Totara Add New Quiz Name Text Field" field with "AutoTest Assignment3"
    And I select "2022" from "Totara Assignment Due Date Year" element
    And I wait for "1" seconds
    And I execute "arguments[0].click();" JS code for "Save and return to course" "BPP Digital Admin Value attribute button"
    And I wait for "3" seconds
    Then I execute "Logout as Admin Totara" reusable step
    #User validation
    Given I execute "Log In to Hub as Student" reusable step replacing some steps
      | 2 | I set "vpecourse@guerillamail.info" text to the "Email" "Build Empire text field" |
      | 3 | I set "A@polloGlobal2020" text to the "Password" "Build Empire text field"        |
    And I wait for "1" seconds
    And I should see the "VPE Course Automation" "Build Empire My Learning Right Block Course name"
    Then I click on the "VPE Course Automation" "Build Empire My Learning Right Block Course name"
    And I switch to window with index "2"
    Then I should see the "AutoTest Created" "element"
    Then I should see the "AutoTestSender" "element"
    Then I should see the "AutoTest Assignment3" "element"
    Then I should see the "AutoTest Assignment2" "element"
    Then I click on the "AutoTest Created" "element"
    And I should see the "Hub VPE Assignemnt Activity Layout" element
    And I should see the "REPLY" "text contained in element"
    Then I click on the "REPLY" "text contained in element"
    And I upload "fileToUpload.txt" file to "Direct App Upload File" element
    And I set "AutomationScript" text to the "Type your response here" "Build Empire RFI Textarea text item"
    Then I click on the "SEND " "button"
    And I should see the "Hub VPE Sent Email Notification" message
    And I switch to window with index "1"
    When I execute "Log Out from Hub Student" reusable step
    And I should see the "Already registered? Log in" message
    When I am on "MD_COMMON_LINKS_TOTARALOGINURL" URL
    And I click on the "BPP Digital Log out button" button if "Totara Login Confirmation Popup window" "element is present"
    #Remove topic
    Then I execute "Login as Admin Totara" reusable step
    When I click on the "Totara Volodymyr Course" link by JS
    Then I am on "https://totara.staging.bppdigital.buildempire.app/course/management.php?categoryid=4&courseid=227" URL
    And I execute "arguments[0].click();" JS code for "View" "text contained in A element"
    And I click on the "Turn editing on" "BPP Digital Admin Value attribute button"
    And I should scroll to the "bottom" of the page
    Then I click on the "Totara Edit Topic Dropdown" element by JS
    Then I click on the "Totara Delete Topic Dropdown Option" element by JS
    And I wait for "1" seconds
    Then I execute "arguments[0].click();" JS code for "Delete" "Totara button"
    And I wait for "5" seconds
    And I should scroll to the "top" of the page