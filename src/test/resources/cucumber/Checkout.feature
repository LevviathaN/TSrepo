@Checkout
@Ignore
Feature: Checkout
  As a customer
  I want to be able to complete checkout process with any product
  In order to purchase the product

  Scenario Outline: Checkout
    Given I am on the "<pdp>" PDP
    When I click on the "Add to Cart" button
    And If Cross Sell popup appears I click on the "View Cart" button
    Then I click on the "Proceed to Checkout" button
    When I fill all required fields

    Examples:
      |pdp                                |
      |Tomorrow Hybrid Mattress           |
      |Tomorrow Cooling Memory Foam Pillow|
#      |Tomorrow Hypoallergenic Plush Pillow|
#      |Tomorrow White Comforter|
#      |Tomorrow White Sheet Set|
#      |Tomorrow Waterproof Mattress Protector|
#      |Tomorrow Blackout Curtains|
#      |Tomorrow Adjustable Bed|
#      |Tomorrow Platform Bed|