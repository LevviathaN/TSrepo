@Mobile
Feature: BPP University Hub Sub Topics and Labels

  Background:
    Given I execute "Register New Student Account" reusable step
    Then I execute "Nada Student Email Verification" reusable step
    Then I execute "Log In to Hub as Student" reusable step

  @Positive @iPhone @Samsung #TC-867
  Scenario: Hub Sub Topics and Labels_Mobile
    #login to Totara as an Admin
    Then Browser deletes cookies
    And I wait for "2" seconds
    And I click on the "Hub VPE Mobile Hamburger Menu" button
    And I wait for "3" seconds
    And I click on the "Direct App Mobile Logout" element
    And I wait for "8" seconds
    Then Browser deletes cookies
    And I wait for "8" seconds
    Given I am on "MD_COMMON_LINKS_TOTARALOGINURL" URL
    And I wait for "3" seconds
    And I execute "Logout as Admin Totara" reusable step if "Totara Account Expandable Menu" "element is present"
    Then I click on the "Totara Login" button by JS
    And I wait for "4" seconds
    And I fill the "Direct App Admin Email" field with "MD_COMMON_CREDENTIALS_TOTARAADMINUSER"
    And I fill the "Direct App Admin Password" field with "MD_COMMON_CREDENTIALS_TOTARAADMINPASSWORD"
    Then I click on the "Direct App Login Button" button
    And I wait for "4" seconds
    #Enrol student to VPE Automation
    And I execute "arguments[0].click()" JS code for "ICAEW Accounting Full" "BPP Digital Student Applications Menu link"
    And I click on the "Totara Left Menu Users link" link by JS
    And I click on the "Totara Enrolled Users button" button by JS
    And I wait for "3" seconds
    And I click on the "Totara Enrol User button" button by JS
    And I wait for "3" seconds
    And I set "EC_AUTO_EMAIL" text to the element with ID "enrolusersearch" using JS
    And I wait for "2" seconds
    And I click on the "Totara Enrol User Search button" button by JS
    And I click on the "Totara Enrol Button" button by JS
    And I wait for "6" seconds
    And I click on the "Totara Finish Enroling Users button" button by JS
    And I wait for "2" seconds
    #Totara Add Subtopic and Content
    When I click on the "Dashboard" "text contained in SPAN element"
    And I execute "arguments[0].click()" JS code for "ICAEW Accounting Full" "BPP Digital Student Applications Menu link"
    And I wait for "4" seconds
    And I click on the "Turn editing on" "BPP Digital Admin Value attribute button" by JS
    And I wait for "4" seconds
    And I click on the "Totara Automation Mobile Topic Add an Activity or Resource" element by JS
    And I wait for "6" seconds
    And I click on the "Totara Add an Activity Label Radio button" element
    And I click on the "Totara Add an Activity Add Button" button
    And I wait for "2" seconds
    And I click on the "id_introeditoreditable" "Totara field by ID tag"
    Then I execute "document.getElementById('id_introeditoreditable').innerText = 'Automation Mobile Sub-Topic'" JS code for "Totara Adding a New Label Text Field" element
    And I click on the "Save and return to course" "BPP Digital Admin Value attribute button"
    And I wait for "4" seconds
    And I click on the "Totara Automation Mobile Topic Add an Activity or Resource" element by JS
    And I wait for "6" seconds
    And I click on the "Totara Add an Activity Label Radio button" element
    And I wait for "4" seconds
    And I click on the "Totara Add an Activity Add Button" button
    And I wait for "4" seconds
    And I click on the "id_introeditoreditable" "Totara field by ID tag"
    Then I execute "document.getElementById('id_introeditoreditable').innerText = 'Automation Mobile Expandable Label'" JS code for "Totara Adding a New Label Text Field" element
    And I click on the "Save and return to course" "BPP Digital Admin Value attribute button"
    And I click on the "Totara Automation Mobile Exp Label Edit button" element
    And I wait for "4" seconds
    And I click on the "Totara Automation Mobile Exp Label Edit Move Right Item" element
    And I wait for "4" seconds
    And I execute "return document.readyState = 'complete'" JS code
    And I click on the "Totara Automation Mobile Topic Add an Activity or Resource" element by JS
    And I wait for "6" seconds
    And I click on the "Totara Add an Activity Quiz Radio button" button
    And I click on the "Totara Add an Activity Add Button" button
    And I fill the "Totara Add New Quiz Name Text Field" field with "Automation Mobile Quiz Name"
    And I wait for "4" seconds
    And I set "Automation Mobile Description" text to the element with ID "id_introeditoreditable" using JS
    And I click on the "Save and return to course" "BPP Digital Admin Value attribute button"
    And I click on the "Totara Automation Mobile Quiz Link" link by JS
    And I click on the "Totara Automation Quiz Edit Quiz Button" link by JS
    And I wait for "3" seconds
    And I click on the "Totara Automation Quiz Question Add Button" button by JS
    And I click on the "Totara Automation Quiz a New Question Link" button by JS
    And I click on the "Totara Automation Quiz Question to Add True or False Radio Button" button by JS
    And I click on the "Totara Add an Activity Add Button" button by JS
    And I select "Default for Automation Mobile Quiz Name (0)" from "Totara Automation Quiz Creation Category Drop down" element
    And I set "Questions saved from context Quiz: Auto Quiz One. (1)" text to the element with ID "id_name" using JS
    And I wait for "4" seconds
    And I click on the "Totara Automation Quiz Question Text Order List Icon" link by JS
    And I set "12" text to the element with ID "id_defaultmark" using JS
    And I select "True" from "Totara Automation Quiz Correct Answer Drop Down" element
    And I click on the "Save changes" "Totara button" by JS
    And I wait for "3" seconds
    And I click on the "Totara Breadcrambs ICAEW Course Item" button by JS
    And I click on the "Totara Automation Mobile Quiz Name Edit Quiz Button" button by JS
    And I click on the "Totara Automation Mobile Quiz Name Edit Move Right Item" link by JS
    And I wait for "4" seconds
    And I click on the "Totara Automation Mobile Quiz Name Edit Move Right Item" link by JS
    #Admin Logout
    And I execute "Logout as Admin Totara" reusable step
    And I wait for "2" seconds
    And Browser deletes cookies
    And I wait for "4" seconds
    #Login As student
    And I execute "Log In to Hub as Student Fixed" reusable step replacing some steps
      |7|Wait for "10" seconds|
    #Navigate to Created Sub Topics
    And I click on the "Hub VPE Mobile Hamburger Menu" button by JS
    And I click on the "Hub Mobile My Learning Link Hamburger Menu" option by JS
    And I click on the "BPP Digital Student My Learning Page Open First Course Sticker" element
    And I click on the "BPP Digital Student Icaew Course Page Open Second Topic Sticker" element
    Then I validate text "Automation Mobile" to be displayed for "BPP Digital Automation Validate Topic Header" element
    Then I validate text "CONTAINS=Automation Mobile Sub-Topic" to be displayed for "BPP Digital Automation Validate Sub Topic Header" element
    Then Attribute "data-icon" of "BPP Digital Automation Validate Angle Down Icon" should have value "angle-down"
    Then I validate text "Automation Mobile Expandable Label" to be displayed for "BPP Digital Automation Validate Expandable Sub Topic Header" element
    And I click on the "BPP Digital Automation Validate Expandable Sub Topic Header" element by JS
    Then I validate text "Automation Mobile Quiz Name" to be displayed for "BPP Digital Automation Mobile Validate Sub Topic Quiz Link" element
    Then Attribute "data-icon" of "BPP Digital Automation Validate Quiz Square Svg Item" should have value "check"
    And I click on the "BPP Digital Automation Validate Quiz Square Svg Item" element
    And Browser performs "Refresh" command
    And I click on the "BPP Digital Automation Validate Expandable Sub Topic Header" link by JS
    And Attribute "data-icon" of "BPP Digital Automation Validate Quiz Check Svg Item" should have value "check"
    And Attribute "class" of "BPP Digital Automation Validate Green Check Icon" should have value "CONTAINS=complete"
    #Student Logout
    Then Browser deletes cookies
    And I wait for "2" seconds
    And I click on the "Hub VPE Mobile Hamburger Menu" button
    And I wait for "3" seconds
    And I click on the "Direct App Mobile Logout" element
    And I wait for "4" seconds
    And Browser deletes cookies
    #Admin Login With Walkaround
    And I wait for "8" seconds
    Given I am on "MD_COMMON_LINKS_TOTARALOGINURL" URL
    And I wait for "3" seconds
    Then I click on the "Totara Login" button by JS
    And I wait for "4" seconds
    And I fill the "Direct App Admin Email" field with "MD_COMMON_CREDENTIALS_TOTARAADMINUSER"
    And I fill the "Direct App Admin Password" field with "MD_COMMON_CREDENTIALS_TOTARAADMINPASSWORD"
    Then I click on the "Direct App Login Button" button
    And I wait for "4" seconds
    #Delete SubTopics
    And I execute "arguments[0].click()" JS code for "ICAEW Accounting Full" "BPP Digital Student Applications Menu link"
#    When I click on the "Totara ICAEW Course" link by JS
    And I click on the "Turn editing on" "BPP Digital Admin Value attribute button"
    And I click on the "Totara Automation Mobile Quiz Name Edit Quiz Button" link
    And I click on the "Totara Automation Mobile Quiz Name Delete Quiz Button" link
    And I click on the "Moodle Staging Confirm Activity Delete Button" link
    And Browser performs "Refresh" command
    And I wait for "15" seconds
    And I click on the "Totara Automation Mobile Exp Label Edit button" button
    And I click on the "Totara Automation Mobile Exp Label Delete Item" link
    And I click on the "Moodle Staging Confirm Activity Delete Button" link
    And Browser performs "Refresh" command
    And I wait for "15" seconds
    And I click on the "Totara Automation Mobile Sub Topic Edit Button" link
    And I click on the "Totara Automation Mobile Sub Topic Delete Button" link
    And I click on the "Moodle Staging Confirm Activity Delete Button" link
    And I wait for "5" seconds