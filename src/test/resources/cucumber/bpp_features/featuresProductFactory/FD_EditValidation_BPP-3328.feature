@ProductFactory @FinancialDimensions
Feature: Financial Dimension - Edit Validation - BPP-3328
  As a Senior Product Setter
  I want the "Applies To" field on a Financial Dimension to be readonly when editing
  So that I cannot introduce breaking changes to existing Financial Dimensions.

  Background:
    Given I execute "Log In" reusable step

  @Positive @Regression @P1 #TC-1707
  Scenario Outline: Validate that Applies To drop-down is locked for <type> item
    When I click on the "Financials" "Product Factory navigation item"
    When I click on the "Financial Dimensions" "Product Factory navigation sub item"
    Then I click on the "Create" "Product Factory button"
    And I set "FDCode[####]" text to the "Code" "Product Factory text field"
    And I set "FDDescription[####]" text to the "Description" "Product Factory text field"
    And I click on the "Applies To" "Product Factory dropdown"
    And I click on the "<type>" "Product Factory dropdown option"
    And I click on the "Save" "Product Factory button"
    Then I should see the "EC_FD_CODE" element
    Then I should see the "EC_FD_DESCRIPTION" element
    And I should scroll to the bottom of the page
    And I click on the "EC_FD_CODE" "Product Factory edit button"
    Then Attribute "aria-pressed" of "<type>" "Product Factory Locked Applies To drop-down item" should have value "false"

    Examples:
      |type         |
      |Course       |
      |Material     |
      |Course Type  |
      |Material Type|
      |Location     |
      |Body         |

  @Positive @Regression @P1 #TC-1803
  Scenario: Verify that all Financial Dimensions are displayed in areas they are applied to
    When I click on the "Financials" "Product Factory navigation item"
    When I click on the "Financial Dimensions" "Product Factory navigation sub item"
    Then I click on the "Create" "Product Factory button"
    And I set "FDMaterialCode[####]" text to the "Code" "Product Factory text field"
    And I set "FDMaterialDescription[####]" text to the "Description" "Product Factory text field"
    And I click on the "Applies To" "Product Factory dropdown"
    And I click on the "Material" "Product Factory dropdown option"
    And I click on the "Save" "Product Factory button"
    Then I should see the "EC_FD_MATERIAL_CODE" element
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
    When I click on the "Financials" "Product Factory navigation item"
    When I click on the "Financial Dimensions" "Product Factory navigation sub item"
    Then I click on the "Create" "Product Factory button"
    And I set "FDCourseCode[####]" text to the "Code" "Product Factory text field"
    And I set "FDCourseDescription[####]" text to the "Description" "Product Factory text field"
    And I click on the "Applies To" "Product Factory dropdown"
    And I click on the "Course" "Product Factory dropdown option"
    And I click on the "Save" "Product Factory button"
    Then I should see the "EC_FD_COURSE_CODE" element
    When I click on the "Products" "Product Factory navigation item"
    And I click on the "Courses" "Product Factory navigation sub item"
    And I click on the "Financials" "Product Factory button"
    And I click on the "Entity Dimension" "Product Factory change button"
    Then I validate text "Course" to be displayed for "Product Factory Choose Dimension Popup Item Type" element
    And I press "MD_COMMON_KEYBOARD_ESCAPE" for "Product Factory Body of Webpage"
    And I click on the "Revenue Dimension" "Product Factory change button"
    Then I validate text "Course" to be displayed for "Product Factory Choose Dimension Popup Item Type" element
    And I press "MD_COMMON_KEYBOARD_ESCAPE" for "Product Factory Body of Webpage"
    And I click on the "Cancel" "Product Factory button"
    When I click on the "Financials" "Product Factory navigation item"
    When I click on the "Financial Dimensions" "Product Factory navigation sub item"
    Then I click on the "Create" "Product Factory button"
    And I set "FDBodyCode[####]" text to the "Code" "Product Factory text field"
    And I set "FDBodyDescription[####]" text to the "Description" "Product Factory text field"
    And I click on the "Applies To" "Product Factory dropdown"
    And I click on the "Body" "Product Factory dropdown option"
    And I click on the "Save" "Product Factory button"
    Then I should see the "EC_FD_BODY_CODE" element
    When I click on the "Programme" "Product Factory navigation item"
    And I click on the "Bodies" "Product Factory navigation sub item"
    And I click on the "Create" "Product Factory button"
    And I click on the "Financial Dimension" "Product Factory change button"
    Then I validate text "Body" to be displayed for "Product Factory Choose Dimension Popup Item Type" element
    And I press "MD_COMMON_KEYBOARD_ESCAPE" for "Product Factory Body of Webpage"
    And I click on the "Cancel" "Product Factory button"
    When I click on the "Financials" "Product Factory navigation item"
    When I click on the "Financial Dimensions" "Product Factory navigation sub item"
    Then I click on the "Create" "Product Factory button"
    And I set "FDLocationCode[####]" text to the "Code" "Product Factory text field"
    And I set "FDLocationDescription[####]" text to the "Description" "Product Factory text field"
    And I click on the "Applies To" "Product Factory dropdown"
    And I click on the "Location" "Product Factory dropdown option"
    And I click on the "Save" "Product Factory button"
    Then I should see the "EC_FD_LOCATION_CODE" element
    When I click on the "Delivery" "Product Factory navigation item"
    And I click on the "Locations" "Product Factory navigation sub item"
    And I click on the "Create" "Product Factory button"
    And I click on the "Financial Dimension" "Product Factory dropdown"
    Then I should see the "EC_FD_LOCATION_DESCRIPTION" "Product Factory Financial Dimension list in Locations"
    And I press "MD_COMMON_KEYBOARD_ESCAPE" for "Product Factory Body of Webpage"
    And I click on the "Cancel" "Product Factory button"
    When I click on the "Financials" "Product Factory navigation item"
    When I click on the "Financial Dimensions" "Product Factory navigation sub item"
    Then I click on the "Create" "Product Factory button"
    And I set "FDCourseTypeCode[####]" text to the "Code" "Product Factory text field"
    And I set "FDCourseTypeDescription[####]" text to the "Description" "Product Factory text field"
    And I click on the "Applies To" "Product Factory dropdown"
    And I click on the "Course Type" "Product Factory dropdown option"
    And I click on the "Save" "Product Factory button"
    Then I should see the "EC_FD_COURSE_TYPE_CODE" element
    When I click on the "Types" "Product Factory navigation item"
    And I click on the "Course Types" "Product Factory navigation sub item"
    And I click on the "Create" "Product Factory button"
    And I click on the "Cost Centre Financial Dimension" "Product Factory change button"
    Then I validate text "Course Type" to be displayed for "Product Factory Choose Dimension Popup Item Type" element
    And I press "MD_COMMON_KEYBOARD_ESCAPE" for "Product Factory Body of Webpage"
    And I click on the "Project Financial Dimension" "Product Factory change button"
    Then I validate text "Course Type" to be displayed for "Product Factory Choose Dimension Popup Item Type" element
    And I press "MD_COMMON_KEYBOARD_ESCAPE" for "Product Factory Body of Webpage"
    And I click on the "Cancel" "Product Factory button"
    When I click on the "Financials" "Product Factory navigation item"
    When I click on the "Financial Dimensions" "Product Factory navigation sub item"
    Then I click on the "Create" "Product Factory button"
    And I set "FDMaterialTypeCode[####]" text to the "Code" "Product Factory text field"
    And I set "FDMaterialTypeDescription[####]" text to the "Description" "Product Factory text field"
    And I click on the "Applies To" "Product Factory dropdown"
    And I click on the "Material Type" "Product Factory dropdown option"
    And I click on the "Save" "Product Factory button"
    Then I should see the "EC_FD_MATERIAL_TYPE_CODE" element
    When I click on the "Types" "Product Factory navigation item"
    And I click on the "Material Types" "Product Factory navigation sub item"
    And I click on the "Create" "Product Factory button"
    And I click on the "Financial Dimension" "Product Factory change button"
    Then I validate text "Material Type" to be displayed for "Product Factory Choose Dimension Popup Item Type" element
    And I press "MD_COMMON_KEYBOARD_ESCAPE" for "Product Factory Body of Webpage"
    And I click on the "Cancel" "Product Factory button"

  @Positive @Negative @P2 @BET #TC-1807
  Scenario: Verify that Financial Dimensions for Material or Course can not be changed after at least one item is created
    ######Precondition Create Material Financial Dimensions
    #First
    When I click on the "Financials" "Product Factory navigation item"
    When I click on the "Financial Dimensions" "Product Factory navigation sub item"
    Then I click on the "Create" "Product Factory button"
    And I set "FDMaterialCodeOne[####]" text to the "Code" "Product Factory text field"
    And I set "FDMaterialDescriptionOne[####]" text to the "Description" "Product Factory text field"
    And I click on the "Applies To" "Product Factory dropdown"
    And I click on the "Material" "Product Factory dropdown option"
    And I click on the "Save" "Product Factory button"
    Then I should see the "EC_FD_MATERIAL_CODE_ONE" element
    #Second
    When I click on the "Financials" "Product Factory navigation item"
    When I click on the "Financial Dimensions" "Product Factory navigation sub item"
    Then I click on the "Create" "Product Factory button"
    And I set "FDMaterialCodeTwo[####]" text to the "Code" "Product Factory text field"
    And I set "FDMaterialDescriptionTwo[####]" text to the "Description" "Product Factory text field"
    And I click on the "Applies To" "Product Factory dropdown"
    And I click on the "Material" "Product Factory dropdown option"
    And I click on the "Save" "Product Factory button"
    Then I should see the "EC_FD_MATERIAL_CODE_TWO" element
    #Third
    When I click on the "Financials" "Product Factory navigation item"
    When I click on the "Financial Dimensions" "Product Factory navigation sub item"
    Then I click on the "Create" "Product Factory button"
    And I set "FDMaterialCodeThree[####]" text to the "Code" "Product Factory text field"
    And I set "FDMaterialDescriptionThree[####]" text to the "Description" "Product Factory text field"
    And I click on the "Applies To" "Product Factory dropdown"
    And I click on the "Material" "Product Factory dropdown option"
    And I click on the "Save" "Product Factory button"
    Then I should see the "EC_FD_MATERIAL_CODE_THREE" element
    ######Add Material Financial Dimension
    When I click on the "Products" "Product Factory navigation item"
    And I click on the "Materials" "Product Factory navigation sub item"
    When I click on the "Financials" "Product Factory navigation item"
    And I click on the "Entity Dimension" "Product Factory change button"
    And I click on the "EC_FD_MATERIAL_CODE_ONE" "Product Factory change modal option"
    And I click on the "Revenue Dimension" "Product Factory change button"
    And I click on the "EC_FD_MATERIAL_CODE_TWO" "Product Factory change modal option"
    And I click on the "Save" "Product Factory button"
    ######Verify Materials
#    When I execute "Create Body Financial Dimension" reusable step
#    And I execute "Create Body" reusable step
#    And I execute "Create Level" reusable step
#    And I execute "Link Body To Level" reusable step
#    And I execute "Create Sitting" reusable step
#    And I execute "Create VAT Rule" reusable step
#    And I execute "Create Material Type Financial Dimension" reusable step
#    And I execute "Create Material Type" reusable step
#    And I execute "Create Paper" reusable step
#    And I execute "Link Body To Paper" reusable step
#    And I execute "Create Material" reusable step

