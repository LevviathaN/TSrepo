@ProductFactory @CBA @Deactivation
Feature: CBA - Publish changes - BPP-3210
  As a Product Setter
  when I am editing a non-draft CBA, with unpublished changes,
  I want the ability to Publish the CBA and the associated Courses, Instances

  Background:
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

  @Positive @Regression @P1 #TC-3106
  Scenario: Publish CBA Changes Validation
    And I execute "Log In" reusable step replacing some steps
      |3|I fill the "Product Factory Email" field with "MD_COMMON_CREDENTIALS_PRODUCTFACTORYJUNIORPRODUCTSETTER"|
      |4|I fill the "Product Factory Password" field with "MD_COMMON_CREDENTIALS_PRODUCTFACTORYJUNIORPSPASSWORD"|
    When I click on the "Products" "Product Factory navigation item"
    When I click on the "CBAs" "Product Factory navigation sub item"
    Then I set "EC_COURSE_TYPE_DESCRIPTION" text to the "Search" "Product Factory text field" from keyboard
    And I click on the "submit" "element by type"
    And I click on the "EC_COURSE_TYPE_DESCRIPTION" "Product Factory edit button"
    When I click on the "Activate" "Product Factory button"
    #todo CBA might not be activated due to absence of the CBA price
    Then I should see the "(Active)" element
    Then I should see the "Published" message
    And I should see the "Deactivate" "Product Factory button"
    And Attribute "tabindex" of "Publish" "Product Factory button" should have value "-1"

    And I set "01/09/2025" text to the "Date" "Product Factory text field"
    And I click on the "Edit CBA" element
    Then I should see the "Date updated on CBA Course Sessions." element
    And I should see the "Unpublished changes" message
    And Attribute "tabindex" of "Publish" "Product Factory button" should have value "0"
    And I click on the "Publish" "Product Factory button"
    And I click on the "Yes" "Product Factory button"
    Then I should see the "Published" message
    And Attribute "tabindex" of "Publish" "Product Factory button" should have value "-1"

    And I click on the "Start Time" "Product Factory edit button"
    And I set "11:00" text to the "Start Time" "Product Factory text field"
    And I click on the "Save" "Product Factory button"
    And I should see the "11:00" message
    And I should see the "Times updated on CBA Course Sessions." message
    And I should see the "Unpublished changes" message
    And Attribute "tabindex" of "Publish" "Product Factory button" should have value "0"
    And I click on the "Publish" "Product Factory button"
    And I click on the "Yes" "Product Factory button"
    Then I should see the "Published" message
    And Attribute "tabindex" of "Publish" "Product Factory button" should have value "-1"

    And I click on the "Default Institute Fee (£)" "Product Factory edit button"
    And I set "~UpdatedInstituteFee[###]" text to the "Default Institute Fee (£)" "Product Factory text field"
    And I click on the "Save" "Product Factory button"
    And I click on the "Yes" "Product Factory button"
    And I should see the "Institute Fees Updated on CBA Courses." message
    Then I should see the "[EC_UPDATED_INSTITUTE_FEE].00" element in quantity of "2"
    And I should see the "Unpublished changes" message
    And Attribute "tabindex" of "Publish" "Product Factory button" should have value "0"
    And I click on the "Publish" "Product Factory button"
    And I click on the "Yes" "Product Factory button"
    Then I should see the "Published" message
    And Attribute "tabindex" of "Publish" "Product Factory button" should have value "-1"

    And I click on the "Capacity" "Product Factory edit button"
    And I set "2" text to the "Capacity" "Product Factory text field"
    And I click on the "Save" "Product Factory button"
    Then I should see the "Capacity updated on Course Instances." message
    Then I should see the "2" element
    And I should see the "Unpublished changes" message
    And Attribute "tabindex" of "Publish" "Product Factory button" should have value "0"
    And I click on the "Publish" "Product Factory button"
    And I click on the "Yes" "Product Factory button"
    Then I should see the "Published" message
    And Attribute "tabindex" of "Publish" "Product Factory button" should have value "-1"

    Then I click on the "EC_BODY_SHORT_NAME" "Product Factory CBA Course Institute Fee edit button"
    And I set "~UpdatedInstituteFee[###]" text to the "EC_BODY_SHORT_NAME" "Product Factory CBA Course Institute Fee input field"
    And I click on the "Yes" "Product Factory button title"
    Then I should see the "[EC_UPDATED_INSTITUTE_FEE].00" element
    And I should see the "Unpublished changes" message
    And Attribute "tabindex" of "Publish" "Product Factory button" should have value "0"
    And I click on the "Publish" "Product Factory button"
    And I click on the "Yes" "Product Factory button"
    Then I should see the "Published" message
    And Attribute "tabindex" of "Publish" "Product Factory button" should have value "-1"

    And I click on the "Clients" element
    And I click on the "Edit" "Product Factory button"
    And I "check" "EC_CLIENT_NAME" "Product Factory checkbox"
    And I click on the "Save" "Product Factory button"
    And I should see the "Clients updated on CBA Courses." message
    And I should see the "Unpublished changes" message
    And Attribute "tabindex" of "Publish" "Product Factory button" should have value "0"
    And I click on the "Publish" "Product Factory button"
    And I click on the "Yes" "Product Factory button"
    Then I should see the "Published" message
    And Attribute "tabindex" of "Publish" "Product Factory button" should have value "-1"


  @Positive @Regression @P1 #TC-3110
  Scenario: Publish CBA Changes Validation Additional Scenarios
    And I execute "Log In" reusable step replacing some steps
      |3|I fill the "Product Factory Email" field with "MD_COMMON_CREDENTIALS_PRODUCTFACTORYJUNIORPRODUCTSETTER"|
      |4|I fill the "Product Factory Password" field with "MD_COMMON_CREDENTIALS_PRODUCTFACTORYJUNIORPSPASSWORD"|

    When I click on the "Products" "Product Factory navigation item"
    When I click on the "CBAs" "Product Factory navigation sub item"
    Then I set "EC_COURSE_TYPE_DESCRIPTION" text to the "Search" "Product Factory text field" from keyboard
    And I click on the "submit" "element by type"
    And I click on the "EC_COURSE_TYPE_DESCRIPTION" "Product Factory edit button"
