@ProductFactory @PricingMatrices @Amend
Feature: Pricing Matrix - update prices - BPP-1321
  As a Senior Product Setter
  I want to be able to change individual Prices on a Pricing Matrix,
  so that any industry or business price-related changes are reflected in the appropriate Course(s).

  Background:
    Given I execute "Log In" reusable step
    When I execute "Create Body Financial Dimension" reusable step
    And I execute "Create Body" reusable step
    Then I execute "Create Sitting" reusable step
    And I execute "Create VAT Rule" reusable step
    And I execute "Create Course Type Financial Dimension" reusable step
    And I execute "Create Course Type" reusable step
    And I execute "Create Region Financial Dimension" reusable step
    And I execute "Create Region" reusable step
    And I execute "Create Paper" reusable step
    And I execute "Link Body To Paper" reusable step
    And I execute "Create Pricing Matrix" reusable step
    And I execute "Create Pricing Matrix Full" reusable step

  @Positive @Regression @P1
  Scenario: Update Pricing Matrix Price
    When I click on the "Financials" "Product Factory navigation item"
    When I click on the "Prices" "Product Factory navigation sub item"
    When I click on the "EC_BODY_NAME" "Product Factory edit button"
    And I click on the "EC_COURSE_TYPE_DESCRIPTION" "text"
    And I click on the "EC_REGION_NAME" "text"
    And I click on the "EC_PAPER_DESCRIPTION" "Product Factory change button"
    And I set "999" text to the "Enter a Price (£)" "Product Factory text field"
    And I click on the "Save" "Product Factory active button"
    Then I should see the "£999" "element"