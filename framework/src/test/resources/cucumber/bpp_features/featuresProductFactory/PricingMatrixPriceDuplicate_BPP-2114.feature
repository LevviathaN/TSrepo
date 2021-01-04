@ProductFactory @PricingMatrices
Feature: Pricing Matrix - price creation validation to prevent duplicates - BPP-2114
  As a Senior Product Setter
  I want to there to be validation that prevents Prices based on the combinations that have already been setup being created again,
  so that I do not create duplicate Prices, as this will break Course Price lookup.

  Background:
    Given I execute "Log In" reusable step
    When I execute "Create Body Financial Dimension" reusable step
    And I execute "Create Body" reusable step
    Then I execute "Create Sitting" reusable step
    And I execute "Create VAT Rule" reusable step
    And I execute "Create Course Type Financial Dimension" reusable step
    And I execute "Create Course Type" reusable step
    And I execute "Create Region Financial Dimension" reusable step
    And I execute "Create Region" reusable step
    And I execute "Create Paper" reusable step
    And I execute "Link Body To Paper" reusable step
    When I execute "Create Paper" reusable step replacing some steps
      |5|I set "PaperDescriptionNew[######]" text to the "Description" "Product Factory text field"|
      |9|I set "EC_PAPER_DESCRIPTION_NEW" text to the "Search" "Product Factory text field" from keyboard|
      |11|I should see the "EC_PAPER_DESCRIPTION_NEW" element                                     |
    When I execute "Link Body To Paper" reusable step replacing some steps
      |3|I set "EC_PAPER_DESCRIPTION_NEW" text to the "Search" "Product Factory text field" from keyboard|
      |5|I click on the "EC_PAPER_DESCRIPTION_NEW" "Product Factory Change Body button"|
    And I execute "Create Pricing Matrix" reusable step
    And I execute "Create Pricing Matrix Full" reusable step

  @Negative @P1 @Duplicate @Runn #TC-1158
  Scenario: Add Duplicate Price in Pricing Matrix
    And I execute "Create Course Type" reusable step replacing some steps
      |4|I set "CourseTypeDescriptionNew[######]" text to the "Description" "Product Factory text field"|
      |13|I set "EC_COURSE_TYPE_DESCRIPTION_NEW" text to the "Search" "Product Factory text field" from keyboard |
      |15|I should see the "EC_COURSE_TYPE_DESCRIPTION_NEW" element                                     |
    When I execute "Create Region" reusable step replacing some steps
      |4|I set "RegionNameNew[######]" text to the "Name" "Product Factory text field"|
      |8|I should see the "EC_REGION_NAME_NEW" element                              |
    When I click on the "Financials" "Product Factory navigation item"
    When I click on the "Prices" "Product Factory navigation sub item"
    When I click on the "EC_BODY_NAME" "Product Factory edit button"
    Then I click on the "Create" "Product Factory button"
    Then I set "EC_COURSE_TYPE_DESCRIPTION_NEW" text to the "Search" "Product Factory text field" from keyboard
    And I click on the "submit" "element by type"
    And I click on the "EC_COURSE_TYPE_DESCRIPTION_NEW" "Product Factory select button"
    And I "check" "EC_REGION_NAME_NEW" "Product Factory dialog checkbox"
    And I click on the "Next" button
    And I "check" "EC_PAPER_DESCRIPTION_NEW" "Product Factory dialog checkbox"
    And I click on the "Next" button
    And I set "888" text to the "Enter a Price (£)" "Product Factory text field"
    And I click on the "Next" button
    And I click on the "Finish" button