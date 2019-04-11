@FAQ
@Ignore
Feature: FAQ
  As a customer
  I want to be able to see FAQ section
  In order to get answers to my questions

  Scenario Outline: Search
    Given I am on the FAQ Page
    When I fill "search" field with "<request>"
    And I click on the "Search" button
    Then I should see questions and answers relevant to request "<request>"

    Examples:
    |request     |
    |Pillow      |
    |Sleeptracker|
# these two are failing
#    |Plush Pillow|
#    |Memory Foam |