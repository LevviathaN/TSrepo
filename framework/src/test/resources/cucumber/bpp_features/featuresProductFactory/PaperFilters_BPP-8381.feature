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

    Then I set "XXX" text to the "Search" "Product Factory text field" from keyboard
    And I click on the "submit" "element by type"
    And I shouldn't see the "EC_PAPER_DESCRIPTION" "element"
    And I click on the "Clear the search text" "element by title"

    Then I set "EC_BODY_SHORT_NAME" text to the "Search" "Product Factory text field" from keyboard
    And I click on the "submit" "element by type"
    And I should see the "EC_PAPER_DESCRIPTION" "element"
    And I click on the "Clear the search text" "element by title"

    Then I set "EC_LEVEL_SHORT_NAME" text to the "Search" "Product Factory text field" from keyboard
    And I click on the "submit" "element by type"
    And I should see the "EC_PAPER_DESCRIPTION" "element"
    And I click on the "Clear the search text" "element by title"