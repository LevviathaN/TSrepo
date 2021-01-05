@Reusable
Feature: Product Factory
  As a product setter
  I want to be able to log into Product Factory
  In order to (just cause)

  Scenario: Log In
    Given I am on "MD_COMMON_LINKS_PRODUCTFACTORYURLNEW" URL
    When I click on the "Sign in with Auth0" button
    And I fill the "Product Factory Email" field with "MD_COMMON_CREDENTIALS_PRODUCTFACTORYUSER"
    And I fill the "Product Factory Password" field with "MD_COMMON_CREDENTIALS_PRODUCTFACTORYPASSWORD"
    And I click on the "Product Factory Login" button
#    Then I should be redirected to the "Product Factory" page

  Scenario: Create Body Financial Dimension
    When I click on the "Financials" "Product Factory navigation item"
    When I click on the "Financial Dimensions" "Product Factory navigation sub item"
    Then I click on the "Create" "Product Factory button"
    And I set "BodyFDCode[######]" text to the "Code" "Product Factory text field"
    And I set "BodyFDDescription[######]" text to the "Description" "Product Factory text field"
    And I click on the "Applies To" "Product Factory dropdown"
    And I click on the "Body" "Product Factory dropdown option"
    And I click on the "Dimension" "Product Factory dropdown"
    And I click on the "Product" "Product Factory dropdown option"
    And I click on the "Save" "Product Factory button"
    Then I should see the "EC_BODY_FD_CODE" element

  Scenario: Create Location Financial Dimension
    When I click on the "Financials" "Product Factory navigation item"
    When I click on the "Financial Dimensions" "Product Factory navigation sub item"
    Then I click on the "Create" "Product Factory button"
    And I set "LocationFDCode[####]" text to the "Code" "Product Factory text field"
    And I set "LocationFDDescription[####]" text to the "Description" "Product Factory text field"
    And I click on the "Applies To" "Product Factory dropdown"
    And I click on the "Location" "Product Factory dropdown option"
    And I click on the "Dimension" "Product Factory dropdown"
    And I click on the "Product" "Product Factory dropdown option"
    And I click on the "Save" "Product Factory button"
    Then I should see the "EC_LOCATION_FD_CODE" element

  Scenario: Create Region Financial Dimension
    When I click on the "Financials" "Product Factory navigation item"
    When I click on the "Financial Dimensions" "Product Factory navigation sub item"
    Then I click on the "Create" "Product Factory button"
    And I set "RegionFDCode[######]" text to the "Code" "Product Factory text field"
    And I set "RegionFDDescription[######]" text to the "Description" "Product Factory text field"
    And I click on the "Applies To" "Product Factory dropdown"
    And I click on the "Region" "Product Factory dropdown option"
    And I click on the "Dimension" "Product Factory dropdown"
    And I click on the "Product" "Product Factory dropdown option"
    And I click on the "Save" "Product Factory button"
    Then I should see the "EC_REGION_FD_CODE" element

  Scenario: Create Course Type Financial Dimension
    When I click on the "Financials" "Product Factory navigation item"
    When I click on the "Financial Dimensions" "Product Factory navigation sub item"
    Then I click on the "Create" "Product Factory button"
    And I set "CourseTypeFDCode[######]" text to the "Code" "Product Factory text field"
    And I set "CourseTypeFDDescription[######]" text to the "Description" "Product Factory text field"
    And I click on the "Applies To" "Product Factory dropdown"
    And I click on the "Course Type" "Product Factory dropdown option"
    And I click on the "Dimension" "Product Factory dropdown"
    And I click on the "Product" "Product Factory dropdown option"
    And I click on the "Save" "Product Factory button"
    Then I should see the "EC_COURSE_TYPE_FD_CODE" element

  Scenario: Create Material Type Financial Dimension
    When I click on the "Financials" "Product Factory navigation item"
    When I click on the "Financial Dimensions" "Product Factory navigation sub item"
    Then I click on the "Create" "Product Factory button"
    And I set "MaterialTypeFDCode[######]" text to the "Code" "Product Factory text field"
    And I set "MaterialTypeFDDescription[######]" text to the "Description" "Product Factory text field"
    And I click on the "Applies To" "Product Factory dropdown"
    And I click on the "Material Type" "Product Factory dropdown option"
    And I click on the "Dimension" "Product Factory dropdown"
    And I click on the "Product" "Product Factory dropdown option"
    And I click on the "Save" "Product Factory button"
    Then I should see the "EC_MATERIAL_TYPE_FD_CODE" element

  Scenario: Create Material Financial Dimension
    When I click on the "Financials" "Product Factory navigation item"
    When I click on the "Financial Dimensions" "Product Factory navigation sub item"
    Then I click on the "Create" "Product Factory button"
    And I set "MaterialFDCode[######]" text to the "Code" "Product Factory text field"
    And I set "MaterialFDDescription[######]" text to the "Description" "Product Factory text field"
    And I click on the "Applies To" "Product Factory dropdown"
    And I click on the "Material" "Product Factory dropdown option"
    And I click on the "Dimension" "Product Factory dropdown"
    And I click on the "Product" "Product Factory dropdown option"
    And I click on the "Save" "Product Factory button"
    Then I should see the "EC_MATERIAL_FD_CODE" element

  Scenario: Create Course Financial Dimension
    When I click on the "Financials" "Product Factory navigation item"
    When I click on the "Financial Dimensions" "Product Factory navigation sub item"
    Then I click on the "Create" "Product Factory button"
    And I set "CourseFDCode[######]" text to the "Code" "Product Factory text field"
    And I set "CourseFDDescription[######]" text to the "Description" "Product Factory text field"
    And I click on the "Applies To" "Product Factory dropdown"
    And I click on the "Course" "Product Factory dropdown option"
    And I click on the "Dimension" "Product Factory dropdown"
    And I click on the "Product" "Product Factory dropdown option"
    And I click on the "Save" "Product Factory button"
    Then I should see the "EC_COURSE_FD_CODE" element

  Scenario: Create Body
    When I click on the "Programme" "Product Factory navigation item"
    When I click on the "Bodies" "Product Factory navigation sub item"
    Then I click on the "Create" "Product Factory button"
    And I set "BodyShortName[######]" text to the "Short Name" "Product Factory text field"
    And I set "BodyName[######]" text to the "Name" "Product Factory text field"
    And I click on the "Change" "button"
    And I click on the "EC_BODY_FD_CODE" "Product Factory change modal option"
    And I click on the "Save" "Product Factory button"
    Then I should see the "EC_BODY_SHORT_NAME" element

  Scenario: Create Level
    When I click on the "Programme" "Product Factory navigation item"
    When I click on the "Levels" "Product Factory navigation sub item"
    Then I click on the "Create" "Product Factory button"
    And I set "LevelShortName[######]" text to the "Short Name" "Product Factory text field"
    And I set "LevelName[######]" text to the "Name" "Product Factory text field"
    And I click on the "Save" "Product Factory button"
    Then I should see the "EC_LEVEL_SHORT_NAME" element

  Scenario: Create Paper
    When I click on the "Programme" "Product Factory navigation item"
    When I click on the "Papers" "Product Factory navigation sub item"
    Then I click on the "Create" "Product Factory button"
    And I set "PprSN[#####]" text to the "Short Name" "Product Factory text field"
    And I set "PaperDescription[######]" text to the "Description" "Product Factory text field"
    And I click on the "Save" "Product Factory button"
    And I wait for "5" seconds
    And I click on the "Search" "Product Factory text field"
    Then I set "EC_PAPER_DESCRIPTION" text to the "Search" "Product Factory text field" from keyboard
    And I click on the "submit" "element by type"
    Then I should see the "EC_PAPER_DESCRIPTION" element
    And I click on the "Clear the search text" "element by title"

  Scenario: Link Body To Paper
    When I click on the "Programme" "Product Factory navigation item"
    When I click on the "Papers" "Product Factory navigation sub item"
    Then I set "EC_PAPER_DESCRIPTION" text to the "Search" "Product Factory text field" from keyboard
    And I click on the "submit" "element by type"
    When I click on the "EC_PAPER_DESCRIPTION" "Product Factory Change Body button"
    And I click on the "EC_BODY_NAME" "Product Factory select button"
    Then I click on the "Yes" "Product Factory button"
    And I click on the "Clear the search text" "element by title"

  Scenario: Link Paper To Level
    When I click on the "Programme" "Product Factory navigation item"
    When I click on the "Papers" "Product Factory navigation sub item"
    Then I set "EC_PAPER_DESCRIPTION" text to the "Search" "Product Factory text field" from keyboard
    And I click on the "submit" "element by type"
    When I click on the "EC_PAPER_DESCRIPTION" "Product Factory Link Levels button"
    And I "check" "EC_LEVEL_NAME" "Product Factory dialog checkbox"
    And I click on the "Save" "Product Factory button"
    And I click on the "Clear the search text" "element by title"

  Scenario: Create Sitting
    When I click on the "Delivery" "Product Factory navigation item"
    When I click on the "Sittings" "Product Factory navigation sub item"
    Then I click on the "Create" "Product Factory button"
    And I set "SittingName[######]" text to the "Name" "Product Factory text field"
    And I remember "KW_AUTO_TODAY|DD/MM/YYYY" text as "EC_SITTING_START_DATE" variable
    And I set "EC_SITTING_START_DATE" text to the "Start Date" "Product Factory text field"
    And I remember "10/10/2025" text as "EC_SITTING_END_DATE" variable
    And I set "EC_SITTING_END_DATE" text to the "End Date" "Product Factory text field"
    And I "check" "EC_BODY_NAME" "Product Factory dialog checkbox"
    And I click on the "Save" "Product Factory button"
    Then I should see the "EC_SITTING_NAME" element

  Scenario: Create Location
    When I click on the "Delivery" "Product Factory navigation item"
    When I click on the "Locations" "Product Factory navigation sub item"
    Then I click on the "Create" "Product Factory button"
    And I set "LocationName[######]" text to the "Name" "Product Factory text field"
    And I set "LocationAddress[######]" text to the "Address Line 1" "Product Factory text field"
    And I click on the "Region" "Product Factory dropdown"
    And I click on the "EC_REGION_NAME" "Product Factory dropdown option"
    And I click on the "Financial Dimension" "Product Factory dropdown"
    And I click on the "EC_LOCATION_FD_DESCRIPTION" "Product Factory dropdown option"
    And I click on the "Save" "Product Factory button"
    Then I should see the "EC_LOCATION_NAME" element

  Scenario: Create Region
    When I click on the "Delivery" "Product Factory navigation item"
    When I click on the "Region" "Product Factory navigation sub item"
    Then I click on the "Create" "Product Factory button"
    And I set "RegionName[######]" text to the "Name" "Product Factory text field"
    And I click on the "Financial Dimension" "Product Factory dropdown"
    And I click on the "EC_REGION_FD_DESCRIPTION" "Product Factory dropdown option"
    And I click on the "Save" "Product Factory button"
    Then I should see the "EC_REGION_NAME" element

  Scenario: Create Session Duration
    When I click on the "Delivery" "Product Factory navigation item"
    When I click on the "Session Durations" "Product Factory navigation sub item"
    Then I click on the "Create" "Product Factory button"
    And I set "SessionDurationDescription[######]" text to the "Description" "Product Factory text field"
    And I set "1" text to the "Number of Dates" "Product Factory text field"
    And I click on the "Save" "Product Factory button"
    Then I should see the "EC_SESSION_DURATION_DESCRIPTION" element

  Scenario: Create Course Type
    When I click on the "Types" "Product Factory navigation item"
    When I click on the "Course Types" "Product Factory navigation sub item"
    Then I click on the "Create" "Product Factory button"
    And I set "CourseTypeDescription[######]" text to the "Description" "Product Factory text field"
    And I click on the "VAT Rule" "Product Factory dropdown"
    And I click on the "EC_VAT_RULE_CODE" "Product Factory dropdown option"
    And I click on the "Cost Centre Financial Dimension" "Product Factory dropdown"
    And I click on the "EC_COURSE_TYPE_FD_CODE" "Product Factory dropdown option"
    And I click on the "Project Financial Dimension" "Product Factory dropdown"
    And I click on the "EC_COURSE_TYPE_FD_CODE" "Product Factory dropdown option"
    And I click on the "Save" "Product Factory button"
    And I wait for "3" seconds
    Then I set "EC_COURSE_TYPE_DESCRIPTION" text to the "Search" "Product Factory text field" from keyboard
    And I click on the "submit" "element by type"
    Then I should see the "EC_COURSE_TYPE_DESCRIPTION" element
    And I click on the "Clear the search text" "element by title"

  Scenario: Create Material Type
    When I click on the "Types" "Product Factory navigation item"
    When I click on the "Material Types" "Product Factory navigation sub item"
    Then I click on the "Create" "Product Factory button"
    And I set "MaterialTypeName[######]" text to the "Name" "Product Factory text field"
    And I set "MaterialTypeDescription[######]" text to the "Description" "Product Factory text field"
    And I click on the "Financial Dimension" "Product Factory change button"
    And I click on the "EC_MATERIAL_TYPE_FD_CODE" "Product Factory select button"
    And I click on the "Save" "Product Factory button"
    Then I should see the "EC_MATERIAL_TYPE_DESCRIPTION" element

  Scenario: Create Client
    When I click on the "Miscellaneous" "Product Factory navigation item"
    When I click on the "Clients" "Product Factory navigation sub item"
    Then I click on the "Create" "Product Factory button"
    And I set "ClientName[######]" text to the "Name" "Product Factory text field"
    And I click on the "Save" "Product Factory button"
    Then I should see the "EC_CLIENT_NAME" element

  Scenario: Create Stream
    When I click on the "Miscellaneous" "Product Factory navigation item"
    When I click on the "Streams" "Product Factory navigation sub item"
    Then I click on the "Create" "Product Factory button"
    And I set "StreamName[######]" text to the "Name" "Product Factory text field"
    And I click on the "Save" "Product Factory button"
    Then I should see the "EC_STREAM_NAME" element

  Scenario: Create Deactivation Reason
    When I click on the "Miscellaneous" "Product Factory navigation item"
    When I click on the "Deactivation Reasons" "Product Factory navigation sub item"
    Then I click on the "Create" "Product Factory button"
    And I set "DeactivationReasonDescription[######]" text to the "Description" "Product Factory text field"
    And I click on the "Save" "Product Factory button"
    Then I should see the "EC_DEACTIVATION_REASON_DESCRIPTION" element

  Scenario: Create Pricing Matrix
    When I click on the "Financials" "Product Factory navigation item"
    When I click on the "Prices" "Product Factory navigation sub item"
    Then I click on the "Create" "Product Factory button"
    And I wait for "2" seconds
    And I click on the "Body" "Product Factory change button"
    And I click on the "EC_BODY_NAME" "Product Factory change modal option"
    And I click on the "Sitting" "Product Factory change button"
    And I click on the "EC_SITTING_NAME" "Product Factory change modal option"
    And I click on the "Save" "Product Factory button"
    Then I should see the "EC_BODY_NAME" element

  Scenario: Create Pricing Matrix Full
    When I click on the "Financials" "Product Factory navigation item"
    When I click on the "Prices" "Product Factory navigation sub item"
    When I click on the "EC_BODY_NAME" "Product Factory edit button"
    Then I click on the "Create" "Product Factory button"
    Then I set "EC_COURSE_TYPE_DESCRIPTION" text to the "Search" "Product Factory text field" from keyboard
    And I click on the "submit" "element by type"
    And I click on the "EC_COURSE_TYPE_DESCRIPTION" "Product Factory select button"
    And I "check" "EC_REGION_NAME" "Product Factory dialog checkbox"
    And I click on the "Next" button
    And I click on the "EC_PAPER_DESCRIPTION" "Product Factory dialog checkbox" by JS
    And I click on the "Next" button
    And I set "~PricingMatrixPrice[####]" text to the "Enter a Price (£)" "Product Factory text field"
    And I click on the "Next" button
    And I click on the "Finish" button

  Scenario: Create VAT Rule
    When I click on the "Financials" "Product Factory navigation item"
    When I click on the "VAT Rules" "Product Factory navigation sub item"
    Then I click on the "Create" "Product Factory button"
    And I set "VatRuleCode[######]" text to the "Code" "Product Factory text field"
    And I set "VatRuleDescription[######]" text to the "Description" "Product Factory text field"
    And I click on the "Save" "Product Factory button"
    Then I should see the "EC_VAT_RULE_CODE" element

  Scenario: Link Body To Level
    When I click on the "Programme" "Product Factory navigation item"
    When I click on the "Bodies" "Product Factory navigation sub item"
    Then I click on the "EC_BODY_NAME" "Product Factory Link Levels button"
    And I "check" "EC_LEVEL_NAME" "Product Factory dialog checkbox"
    And I click on the "Save" "Product Factory button"

  Scenario: Create Material
    When I click on the "Products" "Product Factory navigation item"
    When I click on the "Materials" "Product Factory navigation sub item"
    And I click on the "Create" "Product Factory button"
    And I click on the "BPP Learning Media" "Product Factory button"
    And I click on the "EC_MATERIAL_TYPE_NAME" "Product Factory select button"
    And I set "~Price[####]" text to the "Price (£)" "Product Factory text field"
    And I set "~Weight[##]" text to the "Weight (kg)" "Product Factory text field"
    And I set "EC_ISBN" text to the "ISBN" "Product Factory text field"
    And I set "From[TODAY]" text to the "Available From" "Product Factory text field"
    And I set "10/10/2022" text to the "Expiry Date" "Product Factory text field"
    And I set "1020" text to the "Edition" "Product Factory text field"
    And I click on the "Production Method" "Product Factory dropdown"
    And I click on the "Not Applicable" "Product Factory dropdown option"
    And I click on the "Learning Media VAT Rule" "Product Factory change button"
    And I click on the "EC_VAT_RULE_CODE" "Product Factory select button"
    And I click on the "Course Material VAT Rule" "Product Factory change button"
    And I click on the "EC_VAT_RULE_CODE" "Product Factory select button"
    And I click on the "Next" "Product Factory button"
    And I click on the "Choose a Body" "button"
    And I click on the "EC_BODY_NAME" "Product Factory select button"
    And I click on the "Next" "Product Factory button"
    And I "check" "EC_LEVEL_NAME" "Product Factory checkbox"
    And I click on the "Next" "Product Factory button"
    And I "check" "EC_SITTING_NAME" "Product Factory checkbox"
    And I click on the "Next" "Product Factory button"
    And I click on the "Finish" "Product Factory button"

  Scenario: Create Course Instance
    When I click on the "Products" "Product Factory navigation item"
    When I click on the "Courses" "Product Factory navigation sub item"
    And I click on the "Create" "Product Factory button"
