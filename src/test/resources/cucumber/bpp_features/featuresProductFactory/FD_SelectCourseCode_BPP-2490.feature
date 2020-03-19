@ProductFactory @FinancialDimensions @Courses
Feature: Financial Dimension - Select Course Code initial selection - BPP-2490
  As a Senior Product Setter
  I want to be able to select a Financial Dimension to be applied to all Courses
  I can track appropriate figures for accounting purposes.

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

  @Positive @P1 #TC-1605
  Scenario: Course Page Filters Validation
    Given I execute "Create Course Instance" reusable step
    When I click on the "Products" "Product Factory navigation item"
    When I click on the "Courses" "Product Factory navigation sub item"

    And I set "EC_BODY_SHORT_NAME" text to the "Body" "Product Factory text field"
    And I click on the "Search" "Product Factory button"
    Then I should see the "EC_BODY_SHORT_NAME" element
    And I should see the "Product Factory Materials Page Entry" element in quantity of "2"
    When I click on the "Clear Filters" "Product Factory button"
    Then Attribute "value" of "Body" "Product Factory text field" should have value ""

    And I set "EC_LEVEL_NAME" text to the "Level" "Product Factory text field"
    And I click on the "Search" "Product Factory button"
    Then I should see the "EC_LEVEL_NAME" element
    And I should see the "Product Factory Materials Page Entry" element in quantity of "2"
    When I click on the "Clear Filters" "Product Factory button"
    Then Attribute "value" of "Level" "Product Factory text field" should have value ""

    And I set "EC_PAPER_DESCRIPTION" text to the "Paper" "Product Factory text field"
    And I click on the "Search" "Product Factory button"
    Then I should see the "EC_PAPER_DESCRIPTION" element
    And I should see the "Product Factory Materials Page Entry" element in quantity of "2"
    When I click on the "Clear Filters" "Product Factory button"
    Then Attribute "value" of "Paper" "Product Factory text field" should have value ""

    And I set "EC_SITTING_NAME" text to the "Sitting" "Product Factory text field"
    And I click on the "Search" "Product Factory button"
    Then I should see the "EC_SITTING_NAME" element
    And I should see the "Product Factory Materials Page Entry" element in quantity of "2"
    When I click on the "Clear Filters" "Product Factory button"
    Then Attribute "value" of "Sitting" "Product Factory text field" should have value ""

    And I set "EC_COURSE_TYPE_DESCRIPTION" text to the "Course Type" "Product Factory text field"
    And I click on the "Search" "Product Factory button"
    Then I should see the "EC_COURSE_TYPE_DESCRIPTION" element
    And I should see the "Product Factory Materials Page Entry" element in quantity of "2"
    When I click on the "Clear Filters" "Product Factory button"
    Then Attribute "value" of "Course Type" "Product Factory text field" should have value ""

    And I set "EC_REGION_NAME" text to the "Region" "Product Factory text field"
    And I click on the "Search" "Product Factory button"
    Then I should see the "EC_REGION_NAME" element
    And I should see the "Product Factory Materials Page Entry" element in quantity of "2"
    When I click on the "Clear Filters" "Product Factory button"
    Then Attribute "value" of "Region" "Product Factory text field" should have value ""

    When I click on the "Generate Session Report" "Product Factory button"
    And I click on the "Click here to download the report" "button"
    And I click on the "EC_BODY_SHORT_NAME" "Product Factory edit button"
    Then I should see the "Edit Course" element