@ProductFactory @DigitalContent
Feature: Digital Content List - add Search Filter - BPP-8380
  As a Senior Product Setter
  I want to create a Digital Content Id
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
    And I remember "AtPprSN" text as "EC_PPR_SN" variable
    And I remember "AutoPaperName" text as "EC_PAPER_NAME" variable
    And I remember "AutoPaperDescription" text as "EC_PAPER_DESCRIPTION" variable
    And I remember "AutoSittingName" text as "EC_SITTING_NAME" variable
    And I remember "22/02/2021" text as "EC_SITTING_START_DATE" variable
    And I remember "10/10/2025" text as "EC_SITTING_END_DATE" variable

    And I remember "AutoStreamNameTwo" text as "EC_STREAM_NAME_TWO" variable
    And I remember "AutoBodyFDCodeTwo" text as "EC_BODY_FD_CODE_TWO" variable
    And I remember "AutoBodyFDDescriptionTwo" text as "EC_BODY_FD_DESCRIPTION_TWO" variable
    And I remember "AutoBodyShortNameTwo" text as "EC_BODY_SHORT_NAME_TWO" variable
    And I remember "AutoBodyNameTwo" text as "EC_BODY_NAME_TWO" variable
    And I remember "AutoVatRuleCodeTwo" text as "EC_VAT_RULE_CODE_TWO" variable
    And I remember "AutoVatRuleDescriptionTwo" text as "EC_VAT_RULE_DESCRIPTION_TWO" variable
    And I remember "AutoCourseTypeFDCodeTwo" text as "EC_COURSE_TYPE_FD_CODE_TWO" variable
    And I remember "AutoCourseTypeFDDescriptionTwo" text as "EC_COURSE_TYPE_FD_DESCRIPTION_TWO" variable
    And I remember "AutoCourseTypeDescriptionTwo" text as "EC_COURSE_TYPE_DESCRIPTION_TWO" variable
    And I remember "AutoLocationFDCodeTwo" text as "EC_LOCATION_FD_CODE_TWO" variable
    And I remember "AutoLocationFDDescriptionTwo" text as "EC_LOCATION_FD_DESCRIPTION_TWO" variable
    And I remember "AutoRegionFDCodeTwo" text as "EC_REGION_FD_CODE_TWO" variable
    And I remember "AutoRegionFDDescriptionTwo" text as "EC_REGION_FD_DESCRIPTION_TWO" variable
    And I remember "AutoRegionNameTwo" text as "EC_REGION_NAME_TWO" variable
    And I remember "AutoLocationNameTwo" text as "EC_LOCATION_NAME_TWO" variable
    And I remember "AutoLocationAddressTwo" text as "EC_LOCATION_ADDRESS_TWO" variable
    And I remember "AutoLevelNameTwo" text as "EC_LEVEL_NAME_TWO" variable
    And I remember "AutoLevelShortNameTwo" text as "EC_LEVEL_SHORT_NAME_TWO" variable
    And I remember "AtPprSNTwo" text as "EC_PPR_SN_TWO" variable
    And I remember "AutoPaperNameTwo" text as "EC_PAPER_NAME_TWO" variable
    And I remember "AutoPaperDescriptionTwo" text as "EC_PAPER_DESCRIPTION_TWO" variable
    And I remember "AutoSittingNameTwo" text as "EC_SITTING_NAME_TWO" variable

  @Positive @P1 #TC-3568
  Scenario: Digital Content Page Filters Validation
    When I click on the "Delivery" "Product Factory navigation item"
    When I click on the "Digital Content" "Product Factory navigation sub item"
    # Check Body filters
    And I set "EC_BODY_SHORT_NAME_TWO" text to the "Search" "Product Factory text field" from keyboard
    And I click on the "submit" "element by type"
    Then I shouldn't see the "EC_BODY_SHORT_NAME" element
    And I click on the "Clear the search text" "element by title"
    # Check Level filters
    And I set "EC_LEVEL_SHORT_NAME_TWO" text to the "Search" "Product Factory text field" from keyboard
    And I click on the "submit" "element by type"
    Then I shouldn't see the "EC_BODY_SHORT_NAME" element
    And I click on the "Clear the search text" "element by title"
    # Check Paper filters
    And I set "EC_PAPER_NAME_TWO" text to the "Search" "Product Factory text field" from keyboard
    And I click on the "submit" "element by type"
    Then I shouldn't see the "EC_BODY_SHORT_NAME" element
    And I click on the "Clear the search text" "element by title"
    # Check Sitting filters
    And I set "EC_SITTING_NAME_TWO" text to the "Search" "Product Factory text field" from keyboard
    And I click on the "submit" "element by type"
    Then I shouldn't see the "EC_BODY_SHORT_NAME" element
    And I click on the "Clear the search text" "element by title"
    # Check Course Type filters
    And I set "EC_COURSE_TYPE_DESCRIPTION_TWO" text to the "Search" "Product Factory text field" from keyboard
    And I click on the "submit" "element by type"
    Then I shouldn't see the "EC_BODY_SHORT_NAME" element
    And I click on the "Clear the search text" "element by title"

  @Positive #TC-4592
  Scenario: Digital Content Search - Additional Filters
    When I click on the "Delivery" "Product Factory navigation item"
    When I click on the "Digital Content" "Product Factory navigation sub item"
    # Check Body Additional Filter
    And I click on the "Product Factory Additional Filters Button" button
    And I click on the "Body" "Product Factory dropdown"
    And I click on the "[EC_BODY_SHORT_NAME] - [EC_BODY_NAME]" "Product Factory dropdown option"
    And I click on the "Done" "Product Factory button"
    Then I should see the "EC_BODY_SHORT_NAME" "button"
    And I shouldn't see the "EC_BODY_SHORT_NAME_TWO" element
    And I click on the "Product Factory Clear Body Additional Filter" button
    # Check Level Additional Filter
    And I click on the "Product Factory Additional Filters Button" button
    And I click on the "Level" "Product Factory dropdown"
    And I click on the "[EC_LEVEL_SHORT_NAME] - [EC_LEVEL_NAME]" "Product Factory dropdown option"
    And I click on the "Done" "Product Factory button"
    Then I should see the "EC_LEVEL_SHORT_NAME" "button"
    And I shouldn't see the "EC_LEVEL_SHORT_NAME_TWO" element
    And I click on the "Product Factory Clear Level Additional Filter" button
    # Check Course Type Additional Filter
    And I click on the "Product Factory Additional Filters Button" button
    And I click on the "CourseType" "Product Factory dropdown"
    And I click on the "EC_COURSE_TYPE_DESCRIPTION" "Product Factory dropdown option"
    And I click on the "Done" "Product Factory button"
    Then I should see the "EC_COURSE_TYPE_DESCRIPTION" "button"
    And I shouldn't see the "EC_COURSE_TYPE_DESCRIPTION_TWO" element
    And I click on the "Product Factory Clear Course Type Additional Filter" button
    # Check Sitting Additional Filter
    And I click on the "Product Factory Additional Filters Button" button
    And I click on the "Sitting" "Product Factory dropdown"
    And I click on the "[EC_SITTING_NAME] ([EC_SITTING_START_DATE] - [EC_SITTING_END_DATE])" "Product Factory dropdown option"
    And I click on the "Done" "Product Factory button"
    Then I should see the "EC_SITTING_NAME" "button"
    And I shouldn't see the "EC_SITTING_SHORT_NAME_TWO" element
    And I click on the "Product Factory Clear Sitting Additional Filter" button
    # Check Paper Additional Filter
    And I click on the "Product Factory Additional Filters Button" button
    And I click on the "Paper" "Product Factory dropdown"
    And I click on the "Equals" "Product Factory dropdown option"
    And I set "EC_PAPER_NAME" text to the "Paper" "Product Factory text field"
    And I click on the "Done" "Product Factory button"
    Then I should see the "EC_PAPER_NAME" "button"
    And I shouldn't see the "EC_PAPER_NAME_TWO" element
    And I click on the "Product Factory Clear Paper Additional Filter" button
    # Check all Additional Filters at once
    And I click on the "Product Factory Additional Filters Button" button
    And I click on the "Body" "Product Factory dropdown"
    And I click on the "[EC_BODY_SHORT_NAME] - [EC_BODY_NAME]" "Product Factory dropdown option"
    And I click on the "Level" "Product Factory dropdown"
    And I click on the "[EC_LEVEL_SHORT_NAME] - [EC_LEVEL_NAME]" "Product Factory dropdown option"
    And I click on the "CourseType" "Product Factory dropdown"
    And I click on the "EC_COURSE_TYPE_DESCRIPTION" "Product Factory dropdown option"
    And I click on the "Sitting" "Product Factory dropdown"
    And I click on the "[EC_SITTING_NAME] ([EC_SITTING_START_DATE] - [EC_SITTING_END_DATE])" "Product Factory dropdown option"
    And I click on the "Paper" "Product Factory dropdown"
    And I click on the "Equals" "Product Factory dropdown option"
    And I set "EC_PAPER_NAME" text to the "Paper" "Product Factory text field"
    And I click on the "Done" "Product Factory button"
    Then I should see the "EC_PAPER_NAME" "button"
    And I shouldn't see the "EC_PAPER_NAME_TWO" element
    And I click on the "Product Factory Clear Paper Additional Filter" button