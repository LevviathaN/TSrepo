@Demo @AutomationPratice @PLP
Feature: Checkout Process
  As a Customer
  I want to be able to go through checkout process
  So that I can purchase a product in the online-store


  @Positive @PDP
  Scenario: Validate Price on PLP and PDP
    When I execute "Add Dress to Cart" reusable step
    And I wait for "3" seconds
    And I capture text data "Automationpractice Add to Cart Total Price" as "EC_PRICE" variable
    And I click on the "Close window" "element by title"
    And I scroll the page by "0" horizontal and "300" vertical
    And I hover over the "Printed Summer Dress" "AutomationPractice PLP Product Tile"
    And I click on the "Printed Summer Dress" "AutomationPractice PLP More Button"
    And I wait for "3" seconds
    And I validate text "EC_PRICE" to be displayed for "our_price_display" "element by id"

  @Positive
  Scenario: Validate Compare Functionality
    Given I am on "http://automationpractice.com/index.php" URL
    When I set "Dress" text to the "search_query" "element by name"
    And I click on the "submit_search" "element by name"
    And I hover over the "Printed Summer Dress" "AutomationPractice PLP Product Tile"
    And I click on the "Printed Summer Dress" "AutomationPractice PLP Add to Compare Button"
    And I hover over the "Printed Dress" "AutomationPractice PLP Product Tile"
    And I click on the "Printed Dress" "AutomationPractice PLP Add to Compare Button"
    And I wait for "2" seconds
    And I validate text "1" to be displayed for "Automationpractice Compare Button Items" element