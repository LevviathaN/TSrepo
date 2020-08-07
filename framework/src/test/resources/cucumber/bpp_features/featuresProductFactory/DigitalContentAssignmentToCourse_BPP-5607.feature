@ProductFactory @DigitalContent
Feature: Digital Content Id - Assignment to Course - BPP-5607
  As a Product Setter
  I want a Digital Content Id assigned to a Course automatically but not created if there is no match
  so that only Digital Content Id that have been manually created are used.

  Background:
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
    And I execute "Create Sitting" reusable step replacing some steps
      |4|I set "SittingNameTwo[######]" text to the "Name" "Product Factory text field"|
    And I execute "Create Sitting" reusable step replacing some steps
      |4|I set "SittingNameThree[######]" text to the "Name" "Product Factory text field"|
    And I execute "Create Pricing Matrix" reusable step
    And I execute "Create Pricing Matrix Full" reusable step
    And I execute "Create Pricing Matrix" reusable step replacing some steps
      |8|I click on the "EC_SITTING_NAME_TWO" "Product Factory change modal option"|
    And I execute "Create Pricing Matrix Full" reusable step replacing some steps
      |3|I click on the "EC_SITTING_NAME_TWO" "Product Factory edit button"|
    And I execute "Create Material Type Financial Dimension" reusable step
    And I execute "Create Material Type" reusable step
    And I execute "Create Session Duration" reusable step
    And I execute "Create Client" reusable step
    And I execute "Create Material" reusable step

  @Positive @P1 #TC-3181
  Scenario: Create Course With Matching Digital Content
    Given I execute "Create Digital Content" reusable step
    Then I execute "Create Course Instance" reusable step
    When I click on the "EC_LOCATION_NAME" "Product Factory course instance sessions dropdown button"
    And I click on the "EC_LOCATION_NAME" "Product Factory Session Dates button"
    And I fill the "Product Factory Session Dates Popup Date Input Field" field with "EC_SITTING_START_DATE"
    And I fill the "Product Factory Session Dates Popup Start Time Input Field" field with "03:00AM"
    And I fill the "Product Factory Session Dates Popup End Time Input Field" field with "04:00AM"
    And I click on the "Next" "Product Factory button"
    And I click on the "Finish" "Product Factory button"
    When I click on the "Activate" "Product Factory button"
    Then I shouldn't see the "Could not find Digital Content for the Course" message

  @Negative @P1 #TC-3183
  Scenario: Create Course With No Matching Digital Content
    Then I execute "Create Course Instance" reusable step
    When I click on the "EC_LOCATION_NAME" "Product Factory course instance sessions dropdown button"
    And I click on the "EC_LOCATION_NAME" "Product Factory Session Dates button"
    And I fill the "Product Factory Session Dates Popup Date Input Field" field with "EC_SITTING_START_DATE"
    And I fill the "Product Factory Session Dates Popup Start Time Input Field" field with "03:00AM"
    And I fill the "Product Factory Session Dates Popup End Time Input Field" field with "04:00AM"
    And I click on the "Next" "Product Factory button"
    And I click on the "Finish" "Product Factory button"
    When I click on the "Activate" "Product Factory button"
    Then I should see the "Could not find Digital Content for the Course" message

  @Positive @P1 #TC-3184
  Scenario: Change Sitting In The Active Course to One That Has Digital Content
    Given I execute "Create Digital Content" reusable step with some additional steps
      |11|I "check" "EC_SITTING_NAME_TWO" "Product Factory dialog checkbox"|
    Then I execute "Create Course Instance" reusable step
    When I click on the "EC_LOCATION_NAME" "Product Factory course instance sessions dropdown button"
    And I click on the "EC_LOCATION_NAME" "Product Factory Session Dates button"
    And I fill the "Product Factory Session Dates Popup Date Input Field" field with "EC_SITTING_START_DATE"
    And I fill the "Product Factory Session Dates Popup Start Time Input Field" field with "03:00AM"
    And I fill the "Product Factory Session Dates Popup End Time Input Field" field with "04:00AM"
    And I click on the "Next" "Product Factory button"
    And I click on the "Finish" "Product Factory button"
    When I click on the "Activate" "Product Factory button"
    And I should see the "Deactivate" "Product Factory button"
    And I click on the "EC_LOCATION_NAME" "Product Factory course instance Activate button"
    And I shouldn't see the "EC_LOCATION_NAME" "Product Factory course instance Delete button"
    When I click on the "EC_SITTING_NAME" "Product Factory edit button"
    And I click on the "EC_SITTING_NAME_TWO" "Product Factory select button"
    Then I shouldn't see the "Could not find Digital Content for the Course" message

  @Negative @P1 #TC-3185
  Scenario: Change Sitting In The Active Course to One Without Digital Content
    Given I execute "Create Digital Content" reusable step with some additional steps
      |11|I "check" "EC_SITTING_NAME_TWO" "Product Factory dialog checkbox"|
    Then I execute "Create Course Instance" reusable step
    When I click on the "EC_LOCATION_NAME" "Product Factory course instance sessions dropdown button"
    And I click on the "EC_LOCATION_NAME" "Product Factory Session Dates button"
    And I fill the "Product Factory Session Dates Popup Date Input Field" field with "EC_SITTING_START_DATE"
    And I fill the "Product Factory Session Dates Popup Start Time Input Field" field with "03:00AM"
    And I fill the "Product Factory Session Dates Popup End Time Input Field" field with "04:00AM"
    And I click on the "Next" "Product Factory button"
    And I click on the "Finish" "Product Factory button"
    When I click on the "Activate" "Product Factory button"
    And I should see the "Deactivate" "Product Factory button"
    And I click on the "EC_LOCATION_NAME" "Product Factory course instance Activate button"
    And I shouldn't see the "EC_LOCATION_NAME" "Product Factory course instance Delete button"
    When I click on the "EC_SITTING_NAME" "Product Factory edit button"
    And I click on the "EC_SITTING_NAME_THREE" "Product Factory select button"
    Then I should see the "Could not find Digital Content for the Course" message