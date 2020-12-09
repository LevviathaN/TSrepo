@Mobile
Feature: BPP University Hub My Learning Course Expiry Date
  As a learner, at a glance, I'd like to be able to see when the access to my course expires

  @Positive @BET #TC-718
  Scenario: HUB - My Learning Course Expiry Date Mobile
    #Admin Totara Log in
    Then I execute "Login as Admin Totara" reusable step replacing some steps
    |5|I click on the "Direct App Login Button" button by JS|
    #Enrol to a course
    And I execute "arguments[0].click()" JS code for "ICAEW Accounting Full" "BPP Digital Student Applications Menu link"
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
    Given I execute "Log In to Hub as Student" reusable step replacing some steps
      |2|I set "AutoMacyHood3654@getnada.com" text to the "Email" "Build Empire text field"|
      |3|I set "A@polloGlobal2020" text to the "Password" "Build Empire text field"|
    #My Learning Validation
    Then I should see the "My Learning" "Build Empire Navigation Menu elements"
    #Student Validate End Date
    And I click on the "My Learning" "Build Empire Navigation Menu elements"
    And I click on the "BPP Digital Student My Learning Page Open First Course Sticker" element
    And I should see the "<EC_DAY_SPACE><EC_MONTH_SPACE><EC_YEAR>" button