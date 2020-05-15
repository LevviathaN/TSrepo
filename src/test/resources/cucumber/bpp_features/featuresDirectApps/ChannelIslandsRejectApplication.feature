@DirectApps @ChannelIslands
Feature: Reject Channel Islands Application

  @Negative #TC-1270
  Scenario: Reject Channel Islands Application as Training Manager
    Given I execute "Register New Student Account" reusable step replacing some steps
      | 1 | I am on "MD_COMMON_LINKS_BUILDEMPIRECHANNELISLANDURL" URL |
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
    Then I execute "Reject Channel Isalnds Application as Training Manager" reusable step
    And I execute "Harakirimail Validate Accepted Application Email" reusable step replacing some steps
      | 11 | I validate text "Your application has been rejected" to be displayed for "Guerilla Email Header" element |

  @Negative
  Scenario: Reject Channel Islands Application as Training Manager on Review Application Details Page
    Given I execute "Register New Student Account" reusable step replacing some steps
      | 1 | I am on "MD_COMMON_LINKS_BUILDEMPIRECHANNELISLANDURL" URL |
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
    And I click on the "Direct App My Approvals Tab" button by JS
    And I click on the "Direct App Channel Islands Training Manager Review Application Details" button
    And I click on the "Reject" "button"
    And I fill the "Direct App Channel Islands Training Manager Reason" field with "Automation Testing"
    And I click on the "Direct App Channel Islands Training Manager OK" button
    And I wait for "1" seconds
    And I execute "Harakirimail Validate Accepted Application Email" reusable step replacing some steps
      | 11 | I validate text "Your application has been rejected" to be displayed for "Guerilla Email Header" element |