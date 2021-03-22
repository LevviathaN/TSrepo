@ProductFactory @Courses @Streams
Feature: Edit Instance - Streams - BPP-6518
  As a Product Setter
  I want the ability to specify the Stream on an Instance
  so that the relationship is made.

  Background:
    Given I execute "Generate ISBN" reusable step
    Given I execute "Log In" reusable step

    And I remember "AutoDeactivationReasonDescriptionDraftCourse" text as "EC_DEACTIVATION_REASON_DESCRIPTION" variable
    And I remember "AutoDeactivationReasonDescriptionPreventDraftCourse" text as "EC_DEACTIVATION_REASON_DESCRIPTION_PREVENT" variable
    And I remember "23/10/2020" text as "EC_SITTING_START_DATE" variable
    And I remember "10/10/2025" text as "EC_SITTING_END_DATE" variable
    And I remember "AutoStreamNameDraftCourse" text as "EC_STREAM_NAME" variable
    And I remember "AutoBodyFDCodeDraftCourse" text as "EC_BODY_FD_CODE" variable
    And I remember "AutoBodyFDDescriptionDraftCourse" text as "EC_BODY_FD_DESCRIPTION" variable
    And I remember "AutoBodyShortNameDraftCourse" text as "EC_BODY_SHORT_NAME" variable
    And I remember "AutoBodyNameDraftCourse" text as "EC_BODY_NAME" variable
    And I remember "AutoVatRuleCodeDraftCourse" text as "EC_VAT_RULE_CODE" variable
    And I remember "AutoVatRuleDescriptionDraftCourse" text as "EC_VAT_RULE_DESCRIPTION" variable
    And I remember "AutoCourseTypeFDCodeDraftCourse" text as "EC_COURSE_TYPE_FD_CODE" variable
    And I remember "AutoCourseTypeFDDescriptionDraftCourse" text as "EC_COURSE_TYPE_FD_DESCRIPTION" variable
    And I remember "AutoCourseTypeDescriptionDraftCourse" text as "EC_COURSE_TYPE_DESCRIPTION" variable
    And I remember "AutoLocationFDCodeDraftCourse" text as "EC_LOCATION_FD_CODE" variable
    And I remember "AutoLocationFDDescriptionDraftCourse" text as "EC_LOCATION_FD_DESCRIPTION" variable
    And I remember "AutoRegionFDCodeDraftCourse" text as "EC_REGION_FD_CODE" variable
    And I remember "AutoRegionFDDescriptionDraftCourse" text as "EC_REGION_FD_DESCRIPTION" variable
    And I remember "AutoRegionNameDraftCourse" text as "EC_REGION_NAME" variable
    And I remember "AutoLocationNameDraftCourse" text as "EC_LOCATION_NAME" variable
    And I remember "AutoLocationAddressDraftCourse" text as "EC_LOCATION_ADDRESS" variable
    And I remember "AutoLevelNameDraftCourse" text as "EC_LEVEL_NAME" variable
    And I remember "AutoLevelShortNameDraftCourse" text as "EC_LEVEL_SHORT_NAME" variable
    And I remember "DraftPprSN" text as "EC_PPR_SN" variable
    And I remember "DraftPaperName" text as "EC_PAPER_NAME" variable
    And I remember "DraftPaperDescription" text as "EC_PAPER_DESCRIPTION" variable
    And I remember "AutoSittingNameDraftCourse" text as "EC_SITTING_NAME" variable
    And I remember "AutoMaterialTypeFDCodeDraftCourse" text as "EC_MATERIAL_TYPE_FD_CODE" variable
    And I remember "AutoMaterialTypeFDDescriptionDraftCourse" text as "EC_MATERIAL_TYPE_FD_DESCRIPTION" variable
    And I remember "AutoMaterialTypeNameDraftCourse" text as "EC_MATERIAL_TYPE_NAME" variable
    And I remember "AutoMaterialTypeDescriptionDraftCourse" text as "EC_MATERIAL_TYPE_DESCRIPTION" variable
    And I remember "AutoSessionDurationDescriptionDraftCourse" text as "EC_SESSION_DURATION_DESCRIPTION" variable
    And I remember "AutoClientNameDraftCourse" text as "EC_CLIENT_NAME" variable
    And I remember "2000" text as "EC_PRICE" variable
    And I remember "10" text as "EC_WEIGHT" variable
    And I remember "1000" text as "EC_PRICING_MATRIX_PRICE" variable
    And I remember "AutoStreamNameTwo" text as "EC_STREAM_NAME_TWO" variable

  @Positive @P1 @NotFinished @DoNotRun #TC-2951, TC-2968
  Scenario: Add a Stream to Course Instance
    And I execute "Create Digital Content" reusable step
    And I execute "Create Stream" reusable step replacing some steps
      |4|I set "AutoStreamName" text to the "Name" "Product Factory text field"|
    And I execute "Create Stream" reusable step replacing some steps
      |4|I set "AutoStreamNameTwo" text to the "Name" "Product Factory text field"|
      |6|I should see the "EC_STREAM_NAME_TWO" element|
    Given I execute "Edit Course" reusable step
    Given I execute "Keep the Course Activated" reusable step
    Given I execute "Keep the Course Instance Activated" reusable step
#    Given I click on the "Activate" "Product Factory button" if "Activate,Product Factory button" "special element is present"
    And I execute "Populate Course Instance Session Dates" reusable step
    And I click on the "Stream" "Product Factory edit button"
    And I click on the "EC_STREAM_NAME" "Product Factory select button" if "EC_STREAM_NAME,Product Factory deselect button" "special element is present"
    And I should see the "EC_STREAM_NAME" element
#    Then I click on the "Delete" "Product Factory button title"
#    And I click on the "Yes" "Product Factory button"

    Then I click on the "Create" "Product Factory button"
    And I click on the "Default Session Duration" "Product Factory dropdown"
    And I click on the "EC_SESSION_DURATION_DESCRIPTION" "Product Factory dropdown option"
    And I click on the "Default Location" "Product Factory dropdown"
    And I click on the "EC_LOCATION_NAME" "Product Factory dropdown option"
    And I click on the "Save" "Product Factory button"
    And I execute modified "Populate Course Instance Session Dates" reusable step
      |1|Delete||
    And I click on the "Activate" "Product Factory button"

    And I click on the "Stream" "Product Factory edit button"
    And I click on the "EC_STREAM_NAME" "Product Factory select button"
    And I should see the "EC_STREAM_NAME" element
    And I click on the "Stream" "Product Factory edit button"
    And I click on the "EC_STREAM_NAME_TWO" "Product Factory select button" if "EC_STREAM_NAME_TWO,Product Factory deselect button" "special element is present"
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