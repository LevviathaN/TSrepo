@Demo @AutomationPratice @Checkout
Feature: Checkout Process
  As a Customer
  I want to be able to go through checkout process
  So that I can purchase a product in the online-store


  @Positive @ShoppingCart @PLP #First test scenario
  Scenario: Add Product to Cart
    Given I am on "http://automationpractice.com/index.php" URL
    When I set "Dress" text to the "search_query" "element by name"
    And I click on the "submit_search" "element by name"
    And I hover over the "Printed Summer Dress" "AutomationPractice PLP Product Tile"
    And I click on the "Printed Summer Dress" "AutomationPractice PLP Add to Cart Button"
    Then I should see the "Product successfully added to your shopping cart" "exact element"

  @Negative @ShoppingCart @PLP #And example of negative scenario
  Scenario: Delete Product from Mini Cart
    Given I am on "http://automationpractice.com/index.php" URL
    When I set "Dress" text to the "search_query" "element by name"
    And I click on the "submit_search" "element by name"
    And I hover over the "Printed Summer Dress" "AutomationPractice PLP Product Tile"
    And I click on the "Printed Summer Dress" "AutomationPractice PLP Add to Cart Button"
    Then I should see the "Product successfully added to your shopping cart" "exact element"
    And I click on the "Close window" "element by title"
    When I hover over the "Cart" button
    And I click on the "Printed Summer Dress" "AutomationPractice Mini Cart Delete Button"
    When I hover over the "Cart" button
    Then I shouldn't see the "Printed Summer Dress" "AutomationPractice Mini Cart Delete Button"
