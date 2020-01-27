@ProductFactory @Materials
Feature: Materials - Create Materials - BPP-1099
  As a Product Setter
  I want to be able to create a Material
  so that the Material is available for Courses.

  Background:
    Given I execute "Log In" reusable step
    When I execute "Create Body Financial Dimension" reusable step
    And I execute "Create Body" reusable step
    And I execute "Create Level" reusable step
    And I execute "Create Sitting" reusable step
    And I execute "Create VAT Rule" reusable step
    And I execute "Create Material Type Financial Dimension" reusable step
    And I execute "Create Material Financial Dimension" reusable step
    And I execute "Create Material Type" reusable step
    And I execute "Create Paper" reusable step
    And I execute "Link Body To Paper" reusable step
    And I execute "Link Body To Level" reusable step

  @Positive @P1
  Scenario: Create Material Wizard
    When I click on the "Products" "Product Factory navigation item"
    When I click on the "Materials" "Product Factory navigation sub item"
    Then I click on the "Entity Dimension" "Product Factory change button" if "System Setting" "element is present"
    Then I click on the "EC_MATERIAL_FD_CODE" "Product Factory change modal option" if "System Setting" "element is present"
    Then I click on the "Revenue Dimension" "Product Factory change button" if "System Setting" "element is present"
    Then I click on the "EC_MATERIAL_FD_CODE" "Product Factory change modal option" if "System Setting" "element is present"
    Then I click on the "Save" "Product Factory button" if "System Setting" "element is present"
    And I click on the "Create" "Product Factory button"
    And I click on the "BPP Learning Media" "Product Factory button"
    And I click on the "EC_MATERIAL_TYPE_NAME" "Product Factory select button"
    And I set "1000" text to the "Price (£)" "Product Factory text field"
    And I set "10" text to the "Weight (kg)" "Product Factory text field"
    And I set "123" text to the "ISBN" "Product Factory text field"
    And I set "10/10/2022" text to the "Expiry Date" "Product Factory text field"
    And I click on the "Learning Media VAT Rule" "Product Factory change button"
    And I click on the "EC_VAT_RULE_CODE" "Product Factory select button"
    And I click on the "Course Material VAT Rule" "Product Factory change button"
    And I click on the "EC_VAT_RULE_CODE" "Product Factory select button"
    And I click on the "Next" "Product Factory button"
    And I click on the "Choose a Body" "button"
    And I click on the "EC_BODY_NAME" "Product Factory select button"
    And I click on the "Next" "Product Factory button"
    And I "check" "EC_LEVEL_NAME" "Product Factory checkbox"
    And I click on the "Next" "Product Factory button"
    And I "check" "EC_SITTING_NAME" "Product Factory checkbox"
    And I click on the "Next" "Product Factory button"
    And I click on the "Finish" "Product Factory button"

  @Positive @P1 @Amend #blocked by BPP-3250
  Scenario: Amend Material Using Modal
    When I execute "Create Material" reusable step
    And I execute "Create Body" reusable step
    And I execute "Create Level" reusable step
    And I execute "Create Sitting" reusable step
    And I execute "Create VAT Rule" reusable step
    And I execute "Create Material Type" reusable step

    And I click on the "EC_MATERIAL_TYPE_NAME" "Product Factory edit button"
    And I click on the "Body" "Product Factory edit button"
    And I click on the "Change Body" "button"
    And I click on the "EC_BODY_NAME" "Product Factory select button"
    And I click on the "Next" "Product Factory button"
    And I "check" "EC_LEVEL_NAME" "Product Factory checkbox"
    And I click on the "Next" "Product Factory button"
    And I "check" "EC_SITTING_NAME" "Product Factory checkbox"
    And I click on the "Next" "Product Factory button"
    And I click on the "Finish" "Product Factory button"
    Then I should see the "EC_BODY_SHORT_NAME" "element"
    And I should see the "EC_LEVEL_SHORT_NAME" "element"
    And I should see the "EC_SITTING_NAME" "element"

    When I click on the "Basic Properties" "Product Factory edit button"
    And I click on the "EC_MATERIAL_TYPE_NAME" "Product Factory select button"
    And I set "999" text to the "Price (£)" "Product Factory text field"
    And I set "9" text to the "Weight (kg)" "Product Factory text field"
    And I set "456" text to the "ISBN" "Product Factory text field"
    And I set "09/09/2022" text to the "Expiry Date" "Product Factory text field"
    And I click on the "Learning Media VAT Rule" "Product Factory change button"
    And I click on the "EC_VAT_RULE_CODE" "Product Factory select button"
    And I click on the "Course Material VAT Rule" "Product Factory change button"
    And I click on the "EC_VAT_RULE_CODE" "Product Factory select button"
    And I click on the "Save" "Product Factory button"
    Then I should see the "EC_MATERIAL_TYPE_NAME" "element"
    And I should see the "£999" "element"
    And I should see the "10kg" "element"
    And I should see the "09/09/2022" "element"