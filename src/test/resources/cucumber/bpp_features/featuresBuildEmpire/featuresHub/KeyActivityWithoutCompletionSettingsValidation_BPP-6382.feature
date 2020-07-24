@Hub @KeyActivities
Feature: Validate Key Activity Without Completion Settings On Topic Overview Page - BPP-6382
  As a user in the Hub,
  when a Key Activity, has been set without any completion settings,
  then I expect this to be reflected in the Topic Overview page within the Hub

  @Positive @Smoke #TC-2827
  Scenario: Validate Key Activity Without Completion Settings On Topic Overview Page
    When I execute "Register New Student Account" reusable step
    Then I shouldn't see the "My Learning" "Build Empire Navigation Menu elements"
    When I execute "Log Out from Hub Student" reusable step
    And I should see the "Already registered? Log in" message
    When I am on "MD_COMMON_LINKS_TOTARALOGINURL" URL
    And I click on the "BPP Digital Log out button" button if "Totara Login Confirmation Popup window" "element is present"
    Then I execute "Login as Admin Totara" reusable step
    When I click on the "Totara Volodymyr Course" link by JS
    And I click on the "Totara Left Menu Users link" link by JS
    And I click on the "Totara Enrolled Users button" button by JS
    And I wait for "3" seconds
    And I click on the "Totara Enrol User button" button by JS
    And I set "EC_AUTO_EMAIL" text to the element with ID "enrolusersearch" using JS
    And I click on the "Totara Enrol User Search button" button by JS
    And I click on the "Totara Enrol Button" button by JS
    And I click on the "Totara Finish Enroling Users button" button by JS
    Then I execute "Logout as Admin Totara" reusable step
    And I execute "Log In to Hub as Student" reusable step
    And I click on the "Mini Course for Volodymyr" "Build Empire Homepage My Learning Course"
    And I click on the "Topic 3" "Build Empire Course Topic"
    And I shouldn't see the "Direct App Course Topic Key Activity Completion Settings" element