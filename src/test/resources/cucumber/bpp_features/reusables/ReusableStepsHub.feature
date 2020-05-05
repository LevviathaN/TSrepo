@Reusable
Feature: Hub, Totara

  Scenario: Login as Admin Totara
    Given I am on "MD_COMMON_LINKS_TOTARALOGINURL" URL
    And I fill the "Salesforce Username" field with "MD_COMMON_CREDENTIALS_TOTARAADMINUSER"
    And I fill the "Salesforce Password" field with "MD_COMMON_CREDENTIALS_TOTARAADMINPASSWORD"
    Then I click on the "Totara Login" button by JS

  Scenario: Logout as Admin Totara
    Then I click on the "Totara Account Expandable Menu" element by JS
    Then I click on the "Totara Logout Link" button by JS
    And I wait for "2" seconds
    And I execute "document.getElementsByClassName('logininfo')[0].innerText;" JS code

  Scenario: Enrol Student to ICAEW Course
    When I am on "MD_COMMON_LINKS_TOTARALOGINURL" URL
    And I click on the "BPP Digital Log out button" button if "Totara Login Confirmation Popup window" "element is present"
    Then I execute "Login as Admin Totara" reusable step
    When I click on the "Totara ICAEW Course" link by JS
    And I click on the "Totara Left Menu Users link" link by JS
    And I click on the "Totara Enrolled Users button" button by JS
    And I wait for "3" seconds
    And I click on the "Totara Enrol User button" button by JS
    And I set "EC_AUTO_EMAIL" text to the element with ID "enrolusersearch" using JS
    And I click on the "Totara Enrol User Search button" button by JS
    And I click on the "Totara Enrol Button" button by JS
    And I click on the "Totara Finish Enroling Users button" button by JS
    Then I execute "Logout as Admin Totara" reusable step