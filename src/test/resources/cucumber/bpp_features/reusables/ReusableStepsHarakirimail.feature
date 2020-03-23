@Reusable
Feature: Harakirimail, Guerrillamail

  Scenario: Harakirimail Mitigating Circustances Reject Validation
    Given I am on "https://harakirimail.com/" URL
    And I fill the "Harakirimail Inbox Name" field with "EC_AUTO_EMAIL"
    Then I click on the "Harakirimail Get Inbox" button
    Then I click on the "Harakirimail Application Outcome Letter" button
    And I validate text "CONTAINS=Your application has been rejected" to be displayed for "Harakirimail Validate Letter Body" element
    Then I click on the "Harakirimail Back To Inbox" button

  Scenario: Harakirimail Mitigating Circustances Status Validations
    Given I am on "https://harakirimail.com/" URL
    And I fill the "Harakirimail Inbox Name" field with "EC_AUTO_EMAIL"
    Then I click on the "Harakirimail Get Inbox" button
    And I click on the "Harakirimail Validate Mitigating Circustances Submitted Link" button
    And I validate text "Your Mitigating Circumstances has been submitted" to be displayed for "Harakirimail Validate Mitigating Header" element
    Then I click on the "Harakirimail Back To Inbox" button
    And I click on the "Harakirimail Your MC Are Now Reviewed" element
    And I validate text "Your Mitigating Circumstances are now being reviewed" to be displayed for "Harakirimail Validate Mitigating Header" element
    Then I click on the "Harakirimail Back To Inbox" button
    And I click on the "Harakirimail Your MC Referred" element
    And I validate text "Your Mitigating Circumstances application has been referred" to be displayed for "Harakirimail Validate Mitigating Header" element
    Then I click on the "Harakirimail Back To Inbox" button
    And I click on the "Harakirimail Your MC Progressing" element
    And I validate text "Your Mitigating Circumstances is progressing" to be displayed for "Harakirimail Validate Mitigating Header" element
    Then I click on the "Harakirimail Back To Inbox" button
    And I click on the "Harakirimail Your MC Outcome" element
    And I validate text "CONTAINS=Reason Part Accept" to be displayed for "Harakirimail Validate Letter Body" element
    Then I click on the "Harakirimail Back To Inbox" button
    And I click on the "Harakirimail Your MC Accepted" element
    And I validate text "CONTAINS=Your application has been granted" to be displayed for "Harakirimail Validate Letter Body" element
    And I validate text "CONTAINS=Reason Accept" to be displayed for "Harakirimail Validate Letter Body" element
    Then I click on the "Harakirimail Back To Inbox" button
    And I click on the "Harakirimail Your MC Outcome Second" element
    And I validate text "CONTAINS=Your application has been rejected" to be displayed for "Harakirimail Validate Letter Body" element
    And I validate text "CONTAINS=Reason Reject" to be displayed for "Harakirimail Validate Letter Body" element

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
    Given I am on "https://harakirimail.com/" URL
    And I fill the "Harakirimail Inbox Name" field with "EC_AUTO_EMAIL"
    Then I click on the "Harakirimail Get Inbox" button
    Then I click on the "Harakirimail First Email" button
    And I validate text "Your application has been approved" to be displayed for "Harakirimail Validate Mitigating Header" element