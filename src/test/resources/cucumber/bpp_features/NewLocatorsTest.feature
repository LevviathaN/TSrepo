@ProductFactory @Bodies @NewLocators
Feature: Product Factory Random Bodies
  As a product setter
  I want to be able to create Bodies with random names in Product Factory
  In order to (just cause)

  Background:
    Given I execute "Log In" reusable step
    And I execute "Create Body Financial Dimension" reusable step without some steps

  @Create @Random @Single
  Scenario: Create New Body Using Random Data
    When I click on the "Programme" "Product Factory navigation item"
    When I click on the "Bodies" "Product Factory navigation sub item"
    Then I click on the "Product Factory create button"
    And I set "BodyShortName[####]" text to the "Short Name" "Product Factory text field"
    And I set "BodyName[####]" text to the "Name" "Product Factory text field"
    And I click on the "Change" "button"
    And I click on the "EC_FD_CODE" "Product Factory change modal option"
    And I click on the "Save" "button"
    Then I should see the "EC_BODY_SHORT_NAME" element