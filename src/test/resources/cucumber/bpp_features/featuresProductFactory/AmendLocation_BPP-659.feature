@ProductFactory @Locations @Amend
Feature: Amend Reference Data - Location - BPP-659
  As a Senior Product Setter
  I need to be able to amend Locations specifying the Name (unique) and the Address text box.

  Background:
    Given I execute "Log In" reusable step
    When I execute "Create Location Financial Dimension" reusable step
    And I execute "Create Region Financial Dimension" reusable step
    Then I execute "Create Region" reusable step

  @Positive @P1 #TC-659
  Scenario: Amend Location
    When I execute "Create Location Financial Dimension" reusable step replacing some steps
      |4|I set "LocationFDCodeTwo[####]" text to the "Code" "Product Factory text field"|
      |5|I set "LocationFDDescriptionTwo[####]" text to the "Description" "Product Factory text field"|
    Then I execute "Create Region" reusable step replacing some steps
      |4|I set "RegionNameTwo[######]" text to the "Name" "Product Factory text field"|
    When I execute "Create Location" reusable step
    When I click on the "Delivery" "Product Factory navigation item"
    When I click on the "Locations" "Product Factory navigation sub item"
    Then I click on the "EC_LOCATION_NAME" "Product Factory edit button"
    And I set "LocationNameTwo[######]" text to the "Name" "Product Factory text field"
    And I set "LocationAddressTwo[######]" text to the "Address" "Product Factory text field"
    And I click on the "Region" "Product Factory dropdown"
    And I click on the "EC_REGION_NAME_TWO" "Product Factory dropdown option"
    And I click on the "Financial Dimension" "Product Factory dropdown"
    And I click on the "EC_LOCATION_FD_DESCRIPTION_TWO" "Product Factory dropdown option"
    And I click on the "Save" "Product Factory button"
    Then I should see the "EC_LOCATION_NAME_TWO" "element"