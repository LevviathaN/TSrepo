@Assets
#@Ignore
Feature: Assets
  As an admin
  I want to be able perform CRUD operations on AEM Assets
  In order to (just cause)

#  CCM-2.1 todo
  Scenario: Upload Asset
    Given I am logged into AEM
    Given I am on the Assets Page
    When I click on the "Create" button
    And With "Files" option i upload the file by path "/Users/ruslanlevytskyi/Downloads/2016nstPMS0547.jpg"
    Then I wait for 15 seconds


#    CCM-2.3
  Scenario: Delete Asset
    Given I am logged into AEM
    Given I am on "https://author-bass-assets-stage65.adobecqms.net/assets.html/content/dam/test-folder" URL
    Given "Asset2" asset should be present
    When I delete "Asset2" asset
    And I click on the "Delete" button
    Then I wait for 5 seconds

  Scenario: Publish Asset
    Given I am logged into AEM
    Given I am on "https://author-bass-assets-stage65.adobecqms.net/assets.html/content/dam/test-folder" URL
    Given "Asset2" asset should be present

#    CCM-6.1 todo
  Scenario: Smart Tags Verification
    Given I am logged into AEM
    Given I am on "https://author-bass-assets-stage65.adobecqms.net/assets.html/content/dam/test-folder" URL
