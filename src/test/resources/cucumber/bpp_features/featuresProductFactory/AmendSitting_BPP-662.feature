@ProductFactory @Sittings @Amend
Feature: Amend Reference Data - Sitting - BPP-662
  As a Senior Product Setter
  I need to be able to amend the Sittings associated to a Body
  so that I can identify when course materials are available and exam sittings are planned for

  Background:
    Given I execute "Log In" reusable step
    When I execute "Create Body Financial Dimension" reusable step
    Then I execute "Create Body" reusable step
    Given I execute "Create Sitting" reusable step

  @Positive @Regression @P1 #TC-1547
  Scenario: Amend a Sitting Using a Modal
    When I click on the "Delivery" "Product Factory navigation item"
    When I click on the "Sittings" "Product Factory navigation sub item"
    When I click on the "EC_SITTING_NAME" "Product Factory edit button"
    And I set "SittingName[######]" text to the "Name" "Product Factory text field"
    And I remember "KW_AUTO_TODAY|DD/MM/YYYY" text as "EC_SITTING_START_DATE" variable
    And I set "EC_SITTING_START_DATE" text to the "Start Date" "Product Factory text field"
    And I remember "10/10/2020" text as "EC_SITTING_END_DATE" variable
    And I set "EC_SITTING_END_DATE" text to the "End Date" "Product Factory text field"
    And I click on the "Save" "Product Factory button"
    Then I should see the "EC_SITTING_NAME" element

  @Negative @P2 @NoQTest
  Scenario: Submitting Unchanged Sitting Fields
    When I click on the "Delivery" "Product Factory navigation item"
    When I click on the "Sittings" "Product Factory navigation sub item"
    When I click on the "EC_SITTING_NAME" "Product Factory edit button"
    Then Attribute "tabindex" of "Save" "Product Factory button" should have value "-1"

  @Negative @P2 @NoQTest
  Scenario: Amend a Sitting Where Name Already Exists
    Given I execute "Create Sitting" reusable step replacing some steps
      |4|I set "SittingNameNew[######]" text to the "Name" "Product Factory text field"|
    When I click on the "Delivery" "Product Factory navigation item"
    When I click on the "Sittings" "Product Factory navigation sub item"
    When I click on the "EC_SITTING_NAME" "Product Factory edit button"
    And I set "EC_SITTING_NAME_NEW" text to the "Name" "Product Factory text field"
    And I click on the "Save" "Product Factory button"
    Then I should see the "The combination of Name and Dates must be unique" "message"