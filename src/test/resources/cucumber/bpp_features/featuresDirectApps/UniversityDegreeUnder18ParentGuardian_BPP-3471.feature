@DirectApps @UniversityDegreeApprenticeships
Feature: As a student who, is under 18, I expect to provide more information about my Parent/Legal Guardian

  Background:
    Given I execute "Register New Student Account" reusable step

  @Under18 @ParentGuardian #TC-1734, TC-1757, TC-1759
  Scenario: As a student who, is under 18, I expect to provide more information about my Parent/Legal Guardian
    When I execute "Create University Degree Apprenticeships Application" reusable step

    Given I click on the "Direct App Start Application" button
    And I click on the "Eligibility criteria" "Build Empire application section"
    And I click on the "Are you aged over 15" "Build Empire bespoke Scotland eligibility"
    And I click on the "Do you have a husband, wife, civil partner and child of any of the above?" "Build Empire bespoke Scotland eligibility"
    And I click on the "Direct App Right To Work in England?" element
    And I click on the "Direct App Do You Spend 50 Percent" element
    And I click on the "Direct App Bespoke Enrolled On Another Apprenticeship No" element
    And I click on the "Direct App Are You In Employment" element
    And I click on the "Direct App Are You Working At Least 16 Hours" element
    And I click on the "Are you applying for a programme" "Build Empire bespoke Scotland eligibility"
    And I click on the "Do you confirm that your job is a new job role relevant to the career development" "Build Empire bespoke Scotland eligibility"
    And I click on the "Do you confirm that you have discussed" "Build Empire bespoke Scotland eligibility"
    And I click on the "Direct App Next" button
    And I click on the "Title" "Build Empire dropdown"
    And I click on the "Mr" "Build Empire dropdown option"
    And I set "EC_AUTO_FIRSTNAME" text to the "Forenames" "Build Empire text field by title"
    And I set "EC_AUTO_LASTNAME" text to the "Family name" "Build Empire text field by title"
    And I set "12345" text to the "National insurance number" "Build Empire text field by title"
    And I set "[DOB-17]" text to the "Date of Birth" "Build Empire text field by title"
    And I press "MD_COMMON_KEYBOARD_ENTER" for "Direct App DOB"
    And I set "EC_PHONE" text to the "Phone number" "Build Empire text field by title"
    And I set "Address 1" text to the "Address Line 1*" "Build Empire text field"
    And I set "Address 2" text to the "Address Line 2" "Build Empire text field"
    And I set "Address 3" text to the "Address Line 3" "Build Empire text field"
    And I set "80004" text to the "Postcode" "Build Empire text field by title"
    And I set "London" text to the "Town/City*" "Build Empire text field"
    And I click on the "Country*" "Build Empire dropdown"
    And I click on the "Afghanistan" "Build Empire dropdown option"
    #Parent/Legal Guardian Information
    And I validate text "CONTAINS=If you are under 18 years of age at the point you are accepted" to be displayed for "Direct App If You Are Under 18 Note" element
    And I set "Autotest" text to the "Name of Parent/Legal" "Build Empire text field by title"
    Then I remember "email11@mail.com" text as "EC_GUARDIAN_EMAIL" variable
    And I set "EC_GUARDIAN_EMAIL" text to the "Email Address of Parent/Legal" "Build Empire text field by title"
    And I set "[PHONE-0913]" text to the "Phone Number of Parent/Legal" "Build Empire text field by title"
    And I set "JustTest" text to the "What is your relationship with this person?" "Build Empire text field by title"
    And I click on the "Direct App Mark As Complete Checkbox" button
    And I click on the "Direct App Next" button
    And I click on the "Direct App Additional Info Aged 19" element
    And I click on the "employment status?:" "Build Empire nationality dropdown"
    And I click on the "I am a graduate (This is my first professional role)" "Build Empire nationality option"
    And I click on the "Direct App Mark As Complete Checkbox" button
    And I click on the "Direct App Next" button
    And I click on the "Diret App Gender Dropdown" element
    And I click on the "Diret App Gender Not To Say" element
    And I click on the "Diret App Gender At Birth Dropdown" element
    And I click on the "Diret App Gender At Birth Yes" element
    And I click on the "Diret App Ethnicity Dropdown" element
    And I click on the "Diret App Ethnicity White And Black African" element
    And I click on the "Diret App Religion Dropdown" element
    And I click on the "Diret App Religion Chrisitan" element
    And I click on the "Diret App Sexual Orientation Dropdown" element
    And I click on the "Diret App Sexual Orientation Heterosexual" element
    And I click on the "Diret App Learning Disability No" element
    And I click on the "Direct App Mark As Complete Checkbox" button
    And I click on the "Direct App Next" button
    And I click on the "Country of birth" "Build Empire nationality dropdown"
    And I click on the "Albania" "Build Empire nationality option"
    And I click on the "Country of nationality" "Build Empire nationality dropdown"
    And I click on the "United Kingdom" "Build Empire nationality option"
    And I click on the "Country of domicile/permanent residency" "Build Empire nationality dropdown"
    And I click on the "Aruba" "Build Empire nationality option"
    And I click on the "Is English your first language?" "Build Empire nationality dropdown"
    And I click on the "Yes" "Build Empire nationality option"
    And I click on the "Direct App Nationality No Visa" element
    And I click on the "Direct App Mark As Complete Checkbox" button
    And I click on the "Direct App Next" button
    And I click on the "Direct App Add Qualification" button
    And I click on the "Direct App Country of Study Dropdown" button
    And I click on the "United Kingdom" "Build Empire qualification option"
    And I click on the "Direct App Education Type Degree Radiobutton" button
    And I click on the "Direct App Qualification Title Dropdown" button
    And I fill the "Direct App Education Search Field Dropdown" field with "Foundation degree"
    Then I click on the "Foundation degree" "Build Empire application type"
    And I click on the "Direct App Subject Dropdown" button
    And I fill the "Direct App Education Search Field Dropdown" field with "Applied Law (QCF)"
    Then I click on the "Applied Law (QCF)" "Build Empire application type"
    And I click on the "Direct App Awarding Institution Dropdown" button
    And I fill the "Direct App Education Search Field Dropdown" field with "Zaskin College"
    Then I click on the "Zaskin College" "Build Empire application type"
    And I fill the "Direct App Year Completed" field with "11/11/2018"
    And I press "MD_COMMON_KEYBOARD_ENTER" for "Direct App Year Completed"
    And I fill the "Direct App Grade" field with "AutoGrade"
    And I fill the "Direct App Examining Body" field with "AutoBody"
    And I click on the "Direct App Add Assesment" button
    And I click on the "Direct App Mark As Complete Checkbox" button
    And I click on the "Direct App Next" button
    And I click on the "Do you have any criminal" "Build Empire radiobutton no"
    And I click on the "Direct App Mark As Complete Checkbox" button
    And I click on the "Direct App Next" button
    And I fill the "Direct App Job Title" field with "Job Title"
    And I set "32" text to the "contracted working hours?" "Build Empire employment element"
    And I set "Manager Name" text to the "Manager First Name" "Build Empire employment element"
    And I set "Manager Lastname" text to the "Manager Last Name" "Build Empire employment element"
    And I set "AutoTitle" text to the "Manager Job Title" "Build Empire employment element"
    And I set "automation11@mail.com" text to the "Manager Email" "Build Empire employment element"
    And I fill the "Direct App Line Manager Telephone" field with "0913<KW_AUTO_RANDOMNUMBER|#######>"
    And I click on the "Do you know how to raise" "Build Empire radiobutton yes"
    And I set "Address 1" text to the "Address Line 1" "Build Empire employment element"
    And I set "London" text to the "Company/Employer City" "Build Empire employment element"
    And I click on the "Direct App Company Country Dropdown" element
    And I click on the "Direct App Company Country Dropdown UK Option" element
    And I set "12345" text to the "Postcode" "Build Empire employment element"
    And I click on the "Direct App Company Health And Safety" element
    And I fill the "Direct App When You Start At Company" field with "05/05/2018"
    And I press "MD_COMMON_KEYBOARD_ENTER" for "Direct App When You Start At Company"
    And I click on the "Direct App Mark As Complete Checkbox" button
    And I click on the "Direct App Next" button
    And I click on the "Direct App Privacy Notice By Email" button
    And I click on the "Direct App Mark As Complete Checkbox" button
    And I click on the "Direct App Save And Return" button
    Then I execute "Submit Application Hub" reusable step
    And I execute "Log Out from Hub Student" reusable step
    And I execute "Log In to Hub as Admin" reusable step
    And I execute "Pre Approve Apprenticeships Application As Admin" reusable step with some additional steps
      | 8 | I validate text "EC_GUARDIAN_EMAIL" to be displayed for "Direct App Admin Parent Guardian Email" element |
      | 9 | I validate text "EC_GUARDIAN_PHONE" to be displayed for "Direct App Admin Parent Guardian Phone" element |
    And I execute "Attach Automation Line Manager Apprenticeships" reusable step replacing some steps
      | 4 | I fill the "Direct App Admin Attach Line Manger Text Field" field with "automationlinemanager9@harakirimail.com" |
      | 6 | I click on the "Automation9 Linemanager9 " "Build Empire line manager option"                                    |
    And I execute "Log Out from Hub Admin" reusable step
    Given I am on "MD_COMMON_LINKS_BUILDEMPIREURL" URL
    And I set "automationlinemanager9@harakirimail.com" text to the "Email" "Build Empire text field"
    And I set "Line Manager009!" text to the "Password" "Build Empire text field"
    Then I click on the "Direct App Login Button" button
    Given I click on the "Direct App My Approvals Tab" link by JS
    And I click on the "Direct App Line Manager Review Application" button
    And I click on the "Personal details" "Build Empire application section"
    Then I validate text "EC_GUARDIAN_EMAIL" to be displayed for "Direct App Line Manager Parent Guardian Email" element