@ProductFactory @CBA @Courses @Amend
Feature: CBA - Disable Course Edit Page - BPP-3214
  As a Product Setter
  I want the Course Edit page to be disabled when the course is a part of a CBA
  so the Course can not be changed.

  Background:
    Given I execute "Log In" reusable step
    When I execute "Create Body Financial Dimension" reusable step
    And I execute "Create Body" reusable step
    And I execute "Create VAT Rule" reusable step
    And I execute "Create Sitting" reusable step
    And I execute "Create Session Duration" reusable step with some additional steps
      |6|I "check" "Allowed for CBA" "Product Factory checkbox"|
    And I execute "Create Course Type Financial Dimension" reusable step
    And I execute "Create Course Type" reusable step
    And I execute "Create Location Financial Dimension" reusable step
    And I execute "Create Region Financial Dimension" reusable step
    And I execute "Create Region" reusable step
    And I execute "Create Location" reusable step
    And I execute "Create Client" reusable step
    And I execute "Create Level" reusable step
    And I execute "Create Paper" reusable step with some additional steps
      |5|I "check" "CBA Paper?" "Product Factory checkbox"|
      |6|I set "1" text to the "Time in Hours" "Product Factory text field"|
    And I execute "Link Body To Level" reusable step
    And I execute "Link Body To Paper" reusable step
    And I execute "Link Paper To Level" reusable step
    And I execute "Create Pricing Matrix" reusable step
    And I execute "Create Pricing Matrix Full" reusable step


  @Positive @Regression @P1 #TC-2587
  Scenario: Disable Course Edit Page
    When I click on the "exit_to_app" button
    And I execute "Log In" reusable step replacing some steps
      |3|I fill the "Product Factory Email" field with "MD_COMMON_CREDENTIALS_PRODUCTFACTORYJUNIORPRODUCTSETTER"|
      |4|I fill the "Product Factory Password" field with "MD_COMMON_CREDENTIALS_PRODUCTFACTORYJUNIORPSPASSWORD"|
    And I execute "Create CBA Record" reusable step
    When I click on the "Create" "Product Factory button"
    Then I "check" "EC_BODY_SHORT_NAME" "Product Factory checkbox"
    And I click on the "Next" "Product Factory button"
    And I click on the "Next" "Product Factory button"
    And I click on the "Finish" "Product Factory button"
    And I wait for "3" seconds

#    When I click on the "Products" "Product Factory navigation item"
#    When I click on the "Courses" "Product Factory navigation sub item"
#    And I set "EC_BODY_SHORT_NAME" text to the "Body" "Product Factory text field"
#    And I click on the "Search" "Product Factory button"
#    And I click on the "EC_BODY_SHORT_NAME" "Product Factory edit button"
#
#    Then I shouldn't see the "Level" "Product Factory edit button"
#    And I shouldn't see the "Paper" "Product Factory edit button"
#    And I shouldn't see the "Sitting" "Product Factory edit button"