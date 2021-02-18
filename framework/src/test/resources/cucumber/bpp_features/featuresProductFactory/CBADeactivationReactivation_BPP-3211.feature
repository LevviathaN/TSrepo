@ProductFactory @CBA @Deactivation
Feature: CBA - deactivation/reactivation - BPP-3211
  As a Product Setter
  when I am editing a CBA
  I want the ability to deactivate the CBA (and the associated Courses).

  Background:
    And I execute "Log In" reusable step replacing some steps
      |3|I fill the "Product Factory Email" field with "MD_COMMON_CREDENTIALS_PRODUCTFACTORYJUNIORPRODUCTSETTER"|
      |4|I fill the "Product Factory Password" field with "MD_COMMON_CREDENTIALS_PRODUCTFACTORYJUNIORPSPASSWORD"|
    And I remember "AutoDeactivationReasonDescriptionPrevent" text as "EC_DEACTIVATION_REASON_DESCRIPTION_PREVENT" variable
    And I remember "AutoDeactivationReasonDescription" text as "EC_DEACTIVATION_REASON_DESCRIPTION" variable
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
    And I remember "AtCBAPprSN" text as "EC_PPR_SN" variable
    And I remember "AutoCBAPaperName" text as "EC_PAPER_NAME" variable
    And I remember "AutoCBAPaperDescription" text as "EC_PAPER_DESCRIPTION" variable
    And I remember "AutoSittingName" text as "EC_SITTING_NAME" variable
    And I remember "AutoMaterialTypeFDCode" text as "EC_MATERIAL_TYPE_FD_CODE" variable
    And I remember "AutoMaterialTypeFDDescription" text as "EC_MATERIAL_TYPE_FD_DESCRIPTION" variable
    And I remember "AutoMaterialTypeName" text as "EC_MATERIAL_TYPE_NAME" variable
    And I remember "AutoMaterialTypeDescription" text as "EC_MATERIAL_TYPE_DESCRIPTION" variable
    And I remember "AutoCBASessionDurationDescription" text as "EC_SESSION_DURATION_DESCRIPTION" variable
    And I remember "AutoClientName" text as "EC_CLIENT_NAME" variable
    And I remember "2000" text as "EC_PRICE" variable
    And I remember "10" text as "EC_WEIGHT" variable
    And I remember "1000" text as "EC_PRICING_MATRIX_PRICE" variable

    And I remember "AutoSittingNameTwo" text as "EC_SITTING_NAME_TWO" variable
    And I remember "AutoCBASessionDurationDescriptionTwo" text as "EC_SESSION_DURATION_DESCRIPTION_TWO" variable
    And I remember "AutoCourseTypeDescriptionTwo" text as "EC_COURSE_TYPE_DESCRIPTION_TWO" variable
    And I remember "AutoRegionNameTwo" text as "EC_REGION_NAME_TWO" variable
    And I remember "AutoLocationNameTwo" text as "EC_LOCATION_NAME_TWO" variable
    And I remember "AutoLocationAddressTwo" text as "EC_LOCATION_ADDRESS_TWO" variable

  @Positive @Regression @P1 #TC-3119
  Scenario: Deactivate CBA Course With Prevent Reactivation Option On
    When I click on the "Products" "Product Factory navigation item"
    When I click on the "CBAs" "Product Factory navigation sub item"
    Then I set "EC_COURSE_TYPE_DESCRIPTION" text to the "Search" "Product Factory text field" from keyboard
    And I click on the "submit" "element by type"
    And I click on the "EC_COURSE_TYPE_DESCRIPTION" "Product Factory edit button"
#    And I shouldn't see the "Sitting" "Product Factory edit button"
#    And I shouldn't see the "Session Duration" "Product Factory edit button"
#    And I shouldn't see the "Location" "Product Factory edit button"
#    And I shouldn't see the "Course Type" "Product Factory edit button"
#    And I click on the "Start Time" "Product Factory edit button"
#    And I set "55:00" text to the "Start Time" "Product Factory text field"
#    And I shouldn't see the "05:50" "element"
#    And I click on the "Save" "Product Factory button"

#    Then I click on the "CBAs" "Product Factory button title"
#    Then I set "EC_COURSE_TYPE_DESCRIPTION" text to the "Search" "Product Factory text field" from keyboard
#    And I click on the "submit" "element by type"
#    And I click on the "EC_COURSE_TYPE_DESCRIPTION" "Product Factory edit button"
    When I click on the "Activate" "Product Factory button" if "Activate,Product Factory button" "special element is present"
    When I click on the "Reactivate" "Product Factory button" if "Reactivate,Product Factory button" "special element is present"
    When I click on the "Yes" "Product Factory button" if "Yes,Product Factory button" "special element is present"
    Then I should see the "(Active)" element
#    Then I should see the "Published" message
#    And I should see the "Deactivate" "Product Factory button"
    And I click on the "Deactivate" "Product Factory button"
    And I click on the "Deactivation Reason" "Product Factory change button"
    And I click on the "EC_DEACTIVATION_REASON_DESCRIPTION_PREVENT" "Product Factory select button"
    And I click on the "Confirm Deactivation" "Product Factory button"
    And Attribute "tabindex" of "Reactivate" "Product Factory button" should have value "-1"

  @Positive @Regression @P1 #TC-3118, TC-3121
  Scenario: Deactivate CBA Course With Prevent Reactivation Option Off
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
    Then I set "EC_COURSE_TYPE_DESCRIPTION" text to the "Search" "Product Factory text field" from keyboard
    And I click on the "submit" "element by type"
    And I click on the "EC_COURSE_TYPE_DESCRIPTION" "Product Factory edit button"
    When I click on the "Activate" "Product Factory button"
    Then I should see the "(Active)" element
    Then I should see the "Published" message
    And I should see the "Deactivate" "Product Factory button"
    And I click on the "Deactivate" "Product Factory button"
    And I click on the "Deactivation Reason" "Product Factory change button"
    And I click on the "EC_DEACTIVATION_REASON_DESCRIPTION" "Product Factory select button"
    And I click on the "Confirm Deactivation" "Product Factory button"
    And Attribute "tabindex" of "Reactivate" "Product Factory button" should have value "0"

    When I click on the "Products" "Product Factory navigation item"
    When I click on the "CBAs" "Product Factory navigation sub item"
    Then I set "EC_COURSE_TYPE_DESCRIPTION" text to the "Search" "Product Factory text field" from keyboard
    And I click on the "submit" "element by type"
    And I click on the "EC_COURSE_TYPE_DESCRIPTION" "Product Factory edit button"
    And I click on the "Reactivate" "Product Factory button"
    And I click on the "Yes" "Product Factory button"
    Then I should see the "Deactivate" "Product Factory button"