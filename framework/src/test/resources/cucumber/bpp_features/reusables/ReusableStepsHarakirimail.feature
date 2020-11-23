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

  Scenario: Maildrop Mitigating Circustances Reject Validation
    Given I am on "https://maildrop.cc/" URL
    And I fill the "Maildrop Email Field" field with "[SUBSTRING(EC_AUTO_EMAIL,0,-12)]"
    And I wait for "10" seconds
    Then I click on the "Maildrop View Inbox Button" button
    And I wait for "50" seconds
    And I click on the "Reload" button
    And I wait for "40" seconds
    And I click on the "Reload" button
    And I wait for "10" seconds
    And I should see the "Your Mitigating Circumstances application outcome" element

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

  Scenario: Maildrop Mitigating Circustances Status Validations
    Given I am on "https://maildrop.cc/" URL
    And I fill the "Maildrop Email Field" field with "[SUBSTRING(EC_AUTO_EMAIL,0,-12)]"
    And I wait for "10" seconds
    Then I click on the "Maildrop View Inbox Button" button
    And I wait for "50" seconds
    And I click on the "Reload" button
    And I wait for "40" seconds
    And I click on the "Reload" button
    And I wait for "10" seconds
    And I should see the "Your Mitigating Circumstances application outcome" "Maildrop Specific BPP Email"
    And I wait for "2" seconds
    And I should see the "Your Mitigating Circumstances has been submitted" "Maildrop Specific BPP Email"
    And I wait for "2" seconds
    And I should see the "Your Mitigating Circumstances are now being reviewed" "Maildrop Specific BPP Email"
    And I wait for "2" seconds
    And I should see the "Your Mitigating Circumstances application has been referred" "Maildrop Specific BPP Email"
    And I wait for "2" seconds
    And I should see the "Your Mitigating Circumstances is progressing" "Maildrop Specific BPP Email"
    And I wait for "2" seconds
    And I should see the "Mitigating Circumstances application has been accepted" "Maildrop Specific BPP Email"

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
    Then I capture a part of "Guerilla New Line Manager Email" element text by "https:\/\/bpp-stage.eu.auth0.com\/lo\/reset.+[aA-zZ0-9]\#" regex and save as "EC_RESET_PASSWORD" variable

  Scenario: Nada Reset Line Manager Password
    Given I am on "https://getnada.com/" URL
    And I click on the "Nada Add More Inboxes Button" button
    And I wait for "5" seconds
    And I fill the "Nada User Name Field" field with "[SUBSTRING(EC_LINEMANAGER_EMAIL,0,-12)]"
    Then I select "@getnada.com" from "Nada Domain Select" element
    And I wait for "10" seconds
    And I click on the "Add now!" "exact element"
    And I wait for "2" seconds
    Then I click on the "Nada BPP Email" element by JS
    Then I capture a part of "Nada Email Body" element text by "https:\/\/bpp-stage.eu.auth0.com\/lo\/reset.+[aA-zZ0-9]\#" regex and save as "EC_RESET_PASSWORD" variable

  Scenario: Maildrop Reset Line Manager Password
    Given I am on "https://maildrop.cc/" URL
    And I fill the "Maildrop Email Field" field with "[SUBSTRING(EC_LINEMANAGER_EMAIL,0,-12)]"
    And I wait for "10" seconds
    Then I click on the "Maildrop View Inbox Button" button
    And I wait for "50" seconds
    And I click on the "Reload" button
    And I wait for "40" seconds
    And Browser performs "REFRESH" command
    And I wait for "60" seconds
    And I click on the "Reload" button
    And I wait for "10" seconds
    And I click on the "BPP <no-reply@staging.bppdigital.buildempire.co.uk>" element
    And I wait for "2" seconds
    Then I capture a part of "Maildrop Email Body Framed" element text by "https:\/\/bpp-stage.eu.auth0.com\/lo\/reset.+[aA-zZ0-9]\#" regex and save as "EC_RESET_PASSWORD" variable

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

  Scenario: Maildrop Validate Accepted Application Email
    Given I am on "https://maildrop.cc/" URL
    And I fill the "Maildrop Email Field" field with "[SUBSTRING(EC_AUTO_EMAIL,0,-12)]"
    And I wait for "10" seconds
    Then I click on the "Maildrop View Inbox Button" button
    And I wait for "50" seconds
    And I click on the "Reload" button
    And I wait for "40" seconds
    And I click on the "Reload" button
    And I wait for "10" seconds
    And I should see the "Your application has been approved" element

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
    And I click on the "Harakirimail Validate Mitigating Circumstances Submitted Link" button
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

  Scenario: Harakirimail Reset Line Manager Password
    Given I am on "https://harakirimail.com/" URL
    Then I fill the "Harakirimail Inbox Name" field with "EC_LINEMANAGER_EMAIL"
    Then I click on the "Harakirimail Get Inbox" element
    Then I click on the "Harakirimail First Email" element
    Then I capture special data "Harakirimail First Email Body" as "EC_RESET_PASSWORD" variable