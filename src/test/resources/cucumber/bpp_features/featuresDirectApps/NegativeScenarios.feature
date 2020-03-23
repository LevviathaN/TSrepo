@DirectApps @Negative
Feature: Negative Scenarios Apprenticeship Application

  Background:
    Given I execute "Register New Student Account" reusable step

  @NegativeScenarios
  Scenario: Negative Scenarios
    When I execute "Create Professional Apprenticeships Application UAT Accounting Option Two" reusable step
    And I execute "Negative Scenarios - Validate User Cannot Proceed" reusable step