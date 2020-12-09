@Demo @AutomationPratice @ShoppingCart
Feature: Shopping Cart
  As a Customer
  I want to be able to add products to shopping cart
  So that I can purchase a product in the online-store

  @Positive @MiniCart
  Scenario: Validate Price in Mini Cart
    When I execute "Add Dress to Cart" reusable step
    And I wait for "3" seconds
    And I capture text data "Automationpractice Add to Cart Total Price" as "EC_PRICE" variable
    And I click on the "Close window" "element by title"
    And I hover over the "Cart" button
    And I validate text "EC_PRICE" to be displayed for "Printed Su..." "AutomationPractice Mini Cart Price"

  @Positive @MiniCart
  Scenario: Validate Price on the Shopping Cart Page
    When I execute "Add Dress to Cart" reusable step
    And I wait for "3" seconds
    And I capture text data "Automationpractice Add to Cart Total Price" as "EC_PRICE" variable
    And I click on the "Close window" "element by title"
    And I click on the "Cart" button
    And I validate text "EC_PRICE" to be displayed for "Printed Summer Dress" "AutomationPractice Shopping Cart Price"