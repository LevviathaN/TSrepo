@Hub
Feature: BPP University Hub My Learning
  As a learner
  I need an easy way to access the My Learning page from the navigation of The Hub

  @Positive #TC-1119 TC-1121
  Scenario: HUB - My Learning
    #Register New Student
    When I remember "Auto[EMAIL-HARAKIRI]" text as "EC_VARIABLE" variable
    When I execute "Register New Student Account" reusable step replacing some steps
      |5|I set "EC_AUTO_EMAIL" text to the "Email" "Build Empire text field"|
    #Verify Left panel
    Then I should see the "Home" "Build Empire Navigation Menu elements"
    Then I should see the "My Applications" "Build Empire Navigation Menu elements"
    Then I should see the "Help & Support" "Build Empire Navigation Menu elements"
    Then Attribute "alt" of "Direct App Left Navigation Panel menu logo image" should have value "BPP University"
    Then I shouldn't see the "My Learning" "Build Empire Navigation Menu elements"
    #Student Log out
    When I execute "Log Out from Hub Student" reusable step
    #Admin Login With Walkaround
    When I am on "MD_COMMON_LINKS_TOTARALOGINURL" URL
    And I click on the "BPP Digital Log out button" button if "Totara Login Confirmation Popup window" "element is present"
    Then I execute "Login as Admin Totara" reusable step
    #Enrol to a course
    When I click on the "Totara ICAEW Course" link by JS
    And I click on the "Totara Left Menu Users link" link by JS
    And I click on the "Totara Enrolled Users button" button by JS
    And I wait for "3" seconds
    And I click on the "Totara Enrol User button" button by JS
    And I execute "document.getElementById('enrolusersearch').setAttribute('value', 'EC_VARIABLE')" JS code
    And I click on the "Totara Enrol User Search button" button by JS
    And I click on the "Totara Enrol Button" button by JS
    And I click on the "Totara Finish Enroling Users button" button by JS
    #Admin Logout
    Then I execute "Logout as Admin Totara" reusable step
    #Login As student
    When I execute "Log In to Hub as Student" reusable step
    #My Learning Validation
    Then I should see the "My Learning" "Build Empire Navigation Menu elements"
    #Student Log out
    When I execute "Log Out from Hub Student" reusable step
    #Login As Training Manager
    When I execute "Log In to Hub as Student" reusable step replacing some steps
      |2|I set "02917uk7sr@harakirimail.com" text to the "Email" "Build Empire text field"|
      |3|I set "qwerty azerty1!" text to the "Password" "Build Empire text field"|
    #My Approval Validation
    Then I should see the "My Approvals" "Build Empire Navigation Menu elements"