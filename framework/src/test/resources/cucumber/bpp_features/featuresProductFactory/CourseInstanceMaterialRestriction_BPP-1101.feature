@ProductFactory @Courses @Materials
Feature: Course Instance - Link Materials restrictions - BPP-1101
  As a Product Setter
  I want to be able to only link Materials to a Course Instance that are allowed by the data rules
  so that I can sell a Course.

  Background:
    Given I execute "Generate ISBN" reusable step
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
    And I execute "Create Pricing Matrix" reusable step
    And I execute "Create Pricing Matrix Full" reusable step
    And I execute "Create Material Type Financial Dimension" reusable step
    And I execute "Create Material Type" reusable step
    And I execute "Create Session Duration" reusable step
    And I execute "Create Client" reusable step

  @Negative @P1 #TC-1079
  Scenario: Verify Link Materials Restriction Negative
    Given I execute modified "Create Material" reusable step
      |7|Add|I "check" "Is Course Material" "Product Factory checkbox"|
      |17|Delete|                                                     |
      |18|Delete|                                                     |
    And I execute "Create Course Instance" reusable step
    When I click on the "Products" "Product Factory navigation item"
    When I click on the "Courses" "Product Factory navigation sub item"
    And I set "EC_BODY_SHORT_NAME" text to the "Search" "Product Factory text field" from keyboard
    And I click on the "submit" "element by type"
    And I click on the "EC_BODY_SHORT_NAME" "Product Factory edit button"
    And I click on the "Materials" element
    And I click on the "Add Material" "button"
    Then I shouldn't see the "EC_MATERIAL_TYPE_NAME" element

  @Positive @P1 #TC-1077, TC-1072, TC-1073
  Scenario: Verify Link Materials Restriction Positive
    And I execute "Create Material" reusable step
    And I execute "Create Course Instance" reusable step
    When I click on the "Products" "Product Factory navigation item"
    When I click on the "Courses" "Product Factory navigation sub item"
    And I set "EC_BODY_SHORT_NAME" text to the "Search" "Product Factory text field" from keyboard
    And I click on the "submit" "element by type"
    And I click on the "EC_BODY_SHORT_NAME" "Product Factory edit button"
    And I click on the "Materials" element
    And I click on the "Add Material" "button"
    Then I should see the "EC_MATERIAL_TYPE_NAME" element

  @Positive @P1 #TC-1063
  Scenario: Verify Already Selected Material Is Not Displayed In Dropdown
    Given I execute "Create Material" reusable step
    And I execute "Create Course Instance" reusable step
    When I click on the "Products" "Product Factory navigation item"
    When I click on the "Courses" "Product Factory navigation sub item"
    And I set "EC_BODY_SHORT_NAME" text to the "Search" "Product Factory text field" from keyboard
    And I click on the "submit" "element by type"
    And I click on the "EC_BODY_SHORT_NAME" "Product Factory edit button"
    And I click on the "Materials" element
    And I click on the "Add Material" "button"
    Then I "check" "EC_MATERIAL_TYPE_NAME" "Product Factory checkbox"
    And I click on the "Save" "button"
    And I click on the "Add Material" "button"
    Then I shouldn't see the "EC_MATERIAL_TYPE_NAME" element

  @Negative @P1 #TC-1075
  Scenario: Add Expired Material
    Given I execute "Create Material" reusable step replacing some steps
      |10|I set "10/10/2019" text to the "Expiry Date" "Product Factory text field"|
    And I execute "Create Course Instance" reusable step
    When I click on the "Products" "Product Factory navigation item"
    When I click on the "Courses" "Product Factory navigation sub item"
    And I set "EC_BODY_SHORT_NAME" text to the "Search" "Product Factory text field" from keyboard
    And I click on the "submit" "element by type"
    And I click on the "EC_BODY_SHORT_NAME" "Product Factory edit button"
    And I click on the "Materials" element
    And I click on the "Add Material" "button"
    Then I shouldn't see the "EC_MATERIAL_TYPE_NAME" element