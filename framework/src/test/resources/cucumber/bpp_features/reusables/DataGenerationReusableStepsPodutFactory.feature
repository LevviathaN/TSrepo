@ReusableOld
Feature: Product Factory
  As a product setter
  I want to be able to log into Product Factory
  In order to (just cause)

  Background:
    Given I execute "Generate ISBN" reusable step
    And I execute "Log In" reusable step

      #______________________________Remember Variables__________________________
  Scenario: Remember Variables
    And I remember "AutoDeactivationReasonDescription" text as "EC_DEACTIVATION_REASON_DESCRIPTION" variable
    And I remember "AutoDeactivationReasonDescriptionPrevent" text as "EC_DEACTIVATION_REASON_DESCRIPTION_PREVENT" variable
    And I remember "23/10/2020" text as "EC_SITTING_START_DATE" variable
    And I remember "10/10/2025" text as "EC_SITTING_END_DATE" variable
    And I remember "AutoStreamName" text as "EC_STREAM_NAME" variable
    And I remember "AutoBodyFDCode" text as "EC_BODY_FD_CODE" variable
    And I remember "AutoBodyFDDescription" text as "EC_BODY_FD_DESCRIPTION" variable
    And I remember "AutoVerticalName" text as "EC_VERTICAL_NAME" variable
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

  Scenario Outline: Remember Variables <type>
    And I remember "AutoDeactivationReasonDescription" text as "EC_DEACTIVATION_REASON_DESCRIPTION" variable
    And I remember "AutoDeactivationReasonDescriptionPrevent" text as "EC_DEACTIVATION_REASON_DESCRIPTION_PREVENT" variable
    And I remember "23/10/2020" text as "EC_SITTING_START_DATE" variable
    And I remember "10/10/2025" text as "EC_SITTING_END_DATE" variable
    And I remember "AutoStreamName" text as "EC_STREAM_NAME" variable
    And I remember "AutoBodyFDCode" text as "EC_BODY_FD_CODE" variable
    And I remember "AutoBodyFDDescription" text as "EC_BODY_FD_DESCRIPTION" variable
    And I remember "AutoVerticalName" text as "EC_VERTICAL_NAME" variable
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

    Examples:
      |type        |varName     |ecName       |shortVar|shortEc|
      |            |            |             |        |       |
#    |Two         |$Two        |_TWO         |$Two    |_TWO   |
#    |Three       |$Three      |_THREE       |$Tre    |_TRE   |
#    |Edit        |$Edit       |_EDIT        |$Edt    |_EDT   |
#    |Draft CBA   |$DraftCBA   |_DRAFT_CBA   |$DCB    |_DCB   |
#    |Draft Course|$DraftCourse|_DRAFT_COURSE|$DCO    |_DCO   |

    #_________________________________Create Reference Data Set____________________________
  @CreateData
  Scenario: Create Reference Data Set
    Given I execute "Create Deactivation Reason" reusable step replacing some steps
      |4|I set "$Auto[DeactivationReasonDescription]" text to the "Description" "Product Factory text field"|
    And I execute modified "Create Deactivation Reason" reusable step
      |4|Replace|I set "$Auto[DeactivationReasonDescriptionPrevent]" text to the "Description" "Product Factory text field"|
      |5|Add|I click on the "Prevent Reactivation" "Product Factory checkbox"|
      |7|Replace|I should see the "EC_DEACTIVATION_REASON_DESCRIPTION_PREVENT" element|
    And I execute "Create Stream" reusable step replacing some steps
      |4|I set "$Auto[StreamName]" text to the "Name" "Product Factory text field"|
    When I execute "Create Body Financial Dimension" reusable step replacing some steps
      |4|I set "$Auto[BodyFDCode]" text to the "Code" "Product Factory text field"|
      |5|I set "$Auto[BodyFDDescription]" text to the "Description" "Product Factory text field"|
    And I execute "Create Vertical" reusable step replacing some steps
      |4|I set "$Auto[VerticalName]" text to the "Name" "Product Factory text field"|
    And I execute "Create Body" reusable step replacing some steps
      |4|I set "$Auto[BodyShortName]" text to the "Short Name" "Product Factory text field"|
      |5|I set "$Auto[BodyName]" text to the "Name" "Product Factory text field"|
    And I execute "Create VAT Rule" reusable step replacing some steps
      |4|I set "$Auto[VatRuleCode]" text to the "Code" "Product Factory text field"|
      |5|I set "$Auto[VatRuleDescription]" text to the "Description" "Product Factory text field"|
    And I execute "Create Course Type Financial Dimension" reusable step replacing some steps
      |4|I set "$Auto[CourseTypeFDCode]" text to the "Code" "Product Factory text field"|
      |5|I set "$Auto[CourseTypeFDDescription]" text to the "Description" "Product Factory text field"|
    And I execute "Create Course Type" reusable step replacing some steps
      |4|I set "$Auto[CourseTypeDescription]" text to the "Description" "Product Factory text field"|
    And I execute "Create Location Financial Dimension" reusable step replacing some steps
      |4|I set "$Auto[LocationFDCode]" text to the "Code" "Product Factory text field"|
      |5|I set "$Auto[LocationFDDescription]" text to the "Description" "Product Factory text field"|
    And I execute "Create Region Financial Dimension" reusable step replacing some steps
      |4|I set "$Auto[RegionFDCode]" text to the "Code" "Product Factory text field"|
      |5|I set "$Auto[RegionFDDescription]" text to the "Description" "Product Factory text field"|
    And I execute "Create Region" reusable step replacing some steps
      |4|I set "$Auto[RegionName]" text to the "Name" "Product Factory text field"|
    And I execute "Create Location" reusable step replacing some steps
      |4|I set "$Auto[LocationName]" text to the "Name" "Product Factory text field"|
      |5|I set "$Auto[LocationAddress]" text to the "Address Line 1" "Product Factory text field"|
    And I execute "Create Level" reusable step replacing some steps
      |4|I set "$Auto[LevelShortName]" text to the "Short Name" "Product Factory text field"|
      |5|I set "$Auto[LevelName]" text to the "Name" "Product Factory text field"|
    And I execute "Create Paper" reusable step replacing some steps
      |4|I set "$Auto[PprSN]" text to the "Short Name" "Product Factory text field"|
      |5|I set "$Auto[PaperName]" text to the "Name" "Product Factory text field"|
      |6|I set "$Auto[PaperDescription]" text to the "Description" "Product Factory text field"|
    And I execute modified "Create Paper" reusable step
      |4|Replace|I set "$At[CBAPprSN]" text to the "Short Name" "Product Factory text field"|
      |5|Add    |I "check" "CBA Paper?" "Product Factory checkbox"|
      |6|Add    |I set "1" text to the "Time in Hours" "Product Factory text field"|
      |7|Replace|I set "$Auto[CBAPaperName]" text to the "Name" "Product Factory text field"|
      |8|Replace|I set "$Auto[CBAPaperDescription]" text to the "Description" "Product Factory text field"|
    And I execute "Link Body To Level" reusable step
    And I execute "Link Body To Paper" reusable step
    And I execute "Link Paper To Level" reusable step
    And I execute "Link Body To Paper" reusable step replacing some steps
      |3|I set "EC_CBA_PAPER_DESCRIPTION" text to the "Search" "Product Factory text field" from keyboard|
      |5|I click on the "EC_CBA_PAPER_NAME" "Product Factory Change Body button"|
    And I execute "Link Paper To Level" reusable step replacing some steps
      |3|I set "EC_CBA_PAPER_DESCRIPTION" text to the "Search" "Product Factory text field" from keyboard|
      |5|I click on the "EC_CBA_PAPER_NAME" "Product Factory Link Levels button"|
    And I execute "Create Sitting" reusable step replacing some steps
      |4|I set "$Auto[SittingName]" text to the "Name" "Product Factory text field"|
    And I execute "Create Pricing Matrix" reusable step
    And I execute "Create Pricing Matrix Full" reusable step replacing some steps
      |12|I set "~PricingMatrixPrice[1000]" text to the "Enter a Price (£)" "Product Factory text field"|
    And I execute "Create Pricing Matrix Full" reusable step replacing some steps
      |10|I click on the "EC_CBA_PAPER_NAME" "Product Factory dialog checkbox"|
      |12|I set "~PricingMatrixPrice[1000]" text to the "Enter a Price (£)" "Product Factory text field"|
    And I execute "Create Material Type Financial Dimension" reusable step replacing some steps
      |4|I set "$Auto[MaterialTypeFDCode]" text to the "Code" "Product Factory text field"|
      |5|I set "$Auto[MaterialTypeFDDescription]" text to the "Description" "Product Factory text field"|
    And I execute "Create Material Type" reusable step replacing some steps
      |4|I set "$Auto[MaterialTypeName]" text to the "Name" "Product Factory text field"|
      |5|I set "$Auto[MaterialTypeDescription]" text to the "Description" "Product Factory text field"|
    And I execute "Create Session Duration" reusable step replacing some steps
      |4|I set "$Auto[SessionDurationDescription]" text to the "Description" "Product Factory text field"|
    And I execute modified "Create Session Duration" reusable step
      |4|Replace|I set "$Auto[CBASessionDurationDescription]" text to the "Description" "Product Factory text field"|
      |6|Add    |I "check" "Allowed for CBA" "Product Factory checkbox"|
      |8|Replace|I should see the "EC_CBA_SESSION_DURATION_DESCRIPTION" element|
    And I execute "Create Session Duration" reusable step replacing some steps
      |4|I set "$Auto[SessionDurationDescriptionTwoDates]" text to the "Description" "Product Factory text field"|
      |5|I set "2" text to the "Number of Dates" "Product Factory text field"|
    And I execute "Create Client" reusable step replacing some steps
      |4|I set "$Auto[ClientName]" text to the "Name" "Product Factory text field"|
    And I execute "Create Material" reusable step replacing some steps
      |6|I set "~Price[2000]" text to the "Price (£)" "Product Factory text field"|
      |7|I set "~Weight[10]" text to the "Weight (kg)" "Product Factory text field"|
    Then I execute "Create Course" reusable step
    And I execute "Create Course Instance" reusable step
    And I execute modified "Create CBA Record" reusable step
      |5|Replace|I click on the "EC_CBA_SESSION_DURATION_DESCRIPTION" "Product Factory select button"|
      |14|Replace|I should see the "EC_CBA_SESSION_DURATION_DESCRIPTION" element                         |
    And I execute modified "Create CBA Course" reusable step
      |11|Replace|I should see the "EC_CBA_PAPER_NAME" element|
    And I execute "Create Digital Content" reusable step
    And I execute "Create Stream" reusable step replacing some steps
      |4|I set "AutoStreamName" text to the "Name" "Product Factory text field"|

    #_________________________________Create Reference Data Set____________________________
  @CreateData
  Scenario Outline: Create Reference Data Set <type>
    Given I execute "Create Deactivation Reason" reusable step replacing some steps
      |4|I set "$Auto[DeactivationReasonDescription]<varName>" text to the "Description" "Product Factory text field"|
    And I execute modified "Create Deactivation Reason" reusable step
      |4|Replace|I set "$Auto[DeactivationReasonDescriptionPrevent]<varName>" text to the "Description" "Product Factory text field"|
      |5|Add|I click on the "Prevent Reactivation" "Product Factory checkbox"|
      |7|Replace|I should see the "EC_DEACTIVATION_REASON_DESCRIPTION_PREVENT<ecName>" element|
    And I execute "Create Stream" reusable step replacing some steps
      |4|I set "$Auto[StreamName]<varName>" text to the "Name" "Product Factory text field"|
    When I execute "Create Body Financial Dimension" reusable step replacing some steps
      |4|I set "$Auto[BodyFDCode]<varName>" text to the "Code" "Product Factory text field"|
      |5|I set "$Auto[BodyFDDescription]<varName>" text to the "Description" "Product Factory text field"|
    And I execute "Create Vertical" reusable step replacing some steps
      |4|I set "$Auto[VerticalName]<varName>" text to the "Name" "Product Factory text field"|
    And I execute "Create Body" reusable step replacing some steps
      |4|I set "$Auto[BodyShortName]<varName>" text to the "Short Name" "Product Factory text field"|
      |5|I set "$Auto[BodyName]<varName>" text to the "Name" "Product Factory text field"|
    And I execute "Create VAT Rule" reusable step replacing some steps
      |4|I set "$Auto[VatRuleCode]<varName>" text to the "Code" "Product Factory text field"|
      |5|I set "$Auto[VatRuleDescription]<varName>" text to the "Description" "Product Factory text field"|
    And I execute "Create Course Type Financial Dimension" reusable step replacing some steps
      |4|I set "$Auto[CourseTypeFDCode]<varName>" text to the "Code" "Product Factory text field"|
      |5|I set "$Auto[CourseTypeFDDescription]<varName>" text to the "Description" "Product Factory text field"|
    And I execute "Create Course Type" reusable step replacing some steps
      |4|I set "$Auto[CourseTypeDescription]<varName>" text to the "Description" "Product Factory text field"|
    And I execute "Create Location Financial Dimension" reusable step replacing some steps
      |4|I set "$Auto[LocationFDCode]<varName>" text to the "Code" "Product Factory text field"|
      |5|I set "$Auto[LocationFDDescription]<varName>" text to the "Description" "Product Factory text field"|
    And I execute "Create Region Financial Dimension" reusable step replacing some steps
      |4|I set "$Auto[RegionFDCode]<varName>" text to the "Code" "Product Factory text field"|
      |5|I set "$Auto[RegionFDDescription]<varName>" text to the "Description" "Product Factory text field"|
    And I execute "Create Region" reusable step replacing some steps
      |4|I set "$Auto[RegionName]<varName>" text to the "Name" "Product Factory text field"|
    And I execute "Create Location" reusable step replacing some steps
      |4|I set "$Auto[LocationName]<varName>" text to the "Name" "Product Factory text field"|
      |5|I set "$Auto[LocationAddress]<varName>" text to the "Address Line 1" "Product Factory text field"|
    And I execute "Create Level" reusable step replacing some steps
      |4|I set "$Auto[LevelShortName]<varName>" text to the "Short Name" "Product Factory text field"|
      |5|I set "$Auto[LevelName]<varName>" text to the "Name" "Product Factory text field"|
    And I execute "Create Paper" reusable step replacing some steps
      |4|I set "$At[PprSN]<shortVar>" text to the "Short Name" "Product Factory text field"|
      |5|I set "$Auto[PaperName]<varName>" text to the "Name" "Product Factory text field"|
      |6|I set "$Auto[PaperDescription]<varName>" text to the "Description" "Product Factory text field"|
    And I execute modified "Create Paper" reusable step
      |4|Replace|I set "$A[CBAPsn]<shortVar>" text to the "Short Name" "Product Factory text field"|
      |5|Add    |I "check" "CBA Paper?" "Product Factory checkbox"|
      |6|Add    |I set "1" text to the "Time in Hours" "Product Factory text field"|
      |7|Replace|I set "$Auto[CBAPaperName]<varName>" text to the "Name" "Product Factory text field"|
      |8|Replace|I set "$Auto[CBAPaperDescription]<varName>" text to the "Description" "Product Factory text field"|
    And I execute "Link Body To Level" reusable step
    And I execute "Link Body To Paper" reusable step
    And I execute "Link Paper To Level" reusable step
    And I execute "Link Body To Paper" reusable step replacing some steps
      |3|I set "EC_CBA_PAPER_DESCRIPTION<ecName>" text to the "Search" "Product Factory text field" from keyboard|
      |5|I click on the "EC_CBA_PAPER_NAME<ecName>" "Product Factory Change Body button"|
    And I execute "Link Paper To Level" reusable step replacing some steps
      |3|I set "EC_CBA_PAPER_DESCRIPTION<ecName>" text to the "Search" "Product Factory text field" from keyboard|
      |5|I click on the "EC_CBA_PAPER_NAME<ecName>" "Product Factory Link Levels button"|
    And I execute "Create Sitting" reusable step replacing some steps
      |4|I set "$Auto[SittingName]<varName>" text to the "Name" "Product Factory text field"|
    And I execute "Create Pricing Matrix" reusable step
    And I execute "Create Pricing Matrix Full" reusable step replacing some steps
      |12|I set "~PricingMatrixPrice[1000]" text to the "Enter a Price (£)" "Product Factory text field"|
    And I execute "Create Pricing Matrix Full" reusable step replacing some steps
      |10|I click on the "EC_CBA_PAPER_NAME" "Product Factory dialog checkbox"|
      |12|I set "~PricingMatrixPrice[1000]" text to the "Enter a Price (£)" "Product Factory text field"|
    And I execute "Create Material Type Financial Dimension" reusable step replacing some steps
      |4|I set "$Auto[MaterialTypeFDCode]<varName>" text to the "Code" "Product Factory text field"|
      |5|I set "$Auto[MaterialTypeFDDescription]<varName>" text to the "Description" "Product Factory text field"|
    And I execute "Create Material Type" reusable step replacing some steps
      |4|I set "$Auto[MaterialTypeName]<varName>" text to the "Name" "Product Factory text field"|
      |5|I set "$Auto[MaterialTypeDescription]<varName>" text to the "Description" "Product Factory text field"|
    And I execute "Create Session Duration" reusable step replacing some steps
      |4|I set "$Auto[SessionDurationDescription]<varName>" text to the "Description" "Product Factory text field"|
    And I execute modified "Create Session Duration" reusable step
      |4|Replace|I set "$Auto[CBASessionDurationDescription]<varName>" text to the "Description" "Product Factory text field"|
      |6|Add    |I "check" "Allowed for CBA" "Product Factory checkbox"|
      |8|Replace|I should see the "EC_CBA_SESSION_DURATION_DESCRIPTION<ecName>" element|
    And I execute "Create Session Duration" reusable step replacing some steps
      |4|I set "$Auto[SessionDurationDescriptionTwoDates]<varName>" text to the "Description" "Product Factory text field"|
      |5|I set "2" text to the "Number of Dates" "Product Factory text field"|
    And I execute "Create Client" reusable step replacing some steps
      |4|I set "$Auto[ClientName]<varName>" text to the "Name" "Product Factory text field"|
    And I execute "Create Material" reusable step replacing some steps
      |6|I set "~Price[2000]" text to the "Price (£)" "Product Factory text field"|
      |7|I set "~Weight[10]" text to the "Weight (kg)" "Product Factory text field"|
    Then I execute "Create Course" reusable step
    And I execute "Create Course Instance" reusable step
    And I execute modified "Create CBA Record" reusable step
      |5|Replace|I click on the "EC_CBA_SESSION_DURATION_DESCRIPTION<ecName>" "Product Factory select button"|
      |14|Replace|I should see the "EC_CBA_SESSION_DURATION_DESCRIPTION<ecName>" element                         |
    And I execute modified "Create CBA Course" reusable step
      |11|Replace|I should see the "EC_CBA_PAPER_NAME<ecName>" element|
    And I execute "Create Digital Content" reusable step
    And I execute "Create Stream" reusable step replacing some steps
      |4|I set "$Auto[StreamName]<varName>" text to the "Name" "Product Factory text field"|

    Examples:
    |type        |varName     |ecName       |shortVar|shortEc|
    |            |            |             |        |       |
