@ProductFactoryCleanup @BeforePF
Feature: Product Factory database cleanup

  #TC-1668, TC-1666, TC-1606
  Scenario: Clean Product Factory Database
    Given I am on "http://ec2-3-10-141-78.eu-west-2.compute.amazonaws.com:7474/" URL
    When I fill the "Product Factory Database Connect URL Field" field with "bolt://ec2-3-10-141-78.eu-west-2.compute.amazonaws.com:7687"
    Then I fill the "Product Factory Database Username Field" field with "Neo4j"
    And I fill the "Product Factory Database Password Field" field with "3M#$,qns5uw*W#jr"
    And I click on the "Product Factory Database Connect Button" button
    When I click on the "Product Factory Database Query Field" button
    And I fill the "Product Factory Database Query Field" field with "MATCH (n) DETACH DELETE n"
    Then I click on the "Product Factory Database Submit Query Button" button

    And I execute "Log In" reusable step replacing some steps
      |3|I fill the "Product Factory Email" field with "MD_COMMON_CREDENTIALS_PRODUCTFACTORYJUNIORPRODUCTSETTER"|
      |4|I fill the "Product Factory Password" field with "MD_COMMON_CREDENTIALS_PRODUCTFACTORYJUNIORPSPASSWORD"|
    Then I click on the "Products" "Product Factory navigation item"
    And I click on the "Materials" "Product Factory navigation sub item"
    Then I should see the "Materials have not yet been configured for use. Please check with your Team Leader" message
    When I click on the "exit_to_app" button
    And I execute "Log In" reusable step

    When I click on the "Financials" "Product Factory navigation item"
    When I click on the "Financial Dimensions" "Product Factory navigation sub item"
    Then I click on the "Create" "Product Factory button"
    And I set "FDCode[######]" text to the "Code" "Product Factory text field"
    And I set "FDDescription[######]" text to the "Description" "Product Factory text field"
    And I click on the "Applies To" "Product Factory dropdown"
    And I click on the "Course" "Product Factory dropdown option"
    And I click on the "Save" "Product Factory button"
    Then I should see the "EC_FD_CODE" element
    When I click on the "Products" "Product Factory navigation item"
    When I click on the "Courses" "Product Factory navigation sub item"
    And I click on the "Entity Dimension" "Product Factory change button"
    And I click on the "EC_FD_CODE" "Product Factory select button"
    And I click on the "Revenue Dimension" "Product Factory change button"
    And I click on the "EC_FD_CODE" "Product Factory select button"
    And I click on the "Save" "Product Factory button"

    When I click on the "Financials" "Product Factory navigation item"
    When I click on the "Financial Dimensions" "Product Factory navigation sub item"
    Then I click on the "Create" "Product Factory button"
    And I set "FDCode[######]" text to the "Code" "Product Factory text field"
    And I set "FDDescription[######]" text to the "Description" "Product Factory text field"
    And I click on the "Applies To" "Product Factory dropdown"
    And I click on the "Material" "Product Factory dropdown option"
    And I click on the "Save" "Product Factory button"
    Then I should see the "EC_FD_CODE" element
    When I click on the "Products" "Product Factory navigation item"
    When I click on the "Materials" "Product Factory navigation sub item"
    And I click on the "Entity Dimension" "Product Factory change button"
    And I click on the "EC_FD_CODE" "Product Factory select button"
    And I click on the "Revenue Dimension" "Product Factory change button"
    And I click on the "EC_FD_CODE" "Product Factory select button"
    And I click on the "Save" "Product Factory button"