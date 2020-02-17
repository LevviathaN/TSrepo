@Reusable
Feature: Salesforce

  Scenario: Log In To Salesforce
    Given I am on "MD_COMMON_LINKS_SALESFORCEURL" URL
    Then I fill the "Salesforce Username" field with "MD_COMMON_CREDENTIALS_BPPADMINUSER"
    And I fill the "Salesforce Password" field with "MD_COMMON_CREDENTIALS_BPPADMINPASSWORD"
    And I click on the "Salesforce Login" button
    Then I should see the "Salesforce Context Bar Menu" element

  Scenario: Log Out Salesforce
    And I click on the "Salesforce User Menu Icon" button
    And I click on the "Salesforce Log Out" button
    
  Scenario: Switch to Sales App
    Given I click on the "Salesforce App Launcher" button
    When I click on the "View All" link
    And I click on the "Sales" "Salesforce app launcher app"

  Scenario: Create Student Account
    When I click on the "Accounts" "Salesforce navigation menu dropdown"
    And I click on the "New Account" "Salesforce navigation menu dropdown option"
    And I click on the "Student Account" "Salesforce radiobutton"
    And I click on the "Next" "button"
    And I set "Auto[FIRSTNAME]" text to the "First Name" "Salesforce text field"
    And I set "Auto[LASTNAME]" text to the "Last Name" "Salesforce text field"
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
    And I click on the "Legal Entity" "Salesforce dropdown field"
    And I click on the "UC~COL" "Salesforce dropdown field option"
    And I click on the "Preferred Email" "Salesforce dropdown field"
    And I click on the "Personal" "Salesforce dropdown field option"
    And I set "Auto[EMAIL-HARAKIRI]" text to the "Personal Email" "Salesforce text field"
    And I click on the "Save" "button"
#    Then I should see the " was created." message

  Scenario: Create Address
    And I click on the "Addresses" "Salesforce related new button"
    And I click on the "Address Type" "Salesforce dropdown field"
    And I click on the "Billing" "option"
    And I set "KW_AUTO_STREET" text to the "Address Line 1" "Salesforce text field"
    And I set "KW_AUTO_CITYUSA" text to the "City" "Salesforce text field"
    And I set "KW_AUTO_ZIPCODE|#####" text to the "Postal Code" "Salesforce text field"
    And I set "United States" text to the "Country" "Salesforce search field"
    And I click on the "States" "option"
    And I click on the "Save" "button"
#    And I wait for "2" seconds
#    Then I should see the " was created." message

  Scenario: Get Profile ID
    And I click on the "Salesforce More Actions Dropdown" button
    And I click on the "Get Profile ID LEX" "Salesforce dropdown option"
    Then I should see the "Profile Id created Successfully" message

  Scenario: Create Opportunity
    And I click on the "Opportunities" "Salesforce related new button"
    And I click on the "Application" "Salesforce radiobutton"
    And I click on the "Next" "button"
    And I set "OpportunityName[###]" text to the "Opportunity Name" "Salesforce text field"
    And I click on the "Status" "Salesforce dropdown field"
    And I click on the "Accepted - Conditional Firm" "Salesforce dropdown field option"
    And I click on the "Customer Group" "Salesforce dropdown field"
    And I click on the "SELFFUND" "Salesforce dropdown field option"
    And I click on the "Application Type" "Salesforce dropdown field"
    And I click on the "University Application" "Salesforce dropdown field option"
    And I set "31/01/2022" text to the "Close Date" "Salesforce date field"
    And I click on the "Save" "button"
#    And I should see the " was created." message

  # This reusable scenario is not working, because its steps are not treated as separate Gherkin steps, so AfterStep
  # method that switches to default content is not called for two serial inframe elements, so the second element's parent
  # xpath cannot be found as the focus of the test is still inside the first element's iframe
  Scenario: Add Product Catalog Item
    And I click on the "EC_AUTO_LASTNAME" "Salesforce link"
    And I click on the "Product Catalog Items" "Salesforce related new button"
    And I click on the "New" "Salesforce dropdown option"
    And I fill the "Salesforce Offering Text Field" field with "2381626"
    And I click on the "Salesforce Search" button
    And I "check" "Salesforce Offering Checkbox" checkbox
    And I "check" "Salesforce Product Checkbox" checkbox
    And I click on the "Salesforce Add Products" button
    And I wait for "4" seconds
