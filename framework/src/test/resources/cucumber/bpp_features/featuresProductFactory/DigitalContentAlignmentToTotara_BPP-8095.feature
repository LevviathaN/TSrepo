@ProductFactory @DigitalContent
Feature: Better alignment of Totara Course and Product Factory Digital Content (Terra Changes) - BPP-8095
  As a member of VLE Operations
  I would like the Course Name and Category in Totara to reflect the Digital Content that's setup in Product Factory
  So that I know what my Course represents
  Students aren't confused by a Course Name that doesn't reflect what they are studying

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

  @Positive @P1 #TC-3453
  Scenario: Verify Digital Content Name Suffix Field
    When I click on the "Delivery" "Product Factory navigation item"
    When I click on the "Digital Content" "Product Factory navigation sub item"
    Then I click on the "Create" "Product Factory button"
    And I should see the "undefined" element
    And I click on the "Body" "Product Factory dropdown"
    And I click on the "EC_BODY_NAME" "Product Factory dropdown option"
    And I click on the "Level" "Product Factory dropdown"
    And I click on the "EC_LEVEL_NAME" "Product Factory dropdown option"
    And I click on the "Paper" "Product Factory dropdown"
    And I click on the "EC_PAPER_NAME" "Product Factory dropdown option"
    And I click on the "Sittings" "Product Factory edit button"
    And I "check" "EC_SITTING_NAME" "Product Factory dialog checkbox"
    And I click on the "Save" "Product Factory active button"
    And I should see the "[EC_BODY_SHORT_NAME] - [EC_LEVEL_SHORT_NAME] - [EC_PAPER_NAME] - [EC_SITTING_NAME]" element
    And I click on the "Course Types" "Product Factory edit button"
    And I set "EC_COURSE_TYPE_DESCRIPTION" text to the "Search" "Product Factory text field two" from keyboard
    And I click on the "submit" "element by type two"
    And I "check" "EC_COURSE_TYPE_DESCRIPTION" "Product Factory dialog checkbox"
    And I click on the "Save" "Product Factory active button"
    And I set "Suffix" text to the "Name Suffix" "Product Factory text field"
    And I should see the "[EC_BODY_SHORT_NAME] - [EC_LEVEL_SHORT_NAME] - [EC_PAPER_NAME] - [EC_SITTING_NAME] - Suffix" element
    And I click on the "Save" "Product Factory button"
    And I set "EC_COURSE_TYPE_DESCRIPTION" text to the "Search" "Product Factory text field" from keyboard
    And I click on the "submit" "element by type"
    Then I should see the "EC_BODY_SHORT_NAME" element

  @Positive @P1 #TC-3455
  Scenario: Verify Digital Content Name Suffix Field Empty
    When I click on the "Delivery" "Product Factory navigation item"
    When I click on the "Digital Content" "Product Factory navigation sub item"
    Then I click on the "Create" "Product Factory button"
    And I should see the "undefined" element
    And I click on the "Body" "Product Factory dropdown"
    And I click on the "EC_BODY_NAME" "Product Factory dropdown option"
    And I click on the "Level" "Product Factory dropdown"
    And I click on the "EC_LEVEL_NAME" "Product Factory dropdown option"
    And I click on the "Paper" "Product Factory dropdown"
    And I click on the "EC_PAPER_DESCRIPTION" "Product Factory dropdown option"
    And I click on the "Sittings" "Product Factory edit button"
    And I "check" "EC_SITTING_NAME" "Product Factory dialog checkbox"
    And I click on the "Save" "Product Factory active button"
    And I should see the "[EC_BODY_SHORT_NAME] - [EC_LEVEL_SHORT_NAME] - [EC_PAPER_DESCRIPTION] - [EC_SITTING_NAME]" element
    And I click on the "Course Types" "Product Factory edit button"
    And I set "EC_COURSE_TYPE_DESCRIPTION" text to the "Description" "Product Factory text field"
    And I click on the "Search" "Product Factory button two"
    And I "check" "EC_COURSE_TYPE_DESCRIPTION" "Product Factory dialog checkbox"
    And I click on the "Save" "Product Factory active button"
    And I should see the "[EC_BODY_SHORT_NAME] - [EC_LEVEL_SHORT_NAME] - [EC_PAPER_DESCRIPTION] - [EC_SITTING_NAME]" element
    And I click on the "Save" "Product Factory button"
    And I set "EC_COURSE_TYPE_DESCRIPTION" text to the "Course Type" "Product Factory text field"
    And I click on the "Search" "Product Factory button"
    Then I should see the "EC_BODY_SHORT_NAME" element

  @Negative @P1 #TC-3453
  Scenario: Digital Content Name Suffix Field Prevent Duplicate
    Given I execute "Create Digital Content" reusable step with some additional steps
      |18|I set "Suffix" text to the "Name Suffix" "Product Factory text field"|
      |19|I should see the "[EC_BODY_SHORT_NAME] - [EC_LEVEL_SHORT_NAME] - [EC_PAPER_DESCRIPTION] - [EC_SITTING_NAME] - Suffix" element|
    Given I execute "Create Digital Content" reusable step with some additional steps
      |18|I set "New Suffix" text to the "Name Suffix" "Product Factory text field"|
      |19|I should see the "[EC_BODY_SHORT_NAME] - [EC_LEVEL_SHORT_NAME] - [EC_PAPER_DESCRIPTION] - [EC_SITTING_NAME] - New Suffix" element|
    Then I should see the "Digital Content already exists for the combination of Body, Level, Paper, Sitting ([EC_SITTING_NAME]) & Course Type ([EC_COURSE_TYPE_DESCRIPTION])" message