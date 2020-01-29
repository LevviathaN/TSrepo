@ProductFactory @CourseTypes @Amend
Feature: Amend Reference Data - Course Type - BPP-661
  As a Senior Product Setter
  I need to search for and Amend a Course Type
  so that I can modify the type of tuition on a Course

  Background:
    Given I execute "Log In" reusable step
    When I execute "Create Course Type Financial Dimension" reusable step
    And I execute "Create VAT Rule" reusable step
    Then I execute "Create Course Type" reusable step

  @Positive @Regression @P1 #TC-1546
  Scenario: Amend a Course Type Using a Modal
    When I click on the "Types" "Product Factory navigation item"
    When I click on the "Course Types" "Product Factory navigation sub item"
    When I click on the "EC_COURSE_TYPE_DESCRIPTION" "Product Factory edit button"
    And I set "CourseTypeDescription[####]" text to the "Description" "Product Factory text field"
    And I click on the "Save" "Product Factory button"
    Then I should see the "EC_COURSE_TYPE_DESCRIPTION" element

  @Negative @P2 #TC-792
  Scenario: Submitting Unchanged Course Type Fields
    When I click on the "Types" "Product Factory navigation item"
    When I click on the "Course Types" "Product Factory navigation sub item"
    When I click on the "EC_COURSE_TYPE_DESCRIPTION" "Product Factory edit button"
    Then Attribute "tabindex" of "Save" "Product Factory button" should have value "-1"

  @Negative @P2 #TC-794
  Scenario: Amend a Course Type Where Short Name Already Exists
    Given I execute "Create Course Type" reusable step replacing some steps
      |4|I set "CourseTypeDescriptionNew[####]" text to the "Description" "Product Factory text field"|
    When I click on the "Types" "Product Factory navigation item"
    When I click on the "Course Types" "Product Factory navigation sub item"
    When I click on the "EC_COURSE_TYPE_DESCRIPTION" "Product Factory edit button"
    And I set "EC_COURSE_TYPE_DESCRIPTION_NEW" text to the "Description" "Product Factory text field"
    And I click on the "Save" "Product Factory button"
    Then I should see the "Description must be unique" "message"