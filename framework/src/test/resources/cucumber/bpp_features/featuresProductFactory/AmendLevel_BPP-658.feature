@ProductFactory @Levels @Amend
Feature: Amend Reference Data - Level - BPP-658
  As a Senior Product Setter
  I need to be able to amend the different Levels under a body
  so that I can assign a paper to a level where a level is required and student understands the complexity of the paper they are studying/applying for.

  Background:
    Given I execute "Log In" reusable step
    And I execute "Create Level" reusable step

  @Positive @Regression @P1 #TC-823
  Scenario: Amend a Level Using a Modal
    When I click on the "Programme" "Product Factory navigation item"
    When I click on the "Levels" "Product Factory navigation sub item"
    When I click on the "EC_LEVEL_NAME" "Product Factory edit button"
    And I set "LevelShortName[######]" text to the "Short Name" "Product Factory text field"
    And I set "LevelName[######]" text to the "Name" "Product Factory text field"
    And I click on the "Save" "Product Factory button"
    Then I should see the "EC_LEVEL_SHORT_NAME" element

  @Negative @P2 #TC-822
  Scenario: Submitting Incomplete Level Fields
    When I click on the "Programme" "Product Factory navigation item"
    When I click on the "Levels" "Product Factory navigation sub item"
    When I click on the "EC_LEVEL_NAME" "Product Factory edit button"
    Then Attribute "tabindex" of "Save" "Product Factory button" should have value "-1"

  @Negative @P2 #TC-824
  Scenario: Amend a Level Where Short Name Already Exists
    Given I execute "Create Level" reusable step replacing some steps
      |4|I set "LevelShortNameNew[######]" text to the "Short Name" "Product Factory text field"|
      |5|I set "LevelNameNew[######]" text to the "Name" "Product Factory text field"|
    When I click on the "Programme" "Product Factory navigation item"
    When I click on the "Levels" "Product Factory navigation sub item"
    When I click on the "EC_LEVEL_NAME" "Product Factory edit button"
    And I set "EC_LEVEL_SHORT_NAME_NEW" text to the "Short Name" "Product Factory text field"
    And I set "EC_LEVEL_NAME_NEW" text to the "Name" "Product Factory text field"
    And I click on the "Save" "Product Factory button"
    Then I should see the "Short Name must be unique" "message"