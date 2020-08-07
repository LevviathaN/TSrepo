@ProductFactory @Streams
Feature: Reference Data - Streams - BPP-6516
  As a Senior Product Setter
  I want to be able to add and amend Stream reference data
  so that a Stream is stored in PF.

  Background:
    Given I execute "Log In" reusable step

  @Positive @P1 #TC-2929
  Scenario: Add a New Stream Using a Modal
    When I click on the "Miscellaneous" "Product Factory navigation item"
    When I click on the "Streams" "Product Factory navigation sub item"
    Then I click on the "Create" "Product Factory button"
    And I set "StreamName[######]" text to the "Name" "Product Factory text field"
    And I click on the "Save" "Product Factory button"
    Then I should see the "EC_STREAM_NAME" element

  @Negative @P2 @Amend #TC-2932
  Scenario: Amend a Stream With Name That Already Exists
    When I click on the "Miscellaneous" "Product Factory navigation item"
    When I click on the "Clients" "Product Factory navigation sub item"
    Then I click on the "Create" "Product Factory button"
    Then Attribute "tabindex" of "Save" "Product Factory button" should have value "-1"

  @Negative @P2 #TC-2930
  Scenario: Add a Stream Where Description Already Exists
    When I execute "Create Stream" reusable step
    When I click on the "Miscellaneous" "Product Factory navigation item"
    When I click on the "Streams" "Product Factory navigation sub item"
    Then I click on the "Create" "Product Factory button"
    And I set "EC_STREAM_NAME" text to the "Name" "Product Factory text field"
    And I click on the "Save" "Product Factory button"
    Then I should see the "Name must be unique" "message"

  @Positive @Regression @P1 @Amend #TC-2931
  Scenario: Amend a Stream Using a Modal
    When I execute "Create Stream" reusable step
    When I click on the "Miscellaneous" "Product Factory navigation item"
    When I click on the "Streams" "Product Factory navigation sub item"
    Then I click on the "EC_STREAM_NAME" "Product Factory edit button"
    And I set "StreamName[######]" text to the "Name" "Product Factory text field"
    And I click on the "Save" "Product Factory button"
    Then I should see the "EC_STREAM_NAME" element