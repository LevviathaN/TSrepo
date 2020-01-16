@Reusable
Feature: BuildEmpire

  Scenario: Register New Student Account
    Given I am on "MD_COMMON_LINKS_BUILDEMPIREURL" URL
    Then I click on the "Direct App Register Link on Login Page" button
    And I set "AutoFirstName[####]" text to the "First name" "Build Empire text field"
    And I set "AutoLastName[####]" text to the "Surname" "Build Empire text field"
    And I set "EmailAuto<KW_AUTO_FIRSTNAME>/@/harakirimail.com" text to the "Email" "Build Empire text field"
    Then I remember "0913<KW_AUTO_RANDOMNUMBER|#######>" text as "EC_PHONENUMBER" variable
    And I set "EC_PHONENUMBER" text to the "Telephone number" "Build Empire text field"
    Then I remember "A@polloGlobal2018" text as "EC_PASSWORD" variable
    And I set "EC_PASSWORD" text to the "Password" "Build Empire text field"
    And I click on the "Direct App I Am Happy To Receive Information" button
    Then I click on the "Direct App Register" button

  Scenario: Log In to Hub as Student
    Given I am on "MD_COMMON_LINKS_BUILDEMPIREURL" URL
    And I set "EC_EMAIL_ADDRESS" text to the "Email" "Build Empire text field"
    And I set "EC_PASSWORD" text to the "Password" "Build Empire text field"
    Then I click on the "Direct App Login Button" button

  Scenario: Log In to Hub as Admin
    Given I am on "MD_COMMON_LINKS_BUILDEMPIREADMINURL" URL
    And I fill the "Direct App Admin Email" field with "MD_COMMON_CREDENTIALS_BUILDEMPIRENEILADMINUSER"
    And I fill the "Direct App Admin Password" field with "MD_COMMON_CREDENTIALS_BUILDEMPIRENEILADMINPASSWORD"
    Then I click on the "Direct App Login Button" button

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