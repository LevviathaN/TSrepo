@ProductFactory @PricingMatrices
Feature: Pricing Matrix creation page - BPP-1320
  As a Senior Product Setter
  I want to be able to enter and amend Pricing Matrices
  so that Course prices are calculated.

  Background:
    Given I execute "Log In" reusable step
    When I execute "Create Body Financial Dimension" reusable step
    And I execute "Create Body" reusable step
    Then I execute "Create Sitting" reusable step

  @Positive @Regression @P1
  Scenario: Add a New Pricing Matrix Using a Modal
    When I click on the "Financials" "Product Factory navigation item"
    When I click on the "Prices" "Product Factory navigation sub item"
    Then I click on the "Create" "Product Factory button"
    And I click on the "Body" "Product Factory change button"
    And I click on the "EC_BODY_NAME" "Product Factory change modal option"
    And I click on the "Sitting" "Product Factory change button"
    And I click on the "EC_SITTING_NAME" "Product Factory change modal option"
    And I click on the "Save" "Product Factory button"
    Then I should see the "EC_BODY_NAME" element