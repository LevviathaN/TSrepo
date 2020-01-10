@ProductFactory @MaterialTypes
Feature: Materials - Create Material Type - BPP-1090
  As a Senior Product Setter
  I want to be able to Create Material Types
  so that I create Material of this type.

  Background:
    Given I execute "Log In" reusable step
    When I execute "Create Material Type Financial Dimension" reusable step

  @Positive @Regression @P1
  Scenario: Add a New Material Type Using a Modal
    When I click on the "Types" "Product Factory navigation item"
    When I click on the "Material Types" "Product Factory navigation sub item"
    Then I click on the "Create" "Product Factory button"
    And I set "MaterialTypeName[####]" text to the "Name" "Product Factory text field"
    And I set "MaterialTypeDescription[####]" text to the "Description" "Product Factory text field"
    And I click on the "Financial Dimension" "Product Factory change button"
    And I click on the "EC_MATERIAL_TYPE_FD_CODE" "Product Factory select button"
    And I click on the "Save" "Product Factory button"
    Then I should see the "EC_MATERIAL_TYPE_DESCRIPTION" element