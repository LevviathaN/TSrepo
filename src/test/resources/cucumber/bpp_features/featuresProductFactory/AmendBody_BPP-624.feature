@ProductFactory @Bodies @Amend
Feature: Amend Reference Data - Body - BPP-624
  As a Senior Product Setter
  I need to search for and Amend a Body (Professional Body / Awarding Body)
  So that I can associate new Courses to that Body

  Background:
    Given I execute "Log In" reusable step
    And I execute "Create Body Financial Dimension" reusable step
    And I execute "Create Body" reusable step

  @Positive @Regression @P1
  Scenario: Amend a Body Using a Modal
    When I click on the "Programme" "Product Factory navigation item"
    When I click on the "Bodies" "Product Factory navigation sub item"
    When I click on the "EC_BODY_NAME" "Product Factory edit button"
    And I set "BodyShortName[####]" text to the "Short Name" "Product Factory text field"
    And I set "BodyName[####]" text to the "Name" "Product Factory text field"
    And I click on the "Save" "Product Factory button"
    Then I should see the "EC_BODY_SHORT_NAME" element

  @Negative @P2
  Scenario: Submitting Incomplete Body Fields
    When I click on the "Programme" "Product Factory navigation item"
    When I click on the "Bodies" "Product Factory navigation sub item"
    When I click on the "EC_BODY_NAME" "Product Factory edit button"
    And I set "" text to the "Short Name" "Product Factory text field"
    And I set "" text to the "Name" "Product Factory text field"
    Then Attribute "tabindex" of "Save" "Product Factory button" should have value "-1"

  @Negative @P2
  Scenario: Amend a Body Where Short Name Already Exists
    Given I execute "Create Body" reusable step replacing some steps
    |4|I set "BodyShortNameNew[####]" text to the "Short Name" "Product Factory text field"|
    |5|I set "BodyNameNew[####]" text to the "Name" "Product Factory text field"|
    When I click on the "Programme" "Product Factory navigation item"
    When I click on the "Bodies" "Product Factory navigation sub item"
    When I click on the "EC_BODY_NAME" "Product Factory edit button"
    And I set "EC_BODY_SHORT_NAME_NEW" text to the "Short Name" "Product Factory text field"
    And I set "EC_BODY_NAME_NEW" text to the "Name" "Product Factory text field"
    And I click on the "Save" "Product Factory button"
    Then I should see the "Short Name must be unique" "message"