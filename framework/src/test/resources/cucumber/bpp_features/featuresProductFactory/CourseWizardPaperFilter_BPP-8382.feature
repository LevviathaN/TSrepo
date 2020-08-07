@ProductFactory @Courses
Feature: Course Wizard - add filter to Paper list - BPP-8382

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

  @Positive @P1 #TC-3609, TC-3612, TC-3617, TC-3618
  Scenario: Validate Paper and Course Type Filters in Course Creation Wizard
    When I click on the "Products" "Product Factory navigation item"
    When I click on the "Courses" "Product Factory navigation sub item"
    And I click on the "Create" "Product Factory button"

    And I set "XXX" text to the "Description" "Product Factory text field"
    And I click on the "Search" "Product Factory button"
    And I shouldn't see the "EC_PAPER_DESCRIPTION" "element"
    And I click on the "Clear Filters" "Product Factory button"
    And I set "EC_BODY_SHORT_NAME" text to the "Body" "Product Factory text field"
    And I click on the "Search" "Product Factory button"
    And I should see the "EC_PAPER_DESCRIPTION" "element"
    And I click on the "Clear Filters" "Product Factory button"
    And I set "EC_LEVEL_SHORT_NAME" text to the "Level" "Product Factory text field"
    And I click on the "Search" "Product Factory button"
    And I should see the "EC_PAPER_DESCRIPTION" "element"
    And I click on the "Clear Filters" "Product Factory button"

    And I set "EC_PAPER_DESCRIPTION" text to the "Description" "Product Factory text field"
    And I click on the "Search" "Product Factory button"
    And I click on the "EC_PAPER_DESCRIPTION" "Product Factory select button"
    And I click on the "EC_LEVEL_NAME" "Product Factory select button"
    And I click on the "EC_SITTING_NAME" "Product Factory select button"

    And I set "XXX" text to the "Description" "Product Factory text field two"
    And I click on the "Search" "Product Factory button two"
    And I shouldn't see the "EC_COURSE_TYPE_DESCRIPTION" "element"
    When I click on the "Clear Filters" "Product Factory button two"

    And I set "EC_COURSE_TYPE_DESCRIPTION" text to the "Description" "Product Factory text field two"
    And I click on the "Search" "Product Factory button two"
    And I click on the "EC_COURSE_TYPE_DESCRIPTION" "Product Factory select button"
    And I click on the "EC_REGION_NAME" "Product Factory select button"
    And I click on the "Product Factory Number Of Sessions Edit Button" button
    When I set "1" text to the "Number of Sessions" "Product Factory text field"
    And I click on the "Save" "Product Factory button"
    And I click on the "Finish" "Product Factory button"
    Then I click on the "Create" "Product Factory button"
    And I click on the "Default Session Duration" "Product Factory dropdown"
    And I click on the "EC_SESSION_DURATION_DESCRIPTION" "Product Factory dropdown option"
    And I click on the "Default Location" "Product Factory dropdown"
    And I click on the "EC_LOCATION_NAME" "Product Factory dropdown option"
    And I click on the "Save" "Product Factory button"