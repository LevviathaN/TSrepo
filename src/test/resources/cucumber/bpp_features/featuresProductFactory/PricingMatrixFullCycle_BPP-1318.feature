@ProductFactory @PricingMatrices
Feature: Pricing Matrix - price creation wizard - BPP-1318
  As a Senior Product Setter
  I want to be able to enter the Pricing Matrices details
  so that Course prices are calculated.

  Background:
    Given I execute "Log In" reusable step
    When I execute "Create Body Financial Dimension" reusable step
    And I execute "Create Body" reusable step
    Then I execute "Create Sitting" reusable step
    And I execute "Create VAT Rule" reusable step
    And I execute "Create Course Type Financial Dimension" reusable step
    And I execute "Create Course Type" reusable step
    And I execute "Create Region Financial Dimension" reusable step
    And I execute "Create Paper" reusable step
    And I execute "Create Region" reusable step
    And I execute "Link Body To Paper" reusable step
    And I execute "Create Pricing Matrix" reusable step

  @Positive @Regression @P1 @FullCycle #TC-1032
  Scenario: Add a New Pricing Matrix Full Cycle
    When I click on the "EC_BODY_NAME" "Product Factory edit button"
    Then I click on the "Create" "Product Factory button"
    And I set "EC_COURSE_TYPE_DESCRIPTION" text to the "Description" "Product Factory text field"
    And I click on the "Search" "Product Factory button"
    And I click on the "EC_COURSE_TYPE_DESCRIPTION" "Product Factory select button"
    And I "check" "EC_REGION_NAME" "Product Factory dialog checkbox"
    And I click on the "Next" button
    And I "check" "EC_PAPER_DESCRIPTION" "Product Factory dialog checkbox"
    And I click on the "Next" button
    And I set "1000" text to the "Enter a Price (£)" "Product Factory text field"
    And I click on the "Next" button
    And I click on the "Finish" button