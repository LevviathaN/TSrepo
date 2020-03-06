@DirectApps @ProfessionalApprenticeships
Feature: Professional Apprenticeship Applications

  @Positive @P1 @FullApplicationIncomplete
  Scenario: Create Professional Apprenticeship Applications Full Application Incomplete
    Given I execute "Register New Student Account" reusable step
    When I execute "Create Professional Apprenticeships Application UAT Accounting Option Two" reusable step
    Given I execute "Complete Professional Apprenticeships UAT Accounting Assisstant" reusable step
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
    Then I execute "Line Manager Review Apprenticeships" reusable step
    And I execute "Log Out from Hub Student" reusable step
    Then I execute "Log In to Hub as Student" reusable step
    And I execute "Complete Professional Apprenticeships Application After Review" reusable step
    Then I execute "Submit Application Hub" reusable step
    And I execute "Log Out from Hub Student" reusable step
    And I execute "Log In to Hub as Admin" reusable step
    And I execute "Incomplete University Degree Apprenticeships Application As Admin" reusable step
    #And I execute "Harakirimail Mitigating Circustances Reject Validation" reusable step replacing some steps
      #|5|I validate text "CONTAINS=Unfortunately your application for Chartered Manager Degree Apprenticeship is incomplete. " to be displayed for "Harakirimail Validate Letter Body" element|

