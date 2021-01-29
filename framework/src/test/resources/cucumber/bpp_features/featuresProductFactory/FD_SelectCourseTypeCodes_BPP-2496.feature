@ProductFactory @FinancialDimensions @CourseTypes
Feature: Financial Dimension - Select Course Type Codes - BPP-2496
  As a Senior Product Setter
  I want to be able to select 2 Financial Dimensions on an individual Course Type
  so that I can track both the "AX Cost Centre" and "AX Project" codes for accounting purposes.

  Background:
    Given I execute "Generate ISBN" reusable step
    Given I execute "Log In" reusable step
    When I execute "Create Course Type Financial Dimension" reusable step
    Then I execute "Create VAT Rule" reusable step

  @Negative @Regression @P1 #TC-1772
  Scenario: Edit Financial Dimension for the Course Type that is in use
    When I execute "Create Body Financial Dimension" reusable step
    And I execute "Create Course Type Financial Dimension" reusable step replacing some steps
      |4|I set "CourseTypeFDCodeTwo[######]" text to the "Code" "Product Factory text field"|
    And I execute "Create Course Type Financial Dimension" reusable step replacing some steps
      |4|I set "CourseTypeFDCodeThree[######]" text to the "Code" "Product Factory text field"|
    And I execute "Create Body" reusable step
    And I execute "Create Course Type" reusable step with some additional steps
      |8|I should see the "EC_COURSE_TYPE_FD_CODE_TWO" element|
      |9|I should see the "EC_COURSE_TYPE_FD_CODE_THREE" element|
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
    And I execute "Create Material" reusable step
    And I execute "Create Course Instance" reusable step
    And I execute "Create Course Type Financial Dimension" reusable step

    When I click on the "Types" "Product Factory navigation item"
    When I click on the "Course Types" "Product Factory navigation sub item"
    And I set "EC_COURSE_TYPE_DESCRIPTION" text to the "Search" "Product Factory text field" from keyboard
    And I click on the "submit" "element by type"
    When I click on the "EC_COURSE_TYPE_DESCRIPTION" "Product Factory edit button"
    And I click on the "Cost Centre Financial Dimension" "Product Factory dropdown"
    And I click on the "EC_COURSE_TYPE_FD_CODE" "Product Factory dropdown option"
    And I click on the "Project Financial Dimension" "Product Factory dropdown"
    And I click on the "EC_COURSE_TYPE_FD_CODE" "Product Factory dropdown option"
    And I click on the "Save" "Product Factory button"
    Then I should see the "The Course Type is already linked to one or more Prices, so you cannot change the Course Type Financial Dimensions" element