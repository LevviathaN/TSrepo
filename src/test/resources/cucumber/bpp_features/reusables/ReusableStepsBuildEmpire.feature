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
    And I fill the "Direct App Admin Filter By User" field with "EC_EMAIL_AUTO"
    And I press "MD_COMMON_KEYBOARD_ENTER" for "Direct App Admin Filter By User"
    And I click on the "Show" "button"
    Then I click on the "Begin review" "Build Empire admin applications tab"
    And I select "Reject" from "Direct App Admin New Status Dropdown" element
    Then I click on the "Submit" "button"
    Then I validate text "CONTAINS=Full application in review" to be displayed for "Direct App Admin Status Changed for Mitigating Circumstances" element