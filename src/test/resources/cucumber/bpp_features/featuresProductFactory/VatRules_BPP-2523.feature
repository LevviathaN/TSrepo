@ProductFactory @VatRules
Feature: Reference Data - Vat Rules - Create Record - BPP-2523
  As a Senior Product Setter
  I want to be able to create new VAT Rules records
  so that I can setup appropriate rules for use when selling Courses and Materials.

  Background:
    Given I execute "Log In" reusable step

  @Positive @Regression @P1 #TC-1340
  Scenario: Add a New VAT Rule Using a Modal
    When I click on the "Financials" "Product Factory navigation item"
    When I click on the "VAT Rules" "Product Factory navigation sub item"
    Then I click on the "Create" "Product Factory button"
    And I set "VatRuleCode[####]" text to the "Code" "Product Factory text field"
    And I set "VatRuleDescription[####]" text to the "Description" "Product Factory text field"
    And I click on the "Save" "Product Factory button"
    Then I should see the "EC_VAT_RULE_CODE" element