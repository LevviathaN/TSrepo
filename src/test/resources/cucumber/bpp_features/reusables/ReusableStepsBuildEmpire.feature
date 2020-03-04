@Reusable
Feature: BuildEmpire

  Scenario: Register New Student Account
    Given I am on "MD_COMMON_LINKS_BUILDEMPIREURL" URL
    Then I click on the "Direct App Register Link on Login Page" button
    And I set "Auto[FIRSTNAME]" text to the "First name" "Build Empire text field"
    And I set "Auto[LASTNAME]" text to the "Surname" "Build Empire text field"
    And I set "Auto[EMAIL-HARAKIRI]" text to the "Email" "Build Empire text field"
    And I set "[PHONE-0913]" text to the "Telephone number" "Build Empire text field"
    Then I remember "A@polloGlobal2018" text as "EC_PASSWORD" variable
    And I set "EC_PASSWORD" text to the "Password" "Build Empire text field"
    And I click on the "Direct App I Am Happy To Receive Information" button
    Then I click on the "Direct App Register" button
    And I wait for "2" seconds
    And I should see the "Direct App My Applications Left Menu Link" button

  Scenario: Log In to Hub as Student
    Given I am on "MD_COMMON_LINKS_BUILDEMPIREURL" URL
    And I set "EC_AUTO_EMAIL" text to the "Email" "Build Empire text field"
    And I set "EC_PASSWORD" text to the "Password" "Build Empire text field"
    Then I click on the "Direct App Login Button" button
    And I should see the "Direct App My Applications Left Menu Link" element

  Scenario: Log In to Hub as Admin
    Given I am on "MD_COMMON_LINKS_BUILDEMPIREADMINURL" URL
    And I fill the "Direct App Admin Email" field with "MD_COMMON_CREDENTIALS_BUILDEMPIRENEILADMINUSER"
    And I fill the "Direct App Admin Password" field with "MD_COMMON_CREDENTIALS_BUILDEMPIRENEILADMINPASSWORD"
    Then I click on the "Direct App Login Button" button
    And I should see the "Direct App Admin Apps Top Link" element

  Scenario: Create Scholarships Application
    Given I click on the "Direct App My Applications Left Menu Link" button by JS
    And I click on the "Direct App What Are You Applying For Dropdown" element
    Then I click on the "BPP University Scholarships" "Build Empire application type"
    And I click on the "Diret App Scholarship Dropdown" element
    Then I click on the "Career Guarantee Scholarship" "Build Empire application type"
    And I click on the "Direct App Programme Dropdown" element
    Then I click on the "Legal Practice Course (LPC)" "Build Empire application type"
    And I click on the "Direct App Create Application" button

  Scenario: Submit Application Hub
    Given I click on the "Direct App Submit Application" button
    And I click on the "Direct App Confirm Application Radiobutton" button
    And I click on the "Direct App Submit Application on Confirmation Page" button
    Then I validate text "CONTAINS=100" to be displayed for "Direct App Application Progress Percentage" element

  Scenario: Log Out from Hub Student
    Given I click on the "Direct App User Icon Menu" button
    And I click on the "Direct App Logout" link
    And I wait for "1" seconds

  Scenario: Log Out from Hub Admin
    Given I click on the "Direct App User Icon Menu Admin" button
    And I click on the "Direct App Admin Logout" link

  Scenario: Create Academic Appeals Application
    Given I click on the "Direct App My Applications Left Menu Link" button
    And I click on the "Direct App What Are You Applying For Dropdown" element
    Then I click on the "BPP University Academic Appeals" "Build Empire application type"
    And I click on the "Direct App Create Application" button

  Scenario: Accept Academic Appeals As Admin
    Given I click on the "Direct App Admin Apps Top Link" button
    And I click on the "Direct App Admin Direct Apps List Option" element
    Then I click on the "Academic Appeals" "Build Empire admin applications tab"
    And I fill the "Direct App Admin Filter By User" field with "EC_AUTO_EMAIL"
    And I press "MD_COMMON_KEYBOARD_ENTER" for "Direct App Admin Filter By User"
    And I click on the "Show" "button"
    Then I click on the "Begin review" "Build Empire admin applications tab"
    Then I click on the "Submit" "button"

  Scenario: Create Mitigating Circumstances Application
    Given I click on the "Direct App My Applications Left Menu Link" button
    And I click on the "Direct App What Are You Applying For Dropdown" element
    Then I click on the "BPP University Mitigating Circumstances" "Build Empire application type"
    And I click on the "Direct App Create Application" button

  Scenario: Accept Mitigating Circumstances As Admin
    Given I click on the "Direct App Admin Apps Top Link" button
    And I click on the "Direct App Admin Direct Apps List Option" element
    Then I click on the "Mitigating Circumstances" "Build Empire admin applications tab"
    And I fill the "Direct App Admin Filter By User" field with "EC_AUTO_EMAIL"
    And I press "MD_COMMON_KEYBOARD_ENTER" for "Direct App Admin Filter By User"
    And I click on the "Show" "button"
    Then I click on the "Begin review" "Build Empire admin applications tab"
    Then I click on the "Submit" "button"
    Then I validate text "CONTAINS=Accepted" to be displayed for "Direct App Admin Status Changed for Mitigating Circumstances" element

  Scenario: Reject Mitigating Circumstances As Admin
    Given I click on the "Direct App Admin Apps Top Link" button
    And I click on the "Direct App Admin Direct Apps List Option" element
    Then I click on the "Mitigating Circumstances" "Build Empire admin applications tab"
    And I select "Submitted" from "Direct App Admin Assesment Status" element
    And I fill the "Direct App Admin Filter By User" field with "EC_AUTO_EMAIL"
    And I press "MD_COMMON_KEYBOARD_ENTER" for "Direct App Admin Filter By User"
    And I click on the "Show" "button"
    Then I click on the "Begin review" "Build Empire admin applications tab"
    And I select "Reject" from "Direct App Admin New Status Dropdown" element
    Then I click on the "Submit" "button"
    Then I validate text "CONTAINS=Rejected" to be displayed for "Direct App Admin Status Changed for Mitigating Circumstances" element

  Scenario: Student Validates Application Status Changed
    Given I click on the "Direct App My Applications Left Menu Link" button
    Then I validate text "CONTAINS=Your application was successful." to be displayed for "Direct App Validate As Student Application Accepted" element

  Scenario: Put in Review Mitigating Circumstances
    Given I click on the "Direct App Admin Apps Top Link" button
    And I click on the "Direct App Admin Direct Apps List Option" element
    Then I click on the "Mitigating Circumstances" "Build Empire admin applications tab"
    And I select "Rejected" from "Direct App Admin Assesment Status" element
    And I fill the "Direct App Admin Filter By User" field with "EC_AUTO_EMAIL"
    And I press "MD_COMMON_KEYBOARD_ENTER" for "Direct App Admin Filter By User"
    And I click on the "Show" "button"
    Then I click on the "Submit" "button"
    Then I validate text "CONTAINS=Full application in review" to be displayed for "Direct App Admin Status Changed for Mitigating Circumstances" element

  Scenario: Complete Mitigating Circumstances
    Given I click on the "Direct App Start Application" button
    And I click on the "Eligibility criteria" "Build Empire application section"
    And I click on the "Direct App Final Award In Relation To Programme" button
    And I click on the "Direct App Ten Working Days To Submit Application Yes" button
    And I click on the "Direct App Next" button
    And I fill the "Diret App Phone Number" field with "EC_PHONE"
    And I click on the "Direct App Country Dropdown" button
    And I click on the "Direct App Country Dropdown UK Option" button
    And I set "Address 1" text to the "Address Line 1*" "Build Empire text field"
    And I set "Address 1" text to the "Address Line 2" "Build Empire text field"
    And I set "London" text to the "Town/City*" "Build Empire text field"
    And I fill the "Diret App Postal Code" field with "12345"
    Then I remember "KW_AUTO_DOB|DD/MM/YYYY_21" text as "EC_DOB" variable
    And I fill the "Direct App DOB" field with "EC_DOB"
    And I press "MD_COMMON_KEYBOARD_ENTER" for "Direct App DOB"
    And I set "London" text to the "Town/City*" "Build Empire text field"
    And I click on the "Direct App Mark As Complete Checkbox" button
    And I click on the "Direct App Next" button
    And I set "12345" text to the "SRN" "Build Empire course details element"
    And I set "Auto Location" text to the "Location" "Build Empire course details element"
    And I set "Automation" text to the "Programme" "Build Empire course details element"
    And I set "Automation" text to the "Programme Leader" "Build Empire course details element"
    And I click on the "Direct App Course Details Mode" element
    And I click on the "Direct App Course Details Mode Part One" element
    And I fill the "Direct App Course Details Start Date" field with "12/2020"
    And I press "MD_COMMON_KEYBOARD_ENTER" for "Direct App Course Details Start Date"
    And I click on the "Direct App Course Details School" element
    And I click on the "Direct App Course Details School Law" element
    And I click on the "Direct App Mark As Complete Checkbox" button
    And I click on the "Direct App Next" button
    And I click on the "Direct App Add Assesment" button
    And I fill the "Direct App Assesment Title" field with "AutoTitle"
    And I click on the "Direct App Assesment Type" button
    And I click on the "Direct App Assesment Exam Type Option" button
    And I click on the "Direct App Assesment Attempt Number" button
    And I click on the "Direct App Assesment Attempt Number Option Two" button
    And I click on the "Direct App Sit Assesment Yes" button
    And I fill the "Direct App Assesment Date" field with "12/12/2018"
    And I press "MD_COMMON_KEYBOARD_ENTER" for "Direct App Assesment Date"
    And I click on the "Direct App Mitigating Checkbox If Application Is Successful" button
    And I fill the "Direct App Assesment Examination Venue" field with "Venue"
    And I click on the "Direct App Did You Apply For Deferral" button
    And I click on the "Direct App Add Filled Assesment" button
    And I click on the "Direct App Mark As Complete Checkbox" button
    And I click on the "Direct App Next" button
    Then I upload "directApp.pdf" file to "Direct App Upload File" element
    And I fill the "Direct App Uploaded File Description" field with "Test"
    And I fill the "Direct App Details Text Area  Line Manager" field with "Automation Testing"
    And I click on the "Direct App Mark As Complete Checkbox" button
    And I click on the "Direct App Save And Return" button

  Scenario: Create Bespoke Scotland Application
    Given I click on the "Direct App My Applications Left Menu Link" link by JS
    And I click on the "Direct App What Are You Applying For Dropdown" element
    Then I click on the "Professional Career Development Programmes Scotland" "Build Empire application type"
    And I click on the "Direct App Programmes Programm Dropdown" element
    Then I click on the "Chartered Manager" "Build Empire application type"
    And I click on the "Direct App Create Application" button

  Scenario: Pre Approve Apprenticeships Application As Admin
    Given I click on the "Direct App Admin Apps Top Link" button
    And I click on the "Direct App Admin Direct Apps List Option" element
    And I wait for "2" seconds
    Given I click on the "Direct App Admin Apprenticeships Tab" link by JS
    And I fill the "Direct App Admin Filter By User" field with "EC_AUTO_EMAIL"
    And I press "MD_COMMON_KEYBOARD_ENTER" for "Direct App Admin Filter By User"
    And I click on the "Show" "button"
    Then I click on the "Begin review" "Build Empire admin applications tab"
    Then I click on the "Direct App Admin Submit Apprenticeships" button
    Then I validate text "CONTAINS=Pre approved" to be displayed for "Direct App Admin Status Changed for Apprenticeships" element

  Scenario: Complete Bespoke Scotland Application
    Given I click on the "I Understand" "button"
    And I click on the "Eligibility criteria" "Build Empire application section"
    And I click on the "You are UK based and you have lived here for at least three years" "Build Empire bespoke Scotland eligibility"
    And I click on the "The programme addresses development needs in your current role" "Build Empire bespoke Scotland eligibility"
    And I click on the "Your line manager is willing to support you throughout the programme" "Build Empire bespoke Scotland eligibility"
    And I click on the "You are working at least 16 hours per week" "Build Empire bespoke Scotland eligibility"
    And I click on the "You are applying for a programme that is related to your current/future job role," "Build Empire bespoke Scotland eligibility"
    And I click on the "You confirm that you have discussed this application with your line manager" "Build Empire bespoke Scotland eligibility"
    And I click on the "You confirm that you meet the programme eligibility criteria" "Build Empire bespoke Scotland eligibility"
    And I click on the "Direct App Next" button
    And I fill the "Direct App Insurance Number" field with "12345"
    And I click on the "Direct App Country Dropdown" button
    And I click on the "Direct App Country Dropdown UK Option" button
    And I fill the "Diret App Phone Number" field with "EC_PHONE"
    And I set "Address 1" text to the "Address Line 1*" "Build Empire text field"
    And I set "Address 2" text to the "Address Line 2" "Build Empire text field"
    And I fill the "Diret App Postal Code" field with "12345"
    Then I remember "KW_AUTO_DOB|DD/MM/YYYY_21" text as "EC_DOB" variable
    And I fill the "Direct App DOB" field with "EC_DOB"
    And I press "MD_COMMON_KEYBOARD_ENTER" for "Direct App DOB"
    And I set "London" text to the "Town/City*" "Build Empire text field"
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
    And I fill the "Direct App Job Title" field with "Job Title"
    And I set "32" text to the "contracted working hours?" "Build Empire employment element"
    And I set "Manager Name" text to the "Manager First Name" "Build Empire employment element"
    And I set "Manager Lastname" text to the "Manager Last Name" "Build Empire employment element"
    And I set "AutoTitle" text to the "Manager Job Title" "Build Empire employment element"
    And I set "automation11@mail.com" text to the "Manager Email" "Build Empire employment element"
    And I fill the "Direct App Line Manager Telephone" field with "[PHONE-0913]"
    And I set "Address 1" text to the "Address Line 1" "Build Empire employment element"
    And I set "London" text to the "Company/Employer City" "Build Empire employment element"
    And I set "Employee" text to the "Employee" "Build Empire employment element"
    And I set "Division" text to the "Division" "Build Empire employment element"
    And I set "Subdivision" text to the "Subdivision" "Build Empire employment element"
    And I click on the "Direct App Company Health And Safety" element
    And I fill the "Direct App When You Start At Company" field with "05/05/2018"
    And I press "MD_COMMON_KEYBOARD_ENTER" for "Direct App When You Start At Company"
    And I click on the "Direct App Company Country Dropdown" element
    And I click on the "Direct App Company Country Dropdown UK Option" element
    And I set "12345" text to the "Postcode" "Build Empire employment element"
    And I click on the "Direct App Mark As Complete Checkbox" button
    And I click on the "Direct App Save And Return" button

  Scenario: Attach Automation Line Manager
    Given I click on the "Direct App Admin Line Manager Edit" button
    And I select "Automation" from "Direct App Admin Company Dropdown" element
    And I click on the "Direct App Admin Line Manager Dropdown" button
    And I fill the "Direct App Admin Attach Line Manger Text Field" field with "idlgfcvgdh@harakirimail.com"
    And I wait for "1" seconds
    And I click on the "Automation Line Manager 2" "Build Empire line manager option"
    Then I click on the "Attach" "button"

  Scenario: Complete Bespoke Sctotland Application After Review
    Given I click on the "Direct App My Applications Left Menu Link" link by JS
    Then I validate text "CONTAINS=82" to be displayed for "Direct App Application Progress Percentage After Review" element
    Then I click on the "Direct App Go To Application" button
    And I click on the "documents" "Build Empire application section"
    And I click on the "Download Application of" "Build Empire application section"
    Then I switch to window with index "0"
    Then I upload "directApp.pdf" file to "Direct App Upload Other Documents" element
    Then I upload "application_pack_document.pdf" file to "Direct App Upload Job Role Documents" element
    And I wait for "3" seconds
    And I click on the "Direct App Mark As Complete Checkbox" button
    And I click on the "Direct App Save And Return" button

  Scenario: Line Manager Review Bespoke Scotland
    Given I click on the "Direct App My Approvals Tab" link by JS
    And I click on the "Direct App Line Manager Review Application" button
    And I click on the "Line Manager Review" "Build Empire application section"
    And I click on the "Direct App Line Manager Agreee To Support Amount" element
    And I click on the "Direct App Mark As Complete Checkbox" button
    And I click on the "Direct App Save Review And Back" button
    And I wait for "1" seconds
    And Browser performs "Refresh" command
    And I click on the "Application documents" "Build Empire application section"
    And I click on the "Download Application of" "Build Empire application section"
    Then I switch to window with index "0"
    Then I upload "application_pack_document.pdf" file to "Direct App Upload Job Role Documents" element
    And I wait for "2" seconds
    And I click on the "Direct App Mark As Complete Checkbox" button
    And I click on the "Direct App Save Review And Back" button
    And I click on the "Submit Approval" "button"
    And I click on the "OK" "button"

  Scenario: Accept Application As Admin
    Given I click on the "Direct App Admin Apps Top Link" button
    And I click on the "Direct App Admin Direct Apps List Option" element
    And I wait for "2" seconds
    Given I click on the "Direct App Admin Apprenticeships Tab" link by JS
    And I fill the "Direct App Admin Filter By User" field with "EC_AUTO_EMAIL"
    And I press "MD_COMMON_KEYBOARD_ENTER" for "Direct App Admin Filter By User"
    And I click on the "Show" "button"
    Then I click on the "Begin full application review" "Build Empire application section"
    Then I click on the "Submit" "button"
    Then I validate text "Accepted" to be displayed for "Direct App Admin Status Changed for Apprenticeships" element

  Scenario: Validate Successful Application As Student
    Given I click on the "Direct App My Applications Left Menu Link" link by JS
    And I should see the "Direct App Application Accepted Message" element

  Scenario: Create Bespoke England Application
    Given I click on the "Direct App My Applications Left Menu Link" link by JS
    And I click on the "Direct App What Are You Applying For Dropdown" element
    Then I click on the "Professional Career Development Programmes England" "Build Empire application type"
    And I click on the "Direct App Programmes Programm Dropdown" element
    Then I click on the "Chartered Manager" "Build Empire application type"
    And I click on the "Direct App Create Application" button

  Scenario: Complete Bespoke England Application
    Given I click on the "I Understand" "button"
    And I click on the "Eligibility criteria" "Build Empire application section"
    And I click on the "Are you aged 16" "Build Empire bespoke Scotland eligibility"
    And I click on the "Do you have a husband, wife, civil partner and child of any of the above?" "Build Empire bespoke Scotland eligibility"
    And I click on the "Direct App Are You Working At Least 16 Hours" element
    And I click on the "Direct App Right To Work in England?" element
    And I click on the "Direct App Bespoke Enrolled On Another Apprenticeship No" element
    And I click on the "Direct App Do You Spend 50 Percent" element
    And I click on the "Do you confirm that your job is a new job role relevant to the career development" "Build Empire bespoke Scotland eligibility"
    And I click on the "Are you applying for a programme" "Build Empire bespoke Scotland eligibility"
    And I click on the "Direct App Are You In Employment" element
    And I click on the "Do you confirm that you have discussed" "Build Empire bespoke Scotland eligibility"
    And I click on the "Direct App Next" button
    And I fill the "Direct App Insurance Number" field with "12345"
    And I click on the "Direct App Country Dropdown" button
    And I click on the "Direct App Country Dropdown UK Option" button
    And I fill the "Diret App Phone Number" field with "EC_PHONE"
    And I set "Address 1" text to the "Address Line 1*" "Build Empire text field"
    And I set "Address 2" text to the "Address Line 2" "Build Empire text field"
    And I fill the "Diret App Postal Code" field with "12345"
    Then I remember "KW_AUTO_DOB|DD/MM/YYYY_21" text as "EC_DOB" variable
    And I fill the "Direct App DOB" field with "EC_DOB"
    And I press "MD_COMMON_KEYBOARD_ENTER" for "Direct App DOB"
    And I set "London" text to the "Town/City*" "Build Empire text field"
    And I click on the "Direct App Mark As Complete Checkbox" button
    And I click on the "Direct App Next" button
    And I click on the "Direct App Additional Info Aged 19" element
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
    And I fill the "Direct App Job Title" field with "Job Title"
    And I set "32" text to the "contracted working hours?" "Build Empire employment element"
    And I set "Manager Name" text to the "Manager First Name" "Build Empire employment element"
    And I set "Manager Lastname" text to the "Manager Last Name" "Build Empire employment element"
    And I set "AutoTitle" text to the "Manager Job Title" "Build Empire employment element"
    And I set "automation11@mail.com" text to the "Manager Email" "Build Empire employment element"
    And I fill the "Direct App Line Manager Telephone" field with "0913<KW_AUTO_RANDOMNUMBER|#######>"
    And I set "Address 1" text to the "Address Line 1" "Build Empire employment element"
    And I set "London" text to the "Company/Employer City" "Build Empire employment element"
    And I set "Employee" text to the "Employee" "Build Empire employment element"
    And I set "Division" text to the "Division" "Build Empire employment element"
    And I set "Subdivision" text to the "Subdivision" "Build Empire employment element"
    And I click on the "Direct App Company Health And Safety" element
    And I fill the "Direct App When You Start At Company" field with "05/05/2018"
    And I press "MD_COMMON_KEYBOARD_ENTER" for "Direct App When You Start At Company"
    And I click on the "Direct App Company Country Dropdown" element
    And I click on the "Direct App Company Country Dropdown UK Option" element
    And I set "12345" text to the "Postcode" "Build Empire employment element"
    And I click on the "Direct App Mark As Complete Checkbox" button
    And I click on the "Direct App Next" button
    And I click on the "Direct App Privacy Notice By Email" button
    And I click on the "Direct App Mark As Complete Checkbox" button
    And I click on the "Direct App Save And Return" button

  Scenario: Line Manager Review Bespoke England
    Given I click on the "Direct App My Approvals Tab" link by JS
    And I click on the "Direct App Line Manager Review Application" button
    And I click on the "Line Manager Review" "Build Empire application section"
    Then I fill the "Direct App Details Text Area  Line Manager" field with "Automation"
    And I click on the "Direct App Line Manager Agreee To Support Amount" element
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
    And I wait for "2" seconds
    And I click on the "Direct App Mark As Complete Checkbox" button
    And I click on the "Direct App Save Review And Back" button
    And I click on the "Submit Approval" "button"
    And I click on the "OK" "button"

  Scenario: Complete Apprenticeships Application After Review
    Given I click on the "Direct App My Applications Left Menu Link" link by JS
    Then I validate text "CONTAINS=88" to be displayed for "Direct App Application Progress Percentage After Review" element
    Then I click on the "Direct App Go To Application" button
    And I click on the "documents" "Build Empire application section"
    And I click on the "Download Application of" "Build Empire application section"
    Then I switch to window with index "0"
    Then I upload "directApp.pdf" file to "Direct App Apprenticeships Upload Documents" element
    Then I upload "application_pack_document.pdf" file to "Direct App Upload Job Role Documents" element
    And I wait for "3" seconds
    And I click on the "Direct App Mark As Complete Checkbox" button
    And I click on the "Direct App Save And Return" button

  Scenario: Mitigating Circumstances Admin Status Change
    Given I click on the "Direct App Admin Apps Top Link" button
    And I click on the "Direct App Admin Direct Apps List Option" element
    Then I click on the "Mitigating Circumstances" "Build Empire admin applications tab"
    And I fill the "Direct App Admin Filter By User" field with "EC_AUTO_EMAIL"
    And I press "MD_COMMON_KEYBOARD_ENTER" for "Direct App Admin Filter By User"
    And I click on the "Show" "button"
    Then I click on the "Begin review" "Build Empire admin applications tab"
    Then I validate text "In review" to be displayed for "Direct App Admin Status Changed for Mitigating Circumstances" element
    And I select "Referral Panel" from "Direct App Admin New Status Dropdown" element
    And I fill the "Direct App Admin Reason" field with "Reason Referral Panel"
    Then I click on the "Submit" "button"
    Then I validate text "Referral panel" to be displayed for "Direct App Admin Status Changed for Mitigating Circumstances" element
    And I select "Referral ODP" from "Direct App Admin New Status Dropdown" element
    And I fill the "Direct App Admin Reason" field with "Reason Referral ODP"
    Then I click on the "Submit" "button"
    Then I validate text "Referral ODP" to be displayed for "Direct App Admin Status Changed for Mitigating Circumstances" element
    And I select "Part Accept" from "Direct App Admin New Status Dropdown" element
    And I fill the "Direct App Admin Reason" field with "Reason Part Accept"
    Then I click on the "Submit" "button"
    Then I validate text "Part accepted" to be displayed for "Direct App Admin Status Changed for Mitigating Circumstances" element
    And I fill the "Direct App Admin Reason" field with "Reason Full Application In Review Second"
    Then I click on the "Submit" "button"
    Then I validate text "Full application in review" to be displayed for "Direct App Admin Status Changed for Mitigating Circumstances" element
    And I select "Accept" from "Direct App Admin New Status Dropdown" element
    And I fill the "Direct App Admin Reason" field with "Reason Accept"
    Then I click on the "Submit" "button"
    Then I validate text "Accepted" to be displayed for "Direct App Admin Status Changed for Mitigating Circumstances" element
    And I fill the "Direct App Admin Reason" field with "Reason Full Application In Review Second"
    Then I click on the "Submit" "button"
    Then I validate text "Full application in review" to be displayed for "Direct App Admin Status Changed for Mitigating Circumstances" element
    And I select "Reject" from "Direct App Admin New Status Dropdown" element
    And I fill the "Direct App Admin Reason" field with "Reason Reject"
    Then I click on the "Submit" "button"
    Then I validate text "Rejected" to be displayed for "Direct App Admin Status Changed for Mitigating Circumstances" element

  Scenario: Mitigating Circumstances Admin Status Validation
    Then I validate text "User start" to be displayed for "Direct App Admin Started Status Reason" element
    Then I validate text "User submit" to be displayed for "Direct App Admin Submitted Status Reason" element
    Then I validate text "User begin review" to be displayed for "Direct App Admin In Review Status Reason" element
    Then I validate text "Reason Referral Panel" to be displayed for "Direct App Admin Referral Panel Reason" element
    Then I validate text "Reason Referral ODP" to be displayed for "Direct App Admin Referral ODP Reason" element
    Then I validate text "Reason Part Accept" to be displayed for "Direct App Admin Part Accepted Reason" element
    Then I validate text "Reason Full Application in Review Second" to be displayed for "Direct App Admin Full Application Reason" element
    Then I validate text "Reason Accept" to be displayed for "Direct App Admin Accepted Status Reason" element
    Then I validate text "Reason Full Application in Review Second" to be displayed for "Direct App Admin Full Application Status Second Reason" element
    Then I validate text "Reason Reject" to be displayed for "Direct App Admin Rejected Status Reason" element

  Scenario: Edit Hub Student
    Given I click on the "Direct App User Icon Menu" button
    And I click on the "Direct App Account settings" link
    And I wait for "1" seconds

  Scenario: Create Programmes Application
    Given I click on the "Direct App My Applications Left Menu Link" button by JS
    And I click on the "Direct App What Are You Applying For Dropdown" element
    Then I click on the "BPP University Programmes" "Build Empire application type"
    And I click on the "Direct App Programmes Programm Dropdown" element
    Then I click on the "BSc (Hons) Nursing  [Diploma to Degree]" "Build Empire application type"
    And I click on the "Direct App Location Dropdown" element
    Then I click on the "Online GDL" "Build Empire application type"
    And I click on the "Direct App Start Date Application Dropdown" element
    Then I click on the "Full time- Aug-2020" "Build Empire application type"
    And I click on the "Direct App Create Application" button

  Scenario: Create Professional Apprenticeships Application Chartered Manager
    Given I click on the "Direct App My Applications Left Menu Link" link by JS
    And I click on the "Direct App What Are You Applying For Dropdown" element
    Then I click on the "BPP Professional Apprenticeships" "Build Empire application type"
    And I click on the "Direct App Programmes Programm Dropdown" element
    Then I click on the "Chartered Manager Degree Apprenticeship" "Build Empire application type"
    And I click on the "Direct App Location Dropdown" element
    Then I click on the "Scotland" "Build Empire application type"
    And I click on the "Direct App Create Application" button

  Scenario: Complete Professional Apprenticeships Application Chartered Manager
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
    And I click on the "You confirm that you have discussed" "Build Empire bespoke Scotland eligibility"
    And I click on the "Direct App Next" button
    And I fill the "Direct App Insurance Number" field with "12345"
    And I click on the "Direct App Country Dropdown" button
    And I click on the "Direct App Country Dropdown UK Option" button
    And I fill the "Diret App Phone Number" field with "EC_PHONE"
    And I set "Address 1" text to the "Address Line 1*" "Build Empire text field"
    And I set "Address 2" text to the "Address Line 2" "Build Empire text field"
    And I fill the "Diret App Postal Code" field with "12345"
    Then I remember "KW_AUTO_DOB|DD/MM/YYYY_21" text as "EC_DOB" variable
    And I fill the "Direct App DOB" field with "EC_DOB"
    And I press "MD_COMMON_KEYBOARD_ENTER" for "Direct App DOB"
    And I set "London" text to the "Town/City*" "Build Empire text field"
    And I click on the "Direct App Mark As Complete Checkbox" button
    And I click on the "Direct App Next" button
    And I click on the "Direct App Additional Info Aged 19" element
    And I click on the "Direct App Mark As Complete Checkbox" button
    And I click on the "Direct App Next" button
    Then I click on the "Direct App Scholarship Add" button
    And I set "AutomationTest" text to the "Kin/Emergency" "Build Empire emergency field"
    And I set "AutomationTest" text to the "What is your relationship with this person?" "Build Empire emergency field"
    Then I fill the "Direct App Line Manager Telephone" field with "09138333731"
    And I fill the "Direct App Admin Email" field with "automation111@mailinator.com"
    Then I click on the "Direct App Scholarship Add" button
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
    And I fill the "Direct App Job Title" field with "Job Title"
    And I set "32" text to the "contracted working hours?" "Build Empire employment element"
    And I set "Manager Name" text to the "Manager First Name" "Build Empire employment element"
    And I set "Manager Lastname" text to the "Manager Last Name" "Build Empire employment element"
    And I set "AutoTitle" text to the "Manager Job Title" "Build Empire employment element"
    And I set "automation11@mail.com" text to the "Manager Email" "Build Empire employment element"
    And I fill the "Direct App Line Manager Telephone" field with "0913<KW_AUTO_RANDOMNUMBER|#######>"
    And I set "Address 1" text to the "Address Line 1" "Build Empire employment element"
    And I set "London" text to the "Company/Employer City" "Build Empire employment element"
    And I set "Employee" text to the "Company/Employer Name" "Build Empire employment element"
    And I click on the "Direct App Company Health And Safety" element
    And I fill the "Direct App When You Start At Company" field with "05/05/2018"
    And I press "MD_COMMON_KEYBOARD_ENTER" for "Direct App When You Start At Company"
    And I click on the "Direct App Company Country Dropdown" element
    And I click on the "Direct App Company Country Dropdown UK Option" element
    And I set "12345" text to the "Postcode" "Build Empire employment element"
    And I click on the "Direct App Mark As Complete Checkbox" button
    And I click on the "Direct App Next" button
    And I click on the "Direct App Privacy Notice By Email" button
    And I click on the "Direct App Mark As Complete Checkbox" button
    And I click on the "Direct App Save And Return" button

  Scenario: Line Manager Review Apprenticeships
    Given I click on the "Direct App My Approvals Tab" link by JS
    And I click on the "Direct App Line Manager Review Application" button
    And I click on the "Line Manager Review" "Build Empire application section"
    Then I fill the "Direct App Line Manager End Point Assessment" field with "Automation"
    And I wait for "2" seconds
    And I click on the "Direct App Line Manager Agreee To Support Amount" element
    And I click on the "Direct App Line Manager New Role" button
    Then I fill the "Direct App Details Text Area  Line Manager" field with "Automation"
    And I click on the "Direct App Mark As Complete Checkbox" button
    And I click on the "Direct App Save Review And Next" button
    And I click on the "Direct App Line Manager Review Health And Safety Checkbox" button
    And I click on the "Direct App Save Review And Next" button
    And I wait for "1" seconds
    And I click on the "Download Application of" "Build Empire application section"
    Then I switch to window with index "0"
    Then I upload "application_pack_document.pdf" file to "Direct App Apprenticeships Upload Documents" element
    Then I upload "application_pack_document.pdf" file to "Direct App Upload Job Role Documents" element
    And I wait for "2" seconds
    And I click on the "Direct App Mark As Complete Checkbox" button
    And I click on the "Direct App Save Review And Back" button
    And I click on the "Submit Approval" "button"
    And I click on the "OK" "button"

  Scenario: Attach Automation Line Manager Apprenticeships
    Given I click on the "Direct App Admin Line Manager Edit" button
    And I select "Automation" from "Direct App Admin Company Dropdown" element
    And I click on the "Direct App Admin Line Manager Dropdown" button
    And I fill the "Direct App Admin Attach Line Manger Text Field" field with "2917uk7sr@harakirimail.com"
    And I wait for "1" seconds
    And I click on the "Automation Line Manager 1" "Build Empire line manager option"
    Then I click on the "Attach" "button"

  Scenario: Complete Professional Apprenticeships Application After Review
    Given I click on the "Direct App My Applications Left Menu Link" link by JS
    Then I validate text "CONTAINS=89" to be displayed for "Direct App Application Progress Percentage After Review" element
    Then I click on the "Direct App Go To Application" button
    And I click on the "documents" "Build Empire application section"
    And I click on the "Download Application of" "Build Empire application section"
    Then I switch to window with index "0"
    Then I upload "directApp.pdf" file to "Direct App Apprenticeships Upload Documents" element
    Then I upload "application_pack_document.pdf" file to "Direct App Upload Job Role Documents" element
    And I wait for "3" seconds
    And I click on the "Direct App Mark As Complete Checkbox" button
    And I click on the "Direct App Save And Return" button

  Scenario: Admin Hub Navigate to Blog Posts
    When I click on the "Website" "BPP Digital Admin Menu Item links"
    And I click on the "Blog Posts" "BPP Digital Admin Sub Menu Item links"
    Then I should see the "Blog Posts" "BPP Digital Admin Page Header name"

  Scenario: Create Blog Post
    When I click on the "Add" "BPP Digital Admin Page Button"
    And I set "AutoBlogPost[#####]" text to the "Title *" "BPP Digital Mandatory input field"
    And I set "AutoUrl[#####]" text to the "URL path *" "BPP Digital Mandatory input field"
    And I set "MetaDescription[####]" text to the "Meta description *" "BPP Digital Mandatory input field"
    And I set "Summary[####]" text to the "Summary *" "BPP Digital Mandatory input field"
    And I select "Research" from "Content type *" "BPP Digital Mandatory select field"
    And I select "Topic 1" from "Topics" "BPP Digital Mandatory select field"
    And I select "Law" from "Schools/Professions" "BPP Digital Mandatory select field"
    And I click on the "Save" "BPP Digital Admin Value attribute button"
    Then I should see the "Blog Post was successfully created." "BPP Digital Admin Alert Message"

  Scenario: Search for Blog Post
    When I fill the "BPP Digital Filter by title search field" field with "EC_AUTO_BLOG_POST"
    And I press "MD_COMMON_KEYBOARD_ENTER" for "BPP Digital Filter by title search field"
    Then I should see the "EC_AUTO_BLOG_POST" element

  Scenario: Add Image Component To CMS or Blog Post page
    When I click on the "Add component" "BPP Digital Search Label Button"
    And I click on the "Images" "BPP Digital Admin Sub Menu Item links"
    And I set "CMS Images" text to the "Html" "BPP Digital Mandatory input field"
    And I click on the "Add" "BPP Digital Search Label Button"
    And I click on the "Image" "BPP Digital Admin Choose Image button"
    And I switch to window with index "2"
    And I set "tree.jpg" text to the "Search..." "Build Empire text field"
    And I click on the "Search" "button"
    And I capture text data "BPP Digital First Image name from Image Uploader Popup" as "EC_IMAGE_NAME" variable
    And I click on the "BPP Digital First CMS Component First Select button" button by JS
    And I switch to window with index "1"
    Then I click on the "Save" "BPP Digital Admin Value attribute button"
    Then I wait for "3" seconds

  Scenario: Publish a CMS or Blog Post page
    When I wait for "2" seconds
    When I click on the "Publish" "button"
    Then I should see the "Blog post successfully published." "BPP Digital Admin Alert Message"

  Scenario: Archive a CMS or Blog Post page
    When I wait for "2" seconds
    When I click on the "Archive" "button"
    Then I should see the "Blog post successfully archived." "BPP Digital Admin Alert Message"

  Scenario: Admin Hub Navigate to Blog Topics
    When I click on the "Website" "BPP Digital Admin Menu Item links"
    And I click on the "Blog Topics" "BPP Digital Admin Sub Menu Item links"
    Then I should see the "Blog Topics" "BPP Digital Admin Page Header name"

  Scenario: Create Blog Topic
    When I click on the "Add" "BPP Digital Admin Page Button"
    And I set "AutoBlogTopic[#####]" text to the "Name *" "BPP Digital Mandatory input field"
    And I click on the "Save" "BPP Digital Admin Value attribute button"
    Then I should see the "Blog Topic was successfully created." "BPP Digital Admin Alert Message"

  Scenario: Create University Degree Apprenticeships Application
    Given I click on the "Direct App My Applications Left Menu Link" link by JS
    And I click on the "Direct App What Are You Applying For Dropdown" element
    Then I click on the "BPP University Degree Apprenticeships" "Build Empire application type"
    And I click on the "Direct App Programmes Programm Dropdown" element
    Then I click on the "Chartered Manager Degree Apprenticeship" "Build Empire application type"
    And I click on the "Location" "Build Empire dropdown"
    And I click on the "Scotland" "Build Empire dropdown option"
    And I click on the "Direct App Create Application" button

  Scenario: Complete University Degree Apprenticeships Application Chartered Manager
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
    And I set "[DOB-21]" text to the "Date of Birth" "Build Empire text field by title"
    And I press "MD_COMMON_KEYBOARD_ENTER" for "Direct App DOB"
    And I set "EC_PHONE" text to the "Phone number" "Build Empire text field by title"
    And I set "Address 1" text to the "Address Line 1*" "Build Empire text field"
    And I set "Address 2" text to the "Address Line 2" "Build Empire text field"
    And I set "Address 3" text to the "Address Line 3" "Build Empire text field"
    And I set "80004" text to the "Postcode" "Build Empire text field by title"
    And I set "[CITYUSA]" text to the "Town/City*" "Build Empire text field"
    And I click on the "Country*" "Build Empire dropdown"
    And I click on the "Afghanistan" "Build Empire dropdown option"
    And I click on the "Direct App Mark As Complete Checkbox" button
    And I click on the "Direct App Next" button
    And I click on the "Direct App Additional Info Aged 19" element
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

  Scenario: Line Manager Review University Degree Apprenticeships Scotland
    Given I click on the "Direct App My Approvals Tab" link by JS
    And I click on the "Direct App Line Manager Review Application" button
    And I click on the "Line Manager Review" "Build Empire application section"
    Then I fill the "Direct App Details Text Area  Line Manager" field with "Automation"
    And I wait for "2" seconds
    And I click on the "Direct App Line Manager Agreee To Support Amount" element
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
    And I wait for "2" seconds
    And I click on the "Direct App Mark As Complete Checkbox" button
    And I click on the "Direct App Save Review And Back" button
    And I click on the "Submit Approval" "button"
    And I click on the "OK" "button"

  Scenario: Reject University Degree Apprenticeships As Admin
    Given I click on the "Direct App Admin Apps Top Link" button
    And I click on the "Direct App Admin Direct Apps List Option" element
    Then I click on the "Apprenticeships" "Build Empire admin applications tab"
    And I fill the "Direct App Admin Filter By User" field with "EC_AUTO_EMAIL"
    And I press "MD_COMMON_KEYBOARD_ENTER" for "Direct App Admin Filter By User"
    And I click on the "Show" "button"
    Then I click on the "Begin review" "Build Empire admin applications tab"
    And I select "Reject" from "Direct App Admin New Status Dropdown" element
    Then I click on the "Direct App Admin Apprenticeships Change Status Submit Button" button
    Then I validate text "CONTAINS=Rejected" to be displayed for "Direct App Admin Status Changed for Apprenticeships" element

  Scenario: Line Manager Review And Reject University Degree Apprenticeships Scotland
    Given I click on the "Direct App My Approvals Tab" link by JS
    And I click on the "Direct App Line Manager Review Application" button
    And I click on the "Line Manager Review" "Build Empire application section"
    Then I fill the "Direct App Details Text Area  Line Manager" field with "Automation"
    And I wait for "2" seconds
    And I click on the "Direct App Line Manager Agreee To Support Amount" element
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
    And I wait for "2" seconds
    And I click on the "Direct App Mark As Complete Checkbox" button
    And I click on the "Direct App Save Review And Back" button
    And I click on the "Reject Application" button
    And I click on the "OK" "button"

  Scenario: Application Rejected by Line Manager Validation
    Given I click on the "Direct App Admin Apps Top Link" button
    And I click on the "Direct App Admin Direct Apps List Option" element
    Then I click on the "Apprenticeships" "Build Empire admin applications tab"
    And I fill the "Direct App Admin Filter By User" field with "EC_AUTO_EMAIL"
    And I press "MD_COMMON_KEYBOARD_ENTER" for "Direct App Admin Filter By User"
    And I click on the "Show" "button"
    Then I validate text "CONTAINS=Line manager rejected" to be displayed for "Direct App Admin Status Changed for Apprenticeships" element

  Scenario: Incomplete University Degree Apprenticeships Application As Admin
    Given I click on the "Direct App Admin Apps Top Link" button
    And I click on the "Direct App Admin Direct Apps List Option" element
    Then I click on the "Apprenticeships" "Build Empire admin applications tab"
    And I fill the "Direct App Admin Filter By User" field with "EC_AUTO_EMAIL"
    And I press "MD_COMMON_KEYBOARD_ENTER" for "Direct App Admin Filter By User"
    And I click on the "Show" "button"
    And I click on the "Begin review" "Build Empire admin applications tab" if "Begin review" "element is present"
    And I select "Incomplete" from "Direct App Admin Force Change Status Dropdown" element
    And I click on the "Change" "button"
    And I fill the "Direct App Admin Application Page Password Field" field with "MD_COMMON_CREDENTIALS_BUILDEMPIRENEILADMINPASSWORD"
    And I click on the "OK" "button"
    Then I validate text "CONTAINS=Incomplete" to be displayed for "Direct App Admin Status Changed for Apprenticeships" element

  Scenario: Complete University Degree Apprenticeships Application With Existing Student
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
    And I click on the "Direct App Mark As Complete Checkbox" button
    And I click on the "Direct App Next" button
    And I click on the "Direct App Mark As Complete Checkbox" button
    And I click on the "Direct App Next" button
    And I click on the "Direct App Mark As Complete Checkbox" button
    And I click on the "Direct App Next" button
    And I click on the "Direct App Mark As Complete Checkbox" button
    And I click on the "Direct App Next" button
    And I click on the "Direct App Mark As Complete Checkbox" button
    And I click on the "Direct App Next" button
    And I click on the "Direct App Mark As Complete Checkbox" button
    And I click on the "Direct App Next" button
    And I set "AutoTitle" text to the "Manager Job Title" "Build Empire employment element"
    And I fill the "Direct App Line Manager Telephone" field with "0913<KW_AUTO_RANDOMNUMBER|#######>"
    And I click on the "Direct App Mark As Complete Checkbox" button
    And I click on the "Direct App Next" button
    And I click on the "Direct App Privacy Notice By Email" button
    And I click on the "Direct App Mark As Complete Checkbox" button
    And I click on the "Direct App Save And Return" button

  Scenario: Admin Hub Navigate to CMS Pages
    When I click on the "Website" "BPP Digital Admin Menu Item links"
    And I click on the "Pages" "BPP Digital Admin Sub Menu Item links"
    Then I should see the "Pages - All" "BPP Digital Admin Page Header name"

  Scenario: Create CMS Page
    When I click on the "Add" "BPP Digital Admin Page Button"
    And I set "AutoCMSPage[#####]" text to the "Name *" "BPP Digital Mandatory input field"
    And I select "CMS page" from "Page type *" "BPP Digital Mandatory select field"
    And I set "AutoCMSUrl[#####]" text to the "Path *" "BPP Digital Mandatory input field"
    And I select "About us > BPP University > University board" from "Parent" "BPP Digital Mandatory select field"
    And I set "Title[####]" text to the "Title" "BPP Digital Mandatory input field"
    And I set "Summary[####]" text to the "Summary" "BPP Digital Mandatory text area field"
    And I set "MetaDescription[####]" text to the "Meta description" "BPP Digital Mandatory text area field"
    And I set "LinkTitle[####]" text to the "Link title *" "BPP Digital Mandatory input field"
    And I set "LinkDescription[####]" text to the "Link description *" "BPP Digital Mandatory text area field"
    And I "check" "Show in navigation" "BPP Digital Admin Mandatory checkbox"
    And I click on the "Save" "BPP Digital Admin Value attribute button"
    Then I should see the "CMS Page was successfully created." "BPP Digital Admin Alert Message"

  Scenario: Search for CMS Page
    When I fill the "BPP Digital Filter by name search field" field with "EC_AUTO_CMS_PAGE"
    And I press "MD_COMMON_KEYBOARD_ENTER" for "BPP Digital Filter by name search field"
    Then I should see the "EC_AUTO_CMS_PAGE" element

  Scenario: Admin Hub Navigate to Applications list
    When I click on the "Direct Apps" "BPP Digital Admin Menu Item links"
    And I click on the "List" "BPP Digital Admin Sub Menu Item links"
    Then I should see the "Applications" "BPP Digital Admin Page Header name"

  Scenario: Admin Hub Navigate to Website Redirects
    When I click on the "Website" "BPP Digital Admin Menu Item links"
    And I click on the "Redirects" "BPP Digital Admin Sub Menu Item links"
    Then I should see the "Redirects" "BPP Digital Admin Page Header name"

  Scenario: Admin Hub Navigate to new Courses Pages
    When I click on the "Website" "BPP Digital Admin Menu Item links"
    And I click on the "Courses (new)" "BPP Digital Admin Sub Menu Item links"
    Then I should see the "Pages - Courses (new)" "BPP Digital Admin Page Header name"

  Scenario: Admin Hub Navigate to new Professions Pages
    When I click on the "Website" "BPP Digital Admin Menu Item links"
    And I click on the "Professions (new)" "BPP Digital Admin Sub Menu Item links"
    Then I should see the "Pages - Professions (new)" "BPP Digital Admin Page Header name"