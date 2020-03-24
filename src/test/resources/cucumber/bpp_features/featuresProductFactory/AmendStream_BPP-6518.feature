@ProductFactory @Courses @Streams
Feature: Edit Instance - Streams - BPP-6518
  As a Product Setter
  I want the ability to specify the Stream on an Instance
  so that the relationship is made.

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
    And I execute "Create Pricing Matrix" reusable step
    And I execute "Create Pricing Matrix Full" reusable step
    And I execute "Create Material Type Financial Dimension" reusable step
    And I execute "Create Material Type" reusable step
    And I execute "Create Session Duration" reusable step
    And I execute "Create Client" reusable step
    And I execute "Create Stream" reusable step
    Then I click on the "Create" "Product Factory button"
    And I set "StreamNameTwo[######]" text to the "Name" "Product Factory text field"
    And I click on the "Save" "Product Factory button"
    Then I should see the "EC_STREAM_NAME_TWO" element
    And I execute "Create Material" reusable step
    And I execute "Create Course Instance" reusable step

  @Positive @P1 #TC-2951, TC-2968
  Scenario: Add a Stream to Course Instance
    Given I click on the "Activate" "Product Factory button"
    And I click on the "Product Factory Course Instances Arrow Down Icon" element
    And I click on the "Product Factory Course Instances Sessions Number of Dates Icon" element
    And I fill the "Product Factory Session Dates Popup Date Input Field" field with "EC_SITTING_START_DATE"
    And I fill the "Product Factory Session Dates Popup Start Time Input Field" field with "03:00AM"
    And I fill the "Product Factory Session Dates Popup End Time Input Field" field with "04:00AM"
    And I click on the "Next" "Product Factory button"
    And I click on the "Finish" "Product Factory button"
    And I click on the "Stream" "Product Factory edit button"
    And I click on the "EC_STREAM_NAME" "Product Factory select button"
    And I should see the "EC_STREAM_NAME" element
    Then I click on the "Delete" "Product Factory button title"
    And I click on the "Yes" "Product Factory button"

    Then I click on the "Create" "Product Factory button"
    And I click on the "Default Session Duration" "Product Factory dropdown"
    And I click on the "EC_SESSION_DURATION_DESCRIPTION" "Product Factory dropdown option"
    And I click on the "Default Location" "Product Factory dropdown"
    And I click on the "EC_LOCATION_NAME" "Product Factory dropdown option"
    And I click on the "Save" "Product Factory button"
    And I click on the "Product Factory Course Instances Arrow Down Icon" element
    And I click on the "Product Factory Course Instances Sessions Number of Dates Icon" element
    And I fill the "Product Factory Session Dates Popup Date Input Field" field with "EC_SITTING_START_DATE"
    And I fill the "Product Factory Session Dates Popup Start Time Input Field" field with "03:00AM"
    And I fill the "Product Factory Session Dates Popup End Time Input Field" field with "04:00AM"
    And I click on the "Next" "Product Factory button"
    And I click on the "Finish" "Product Factory button"
    And I click on the "Activate" "Product Factory button"

    And I click on the "Stream" "Product Factory edit button"
    And I click on the "EC_STREAM_NAME" "Product Factory select button"
    And I should see the "EC_STREAM_NAME" element
    And I click on the "Stream" "Product Factory edit button"
    And I click on the "EC_STREAM_NAME_TWO" "Product Factory select button"
    And I should see the "EC_STREAM_NAME_TWO" element
    And I click on the "Stream" "Product Factory edit button"
    And I click on the "Deselect" "Product Factory button"
    And I shouldn't see the "EC_STREAM_NAME_TWO" "element"
    And I click on the "Stream" "Product Factory edit button"
    And I click on the "EC_STREAM_NAME" "Product Factory select button"
    And I should see the "EC_STREAM_NAME" element

    And I click on the "Publish" "Product Factory active button"
    And I click on the "Yes" "Product Factory button"
    When Browser performs "REFRESH" command
    And I should see the "EC_STREAM_NAME" element