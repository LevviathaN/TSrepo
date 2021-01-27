@ProductFactory @CBA @Amend
Feature: CBA - Edit - BPP-3200
  As a Product Setter
  I want to be able to amend any existing CBA records so that I can make any required changes.

  Background:
    Given I execute "Log In" reusable step
    When I execute "Create Body Financial Dimension" reusable step
    And I execute "Create Body" reusable step
    And I execute "Create VAT Rule" reusable step
    And I execute "Create Sitting" reusable step
    And I execute "Create Sitting" reusable step replacing some steps
      |4|I set "SittingNameTwo[######]" text to the "Name" "Product Factory text field"|
    And I execute "Create Session Duration" reusable step with some additional steps
      |6|I "check" "Allowed for CBA" "Product Factory checkbox"|
    When I click on the "Delivery" "Product Factory navigation item"
    When I click on the "Session Durations" "Product Factory navigation sub item"
    Then I click on the "Create" "Product Factory button"
    And I set "SessionDurationDescriptionTwo[######]" text to the "Description" "Product Factory text field"
    And I set "1" text to the "Number of Dates" "Product Factory text field"
    And I "check" "Allowed for CBA" "Product Factory checkbox"
    And I click on the "Save" "Product Factory button"
    And I execute "Create Course Type Financial Dimension" reusable step
    And I execute "Create Course Type" reusable step
    And I execute "Create Course Type" reusable step replacing some steps
      |4 |I set "CourseTypeDescriptionTwo[######]" text to the "Description" "Product Factory text field"|
      |13|Then I set "EC_COURSE_TYPE_DESCRIPTION_TWO" text to the "Search" "Product Factory text field" from keyboard|
      |15|I should see the "EC_COURSE_TYPE_DESCRIPTION_TWO" element                                          |
    And I execute "Create Location Financial Dimension" reusable step
    And I execute "Create Region Financial Dimension" reusable step
    And I execute "Create Region" reusable step
    And I execute "Create Region" reusable step replacing some steps
      |4|I set "RegionNameTwo[######]" text to the "Name" "Product Factory text field"|
      |8|I should see the "EC_REGION_NAME" element                                    |
    And I execute "Create Location" reusable step
    And I execute "Create Location" reusable step replacing some steps
      |4|I set "LocationNameTwo[######]" text to the "Name" "Product Factory text field"|
      |5|I set "LocationAddressTwo[######]" text to the "Address Line 1" "Product Factory text field"|
      |7|I click on the "EC_REGION_NAME_TWO" "Product Factory dropdown option"                |
    And I execute "Create Client" reusable step
    And I execute "Create Level" reusable step
    And I execute "Create Paper" reusable step with some additional steps
      |5|I "check" "CBA Paper?" "Product Factory checkbox"|
      |6|I set "1" text to the "Time in Hours" "Product Factory text field"|
    And I execute "Link Body To Level" reusable step
    And I execute "Link Body To Paper" reusable step
    And I execute "Link Paper To Level" reusable step
    And I execute "Create Pricing Matrix" reusable step
    And I execute "Create Pricing Matrix Full" reusable step

  @Positive @Regression @P1 #TC-2717, TC-2721, TC-2864
  Scenario: Update Draft CBA Record
    When I click on the "exit_to_app" button
    And I execute "Log In" reusable step replacing some steps
      |3|I fill the "Product Factory Email" field with "MD_COMMON_CREDENTIALS_PRODUCTFACTORYJUNIORPRODUCTSETTER"|
      |4|I fill the "Product Factory Password" field with "MD_COMMON_CREDENTIALS_PRODUCTFACTORYJUNIORPSPASSWORD"|
    And I execute "Create CBA Record" reusable step
    When I click on the "Products" "Product Factory navigation item"
    When I click on the "CBAs" "Product Factory navigation sub item"
    And I set "EC_COURSE_TYPE_DESCRIPTION" text to the "Search" "Product Factory text field" from keyboard
    And I click on the "submit" "element by type"
    And I click on the "EC_COURSE_TYPE_DESCRIPTION" "Product Factory edit button"
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
    When I click on the "exit_to_app" button
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