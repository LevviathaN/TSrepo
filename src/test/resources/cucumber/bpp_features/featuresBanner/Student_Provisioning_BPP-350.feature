@Banner @Student
Feature: Admitting a single student in Banner - BPP-350
  As a member of the Admissions team, I want to admit a student in Banner.

  Background:
    Given I execute "Log In To Banner" reusable step
    And I execute "Navigate To Form" reusable step

  @Positive @P1
  Scenario: Admit Student in Banner
    Given I switch to "Banner Frame" frame
    When I fill the "Banner Id Text" field with "EC_BANNER_ID"
    And I fill the "Banner Term Code" field with "201617"
    Then I click on the "Banner Go" button
    And I click on the "Banner SAADCRV Set Desicison Insert Button" button
    Then I fill the "Banner Decision Code" field with "AM"
    And I press "MD_COMMON_KEYBOARD_ENTER" for "Banner Decision Code"
    Then I click on the "Save" "button"
    And I should see the "Decision Processed" message