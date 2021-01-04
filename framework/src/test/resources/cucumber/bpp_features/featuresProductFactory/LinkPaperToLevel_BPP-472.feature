@ProductFactory @LinkPaperToLevel
Feature: Reference Data - Link Paper To Level - BPP-472
  As a Senior Product Setter
  I need to be able to specify which Papers(Modules) are linked to a Level.
  (Relate Paper to a Level through "for Level" relationship)
  If a Paper is Linked to Body, only Levels related to same Body (Allowed Level) should be available/linked to.

  Background:
    Given I execute "Log In" reusable step
    And I execute "Create Level" reusable step
    And I execute "Create Paper" reusable step

  @Positive @Regression @P1 #TC-714
  Scenario: Link Paper to Level
    Then I set "EC_PAPER_DESCRIPTION" text to the "Search" "Product Factory text field" from keyboard
    And I click on the "submit" "element by type"
    When I click on the "EC_PAPER_DESCRIPTION" "Product Factory Link Levels button"
    And I "check" "EC_LEVEL_NAME" "Product Factory dialog checkbox"
    And I click on the "Save" "Product Factory button"