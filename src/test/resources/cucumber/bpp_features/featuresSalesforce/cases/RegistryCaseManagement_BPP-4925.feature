@Salesforce @RegistryCaseManagement
Feature: Registry Case Management
  As a Registry Salesforce Classic User switching to Lightning Experience,
  I want Registry Case Management to perform as expected

  @EndToEnd #TC-2535
  Scenario: Registry Case Management End To End
    Given I execute "Log In To Salesforce" reusable step
    And I execute "Create ID Card Registry Case" reusable step
    Then I click on the "Salesforce Edit Case Status" button by JS
    And I click on the "Salesforce Case Status Dropdown" element
    And I click on the "In Progress" "Salesforce cases status option"
    And I click on the "Save" "Product Factory button title"
    And I wait for "2" seconds
    And I validate text "In Progress" to be displayed for "Salesforce Case Status Data Field" element
    Then I click on the "Salesforce Edit Case Status" button by JS
    And I click on the "Salesforce Case Status Dropdown" element
    And I click on the "Ready to Close" "Salesforce cases status option"
    And I click on the "Save" "Product Factory button title"
    And I wait for "2" seconds
    And I validate text "Ready to Close" to be displayed for "Salesforce Case Status Data Field" element

  @AssignmentRules #TC-2536
  Scenario: Registry Case Management Verify Case Assignment
    Given I execute "Log In To Salesforce" reusable step
    And I execute "Validate All Registry Cases Types" reusable step

    Given I execute "Log In To Salesforce" reusable step
    When I click on the "Cases" "Salesforce navigation menu dropdown"
    And I click on the "New Case" "Salesforce navigation menu dropdown option"
    And I click on the "Student Query" "Salesforce radiobutton"
    And I click on the "Next" "button"
    And I click on the "Study Location" "Salesforce dropdown field"
    And I click on the "Case Origin" "Salesforce dropdown field"
    And I click on the "Face-to-Face" "Salesforce cases dropdown option"
    And I click on the "Query Type" "Salesforce dropdown field"
    And I click on the "Assessments" "Salesforce cases dropdown option"
    And I click on the "Query Subtype" "Salesforce dropdown field"
    Then I click on the "Appeals" "Salesforce cases dropdown option"
    And I click on the "Study Location" "Salesforce dropdown field"
    Then I click on the "Birmingham" "Salesforce cases dropdown option"
    And I click on the "School" "Salesforce dropdown field"
    Then I click on the "Law" "Salesforce cases dropdown option"
    And I click on the "Save" "Product Factory button title"
    And I wait for "3" seconds
    And I validate text "CONTAINS=Programme Support" to be displayed for "Salesforce Case Owner Data Field" element
    And I capture text data "Salesforce Case Number Data Field" as "EC_CASENUMBER" variable
    Then I click on the "Salesforce Edit Case Status" button by JS
    And I click on the "Query Subtype" "Salesforce dropdown field"
    Then I click on the "Exemptions" "Salesforce cases dropdown option"
    And I click on the "Save" "Product Factory button title"
    And I wait for "3" seconds
    And I validate text "CONTAINS=Academic Admin" to be displayed for "Salesforce Case Owner Data Field" element
    Then I click on the "Salesforce Edit Case Status" button by JS
    And I click on the "Query Subtype" "Salesforce dropdown field"
    Then I click on the "Transcripts" "Salesforce cases dropdown option"
    And I click on the "Save" "Product Factory button title"
    And I wait for "3" seconds
    And I validate text "CONTAINS=Exam Queries" to be displayed for "Salesforce Case Owner Data Field" element
    Then I click on the "Salesforce Edit Case Status" button by JS
    And I click on the "Query Subtype" "Salesforce dropdown field"
    Then I click on the "Learning Support" "Salesforce cases dropdown option"
    And I click on the "Save" "Product Factory button title"
    And I wait for "3" seconds
    And I validate text "CONTAINS=Exam Ops" to be displayed for "Salesforce Case Owner Data Field" element
    Then I click on the "Salesforce Edit Case Status" button by JS
    And I click on the "Query Subtype" "Salesforce dropdown field"
    Then I click on the "Overseas Exams" "Salesforce cases dropdown option"
    And I click on the "Save" "Product Factory button title"
    And I wait for "3" seconds
    And I validate text "CONTAINS=Overseas Exams" to be displayed for "Salesforce Case Owner Data Field" element

  @Milestones #TC-2537
  Scenario: Registry Case Management Verify Milestones
