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
    And I execute "Create Sitting" reusable step
    And I execute "Create Pricing Matrix" reusable step
    And I execute "Create Pricing Matrix Full" reusable step
    And I execute "Create Material Type Financial Dimension" reusable step
    And I execute "Create Material Type" reusable step
    And I execute "Create Session Duration" reusable step
    And I execute "Create Client" reusable step
    And I execute "Create Material" reusable step

  @Positive @P1
  Scenario: Create Course Instance
    When I click on the "Products" "Product Factory navigation item"
    When I click on the "Courses" "Product Factory navigation sub item"
    And I click on the "Create" "Product Factory button"
    And I click on the "EC_PAPER_DESCRIPTION" "Product Factory select button"
    And I click on the "EC_LEVEL_NAME" "Product Factory select button"
    And I click on the "EC_SITTING_NAME" "Product Factory select button"
    And I click on the "EC_COURSE_TYPE_DESCRIPTION" "Product Factory select button"
    And I click on the "EC_REGION_NAME" "Product Factory select button"
    And I click on the "Finish" "Product Factory button"
    Then I click on the "Create" "Product Factory button"
    And I click on the "Default Session Duration" "Product Factory dropdown"
    And I click on the "EC_SESSION_DURATION_DESCRIPTION" "Product Factory dropdown option"
    And I click on the "Default Location" "Product Factory dropdown"
    And I click on the "EC_LOCATION_NAME" "Product Factory dropdown option"
    And I click on the "Save" "Product Factory button"