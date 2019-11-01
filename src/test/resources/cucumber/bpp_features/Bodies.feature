@Bodies
Feature: Product Factory Bodies
    As a product setter
    I want to be able to create Bodies in Product Factory
    In order to (just cause)

  @Create @Single
  Scenario: Create New Body
    Given I execute "Log In" reusable step
#    And I execute "Create Body Financial Dimension" reusable step
    When I click on the "Bodies" link
    Then I click on the "Create" button
    And I fill the "Short Name" PF field with "SomeShortName1"
    And I fill the "Name" PF field with "SomeName1"
    And I click on the "Change" button
    And I select "SomeCode1234" from PF dialog
    And I click on the "Save" button
    Then I should see the "SomeShortName1" element

  @Create @Random
  Scenario: Create New Body Using Random Data
    Given I execute "Log In" reusable step
#    And I execute "Create Body Financial Dimension" reusable step
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

  @Create @Multiple
  Scenario Outline: Create New Bodies
    Given I execute "Log In" reusable step
#    And I execute "Create Body Financial Dimension" reusable step
    When I click on the "Bodies" link
    Then I click on the "Create" button
    And I fill the "Short Name" PF field with "<short_name>"
    And I fill the "Name" PF field with "<name>"
    And I click on the "Change" button
    And I select "EC_FD_CODE" from PF dialog
    And I click on the "Save" button
    Then I should see the "<short_name>" element

    Examples:
    |short_name    |name     |
    |SomeShortName3|SomeName3|
    |SomeShortName4|SomeName4|


  @Ignore
  Scenario Outline: Create New Bodies Using Random Data
    Given I execute "Log In" reusable step
#    And I execute "Create Body Financial Dimension" reusable step
    When I click on the "Bodies" link
    Then I click on the "Create" button
    And I remember "<short_name>" text as "EC_BODY_SHORT_NAME" variable
    And I fill the "Short Name" PF field with "EC_BODY_SHORT_NAME"
    And I remember "<name>" text as "EC_BODY_NAME" variable
    And I fill the "Name" PF field with "EC_BODY_NAME"
    And I click on the "Change" button
    And I select "EC_FD_CODE" from PF dialog
    And I click on the "Save" button
    Then I should see the "EC_BODY_SHORT_NAME" element

    Examples:
      |short_name                                      |name                                       |
      |AutoBodyShortName<KW_AUTO_RANDOMNUMBER\|#######>|AutoBodyName<KW_AUTO_RANDOMNUMBER\|#######>|
      |AutoBodyShortName<KW_AUTO_RANDOMNUMBER\|#######>|AutoBodyName<KW_AUTO_RANDOMNUMBER\|#######>|