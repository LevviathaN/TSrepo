@Debug
Feature: Debug Feature

  Scenario: Debug Scenario 1
    Given I execute "Log In" reusable step
#    When I click on the "Miscellaneous" "Product Factory navigation item"
#    When I click on the "Clients" "Product Factory navigation sub item"
#    When I click on the "NoClientName" "Product Factory edit button"

  Scenario: Debug Scenario 2
    Given I execute "Log In" reusable step
#    And I execute "retCreate Client" reusable step
    When I click on the "Miscellaneous" "Product Factory navigation item"
    When I click on the "Clients" "Product Factory navigation sub item"
    When I click on the "TestClientName6" "Product Factory edit button"

  Scenario: Debug Scenario 3
    Given I execute "Log In" reusable step
#    And I execute "Create Client" reusable step
#    When I click on the "EC_CLIENT_NAME" "Product Factory edit button"
#    Then I click on the "EC_CLIENT_NAME" "Product Factory edit button"
#    When I click on the "Programme" "Product Factory navigation item"
#    When I click on the "Bodies" "Product Factory navigation sub item"
#    Then I click on the "Create" "Product Factory button"

#  Scenario: Debug Scenario 4
#    Given I execute "Log In" reusable step
#
#  Scenario: Debug Scenario 5
#    Given I click on the "Nothing" option
#
#  Scenario: Debug Scenario 6
#    Given I click on the "Whatever" option