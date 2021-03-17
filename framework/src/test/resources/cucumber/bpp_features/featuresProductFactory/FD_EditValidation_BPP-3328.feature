@ProductFactory @FinancialDimensions
Feature: Financial Dimension - Edit Validation - BPP-3328
  As a Senior Product Setter
  I want the "Applies To" field on a Financial Dimension to be readonly when editing
  So that I cannot introduce breaking changes to existing Financial Dimensions.

  Background:
    Given I execute "Log In" reusable step
    And I execute "Remember Variables For Creation " reusable step
    And I execute "Remember Variables Two" reusable step

  @Positive @Regression @P1 #TC-1707
  Scenario Outline: Validate that Applies To drop-down is locked for <type> item
    When I click on the "Financials" "Product Factory navigation item"
    When I click on the "Financial Dimensions" "Product Factory navigation sub item"
    And I click on the "<EC>" "Product Factory edit button"
    Then Attribute "aria-pressed" of "<type>" "Product Factory Locked Applies To drop-down item" should have value "false"

    Examples:
      |type         |EC|
      |Course       |AutoCourseFDCode|
      |Material     |AutoMaterialFDCode|
      |Course Type  |AutoCourseTypeFDCode|
      |Material Type|AutoMaterialTypeFDCode|
      |Location     |AutoLocationFDCode|
      |Body         |AutoBodyFDCode|

  @Positive @Regression @P1 #TC-1803
  Scenario: Verify that all Financial Dimensions are displayed in areas they are applied to
    #Material
    When I click on the "Products" "Product Factory navigation item"
    And I click on the "Materials" "Product Factory navigation sub item"
    And I click on the "Financials" "Product Factory button"
    And I click on the "Entity Dimension" "Product Factory change button"
    Then I validate text "Material" to be displayed for "Product Factory Choose Dimension Popup Item Type" element
    And I press "MD_COMMON_KEYBOARD_ESCAPE" for "Product Factory Body of Webpage"
    And I click on the "Revenue Dimension" "Product Factory change button"
    Then I validate text "Material" to be displayed for "Product Factory Choose Dimension Popup Item Type" element
    And I press "MD_COMMON_KEYBOARD_ESCAPE" for "Product Factory Body of Webpage"
    And I click on the "Cancel" "Product Factory button"
    #Course
    When I click on the "Products" "Product Factory navigation item"
    And I click on the "Courses" "Product Factory navigation sub item"
    And I click on the "Financials" "button"
    And I click on the "Entity Dimension" "Product Factory change button"
    Then I validate text "Course" to be displayed for "Product Factory Choose Dimension Popup Item Type" element
    And I press "MD_COMMON_KEYBOARD_ESCAPE" for "Product Factory Body of Webpage"
    And I click on the "Revenue Dimension" "Product Factory change button"
    Then I validate text "Course" to be displayed for "Product Factory Choose Dimension Popup Item Type" element
    And I press "MD_COMMON_KEYBOARD_ESCAPE" for "Product Factory Body of Webpage"
    And I click on the "Cancel" "Product Factory button"
    #Body
    When I click on the "Programme" "Product Factory navigation item"
    And I click on the "Bodies" "Product Factory navigation sub item"
    And I click on the "Create" "Product Factory button"
    And I click on the "Financial Dimension" "Product Factory dropdown"
    Then I should see the "EC_BODY_FD_DESCRIPTION" "Product Factory dropdown option"
    And I press "MD_COMMON_KEYBOARD_ESCAPE" for "Product Factory Body of Webpage"
    And I click on the "Cancel" "Product Factory button"
    #Location
    When I click on the "Delivery" "Product Factory navigation item"
    And I click on the "Locations" "Product Factory navigation sub item"
    And I click on the "Create" "Product Factory button"
    And I click on the "Financial Dimension" "Product Factory dropdown"
    Then I should see the "EC_LOCATION_FD_DESCRIPTION" "Product Factory dropdown option"
    And I press "MD_COMMON_KEYBOARD_ESCAPE" for "Product Factory Body of Webpage"
    And I click on the "Cancel" "Product Factory button"
    #Course Type
    When I click on the "Types" "Product Factory navigation item"
    And I click on the "Course Types" "Product Factory navigation sub item"
    And I click on the "Create" "Product Factory button"
    And I click on the "Cost Centre Financial Dimension" "Product Factory dropdown"
    Then I should see the "EC_COURSE_TYPE_FD_CODE" "Product Factory dropdown option"
    And I press "MD_COMMON_KEYBOARD_ESCAPE" for "Product Factory Body of Webpage"
    And I click on the "Project Financial Dimension" "Product Factory dropdown"
    Then I should see the "EC_COURSE_TYPE_FD_CODE" "Product Factory dropdown option"
    And I press "MD_COMMON_KEYBOARD_ESCAPE" for "Product Factory Body of Webpage"
    And I click on the "Cancel" "Product Factory button"
    #Material Type
    When I click on the "Types" "Product Factory navigation item"
    And I click on the "Material Types" "Product Factory navigation sub item"
    And I click on the "Create" "Product Factory button"
    And I click on the "Financial Dimension" "Product Factory change button"
    Then I validate text "Material Type" to be displayed for "Product Factory Choose Dimension Popup Item Type" element
    And I press "MD_COMMON_KEYBOARD_ESCAPE" for "Product Factory Body of Webpage"
    And I click on the "Cancel" "Product Factory button"

  @Positive @Negative @P2 #TC-1807 todo: should work, but too much data on environment. Will see on next run.
  Scenario: Verify that Financial Dimensions for Material or Course can not be changed after at least one item is created
