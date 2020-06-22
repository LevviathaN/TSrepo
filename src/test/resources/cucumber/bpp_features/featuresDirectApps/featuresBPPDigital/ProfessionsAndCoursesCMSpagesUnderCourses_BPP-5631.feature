@DigitalSite
Feature: BPP Digital Professions and Courses CMS pages Under Courses
  As an admin
  I expect "Professions (New) and Courses (new)" CMS pages to appear under "courses" in search
  Instead of under "Other pages"

  Background:
    Given I execute "Log In to Hub as Admin" reusable step

  @Positive @Smoke @BET #TC-2679
  Scenario: Professions and Courses CMS pages Under Courses
    Then I execute "Admin Hub Navigate to new Courses Pages" reusable step
    When I click on the "Add" "BPP Digital Admin Page Button"