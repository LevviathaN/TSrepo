@Reusable @Automationpractice
Feature: Automationpractice
  Reusable scenarios for Automationpractice site

  Scenario: Add Dress to Cart
    Given I am on "http://automationpractice.com/index.php" URL
    When I set "Dress" text to the "search_query" "element by name"
    And I click on the "submit_search" "element by name"
    And I hover over the "Printed Summer Dress" "AutomationPractice PLP Product Tile"
    And I click on the "Printed Summer Dress" "AutomationPractice PLP Add to Cart Button"
    Then I should see the "Product successfully added to your shopping cart" "exact element"