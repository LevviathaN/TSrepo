@CrossSell
@Ignore
Feature: Cross Sell
  As a customer
  I want to see Cross Sell promotions when i add mattress to cart
  In order to purchase mattress accessories

  Scenario: Add product from Cross Sell popup to cart
    Given I am on the "Tomorrow Hybrid Mattress" PDP
    When I click on the "Add to Cart" button
    And I add "Tomorrow Waterproof Mattress Protector" to cart from Cross Sell popup
    And I add "Tomorrow Cooling Memory Foam Pillow" to cart from Cross Sell popup
    Then I should see the "Item added to cart" message
    When I click on the "View Cart" button in Cross Sell modal
    Then I should be redirected to the "Shopping Cart | Tomorrow Sleep" page
    And I should see "Tomorrow Waterproof Mattress Protector" in the Cart
    And I should see "Tomorrow Cooling Memory Foam Pillow" in the Cart
    And I should see "Tomorrow Hybrid Mattress" in the Cart
    Then I empty Shopping Cart
    And I wait for 3 seconds