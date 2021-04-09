@ProductFactory @FinancialDimensions @Locations
Feature: Financial Dimension - Select Location Codes - BPP-2495
  As a Senior Product Setter
  I want to be able to select 2 Financial Dimensions on an individual Location
  so that I can track both the "AX Division" and "AX Location" codes for accounting purposes.

  Background:
    Given I execute "Generate ISBN" reusable step
    Given I execute "Log In" reusable step

  @Negative @Regression @P1 #TC-1771, TC-1718
  Scenario: Edit Financial Dimension for the Location that is in use
    When I execute "Create Body Financial Dimension" reusable step
    And I execute "Create Vertical" reusable step
    And I execute "Create Body" reusable step
    And I execute "Create VAT Rule" reusable step
    And I execute "Create Course Type Financial Dimension" reusable step
    And I execute "Create Course Type" reusable step
    And I execute "Create Location Financial Dimension" reusable step
    And I execute "Create Location Financial Dimension" reusable step replacing some steps
      |4|I set "LocationFDCodeTwo[######]" text to the "Code" "Product Factory text field"|
      |5|I set "LocationFDDescriptionTwo[######]" text to the "Description" "Product Factory text field"|
    And I execute "Create Region Financial Dimension" reusable step
    And I execute "Create Region" reusable step
    And I execute "Create Location" reusable step
    And I execute "Create Location" reusable step
    And I execute "Create Stock Site" reusable step
    And I execute "Create Level" reusable step
    And I execute "Create Paper" reusable step
    And I execute "Link Body To Level" reusable step
    And I execute "Link Body To Paper" reusable step
    And I execute "Link Paper To Level" reusable step
    And I execute "Create Sitting" reusable step
    And I execute "Create Pricing Matrix" reusable step
    And I execute "Create Pricing Matrix Full" reusable step
    And I execute "Create Material Type Financial Dimension" reusable step
    And I execute "Create Material Type" reusable step
    And I execute "Create Session Duration" reusable step
    And I execute "Create Client" reusable step
    And I execute "Create Material" reusable step
    And I execute "Create Course" reusable step
    And I execute "Create Course Instance" reusable step

    When I click on the "Delivery" "Product Factory navigation item"
    When I click on the "Locations" "Product Factory navigation sub item"
    When I click on the "EC_LOCATION_NAME" "Product Factory edit button"
    And I click on the "Financial Dimension" "Product Factory dropdown"
    And I click on the "EC_LOCATION_FD_DESCRIPTION_TWO" "Product Factory dropdown option"
    And I click on the "Save" "Product Factory button"
    Then I should see the "The Location is already linked to one or more Sessions, so you cannot change the Financial Dimension" element