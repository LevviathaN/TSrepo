@PDP
@Ignore
Feature: Checkout
  As a customer
  I want to be able to see different product pages
  In order to purchase the product

Scenario Outline: Change product variation on PDP
  Given I am on the "<pdp>" PDP
  Then "<default size>" size should be selected by default
  And price should be equal to "<price of default size>"
  When I click on the "<selected size>" option
  Then price should be equal to "<price of selected size>"

  Examples:
    |pdp                                   |default size|price of default size|selected size|price of selected size|
    |Tomorrow Hybrid Mattress              |Queen       |$940.00              |Twin         |$545.00               |
    |Tomorrow Waterproof Mattress Protector|Twin        |$75.00               |King         |$95.00                |

Scenario Outline: Add product to Shopping Cart (presence on Cart Page)
  Given I am on the "<pdp>" PDP
  When I click on the "Add to Cart" button
  And If Cross Sell popup appears I click on the "View Cart" button
  Then I should be redirected to the "Shopping Cart | Tomorrow Sleep" page
  And I should see "<pdp>" in the Cart

  Examples:
    |pdp|
    |Tomorrow Hybrid Mattress|
    |Tomorrow Waterproof Mattress Protector|