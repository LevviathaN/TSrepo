@DirectApps @ChannelIslands
Feature: Approve Channel Islands Application

  @Positive #TC-1268 TC-1269
  Scenario: Approve Channel Islands Application as Training Manger
    Given I execute "Register New Student Account" reusable step replacing some steps
    |1|I am on "MD_COMMON_LINKS_BUILDEMPIRECHANNELISLANDURL" URL|
    When I execute "Create Channel Islands Mk2 Application" reusable step
    And I execute "Complete Channel Islands Mk2 Application" reusable step
    Then I execute "Submit Application Hub" reusable step
    And I execute "Log Out from Hub Student" reusable step
    Then I execute "Guerillamail Reset Line Manager Password" reusable step
    Then I execute "Reset Line Manager Password" reusable step
    Given I am on "MD_COMMON_LINKS_BUILDEMPIREURL" URL
    And I set "EC_LINEMANAGER_EMAIL" text to the "Email" "Build Empire text field"
    And I set "Welcome2@#!Welc" text to the "Password" "Build Empire text field"
    Then I click on the "Direct App Login Button" button
    And I should see the "Direct App My Approvals Tab" element
    Then I execute "Approve Channel Isalnds Application as Training Manager" reusable step
    Given I am on "https://harakirimail.com/" URL
    And I fill the "Harakirimail Inbox Name" field with "EC_AUTO_EMAIL"
    Then I click on the "Harakirimail Get Inbox" button
    Then I click on the "Harakirimail First Email" button
    And I validate text "Your application has been approved" to be displayed for "Harakirimail Validate Mitigating Header" element

