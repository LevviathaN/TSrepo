@ProductFactory @CBA @Courses @Amend
Feature: CBA - Update CBA Course Institution fee - BPP-3207
  As a Product Setter
  I want to be able to update the CBA Course Institution fee

  Background:
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

  @Positive @Regression @P1 #TC-2650
  Scenario: Update CBA Course Institution Fee
    And I execute "Log In" reusable step replacing some steps
      |3|I fill the "Product Factory Email" field with "MD_COMMON_CREDENTIALS_PRODUCTFACTORYJUNIORPRODUCTSETTER"|
      |4|I fill the "Product Factory Password" field with "MD_COMMON_CREDENTIALS_PRODUCTFACTORYJUNIORPSPASSWORD"|
    When I click on the "Products" "Product Factory navigation item"
    When I click on the "CBAs" "Product Factory navigation sub item"
    And I set "EC_COURSE_TYPE_DESCRIPTION" text to the "Search" "Product Factory text field" from keyboard
    And I click on the "submit" "element by type"
    And I click on the "EC_COURSE_TYPE_DESCRIPTION" "Product Factory edit button"
    Then I click on the "EC_BODY_SHORT_NAME" "Product Factory CBA Course Institute Fee edit button"
    And I set "~UpdatedInstituteFee[###]" text to the "EC_BODY_SHORT_NAME" "Product Factory CBA Course Institute Fee input field"
    And I click on the "Yes" "Product Factory button title"
    Then I should see the "[EC_UPDATED_INSTITUTE_FEE].00" element
#    todo price is absent on the page. Ask Maksym why so
#    And I should see the "[SUM(EC_PRICING_MATRIX_PRICE,EC_UPDATED_INSTITUTE_FEE)]$.00" element

  @Positive @Regression @P1 #TC-2768
  Scenario: Update CBA Course Institution Fee Additional Scenarios
    Given I execute "Log In" reusable step
    When I click on the "Products" "Product Factory navigation item"
    When I click on the "CBAs" "Product Factory navigation sub item"
    And I set "EC_COURSE_TYPE_DESCRIPTION" text to the "Search" "Product Factory text field" from keyboard
    And I click on the "submit" "element by type"
    And I click on the "EC_COURSE_TYPE_DESCRIPTION" "Product Factory edit button"
    And I click on the "Default Institute Fee (£)" "Product Factory edit button"
    And I set "~UpdatedInstituteFee[###]" text to the "Default Institute Fee (£)" "Product Factory text field"
    And I click on the "Save" "Product Factory button"
    And I click on the "Yes" "Product Factory button"
    And I click on the "Close" "Product Factory button"
    Then I should see the "[EC_UPDATED_INSTITUTE_FEE].00" element
#    And I should see the "[SUM(EC_PRICING_MATRIX_PRICE,EC_UPDATED_INSTITUTE_FEE)]$.00" element
    When I click on the "Financials" "Product Factory navigation item"
    When I click on the "Prices" "Product Factory navigation sub item"
    When I click on the "EC_SITTING_NAME" "Product Factory edit button"
    And I click on the "EC_COURSE_TYPE_DESCRIPTION" "text"
    And I click on the "EC_REGION_NAME" "text"
    And I click on the "EC_PAPER_NAME" "Product Factory change button"
    And I set "~UpdatedPMPrice[###]" text to the "Enter a Price (£)" "Product Factory text field"
    And I click on the "Save" "Product Factory active button"
    And I wait for "3" seconds
    When I click on the "Products" "Product Factory navigation item"
    When I click on the "CBAs" "Product Factory navigation sub item"
    Then I set "EC_SITTING_NAME" text to the "Search" "Product Factory text field" from keyboard
    And I click on the "submit" "element by type"
    And I click on the "EC_SITTING_NAME" "Product Factory edit button"
    And Browser performs "REFRESH" command
    Then I should see the "[EC_UPDATED_PM_PRICE].00" element
#    And I should see the "[SUM(EC_UPDATED_PM_PRICE,EC_UPDATED_INSTITUTE_FEE)]$.00" element