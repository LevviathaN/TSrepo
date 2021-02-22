@ProductFactory @CBA @Amend
Feature: CBA - Edit - BPP-3200
  As a Product Setter
  I want to be able to amend any existing CBA records so that I can make any required changes.

  Background:
    And I remember "23/10/2020" text as "EC_SITTING_START_DATE" variable
    And I remember "10/10/2025" text as "EC_SITTING_END_DATE" variable
    And I remember "AutoStreamNameDraftCBA" text as "EC_STREAM_NAME" variable
    And I remember "AutoBodyFDCodeDraftCBA" text as "EC_BODY_FD_CODE" variable
    And I remember "AutoBodyFDDescriptionDraftCBA" text as "EC_BODY_FD_DESCRIPTION" variable
    And I remember "AutoBodyShortNameDraftCBA" text as "EC_BODY_SHORT_NAME" variable
    And I remember "AutoBodyNameDraftCBA" text as "EC_BODY_NAME" variable
    And I remember "AutoVatRuleCodeDraftCBA" text as "EC_VAT_RULE_CODE" variable
    And I remember "AutoVatRuleDescriptionDraftCBA" text as "EC_VAT_RULE_DESCRIPTION" variable
    And I remember "AutoCourseTypeFDCodeDraftCBA" text as "EC_COURSE_TYPE_FD_CODE" variable
    And I remember "AutoCourseTypeFDDescriptionDraftCBA" text as "EC_COURSE_TYPE_FD_DESCRIPTION" variable
    And I remember "AutoCourseTypeDescriptionDraftCBA" text as "EC_COURSE_TYPE_DESCRIPTION" variable
    And I remember "AutoLocationFDCodeDraftCBA" text as "EC_LOCATION_FD_CODE" variable
    And I remember "AutoLocationFDDescriptionDraftCBA" text as "EC_LOCATION_FD_DESCRIPTION" variable
    And I remember "AutoRegionFDCodeDraftCBA" text as "EC_REGION_FD_CODE" variable
    And I remember "AutoRegionFDDescriptionDraftCBA" text as "EC_REGION_FD_DESCRIPTION" variable
    And I remember "AutoRegionNameDraftCBA" text as "EC_REGION_NAME" variable
    And I remember "AutoLocationNameDraftCBA" text as "EC_LOCATION_NAME" variable
    And I remember "AutoLocationAddressDraftCBA" text as "EC_LOCATION_ADDRESS" variable
    And I remember "AutoLevelNameDraftCBA" text as "EC_LEVEL_NAME" variable
    And I remember "AutoLevelShortNamDraftCBAe" text as "EC_LEVEL_SHORT_NAME" variable
    And I remember "ACBAPsn" text as "EC_PPR_SN" variable
    And I remember "AutoCBAPaperNameDraftCBA" text as "EC_PAPER_NAME" variable
    And I remember "AutoCBAPaperDescriptionDraftCBA" text as "EC_PAPER_DESCRIPTION" variable
    And I remember "AutoSittingNameDraftCBA" text as "EC_SITTING_NAME" variable
    And I remember "AutoMaterialTypeFDCodeDraftCBA" text as "EC_MATERIAL_TYPE_FD_CODE" variable
    And I remember "AutoMaterialTypeFDDescriptionDraftCBA" text as "EC_MATERIAL_TYPE_FD_DESCRIPTION" variable
    And I remember "AutoMaterialTypeNameDraftCBA" text as "EC_MATERIAL_TYPE_NAME" variable
    And I remember "AutoMaterialTypeDescriptionDraftCBA" text as "EC_MATERIAL_TYPE_DESCRIPTION" variable
    And I remember "AutoCBASessionDurationDescriptionDraftCBA" text as "EC_SESSION_DURATION_DESCRIPTION" variable
    And I remember "AutoClientNameDraftCBA" text as "EC_CLIENT_NAME" variable
    And I remember "2000" text as "EC_PRICE" variable
    And I remember "10" text as "EC_WEIGHT" variable
    And I remember "1000" text as "EC_PRICING_MATRIX_PRICE" variable

    And I remember "AutoRegionNameTwo" text as "EC_REGION_NAME_TWO" variable
    And I remember "AutoLocationNameTwo" text as "EC_LOCATION_NAME_TWO" variable
    And I remember "AutoLocationAddressTwo" text as "EC_LOCATION_ADDRESS_TWO" variable
    And I remember "AutoSittingNameTwo" text as "EC_SITTING_NAME_TWO" variable
    And I remember "AutoCBASessionDurationDescriptionTwo" text as "EC_SESSION_DURATION_DESCRIPTION_TWO" variable
    And I remember "AutoCourseTypeDescriptionTwo" text as "EC_COURSE_TYPE_DESCRIPTION_TWO" variable

  @Positive @Regression @P1 #TC-2717, TC-2721, TC-2864
  Scenario: Update Draft CBA Record
    And I execute "Log In" reusable step replacing some steps
      |3|I fill the "Product Factory Email" field with "MD_COMMON_CREDENTIALS_PRODUCTFACTORYJUNIORPRODUCTSETTER"|
      |4|I fill the "Product Factory Password" field with "MD_COMMON_CREDENTIALS_PRODUCTFACTORYJUNIORPSPASSWORD"|
    And I remember "11:58:00" text as "EC_CBA_START_TIME" variable
    And I execute modified "Create CBA Record" reusable step
      |19|Replace|I set "11:58AM" text to the "Start Time" "Product Factory text field"|
    When I click on the "Products" "Product Factory navigation item"
    When I click on the "CBAs" "Product Factory navigation sub item"
    And I set "EC_COURSE_TYPE_DESCRIPTION" text to the "Search" "Product Factory text field" from keyboard
    And I click on the "submit" "element by type"
    And I click on the "EC_CBA_START_TIME" "Product Factory edit button"
    And I click on the "Sitting" "Product Factory edit button"
    And I click on the "EC_SITTING_NAME_TWO" "Product Factory select button"
    And I click on the "Session Duration" "Product Factory edit button"
    And I click on the "EC_SESSION_DURATION_DESCRIPTION_TWO" "Product Factory select button"
    And I click on the "Location" "Product Factory edit button"
    And I click on the "Region" "Product Factory dropdown"
    And I click on the "EC_REGION_NAME_TWO" "element"
    And I should see the "Location" "Product Factory dropdown"
    And I click on the "Location" "Product Factory dropdown"
    And I click on the "EC_LOCATION_NAME_TWO" "element"
    And I click on the "Course Type" "Product Factory edit button"
    And I set "EC_COURSE_TYPE_DESCRIPTION_TWO" text to the "Search" "Product Factory text field" from keyboard
    And I click on the "submit" "element by type"
    And I click on the "EC_COURSE_TYPE_DESCRIPTION_TWO" "Product Factory select button"
    And I click on the "Start Time" "Product Factory edit button"
    And I set "55:00" text to the "Start Time" "Product Factory text field"
    And I shouldn't see the "05:50" "element"
    And I click on the "Save" "Product Factory button"
    Then I click on the "CBAs" "Product Factory button title"
    And I set "EC_COURSE_TYPE_DESCRIPTION_TWO" text to the "Search" "Product Factory text field" from keyboard
    And I click on the "submit" "element by type"
    And I should see the "EC_COURSE_TYPE_DESCRIPTION_TWO" "Product Factory edit button"

  @Positive @Regression @P1 #TC-2865
  Scenario: Update Draft CBA Record With Course
    And I execute "Log In" reusable step replacing some steps
      |3|I fill the "Product Factory Email" field with "MD_COMMON_CREDENTIALS_PRODUCTFACTORYJUNIORPRODUCTSETTER"|
      |4|I fill the "Product Factory Password" field with "MD_COMMON_CREDENTIALS_PRODUCTFACTORYJUNIORPSPASSWORD"|
    And I execute "Create CBA Record" reusable step
    When I click on the "Create" "Product Factory button"
    Then I "check" "EC_BODY_SHORT_NAME" "Product Factory checkbox"
    And I click on the "Next" "Product Factory button"
    And I click on the "Next" "Product Factory button"
    And I click on the "Finish" "Product Factory button"
    And I wait for "3" seconds
    And I shouldn't see the "Sitting" "Product Factory edit button"
    And I shouldn't see the "Session Duration" "Product Factory edit button"
    And I shouldn't see the "Location" "Product Factory edit button"
    And I shouldn't see the "Course Type" "Product Factory edit button"
    And I click on the "Start Time" "Product Factory edit button"
    And I set "55:00" text to the "Start Time" "Product Factory text field"
    And I shouldn't see the "05:50" "element"
    And I click on the "Save" "Product Factory button"
    Then I click on the "CBAs" "Product Factory button title"
    And I set "EC_COURSE_TYPE_DESCRIPTION" text to the "Search" "Product Factory text field" from keyboard
    And I click on the "submit" "element by type"
    And I should see the "EC_COURSE_TYPE_DESCRIPTION" "Product Factory edit button"