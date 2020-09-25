@ProductFactory @Region
Feature: Product Factory - Region - BPP-3648
  As a Senior Product Setter
  I need to be able to create and edit Regions in Product Factory
  So that it can be associated to a Location in Product Factory and Salesforce can utilise this concept.

  @Positive @Regression @P1 #TC-1874, TC-1890
  Scenario: Create new Region
    Given I execute "Log In" reusable step
    When I execute "Create Region Financial Dimension" reusable step
    When I execute "Create Region" reusable step
    And I click on the "EC_REGION_NAME" "Product Factory edit button"
    And I click on the "Audit Log" "Product Factory button title"
    And I remember "KW_AUTO_TODAY|DD/MM/YYYY" text as "EC_DATE" variable
    Then I validate text "CONTAINS=Created" to be displayed for "Product Factory Audit Log Created Node" element
    Then I validate text "Samuel Slade" to be displayed for "Product Factory Audit Log Created By Node" element
    #todo date format issue
#    Then I validate text "CONTAINS=EC_DATE" to be displayed for "Product Factory Audit Log Created When Node" element

  @Negative @P2 #TC-1885
  Scenario: Create new Region with Duplicate Data
    Given I execute "Log In" reusable step
    When I execute "Create Region Financial Dimension" reusable step
    When I execute "Create Region" reusable step
    When I execute "Create Region" reusable step replacing some steps
      |4|I set "EC_REGION_NAME" text to the "Name" "Product Factory text field"|
      |8|I should see the "Name must be unique" message                             |
    Then I validate text "Name must be unique" to be displayed for "Product Factory Toast Error Message Top-Right corner" element
    Then I validate text "Name must be unique" to be displayed for "Product Factory Name Filed Validation Error Message" element

  @Negative @P2 #TC-1888
  Scenario: Verify that Region page is not available for Product Setup Assistant role
    Given I execute "Log In" reusable step replacing some steps
      |3|I fill the "Product Factory Email" field with "MD_COMMON_CREDENTIALS_PRODUCTFACTORYJUNIORPRODUCTSETTER"|
      |4|I fill the "Product Factory Password" field with "MD_COMMON_CREDENTIALS_PRODUCTFACTORYJUNIORPSPASSWORD"|
    When I click on the "Delivery" "Product Factory navigation item"
    When I click on the "Region" "Product Factory navigation sub item"
    Then I shouldn't see the "Create" element
    Then I shouldn't see the "Product Factory Grid Button Icon Element" element

  @Negative @P2 #TC-1901, TC-1892
  Scenario: Change Financial Dimension for created Region
    Given I execute "Log In" reusable step
    When I execute "Create Region Financial Dimension" reusable step
    When I execute "Create Region Financial Dimension" reusable step replacing some steps
      |4|I set "RegionFDCodeTwo[######]" text to the "Code" "Product Factory text field"|
      |5|I set "RegionFDDescriptionTwo[######]" text to the "Description" "Product Factory text field"|
      |9|I should see the "EC_REGION_FD_CODE_TWO" element|
    When I execute "Create Region" reusable step
    And I click on the "EC_REGION_NAME" "Product Factory edit button"
    And I set "RegionNameTwo[######]" text to the "Name" "Product Factory text field"
    And I click on the "Financial Dimension" "Product Factory dropdown"
    And I click on the "EC_REGION_FD_DESCRIPTION_TWO" "Product Factory dropdown option"
    And I click on the "Save" "Product Factory button"
    Then I should see the "EC_REGION_NAME_TWO" element

  @Negative @P2 #TC-1902
  Scenario: Edit and save Region with Duplicate Data
    Given I execute "Log In" reusable step
    When I execute "Create Region Financial Dimension" reusable step
    When I execute "Create Region" reusable step
    When I execute "Create Region" reusable step replacing some steps
      |4|I set "RegionNameTwo[####]" text to the "Name" "Product Factory text field"|
      |8|I should see the "EC_REGION_NAME_TWO" element|
    And I click on the "EC_REGION_NAME_TWO" "Product Factory edit button"
    And I set "EC_REGION_NAME" text to the "Name" "Product Factory text field"
    And I click on the "Save" "Product Factory button"
    Then I validate text "Name must be unique" to be displayed for "Product Factory Toast Error Message Top-Right corner" element
    Then I validate text "Name must be unique" to be displayed for "Product Factory Name Filed Validation Error Message" element

  @Negative @P2 #TC-1893
  Scenario: Edit Region that is in Use
    Given I execute "Log In" reusable step
    And I execute "Create Region Financial Dimension" reusable step
    And I execute "Create Region Financial Dimension" reusable step replacing some steps
      |4|I set "RegionFDCodeTwo[######]" text to the "Code" "Product Factory text field"|
      |5|I set "RegionFDDescriptionTwo[######]" text to the "Description" "Product Factory text field"|
    And I execute "Create Region" reusable step
    And I execute "Create Location Financial Dimension" reusable step
    And I execute "Create Location" reusable step
    When I click on the "Delivery" "Product Factory navigation item"
    When I click on the "Region" "Product Factory navigation sub item"
    And I click on the "EC_REGION_NAME" "Product Factory edit button"
    And I click on the "Financial Dimension" "Product Factory dropdown"
    And I click on the "EC_REGION_FD_DESCRIPTION_TWO" "Product Factory dropdown option"
    And I click on the "Save" "Product Factory button"
    And I should see the "The Region is already linked to one or more Locations, so you cannot change the Financial Dimension" message