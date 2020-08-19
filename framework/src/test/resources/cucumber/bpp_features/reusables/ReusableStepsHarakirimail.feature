@Reusable
Feature: Harakirimail, Guerrillamail

  Scenario: Guerrillamail Mitigating Circustances Reject Validation
    Given I am on "https://www.guerrillamail.com/" URL
    Then I click on the "Guerilla Edit" button
    And I fill the "Guerilla Email Input" field with "EC_AUTO_EMAIL"
    Then I click on the "Guerilla Email Set" button
    And I wait for "10" seconds
    Given I am on "https://www.guerrillamail.com/" URL
    Then I click on the "Guerilla Edit" button
    And I fill the "Guerilla Email Input" field with "EC_AUTO_EMAIL"
    Then I click on the "Guerilla Email Set" button
    Then I click on the "Guerilla Your MC Outcome" link
    And I validate text "CONTAINS=Your Mitigating Circumstances application outcome" to be displayed for "Guerilla Email Header" element
    And I validate text "CONTAINS=Your application has been rejected" to be displayed for "Guerilla Email Body" element

  Scenario: Guerillamail Mitigating Circustances Status Validations
    Given I am on "https://www.guerrillamail.com/" URL
    Then I click on the "Guerilla Edit" button
    And I fill the "Guerilla Email Input" field with "EC_AUTO_EMAIL"
    Then I click on the "Guerilla Email Set" button
    And I wait for "10" seconds
    Given I am on "https://www.guerrillamail.com/" URL
    Then I click on the "Guerilla Edit" button
    And I fill the "Guerilla Email Input" field with "EC_AUTO_EMAIL"
    Then I click on the "Guerilla Email Set" button
    Then I click on the "Guerilla Validate Mitigating Circustances Submitted Link" link
    And I validate text "CONTAINS=Your Mitigating Circumstances has been submitted" to be displayed for "Guerilla Email Header" element
    Then I click on the "Guerilla Back To Inbox" button
    Then I click on the "Guerilla Your MC Are Now Reviewed" link
    And I validate text "CONTAINS=Your Mitigating Circumstances are now being reviewed" to be displayed for "Guerilla Email Header" element
    Then I click on the "Guerilla Back To Inbox" button
    Then I click on the "Guerilla Your MC Referred" link
    And I validate text "CONTAINS=Your Mitigating Circumstances application has been referred" to be displayed for "Guerilla Email Header" element
    Then I click on the "Guerilla Back To Inbox" button
    Then I click on the "Guerilla Your MC Progressing" link
    And I validate text "CONTAINS=Your Mitigating Circumstances is progressing" to be displayed for "Guerilla Email Header" element
    Then I click on the "Guerilla Back To Inbox" button
    Then I click on the "Guerilla Your MC Outcome" link
    And I validate text "CONTAINS=Your Mitigating Circumstances application outcome" to be displayed for "Guerilla Email Header" element
    Then I click on the "Guerilla Back To Inbox" button
    Then I click on the "Guerilla Your MC Accepted" link
    And I validate text "CONTAINS=Mitigating Circumstances application has been accepted" to be displayed for "Guerilla Email Header" element
    Then I click on the "Guerilla Back To Inbox" button

  Scenario: Guerillamail Reset Line Manager Password
    Given I am on "https://www.guerrillamail.com/" URL
    Then I click on the "Guerilla Edit" button
    And I fill the "Guerilla Email Input" field with "EC_LINEMANAGER_EMAIL"
    Then I click on the "Guerilla Email Set" button
    And I wait for "10" seconds
    Given I am on "https://www.guerrillamail.com/" URL
    Then I click on the "Guerilla Edit" button
    And I fill the "Guerilla Email Input" field with "EC_LINEMANAGER_EMAIL"
    Then I click on the "Guerilla Email Set" button
    And I wait for "10" seconds
    And Browser performs "Refresh" command
    And I wait for "2" seconds
    Then I click on the "Guerilla BPP Email" element
    Then I capture special data "Guerilla New Line Manager Email" as "EC_RESET_PASSWORD" variable

  Scenario: Harakirimail Validate Accepted Application Email
    Given I am on "https://www.guerrillamail.com/" URL
    Then I click on the "Guerilla Edit" button
    And I fill the "Guerilla Email Input" field with "EC_AUTO_EMAIL"
    Then I click on the "Guerilla Email Set" button
    And I wait for "10" seconds
    Given I am on "https://www.guerrillamail.com/" URL
    Then I click on the "Guerilla Edit" button
    And I fill the "Guerilla Email Input" field with "EC_AUTO_EMAIL"
    Then I click on the "Guerilla Email Set" button
    Then I click on the "Guerilla BPP Email" element
    And I validate text "Your application has been approved" to be displayed for "Guerilla Email Header" element