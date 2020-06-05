@Hub @VPE
Feature: The Hub - Law School - Moving between Hub and VPE
  As a user, I expect to be able to access my VPE from the Hub dashboard, as this will be a way for me to access content within my VPE

  @Positive #TC-2086, TC-2077
  Scenario: The Hub - Law School - Moving between Hub and VPE
  #User enrolled for 1 course
    Given I execute "Log In to Hub as Student" reusable step replacing some steps
      | 2 | I set "vpecourse@guerillamail.info" text to the "Email" "Build Empire text field" |
      | 3 | I set "A@polloGlobal2020" text to the "Password" "Build Empire text field"                    |
    And I wait for "1" seconds
    And I should see the "Virtual Practice Environment" "element"
    And Attribute "alt" of "Hub VPE Image Logo" should have value "VPE banner"
    And I should see the "VPE Course Automation" "Build Empire My Learning Right Block Course name"
    When I execute "Log Out from Hub Student" reusable step
    #User enrolled for 2 courses
    Given I execute "Log In to Hub as Student" reusable step replacing some steps
      | 2 | I set "vpe2courses@guerillamail.info" text to the "Email" "Build Empire text field" |
      | 3 | I set "A@polloGlobal2020" text to the "Password" "Build Empire text field"                    |
    And I wait for "1" seconds
    And I should see the "Virtual Practice Environment" "element"
    And Attribute "alt" of "Hub VPE Image Logo" should have value "VPE banner"
    And I should see the "VPE Course Automation" "Build Empire My Learning Right Block Course name"
    And I should see the "VPE Volodymyr's Course" "Build Empire My Learning Right Block Course name"
    Then I click on the "Hub VPE Image Logo" element
    And I switch to window with index "2"
    And I should see the "Hub VPE BPP Lion Image Logo" element
    And Attribute "alt" of "Hub VPE Image Logo" should have value "lion_head_logo"