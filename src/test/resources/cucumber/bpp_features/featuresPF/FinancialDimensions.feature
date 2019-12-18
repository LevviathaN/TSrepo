@ProductFactory @FinancialDimensions
Feature: Product Factory Financial Dimensions
  As a product setter
  I want to be able to create Bodies in Product Factory
  In order to (just cause)

  Background:
    Given I execute "Log In" reusable step

  @Multiple
  Scenario Outline: Create All Financial Dimensions
    When I click on the "Financials" "Product Factory navigation item"
    When I click on the "Financial Dimensions" "Product Factory navigation sub item"
    Then I click on the "Create" "Product Factory button"
    And I set "FDCode[####]" text to the "Code" "Product Factory text field"
    And I set "FDDescription[####]" text to the "Description" "Product Factory text field"
    And I click on the "Applies To" "Product Factory dropdown"
    And I click on the "<type>" "Product Factory dropdown option"
    And I click on the "Save" "Product Factory button"
    Then I should see the "EC_FD_CODE" element

    Examples:
      |type         |
      |Course       |
      |Material     |
      |Course Type  |
      |Material Type|
      |Location     |
      |Body         |