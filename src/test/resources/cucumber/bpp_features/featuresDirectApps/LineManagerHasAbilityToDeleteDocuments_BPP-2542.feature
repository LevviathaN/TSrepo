@DirectApps
Feature: Line Manager has an Ability to Delete Documents that was Uploaded by Mistake

  Background:
    Given I execute "Register New Student Account" reusable step

  @Positive #TC-8143
  Scenario: Line Manager has an Ability to Delete Documents that was Uploaded by Mistake
    When I execute "Create University Degree Apprenticeships Application" reusable step
    And I execute "Complete University Degree Apprenticeships Application Chartered Manager" reusable step
    Then I execute "Submit Application Hub" reusable step
    And I execute "Log Out from Hub Student" reusable step
    And I execute "Log In to Hub as Admin" reusable step
    And I execute "Pre Approve Apprenticeships Application As Admin" reusable step
    And I execute "Attach Automation Line Manager Apprenticeships" reusable step replacing some steps
      |4|I fill the "Direct App Admin Attach Line Manger Text Field" field with "cecdcxmnyt@harakirimail.com"|
      |6|I click on the "Automation Line Manager 3" "Build Empire line manager option"|
    And I execute "Log Out from Hub Admin" reusable step
    Given I am on "MD_COMMON_LINKS_BUILDEMPIREURL" URL
    And I set "cecdcxmnyt@harakirimail.com" text to the "Email" "Build Empire text field"
    And I set "qwerty azerty1!" text to the "Password" "Build Empire text field"
    Then I click on the "Direct App Login Button" button
    Given I click on the "Direct App My Approvals Tab" link by JS
    And I click on the "Direct App Line Manager Review Application" button
    And I click on the "Line Manager Review" "Build Empire application section"
    Then I fill the "Direct App Details Text Area  Line Manager" field with "Automation"
    And I wait for "2" seconds
    And I click on the "Direct App Line Manager Agree To Support Amount" element
    And I click on the "Direct App Line Manager New Role" button
    And I click on the "Direct App Mark As Complete Checkbox" button
    And I click on the "Direct App Save Review And Back" button
    And I wait for "1" seconds
    And Browser performs "Refresh" command
    And I click on the "Application documents" "Build Empire application section"
    And I click on the "Download Application of" "Build Empire application section"
    Then I switch to window with index "0"
    Then I upload "application_pack_document.pdf" file to "Direct App Apprenticeships Upload Documents" element
    Then I upload "application_pack_document.pdf" file to "Direct App Upload Job Role Documents" element
    And I wait for "3" seconds
    Then I should see the "Application pack" "Build Empire Downloaded Application Documents Title name"
    Then I should see the "Application of Learning Agreement" "Build Empire Downloaded Application Documents Title name"
    And Browser performs "Refresh" command
    And I wait for "3" seconds
    And I click on the "Application pack" "Build Empire Delete Downloaded Application Document Title by name"
    And I click on the "Application of Learning Agreement" "Build Empire Delete Downloaded Application Document Title by name"
    And I wait for "3" seconds
    Then I shouldn't see the "Application pack" "Build Empire Downloaded Application Documents Title name"
    Then I shouldn't see the "Application of Learning Agreement" "Build Empire Downloaded Application Documents Title name"