@ProductFactory @FinancialDimensions @CourseTypes
Feature: Financial Dimension - Select Course Type Codes - BPP-2496
  As a Senior Product Setter
  I want to be able to select 2 Financial Dimensions on an individual Course Type
  so that I can track both the "AX Cost Centre" and "AX Project" codes for accounting purposes.

  Background:
    Given I execute "Generate ISBN" reusable step
    Given I execute "Log In" reusable step
    And I remember "AutoDeactivationReasonDescription" text as "EC_DEACTIVATION_REASON_DESCRIPTION" variable
    And I remember "AutoDeactivationReasonDescriptionPrevent" text as "EC_DEACTIVATION_REASON_DESCRIPTION_PREVENT" variable
    And I remember "23/10/2020" text as "EC_SITTING_START_DATE" variable
    And I remember "10/10/2025" text as "EC_SITTING_END_DATE" variable
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

    And I remember "AutoCourseTypeFDCodeTwo" text as "EC_COURSE_TYPE_FD_CODE_TWO" variable
    And I remember "AutoCourseTypeFDDescriptionTwo" text as "EC_COURSE_TYPE_FD_DESCRIPTION_TWO" variable
    And I remember "AutoCourseTypeFDCodeThree" text as "EC_COURSE_TYPE_FD_CODE_THREE" variable
    And I remember "AutoCourseTypeFDDescriptionThree" text as "EC_COURSE_TYPE_FD_DESCRIPTION_THREE" variable

  @Negative @Regression @P1 #TC-1772
  Scenario: Edit Financial Dimension for the Course Type that is in use
    When I click on the "Types" "Product Factory navigation item"
    When I click on the "Course Types" "Product Factory navigation sub item"
    And I set "EC_COURSE_TYPE_DESCRIPTION" text to the "Search" "Product Factory text field" from keyboard
    And I click on the "submit" "element by type"
    When I click on the "EC_COURSE_TYPE_DESCRIPTION" "Product Factory edit button"
    And I click on the "Cost Centre Financial Dimension" "Product Factory dropdown"
    And I click on the "EC_COURSE_TYPE_FD_CODE_TWO" "Product Factory dropdown option"
    And I click on the "Project Financial Dimension" "Product Factory dropdown"
    And I click on the "EC_COURSE_TYPE_FD_CODE_THREE" "Product Factory dropdown option"
    And I click on the "Save" "Product Factory button"
    Then I should see the "The Course Type is already linked to one or more Prices, so you cannot change the Course Type Financial Dimensions" element