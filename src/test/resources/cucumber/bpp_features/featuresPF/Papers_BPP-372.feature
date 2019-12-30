@ProductFactory @Papers
Feature: Reference Data - Paper - BPP-372
  As a Senior Product Setter
  I need to be able to create Papers
  So that I can use the Paper as part of a course template construction and shared between templates and instances.

  Background:
    Given I execute "Log In" reusable step

  @Positive @Regression @P1
  Scenario: Add a New Paper Using a Modal
    When I click on the "Programme" "Product Factory navigation item"
    When I click on the "Papers" "Product Factory navigation sub item"
    Then I click on the "Create" "Product Factory button"
    And I set "PaperDescription[####]" text to the "Description" "Product Factory text field"
    And I click on the "Save" "Product Factory button"
    Then I should see the "EC_PAPER_DESCRIPTION" element

  @Negative @P2
  Scenario: Submitting Incomplete Fields
    When I click on the "Programme" "Product Factory navigation item"
    When I click on the "Papers" "Product Factory navigation sub item"
    Then I click on the "Create" "Product Factory button"
    Then Attribute "tabindex" of "Save" "Product Factory button" should have value "-1"

  @Negative @P2
  Scenario: Add a Paper Where Description Already Exists
    When I execute "Create Paper" reusable step
    When I click on the "Programme" "Product Factory navigation item"
    When I click on the "Papers" "Product Factory navigation sub item"
    Then I click on the "Create" "Product Factory button"
    And I set "EC_PAPER_DESCRIPTION" text to the "Description" "Product Factory text field"
    And I click on the "Save" "Product Factory button"
    Then I should see the "Description must be unique" "message"