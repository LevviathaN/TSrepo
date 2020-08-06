@ProductFactory @Sittings
Feature: Reference Data - Sitting - BPP-401
  As a Senior Product Setter
  I need to be able to create Sittings associated to a Body
  I can identify when course materials are available and exam sittings are planned for

  Background:
    Given I execute "Log In" reusable step
    When I execute "Create Body Financial Dimension" reusable step
    Then I execute "Create Body" reusable step

  @Positive @Regression @P1 #TC-835
  Scenario: Add a New Sitting Using a Modal (Link to One Body)
    When I click on the "Delivery" "Product Factory navigation item"
    When I click on the "Sittings" "Product Factory navigation sub item"
    Then I click on the "Create" "Product Factory button"
    And I set "SittingName[######]" text to the "Name" "Product Factory text field"
    And I remember "KW_AUTO_TODAY|DD/MM/YYYY" text as "EC_SITTING_START_DATE" variable
    And I set "EC_SITTING_START_DATE" text to the "Start Date" "Product Factory text field"
    And I remember "10/10/2020" text as "EC_SITTING_END_DATE" variable
    And I set "EC_SITTING_END_DATE" text to the "End Date" "Product Factory text field"
    And I "check" "EC_BODY_NAME" "Product Factory dialog checkbox"
    And I click on the "Save" "Product Factory button"
    Then I should see the "EC_SITTING_NAME" element

  @Positive @Regression @P2 #TC-836
  Scenario: Add a New Sitting Using a Modal (Link to Multiple Bodies)
    Then I click on the "Create" "Product Factory button"
    And I set "BodyShortNameNew[######]" text to the "Short Name" "Product Factory text field"
    And I set "BodyNameNew[######]" text to the "Name" "Product Factory text field"
    And I click on the "Change" "button"
    And I click on the "EC_BODY_FD_CODE" "Product Factory change modal option"
    And I click on the "Save" "Product Factory button"
    
    When I click on the "Delivery" "Product Factory navigation item"
    When I click on the "Sittings" "Product Factory navigation sub item"
    Then I click on the "Create" "Product Factory button"
    And I set "SittingName[######]" text to the "Name" "Product Factory text field"
    And I remember "KW_AUTO_TODAY|DD/MM/YYYY" text as "EC_SITTING_START_DATE" variable
    And I set "EC_SITTING_START_DATE" text to the "Start Date" "Product Factory text field"
    And I remember "10/10/2020" text as "EC_SITTING_END_DATE" variable
    And I set "EC_SITTING_END_DATE" text to the "End Date" "Product Factory text field"
    And I "check" "EC_BODY_NAME" "Product Factory dialog checkbox"
    And I "check" "EC_BODY_NAME_NEW" "Product Factory dialog checkbox"
    And I click on the "Save" "Product Factory button"
    Then I should see the "EC_SITTING_NAME" element

  @Negative @P2 #TC-837
  Scenario: Submitting Incomplete Sitting Fields
    When I click on the "Delivery" "Product Factory navigation item"
    When I click on the "Sittings" "Product Factory navigation sub item"
    Then I click on the "Create" "Product Factory button"
    Then Attribute "tabindex" of "Save" "Product Factory button" should have value "-1"

  @Negative @P2 @NoQTest
  Scenario: Add a Sitting Where Name Already Exists
    When I execute "Create Sitting" reusable step
    When I click on the "Delivery" "Product Factory navigation item"
    When I click on the "Sittings" "Product Factory navigation sub item"
    Then I click on the "Create" "Product Factory button"
    And I set "EC_SITTING_NAME" text to the "Name" "Product Factory text field"
    And I remember "KW_AUTO_TODAY|DD/MM/YYYY" text as "EC_SITTING_START_DATE" variable
    And I set "EC_SITTING_START_DATE" text to the "Start Date" "Product Factory text field"
    And I remember "10/10/2020" text as "EC_SITTING_END_DATE" variable
    And I set "EC_SITTING_END_DATE" text to the "End Date" "Product Factory text field"
    And I "check" "EC_BODY_NAME" "Product Factory dialog checkbox"
    And I click on the "Save" "Product Factory button"
    Then I should see the "The combination of Name and Dates must be unique" "message"