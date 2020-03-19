@Reusable
Feature: Harakirimail

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