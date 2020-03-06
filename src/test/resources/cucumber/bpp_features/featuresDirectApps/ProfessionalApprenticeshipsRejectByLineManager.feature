@DirectApps @ProfessionalApprenticeships
Feature: Reject Professional Apprenticeship Applications

  @Negative @RejectByLineManager
  Scenario: Reject Professional Apprenticeship Applications By Line Manager
    Given I execute "Register New Student Account" reusable step
    When I execute "Create Professional Apprenticeships Application UAT Accounting Option Two" reusable step
    Given I execute "Complete Professional Apprenticeships UAT Accounting Assisstant" reusable step
    Then I execute "Submit Application Hub" reusable step
    And I execute "Log Out from Hub Student" reusable step
    And I execute "Log In to Hub as Admin" reusable step
    And I execute "Pre Approve Apprenticeships Application As Admin" reusable step
    And I execute "Attach Automation Line Manager Apprenticeships" reusable step replacing some steps
      |4|I fill the "Direct App Admin Attach Line Manger Text Field" field with "chumashimagrotis@harakirimail.com"|
      |6|I click on the "Automation LineManagerThree" "Build Empire line manager option"|
    And I execute "Log Out from Hub Admin" reusable step
    Given I am on "MD_COMMON_LINKS_BUILDEMPIREURL" URL
    And I set "chumashimagrotis@harakirimail.com" text to the "Email" "Build Empire text field"
    And I set "Test password 2018" text to the "Password" "Build Empire text field"
    Then I click on the "Direct App Login Button" button
    And I execute "Line Manager Review And Reject Professional Apprenticeships" reusable step
    And I execute "Log Out from Hub Student" reusable step
    Then I execute "Log In to Hub as Student" reusable step
    And I click on the "Direct App My Applications Left Menu Link" link by JS
    Then I validate text "" to be displayed for "Direct App Validate As Student Application Accepted" element
    And I execute "Log Out from Hub Student" reusable step
    And I execute "Log In to Hub as Admin" reusable step
    And I execute "Application Rejected by Line Manager Validation" reusable step