#    And I should see the "MSc Management CRO PTD 201819 for student application" element

  Scenario: Submit Application
    And I click on the "Salesforce More Actions Dropdown" button
    And I click on the "Submit Application LEX" "Salesforce dropdown option"
    And I click on the "Salesforce Account Name Link" element
    And I click on the "Salesforce Account Details Tab" element
    And I validate text "STARTS-WITH=BP" to be displayed for "Salesforce Banner ID Field" element
    And I capture text data "Salesforce Banner ID Field" as "EC_BANNER_ID" variable
    And I capture text data "Salesforce Profile Services ID Field" as "EC_PROFILE_ID" variable

  Scenario: Create And Validate CSA Cases Query Types and Subtypes
    When I click on the "Cases" "Salesforce navigation menu dropdown"
    And I click on the "New Case" "Salesforce navigation menu dropdown option"
    And I click on the "Customer Service" "Salesforce radiobutton"
    And I click on the "Next" "button"
    And I set "Automation CSA" text to the "Student" "Salesforce search field"
    And I click on the "Automation CSA" "Salesforce cases search student"
    And I click on the "Case Origin" "Salesforce dropdown field"
    And I click on the "Email" "Salesforce cases dropdown option"
    And I click on the "Query Type" "Salesforce dropdown field"
    And I click on the "My course" "Salesforce cases dropdown option"
    And I click on the "Query Subtype" "Salesforce dropdown field"
    Then I should see the "I have a question about my course" "Salesforce cases dropdown option"
    Then I should see the "I have a question about the achievement ladder" "Salesforce cases dropdown option"
    Then I should see the "I have a question about my exam" "Salesforce cases dropdown option"
    Then I should see the "I need to defer my course" "Salesforce cases dropdown option"
    Then I should see the "What is the eligibility for Pass Assurance?" "Salesforce cases dropdown option"
    Then I should see the "I need to request a reference" "Salesforce cases dropdown option"
    Then I should see the "I have missed my assessment deadline" "Salesforce cases dropdown option"
    Then I should see the "I need to extend my assessment deadline" "Salesforce cases dropdown option"
    Then I should see the "I have a question about my mark for my mock/assessment" "Salesforce cases dropdown option"
    Then I should see the "I haven’t received my exam results" "Salesforce cases dropdown option"
    Then I should see the "I need further support with my studies" "Salesforce cases dropdown option"
    Then I should see the "I need to access learning support advice" "Salesforce cases dropdown option"
    Then I should see the "I have a specialist learning need, which I need additional support for" "Salesforce cases dropdown option"
    Then I should see the "I need to change my course" "Salesforce cases dropdown option"
    Then I should see the "I need to change my study mode" "Salesforce cases dropdown option"
    Then I should see the "I need to change my study location" "Salesforce cases dropdown option"
    And I click on the "Query Type" "Salesforce dropdown field"
    And I click on the "My online learning and VLE" "Salesforce cases dropdown option"
    And I click on the "Query Subtype" "Salesforce dropdown field"
    Then I should see the "I haven’t received my log in details" "Salesforce cases dropdown option"
    Then I should see the "I need to extend my online access" "Salesforce cases dropdown option"
    Then I should see the "How do I access my account?" "Salesforce cases dropdown option"
    Then I should see the "I haven’t received a link to my live course" "Salesforce cases dropdown option"
    Then I should see the "I can’t see my course on the VLE" "Salesforce cases dropdown option"
    Then I should see the "I need to remove old papers from my VLE" "Salesforce cases dropdown option"
    Then I should see the "I have a different problem with my VLE" "Salesforce cases dropdown option"
    Then I should see the "My account has been locked" "Salesforce cases dropdown option"
    And I click on the "Query Type" "Salesforce dropdown field"
    And I click on the "My study materials" "Salesforce cases dropdown option"
    And I click on the "Query Subtype" "Salesforce dropdown field"
    Then I should see the "When will my study materials be delivered?" "Salesforce cases dropdown option"
    Then I should see the "I haven’t received my study materials" "Salesforce cases dropdown option"
    Then I should see the "When will my study materials be back in stock?" "Salesforce cases dropdown option"
    Then I should see the "How do I access my EBook?" "Salesforce cases dropdown option"
    Then I should see the "My Ebook has expired" "Salesforce cases dropdown option"
    Then I should see the "Where can I find the link to my EBook?" "Salesforce cases dropdown option"
    Then I should see the "I would like to return my study materials" "Salesforce cases dropdown option"
    Then I should see the "My activation code isn’t working" "Salesforce cases dropdown option"
    Then I should see the "I have a question about a Customs Charge" "Salesforce cases dropdown option"
    Then I should see the "Which variants do BPP offer?" "Salesforce cases dropdown option"
    And I click on the "Query Type" "Salesforce dropdown field"
    And I click on the "Feedback" "Salesforce cases dropdown option"
    And I click on the "Query Subtype" "Salesforce dropdown field"
    Then I should see the "I have some feedback about my course" "Salesforce cases dropdown option"
    Then I should see the "I have some feedback about my tutor" "Salesforce cases dropdown option"
    Then I should see the "I have some feedback about my study centre/location" "Salesforce cases dropdown option"
    Then I should see the "I have some feedback about my recent experience" "Salesforce cases dropdown option"
    And I click on the "Query Type" "Salesforce dropdown field"
    And I click on the "Finance and billing" "Salesforce cases dropdown option"
    And I click on the "Query Subtype" "Salesforce dropdown field"
    Then I should see the "My invoice address is incorrect" "Salesforce cases dropdown option"
    Then I should see the "My company discount hasn’t been applied to my order" "Salesforce cases dropdown option"
    Then I should see the "The Purchase Order wasn’t included in my invoice" "Salesforce cases dropdown option"
    Then I should see the "When will I receive my refund?" "Salesforce cases dropdown option"
    Then I should see the "I need my invoice to be re-sent to me" "Salesforce cases dropdown option"
    Then I should see the "I need my receipt to be re-sent to me" "Salesforce cases dropdown option"
    Then I click on the "My invoice address is incorrect" "Salesforce cases dropdown option"
    And I click on the "Salesforce CS Ready to Close" element
    And I click on the "Save" "Product Factory button title"
    And I wait for "3" seconds
    And I validate text "CONTAINS=Customer Service Team" to be displayed for "Salesforce Case Owner Data Field" element
    And I capture text data "Salesforce Case Number Data Field" as "EC_CASENUMBER" variable

  Scenario: Create CSA Case My Course
    When I click on the "Cases" "Salesforce navigation menu dropdown"
    And I click on the "New Case" "Salesforce navigation menu dropdown option"
    And I click on the "Customer Service" "Salesforce radiobutton"
    And I click on the "Next" "button"
    And I set "Automation CSA" text to the "Student" "Salesforce search field"
    And I click on the "Automation CSA" "Salesforce cases search student"
    And I click on the "Case Origin" "Salesforce dropdown field"
    And I click on the "Email" "Salesforce cases dropdown option"
    And I click on the "Query Type" "Salesforce dropdown field"
    And I click on the "My course" "Salesforce cases dropdown option"
    And I click on the "Query Subtype" "Salesforce dropdown field"
    Then I click on the "I need to defer my course" "Salesforce cases dropdown option"
    And I click on the "Salesforce CS Ready to Close" element
    And I click on the "Save" "Product Factory button title"
    And I wait for "3" seconds
    And I validate text "CONTAINS=Customer Service Team" to be displayed for "Salesforce Case Owner Data Field" element
    And I capture text data "Salesforce Case Number Data Field" as "EC_CASENUMBER" variable

  Scenario: Search Case by Number
    And I fill the "Salesforce Main Header Search" field with "EC_CASENUMBER"
    And I press "MD_COMMON_KEYBOARD_ENTER" for "Salesforce Main Header Search"
    And I wait for "2" seconds
    And I click on the "Salesforce First Case Number Search Table" element
    And I click on the "Salesforce First Case Number" element

  Scenario: Create ID Card Registry Case
    When I click on the "Cases" "Salesforce navigation menu dropdown"
    And I click on the "New Case" "Salesforce navigation menu dropdown option"
    And I click on the "Student Query" "Salesforce radiobutton"
    And I click on the "Next" "button"
    And I click on the "Study Location" "Salesforce dropdown field"
    And I click on the "Case Origin" "Salesforce dropdown field"
    And I click on the "Face-to-Face" "Salesforce cases dropdown option"
    And I click on the "Query Type" "Salesforce dropdown field"
    And I click on the "ID Card" "Salesforce cases dropdown option"
    And I click on the "Query Subtype" "Salesforce dropdown field"
    Then I click on the "Lost" "Salesforce cases dropdown option"
    And I click on the "Study Location" "Salesforce dropdown field"
    Then I click on the "Leeds" "Salesforce cases dropdown option"
    And I click on the "School" "Salesforce dropdown field"
    Then I click on the "Law" "Salesforce cases dropdown option"
    And I click on the "Save" "Product Factory button title"
    And I wait for "3" seconds
    And I validate text "CONTAINS=Student Records" to be displayed for "Salesforce Case Owner Data Field" element
    And I capture text data "Salesforce Case Number Data Field" as "EC_CASENUMBER" variable