@ProductFactory @Papers @Amend
Feature: Amend Reference Data - Paper - BPP-660
  As a Senior Product Setter
  I need to be able to Amend Papers,
  so that I can use the Paper as part of a course template construction and shared between templates and instances.

  Background:
    Given I execute "Log In" reusable step
    And I execute "Create Paper" reusable step

  @Positive @Regression @P1 #TC-828
  Scenario: Amend a Paper Using a Modal
    When I click on the "Programme" "Product Factory navigation item"
    When I click on the "Papers" "Product Factory navigation sub item"
    Then I set "EC_PAPER_DESCRIPTION" text to the "Search" "Product Factory text field" from keyboard
    And I click on the "submit" "element by type"
    When I click on the "EC_PAPER_NAME" "Product Factory edit button"
    And I set "PaperDescriptionTwo[######]" text to the "Description" "Product Factory text field"
    And I set "PaperNameTwo[######]" text to the "Name" "Product Factory text field"
    And I click on the "Save" "Product Factory button"
    Then I set "EC_PAPER_DESCRIPTION_TWO" text to the "Search" "Product Factory text field" from keyboard
    And I click on the "submit" "element by type"
    Then I should see the "EC_PAPER_NAME_TWO" element

  @Negative @P2 #TC-827
  Scenario: Submitting Unchanged Paper Fields
    When I click on the "Programme" "Product Factory navigation item"
    When I click on the "Papers" "Product Factory navigation sub item"
    Then I set "EC_PAPER_DESCRIPTION" text to the "Search" "Product Factory text field" from keyboard
    And I click on the "submit" "element by type"
    When I click on the "EC_PAPER_NAME" "Product Factory edit button"
    Then Attribute "tabindex" of "Save" "Product Factory button" should have value "-1"

  @Negative @P2 #TC-831
  Scenario: Amend a Paper Where Description Already Exists
    Given I execute "Create Paper" reusable step replacing some steps
      |5|I set "PaperDescriptionNew[######]" text to the "Description" "Product Factory text field"|
      |6|I set "PaperNameNew[######]" text to the "Name" "Product Factory text field"|
      |10|I set "EC_PAPER_DESCRIPTION_NEW" text to the "Search" "Product Factory text field" from keyboard   |
      |12|I should see the "EC_PAPER_NAME_NEW" element                                       |
    When I click on the "Programme" "Product Factory navigation item"
    When I click on the "Papers" "Product Factory navigation sub item"
    Then I set "EC_PAPER_DESCRIPTION" text to the "Search" "Product Factory text field" from keyboard
    And I click on the "submit" "element by type"
    When I click on the "EC_PAPER_DESCRIPTION" "Product Factory edit button"
    And I set "EC_PAPER_DESCRIPTION_NEW" text to the "Description" "Product Factory text field"
    And I click on the "Save" "Product Factory button"
    Then I should see the "Description must be unique" "message"

  @Positive @Regression @P1 #TC-1552
  Scenario: Amend a Paper Changing Body And Level
    When I execute "Create Body Financial Dimension" reusable step
    When I execute "Create Body" reusable step
    When I execute "Create Body" reusable step replacing some steps
      |4|I set "BodyShortNameNew[######]" text to the "Short Name" "Product Factory text field"|
      |5|I set "BodyNameNew[######]" text to the "Name" "Product Factory text field"           |
    When I execute "Create Level" reusable step
    When I execute "Create Level" reusable step replacing some steps
      |4|I set "LevelShortNameNew[######]" text to the "Short Name" "Product Factory text field"|
      |5|I set "LevelNameNew[######]" text to the "Name" "Product Factory text field"           |
    When I execute "Link Body To Level" reusable step
    When I execute "Link Body To Paper" reusable step
    When I execute "Link Paper To Level" reusable step
    When I execute "Link Body To Level" reusable step replacing some steps
      |3|I click on the "EC_BODY_NAME_NEW" "Product Factory Link Levels button"  |
      |4|I click on the "EC_LEVEL_NAME_NEW" "Product Factory dialog checkbox"|
    When I execute "Link Body To Paper" reusable step replacing some steps
      |6|I click on the "EC_BODY_NAME_NEW" "Product Factory select button"|
    When I execute "Link Paper To Level" reusable step replacing some steps
      |6|I "check" "EC_LEVEL_NAME_NEW" "Product Factory dialog checkbox"|