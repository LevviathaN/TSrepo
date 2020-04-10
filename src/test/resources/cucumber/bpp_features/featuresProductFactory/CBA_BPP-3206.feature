@ProductFactory @CBA
Feature: CBA - Create Courses and Instances - BPP-3206
  As a Product Setter
  I want to be able to amend create Courses with a single Instance for a CBA record

  Background:
    Given I execute "Log In" reusable step
    When I execute "Create Body Financial Dimension" reusable step
    And I execute "Create Body" reusable step
    And I execute "Create VAT Rule" reusable step
    And I execute "Create Sitting" reusable step
    And I execute "Create Session Duration" reusable step with some additional steps
      |5|I "check" "Allowed for CBA" "Product Factory checkbox"|
    And I execute "Create Course Type Financial Dimension" reusable step
    And I execute "Create Course Type" reusable step
    And I execute "Create Location Financial Dimension" reusable step
    And I execute "Create Region Financial Dimension" reusable step
    And I execute "Create Region" reusable step
    And I execute "Create Location" reusable step
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

  @Positive @Regression @P1 #TC-3000
  Scenario: Create New CBA Record
    When I click on the "exit_to_app" button
    And I execute "Log In" reusable step replacing some steps
      |3|I fill the "Product Factory Email" field with "MD_COMMON_CREDENTIALS_PRODUCTFACTORYJUNIORPRODUCTSETTER"|
      |4|I fill the "Product Factory Password" field with "MD_COMMON_CREDENTIALS_PRODUCTFACTORYJUNIORPSPASSWORD"|

    When I click on the "Products" "Product Factory navigation item"
    When I click on the "CBAs" "Product Factory navigation sub item"
    And I click on the "Create" "Product Factory button"
    And I click on the "EC_SITTING_NAME" "Product Factory select button"
    And I click on the "EC_SESSION_DURATION_DESCRIPTION" "Product Factory select button"
    And I click on the "Region" "Product Factory dropdown"
    And I click on the "EC_REGION_NAME" "element"
    And I click on the "Location" "Product Factory dropdown"
    And I click on the "EC_LOCATION_NAME" "element"
    And I click on the "EC_COURSE_TYPE_DESCRIPTION" "Product Factory select button"

    Then I should see the "EC_SITTING_NAME" element
    Then I should see the "EC_SESSION_DURATION_DESCRIPTION" element
    Then I should see the "EC_REGION_NAME" element
    Then I should see the "EC_LOCATION_NAME" element
    Then I should see the "EC_COURSE_TYPE_DESCRIPTION" element

    And I set "[TODAY]" text to the "Date" "Product Factory text field"
    And I set "01:00AM" text to the "Start Time" "Product Factory text field"
    And I set "1" text to the "Capacity" "Product Factory text field"
    And I click on the "Finish" "Product Factory button"

    Then I should see the "EC_SITTING_NAME" element
    Then I should see the "EC_SESSION_DURATION_DESCRIPTION" element
    Then I should see the "EC_REGION_NAME" element
    Then I should see the "EC_LOCATION_NAME" element
    Then I should see the "EC_COURSE_TYPE_DESCRIPTION" element
    Then I should see the "01:00" element
    Then I should see the "1" element

    And I click on the "Default Institute Fee (£)" "Product Factory edit button"
    And I set "~DefaultInstituteFee[###]" text to the "Default Institute Fee (£)" "Product Factory text field"
    And I click on the "Save" "Product Factory button"
    When I click on the "Create" "Product Factory button"
    And I "check" "EC_BODY_SHORT_NAME" "Product Factory checkbox"
    And I click on the "Next" "Product Factory button"
    And I click on the "Next" "Product Factory button"

    Then I should see the "EC_BODY_SHORT_NAME" element
    Then I should see the "EC_PAPER_DESCRIPTION" element
    Then I should see the "EC_LEVEL_SHORT_NAME" element

    And I click on the "Finish" "Product Factory button"

    Then I shouldn't see the "Sitting" "Product Factory edit button"
    Then I shouldn't see the "Session Duration" "Product Factory edit button"
    Then I shouldn't see the "Location" "Product Factory edit button"
    Then I shouldn't see the "Course Type" "Product Factory edit button"

    When I click on the "Clients" "Product Factory button"
    And I click on the "Edit" "Product Factory button"
    And I "check" "EC_CLIENT_NAME" "Product Factory checkbox"
    And I click on the "Save" "Product Factory button"
    Then I should see the "Clients updated on CBA Courses." message
    And I click on the "Close" "Product Factory button"
    When I click on the "Activate" "Product Factory button"
    Then I should see the "(Active)" element
    Then I should see the "Published" message
    And I should see the "Deactivate" "Product Factory button"


