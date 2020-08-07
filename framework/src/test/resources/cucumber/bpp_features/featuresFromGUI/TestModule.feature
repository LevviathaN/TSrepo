Feature: New Module
   This is a test example of created feaure file
   using framework GUI
   
   Background:
       Given You can do something before each scenario
       But Background is optional

   @SomeTag @CamelCase @YouCanUseItToRunSpecificScenarios
   #you can also add some comments
   Scenario: My first test scenario
       When I click on the "LOCATOR"
       And I do some "specific" thing
       Then I expect to see "something"


   @DataDrivenScenario @OneScenarioExecutedWithDifferentData
   Scenario Outline: My first data driven scenario
       When I do one thing
       And I do some "<action>" thing
       Then I expect to see "<result>"

       Examples:
           |action |result |
           |job    |money  |
           |workout|muscles|