#    |Two         |$Two        |_TWO         |$Two    |_TWO   |
#    |Three       |$Three      |_THREE       |$Tre    |_TRE   |
#    |Edit        |$Edit       |_EDIT        |$Edt    |_EDT   |
#    |Draft CBA   |$DraftCBA   |_DRAFT_CBA   |$DCB    |_DCB   |
#    |Draft Course|$DraftCourse|_DRAFT_COURSE|$DCO    |_DCO   |

  #______________________________Remember Variables 2__________________________
  Scenario: Remember Variables Two
    And I remember "23/10/2020" text as "EC_SITTING_START_DATE" variable
    And I remember "10/10/2025" text as "EC_SITTING_END_DATE" variable
    And I remember "AutoBodyFDCodeTwo" text as "EC_BODY_FD_CODE" variable
    And I remember "AutoBodyFDDescriptionTwo" text as "EC_BODY_FD_DESCRIPTION" variable
    And I remember "AutoVerticalNameTwo" text as "EC_VERTICAL_NAME" variable
    And I remember "AutoBodyShortNameTwo" text as "EC_BODY_SHORT_NAME" variable
    And I remember "AutoBodyNameTwo" text as "EC_BODY_NAME" variable
    And I remember "AutoVatRuleCodeTwo" text as "EC_VAT_RULE_CODE" variable
    And I remember "AutoVatRuleDescriptionTwo" text as "EC_VAT_RULE_DESCRIPTION" variable
    And I remember "AutoCourseTypeFDCodeTwo" text as "EC_COURSE_TYPE_FD_CODE" variable
    And I remember "AutoCourseTypeFDDescriptionTwo" text as "EC_COURSE_TYPE_FD_DESCRIPTION" variable
    And I remember "AutoCourseTypeDescriptionTwo" text as "EC_COURSE_TYPE_DESCRIPTION" variable
    And I remember "AutoLocationFDCodeTwo" text as "EC_LOCATION_FD_CODE" variable
    And I remember "AutoLocationFDDescriptionTwo" text as "EC_LOCATION_FD_DESCRIPTION" variable
    And I remember "AutoRegionFDCodeTwo" text as "EC_REGION_FD_CODE" variable
    And I remember "AutoRegionFDDescriptionTwo" text as "EC_REGION_FD_DESCRIPTION" variable
    And I remember "AutoRegionNameTwo" text as "EC_REGION_NAME" variable
    And I remember "AutoLocationNameTwo" text as "EC_LOCATION_NAME" variable
    And I remember "AutoLocationAddressTwo" text as "EC_LOCATION_ADDRESS" variable
    And I remember "AutoLevelNameTwo" text as "EC_LEVEL_NAME" variable
    And I remember "AutoLevelShortNameTwo" text as "EC_LEVEL_SHORT_NAME" variable
    And I remember "AtPprSNTwo" text as "EC_PPR_SN" variable
    And I remember "AutoPaperNameTwo" text as "EC_PAPER_NAME" variable
    And I remember "AutoPaperDescriptionTwo" text as "EC_PAPER_DESCRIPTION" variable
    And I remember "AutoSittingNameTwo" text as "EC_SITTING_NAME" variable
    And I remember "AutoMaterialTypeFDCodeTwo" text as "EC_MATERIAL_TYPE_FD_CODE" variable
    And I remember "AutoMaterialTypeFDDescriptionTwo" text as "EC_MATERIAL_TYPE_FD_DESCRIPTION" variable
    And I remember "AutoMaterialTypeNameTwo" text as "EC_MATERIAL_TYPE_NAME" variable
    And I remember "AutoMaterialTypeDescriptionTwo" text as "EC_MATERIAL_TYPE_DESCRIPTION" variable
    And I remember "AutoSessionDurationDescriptionTwo" text as "EC_SESSION_DURATION_DESCRIPTION" variable
    And I remember "AutoCBASessionDurationDescriptionTwo" text as "EC_CBA_SESSION_DURATION_DESCRIPTION" variable
    And I remember "AutoSessionDurationDescriptionTwoDatesTwo" text as "EC_SESSION_DURATION_DESCRIPTION_TWO_DATES" variable
    And I remember "AutoClientNameTwo" text as "EC_CLIENT_NAME" variable
    And I remember "2000" text as "EC_PRICE" variable
    And I remember "10" text as "EC_WEIGHT" variable

    #_________________________________Create Reference Data Set 2____________________________
  @CreateData
  Scenario: Create Reference Data Set Two
    When I execute "Create Body Financial Dimension" reusable step replacing some steps
      |4|I set "$Auto[BodyFDCode]$Two" text to the "Code" "Product Factory text field"|
      |5|I set "$Auto[BodyFDDescription]$Two" text to the "Description" "Product Factory text field"|
    And I execute "Create Vertical" reusable step replacing some steps
      |4|I set "$Auto[VerticalName]$Two" text to the "Name" "Product Factory text field"|
    And I execute "Create Body" reusable step replacing some steps
      |4|I set "$Auto[BodyShortName]$Two" text to the "Short Name" "Product Factory text field"|
      |5|I set "$Auto[BodyName]$Two" text to the "Name" "Product Factory text field"|
    And I execute "Create VAT Rule" reusable step replacing some steps
      |4|I set "$Auto[VatRuleCode]$Two" text to the "Code" "Product Factory text field"|
      |5|I set "$Auto[VatRuleDescription]$Two" text to the "Description" "Product Factory text field"|
    And I execute "Create Course Type Financial Dimension" reusable step replacing some steps
      |4|I set "$Auto[CourseTypeFDCode]$Two" text to the "Code" "Product Factory text field"|
      |5|I set "$Auto[CourseTypeFDDescription]$Two" text to the "Description" "Product Factory text field"|
    And I execute "Create Course Type" reusable step replacing some steps
      |4|I set "$Auto[CourseTypeDescription]$Two" text to the "Description" "Product Factory text field"|
    And I execute "Create Location Financial Dimension" reusable step replacing some steps
      |4|I set "$Auto[LocationFDCode]$Two" text to the "Code" "Product Factory text field"|
      |5|I set "$Auto[LocationFDDescription]$Two" text to the "Description" "Product Factory text field"|
    And I execute "Create Region Financial Dimension" reusable step replacing some steps
      |4|I set "$Auto[RegionFDCode]$Two" text to the "Code" "Product Factory text field"|
      |5|I set "$Auto[RegionFDDescription]$Two" text to the "Description" "Product Factory text field"|
    And I execute "Create Region" reusable step replacing some steps
      |4|I set "$Auto[RegionName]$Two" text to the "Name" "Product Factory text field"|
    And I execute "Create Location" reusable step replacing some steps
      |4|I set "$Auto[LocationName]$Two" text to the "Name" "Product Factory text field"|
      |5|I set "$Auto[LocationAddress]$Two" text to the "Address Line 1" "Product Factory text field"|
    And I execute "Create Level" reusable step replacing some steps
      |4|I set "$Auto[LevelShortName]$Two" text to the "Short Name" "Product Factory text field"|
      |5|I set "$Auto[LevelName]$Two" text to the "Name" "Product Factory text field"|
    And I execute "Create Paper" reusable step replacing some steps
      |4|I set "$At[PprSN]$Two" text to the "Short Name" "Product Factory text field"|
      |5|I set "$Auto[PaperName]$Two" text to the "Name" "Product Factory text field"|
      |6|I set "$Auto[PaperDescription]$Two" text to the "Description" "Product Factory text field"|
    And I execute "Link Body To Level" reusable step
    And I execute "Link Body To Paper" reusable step
    And I execute "Link Paper To Level" reusable step
    And I execute "Create Sitting" reusable step replacing some steps
      |4|I set "$Auto[SittingName]$Two" text to the "Name" "Product Factory text field"|
    And I execute "Create Pricing Matrix" reusable step
    And I execute "Create Pricing Matrix Full" reusable step replacing some steps
      |12|I set "~PricingMatrixPrice[1000]" text to the "Enter a Price (£)" "Product Factory text field"|
    And I execute "Create Material Type Financial Dimension" reusable step replacing some steps
      |4|I set "$Auto[MaterialTypeFDCode]$Two" text to the "Code" "Product Factory text field"|
      |5|I set "$Auto[MaterialTypeFDDescription]$Two" text to the "Description" "Product Factory text field"|
    And I execute "Create Material Type" reusable step replacing some steps
      |4|I set "$Auto[MaterialTypeName]$Two" text to the "Name" "Product Factory text field"|
      |5|I set "$Auto[MaterialTypeDescription]$Two" text to the "Description" "Product Factory text field"|
    And I execute "Create Session Duration" reusable step replacing some steps
      |4|I set "$Auto[SessionDurationDescription]$Two" text to the "Description" "Product Factory text field"|
    And I execute modified "Create Session Duration" reusable step
      |4|Replace|I set "$Auto[CBASessionDurationDescription]$Two" text to the "Description" "Product Factory text field"|
      |6|Add    |I "check" "Allowed for CBA" "Product Factory checkbox"|
      |8|Replace|I should see the "EC_CBA_SESSION_DURATION_DESCRIPTION" element|
    And I execute "Create Session Duration" reusable step replacing some steps
      |4|I set "$Auto[SessionDurationDescriptionTwoDates]$Two" text to the "Description" "Product Factory text field"|
      |5|I set "2" text to the "Number of Dates" "Product Factory text field"|
    And I execute "Create Client" reusable step replacing some steps
      |4|I set "$Auto[ClientName]$Two" text to the "Name" "Product Factory text field"|
    And I execute "Create Material" reusable step replacing some steps
      |6|I set "~Price[2000]" text to the "Price (£)" "Product Factory text field"|
      |7|I set "~Weight[10]" text to the "Weight (kg)" "Product Factory text field"|
    Then I execute "Create Course" reusable step
    And I execute "Create Course Instance" reusable step
    And I execute "Create Stream" reusable step replacing some steps
      |4|I set "AutoStreamNameTwo" text to the "Name" "Product Factory text field"|
      |6|I should see the "EC_STREAM_NAME_TWO" element|

