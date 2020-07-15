@ProductFactory @Papers
Feature: Paper List - add search filter - BPP-8381
  As a Product Setter
  I want a search filter added to the Paper List page
  so that the records can be filtered.

  Background:
    Given I execute "Log In" reusable step
    When I execute "Create Body Financial Dimension" reusable step
    Then I execute "Create Body" reusable step
    And I execute "Create Paper" reusable step
    And I execute "Create Level" reusable step
    And I execute "Link Body To Level" reusable step
    And I execute "Link Body To Paper" reusable step
    And I execute "Link Paper To Level" reusable step

  @Positive @Regression #TC-3582, TC-3600
  Scenario: Paper Filters Validation
    When I click on the "Programme" "Product Factory navigation item"
    When I click on the "Papers" "Product Factory navigation sub item"
    And I wait for "3" seconds

    And I set "XXX" text to the "Description" "Product Factory text field"
    And I click on the "Search" "Product Factory button"
    And I shouldn't see the "EC_PAPER_DESCRIPTION" "element"
    And I click on the "Clear Filters" "Product Factory button"

    And I set "EC_BODY_SHORT_NAME" text to the "Body" "Product Factory text field"
    And I click on the "Search" "Product Factory button"
    And I should see the "EC_PAPER_DESCRIPTION" "element"
    And I click on the "Clear Filters" "Product Factory button"

    And I set "EC_LEVEL_SHORT_NAME" text to the "Level" "Product Factory text field"
    And I click on the "Search" "Product Factory button"
    And I should see the "EC_PAPER_DESCRIPTION" "element"
    And I click on the "Clear Filters" "Product Factory button"

    And I set "EC_BODY_SHORT_NAME" text to the "Body" "Product Factory text field"
    And I set "EC_LEVEL_SHORT_NAME" text to the "Level" "Product Factory text field"
    And I click on the "Search" "Product Factory button"
    And I should see the "EC_PAPER_DESCRIPTION" "element"
    And I click on the "Clear Filters" "Product Factory button"