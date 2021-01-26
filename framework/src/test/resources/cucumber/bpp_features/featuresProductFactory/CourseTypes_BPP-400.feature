@ProductFactory @CourseTypes
Feature: Reference Data - Course Type - BPP-400
  As a Senior Product Setter
  I need to be able to specify a Course Type
  So that I can identify the type of tuition on a Course

  Background:
    Given I execute "Log In" reusable step
    When I execute "Create Course Type Financial Dimension" reusable step
    Then I execute "Create VAT Rule" reusable step

  @Positive @Regression @P1 #TC-701, TC-1720
  Scenario: Add a New Course Type Using a Modal
    When I click on the "Types" "Product Factory navigation item"
    When I click on the "Course Types" "Product Factory navigation sub item"
    Then I click on the "Create" "Product Factory button"
    And I set "CourseTypeDescription[######]" text to the "Description" "Product Factory text field"
    And I click on the "VAT Rule" "Product Factory dropdown"
    And I click on the "EC_VAT_RULE_CODE" "Product Factory dropdown option"
    And I click on the "Cost Centre Financial Dimension" "Product Factory dropdown"
    And I click on the "EC_COURSE_TYPE_FD_CODE" "Product Factory dropdown option"
    And I click on the "Project Financial Dimension" "Product Factory dropdown"
    And I click on the "EC_COURSE_TYPE_FD_CODE" "Product Factory dropdown option"
    And I click on the "Save" "Product Factory button"
    And I wait for "3" seconds
    Then I set "EC_COURSE_TYPE_DESCRIPTION" text to the "Search" "Product Factory text field" from keyboard
    And I click on the "submit" "element by type"
    Then I should see the "EC_COURSE_TYPE_DESCRIPTION" element
    And I click on the "Clear the search text" "element by title"

  @Negative @P2 #TC-700
  Scenario: Submitting Incomplete Course Type Fields
    When I click on the "Types" "Product Factory navigation item"
    When I click on the "Course Types" "Product Factory navigation sub item"
    Then I click on the "Create" "Product Factory button"
    Then Attribute "tabindex" of "Save" "Product Factory button" should have value "-1"

  @Negative @P2 #TC-702
  Scenario: Add a Course Type Where Description Already Exists
    When I execute "Create Course Type" reusable step
    When I click on the "Types" "Product Factory navigation item"
    When I click on the "Course Types" "Product Factory navigation sub item"
    Then I click on the "Create" "Product Factory button"
    And I set "EC_COURSE_TYPE_DESCRIPTION" text to the "Description" "Product Factory text field"
    And I click on the "VAT Rule" "Product Factory dropdown"
    And I click on the "EC_VAT_RULE_CODE" "Product Factory dropdown option"
    And I click on the "Cost Centre Financial Dimension" "Product Factory dropdown"
    And I click on the "EC_COURSE_TYPE_FD_CODE" "Product Factory dropdown option"
    And I click on the "Project Financial Dimension" "Product Factory dropdown"
    And I click on the "EC_COURSE_TYPE_FD_CODE" "Product Factory dropdown option"
    And I click on the "Save" "Product Factory button"
    Then I should see the "Description must be unique" "message"