#    ______________________________Remember Variables 3__________________________
  Scenario: Remember Variables Three
    And I remember "23/10/2020" text as "EC_SITTING_START_DATE" variable
    And I remember "10/10/2025" text as "EC_SITTING_END_DATE" variable
    And I remember "AutoBodyFDCodeThree" text as "EC_BODY_FD_CODE" variable
    And I remember "AutoBodyFDDescriptionThree" text as "EC_BODY_FD_DESCRIPTION" variable
    And I remember "AutoVerticalNameThree" text as "EC_VERTICAL_NAME" variable
    And I remember "AutoBodyShortNameThree" text as "EC_BODY_SHORT_NAME" variable
    And I remember "AutoBodyNameThree" text as "EC_BODY_NAME" variable
    And I remember "AutoVatRuleCodeThree" text as "EC_VAT_RULE_CODE" variable
    And I remember "AutoVatRuleDescriptionThree" text as "EC_VAT_RULE_DESCRIPTION" variable
    And I remember "AutoCourseTypeFDCodeThree" text as "EC_COURSE_TYPE_FD_CODE" variable
    And I remember "AutoCourseTypeFDDescriptionThree" text as "EC_COURSE_TYPE_FD_DESCRIPTION" variable
    And I remember "AutoCourseTypeDescriptionThree" text as "EC_COURSE_TYPE_DESCRIPTION" variable
    And I remember "AutoLocationFDCodeThree" text as "EC_LOCATION_FD_CODE" variable
    And I remember "AutoLocationFDDescriptionThree" text as "EC_LOCATION_FD_DESCRIPTION" variable
    And I remember "AutoRegionFDCodeThree" text as "EC_REGION_FD_CODE" variable
    And I remember "AutoRegionFDDescriptionThree" text as "EC_REGION_FD_DESCRIPTION" variable
    And I remember "AutoRegionNameThree" text as "EC_REGION_NAME" variable
    And I remember "AutoLocationNameThree" text as "EC_LOCATION_NAME" variable
    And I remember "AutoLocationAddressThree" text as "EC_LOCATION_ADDRESS" variable
    And I remember "AutoLevelNameThree" text as "EC_LEVEL_NAME" variable
    And I remember "AutoLevelShortNameThree" text as "EC_LEVEL_SHORT_NAME" variable
    And I remember "AtPprSNTre" text as "EC_PPR_SN" variable
    And I remember "AutoPaperNameThree" text as "EC_PAPER_NAME" variable
    And I remember "AutoPaperDescriptionThree" text as "EC_PAPER_DESCRIPTION" variable
    And I remember "AutoSittingNameThree" text as "EC_SITTING_NAME" variable
    And I remember "AutoMaterialTypeFDCodeThree" text as "EC_MATERIAL_TYPE_FD_CODE" variable
    And I remember "AutoMaterialTypeFDDescriptionThree" text as "EC_MATERIAL_TYPE_FD_DESCRIPTION" variable
    And I remember "AutoMaterialTypeNameThree" text as "EC_MATERIAL_TYPE_NAME" variable
    And I remember "AutoMaterialTypeDescriptionThree" text as "EC_MATERIAL_TYPE_DESCRIPTION" variable
    And I remember "AutoSessionDurationDescriptionThree" text as "EC_SESSION_DURATION_DESCRIPTION" variable
    And I remember "AutoCBASessionDurationDescriptionThree" text as "EC_CBA_SESSION_DURATION_DESCRIPTION" variable
    And I remember "AutoSessionDurationDescriptionTwoDatesThree" text as "EC_SESSION_DURATION_DESCRIPTION_TWO_DATES" variable
    And I remember "AutoClientNameThree" text as "EC_CLIENT_NAME" variable
    And I remember "2000" text as "EC_PRICE" variable
    And I remember "10" text as "EC_WEIGHT" variable

    #_________________________________Create Reference Data Set 3____________________________
  @CreateData
  Scenario: Create Reference Data Set Three
    When I execute "Create Body Financial Dimension" reusable step replacing some steps
      |4|I set "$Auto[BodyFDCode]$Three" text to the "Code" "Product Factory text field"|
      |5|I set "$Auto[BodyFDDescription]$Three" text to the "Description" "Product Factory text field"|
    And I execute "Create Vertical" reusable step replacing some steps
      |4|I set "$Auto[VerticalName]$Three" text to the "Name" "Product Factory text field"|
    And I execute "Create Body" reusable step replacing some steps
      |4|I set "$Auto[BodyShortName]$Three" text to the "Short Name" "Product Factory text field"|
      |5|I set "$Auto[BodyName]$Three" text to the "Name" "Product Factory text field"|
    And I execute "Create VAT Rule" reusable step replacing some steps
      |4|I set "$Auto[VatRuleCode]$Three" text to the "Code" "Product Factory text field"|
      |5|I set "$Auto[VatRuleDescription]$Three" text to the "Description" "Product Factory text field"|
    And I execute "Create Course Type Financial Dimension" reusable step replacing some steps
      |4|I set "$Auto[CourseTypeFDCode]$Three" text to the "Code" "Product Factory text field"|
      |5|I set "$Auto[CourseTypeFDDescription]$Three" text to the "Description" "Product Factory text field"|
    And I execute "Create Course Type" reusable step replacing some steps
      |4|I set "$Auto[CourseTypeDescription]$Three" text to the "Description" "Product Factory text field"|
    And I execute "Create Location Financial Dimension" reusable step replacing some steps
      |4|I set "$Auto[LocationFDCode]$Three" text to the "Code" "Product Factory text field"|
      |5|I set "$Auto[LocationFDDescription]$Three" text to the "Description" "Product Factory text field"|
    And I execute "Create Region Financial Dimension" reusable step replacing some steps
      |4|I set "$Auto[RegionFDCode]$Three" text to the "Code" "Product Factory text field"|
      |5|I set "$Auto[RegionFDDescription]$Three" text to the "Description" "Product Factory text field"|
    And I execute "Create Region" reusable step replacing some steps
      |4|I set "$Auto[RegionName]$Three" text to the "Name" "Product Factory text field"|
    And I execute "Create Location" reusable step replacing some steps
      |4|I set "$Auto[LocationName]$Three" text to the "Name" "Product Factory text field"|
      |5|I set "$Auto[LocationAddress]$Three" text to the "Address Line 1" "Product Factory text field"|
    And I execute "Create Level" reusable step replacing some steps
      |4|I set "$Auto[LevelShortName]$Three" text to the "Short Name" "Product Factory text field"|
      |5|I set "$Auto[LevelName]$Three" text to the "Name" "Product Factory text field"|
    And I execute "Create Paper" reusable step replacing some steps
      |4|I set "$At[PprSN]$Tre" text to the "Short Name" "Product Factory text field"|
      |5|I set "$Auto[PaperName]$Three" text to the "Name" "Product Factory text field"|
      |6|I set "$Auto[PaperDescription]$Three" text to the "Description" "Product Factory text field"|
    And I execute "Link Body To Level" reusable step
    And I execute "Link Body To Paper" reusable step
    And I execute "Link Paper To Level" reusable step
    And I execute "Create Sitting" reusable step replacing some steps
      |4|I set "$Auto[SittingName]$Three" text to the "Name" "Product Factory text field"|
    And I execute "Create Pricing Matrix" reusable step
    And I execute "Create Pricing Matrix Full" reusable step replacing some steps
      |12|I set "~PricingMatrixPrice[1000]" text to the "Enter a Price (£)" "Product Factory text field"|
    And I execute "Create Material Type Financial Dimension" reusable step replacing some steps
      |4|I set "$Auto[MaterialTypeFDCode]$Three" text to the "Code" "Product Factory text field"|
      |5|I set "$Auto[MaterialTypeFDDescription]$Three" text to the "Description" "Product Factory text field"|
    And I execute "Create Material Type" reusable step replacing some steps
      |4|I set "$Auto[MaterialTypeName]$Three" text to the "Name" "Product Factory text field"|
      |5|I set "$Auto[MaterialTypeDescription]$Three" text to the "Description" "Product Factory text field"|
    And I execute "Create Session Duration" reusable step replacing some steps
      |4|I set "$Auto[SessionDurationDescription]$Three" text to the "Description" "Product Factory text field"|
    And I execute modified "Create Session Duration" reusable step
      |4|Replace|I set "$Auto[CBASessionDurationDescription]$Three" text to the "Description" "Product Factory text field"|
      |6|Add    |I "check" "Allowed for CBA" "Product Factory checkbox"|
      |8|Replace|I should see the "EC_CBA_SESSION_DURATION_DESCRIPTION" element|
    And I execute "Create Session Duration" reusable step replacing some steps
      |4|I set "$Auto[SessionDurationDescriptionTwoDates]$Three" text to the "Description" "Product Factory text field"|
      |5|I set "2" text to the "Number of Dates" "Product Factory text field"|
    And I execute "Create Client" reusable step replacing some steps
      |4|I set "$Auto[ClientName]$Three" text to the "Name" "Product Factory text field"|
    And I execute "Create Material" reusable step replacing some steps
      |6|I set "~Price[2000]" text to the "Price (£)" "Product Factory text field"|
      |7|I set "~Weight[10]" text to the "Weight (kg)" "Product Factory text field"|
    Then I execute "Create Course" reusable step
    And I execute "Create Course Instance" reusable step

          #______________________________Remember Variables For Editing__________________________
  Scenario: Remember Variables For Editing
    And I remember "AutoDeactivationReasonDescriptionEdit" text as "EC_DEACTIVATION_REASON_DESCRIPTION" variable
    And I remember "AutoDeactivationReasonDescriptionPreventEdit" text as "EC_DEACTIVATION_REASON_DESCRIPTION_PREVENT" variable
    And I remember "23/10/2020" text as "EC_SITTING_START_DATE" variable
    And I remember "10/10/2025" text as "EC_SITTING_END_DATE" variable
    And I remember "AutoStreamNameEdit" text as "EC_STREAM_NAME" variable
    And I remember "AutoBodyFDCodeEdit" text as "EC_BODY_FD_CODE" variable
    And I remember "AutoBodyFDDescriptionEdit" text as "EC_BODY_FD_DESCRIPTION" variable
    And I remember "AutoVerticalNameEdit" text as "EC_VERTICAL_NAME" variable
    And I remember "AutoBodyShortNameEdit" text as "EC_BODY_SHORT_NAME" variable
    And I remember "AutoBodyNameEdit" text as "EC_BODY_NAME" variable
    And I remember "AutoVatRuleCodeEdit" text as "EC_VAT_RULE_CODE" variable
    And I remember "AutoVatRuleDescriptionEdit" text as "EC_VAT_RULE_DESCRIPTION" variable
    And I remember "AutoCourseTypeFDCodeEdit" text as "EC_COURSE_TYPE_FD_CODE" variable
    And I remember "AutoCourseTypeFDDescriptionEdit" text as "EC_COURSE_TYPE_FD_DESCRIPTION" variable
    And I remember "AutoCourseTypeDescriptionEdit" text as "EC_COURSE_TYPE_DESCRIPTION" variable
    And I remember "AutoLocationFDCodeEdit" text as "EC_LOCATION_FD_CODE" variable
    And I remember "AutoLocationFDDescriptionEdit" text as "EC_LOCATION_FD_DESCRIPTION" variable
    And I remember "AutoRegionFDCodeEdit" text as "EC_REGION_FD_CODE" variable
    And I remember "AutoRegionFDDescriptionEdit" text as "EC_REGION_FD_DESCRIPTION" variable
    And I remember "AutoRegionNameEdit" text as "EC_REGION_NAME" variable
    And I remember "AutoLocationNameEdit" text as "EC_LOCATION_NAME" variable
    And I remember "AutoLocationAddressEdit" text as "EC_LOCATION_ADDRESS" variable
    And I remember "AutoLevelNameEdit" text as "EC_LEVEL_NAME" variable
    And I remember "AutoLevelShortNameEdit" text as "EC_LEVEL_SHORT_NAME" variable
    And I remember "EditPprSN" text as "EC_PPR_SN" variable
    And I remember "AutoPaperNameEdit" text as "EC_PAPER_NAME" variable
    And I remember "AutoPaperDescriptionEdit" text as "EC_PAPER_DESCRIPTION" variable
    And I remember "EdCBAPprSN" text as "EC_CBA_PPR_SN" variable
    And I remember "AutoCBAPaperNameEdit" text as "EC_CBA_PAPER_NAME" variable
    And I remember "AutoCBAPaperDescriptionEdit" text as "EC_CBA_PAPER_DESCRIPTION" variable
    And I remember "AutoSittingNameEdit" text as "EC_SITTING_NAME" variable
    And I remember "AutoMaterialTypeFDCodeEdit" text as "EC_MATERIAL_TYPE_FD_CODE" variable
    And I remember "AutoMaterialTypeFDDescriptionEdit" text as "EC_MATERIAL_TYPE_FD_DESCRIPTION" variable
    And I remember "AutoMaterialTypeNameEdit" text as "EC_MATERIAL_TYPE_NAME" variable
    And I remember "AutoMaterialTypeDescriptionEdit" text as "EC_MATERIAL_TYPE_DESCRIPTION" variable
    And I remember "AutoSessionDurationDescriptionEdit" text as "EC_SESSION_DURATION_DESCRIPTION" variable
    And I remember "AutoCBASessionDurationDescriptionEdit" text as "EC_CBA_SESSION_DURATION_DESCRIPTION" variable
    And I remember "AutoSessionDurationDescriptionTwoDatesEdit" text as "EC_SESSION_DURATION_DESCRIPTION_TWO_DATES" variable
    And I remember "AutoClientNameEdit" text as "EC_CLIENT_NAME" variable
    And I remember "2000" text as "EC_PRICE" variable
    And I remember "10" text as "EC_WEIGHT" variable
    And I remember "1000" text as "EC_PRICING_MATRIX_PRICE" variable

    #_________________________________Create Reference Data Set For Editing____________________________
  @CreateData
  Scenario: Create Reference Data Set For Editing
    Given I execute "Create Deactivation Reason" reusable step replacing some steps
      |4|I set "$Auto[DeactivationReasonDescription]$Edit" text to the "Description" "Product Factory text field"|
    And I execute modified "Create Deactivation Reason" reusable step
      |4|Replace|I set "$Auto[DeactivationReasonDescriptionPrevent]$Edit" text to the "Description" "Product Factory text field"|
      |5|Add|I click on the "Prevent Reactivation" "Product Factory checkbox"|
      |7|Replace|I should see the "EC_DEACTIVATION_REASON_DESCRIPTION_PREVENT" element|
    And I execute "Create Stream" reusable step replacing some steps
      |4|I set "$Auto[StreamName]$Edit" text to the "Name" "Product Factory text field"|
    When I execute "Create Body Financial Dimension" reusable step replacing some steps
      |4|I set "$Auto[BodyFDCode]$Edit" text to the "Code" "Product Factory text field"|
      |5|I set "$Auto[BodyFDDescription]$Edit" text to the "Description" "Product Factory text field"|
    And I execute "Create Vertical" reusable step replacing some steps
      |4|I set "$Auto[VerticalName]$Edit" text to the "Name" "Product Factory text field"|
    And I execute "Create Body" reusable step replacing some steps
      |4|I set "$Auto[BodyShortName]$Edit" text to the "Short Name" "Product Factory text field"|
      |5|I set "$Auto[BodyName]$Edit" text to the "Name" "Product Factory text field"|
    And I execute "Create VAT Rule" reusable step replacing some steps
      |4|I set "$Auto[VatRuleCode]$Edit" text to the "Code" "Product Factory text field"|
      |5|I set "$Auto[VatRuleDescription]$Edit" text to the "Description" "Product Factory text field"|
    And I execute "Create Course Type Financial Dimension" reusable step replacing some steps
      |4|I set "$Auto[CourseTypeFDCode]$Edit" text to the "Code" "Product Factory text field"|
      |5|I set "$Auto[CourseTypeFDDescription]$Edit" text to the "Description" "Product Factory text field"|
    And I execute "Create Course Type" reusable step replacing some steps
      |4|I set "$Auto[CourseTypeDescription]$Edit" text to the "Description" "Product Factory text field"|
    And I execute "Create Location Financial Dimension" reusable step replacing some steps
      |4|I set "$Auto[LocationFDCode]$Edit" text to the "Code" "Product Factory text field"|
      |5|I set "$Auto[LocationFDDescription]$Edit" text to the "Description" "Product Factory text field"|
    And I execute "Create Region Financial Dimension" reusable step replacing some steps
      |4|I set "$Auto[RegionFDCode]$Edit" text to the "Code" "Product Factory text field"|
      |5|I set "$Auto[RegionFDDescription]$Edit" text to the "Description" "Product Factory text field"|
    And I execute "Create Region" reusable step replacing some steps
      |4|I set "$Auto[RegionName]$Edit" text to the "Name" "Product Factory text field"|
    And I execute "Create Location" reusable step replacing some steps
      |4|I set "$Auto[LocationName]$Edit" text to the "Name" "Product Factory text field"|
      |5|I set "$Auto[LocationAddress]$Edit" text to the "Address Line 1" "Product Factory text field"|
    And I execute "Create Level" reusable step replacing some steps
      |4|I set "$Auto[LevelShortName]$Edit" text to the "Short Name" "Product Factory text field"|
      |5|I set "$Auto[LevelName]$Edit" text to the "Name" "Product Factory text field"|
    And I execute "Create Paper" reusable step replacing some steps
      |4|I set "$Edit[PprSN]" text to the "Short Name" "Product Factory text field"|
      |5|I set "$Auto[PaperName]$Edit" text to the "Name" "Product Factory text field"|
      |6|I set "$Auto[PaperDescription]$Edit" text to the "Description" "Product Factory text field"|
    And I execute modified "Create Paper" reusable step
      |4|Replace|I set "$Ed[CBAPprSN]" text to the "Short Name" "Product Factory text field"|
      |5|Add    |I "check" "CBA Paper?" "Product Factory checkbox"|
      |6|Add    |I set "1" text to the "Time in Hours" "Product Factory text field"|
      |7|Replace|I set "$Auto[CBAPaperName]$Edit" text to the "Name" "Product Factory text field"|
      |8|Replace|I set "$Auto[CBAPaperDescription]$Edit" text to the "Description" "Product Factory text field"|
    And I execute "Link Body To Level" reusable step
    And I execute "Link Body To Paper" reusable step
    And I execute "Link Paper To Level" reusable step
    And I execute "Link Body To Paper" reusable step replacing some steps
      |3|I set "EC_CBA_PAPER_DESCRIPTION" text to the "Search" "Product Factory text field" from keyboard|
      |5|I click on the "EC_CBA_PAPER_NAME" "Product Factory Change Body button"|
    And I execute "Link Paper To Level" reusable step replacing some steps
      |3|I set "EC_CBA_PAPER_DESCRIPTION" text to the "Search" "Product Factory text field" from keyboard|
      |5|I click on the "EC_CBA_PAPER_NAME" "Product Factory Link Levels button"|
    And I execute "Create Sitting" reusable step replacing some steps
      |4|I set "$Auto[SittingName]$Edit" text to the "Name" "Product Factory text field"|
    And I execute "Create Pricing Matrix" reusable step
    And I execute "Create Pricing Matrix Full" reusable step replacing some steps
      |12|I set "~PricingMatrixPrice[1000]" text to the "Enter a Price (£)" "Product Factory text field"|
    And I execute "Create Pricing Matrix Full" reusable step replacing some steps
      |10|I click on the "EC_CBA_PAPER_NAME" "Product Factory dialog checkbox"|
      |12|I set "~PricingMatrixPrice[1000]" text to the "Enter a Price (£)" "Product Factory text field"|
    And I execute "Create Material Type Financial Dimension" reusable step replacing some steps
      |4|I set "$Auto[MaterialTypeFDCode]$Edit" text to the "Code" "Product Factory text field"|
      |5|I set "$Auto[MaterialTypeFDDescription]$Edit" text to the "Description" "Product Factory text field"|
    And I execute "Create Material Type" reusable step replacing some steps
      |4|I set "$Auto[MaterialTypeName]$Edit" text to the "Name" "Product Factory text field"|
      |5|I set "$Auto[MaterialTypeDescription]$Edit" text to the "Description" "Product Factory text field"|
    And I execute "Create Session Duration" reusable step replacing some steps
      |4|I set "$Auto[SessionDurationDescription]$Edit" text to the "Description" "Product Factory text field"|
    And I execute modified "Create Session Duration" reusable step
      |4|Replace|I set "$Auto[CBASessionDurationDescription]$Edit" text to the "Description" "Product Factory text field"|
      |6|Add    |I "check" "Allowed for CBA" "Product Factory checkbox"|
      |8|Replace|I should see the "EC_CBA_SESSION_DURATION_DESCRIPTION" element|
    And I execute "Create Session Duration" reusable step replacing some steps
      |4|I set "$Auto[SessionDurationDescriptionTwoDates]$Edit" text to the "Description" "Product Factory text field"|
      |5|I set "2" text to the "Number of Dates" "Product Factory text field"|
    And I execute "Create Client" reusable step replacing some steps
      |4|I set "$Auto[ClientName]$Edit" text to the "Name" "Product Factory text field"|
    And I execute "Create Material" reusable step replacing some steps
      |6|I set "~Price[2000]" text to the "Price (£)" "Product Factory text field"|
      |7|I set "~Weight[10]" text to the "Weight (kg)" "Product Factory text field"|
    Then I execute "Create Course" reusable step
    And I execute "Create Course Instance" reusable step
    And I execute modified "Create CBA Record" reusable step
      |5|Replace|I click on the "EC_CBA_SESSION_DURATION_DESCRIPTION" "Product Factory select button"|
      |14|Replace|I should see the "EC_CBA_SESSION_DURATION_DESCRIPTION" element                         |
    And I execute modified "Create CBA Course" reusable step
      |11|Replace|I should see the "EC_CBA_PAPER_NAME" element|
    And I execute "Create Digital Content" reusable step

  @RenewData @Runn
  Scenario: Renew Reference Data Set For Editing
    When I click on the "Miscellaneous" "Product Factory navigation item"
    When I click on the "Deactivation Reasons" "Product Factory navigation sub item"
