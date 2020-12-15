@BuildEmpire @Hub @KeyActivities @Totara
Feature: Verify URL Feedback or Page Key Activity Complition Appearance In HUB - BPP-5483
  As a user in the Hub,
  when a URL, FEEDBACK or PAGE learning activity has been set as the Key Activity on my Topic Overview page,
  I expect it to have the following workflow

  @Positive @Smoke @URL #TC-2886
  Scenario: URL Feedback or Page Key Activity On Topic Overview Page Validation
    When I execute "Register New Student Account" reusable step
    Then I shouldn't see the "My Learning" "Build Empire Navigation Menu elements"
    And I execute "Logout Totara Workaround" reusable step
    #When I execute "Log Out from Hub Student" reusable step
    #And I should see the "Already registered? Log in" message
    #When I am on "MD_COMMON_LINKS_TOTARALOGINURL" URL
    #And I click on the "BPP Digital Log out button" button if "Totara Login Confirmation Popup window" "element is present"
    Then I execute "Login as Admin Totara" reusable step
    When I click on the "Totara Volodymyr Course" link by JS
    And I click on the "Totara Left Menu Users link" link by JS
    And I click on the "Totara Enrolled Users button" button by JS
    And I wait for "3" seconds
    And I click on the "Totara Enrol User button" button by JS
    And I set "EC_AUTO_EMAIL" text to the element with "Id" "enrolusersearch" using JS
    And I click on the "Totara Enrol User Search button" button by JS
    And I click on the "Totara Enrol Button" button by JS
    And I click on the "Totara Finish Enroling Users button" button by JS
    Then I execute "Logout as Admin Totara" reusable step
    And Browser deletes cookies
    And I execute "Log In to Hub as Student" reusable step
    And I click on the "Mini Course for Volodymyr" "Build Empire Homepage My Learning Course"
    And I click on the "URL" "Build Empire Course Topic"
    And I validate text "CONTAINS=You haven't attempted this assessment yet" to be displayed for "Direct App Topic Assessment Status Results" element
    And I click on the "Click to complete" "Build Empire button"
    And I wait for "3" seconds
    Then I switch to window with index "2"
    And I wait for "2" seconds
    When I switch to window with index "1"
    And Browser performs "REFRESH" command
    And I should see the "Completed" "element"
    And I click on the "/my/learning/course/12" "link address"
    And I click on the "Page" "Build Empire Course Topic"
    And I validate text "CONTAINS=You haven't attempted this assessment yet" to be displayed for "Direct App Topic Assessment Status Results" element
    And I click on the "Click to complete" "Build Empire button"
    And I wait for "4" seconds
    When I switch to window with index "1"
    And Browser performs "REFRESH" command
    Then I execute "Log In to Hub as Student" reusable step if "Already registered? Log in" "element is present"
    And I click on the "Mini Course for Volodymyr" "Build Empire Homepage My Learning Course" if "Click to complete" "element is absent"
    And I click on the "Page" "Build Empire Course Topic" if "Click to complete" "element is absent"
    And I should see the "Completed" "element"
    And I click on the "/my/learning/course/12" "link address"
    And I click on the "Feedback" "Build Empire Course Topic"
    And I validate text "CONTAINS=You haven't attempted this assessment yet" to be displayed for "Direct App Topic Assessment Status Results" element
    And I click on the "Click to complete" "Build Empire button"
    And I wait for "4" seconds
    Then I switch to window with index "2"
    And I click on the "Answer the questions..." element
    And I set "7" text to the "Rate us from 1 to 10 (1 - 10)" "Totara text field"
    And I click on the "Submit your answers" "Totara button"
    And I click on the "Continue" "Totara button"
    When I switch to window with index "1"
    And Browser performs "REFRESH" command
    And I should see the "Completed" "element"
    And I click on the "/my/learning/course/12" "link address"