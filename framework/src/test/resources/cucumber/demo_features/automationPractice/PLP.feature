@Demo @AutomationPratice @PLP
Feature: Checkout Process
  As a Customer
  I want to be able to go through checkout process
  So that I can purchase a product in the online-store


  @Positive @PDP
  Scenario: Validate Price on PLP and PDP
    When I execute "Add Dress to Cart" reusable step
    And I capture text data "Automationpractice Add to Cart Total Price" as "EC_PRICE" variable
    And I click on the "Close window" "element by title"

  @Positive
  Scenario: Validate Compare Functionality
    Given I am on "http://automationpractice.com/index.php" URL
    When I set "Dress" text to the "search_query" "element by name"
    And I click on the "submit_search" "element by name"
    And I hover over the "Printed Summer Dress" "AutomationPractice PLP Product Tile"
    And I click on the "Printed Summer Dress" "AutomationPractice PLP Add to Compare Button"
    And I hover over the "Printed Dress" "AutomationPractice PLP Product Tile"
    And I click on the "Printed Dress" "AutomationPractice PLP Add to Compare Button"
    And I validate text "2" to be displayed for "Automationpractice Compare Button Items" element