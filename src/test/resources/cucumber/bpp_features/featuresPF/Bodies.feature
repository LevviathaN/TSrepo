@ProductFactory @Bodies
Feature: Product Factory Bodies
    As a product setter
    I want to be able to create Bodies in Product Factory
    In order to (just cause)

  Background:
    Given I execute "Log In" reusable step
    And I execute "Create Body Financial Dimension" reusable step

  @Test
  Scenario: Create New Body
    When I click on the "PRODUCT_FACTORY_WORK_LINK" link
    When I click on the "PRODUCT_FACTORY_BODY_LINK" link
    Then I click on the "PRODUCT_FACTORY_CREATE_BUTTON" button
    And I fill the "PRODUCT_FACTORY_SHORTNAME_TEXT_FIELD" PF field with "<short_name>"
    And I fill the "PRODUCT_FACTORY_NAME_TEXT_FIELD" PF field with "SomeName1"
    And I click on the "PRODUCT_FACTORY_CHANGE_BUTTON" button
    And I select "PRODUCT_FACTORY_SELECT_DROPDOWN" from PF dialog
    And I click on the "PRODUCT_FACTORY_SAVE_BUTTON" button
    Then I should see the "SomeShortName1" element

  @Create
  Scenario Outline: Create New Bodies
    When I click on the "work" link
    When I click on the "Bodies" link
    Then I click on the "Create" button
    And I fill the "Short Name" PF field with "<short_name>"
    And I fill the "Name" PF field with "<name>"
    And I click on the "Change" button
    And I select "EC_FD_CODE" from PF dialog
    And I click on the "Save" button
    Then I should see the "<short_name>" element

    Examples:
    |short_name    |name     |
    |SomeShortName3|SomeName3|
    |SomeShortName4|SomeName4|