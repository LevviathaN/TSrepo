@API
Feature: Product Factory API Data Creation
  As an Admin I should be able to create items in PF using API

  @BET
  Scenario: Create Financial Dimension
    When I create new Financial Dimension

  @ISBN
  Scenario: Generate new ISBN
    When I generate new ISBN code
