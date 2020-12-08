@BuildEmpire @Hub @Smoke
Feature: BPP University Hub My Learning
  As a learner
  I need an easy way to access the My Learning page from the navigation of The Hub

  @Positive @MyLearning @BET #TC-1119 TC-1121
  Scenario: HUB - My Learning
    #Register New Student
    When I execute "Register New Student Account" reusable step
    And I execute "Nada Student Email Verification" reusable step
    #Student Log out
    When I execute "Log In to Hub as Student" reusable step
    And I wait for "3" seconds
    When I execute "Log Out from Hub Student" reusable step
    #Admin Login With Walkaround
    And I execute "Logout Totara Workaround" reusable step
    Then I execute "Login as Admin Totara" reusable step
    #Enrol to a course
    When I click on the "Totara ICAEW Course" link by JS
    And I click on the "Totara Left Menu Users link" link by JS
    And I click on the "Totara Enrolled Users button" button by JS
    And I wait for "3" seconds
    And I click on the "Totara Enrol User button" button by JS
    And I set "EC_AUTO_EMAIL" text to the element with ID "enrolusersearch" using JS
    And I click on the "Totara Enrol User Search button" button by JS
    And I click on the "Totara Enrol Button" button by JS
    And I click on the "Totara Finish Enroling Users button" button by JS
    When I click on the "Dashboard" "text contained in SPAN element"
    When I click on the "Totara ICAEW Course" link by JS
    And I click on the "Turn editing on" "BPP Digital Admin Value attribute button" by JS
    And I wait for "6" seconds
    And I click on the "Totara Edit Course Setting link" element by JS
    And I select "KW_AUTO_SELECT" from "Totara expiry Course Date Day dropdown" element
    And I select "KW_AUTO_SELECT" from "Totara expiry Course Date Month dropdown" element
    And I select "KW_AUTO_SELECT" from "Totara expiry Course Date Year dropdown" element
    And I click on the "Save and display" "Totara button" by JS
    And I wait for "6" seconds
    And I click on the "Totara Edit Course Setting link" element by JS
    And I capture text data "Totara expiry Course Date Day selected" as "EC_DAY" variable
    And I capture text data "Totara expiry Course Date Month selected" as "EC_MONTH" variable
    And I capture text data "Totara expiry Course Date Year selected" as "EC_YEAR" variable
    And I remember "<EC_DAY> " text as "EC_DAY_SPACE" variable
    And I remember "<EC_MONTH> " text as "EC_MONTH_SPACE" variable
    #Admin Logout
    Then I execute "Logout as Admin Totara" reusable step
    And Browser deletes cookies
    #Login As student
    When I execute "Log In to Hub as Student" reusable step
    #My Learning Validation
    Then I should see the "My Learning" "Build Empire Navigation Menu elements"
    #Student Validate End Date
    And I click on the "My Learning" "Build Empire Navigation Menu elements"
    And I click on the "BPP Digital Student My Learning Page Open First Course Sticker" element
    And I should see the "<EC_DAY_SPACE><EC_MONTH_SPACE><EC_YEAR>" button