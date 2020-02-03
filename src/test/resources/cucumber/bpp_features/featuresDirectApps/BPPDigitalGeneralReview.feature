@DirectApps @BPPDigital
Feature: BPP Digital General Review

  Background:
    Given I am on "MD_COMMON_LINKS_BPPDIGITALINDEXURL" URL

  @Positive @BET #TC-2266
  Scenario: BPP Digital General Review
    When I fill the "BPP Digital Find a Course Search Field" field with "Law"
    And I press "MD_COMMON_KEYBOARD_ENTER" for "BPP Digital Find a Course Search Field"
    Then I validate text "Law" to be displayed for "BPP Digital Find a Course Search Field Total Results Search Field" element
    Then I validate text "CONTAINS=results" to be displayed for "BPP Digital Find a Course Search Field Total Results" element
    And I capture text data "BPP Digital Find a Course Search Field Total Results" as "EC_RESULTS" variable
    When I click on the "Courses" "BPP Digital Search Label Button"
    Then I validate text "Courses" to be displayed for "BPP Digital Find a Course Search Field Total Results Header" element
    When I click on the "Professional Development Courses" "BPP Digital Search Label Button"
    Then I validate text "Professional Development Courses" to be displayed for "BPP Digital Find a Course Search Field Total Results Header" element
    When I click on the "News and Insights" "BPP Digital Search Label Button"
    Then I validate text "News and Insights" to be displayed for "BPP Digital Find a Course Search Field Total Results Header" element
    When I click on the "Other pages..." "BPP Digital Search Label Button"
    Then I validate text "Other pages..." to be displayed for "BPP Digital Find a Course Search Field Total Results Header" element
    When I click on the "Clear all" "BPP Digital Search Label Button"
    And I wait for "3" seconds
    Then I validate text "EC_RESULTS" to be displayed for "BPP Digital Find a Course Search Field Total Results" element
    #Explore our subjects block:
    When I click on the "BPP Digital Breadcrumb Level One" link
    And I click on the "Law" "BPP Digital Explore Our Subjects item link"
    Then I validate text "CONTAINS=law" to be displayed for "BPP Digital Breadcrumb Level Three" element
    When I click on the "BPP Digital Breadcrumb Level One" link
    And I click on the "Accountancy" "BPP Digital Explore Our Subjects item link"
    Then I validate text "CONTAINS=accountancy" to be displayed for "BPP Digital Breadcrumb Level Three" element
    When I click on the "BPP Digital Breadcrumb Level One" link
    And I click on the "Technology" "BPP Digital Explore Our Subjects item link"
    Then I validate text "CONTAINS=Innovation and Technology" to be displayed for "BPP Digital Breadcrumb Level Three" element
    When I click on the "BPP Digital Breadcrumb Level One" link
    And I click on the "Data" "BPP Digital Explore Our Subjects item link"
    Then I validate text "CONTAINS=Data" to be displayed for "BPP Digital Breadcrumb Level Three" element
    When I click on the "BPP Digital Breadcrumb Level One" link
    And I click on the "Nursing" "BPP Digital Explore Our Subjects item link"
    Then I validate text "CONTAINS=Nursing and Healthcare" to be displayed for "BPP Digital Breadcrumb Level Three" element
    When I click on the "BPP Digital Breadcrumb Level One" link
    And I click on the "Financial Services" "BPP Digital Explore Our Subjects item link"
    Then I validate text "CONTAINS=Financial Services" to be displayed for "BPP Digital Breadcrumb Level Three" element
    When I click on the "BPP Digital Breadcrumb Level One" link
    And I click on the "View all" "BPP Digital Explore Our Subjects item link"
    Then I validate text "Courses" to be displayed for "BPP Digital Site Page Header" element
#    When I click on the "BPP Digital Breadcrumb Level One" link
#    And I click on the "Apprenticeships" "BPP Digital Qualifications at Every Level item link"
#    Then I validate text "CONTAINS=Apprenticeships" to be displayed for "BPP Digital Breadcrumb Level Two" element
    When I click on the "BPP Digital Breadcrumb Level One" link
    And I click on the "Master's & Undergraduate Degrees" "BPP Digital Qualifications at Every Level item link"
    Then I validate text "CONTAINS=Degree Courses" to be displayed for "BPP Digital Breadcrumb Level Three" element
    When I click on the "BPP Digital Breadcrumb Level One" link
    And I click on the "Professional Development" "BPP Digital Qualifications at Every Level item link"
    Then I validate text "CONTAINS=Professional Development" to be displayed for "BPP Digital Breadcrumb Level Three" element
    When I click on the "BPP Digital Breadcrumb Level One" link
    And I click on the "Professional Qualifications" "BPP Digital Qualifications at Every Level item link"
    Then I validate text "CONTAINS=Professional Qualifications" to be displayed for "BPP Digital Breadcrumb Level Three" element
    #Login Existing User
    When I hover over the "BPP Digital Login link" element
    Then I should see the "Student Applications" "BPP Digital Student Applications Menu link"
    When I click on the "Student Applications" "BPP Digital Student Applications Menu link"
    And I fill the "Direct App Admin Email" field with "MD_COMMON_CREDENTIALS_BUILDEMPIRENEILADMINUSER"
    And I fill the "Direct App Admin Password" field with "MD_COMMON_CREDENTIALS_BUILDEMPIRENEILADMINPASSWORD"
    And I click on the "Direct App Login Button" button
    And I should see the "Direct App My Applications Left Menu Link" element
    Then I wait for "3" seconds
    Then I execute "Log Out from Hub Student" reusable step
    #Validate Created Profession (Should be created in Professions with name 'Automation General Review Profession Page')
    Given I am on "MD_COMMON_LINKS_BPPDIGITALINDEXURL" URL
    When I fill the "BPP Digital Find a Course Search Field" field with "Automation General Review Profession Page"
    And I press "MD_COMMON_KEYBOARD_ENTER" for "BPP Digital Find a Course Search Field"
    Then I wait for "5" seconds
    Then I should see the "Automation General Review Profession Page" "BPP Digital Section Article Name"
    #Validate Main Menu Items
    When I hover over the "BPP Digital Study expandable link" link
    When I click on the "Courses" "BPP Digital Courses Menu Item links"

