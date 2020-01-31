@ProductFactory @DeactivationReasons
Feature: Reference Data - Course Deactivation Reasons - BPP-1613
  As a Senior Product Setter
  I want to be able to create, view and amend Course Deactivation Reasons records
  so that they can be associated to the audit when deactivating Courses.

  Background:
    Given I execute "Log In" reusable step

  @Positive @Regression @P1 #TC-975
  Scenario: Add a New Deactivation Reason Using a Modal
    When I click on the "Miscellaneous" "Product Factory navigation item"
    When I click on the "Deactivation Reasons" "Product Factory navigation sub item"
    Then I click on the "Create" "Product Factory button"
    And I set "DeactivationReasonDescription[####]" text to the "Description" "Product Factory text field"
    And I click on the "Save" "Product Factory button"
    Then I should see the "EC_DEACTIVATION_REASON_DESCRIPTION" element

  @Negative @P2 #TC-977
  Scenario: Add a Deactivation Reason Where Description Already Exists
    When I execute "Create Deactivation Reason" reusable step
    When I click on the "Miscellaneous" "Product Factory navigation item"
    When I click on the "Deactivation Reasons" "Product Factory navigation sub item"
    Then I click on the "Create" "Product Factory button"
    And I set "EC_DEACTIVATION_REASON_DESCRIPTION" text to the "Description" "Product Factory text field"
    And I click on the "Save" "Product Factory button"
    Then I should see the "Description must be unique" "message"

  @Positive @Regression @P1 @Amend #TC-978
  Scenario: Amend a Deactivation Reason Using a Modal
    When I execute "Create Deactivation Reason" reusable step
    When I click on the "Miscellaneous" "Product Factory navigation item"
    When I click on the "Deactivation Reasons" "Product Factory navigation sub item"
    Then I click on the "EC_DEACTIVATION_REASON_DESCRIPTION" "Product Factory edit button"
    And I set "DeactivationReasonDescription[####]" text to the "Description" "Product Factory text field"
    And I click on the "Save" "Product Factory button"
    Then I should see the "EC_DEACTIVATION_REASON_DESCRIPTION" element