#    Then I set "AutoDeactivationReasonDescriptionEdit" text to the "Search" "Product Factory text field" from keyboard
#    And I click on the "submit" "element by type"
    And I remember "AutoDeactivationReasonDescriptionEdit" text as "EC_DEACTIVATION_REASON_DESCRIPTION" variable
    Given I execute modified "Create Deactivation Reason" reusable step if "AutoDeactivationReasonDescriptionEdit" "element is absent"
      |4|Replace|I set "$Auto[DeactivationReasonDescription]$Edit" text to the "Description" "Product Factory text field"|

    When I click on the "Miscellaneous" "Product Factory navigation item"
    When I click on the "Deactivation Reasons" "Product Factory navigation sub item"
#    Then I set "AutoDeactivationReasonDescriptionPreventEdit" text to the "Search" "Product Factory text field" from keyboard
#    And I click on the "submit" "element by type"
    And I remember "AutoDeactivationReasonDescriptionPreventEdit" text as "EC_DEACTIVATION_REASON_DESCRIPTION_PREVENT" variable
    And I execute modified "Create Deactivation Reason" reusable step if "AutoDeactivationReasonDescriptionPreventEdit" "element is absent"
      |4|Replace|I set "$Auto[DeactivationReasonDescriptionPrevent]$Edit" text to the "Description" "Product Factory text field"|
      |5|Add|I click on the "Prevent Reactivation" "Product Factory checkbox"|
      |7|Replace|I should see the "EC_DEACTIVATION_REASON_DESCRIPTION_PREVENT" element|

    When I click on the "Miscellaneous" "Product Factory navigation item"
    When I click on the "Streams" "Product Factory navigation sub item"
