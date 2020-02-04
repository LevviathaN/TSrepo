@DirectApps @MitigatingCircumstances
Feature: Accept Mitigating Circumstances Application

  Background:
    Given I execute "Register New Student Account" reusable step
    When I execute "Create Mitigating Circumstances Application" reusable step

  @Positive #TC-1266 #TC-1257
  Scenario: Accept Mitigating Circumstances
    And I execute "Complete Mitigating Circumstances" reusable step
    Then I execute "Submit Application Hub" reusable step
    And I execute "Log Out from Hub Student" reusable step
    And I execute "Log In to Hub as Admin" reusable step
    And I execute "Accept Mitigating Circumstances As Admin" reusable step

  @Positive #TC-1264
  Scenario: Send Emails to Students
    And I execute "Complete Mitigating Circumstances" reusable step
    Then I execute "Submit Application Hub" reusable step
    And I execute "Log Out from Hub Student" reusable step
    And I execute "Log In to Hub as Admin" reusable step
    And I execute "Mitigating Circumstances Admin Status Change" reusable step