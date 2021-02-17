@ProductFactory @DigitalContent
Feature: Digital Content Id - Assignment to Course - BPP-5607
  As a Product Setter
  I want a Digital Content Id assigned to a Course automatically but not created if there is no match
  so that only Digital Content Id that have been manually created are used.

  Background:
    Given I execute "Generate ISBN" reusable step
    Given I execute "Log In" reusable step

    And I remember "AutoStreamName" text as "EC_STREAM_NAME" variable
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
    And I remember "AutoSittingName" text as "EC_SITTING_NAME" variable

#    When I execute "Create Body Financial Dimension" reusable step
#    And I execute "Create Body" reusable step
#    And I execute "Create VAT Rule" reusable step
#    And I execute "Create Course Type Financial Dimension" reusable step
#    And I execute "Create Course Type" reusable step
#    And I execute "Create Location Financial Dimension" reusable step
#    And I execute "Create Region Financial Dimension" reusable step
#    And I execute "Create Region" reusable step
#    And I execute "Create Location" reusable step
#    And I execute "Create Level" reusable step
#    And I execute "Create Paper" reusable step
#    And I execute "Link Body To Level" reusable step
#    And I execute "Link Body To Paper" reusable step
#    And I execute "Link Paper To Level" reusable step
#    And I execute "Create Sitting" reusable step
    And I execute "Create Sitting" reusable step replacing some steps
      |4|I set "SittingNameTwo[######]" text to the "Name" "Product Factory text field"|
    And I execute "Create Sitting" reusable step replacing some steps
      |4|I set "SittingNameThree[######]" text to the "Name" "Product Factory text field"|
#    And I execute "Create Pricing Matrix" reusable step
#    And I execute "Create Pricing Matrix Full" reusable step
    And I execute "Create Pricing Matrix" reusable step replacing some steps
      |8|I click on the "EC_SITTING_NAME_TWO" "Product Factory change modal option"|
    And I execute "Create Pricing Matrix Full" reusable step replacing some steps
      |3|I click on the "EC_SITTING_NAME_TWO" "Product Factory edit button"|
#    And I execute "Create Material Type Financial Dimension" reusable step
#    And I execute "Create Material Type" reusable step
#    And I execute "Create Session Duration" reusable step
#    And I execute "Create Client" reusable step
#    And I execute "Create Material" reusable step

  @Positive @P1 @Runn #TC-3181
  Scenario: Create Course With Matching Digital Content
    Given I execute "Create Digital Content" reusable step with some additional steps
      |18|I set "Suffix[###]" text to the "Name Suffix" "Product Factory text field"|
      |19|I should see the "[EC_BODY_SHORT_NAME] - [EC_LEVEL_SHORT_NAME] - [EC_PAPER_NAME] - [EC_SITTING_NAME] - [EC_SUFFIX]" element|
    Then I execute "Create Course" reusable step
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
    Then I execute "Create Course" reusable step
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
    Then I execute "Create Course" reusable step
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
    Then I execute "Create Course" reusable step
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