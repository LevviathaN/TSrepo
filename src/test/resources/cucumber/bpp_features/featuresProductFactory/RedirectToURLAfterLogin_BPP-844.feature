@ProductFactory @Redirect
Feature: Redirect to Requested URL after Authentication - BPP-844


  @Positive @Regression @P1 #TC-3445
  Scenario Outline: Redirect to <submenu> URL after Authentication - Senior Product Setter
    Given I execute "Log In" reusable step
    When I click on the "<menu>" "Product Factory navigation item"
    When I click on the "<submenu>" "Product Factory navigation sub item"
    Then I capture current URL as "EC_SAVED_URL" variable
    When I click on the "exit_to_app" button
    And I execute "Log In" reusable step replacing some steps
      |1|I am on "EC_SAVED_URL" URL|
    Then I should see the "<validate>" element

    Examples:
    |menu|submenu|validate|
    |Programme|Bodies|Bodies|
    |Programme|Levels|Levels|
    |Programme|Papers|Papers|
    |Delivery|Sittings|Sittings|
    |Delivery|Region|Regions|
    |Delivery|Locations|Locations|
    |Delivery|Session Durations|Session Durations|
    |Delivery|Digital Content|Digital Content|
    |Types   |Material Types |Material Types |
    |Types   |Course Types   |Course Types   |
    |Miscellaneous|Clients   |Clients   |
    |Miscellaneous|Streams   |Streams   |
    |Miscellaneous|Deactivation Reasons|Deactivation Reasons|
    |Financials   |Prices              |Pricing Matrices|
    |Financials   |VAT Rules           |VAT Rules           |
    |Financials   |Financial Dimensions|Financial Dimensions|
    |Products     |Materials           |Materials           |
    |Products     |Courses             |Courses             |
    |Products     |CBAs                |CBAs                |
    |Products     |Create Material     |Create Material     |
    |Products     |Create Course       |Create Course       |
    |Products     |Create CBA          |Create CBA          |


  @Positive @Regression @P1 #TC-3446
  Scenario Outline: Redirect to <submenu> URL after Authentication - Simple Product Setter
    Given I execute "Log In" reusable step replacing some steps
      |3|I fill the "Product Factory Email" field with "MD_COMMON_CREDENTIALS_PRODUCTFACTORYJUNIORPRODUCTSETTER"|
      |4|I fill the "Product Factory Password" field with "MD_COMMON_CREDENTIALS_PRODUCTFACTORYJUNIORPSPASSWORD"|
    When I click on the "<menu>" "Product Factory navigation item"
    When I click on the "<submenu>" "Product Factory navigation sub item"
    Then I capture current URL as "EC_BODIES_URL" variable
    When I click on the "exit_to_app" button
    And I execute "Log In" reusable step replacing some steps
      |1|I am on "EC_BODIES_URL" URL|
    Then I should see the "<validate>" element

    Examples:
      |menu|submenu|validate|
      |Programme|Bodies|Bodies|
      |Programme|Levels|Levels|
      |Programme|Papers|Papers|
      |Delivery|Sittings|Sittings|
      |Delivery|Region|Regions|
      |Delivery|Locations|Locations|
      |Delivery|Session Durations|Session Durations|
      |Delivery|Digital Content|Digital Content|
      |Types   |Material Types |Material Types |
      |Types   |Course Types   |Course Types   |
      |Miscellaneous|Clients   |Clients   |
      |Miscellaneous|Streams   |Streams   |
      |Miscellaneous|Deactivation Reasons|Deactivation Reasons|
      |Financials   |Prices              |Pricing Matrices|
      |Financials   |VAT Rules           |VAT Rules           |
      |Products     |Materials           |Materials           |
      |Products     |Courses             |Courses             |
      |Products     |CBAs                |CBAs                |
      |Products     |Create Material     |Create Material     |
      |Products     |Create Course       |Create Course       |
      |Products     |Create CBA          |Create CBA          |

  @Negative @Regression @P1 #TC-3447
  Scenario: Redirect to Requested URL after Authentication - Simple Product Setter Restrictions
    Given I execute "Log In" reusable step
    When I execute "Create Body Financial Dimension" reusable step
    And I click on the "EC_BODY_FD_CODE" "Product Factory edit button"
    Then I capture current URL as "EC_BODIES_URL" variable
    When I click on the "exit_to_app" button
    Given I execute "Log In" reusable step replacing some steps
      |1|I am on "EC_BODIES_URL" URL|
      |3|I fill the "Product Factory Email" field with "MD_COMMON_CREDENTIALS_PRODUCTFACTORYJUNIORPRODUCTSETTER"|
      |4|I fill the "Product Factory Password" field with "MD_COMMON_CREDENTIALS_PRODUCTFACTORYJUNIORPSPASSWORD"|
    Then I should see the "PAGE FORBIDDEN" message