@ProductFactory @Materials
Feature: Materials - Create Materials - BPP-1099
  As a Product Setter
  I want to be able to create a Material
  so that the Material is available for Courses.

  Background:
    Given I execute "Generate ISBN" reusable step
    And I click on the " generate" "button"
    And I capture text data "ISBN Generator Number Field" as "EC_NEW_ISBN" variable
    Given I execute "Log In" reusable step
    And I remember "AutoMaterialTypeName" text as "EC_MATERIAL_TYPE_NAME" variable
    And I remember "AutoVatRuleCode" text as "EC_VAT_RULE_CODE" variable
    And I remember "AutoBodyName" text as "EC_BODY_NAME" variable
    And I remember "AutoLevelName" text as "EC_LEVEL_NAME" variable
    And I remember "AutoSittingName" text as "EC_SITTING_NAME" variable

    And I remember "AutoMaterialTypeNameTwo" text as "EC_MATERIAL_TYPE_NAME_TWO" variable
    And I remember "AutoVatRuleCodeTwo" text as "EC_VAT_RULE_CODE_TWO" variable
    And I remember "AutoBodyNameTwo" text as "EC_BODY_NAME_TWO" variable
    And I remember "AutoBodyShortNameTwo" text as "EC_BODY_SHORT_NAME_TWO" variable
    And I remember "AutoLevelNameTwo" text as "EC_LEVEL_NAME_TWO" variable
    And I remember "AutoLevelShortNameTwo" text as "EC_LEVEL_SHORT_NAME_TWO" variable
    And I remember "AutoSittingNameTwo" text as "EC_SITTING_NAME_TWO" variable

  @Positive @P1 #TC-918
  Scenario: Create Material Wizard
    Given I execute "Create Material" reusable step
    When I click on the "Products" "Product Factory navigation item"
    When I click on the "Materials" "Product Factory navigation sub item"
    Then I set "EC_MATERIAL_TYPE_NAME" text to the "Search" "Product Factory text field" from keyboard
    And I click on the "submit" "element by type"
    And I should see the "EC_MATERIAL_TYPE_NAME" "Product Factory edit button"

  @Positive @P1 @Amend @NotResolved
  Scenario: Amend Material Using Modal
    When I execute "Create Material" reusable step
    When I click on the "Products" "Product Factory navigation item"
    When I click on the "Materials" "Product Factory navigation sub item"
    And I click on the "Search" "Product Factory text field"
    Then I set "EC_MATERIAL_TYPE_NAME" text to the "Search" "Product Factory text field" from keyboard
    And I click on the "submit" "element by type"
    And I click on the "EC_MATERIAL_TYPE_NAME" "Product Factory edit button"
    And I click on the "Body" "Product Factory edit button"
    And I click on the "Change Body" "button"
    And I click on the "EC_BODY_NAME_TWO" "Product Factory select button"
    And I click on the "Next" "Product Factory button"
    And I "check" "EC_LEVEL_NAME_TWO" "Product Factory checkbox"
    And I click on the "Next" "Product Factory button"
    And I "check" "EC_SITTING_NAME_TWO" "Product Factory checkbox"
    And I click on the "Next" "Product Factory button"
    And I click on the "Finish" "Product Factory button"
    Then I should see the "EC_BODY_SHORT_NAME_TWO" "element"
    And I should see the "EC_LEVEL_SHORT_NAME_TWO" "element"
    And I should see the "EC_SITTING_NAME_TWO" "element"

    When I click on the "Basic Properties" "Product Factory edit button"
    And I click on the "EC_MATERIAL_TYPE_NAME_TWO" "Product Factory select button"
    And I set "999" text to the "Price (£)" "Product Factory text field"
    And I set "9" text to the "Weight (kg)" "Product Factory text field"
    And I set "EC_NEW_ISBN" text to the "ISBN" "Product Factory text field"
    And I set "09/09/2022" text to the "Expiry Date" "Product Factory text field"
    And I set "1020" text to the "Edition" "Product Factory text field"
    And I click on the "Production Method" "Product Factory dropdown"
    And I click on the "On Demand" "Product Factory dropdown option"
    And I click on the "Learning Media VAT Rule" "Product Factory change button"
    And I click on the "EC_VAT_RULE_CODE_TWO" "Product Factory select button"
    And I click on the "Course Material VAT Rule" "Product Factory change button"
    And I click on the "EC_VAT_RULE_CODE_TWO" "Product Factory select button"
    And I click on the "Save" "Product Factory button"
    Then I should see the "EC_MATERIAL_TYPE_NAME_TWO" "element"
    And I should see the "£999" "element"
    And I should see the "9kg" "element"
    And I should see the "09/09/2022" "element"