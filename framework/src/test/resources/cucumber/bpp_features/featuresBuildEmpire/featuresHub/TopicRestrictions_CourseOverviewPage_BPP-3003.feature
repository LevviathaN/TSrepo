@BuildEmpire @Hub @Totara @Restrictions
Feature: Topic Restrictions Course Overview Page - BPP-3003
  As a user in the Hub,
  when a MOODLE QUIZ has been set as the Key Activity on my Topic Overview page,
  I expect to see a score and date for each attempt

  @Positive #TC-1621
  Scenario: Topic Restrictions Course Overview by Group
    #Register New Student
    And I execute "Register New Student Account" reusable step
    Then I execute "Harakirimail Verify Email" reusable step
    Then I execute "Log In to Hub as Student" reusable step
    #Validate Timeline block
    Then I should see the "Timeline" message
    Then I should see the "Welcome to your new logged in area." message
    #When I execute "Log Out from Hub Student" reusable step
    When I am on "MD_COMMON_LINKS_TOTARALOGINURL" URL
    And I execute "Logout as Admin Totara" reusable step if "Totara Account Expandable Menu" "element is present"
    And Browser deletes cookies
    Then I execute "Login as Admin Totara" reusable step
    #Enrol to a course
    When I click on the "Totara ICAEW Course" link by JS
    And I click on the "Totara Left Menu Users link" link by JS
    And I click on the "Totara Enrolled Users button" button by JS
    And I wait for "3" seconds
    And I click on the "Totara Enrol User button" button by JS
    And I set "EC_AUTO_EMAIL" text to the element with ID "enrolusersearch" using JS
    And I click on the "Totara Enrol User Search button" button by JS
    And I click on the "Totara Enrol Button" button by JS
    And I click on the "Totara Finish Enroling Users button" button by JS
    #Add to Test Group 1:
    And I click on the "ICAEW Accounting Full" "element by title"
    And I click on the "Totara Left Menu Users link" link by JS
    And I click on the "Totara Left Menu Groups link" button by JS
    And I wait for "5" seconds
    And I click on the "Test Group 1" "Dropdown Option Contains text"
    And I click on the "Add/remove users" "Totara button"
    And I set "EC_AUTO_EMAIL" text to the "addselect_searchtext" "element by name"
    And I click on the "Totara Potential Member first option" element
    And I click on the "Add" "element by title"
    #Admin Logout
    Then I execute "Logout as Admin Totara" reusable step
    And Browser deletes cookies
    And I wait for "4" seconds
    #Login As student
    When I execute "Log In to Hub as Student" reusable step
    #My Learning Validation
    Then I should see the "You are now enrolled onto ICAEW Accounting Full" message
    #Click Most Recent Course
    When I click on the "ICAEW Accounting Full" "Build Empire My Learning Right Block Course name"
    And I validate text "CONTAINS=Automation Restrictions Group A" to be displayed for "Hub Course Page Topics Name validation" element
    And I validate text "CONTAINS=This Is Group A" to be displayed for "Hub Course Page Topics Name validation" element
    #When I execute "Log Out from Hub Student" reusable step
    When I am on "MD_COMMON_LINKS_TOTARALOGINURL" URL
    And I execute "Logout as Admin Totara" reusable step if "Totara Account Expandable Menu" "element is present"
    And Browser deletes cookies
    Then I execute "Login as Admin Totara" reusable step
    #Add to Test Group 1:
    And I click on the "ICAEW Accounting Full" "element by title"
    And I click on the "Totara Left Menu Users link" link by JS
    And I click on the "Totara Left Menu Groups link" button by JS
    And I wait for "5" seconds
    And I click on the "Test Group 1" "Dropdown Option Contains text"
    And I click on the "Add/remove users" "Totara button"
    And I set "EC_AUTO_EMAIL" text to the "removeselect_searchtext" "element by name"
    And I click on the "Totara Potential Member first option" element
    And I click on the "Remove" "element by title"
    #Admin Logout
    Then I execute "Logout as Admin Totara" reusable step
    And Browser deletes cookies
    And I wait for "4" seconds
    #Login As student
    When I execute "Log In to Hub as Student" reusable step
    #My Learning Validation
    Then I should see the "You are now enrolled onto ICAEW Accounting Full" message
    #Click Most Recent Course
    When I click on the "ICAEW Accounting Full" "Build Empire My Learning Right Block Course name"
    And I validate text "NOT_CONTAINS=Automation Restrictions Group A" to be displayed for "Hub Course Page Topics Name validation" element
    And I validate text "NOT_CONTAINS=This Is Group A" to be displayed for "Hub Course Page Topics Name validation" element

  @Positive #TC-1622
  Scenario: Topic Restrictions Course Overview by Date
    #Register New Student
    And I execute "Register New Student Account" reusable step
    Then I execute "Harakirimail Verify Email" reusable step
    Then I execute "Log In to Hub as Student" reusable step
    #Validate Timeline block
    Then I should see the "Timeline" message
    Then I should see the "Welcome to your new logged in area." message
    #When I execute "Log Out from Hub Student" reusable step
    When I am on "MD_COMMON_LINKS_TOTARALOGINURL" URL
    And I execute "Logout as Admin Totara" reusable step if "Totara Account Expandable Menu" "element is present"
    And Browser deletes cookies
    Then I execute "Login as Admin Totara" reusable step
    #Enrol to a course
    When I click on the "Totara ICAEW Course" link by JS
    And I click on the "Totara Left Menu Users link" link by JS
    And I click on the "Totara Enrolled Users button" button by JS
    And I wait for "3" seconds
    And I click on the "Totara Enrol User button" button by JS
    And I set "EC_AUTO_EMAIL" text to the element with ID "enrolusersearch" using JS
    And I click on the "Totara Enrol User Search button" button by JS
    And I click on the "Totara Enrol Button" button by JS
    And I click on the "Totara Finish Enroling Users button" button by JS
    #Add Date Restriction
    And I click on the "ICAEW Accounting Full" "element by title"
    And I click on the "Turn editing on" "BPP Digital Admin Value attribute button"
    And I click on the "Totara Automation Restrictionas Date B Edit button" element
    And I click on the "Totara Automation Restrictions Date B Label Edit button" element
    And I click on the "Restrict access" "Build Empire link"
    And I click on the "Add restriction..." "BPP Digital Profile button"
    And I click on the "Date" "BPP Digital Profile button"
    And I click on the "Totara Year Dropdown menu" element
    And I click on the "Totara Year Dropdown Next Year option" element
    And I click on the "Totara Restriction EYE icon" element
    And I click on the "Save changes" "Totara button"
    #Admin Logout
    Then I execute "Logout as Admin Totara" reusable step
    And Browser deletes cookies
    And I wait for "4" seconds
    #Login As student
    When I execute "Log In to Hub as Student" reusable step
    #My Learning Validation
    Then I should see the "You are now enrolled onto ICAEW Accounting Full" message
    #Click Most Recent Course
    When I click on the "ICAEW Accounting Full" "Build Empire My Learning Right Block Course name"
    And I validate text "NOT_CONTAINS=Automation Restrictions Date B" to be displayed for "Hub Course Page Topics Name validation" element
    And I validate text "NOT_CONTAINS=This Is Group B" to be displayed for "Hub Course Page Topics Name validation" element
    #When I execute "Log Out from Hub Student" reusable step
    When I am on "MD_COMMON_LINKS_TOTARALOGINURL" URL
    And I execute "Logout as Admin Totara" reusable step if "Totara Account Expandable Menu" "element is present"
    And Browser deletes cookies
    Then I execute "Login as Admin Totara" reusable step
    #Add Date Restriction
    And I click on the "ICAEW Accounting Full" "element by title"
    And I click on the "Turn editing on" "BPP Digital Admin Value attribute button"
    And I click on the "Totara Automation Restrictionas Date B Edit button" element
    And I click on the "Totara Automation Restrictions Date B Label Edit button" element
    And I click on the "Restrict access" "Build Empire link"
    And I click on the "Delete" "Salesforce cases status option"
    And I click on the "Save changes" "Totara button"
    #Admin Logout
    Then I execute "Logout as Admin Totara" reusable step
    And Browser deletes cookies
    And I wait for "4" seconds
    #Login As student
    When I execute "Log In to Hub as Student" reusable step
    #My Learning Validation
    Then I should see the "You are now enrolled onto ICAEW Accounting Full" message
    #Click Most Recent Course
    When I click on the "ICAEW Accounting Full" "Build Empire My Learning Right Block Course name"
    And I validate text "CONTAINS=Automation Restrictions Date B" to be displayed for "Hub Course Page Topics Name validation" element
    And I validate text "CONTAINS=This is Group B" to be displayed for "Hub Course Page Topics Name validation" element

  @Positive #TC-1623
  Scenario: Topic Restrictions Course Overview by Activity Completion
    #Register New Student
    And I execute "Register New Student Account" reusable step
    Then I execute "Harakirimail Verify Email" reusable step
    Then I execute "Log In to Hub as Student" reusable step
    #Validate Timeline block
    Then I should see the "Timeline" message
    Then I should see the "Welcome to your new logged in area." message
    #When I execute "Log Out from Hub Student" reusable step
    When I am on "MD_COMMON_LINKS_TOTARALOGINURL" URL
    And I execute "Logout as Admin Totara" reusable step if "Totara Account Expandable Menu" "element is present"
    And Browser deletes cookies
    Then I execute "Login as Admin Totara" reusable step
    #Enrol to a course
    When I click on the "Totara ICAEW Course" link by JS
    And I click on the "Totara Left Menu Users link" link by JS
    And I click on the "Totara Enrolled Users button" button by JS
    And I wait for "3" seconds
    And I click on the "Totara Enrol User button" button by JS
    And I set "EC_AUTO_EMAIL" text to the element with ID "enrolusersearch" using JS
    And I click on the "Totara Enrol User Search button" button by JS
    And I click on the "Totara Enrol Button" button by JS
    And I click on the "Totara Finish Enroling Users button" button by JS
    #Admin Logout
    Then I execute "Logout as Admin Totara" reusable step
    And Browser deletes cookies
    And I wait for "4" seconds
    #Login As student
    When I execute "Log In to Hub as Student" reusable step
    #My Learning Validation
    Then I should see the "You are now enrolled onto ICAEW Accounting Full" message
    #Click Most Recent Course
    When I click on the "ICAEW Accounting Full" "Build Empire My Learning Right Block Course name"
    And I validate text "CONTAINS=Automation Restrictions Completion C" to be displayed for "Hub Course Page Topics Name validation" element
    And I validate text "CONTAINS=This is Group C" to be displayed for "Hub Course Page Topics Name validation" element
    And I click on the "BPP Digital Student Icaew Course Page Open Automation Restrictions C Topic Sticker" element
    And I click on the "BPP Digital Student Sub Topic Quiz svg element" element
    When I click on the "Direct App My Learning Left Menu Link" link by JS
    And I wait for "3" seconds
    And Browser performs "Refresh" command
    And I wait for "15" seconds
    When I click on the "Direct App First Course link" link
    And I validate text "NOT_CONTAINS=Automation Restrictions Completion C" to be displayed for "Hub Course Page Topics Name validation" element
    And I validate text "NOT_CONTAINS=This is Group C" to be displayed for "Hub Course Page Topics Name validation" element

  @Positive #TC-1624
  Scenario: Topic Restrictions Course Overview by Multiple Restrictions
    #Register New Student
    And I execute "Register New Student Account" reusable step
    Then I execute "Harakirimail Verify Email" reusable step
    Then I execute "Log In to Hub as Student" reusable step
    #Validate Timeline block
    Then I should see the "Timeline" message
    Then I should see the "Welcome to your new logged in area." message
    #When I execute "Log Out from Hub Student" reusable step
    When I am on "MD_COMMON_LINKS_TOTARALOGINURL" URL
    And I execute "Logout as Admin Totara" reusable step if "Totara Account Expandable Menu" "element is present"
    And Browser deletes cookies
    Then I execute "Login as Admin Totara" reusable step
    #Enrol to a course
    When I click on the "Totara ICAEW Course" link by JS
    And I click on the "Totara Left Menu Users link" link by JS
    And I click on the "Totara Enrolled Users button" button by JS
    And I wait for "3" seconds
    And I click on the "Totara Enrol User button" button by JS
    And I set "EC_AUTO_EMAIL" text to the element with ID "enrolusersearch" using JS
    And I click on the "Totara Enrol User Search button" button by JS
    And I click on the "Totara Enrol Button" button by JS
    And I click on the "Totara Finish Enroling Users button" button by JS
    #Admin Logout
    Then I execute "Logout as Admin Totara" reusable step
    And Browser deletes cookies
    And I wait for "4" seconds
    #Login As student
    When I execute "Log In to Hub as Student" reusable step
    #My Learning Validation
    Then I should see the "You are now enrolled onto ICAEW Accounting Full" message
    #Click Most Recent Course
    When I click on the "ICAEW Accounting Full" "Build Empire My Learning Right Block Course name"
    And I validate text "CONTAINS=Automation Restrictions Multiple D" to be displayed for "Hub Course Page Topics Name validation" element
    And I click on the "BPP Digital Student Icaew Course Page Open Automation Restrictions D Topic Sticker" element
    And I click on the "BPP Digital Student Sub Topic Quiz svg element" element
    When I click on the "Direct App My Learning Left Menu Link" link by JS
    And I wait for "3" seconds
    And Browser performs "Refresh" command
    And I wait for "15" seconds
    When I click on the "Direct App First Course link" link
    And I validate text "CONTAINS=Automation Restrictions Multiple D" to be displayed for "Hub Course Page Topics Name validation" element
    #When I execute "Log Out from Hub Student" reusable step
    When I am on "MD_COMMON_LINKS_TOTARALOGINURL" URL
    And I execute "Logout as Admin Totara" reusable step if "Totara Account Expandable Menu" "element is present"
    And Browser deletes cookies
    Then I execute "Login as Admin Totara" reusable step
    #Add to Test Group 1:
    When I click on the "Totara ICAEW Course" link by JS
    And I click on the "Totara Left Menu Users link" link by JS
    And I click on the "Totara Left Menu Groups link" button by JS
    And I wait for "5" seconds
    And I click on the "Test Group 1" "Dropdown Option Contains text"
    And I click on the "Add/remove users" "Totara button"
    And I set "EC_AUTO_EMAIL" text to the "addselect_searchtext" "element by name"
    And I click on the "Totara Potential Member first option" element
    And I click on the "Add" "element by title"
    #Admin Logout
    Then I execute "Logout as Admin Totara" reusable step
    And Browser deletes cookies
    And I wait for "4" seconds
    #Login As student
    When I execute "Log In to Hub as Student" reusable step
    #My Learning Validation
    Then I should see the "You are now enrolled onto ICAEW Accounting Full" message
    #Click Most Recent Course
    When I click on the "ICAEW Accounting Full" "Build Empire My Learning Right Block Course name"
    And I validate text "NOT_CONTAINS=Automation Restrictions Multiple D" to be displayed for "Hub Course Page Topics Name validation" element
    #############################
    #When I execute "Log Out from Hub Student" reusable step
    When I am on "MD_COMMON_LINKS_TOTARALOGINURL" URL
    And I execute "Logout as Admin Totara" reusable step if "Totara Account Expandable Menu" "element is present"
    And Browser deletes cookies
    Then I execute "Login as Admin Totara" reusable step
    #Add to Test Group 1:
    And I click on the "ICAEW Accounting Full" "element by title"
    And I click on the "Totara Left Menu Users link" link by JS
    And I click on the "Totara Left Menu Groups link" button by JS
    And I wait for "5" seconds
    And I click on the "Test Group 1" "Dropdown Option Contains text"
    And I click on the "Add/remove users" "Totara button"
    And I set "EC_AUTO_EMAIL" text to the "removeselect_searchtext" "element by name"
    And I click on the "Totara Potential Member first option" element
    And I click on the "Remove" "element by title"
    #Admin Logout
    Then I execute "Logout as Admin Totara" reusable step
    And Browser deletes cookies
    And I wait for "4" seconds