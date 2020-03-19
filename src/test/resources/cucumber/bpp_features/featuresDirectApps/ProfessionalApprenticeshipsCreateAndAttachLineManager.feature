@DirectApps @ProfessionalApprenticeships
Feature: Submit Professional Apprenticeship Applications With New Line Manager

  Background:
    Given I execute "Register New Student Account" reusable step

  @Positive @CreateLineManager #TC-1025
  Scenario: Professional Apprenticeships Create And Attach Line Manager
    When I execute "Create Professional Apprenticeships Application UAT Accounting Option Two" reusable step
    Given I execute "Complete Professional Apprenticeships UAT Accounting Assisstant" reusable step
    Then I execute "Submit Application Hub" reusable step
    And I execute "Log Out from Hub Student" reusable step
    And I execute "Log In to Hub as Admin" reusable step
    And I execute "Pre Approve Apprenticeships Application As Admin" reusable step
    And I execute "Create Automation Line Manager" reusable step
    And I execute "Log Out from Hub Admin" reusable step
    And I execute "Guerillamail Reset Line Manager Password" reusable step
    And I execute "Reset Line Manager Password" reusable step
    Given I am on "MD_COMMON_LINKS_BUILDEMPIREURL" URL
    And I set "EC_LINEMANAGER_EMAIL" text to the "Email" "Build Empire text field"
    And I set "Welcome2@#!Welc" text to the "Password" "Build Empire text field"
    Then I click on the "Direct App Login Button" button
    And I should see the "Direct App My Approvals Tab" element
    Then I execute "Line Manager Review Apprenticeships" reusable step
    And I execute "Log Out from Hub Student" reusable step
    Then I execute "Log In to Hub as Student" reusable step
    And I execute "Complete Professional Apprenticeships Application After Review" reusable step
    Then I execute "Submit Application Hub" reusable step
    And I execute "Log Out from Hub Student" reusable step
    And I execute "Log In to Hub as Admin" reusable step
    And I execute "Accept Application As Admin" reusable step
    And I execute "Log Out from Hub Admin" reusable step
    And I execute "Log In to Hub as Student" reusable step
    And I execute "Validate Successful Application As Student" reusable step
    And I execute "Log Out from Hub Student" reusable step