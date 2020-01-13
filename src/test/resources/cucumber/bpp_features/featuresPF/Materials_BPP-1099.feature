@ProductFactory @Materials
Feature: Materials - Create Materials - BPP-1099
  As a Product Setter
  I want to be able to create a Material
  so that the Material is available for Courses.

  Background:
    Given I execute "Log In" reusable step
    When I execute "Create Body Financial Dimension" reusable step
    And I execute "Create Body" reusable step
    And I execute "Create Level" reusable step
    And I execute "Create Sitting" reusable step
    And I execute "Create VAT Rule" reusable step
    And I execute "Create Material Type Financial Dimension" reusable step
    And I execute "Create Material Type" reusable step
    And I execute "Create Paper" reusable step
    And I execute "Link Body To Paper" reusable step

  @Positive @P1
  Scenario: Wizard Traversal
    When I click on the "Products" "Product Factory navigation item"
    When I click on the "Materials" "Product Factory navigation sub item"
    And I click on the "Create" "Product Factory button"
    And I click on the "BPP Learning Media" "Product Factory button"
    And I click on the "EC_MATERIAL_TYPE_NAME" "Product Factory select button"
    And I set "1000" text to the "Price (£)" "Product Factory text field"
    And I set "10" text to the "Weight (kg)" "Product Factory text field"
    And I set "123" text to the "ISBN" "Product Factory text field"
    And I set "10/10/2022" text to the "Expiry Date" "Product Factory text field"
    And I click on the "Learning Media VAT Rule" "Product Factory change button"
    And I click on the "EC_VAT_RULE_CODE" "Product Factory select button"
    And I click on the "Course Material VAT Rule" "Product Factory change button"
    And I click on the "EC_VAT_RULE_CODE" "Product Factory select button"
    And I click on the "Next" "Product Factory button"
    And I click on the "Choose a Body" "button"
    And I click on the "EC_BODY_NAME" "Product Factory select button"
    And I click on the "Next" "Product Factory button"
    And I "check" "EC_LEVEL_NAME" "Product Factory checkbox"
    And I click on the "Next" "Product Factory button"
    And I "check" "EC_SITTING_NAME" "Product Factory checkbox"
    And I click on the "Next" "Product Factory button"
    And I click on the "Finish" "Product Factory button"


#  Scenario: Choosing a Paper
#    When I click on the "Programme" "Product Factory navigation item"
#    When I click on the "Bodies" "Product Factory navigation sub item"
#
#  Scenario: Choosing a Body
#    When I click on the "Programme" "Product Factory navigation item"
#    When I click on the "Bodies" "Product Factory navigation sub item"
#
#  Scenario: Material Type Constraints
#    When I click on the "Programme" "Product Factory navigation item"
#    When I click on the "Bodies" "Product Factory navigation sub item"