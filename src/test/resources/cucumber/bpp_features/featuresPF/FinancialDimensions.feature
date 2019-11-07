@ProductFactory @FinancialDimensions
Feature: Product Factory Financial Dimensions
  As a product setter
  I want to be able to create Bodies in Product Factory
  In order to (just cause)

  Background:
    Given I execute "Log In" reusable step

  @Single @Hardcoded
  Scenario: Create Body Financial Dimension
    Given I execute "Log In" reusable step
    When I click on the "Financial Dimensions" link
    Then I click on the "Create" button
    And I fill the "Code" PF field with "SomeCode1234"
    And I fill the "Description" PF field with "Some Description"
    And I click on the "Change" button
    And I select "Body" from PF dialog
    And I click on the "Save" button
    Then I should see the "SomeCode1234" element

  @Multiple
  Scenario Outline: Create All Financial Dimensions
    When I click on the "attach_money" link
    Then I click on the "Financial Dimensions" link
    Then I click on the "Create" button
    And I remember "AutoFDCode<KW_AUTO_RANDOMNUMBER|####>" text as "EC_FD_CODE" variable
    And I fill the "Code" PF field with "EC_FD_CODE"
    And I remember "AutoFDDescription<KW_AUTO_RANDOMNUMBER|####>" text as "EC_FD_DESC" variable
    And I fill the "Description" PF field with "EC_FD_DESC"
    And I click on the "xpath=//div[@aria-pressed='false']" element
    And I click on the "<type>" option
    And I click on the "Save" button
    Then I should see the "EC_FD_CODE" element

    Examples:
      |type         |
      |Course       |
      |Material     |
      |Course Type  |
      |Material Type|
      |Location     |
      |Body         |