@ProductFactory @Courses
Feature: Course - Copy Instance Location to Sessions Option
  As a Product Setter
  I want to be given an option when I'm changing the location of the instance, to copy the location down to the sessions,
  so that I don't have to update each individual session.

  Background:
    Given I execute "Generate ISBN" reusable step
    Given I execute "Log In" reusable step
    When I execute "Create Body Financial Dimension" reusable step
    And I execute "Create Body" reusable step
    And I execute "Create VAT Rule" reusable step
    And I execute "Create Course Type Financial Dimension" reusable step
    And I execute "Create Course Type" reusable step
    And I execute "Create Location Financial Dimension" reusable step
    And I execute "Create Region Financial Dimension" reusable step
    And I execute "Create Region" reusable step
    And I execute "Create Location" reusable step
    And I execute "Create Location" reusable step replacing some steps
      |4|I set "LocationNameTwo[######]" text to the "Name" "Product Factory text field"|
      |5|I set "LocationAddressTwo[######]" text to the "Address Line 1" "Product Factory text field"|
    And I execute "Create Level" reusable step
    And I execute "Create Paper" reusable step
    And I execute "Link Body To Level" reusable step
    And I execute "Link Body To Paper" reusable step
    And I execute "Link Paper To Level" reusable step
    And I execute "Create Sitting" reusable step
    And I execute "Create Pricing Matrix" reusable step
    And I execute "Create Pricing Matrix Full" reusable step
    And I execute "Create Material Type Financial Dimension" reusable step
    And I execute "Create Material Type" reusable step
    And I execute "Create Session Duration" reusable step
    And I execute "Create Client" reusable step
    And I execute "Create Material" reusable step

  @Positive @P1 #TC-3905, TC-3914
  Scenario: Copy Instance Location to Sessions Option
    When I execute "Create Digital Content" reusable step
    And I execute "Create Course Instance" reusable step
    When I click on the "Products" "Product Factory navigation item"
    When I click on the "Courses" "Product Factory navigation sub item"
    And I set "EC_BODY_SHORT_NAME" text to the "Body" "Product Factory text field"
    And I click on the "Search" "Product Factory button"
    Then I click on the "EC_BODY_SHORT_NAME" "Product Factory edit button"
    And I click on the "Default Location" "Product Factory edit button"
    And I click on the "EC_LOCATION_NAME_TWO" "Product Factory select button"
    And I should see the "Do you want to copy the new Location to the Sessions?" message
    And I click on the "Yes" "Product Factory button"
    When I click on the "EC_LOCATION_NAME_TWO" "Product Factory course instance sessions dropdown button"
    Then I shouldn't see the "EC_LOCATION_NAME" element
    And I should see the "EC_LOCATION_NAME_TWO" element

    Then I click on the "Default Location" "Product Factory edit button"
    And I click on the "EC_LOCATION_NAME" "Product Factory select button"
    And I should see the "Do you want to copy the new Location to the Sessions?" message
    And I click on the "No" "Product Factory button"
    Then I should see the "EC_LOCATION_NAME" element
    And I should see the "EC_LOCATION_NAME_TWO" element