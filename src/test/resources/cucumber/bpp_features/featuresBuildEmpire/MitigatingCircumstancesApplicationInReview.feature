@DirectApps @MitigatingCircumstances
Feature: Mitigating Circumstances Application in Review

  Background:
    Given I execute "Register New Student Account" reusable step
    When I execute "Create Mitigating Circumstances Application" reusable step

  @Negative #TC-1265
  Scenario: Mitigating Circumstances Application in Review
    And I execute "Complete Mitigating Circumstances" reusable step
    Then I execute "Submit Application Hub" reusable step
    And I execute "Log Out from Hub Student" reusable step
    And I execute "Log In to Hub as Admin" reusable step
    And I execute "Reject Mitigating Circumstances As Admin" reusable step
    And I execute "Log Out from Hub Admin" reusable step
    Then I execute "Log In to Hub as Student" reusable step
    Given I click on the "Direct App My Applications Left Menu Link" button by JS
    Then I validate text "CONTAINS=Unfortunately, your application wasn't successful this time" to be displayed for "Direct App Validate As Student Application Accepted" element
    And I execute "Guerrillamail Mitigating Circustances Reject Validation" reusable step
    And I execute "Log In to Hub as Admin" reusable step
    And I execute "Put in Review Mitigating Circumstances" reusable step