#    Then I set "AutoStreamNameEdit" text to the "Search" "Product Factory text field" from keyboard
#    And I click on the "submit" "element by type"
    And I remember "AutoStreamNameEdit" text as "EC_STREAM_NAME" variable
    And I execute modified "Create Stream" reusable step if "AutoStreamNameEdit" "element is absent"
      |4|Replace|I set "$Auto[StreamName]$Edit" text to the "Name" "Product Factory text field"|

    When I click on the "Financials" "Product Factory navigation item"
    When I click on the "Financial Dimensions" "Product Factory navigation sub item"
#    Then I set "AutoBodyFDCodeEdit" text to the "Search" "Product Factory text field" from keyboard
#    And I click on the "submit" "element by type"
    And I remember "AutoBodyFDCodeEdit" text as "EC_BODY_FD_CODE" variable
    And I remember "AutoBodyFDDescriptionEdit" text as "EC_BODY_FD_DESCRIPTION" variable
    When I execute modified "Create Body Financial Dimension" reusable step if "AutoBodyFDCodeEdit" "element is absent"
      |4|Replace|I set "$Auto[BodyFDCode]$Edit" text to the "Code" "Product Factory text field"|
      |5|Replace|I set "$Auto[BodyFDDescription]$Edit" text to the "Description" "Product Factory text field"|

    When I click on the "Miscellaneous" "Product Factory navigation item"
    When I click on the "Streams" "Product Factory navigation sub item"
    And I remember "AutoVerticalNameEdit" text as "EC_VERTICAL_NAME_CODE" variable
    And I execute modified "Create Vertical" reusable step if "AutoBodyFDCodeEdit" "element is absent"
      |4|Replace|I set "$Auto[VerticalName]" text to the "Name" "Product Factory text field"|

    When I click on the "Programme" "Product Factory navigation item"
    When I click on the "Bodies" "Product Factory navigation sub item"
    Then I set "AutoBodyShortNameEdit" text to the "Search" "Product Factory text field" from keyboard
    And I click on the "submit" "element by type"
    And I remember "AutoBodyShortNameEdit" text as "EC_BODY_SHORT_NAME" variable
    And I remember "AutoBodyNameEdit" text as "EC_BODY_NAME" variable
    And I execute modified "Create Body" reusable step if "AutoBodyShortNameEdit" "element is absent"
      |4|Replace|I set "$Auto[BodyShortName]$Edit" text to the "Short Name" "Product Factory text field"|
      |5|Replace|I set "$Auto[BodyName]$Edit" text to the "Name" "Product Factory text field"|

    When I click on the "Financials" "Product Factory navigation item"
    When I click on the "VAT Rules" "Product Factory navigation sub item"
