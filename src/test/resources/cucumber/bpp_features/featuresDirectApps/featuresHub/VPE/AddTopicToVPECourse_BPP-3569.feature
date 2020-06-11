@Hub @VPE
Feature: The Hub - Law School - Topics / Top Level Navigation
  As an admin when I create a Topic in Totara, I expect this to appear in top navigation menu within VPE

  @Positive @AddTopic #TC-2081, TC-2076
  Scenario:The Hub - Law School - Topics Top Level Navigation
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
    Then I click on the "AutoTest Created" "element"
    And I should see the "Hub VPE Assignemnt Activity Layout" element
    And I should see the "REPLY" "text contained in element"
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
    Then I execute "Logout as Admin Totara" reusable step