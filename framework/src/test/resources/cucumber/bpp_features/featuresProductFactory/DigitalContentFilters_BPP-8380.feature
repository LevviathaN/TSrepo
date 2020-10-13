@ProductFactory @DigitalContent
Feature: Digital Content List - add Search Filter - BPP-8380
  As a Senior Product Setter
  I want to create a Digital Content Id
  so that Course(s) can be given a Digital Content Id.

  Background:
    Given I execute "Log In" reusable step
    When I execute "Create Body Financial Dimension" reusable step
    And I execute "Create Body" reusable step
    And I execute "Create VAT Rule" reusable step
    And I execute "Create Course Type Financial Dimension" reusable step
    And I execute "Create Course Type" reusable step
    And I execute "Create Location Financial Dimension" reusable step
    And I execute "Create Region Financial Dimension" reusable step
    And I execute "Create Region" reusable step
    And I execute "Create Location" reusable step
    And I execute "Create Level" reusable step
    And I execute "Create Paper" reusable step
    And I execute "Link Body To Level" reusable step
    And I execute "Link Body To Paper" reusable step
    And I execute "Link Paper To Level" reusable step
    And I execute "Create Sitting" reusable step

  @Positive @P1 #TC-3568
  Scenario: Digital Content Page Filters Validation
    When I execute "Create Body Financial Dimension" reusable step replacing some steps
      |4|I set "BodyFDCodeTwo[######]" text to the "Code" "Product Factory text field"|
      |5|I set "BodyFDDescriptionTwo[######]" text to the "Description" "Product Factory text field"|
      |9|I should see the "EC_BODY_FD_CODE_TWO" element|
    And I execute "Create Body" reusable step replacing some steps
      |4|I set "BodyShortNameTwo[######]" text to the "Short Name" "Product Factory text field"|
      |5|I set "BodyNameTwo[######]" text to the "Name" "Product Factory text field"|
      |9|I should see the "EC_BODY_SHORT_NAME_TWO" element|
    And I execute "Create VAT Rule" reusable step
    And I execute "Create Course Type Financial Dimension" reusable step replacing some steps
      |4|I set "CourseTypeFDCodeTwo[######]" text to the "Code" "Product Factory text field"|
      |5|I set "CourseTypeFDDescriptionTwo[######]" text to the "Description" "Product Factory text field"|
      |9|I should see the "EC_COURSE_TYPE_FD_CODE_TWO" element|
    And I execute "Create Course Type" reusable step replacing some steps
      |4|I set "CourseTypeDescriptionTwo[######]" text to the "Description" "Product Factory text field two"|
      |13|I set "EC_COURSE_TYPE_DESCRIPTION_TWO" text to the "Description" "Product Factory text field"|
      |15|I should see the "EC_COURSE_TYPE_DESCRIPTION_TWO" element|
    And I execute "Create Location Financial Dimension" reusable step replacing some steps
      |4|I set "LocationFDCodeTwo[####]" text to the "Code" "Product Factory text field"|
      |5|I set "LocationFDDescriptionTwo[####]" text to the "Description" "Product Factory text field"|
      |9|I should see the "EC_LOCATION_FD_CODE_TWO" element|
    And I execute "Create Region Financial Dimension" reusable step replacing some steps
      |4|I set "RegionFDCodeTwo[######]" text to the "Code" "Product Factory text field"|
      |5|I set "RegionFDDescriptionTwo[######]" text to the "Description" "Product Factory text field"|
      |9|I should see the "EC_REGION_FD_CODE_TWO" element|
    And I execute "Create Region" reusable step replacing some steps
      |4|I set "RegionNameTwo[######]" text to the "Name" "Product Factory text field"|
      |8|I should see the "EC_REGION_NAME_TWO" element|
    And I execute "Create Location" reusable step replacing some steps
      |4|I set "LocationNameTwo[######]" text to the "Name" "Product Factory text field"|
      |5|I set "LocationAddressTwo[######]" text to the "Address Line 1" "Product Factory text field"|
      |11|I should see the "EC_LOCATION_NAME_TWO" element|
    And I execute "Create Level" reusable step replacing some steps
      |4|I set "LevelShortNameTwo[######]" text to the "Short Name" "Product Factory text field"|
      |5|I set "LevelNameTwo[######]" text to the "Name" "Product Factory text field"|
      |7|I should see the "EC_LEVEL_SHORT_NAME_TWO" element|
    And I execute "Create Paper" reusable step replacing some steps
      |5|I set "PaperDescriptionTwo[######]" text to the "Description" "Product Factory text field two"|
      |8|I set "EC_PAPER_DESCRIPTION_TWO" text to the "Description" "Product Factory text field"|
      |10|I should see the "EC_PAPER_DESCRIPTION_TWO" element|
    And I execute "Link Body To Level" reusable step replacing some steps
      |3|I click on the "EC_BODY_NAME_TWO" "Product Factory Link Levels button"|
      |4|I "check" "EC_LEVEL_NAME_TWO" "Product Factory dialog checkbox"       |
    And I execute "Link Body To Paper" reusable step replacing some steps
      |3|I set "EC_PAPER_DESCRIPTION_TWO" text to the "Description" "Product Factory text field"|
      |5|I click on the "EC_PAPER_DESCRIPTION_TWO" "Product Factory Change Body button"|
      |6|I click on the "EC_BODY_NAME_TWO" "Product Factory select button"             |
    And I execute "Link Paper To Level" reusable step replacing some steps
      |3|I set "EC_PAPER_DESCRIPTION_TWO" text to the "Description" "Product Factory text field"|
      |5|I click on the "EC_PAPER_DESCRIPTION_TWO" "Product Factory Link Levels button"|
      |6|I "check" "EC_LEVEL_NAME_TWO" "Product Factory dialog checkbox"       |
    And I execute "Create Sitting" reusable step replacing some steps
      |4|I set "SittingNameTwo[######]" text to the "Name" "Product Factory text field"|
      |9|I "check" "EC_BODY_NAME_TWO" "Product Factory dialog checkbox"                    |
      |11|I should see the "EC_SITTING_NAME_TWO" element|

    Given I execute "Create Digital Content" reusable step

    And I set "EC_BODY_SHORT_NAME_TWO" text to the "Body" "Product Factory text field"
    And I click on the "Search" "Product Factory button"
    Then I shouldn't see the "EC_BODY_SHORT_NAME" element
    And I click on the "Clear Filters" "Product Factory button"

    And I set "EC_LEVEL_SHORT_NAME_TWO" text to the "Level" "Product Factory text field"
    And I click on the "Search" "Product Factory button"
    Then I shouldn't see the "EC_BODY_SHORT_NAME" element
    And I click on the "Clear Filters" "Product Factory button"

    And I set "EC_PAPER_DESCRIPTION_TWO" text to the "Paper" "Product Factory text field"
    And I click on the "Search" "Product Factory button"
    Then I shouldn't see the "EC_BODY_SHORT_NAME" element
    And I click on the "Clear Filters" "Product Factory button"

    And I set "EC_SITTING_NAME_TWO" text to the "Sitting" "Product Factory text field"
    And I click on the "Search" "Product Factory button"
    Then I shouldn't see the "EC_BODY_SHORT_NAME" element
    And I click on the "Clear Filters" "Product Factory button"

    And I set "EC_COURSE_TYPE_DESCRIPTION_TWO" text to the "Course Type" "Product Factory text field"
    And I click on the "Search" "Product Factory button"
    Then I shouldn't see the "EC_BODY_SHORT_NAME" element
    And I click on the "Clear Filters" "Product Factory button"

    And I set "EC_BODY_SHORT_NAME_TWO" text to the "Body" "Product Factory text field"
    And I set "EC_LEVEL_SHORT_NAME_TWO" text to the "Level" "Product Factory text field"
    And I set "EC_PAPER_DESCRIPTION_TWO" text to the "Paper" "Product Factory text field"
    And I set "EC_SITTING_NAME_TWO" text to the "Sitting" "Product Factory text field"
    And I set "EC_COURSE_TYPE_DESCRIPTION_TWO" text to the "Course Type" "Product Factory text field"
    And I click on the "Search" "Product Factory button"
    Then I shouldn't see the "EC_BODY_SHORT_NAME" element
    And I click on the "Clear Filters" "Product Factory button"