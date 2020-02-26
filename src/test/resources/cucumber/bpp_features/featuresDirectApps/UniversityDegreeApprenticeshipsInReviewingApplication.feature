@DirectApps @UniversityDegreeApprenticeships
Feature: University Degree Apprenticeships

  @Positive @P1 @InReviewingApplication
  Scenario: University Degree Apprenticeships in Reviewing Application
    Given I execute "Register New Student Account" reusable step
    When I execute "Create University Degree Apprenticeships Application" reusable step
    And I execute "Complete University Degree Apprenticeships Application Chartered Manager" reusable step
    Then I execute "Submit Application Hub" reusable step
    And I execute "Log Out from Hub Student" reusable step
    And I execute "Log In to Hub as Admin" reusable step
    And I execute "Incomplete University Degree Apprenticeships Application As Admin" reusable step
    And I execute "Log Out from Hub Admin" reusable step
    Then I execute "Log In to Hub as Student" reusable step
    And I click on the "Direct App My Applications Left Menu Link" link by JS
    And I click on the "Amend application" "Build Empire button"
    And I click on the "Direct App Reopen Application Button" link by JS
    And I execute "Complete University Degree Apprenticeships Application With Existing Student" reusable step replacing some steps
      |1|I click on the "Go to Application" "Build Empire link"|
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
    Then I execute "Line Manager Review University Degree Apprenticeships Scotland" reusable step
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