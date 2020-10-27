@ProductFactory @Locations @Region
Feature: Add/Amend Region to Location - BPP-3673
  As a Senior Product Setter
  I need to be able to set a Price Region against a Location

  Background:
    Given I execute "Generate ISBN" reusable step
    Given I execute "Log In" reusable step

  @Positive @Regression @P1 #TC-1869
  Scenario: Creating New Location without and with a Region record
    When I execute "Create Region Financial Dimension" reusable step
    When I execute "Create Location Financial Dimension" reusable step
    When I execute "Create Region" reusable step
    When I click on the "Delivery" "Product Factory navigation item"
    When I click on the "Locations" "Product Factory navigation sub item"
    Then I click on the "Create" "Product Factory button"
    And I set "LocationName[######]" text to the "Name" "Product Factory text field"
    And I set "LocationAddress[######]" text to the "Address Line 1" "Product Factory text field"
    And I click on the "Financial Dimension" "Product Factory dropdown"
    And I click on the "EC_LOCATION_FD_DESCRIPTION" "Product Factory dropdown option"
    Then Attribute "tabindex" of "Save" "Product Factory button" should have value "-1"
    And I click on the "Region" "Product Factory dropdown"
    And I click on the "EC_REGION_NAME" "Product Factory dropdown option"
    And I click on the "Save" "Product Factory button"
    Then I should see the "EC_LOCATION_NAME" element

  @Positive @Regression @P1 #TC-1870
  Scenario: Edit Location that is not in use
    When I execute "Create Region Financial Dimension" reusable step
    When I execute "Create Location Financial Dimension" reusable step
    When I execute "Create Region" reusable step
    When I execute "Create Region" reusable step replacing some steps
      |4|I set "RegionNameTwo[######]" text to the "Name" "Product Factory text field"|
      |8|I should see the "EC_REGION_NAME_TWO" element|
    When I click on the "Delivery" "Product Factory navigation item"
    When I click on the "Locations" "Product Factory navigation sub item"
    Then I click on the "Create" "Product Factory button"
    And I set "LocationName[#######]" text to the "Name" "Product Factory text field"
    And I set "LocationAddress[#######]" text to the "Address Line 1" "Product Factory text field"
    And I click on the "Region" "Product Factory dropdown"
    And I click on the "EC_REGION_NAME" "Product Factory dropdown option"
    And I click on the "Financial Dimension" "Product Factory dropdown"
    And I click on the "EC_LOCATION_FD_DESCRIPTION" "Product Factory dropdown option"
    And I click on the "Save" "Product Factory button"
    Then I should see the "EC_LOCATION_NAME" element
    When I click on the "Delivery" "Product Factory navigation item"
    When I click on the "Locations" "Product Factory navigation sub item"
    And I click on the "EC_LOCATION_NAME" "Product Factory edit button"
    And I set "LocationNameWithNewRegion[#####]" text to the "Name" "Product Factory text field"
    And I click on the "Region" "Product Factory dropdown"
    And I click on the "EC_REGION_NAME_TWO" "Product Factory dropdown option"
    And I click on the "Save" "Product Factory button"
    Then I should see the "EC_LOCATION_NAME_WITH_NEW_REGION" element
    And I shouldn't see the "The Location is already linked to one or more Prices, so you cannot change the Region" element
    And I shouldn't see the "The Location is already linked to one or more Sessions, so you cannot change the Region" element

  @Positive @Regression @P1 #TC-1871
  Scenario: Edit Location that is in use
    When I execute "Create Body Financial Dimension" reusable step
    And I execute "Create Body" reusable step
    And I execute "Create VAT Rule" reusable step
    And I execute "Create Course Type Financial Dimension" reusable step
    And I execute "Create Course Type" reusable step
    And I execute "Create Location Financial Dimension" reusable step
    And I execute "Create Region Financial Dimension" reusable step
    And I execute "Create Region" reusable step
    And I execute "Create Region" reusable step replacing some steps
      |4|I set "RegionNameTwo[######]" text to the "Name" "Product Factory text field"|
      |8|I should see the "EC_REGION_NAME_TWO" element|
    And I execute "Create Location" reusable step
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

    #Course Creation
    When I execute "Create Course Instance" reusable step

    And I click on the "Activate" "Product Factory button"
    And I click on the "Product Factory Course Instances Arrow Down Icon" element
    And I click on the "Product Factory Course Instances Sessions Number of Dates Icon" element
    And I fill the "Product Factory Session Dates Popup Date Input Field" field with "EC_SITTING_START_DATE"
    And I fill the "Product Factory Session Dates Popup Start Time Input Field" field with "03:00AM"
    And I fill the "Product Factory Session Dates Popup End Time Input Field" field with "04:00AM"
    And I click on the "Next" "Product Factory button"
    And I click on the "Finish" "Product Factory button"
    And I shouldn't see the "Product Factory Toast Error Message Top-Right corner" element
    And I click on the "Activate" "Product Factory button"

    #Edit Region inside of Location and Validate Error
    When I click on the "Delivery" "Product Factory navigation item"
    When I click on the "Locations" "Product Factory navigation sub item"
    And I click on the "EC_LOCATION_NAME" "Product Factory edit button"
    And I click on the "Region" "Product Factory dropdown"
    And I click on the "EC_REGION_NAME_TWO" "Product Factory dropdown option"
    And I click on the "Save" "Product Factory button"
    Then I validate text "The Location is already linked to one or more Instances, so you cannot change the Region" to be displayed for "Product Factory Toast Error Message Top-Right corner" element