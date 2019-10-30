@Ignore
@Reusable
Feature: Product Factory
  As a product setter
  I want to be able to log into Product Factory
  In order to (just cause)

  Scenario: Log In
    Given I am on "http://pf-services-qa-1697893966.eu-west-2.elb.amazonaws.com" URL
    When I click on the "Sign in with Auth0" button
    And I fill the "email" field with "samuelslade@bpp.com"
    And I fill the "password" field with "Password1"
    And I click on the "Login" button which is "2"
    When I wait for "4" seconds
    Then I should be redirected to the "Product Factory" page

  Scenario: Create Body Financial Dimension
    When I click on the "Financial Dimensions" link
    Then I click on the "Create" button
    And I fill the "Code" PF field with "SomeCode1234"
    And I fill the "Description" PF field with "Some Description"
    And I click on the "Change" button
    And I select "Body" from PF dialog
    And I click on the "Save" button
    Then I should see the "SomeCode1234" element