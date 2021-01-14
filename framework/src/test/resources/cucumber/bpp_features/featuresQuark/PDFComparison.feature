@Quark
Feature: Quark PDF File Comparison

  Scenario Outline: <BaseFile> to <QuarkWebPlatformFile> comparison
    #Given I am on "http://twaxqkpp401/workspace/login.qsp#" URL
    #And I fill the "Quark Username" field with "yzosin"
    #And I fill the "Direct App Admin Password" field with "Welcome8@"
    #Then I should see the "Quark Publishing Logo" element
    
    Given I am on "http://twaxqkpp401/workspace/login.qsp#" URL
    And I compare "<BaseFile>" file with "<QuarkWebPlatformFile>" PDF file

    Examples:
      | BaseFile      | QuarkWebPlatformFile|
      | Version_1.pdf | Version_2.pdf|