@FinancialDimensions
Feature: Product Factory Financial Dimensions
  As a product setter
  I want to be able to create Bodies in Product Factory
  In order to (just cause)

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

  Scenario Outline: Create All Financial Dimensions
    Given I execute "Log In" reusable step
    When I click on the "Financial Dimensions" link
    Then I click on the "Create" button
    And I fill the "Code" PF field with "SomeCode1234"
    And I fill the "Description" PF field with "Some Description"
    And I click on the "Change" button
    And I select "<type>" from PF dialog
    And I click on the "Save" button
    Then I should see the "SomeCode1234" element

    Examples:
      |type         |
      |Course       |
      |Material     |
      |Course Type  |
      |Material Type|
      |Location     |
      |Body         |