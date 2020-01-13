@ProductFactory @FinancialDimensions
Feature: Financial Dimension - Reference Data Update Record - BPP-2489
  As a Senior Product Setter
  I want to be able to amend any existing Financial Dimension records
  So that I can correct any errors and make any required changes.

  Background:
    Given I execute "Log In" reusable step

  @Positive @Regression @BET
  Scenario Outline: Update created Financial Dimension record
    When I click on the "Financials" "Product Factory navigation item"
    When I click on the "Financial Dimensions" "Product Factory navigation sub item"
    Then I click on the "Create" "Product Factory button"
    And I set "FDCode[####]" text to the "Code" "Product Factory text field"
    And I set "FDDescription[####]" text to the "Description" "Product Factory text field"
    And I click on the "Applies To" "Product Factory dropdown"
    And I click on the "<type>" "Product Factory dropdown option"
    And I click on the "Save" "Product Factory button"
    Then I should see the "EC_FD_CODE" element
    And I should scroll to bottom of the page
    Then I should see the "EC_FD_CODE" "Product Factory edit button"
    And I click on the "EC_FD_CODE" "Product Factory edit button"
    Then I should see the "EC_FD_CODE" "Product Factory edit button"
    Then I should see the "EC_FD_DESCRIPTION" "Product Factory edit button"
    Then Attribute "tabindex" of "Save" "Product Factory button" should have value "-1"
    And I set "FDCodeTwo[####]" text to the "Code" "Product Factory text field"
    And I set "FDDescriptionTwo[####]" text to the "Description" "Product Factory text field"
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