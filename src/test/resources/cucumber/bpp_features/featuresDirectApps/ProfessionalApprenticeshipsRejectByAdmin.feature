@DirectApps @ProfessionalApprenticeships
Feature: Submit Professional Apprenticeship Applications

  Background:
    Given I execute "Register New Student Account" reusable step

  @Negative @RejectByAdmin #TC-1023
  Scenario: Submit Professional Apprenticeship Chartered Manager Degree
    When I execute "Create Professional Apprenticeships Application Chartered Manager" reusable step
    Given I execute "Complete Professional Apprenticeships Application Chartered Manager" reusable step
    Then I execute "Submit Application Hub" reusable step
    And I execute "Log Out from Hub Student" reusable step
    And I execute "Log In to Hub as Admin" reusable step
    And I execute "Reject University Degree Apprenticeships As Admin" reusable step
    And I execute "Log Out from Hub Admin" reusable step
    Then I execute "Log In to Hub as Student" reusable step
    And I click on the "Direct App My Applications Left Menu Link" link by JS
    Then I validate text "CONTAINS=Unfortunately," to be displayed for "Direct App Validate As Student Application Accepted" element