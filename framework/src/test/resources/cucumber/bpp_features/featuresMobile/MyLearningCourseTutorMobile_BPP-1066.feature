@Mobile
Feature: BPP University Hub My Learning Course Tutor

  @NoRun #TC-721
  Scenario: Hub My Learning Course Tutor_Mobile
    #Then I execute "Login as Admin Totara" reusable step
    Given I am on "MD_COMMON_LINKS_TOTARALOGINURL" URL
    Then I click on the "Totara Login" button
    And I fill the "Direct App Admin Email" field with "MD_COMMON_CREDENTIALS_TOTARAADMINUSER"
    And I fill the "Direct App Admin Password" field with "MD_COMMON_CREDENTIALS_TOTARAADMINPASSWORD"
    Then I click on the "Direct App Login Button" button
    And I wait for "10" seconds
    #Totara Assign Tutor Role
    When I click on the "Dashboard" "text contained in SPAN element"
    When I click on the "Totara ICAEW Course" link by JS
    And I click on the "Totara Left Menu Users link" link by JS
    And I click on the "Totara Enrolled Users button" button by JS
    And I wait for "3" seconds
    And I click on the "Totara Course Assign Roles Link" link by JS
    And I click on the "Totara Course Bpp Lead Tutor Button" button by JS
    And I capture text data "Totara Course Current Tutor Name" as "EC_CURRENT_TUTOR" variable
    #Admin Logout
    And I execute "Logout as Admin Totara" reusable step
    #Login As student
    And I wait for "3" seconds
    When I am on "MD_COMMON_LINKS_BUILDEMPIREURL" URL
    And Browser performs "Refresh" command
    And I execute "Log In to Hub as Student" reusable step replacing some steps
      | 2 | I set "AutoFrancisMcKnight2507@getnada.com" text to the "Email" "Build Empire text field" |
      | 3 | I set "A@polloGlobal2020" text to the "Password" "Build Empire text field"               |
    And I wait for "10" seconds
    #Validate My Learning Course Tutor Name
    And I wait for "5" seconds
    And I am on "https://web-stage-bppdigital.bppuniversity.com/my/learning" URL
    And I wait for "4" seconds
    And I validate text "CONTAINS=EC_CURRENT_TUTOR" to be displayed for "Direct App My Learning First Course Tutor Name" element
    #Student Logout
    #And I click on the "Hub VPE Mobile Hamburger Menu" element
    #Then I click on the "Direct App Mobile Logout" element
    #Admin Login With Walkaround
    And I execute "Logout as Admin Totara" reusable step
    Then I execute "Login as Admin Totara" reusable step
    #Totara UnAssign Tutor Role
    And I wait for "10" seconds
    When I click on the "Totara ICAEW Course" link by JS
    And I click on the "Totara Left Menu Users link" link by JS
    And I click on the "Totara Enrolled Users button" button by JS
    And I wait for "3" seconds
    And I click on the "Totara Course Remove Bpp Lead Tutor Button" button by JS
    And I click on the "Totara Course Confirm Remove Bpp Lead Tutor Button" button by JS
    #Admin Logout
    And I execute "Logout as Admin Totara" reusable step
    And I wait for "7" seconds
    And Browser deletes cookies
    #Login As student
    And I wait for "3" seconds
    When I am on "MD_COMMON_LINKS_BUILDEMPIREURL" URL
    And Browser performs "Refresh" command
    And I execute "Log In to Hub as Student" reusable step if "Login" "element is present"
    And I wait for "15" seconds
    #Validate My Learning Course Tutor Name
    And I wait for "3" seconds
    And I am on "https://web-stage-bppdigital.bppuniversity.com/my/learning" URL
    And I wait for "4" seconds
    And I validate text "NOT_CONTAINS=EC_CURRENT_TUTOR" to be displayed for "Direct App My Learning First Course Tutor Name" element