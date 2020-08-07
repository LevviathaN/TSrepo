@ProductFactory @DigitalContent
Feature: Digital Content Id - Edit - BPP-7052
  As a Senior Product Setter
  I want to be able to edit the Digital Content Id Sittings
  so that Course the it assigned to the correct Courses.

  Background:
    Given I execute "Log In" reusable step
    When I execute "Create Body Financial Dimension" reusable step
    And I execute "Create Body" reusable step
    And I execute "Create VAT Rule" reusable step
    And I execute "Create Course Type Financial Dimension" reusable step
    And I execute "Create Course Type" reusable step
    And I execute "Create Course Type" reusable step replacing some steps
      |4|I set "CourseTypeDescriptionTwo[######]" text to the "Description" "Product Factory text field two"|
      |13|I set "EC_COURSE_TYPE_DESCRIPTION_TWO" text to the "Description" "Product Factory text field"     |
      |15|I should see the "EC_COURSE_TYPE_DESCRIPTION_TWO" element                                         |
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
    And I execute "Create Sitting" reusable step replacing some steps
      |4|I set "SittingNameTwo[######]" text to the "Name" "Product Factory text field"|
    And I execute "Create Sitting" reusable step replacing some steps
      |4|I set "SittingNameThree[######]" text to the "Name" "Product Factory text field"|

  @Positive @P1 #TC-3169
  Scenario: Edit Digital Content
    Given I execute "Create Digital Content" reusable step
    When I click on the "Delivery" "Product Factory navigation item"
    When I click on the "Digital Content" "Product Factory navigation sub item"
    And I click on the "EC_BODY_SHORT_NAME" "Product Factory edit button"
    When I click on the "Sittings" "Product Factory edit button"
    And I click on the "EC_SITTING_NAME_TWO" "Product Factory checkbox"
    And I click on the "Save" "Product Factory active button"
    And I click on the "Save" "Product Factory active button"
    And I click on the "EC_BODY_SHORT_NAME" "Product Factory edit button"
    When I click on the "Course Types" "Product Factory edit button"
    And I set "EC_COURSE_TYPE_DESCRIPTION_TWO" text to the "Description" "Product Factory text field"
    And I click on the "Search" "Product Factory button two"
    And I click on the "EC_COURSE_TYPE_DESCRIPTION_TWO" "Product Factory checkbox"
    And I click on the "Save" "Product Factory active button"
    And I click on the "Save" "Product Factory active button"

  @Negative @P1 #TC-3198
  Scenario: Edit Digital Content Duplicate
    Given I execute "Create Digital Content" reusable step
    Given I execute "Create Digital Content" reusable step replacing some steps
      |11|I "check" "EC_SITTING_NAME_TWO" "Product Factory dialog checkbox"|
      |14|I set "EC_COURSE_TYPE_DESCRIPTION_TWO" text to the "Description" "Product Factory text field"|
      |16|I "check" "EC_COURSE_TYPE_DESCRIPTION_TWO" "Product Factory dialog checkbox"|
    And I click on the "EC_BODY_SHORT_NAME" "Product Factory edit button"
    When I click on the "Sittings" "Product Factory edit button"
    And I click on the "EC_SITTING_NAME_TWO" "Product Factory checkbox"
    And I click on the "EC_SITTING_NAME" "Product Factory checkbox"
    And I click on the "Save" "Product Factory active button"
    And I click on the "Save" "Product Factory button"
    And I click on the "EC_BODY_SHORT_NAME" "Product Factory edit button"
    When I click on the "Course Types" "Product Factory edit button"
    And I set "CourseTypeDescription" text to the "Description" "Product Factory text field"
    And I click on the "Search" "Product Factory button two"
    And I click on the "EC_COURSE_TYPE_DESCRIPTION_TWO" "Product Factory checkbox"
    And I click on the "EC_COURSE_TYPE_DESCRIPTION" "Product Factory checkbox"
    And I click on the "Save" "Product Factory active button"
    And I click on the "Save" "Product Factory button"
    Then I should see the "Digital Content already exists" "text contained in element"

  @Positive @P1 #TC-3209
  Scenario: Edit Digital Content Not In Use
    Given I execute "Create Digital Content" reusable step with some additional steps
      |11|I "check" "EC_SITTING_NAME_TWO" "Product Factory dialog checkbox"|
      |18|I set "EC_COURSE_TYPE_DESCRIPTION_TWO" text to the "Description" "Product Factory text field"|
      |19|I click on the "Search" "Product Factory button two"                                         |
      |20|I "check" "EC_COURSE_TYPE_DESCRIPTION_TWO" "Product Factory dialog checkbox"|
    Then I should see the "EC_BODY_SHORT_NAME" element
    When I click on the "Delivery" "Product Factory navigation item"
    When I click on the "Digital Content" "Product Factory navigation sub item"
    And I click on the "EC_BODY_SHORT_NAME" "Product Factory edit button"
    And I click on the "Course Types" "Product Factory edit button"
    And I set "CourseTypeDescription" text to the "Description" "Product Factory text field"
    And I click on the "Search" "Product Factory button two"
    And I "check" "EC_COURSE_TYPE_DESCRIPTION_TWO" "Product Factory dialog checkbox"
    And I click on the "Save" "Product Factory active button"
    And I click on the "Save" "Product Factory button"
    And I click on the "EC_BODY_SHORT_NAME" "Product Factory edit button"
    And I click on the "Sittings" "Product Factory edit button"
    And I "check" "EC_SITTING_NAME_TWO" "Product Factory dialog checkbox"
    And I click on the "Save" "Product Factory active button"
    And I click on the "Save" "Product Factory button"

  @Negative @P1 #TC-3210
  Scenario: Edit Digital Content Additional Scenario
    Given I execute "Create Digital Content" reusable step with some additional steps
      |11|I "check" "EC_SITTING_NAME_TWO" "Product Factory dialog checkbox"|
      |18|I set "EC_COURSE_TYPE_DESCRIPTION_TWO" text to the "Description" "Product Factory text field"|
      |19|I click on the "Search" "Product Factory button two"                                         |
      |20|I "check" "EC_COURSE_TYPE_DESCRIPTION_TWO" "Product Factory dialog checkbox"|
    Then I should see the "EC_BODY_SHORT_NAME" element
    When I click on the "Delivery" "Product Factory navigation item"
    When I click on the "Digital Content" "Product Factory navigation sub item"
    And I click on the "EC_BODY_SHORT_NAME" "Product Factory edit button"
    And I click on the "Course Types" "Product Factory edit button"
    And I set "CourseTypeDescription" text to the "Description" "Product Factory text field"
    And I click on the "Search" "Product Factory button two"
    And I "check" "EC_COURSE_TYPE_DESCRIPTION_TWO" "Product Factory dialog checkbox"
    And I "check" "EC_COURSE_TYPE_DESCRIPTION" "Product Factory dialog checkbox"
    And I click on the "Save" "Product Factory active button"
    Then Attribute "tabindex" of "Save" "Product Factory button" should have value "-1"
    And I click on the "Sittings" "Product Factory edit button"
    And I "check" "EC_SITTING_NAME_TWO" "Product Factory dialog checkbox"
    And I "check" "EC_SITTING_NAME" "Product Factory dialog checkbox"
    And I click on the "Save" "Product Factory active button"
    Then Attribute "tabindex" of "Save" "Product Factory button" should have value "-1"
    And I click on the "Course Types" "Product Factory edit button"
    And I set "CourseTypeDescription" text to the "Description" "Product Factory text field"
    And I click on the "Search" "Product Factory button two"
    And I "check" "EC_COURSE_TYPE_DESCRIPTION" "Product Factory dialog checkbox"
    And I click on the "Save" "Product Factory active button"
    And I click on the "Sittings" "Product Factory edit button"
    And I "check" "EC_SITTING_NAME" "Product Factory dialog checkbox"
    And I click on the "Save" "Product Factory active button"
    Then Attribute "tabindex" of "Save" "Product Factory button" should have value "0"