#    Then I set "AutoVatRuleCodeEdit" text to the "Search" "Product Factory text field" from keyboard
#    And I click on the "submit" "element by type"
    And I remember "AutoVatRuleCodeEdit" text as "EC_VAT_RULE_CODE" variable
    And I remember "AutoVatRuleDescriptionEdit" text as "EC_VAT_RULE_DESCRIPTION" variable
    And I execute modified "Create VAT Rule" reusable step if "AutoVatRuleCodeEdit" "element is absent"
      |4|Replace|I set "$Auto[VatRuleCode]$Edit" text to the "Code" "Product Factory text field"|
      |5|Replace|I set "$Auto[VatRuleDescription]$Edit" text to the "Description" "Product Factory text field"|

    When I click on the "Financials" "Product Factory navigation item"
    When I click on the "Financial Dimensions" "Product Factory navigation sub item"
#    Then I set "AutoCourseTypeFDCodeEdit" text to the "Search" "Product Factory text field" from keyboard
#    And I click on the "submit" "element by type"
    And I remember "AutoCourseTypeFDCodeEdit" text as "EC_COURSE_TYPE_FD_CODE" variable
    And I remember "AutoCourseTypeFDDescriptionEdit" text as "EC_COURSE_TYPE_FD_DESCRIPTION" variable
    And I execute modified "Create Course Type Financial Dimension" reusable step if "AutoCourseTypeFDCodeEdit" "element is absent"
      |4|Replace|I set "$Auto[CourseTypeFDCode]$Edit" text to the "Code" "Product Factory text field"|
      |5|Replace|I set "$Auto[CourseTypeFDDescription]$Edit" text to the "Description" "Product Factory text field"|

    When I click on the "Types" "Product Factory navigation item"
    When I click on the "Course Types" "Product Factory navigation sub item"
    Then I set "AutoCourseTypeDescriptionEdit" text to the "Search" "Product Factory text field" from keyboard
    And I click on the "submit" "element by type"
    And I remember "AutoCourseTypeDescriptionEdit" text as "EC_COURSE_TYPE_DESCRIPTION" variable
    And I execute modified "Create Course Type" reusable step if "AutoCourseTypeDescriptionEdit" "element is absent"
      |4|Replace|I set "$Auto[CourseTypeDescription]$Edit" text to the "Description" "Product Factory text field"|

    When I click on the "Financials" "Product Factory navigation item"
    When I click on the "Financial Dimensions" "Product Factory navigation sub item"
#    Then I set "AutoLocationFDCodeEdit" text to the "Search" "Product Factory text field" from keyboard
#    And I click on the "submit" "element by type"
    And I remember "AutoLocationFDCodeEdit" text as "EC_LOCATION_FD_CODE" variable
    And I remember "AutoLocationFDDescriptionEdit" text as "EC_LOCATION_FD_DESCRIPTION" variable
    And I execute modified "Create Location Financial Dimension" reusable step if "AutoLocationFDCodeEdit" "element is absent"
      |4|Replace|I set "$Auto[LocationFDCode]$Edit" text to the "Code" "Product Factory text field"|
      |5|Replace|I set "$Auto[LocationFDDescription]$Edit" text to the "Description" "Product Factory text field"|

    When I click on the "Financials" "Product Factory navigation item"
    When I click on the "Financial Dimensions" "Product Factory navigation sub item"
#    Then I set "AutoRegionFDCodeEdit" text to the "Search" "Product Factory text field" from keyboard
#    And I click on the "submit" "element by type"
    And I remember "AutoRegionFDCodeEdit" text as "EC_REGION_FD_CODE" variable
    And I remember "AutoRegionFDDescriptionEdit" text as "EC_REGION_FD_DESCRIPTION" variable
    And I execute modified "Create Region Financial Dimension" reusable step if "AutoRegionFDCodeEdit" "element is absent"
      |4|Replace|I set "$Auto[RegionFDCode]$Edit" text to the "Code" "Product Factory text field"|
      |5|Replace|I set "$Auto[RegionFDDescription]$Edit" text to the "Description" "Product Factory text field"|

    When I click on the "Delivery" "Product Factory navigation item"
    When I click on the "Region" "Product Factory navigation sub item"
#    Then I set "AutoRegionNameEdit" text to the "Search" "Product Factory text field" from keyboard
#    And I click on the "submit" "element by type"
    And I remember "AutoRegionNameEdit" text as "EC_REGION_NAME" variable
    And I execute modified "Create Region" reusable step if "AutoRegionNameEdit" "element is absent"
      |4|Replace|I set "$Auto[RegionName]$Edit" text to the "Name" "Product Factory text field"|

    When I click on the "Delivery" "Product Factory navigation item"
    When I click on the "Locations" "Product Factory navigation sub item"
#    Then I set "AutoLocationNameEdit" text to the "Search" "Product Factory text field" from keyboard
#    And I click on the "submit" "element by type"
    And I remember "AutoLocationNameEdit" text as "EC_LOCATION_NAME" variable
    And I remember "AutoLocationAddressEdit" text as "EC_LOCATION_ADDRESS" variable
    And I execute modified "Create Location" reusable step if "AutoLocationNameEdit" "element is absent"
      |4|Replace|I set "$Auto[LocationName]$Edit" text to the "Name" "Product Factory text field"|
      |5|Replace|I set "$Auto[LocationAddress]$Edit" text to the "Address Line 1" "Product Factory text field"|

    When I click on the "Programme" "Product Factory navigation item"
    When I click on the "Levels" "Product Factory navigation sub item"
#    Then I set "AutoLevelShortNameEdit" text to the "Search" "Product Factory text field" from keyboard
#    And I click on the "submit" "element by type"
    And I remember "AutoLevelNameEdit" text as "EC_LEVEL_NAME" variable
    And I remember "AutoLevelShortNameEdit" text as "EC_LEVEL_SHORT_NAME" variable
    And I execute modified "Create Level" reusable step if "AutoLevelShortNameEdit" "element is absent"
      |4|Replace|I set "$Auto[LevelShortName]$Edit" text to the "Short Name" "Product Factory text field"|
      |5|Replace|I set "$Auto[LevelName]$Edit" text to the "Name" "Product Factory text field"|

    When I click on the "Programme" "Product Factory navigation item"
    When I click on the "Papers" "Product Factory navigation sub item"
    Then I set "AutoPaperNameEdit" text to the "Search" "Product Factory text field" from keyboard
    And I click on the "submit" "element by type"
    And I remember "EditPprSN" text as "EC_PPR_SN" variable
    And I remember "AutoPaperNameEdit" text as "EC_PAPER_NAME" variable
    And I remember "AutoPaperDescriptionEdit" text as "EC_PAPER_DESCRIPTION" variable
    And I execute modified "Create Paper" reusable step if "AutoPaperNameEdit" "element is absent"
      |4|Replace|I set "$Edit[PprSN]" text to the "Short Name" "Product Factory text field"|
      |5|Replace|I set "$Auto[PaperName]$Edit" text to the "Name" "Product Factory text field"|
      |6|Replace|I set "$Auto[PaperDescription]$Edit" text to the "Description" "Product Factory text field"|

    When I click on the "Programme" "Product Factory navigation item"
    When I click on the "Papers" "Product Factory navigation sub item"
    Then I set "AutoCBAPaperNameEdit" text to the "Search" "Product Factory text field" from keyboard
    And I click on the "submit" "element by type"
    And I remember "EdCBAPprSN" text as "EC_CBA_PPR_SN" variable
    And I remember "AutoCBAPaperNameEdit" text as "EC_CBA_PAPER_NAME" variable
    And I remember "AutoCBAPaperDescriptionEdit" text as "EC_CBA_PAPER_DESCRIPTION" variable
    And I execute modified "Create Paper" reusable step if "AutoCBAPaperNameEdit" "element is absent"
      |4|Replace|I set "$Ed[CBAPprSN]" text to the "Short Name" "Product Factory text field"|
      |5|Add    |I "check" "CBA Paper?" "Product Factory checkbox"|
      |6|Add    |I set "1" text to the "Time in Hours" "Product Factory text field"|
      |7|Replace|I set "$Auto[CBAPaperName]$Edit" text to the "Name" "Product Factory text field"|
      |8|Replace|I set "$Auto[CBAPaperDescription]$Edit" text to the "Description" "Product Factory text field"|


    And I execute "Link Body To Level" reusable step
    And I execute "Link Body To Paper" reusable step
    And I execute "Link Paper To Level" reusable step
    And I execute "Link Body To Paper" reusable step replacing some steps
      |3|I set "EC_CBA_PAPER_DESCRIPTION" text to the "Search" "Product Factory text field" from keyboard|
      |5|I click on the "EC_CBA_PAPER_NAME" "Product Factory Change Body button"|
    And I execute "Link Paper To Level" reusable step replacing some steps
      |3|I set "EC_CBA_PAPER_DESCRIPTION" text to the "Search" "Product Factory text field" from keyboard|
      |5|I click on the "EC_CBA_PAPER_NAME" "Product Factory Link Levels button"|

    When I click on the "Delivery" "Product Factory navigation item"
    When I click on the "Sittings" "Product Factory navigation sub item"
    And I remember "AutoSittingNameEdit" text as "EC_SITTING_NAME" variable
    And I execute modified "Create Sitting" reusable step if "AutoSittingNameEdit" "element is absent"
      |4|I set "$Auto[SittingName]$Edit" text to the "Name" "Product Factory text field"|

    And I execute "Create Pricing Matrix" reusable step
    And I execute "Create Pricing Matrix Full" reusable step replacing some steps
      |12|I set "~PricingMatrixPrice[1000]" text to the "Enter a Price (£)" "Product Factory text field"|
    And I execute "Create Pricing Matrix Full" reusable step replacing some steps
      |10|I click on the "EC_CBA_PAPER_NAME" "Product Factory dialog checkbox"|
      |12|I set "~PricingMatrixPrice[1000]" text to the "Enter a Price (£)" "Product Factory text field"|

    When I click on the "Financials" "Product Factory navigation item"
    When I click on the "Financial Dimensions" "Product Factory navigation sub item"
