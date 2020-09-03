@Salesforce @CPQ
Feature: Create Student Account
  Description

  @Positive #TC-2609
  Scenario: CPQ Single Booking
    Given I execute "Log In To Salesforce" reusable step replacing some steps
      |2|I fill the "Salesforce Username" field with "MD_COMMON_CREDENTIALS_SALESFORCECPQUSER"|
      |3|I fill the "Salesforce Password" field with "MD_COMMON_CREDENTIALS_SALESFORCECPQPASSWORD"|
    #Check amount of available bookings
    Given I am on "MD_COMMON_LINKS_SALESFORCEINSTANCE" URL
    And I capture text data "Salesforce Instance Availability Data Field" as "EC_AVAILABILITY_NUMBER" variable
    #Create Student Account
    When I click on the "Accounts" "Salesforce navigation menu dropdown"
    And I wait for "2" seconds
    And I click on the "New Account" "Salesforce navigation menu dropdown option"
    And I click on the "Student Account" "Salesforce radiobutton"
    And I click on the "Next" "button"
    And I set "Auto[FIRSTNAME]" text to the "First Name" "Salesforce text field"
    And I set "Auto[#####]" text to the "Last Name" "Salesforce text field"
    And I click on the "Student Type" "Salesforce dropdown field"
    And I click on the "Domestic" "option"
    And I set "31/01/1998" text to the "Date of Birth" "Salesforce date field"
    And I click on the "Gender" "Salesforce dropdown field"
    And I click on the "Male" "option"
    And I set "Canada" text to the "Primary Nationality" "Salesforce search field"
    And I click on the "Canada" "option"
    And I click on the "Domicile" "Salesforce dropdown field"
    And I click on the "Canada" "Salesforce dropdown field option"
    And I click on the "Preferred Phone" "Salesforce dropdown field"
    And I click on the "Home" "Salesforce dropdown field option"
    And I set "[PHONE]" text to the "Home Phone" "Salesforce text field"
    And I click on the "Preferred Email" "Salesforce dropdown field"
    And I click on the "Personal" "Salesforce dropdown field option"
    And I set "Auto[EMAIL-HARAKIRI]" text to the "Personal Email" "Salesforce text field"
    And I click on the "Save" "button"
    Then I execute "Create Business Account" reusable step
    Then I execute "Create Business Opportunity" reusable step
    #Add Quote
    And I click on the "EC_AUTO_OPPORTUNITY" "Salesforce link"
    Then I click on the "Salesforce Create Quote" element
    And I wait for "3" seconds
    And I click on the "Salesforce Quote Save" button
    And I click on the "Salesforce Quotes Quick Link Tab" element
    Then I click on the "Salesforce Quotes First Link" element
    And I click on the "Salesforce Quotes Edit Lines" element
    Then I wait for "10" seconds
    And I hover over the "Salesforce Quotes Add Products" button
    And I click on the "Salesforce Quotes Add Products" element
    Then I fill the "Salesforce Quotes Search Products" field with "SF CPQ test - SF CPQ test - SF CPQ flow test - London - Classroom - AutoTest Sitting"
    And I press "MD_COMMON_KEYBOARD_ENTER" for "Salesforce Quotes Search Products"
    Then I click on the "Salesforce Quotes Product Checkbox" option
    And I click on the "Salesforce Quotes Select Product Button" element
    And I wait for "2" seconds
    And I click on the "Salesforce Quotes Select Product Page Save" element
    Then I click on the "Salesforce Quotes Edit" element
    And I click on the "Salesforce Quotes Ordered Checkbox" element
    And I capture text data "Salesforce Quote Edited Quote Number" as "EC_QUOTE" variable
    And I click on the "Salesforce Quote Edited Save" element
    And I wait for "1" seconds
    Then Browser performs "REFRESH" command
    And I wait for "5" seconds
    And I click on the "Salesforce Orders Quick Link Tab on Quote Page" element
    Then I click on the "Salesforce Orders First Link" link
    And I wait for "1" seconds
    Then I execute "Add A Booking" reusable step
    Given I am on "MD_COMMON_LINKS_SALESFORCEINSTANCE" URL
    And I wait for "2" seconds
    And I validate text "EC_AVAILABILITY_NUMBER" to be displayed for "Salesforce Instance Availability Data Field" element
    And I capture text data "Salesforce Instance Availability Data Field" as "EC_MINUSONE_BOOKING" variable
