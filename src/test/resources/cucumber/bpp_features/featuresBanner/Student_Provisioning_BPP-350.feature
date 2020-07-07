@Banner
Feature: Admitting a single student in Banner - BPP-350
  As a member of the Admissions team, I want to admit a student in Banner.

  @AdmitStudent @EndToEnd
  Scenario: Admit Student in Banner
    Given I execute "Log In To Salesforce" reusable step
    Given I execute "Create Student Account" reusable step
    And I execute "Create Address" reusable step
    And I execute "Get Profile ID" reusable step
    And I execute "Create Opportunity" reusable step
    And I click on the "EC_OPPORTUNITY_NAME" "Salesforce link"
    And I click on the "Product Catalog Items" "Salesforce related new button"
    And I click on the "New" "Salesforce dropdown option"
    And I fill the "Salesforce Offering Text Field" field with "MD_COMMON_VALIDDATA_SFOFFERING"
    And I click on the "Salesforce Search" button
    And I "check" "Salesforce Offering Checkbox" checkbox
    And I click on the "Salesforce Add Products" button
    Given I execute "Submit Application" reusable step
    Given I execute "Log In To Banner" reusable step
    And I execute "Navigate To Form" reusable step
    When I fill the "Banner Id Text" field with "EC_BANNER_ID"
    And I fill the "Banner Term Code" field with "201819"
    Then I click on the "Banner Go" button
    And I click on the "Banner SAADCRV Set Desicison Insert Button" button
    Then I fill the "Banner Decision Code" field with "AM"
    And I press "MD_COMMON_KEYBOARD_ENTER" for "Banner Decision Code"
    Then I click on the "Banner Save" button
    And I should see the "Decision Processed" message in "Banner Frame" frame