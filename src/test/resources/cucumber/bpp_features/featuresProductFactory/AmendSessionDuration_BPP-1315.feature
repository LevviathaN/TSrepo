@ProductFactory @SessionDurations @Amend
Feature: Reference Data - Amend Session Duration - BPP-1315
  As a Senior Product Setter
  I want to be able amend Session Duration data
  so that a Course Instance can be created using this Session.

  Background:
    Given I execute "Log In" reusable step
    And I execute "Create Session Duration" reusable step

  @Positive @Regression @P1
  Scenario: Amend a Session Duration Using a Modal
    When I click on the "Delivery" "Product Factory navigation item"
    When I click on the "Session Durations" "Product Factory navigation sub item"
    When I click on the "EC_SESSION_DURATION_DESCRIPTION" "Product Factory edit button"
    And I set "SessionDurationDescription[####]" text to the "Description" "Product Factory text field"
    And I click on the "Save" "Product Factory button"
    Then I should see the "EC_SESSION_DURATION_DESCRIPTION" element