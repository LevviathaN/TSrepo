@SoppingCart
#@Ignore
Feature: Shopping Cart
  As a customer
  I want to be able to add products to cart
  In order to purchase the product

  Scenario: Change product quantity on Cart Page
    Given I am on the Cart Page
    Given There is "Tomorrow Hybrid Mattress" of size "Queen" added to Shopping Cart
    Then Quantity of "Tomorrow Hybrid Mattress" in the Cart should be equal to "1"
    Then Price of "Tomorrow Hybrid Mattress" in the Cart should be equal to "$990.00"
    When I increase quantity of "Tomorrow Hybrid Mattress"
    Then Quantity of "Tomorrow Hybrid Mattress" in the Cart should be equal to "2"
    Then Price of "Tomorrow Hybrid Mattress" in the Cart should be equal to "$1,980.00"
    When I decrease quantity of "Tomorrow Hybrid Mattress"
    Then Quantity of "Tomorrow Hybrid Mattress" in the Cart should be equal to "1"
    Then Price of "Tomorrow Hybrid Mattress" in the Cart should be equal to "$990.00"