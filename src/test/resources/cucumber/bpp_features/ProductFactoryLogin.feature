Feature: Product Factory
    As a product setter
    I want to be able to create Bodies in Product Factory
    In order to (just cause)

  Scenario: Log In
    Given I am on "http://pf-services-qa-1697893966.eu-west-2.elb.amazonaws.com" URL
    When I click on the "Sign in with Auth0" button
    And I fill the "email" field with "samuelslade@bpp.com"
    And I fill the "password" field with "Password1"
    And I click on the "Login" button which is 2
    When I wait for 4 seconds
    Then I should be redirected to the "Product Factory" page

  Scenario Outline: Create New Body
    When I click on the "Bodies" link
    Then I click on the "Create" button
    And I fill the "Short Name" field with "<short_name>"
    And I fill the "Name" field with "<name>"

    Examples:
    |short_name    |name     |
    |SomeShortName1|SomeName1|
    |SomeShortName2|SomeName2|
    |SomeShortName3|SomeName3|