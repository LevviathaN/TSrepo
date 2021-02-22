@ProductFactory @FinancialDimensions @Courses
Feature: Financial Dimension - Select Course Code initial selection - BPP-2490
  As a Senior Product Setter
  I want to be able to select a Financial Dimension to be applied to all Courses
  I can track appropriate figures for accounting purposes.

  Background:
    Given I execute "Generate ISBN" reusable step
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

  @Positive @P1 @BlockedByIssue #TC-4411 (Blocked by BPP-11454)
  Scenario: Course Page Filters Validation
    Then I execute "Create Course" reusable step
    Given I execute "Create Course Instance" reusable step
    When I click on the "Products" "Product Factory navigation item"
    When I click on the "Courses" "Product Factory navigation sub item"

    And I set "EC_BODY_SHORT_NAME" text to the "Search" "Product Factory text field" from keyboard
    And I click on the "submit" "element by type"
    Then I should see the "EC_BODY_SHORT_NAME" element
    And I should see the "Product Factory Materials Page Entry" element in quantity of "2"
    When I click on the "Clear the search text" "element by title"
    Then Attribute "value" of "Search" "Product Factory text field" should have value ""

    And I set "EC_LEVEL_NAME" text to the "Search" "Product Factory text field" from keyboard
    And I click on the "submit" "element by type"
    Then I should see the "EC_BODY_SHORT_NAME" element
    And I should see the "Product Factory Materials Page Entry" element in quantity of "2"
    When I click on the "Clear the search text" "element by title"
    Then Attribute "value" of "Search" "Product Factory text field" should have value ""

    And I set "EC_PAPER_DESCRIPTION" text to the "Search" "Product Factory text field" from keyboard
    And I click on the "submit" "element by type"
    Then I should see the "EC_BODY_SHORT_NAME" element
    And I should see the "Product Factory Materials Page Entry" element in quantity of "2"
    When I click on the "Clear the search text" "element by title"
    Then Attribute "value" of "Search" "Product Factory text field" should have value ""

    And I set "EC_SITTING_NAME" text to the "Search" "Product Factory text field" from keyboard
    And I click on the "submit" "element by type"
    Then I should see the "EC_BODY_SHORT_NAME" element
    And I should see the "Product Factory Materials Page Entry" element in quantity of "2"
    When I click on the "Clear the search text" "element by title"
    Then Attribute "value" of "Search" "Product Factory text field" should have value ""

    And I set "EC_COURSE_TYPE_DESCRIPTION" text to the "Search" "Product Factory text field" from keyboard
    And I click on the "submit" "element by type"
    Then I should see the "EC_BODY_SHORT_NAME" element
    And I should see the "Product Factory Materials Page Entry" element in quantity of "2"
    When I click on the "Clear the search text" "element by title"
    Then Attribute "value" of "Search" "Product Factory text field" should have value ""

    And I set "EC_REGION_NAME" text to the "Search" "Product Factory text field" from keyboard
    And I click on the "submit" "element by type"
    Then I should see the "EC_BODY_SHORT_NAME" element
    And I should see the "Product Factory Materials Page Entry" element in quantity of "2"
    When I click on the "Clear the search text" "element by title"
    Then Attribute "value" of "Search" "Product Factory text field" should have value ""