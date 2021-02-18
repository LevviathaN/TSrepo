@ProductFactory @DigitalContent
Feature: Digital Content Id - Multiple Course Types - BPP-7203
  As a Senior Product Setter
  I want to create Digital Content
  so that Course(s) can be given a Digital Content Id.

  Background:
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
    And I remember "AutoSessionDurationDescription" text as "EC_SESSION_DURATION_DESCRIPTION" variable

#    When I execute "Create Body Financial Dimension" reusable step
#    And I execute "Create Body" reusable step
#    And I execute "Create VAT Rule" reusable step
#    And I execute "Create Course Type Financial Dimension" reusable step
#    And I execute "Create Course Type" reusable step
    And I execute "Create Course Type" reusable step replacing some steps
      |4|I set "CourseTypeDescriptionTwo[######]" text to the "Description" "Product Factory text field"|
      |13|I set "EC_COURSE_TYPE_DESCRIPTION_TWO" text to the "Search" "Product Factory text field" from keyboard|
      |15|I should see the "EC_COURSE_TYPE_DESCRIPTION_TWO" element                                         |
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

  @Positive @P1 #TC-3203
  Scenario: Create Digital Content Multiple Course Types
    Given I execute "Create Digital Content" reusable step with some additional steps
      |18|I set "Suffix[###]" text to the "Name Suffix" "Product Factory text field"|
      |19|I should see the "[EC_BODY_SHORT_NAME] - [EC_LEVEL_SHORT_NAME] - [EC_PAPER_NAME] - [EC_SITTING_NAME] - [EC_SUFFIX]" element|
    Given I execute modified "Create Digital Content" reusable step
      |19|Add|I set "EC_COURSE_TYPE_DESCRIPTION_TWO" text to the "Description" "Product Factory text field"|
      |20|Add|I click on the "Search" "Product Factory button two"|
      |21|Add|I "check" "EC_COURSE_TYPE_DESCRIPTION_TWO" "Product Factory dialog checkbox"|

  @Negative @P1 #TC-3204
  Scenario: Create Digital Content Multiple Course Types Duplicate
    Given I execute modified "Create Digital Content" reusable step
      |18|Add|I set "Suffix[###]" text to the "Name Suffix" "Product Factory text field"|
      |19|Add|I should see the "[EC_BODY_SHORT_NAME] - [EC_LEVEL_SHORT_NAME] - [EC_PAPER_DESCRIPTION] - [EC_SITTING_NAME] - [EC_SUFFIX]" element|
      |21|Add|I set "EC_COURSE_TYPE_DESCRIPTION_TWO" text to the "Description" "Product Factory text field"|
      |22|Add|I click on the "Search" "Product Factory button two"|
      |23|Add|I "check" "EC_COURSE_TYPE_DESCRIPTION_TWO" "Product Factory dialog checkbox"|
    Given I execute modified "Create Digital Content" reusable step
      |18|Add|I set "EC_SUFFIX" text to the "Name Suffix" "Product Factory text field"|
      |19|Add|I should see the "[EC_BODY_SHORT_NAME] - [EC_LEVEL_SHORT_NAME] - [EC_PAPER_DESCRIPTION] - [EC_SITTING_NAME] - [EC_SUFFIX]" element|
      |21|Add|I set "EC_COURSE_TYPE_DESCRIPTION_TWO" text to the "Description" "Product Factory text field"|
      |22|Add|I click on the "Search" "Product Factory button two"|
      |23|Add|I "check" "EC_COURSE_TYPE_DESCRIPTION_TWO" "Product Factory dialog checkbox"|
    Then I should see the "Digital Content already exists" "text contained in element"

  @Positive @P1 #TC-3205, TC-3206
  Scenario: Create Digital Content Multiple Course Types Remove
    Given I execute "Create Digital Content" reusable step with some additional steps
      |11|I "check" "EC_SITTING_NAME_TWO" "Product Factory dialog checkbox"|
      |18|I set "EC_COURSE_TYPE_DESCRIPTION_TWO" text to the "Description" "Product Factory text field"|
      |19|I click on the "Search" "Product Factory button two"                                         |
      |20|I "check" "EC_COURSE_TYPE_DESCRIPTION_TWO" "Product Factory dialog checkbox"|
    Then I should see the "EC_BODY_SHORT_NAME" element
    When I click on the "Delivery" "Product Factory navigation item"
    When I click on the "Digital Content" "Product Factory navigation sub item"
    And I set "EC_BODY_SHORT_NAME" text to the "Body" "Product Factory text field"
    And I click on the "Search" "Product Factory button"
    And I click on the "EC_BODY_SHORT_NAME" "Product Factory edit button"
    And I click on the "Course Types" "Product Factory edit button"
    And I set "CourseTypeDescription" text to the "Description" "Product Factory text field"
    And I click on the "Search" "Product Factory button two"
    And I "check" "EC_COURSE_TYPE_DESCRIPTION_TWO" "Product Factory dialog checkbox"
    And I "check" "EC_COURSE_TYPE_DESCRIPTION" "Product Factory dialog checkbox"
    And I click on the "Save" "Product Factory active button"
    Then Attribute "tabindex" of "Save" "Product Factory button" should have value "-1"
    And I click on the "Course Types" "Product Factory edit button"
    And I set "CourseTypeDescription" text to the "Description" "Product Factory text field"
    And I click on the "Search" "Product Factory button two"
    And I "check" "EC_COURSE_TYPE_DESCRIPTION" "Product Factory dialog checkbox"
    And I click on the "Save" "Product Factory active button"
    Then Attribute "tabindex" of "Save" "Product Factory button" should have value "0"