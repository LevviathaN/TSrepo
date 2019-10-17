Feature: Is it Friday yet?
  Everybody wants to know when it's Friday

  Scenario: Sunday isn't Friday
    Given I am on "http://pf-services-qa-1697893966.eu-west-2.elb.amazonaws.com" URL
    When I click on the "Sign in with Auth0" button
    And I fill the "email" field with "samuelslade@bpp.com"
    And I fill the "password" field with "Password1"
    And I click on the "Login" button which is "2"