#    ######Precondition Create Material Financial Dimensions
#    ###First Financial Dimension for Material Block (Pre-requisite)
#    When I execute "Create Material Financial Dimension" reusable step replacing some steps
#      |4|I set "MaterialFDCodeOne[######]" text to the "Code" "Product Factory text field"|
#      |5|I set "MaterialFDDescriptionOne[######]" text to the "Description" "Product Factory text field"|
#      |11|I should see the "EC_MATERIAL_FD_CODE_ONE" element|
#
#    ###Second Financial Dimension for Material Block (Pre-requisite)
#    When I execute "Create Material Financial Dimension" reusable step replacing some steps
#      |4|I set "MaterialFDCodeTwo[######]" text to the "Code" "Product Factory text field"|
#      |5|I set "MaterialFDDescriptionTwo[######]" text to the "Description" "Product Factory text field"|
#      |11|I should see the "EC_MATERIAL_FD_CODE_TWO" element|
#
#    ###Third Financial Dimension for Material Block (Data Preparation)
#    When I execute "Create Material Financial Dimension" reusable step replacing some steps
#      |4|I set "MaterialFDCodeThree[######]" text to the "Code" "Product Factory text field"|
#      |5|I set "MaterialFDDescriptionThree[######]" text to the "Description" "Product Factory text field"|
#      |11|I should see the "EC_MATERIAL_FD_CODE_THREE" element|
#
#    ###Fourth Financial Dimension for Material Block (Data Preparation)
#    When I execute "Create Material Financial Dimension" reusable step replacing some steps
#      |4|I set "MaterialFDCodeFour[######]" text to the "Code" "Product Factory text field"|
#      |5|I set "MaterialFDDescriptionFour[######]" text to the "Description" "Product Factory text field"|
#      |11|I should see the "EC_MATERIAL_FD_CODE_FOUR" element|
#
#    ######Precondition Create Course Financial Dimensions
#    ###First Financial Dimension for Material Block (Pre-requisite)
#    When I execute "Create Course Financial Dimension" reusable step replacing some steps
#      |4|I set "CourseFDCodeOne[######]" text to the "Code" "Product Factory text field"|
#      |5|I set "CourseFDDescriptionOne[######]" text to the "Description" "Product Factory text field"|
#      |11|I should see the "EC_COURSE_FD_CODE_ONE" element|
#
#    ###Second Financial Dimension for Material Block (Pre-requisite)
#    When I execute "Create Course Financial Dimension" reusable step replacing some steps
#      |4|I set "CourseFDCodeTwo[######]" text to the "Code" "Product Factory text field"|
#      |5|I set "CourseFDDescriptionTwo[######]" text to the "Description" "Product Factory text field"|
#      |11|I should see the "EC_COURSE_FD_CODE_TWO" element|
#
#    ###Third Financial Dimension for Material Block (Data Preparation)
#    When I execute "Create Course Financial Dimension" reusable step replacing some steps
#      |4|I set "CourseFDCodeThree[######]" text to the "Code" "Product Factory text field"|
#      |5|I set "CourseFDDescriptionThree[######]" text to the "Description" "Product Factory text field"|
#      |11|I should see the "EC_COURSE_FD_CODE_THREE" element|
#
#    ###Fourth Financial Dimension for Material Block (Data Preparation)
#    When I execute "Create Course Financial Dimension" reusable step replacing some steps
#      |4|I set "CourseFDCodeFour[######]" text to the "Code" "Product Factory text field"|
#      |5|I set "CourseFDDescriptionFour[######]" text to the "Description" "Product Factory text field"|
#      |11|I should see the "EC_COURSE_FD_CODE_FOUR" element|
#
#    ###Reusable steps for Creating Course
#    And I execute "Create Body Financial Dimension" reusable step
#    And I execute "Create Body" reusable step
#    And I execute "Create VAT Rule" reusable step
#    And I execute "Create Course Type Financial Dimension" reusable step
#    And I execute "Create Course Type" reusable step
#    And I execute "Create Location Financial Dimension" reusable step
#    And I execute "Create Region Financial Dimension" reusable step
#    And I execute "Create Region" reusable step
#    And I execute "Create Location" reusable step
#    And I execute "Create Level" reusable step
#    And I execute "Create Paper" reusable step
#    And I execute "Link Body To Level" reusable step
#    And I execute "Link Body To Paper" reusable step
#    And I execute "Link Paper To Level" reusable step
#    And I execute "Create Sitting" reusable step
#    And I execute "Create Material Type Financial Dimension" reusable step
#    And I execute "Create Material Type" reusable step
#    And I execute "Create Session Duration" reusable step
#    And I execute "Create Client" reusable step
#    And I execute "Create Material" reusable step
#    And I execute "Create Pricing Matrix" reusable step
#    And I execute "Create Pricing Matrix Full" reusable step
#
#    ###Create Course
#    Then I execute "Create Course" reusable step
#    And I execute "Create Course Instance" reusable step

   ###Edit Course after item creation
    When I click on the "Products" "Product Factory navigation item"
    And I click on the "Materials" "Product Factory navigation sub item"
    And I click on the "Financials" "Product Factory button"
    And I click on the "Entity Dimension" "Product Factory change button"
    And I click on the "EC_MATERIAL_FD_CODE_TWO" "Product Factory change modal option"
    And I click on the "Save" "Product Factory button"
    Then I validate text "Materials have already been created, so you cannot change the Material Financial Dimensions" to be displayed for "Product Factory Toast Error Message Top-Right corner" element
    And I click on the "Cancel" "Product Factory button"
    And I click on the "Financials" "Product Factory button"
    And I click on the "Revenue Dimension" "Product Factory change button"
    And I click on the "EC_MATERIAL_FD_CODE_TWO" "Product Factory change modal option"
    And I click on the "Save" "Product Factory button"
    Then I validate text "Materials have already been created, so you cannot change the Material Financial Dimensions" to be displayed for "Product Factory Toast Error Message Top-Right corner" element
    And I click on the "Cancel" "Product Factory button"
    And I click on the "Financials" "Product Factory button"
    And I click on the "Entity Dimension" "Product Factory change button"
    And I click on the "EC_MATERIAL_FD_CODE_TWO" "Product Factory change modal option"
    And I click on the "Revenue Dimension" "Product Factory change button"
    And I click on the "EC_MATERIAL_FD_CODE_TWO" "Product Factory change modal option"
    And I click on the "Save" "Product Factory button"
    Then I validate text "Materials have already been created, so you cannot change the Material Financial Dimensions" to be displayed for "Product Factory Toast Error Message Top-Right corner" element
    And I click on the "Cancel" "Product Factory button"

    ###Edit Material after item creation
    When I click on the "Products" "Product Factory navigation item"
    And I click on the "Materials" "Product Factory navigation sub item"
    And I click on the "Financials" "Product Factory button"
    And I click on the "Entity Dimension" "Product Factory change button"
    And I click on the "EC_MATERIAL_FD_CODE_TWO" "Product Factory change modal option"
    And I click on the "Save" "Product Factory button"
    Then I validate text "Materials have already been created, so you cannot change the Material Financial Dimensions" to be displayed for "Product Factory Toast Error Message Top-Right corner" element
    And I click on the "Cancel" "Product Factory button"
    And I click on the "Financials" "Product Factory button"
    And I click on the "Revenue Dimension" "Product Factory change button"
    And I click on the "EC_MATERIAL_FD_CODE_TWO" "Product Factory change modal option"
    And I click on the "Save" "Product Factory button"
    Then I validate text "Materials have already been created, so you cannot change the Material Financial Dimensions" to be displayed for "Product Factory Toast Error Message Top-Right corner" element
    And I click on the "Cancel" "Product Factory button"
    And I click on the "Financials" "Product Factory button"
    And I click on the "Entity Dimension" "Product Factory change button"
    And I click on the "EC_MATERIAL_FD_CODE_TWO" "Product Factory change modal option"
    And I click on the "Revenue Dimension" "Product Factory change button"
    And I click on the "EC_MATERIAL_FD_CODE_TWO" "Product Factory change modal option"
    And I click on the "Save" "Product Factory button"
    Then I validate text "Materials have already been created, so you cannot change the Material Financial Dimensions" to be displayed for "Product Factory Toast Error Message Top-Right corner" element
    And I click on the "Cancel" "Product Factory button"
