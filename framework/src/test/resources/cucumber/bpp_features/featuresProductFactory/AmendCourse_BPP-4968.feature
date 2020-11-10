@ProductFactory @Courses @Amend
Feature: Course - Update Instances for non draft Courses - BPP-4968
  As a Product Setter
  I want to be able to update a Course Instance for a non draft Course
  So that the Course published AWS notification is published for consumption by the middleware.

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
    And I execute "Create Location" reusable step replacing some steps
      |4|I set "LocationNameThree[######]" text to the "Name" "Product Factory text field"|
      |5|I set "LocationAddressThree[######]" text to the "Address Line 1" "Product Factory text field"|
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
    And I execute "Create Session Duration" reusable step replacing some steps
      |4|I set "SessionDurationDescriptionTwoDates[######]" text to the "Description" "Product Factory text field"|
      |5|I set "2" text to the "Number of Dates" "Product Factory text field"|
    And I execute "Create Client" reusable step
    And I execute "Create Stream" reusable step
    And I execute "Create Material" reusable step
    And I execute "Create Digital Content" reusable step

  @Positive @P1 #TC-2406
  Scenario: Amend Non Draft Course Instance
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
    When I click on the "EC_LOCATION_NAME" "Product Factory course instance sessions dropdown button"
    Then I should see the "Default Location" "Product Factory edit button"
    And I should see the "Capacity" "Product Factory edit button"
    And I should see the "Stream" "Product Factory edit button"
    And I should see the "EC_LOCATION_NAME" "Product Factory edit button"
    And I should see the "EC_LOCATION_NAME" "Product Factory Session Dates button"
    And I shouldn't see the "Default Session Duration" "Product Factory edit button"
    And I shouldn't see the "EC_SESSION_DURATION_DESCRIPTION" "Product Factory edit button"

    And Attribute "tabindex" of "EC_LOCATION_NAME" "Product Factory course instance Publish button" should have value "-1"
    When I click on the "Default Location" "Product Factory edit button"
    And I click on the "EC_LOCATION_NAME_TWO" "Product Factory select button"
    And I click on the "No" "Product Factory button"

    When I click on the "Capacity" "Product Factory edit button"
    And I set "2" text to the "Capacity" "Product Factory text field"
    And I click on the "Save" "Product Factory button"
    And Attribute "tabindex" of "EC_LOCATION_NAME_TWO" "Product Factory course instance Publish button" should have value "0"
    And I click on the "EC_LOCATION_NAME_TWO" "Product Factory course instance Publish button"
    And I click on the "Yes" "Product Factory button"
    Then Attribute "tabindex" of "EC_LOCATION_NAME_TWO" "Product Factory course instance Publish button" should have value "-1"

    When I click on the "Stream" "Product Factory edit button"
    And I click on the "EC_STREAM_NAME" "Product Factory select button"
    And Attribute "tabindex" of "EC_LOCATION_NAME_TWO" "Product Factory course instance Publish button" should have value "0"
    And I click on the "EC_LOCATION_NAME_TWO" "Product Factory course instance Publish button"
    And I click on the "Yes" "Product Factory button"
    Then Attribute "tabindex" of "EC_LOCATION_NAME_TWO" "Product Factory course instance Publish button" should have value "-1"

    When I click on the "EC_LOCATION_NAME" "Product Factory edit button"
    And I click on the "EC_LOCATION_NAME_TWO" "Product Factory select button"
    And Attribute "tabindex" of "EC_LOCATION_NAME_TWO" "Product Factory course instance Publish button" should have value "0"
    And I click on the "EC_LOCATION_NAME_TWO" "Product Factory course instance Publish button"
    And I click on the "Yes" "Product Factory button"
    Then Attribute "tabindex" of "EC_LOCATION_NAME_TWO" "Product Factory course instance Publish button" should have value "-1"

    When I click on the "EC_LOCATION_NAME_TWO" "Product Factory Session Dates button"
    And I fill the "Product Factory Session Dates Popup Start Time Input Field" field with "PM"
    And I fill the "Product Factory Session Dates Popup End Time Input Field" field with "PM"
    And I click on the "Next" "Product Factory button"
    Then I should see the "15:00:00" "element"
    Then I should see the "16:00:00" "element"
    And I click on the "Finish" "Product Factory button"
    And I click on the "EC_LOCATION_NAME_TWO" "Product Factory course instance Publish button"
    And I click on the "Yes" "Product Factory button"
    Then Attribute "tabindex" of "EC_LOCATION_NAME_TWO" "Product Factory course instance Publish button" should have value "-1"

  @Positive @P2 @Run #TC-2407
  Scenario: Amend Non Draft Course Instance Additional Scenario
    And I execute "Create Course Instance" reusable step replacing some steps
      |14|I set "2" text to the "Number of Sessions" "Product Factory text field"|

    #Create second instance
    Then I click on the "Create" "Product Factory button"
    And I click on the "Default Session Duration" "Product Factory dropdown"
    And I click on the "EC_SESSION_DURATION_DESCRIPTION_TWO_DATES" "Product Factory dropdown option"
    And I click on the "Default Location" "Product Factory dropdown"
    And I click on the "EC_LOCATION_NAME_THREE" "Product Factory dropdown option"
    And I click on the "Save" "Product Factory button"

    #Set session dates for both sessions of first instance
    When I click on the "EC_LOCATION_NAME" "Product Factory course instance sessions dropdown button"
    And I click on the "EC_LOCATION_NAME" "Product Factory Session Dates button"
    And I set "EC_SITTING_START_DATE" text to the "1" "Product Factory Session Dates Popup Date Input Field"
    And I set "03:00AM" text to the "1" "Product Factory Session Dates Popup Start Time Input Field"
    And I set "04:00AM" text to the "1" "Product Factory Session Dates Popup End Time Input Field"
    And I click on the "Next" "Product Factory button"
    And I set "EC_SITTING_START_DATE" text to the "1" "Product Factory Session Two Dates Popup Date Input Field"
    And I set "03:00AM" text to the "1" "Product Factory Session Two Dates Popup Start Time Input Field"
    And I set "04:00AM" text to the "1" "Product Factory Session Two Dates Popup End Time Input Field"
    And I click on the "Next" "Product Factory button"
    And I click on the "Finish" "Product Factory button"
    Then I click on the "EC_LOCATION_NAME" "Product Factory course instance sessions dropdown button"

    #Set session dates for both sessions of second instance
    When I click on the "EC_LOCATION_NAME_THREE" "Product Factory course instance sessions dropdown button"
    And I click on the "EC_LOCATION_NAME_THREE" "Product Factory Session Dates button"
    And I set "EC_SITTING_START_DATE" text to the "1" "Product Factory Session Dates Popup Date Input Field"
    And I set "03:00AM" text to the "1" "Product Factory Session Dates Popup Start Time Input Field"
    And I set "04:00AM" text to the "1" "Product Factory Session Dates Popup End Time Input Field"
    And I set "EC_SITTING_START_DATE" text to the "2" "Product Factory Session Dates Popup Date Input Field"
    And I set "03:00AM" text to the "2" "Product Factory Session Dates Popup Start Time Input Field"
    And I set "04:00AM" text to the "2" "Product Factory Session Dates Popup End Time Input Field"
    And I click on the "Next" "Product Factory button"
    And I set "EC_SITTING_START_DATE" text to the "1" "Product Factory Session Two Dates Popup Date Input Field"
    And I set "03:00AM" text to the "1" "Product Factory Session Two Dates Popup Start Time Input Field"
    And I set "04:00AM" text to the "1" "Product Factory Session Two Dates Popup End Time Input Field"
    And I set "EC_SITTING_START_DATE" text to the "2" "Product Factory Session Two Dates Popup Date Input Field"
    And I set "03:00AM" text to the "2" "Product Factory Session Two Dates Popup Start Time Input Field"
    And I set "04:00AM" text to the "2" "Product Factory Session Two Dates Popup End Time Input Field"
    And I click on the "Next" "Product Factory button"
    And I click on the "Finish" "Product Factory button"

    #Activate course and instances
    Then I click on the "Activate" "Product Factory button"
    And I should see the "Deactivate" "Product Factory button"
    And I click on the "EC_LOCATION_NAME" "Product Factory course instance Activate button"
    And I shouldn't see the "EC_LOCATION_NAME" "Product Factory course instance Delete button"
    And I click on the "EC_LOCATION_NAME_THREE" "Product Factory course instance Activate button"
    And I shouldn't see the "EC_LOCATION_NAME_THREE" "Product Factory course instance Delete button"

    #Verify absence of Course edit buttons
    When I click on the "Products" "Product Factory navigation item"
    When I click on the "Courses" "Product Factory navigation sub item"
    And I set "EC_BODY_SHORT_NAME" text to the "Search" "Product Factory text field"
    And I click on the "submit" "element by type"
    And I click on the "EC_BODY_SHORT_NAME" "Product Factory edit button"
    Then I should see the "Default Location" "Product Factory edit button"
    And I should see the "Capacity" "Product Factory edit button"
    And I should see the "Stream" "Product Factory edit button"

    #Verify absence of first instance edit buttons
    When I click on the "EC_LOCATION_NAME" "Product Factory course instance sessions dropdown button"
    And I should see the "EC_LOCATION_NAME" "Product Factory edit button"
    And I should see the "EC_LOCATION_NAME" "Product Factory Session Dates button"
    And I shouldn't see the "Default Session Duration" "Product Factory edit button"
    And I shouldn't see the "EC_SESSION_DURATION_DESCRIPTION" "Product Factory edit button"
    Then I click on the "EC_LOCATION_NAME" "Product Factory course instance sessions dropdown button"

    #Verify absence of second instance edit buttons
    When I click on the "EC_LOCATION_NAME_THREE" "Product Factory course instance sessions dropdown button"
    And I should see the "EC_LOCATION_NAME_THREE" "Product Factory edit button"
    And I should see the "EC_LOCATION_NAME_THREE" "Product Factory Session Dates button"
    And I shouldn't see the "Default Session Duration" "Product Factory edit button"
    And I shouldn't see the "EC_SESSION_DURATION_DESCRIPTION" "Product Factory edit button"
    Then I click on the "EC_LOCATION_NAME_THREE" "Product Factory course instance sessions dropdown button"

    #Edit default location of first instance
    And Attribute "tabindex" of "EC_LOCATION_NAME" "Product Factory course instance Publish button" should have value "-1"
    When I click on the "Default Location" "Product Factory edit button"
    And I click on the "EC_LOCATION_NAME_TWO" "Product Factory select button"
    And I click on the "No" "Product Factory button"

    #Edit capacity of first instance
    When I click on the "Capacity" "Product Factory edit button"
    And I set "2" text to the "Capacity" "Product Factory text field"
    And I click on the "Save" "Product Factory button"
    And Attribute "tabindex" of "EC_LOCATION_NAME_TWO" "Product Factory course instance Publish button" should have value "0"
    And I click on the "EC_LOCATION_NAME_TWO" "Product Factory course instance Publish button"
    And I click on the "Yes" "Product Factory button"
    Then Attribute "tabindex" of "EC_LOCATION_NAME_TWO" "Product Factory course instance Publish button" should have value "-1"

    #Edit stream of first instance
    When I click on the "Stream" "Product Factory edit button"
    And I click on the "EC_STREAM_NAME" "Product Factory select button"
    And Attribute "tabindex" of "EC_LOCATION_NAME_TWO" "Product Factory course instance Publish button" should have value "0"
    And I click on the "EC_LOCATION_NAME_TWO" "Product Factory course instance Publish button"
    And I click on the "Yes" "Product Factory button"
    Then Attribute "tabindex" of "EC_LOCATION_NAME_TWO" "Product Factory course instance Publish button" should have value "-1"

    #Edit location name of first instance
    Then I click on the "EC_LOCATION_NAME_TWO" "Product Factory course instance sessions dropdown button"
    When I click on the "EC_LOCATION_NAME" "Product Factory edit button"
    And I click on the "EC_LOCATION_NAME_TWO" "Product Factory select button"
    And Attribute "tabindex" of "EC_LOCATION_NAME_TWO" "Product Factory course instance Publish button" should have value "0"
    And I click on the "EC_LOCATION_NAME_TWO" "Product Factory course instance Publish button"
    And I click on the "Yes" "Product Factory button"
    Then Attribute "tabindex" of "EC_LOCATION_NAME_TWO" "Product Factory course instance Publish button" should have value "-1"

    #Edit session dates of first instance
    When I click on the "EC_LOCATION_NAME_TWO" "Product Factory Session Dates button"
    And I set "PM" text to the "1" "Product Factory Session Dates Popup Start Time Input Field"
    And I set "PM" text to the "1" "Product Factory Session Dates Popup End Time Input Field"
    And I click on the "Next" "Product Factory button"
    And I set "PM" text to the "1" "Product Factory Session Two Dates Popup Start Time Input Field"
    And I set "PM" text to the "1" "Product Factory Session Two Dates Popup End Time Input Field"
    And I click on the "Next" "Product Factory button"
    Then I should see the "15:00:00" "element"
    Then I should see the "16:00:00" "element"
    And I click on the "Finish" "Product Factory button"
    And I click on the "EC_LOCATION_NAME_TWO" "Product Factory course instance Publish button"
    And I click on the "Yes" "Product Factory button"
    Then Attribute "tabindex" of "EC_LOCATION_NAME_TWO" "Product Factory course instance Publish button" should have value "-1"
