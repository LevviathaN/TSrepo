@ProductFactory @Bodies @LinkBodyToPaper
Feature: Reference Data - Link Body/Paper(Module) - BPP-471
  As a Senior Product Setter
  I need to be able to specify which Levels are linked to a Body.
  (Relate Body to Level through "Allowed Level" relationship)

  Background:
    Given I execute "Log In" reusable step
    When I execute "Create Body Financial Dimension" reusable step
    And I execute "Create Body" reusable step
    Then I execute "Create Paper" reusable step

  @Positive @Regression @P1 #TC-697
  Scenario: Link Body to Paper
    Then I set "EC_PAPER_DESCRIPTION" text to the "Search" "Product Factory text field" from keyboard
    And I click on the "submit" "element by type"
    When I click on the "EC_PAPER_DESCRIPTION" "Product Factory Change Body button"
    And I click on the "EC_BODY_NAME" "Product Factory select button"
    And I click on the "Yes" "button"