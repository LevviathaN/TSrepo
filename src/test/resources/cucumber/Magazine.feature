@Magazine
@Ignore
Feature: Magazine
  As a customer
  I want to be able to see magazine articles
  In order to get fresh news from social media

  Scenario Outline: Articles category filter verification
    Given I am on the Magazine Page
    When I click on the "<category>" link in Navigation Pane
    Then I should see only "<category>" category articles

    Examples:
    |category   |
    |IN BED WITH|
    |PLAY       |
    |HEAL       |
    |SLEEP      |
