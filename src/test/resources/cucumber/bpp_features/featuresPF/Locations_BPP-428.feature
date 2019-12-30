@ProductFactory @Locations
Feature: Reference Data - Location - BPP-428
  As a Senior Product Setter
  I need to be able to create Locations specifying the Name (unique) and the Address text box.

  Background:
    Given I execute "Log In" reusable step
    When I execute "Create Location Financial Dimension" reusable step
    And I execute "Create Region Financial Dimension" reusable step
    Then I execute "Create Region" reusable step

  @Positive @Regression @P1
  Scenario: Add a New Location Using a Modal
    When I click on the "Delivery" "Product Factory navigation item"
    When I click on the "Locations" "Product Factory navigation sub item"
    Then I click on the "Create" "Product Factory button"
    And I set "LocationName[####]" text to the "Name" "Product Factory text field"
    And I set "LocationAddress[####]" text to the "Address" "Product Factory text field"
    And I click on the "Region" "Product Factory dropdown"
    And I click on the "EC_REGION_NAME" "Product Factory dropdown option"
    And I click on the "Financial Dimension" "Product Factory dropdown"
    And I click on the "EC_LOCATION_FD_DESCRIPTION" "Product Factory dropdown option"
    And I click on the "Save" "Product Factory button"
    Then I should see the "EC_LOCATION_NAME" element

  @Negative @P2
  Scenario: Submitting Incomplete Fields
    When I click on the "Delivery" "Product Factory navigation item"
    When I click on the "Locations" "Product Factory navigation sub item"
    Then I click on the "Create" "Product Factory button"
    Then Attribute "tabindex" of "Save" "Product Factory button" should have value "-1"

  @Negative @P2
  Scenario: Add a Location Where Name Already Exists
    When I execute "Create Location" reusable step
    When I click on the "Delivery" "Product Factory navigation item"
    When I click on the "Locations" "Product Factory navigation sub item"
    Then I click on the "Create" "Product Factory button"
    And I set "EC_LOCATION_NAME" text to the "Name" "Product Factory text field"
    And I set "LocationAddress[####]" text to the "Address" "Product Factory text field"
    And I click on the "Region" "Product Factory dropdown"
    And I click on the "EC_REGION_NAME" "Product Factory dropdown option"
    And I click on the "Financial Dimension" "Product Factory dropdown"
    And I click on the "EC_LOCATION_FD_DESCRIPTION" "Product Factory dropdown option"
    And I click on the "Save" "Product Factory button"
    Then I should see the "Name must be unique" "message"