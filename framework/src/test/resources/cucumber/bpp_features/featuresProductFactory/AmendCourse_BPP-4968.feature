@ProductFactory @Courses @Amend @NotFinished
Feature: Course - Update Instances for non draft Courses - BPP-4968
  As a Product Setter
  I want to be able to update a Course Instance for a non draft Course
  So that the Course published AWS notification is published for consumption by the middleware.

  Background:
    Given I execute "Generate ISBN" reusable step
    Given I execute "Log In" reusable step
    And I remember "23/10/2020" text as "EC_SITTING_START_DATE" variable
    And I remember "10/10/2025" text as "EC_SITTING_END_DATE" variable
    And I remember "AutoBodyFDCode" text as "EC_BODY_FD_CODE" variable
    And I remember "AutoBodyFDDescription" text as "EC_BODY_FD_DESCRIPTION" variable
    And I remember "AutoBodyShortName" text as "EC_BODY_SHORT_NAME" variable
    And I remember "AutoBodyName" text as "EC_BODY_NAME" variable
    And I remember "AutoVatRuleCode" text as "EC_VAT_RULE_CODE" variable
    And I remember "AutoVatRuleDescription" text as "EC_VAT_RULE_DESCRIPTION" variable
    And I remember "AutoCourseTypeFDCode" text as "EC_COURSE_TYPE_FD_CODE" variable
    And I remember "AutoCourseTypeFDDescription" text as "EC_COURSE_TYPE_FD_DESCRIPTION" variable
    And I remember "AutoCourseTypeDescription" text as "EC_COURSE_TYPE_DESCRIPTION" variable
    And I remember "AutoLocationFDCode" text as "EC_LOCATION_FD_CODE" variable
    And I remember "AutoLocationFDDescription" text as "EC_LOCATION_FD_DESCRIPTION" variable
    And I remember "AutoRegionFDCode" text as "EC_REGION_FD_CODE" variable
    And I remember "AutoRegionFDDescription" text as "EC_REGION_FD_DESCRIPTION" variable
    And I remember "AutoRegionName" text as "EC_REGION_NAME" variable
    And I remember "AutoLocationName" text as "EC_LOCATION_NAME" variable
    And I remember "AutoLocationAddress" text as "EC_LOCATION_ADDRESS" variable
    And I remember "AutoLevelName" text as "EC_LEVEL_NAME" variable
    And I remember "AutoLevelShortName" text as "EC_LEVEL_SHORT_NAME" variable
    And I remember "AutoPprSN" text as "EC_PPR_SN" variable
    And I remember "AutoPaperName" text as "EC_PAPER_NAME" variable
    And I remember "AutoPaperDescription" text as "EC_PAPER_DESCRIPTION" variable
    And I remember "AtCBAPprSN" text as "EC_CBA_PPR_SN" variable
    And I remember "AutoCBAPaperName" text as "EC_CBA_PAPER_NAME" variable
    And I remember "AutoCBAPaperDescription" text as "EC_CBA_PAPER_DESCRIPTION" variable
    And I remember "AutoSittingName" text as "EC_SITTING_NAME" variable
    And I remember "AutoMaterialTypeFDCode" text as "EC_MATERIAL_TYPE_FD_CODE" variable
    And I remember "AutoMaterialTypeFDDescription" text as "EC_MATERIAL_TYPE_FD_DESCRIPTION" variable
    And I remember "AutoMaterialTypeName" text as "EC_MATERIAL_TYPE_NAME" variable
    And I remember "AutoMaterialTypeDescription" text as "EC_MATERIAL_TYPE_DESCRIPTION" variable
    And I remember "AutoSessionDurationDescription" text as "EC_SESSION_DURATION_DESCRIPTION" variable
    And I remember "AutoCBASessionDurationDescription" text as "EC_CBA_SESSION_DURATION_DESCRIPTION" variable
    And I remember "AutoSessionDurationDescriptionTwoDates" text as "EC_SESSION_DURATION_DESCRIPTION_TWO_DATES" variable
    And I remember "AutoClientName" text as "EC_CLIENT_NAME" variable
    And I remember "2000" text as "EC_PRICE" variable
    And I remember "10" text as "EC_WEIGHT" variable
    And I remember "1000" text as "EC_PRICING_MATRIX_PRICE" variable

    And I remember "AutoLocationNameTwo" text as "EC_LOCATION_NAME_TWO" variable
    And I remember "AutoLocationAddressTwo" text as "EC_LOCATION_ADDRESS_TWO" variable
    And I remember "AutoLocationNameThree" text as "EC_LOCATION_NAME_THREE" variable
    And I remember "AutoLocationAddressThree" text as "EC_LOCATION_ADDRESS_THREE" variable

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
    And I set "EC_COURSE_TYPE_DESCRIPTION" text to the "Search" "Product Factory text field" from keyboard
    And I click on the "submit" "element by type"
    And I click on the "EC_COURSE_TYPE_DESCRIPTION" "Product Factory edit button"
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

  @Positive @P2 #TC-2407
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
    And I set "EC_BODY_SHORT_NAME" text to the "Search" "Product Factory text field" from keyboard
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
    #todo potential bug button dont become inactive: https://automate.browserstack.com/dashboard/v2/builds/7ea61fc734dbad863763136e7721bfa21337514e/sessions/a1d84c57a2c0ed6245067a21874e4103ca5e4428
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
