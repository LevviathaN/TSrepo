@Quark
Feature: Quark PDF File Comparison

  Scenario Outline: <BaseFile> to <QuarkWebPlatformFile> comparison
    #Given I am on "http://7.0.12.124/workspace/login.qsp" URL
    # And I fill the "Quark Username" field with "MD_COMMON_CREDENTIALS_QUARKUSER"
    #And I fill the "Direct App Admin Password" field with "MD_COMMON_CREDENTIALS_QUARKUSER"
    Given I am on "http://yzosin:Welcome8%40@7.0.12.124/workspace/login.qsp" URL
    And I wait for "3" seconds
    Then I should see the "Quark Publishing Logo" element
    And I click on the "Quark Home Menu" element
    And I wait for "1" seconds
    Then I doubleclick on the "TEST" "element by title"
    And I doubleclick on the "ACCA ICAEW Regression test packs" "element by title"
    And I doubleclick on the "ACCA" "element by title"
    And I wait for "1" seconds
    Then I hover over the "ACCA Print Workbook.xml" "Quark XML File"
    And I perform right mouse click on the "ACCA E-Version Workbook.xml" "Quark XML File"
    And I wait for "3" seconds
    Then I hover over the "Quark Publish" element
    And I hover over the "Quark Publish ICAEW PDF" element


    #And I compare "<BaseFile>" file with "<QuarkWebPlatformFile>" PDF file

    Examples:
      | BaseFile                     | QuarkWebPlatformFile|
      | Version_1.pdf                | ACCA E-Version Workbook.pdf|