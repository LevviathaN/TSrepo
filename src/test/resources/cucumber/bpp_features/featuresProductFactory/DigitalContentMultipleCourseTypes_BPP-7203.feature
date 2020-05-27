@ProductFactory @DigitalContent
Feature: Digital Content Id - Multiple Course Types - BPP-7203
  As a Senior Product Setter
  I want to create Digital Content
  so that Course(s) can be given a Digital Content Id.

  Background:
    Given I execute "Log In" reusable step
    When I execute "Create Body Financial Dimension" reusable step
    And I execute "Create Body" reusable step
    And I execute "Create VAT Rule" reusable step
    And I execute "Create Course Type Financial Dimension" reusable step
    And I execute "Create Course Type" reusable step
    And I execute "Create Course Type" reusable step replacing some steps
      |4|I set "CourseTypeDescriptionTwo[######]" text to the "Description" "Product Factory text field"|
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

  @Positive @P1 #TC-3203
  Scenario: Create Digital Content Multiple Course Types
    Given I execute "Create Digital Content" reusable step with some additional steps
      |14|I "check" "EC_COURSE_TYPE_DESCRIPTION_TWO" "Product Factory dialog checkbox"|
    Then I should see the "EC_BODY_SHORT_NAME" element

  @Negative @P1 #TC-3204
  Scenario: Create Digital Content Multiple Course Types Duplicate
    Given I execute "Create Digital Content" reusable step with some additional steps
      |14|I "check" "EC_COURSE_TYPE_DESCRIPTION_TWO" "Product Factory dialog checkbox"|
    Then I should see the "EC_BODY_SHORT_NAME" element
    And I execute "Create Digital Content" reusable step with some additional steps
      |14|I "check" "EC_COURSE_TYPE_DESCRIPTION_TWO" "Product Factory dialog checkbox"|
    Then I should see the "EC_BODY_SHORT_NAME" element