@ProductFactory @CBA @Filters
Feature: CBA - Create Courses and Instances - BPP-3206
  As a Product Setter
  I want a search filter added to the CBA List page
  so that the records can be filtered.

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
      |4|I set "CourseTypeDescriptionTwo[######]" text to the "Description" "Product Factory text field two"|
      |13|I set "EC_COURSE_TYPE_DESCRIPTION_TWO" text to the "Description" "Product Factory text field" |
      |15|I should see the "EC_COURSE_TYPE_DESCRIPTION_TWO" element                                     |
    And I execute "Create Location Financial Dimension" reusable step
    And I execute "Create Region Financial Dimension" reusable step
    And I execute "Create Region" reusable step
    And I execute "Create Region" reusable step replacing some steps
      |4|I set "RegionNameTwo[######]" text to the "Name" "Product Factory text field"|
      |8|I should see the "EC_REGION_NAME" element                                    |
    And I execute "Create Location" reusable step
    And I execute "Create Location" reusable step replacing some steps
      |4|I set "LocationNameTwo[######]" text to the "Name" "Product Factory text field"|
      |5|I set "LocationAddressTwo[######]" text to the "Address" "Product Factory text field"|
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

  @Positive @Regression @P1 #TC-3771
  Scenario: CBA Page Filters Validation
    When I click on the "exit_to_app" button
    And I execute "Log In" reusable step replacing some steps
      |3|I fill the "Product Factory Email" field with "MD_COMMON_CREDENTIALS_PRODUCTFACTORYJUNIORPRODUCTSETTER"|
      |4|I fill the "Product Factory Password" field with "MD_COMMON_CREDENTIALS_PRODUCTFACTORYJUNIORPSPASSWORD"|

    And I execute "Create CBA Record" reusable step
    And I wait for "2" seconds
    And I execute "Create CBA Course" reusable step

    When I click on the "Products" "Product Factory navigation item"
    When I click on the "CBAs" "Product Factory navigation sub item"

    Then I should see the "Sitting" "Product Factory text field"
    Then I should see the "Session Duration" "Product Factory text field"
    Then I should see the "Region" "Product Factory text field"
    Then I should see the "Location" "Product Factory text field"
    Then I should see the "Course Type" "Product Factory text field"
    Then I should see the "Client" "Product Factory text field"

    And I set "EC_SITTING_NAME" text to the "Sitting" "Product Factory text field"
    And I click on the "Search" "Product Factory button"
    And I should see the "EC_SITTING_NAME" "element"
    And I click on the "Clear Filters" "Product Factory button"

    And I set "EC_SESSION_DURATION_DESCRIPTION" text to the "Session Duration" "Product Factory text field"
    And I click on the "Search" "Product Factory button"
    And I should see the "EC_SESSION_DURATION_DESCRIPTION" "element"
    And I click on the "Clear Filters" "Product Factory button"

    And I set "EC_REGION_NAME" text to the "Region" "Product Factory text field"
    And I click on the "Search" "Product Factory button"
    And I should see the "EC_REGION_NAME" "element"
    And I click on the "Clear Filters" "Product Factory button"

    And I set "EC_LOCATION_NAME" text to the "Location" "Product Factory text field"
    And I click on the "Search" "Product Factory button"
    And I should see the "EC_LOCATION_NAME" "element"
    And I click on the "Clear Filters" "Product Factory button"

    And I set "EC_COURSE_TYPE_DESCRIPTION" text to the "Course Type" "Product Factory text field"
    And I click on the "Search" "Product Factory button"
    And I should see the "EC_COURSE_TYPE_DESCRIPTION" "element"
    And I click on the "Clear Filters" "Product Factory button"

    And I set "EC_CLIENT_NAME" text to the "Client" "Product Factory text field"
    And I click on the "Search" "Product Factory button"
    And I should see the "EC_SITTING_NAME" "element"
    And I click on the "Clear Filters" "Product Factory button"

  @Negative @Regression @P1 #TC-3772
  Scenario: CBA Page Filters Validation Additional Scenarios
    And I execute "Create CBA Record" reusable step
    And I wait for "2" seconds
    And I execute "Create CBA Course" reusable step

    When I click on the "Products" "Product Factory navigation item"
    When I click on the "CBAs" "Product Factory navigation sub item"

    And I set "EC_SITTING_NAME_TWO" text to the "Sitting" "Product Factory text field"
    And I click on the "Search" "Product Factory button"
    And I shouldn't see the "EC_SITTING_NAME_TWO" "element"
    And I click on the "Clear Filters" "Product Factory button"

    And I execute "Create CBA Record" reusable step replacing some steps
      |4|I click on the "EC_SITTING_NAME_TWO" "Product Factory select button"|
      |5|I click on the "EC_SESSION_DURATION_DESCRIPTION_TWO" "Product Factory select button"|
      |7|I click on the "EC_REGION_NAME_TWO" "element"                            |
      |9|I click on the "EC_LOCATION_NAME_TWO" "element"                         |
      |10|I set "EC_COURSE_TYPE_DESCRIPTION_TWO" text to the "Description" "Product Factory text field"|
      |12|I click on the "EC_COURSE_TYPE_DESCRIPTION_TWO" "Product Factory select button"              |
      |13|I should see the "EC_SITTING_NAME_TWO" element|
      |14|I should see the "EC_SESSION_DURATION_DESCRIPTION_TWO" element|
      |15|I should see the "EC_REGION_NAME_TWO" element|
      |16|I should see the "EC_LOCATION_NAME_TWO" element|
      |17|I should see the "EC_COURSE_TYPE_DESCRIPTION_TWO" element|

    When I click on the "Products" "Product Factory navigation item"
    When I click on the "CBAs" "Product Factory navigation sub item"

    And I set "EC_SITTING_NAME_TWO" text to the "Sitting" "Product Factory text field"
    And I set "EC_SESSION_DURATION_DESCRIPTION" text to the "Session Duration" "Product Factory text field"
    And I click on the "Search" "Product Factory button"
    And I shouldn't see the "EC_SITTING_NAME_TWO" "element"
    And I shouldn't see the "EC_SESSION_DURATION_DESCRIPTION" "element"
    And I click on the "Clear Filters" "Product Factory button"

    And I set "EC_SITTING_NAME" text to the "Sitting" "Product Factory text field"
    And I set "EC_SESSION_DURATION_DESCRIPTION" text to the "Session Duration" "Product Factory text field"
    And I click on the "Search" "Product Factory button"
    And I should see the "EC_SITTING_NAME" "element"
    And I click on the "Clear Filters" "Product Factory button"

    And I set "EC_SITTING_NAME" text to the "Sitting" "Product Factory text field"
    And I set "EC_SESSION_DURATION_DESCRIPTION" text to the "Session Duration" "Product Factory text field"
    And I set "EC_REGION_NAME" text to the "Region" "Product Factory text field"
    And I click on the "Search" "Product Factory button"
    And I should see the "EC_SITTING_NAME" "element"
    And I click on the "Clear Filters" "Product Factory button"

    And I set "EC_SITTING_NAME" text to the "Sitting" "Product Factory text field"
    And I set "EC_SESSION_DURATION_DESCRIPTION" text to the "Session Duration" "Product Factory text field"
    And I set "EC_REGION_NAME" text to the "Region" "Product Factory text field"
    And I set "EC_LOCATION_NAME" text to the "Location" "Product Factory text field"
    And I click on the "Search" "Product Factory button"
    And I should see the "EC_SITTING_NAME" "element"
    And I click on the "Clear Filters" "Product Factory button"

    And I set "EC_SITTING_NAME" text to the "Sitting" "Product Factory text field"
    And I set "EC_SESSION_DURATION_DESCRIPTION" text to the "Session Duration" "Product Factory text field"
    And I set "EC_REGION_NAME" text to the "Region" "Product Factory text field"
    And I set "EC_LOCATION_NAME" text to the "Location" "Product Factory text field"
    And I set "EC_COURSE_TYPE_DESCRIPTION" text to the "Course Type" "Product Factory text field"
    And I click on the "Search" "Product Factory button"
    And I should see the "EC_SITTING_NAME" "element"
    And I click on the "Clear Filters" "Product Factory button"

    And I set "EC_SITTING_NAME" text to the "Sitting" "Product Factory text field"
    And I set "EC_SESSION_DURATION_DESCRIPTION" text to the "Session Duration" "Product Factory text field"
    And I set "EC_REGION_NAME" text to the "Region" "Product Factory text field"
    And I set "EC_LOCATION_NAME" text to the "Location" "Product Factory text field"
    And I set "EC_COURSE_TYPE_DESCRIPTION" text to the "Course Type" "Product Factory text field"
    And I set "EC_CLIENT_NAME" text to the "Client" "Product Factory text field"
    And I click on the "Search" "Product Factory button"
    And I should see the "EC_SITTING_NAME" "element"
    And I click on the "Clear Filters" "Product Factory button"

    And I set "EC_SITTING_NAME" text to the "Sitting" "Product Factory text field"
    And I set "EC_SITTING_NAME_TWO" text to the "Sitting" "Product Factory text field"
    And I click on the "Search" "Product Factory button"
    And I shouldn't see the "EC_SITTING_NAME" "element"
    And I click on the "Clear Filters" "Product Factory button"

    And I set "EC_SITTING_NAME" text to the "Region" "Product Factory text field"
    And I click on the "Search" "Product Factory button"
    And I shouldn't see the "EC_SITTING_NAME" "element"
    And I click on the "Clear Filters" "Product Factory button"

    And I set "EC_REGION_NAME" text to the "Sitting" "Product Factory text field"
    And I click on the "Search" "Product Factory button"
    And I shouldn't see the "EC_REGION_NAME" "element"
    And I click on the "Clear Filters" "Product Factory button"