@Reviews
@Ignore
Feature: Reviews
  As a customer
  I want to be able to view and write reviews for products
  In order to share experience of product with other customers

  Scenario: Write a review
    Given I am on the Reviews Page
    When I click on the "WRITE A REVIEW" button
    And I fill "Your Location" field with "New York"
    And I rate product with 5 stars
    And I fill "Review Headline" field with "Pretty nice!"
    And I leave comment: "Very high quality product"
    And I fill "Name" field with "John D"
    And I fill "Email" field with "test@test.com"
    And I click on the "Submit Review" button
    Then I should see the "Thank You!" message
