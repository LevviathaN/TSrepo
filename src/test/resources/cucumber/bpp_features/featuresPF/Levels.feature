@ProductFactory @Levels
Feature: Reference Data - Level - BPP-371
  As a Senior Product Setter
  I need to be able to create different Levels under a body
  So that I can assign a paper to a level where a level is required and student understands the complexity
  of the paper they are studying/applying for.

  Background:
    Given I execute "Log In" reusable step

  @Positive @Regression @P1
  Scenario: Add a new level using a modal
    When I click on the "Programme" "Product Factory navigation item"
    When I click on the "Levels" "Product Factory navigation sub item"
    Then I click on the "Create" "Product Factory button"
    And I set "LevelShortName[####]" text to the "Short Name" "Product Factory text field"
    And I set "LevelName[####]" text to the "Name" "Product Factory text field"
    And I click on the "Save" "Product Factory button"
    Then I should see the "EC_LEVEL_SHORT_NAME" element

  @Negative @P2
  Scenario: Submitting incomplete fields
    When I click on the "Programme" "Product Factory navigation item"
    When I click on the "Levels" "Product Factory navigation sub item"
    Then I click on the "Create" "Product Factory button"
    Then Attribute "tabindex" of "Save" "Product Factory button" should have value "-1"

  @Negative @P2
  Scenario: Add a level where Short Name already exists
    When I execute "Create Level" reusable step
    When I click on the "Programme" "Product Factory navigation item"
    When I click on the "Levels" "Product Factory navigation sub item"
    Then I click on the "Create" "Product Factory button"
    And I set "EC_LEVEL_SHORT_NAME" text to the "Short Name" "Product Factory text field"
    And I set "LevelName[####]" text to the "Name" "Product Factory text field"
    And I click on the "Save" "Product Factory button"
    Then I should see the "Short Name must be unique" "message"