#    Then I set "AutoMaterialTypeFDCodeEdit" text to the "Search" "Product Factory text field" from keyboard
#    And I click on the "submit" "element by type"
    And I remember "AutoMaterialTypeFDCodeEdit" text as "EC_MATERIAL_TYPE_FD_CODE" variable
    And I remember "AutoMaterialTypeFDDescriptionEdit" text as "EC_MATERIAL_TYPE_FD_DESCRIPTION" variable
    And I execute modified "Create Material Type Financial Dimension" reusable step if "AutoMaterialTypeFDCodeEdit" "element is absent"
      |4|Replace|I set "$Auto[MaterialTypeFDCode]$Edit" text to the "Code" "Product Factory text field"|
      |5|Replace|I set "$Auto[MaterialTypeFDDescription]$Edit" text to the "Description" "Product Factory text field"|

    When I click on the "Types" "Product Factory navigation item"
    When I click on the "Material Types" "Product Factory navigation sub item"
#    Then I set "AutoMaterialTypeNameEdit" text to the "Search" "Product Factory text field" from keyboard
#    And I click on the "submit" "element by type"
    And I remember "AutoMaterialTypeNameEdit" text as "EC_MATERIAL_TYPE_NAME" variable
    And I remember "AutoMaterialTypeDescriptionEdit" text as "EC_MATERIAL_TYPE_DESCRIPTION" variable
    And I execute modified "Create Material Type" reusable step if "AutoMaterialTypeNameEdit" "element is absent"
      |4|Replace|I set "$Auto[MaterialTypeName]$Edit" text to the "Name" "Product Factory text field"|
      |5|Replace|I set "$Auto[MaterialTypeDescription]$Edit" text to the "Description" "Product Factory text field"|

    When I click on the "Delivery" "Product Factory navigation item"
    When I click on the "Session Duration" "Product Factory navigation sub item"
#    Then I set "AutoSessionDurationDescriptionEdit" text to the "Search" "Product Factory text field" from keyboard
#    And I click on the "submit" "element by type"
    And I remember "AutoSessionDurationDescriptionEdit" text as "EC_SESSION_DURATION_DESCRIPTION" variable
    And I execute modified "Create Session Duration" reusable step if "AutoSessionDurationDescriptionEdit" "element is absent"
      |4|Replace|I set "$Auto[SessionDurationDescription]$Edit" text to the "Description" "Product Factory text field"|

    When I click on the "Delivery" "Product Factory navigation item"
    When I click on the "Session Duration" "Product Factory navigation sub item"
#    Then I set "AutoCBASessionDurationDescriptionEdit" text to the "Search" "Product Factory text field" from keyboard
#    And I click on the "submit" "element by type"
    And I remember "AutoCBASessionDurationDescriptionEdit" text as "EC_CBA_SESSION_DURATION_DESCRIPTION" variable
    And I execute modified "Create Session Duration" reusable step if "AutoCBASessionDurationDescriptionEdit" "element is absent"
      |4|Replace|I set "$Auto[CBASessionDurationDescription]$Edit" text to the "Description" "Product Factory text field"|
      |6|Add    |I "check" "Allowed for CBA" "Product Factory checkbox"|
      |8|Replace|I should see the "EC_CBA_SESSION_DURATION_DESCRIPTION" element|

    When I click on the "Delivery" "Product Factory navigation item"
    When I click on the "Session Duration" "Product Factory navigation sub item"
#    Then I set "AutoSessionDurationDescriptionTwoDatesEdit" text to the "Search" "Product Factory text field" from keyboard
#    And I click on the "submit" "element by type"
    And I remember "AutoSessionDurationDescriptionTwoDatesEdit" text as "EC_SESSION_DURATION_DESCRIPTION_TWO_DATES" variable
    And I execute modified "Create Session Duration" reusable step if "AutoSessionDurationDescriptionTwoDatesEdit" "element is absent"
      |4|Replace|I set "$Auto[SessionDurationDescriptionTwoDates]$Edit" text to the "Description" "Product Factory text field"|
      |5|Replace|I set "2" text to the "Number of Dates" "Product Factory text field"|

    When I click on the "Miscellaneous" "Product Factory navigation item"
    When I click on the "Clients" "Product Factory navigation sub item"
