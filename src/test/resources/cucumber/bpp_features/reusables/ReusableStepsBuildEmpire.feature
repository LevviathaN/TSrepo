@Reusable
Feature: BuildEmpire

  Scenario: Register New Student Account
    Given I am on "MD_COMMON_LINKS_BUILDEMPIREURL" URL
    Then I click on the "Direct App Register Link on Login Page" button
    And I set "AutoFirstName[####]" text to the "First name" "Build Empire text field"
    And I set "AutoLastName[####]" text to the "Surname" "Build Empire text field"
    And I set "EmailAuto[#####]/@/harakirimail.com" text to the "Email" "Build Empire text field"
    Then I remember "0913<KW_AUTO_RANDOMNUMBER|#######>" text as "EC_PHONENUMBER" variable
    And I set "EC_PHONENUMBER" text to the "Telephone number" "Build Empire text field"
    Then I remember "A@polloGlobal2018" text as "EC_PASSWORD" variable
    And I set "EC_PASSWORD" text to the "Password" "Build Empire text field"
    And I click on the "Direct App I Am Happy To Receive Information" button
    Then I click on the "Direct App Register" button
    And I should see the "Direct App My Applications Left Menu Link" element

  Scenario: Log In to Hub as Student
    Given I am on "MD_COMMON_LINKS_BUILDEMPIREURL" URL
    And I set "EC_EMAIL_AUTO" text to the "Email" "Build Empire text field"
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
    Given I click on the "Direct App My Applications Left Menu Link" button
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
    And I fill the "Direct App Admin Filter By User" field with "EC_EMAIL_AUTO"
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
    And I fill the "Direct App Admin Filter By User" field with "EC_EMAIL_AUTO"
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
    And I fill the "Direct App Admin Filter By User" field with "EC_EMAIL_AUTO"
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
    And I fill the "Direct App Admin Filter By User" field with "EC_EMAIL_AUTO"
    And I press "MD_COMMON_KEYBOARD_ENTER" for "Direct App Admin Filter By User"
    And I click on the "Show" "button"
    Then I click on the "Begin review" "Build Empire admin applications tab"
    Then I click on the "Submit" "button"
    Then I validate text "CONTAINS=Full application in review" to be displayed for "Direct App Admin Status Changed for Mitigating Circumstances" element

  Scenario: Complete Mitigating Circumstances
    Given I click on the "Direct App Start Application" button
    And I click on the "Eligibility criteria" "Build Empire application section"
    And I click on the "Direct App Final Award In Relation To Programme" button
    And I click on the "Direct App Ten Working Days To Submit Application Yes" button
    And I click on the "Direct App Next" button
    And I fill the "Diret App Phone Number" field with "EC_PHONENUMBER"
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
    And I click on the "Did you have learning support" "Build Empire reason for appeal element"
    And I click on the "Did you submit a Mitigating Circumstances" "Build Empire reason for appeal element"
    And I click on the "Are you appealing the result" "Build Empire reason for appeal element"
    And I click on the "Are you appealing a decision to reject your" "Build Empire reason for appeal element"
    And I click on the "Are you appealing the decision of an Examination Board" "Build Empire reason for appeal element"
    And I fill the "Direct App Details Text Area  Line Manager" field with "Automation Testing"
    And I click on the "Direct App Mark As Complete Checkbox" button
    And I click on the "Direct App Next" button
    Then I upload "directApp.pdf" file to "Direct App Upload File" element
    And I fill the "Direct App Uploaded File Description" field with "Test"
    And I fill the "Direct App Details Text Area  Line Manager" field with "Automation Testing"
    And I click on the "Direct App Mark As Complete Checkbox" button
    And I click on the "Direct App Save And Return" button

  Scenario: Create Bespoke Scotland Application
    Given I click on the "Direct App My Applications Left Menu Link" button
    And I click on the "Direct App What Are You Applying For Dropdown" element
    Then I click on the "Professional Career Development Programmes Scotland" "Build Empire application type"
    And I click on the "Direct App Programmes Programm Dropdown" element
    Then I click on the "Chartered Manager" "Build Empire application type"
    And I click on the "Direct App Create Application" button

  Scenario: Pre Approve Apprenticeships Application As Admin
    Given I click on the "Direct App Admin Apps Top Link" button
    And I click on the "Direct App Admin Direct Apps List Option" element
    Then I click on the "Apprenticeships" "Build Empire admin applications tab"
    And I fill the "Direct App Admin Filter By User" field with "EC_EMAIL_AUTO"
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
    And I fill the "Diret App Phone Number" field with "EC_PHONENUMBER"
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
    And I click on the "Direct App Save And Return" button

  Scenario: Attach Automation Line Manager
    Given I click on the "Direct App Admin Line Manager Edit" button
    And I select "Automation" from "Direct App Admin Company Dropdown" element
    And I click on the "Direct App Admin Line Manager Dropdown" button
    And I fill the "Direct App Admin Attach Line Manger Text Field" field with "idlgfcvgdh@harakirimail.com"
    And I click on the "Automation Line Manager 2" "Build Empire line manager option"
    Then I click on the "Attach" "button"