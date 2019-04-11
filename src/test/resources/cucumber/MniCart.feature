@MiniCart
@Ignore
Feature: Mini Cart
  As a customer
  I want to be able to add products to cart
  In order to purchase the product

  Scenario: Mini Cart items verification
    Given I am on the "Tomorrow Hybrid Mattress" PDP
    When I click on the "Add to Cart" button
    And If Cross Sell popup appears I click on the "View Cart" button
    And I click on Mini Cart icon
    Then I should see "Tomorrow Hybrid Mattress" in Mini Cart
