@DirectApps @ProfessionalApprenticeships
Feature: Submit Professional Apprenticeship Applications

  Background:
    Given I execute "Register New Student Account" reusable step

  @Positive @UATAccounting #TC-1021 #TC-1022
  Scenario: Submit Professional Apprenticeship UAT Acconting
    When I execute "Create Professional Apprenticeships Application UAT Accounting Option Two" reusable step
    Given I execute "Complete Professional Apprenticeships UAT Accounting Assisstant" reusable step
    Then I execute "Submit Application Hub" reusable step
    And I execute "Log Out from Hub Student" reusable step
    And I execute "Log In to Hub as Admin" reusable step
    And I execute "Pre Approve Apprenticeships Application As Admin" reusable step
    And I execute "Attach Automation Line Manager Apprenticeships" reusable step
    And I execute "Log Out from Hub Admin" reusable step
    Given I am on "MD_COMMON_LINKS_BUILDEMPIREURL" URL
    And I set "02917uk7sr@harakirimail.com" text to the "Email" "Build Empire text field"
    And I set "qwerty azerty1!" text to the "Password" "Build Empire text field"
    Then I click on the "Direct App Login Button" button
    And I should see the "Direct App My Applications Left Menu Link" element
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