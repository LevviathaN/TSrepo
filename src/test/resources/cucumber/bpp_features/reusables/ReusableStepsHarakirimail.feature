@Reusable
Feature: Harakirimail

  Scenario: Harakirimail Mitigating Circustances Reject  Validation
    Given I am on "https://harakirimail.com/" URL
    And I fill the "Harakirimail Inbox Name" field with "EC_EMAIL_AUTO"
    Then I click on the "Harakirimail Get Inbox" button
    Then I click on the "Harakirimail Application Outcome Letter" button
    And I validate text "CONTAINS=Your application has been rejected" to be displayed for "Harakirimail Validate Letter Body" element
    Then I click on the "Harakirimail Back To Inbox" button