#    Then I set "AutoClientNameEdit" text to the "Search" "Product Factory text field" from keyboard
#    And I click on the "submit" "element by type"
    And I remember "AutoClientNameEdit" text as "EC_CLIENT_NAME" variable
    And I execute modified "Create Client" reusable step if "AutoClientNameEdit" "element is absent"
      |4|Replace|I set "$Auto[ClientName]$Edit" text to the "Name" "Product Factory text field"|

    And I execute "Create Material" reusable step replacing some steps
      |6|I set "~Price[2000]" text to the "Price (£)" "Product Factory text field"|
      |7|I set "~Weight[10]" text to the "Weight (kg)" "Product Factory text field"|
    Then I execute "Create Course" reusable step
    And I execute "Create Course Instance" reusable step
    And I execute modified "Create CBA Record" reusable step
      |5|Replace|I click on the "EC_CBA_SESSION_DURATION_DESCRIPTION" "Product Factory select button"|
      |14|Replace|I should see the "EC_CBA_SESSION_DURATION_DESCRIPTION" element                         |
    And I execute modified "Create CBA Course" reusable step
      |11|Replace|I should see the "EC_CBA_PAPER_NAME" element|
    And I execute "Create Digital Content" reusable step

              #______________________________Remember Variables For Draft CBA__________________________
  Scenario: Remember Variables For Draft CBA
    And I remember "AutoDeactivationReasonDescriptionDraftCBA" text as "EC_DEACTIVATION_REASON_DESCRIPTION" variable
    And I remember "AutoDeactivationReasonDescriptionPreventDraftCBA" text as "EC_DEACTIVATION_REASON_DESCRIPTION_PREVENT" variable
    And I remember "23/10/2020" text as "EC_SITTING_START_DATE" variable
    And I remember "10/10/2025" text as "EC_SITTING_END_DATE" variable
    And I remember "AutoStreamNameDraftCBA" text as "EC_STREAM_NAME" variable
    And I remember "AutoBodyFDCodeDraftCBA" text as "EC_BODY_FD_CODE" variable
    And I remember "AutoBodyFDDescriptionDraftCBA" text as "EC_BODY_FD_DESCRIPTION" variable
    And I remember "AutoVerticalNameDraftCBA" text as "EC_VERTICAL_NAME" variable
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
    And I remember "AutoLevelShortNameDraftCBA" text as "EC_LEVEL_SHORT_NAME" variable
    And I remember "DrCBAPprSN" text as "EC_CBA_PPR_SN" variable
    And I remember "DraftCBAPaperName" text as "EC_CBA_PAPER_NAME" variable
    And I remember "DraftCBAPaperDescription" text as "EC_CBA_PAPER_DESCRIPTION" variable
    And I remember "AutoSittingNameDraftCBA" text as "EC_SITTING_NAME" variable
    And I remember "AutoMaterialTypeFDCodeDraftCBA" text as "EC_MATERIAL_TYPE_FD_CODE" variable
    And I remember "AutoMaterialTypeFDDescriptionDraftCBA" text as "EC_MATERIAL_TYPE_FD_DESCRIPTION" variable
    And I remember "AutoMaterialTypeNameDraftCBA" text as "EC_MATERIAL_TYPE_NAME" variable
    And I remember "AutoMaterialTypeDescriptionDraftCBA" text as "EC_MATERIAL_TYPE_DESCRIPTION" variable
    And I remember "DraftCBASessionDurationDescription" text as "EC_CBA_SESSION_DURATION_DESCRIPTION" variable
    And I remember "AutoClientNameDraftCBA" text as "EC_CLIENT_NAME" variable
    And I remember "2000" text as "EC_PRICE" variable
    And I remember "10" text as "EC_WEIGHT" variable
    And I remember "1000" text as "EC_PRICING_MATRIX_PRICE" variable

  #_________________________________Create Reference Data Set ____________________________
  @CreateData
  Scenario: Create Draft CBA
    Given I execute "Create Deactivation Reason" reusable step replacing some steps
      |4|I set "$Auto[DeactivationReasonDescription]$DraftCBA" text to the "Description" "Product Factory text field"|
    And I execute modified "Create Deactivation Reason" reusable step
      |4|Replace|I set "$Auto[DeactivationReasonDescriptionPrevent]$DraftCBA" text to the "Description" "Product Factory text field"|
      |5|Add|I click on the "Prevent Reactivation" "Product Factory checkbox"|
      |7|Replace|I should see the "EC_DEACTIVATION_REASON_DESCRIPTION_PREVENT" element|
    And I execute "Create Stream" reusable step replacing some steps
      |4|I set "$Auto[StreamName]$DraftCBA" text to the "Name" "Product Factory text field"|
    When I execute "Create Body Financial Dimension" reusable step replacing some steps
      |4|I set "$Auto[BodyFDCode]$DraftCBA" text to the "Code" "Product Factory text field"|
      |5|I set "$Auto[BodyFDDescription]$DraftCBA" text to the "Description" "Product Factory text field"|
    And I execute "Create Body" reusable step replacing some steps
      |4|I set "$Auto[BodyShortName]$DraftCBA" text to the "Short Name" "Product Factory text field"|
      |5|I set "$Auto[BodyName]$DraftCBA" text to the "Name" "Product Factory text field"|
    And I execute "Create VAT Rule" reusable step replacing some steps
      |4|I set "$Auto[VatRuleCode]$DraftCBA" text to the "Code" "Product Factory text field"|
      |5|I set "$Auto[VatRuleDescription]$DraftCBA" text to the "Description" "Product Factory text field"|
    And I execute "Create Course Type Financial Dimension" reusable step replacing some steps
      |4|I set "$Auto[CourseTypeFDCode]$DraftCBA" text to the "Code" "Product Factory text field"|
      |5|I set "$Auto[CourseTypeFDDescription]$DraftCBA" text to the "Description" "Product Factory text field"|
    And I execute "Create Course Type" reusable step replacing some steps
      |4|I set "$Auto[CourseTypeDescription]$DraftCBA" text to the "Description" "Product Factory text field"|
    And I execute "Create Location Financial Dimension" reusable step replacing some steps
      |4|I set "$Auto[LocationFDCode]$DraftCBA" text to the "Code" "Product Factory text field"|
      |5|I set "$Auto[LocationFDDescription]$DraftCBA" text to the "Description" "Product Factory text field"|
    And I execute "Create Region Financial Dimension" reusable step replacing some steps
      |4|I set "$Auto[RegionFDCode]$DraftCBA" text to the "Code" "Product Factory text field"|
      |5|I set "$Auto[RegionFDDescription]$DraftCBA" text to the "Description" "Product Factory text field"|
    And I execute "Create Region" reusable step replacing some steps
      |4|I set "$Auto[RegionName]$DraftCBA" text to the "Name" "Product Factory text field"|
    And I execute "Create Location" reusable step replacing some steps
      |4|I set "$Auto[LocationName]$DraftCBA" text to the "Name" "Product Factory text field"|
      |5|I set "$Auto[LocationAddress]$DraftCBA" text to the "Address Line 1" "Product Factory text field"|
    And I execute "Create Level" reusable step replacing some steps
      |4|I set "$Auto[LevelShortName]$DraftCBA" text to the "Short Name" "Product Factory text field"|
      |5|I set "$Auto[LevelName]$DraftCBA" text to the "Name" "Product Factory text field"|
    And I execute modified "Create Paper" reusable step
      |4|Replace|I set "$Dr[CBAPprSN]" text to the "Short Name" "Product Factory text field"|
      |5|Add    |I "check" "CBA Paper?" "Product Factory checkbox"|
      |6|Add    |I set "1" text to the "Time in Hours" "Product Factory text field"|
      |7|Replace|I set "$Draft[CBAPaperName]" text to the "Name" "Product Factory text field"|
      |8|Replace|I set "$Draft[CBAPaperDescription]" text to the "Description" "Product Factory text field"|
      |12|Replace|I set "EC_CBA_PAPER_DESCRIPTION" text to the "Search" "Product Factory text field" from keyboard|
      |14|Replace|I should see the "EC_CBA_PAPER_NAME" element|
    And I execute "Link Body To Level" reusable step
    And I execute "Link Body To Paper" reusable step replacing some steps
      |3|I set "EC_CBA_PAPER_DESCRIPTION" text to the "Search" "Product Factory text field" from keyboard|
      |5|I click on the "EC_CBA_PAPER_NAME" "Product Factory Change Body button"|
    And I execute "Link Paper To Level" reusable step replacing some steps
      |3|I set "EC_CBA_PAPER_DESCRIPTION" text to the "Search" "Product Factory text field" from keyboard|
      |5|I click on the "EC_CBA_PAPER_NAME" "Product Factory Link Levels button"|
    And I execute "Create Sitting" reusable step replacing some steps
      |4|I set "$Auto[SittingName]$DraftCBA" text to the "Name" "Product Factory text field"|
    And I execute "Create Pricing Matrix" reusable step
    And I execute "Create Pricing Matrix Full" reusable step replacing some steps
      |10|I click on the "EC_CBA_PAPER_NAME" "Product Factory dialog checkbox"|
      |12|I set "~PricingMatrixPrice[1000]" text to the "Enter a Price (£)" "Product Factory text field"|
    And I execute "Create Material Type Financial Dimension" reusable step replacing some steps
      |4|I set "$Auto[MaterialTypeFDCode]$DraftCBA" text to the "Code" "Product Factory text field"|
      |5|I set "$Auto[MaterialTypeFDDescription]$DraftCBA" text to the "Description" "Product Factory text field"|
    And I execute "Create Material Type" reusable step replacing some steps
      |4|I set "$Auto[MaterialTypeName]$DraftCBA" text to the "Name" "Product Factory text field"|
      |5|I set "$Auto[MaterialTypeDescription]$DraftCBA" text to the "Description" "Product Factory text field"|
    And I execute modified "Create Session Duration" reusable step
      |4|Replace|I set "$Draft[CBASessionDurationDescription]" text to the "Description" "Product Factory text field"|
      |6|Add    |I "check" "Allowed for CBA" "Product Factory checkbox"|
      |8|Replace|I should see the "EC_CBA_SESSION_DURATION_DESCRIPTION" element|
    And I execute "Create Client" reusable step replacing some steps
      |4|I set "$Auto[ClientName]$DraftCBA" text to the "Name" "Product Factory text field"|
    And I execute "Create Material" reusable step replacing some steps
      |6|I set "~Price[2000]" text to the "Price (£)" "Product Factory text field"|
      |7|I set "~Weight[10]" text to the "Weight (kg)" "Product Factory text field"|
    And I execute modified "Create CBA Record" reusable step
      |5|Replace|I click on the "EC_CBA_SESSION_DURATION_DESCRIPTION" "Product Factory select button"|
      |14|Replace|I should see the "EC_CBA_SESSION_DURATION_DESCRIPTION" element                         |

               #______________________________Remember Variables For Draft CBA__________________________
  Scenario: Remember Variables For Draft Course
    And I remember "AutoDeactivationReasonDescriptionDraftCourse" text as "EC_DEACTIVATION_REASON_DESCRIPTION" variable
    And I remember "AutoDeactivationReasonDescriptionPreventDraftCourse" text as "EC_DEACTIVATION_REASON_DESCRIPTION_PREVENT" variable
    And I remember "23/10/2020" text as "EC_SITTING_START_DATE" variable
    And I remember "10/10/2025" text as "EC_SITTING_END_DATE" variable
    And I remember "AutoStreamNameDraftCourse" text as "EC_STREAM_NAME" variable
    And I remember "AutoBodyFDCodeDraftCourse" text as "EC_BODY_FD_CODE" variable
    And I remember "AutoBodyFDDescriptionDraftCourse" text as "EC_BODY_FD_DESCRIPTION" variable
    And I remember "AutoVerticalNameDraftCourse" text as "EC_VERTICAL_NAME" variable
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

  #_________________________________Create Reference Data Set ____________________________
  @CreateData
  Scenario: Create Draft Course
    Given I execute "Create Deactivation Reason" reusable step replacing some steps
      |4|I set "$Auto[DeactivationReasonDescription]$DraftCourse" text to the "Description" "Product Factory text field"|
    And I execute modified "Create Deactivation Reason" reusable step
      |4|Replace|I set "$Auto[DeactivationReasonDescriptionPrevent]$DraftCourse" text to the "Description" "Product Factory text field"|
      |5|Add|I click on the "Prevent Reactivation" "Product Factory checkbox"|
      |7|Replace|I should see the "EC_DEACTIVATION_REASON_DESCRIPTION_PREVENT" element|
    And I execute "Create Stream" reusable step replacing some steps
      |4|I set "$Auto[StreamName]$DraftCourse" text to the "Name" "Product Factory text field"|
    When I execute "Create Body Financial Dimension" reusable step replacing some steps
      |4|I set "$Auto[BodyFDCode]$DraftCourse" text to the "Code" "Product Factory text field"|
      |5|I set "$Auto[BodyFDDescription]$DraftCourse" text to the "Description" "Product Factory text field"|
    And I execute "Create Body" reusable step replacing some steps
      |4|I set "$Auto[BodyShortName]$DraftCourse" text to the "Short Name" "Product Factory text field"|
      |5|I set "$Auto[BodyName]$DraftCourse" text to the "Name" "Product Factory text field"|
    And I execute "Create VAT Rule" reusable step replacing some steps
      |4|I set "$Auto[VatRuleCode]$DraftCourse" text to the "Code" "Product Factory text field"|
      |5|I set "$Auto[VatRuleDescription]$DraftCourse" text to the "Description" "Product Factory text field"|
    And I execute "Create Course Type Financial Dimension" reusable step replacing some steps
      |4|I set "$Auto[CourseTypeFDCode]$DraftCourse" text to the "Code" "Product Factory text field"|
      |5|I set "$Auto[CourseTypeFDDescription]$DraftCourse" text to the "Description" "Product Factory text field"|
    And I execute "Create Course Type" reusable step replacing some steps
      |4|I set "$Auto[CourseTypeDescription]$DraftCourse" text to the "Description" "Product Factory text field"|
    And I execute "Create Location Financial Dimension" reusable step replacing some steps
      |4|I set "$Auto[LocationFDCode]$DraftCourse" text to the "Code" "Product Factory text field"|
      |5|I set "$Auto[LocationFDDescription]$DraftCourse" text to the "Description" "Product Factory text field"|
    And I execute "Create Region Financial Dimension" reusable step replacing some steps
      |4|I set "$Auto[RegionFDCode]$DraftCourse" text to the "Code" "Product Factory text field"|
      |5|I set "$Auto[RegionFDDescription]$DraftCourse" text to the "Description" "Product Factory text field"|
    And I execute "Create Region" reusable step replacing some steps
      |4|I set "$Auto[RegionName]$DraftCourse" text to the "Name" "Product Factory text field"|
    And I execute "Create Location" reusable step replacing some steps
      |4|I set "$Auto[LocationName]$DraftCourse" text to the "Name" "Product Factory text field"|
      |5|I set "$Auto[LocationAddress]$DraftCourse" text to the "Address Line 1" "Product Factory text field"|
    And I execute "Create Level" reusable step replacing some steps
      |4|I set "$Auto[LevelShortName]$DraftCourse" text to the "Short Name" "Product Factory text field"|
      |5|I set "$Auto[LevelName]$DraftCourse" text to the "Name" "Product Factory text field"|
    And I execute "Create Paper" reusable step replacing some steps
      |4|I set "$Draft[PprSN]" text to the "Short Name" "Product Factory text field"|
      |5|I set "$Draft[PaperName]" text to the "Name" "Product Factory text field"|
      |6|I set "$Draft[PaperDescription]" text to the "Description" "Product Factory text field"|
    And I execute "Link Body To Level" reusable step
    And I execute "Link Body To Paper" reusable step
    And I execute "Link Paper To Level" reusable step
    And I execute "Create Sitting" reusable step replacing some steps
      |4|I set "$Auto[SittingName]$DraftCourse" text to the "Name" "Product Factory text field"|
    And I execute "Create Pricing Matrix" reusable step
    And I execute "Create Pricing Matrix Full" reusable step replacing some steps
      |12|I set "~PricingMatrixPrice[1000]" text to the "Enter a Price (£)" "Product Factory text field"|
    And I execute "Create Material Type Financial Dimension" reusable step replacing some steps
      |4|I set "$Auto[MaterialTypeFDCode]$DraftCourse" text to the "Code" "Product Factory text field"|
      |5|I set "$Auto[MaterialTypeFDDescription]$DraftCourse" text to the "Description" "Product Factory text field"|
    And I execute "Create Material Type" reusable step replacing some steps
      |4|I set "$Auto[MaterialTypeName]$DraftCourse" text to the "Name" "Product Factory text field"|
      |5|I set "$Auto[MaterialTypeDescription]$DraftCourse" text to the "Description" "Product Factory text field"|
    And I execute "Create Session Duration" reusable step replacing some steps
      |4|I set "$Auto[SessionDurationDescription]$DraftCourse" text to the "Description" "Product Factory text field"|
    And I execute "Create Session Duration" reusable step replacing some steps
      |4|I set "$Auto[SessionDurationDescriptionTwoDates]DraftCourse" text to the "Description" "Product Factory text field"|
      |5|I set "2" text to the "Number of Dates" "Product Factory text field"|
    And I execute "Create Client" reusable step replacing some steps
      |4|I set "$Auto[ClientName]$DraftCourse" text to the "Name" "Product Factory text field"|
    And I execute "Create Material" reusable step replacing some steps
      |6|I set "~Price[2000]" text to the "Price (£)" "Product Factory text field"|
      |7|I set "~Weight[10]" text to the "Weight (kg)" "Product Factory text field"|
    Then I execute "Create Course" reusable step
    And I execute "Create Course Instance" reusable step
    And I execute modified "Create CBA Record" reusable step
      |5|Replace|I click on the "EC_CBA_SESSION_DURATION_DESCRIPTION" "Product Factory select button"|
      |14|Replace|I should see the "EC_CBA_SESSION_DURATION_DESCRIPTION" element                         |
    And I execute modified "Create CBA Course" reusable step
      |11|Replace|I should see the "EC_CBA_PAPER_NAME" element|
    And I execute "Create Digital Content" reusable step