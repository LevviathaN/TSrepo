@ProductFactory @Bodies
Feature: Reference Data - Body - BPP-369
  As a Senior Product Setter
  I need to create a new Body (Professional Body / Awarding Body)
  So that I can associate new Courses to that Body

  Background:
    Given I execute "Log In" reusable step
    And I execute "Create Body Financial Dimension" reusable step

  @Positive @Create
  Scenario: Add a new body using a modal
    When I click on the "Programme" "Product Factory navigation item"
    When I click on the "Bodies" "Product Factory navigation sub item"
    Then I click on the "Create" "Product Factory button"
    And I set "BodyShortName[####]" text to the "Short Name" "Product Factory text field"
    And I set "BodyName[####]" text to the "Name" "Product Factory text field"
    And I click on the "Change" "Product Factory button"
    And I click on the "EC_FD_CODE" "Product Factory change modal option"
    And I click on the "Save" "Product Factory button"
    Then I should see the "EC_BODY_SHORT_NAME" element

  @Negative
  Scenario: Submitting incomplete fields
    When I click on the "Programme" "Product Factory navigation item"
    When I click on the "Bodies" "Product Factory navigation sub item"
    Then I click on the "Create" "Product Factory button"
    And I click on the "Change" "Product Factory button"
    And I click on the "EC_FD_CODE" "Product Factory change modal option"
    Then Attribute "tabindex" of "Save" "Product Factory button" should have value "-1"

  @Negative @ErrorChecking
  Scenario: Add a body where Short Name already exists
    When I execute "Create Body" reusable step
    When I click on the "Programme" "Product Factory navigation item"
    When I click on the "Bodies" "Product Factory navigation sub item"
    Then I click on the "Create" "Product Factory button"
    And I set "EC_BODY_SHORT_NAME" text to the "Short Name" "Product Factory text field"
    And I set "BodyName[####]" text to the "Name" "Product Factory text field"
    And I click on the "Change" "Product Factory button"
    And I click on the "EC_FD_CODE" "Product Factory change modal option"
    And I click on the "Save" "Product Factory button"
    Then I should see the "Short Name must be unique" "message"