@ProductFactory @Bodies @ParametricReusable
Feature: Product Factory Random Bodies
  As a product setter
  I want to be able to create Bodies with random names in Product Factory
  In order to (just cause)

  Background:
    Given I execute "Log In" reusable step with some additional steps
      |4|I fill the "xpath=//input[@placeholder='Email']" field with "Ruliaaa"|
    And I execute "Create Body Financial Dimension" reusable step without some steps

  @Create @Random @Singlegi
  Scenario: Create New Body Using Random Data
    When I click on the "work" link
    When I click on the "Bodies" link
    Then I click on the "Create" button
    And I remember "AutoBodyShortName<KW_AUTO_RANDOMNUMBER|#######>" text as "EC_BODY_SHORT_NAME" variable
    And I fill the "Short Name" PF field with "EC_BODY_SHORT_NAME"
    And I remember "AutoBodyName<KW_AUTO_RANDOMNUMBER|#######>" text as "EC_BODY_NAME" variable
    And I fill the "Name" PF field with "EC_BODY_NAME"
    And I click on the "Change" button
    And I select "EC_FD_CODE" from PF dialog
    And I click on the "Save" button
    Then I should see the "EC_BODY_SHORT_NAME" element