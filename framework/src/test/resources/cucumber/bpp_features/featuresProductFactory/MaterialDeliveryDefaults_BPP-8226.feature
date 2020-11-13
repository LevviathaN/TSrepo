@ProductFactory @Courses @Materials @MaterialDeliveryDefaults
Feature: Abilit to specify Material Delivery Defaults during Product Setup - BPP-8226
  As a Salesforce User creating a Booking
  I would like the Delivery Address for each Material to be auto-filled in based on the Material Delivery Defaults set in Product Factory
  So that I don't have to remember to update the Delivery Address each time

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


  @Positive @P1 #TC-3556
  Scenario: Verify Ability to specify Material Delivery Default during Product Setup
    Given I execute "Create Material" reusable step
    And I execute "Create Course Instance" reusable step
    When I click on the "Products" "Product Factory navigation item"
    When I click on the "Courses" "Product Factory navigation sub item"
    And I set "EC_BODY_SHORT_NAME" text to the "Search" "Product Factory text field"
    And I click on the "submit" "element by type"
    And I click on the "EC_BODY_SHORT_NAME" "Product Factory edit button"
    And I click on the "Materials" element
    And I click on the "Add Material" "button"
    Then I "check" "EC_MATERIAL_TYPE_NAME" "Product Factory checkbox"
    And I click on the "Save" "button"
    Then I click on the "Material Delivery Default" "Product Factory dropdown"
    When I click on the "Student" element
    Then I should see the "Student" element

  @Negative @P1 #TC-3557
  Scenario: Verify Not Selected Material Delivery Default during Product Setup
    Given I execute "Create Material" reusable step
    And I execute "Create Course Instance" reusable step
    When I click on the "Products" "Product Factory navigation item"
    When I click on the "Courses" "Product Factory navigation sub item"
    And I set "EC_BODY_SHORT_NAME" text to the "Search" "Product Factory text field"
    And I click on the "submit" "element by type"
    And I click on the "EC_BODY_SHORT_NAME" "Product Factory edit button"
    And I click on the "Materials" element
    And I click on the "Add Material" "button"
    Then I "check" "EC_MATERIAL_TYPE_NAME" "Product Factory checkbox"
    And I click on the "Save" "button"
    When I click on the "Activate" "Product Factory button"
    Then I should see the "The Material Delivery Default has not been set for all Course Materials" message

  @Negative @P1 #TC-3558
  Scenario: Edit Course With Selected Material Delivery Default
    Given I execute "Create Material" reusable step
    Given I execute "Create Digital Content" reusable step
    And I execute "Create Course Instance" reusable step
    When I click on the "EC_LOCATION_NAME" "Product Factory course instance sessions dropdown button"
    And I click on the "EC_LOCATION_NAME" "Product Factory Session Dates button"
    And I fill the "Product Factory Session Dates Popup Date Input Field" field with "EC_SITTING_START_DATE"
    And I fill the "Product Factory Session Dates Popup Start Time Input Field" field with "03:00AM"
    And I fill the "Product Factory Session Dates Popup End Time Input Field" field with "04:00AM"
    And I click on the "Next" "Product Factory button"
    And I click on the "Finish" "Product Factory button"
    Then I click on the "Activate" "Product Factory button"
    And I should see the "Deactivate" "Product Factory button"
    And I click on the "EC_LOCATION_NAME" "Product Factory course instance Activate button"
    And I shouldn't see the "EC_LOCATION_NAME" "Product Factory course instance Delete button"
    When I click on the "Products" "Product Factory navigation item"
    When I click on the "Courses" "Product Factory navigation sub item"
    And I set "EC_BODY_SHORT_NAME" text to the "Search" "Product Factory text field"
    And I click on the "submit" "element by type"
    And I click on the "EC_BODY_SHORT_NAME" "Product Factory edit button"
    And I click on the "Materials" element
    And I click on the "Add Material" "button"
    Then I "check" "EC_MATERIAL_TYPE_NAME" "Product Factory checkbox"
    And I click on the "Save" "button"
    Then I click on the "Material Delivery Default" "Product Factory dropdown"
    When I click on the "Student" element
    When I click on the "Publish" "Product Factory button"
    And I click on the "Yes" "Product Factory button"
    Then I shouldn't see the "The Material Delivery Default has not been set for all Course Materials" message

  @Negative @P1 #TC-3559
  Scenario: Edit Course Without Selecting Material Delivery Default
    Given I execute "Create Material" reusable step
    Given I execute "Create Digital Content" reusable step
    And I execute "Create Course Instance" reusable step
    When I click on the "EC_LOCATION_NAME" "Product Factory course instance sessions dropdown button"
    And I click on the "EC_LOCATION_NAME" "Product Factory Session Dates button"
    And I fill the "Product Factory Session Dates Popup Date Input Field" field with "EC_SITTING_START_DATE"
    And I fill the "Product Factory Session Dates Popup Start Time Input Field" field with "03:00AM"
    And I fill the "Product Factory Session Dates Popup End Time Input Field" field with "04:00AM"
    And I click on the "Next" "Product Factory button"
    And I click on the "Finish" "Product Factory button"
    Then I click on the "Activate" "Product Factory button"
    And I should see the "Deactivate" "Product Factory button"
    And I click on the "EC_LOCATION_NAME" "Product Factory course instance Activate button"
    And I shouldn't see the "EC_LOCATION_NAME" "Product Factory course instance Delete button"
    When I click on the "Products" "Product Factory navigation item"
    When I click on the "Courses" "Product Factory navigation sub item"
    And I set "EC_BODY_SHORT_NAME" text to the "Search" "Product Factory text field"
    And I click on the "submit" "element by type"
    And I click on the "EC_BODY_SHORT_NAME" "Product Factory edit button"
    And I click on the "Materials" element
    And I click on the "Add Material" "button"
    Then I "check" "EC_MATERIAL_TYPE_NAME" "Product Factory checkbox"
    And I click on the "Save" "button"
    When I click on the "Publish" "Product Factory button"
    And I click on the "Yes" "Product Factory button"
    Then I should see the "The Material Delivery Default has not been set for all Course Materials" message