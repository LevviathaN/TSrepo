@Reusable
Feature: Product Factory
  As a product setter
  I want to be able to log into Product Factory
  In order to (just cause)

  Scenario: Log In
    Given I am on "http://pf-services-qa-1697893966.eu-west-2.elb.amazonaws.com" URL
    When I click on the "Sign in with Auth0" button
    And I fill the "email" field with "samuelslade@bpp.com"
    And I fill the "password" field with "Password1"
    And I click on the "Login" button which is "2"
    When I wait for "4" seconds
    Then I should be redirected to the "Product Factory" page

  Scenario: Create Body Financial Dimension
    When I click on the "attach_money" link
    Then I click on the "Financial Dimensions" link
    Then I click on the "Create" button
    And I remember "AutoFDCode<KW_AUTO_RANDOMNUMBER|####>" text as "EC_FD_CODE" variable
    And I fill the "Code" PF field with "EC_FD_CODE"
    And I remember "AutoFDDescription<KW_AUTO_RANDOMNUMBER|####>" text as "EC_FD_DESC" variable
    And I fill the "Description" PF field with "EC_FD_DESC"
    And I click on the "xpath=//div[@aria-pressed='false']" element
    And I click on the "Body" option
    And I click on the "Save" button
    Then I should see the "EC_FD_CODE" element

  Scenario: Create Body
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

  Scenario: Create Sitting
    When I click on the "commute" link
    When I click on the "Sittings" link
    Then I click on the "Create" button
    And I remember "AutoSittingName<KW_AUTO_RANDOMNUMBER|#######>" text as "EC_SITTING_NAME" variable
    And I fill the "Name" PF field with "EC_SITTING_NAME"
    And I remember "KW_AUTO_TODAY|DD/MM/YYYY" text as "EC_SITTING_START_DATE" variable
    And I fill the "Start Date" PF field with "EC_SITTING_START_DATE"
    And I remember "10/10/2020" text as "EC_SITTING_END_DATE" variable
    And I fill the "End Date" PF field with "EC_SITTING_END_DATE"
    And I "check" "EC_BODY_NAME" PF checkbox
    And I click on the "Save" button
    Then I should see the "EC_SITTING_NAME" element

  Scenario: Create Pricing Matrix
    When I click on the "attach_money" link
    When I click on the "Prices" link
    Then I click on the "Create" button
    And I remember "AutoSittingName<KW_AUTO_RANDOMNUMBER|#######>" text as "EC_SITTING_NAME" variable
    And I fill the "Name" PF field with "EC_SITTING_NAME"
    And I remember "KW_AUTO_TODAY|DD/MM/YYYY" text as "EC_SITTING_START_DATE" variable
    And I fill the "Start Date" PF field with "EC_SITTING_START_DATE"
    And I remember "10/10/2020" text as "EC_SITTING_END_DATE" variable
    And I fill the "End Date" PF field with "EC_SITTING_END_DATE"
    And I "check" "EC_BODY_NAME" PF checkbox
    And I click on the "Save" button
    Then I should see the "EC_SITTING_NAME" element