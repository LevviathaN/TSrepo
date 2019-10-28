#@Ignore
Feature: Product Factory Bodies
    As a product setter
    I want to be able to create Bodies in Product Factory
    In order to (just cause)

  Scenario Outline: Create New Bodies
#    Given I am logged into Product Factory as "Senior Product Setter"
    When I execute "Log In" reusable step
    When I click on the "Bodies" link
    Then I click on the "Create" button
    And I fill the "Short Name" PF field with "<short_name>"
    And I fill the "Name" PF field with "<name>"
    And I click on the "Save" button
    Then I should see the "<short_name>" element

    Examples:
    |short_name    |name     |
    |SomeShortName3|SomeName3|
#    |SomeShortName4|SomeName4|
