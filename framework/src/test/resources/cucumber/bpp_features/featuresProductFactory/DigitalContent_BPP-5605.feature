@ProductFactory @DigitalContent
Feature: Digital Content Id - Create - BPP-5605
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

  @Positive @P1 #TC-3148, TC-3567
  Scenario: Create Digital Content
    Given I execute "Create Digital Content" reusable step

    And I set "EC_BODY_SHORT_NAME" text to the "Search" "Product Factory text field" from keyboard
    And I click on the "submit" "element by type"
    Then I should see the "EC_BODY_SHORT_NAME" element
    And I click on the "Clear the search text" "element by title"

    And I set "EC_LEVEL_SHORT_NAME" text to the "Search" "Product Factory text field" from keyboard
    And I click on the "submit" "element by type"
    Then I should see the "EC_BODY_SHORT_NAME" element
    And I click on the "Clear the search text" "element by title"

    And I set "EC_PAPER_DESCRIPTION" text to the "Search" "Product Factory text field" from keyboard
    And I click on the "submit" "element by type"
    Then I should see the "EC_BODY_SHORT_NAME" element
    And I click on the "Clear the search text" "element by title"

    And I set "EC_SITTING_NAME" text to the "Search" "Product Factory text field" from keyboard
    And I click on the "submit" "element by type"
    Then I should see the "EC_BODY_SHORT_NAME" element
    And I click on the "Clear the search text" "element by title"

    And I set "EC_COURSE_TYPE_DESCRIPTION" text to the "Search" "Product Factory text field" from keyboard
    And I click on the "submit" "element by type"
    Then I should see the "EC_BODY_SHORT_NAME" element
    And I click on the "Clear the search text" "element by title"

  @Negative @P1 #TC-3149
  Scenario: Create Digital Content Prevent Duplicate
    Given I execute "Create Digital Content" reusable step
    When I execute "Create Digital Content" reusable step
    Then I should see the "Digital Content already exists" "text contained in element"

  @Negative @P1 #TC-3194
  Scenario: Create Digital Content Additional Scenarios
    And I execute "Create Level" reusable step replacing some steps
      |4|I set "LevelShortNameTwo[######]" text to the "Short Name" "Product Factory text field"|
      |5|I set "LevelNameTwo[######]" text to the "Name" "Product Factory text field"|
    And I execute "Create Paper" reusable step replacing some steps
      |5|I set "PaperNameTwo[#####]" text to the "Name" "Product Factory text field"|
      |6|I set "PaperDescriptionTwo[######]" text to the "Description" "Product Factory text field"|
    And I execute "Link Paper To Level" reusable step replacing some steps
      |3|I set "EC_PAPER_DESCRIPTION_TWO" text to the "Search" "Product Factory text field" from keyboard|
      |5|I click on the "EC_PAPER_NAME_TWO" "Product Factory Link Levels button"|
      |6|I "check" "EC_LEVEL_NAME_TWO" "Product Factory dialog checkbox"|
    And I execute "Link Body To Level" reusable step replacing some steps
      |4|I "check" "EC_LEVEL_NAME_TWO" "Product Factory dialog checkbox"|
    And I execute "Link Body To Paper" reusable step replacing some steps
      |3|I set "EC_PAPER_DESCRIPTION_TWO" text to the "Search" "Product Factory text field" from keyboard|
      |5|I click on the "EC_PAPER_NAME_TWO" "Product Factory Change Body button"|
    Given I execute "Create Digital Content" reusable step
    Then I click on the "Create" "Product Factory button"
    Then I click on the "Cancel" "Product Factory button"
    Then I should see the "EC_BODY_SHORT_NAME" element