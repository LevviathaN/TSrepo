@Hub @SubTopics @Totara
Feature: BPP University Hub Sub Topics and Labels

  Background:
    Given I execute "Register New Student Account" reusable step

  @Positive #TC-867
  Scenario: Hub Sub Topics and Labels
    #Logout as a User
    #Then I execute "Log Out from Hub Student" reusable step
    #Admin Login With Walkaround
    #When I am on "MD_COMMON_LINKS_TOTARALOGINURL" URL
    #And I click on the "BPP Digital Log out button" button if "Totara Login Confirmation Popup window" "element is present"
    And I execute "Logout Totara Workaround" reusable step
    Then I execute "Login as Admin Totara" reusable step
    #Enrol To course for ICAEW Course
    When I click on the "Totara ICAEW Course" link by JS
    And I click on the "Totara Left Menu Users link" link by JS
    And I click on the "Totara Enrolled Users button" button by JS
    And I wait for "3" seconds
    And I click on the "Totara Enrol User button" button by JS
    And I set "EC_AUTO_EMAIL" text to the element with ID "enrolusersearch" using JS
    And I click on the "Totara Enrol User Search button" button by JS
    And I click on the "Totara Enrol Button" button by JS
    And I wait for "2" seconds
    And I click on the "Totara Finish Enroling Users button" button by JS
    #Totara Add Subtopic and Content
    When I click on the "Dashboard" "text contained in SPAN element"
    When I click on the "Totara ICAEW Course" link by JS
    And I click on the "Turn editing on" "BPP Digital Admin Value attribute button"
    And I click on the "Totara Automation Topic Add an Activity or Resource" element
    And I click on the "Totara Add an Activity Label Radio button" element
    And I click on the "Totara Add an Activity Add Button" button
    And I fill the "Totara Adding a New Label Text Field" field with "Automation Sub-Topic"
    And I click on the "Save and return to course" "BPP Digital Admin Value attribute button"
    And I click on the "Totara Automation Topic Add an Activity or Resource" element
    And I click on the "Totara Add an Activity Label Radio button" element
    And I click on the "Totara Add an Activity Add Button" button
    And I fill the "Totara Adding a New Label Text Field" field with "Automation Expandable Lable"
    And I click on the "Save and return to course" "BPP Digital Admin Value attribute button"
    And I click on the "Totara Automation Exp Label Edit button" element
    And I click on the "Totara Automation Exp Label Edit Move Right Item" element
    And Browser performs "Refresh" command
    And I click on the "Totara Automation Topic Add an Activity or Resource" element
    And I click on the "Totara Add an Activity Quiz Radio button" button
    And I click on the "Totara Add an Activity Add Button" button
    And I fill the "Totara Add New Quiz Name Text Field" field with "Automation Quiz Name"
    And I wait for "2" seconds
    And I set "Automation Description" text to the element with ID "id_introeditoreditable" using JS
    And I click on the "Save and return to course" "BPP Digital Admin Value attribute button"
    And I click on the "Totara Automation Quiz Link" link by JS
    And I click on the "Totara Automation Quiz Edit Quiz Button" link by JS
    And I click on the "Totara Automation Quiz Question Add Button" button by JS
    And I click on the "Totara Automation Quiz a New Question Link" button by JS
    And I click on the "Totara Automation Quiz Question to Add True or False Radio Button" button by JS
    And I click on the "Totara Add an Activity Add Button" button by JS
    And I select "Default for Automation Quiz Name (0)" from "Totara Automation Quiz Creation Category Drop down" element
    And I set "Questions saved from context Quiz: Auto Quiz One. (1)" text to the element with ID "id_name" using JS
    And I wait for "2" seconds
    And I click on the "Totara Automation Quiz Question Text Order List Icon" link by JS
    And I set "12" text to the element with ID "id_defaultmark" using JS
    And I select "True" from "Totara Automation Quiz Correct Answer Drop Down" element
    And I click on the "Save changes" "Totara button" by JS
    And I wait for "3" seconds
    And I click on the "Totara Breadcrambs ICAEW Course Item" button by JS
    And I click on the "Totara Automation Quiz Name Edit Quiz Button" button by JS
    And I click on the "Totara Automation Quiz Name Edit Move Right Item" link by JS
    And I wait for "2" seconds
    And I click on the "Totara Automation Quiz Name Edit Move Right Item" link by JS
    #Admin Logout
    And I execute "Logout as Admin Totara" reusable step
    And Browser deletes cookies
    #Login As student
    And I execute "Log In to Hub as Student Fixed" reusable step
    #Navigate to Created Sub Topics
    And I click on the "Direct App My Learning Left Menu Link" link by JS
    And I click on the "BPP Digital Student My Learning Page Open First Course Sticker" element
    And I click on the "BPP Digital Student Icaew Course Page Open First Topic Sticker" element
    Then I validate text "Automation" to be displayed for "BPP Digital Automation Validate Topic Header" element
    Then I validate text "CONTAINS=Automation Sub-Topic" to be displayed for "BPP Digital Automation Validate Sub Topic Header" element
    Then Attribute "data-icon" of "BPP Digital Automation Validate Angle Down Icon" should have value "angle-down"
    Then I validate text "Automation Expandable Lable" to be displayed for "BPP Digital Automation Validate Expandable Sub Topic Header" element
    And I click on the "BPP Digital Automation Validate Expandable Sub Topic Header" element by JS
    Then I validate text "Automation Quiz Name" to be displayed for "BPP Digital Automation Validate Sub Topic Quiz Link" element
    Then Attribute "data-icon" of "BPP Digital Automation Validate Quiz Square Svg Item" should have value "check"
    And I click on the "BPP Digital Automation Validate Quiz Square Svg Item" element
    And Browser performs "Refresh" command
    And I click on the "BPP Digital Automation Validate Expandable Sub Topic Header" link by JS
    And Attribute "data-icon" of "BPP Digital Automation Validate Quiz Check Svg Item" should have value "check"
    And Attribute "class" of "BPP Digital Automation Validate Green Check Icon" should have value "CONTAINS=complete"
    #Student Logout
    #And I execute "Log Out from Hub Student" reusable step
    #Admin Login With Walkaround
    And I execute "Logout Totara Workaround" reusable step
    Then I execute "Login as Admin Totara" reusable step
    #Delete SubTopics
    When I click on the "Totara ICAEW Course" link by JS
    And I click on the "Turn editing on" "BPP Digital Admin Value attribute button"
    And I click on the "Totara Automation Quiz Name Edit Quiz Button" link
    And I click on the "Totara Automation Quiz Name Delete Quiz Button" link
    And I click on the "Moodle Staging Confirm Activity Delete Button" link
    And Browser performs "Refresh" command
    And I wait for "15" seconds
    And I click on the "Totara Automation Exp Label Edit button" button
    And I click on the "Totara Automation Exp Label Delete Item" link
    And I click on the "Moodle Staging Confirm Activity Delete Button" link
    And Browser performs "Refresh" command
    And I wait for "15" seconds
    And I click on the "Totara Automation Sub Topic Edit Button" link
    And I click on the "Totara Automation Sub Topic Delete Button" link
    And I click on the "Moodle Staging Confirm Activity Delete Button" link
    And I wait for "5" seconds