#    And I click on the "Search" "Product Factory text field"
    Then I set "EC_PAPER_DESCRIPTION" text to the "Search" "Product Factory text field" from keyboard
    And I click on the "submit" "element by type"
    And I click on the "EC_PAPER_DESCRIPTION" "Product Factory select button" by JS
    And I click on the "EC_LEVEL_NAME" "Product Factory select button"
    And I click on the "EC_SITTING_NAME" "Product Factory select button"
#    And I click on the "Search" "Product Factory text field two"
    Then I set "EC_COURSE_TYPE_DESCRIPTION" text to the "Search" "Product Factory text field two" from keyboard
    And I click on the "submit" "element by type two"
    And I click on the "EC_COURSE_TYPE_DESCRIPTION" "Product Factory select button" by JS
    And I click on the "EC_REGION_NAME" "Product Factory select button"
    And I click on the "Product Factory Number Of Sessions Edit Button" button
    When I set "1" text to the "Number of Sessions" "Product Factory text field"
    And I click on the "Save" "Product Factory button"
    And I click on the "Finish" "Product Factory button"
    #todo: separate Course and Course Instance creation
    Then I click on the "Create" "Product Factory button"
    And I click on the "Default Session Duration" "Product Factory dropdown"
    And I click on the "EC_SESSION_DURATION_DESCRIPTION" "Product Factory dropdown option"
    And I click on the "Default Location" "Product Factory dropdown"
    And I click on the "EC_LOCATION_NAME" "Product Factory dropdown option"
    And I click on the "Save" "Product Factory button"

  Scenario: Clean Database
    Given I am on "MD_COMMON_LINKS_PRODUCTFACTORYDATABASEURLNEW" URL
    When I fill the "Product Factory Database Connect URL Field" field with "bolt://ec2-3-10-141-78.eu-west-2.compute.amazonaws.com:7687"
    Then I fill the "Product Factory Database Username Field" field with "MD_COMMON_LINKS_PRODUCTFACTORYDATABASEUSERNEW"
    And I fill the "Product Factory Database Password Field" field with "MD_COMMON_LINKS_PRODUCTFACTORYDATABASEPASSWORDNEW"
    And I click on the "Product Factory Database Connect Button" button
    When I click on the "Product Factory Database Query Field" button
    And I fill the "Product Factory Database Query Field" field with "MATCH (n) DETACH DELETE n"
    Then I click on the "Product Factory Database Submit Query Button" button

  Scenario: System Settings
    When I click on the "Financials" "Product Factory navigation item"
    When I click on the "Financial Dimensions" "Product Factory navigation sub item"
    Then I click on the "Create" "Product Factory button"
    And I set "FDCode[######]" text to the "Code" "Product Factory text field"
    And I set "FDDescription[######]" text to the "Description" "Product Factory text field"
    And I click on the "Applies To" "Product Factory dropdown"
    And I click on the "Course" "Product Factory dropdown option"
    And I click on the "Save" "Product Factory button"
    Then I should see the "EC_FD_CODE" element
    When I click on the "Products" "Product Factory navigation item"
    When I click on the "Courses" "Product Factory navigation sub item"
    And I click on the "Entity Dimension" "Product Factory change button"
    And I click on the "EC_FD_CODE" "Product Factory select button"
    And I click on the "Revenue Dimension" "Product Factory change button"
    And I click on the "EC_FD_CODE" "Product Factory select button"
    And I click on the "Save" "Product Factory button"

    When I click on the "Financials" "Product Factory navigation item"
    When I click on the "Financial Dimensions" "Product Factory navigation sub item"
    Then I click on the "Create" "Product Factory button"
    And I set "FDCode[######]" text to the "Code" "Product Factory text field"
    And I set "FDDescription[####]" text to the "Description" "Product Factory text field"
    And I click on the "Applies To" "Product Factory dropdown"
    And I click on the "Material" "Product Factory dropdown option"
    And I click on the "Save" "Product Factory button"
    Then I should see the "EC_FD_CODE" element
    When I click on the "Products" "Product Factory navigation item"
    When I click on the "Materials" "Product Factory navigation sub item"
    And I click on the "Entity Dimension" "Product Factory change button"
    And I click on the "EC_FD_CODE" "Product Factory select button"
    And I click on the "Revenue Dimension" "Product Factory change button"
    And I click on the "EC_FD_CODE" "Product Factory select button"
    And I click on the "Save" "Product Factory button"

  Scenario: Create CBA Record
    When I click on the "Products" "Product Factory navigation item"
    When I click on the "CBAs" "Product Factory navigation sub item"
    And I click on the "Create" "Product Factory button"
    And I click on the "EC_SITTING_NAME" "Product Factory select button"
    And I click on the "EC_SESSION_DURATION_DESCRIPTION" "Product Factory select button"
    And I click on the "Region" "Product Factory dropdown"
    And I click on the "EC_REGION_NAME" "element"
    And I click on the "Location" "Product Factory dropdown"
    And I click on the "EC_LOCATION_NAME" "element"
    Then I set "EC_COURSE_TYPE_DESCRIPTION" text to the "Search" "Product Factory text field" from keyboard
    And I click on the "submit" "element by type"
    And I click on the "EC_COURSE_TYPE_DESCRIPTION" "Product Factory select button"
    Then I should see the "EC_SITTING_NAME" element
    Then I should see the "EC_SESSION_DURATION_DESCRIPTION" element
    Then I should see the "EC_REGION_NAME" element
    Then I should see the "EC_LOCATION_NAME" element
    Then I should see the "EC_COURSE_TYPE_DESCRIPTION" element
    And I set "[TODAY]" text to the "Date" "Product Factory text field"
    And I set "01:00AM" text to the "Start Time" "Product Factory text field"
    And I set "1" text to the "Capacity" "Product Factory text field"
    And I click on the "Finish" "Product Factory button"
    And I click on the "Default Institute Fee (£)" "Product Factory edit button"
    And I set "~DefaultInstituteFee[###]" text to the "Default Institute Fee (£)" "Product Factory text field"
    And I click on the "Save" "Product Factory button"
    When I click on the "Clients" "Product Factory button"
    And I click on the "Edit" "Product Factory button"
    And I "check" "EC_CLIENT_NAME" "Product Factory checkbox"
    And I click on the "Save" "Product Factory button"
    And I click on the "Courses" "Product Factory button"

  Scenario: Create CBA Course
    When I click on the "Products" "Product Factory navigation item"
    When I click on the "CBAs" "Product Factory navigation sub item"
    Then I set "EC_COURSE_TYPE_DESCRIPTION" text to the "Search" "Product Factory text field" from keyboard
    And I click on the "submit" "element by type"
    And I click on the "EC_COURSE_TYPE_DESCRIPTION" "Product Factory edit button"
    When I click on the "Create" "Product Factory button"
    And I "check" "EC_BODY_SHORT_NAME" "Product Factory checkbox"
    And I click on the "Next" "Product Factory button"
    And I click on the "Next" "Product Factory button"
    Then I should see the "EC_BODY_SHORT_NAME" element
    Then I should see the "EC_PAPER_DESCRIPTION" element
    Then I should see the "EC_LEVEL_SHORT_NAME" element
    And I click on the "Finish" "Product Factory button"

  Scenario: Create Digital Content
    When I click on the "Delivery" "Product Factory navigation item"
    When I click on the "Digital Content" "Product Factory navigation sub item"
    Then I click on the "Create" "Product Factory button"
    And I click on the "Body" "Product Factory dropdown"
    And I click on the "EC_BODY_NAME" "Product Factory dropdown option"
    And I click on the "Level" "Product Factory dropdown"
    And I click on the "EC_LEVEL_NAME" "Product Factory dropdown option"
    And I click on the "Paper" "Product Factory dropdown"
    And I click on the "EC_PAPER_DESCRIPTION" "Product Factory dropdown option"
    And I click on the "Sittings" "Product Factory edit button"
    And I "check" "EC_SITTING_NAME" "Product Factory dialog checkbox"
    And I click on the "Save" "Product Factory active button"
    And I click on the "Course Types" "Product Factory edit button"
    Then I set "EC_COURSE_TYPE_DESCRIPTION" text to the "Search" "Product Factory text field" from keyboard
    And I click on the "submit" "element by type"
    And I "check" "EC_COURSE_TYPE_DESCRIPTION" "Product Factory dialog checkbox"
    And I click on the "Save" "Product Factory active button"
    And I click on the "Save" "Product Factory button"

  Scenario: Generate ISBN
    Given I am on "https://generate.plus/en/number/isbn" URL
    And I click on the " generate" "button"
    And I capture text data "ISBN Generator Number Field" as "EC_ISBN" variable