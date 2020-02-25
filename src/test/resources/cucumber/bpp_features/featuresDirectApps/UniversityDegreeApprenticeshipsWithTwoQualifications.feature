@DirectApps @UniversityDegreeApprenticeships
Feature: University Degree Apprenticeships

  @Positive @P1 @TwoQualifications
  Scenario: Create University Degree Apprenticeships Application With Two Qualifications
    Given I execute "Register New Student Account" reusable step
    When I execute "Create University Degree Apprenticeships Application" reusable step
    And I execute "Complete University Degree Apprenticeships Application Chartered Manager" reusable step with some additional steps
      |58|I click on the "Direct App Add Qualification" button|
      |59|I click on the "Direct App Country of Study Dropdown" button|
      |60|I click on the "United Kingdom" "Build Empire qualification option"|
      |61|I click on the "Direct App Education Type Degree Radiobutton" button|
      |62|I click on the "Direct App Qualification Title Dropdown" button|
      |63|I fill the "Direct App Education Search Field Dropdown" field with "Foundation degree"|
      |64|I click on the "Foundation degree" "Build Empire application type"|
      |65|I click on the "Direct App Subject Dropdown" button|
      |66|I fill the "Direct App Education Search Field Dropdown" field with "Applied Law (QCF)"|
      |67|I click on the "Applied Law (QCF)" "Build Empire application type"|
      |68|I click on the "Direct App Awarding Institution Dropdown" button|
      |69|I fill the "Direct App Education Search Field Dropdown" field with "Al-Risalah"|
      |70|I click on the "Al-Risalah" "Build Empire application type"|
      |71|I fill the "Direct App Year Completed" field with "11/11/2018"|
      |72|I press "MD_COMMON_KEYBOARD_ENTER" for "Direct App Year Completed"|
      |73|I fill the "Direct App Grade" field with "AutoGrade"|
      |74|I fill the "Direct App Examining Body" field with "AutoBody"|
      |75|I click on the "Direct App Add Assesment" button|
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