@ProductFactory @FinancialDimensions @Materials
Feature: Financial Dimension - Select Material Code - Initial Selection - BPP-2491
  As a Senior Product Setter
  I want to be able to select a Financial Dimension for all Materials
  so that I can track this for accounting purposes.

  Background:
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

    And I set "EC_MATERIAL_TYPE_NAME" text to the "Material Type" "Product Factory text field"
    And I click on the "Search" "Product Factory button"
    Then I should see the "EC_MATERIAL_TYPE_NAME" element
    And I should see the "Product Factory Materials Page Entry" element in quantity of "2"
    When I click on the "Clear Filters" "Product Factory button"
    Then Attribute "value" of "Material Type" "Product Factory text field" should have value ""

    And I set "EC_BODY_SHORT_NAME" text to the "Body" "Product Factory text field"
    And I click on the "Search" "Product Factory button"
    Then I should see the "EC_BODY_SHORT_NAME" element
    And I should see the "Product Factory Materials Page Entry" element in quantity of "2"
    When I click on the "Clear Filters" "Product Factory button"
    Then Attribute "value" of "Body" "Product Factory text field" should have value ""

    And I set "EC_LEVEL_SHORT_NAME" text to the "Level" "Product Factory text field"
    And I click on the "Search" "Product Factory button"
    And Attribute "title" of "1 Levels" "element" should have value "EC_LEVEL_SHORT_NAME"
    And I should see the "Product Factory Materials Page Entry" element in quantity of "2"
    When I click on the "Clear Filters" "Product Factory button"
    Then Attribute "value" of "Level" "Product Factory text field" should have value ""

    And I set "EC_SITTING_NAME" text to the "Sitting" "Product Factory text field"
    And I click on the "Search" "Product Factory button"
    And Attribute "title" of "1 Sittings" "element" should have value "EC_SITTING_NAME"
    And I should see the "Product Factory Materials Page Entry" element in quantity of "2"
    When I click on the "Clear Filters" "Product Factory button"
    Then Attribute "value" of "Sitting" "Product Factory text field" should have value ""

    And I set "EC_ISBN" text to the "ISBN" "Product Factory text field"
    And I click on the "Search" "Product Factory button"
    Then I should see the "EC_ISBN" element
    And I should see the "Product Factory Materials Page Entry" element in quantity of "2"
    When I click on the "Clear Filters" "Product Factory button"
    Then Attribute "value" of "ISBN" "Product Factory text field" should have value ""

    When I click on the "Generate Material Report" "Product Factory button"
    And I click on the "Click here to download the report" "button"
    And I set "EC_BODY_SHORT_NAME" text to the "Body" "Product Factory text field"
    And I click on the "Search" "Product Factory button"
    And I click on the "EC_MATERIAL_TYPE_NAME" "Product Factory edit button"
    Then I should see the "Edit Material" element