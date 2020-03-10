@ProductFactory @Bodies
Feature: Reference Data - Body - BPP-369
  As a Senior Product Setter
  I need to create a new Body (Professional Body / Awarding Body)
  So that I can associate new Courses to that Body

  Background:
    Given I execute "Log In" reusable step replacing some steps
    |3|I fill the "Product Factory Email" field with "samuelslade@bpp.com"|
    |4|I fill the "Product Factory Password" field with "Password1"|
    And I execute "Create Body Financial Dimension" reusable step

  @Positive @Regression @P1 #TC-743
  Scenario: Add a New Body Using a Modal
    When I click on the "Programme" "Product Factory navigation item"
    When I click on the "Bodies" "Product Factory navigation sub item"
    Then I click on the "Create" "Product Factory button"
    And I set "BodyShortName[######]" text to the "Short Name" "Product Factory text field"
    And I set "BodyName[######]" text to the "Name" "Product Factory text field"
    And I click on the "Financial Dimension" "Product Factory change button"
    And I click on the "EC_BODY_FD_CODE" "Product Factory change modal option"
    And I click on the "Save" "Product Factory button"
    Then I should see the "EC_BODY_SHORT_NAME" element

  @Negative @P2 #TC-740
  Scenario: Submitting Incomplete Body Fields
    When I click on the "Programme" "Product Factory navigation item"
    When I click on the "Bodies" "Product Factory navigation sub item"
    Then I click on the "Create" "Product Factory button"
    And I click on the "Financial Dimension" "Product Factory change button"
    And I click on the "EC_BODY_FD_CODE" "Product Factory change modal option"
    Then Attribute "tabindex" of "Save" "Product Factory button" should have value "-1"

  @Negative @P2 #TC-744
  Scenario: Add a Body Where Short Name Already Exists
    When I execute "Create Body" reusable step
    When I click on the "Programme" "Product Factory navigation item"
    When I click on the "Bodies" "Product Factory navigation sub item"
    Then I click on the "Create" "Product Factory button"
    And I set "EC_BODY_SHORT_NAME" text to the "Short Name" "Product Factory text field"
    And I set "BodyName[######]" text to the "Name" "Product Factory text field"
    And I click on the "Financial Dimension" "Product Factory change button"
    And I click on the "EC_BODY_FD_CODE" "Product Factory change modal option"
    And I click on the "Save" "Product Factory button"
    Then I should see the "Short Name must be unique" "message"