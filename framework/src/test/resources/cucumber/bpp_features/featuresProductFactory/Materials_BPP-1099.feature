@ProductFactory @Materials
Feature: Materials - Create Materials - BPP-1099
  As a Product Setter
  I want to be able to create a Material
  so that the Material is available for Courses.

  Background:
    Given I execute "Generate ISBN" reusable step
    And I click on the " generate" "button"
    And I capture text data "ISBN Generator Number Field" as "EC_NEW_ISBN" variable
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

  @Positive @P1 #TC-918
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
    And I set "EC_ISBN" text to the "ISBN" "Product Factory text field"
    And I shouldn't see the "The ISBN specified is not Valid" message
    And I set "10/10/2022" text to the "Expiry Date" "Product Factory text field"
    And I set "From[TODAY]" text to the "Available From" "Product Factory text field"
    And I set "1020" text to the "Edition" "Product Factory text field"
    And I click on the "Production Method" "Product Factory dropdown"
    And I click on the "Not Applicable" "Product Factory dropdown option"
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
    When I click on the "Products" "Product Factory navigation item"
    When I click on the "Materials" "Product Factory navigation sub item"
    And I set "EC_MATERIAL_TYPE_NAME" text to the "Material Type" "Product Factory text field"
    And I click on the "Search" "Product Factory button"
    And I should see the "EC_MATERIAL_TYPE_NAME" "Product Factory edit button"

  @Positive @P1 @Amend
  Scenario: Amend Material Using Modal
    When I execute "Create Material" reusable step
    And I execute "Create Body" reusable step replacing some steps
      |4|I set "BodyShortNameTwo[######]" text to the "Short Name" "Product Factory text field"|
      |5|I set "BodyNameTwo[######]" text to the "Name" "Product Factory text field"|
    And I execute "Create Level" reusable step replacing some steps
      |4|I set "LevelShortNameTwo[######]" text to the "Short Name" "Product Factory text field"|
      |5|I set "LevelNameTwo[######]" text to the "Name" "Product Factory text field"|
    And I execute "Create Sitting" reusable step replacing some steps
      |4|I set "SittingNameTwo[######]" text to the "Name" "Product Factory text field"|
      |9|I "check" "EC_BODY_NAME_TWO" "Product Factory dialog checkbox"                    |
      |11|I should see the "EC_SITTING_NAME_TWO" element                                   |
    And I execute "Create VAT Rule" reusable step replacing some steps
      |4|I set "VatRuleCodeTwo[######]" text to the "Code" "Product Factory text field"|
      |5|I set "VatRuleDescriptionTwo[######]" text to the "Description" "Product Factory text field"|
    And I execute "Create Material Type" reusable step replacing some steps
      |4|I set "MaterialTypeNameTwo[######]" text to the "Name" "Product Factory text field"|
      |5|I set "MaterialTypeDescriptionTwo[######]" text to the "Description" "Product Factory text field"|
    And I execute "Link Body To Paper" reusable step replacing some steps
      |3|I set "EC_PAPER_DESCRIPTION" text to the "Description" "Product Factory text field"|
      |5|I click on the "EC_PAPER_DESCRIPTION" "Product Factory Change Body button"|
      |6|I click on the "EC_BODY_NAME_TWO" "Product Factory select button"         |
    And I execute "Link Body To Level" reusable step replacing some steps
      |3|I click on the "EC_BODY_NAME_TWO" "Product Factory Link Levels button"|
      |4|I "check" "EC_LEVEL_NAME_TWO" "Product Factory dialog checkbox"       |
    When I click on the "Products" "Product Factory navigation item"
    When I click on the "Materials" "Product Factory navigation sub item"
    And I set "EC_MATERIAL_TYPE_NAME" text to the "Material Type" "Product Factory text field"
    And I click on the "Search" "Product Factory button"
    And I click on the "EC_MATERIAL_TYPE_NAME" "Product Factory edit button"
    And I click on the "Body" "Product Factory edit button"
    And I click on the "Change Body" "button"
    And I click on the "EC_BODY_NAME_TWO" "Product Factory select button"
    And I click on the "Next" "Product Factory button"
    And I "check" "EC_LEVEL_NAME_TWO" "Product Factory checkbox"
    And I click on the "Next" "Product Factory button"
    And I "check" "EC_SITTING_NAME_TWO" "Product Factory checkbox"
    And I click on the "Next" "Product Factory button"
    And I click on the "Finish" "Product Factory button"
    Then I should see the "EC_BODY_SHORT_NAME_TWO" "element"
    And I should see the "EC_LEVEL_SHORT_NAME_TWO" "element"
    And I should see the "EC_SITTING_NAME_TWO" "element"

    When I click on the "Basic Properties" "Product Factory edit button"
    And I click on the "EC_MATERIAL_TYPE_NAME_TWO" "Product Factory select button"
    And I set "999" text to the "Price (£)" "Product Factory text field"
    And I set "9" text to the "Weight (kg)" "Product Factory text field"
    And I set "EC_NEW_ISBN" text to the "ISBN" "Product Factory text field"
    And I set "09/09/2022" text to the "Expiry Date" "Product Factory text field"
    And I set "1020" text to the "Edition" "Product Factory text field"
    And I click on the "Learning Media VAT Rule" "Product Factory change button"
    And I click on the "EC_VAT_RULE_CODE_TWO" "Product Factory select button"
    And I click on the "Course Material VAT Rule" "Product Factory change button"
    And I click on the "EC_VAT_RULE_CODE_TWO" "Product Factory select button"
    And I click on the "Save" "Product Factory button"
    Then I should see the "EC_MATERIAL_TYPE_NAME_TWO" "element"
    And I should see the "£999" "element"
    And I should see the "9kg" "element"
    And I should see the "09/09/2022" "element"