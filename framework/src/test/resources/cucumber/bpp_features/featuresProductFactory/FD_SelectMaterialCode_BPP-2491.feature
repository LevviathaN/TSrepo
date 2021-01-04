@ProductFactory @FinancialDimensions @Materials
Feature: Financial Dimension - Select Material Code - Initial Selection - BPP-2491
  As a Senior Product Setter
  I want to be able to select a Financial Dimension for all Materials
  so that I can track this for accounting purposes.

  Background:
    Given I execute "Generate ISBN" reusable step
    Given I execute "Log In" reusable step
    When I execute "Create Body Financial Dimension" reusable step
    And I execute "Create Body" reusable step
    And I execute "Create Level" reusable step
    And I execute "Create Sitting" reusable step
    And I execute "Create VAT Rule" reusable step
    And I execute "Create Material Type Financial Dimension" reusable step
    And I execute "Create Material Financial Dimension" reusable step
    And I execute "Create Material Type" reusable step
    And I execute "Create Paper" reusable step
    And I execute "Link Body To Paper" reusable step
    And I execute "Link Body To Level" reusable step

  @Positive @P1 #TC-1667
  Scenario: Material Page Filters Validation
    Given I execute "Create Material" reusable step

    Then I set "EC_MATERIAL_TYPE_NAME" text to the "Search" "Product Factory text field" from keyboard
    And I click on the "submit" "element by type"
    Then I should see the "EC_MATERIAL_TYPE_NAME" element
    And I should see the "Product Factory Materials Page Entry" element in quantity of "2"
    And I click on the "Clear the search text" "element by title"
    Then Attribute "value" of "Search" "Product Factory text field" should have value ""

    Then I set "EC_BODY_SHORT_NAME" text to the "Search" "Product Factory text field" from keyboard
    And I click on the "submit" "element by type"
    Then I should see the "EC_BODY_SHORT_NAME" element
    And I should see the "Product Factory Materials Page Entry" element in quantity of "2"
    And I click on the "Clear the search text" "element by title"
    Then Attribute "value" of "Search" "Product Factory text field" should have value ""

    Then I set "EC_LEVEL_SHORT_NAME" text to the "Search" "Product Factory text field" from keyboard
    And I click on the "submit" "element by type"
    And Attribute "title" of "1 Levels" "element" should have value "EC_LEVEL_SHORT_NAME"
    And I should see the "Product Factory Materials Page Entry" element in quantity of "2"
    And I click on the "Clear the search text" "element by title"
    Then Attribute "value" of "Search" "Product Factory text field" should have value ""

    Then I set "EC_SITTING_NAME" text to the "Search" "Product Factory text field" from keyboard
    And I click on the "submit" "element by type"
    And Attribute "title" of "1 Sittings" "element" should have value "EC_SITTING_NAME"
    And I should see the "Product Factory Materials Page Entry" element in quantity of "2"
    And I click on the "Clear the search text" "element by title"
    Then Attribute "value" of "Search" "Product Factory text field" should have value ""

    When I click on the "Generate Material Report" "button"
    And I click on the "Click here to download the report" "button"
    Then I set "EC_BODY_SHORT_NAME" text to the "Search" "Product Factory text field" from keyboard
    And I click on the "submit" "element by type"
    And I click on the "EC_MATERIAL_TYPE_NAME" "Product Factory edit button"
    Then I should see the "Edit Material" element