@ProductFactoryCleanup @BeforePF
Feature: Product Factory database cleanup
  
  Scenario: Clean Product Factory Database
    Given I am on "http://ec2-3-10-141-78.eu-west-2.compute.amazonaws.com:7474/" URL
    When I fill the "Product Factory Database Connect URL Field" field with "bolt://ec2-3-10-141-78.eu-west-2.compute.amazonaws.com:7687"
    Then I fill the "Product Factory Database Username Field" field with "Neo4j"
    And I fill the "Product Factory Database Password Field" field with "3M#$,qns5uw*W#jr"
    And I click on the "Product Factory Database Connect Button" button

    When I click on the "Product Factory Database Query Field" button
    And I fill the "Product Factory Database Query Field" field with "MATCH (n) DETACH DELETE n"
    Then I click on the "Product Factory Database Submit Query Button" button