#    When I click on the "Activate" "Product Factory button"
#    Then I should see the "(Active)" element
    Then I should see the "Published" message
    And I should see the "Deactivate" "Product Factory button"
    And Attribute "tabindex" of "Publish" "Product Factory button" should have value "-1"

    #Canceling
    And I click on the "Start Time" "Product Factory edit button"
    And I set "11:00" text to the "Start Time" "Product Factory text field"
    And I click on the "Cancel" "Product Factory button"
    And I shouldn't see the "11:00" message
    And I shouldn't see the "Times updated on CBA Course Sessions." message
    And Attribute "tabindex" of "Publish" "Product Factory button" should have value "-1"

    And I click on the "Default Institute Fee (£)" "Product Factory edit button"
    And I set "~UpdatedInstituteFee[###]" text to the "Default Institute Fee (£)" "Product Factory text field"
    And I click on the "Cancel" "Product Factory button"
    And I shouldn't see the "Institute Fees Updated on CBA Courses." message
    And Attribute "tabindex" of "Publish" "Product Factory button" should have value "-1"

    And I click on the "Capacity" "Product Factory edit button"
    And I set "2" text to the "Capacity" "Product Factory text field"
    And I click on the "Cancel" "Product Factory button"
    Then I shouldn't see the "Capacity updated on Course Instances." message
    And Attribute "tabindex" of "Publish" "Product Factory button" should have value "-1"

    Then I click on the "EC_BODY_SHORT_NAME" "Product Factory CBA Course Institute Fee edit button"
    And I set "~UpdatedInstituteFee[###]" text to the "EC_BODY_SHORT_NAME" "Product Factory CBA Course Institute Fee input field"
    And I click on the "Cancel" "Product Factory button title"
    Then I shouldn't see the "[EC_UPDATED_INSTITUTE_FEE].00" element
    And Attribute "tabindex" of "Publish" "Product Factory button" should have value "-1"

    #Amending all at once
    And I set "01/09/2025" text to the "Date" "Product Factory text field"
    And I click on the "Edit CBA" element
    Then I should see the "Date updated on CBA Course Sessions." element

    And I click on the "Start Time" "Product Factory edit button"
    And I set "11:00" text to the "Start Time" "Product Factory text field"
    And I click on the "Save" "Product Factory button"
    And I should see the "11:00" message
    And I should see the "Times updated on CBA Course Sessions." message

    And I click on the "Default Institute Fee (£)" "Product Factory edit button"
    And I set "~UpdatedInstituteFee[###]" text to the "Default Institute Fee (£)" "Product Factory text field"
    And I click on the "Save" "Product Factory button"
    And I click on the "Yes" "Product Factory button"
    And I should see the "Institute Fees Updated on CBA Courses." message

    And I click on the "Capacity" "Product Factory edit button"
    And I set "2" text to the "Capacity" "Product Factory text field"
    And I click on the "Save" "Product Factory button"
    Then I should see the "Capacity updated on Course Instances." message

    Then I click on the "EC_BODY_SHORT_NAME" "Product Factory CBA Course Institute Fee edit button"
    And I set "~UpdatedInstituteFee[###]" text to the "EC_BODY_SHORT_NAME" "Product Factory CBA Course Institute Fee input field"
    And I click on the "Yes" "Product Factory button title"
    Then I should see the "[EC_UPDATED_INSTITUTE_FEE].00" element

    And Attribute "tabindex" of "Publish" "Product Factory button" should have value "0"
    And I click on the "Publish" "Product Factory button"
    And I click on the "Yes" "Product Factory button"
    Then I should see the "Published" message
    And Attribute "tabindex" of "Publish" "Product Factory button" should have value "-1"