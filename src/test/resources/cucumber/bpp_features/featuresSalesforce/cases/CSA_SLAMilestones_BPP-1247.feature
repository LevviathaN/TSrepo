@Salesforce @CSA
Feature: CSA - SLA Milestones
  As a Salesforce user/CSA
  I want to ensure that all cases assigned to the Customer Service Team Queue
  and are responded to within the agreed SLAs.

  @Milestones
  Scenario: CSA - SLA Milestones
    Given I execute "Log In To Salesforce" reusable step
    When I click on the "Cases" "Salesforce navigation menu dropdown"
    And I click on the "New Case" "Salesforce navigation menu dropdown option"
    And I click on the "Customer Service" "Salesforce radiobutton"
    And I click on the "Next" "button"
    And I set "Automation CSA" text to the "Student" "Salesforce search field"
    And I click on the "Automation CSA" "Salesforce cases search student"
    And I click on the "Case Origin" "Salesforce dropdown field"
    And I click on the "Email" "Salesforce cases dropdown option"
    And I click on the "Query Type" "Salesforce dropdown field"
    And I click on the "My online learning and VLE" "Salesforce cases dropdown option"
    And I click on the "Query Subtype" "Salesforce dropdown field"
    Then I click on the "I need to remove old papers from my VLE" element
    And I click on the "Salesforce CS Ready to Close" element
    And I click on the "Save" "Product Factory button title"
    And I wait for "3" seconds
    Then I should see the "Salesforce Case Milestone Name" element
    Then I validate text "CONTAINS=16 hr" to be displayed for "Salesforce Case Milestone Hours" element
    And I capture text data "Salesforce Case Number Data Field" as "EC_CASENUMBER" variable