@ProductFactory @DigitalContent
Feature: Digital Content Id - Create - BPP-5605
  As a Senior Product Setter
  I want to create a Digital Content Id
  so that Course(s) can be given a Digital Content Id.

  Background:
    Given I execute "Log In" reusable step
    And I execute "Remember Variables " reusable step

  @Positive @P1 #TC-5487
  Scenario: Create Digital Content University Type
    When I click on the "Delivery" "Product Factory navigation item"
    When I click on the "Digital Content" "Product Factory navigation sub item"
    Then I click on the "Create" "Product Factory button"
    And I click on the "University" "text contained in SPAN element"
    And I click on the "Totara" "Product Factory button"
    And I click on the "Save" "Product Factory button"
    And I wait for "6" seconds
    And I click on the "University Programme" "Product Factory dropdown"
    And I click on the "EC_PROGRAMME_NAME" "Product Factory dropdown option"
    And I click on the "Programme Cohort" "Product Factory dropdown"
    And I click on the "EC_COHORT_NAME" "Product Factory dropdown option"
    And I click on the "Next" "Product Factory button"
    And I click on the "Content Restriction" "Product Factory dropdown"
    And I click on the "Cohort Start Teaching Date" "Product Factory dropdown option"
    And I click on the "Next" "Product Factory button"
    And I set "Suffix[###]" text to the "Name Suffix" "Product Factory text field"
    And I click on the "Finish" "Product Factory button"
    Then I should see the "Edit Digital Content" element

  @Positive @P1 @BlockedByIssue #TC-5488 Blocked by BPP-15863
  Scenario: Amend Digital Content University Type
    When I click on the "Delivery" "Product Factory navigation item"
    When I click on the "Digital Content" "Product Factory navigation sub item"
    Then I click on the "EC_COHORT_NAME" "Product Factory edit button"
    And I click on the "EC_COHORT_NAME" "Product Factory edit button"
    And I click on the "Content Restriction" "Product Factory dropdown"
    And I click on the "Provisioning Start Date" "Product Factory dropdown option"
    Then I set "[TODAY]" text to the "Provisioning Start Date" "Product Factory text field"
    Then I click on the "Save" "Product Factory button"
    Then I should see the "Provisioning Start Date" element

  @Negative @P1 #TC-5489
  Scenario: Create Digital Content Prevent Duplicate University Type
    When I click on the "Delivery" "Product Factory navigation item"
    When I click on the "Digital Content" "Product Factory navigation sub item"
    Then I click on the "Create" "Product Factory button"
    And I click on the "University" "text contained in SPAN element"
    And I click on the "Totara" "Product Factory button"
    And I click on the "Save" "Product Factory button"
    And I wait for "6" seconds
    And I click on the "University Programme" "Product Factory dropdown"
    And I click on the "EC_PROGRAMME_NAME" "Product Factory dropdown option"
    And I click on the "Programme Cohort" "Product Factory dropdown"
    And I click on the "EC_COHORT_NAME" "Product Factory dropdown option"
    And I click on the "Next" "Product Factory button"
    And I click on the "Content Restriction" "Product Factory dropdown"
    And I click on the "Cohort Start Teaching Date" "Product Factory dropdown option"
    And I click on the "Next" "Product Factory button"
    And I set "Suffix[###]" text to the "Name Suffix" "Product Factory text field"
    And I click on the "Finish" "Product Factory button"
    Then I should see the "Digital Content already exists for the Cohort" "text contained in element"