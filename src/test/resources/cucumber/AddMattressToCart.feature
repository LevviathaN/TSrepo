@AddToCart
Feature: Add To Cart
  As an customer
  I want to be able to add desirable product to cart
  In order to start checkout process

  Scenario: Add to cart button visibility
    Given I am on the Home Page
    When I click on the "Learn more" button
    Then I should see the "Add to Cart" button