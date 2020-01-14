@ProductFactory @Papers @Amend
Feature: Amend Reference Data - Paper - BPP-660
  As a Senior Product Setter
  I need to be able to Amend Papers,
  so that I can use the Paper as part of a course template construction and shared between templates and instances.

  Background:
    Given I execute "Log In" reusable step
    And I execute "Create Paper" reusable step

  @Positive @Regression @P1
  Scenario: Amend a Paper Using a Modal
    When I click on the "Programme" "Product Factory navigation item"
    When I click on the "Papers" "Product Factory navigation sub item"
    When I click on the "EC_PAPER_DESCRIPTION" "Product Factory edit button"
    And I set "PaperDescription[####]" text to the "Description" "Product Factory text field"
    And I click on the "Save" "Product Factory button"
    Then I should see the "EC_PAPER_DESCRIPTION" element

  @Negative @P2
  Scenario: Submitting Unchanged Paper Fields
    When I click on the "Programme" "Product Factory navigation item"
    When I click on the "Papers" "Product Factory navigation sub item"
    When I click on the "EC_PAPER_DESCRIPTION" "Product Factory edit button"
    Then Attribute "tabindex" of "Save" "Product Factory button" should have value "-1"

  @Negative @P2
  Scenario: Amend a Paper Where Description Already Exists
    Given I execute "Create Paper" reusable step replacing some steps
      |4|I set "PaperDescriptionNew[####]" text to the "Description" "Product Factory text field"|
    When I click on the "Programme" "Product Factory navigation item"
    When I click on the "Papers" "Product Factory navigation sub item"
    When I click on the "EC_PAPER_DESCRIPTION" "Product Factory edit button"
    And I set "EC_PAPER_DESCRIPTION_NEW" text to the "Description" "Product Factory text field"
    And I click on the "Save" "Product Factory button"
    Then I should see the "Description must be unique" "message"