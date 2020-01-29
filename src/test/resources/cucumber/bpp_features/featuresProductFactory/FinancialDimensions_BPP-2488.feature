@ProductFactory @FinancialDimensions
Feature: Financial Dimension - Reference Data Create Record - BPP-2488
  As a Senior Product Setter
  I want to be able to create new Financial Dimension records
  so that I can setup appropriate codes for accounting purposes.

  Background:
    Given I execute "Log In" reusable step

  @Positive @Regression @P1 #TC-1532
  Scenario Outline: Create Financial Dimensions record <type> item
    When I click on the "Financials" "Product Factory navigation item"
    When I click on the "Financial Dimensions" "Product Factory navigation sub item"
    Then I click on the "Create" "Product Factory button"
    And I set "FDCode[####]" text to the "Code" "Product Factory text field"
    And I set "FDDescription[####]" text to the "Description" "Product Factory text field"
    And I click on the "Applies To" "Product Factory dropdown"
    And I click on the "<type>" "Product Factory dropdown option"
    And I click on the "Save" "Product Factory button"
    Then I should see the "EC_FD_CODE" element

    Examples:
      |type         |
      |Course       |
      |Material     |
      |Course Type  |
      |Material Type|
      |Location     |
      |Body         |
      |Region       |

  @Negative @P2 #TC-1542
  Scenario Outline: Create Financial Dimension record <type> item with Duplicate Data
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
    When I click on the "Financials" "Product Factory navigation item"
    When I click on the "Financial Dimensions" "Product Factory navigation sub item"
    Then I click on the "Create" "Product Factory button"
    And I set "FDCodeTwo[####]" text to the "Code" "Product Factory text field"
    And I set "FDDescriptionTwo[####]" text to the "Description" "Product Factory text field"
    And I click on the "Applies To" "Product Factory dropdown"
    And I click on the "<type>" "Product Factory dropdown option"
    And I click on the "Save" "Product Factory button"
    Then I should see the "EC_FD_CODE_TWO" element
    Then I should see the "EC_FD_DESCRIPTION_TWO" element
    Then I click on the "Create" "Product Factory button"
    And I set "EC_FD_CODE" text to the "Code" "Product Factory text field"
    And I set "EC_FD_DESCRIPTION" text to the "Description" "Product Factory text field"
    And I click on the "Applies To" "Product Factory dropdown"
    And I click on the "<type>" "Product Factory dropdown option"
    And I click on the "Save" "Product Factory button"
    Then I validate text "A Financial Dimension with the same code already targets '<Error Type>'" to be displayed for "Product Factory Code Filed Validation Error Message" element
    Then I validate text "A Financial Dimension with the same code already targets '<Error Type>'" to be displayed for "Product Factory Toast Error Message Top-Right corner" element

    Examples:
      |type         |Error Type   |
      |Course       |Course       |
      |Material     |Material     |
      |Course Type  |CourseType   |
      |Material Type|MaterialType |
      |Location     |Location     |
      |Body         |Body         |