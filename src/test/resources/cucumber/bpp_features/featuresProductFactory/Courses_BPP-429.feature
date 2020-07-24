@ProductFactory @Courses
Feature: Create Course Instance - BPP-429
  As a Product Setter
  I need to be able to create a Course based on the Reference Data
  so that I can sell, schedule and publish a Course.

  Background:
    Given I execute "Log In" reusable step
    When I execute "Create Body Financial Dimension" reusable step
    And I execute "Create Body" reusable step
    And I execute "Create VAT Rule" reusable step
    And I execute "Create Course Type Financial Dimension" reusable step
    And I execute "Create Course Type" reusable step
    And I execute "Create Location Financial Dimension" reusable step
    And I execute "Create Region Financial Dimension" reusable step
    And I execute "Create Region" reusable step
    And I execute "Create Location" reusable step
    And I execute "Create Level" reusable step
    And I execute "Create Paper" reusable step
    And I execute "Link Body To Level" reusable step
    And I execute "Link Body To Paper" reusable step
    And I execute "Link Paper To Level" reusable step
    And I execute "Create Sitting" reusable step
    And I execute "Create Pricing Matrix" reusable step
    And I execute "Create Pricing Matrix Full" reusable step
    And I execute "Create Material Type Financial Dimension" reusable step
    And I execute "Create Material Type" reusable step
    And I execute "Create Session Duration" reusable step
    And I execute "Create Client" reusable step
    And I execute "Create Material" reusable step

  @Positive @P1 #TC-696, TC-2793, TC-2794, TC-2769, TC-2382, TC-2402, TC-2403
  Scenario: Create Course Instance
    When I execute "Create Digital Content" reusable step
    When I click on the "Products" "Product Factory navigation item"
    When I click on the "Courses" "Product Factory navigation sub item"
    And I click on the "Create" "Product Factory button"
    And I set "EC_PAPER_DESCRIPTION" text to the "Description" "Product Factory text field"
    And I click on the "Search" "Product Factory button"
    And I click on the "EC_PAPER_DESCRIPTION" "Product Factory select button"
    And I click on the "EC_LEVEL_NAME" "Product Factory select button"
    And I click on the "EC_SITTING_NAME" "Product Factory select button"
    And I set "EC_COURSE_TYPE_DESCRIPTION" text to the "Description" "Product Factory text field two"
    And I click on the "Search" "Product Factory button two"
    And I click on the "EC_COURSE_TYPE_DESCRIPTION" "Product Factory select button"
    And I click on the "EC_REGION_NAME" "Product Factory select button"
    When I click on the "Finish" "Product Factory button"
    Then I should see the "Number of Sessions must be Greater than 0" message
    And I click on the "Product Factory Number Of Sessions Edit Button" button
    When I set "1" text to the "Number of Sessions" "Product Factory text field"
    And I click on the "Save" "Product Factory button"
    And I click on the "Finish" "Product Factory button"
    Then I click on the "Create" "Product Factory button"
    And I validate text "CONTAINS=1" to be displayed for ": " element
    And I click on the "Default Session Duration" "Product Factory dropdown"
    And I click on the "EC_SESSION_DURATION_DESCRIPTION" "Product Factory dropdown option"
    And I click on the "Default Location" "Product Factory dropdown"
    And I click on the "EC_LOCATION_NAME" "Product Factory dropdown option"
    And I click on the "Save" "Product Factory button"
    And I click on the "EC_LOCATION_NAME" "Product Factory course instance Delete button"
    And I click on the "Yes" "Product Factory button"
    Then I shouldn't see the "EC_LOCATION_NAME" "element"
    Then I click on the "Create" "Product Factory button"
    And I validate text "CONTAINS=1" to be displayed for ": " element
    And I click on the "Default Session Duration" "Product Factory dropdown"
    And I click on the "EC_SESSION_DURATION_DESCRIPTION" "Product Factory dropdown option"
    And I click on the "Default Location" "Product Factory dropdown"
    And I click on the "EC_LOCATION_NAME" "Product Factory dropdown option"
    And I click on the "Save" "Product Factory button"
    When I click on the "EC_LOCATION_NAME" "Product Factory course instance sessions dropdown button"
    And I click on the "EC_LOCATION_NAME" "Product Factory Session Dates button"
    And I fill the "Product Factory Session Dates Popup Date Input Field" field with "EC_SITTING_START_DATE"
    And I fill the "Product Factory Session Dates Popup Start Time Input Field" field with "03:00AM"
    And I fill the "Product Factory Session Dates Popup End Time Input Field" field with "04:00AM"
    And I click on the "Next" "Product Factory button"
    And I click on the "Finish" "Product Factory button"
    Then I click on the "Activate" "Product Factory button"
    And I should see the "Deactivate" "Product Factory button"
    And I click on the "EC_LOCATION_NAME" "Product Factory course instance Activate button"
    And I shouldn't see the "EC_LOCATION_NAME" "Product Factory course instance Delete button"