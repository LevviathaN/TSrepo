@Mobile @Tablet
Feature: BPP University Hub Direct App Notifications
  As a Hub user
  I would like to see notifications on my dashboard in regards to Direct Apps

  Background:
    Given I execute "Register New Student Account" reusable step replacing some steps
      |12|I wait for "5" seconds|

  @Positive #TC-1130 TC-1104 TC-1171
  Scenario: Direct App Notifications_Mobile
    And I click on the "Hub VPE Mobile Hamburger Menu" button if "Hub VPE Mobile Hamburger Menu" "element is present"
    And I click on the "Home" "Build Empire Navigation Menu elements"
    Then I should see the "Direct App Announcement Bar" element
    Then I should see the "Welcome to your new logged in area." message
    Then I should see the "Direct App CTA Banner Bar" message
    And I click on the "Start applications" "exact element"
    Then I should see the "What are you applying for?" "exact element"
    And I click on the "Direct App What Are You Applying For Dropdown" element
    Then I click on the "BPP Professional Apprenticeships" "Build Empire application type"
    And I click on the "Direct App Programmes Programm Dropdown" element
    Then I click on the "UAT Accounting Assitant" "Build Empire application type"
    And I click on the "Direct App Start Date Application Dropdown" element
    Then I click on the "UAT Assisstant Accountant - Option 2" "Build Empire application type"
    And I click on the "Direct App Create Application" button
    Then I wait for "2" seconds
    #Logout
    And I click on the "Hub VPE Mobile Hamburger Menu" element
    Then I click on the "Direct App Mobile Logout" element
    And I wait for "15" seconds
    #login
    When I execute "Log In to Hub as Student" reusable step
    And I wait for "1" seconds
    #Verify CTA Banner
    Then I shouldn't see the "Direct App CTA Banner Bar" message
    #Withdraw Application
    And I click on the "Hub VPE Mobile Hamburger Menu" element
    And I wait for "3" seconds
    And I click on the "Direct App My Applications Left Menu Link" link
    And I wait for "1" seconds
    And I click on the "Start Application" "button"
    And I wait for "1" seconds
    And I click on the "Hub VPE Mobile Hamburger Menu" element
    And I wait for "1" seconds
    And I click on the "Direct App My Applications Left Menu Link" link
    And I wait for "1" seconds
    And I click on the "Direct App Withdraw First Application" link
    And I wait for "1" seconds
    And I click on the "Withdraw Application" "BPP Digital Search Label Button"
    And I wait for "1" seconds
    And I click on the "Hub VPE Mobile Hamburger Menu" element
    And I wait for "1" seconds
    And I click on the "Direct App My Applications Left Menu Link" link
    And I wait for "1" seconds
    And I should see the "Submitted Applications" "exact element"
    And I should see the "UAT Accounting Assitant" "exact element"
    Then I validate text "CONTAINS=This application has been withdrawn" to be displayed for "Direct App Validate As Student Application Accepted" element
    #Timeline for New Started Application
    Given I click on the "Direct App My Applications Left Menu Link" link by JS
    And I click on the "Direct App Start New Application Button" link by JS
    And I click on the "Direct App What Are You Applying For Dropdown" element
    Then I click on the "BPP Professional Apprenticeships" "Build Empire application type"
    And I click on the "Direct App Programmes Programm Dropdown" element
    Then I click on the "UAT Accounting Assitant" "Build Empire application type"
    And I click on the "Direct App Start Date Application Dropdown" element
    Then I click on the "UAT Assisstant Accountant - Option 2" "Build Empire application type"
    And I click on the "Direct App Create Application" button
    #Start Application
    Given I click on the "Direct App Start Application" button
    And I click on the "Eligibility criteria" "Build Empire application section"
    And I click on the "Are you aged over 15" "Build Empire bespoke Scotland eligibility"
    And I click on the "Do you have a husband, wife, civil partner and child of any of the above?" "Build Empire bespoke Scotland eligibility"
    And I click on the "Direct App Right To Work in England?" element
    And I click on the "Direct App Do You Spend 50 Percent" element
    And I click on the "Direct App Bespoke Enrolled On Another Apprenticeship No" element
    And I click on the "Direct App Are You In Employment" element
    And I click on the "Direct App Are You Working At Least 16 Hours" element
    And I click on the "Are you applying for a programme" "Build Empire bespoke Scotland eligibility"
    And I click on the "Do you confirm that your job is a new job role relevant to the career development" "Build Empire bespoke Scotland eligibility"
    And I click on the "You confirm that you have discussed" "Build Empire bespoke Scotland eligibility"
    And I click on the "Direct App Next" button
    And I wait for "5" seconds
    #Navigate Home and verify timeline notification
    And I am on "https://web-stage-bppdigital.bppuniversity.com/my/home" URL
    Then I validate text "CONTAINS=You started an application" to be displayed for "Direct App First Timeline Title Four text" element


